// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import groovy.lang.MetaProperty;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.CachedField;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.lang.reflect.Method;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.wrappers.Wrapper;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.ParameterTypes;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import java.util.concurrent.atomic.AtomicInteger;

public class AbstractCallSite implements CallSite
{
    protected final int index;
    protected final String name;
    protected final CallSiteArray array;
    protected final AtomicInteger usage;
    
    public AbstractCallSite(final CallSiteArray array, final int index, final String name) {
        this.name = name;
        this.index = index;
        this.array = array;
        this.usage = GroovyCategorySupport.getCategoryNameUsage(name);
    }
    
    public AbstractCallSite(final CallSite prev) {
        this.name = prev.getName();
        this.index = prev.getIndex();
        this.array = prev.getArray();
        this.usage = prev.getUsage();
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public CallSiteArray getArray() {
        return this.array;
    }
    
    public String getName() {
        return this.name;
    }
    
    public AtomicInteger getUsage() {
        return this.usage;
    }
    
    public final Object callSafe(final Object receiver, final Object[] args) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return this.call(receiver, args);
    }
    
    public final Object callSafe(final Object receiver) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return this.call(receiver);
    }
    
    public final Object callSafe(final Object receiver, final Object arg1) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return this.call(receiver, arg1);
    }
    
    public final Object callSafe(final Object receiver, final Object arg1, final Object arg2) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return this.call(receiver, arg1, arg2);
    }
    
    public final Object callSafe(final Object receiver, final Object arg1, final Object arg2, final Object arg3) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return this.call(receiver, arg1, arg2, arg3);
    }
    
    public Object callSafe(final Object receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return this.call(receiver, arg1, arg2, arg3, arg4);
    }
    
    public Object call(final Object receiver, final Object[] args) throws Throwable {
        return CallSiteArray.defaultCall(this, receiver, args);
    }
    
    public Object call(final Object receiver) throws Throwable {
        return this.call(receiver, CallSiteArray.NOPARAM);
    }
    
    public Object call(final Object receiver, final Object arg1) throws Throwable {
        return this.call(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object call(final Object receiver, final Object arg1, final Object arg2) throws Throwable {
        return this.call(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object call(final Object receiver, final Object arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.call(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object call(final Object receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) throws Throwable {
        return this.call(receiver, ArrayUtil.createArray(arg1, arg2, arg3, arg4));
    }
    
    public Object callCurrent(final GroovyObject receiver, final Object[] args) throws Throwable {
        return CallSiteArray.defaultCallCurrent(this, receiver, args);
    }
    
    public Object callCurrent(final GroovyObject receiver) throws Throwable {
        return this.callCurrent(receiver, CallSiteArray.NOPARAM);
    }
    
    public Object callCurrent(final GroovyObject receiver, final Object arg1) throws Throwable {
        return this.callCurrent(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object callCurrent(final GroovyObject receiver, final Object arg1, final Object arg2) throws Throwable {
        return this.callCurrent(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object callCurrent(final GroovyObject receiver, final Object arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.callCurrent(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object callCurrent(final GroovyObject receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) throws Throwable {
        return this.callCurrent(receiver, ArrayUtil.createArray(arg1, arg2, arg3, arg4));
    }
    
    public Object callStatic(final Class receiver, final Object[] args) throws Throwable {
        return CallSiteArray.defaultCallStatic(this, receiver, args);
    }
    
    public Object callStatic(final Class receiver) throws Throwable {
        return this.callStatic(receiver, CallSiteArray.NOPARAM);
    }
    
    public Object callStatic(final Class receiver, final Object arg1) throws Throwable {
        return this.callStatic(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object callStatic(final Class receiver, final Object arg1, final Object arg2) throws Throwable {
        return this.callStatic(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object callStatic(final Class receiver, final Object arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.callStatic(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object callStatic(final Class receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) throws Throwable {
        return this.callStatic(receiver, ArrayUtil.createArray(arg1, arg2, arg3, arg4));
    }
    
    public Object callConstructor(final Object receiver, final Object[] args) throws Throwable {
        return CallSiteArray.defaultCallConstructor(this, receiver, args);
    }
    
    public Object callConstructor(final Object receiver) throws Throwable {
        return this.callConstructor(receiver, CallSiteArray.NOPARAM);
    }
    
    public Object callConstructor(final Object receiver, final Object arg1) throws Throwable {
        return this.callConstructor(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object callConstructor(final Object receiver, final Object arg1, final Object arg2) throws Throwable {
        return this.callConstructor(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object callConstructor(final Object receiver, final Object arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.callConstructor(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object callConstructor(final Object receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) throws Throwable {
        return this.callConstructor(receiver, ArrayUtil.createArray(arg1, arg2, arg3, arg4));
    }
    
    static boolean noCoerce(final ParameterTypes metaMethod, final Object[] args) {
        final CachedClass[] paramClasses = metaMethod.getParameterTypes();
        if (paramClasses.length != args.length) {
            return false;
        }
        for (int i = 0; i < paramClasses.length; ++i) {
            final CachedClass paramClass = paramClasses[i];
            if (args[i] != null && !paramClass.isDirectlyAssignable(args[i])) {
                return true;
            }
        }
        return false;
    }
    
    static boolean noWrappers(final Object[] args) {
        for (int i = 0; i != args.length; ++i) {
            if (args[i] instanceof Wrapper) {
                return false;
            }
        }
        return true;
    }
    
    public Object callGetProperty(final Object receiver) throws Throwable {
        return this.acceptGetProperty(receiver).getProperty(receiver);
    }
    
    public Object callGroovyObjectGetProperty(final Object receiver) throws Throwable {
        return this.acceptGroovyObjectGetProperty(receiver).getProperty(receiver);
    }
    
    public CallSite acceptGetProperty(final Object receiver) {
        return this.createGetPropertySite(receiver);
    }
    
    public CallSite acceptGroovyObjectGetProperty(final Object receiver) {
        return this.createGroovyObjectGetPropertySite(receiver);
    }
    
    protected final CallSite createGetPropertySite(final Object receiver) {
        if (receiver == null) {
            return new NullCallSite(this);
        }
        if (receiver instanceof GroovyObject) {
            return this.createGroovyObjectGetPropertySite(receiver);
        }
        if (receiver instanceof Class) {
            return this.createClassMetaClassGetPropertySite((Class)receiver);
        }
        return this.createPojoMetaClassGetPropertySite(receiver);
    }
    
    protected final CallSite createGroovyObjectGetPropertySite(final Object receiver) {
        final Class aClass = receiver.getClass();
        try {
            final Method method = aClass.getMethod("getProperty", String.class);
            if (method != null && method.isSynthetic() && ((GroovyObject)receiver).getMetaClass() instanceof MetaClassImpl) {
                return this.createPogoMetaClassGetPropertySite((GroovyObject)receiver);
            }
        }
        catch (NoSuchMethodException ex) {}
        if (receiver instanceof Class) {
            return this.createClassMetaClassGetPropertySite((Class)receiver);
        }
        return this.createPogoGetPropertySite(aClass);
    }
    
    public Object getProperty(final Object receiver) throws Throwable {
        throw new UnsupportedOperationException();
    }
    
    private CallSite createPojoMetaClassGetPropertySite(final Object receiver) {
        final MetaClass metaClass = InvokerHelper.getMetaClass(receiver);
        CallSite site;
        if (metaClass.getClass() != MetaClassImpl.class || GroovyCategorySupport.hasCategoryInCurrentThread()) {
            site = new PojoMetaClassGetPropertySite(this);
        }
        else {
            final MetaProperty effective = ((MetaClassImpl)metaClass).getEffectiveGetMetaProperty(receiver.getClass(), receiver, this.name, false);
            if (effective != null) {
                if (effective instanceof CachedField) {
                    site = new GetEffectivePojoFieldSite(this, (MetaClassImpl)metaClass, (CachedField)effective);
                }
                else {
                    site = new GetEffectivePojoPropertySite(this, (MetaClassImpl)metaClass, effective);
                }
            }
            else {
                site = new PojoMetaClassGetPropertySite(this);
            }
        }
        return this.array.array[this.index] = site;
    }
    
    private CallSite createClassMetaClassGetPropertySite(final Class aClass) {
        final CallSite site = new ClassMetaClassGetPropertySite(this, aClass);
        return this.array.array[this.index] = site;
    }
    
    private CallSite createPogoMetaClassGetPropertySite(final GroovyObject receiver) {
        final MetaClass metaClass = receiver.getMetaClass();
        CallSite site;
        if (metaClass.getClass() != MetaClassImpl.class || GroovyCategorySupport.hasCategoryInCurrentThread()) {
            site = new PogoMetaClassGetPropertySite(this, metaClass);
        }
        else {
            final MetaProperty effective = ((MetaClassImpl)metaClass).getEffectiveGetMetaProperty(metaClass.getClass(), receiver, this.name, false);
            if (effective != null) {
                if (effective instanceof CachedField) {
                    site = new GetEffectivePogoFieldSite(this, metaClass, (CachedField)effective);
                }
                else {
                    site = new GetEffectivePogoPropertySite(this, metaClass, effective);
                }
            }
            else {
                site = new PogoMetaClassGetPropertySite(this, metaClass);
            }
        }
        return this.array.array[this.index] = site;
    }
    
    private CallSite createPogoGetPropertySite(final Class aClass) {
        final CallSite site = new PogoGetPropertySite(this, aClass);
        return this.array.array[this.index] = site;
    }
    
    public final Object callGetPropertySafe(final Object receiver) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return this.callGetProperty(receiver);
    }
    
    public final Object callGroovyObjectGetPropertySafe(final Object receiver) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return this.callGroovyObjectGetProperty(receiver);
    }
}
