// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.Hashtable;
import java.util.BitSet;
import java.util.Vector;
import java.io.Serializable;
import org.apache.oro.text.regex.Pattern;

public final class AwkPattern implements Pattern, Serializable
{
    static final int _INVALID_STATE = -1;
    static final int _START_STATE = 1;
    int _numStates;
    int _endPosition;
    int _options;
    String _expression;
    Vector _Dtrans;
    Vector[] _nodeList;
    Vector _stateList;
    BitSet _U;
    BitSet _emptySet;
    BitSet[] _followSet;
    BitSet _endStates;
    Hashtable _stateMap;
    boolean _matchesNullString;
    boolean[] _fastMap;
    boolean _hasBeginAnchor;
    boolean _hasEndAnchor;
    
    AwkPattern(final String expression, final SyntaxTree syntaxTree) {
        this._hasBeginAnchor = false;
        this._hasEndAnchor = false;
        this._expression = expression;
        this._endPosition = syntaxTree._positions - 1;
        this._followSet = syntaxTree._followSet;
        this._Dtrans = new Vector();
        this._stateList = new Vector();
        this._endStates = new BitSet();
        (this._U = new BitSet(syntaxTree._positions)).or(syntaxTree._root._firstPosition());
        final int[] array = new int[256];
        this._Dtrans.addElement(array);
        this._Dtrans.addElement(array);
        this._numStates = 1;
        if (this._U.get(this._endPosition)) {
            this._endStates.set(this._numStates);
        }
        final DFAState obj = new DFAState((BitSet)this._U.clone(), this._numStates);
        (this._stateMap = new Hashtable()).put(obj._state, obj);
        this._stateList.addElement(obj);
        this._stateList.addElement(obj);
        ++this._numStates;
        this._U.xor(this._U);
        this._emptySet = new BitSet(syntaxTree._positions);
        this._nodeList = new Vector[256];
        for (int i = 0; i < 256; ++i) {
            this._nodeList[i] = new Vector();
            for (int j = 0; j < syntaxTree._positions; ++j) {
                if (syntaxTree._nodes[j]._matches((char)i)) {
                    this._nodeList[i].addElement(syntaxTree._nodes[j]);
                }
            }
        }
        this._fastMap = syntaxTree.createFastMap();
        this._matchesNullString = this._endStates.get(1);
    }
    
    void _createNewState(final int index, final int n, final int[] array) {
        final DFAState dfaState = this._stateList.elementAt(index);
        int size = this._nodeList[n].size();
        this._U.xor(this._U);
        while (size-- > 0) {
            final int position = this._nodeList[n].elementAt(size)._position;
            if (dfaState._state.get(position)) {
                this._U.or(this._followSet[position]);
            }
        }
        if (!this._stateMap.containsKey(this._U)) {
            final DFAState dfaState2 = new DFAState((BitSet)this._U.clone(), this._numStates++);
            this._stateList.addElement(dfaState2);
            this._stateMap.put(dfaState2._state, dfaState2);
            this._Dtrans.addElement(new int[256]);
            if (!this._U.equals(this._emptySet)) {
                array[n] = this._numStates - 1;
                if (this._U.get(this._endPosition)) {
                    this._endStates.set(this._numStates - 1);
                }
            }
            else {
                array[n] = -1;
            }
        }
        else if (this._U.equals(this._emptySet)) {
            array[n] = -1;
        }
        else {
            array[n] = this._stateMap.get(this._U)._stateNumber;
        }
    }
    
    int[] _getStateArray(final int index) {
        return this._Dtrans.elementAt(index);
    }
    
    public String getPattern() {
        return this._expression;
    }
    
    public int getOptions() {
        return this._options;
    }
}
