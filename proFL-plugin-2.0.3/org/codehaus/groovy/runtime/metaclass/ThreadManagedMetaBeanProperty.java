// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.ReflectionCache;
import groovy.lang.MetaProperty;
import org.codehaus.groovy.reflection.CachedClass;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.util.ReferenceBundle;
import groovy.lang.Closure;
import org.codehaus.groovy.util.ManagedConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import groovy.lang.MetaBeanProperty;

public class ThreadManagedMetaBeanProperty extends MetaBeanProperty
{
    private static final ConcurrentHashMap<String, ManagedConcurrentMap> PROPNAME_TO_MAP;
    private final ManagedConcurrentMap instance2Prop;
    private Class declaringClass;
    private ThreadBoundGetter getter;
    private ThreadBoundSetter setter;
    private Object initialValue;
    private Closure initialValueCreator;
    private static final ReferenceBundle SOFT_BUNDLE;
    
    public synchronized Object getInitialValue() {
        return this.getInitialValue(null);
    }
    
    public synchronized Object getInitialValue(final Object object) {
        if (this.initialValueCreator != null) {
            return this.initialValueCreator.call(object);
        }
        return this.initialValue;
    }
    
    public void setInitialValueCreator(final Closure callable) {
        this.initialValueCreator = callable;
    }
    
    public ThreadManagedMetaBeanProperty(final Class declaringClass, final String name, final Class type, final Object iv) {
        super(name, type, null, null);
        this.type = type;
        this.declaringClass = declaringClass;
        this.getter = new ThreadBoundGetter(name);
        this.setter = new ThreadBoundSetter(name);
        this.initialValue = iv;
        this.instance2Prop = getInstance2PropName(name);
    }
    
    public ThreadManagedMetaBeanProperty(final Class declaringClass, final String name, final Class type, final Closure initialValueCreator) {
        super(name, type, null, null);
        this.type = type;
        this.declaringClass = declaringClass;
        this.getter = new ThreadBoundGetter(name);
        this.setter = new ThreadBoundSetter(name);
        this.initialValueCreator = initialValueCreator;
        this.instance2Prop = getInstance2PropName(name);
    }
    
    private static ManagedConcurrentMap getInstance2PropName(final String name) {
        ManagedConcurrentMap res = ThreadManagedMetaBeanProperty.PROPNAME_TO_MAP.get(name);
        if (res == null) {
            res = new ManagedConcurrentMap(ThreadManagedMetaBeanProperty.SOFT_BUNDLE);
            final ManagedConcurrentMap ores = ThreadManagedMetaBeanProperty.PROPNAME_TO_MAP.putIfAbsent(name, res);
            if (ores != null) {
                return ores;
            }
        }
        return res;
    }
    
    @Override
    public MetaMethod getGetter() {
        return this.getter;
    }
    
    @Override
    public MetaMethod getSetter() {
        return this.setter;
    }
    
    static {
        PROPNAME_TO_MAP = new ConcurrentHashMap<String, ManagedConcurrentMap>();
        SOFT_BUNDLE = ReferenceBundle.getSoftBundle();
    }
    
    class ThreadBoundGetter extends MetaMethod
    {
        private final String name;
        
        public ThreadBoundGetter(final String name) {
            this.setParametersTypes(new CachedClass[0]);
            this.name = MetaProperty.getGetterName(name, ThreadManagedMetaBeanProperty.this.type);
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
            return ThreadManagedMetaBeanProperty.this.type;
        }
        
        @Override
        public CachedClass getDeclaringClass() {
            return ReflectionCache.getCachedClass(ThreadManagedMetaBeanProperty.this.declaringClass);
        }
        
        @Override
        public Object invoke(final Object object, final Object[] arguments) {
            return ThreadManagedMetaBeanProperty.this.instance2Prop.getOrPut(object, ThreadManagedMetaBeanProperty.this.getInitialValue()).getValue();
        }
    }
    
    private class ThreadBoundSetter extends MetaMethod
    {
        private final String name;
        
        public ThreadBoundSetter(final String name) {
            this.setParametersTypes(new CachedClass[] { ReflectionCache.getCachedClass(ThreadManagedMetaBeanProperty.this.type) });
            this.name = MetaProperty.getSetterName(name);
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
            return ThreadManagedMetaBeanProperty.this.type;
        }
        
        @Override
        public CachedClass getDeclaringClass() {
            return ReflectionCache.getCachedClass(ThreadManagedMetaBeanProperty.this.declaringClass);
        }
        
        @Override
        public Object invoke(final Object object, final Object[] arguments) {
            ThreadManagedMetaBeanProperty.this.instance2Prop.put(object, arguments[0]);
            return null;
        }
    }
}
