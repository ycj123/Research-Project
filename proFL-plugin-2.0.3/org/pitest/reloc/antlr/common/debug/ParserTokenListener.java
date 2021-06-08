// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.debug;

public interface ParserTokenListener extends ListenerBase
{
    void parserConsume(final ParserTokenEvent p0);
    
    void parserLA(final ParserTokenEvent p0);
}
