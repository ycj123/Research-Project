// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

abstract class LeafNode extends SyntaxNode
{
    static final int _NUM_TOKENS = 256;
    static final int _END_MARKER_TOKEN = 256;
    protected int _position;
    protected BitSet _positionSet;
    
    LeafNode(final int n) {
        this._position = n;
        (this._positionSet = new BitSet(n + 1)).set(n);
    }
    
    abstract boolean _matches(final char p0);
    
    final boolean _nullable() {
        return false;
    }
    
    final BitSet _firstPosition() {
        return this._positionSet;
    }
    
    final BitSet _lastPosition() {
        return this._positionSet;
    }
    
    final void _followPosition(final BitSet[] array, final SyntaxNode[] array2) {
        array2[this._position] = this;
    }
}
