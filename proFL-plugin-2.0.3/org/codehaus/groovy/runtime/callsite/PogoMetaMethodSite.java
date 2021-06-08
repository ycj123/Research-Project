// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.codehaus.groovy.reflection.ParameterTypes;
import org.codehaus.groovy.reflection.CachedMethod;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.MetaClassHelper;
import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;

public class PogoMetaMethodSite extends MetaMethodSite
{
    public PogoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
        super(site, metaClass, metaMethod, params);
    }
    
    public Object invoke(final Object receiver, final Object[] args) throws Throwable {
        MetaClassHelper.unwrap(args);
        try {
            return this.metaMethod.doMethodInvoke(receiver, args);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }
    
    @Override
    public Object callCurrent(final GroovyObject receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver, args)) {
            try {
                return this.invoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCallCurrent(this, receiver, args);
    }
    
    @Override
    public Object call(final Object receiver, final Object[] args) throws Throwable {
        if (this.checkCall(receiver, args)) {
            try {
                return this.invoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }
    
    protected boolean checkCall(final Object receiver, final Object[] args) {
        try {
            return this.usage.get() == 0 && ((GroovyObject)receiver).getMetaClass() == this.metaClass && MetaClassHelper.sameClasses(this.params, args);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return false;
            }
            throw e;
        }
        catch (ClassCastException e2) {
            if (!(receiver instanceof GroovyObject)) {
                return false;
            }
            throw e2;
        }
    }
    
    protected boolean checkCall(final Object receiver) {
        try {
            return this.usage.get() == 0 && ((GroovyObject)receiver).getMetaClass() == this.metaClass && MetaClassHelper.sameClasses(this.params);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return false;
            }
            throw e;
        }
        catch (ClassCastException e2) {
            if (!(receiver instanceof GroovyObject)) {
                return false;
            }
            throw e2;
        }
    }
    
    protected boolean checkCall(final Object receiver, final Object arg1) {
        try {
            return this.usage.get() == 0 && ((GroovyObject)receiver).getMetaClass() == this.metaClass && MetaClassHelper.sameClasses(this.params, arg1);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return false;
            }
            throw e;
        }
        catch (ClassCastException e2) {
            if (!(receiver instanceof GroovyObject)) {
                return false;
            }
            throw e2;
        }
    }
    
    protected boolean checkCall(final Object receiver, final Object arg1, final Object arg2) {
        try {
            return this.usage.get() == 0 && ((GroovyObject)receiver).getMetaClass() == this.metaClass && MetaClassHelper.sameClasses(this.params, arg1, arg2);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return false;
            }
            throw e;
        }
        catch (ClassCastException e2) {
            if (!(receiver instanceof GroovyObject)) {
                return false;
            }
            throw e2;
        }
    }
    
    protected boolean checkCall(final Object receiver, final Object arg1, final Object arg2, final Object arg3) {
        try {
            return this.usage.get() == 0 && ((GroovyObject)receiver).getMetaClass() == this.metaClass && MetaClassHelper.sameClasses(this.params, arg1, arg2, arg3);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return false;
            }
            throw e;
        }
        catch (ClassCastException e2) {
            if (!(receiver instanceof GroovyObject)) {
                return false;
            }
            throw e2;
        }
    }
    
    protected boolean checkCall(final Object receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) {
        try {
            return this.usage.get() == 0 && ((GroovyObject)receiver).getMetaClass() == this.metaClass && MetaClassHelper.sameClasses(this.params, arg1, arg2, arg3, arg4);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return false;
            }
            throw e;
        }
        catch (ClassCastException e2) {
            if (!(receiver instanceof GroovyObject)) {
                return false;
            }
            throw e2;
        }
    }
    
    public static CallSite createPogoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object[] args) {
        if (metaMethod.getClass() == CachedMethod.class) {
            return createCachedMethodSite(site, metaClass, (CachedMethod)metaMethod, params, args);
        }
        return createNonAwareCallSite(site, metaClass, metaMethod, params, args);
    }
    
    private static CallSite createNonAwareCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object[] args) {
        if (metaMethod.correctArguments(args) != args || !AbstractCallSite.noWrappers(args)) {
            return new PogoMetaMethodSite(site, metaClass, metaMethod, params);
        }
        if (AbstractCallSite.noCoerce(metaMethod, args)) {
            return new PogoMetaMethodSiteNoUnwrap(site, metaClass, metaMethod, params);
        }
        return new PogoMetaMethodSiteNoUnwrapNoCoerce(site, metaClass, metaMethod, params);
    }
    
    public static CallSite createCachedMethodSite(final CallSite site, final MetaClassImpl metaClass, final CachedMethod metaMethod, final Class[] params, final Object[] args) {
        if (metaMethod.correctArguments(args) != args || !AbstractCallSite.noWrappers(args)) {
            return new PogoCachedMethodSite(site, metaClass, metaMethod, params);
        }
        if (AbstractCallSite.noCoerce(metaMethod, args)) {
            return new PogoCachedMethodSiteNoUnwrap(site, metaClass, metaMethod, params);
        }
        return metaMethod.createPogoMetaMethodSite(site, metaClass, params);
    }
    
    public static class PogoCachedMethodSite extends PogoMetaMethodSite
    {
        final Method reflect;
        
        public PogoCachedMethodSite(final CallSite site, final MetaClassImpl metaClass, final CachedMethod metaMethod, final Class[] params) {
            super(site, metaClass, metaMethod, params);
            this.reflect = metaMethod.setAccessible();
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
    
    public static class PogoCachedMethodSiteNoUnwrap extends PogoCachedMethodSite
    {
        public PogoCachedMethodSiteNoUnwrap(final CallSite site, final MetaClassImpl metaClass, final CachedMethod metaMethod, final Class[] params) {
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
    
    public static class PogoCachedMethodSiteNoUnwrapNoCoerce extends PogoCachedMethodSite
    {
        public PogoCachedMethodSiteNoUnwrapNoCoerce(final CallSite site, final MetaClassImpl metaClass, final CachedMethod metaMethod, final Class[] params) {
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
    
    public static class PogoMetaMethodSiteNoUnwrap extends PogoMetaMethodSite
    {
        public PogoMetaMethodSiteNoUnwrap(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
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
    
    public static class PogoMetaMethodSiteNoUnwrapNoCoerce extends PogoMetaMethodSite
    {
        public PogoMetaMethodSiteNoUnwrapNoCoerce(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params) {
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
