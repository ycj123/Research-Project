// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

final class EpsilonNode extends SyntaxNode
{
    BitSet _positionSet;
    
    EpsilonNode() {
        this._positionSet = new BitSet(1);
    }
    
    boolean _nullable() {
        return true;
    }
    
    BitSet _firstPosition() {
        return this._positionSet;
    }
    
    BitSet _lastPosition() {
        return this._positionSet;
    }
    
    void _followPosition(final BitSet[] array, final SyntaxNode[] array2) {
    }
    
    SyntaxNode _clone(final int[] array) {
        return new EpsilonNode();
    }
}
