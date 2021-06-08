// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.MetaBeanProperty;
import java.lang.reflect.Method;
import java.util.Iterator;
import groovy.lang.MetaMethod;
import groovy.lang.GroovyObject;
import groovy.lang.ExpandoMetaClass;
import groovy.lang.MetaClass;
import groovy.lang.DelegatingMetaClass;

public class HandleMetaClass extends DelegatingMetaClass
{
    private Object object;
    private static MetaClass myMetaClass;
    private static final Object NONE;
    
    public HandleMetaClass(final MetaClass mc) {
        this(mc, null);
    }
    
    public HandleMetaClass(final MetaClass mc, final Object obj) {
        super(mc);
        if (obj != null) {
            if (InvokerHelper.getMetaClass(obj.getClass()) == mc || !(mc instanceof ExpandoMetaClass)) {
                this.object = obj;
            }
            else {
                this.object = HandleMetaClass.NONE;
            }
        }
        if (HandleMetaClass.myMetaClass == null) {
            HandleMetaClass.myMetaClass = InvokerHelper.getMetaClass(this.getClass());
        }
    }
    
    @Override
    public void initialize() {
        this.replaceDelegate();
        this.delegate.initialize();
    }
    
    public GroovyObject replaceDelegate() {
        if (this.object == null) {
            if (!(this.delegate instanceof ExpandoMetaClass)) {
                (this.delegate = new ExpandoMetaClass(this.delegate.getTheClass(), true, true)).initialize();
            }
            DefaultGroovyMethods.setMetaClass(this.delegate.getTheClass(), this.delegate);
        }
        else if (this.object != HandleMetaClass.NONE) {
            final MetaClass metaClass = this.delegate;
            this.delegate = new ExpandoMetaClass(this.delegate.getTheClass(), false, true);
            if (metaClass instanceof ExpandoMetaClass) {
                final ExpandoMetaClass emc = (ExpandoMetaClass)metaClass;
                for (final MetaMethod method : emc.getExpandoMethods()) {
                    ((ExpandoMetaClass)this.delegate).registerInstanceMethod(method);
                }
            }
            this.delegate.initialize();
            DefaultGroovyMethods.setMetaClass(this.object, this.delegate);
            this.object = HandleMetaClass.NONE;
        }
        return (GroovyObject)this.delegate;
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        return this.replaceDelegate().invokeMethod(name, args);
    }
    
    @Override
    public Object getProperty(final String property) {
        if (ExpandoMetaClass.isValidExpandoProperty(property) && (property.equals("static") || property.equals("constructor") || HandleMetaClass.myMetaClass.hasProperty(this, property) == null)) {
            return this.replaceDelegate().getProperty(property);
        }
        return HandleMetaClass.myMetaClass.getProperty(this, property);
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        this.replaceDelegate().setProperty(property, newValue);
    }
    
    @Override
    public void addNewInstanceMethod(final Method method) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addNewStaticMethod(final Method method) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addMetaMethod(final MetaMethod metaMethod) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addMetaBeanProperty(final MetaBeanProperty metaBeanProperty) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj) || this.getAdaptee().equals(obj) || (obj instanceof HandleMetaClass && this.equals(((HandleMetaClass)obj).getAdaptee()));
    }
    
    static {
        NONE = new Object();
    }
}
