// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.artifact.filter;

import java.util.Collection;
import org.codehaus.plexus.logging.Logger;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.artifact.ArtifactUtils;
import org.apache.maven.artifact.Artifact;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;

public class PatternIncludesArtifactFilter implements ArtifactFilter, StatisticsReportingArtifactFilter
{
    private final List positivePatterns;
    private final List negativePatterns;
    private final boolean actTransitively;
    private final Set patternsTriggered;
    private final List filteredArtifactIds;
    
    public PatternIncludesArtifactFilter(final List patterns) {
        this(patterns, false);
    }
    
    public PatternIncludesArtifactFilter(final List patterns, final boolean actTransitively) {
        this.patternsTriggered = new HashSet();
        this.filteredArtifactIds = new ArrayList();
        this.actTransitively = actTransitively;
        final List pos = new ArrayList();
        final List neg = new ArrayList();
        if (patterns != null && !patterns.isEmpty()) {
            for (final String pattern : patterns) {
                if (pattern.startsWith("!")) {
                    neg.add(pattern.substring(1));
                }
                else {
                    pos.add(pattern);
                }
            }
        }
        this.positivePatterns = pos;
        this.negativePatterns = neg;
    }
    
    public boolean include(final Artifact artifact) {
        final boolean shouldInclude = this.patternMatches(artifact);
        if (!shouldInclude) {
            this.addFilteredArtifactId(artifact.getId());
        }
        return shouldInclude;
    }
    
    protected boolean patternMatches(final Artifact artifact) {
        return this.positiveMatch(artifact) == Boolean.TRUE || this.negativeMatch(artifact) == Boolean.FALSE;
    }
    
    protected void addFilteredArtifactId(final String artifactId) {
        this.filteredArtifactIds.add(artifactId);
    }
    
    private Boolean negativeMatch(final Artifact artifact) {
        if (this.negativePatterns == null || this.negativePatterns.isEmpty()) {
            return null;
        }
        return this.match(artifact, this.negativePatterns);
    }
    
    protected Boolean positiveMatch(final Artifact artifact) {
        if (this.positivePatterns == null || this.positivePatterns.isEmpty()) {
            return null;
        }
        return this.match(artifact, this.positivePatterns);
    }
    
    private boolean match(final Artifact artifact, final List patterns) {
        final String shortId = ArtifactUtils.versionlessKey(artifact);
        final String id = artifact.getDependencyConflictId();
        final String wholeId = artifact.getId();
        if (this.matchAgainst(wholeId, patterns, false)) {
            return true;
        }
        if (this.matchAgainst(id, patterns, false)) {
            return true;
        }
        if (this.matchAgainst(shortId, patterns, false)) {
            return true;
        }
        if (this.actTransitively) {
            final List depTrail = artifact.getDependencyTrail();
            if (depTrail != null && depTrail.size() > 1) {
                for (final String trailItem : depTrail) {
                    if (this.matchAgainst(trailItem, patterns, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean matchAgainst(final String value, final List patterns, final boolean regionMatch) {
        for (final String pattern : patterns) {
            final String[] patternTokens = pattern.split(":");
            final String[] tokens = value.split(":");
            boolean matched = patternTokens.length <= tokens.length;
            for (int i = 0; matched && i < patternTokens.length; matched = this.matches(tokens[i], patternTokens[i]), ++i) {}
            if (!matched && patternTokens.length < tokens.length && patternTokens.length > 0 && "*".equals(patternTokens[0])) {
                matched = true;
                for (int i = 0; matched && i < patternTokens.length; matched = this.matches(tokens[i + (tokens.length - patternTokens.length)], patternTokens[i]), ++i) {}
            }
            if (matched) {
                this.patternsTriggered.add(pattern);
                return true;
            }
            if (regionMatch && value.indexOf(pattern) > -1) {
                this.patternsTriggered.add(pattern);
                return true;
            }
        }
        return false;
    }
    
    private boolean matches(final String token, final String pattern) {
        boolean matches;
        if ("*".equals(pattern) || pattern.length() == 0) {
            matches = true;
        }
        else if (pattern.startsWith("*") && pattern.endsWith("*")) {
            final String contains = pattern.substring(1, pattern.length() - 1);
            matches = (token.indexOf(contains) != -1);
        }
        else if (pattern.startsWith("*")) {
            final String suffix = pattern.substring(1, pattern.length());
            matches = token.endsWith(suffix);
        }
        else if (pattern.endsWith("*")) {
            final String prefix = pattern.substring(0, pattern.length() - 1);
            matches = token.startsWith(prefix);
        }
        else if (pattern.startsWith("[") || pattern.startsWith("(")) {
            matches = this.isVersionIncludedInRange(token, pattern);
        }
        else {
            matches = token.equals(pattern);
        }
        return matches;
    }
    
    private boolean isVersionIncludedInRange(final String version, final String range) {
        try {
            return VersionRange.createFromVersionSpec(range).containsVersion(new DefaultArtifactVersion(version));
        }
        catch (InvalidVersionSpecificationException e) {
            return false;
        }
    }
    
    public void reportMissedCriteria(final Logger logger) {
        if (!this.positivePatterns.isEmpty() || !this.negativePatterns.isEmpty()) {
            final List missed = new ArrayList();
            missed.addAll(this.positivePatterns);
            missed.addAll(this.negativePatterns);
            missed.removeAll(this.patternsTriggered);
            if (!missed.isEmpty() && logger.isWarnEnabled()) {
                final StringBuffer buffer = new StringBuffer();
                buffer.append("The following patterns were never triggered in this ");
                buffer.append(this.getFilterDescription());
                buffer.append(':');
                for (final String pattern : missed) {
                    buffer.append("\no  '").append(pattern).append("'");
                }
                buffer.append("\n");
                logger.warn(buffer.toString());
            }
        }
    }
    
    public String toString() {
        return "Includes filter:" + this.getPatternsAsString();
    }
    
    protected String getPatternsAsString() {
        final StringBuffer buffer = new StringBuffer();
        for (final String pattern : this.positivePatterns) {
            buffer.append("\no '").append(pattern).append("'");
        }
        return buffer.toString();
    }
    
    protected String getFilterDescription() {
        return "artifact inclusion filter";
    }
    
    public void reportFilteredArtifacts(final Logger logger) {
        if (!this.filteredArtifactIds.isEmpty() && logger.isDebugEnabled()) {
            final StringBuffer buffer = new StringBuffer("The following artifacts were removed by this " + this.getFilterDescription() + ": ");
            for (final String artifactId : this.filteredArtifactIds) {
                buffer.append('\n').append(artifactId);
            }
            logger.debug(buffer.toString());
        }
    }
    
    public boolean hasMissedCriteria() {
        if (!this.positivePatterns.isEmpty() || !this.negativePatterns.isEmpty()) {
            final List missed = new ArrayList();
            missed.addAll(this.positivePatterns);
            missed.addAll(this.negativePatterns);
            missed.removeAll(this.patternsTriggered);
            return !missed.isEmpty();
        }
        return false;
    }
}
