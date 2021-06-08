// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

class TokenNode extends LeafNode
{
    char _token;
    
    TokenNode(final char token, final int n) {
        super(n);
        this._token = token;
    }
    
    boolean _matches(final char c) {
        return this._token == c;
    }
    
    SyntaxNode _clone(final int[] array) {
        return new TokenNode(this._token, array[0]++);
    }
}
