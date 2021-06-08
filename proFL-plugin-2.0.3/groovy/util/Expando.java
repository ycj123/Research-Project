// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.HashMap;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.Closure;
import groovy.lang.MissingPropertyException;
import java.util.Iterator;
import groovy.lang.MetaExpandoProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import groovy.lang.GroovyObjectSupport;

public class Expando extends GroovyObjectSupport
{
    private Map expandoProperties;
    
    public Expando() {
    }
    
    public Expando(final Map expandoProperties) {
        this.expandoProperties = expandoProperties;
    }
    
    public Map getProperties() {
        if (this.expandoProperties == null) {
            this.expandoProperties = this.createMap();
        }
        return this.expandoProperties;
    }
    
    public List getMetaPropertyValues() {
        final List ret = new ArrayList();
        for (final Object o : this.getProperties().entrySet()) {
            final Map.Entry entry = (Map.Entry)o;
            ret.add(new MetaExpandoProperty(entry));
        }
        return ret;
    }
    
    @Override
    public Object getProperty(final String property) {
        final Object result = this.getProperties().get(property);
        if (result != null) {
            return result;
        }
        try {
            return super.getProperty(property);
        }
        catch (MissingPropertyException e) {
            return null;
        }
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        this.getProperties().put(property, newValue);
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        try {
            return super.invokeMethod(name, args);
        }
        catch (GroovyRuntimeException e) {
            final Object value = this.getProperty(name);
            if (value instanceof Closure) {
                Closure closure = (Closure)value;
                closure = (Closure)closure.clone();
                closure.setDelegate(this);
                return closure.call((Object[])args);
            }
            throw e;
        }
    }
    
    @Override
    public String toString() {
        final Object method = this.getProperties().get("toString");
        if (method != null && method instanceof Closure) {
            final Closure closure = (Closure)method;
            closure.setDelegate(this);
            return closure.call().toString();
        }
        return this.expandoProperties.toString();
    }
    
    @Override
    public boolean equals(final Object obj) {
        final Object method = this.getProperties().get("equals");
        if (method != null && method instanceof Closure) {
            final Closure closure = (Closure)method;
            closure.setDelegate(this);
            final Boolean ret = (Boolean)closure.call(obj);
            return ret;
        }
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        final Object method = this.getProperties().get("hashCode");
        if (method != null && method instanceof Closure) {
            final Closure closure = (Closure)method;
            closure.setDelegate(this);
            final Integer ret = (Integer)closure.call();
            return ret;
        }
        return super.hashCode();
    }
    
    protected Map createMap() {
        return new HashMap();
    }
}
