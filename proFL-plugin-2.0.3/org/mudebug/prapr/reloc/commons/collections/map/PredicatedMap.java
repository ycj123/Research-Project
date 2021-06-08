// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.collections.Predicate;
import java.io.Serializable;

public class PredicatedMap extends AbstractInputCheckedMapDecorator implements Serializable
{
    private static final long serialVersionUID = 7412622456128415156L;
    protected final Predicate keyPredicate;
    protected final Predicate valuePredicate;
    
    public static Map decorate(final Map map, final Predicate keyPredicate, final Predicate valuePredicate) {
        return new PredicatedMap(map, keyPredicate, valuePredicate);
    }
    
    protected PredicatedMap(final Map map, final Predicate keyPredicate, final Predicate valuePredicate) {
        super(map);
        this.keyPredicate = keyPredicate;
        this.valuePredicate = valuePredicate;
        for (final Map.Entry entry : map.entrySet()) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            this.validate(key, value);
        }
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(super.map);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        super.map = (Map)in.readObject();
    }
    
    protected void validate(final Object key, final Object value) {
        if (this.keyPredicate != null && !this.keyPredicate.evaluate(key)) {
            throw new IllegalArgumentException("Cannot add key - Predicate rejected it");
        }
        if (this.valuePredicate != null && !this.valuePredicate.evaluate(value)) {
            throw new IllegalArgumentException("Cannot add value - Predicate rejected it");
        }
    }
    
    protected Object checkSetValue(final Object value) {
        if (!this.valuePredicate.evaluate(value)) {
            throw new IllegalArgumentException("Cannot set value - Predicate rejected it");
        }
        return value;
    }
    
    protected boolean isSetValueChecking() {
        return this.valuePredicate != null;
    }
    
    public Object put(final Object key, final Object value) {
        this.validate(key, value);
        return super.map.put(key, value);
    }
    
    public void putAll(final Map mapToCopy) {
        for (final Map.Entry entry : mapToCopy.entrySet()) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            this.validate(key, value);
        }
        super.map.putAll(mapToCopy);
    }
}
