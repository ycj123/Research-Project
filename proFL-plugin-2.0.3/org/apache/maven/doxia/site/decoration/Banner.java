// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration;

import java.io.Serializable;

public class Banner implements Serializable
{
    private String name;
    private String src;
    private String alt;
    private String href;
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Banner)) {
            return false;
        }
        final Banner that = (Banner)other;
        boolean result = true;
        result = (result && ((this.getName() != null) ? this.getName().equals(that.getName()) : (that.getName() == null)));
        result = (result && ((this.getSrc() != null) ? this.getSrc().equals(that.getSrc()) : (that.getSrc() == null)));
        result = (result && ((this.getAlt() != null) ? this.getAlt().equals(that.getAlt()) : (that.getAlt() == null)));
        result = (result && ((this.getHref() != null) ? this.getHref().equals(that.getHref()) : (that.getHref() == null)));
        return result;
    }
    
    public String getAlt() {
        return this.alt;
    }
    
    public String getHref() {
        return this.href;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSrc() {
        return this.src;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.name != null) ? this.name.hashCode() : 0);
        result = 37 * result + ((this.src != null) ? this.src.hashCode() : 0);
        result = 37 * result + ((this.alt != null) ? this.alt.hashCode() : 0);
        result = 37 * result + ((this.href != null) ? this.href.hashCode() : 0);
        return result;
    }
    
    public void setAlt(final String alt) {
        this.alt = alt;
    }
    
    public void setHref(final String href) {
        this.href = href;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setSrc(final String src) {
        this.src = src;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("name = '");
        buf.append(this.getName());
        buf.append("'");
        buf.append("\n");
        buf.append("src = '");
        buf.append(this.getSrc());
        buf.append("'");
        buf.append("\n");
        buf.append("alt = '");
        buf.append(this.getAlt());
        buf.append("'");
        buf.append("\n");
        buf.append("href = '");
        buf.append(this.getHref());
        buf.append("'");
        return buf.toString();
    }
}
