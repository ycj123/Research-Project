// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.beans.IntrospectionException;

public class ProxyMetaClass extends MetaClassImpl implements AdaptingMetaClass
{
    protected MetaClass adaptee;
    protected Interceptor interceptor;
    
    public static ProxyMetaClass getInstance(final Class theClass) throws IntrospectionException {
        final MetaClassRegistry metaRegistry = GroovySystem.getMetaClassRegistry();
        final MetaClass meta = metaRegistry.getMetaClass(theClass);
        return new ProxyMetaClass(metaRegistry, theClass, meta);
    }
    
    public ProxyMetaClass(final MetaClassRegistry registry, final Class theClass, final MetaClass adaptee) throws IntrospectionException {
        super(registry, theClass);
        this.adaptee = null;
        this.interceptor = null;
        this.adaptee = adaptee;
        if (null == adaptee) {
            throw new IllegalArgumentException("adaptee must not be null");
        }
        super.initialize();
    }
    
    @Override
    public synchronized void initialize() {
        this.adaptee.initialize();
    }
    
    public Object use(final Closure closure) {
        final MetaClass origMetaClass = this.registry.getMetaClass(this.theClass);
        this.registry.setMetaClass(this.theClass, this);
        try {
            return closure.call();
        }
        finally {
            this.registry.setMetaClass(this.theClass, origMetaClass);
        }
    }
    
    public Object use(final GroovyObject object, final Closure closure) {
        final MetaClass origMetaClass = object.getMetaClass();
        object.setMetaClass(this);
        try {
            return closure.call();
        }
        finally {
            object.setMetaClass(origMetaClass);
        }
    }
    
    public Interceptor getInterceptor() {
        return this.interceptor;
    }
    
    public void setInterceptor(final Interceptor interceptor) {
        this.interceptor = interceptor;
    }
    
    @Override
    public Object invokeMethod(final Object object, final String methodName, final Object[] arguments) {
        return this.doCall(object, methodName, arguments, this.interceptor, new Callable() {
            public Object call() {
                return ProxyMetaClass.this.adaptee.invokeMethod(object, methodName, arguments);
            }
        });
    }
    
    @Override
    public Object invokeStaticMethod(final Object object, final String methodName, final Object[] arguments) {
        return this.doCall(object, methodName, arguments, this.interceptor, new Callable() {
            public Object call() {
                return ProxyMetaClass.this.adaptee.invokeStaticMethod(object, methodName, arguments);
            }
        });
    }
    
    @Override
    public Object invokeConstructor(final Object[] arguments) {
        return this.doCall(this.theClass, "ctor", arguments, this.interceptor, new Callable() {
            public Object call() {
                return ProxyMetaClass.this.adaptee.invokeConstructor(arguments);
            }
        });
    }
    
    @Override
    public Object getProperty(final Class aClass, final Object object, final String property, final boolean b, final boolean b1) {
        if (null == this.interceptor) {
            return super.getProperty(aClass, object, property, b, b1);
        }
        if (this.interceptor instanceof PropertyAccessInterceptor) {
            final PropertyAccessInterceptor pae = (PropertyAccessInterceptor)this.interceptor;
            Object result = pae.beforeGet(object, property);
            if (this.interceptor.doInvoke()) {
                result = super.getProperty(aClass, object, property, b, b1);
            }
            return result;
        }
        return super.getProperty(aClass, object, property, b, b1);
    }
    
    @Override
    public void setProperty(final Class aClass, final Object object, final String property, final Object newValue, final boolean b, final boolean b1) {
        if (null == this.interceptor) {
            super.setProperty(aClass, object, property, newValue, b, b1);
        }
        if (this.interceptor instanceof PropertyAccessInterceptor) {
            final PropertyAccessInterceptor pae = (PropertyAccessInterceptor)this.interceptor;
            pae.beforeSet(object, property, newValue);
            if (this.interceptor.doInvoke()) {
                super.setProperty(aClass, object, property, newValue, b, b1);
            }
        }
        else {
            super.setProperty(aClass, object, property, newValue, b, b1);
        }
    }
    
    public MetaClass getAdaptee() {
        return this.adaptee;
    }
    
    public void setAdaptee(final MetaClass metaClass) {
        this.adaptee = metaClass;
    }
    
    private Object doCall(final Object object, final String methodName, final Object[] arguments, final Interceptor interceptor, final Callable howToInvoke) {
        if (null == interceptor) {
            return howToInvoke.call();
        }
        Object result = interceptor.beforeInvoke(object, methodName, arguments);
        if (interceptor.doInvoke()) {
            result = howToInvoke.call();
        }
        result = interceptor.afterInvoke(object, methodName, arguments, result);
        return result;
    }
    
    private interface Callable
    {
        Object call();
    }
}
