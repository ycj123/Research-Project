// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarantlr.ASdebug;

import groovyjarjarantlr.Token;

public interface IASDebugStream
{
    String getEntireText();
    
    TokenOffsetInfo getOffsetInfo(final Token p0);
}
