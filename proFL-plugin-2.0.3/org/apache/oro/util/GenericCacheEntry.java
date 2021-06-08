// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.util;

import java.io.Serializable;

final class GenericCacheEntry implements Serializable
{
    int _index;
    Object _value;
    Object _key;
    
    GenericCacheEntry(final int index) {
        this._index = index;
        this._value = null;
        this._key = null;
    }
}
