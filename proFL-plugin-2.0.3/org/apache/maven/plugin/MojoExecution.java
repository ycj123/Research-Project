// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.apache.maven.plugin.descriptor.MojoDescriptor;

public class MojoExecution
{
    private final String executionId;
    private final MojoDescriptor mojoDescriptor;
    private Xpp3Dom configuration;
    private List forkedExecutions;
    private List reports;
    
    public MojoExecution(final MojoDescriptor mojoDescriptor) {
        this.forkedExecutions = new ArrayList();
        this.mojoDescriptor = mojoDescriptor;
        this.executionId = null;
        this.configuration = null;
    }
    
    public MojoExecution(final MojoDescriptor mojoDescriptor, final String executionId) {
        this.forkedExecutions = new ArrayList();
        this.mojoDescriptor = mojoDescriptor;
        this.executionId = executionId;
        this.configuration = null;
    }
    
    public MojoExecution(final MojoDescriptor mojoDescriptor, final Xpp3Dom configuration) {
        this.forkedExecutions = new ArrayList();
        this.mojoDescriptor = mojoDescriptor;
        this.configuration = configuration;
        this.executionId = null;
    }
    
    public String getExecutionId() {
        return this.executionId;
    }
    
    public MojoDescriptor getMojoDescriptor() {
        return this.mojoDescriptor;
    }
    
    public Xpp3Dom getConfiguration() {
        return this.configuration;
    }
    
    public void addMojoExecution(final MojoExecution execution) {
        this.forkedExecutions.add(execution);
    }
    
    public void setReports(final List reports) {
        this.reports = reports;
    }
    
    public List getReports() {
        return this.reports;
    }
    
    public List getForkedExecutions() {
        return this.forkedExecutions;
    }
    
    public void setConfiguration(final Xpp3Dom configuration) {
        this.configuration = configuration;
    }
}
