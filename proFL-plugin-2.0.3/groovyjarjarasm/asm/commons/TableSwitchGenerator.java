// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.commons;

import groovyjarjarasm.asm.Label;

public interface TableSwitchGenerator
{
    void generateCase(final int p0, final Label p1);
    
    void generateDefault();
}
