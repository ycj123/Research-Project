// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.lifecycle.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lifecycle
{
    private String id;
    private Map phases;
    private List optionalMojos;
    
    public Lifecycle() {
        this.optionalMojos = new ArrayList();
    }
    
    public String getId() {
        return this.id;
    }
    
    public Map getPhases() {
        return this.phases;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void addOptionalMojo(final String optionalMojo) {
        this.optionalMojos.add(optionalMojo);
    }
    
    public void setOptionalMojos(final List optionalMojos) {
        this.optionalMojos = optionalMojos;
    }
    
    public List getOptionalMojos() {
        return this.optionalMojos;
    }
}
