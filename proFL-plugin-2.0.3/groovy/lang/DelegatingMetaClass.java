// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.List;
import org.codehaus.groovy.ast.ClassNode;
import java.lang.reflect.Method;

public class DelegatingMetaClass implements MetaClass, MutableMetaClass, GroovyObject
{
    protected MetaClass delegate;
    
    public DelegatingMetaClass(final MetaClass delegate) {
        this.delegate = delegate;
    }
    
    public DelegatingMetaClass(final Class theClass) {
        this(GroovySystem.getMetaClassRegistry().getMetaClass(theClass));
    }
    
    public boolean isModified() {
        return this.delegate instanceof MutableMetaClass && ((MutableMetaClass)this.delegate).isModified();
    }
    
    public void addNewInstanceMethod(final Method method) {
        if (this.delegate instanceof MutableMetaClass) {
            ((MutableMetaClass)this.delegate).addNewInstanceMethod(method);
        }
    }
    
    public void addNewStaticMethod(final Method method) {
        if (this.delegate instanceof MutableMetaClass) {
            ((MutableMetaClass)this.delegate).addNewStaticMethod(method);
        }
    }
    
    public void addMetaMethod(final MetaMethod metaMethod) {
        if (this.delegate instanceof MutableMetaClass) {
            ((MutableMetaClass)this.delegate).addMetaMethod(metaMethod);
        }
    }
    
    public void addMetaBeanProperty(final MetaBeanProperty metaBeanProperty) {
        if (this.delegate instanceof MutableMetaClass) {
            ((MutableMetaClass)this.delegate).addMetaBeanProperty(metaBeanProperty);
        }
    }
    
    public void initialize() {
        this.delegate.initialize();
    }
    
    public Object getAttribute(final Object object, final String attribute) {
        return this.delegate.getAttribute(object, attribute);
    }
    
    public ClassNode getClassNode() {
        return this.delegate.getClassNode();
    }
    
    public List<MetaMethod> getMetaMethods() {
        return this.delegate.getMetaMethods();
    }
    
    public List<MetaMethod> getMethods() {
        return this.delegate.getMethods();
    }
    
    public List<MetaMethod> respondsTo(final Object obj, final String name, final Object[] argTypes) {
        return this.delegate.respondsTo(obj, name, argTypes);
    }
    
    public List<MetaMethod> respondsTo(final Object obj, final String name) {
        return this.delegate.respondsTo(obj, name);
    }
    
    public MetaProperty hasProperty(final Object obj, final String name) {
        return this.delegate.hasProperty(obj, name);
    }
    
    public List<MetaProperty> getProperties() {
        return this.delegate.getProperties();
    }
    
    public Object getProperty(final Object object, final String property) {
        return this.delegate.getProperty(object, property);
    }
    
    public Object invokeConstructor(final Object[] arguments) {
        return this.delegate.invokeConstructor(arguments);
    }
    
    public Object invokeMethod(final Object object, final String methodName, final Object arguments) {
        return this.delegate.invokeMethod(object, methodName, arguments);
    }
    
    public Object invokeMethod(final Object object, final String methodName, final Object[] arguments) {
        return this.delegate.invokeMethod(object, methodName, arguments);
    }
    
    public Object invokeStaticMethod(final Object object, final String methodName, final Object[] arguments) {
        return this.delegate.invokeStaticMethod(object, methodName, arguments);
    }
    
    public void setAttribute(final Object object, final String attribute, final Object newValue) {
        this.delegate.setAttribute(object, attribute, newValue);
    }
    
    public void setProperty(final Object object, final String property, final Object newValue) {
        this.delegate.setProperty(object, property, newValue);
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this.delegate.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return this.delegate.hashCode();
    }
    
    @Override
    public String toString() {
        return super.toString() + "[" + this.delegate.toString() + "]";
    }
    
    @Deprecated
    public MetaMethod pickMethod(final String methodName, final Class[] arguments) {
        return this.delegate.pickMethod(methodName, arguments);
    }
    
    public Object getAttribute(final Class sender, final Object receiver, final String messageName, final boolean useSuper) {
        return this.delegate.getAttribute(sender, receiver, messageName, useSuper);
    }
    
    public Object getProperty(final Class sender, final Object receiver, final String messageName, final boolean useSuper, final boolean fromInsideClass) {
        return this.delegate.getProperty(sender, receiver, messageName, useSuper, fromInsideClass);
    }
    
    public MetaProperty getMetaProperty(final String name) {
        return this.delegate.getMetaProperty(name);
    }
    
    public MetaMethod getStaticMetaMethod(final String name, final Object[] args) {
        return this.delegate.getStaticMetaMethod(name, args);
    }
    
    public MetaMethod getStaticMetaMethod(final String name, final Class[] argTypes) {
        return this.delegate.getStaticMetaMethod(name, argTypes);
    }
    
    public MetaMethod getMetaMethod(final String name, final Object[] args) {
        return this.delegate.getMetaMethod(name, args);
    }
    
    public Class getTheClass() {
        return this.delegate.getTheClass();
    }
    
    public Object invokeMethod(final Class sender, final Object receiver, final String methodName, final Object[] arguments, final boolean isCallToSuper, final boolean fromInsideClass) {
        return this.delegate.invokeMethod(sender, receiver, methodName, arguments, isCallToSuper, fromInsideClass);
    }
    
    public Object invokeMissingMethod(final Object instance, final String methodName, final Object[] arguments) {
        return this.delegate.invokeMissingMethod(instance, methodName, arguments);
    }
    
    public Object invokeMissingProperty(final Object instance, final String propertyName, final Object optionalValue, final boolean isGetter) {
        return this.delegate.invokeMissingProperty(instance, propertyName, optionalValue, isGetter);
    }
    
    public boolean isGroovyObject() {
        return GroovyObject.class.isAssignableFrom(this.delegate.getTheClass());
    }
    
    public void setAttribute(final Class sender, final Object receiver, final String messageName, final Object messageValue, final boolean useSuper, final boolean fromInsideClass) {
        this.delegate.setAttribute(sender, receiver, messageName, messageValue, useSuper, fromInsideClass);
    }
    
    public void setProperty(final Class sender, final Object receiver, final String messageName, final Object messageValue, final boolean useSuper, final boolean fromInsideClass) {
        this.delegate.setProperty(sender, receiver, messageName, messageValue, useSuper, fromInsideClass);
    }
    
    public int selectConstructorAndTransformArguments(final int numberOfConstructors, final Object[] arguments) {
        return this.delegate.selectConstructorAndTransformArguments(numberOfConstructors, arguments);
    }
    
    public void setAdaptee(final MetaClass adaptee) {
        this.delegate = adaptee;
    }
    
    public MetaClass getAdaptee() {
        return this.delegate;
    }
    
    public Object invokeMethod(final String name, final Object args) {
        try {
            return this.getMetaClass().invokeMethod(this, name, args);
        }
        catch (MissingMethodException e) {
            if (this.delegate instanceof GroovyObject) {
                return ((GroovyObject)this.delegate).invokeMethod(name, args);
            }
            throw e;
        }
    }
    
    public Object getProperty(final String property) {
        try {
            return this.getMetaClass().getProperty(this, property);
        }
        catch (MissingPropertyException e) {
            if (this.delegate instanceof GroovyObject) {
                return ((GroovyObject)this.delegate).getProperty(property);
            }
            throw e;
        }
    }
    
    public void setProperty(final String property, final Object newValue) {
        try {
            this.getMetaClass().setProperty(this, property, newValue);
        }
        catch (MissingPropertyException e) {
            if (!(this.delegate instanceof GroovyObject)) {
                throw e;
            }
            ((GroovyObject)this.delegate).setProperty(property, newValue);
        }
    }
    
    public MetaClass getMetaClass() {
        return InvokerHelper.getMetaClass(this.getClass());
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        throw new UnsupportedOperationException();
    }
}
