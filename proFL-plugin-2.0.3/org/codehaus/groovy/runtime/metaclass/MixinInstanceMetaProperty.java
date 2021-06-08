// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.CachedClass;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.reflection.MixinInMetaClass;
import groovy.lang.MetaProperty;
import groovy.lang.MetaBeanProperty;

public class MixinInstanceMetaProperty extends MetaBeanProperty
{
    public MixinInstanceMetaProperty(final MetaProperty property, final MixinInMetaClass mixinInMetaClass) {
        super(property.getName(), property.getType(), createGetter(property, mixinInMetaClass), createSetter(property, mixinInMetaClass));
    }
    
    private static MetaMethod createSetter(final MetaProperty property, final MixinInMetaClass mixinInMetaClass) {
        return new MetaMethod() {
            final String name = MetaProperty.getSetterName(property.getName());
            
            {
                this.setParametersTypes(new CachedClass[] { ReflectionCache.getCachedClass(property.getType()) });
            }
            
            @Override
            public int getModifiers() {
                return 1;
            }
            
            @Override
            public String getName() {
                return this.name;
            }
            
            @Override
            public Class getReturnType() {
                return property.getType();
            }
            
            @Override
            public CachedClass getDeclaringClass() {
                return mixinInMetaClass.getInstanceClass();
            }
            
            @Override
            public Object invoke(final Object object, final Object[] arguments) {
                property.setProperty(mixinInMetaClass.getMixinInstance(object), arguments[0]);
                return null;
            }
        };
    }
    
    private static MetaMethod createGetter(final MetaProperty property, final MixinInMetaClass mixinInMetaClass) {
        return new MetaMethod() {
            final String name = MetaProperty.getGetterName(property.getName(), property.getType());
            
            {
                this.setParametersTypes(new CachedClass[0]);
            }
            
            @Override
            public int getModifiers() {
                return 1;
            }
            
            @Override
            public String getName() {
                return this.name;
            }
            
            @Override
            public Class getReturnType() {
                return property.getType();
            }
            
            @Override
            public CachedClass getDeclaringClass() {
                return mixinInMetaClass.getInstanceClass();
            }
            
            @Override
            public Object invoke(final Object object, final Object[] arguments) {
                return property.getProperty(mixinInMetaClass.getMixinInstance(object));
            }
        };
    }
}
