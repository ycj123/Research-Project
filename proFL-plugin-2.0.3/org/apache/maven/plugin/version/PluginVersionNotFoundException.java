// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.version;

public class PluginVersionNotFoundException extends Exception
{
    private final String groupId;
    private final String artifactId;
    
    public PluginVersionNotFoundException(final String groupId, final String artifactId) {
        super("The plugin '" + groupId + ":" + artifactId + "' does not exist or no valid version could be found");
        this.groupId = groupId;
        this.artifactId = artifactId;
    }
    
    public String getGroupId() {
        return this.groupId;
    }
    
    public String getArtifactId() {
        return this.artifactId;
    }
}
