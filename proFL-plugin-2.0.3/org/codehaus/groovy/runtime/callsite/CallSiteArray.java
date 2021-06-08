// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyInterceptable;
import groovy.lang.MetaClass;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.GroovyObject;

public final class CallSiteArray
{
    public final CallSite[] array;
    public static final Object[] NOPARAM;
    public final Class owner;
    
    public CallSiteArray(final Class owner, final String[] names) {
        this.owner = owner;
        this.array = new CallSite[names.length];
        for (int i = 0; i < this.array.length; ++i) {
            this.array[i] = new AbstractCallSite(this, i, names[i]);
        }
    }
    
    public static Object defaultCall(final CallSite callSite, final Object receiver, final Object[] args) throws Throwable {
        return createCallSite(callSite, receiver, args).call(receiver, args);
    }
    
    public static Object defaultCallCurrent(final CallSite callSite, final GroovyObject receiver, final Object[] args) throws Throwable {
        return createCallCurrentSite(callSite, receiver, args, callSite.getArray().owner).callCurrent(receiver, args);
    }
    
    public static Object defaultCallStatic(final CallSite callSite, final Class receiver, final Object[] args) throws Throwable {
        return createCallStaticSite(callSite, receiver, args).callStatic(receiver, args);
    }
    
    public static Object defaultCallConstructor(final CallSite callSite, final Object receiver, final Object[] args) throws Throwable {
        return createCallConstructorSite(callSite, (Class)receiver, args).callConstructor(receiver, args);
    }
    
    private static CallSite createCallStaticSite(final CallSite callSite, final Class receiver, final Object[] args) {
        final MetaClass metaClass = InvokerHelper.getMetaClass(receiver);
        CallSite site;
        if (metaClass instanceof MetaClassImpl) {
            site = ((MetaClassImpl)metaClass).createStaticSite(callSite, args);
        }
        else {
            site = new StaticMetaClassSite(callSite, metaClass);
        }
        replaceCallSite(callSite, site);
        return site;
    }
    
    private static CallSite createCallConstructorSite(final CallSite callSite, final Class receiver, final Object[] args) {
        final MetaClass metaClass = InvokerHelper.getMetaClass(receiver);
        CallSite site;
        if (metaClass instanceof MetaClassImpl) {
            site = ((MetaClassImpl)metaClass).createConstructorSite(callSite, args);
        }
        else {
            site = new MetaClassConstructorSite(callSite, metaClass);
        }
        replaceCallSite(callSite, site);
        return site;
    }
    
    private static CallSite createCallCurrentSite(final CallSite callSite, final GroovyObject receiver, final Object[] args, final Class sender) {
        CallSite site;
        if (receiver instanceof GroovyInterceptable) {
            site = new PogoInterceptableSite(callSite);
        }
        else {
            final MetaClass metaClass = receiver.getMetaClass();
            if (receiver.getClass() != metaClass.getTheClass() && !metaClass.getTheClass().isInterface()) {
                site = new PogoInterceptableSite(callSite);
            }
            else if (metaClass instanceof MetaClassImpl) {
                site = ((MetaClassImpl)metaClass).createPogoCallCurrentSite(callSite, sender, args);
            }
            else {
                site = new PogoMetaClassSite(callSite, metaClass);
            }
        }
        replaceCallSite(callSite, site);
        return site;
    }
    
    private static CallSite createPojoSite(final CallSite callSite, final Object receiver, final Object[] args) {
        final Class klazz = receiver.getClass();
        final MetaClass metaClass = InvokerHelper.getMetaClass(receiver);
        if (callSite.getUsage().get() == 0 && metaClass instanceof MetaClassImpl) {
            final MetaClassImpl mci = (MetaClassImpl)metaClass;
            final ClassInfo info = mci.getTheCachedClass().classInfo;
            if (info.hasPerInstanceMetaClasses()) {
                return new PerInstancePojoMetaClassSite(callSite, info);
            }
            return mci.createPojoCallSite(callSite, receiver, args);
        }
        else {
            final ClassInfo info2 = ClassInfo.getClassInfo(klazz);
            if (info2.hasPerInstanceMetaClasses()) {
                return new PerInstancePojoMetaClassSite(callSite, info2);
            }
            return new PojoMetaClassSite(callSite, metaClass);
        }
    }
    
    private static CallSite createPogoSite(final CallSite callSite, final Object receiver, final Object[] args) {
        if (receiver instanceof GroovyInterceptable) {
            return new PogoInterceptableSite(callSite);
        }
        final MetaClass metaClass = ((GroovyObject)receiver).getMetaClass();
        if (metaClass instanceof MetaClassImpl) {
            return ((MetaClassImpl)metaClass).createPogoCallSite(callSite, args);
        }
        return new PogoMetaClassSite(callSite, metaClass);
    }
    
    private static CallSite createCallSite(final CallSite callSite, final Object receiver, final Object[] args) {
        if (receiver == null) {
            return new NullCallSite(callSite);
        }
        CallSite site;
        if (receiver instanceof Class) {
            site = createCallStaticSite(callSite, (Class)receiver, args);
        }
        else if (receiver instanceof GroovyObject) {
            site = createPogoSite(callSite, receiver, args);
        }
        else {
            site = createPojoSite(callSite, receiver, args);
        }
        replaceCallSite(callSite, site);
        return site;
    }
    
    private static void replaceCallSite(final CallSite oldSite, final CallSite newSite) {
        oldSite.getArray().array[oldSite.getIndex()] = newSite;
    }
    
    static {
        NOPARAM = new Object[0];
    }
}
