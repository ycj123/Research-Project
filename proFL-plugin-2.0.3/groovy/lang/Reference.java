// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.InvokerHelper;
import java.io.Serializable;

public class Reference<T> extends GroovyObjectSupport implements Serializable
{
    private T value;
    
    public Reference() {
    }
    
    public Reference(final T value) {
        this.value = value;
    }
    
    @Override
    public Object getProperty(final String property) {
        final Object value = this.get();
        if (value != null) {
            return InvokerHelper.getProperty(value, property);
        }
        return super.getProperty(property);
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        final Object value = this.get();
        if (value != null) {
            InvokerHelper.setProperty(value, property, newValue);
        }
        else {
            super.setProperty(property, newValue);
        }
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        final Object value = this.get();
        if (value != null) {
            try {
                return InvokerHelper.invokeMethod(value, name, args);
            }
            catch (Exception e) {
                return super.invokeMethod(name, args);
            }
        }
        return super.invokeMethod(name, args);
    }
    
    public T get() {
        return this.value;
    }
    
    public void set(final T value) {
        this.value = value;
    }
}
