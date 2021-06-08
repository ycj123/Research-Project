// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.version;

import org.apache.maven.plugin.InvalidPluginException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.settings.Settings;
import org.apache.maven.project.MavenProject;

public interface PluginVersionManager
{
    public static final String ROLE = ((PluginVersionManager$1.class$org$apache$maven$plugin$version$PluginVersionManager == null) ? (PluginVersionManager$1.class$org$apache$maven$plugin$version$PluginVersionManager = PluginVersionManager$1.class$("org.apache.maven.plugin.version.PluginVersionManager")) : PluginVersionManager$1.class$org$apache$maven$plugin$version$PluginVersionManager).getName();
    
    String resolvePluginVersion(final String p0, final String p1, final MavenProject p2, final Settings p3, final ArtifactRepository p4) throws PluginVersionResolutionException, InvalidPluginException, PluginVersionNotFoundException;
    
    String resolveReportPluginVersion(final String p0, final String p1, final MavenProject p2, final Settings p3, final ArtifactRepository p4) throws PluginVersionResolutionException, InvalidPluginException, PluginVersionNotFoundException;
}
