// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.keyvalue;

import java.util.Map;

public abstract class AbstractMapEntry extends AbstractKeyValue implements Map.Entry
{
    protected AbstractMapEntry(final Object key, final Object value) {
        super(key, value);
    }
    
    public Object setValue(final Object value) {
        final Object answer = super.value;
        super.value = value;
        return answer;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        final Map.Entry other = (Map.Entry)obj;
        return ((this.getKey() == null) ? (other.getKey() == null) : this.getKey().equals(other.getKey())) && ((this.getValue() == null) ? (other.getValue() == null) : this.getValue().equals(other.getValue()));
    }
    
    public int hashCode() {
        return ((this.getKey() == null) ? 0 : this.getKey().hashCode()) ^ ((this.getValue() == null) ? 0 : this.getValue().hashCode());
    }
}
