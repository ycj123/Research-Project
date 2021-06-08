// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Iterator;
import groovy.lang.MissingMethodException;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.GroovyObjectSupport;

public class Proxy extends GroovyObjectSupport
{
    private Object adaptee;
    
    public Proxy() {
        this.adaptee = null;
    }
    
    public Proxy wrap(final Object adaptee) {
        this.setAdaptee(adaptee);
        return this;
    }
    
    public Object getAdaptee() {
        return this.adaptee;
    }
    
    public void setAdaptee(final Object adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        try {
            return super.invokeMethod(name, args);
        }
        catch (MissingMethodException e) {
            return InvokerHelper.invokeMethod(this.adaptee, name, args);
        }
    }
    
    public Iterator iterator() {
        return InvokerHelper.asIterator(this.adaptee);
    }
}
