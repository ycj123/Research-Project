// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.io.Serializable;

public class Logo extends LinkItem implements Serializable
{
    private String img;
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Logo)) {
            return false;
        }
        final Logo that = (Logo)other;
        boolean result = true;
        result = (result && ((this.getImg() != null) ? this.getImg().equals(that.getImg()) : (that.getImg() == null)));
        result = (result && super.equals(other));
        return result;
    }
    
    public String getImg() {
        return this.img;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.img != null) ? this.img.hashCode() : 0);
        result = 37 * result + super.hashCode();
        return result;
    }
    
    public void setImg(final String img) {
        this.img = img;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("img = '");
        buf.append(this.getImg());
        buf.append("'");
        buf.append("\n");
        buf.append(super.toString());
        return buf.toString();
    }
}
