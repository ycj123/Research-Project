// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.debug;

public interface ParserMatchListener extends ListenerBase
{
    void parserMatch(final ParserMatchEvent p0);
    
    void parserMatchNot(final ParserMatchEvent p0);
    
    void parserMismatch(final ParserMatchEvent p0);
    
    void parserMismatchNot(final ParserMatchEvent p0);
}
