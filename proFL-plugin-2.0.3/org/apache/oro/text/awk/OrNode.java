// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

class OrNode extends SyntaxNode
{
    SyntaxNode _left;
    SyntaxNode _right;
    
    OrNode(final SyntaxNode left, final SyntaxNode right) {
        this._left = left;
        this._right = right;
    }
    
    boolean _nullable() {
        return this._left._nullable() || this._right._nullable();
    }
    
    BitSet _firstPosition() {
        final BitSet firstPosition = this._left._firstPosition();
        final BitSet firstPosition2 = this._right._firstPosition();
        final BitSet set = new BitSet(Math.max(firstPosition.size(), firstPosition2.size()));
        set.or(firstPosition2);
        set.or(firstPosition);
        return set;
    }
    
    BitSet _lastPosition() {
        final BitSet lastPosition = this._left._lastPosition();
        final BitSet lastPosition2 = this._right._lastPosition();
        final BitSet set = new BitSet(Math.max(lastPosition.size(), lastPosition2.size()));
        set.or(lastPosition2);
        set.or(lastPosition);
        return set;
    }
    
    void _followPosition(final BitSet[] array, final SyntaxNode[] array2) {
        this._left._followPosition(array, array2);
        this._right._followPosition(array, array2);
    }
    
    SyntaxNode _clone(final int[] array) {
        return new OrNode(this._left._clone(array), this._right._clone(array));
    }
}
