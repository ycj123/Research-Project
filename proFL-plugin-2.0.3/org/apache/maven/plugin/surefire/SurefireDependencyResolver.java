// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import org.apache.maven.surefire.booter.Classpath;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import java.util.Set;
import org.apache.maven.artifact.resolver.filter.ExcludesArtifactFilter;
import java.util.Collections;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.versioning.OverConstrainedVersionException;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.VersionRange;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import java.util.List;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.resolver.ArtifactResolver;

public class SurefireDependencyResolver
{
    private final ArtifactResolver artifactResolver;
    private final ArtifactFactory artifactFactory;
    private final Log log;
    private final ArtifactRepository localRepository;
    private final List<ArtifactRepository> remoteRepositories;
    private final ArtifactMetadataSource artifactMetadataSource;
    private final String pluginName;
    
    protected SurefireDependencyResolver(final ArtifactResolver artifactResolver, final ArtifactFactory artifactFactory, final Log log, final ArtifactRepository localRepository, final List<ArtifactRepository> remoteRepositories, final ArtifactMetadataSource artifactMetadataSource, final String pluginName) {
        this.artifactResolver = artifactResolver;
        this.artifactFactory = artifactFactory;
        this.log = log;
        this.localRepository = localRepository;
        this.remoteRepositories = remoteRepositories;
        this.artifactMetadataSource = artifactMetadataSource;
        this.pluginName = pluginName;
    }
    
    public boolean isWithinVersionSpec(@Nullable final Artifact artifact, @Nonnull final String versionSpec) {
        if (artifact == null) {
            return false;
        }
        try {
            final VersionRange range = VersionRange.createFromVersionSpec(versionSpec);
            try {
                return range.containsVersion(artifact.getSelectedVersion());
            }
            catch (NullPointerException e) {
                return range.containsVersion(new DefaultArtifactVersion(artifact.getBaseVersion()));
            }
        }
        catch (InvalidVersionSpecificationException e2) {
            throw new RuntimeException("Bug in plugin. Please report with stacktrace");
        }
        catch (OverConstrainedVersionException e3) {
            throw new RuntimeException("Bug in plugin. Please report with stacktrace");
        }
    }
    
    public ArtifactResolutionResult resolveArtifact(@Nullable final Artifact filteredArtifact, final Artifact providerArtifact) throws ArtifactResolutionException, ArtifactNotFoundException {
        ArtifactFilter filter = null;
        if (filteredArtifact != null) {
            filter = new ExcludesArtifactFilter(Collections.singletonList(filteredArtifact.getGroupId() + ":" + filteredArtifact.getArtifactId()));
        }
        final Artifact originatingArtifact = this.artifactFactory.createBuildArtifact("dummy", "dummy", "1.0", "jar");
        return this.artifactResolver.resolveTransitively(Collections.singleton(providerArtifact), originatingArtifact, this.localRepository, this.remoteRepositories, this.artifactMetadataSource, filter);
    }
    
    public Classpath getProviderClasspath(final String provider, final String version, final Artifact filteredArtifact) throws ArtifactNotFoundException, ArtifactResolutionException {
        Classpath classPath = ClasspathCache.getCachedClassPath(provider);
        if (classPath == null) {
            final Artifact providerArtifact = this.artifactFactory.createDependencyArtifact("org.apache.maven.surefire", provider, VersionRange.createFromVersion(version), "jar", null, "test");
            final ArtifactResolutionResult result = this.resolveArtifact(filteredArtifact, providerArtifact);
            final List<String> files = new ArrayList<String>();
            for (final Object o : result.getArtifacts()) {
                final Artifact artifact = (Artifact)o;
                this.log.debug("Adding to " + this.pluginName + " test classpath: " + artifact.getFile().getAbsolutePath() + " Scope: " + artifact.getScope());
                files.add(artifact.getFile().getAbsolutePath());
            }
            classPath = new Classpath(files);
            ClasspathCache.setCachedClasspath(provider, classPath);
        }
        return classPath;
    }
    
    public Classpath addProviderToClasspath(final Map<String, Artifact> pluginArtifactMap, final Artifact surefireArtifact) throws ArtifactResolutionException, ArtifactNotFoundException {
        final List<String> files = new ArrayList<String>();
        if (surefireArtifact != null) {
            final ArtifactResolutionResult artifactResolutionResult = this.resolveArtifact(null, surefireArtifact);
            for (final Artifact artifact : pluginArtifactMap.values()) {
                if (!artifactResolutionResult.getArtifacts().contains(artifact)) {
                    files.add(artifact.getFile().getAbsolutePath());
                }
            }
        }
        else {
            for (final Artifact artifact2 : pluginArtifactMap.values()) {
                files.add(artifact2.getFile().getPath());
            }
        }
        return new Classpath(files);
    }
}
