// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.descriptor;

import java.util.Collections;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import org.apache.maven.plugin.lifecycle.LifecycleConfiguration;
import java.io.InputStream;
import java.util.HashMap;
import org.codehaus.plexus.util.IOUtil;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import org.apache.maven.plugin.lifecycle.io.xpp3.LifecycleMappingsXpp3Reader;
import org.apache.maven.plugin.lifecycle.Lifecycle;
import java.util.Iterator;
import java.util.Collection;
import org.apache.maven.artifact.ArtifactUtils;
import org.codehaus.plexus.component.repository.ComponentDescriptor;
import java.util.Set;
import org.codehaus.classworlds.ClassRealm;
import java.util.Map;
import java.util.List;
import org.codehaus.plexus.component.repository.ComponentSetDescriptor;

public class PluginDescriptor extends ComponentSetDescriptor
{
    private String groupId;
    private String artifactId;
    private String version;
    private String goalPrefix;
    private String source;
    private boolean inheritedByDefault;
    private List artifacts;
    private Map lifecycleMappings;
    private ClassRealm classRealm;
    private Map artifactMap;
    private Set introducedDependencyArtifacts;
    private String name;
    private String description;
    
    public PluginDescriptor() {
        this.inheritedByDefault = true;
    }
    
    public List getMojos() {
        return this.getComponents();
    }
    
    public void addMojo(final MojoDescriptor mojoDescriptor) throws DuplicateMojoDescriptorException {
        MojoDescriptor existing = null;
        final List mojos = this.getComponents();
        if (mojos != null && mojos.contains(mojoDescriptor)) {
            final int indexOf = mojos.indexOf(mojoDescriptor);
            existing = mojos.get(indexOf);
        }
        if (existing != null) {
            throw new DuplicateMojoDescriptorException(this.getGoalPrefix(), mojoDescriptor.getGoal(), existing.getImplementation(), mojoDescriptor.getImplementation());
        }
        this.addComponentDescriptor(mojoDescriptor);
    }
    
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }
    
    public String getArtifactId() {
        return this.artifactId;
    }
    
    public void setArtifactId(final String artifactId) {
        this.artifactId = artifactId;
    }
    
    public static String constructPluginKey(final String groupId, final String artifactId, final String version) {
        return groupId + ":" + artifactId + ":" + version;
    }
    
    public String getPluginLookupKey() {
        return this.groupId + ":" + this.artifactId;
    }
    
    public String getId() {
        return constructPluginKey(this.groupId, this.artifactId, this.version);
    }
    
    public static String getDefaultPluginArtifactId(final String id) {
        return "maven-" + id + "-plugin";
    }
    
    public static String getDefaultPluginGroupId() {
        return "org.apache.maven.plugins";
    }
    
    public static String getGoalPrefixFromArtifactId(final String artifactId) {
        if ("maven-plugin-plugin".equals(artifactId)) {
            return "plugin";
        }
        return artifactId.replaceAll("-?maven-?", "").replaceAll("-?plugin-?", "");
    }
    
    public String getGoalPrefix() {
        return this.goalPrefix;
    }
    
    public void setGoalPrefix(final String goalPrefix) {
        this.goalPrefix = goalPrefix;
    }
    
    public void setVersion(final String version) {
        this.version = version;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public void setSource(final String source) {
        this.source = source;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public boolean isInheritedByDefault() {
        return this.inheritedByDefault;
    }
    
    public void setInheritedByDefault(final boolean inheritedByDefault) {
        this.inheritedByDefault = inheritedByDefault;
    }
    
    public List getArtifacts() {
        return this.artifacts;
    }
    
    public void setArtifacts(final List artifacts) {
        this.artifacts = artifacts;
        this.artifactMap = null;
    }
    
    public Map getArtifactMap() {
        if (this.artifactMap == null) {
            this.artifactMap = ArtifactUtils.artifactMapByVersionlessId(this.getArtifacts());
        }
        return this.artifactMap;
    }
    
    public boolean equals(final Object object) {
        return this == object || this.getId().equals(((PluginDescriptor)object).getId());
    }
    
    public int hashCode() {
        return 10 + this.getId().hashCode();
    }
    
    public MojoDescriptor getMojo(final String goal) {
        if (this.getMojos() == null) {
            return null;
        }
        MojoDescriptor mojoDescriptor = null;
        MojoDescriptor desc;
        for (Iterator i = this.getMojos().iterator(); i.hasNext() && mojoDescriptor == null; mojoDescriptor = desc) {
            desc = i.next();
            if (goal.equals(desc.getGoal())) {}
        }
        return mojoDescriptor;
    }
    
    public Lifecycle getLifecycleMapping(final String lifecycle) throws IOException, XmlPullParserException {
        if (this.lifecycleMappings == null) {
            final LifecycleMappingsXpp3Reader reader = new LifecycleMappingsXpp3Reader();
            InputStreamReader r = null;
            LifecycleConfiguration config;
            try {
                final InputStream resourceAsStream = this.classRealm.getResourceAsStream("/META-INF/maven/lifecycle.xml");
                if (resourceAsStream == null) {
                    throw new FileNotFoundException("Unable to find /META-INF/maven/lifecycle.xml in the plugin");
                }
                r = new InputStreamReader(resourceAsStream);
                config = reader.read(r, true);
            }
            finally {
                IOUtil.close(r);
            }
            final Map map = new HashMap();
            for (final Lifecycle l : config.getLifecycles()) {
                map.put(l.getId(), l);
            }
            this.lifecycleMappings = map;
        }
        return this.lifecycleMappings.get(lifecycle);
    }
    
    public void setClassRealm(final ClassRealm classRealm) {
        this.classRealm = classRealm;
    }
    
    public ClassRealm getClassRealm() {
        return this.classRealm;
    }
    
    public void setIntroducedDependencyArtifacts(final Set introducedDependencyArtifacts) {
        this.introducedDependencyArtifacts = introducedDependencyArtifacts;
    }
    
    public Set getIntroducedDependencyArtifacts() {
        return (this.introducedDependencyArtifacts != null) ? this.introducedDependencyArtifacts : Collections.EMPTY_SET;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
}
