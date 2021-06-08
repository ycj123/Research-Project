// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarantlr.debug;

public interface ParserTokenListener extends ListenerBase
{
    void parserConsume(final ParserTokenEvent p0);
    
    void parserLA(final ParserTokenEvent p0);
}
