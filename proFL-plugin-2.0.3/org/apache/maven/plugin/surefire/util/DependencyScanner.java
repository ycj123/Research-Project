// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.util;

import javax.annotation.Nullable;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.MatchPatterns;
import org.apache.maven.artifact.Artifact;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.Iterator;
import java.io.IOException;
import org.apache.maven.plugin.MojoExecutionException;
import java.util.Collection;
import java.util.ArrayList;
import org.apache.maven.surefire.util.DefaultScanResult;
import javax.annotation.Nonnull;
import java.io.File;
import java.util.List;

public class DependencyScanner
{
    private final List<File> dependenciesToScan;
    protected final List<String> includes;
    @Nonnull
    protected final List<String> excludes;
    protected final List<String> specificTests;
    
    public DependencyScanner(final List<File> dependenciesToScan, final List<String> includes, @Nonnull final List<String> excludes, final List<String> specificTests) {
        this.dependenciesToScan = dependenciesToScan;
        this.includes = includes;
        this.excludes = excludes;
        this.specificTests = specificTests;
    }
    
    public DefaultScanResult scan() throws MojoExecutionException {
        final Matcher matcher = new Matcher(this.includes, this.excludes, this.specificTests);
        final List<String> found = new ArrayList<String>();
        for (final File artifact : this.dependenciesToScan) {
            try {
                found.addAll(this.scanArtifact(artifact, matcher));
            }
            catch (IOException e) {
                throw new MojoExecutionException("Could not scan dependency " + artifact.toString(), e);
            }
        }
        return new DefaultScanResult(found);
    }
    
    private List<String> scanArtifact(final File artifact, final Matcher matcher) throws IOException {
        final List<String> found = new ArrayList<String>();
        if (artifact != null && artifact.isFile()) {
            JarFile jar = null;
            try {
                jar = new JarFile(artifact);
                final Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    final JarEntry entry = entries.nextElement();
                    if (matcher.shouldInclude(entry.getName())) {
                        found.add(ScannerUtil.convertJarFileResourceToJavaClassName(entry.getName()));
                    }
                }
            }
            finally {
                if (jar != null) {
                    jar.close();
                }
            }
        }
        return found;
    }
    
    public static List<File> filter(final List<Artifact> artifacts, final List<String> groupArtifactIds) {
        final List<File> matches = new ArrayList<File>();
        if (groupArtifactIds == null || artifacts == null) {
            return matches;
        }
        for (final Artifact artifact : artifacts) {
            for (final String groups : groupArtifactIds) {
                final String[] groupArtifact = groups.split(":");
                if (groupArtifact.length != 2) {
                    throw new IllegalArgumentException("dependencyToScan argument should be in format 'groupid:artifactid': " + groups);
                }
                if (!artifact.getGroupId().matches(groupArtifact[0]) || !artifact.getArtifactId().matches(groupArtifact[1])) {
                    continue;
                }
                matches.add(artifact.getFile());
            }
        }
        return matches;
    }
    
    private class Matcher
    {
        private MatchPatterns includes;
        private MatchPatterns excludes;
        private SpecificFileFilter specificTestFilter;
        
        public Matcher(@Nonnull final List<String> includes, @Nullable final List<String> excludes, final List<String> specificTests) {
            final String[] specific = (specificTests == null) ? new String[0] : ScannerUtil.processIncludesExcludes(specificTests);
            this.specificTestFilter = new SpecificFileFilter(specific);
            if (includes != null && includes.size() > 0) {
                this.includes = MatchPatterns.from(ScannerUtil.processIncludesExcludes(includes));
            }
            else {
                this.includes = MatchPatterns.from("**");
            }
            this.excludes = MatchPatterns.from(ScannerUtil.processIncludesExcludes(excludes));
        }
        
        public boolean shouldInclude(String name) {
            if (!name.endsWith(".class")) {
                return false;
            }
            name = ScannerUtil.convertSlashToSystemFileSeparator(name);
            final boolean isIncluded = this.includes.matches(name, false);
            final boolean isExcluded = this.excludes.matches(name, false);
            return isIncluded && !isExcluded && this.specificTestFilter.accept(name);
        }
    }
}
