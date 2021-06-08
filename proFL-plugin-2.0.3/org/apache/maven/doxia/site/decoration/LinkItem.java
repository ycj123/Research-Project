// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.io.Serializable;

public class LinkItem implements Serializable
{
    private String name;
    private String href;
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LinkItem)) {
            return false;
        }
        final LinkItem that = (LinkItem)other;
        boolean result = true;
        result = (result && ((this.getName() != null) ? this.getName().equals(that.getName()) : (that.getName() == null)));
        result = (result && ((this.getHref() != null) ? this.getHref().equals(that.getHref()) : (that.getHref() == null)));
        return result;
    }
    
    public String getHref() {
        return this.href;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.name != null) ? this.name.hashCode() : 0);
        result = 37 * result + ((this.href != null) ? this.href.hashCode() : 0);
        return result;
    }
    
    public void setHref(final String href) {
        this.href = href;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("name = '");
        buf.append(this.getName());
        buf.append("'");
        buf.append("\n");
        buf.append("href = '");
        buf.append(this.getHref());
        buf.append("'");
        return buf.toString();
    }
}
