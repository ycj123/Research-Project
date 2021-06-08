// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import groovy.lang.GroovyObject;
import groovy.lang.MetaProperty;
import groovy.lang.MetaMethod;
import java.util.List;
import org.codehaus.groovy.ast.ClassNode;
import groovy.lang.MetaClass;
import groovy.lang.DelegatingMetaClass;

public abstract class OwnedMetaClass extends DelegatingMetaClass
{
    public OwnedMetaClass(final MetaClass delegate) {
        super(delegate);
    }
    
    @Override
    public Object getAttribute(final Object object, final String attribute) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getAttribute(owner, attribute);
    }
    
    protected abstract Object getOwner();
    
    @Override
    public ClassNode getClassNode() {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getClassNode();
    }
    
    @Override
    public List<MetaMethod> getMetaMethods() {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getMetaMethods();
    }
    
    @Override
    public List<MetaMethod> getMethods() {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getMethods();
    }
    
    @Override
    public List<MetaMethod> respondsTo(final Object obj, final String name, final Object[] argTypes) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.respondsTo(owner, name, argTypes);
    }
    
    @Override
    public List<MetaMethod> respondsTo(final Object obj, final String name) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.respondsTo(owner, name);
    }
    
    @Override
    public MetaProperty hasProperty(final Object obj, final String name) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.hasProperty(owner, name);
    }
    
    @Override
    public List<MetaProperty> getProperties() {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getProperties();
    }
    
    @Override
    public Object getProperty(final Object object, final String property) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getProperty(owner, property);
    }
    
    @Override
    public Object invokeConstructor(final Object[] arguments) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.invokeConstructor(arguments);
    }
    
    @Override
    public Object invokeMethod(final Object object, final String methodName, final Object arguments) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.invokeMethod(owner, methodName, arguments);
    }
    
    @Override
    public Object invokeMethod(final Object object, final String methodName, final Object[] arguments) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.invokeMethod(owner, methodName, arguments);
    }
    
    protected abstract MetaClass getOwnerMetaClass(final Object p0);
    
    @Override
    public Object invokeStaticMethod(final Object object, final String methodName, final Object[] arguments) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.invokeStaticMethod(object, methodName, arguments);
    }
    
    @Override
    public void setAttribute(final Object object, final String attribute, final Object newValue) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        ownerMetaClass.setAttribute(object, attribute, newValue);
    }
    
    @Override
    public void setProperty(final Object object, final String property, final Object newValue) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        ownerMetaClass.setProperty(object, property, newValue);
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
    
    @Override
    @Deprecated
    public MetaMethod pickMethod(final String methodName, final Class[] arguments) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.pickMethod(methodName, arguments);
    }
    
    @Override
    public Object getAttribute(final Class sender, final Object receiver, final String messageName, final boolean useSuper) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getAttribute(sender, receiver, messageName, useSuper);
    }
    
    @Override
    public Object getProperty(final Class sender, final Object receiver, final String messageName, final boolean useSuper, final boolean fromInsideClass) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getProperty(sender, receiver, messageName, useSuper, fromInsideClass);
    }
    
    @Override
    public MetaProperty getMetaProperty(final String name) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getMetaProperty(name);
    }
    
    @Override
    public MetaMethod getStaticMetaMethod(final String name, final Object[] args) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getStaticMetaMethod(name, args);
    }
    
    @Override
    public MetaMethod getStaticMetaMethod(final String name, final Class[] argTypes) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getStaticMetaMethod(name, argTypes);
    }
    
    @Override
    public MetaMethod getMetaMethod(final String name, final Object[] args) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getMetaMethod(name, args);
    }
    
    public MetaMethod getMetaMethod(final String name, final Class[] argTypes) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getMetaMethod(name, argTypes);
    }
    
    @Override
    public Class getTheClass() {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.getTheClass();
    }
    
    @Override
    public Object invokeMethod(final Class sender, final Object receiver, final String methodName, final Object[] arguments, final boolean isCallToSuper, final boolean fromInsideClass) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.invokeMethod(sender, owner, methodName, arguments, isCallToSuper, fromInsideClass);
    }
    
    @Override
    public Object invokeMissingMethod(final Object instance, final String methodName, final Object[] arguments) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.invokeMissingMethod(owner, methodName, arguments);
    }
    
    @Override
    public Object invokeMissingProperty(final Object instance, final String propertyName, final Object optionalValue, final boolean isGetter) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.invokeMissingProperty(owner, propertyName, optionalValue, isGetter);
    }
    
    @Override
    public boolean isGroovyObject() {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return GroovyObject.class.isAssignableFrom(ownerMetaClass.getTheClass());
    }
    
    @Override
    public void setAttribute(final Class sender, final Object receiver, final String messageName, final Object messageValue, final boolean useSuper, final boolean fromInsideClass) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        ownerMetaClass.setAttribute(sender, owner, messageName, messageValue, useSuper, fromInsideClass);
    }
    
    @Override
    public void setProperty(final Class sender, final Object receiver, final String messageName, final Object messageValue, final boolean useSuper, final boolean fromInsideClass) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        ownerMetaClass.setProperty(sender, owner, messageName, messageValue, useSuper, fromInsideClass);
    }
    
    @Override
    public int selectConstructorAndTransformArguments(final int numberOfConstructors, final Object[] arguments) {
        final Object owner = this.getOwner();
        final MetaClass ownerMetaClass = this.getOwnerMetaClass(owner);
        return ownerMetaClass.selectConstructorAndTransformArguments(numberOfConstructors, arguments);
    }
}
