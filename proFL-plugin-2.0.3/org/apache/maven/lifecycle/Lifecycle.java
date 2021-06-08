// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.lifecycle;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class Lifecycle
{
    private String id;
    private List phases;
    private Map defaultPhases;
    
    public void addPhase(final String phase) {
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
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setPhases(final List phases) {
        this.phases = phases;
    }
    
    public Map getDefaultPhases() {
        return this.defaultPhases;
    }
}
