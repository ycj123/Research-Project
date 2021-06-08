// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.stringtemplate.language;

import org.pitest.reloc.antlr.common.CommonToken;

public class ChunkToken extends CommonToken
{
    protected String indentation;
    
    public ChunkToken() {
    }
    
    public ChunkToken(final int type, final String text, final String indentation) {
        super(type, text);
        this.setIndentation(indentation);
    }
    
    public String getIndentation() {
        return this.indentation;
    }
    
    public void setIndentation(final String indentation) {
        this.indentation = indentation;
    }
    
    public String toString() {
        return super.toString() + "<indent='" + this.indentation + "'>";
    }
}
