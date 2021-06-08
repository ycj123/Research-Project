// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

class DeadEndObject
{
    public Object getProperty(final String property) {
        throw new DeadEndException("Cannot bind to a property on the return value of a method call");
    }
    
    public Object invokeMethod(final String name, final Object args) {
        return this;
    }
}
