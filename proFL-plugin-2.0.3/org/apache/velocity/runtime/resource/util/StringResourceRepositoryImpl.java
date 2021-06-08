// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StringResourceRepositoryImpl implements StringResourceRepository
{
    protected Map resources;
    private String encoding;
    
    public StringResourceRepositoryImpl() {
        this.resources = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.encoding = "UTF-8";
    }
    
    public StringResource getStringResource(final String name) {
        return this.resources.get(name);
    }
    
    public void putStringResource(final String name, final String body) {
        this.resources.put(name, new StringResource(body, this.getEncoding()));
    }
    
    public void removeStringResource(final String name) {
        this.resources.remove(name);
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }
}
