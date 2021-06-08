// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.keyvalue;

import java.util.Map;
import org.mudebug.prapr.reloc.commons.collections.KeyValue;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;

public final class UnmodifiableMapEntry extends AbstractMapEntry implements Unmodifiable
{
    public UnmodifiableMapEntry(final Object key, final Object value) {
        super(key, value);
    }
    
    public UnmodifiableMapEntry(final KeyValue pair) {
        super(pair.getKey(), pair.getValue());
    }
    
    public UnmodifiableMapEntry(final Map.Entry entry) {
        super(entry.getKey(), entry.getValue());
    }
    
    public Object setValue(final Object value) {
        throw new UnsupportedOperationException("setValue() is not supported");
    }
}
