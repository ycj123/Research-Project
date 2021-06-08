// 
// Decompiled by Procyon v0.5.36
// 

package groovy.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ValueHolder implements ValueModel
{
    private Object value;
    private final Class type;
    private PropertyChangeSupport propertyChangeSupport;
    private boolean editable;
    
    public ValueHolder() {
        this(Object.class);
    }
    
    public ValueHolder(final Class type) {
        this.editable = true;
        this.type = type;
    }
    
    public ValueHolder(final Object value) {
        this.editable = true;
        this.value = value;
        this.type = ((value != null) ? value.getClass() : Object.class);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        if (this.propertyChangeSupport == null) {
            this.propertyChangeSupport = new PropertyChangeSupport(this);
        }
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        if (this.propertyChangeSupport != null) {
            this.propertyChangeSupport.removePropertyChangeListener(listener);
        }
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        final Object oldValue = this.value;
        this.value = value;
        if (this.propertyChangeSupport != null) {
            this.propertyChangeSupport.firePropertyChange("value", oldValue, value);
        }
    }
    
    public Class getType() {
        return this.type;
    }
    
    public boolean isEditable() {
        return this.editable;
    }
    
    public void setEditable(final boolean editable) {
        this.editable = editable;
    }
}
