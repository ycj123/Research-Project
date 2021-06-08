// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.GroovyRuntimeException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import groovy.lang.MetaProperty;

public class CachedField extends MetaProperty
{
    public final Field field;
    
    public CachedField(final Field field) {
        super(field.getName(), field.getType());
        this.field = field;
    }
    
    public boolean isStatic() {
        return Modifier.isStatic(this.getModifiers());
    }
    
    public boolean isFinal() {
        return Modifier.isFinal(this.getModifiers());
    }
    
    @Override
    public int getModifiers() {
        return this.field.getModifiers();
    }
    
    @Override
    public Object getProperty(final Object object) {
        try {
            return this.field.get(object);
        }
        catch (IllegalAccessException e) {
            throw new GroovyRuntimeException("Cannot get the property '" + this.name + "'.", e);
        }
    }
    
    @Override
    public void setProperty(final Object object, final Object newValue) {
        final Object goalValue = DefaultTypeTransformation.castToType(newValue, this.field.getType());
        if (this.isFinal()) {
            throw new GroovyRuntimeException("Cannot set the property '" + this.name + "' because the backing field is final.");
        }
        try {
            this.field.set(object, goalValue);
        }
        catch (IllegalAccessException ex) {
            throw new GroovyRuntimeException("Cannot set the property '" + this.name + "'.", ex);
        }
    }
}
