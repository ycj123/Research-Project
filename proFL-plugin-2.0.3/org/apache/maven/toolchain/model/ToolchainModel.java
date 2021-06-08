// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain.model;

import java.io.Serializable;

public class ToolchainModel implements Serializable
{
    private String type;
    private Object provides;
    private Object configuration;
    
    public Object getConfiguration() {
        return this.configuration;
    }
    
    public Object getProvides() {
        return this.provides;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setConfiguration(final Object configuration) {
        this.configuration = configuration;
    }
    
    public void setProvides(final Object provides) {
        this.provides = provides;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
}
