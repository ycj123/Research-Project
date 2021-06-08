// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

final class NegativeCharacterClassNode extends CharacterClassNode
{
    NegativeCharacterClassNode(final int n) {
        super(n);
        this._characterSet.set(256);
    }
    
    boolean _matches(final char bitIndex) {
        return !this._characterSet.get(bitIndex);
    }
    
    SyntaxNode _clone(final int[] array) {
        final NegativeCharacterClassNode negativeCharacterClassNode = new NegativeCharacterClassNode(array[0]++);
        negativeCharacterClassNode._characterSet = (BitSet)this._characterSet.clone();
        return negativeCharacterClassNode;
    }
}
