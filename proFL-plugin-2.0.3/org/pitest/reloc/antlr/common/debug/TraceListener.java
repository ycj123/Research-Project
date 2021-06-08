// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.debug;

public interface TraceListener extends ListenerBase
{
    void enterRule(final TraceEvent p0);
    
    void exitRule(final TraceEvent p0);
}
