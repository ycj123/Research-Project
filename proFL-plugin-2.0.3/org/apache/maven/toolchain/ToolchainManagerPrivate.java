// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

import org.apache.maven.execution.MavenSession;

public interface ToolchainManagerPrivate
{
    public static final String ROLE = ToolchainManagerPrivate.class.getName();
    
    ToolchainPrivate[] getToolchainsForType(final String p0) throws MisconfiguredToolchainException;
    
    void storeToolchainToBuildContext(final ToolchainPrivate p0, final MavenSession p1);
}
