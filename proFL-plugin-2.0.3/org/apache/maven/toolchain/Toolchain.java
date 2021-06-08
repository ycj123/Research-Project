// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

public interface Toolchain
{
    String getType();
    
    String findTool(final String p0);
}
