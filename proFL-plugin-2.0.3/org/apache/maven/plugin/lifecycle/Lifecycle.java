// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Lifecycle implements Serializable
{
    private String id;
    private List phases;
    private String modelEncoding;
    
    public Lifecycle() {
        this.modelEncoding = "UTF-8";
    }
    
    public void addPhase(final Phase phase) {
        this.getPhases().add(phase);
    }
    
    public String getId() {
        return this.id;
    }
    
    public List getPhases() {
        if (this.phases == null) {
            this.phases = new ArrayList();
        }
        return this.phases;
    }
    
    public void removePhase(final Phase phase) {
        this.getPhases().remove(phase);
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setPhases(final List phases) {
        this.phases = phases;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
