// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

import org.apache.maven.toolchain.model.ToolchainModel;
import java.util.Map;

public interface ToolchainPrivate extends Toolchain
{
    boolean matchesRequirements(final Map p0);
    
    ToolchainModel getModel();
}
