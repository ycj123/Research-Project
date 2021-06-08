// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.io.Serializable;

public class Version implements Serializable
{
    private String position;
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Version)) {
            return false;
        }
        final Version that = (Version)other;
        boolean result = true;
        result = (result && ((this.getPosition() != null) ? this.getPosition().equals(that.getPosition()) : (that.getPosition() == null)));
        return result;
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.position != null) ? this.position.hashCode() : 0);
        return result;
    }
    
    public void setPosition(final String position) {
        this.position = position;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("position = '");
        buf.append(this.getPosition());
        buf.append("'");
        return buf.toString();
    }
}
