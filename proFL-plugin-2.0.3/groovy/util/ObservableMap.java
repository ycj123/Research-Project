// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Collections;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import groovy.lang.Closure;
import java.beans.PropertyChangeSupport;
import java.util.Map;

public class ObservableMap implements Map
{
    private Map delegate;
    private PropertyChangeSupport pcs;
    private Closure test;
    
    public ObservableMap() {
        this(new LinkedHashMap(), null);
    }
    
    public ObservableMap(final Closure test) {
        this(new LinkedHashMap(), test);
    }
    
    public ObservableMap(final Map delegate) {
        this(delegate, null);
    }
    
    public ObservableMap(final Map delegate, final Closure test) {
        this.delegate = delegate;
        this.test = test;
        this.pcs = new PropertyChangeSupport(this);
    }
    
    public void clear() {
        final Map values = new HashMap();
        if (!this.delegate.isEmpty()) {
            values.putAll(this.delegate);
        }
        this.delegate.clear();
        if (values != null) {
            this.pcs.firePropertyChange(new PropertyClearedEvent(this, values));
        }
    }
    
    public boolean containsKey(final Object key) {
        return this.delegate.containsKey(key);
    }
    
    public boolean containsValue(final Object value) {
        return this.delegate.containsValue(value);
    }
    
    public Set entrySet() {
        return this.delegate.entrySet();
    }
    
    @Override
    public boolean equals(final Object o) {
        return this.delegate.equals(o);
    }
    
    public Object get(final Object key) {
        return this.delegate.get(key);
    }
    
    @Override
    public int hashCode() {
        return this.delegate.hashCode();
    }
    
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }
    
    public Set keySet() {
        return this.delegate.keySet();
    }
    
    public Object put(final Object key, final Object value) {
        Object oldValue = null;
        final boolean newKey = !this.delegate.containsKey(key);
        if (this.test != null) {
            oldValue = this.delegate.put(key, value);
            Object result = null;
            if (this.test.getMaximumNumberOfParameters() == 2) {
                result = this.test.call(new Object[] { key, value });
            }
            else {
                result = this.test.call(value);
            }
            if (result != null && result instanceof Boolean && (boolean)result) {
                if (newKey) {
                    this.pcs.firePropertyChange(new PropertyAddedEvent(this, String.valueOf(key), value));
                }
                else if (oldValue != value) {
                    this.pcs.firePropertyChange(new PropertyUpdatedEvent(this, String.valueOf(key), oldValue, value));
                }
            }
        }
        else {
            oldValue = this.delegate.put(key, value);
            if (newKey) {
                this.pcs.firePropertyChange(new PropertyAddedEvent(this, String.valueOf(key), value));
            }
            else if (oldValue != value) {
                this.pcs.firePropertyChange(new PropertyUpdatedEvent(this, String.valueOf(key), oldValue, value));
            }
        }
        return oldValue;
    }
    
    public void putAll(final Map map) {
        if (map != null) {
            final List events = new ArrayList();
            for (final Entry entry : map.entrySet()) {
                final String key = String.valueOf(entry.getKey());
                final Object newValue = entry.getValue();
                Object oldValue = null;
                final boolean newKey = !this.delegate.containsKey(key);
                if (this.test != null) {
                    oldValue = this.delegate.put(key, newValue);
                    Object result = null;
                    if (this.test.getMaximumNumberOfParameters() == 2) {
                        result = this.test.call(new Object[] { key, newValue });
                    }
                    else {
                        result = this.test.call(newValue);
                    }
                    if (result == null || !(result instanceof Boolean) || !(boolean)result) {
                        continue;
                    }
                    if (newKey) {
                        events.add(new PropertyAddedEvent(this, key, newValue));
                    }
                    else {
                        if (oldValue == newValue) {
                            continue;
                        }
                        events.add(new PropertyUpdatedEvent(this, key, oldValue, newValue));
                    }
                }
                else {
                    oldValue = this.delegate.put(key, newValue);
                    if (newKey) {
                        events.add(new PropertyAddedEvent(this, key, newValue));
                    }
                    else {
                        if (oldValue == newValue) {
                            continue;
                        }
                        events.add(new PropertyUpdatedEvent(this, key, oldValue, newValue));
                    }
                }
            }
            if (events.size() > 0) {
                this.pcs.firePropertyChange(new MultiPropertyEvent(this, events.toArray(new PropertyEvent[events.size()])));
            }
        }
    }
    
    public Object remove(final Object key) {
        final Object result = this.delegate.remove(key);
        if (key != null) {
            this.pcs.firePropertyChange(new PropertyRemovedEvent(this, String.valueOf(key), result));
        }
        return result;
    }
    
    public int size() {
        return this.delegate.size();
    }
    
    public Collection values() {
        return this.delegate.values();
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
    
    public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(propertyName, listener);
    }
    
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return this.pcs.getPropertyChangeListeners();
    }
    
    public PropertyChangeListener[] getPropertyChangeListeners(final String propertyName) {
        return this.pcs.getPropertyChangeListeners(propertyName);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(propertyName, listener);
    }
    
    public boolean hasListeners(final String propertyName) {
        return this.pcs.hasListeners(propertyName);
    }
    
    public abstract static class PropertyEvent extends PropertyChangeEvent
    {
        public static final int ADDED = 0;
        public static final int UPDATED = 1;
        public static final int REMOVED = 2;
        public static final int CLEARED = 3;
        public static final int MULTI = 4;
        protected static final Object OLDVALUE;
        protected static final Object NEWVALUE;
        private int type;
        
        public PropertyEvent(final Object source, final String propertyName, final Object oldValue, final Object newValue, final int type) {
            super(source, propertyName, oldValue, newValue);
            switch (type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4: {
                    this.type = type;
                    break;
                }
                default: {
                    this.type = 1;
                    break;
                }
            }
        }
        
        public int getType() {
            return this.type;
        }
        
        public String getTypeAsString() {
            switch (this.type) {
                case 0: {
                    return "ADDED";
                }
                case 1: {
                    return "UPDATED";
                }
                case 2: {
                    return "REMOVED";
                }
                case 3: {
                    return "CLEARED";
                }
                case 4: {
                    return "MULTI";
                }
                default: {
                    return "UPDATED";
                }
            }
        }
        
        static {
            OLDVALUE = new Object();
            NEWVALUE = new Object();
        }
    }
    
    public static class PropertyAddedEvent extends PropertyEvent
    {
        public PropertyAddedEvent(final Object source, final String propertyName, final Object newValue) {
            super(source, propertyName, null, newValue, 0);
        }
    }
    
    public static class PropertyUpdatedEvent extends PropertyEvent
    {
        public PropertyUpdatedEvent(final Object source, final String propertyName, final Object oldValue, final Object newValue) {
            super(source, propertyName, oldValue, newValue, 1);
        }
    }
    
    public static class PropertyRemovedEvent extends PropertyEvent
    {
        public PropertyRemovedEvent(final Object source, final String propertyName, final Object oldValue) {
            super(source, propertyName, oldValue, null, 2);
        }
    }
    
    public static class PropertyClearedEvent extends PropertyEvent
    {
        public static final String CLEAR_PROPERTY = "groovy_util_ObservableMap_PropertyClearedEvent_CLEAR";
        private Map values;
        
        public PropertyClearedEvent(final Object source, final Map values) {
            super(source, "groovy_util_ObservableMap_PropertyClearedEvent_CLEAR", PropertyClearedEvent.OLDVALUE, PropertyClearedEvent.NEWVALUE, 3);
            this.values = new HashMap();
            if (values != null) {
                this.values.putAll(values);
            }
        }
        
        public Map getValues() {
            return Collections.unmodifiableMap((Map<?, ?>)this.values);
        }
    }
    
    public static class MultiPropertyEvent extends PropertyEvent
    {
        public static final String MULTI_PROPERTY = "groovy_util_ObservableMap_MultiPropertyEvent_MULTI";
        private PropertyEvent[] events;
        
        public MultiPropertyEvent(final Object source, final PropertyEvent[] events) {
            super(source, "groovy_util_ObservableMap_MultiPropertyEvent_MULTI", MultiPropertyEvent.OLDVALUE, MultiPropertyEvent.NEWVALUE, 4);
            this.events = new PropertyEvent[0];
            if (events != null && events.length > 0) {
                System.arraycopy(events, 0, this.events = new PropertyEvent[events.length], 0, events.length);
            }
        }
        
        public PropertyEvent[] getEvents() {
            final PropertyEvent[] copy = new PropertyEvent[this.events.length];
            System.arraycopy(this.events, 0, copy, 0, this.events.length);
            return copy;
        }
    }
}
