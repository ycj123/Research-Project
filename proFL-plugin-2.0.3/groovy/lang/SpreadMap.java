// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.Iterator;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import java.util.HashMap;

public class SpreadMap extends HashMap
{
    private Map mapData;
    private int hashCode;
    
    public SpreadMap(final Object[] values) {
        this.mapData = new HashMap(values.length / 2);
        int i = 0;
        while (i < values.length) {
            this.mapData.put(values[i++], values[i++]);
        }
    }
    
    public SpreadMap(final Map map) {
        this.mapData = map;
    }
    
    @Override
    public Object get(final Object obj) {
        return this.mapData.get(obj);
    }
    
    @Override
    public Object put(final Object key, final Object value) {
        throw new RuntimeException("SpreadMap: " + this + " is an immutable map, and so (" + key + ": " + value + ") cannot be added.");
    }
    
    @Override
    public Object remove(final Object key) {
        throw new RuntimeException("SpreadMap: " + this + " is an immutable map, and so the key (" + key + ") cannot be deleteded.");
    }
    
    @Override
    public void putAll(final Map t) {
        throw new RuntimeException("SpreadMap: " + this + " is an immutable map, and so the map (" + t + ") cannot be put in this spreadMap.");
    }
    
    @Override
    public int size() {
        return this.mapData.keySet().size();
    }
    
    @Override
    public boolean equals(final Object that) {
        return that instanceof SpreadMap && this.equals((SpreadMap)that);
    }
    
    public boolean equals(final SpreadMap that) {
        if (that == null) {
            return false;
        }
        if (this.size() == that.size()) {
            final SpreadMap other = that;
            for (final Object key : this.mapData.keySet()) {
                if (!DefaultTypeTransformation.compareEqual(this.get(key), other.get(key))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            for (final Object key : this.mapData.keySet()) {
                final int hash = (key != null) ? key.hashCode() : 47806;
                this.hashCode ^= hash;
            }
        }
        return this.hashCode;
    }
    
    @Override
    public String toString() {
        if (this.mapData.isEmpty()) {
            return "*:[:]";
        }
        final StringBuffer buff = new StringBuffer("*:[");
        final Iterator iter = this.mapData.keySet().iterator();
        while (iter.hasNext()) {
            final Object key = iter.next();
            buff.append(key + ":" + this.mapData.get(key));
            if (iter.hasNext()) {
                buff.append(", ");
            }
        }
        buff.append("]");
        return buff.toString();
    }
}
