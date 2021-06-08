// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.keyvalue;

import java.util.Map;
import org.mudebug.prapr.reloc.commons.collections.KeyValue;

public final class DefaultMapEntry extends AbstractMapEntry
{
    public DefaultMapEntry(final Object key, final Object value) {
        super(key, value);
    }
    
    public DefaultMapEntry(final KeyValue pair) {
        super(pair.getKey(), pair.getValue());
    }
    
    public DefaultMapEntry(final Map.Entry entry) {
        super(entry.getKey(), entry.getValue());
    }
}
