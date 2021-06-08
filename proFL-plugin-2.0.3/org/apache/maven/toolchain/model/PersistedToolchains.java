// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class PersistedToolchains implements Serializable
{
    private List<ToolchainModel> toolchains;
    private String modelEncoding;
    
    public PersistedToolchains() {
        this.modelEncoding = "UTF-8";
    }
    
    public void addToolchain(final ToolchainModel toolchainModel) {
        if (!(toolchainModel instanceof ToolchainModel)) {
            throw new ClassCastException("PersistedToolchains.addToolchains(toolchainModel) parameter must be instanceof " + ToolchainModel.class.getName());
        }
        this.getToolchains().add(toolchainModel);
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
    
    public List<ToolchainModel> getToolchains() {
        if (this.toolchains == null) {
            this.toolchains = new ArrayList<ToolchainModel>();
        }
        return this.toolchains;
    }
    
    public void removeToolchain(final ToolchainModel toolchainModel) {
        if (!(toolchainModel instanceof ToolchainModel)) {
            throw new ClassCastException("PersistedToolchains.removeToolchains(toolchainModel) parameter must be instanceof " + ToolchainModel.class.getName());
        }
        this.getToolchains().remove(toolchainModel);
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public void setToolchains(final List<ToolchainModel> toolchains) {
        this.toolchains = toolchains;
    }
}
