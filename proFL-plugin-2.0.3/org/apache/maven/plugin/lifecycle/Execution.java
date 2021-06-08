// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Execution implements Serializable
{
    private Object configuration;
    private List goals;
    private String modelEncoding;
    
    public Execution() {
        this.modelEncoding = "UTF-8";
    }
    
    public void addGoal(final String string) {
        this.getGoals().add(string);
    }
    
    public Object getConfiguration() {
        return this.configuration;
    }
    
    public List getGoals() {
        if (this.goals == null) {
            this.goals = new ArrayList();
        }
        return this.goals;
    }
    
    public void removeGoal(final String string) {
        this.getGoals().remove(string);
    }
    
    public void setConfiguration(final Object configuration) {
        this.configuration = configuration;
    }
    
    public void setGoals(final List goals) {
        this.goals = goals;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
