// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarantlr.debug;

public interface ParserController extends ParserListener
{
    void checkBreak();
    
    void setParserEventSupport(final ParserEventSupport p0);
}
