// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import java.io.Serializable;

public class NameValuePair implements Serializable
{
    private String name;
    private String value;
    
    public NameValuePair() {
        this(null, null);
    }
    
    public NameValuePair(final String name, final String value) {
        this.name = null;
        this.value = null;
        this.name = name;
        this.value = value;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String toString() {
        return "name=" + this.name + ", " + "value=" + this.value;
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (this.getClass().equals(object.getClass())) {
            final NameValuePair pair = (NameValuePair)object;
            return ((null == this.name) ? (null == pair.name) : this.name.equals(pair.name)) && ((null == this.value) ? (null == pair.value) : this.value.equals(pair.value));
        }
        return false;
    }
    
    public int hashCode() {
        return this.getClass().hashCode() ^ ((null == this.name) ? 0 : this.name.hashCode()) ^ ((null == this.value) ? 0 : this.value.hashCode());
    }
}
