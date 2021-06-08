// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

final class SyntaxTree
{
    int _positions;
    SyntaxNode _root;
    LeafNode[] _nodes;
    BitSet[] _followSet;
    
    SyntaxTree(final SyntaxNode root, final int positions) {
        this._root = root;
        this._positions = positions;
    }
    
    void _computeFollowPositions() {
        this._followSet = new BitSet[this._positions];
        this._nodes = new LeafNode[this._positions];
        int positions = this._positions;
        while (0 < positions--) {
            this._followSet[positions] = new BitSet(this._positions);
        }
        this._root._followPosition(this._followSet, this._nodes);
    }
    
    private void __addToFastMap(final BitSet set, final boolean[] array, final boolean[] array2) {
        for (int i = 0; i < this._positions; ++i) {
            if (set.get(i) && !array2[i]) {
                array2[i] = true;
                for (int j = 0; j < 256; ++j) {
                    if (!array[j]) {
                        array[j] = this._nodes[i]._matches((char)j);
                    }
                }
            }
        }
    }
    
    boolean[] createFastMap() {
        final boolean[] array = new boolean[256];
        this.__addToFastMap(this._root._firstPosition(), array, new boolean[this._positions]);
        return array;
    }
}
