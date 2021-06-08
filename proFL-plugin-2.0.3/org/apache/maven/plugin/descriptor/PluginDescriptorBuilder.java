// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.descriptor;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import org.codehaus.plexus.configuration.xml.XmlPlexusConfiguration;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import org.codehaus.plexus.component.repository.ComponentRequirement;
import java.util.List;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.codehaus.plexus.component.repository.ComponentDependency;
import java.util.ArrayList;
import org.codehaus.plexus.configuration.PlexusConfigurationException;
import java.io.Reader;

public class PluginDescriptorBuilder
{
    public PluginDescriptor build(final Reader reader) throws PlexusConfigurationException {
        return this.build(reader, null);
    }
    
    public PluginDescriptor build(final Reader reader, final String source) throws PlexusConfigurationException {
        final PlexusConfiguration c = this.buildConfiguration(reader);
        final PluginDescriptor pluginDescriptor = new PluginDescriptor();
        pluginDescriptor.setSource(source);
        pluginDescriptor.setGroupId(c.getChild("groupId").getValue());
        pluginDescriptor.setArtifactId(c.getChild("artifactId").getValue());
        pluginDescriptor.setVersion(c.getChild("version").getValue());
        pluginDescriptor.setGoalPrefix(c.getChild("goalPrefix").getValue());
        pluginDescriptor.setName(c.getChild("name").getValue());
        pluginDescriptor.setDescription(c.getChild("description").getValue());
        final String isolatedRealm = c.getChild("isolatedRealm").getValue();
        if (isolatedRealm != null) {
            pluginDescriptor.setIsolatedRealm(Boolean.valueOf(isolatedRealm));
        }
        final String inheritedByDefault = c.getChild("inheritedByDefault").getValue();
        if (inheritedByDefault != null) {
            pluginDescriptor.setInheritedByDefault(Boolean.valueOf(inheritedByDefault));
        }
        final PlexusConfiguration[] mojoConfigurations = c.getChild("mojos").getChildren("mojo");
        for (int i = 0; i < mojoConfigurations.length; ++i) {
            final PlexusConfiguration component = mojoConfigurations[i];
            final MojoDescriptor mojoDescriptor = this.buildComponentDescriptor(component, pluginDescriptor);
            pluginDescriptor.addMojo(mojoDescriptor);
        }
        final PlexusConfiguration[] dependencyConfigurations = c.getChild("dependencies").getChildren("dependency");
        final List dependencies = new ArrayList();
        for (int j = 0; j < dependencyConfigurations.length; ++j) {
            final PlexusConfiguration d = dependencyConfigurations[j];
            final ComponentDependency cd = new ComponentDependency();
            cd.setArtifactId(d.getChild("artifactId").getValue());
            cd.setGroupId(d.getChild("groupId").getValue());
            cd.setType(d.getChild("type").getValue());
            cd.setVersion(d.getChild("version").getValue());
            dependencies.add(cd);
        }
        pluginDescriptor.setDependencies(dependencies);
        return pluginDescriptor;
    }
    
    public MojoDescriptor buildComponentDescriptor(final PlexusConfiguration c, final PluginDescriptor pluginDescriptor) throws PlexusConfigurationException {
        final MojoDescriptor mojo = new MojoDescriptor();
        mojo.setPluginDescriptor(pluginDescriptor);
        mojo.setGoal(c.getChild("goal").getValue());
        mojo.setImplementation(c.getChild("implementation").getValue());
        final PlexusConfiguration langConfig = c.getChild("language");
        if (langConfig != null) {
            mojo.setLanguage(langConfig.getValue());
        }
        final PlexusConfiguration configuratorConfig = c.getChild("configurator");
        if (configuratorConfig != null) {
            mojo.setComponentConfigurator(configuratorConfig.getValue());
        }
        final PlexusConfiguration composerConfig = c.getChild("composer");
        if (composerConfig != null) {
            mojo.setComponentComposer(composerConfig.getValue());
        }
        final String since = c.getChild("since").getValue();
        if (since != null) {
            mojo.setSince(since);
        }
        final String phase = c.getChild("phase").getValue();
        if (phase != null) {
            mojo.setPhase(phase);
        }
        final String executePhase = c.getChild("executePhase").getValue();
        if (executePhase != null) {
            mojo.setExecutePhase(executePhase);
        }
        final String executeMojo = c.getChild("executeGoal").getValue();
        if (executeMojo != null) {
            mojo.setExecuteGoal(executeMojo);
        }
        final String executeLifecycle = c.getChild("executeLifecycle").getValue();
        if (executeLifecycle != null) {
            mojo.setExecuteLifecycle(executeLifecycle);
        }
        mojo.setInstantiationStrategy(c.getChild("instantiationStrategy").getValue());
        mojo.setDescription(c.getChild("description").getValue());
        final String dependencyResolution = c.getChild("requiresDependencyResolution").getValue();
        if (dependencyResolution != null) {
            mojo.setDependencyResolutionRequired(dependencyResolution);
        }
        final String directInvocationOnly = c.getChild("requiresDirectInvocation").getValue();
        if (directInvocationOnly != null) {
            mojo.setDirectInvocationOnly(Boolean.valueOf(directInvocationOnly));
        }
        final String requiresProject = c.getChild("requiresProject").getValue();
        if (requiresProject != null) {
            mojo.setProjectRequired(Boolean.valueOf(requiresProject));
        }
        final String requiresReports = c.getChild("requiresReports").getValue();
        if (requiresReports != null) {
            mojo.setRequiresReports(Boolean.valueOf(requiresReports));
        }
        final String aggregator = c.getChild("aggregator").getValue();
        if (aggregator != null) {
            mojo.setAggregator(Boolean.valueOf(aggregator));
        }
        final String requiresOnline = c.getChild("requiresOnline").getValue();
        if (requiresOnline != null) {
            mojo.setOnlineRequired(Boolean.valueOf(requiresOnline));
        }
        final String inheritedByDefault = c.getChild("inheritedByDefault").getValue();
        if (inheritedByDefault != null) {
            mojo.setInheritedByDefault(Boolean.valueOf(inheritedByDefault));
        }
        final PlexusConfiguration[] parameterConfigurations = c.getChild("parameters").getChildren("parameter");
        final List parameters = new ArrayList();
        for (int i = 0; i < parameterConfigurations.length; ++i) {
            final PlexusConfiguration d = parameterConfigurations[i];
            final Parameter parameter = new Parameter();
            parameter.setName(d.getChild("name").getValue());
            parameter.setAlias(d.getChild("alias").getValue());
            parameter.setType(d.getChild("type").getValue());
            final String required = d.getChild("required").getValue();
            parameter.setRequired(Boolean.valueOf(required));
            final PlexusConfiguration editableConfig = d.getChild("editable");
            if (editableConfig != null) {
                final String editable = d.getChild("editable").getValue();
                parameter.setEditable(editable == null || Boolean.valueOf(editable));
            }
            parameter.setDescription(d.getChild("description").getValue());
            parameter.setDeprecated(d.getChild("deprecated").getValue());
            parameter.setImplementation(d.getChild("implementation").getValue());
            parameters.add(parameter);
        }
        mojo.setParameters(parameters);
        mojo.setMojoConfiguration(c.getChild("configuration"));
        final PlexusConfiguration[] requirements = c.getChild("requirements").getChildren("requirement");
        for (int j = 0; j < requirements.length; ++j) {
            final PlexusConfiguration requirement = requirements[j];
            final ComponentRequirement cr = new ComponentRequirement();
            cr.setRole(requirement.getChild("role").getValue());
            cr.setRoleHint(requirement.getChild("role-hint").getValue());
            cr.setFieldName(requirement.getChild("field-name").getValue());
            mojo.addRequirement(cr);
        }
        return mojo;
    }
    
    public PlexusConfiguration buildConfiguration(final Reader configuration) throws PlexusConfigurationException {
        try {
            return new XmlPlexusConfiguration(Xpp3DomBuilder.build(configuration));
        }
        catch (IOException e) {
            throw new PlexusConfigurationException("Error creating configuration", e);
        }
        catch (XmlPullParserException e2) {
            throw new PlexusConfigurationException("Error creating configuration", e2);
        }
    }
}
