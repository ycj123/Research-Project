// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Phase implements Serializable
{
    private String id;
    private List executions;
    private Object configuration;
    private String modelEncoding;
    
    public Phase() {
        this.modelEncoding = "UTF-8";
    }
    
    public void addExecution(final Execution execution) {
        this.getExecutions().add(execution);
    }
    
    public Object getConfiguration() {
        return this.configuration;
    }
    
    public List getExecutions() {
        if (this.executions == null) {
            this.executions = new ArrayList();
        }
        return this.executions;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void removeExecution(final Execution execution) {
        this.getExecutions().remove(execution);
    }
    
    public void setConfiguration(final Object configuration) {
        this.configuration = configuration;
    }
    
    public void setExecutions(final List executions) {
        this.executions = executions;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
