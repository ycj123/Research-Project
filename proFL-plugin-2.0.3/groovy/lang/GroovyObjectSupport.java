// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.InvokerHelper;

public abstract class GroovyObjectSupport implements GroovyObject
{
    private transient MetaClass metaClass;
    
    public GroovyObjectSupport() {
        this.metaClass = InvokerHelper.getMetaClass(this.getClass());
    }
    
    public Object getProperty(final String property) {
        return this.getMetaClass().getProperty(this, property);
    }
    
    public void setProperty(final String property, final Object newValue) {
        this.getMetaClass().setProperty(this, property, newValue);
    }
    
    public Object invokeMethod(final String name, final Object args) {
        return this.getMetaClass().invokeMethod(this, name, args);
    }
    
    public MetaClass getMetaClass() {
        if (this.metaClass == null) {
            this.metaClass = InvokerHelper.getMetaClass(this.getClass());
        }
        return this.metaClass;
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        this.metaClass = metaClass;
    }
}
