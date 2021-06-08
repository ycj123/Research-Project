// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

class CharacterClassNode extends LeafNode
{
    BitSet _characterSet;
    
    CharacterClassNode(final int n) {
        super(n);
        this._characterSet = new BitSet(257);
    }
    
    void _addToken(final int bitIndex) {
        this._characterSet.set(bitIndex);
    }
    
    void _addTokenRange(int i, final int n) {
        while (i <= n) {
            this._characterSet.set(i++);
        }
    }
    
    boolean _matches(final char bitIndex) {
        return this._characterSet.get(bitIndex);
    }
    
    SyntaxNode _clone(final int[] array) {
        final CharacterClassNode characterClassNode = new CharacterClassNode(array[0]++);
        characterClassNode._characterSet = (BitSet)this._characterSet.clone();
        return characterClassNode;
    }
}
