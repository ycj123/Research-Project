// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common.ASdebug;

import org.pitest.reloc.antlr.common.Token;

public interface IASDebugStream
{
    String getEntireText();
    
    TokenOffsetInfo getOffsetInfo(final Token p0);
}
