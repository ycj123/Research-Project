// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.regex;

final class CharStringPointer
{
    static final char _END_OF_STRING = '\uffff';
    int _offset;
    char[] _array;
    
    CharStringPointer(final char[] array, final int offset) {
        this._array = array;
        this._offset = offset;
    }
    
    CharStringPointer(final char[] array) {
        this(array, 0);
    }
    
    char _getValue() {
        return this._getValue(this._offset);
    }
    
    char _getValue(final int n) {
        if (n < this._array.length && n >= 0) {
            return this._array[n];
        }
        return '\uffff';
    }
    
    char _getValueRelative(final int n) {
        return this._getValue(this._offset + n);
    }
    
    int _getLength() {
        return this._array.length;
    }
    
    int _getOffset() {
        return this._offset;
    }
    
    void _setOffset(final int offset) {
        this._offset = offset;
    }
    
    boolean _isAtEnd() {
        return this._offset >= this._array.length;
    }
    
    char _increment(final int n) {
        this._offset += n;
        if (this._isAtEnd()) {
            this._offset = this._array.length;
            return '\uffff';
        }
        return this._array[this._offset];
    }
    
    char _increment() {
        return this._increment(1);
    }
    
    char _decrement(final int n) {
        this._offset -= n;
        if (this._offset < 0) {
            this._offset = 0;
        }
        return this._array[this._offset];
    }
    
    char _decrement() {
        return this._decrement(1);
    }
    
    char _postIncrement() {
        final char getValue = this._getValue();
        this._increment();
        return getValue;
    }
    
    char _postDecrement() {
        final char getValue = this._getValue();
        this._decrement();
        return getValue;
    }
    
    String _toString(final int offset) {
        return new String(this._array, offset, this._array.length - offset);
    }
    
    public String toString() {
        return this._toString(0);
    }
}
