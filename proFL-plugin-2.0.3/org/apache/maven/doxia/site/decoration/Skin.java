// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.io.Serializable;

public class Skin implements Serializable
{
    private String groupId;
    private String artifactId;
    private String version;
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Skin)) {
            return false;
        }
        final Skin that = (Skin)other;
        boolean result = true;
        result = (result && ((this.getGroupId() != null) ? this.getGroupId().equals(that.getGroupId()) : (that.getGroupId() == null)));
        result = (result && ((this.getArtifactId() != null) ? this.getArtifactId().equals(that.getArtifactId()) : (that.getArtifactId() == null)));
        result = (result && ((this.getVersion() != null) ? this.getVersion().equals(that.getVersion()) : (that.getVersion() == null)));
        return result;
    }
    
    public String getArtifactId() {
        return this.artifactId;
    }
    
    public String getGroupId() {
        return this.groupId;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.groupId != null) ? this.groupId.hashCode() : 0);
        result = 37 * result + ((this.artifactId != null) ? this.artifactId.hashCode() : 0);
        result = 37 * result + ((this.version != null) ? this.version.hashCode() : 0);
        return result;
    }
    
    public void setArtifactId(final String artifactId) {
        this.artifactId = artifactId;
    }
    
    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }
    
    public void setVersion(final String version) {
        this.version = version;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("groupId = '");
        buf.append(this.getGroupId());
        buf.append("'");
        buf.append("\n");
        buf.append("artifactId = '");
        buf.append(this.getArtifactId());
        buf.append("'");
        buf.append("\n");
        buf.append("version = '");
        buf.append(this.getVersion());
        buf.append("'");
        return buf.toString();
    }
    
    public static Skin getDefaultSkin() {
        final Skin skin = new Skin();
        skin.setGroupId("org.apache.maven.skins");
        skin.setArtifactId("maven-default-skin");
        return skin;
    }
}
