// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.fml.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Part implements Serializable
{
    private String id;
    private String title;
    private List faqs;
    private String modelEncoding;
    
    public Part() {
        this.modelEncoding = "UTF-8";
    }
    
    public void addFaq(final Faq faq) {
        if (!(faq instanceof Faq)) {
            throw new ClassCastException("Part.addFaqs(faq) parameter must be instanceof " + Faq.class.getName());
        }
        this.getFaqs().add(faq);
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Part)) {
            return false;
        }
        final Part that = (Part)other;
        boolean result = true;
        result = (result && ((this.getId() != null) ? this.getId().equals(that.getId()) : (that.getId() == null)));
        result = (result && ((this.getTitle() != null) ? this.getTitle().equals(that.getTitle()) : (that.getTitle() == null)));
        result = (result && ((this.getFaqs() != null) ? this.getFaqs().equals(that.getFaqs()) : (that.getFaqs() == null)));
        return result;
    }
    
    public List getFaqs() {
        if (this.faqs == null) {
            this.faqs = new ArrayList();
        }
        return this.faqs;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + ((this.id != null) ? this.id.hashCode() : 0);
        result = 37 * result + ((this.title != null) ? this.title.hashCode() : 0);
        result = 37 * result + ((this.faqs != null) ? this.faqs.hashCode() : 0);
        return result;
    }
    
    public void removeFaq(final Faq faq) {
        if (!(faq instanceof Faq)) {
            throw new ClassCastException("Part.removeFaqs(faq) parameter must be instanceof " + Faq.class.getName());
        }
        this.getFaqs().remove(faq);
    }
    
    public void setFaqs(final List faqs) {
        this.faqs = faqs;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("id = '");
        buf.append(this.getId());
        buf.append("'");
        buf.append("\n");
        buf.append("title = '");
        buf.append(this.getTitle());
        buf.append("'");
        buf.append("\n");
        buf.append("faqs = '");
        buf.append(this.getFaqs());
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
