// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.common;

class StringLiteralSymbol extends TokenSymbol
{
    protected String label;
    
    public StringLiteralSymbol(final String s) {
        super(s);
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
}
