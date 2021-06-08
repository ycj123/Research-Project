// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

final class PlusNode extends StarNode
{
    PlusNode(final SyntaxNode syntaxNode) {
        super(syntaxNode);
    }
    
    boolean _nullable() {
        return false;
    }
    
    SyntaxNode _clone(final int[] array) {
        return new PlusNode(this._left._clone(array));
    }
}
