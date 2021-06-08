// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.io.Serializable;

public class PublishDate implements Serializable
{
    private String position;
    private String format;
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PublishDate)) {
            return false;
        }
        final PublishDate that = (PublishDate)other;
        boolean result = true;
        result = (result && ((this.getPosition() != null) ? this.getPosition().equals(that.getPosition()) : (that.getPosition() == null)));
        result = (result && ((this.getFormat() != null) ? this.getFormat().equals(that.getFormat()) : (that.getFormat() == null)));
        return result;
    }
    
    public String getFormat() {
        return this.format;
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.position != null) ? this.position.hashCode() : 0);
        result = 37 * result + ((this.format != null) ? this.format.hashCode() : 0);
        return result;
    }
    
    public void setFormat(final String format) {
        this.format = format;
    }
    
    public void setPosition(final String position) {
        this.position = position;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("position = '");
        buf.append(this.getPosition());
        buf.append("'");
        buf.append("\n");
        buf.append("format = '");
        buf.append(this.getFormat());
        buf.append("'");
        return buf.toString();
    }
}
