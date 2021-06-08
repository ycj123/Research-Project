// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.descriptor;

import org.apache.maven.plugin.Mojo;
import org.codehaus.plexus.configuration.xml.XmlPlexusConfiguration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import java.util.Map;
import java.util.List;
import org.codehaus.plexus.component.repository.ComponentDescriptor;

public class MojoDescriptor extends ComponentDescriptor implements Cloneable
{
    public static String MAVEN_PLUGIN;
    public static final String SINGLE_PASS_EXEC_STRATEGY = "once-per-session";
    public static final String MULTI_PASS_EXEC_STRATEGY = "always";
    private static final String DEFAULT_INSTANTIATION_STRATEGY = "per-lookup";
    private static final String DEFAULT_LANGUAGE = "java";
    private List parameters;
    private Map parameterMap;
    private String executionStrategy;
    private String goal;
    private String phase;
    private String since;
    private String executePhase;
    private String executeGoal;
    private String executeLifecycle;
    private String deprecated;
    private boolean aggregator;
    private String dependencyResolutionRequired;
    private boolean projectRequired;
    private boolean onlineRequired;
    private PlexusConfiguration mojoConfiguration;
    private PluginDescriptor pluginDescriptor;
    private boolean inheritedByDefault;
    private boolean directInvocationOnly;
    private boolean requiresReports;
    
    public MojoDescriptor() {
        this.executionStrategy = "once-per-session";
        this.aggregator = false;
        this.dependencyResolutionRequired = null;
        this.projectRequired = true;
        this.onlineRequired = false;
        this.inheritedByDefault = true;
        this.directInvocationOnly = false;
        this.requiresReports = false;
        this.setInstantiationStrategy("per-lookup");
        this.setComponentFactory("java");
    }
    
    public String getLanguage() {
        return this.getComponentFactory();
    }
    
    public void setLanguage(final String language) {
        this.setComponentFactory(language);
    }
    
    public String getDeprecated() {
        return this.deprecated;
    }
    
    public void setDeprecated(final String deprecated) {
        this.deprecated = deprecated;
    }
    
    public List getParameters() {
        return this.parameters;
    }
    
    public void setParameters(final List parameters) throws DuplicateParameterException {
        for (final Parameter parameter : parameters) {
            this.addParameter(parameter);
        }
    }
    
    public void addParameter(final Parameter parameter) throws DuplicateParameterException {
        if (this.parameters != null && this.parameters.contains(parameter)) {
            throw new DuplicateParameterException(parameter.getName() + " has been declared multiple times in mojo with goal: " + this.getGoal() + " (implementation: " + this.getImplementation() + ")");
        }
        if (this.parameters == null) {
            this.parameters = new LinkedList();
        }
        this.parameters.add(parameter);
    }
    
    public Map getParameterMap() {
        if (this.parameterMap == null) {
            this.parameterMap = new HashMap();
            if (this.parameters != null) {
                for (final Parameter pd : this.parameters) {
                    this.parameterMap.put(pd.getName(), pd);
                }
            }
        }
        return this.parameterMap;
    }
    
    public void setDependencyResolutionRequired(final String requiresDependencyResolution) {
        this.dependencyResolutionRequired = requiresDependencyResolution;
    }
    
    public String isDependencyResolutionRequired() {
        return this.dependencyResolutionRequired;
    }
    
    public void setProjectRequired(final boolean requiresProject) {
        this.projectRequired = requiresProject;
    }
    
    public boolean isProjectRequired() {
        return this.projectRequired;
    }
    
    public void setOnlineRequired(final boolean requiresOnline) {
        this.onlineRequired = requiresOnline;
    }
    
    public boolean isOnlineRequired() {
        return this.onlineRequired;
    }
    
    public boolean requiresOnline() {
        return this.onlineRequired;
    }
    
    public String getPhase() {
        return this.phase;
    }
    
    public void setPhase(final String phase) {
        this.phase = phase;
    }
    
    public String getSince() {
        return this.since;
    }
    
    public void setSince(final String since) {
        this.since = since;
    }
    
    public String getGoal() {
        return this.goal;
    }
    
    public void setGoal(final String goal) {
        this.goal = goal;
    }
    
    public String getExecutePhase() {
        return this.executePhase;
    }
    
    public void setExecutePhase(final String executePhase) {
        this.executePhase = executePhase;
    }
    
    public boolean alwaysExecute() {
        return "always".equals(this.executionStrategy);
    }
    
    public String getExecutionStrategy() {
        return this.executionStrategy;
    }
    
    public void setExecutionStrategy(final String executionStrategy) {
        this.executionStrategy = executionStrategy;
    }
    
    public PlexusConfiguration getMojoConfiguration() {
        if (this.mojoConfiguration == null) {
            this.mojoConfiguration = new XmlPlexusConfiguration("configuration");
        }
        return this.mojoConfiguration;
    }
    
    public void setMojoConfiguration(final PlexusConfiguration mojoConfiguration) {
        this.mojoConfiguration = mojoConfiguration;
    }
    
    public String getRole() {
        return Mojo.ROLE;
    }
    
    public String getRoleHint() {
        return this.getId();
    }
    
    public String getId() {
        return this.getPluginDescriptor().getId() + ":" + this.getGoal();
    }
    
    public String getFullGoalName() {
        return this.getPluginDescriptor().getGoalPrefix() + ":" + this.getGoal();
    }
    
    public String getComponentType() {
        return MojoDescriptor.MAVEN_PLUGIN;
    }
    
    public PluginDescriptor getPluginDescriptor() {
        return this.pluginDescriptor;
    }
    
    public void setPluginDescriptor(final PluginDescriptor pluginDescriptor) {
        this.pluginDescriptor = pluginDescriptor;
    }
    
    public boolean isInheritedByDefault() {
        return this.inheritedByDefault;
    }
    
    public void setInheritedByDefault(final boolean inheritedByDefault) {
        this.inheritedByDefault = inheritedByDefault;
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof MojoDescriptor) {
            final MojoDescriptor other = (MojoDescriptor)object;
            return this.compareObjects(this.getPluginDescriptor(), other.getPluginDescriptor()) && this.compareObjects(this.getGoal(), other.getGoal());
        }
        return false;
    }
    
    private boolean compareObjects(final Object first, final Object second) {
        return (first != null || second == null) && (first == null || second != null) && first.equals(second);
    }
    
    public int hashCode() {
        int result = 1;
        final String goal = this.getGoal();
        if (goal != null) {
            result += goal.hashCode();
        }
        final PluginDescriptor pd = this.getPluginDescriptor();
        if (pd != null) {
            result -= pd.hashCode();
        }
        return result;
    }
    
    public String getExecuteLifecycle() {
        return this.executeLifecycle;
    }
    
    public void setExecuteLifecycle(final String executeLifecycle) {
        this.executeLifecycle = executeLifecycle;
    }
    
    public void setAggregator(final boolean aggregator) {
        this.aggregator = aggregator;
    }
    
    public boolean isAggregator() {
        return this.aggregator;
    }
    
    public boolean isDirectInvocationOnly() {
        return this.directInvocationOnly;
    }
    
    public void setDirectInvocationOnly(final boolean directInvocationOnly) {
        this.directInvocationOnly = directInvocationOnly;
    }
    
    public boolean isRequiresReports() {
        return this.requiresReports;
    }
    
    public void setRequiresReports(final boolean requiresReports) {
        this.requiresReports = requiresReports;
    }
    
    public void setExecuteGoal(final String executeGoal) {
        this.executeGoal = executeGoal;
    }
    
    public String getExecuteGoal() {
        return this.executeGoal;
    }
    
    static {
        MojoDescriptor.MAVEN_PLUGIN = "maven-plugin";
    }
}
