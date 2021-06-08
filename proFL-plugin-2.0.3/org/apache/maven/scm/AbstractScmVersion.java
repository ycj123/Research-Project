// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.io.Serializable;

public abstract class AbstractScmVersion implements ScmVersion, Serializable
{
    private static final long serialVersionUID = -3388495744009098066L;
    private String name;
    
    public AbstractScmVersion(final String name) {
        this.setName(name);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        if (name != null) {
            name = name.trim();
        }
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}
