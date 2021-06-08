// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.Collections;
import java.util.Iterator;
import groovy.lang.GroovyObjectSupport;

public class NullObject extends GroovyObjectSupport
{
    private static final NullObject INSTANCE;
    
    private NullObject() {
    }
    
    public static NullObject getNullObject() {
        return NullObject.INSTANCE;
    }
    
    public Object clone() {
        throw new NullPointerException("Cannot invoke method clone() on null object");
    }
    
    @Override
    public Object getProperty(final String property) {
        throw new NullPointerException("Cannot get property '" + property + "' on null object");
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        throw new NullPointerException("Cannot set property '" + property + "' on null object");
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        throw new NullPointerException("Cannot invoke method " + name + "() on null object");
    }
    
    @Override
    public boolean equals(final Object to) {
        return to == null;
    }
    
    public Iterator iterator() {
        return Collections.EMPTY_LIST.iterator();
    }
    
    public Object plus(final String s) {
        return this.getMetaClass().invokeMethod(this, "toString", new Object[0]) + s;
    }
    
    public boolean is(final Object other) {
        return other == null;
    }
    
    public Object asType(final Class c) {
        return null;
    }
    
    public boolean asBoolean() {
        return false;
    }
    
    @Override
    public String toString() {
        return "null";
    }
    
    @Override
    public int hashCode() {
        throw new NullPointerException("Cannot invoke method hashCode() on null object");
    }
    
    static {
        INSTANCE = new NullObject();
    }
}
