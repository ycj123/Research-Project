// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.ASdebug;

import org.pitest.reloc.antlr.common.Token;
import org.pitest.reloc.antlr.common.TokenStream;

public final class ASDebugStream
{
    public static String getEntireText(final TokenStream tokenStream) {
        if (tokenStream instanceof IASDebugStream) {
            return ((IASDebugStream)tokenStream).getEntireText();
        }
        return null;
    }
    
    public static TokenOffsetInfo getOffsetInfo(final TokenStream tokenStream, final Token token) {
        if (tokenStream instanceof IASDebugStream) {
            return ((IASDebugStream)tokenStream).getOffsetInfo(token);
        }
        return null;
    }
}
