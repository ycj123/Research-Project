// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

public final class RequirementMatcherFactory
{
    private RequirementMatcherFactory() {
    }
    
    public static RequirementMatcher createExactMatcher(final String provideValue) {
        return new ExactMatcher(provideValue);
    }
    
    public static RequirementMatcher createVersionMatcher(final String provideValue) {
        return new VersionMatcher(provideValue);
    }
    
    private static final class ExactMatcher implements RequirementMatcher
    {
        private String provides;
        
        private ExactMatcher(final String provides) {
            this.provides = provides;
        }
        
        public boolean matches(final String requirement) {
            return this.provides.equalsIgnoreCase(requirement);
        }
    }
    
    private static final class VersionMatcher implements RequirementMatcher
    {
        DefaultArtifactVersion version;
        
        private VersionMatcher(final String version) {
            this.version = new DefaultArtifactVersion(version);
        }
        
        public boolean matches(final String requirement) {
            try {
                final VersionRange range = VersionRange.createFromVersionSpec(requirement);
                if (range.hasRestrictions()) {
                    return range.containsVersion(this.version);
                }
                return range.getRecommendedVersion().compareTo(this.version) == 0;
            }
            catch (InvalidVersionSpecificationException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
}
