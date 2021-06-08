// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.debug;

public interface ParserController extends ParserListener
{
    void checkBreak();
    
    void setParserEventSupport(final ParserEventSupport p0);
}
