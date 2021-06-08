// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import java.util.Iterator;
import java.util.TreeMap;
import groovy.lang.MetaClass;
import groovy.lang.MissingPropertyException;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.Set;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class BindPath
{
    Map<String, TriggerBinding> localSynthetics;
    Object currentObject;
    String propertyName;
    PropertyChangeListener localListener;
    PropertyChangeListener globalListener;
    BindingUpdatable syntheticFullBinding;
    BindPath[] children;
    static final Class[] NAME_PARAMS;
    static final Class[] GLOBAL_PARAMS;
    
    public synchronized void updatePath(final PropertyChangeListener listener, final Object newObject, final Set updateSet) {
        if (this.currentObject != newObject) {
            this.removeListeners();
        }
        if (this.children != null && this.children.length > 0) {
            try {
                Object newValue = null;
                if (newObject != null) {
                    updateSet.add(newObject);
                    newValue = this.extractNewValue(newObject);
                }
                for (final BindPath child : this.children) {
                    child.updatePath(listener, newValue, updateSet);
                }
            }
            catch (Exception ex) {}
        }
        if (this.currentObject != newObject) {
            this.addListeners(listener, newObject, updateSet);
        }
    }
    
    public void addAllListeners(final PropertyChangeListener listener, final Object newObject, final Set updateSet) {
        this.addListeners(listener, newObject, updateSet);
        if (this.children != null && this.children.length > 0) {
            try {
                Object newValue = null;
                if (newObject != null) {
                    updateSet.add(newObject);
                    newValue = this.extractNewValue(newObject);
                }
                for (final BindPath child : this.children) {
                    child.addAllListeners(listener, newValue, updateSet);
                }
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }
    
    private Object extractNewValue(final Object newObject) {
        Object newValue;
        try {
            newValue = InvokerHelper.getProperty(newObject, this.propertyName);
        }
        catch (MissingPropertyException mpe) {
            try {
                newValue = InvokerHelper.getAttribute(newObject, this.propertyName);
                if (newValue instanceof Reference) {
                    newValue = ((Reference)newValue).get();
                }
            }
            catch (Exception e) {
                newValue = null;
            }
        }
        return newValue;
    }
    
    public void addListeners(final PropertyChangeListener listener, final Object newObject, final Set updateSet) {
        this.removeListeners();
        if (newObject != null) {
            final TriggerBinding syntheticTrigger = this.getSyntheticTriggerBinding(newObject);
            final MetaClass mc = InvokerHelper.getMetaClass(newObject);
            if (syntheticTrigger != null) {
                final PropertyBinding psb = new PropertyBinding(newObject, this.propertyName);
                final PropertyChangeProxyTargetBinding proxytb = new PropertyChangeProxyTargetBinding(newObject, this.propertyName, listener);
                (this.syntheticFullBinding = syntheticTrigger.createBinding(psb, proxytb)).bind();
                updateSet.add(newObject);
            }
            else if (!mc.respondsTo(newObject, "addPropertyChangeListener", BindPath.NAME_PARAMS).isEmpty()) {
                InvokerHelper.invokeMethod(newObject, "addPropertyChangeListener", new Object[] { this.propertyName, listener });
                this.localListener = listener;
                updateSet.add(newObject);
            }
            else if (!mc.respondsTo(newObject, "addPropertyChangeListener", BindPath.GLOBAL_PARAMS).isEmpty()) {
                InvokerHelper.invokeMethod(newObject, "addPropertyChangeListener", listener);
                this.globalListener = listener;
                updateSet.add(newObject);
            }
        }
        this.currentObject = newObject;
    }
    
    public void removeListeners() {
        if (this.globalListener != null) {
            try {
                InvokerHelper.invokeMethod(this.currentObject, "removePropertyChangeListener", this.globalListener);
            }
            catch (Exception ex) {}
            this.globalListener = null;
        }
        if (this.localListener != null) {
            try {
                InvokerHelper.invokeMethod(this.currentObject, "removePropertyChangeListener", new Object[] { this.propertyName, this.localListener });
            }
            catch (Exception ex2) {}
            this.localListener = null;
        }
        if (this.syntheticFullBinding != null) {
            this.syntheticFullBinding.unbind();
        }
    }
    
    public synchronized void updateLocalSyntheticProperties(final Map<String, TriggerBinding> synthetics) {
        this.localSynthetics = null;
        final String endName = "#" + this.propertyName;
        for (final Map.Entry<String, TriggerBinding> synteticEntry : synthetics.entrySet()) {
            if (synteticEntry.getKey().endsWith(endName)) {
                if (this.localSynthetics == null) {
                    this.localSynthetics = new TreeMap<String, TriggerBinding>();
                }
                this.localSynthetics.put(synteticEntry.getKey(), synteticEntry.getValue());
            }
        }
    }
    
    public TriggerBinding getSyntheticTriggerBinding(final Object newObject) {
        if (this.localSynthetics == null) {
            return null;
        }
        for (Class currentClass = newObject.getClass(); currentClass != null; currentClass = currentClass.getSuperclass()) {
            final TriggerBinding trigger = this.localSynthetics.get(currentClass.getName() + "#" + this.propertyName);
            if (trigger != null) {
                return trigger;
            }
        }
        return null;
    }
    
    static {
        NAME_PARAMS = new Class[] { String.class, PropertyChangeListener.class };
        GLOBAL_PARAMS = new Class[] { PropertyChangeListener.class };
    }
}
