// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class LifecycleConfiguration implements Serializable
{
    private List lifecycles;
    private String modelEncoding;
    
    public LifecycleConfiguration() {
        this.modelEncoding = "UTF-8";
    }
    
    public void addLifecycle(final Lifecycle lifecycle) {
        this.getLifecycles().add(lifecycle);
    }
    
    public List getLifecycles() {
        if (this.lifecycles == null) {
            this.lifecycles = new ArrayList();
        }
        return this.lifecycles;
    }
    
    public void removeLifecycle(final Lifecycle lifecycle) {
        this.getLifecycles().remove(lifecycle);
    }
    
    public void setLifecycles(final List lifecycles) {
        this.lifecycles = lifecycles;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
