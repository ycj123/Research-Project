// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import java.util.Map;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.apache.maven.model.ReportPlugin;
import org.apache.maven.plugin.version.PluginVersionNotFoundException;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.plugin.version.PluginVersionResolutionException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.settings.Settings;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.reporting.MavenReport;
import org.apache.maven.project.artifact.InvalidDependencyVersionException;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

public interface PluginManager
{
    public static final String ROLE = ((PluginManager$1.class$org$apache$maven$plugin$PluginManager == null) ? (PluginManager$1.class$org$apache$maven$plugin$PluginManager = PluginManager$1.class$("org.apache.maven.plugin.PluginManager")) : PluginManager$1.class$org$apache$maven$plugin$PluginManager).getName();
    
    void executeMojo(final MavenProject p0, final MojoExecution p1, final MavenSession p2) throws MojoExecutionException, ArtifactResolutionException, MojoFailureException, ArtifactNotFoundException, InvalidDependencyVersionException, PluginManagerException, PluginConfigurationException;
    
    MavenReport getReport(final MavenProject p0, final MojoExecution p1, final MavenSession p2) throws ArtifactNotFoundException, PluginConfigurationException, PluginManagerException, ArtifactResolutionException;
    
    PluginDescriptor getPluginDescriptorForPrefix(final String p0);
    
    Plugin getPluginDefinitionForPrefix(final String p0, final MavenSession p1, final MavenProject p2);
    
    PluginDescriptor verifyPlugin(final Plugin p0, final MavenProject p1, final Settings p2, final ArtifactRepository p3) throws ArtifactResolutionException, PluginVersionResolutionException, ArtifactNotFoundException, InvalidVersionSpecificationException, InvalidPluginException, PluginManagerException, PluginNotFoundException, PluginVersionNotFoundException;
    
    PluginDescriptor verifyReportPlugin(final ReportPlugin p0, final MavenProject p1, final MavenSession p2) throws PluginVersionResolutionException, ArtifactResolutionException, ArtifactNotFoundException, InvalidVersionSpecificationException, InvalidPluginException, PluginManagerException, PluginNotFoundException, PluginVersionNotFoundException;
    
    Object getPluginComponent(final Plugin p0, final String p1, final String p2) throws PluginManagerException, ComponentLookupException;
    
    Map getPluginComponents(final Plugin p0, final String p1) throws ComponentLookupException, PluginManagerException;
}
