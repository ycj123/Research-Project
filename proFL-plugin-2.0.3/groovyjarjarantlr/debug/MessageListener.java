// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarantlr.debug;

public interface MessageListener extends ListenerBase
{
    void reportError(final MessageEvent p0);
    
    void reportWarning(final MessageEvent p0);
}
