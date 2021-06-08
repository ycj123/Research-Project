// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

import org.apache.maven.toolchain.model.ToolchainModel;

public interface ToolchainFactory
{
    public static final String ROLE = ToolchainFactory.class.getName();
    
    ToolchainPrivate createToolchain(final ToolchainModel p0) throws MisconfiguredToolchainException;
    
    ToolchainPrivate createDefaultToolchain();
}
