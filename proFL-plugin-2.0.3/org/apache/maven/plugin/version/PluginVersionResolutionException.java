// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.version;

public class PluginVersionResolutionException extends Exception
{
    private final String groupId;
    private final String artifactId;
    private final String baseMessage;
    
    public PluginVersionResolutionException(final String groupId, final String artifactId, final String baseMessage, final Throwable cause) {
        super("Error resolving version for '" + groupId + ":" + artifactId + "': " + baseMessage, cause);
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.baseMessage = baseMessage;
    }
    
    public PluginVersionResolutionException(final String groupId, final String artifactId, final String baseMessage) {
        super("Error resolving version for '" + groupId + ":" + artifactId + "': " + baseMessage);
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.baseMessage = baseMessage;
    }
    
    public String getGroupId() {
        return this.groupId;
    }
    
    public String getArtifactId() {
        return this.artifactId;
    }
    
    public String getBaseMessage() {
        return this.baseMessage;
    }
}
