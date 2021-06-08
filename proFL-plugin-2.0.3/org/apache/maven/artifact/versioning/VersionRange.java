// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.artifact.versioning;

import org.apache.maven.artifact.Artifact;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class VersionRange
{
    private final ArtifactVersion recommendedVersion;
    private final List restrictions;
    
    private VersionRange(final ArtifactVersion recommendedVersion, final List restrictions) {
        this.recommendedVersion = recommendedVersion;
        this.restrictions = restrictions;
    }
    
    public ArtifactVersion getRecommendedVersion() {
        return this.recommendedVersion;
    }
    
    public List getRestrictions() {
        return this.restrictions;
    }
    
    public VersionRange cloneOf() {
        List copiedRestrictions = null;
        if (this.restrictions != null) {
            copiedRestrictions = new ArrayList();
            if (!this.restrictions.isEmpty()) {
                copiedRestrictions.addAll(this.restrictions);
            }
        }
        return new VersionRange(this.recommendedVersion, copiedRestrictions);
    }
    
    public static VersionRange createFromVersionSpec(final String spec) throws InvalidVersionSpecificationException {
        if (spec == null) {
            return null;
        }
        final List restrictions = new ArrayList();
        String process = spec;
        ArtifactVersion version = null;
        ArtifactVersion upperBound = null;
        ArtifactVersion lowerBound = null;
        while (process.startsWith("[") || process.startsWith("(")) {
            final int index1 = process.indexOf(")");
            final int index2 = process.indexOf("]");
            int index3;
            if (((index3 = index2) < 0 || index1 < index2) && index1 >= 0) {
                index3 = index1;
            }
            if (index3 < 0) {
                throw new InvalidVersionSpecificationException("Unbounded range: " + spec);
            }
            final Restriction restriction = parseRestriction(process.substring(0, index3 + 1));
            if (lowerBound == null) {
                lowerBound = restriction.getLowerBound();
            }
            if (upperBound != null && (restriction.getLowerBound() == null || restriction.getLowerBound().compareTo(upperBound) < 0)) {
                throw new InvalidVersionSpecificationException("Ranges overlap: " + spec);
            }
            restrictions.add(restriction);
            upperBound = restriction.getUpperBound();
            process = process.substring(index3 + 1).trim();
            if (process.length() <= 0 || !process.startsWith(",")) {
                continue;
            }
            process = process.substring(1).trim();
        }
        if (process.length() > 0) {
            if (restrictions.size() > 0) {
                throw new InvalidVersionSpecificationException("Only fully-qualified sets allowed in multiple set scenario: " + spec);
            }
            version = new DefaultArtifactVersion(process);
            restrictions.add(Restriction.EVERYTHING);
        }
        return new VersionRange(version, restrictions);
    }
    
    private static Restriction parseRestriction(final String spec) throws InvalidVersionSpecificationException {
        final boolean lowerBoundInclusive = spec.startsWith("[");
        final boolean upperBoundInclusive = spec.endsWith("]");
        final String process = spec.substring(1, spec.length() - 1).trim();
        final int index = process.indexOf(",");
        Restriction restriction;
        if (index < 0) {
            if (!lowerBoundInclusive || !upperBoundInclusive) {
                throw new InvalidVersionSpecificationException("Single version must be surrounded by []: " + spec);
            }
            final ArtifactVersion version = new DefaultArtifactVersion(process);
            restriction = new Restriction(version, lowerBoundInclusive, version, upperBoundInclusive);
        }
        else {
            final String lowerBound = process.substring(0, index).trim();
            final String upperBound = process.substring(index + 1).trim();
            if (lowerBound.equals(upperBound)) {
                throw new InvalidVersionSpecificationException("Range cannot have identical boundaries: " + spec);
            }
            ArtifactVersion lowerVersion = null;
            if (lowerBound.length() > 0) {
                lowerVersion = new DefaultArtifactVersion(lowerBound);
            }
            ArtifactVersion upperVersion = null;
            if (upperBound.length() > 0) {
                upperVersion = new DefaultArtifactVersion(upperBound);
            }
            if (upperVersion != null && lowerVersion != null && upperVersion.compareTo(lowerVersion) < 0) {
                throw new InvalidVersionSpecificationException("Range defies version ordering: " + spec);
            }
            restriction = new Restriction(lowerVersion, lowerBoundInclusive, upperVersion, upperBoundInclusive);
        }
        return restriction;
    }
    
    public static VersionRange createFromVersion(final String version) {
        return new VersionRange(new DefaultArtifactVersion(version), Collections.EMPTY_LIST);
    }
    
    public VersionRange restrict(final VersionRange restriction) {
        final List r1 = this.restrictions;
        final List r2 = restriction.restrictions;
        List restrictions;
        if (r1.isEmpty() || r2.isEmpty()) {
            restrictions = Collections.EMPTY_LIST;
        }
        else {
            restrictions = this.intersection(r1, r2);
        }
        ArtifactVersion version = null;
        if (restrictions.size() > 0) {
            boolean found = false;
            final Iterator i = restrictions.iterator();
            while (i.hasNext() && !found) {
                final Restriction r3 = i.next();
                if (this.recommendedVersion != null && r3.containsVersion(this.recommendedVersion)) {
                    version = this.recommendedVersion;
                    found = true;
                }
                else {
                    if (version != null || restriction.getRecommendedVersion() == null || !r3.containsVersion(restriction.getRecommendedVersion())) {
                        continue;
                    }
                    version = restriction.getRecommendedVersion();
                }
            }
        }
        else if (this.recommendedVersion != null) {
            version = this.recommendedVersion;
        }
        else if (restriction.recommendedVersion != null) {
            version = restriction.recommendedVersion;
        }
        return new VersionRange(version, restrictions);
    }
    
    private List intersection(final List r1, final List r2) {
        final List restrictions = new ArrayList(r1.size() + r2.size());
        final Iterator i1 = r1.iterator();
        final Iterator i2 = r2.iterator();
        Restriction res1 = i1.next();
        Restriction res2 = i2.next();
        boolean done = false;
        while (!done) {
            if (res1.getLowerBound() == null || res2.getUpperBound() == null || res1.getLowerBound().compareTo(res2.getUpperBound()) <= 0) {
                if (res1.getUpperBound() == null || res2.getLowerBound() == null || res1.getUpperBound().compareTo(res2.getLowerBound()) >= 0) {
                    ArtifactVersion lower;
                    boolean lowerInclusive;
                    if (res1.getLowerBound() == null) {
                        lower = res2.getLowerBound();
                        lowerInclusive = res2.isLowerBoundInclusive();
                    }
                    else if (res2.getLowerBound() == null) {
                        lower = res1.getLowerBound();
                        lowerInclusive = res1.isLowerBoundInclusive();
                    }
                    else {
                        final int comparison = res1.getLowerBound().compareTo(res2.getLowerBound());
                        if (comparison < 0) {
                            lower = res2.getLowerBound();
                            lowerInclusive = res2.isLowerBoundInclusive();
                        }
                        else if (comparison == 0) {
                            lower = res1.getLowerBound();
                            lowerInclusive = (res1.isLowerBoundInclusive() && res2.isLowerBoundInclusive());
                        }
                        else {
                            lower = res1.getLowerBound();
                            lowerInclusive = res1.isLowerBoundInclusive();
                        }
                    }
                    ArtifactVersion upper;
                    boolean upperInclusive;
                    if (res1.getUpperBound() == null) {
                        upper = res2.getUpperBound();
                        upperInclusive = res2.isUpperBoundInclusive();
                    }
                    else if (res2.getUpperBound() == null) {
                        upper = res1.getUpperBound();
                        upperInclusive = res1.isUpperBoundInclusive();
                    }
                    else {
                        final int comparison = res1.getUpperBound().compareTo(res2.getUpperBound());
                        if (comparison < 0) {
                            upper = res1.getUpperBound();
                            upperInclusive = res1.isUpperBoundInclusive();
                        }
                        else if (comparison == 0) {
                            upper = res1.getUpperBound();
                            upperInclusive = (res1.isUpperBoundInclusive() && res2.isUpperBoundInclusive());
                        }
                        else {
                            upper = res2.getUpperBound();
                            upperInclusive = res2.isUpperBoundInclusive();
                        }
                    }
                    if (lower == null || upper == null || lower.compareTo(upper) != 0) {
                        restrictions.add(new Restriction(lower, lowerInclusive, upper, upperInclusive));
                    }
                    else if (lowerInclusive && upperInclusive) {
                        restrictions.add(new Restriction(lower, lowerInclusive, upper, upperInclusive));
                    }
                    if (upper == res2.getUpperBound()) {
                        if (i2.hasNext()) {
                            res2 = i2.next();
                        }
                        else {
                            done = true;
                        }
                    }
                    else if (i1.hasNext()) {
                        res1 = i1.next();
                    }
                    else {
                        done = true;
                    }
                }
                else if (i1.hasNext()) {
                    res1 = i1.next();
                }
                else {
                    done = true;
                }
            }
            else if (i2.hasNext()) {
                res2 = i2.next();
            }
            else {
                done = true;
            }
        }
        return restrictions;
    }
    
    public ArtifactVersion getSelectedVersion(final Artifact artifact) throws OverConstrainedVersionException {
        ArtifactVersion version;
        if (this.recommendedVersion != null) {
            version = this.recommendedVersion;
        }
        else {
            if (this.restrictions.size() == 0) {
                throw new OverConstrainedVersionException("The artifact has no valid ranges", artifact);
            }
            version = null;
        }
        return version;
    }
    
    public boolean isSelectedVersionKnown(final Artifact artifact) throws OverConstrainedVersionException {
        boolean value = false;
        if (this.recommendedVersion != null) {
            value = true;
        }
        else if (this.restrictions.size() == 0) {
            throw new OverConstrainedVersionException("The artifact has no valid ranges", artifact);
        }
        return value;
    }
    
    @Override
    public String toString() {
        if (this.recommendedVersion != null) {
            return this.recommendedVersion.toString();
        }
        final StringBuffer buf = new StringBuffer();
        final Iterator i = this.restrictions.iterator();
        while (i.hasNext()) {
            final Restriction r = i.next();
            buf.append(r.toString());
            if (i.hasNext()) {
                buf.append(",");
            }
        }
        return buf.toString();
    }
    
    public ArtifactVersion matchVersion(final List versions) {
        ArtifactVersion matched = null;
        for (final ArtifactVersion version : versions) {
            if (this.containsVersion(version) && (matched == null || version.compareTo(matched) > 0)) {
                matched = version;
            }
        }
        return matched;
    }
    
    public boolean containsVersion(final ArtifactVersion version) {
        for (final Restriction restriction : this.restrictions) {
            if (restriction.containsVersion(version)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasRestrictions() {
        return !this.restrictions.isEmpty() && this.recommendedVersion == null;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VersionRange)) {
            return false;
        }
        final VersionRange other = (VersionRange)obj;
        boolean equals = this.recommendedVersion == other.recommendedVersion || (this.recommendedVersion != null && this.recommendedVersion.equals(other.recommendedVersion));
        equals &= (this.restrictions == other.restrictions || (this.restrictions != null && this.restrictions.equals(other.restrictions)));
        return equals;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + ((this.recommendedVersion == null) ? 0 : this.recommendedVersion.hashCode());
        hash = 31 * hash + ((this.restrictions == null) ? 0 : this.restrictions.hashCode());
        return hash;
    }
}
