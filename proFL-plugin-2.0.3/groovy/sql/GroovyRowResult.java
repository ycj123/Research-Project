// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import groovy.lang.MissingPropertyException;
import java.util.Map;
import groovy.lang.GroovyObjectSupport;

public class GroovyRowResult extends GroovyObjectSupport implements Map
{
    private final Map result;
    
    public GroovyRowResult(final Map result) {
        this.result = result;
    }
    
    @Override
    public Object getProperty(final String property) {
        try {
            Object value = this.result.get(property);
            if (value != null) {
                return value;
            }
            if (this.result.containsKey(property)) {
                return null;
            }
            final String propertyUpper = property.toUpperCase();
            value = this.result.get(propertyUpper);
            if (value != null) {
                return value;
            }
            if (this.result.containsKey(propertyUpper)) {
                return null;
            }
            throw new MissingPropertyException(property, GroovyRowResult.class);
        }
        catch (Exception e) {
            throw new MissingPropertyException(property, GroovyRowResult.class, e);
        }
    }
    
    public Object getAt(int index) {
        try {
            if (index < 0) {
                index += this.result.size();
            }
            final Iterator it = this.result.values().iterator();
            int i = 0;
            Object obj = null;
            while (obj == null && it.hasNext()) {
                if (i == index) {
                    obj = it.next();
                }
                else {
                    it.next();
                }
                ++i;
            }
            return obj;
        }
        catch (Exception e) {
            throw new MissingPropertyException(Integer.toString(index), GroovyRowResult.class, e);
        }
    }
    
    @Override
    public String toString() {
        return this.result.toString();
    }
    
    public void clear() {
        this.result.clear();
    }
    
    public boolean containsKey(final Object key) {
        return this.result.containsKey(key);
    }
    
    public boolean containsValue(final Object value) {
        return this.result.containsValue(value);
    }
    
    public Set entrySet() {
        return this.result.entrySet();
    }
    
    @Override
    public boolean equals(final Object o) {
        return this.result.equals(o);
    }
    
    public Object get(final Object property) {
        if (property instanceof String) {
            return this.getProperty((String)property);
        }
        return null;
    }
    
    @Override
    public int hashCode() {
        return this.result.hashCode();
    }
    
    public boolean isEmpty() {
        return this.result.isEmpty();
    }
    
    public Set keySet() {
        return this.result.keySet();
    }
    
    public Object put(final Object key, final Object value) {
        return this.result.put(key, value);
    }
    
    public void putAll(final Map t) {
        this.result.putAll(t);
    }
    
    public Object remove(final Object key) {
        return this.result.remove(key);
    }
    
    public int size() {
        return this.result.size();
    }
    
    public Collection values() {
        return this.result.values();
    }
}
