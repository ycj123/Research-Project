// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import java.lang.reflect.InvocationTargetException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyRuntimeException;
import java.lang.reflect.Method;
import org.codehaus.groovy.reflection.ParameterTypes;
import org.codehaus.groovy.reflection.CachedMethod;
import org.codehaus.groovy.runtime.NullObject;
import org.codehaus.groovy.runtime.MetaClassHelper;
import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;

public class PojoMetaMethodSite extends MetaMethodSite
{
    protected final int version;
    
    public PojoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
        super(site, metaClass, metaMethod, params);
        this.version = metaClass.getVersion();
    }
    
    public Object invoke(final Object receiver, final Object[] args) throws Throwable {
        MetaClassHelper.unwrap(args);
        return this.metaMethod.doMethodInvoke(receiver, args);
    }
    
    @Override
    public Object call(final Object receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver, args)) {
            return this.invoke(receiver, args);
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }
    
    protected final boolean checkPojoMetaClass() {
        return this.usage.get() == 0 && ((MetaClassImpl)this.metaClass).getVersion() == this.version;
    }
    
    protected final boolean checkCall(final Object receiver, final Object[] args) {
        try {
            return receiver.getClass() == this.metaClass.getTheClass() && this.checkPojoMetaClass() && MetaClassHelper.sameClasses(this.params, args);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return this.checkCall(NullObject.getNullObject(), args);
            }
            throw e;
        }
    }
    
    protected final boolean checkCall(final Object receiver) {
        try {
            return receiver.getClass() == this.metaClass.getTheClass() && this.checkPojoMetaClass() && MetaClassHelper.sameClasses(this.params);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return this.checkCall(NullObject.getNullObject());
            }
            throw e;
        }
    }
    
    protected final boolean checkCall(final Object receiver, final Object arg1) {
        try {
            return receiver.getClass() == this.metaClass.getTheClass() && this.checkPojoMetaClass() && MetaClassHelper.sameClasses(this.params, arg1);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return this.checkCall(NullObject.getNullObject(), arg1);
            }
            throw e;
        }
    }
    
    protected final boolean checkCall(final Object receiver, final Object arg1, final Object arg2) {
        try {
            return receiver.getClass() == this.metaClass.getTheClass() && this.checkPojoMetaClass() && MetaClassHelper.sameClasses(this.params, arg1, arg2);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return this.checkCall(NullObject.getNullObject(), arg1, arg2);
            }
            throw e;
        }
    }
    
    protected final boolean checkCall(final Object receiver, final Object arg1, final Object arg2, final Object arg3) {
        try {
            return receiver.getClass() == this.metaClass.getTheClass() && this.checkPojoMetaClass() && MetaClassHelper.sameClasses(this.params, arg1, arg2, arg3);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return this.checkCall(NullObject.getNullObject(), arg1, arg2, arg3);
            }
            throw e;
        }
    }
    
    protected final boolean checkCall(final Object receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) {
        try {
            return receiver.getClass() == this.metaClass.getTheClass() && this.checkPojoMetaClass() && MetaClassHelper.sameClasses(this.params, arg1, arg2, arg3, arg4);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return this.checkCall(NullObject.getNullObject(), arg1, arg2, arg3, arg4);
            }
            throw e;
        }
    }
    
    public static CallSite createPojoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
        if (metaMethod instanceof CallSiteAwareMetaMethod) {
            return ((CallSiteAwareMetaMethod)metaMethod).createPojoCallSite(site, metaClass, metaMethod, params, receiver, args);
        }
        if (metaMethod.getClass() == CachedMethod.class) {
            return createCachedMethodSite(site, metaClass, (CachedMethod)metaMethod, params, args);
        }
        return createNonAwareCallSite(site, metaClass, metaMethod, params, args);
    }
    
    public static CallSite createCachedMethodSite(final CallSite site, final MetaClassImpl metaClass, final CachedMethod metaMethod, final Class[] params, final Object[] args) {
        if (metaMethod.correctArguments(args) != args || !AbstractCallSite.noWrappers(args)) {
            return new PojoCachedMethodSite(site, metaClass, metaMethod, params);
        }
        if (AbstractCallSite.noCoerce(metaMethod, args)) {
            return new PojoCachedMethodSiteNoUnwrap(site, metaClass, metaMethod, params);
        }
        return metaMethod.createPojoMetaMethodSite(site, metaClass, params);
    }
    
    public static CallSite createNonAwareCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object[] args) {
        if (metaMethod.correctArguments(args) != args || !AbstractCallSite.noWrappers(args)) {
            return new PojoMetaMethodSite(site, metaClass, metaMethod, params);
        }
        if (AbstractCallSite.noCoerce(metaMethod, args)) {
            return new PojoMetaMethodSiteNoUnwrap(site, metaClass, metaMethod, params);
        }
        return new PojoMetaMethodSiteNoUnwrapNoCoerce(site, metaClass, metaMethod, params);
    }
    
    public static class PojoCachedMethodSite extends PojoMetaMethodSite
    {
        final Method reflect;
        
        public PojoCachedMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
            this.reflect = ((CachedMethod)metaMethod).setAccessible();
        }
        
        @Override
        public Object invoke(final Object receiver, Object[] args) throws Throwable {
            MetaClassHelper.unwrap(args);
            args = this.metaMethod.coerceArgumentsToClasses(args);
            try {
                return this.reflect.invoke(receiver, args);
            }
            catch (InvocationTargetException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof GroovyRuntimeException) {
                    throw ScriptBytecodeAdapter.unwrap((GroovyRuntimeException)cause);
                }
                throw cause;
            }
        }
    }
    
    public static class PojoCachedMethodSiteNoUnwrap extends PojoCachedMethodSite
    {
        public PojoCachedMethodSiteNoUnwrap(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public final Object invoke(final Object receiver, Object[] args) throws Throwable {
            args = this.metaMethod.coerceArgumentsToClasses(args);
            try {
                return this.reflect.invoke(receiver, args);
            }
            catch (InvocationTargetException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof GroovyRuntimeException) {
                    throw ScriptBytecodeAdapter.unwrap((GroovyRuntimeException)cause);
                }
                throw cause;
            }
        }
    }
    
    public static class PojoCachedMethodSiteNoUnwrapNoCoerce extends PojoCachedMethodSite
    {
        public PojoCachedMethodSiteNoUnwrapNoCoerce(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public final Object invoke(final Object receiver, final Object[] args) throws Throwable {
            try {
                return this.reflect.invoke(receiver, args);
            }
            catch (InvocationTargetException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof GroovyRuntimeException) {
                    throw ScriptBytecodeAdapter.unwrap((GroovyRuntimeException)cause);
                }
                throw cause;
            }
        }
    }
    
    public static class PojoMetaMethodSiteNoUnwrap extends PojoMetaMethodSite
    {
        public PojoMetaMethodSiteNoUnwrap(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public final Object invoke(final Object receiver, final Object[] args) throws Throwable {
            try {
                return this.metaMethod.doMethodInvoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
    }
    
    public static class PojoMetaMethodSiteNoUnwrapNoCoerce extends PojoMetaMethodSite
    {
        public PojoMetaMethodSiteNoUnwrapNoCoerce(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
        }
        
        @Override
        public final Object invoke(final Object receiver, final Object[] args) throws Throwable {
            try {
                return this.metaMethod.invoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
    }
}
