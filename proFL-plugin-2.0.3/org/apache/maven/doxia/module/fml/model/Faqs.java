// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.fml.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Faqs implements Serializable
{
    private String title;
    private boolean toplink;
    private List parts;
    private String modelEncoding;
    
    public Faqs() {
        this.title = "FAQ";
        this.toplink = true;
        this.modelEncoding = "UTF-8";
    }
    
    public void addPart(final Part part) {
        if (!(part instanceof Part)) {
            throw new ClassCastException("Faqs.addParts(part) parameter must be instanceof " + Part.class.getName());
        }
        this.getParts().add(part);
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Faqs)) {
            return false;
        }
        final Faqs that = (Faqs)other;
        boolean result = true;
        result = (result && ((this.getTitle() != null) ? this.getTitle().equals(that.getTitle()) : (that.getTitle() == null)));
        result = (result && this.toplink == that.toplink);
        result = (result && ((this.getParts() != null) ? this.getParts().equals(that.getParts()) : (that.getParts() == null)));
        return result;
    }
    
    public List getParts() {
        if (this.parts == null) {
            this.parts = new ArrayList();
        }
        return this.parts;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.title != null) ? this.title.hashCode() : 0);
        result = 37 * result + (this.toplink ? 0 : 1);
        result = 37 * result + ((this.parts != null) ? this.parts.hashCode() : 0);
        return result;
    }
    
    public boolean isToplink() {
        return this.toplink;
    }
    
    public void removePart(final Part part) {
        if (!(part instanceof Part)) {
            throw new ClassCastException("Faqs.removeParts(part) parameter must be instanceof " + Part.class.getName());
        }
        this.getParts().remove(part);
    }
    
    public void setParts(final List parts) {
        this.parts = parts;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setToplink(final boolean toplink) {
        this.toplink = toplink;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("title = '");
        buf.append(this.getTitle());
        buf.append("'");
        buf.append("\n");
        buf.append("toplink = '");
        buf.append(this.isToplink());
        buf.append("'");
        buf.append("\n");
        buf.append("parts = '");
        buf.append(this.getParts());
        buf.append("'");
        return buf.toString();
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
