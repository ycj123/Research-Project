// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import java.util.List;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.AbstractArtifactResolutionException;

public class PluginNotFoundException extends AbstractArtifactResolutionException
{
    public PluginNotFoundException(final ArtifactNotFoundException e) {
        super("Plugin could not be found - check that the goal name is correct: " + e.getMessage(), e.getGroupId(), e.getArtifactId(), e.getVersion(), "maven-plugin", null, e.getRemoteRepositories(), null, e.getCause());
    }
}
