// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;

public class MapEntry implements Map.Entry
{
    private Object key;
    private Object value;
    
    public MapEntry(final Object key, final Object value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public boolean equals(final Object that) {
        return that instanceof MapEntry && this.equals((MapEntry)that);
    }
    
    public boolean equals(final MapEntry that) {
        return DefaultTypeTransformation.compareEqual(this.key, that.key) && DefaultTypeTransformation.compareEqual(this.value, that.value);
    }
    
    @Override
    public int hashCode() {
        return this.hash(this.key) ^ this.hash(this.value);
    }
    
    @Override
    public String toString() {
        return "" + this.key + ":" + this.value;
    }
    
    public Object getKey() {
        return this.key;
    }
    
    public void setKey(final Object key) {
        this.key = key;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public Object setValue(final Object value) {
        return this.value = value;
    }
    
    protected int hash(final Object object) {
        return (object == null) ? 47806 : object.hashCode();
    }
}
