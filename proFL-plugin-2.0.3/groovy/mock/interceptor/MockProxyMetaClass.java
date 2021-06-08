// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.PropertyAccessInterceptor;
import groovy.lang.Interceptor;
import groovy.lang.GroovySystem;
import java.beans.IntrospectionException;
import groovy.lang.MetaClass;
import groovy.lang.MetaClassRegistry;
import groovy.lang.ProxyMetaClass;

public class MockProxyMetaClass extends ProxyMetaClass
{
    public final boolean interceptConstruction;
    private boolean fallingThrough;
    static final FallThroughMarker FALL_THROUGH_MARKER;
    
    public MockProxyMetaClass(final MetaClassRegistry registry, final Class theClass, final MetaClass adaptee) throws IntrospectionException {
        this(registry, theClass, adaptee, false);
    }
    
    public MockProxyMetaClass(final MetaClassRegistry registry, final Class theClass, final MetaClass adaptee, final boolean interceptConstruction) throws IntrospectionException {
        super(registry, theClass, adaptee);
        this.interceptConstruction = interceptConstruction;
    }
    
    public static MockProxyMetaClass make(final Class theClass) throws IntrospectionException {
        return make(theClass, false);
    }
    
    public static MockProxyMetaClass make(final Class theClass, final boolean interceptConstruction) throws IntrospectionException {
        final MetaClassRegistry metaRegistry = GroovySystem.getMetaClassRegistry();
        final MetaClass meta = metaRegistry.getMetaClass(theClass);
        return new MockProxyMetaClass(metaRegistry, theClass, meta, interceptConstruction);
    }
    
    @Override
    public Object invokeMethod(final Object object, final String methodName, final Object[] arguments) {
        if (null == this.interceptor && !this.fallingThrough) {
            throw new RuntimeException("cannot invoke method '" + methodName + "' without interceptor");
        }
        Object result = MockProxyMetaClass.FALL_THROUGH_MARKER;
        if (this.interceptor != null) {
            result = this.interceptor.beforeInvoke(object, methodName, arguments);
        }
        if (result == MockProxyMetaClass.FALL_THROUGH_MARKER) {
            final Interceptor saved = this.interceptor;
            this.interceptor = null;
            final boolean savedFallingThrough = this.fallingThrough;
            this.fallingThrough = true;
            result = this.adaptee.invokeMethod(object, methodName, arguments);
            this.fallingThrough = savedFallingThrough;
            this.interceptor = saved;
        }
        return result;
    }
    
    @Override
    public Object invokeStaticMethod(final Object object, final String methodName, final Object[] arguments) {
        if (null == this.interceptor && !this.fallingThrough) {
            throw new RuntimeException("cannot invoke static method '" + methodName + "' without interceptor");
        }
        Object result = MockProxyMetaClass.FALL_THROUGH_MARKER;
        if (this.interceptor != null) {
            result = this.interceptor.beforeInvoke(object, methodName, arguments);
        }
        if (result == MockProxyMetaClass.FALL_THROUGH_MARKER) {
            final Interceptor saved = this.interceptor;
            this.interceptor = null;
            final boolean savedFallingThrough = this.fallingThrough;
            this.fallingThrough = true;
            result = this.adaptee.invokeStaticMethod(object, methodName, arguments);
            this.fallingThrough = savedFallingThrough;
            this.interceptor = saved;
        }
        return result;
    }
    
    @Override
    public Object getProperty(final Class aClass, final Object object, final String property, final boolean b, final boolean b1) {
        if (null == this.interceptor && !this.fallingThrough) {
            throw new RuntimeException("cannot get property '" + property + "' without interceptor");
        }
        Object result = MockProxyMetaClass.FALL_THROUGH_MARKER;
        if (this.interceptor != null && this.interceptor instanceof PropertyAccessInterceptor) {
            result = ((PropertyAccessInterceptor)this.interceptor).beforeGet(object, property);
        }
        if (result == MockProxyMetaClass.FALL_THROUGH_MARKER) {
            final Interceptor saved = this.interceptor;
            this.interceptor = null;
            final boolean savedFallingThrough = this.fallingThrough;
            this.fallingThrough = true;
            result = this.adaptee.getProperty(aClass, object, property, b, b1);
            this.fallingThrough = savedFallingThrough;
            this.interceptor = saved;
        }
        return result;
    }
    
    @Override
    public void setProperty(final Class aClass, final Object object, final String property, final Object newValue, final boolean b, final boolean b1) {
        if (null == this.interceptor && !this.fallingThrough) {
            throw new RuntimeException("cannot set property '" + property + "' without interceptor");
        }
        Object result = MockProxyMetaClass.FALL_THROUGH_MARKER;
        if (this.interceptor != null && this.interceptor instanceof PropertyAccessInterceptor) {
            final Object[] resultHolder = { null };
            ((PropertyAccessInterceptor)this.interceptor).beforeSet(resultHolder, property, newValue);
            result = resultHolder[0];
        }
        if (result == MockProxyMetaClass.FALL_THROUGH_MARKER) {
            final Interceptor saved = this.interceptor;
            this.interceptor = null;
            final boolean savedFallingThrough = this.fallingThrough;
            this.fallingThrough = true;
            this.adaptee.setProperty(aClass, object, property, newValue, b, b1);
            this.fallingThrough = savedFallingThrough;
            this.interceptor = saved;
        }
    }
    
    @Override
    public Object invokeConstructor(final Object[] arguments) {
        if (this.interceptConstruction && null == this.interceptor) {
            throw new RuntimeException("cannot invoke constructor without interceptor");
        }
        if (this.interceptConstruction) {
            final GroovyObject newInstance = (GroovyObject)this.interceptor.beforeInvoke(null, this.getTheClass().getSimpleName(), arguments);
            newInstance.setMetaClass(this);
            return newInstance;
        }
        return this.adaptee.invokeConstructor(arguments);
    }
    
    static {
        FALL_THROUGH_MARKER = new FallThroughMarker(new Object());
    }
    
    static class FallThroughMarker extends Closure
    {
        public FallThroughMarker(final Object owner) {
            super(owner);
        }
    }
}
