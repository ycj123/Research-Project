// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

class StarNode extends SyntaxNode
{
    SyntaxNode _left;
    
    StarNode(final SyntaxNode left) {
        this._left = left;
    }
    
    boolean _nullable() {
        return true;
    }
    
    BitSet _firstPosition() {
        return this._left._firstPosition();
    }
    
    BitSet _lastPosition() {
        return this._left._lastPosition();
    }
    
    void _followPosition(final BitSet[] array, final SyntaxNode[] array2) {
        this._left._followPosition(array, array2);
        final BitSet lastPosition = this._lastPosition();
        final BitSet firstPosition = this._firstPosition();
        int size = lastPosition.size();
        while (0 < size--) {
            if (lastPosition.get(size)) {
                array[size].or(firstPosition);
            }
        }
    }
    
    SyntaxNode _clone(final int[] array) {
        return new StarNode(this._left._clone(array));
    }
}
