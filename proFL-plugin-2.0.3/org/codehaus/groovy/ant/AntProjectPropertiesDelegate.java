// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.Enumeration;
import java.util.Collection;
import org.apache.tools.ant.Project;
import java.util.Hashtable;

public class AntProjectPropertiesDelegate extends Hashtable
{
    private Project project;
    
    public AntProjectPropertiesDelegate(final Project project) {
        this.project = project;
    }
    
    @Override
    public synchronized int hashCode() {
        return this.project.getProperties().hashCode();
    }
    
    @Override
    public synchronized int size() {
        return this.project.getProperties().size();
    }
    
    @Override
    public synchronized void clear() {
        throw new UnsupportedOperationException("Impossible to clear the project properties.");
    }
    
    @Override
    public synchronized boolean isEmpty() {
        return this.project.getProperties().isEmpty();
    }
    
    @Override
    public synchronized Object clone() {
        return this.project.getProperties().clone();
    }
    
    @Override
    public synchronized boolean contains(final Object value) {
        return this.project.getProperties().contains(value);
    }
    
    @Override
    public synchronized boolean containsKey(final Object key) {
        return this.project.getProperties().containsKey(key);
    }
    
    @Override
    public boolean containsValue(final Object value) {
        return this.project.getProperties().containsValue(value);
    }
    
    @Override
    public synchronized boolean equals(final Object o) {
        return this.project.getProperties().equals(o);
    }
    
    @Override
    public synchronized String toString() {
        return this.project.getProperties().toString();
    }
    
    @Override
    public Collection values() {
        return this.project.getProperties().values();
    }
    
    @Override
    public synchronized Enumeration elements() {
        return this.project.getProperties().elements();
    }
    
    @Override
    public synchronized Enumeration keys() {
        return this.project.getProperties().keys();
    }
    
    public AntProjectPropertiesDelegate(final Map t) {
        super(t);
    }
    
    @Override
    public synchronized void putAll(final Map t) {
        final Set keySet = t.keySet();
        for (final Object key : keySet) {
            final Object value = t.get(key);
            this.put(key, value);
        }
    }
    
    @Override
    public Set entrySet() {
        return this.project.getProperties().entrySet();
    }
    
    @Override
    public Set keySet() {
        return this.project.getProperties().keySet();
    }
    
    @Override
    public synchronized Object get(final Object key) {
        return this.project.getProperties().get(key);
    }
    
    @Override
    public synchronized Object remove(final Object key) {
        throw new UnsupportedOperationException("Impossible to remove a property from the project properties.");
    }
    
    @Override
    public synchronized Object put(final Object key, final Object value) {
        Object oldValue = null;
        if (this.containsKey(key)) {
            oldValue = this.get(key);
        }
        this.project.setProperty(key.toString(), value.toString());
        return oldValue;
    }
}
