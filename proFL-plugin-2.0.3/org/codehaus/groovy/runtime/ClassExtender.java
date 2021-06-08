// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.HashMap;
import groovy.lang.Closure;
import java.util.Map;

public class ClassExtender
{
    private Map variables;
    private Map methods;
    
    public synchronized Object get(final String name) {
        if (this.variables != null) {
            return this.variables.get(name);
        }
        return null;
    }
    
    public synchronized void set(final String name, final Object value) {
        if (this.variables == null) {
            this.variables = this.createMap();
        }
        this.variables.put(name, value);
    }
    
    public synchronized void remove(final String name) {
        if (this.variables != null) {
            this.variables.remove(name);
        }
    }
    
    public void call(final String name, final Object params) {
        Closure closure = null;
        synchronized (this) {
            if (this.methods != null) {
                closure = this.methods.get(name);
            }
        }
        if (closure != null) {
            closure.call(params);
        }
    }
    
    public synchronized void addMethod(final String name, final Closure closure) {
        if (this.methods == null) {
            this.methods = this.createMap();
        }
        this.methods.put(name, this.methods);
    }
    
    public synchronized void removeMethod(final String name) {
        if (this.methods != null) {
            this.methods.remove(name);
        }
    }
    
    protected Map createMap() {
        return new HashMap();
    }
}
