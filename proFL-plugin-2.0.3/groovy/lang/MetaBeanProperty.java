// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.lang.reflect.Modifier;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.reflection.CachedField;

public class MetaBeanProperty extends MetaProperty
{
    private MetaMethod getter;
    private MetaMethod setter;
    private CachedField field;
    
    public MetaBeanProperty(final String name, final Class type, final MetaMethod getter, final MetaMethod setter) {
        super(name, type);
        this.getter = getter;
        this.setter = setter;
    }
    
    @Override
    public Object getProperty(final Object object) {
        final MetaMethod getter = this.getGetter();
        if (getter != null) {
            return getter.invoke(object, MetaClassHelper.EMPTY_ARRAY);
        }
        if (this.field != null) {
            return this.field.getProperty(object);
        }
        throw new GroovyRuntimeException("Cannot read write-only property: " + this.name);
    }
    
    @Override
    public void setProperty(final Object object, Object newValue) {
        final MetaMethod setter = this.getSetter();
        if (setter != null) {
            newValue = DefaultTypeTransformation.castToType(newValue, this.getType());
            setter.invoke(object, new Object[] { newValue });
            return;
        }
        if (this.field != null && !Modifier.isFinal(this.field.getModifiers())) {
            this.field.setProperty(object, newValue);
            return;
        }
        throw new GroovyRuntimeException("Cannot set read-only property: " + this.name);
    }
    
    public MetaMethod getGetter() {
        return this.getter;
    }
    
    public MetaMethod getSetter() {
        return this.setter;
    }
    
    void setGetter(final MetaMethod getter) {
        this.getter = getter;
    }
    
    void setSetter(final MetaMethod setter) {
        this.setter = setter;
    }
    
    @Override
    public int getModifiers() {
        final MetaMethod getter = this.getGetter();
        final MetaMethod setter = this.getSetter();
        if (setter != null && getter == null) {
            return setter.getModifiers();
        }
        if (getter != null && setter == null) {
            return getter.getModifiers();
        }
        final int modifiers = getter.getModifiers() | setter.getModifiers();
        int visibility = 0;
        if (Modifier.isPublic(modifiers)) {
            visibility = 1;
        }
        if (Modifier.isProtected(modifiers)) {
            visibility = 4;
        }
        if (Modifier.isPrivate(modifiers)) {
            visibility = 2;
        }
        int states = getter.getModifiers() & setter.getModifiers();
        states &= 0xFFFFFFF8;
        states |= visibility;
        return states;
    }
    
    public void setField(final CachedField f) {
        this.field = f;
    }
    
    public CachedField getField() {
        return this.field;
    }
}
