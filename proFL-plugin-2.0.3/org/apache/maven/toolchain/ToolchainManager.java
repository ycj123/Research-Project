// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

import org.apache.maven.execution.MavenSession;

public interface ToolchainManager
{
    public static final String ROLE = ToolchainManager.class.getName();
    
    Toolchain getToolchainFromBuildContext(final String p0, final MavenSession p1);
}
