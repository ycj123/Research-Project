// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

final class DFAState
{
    int _stateNumber;
    BitSet _state;
    
    DFAState(final BitSet state, final int stateNumber) {
        this._state = state;
        this._stateNumber = stateNumber;
    }
}
