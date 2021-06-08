// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import org.apache.maven.model.Plugin;
import org.apache.maven.artifact.repository.ArtifactRepository;
import java.util.List;

public interface PluginMappingManager
{
    Plugin getByPrefix(final String p0, final List p1, final List p2, final ArtifactRepository p3);
}
