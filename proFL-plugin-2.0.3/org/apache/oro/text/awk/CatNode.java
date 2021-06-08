// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

final class CatNode extends SyntaxNode
{
    SyntaxNode _left;
    SyntaxNode _right;
    
    boolean _nullable() {
        return this._left._nullable() && this._right._nullable();
    }
    
    BitSet _firstPosition() {
        if (this._left._nullable()) {
            final BitSet firstPosition = this._left._firstPosition();
            final BitSet firstPosition2 = this._right._firstPosition();
            final BitSet set = new BitSet(Math.max(firstPosition.size(), firstPosition2.size()));
            set.or(firstPosition2);
            set.or(firstPosition);
            return set;
        }
        return this._left._firstPosition();
    }
    
    BitSet _lastPosition() {
        if (this._right._nullable()) {
            final BitSet lastPosition = this._left._lastPosition();
            final BitSet lastPosition2 = this._right._lastPosition();
            final BitSet set = new BitSet(Math.max(lastPosition.size(), lastPosition2.size()));
            set.or(lastPosition2);
            set.or(lastPosition);
            return set;
        }
        return this._right._lastPosition();
    }
    
    void _followPosition(final BitSet[] array, final SyntaxNode[] array2) {
        this._left._followPosition(array, array2);
        this._right._followPosition(array, array2);
        final BitSet lastPosition = this._left._lastPosition();
        final BitSet firstPosition = this._right._firstPosition();
        int size = lastPosition.size();
        while (0 < size--) {
            if (lastPosition.get(size)) {
                array[size].or(firstPosition);
            }
        }
    }
    
    SyntaxNode _clone(final int[] array) {
        final CatNode catNode = new CatNode();
        catNode._left = this._left._clone(array);
        catNode._right = this._right._clone(array);
        return catNode;
    }
}
