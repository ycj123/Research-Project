// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.extension;

import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.codehaus.plexus.PlexusContainerException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.project.MavenProject;
import org.apache.maven.model.Extension;

public interface ExtensionManager
{
    void addExtension(final Extension p0, final MavenProject p1, final ArtifactRepository p2) throws ArtifactResolutionException, PlexusContainerException, ArtifactNotFoundException;
    
    void registerWagons();
}
