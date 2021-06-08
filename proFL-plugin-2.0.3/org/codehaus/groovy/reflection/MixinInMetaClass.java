// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.metaclass.NewInstanceMetaMethod;
import java.util.Iterator;
import org.codehaus.groovy.runtime.metaclass.MixinInstanceMetaMethod;
import org.codehaus.groovy.runtime.metaclass.MixinInstanceMetaProperty;
import groovy.lang.MetaProperty;
import groovy.lang.GroovySystem;
import groovy.lang.MetaMethod;
import java.util.ArrayList;
import groovy.lang.DelegatingMetaClass;
import org.codehaus.groovy.runtime.HandleMetaClass;
import java.util.List;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.metaclass.MixedInMetaClass;
import org.codehaus.groovy.runtime.MetaClassHelper;
import groovy.lang.GroovyRuntimeException;
import java.lang.reflect.Modifier;
import org.codehaus.groovy.util.ReferenceBundle;
import groovy.lang.ExpandoMetaClass;
import org.codehaus.groovy.util.ManagedConcurrentMap;

public class MixinInMetaClass extends ManagedConcurrentMap
{
    final ExpandoMetaClass emc;
    final CachedClass mixinClass;
    final CachedConstructor constructor;
    private static ReferenceBundle softBundle;
    
    public MixinInMetaClass(final ExpandoMetaClass emc, final CachedClass mixinClass) {
        super(MixinInMetaClass.softBundle);
        this.emc = emc;
        this.mixinClass = mixinClass;
        this.constructor = this.findDefaultConstructor(mixinClass);
        emc.addMixinClass(this);
    }
    
    private CachedConstructor findDefaultConstructor(final CachedClass mixinClass) {
        for (final CachedConstructor constr : mixinClass.getConstructors()) {
            if (Modifier.isPublic(constr.getModifiers())) {
                final CachedClass[] classes = constr.getParameterTypes();
                if (classes.length == 0) {
                    return constr;
                }
            }
        }
        throw new GroovyRuntimeException("No default constructor for class " + mixinClass.getName() + "! Can't be mixed in.");
    }
    
    public synchronized Object getMixinInstance(final Object object) {
        Object mixinInstance = this.get(object);
        if (mixinInstance == null) {
            mixinInstance = this.constructor.invoke(MetaClassHelper.EMPTY_ARRAY);
            new MixedInMetaClass(mixinInstance, object);
            this.put(object, mixinInstance);
        }
        return mixinInstance;
    }
    
    public synchronized void setMixinInstance(final Object object, final Object mixinInstance) {
        if (mixinInstance == null) {
            this.remove(object);
        }
        else {
            this.put(object, mixinInstance);
        }
    }
    
    public CachedClass getInstanceClass() {
        return this.emc.getTheCachedClass();
    }
    
    public CachedClass getMixinClass() {
        return this.mixinClass;
    }
    
    public static void mixinClassesToMetaClass(MetaClass self, final List<Class> categoryClasses) {
        final Class selfClass = self.getTheClass();
        if (self instanceof HandleMetaClass) {
            self = (MetaClass)((HandleMetaClass)self).replaceDelegate();
        }
        if (!(self instanceof ExpandoMetaClass)) {
            if (!(self instanceof DelegatingMetaClass) || !(((DelegatingMetaClass)self).getAdaptee() instanceof ExpandoMetaClass)) {
                throw new GroovyRuntimeException("Can't mixin methods to meta class: " + self);
            }
            self = ((DelegatingMetaClass)self).getAdaptee();
        }
        final ExpandoMetaClass mc = (ExpandoMetaClass)self;
        final List<MetaMethod> arr = new ArrayList<MetaMethod>();
        for (final Class categoryClass : categoryClasses) {
            final CachedClass cachedCategoryClass = ReflectionCache.getCachedClass(categoryClass);
            final MixinInMetaClass mixin = new MixinInMetaClass(mc, cachedCategoryClass);
            final MetaClass metaClass = GroovySystem.getMetaClassRegistry().getMetaClass(categoryClass);
            final List<MetaProperty> propList = metaClass.getProperties();
            for (final MetaProperty prop : propList) {
                if (self.getMetaProperty(prop.getName()) == null) {
                    mc.registerBeanProperty(prop.getName(), new MixinInstanceMetaProperty(prop, mixin));
                }
            }
            for (final MetaProperty prop2 : cachedCategoryClass.getFields()) {
                if (self.getMetaProperty(prop2.getName()) == null) {
                    mc.registerBeanProperty(prop2.getName(), new MixinInstanceMetaProperty(prop2, mixin));
                }
            }
            for (final MetaMethod method : metaClass.getMethods()) {
                final int mod = method.getModifiers();
                if (!Modifier.isPublic(mod)) {
                    continue;
                }
                if (method instanceof CachedMethod && ((CachedMethod)method).getCachedMethod().isSynthetic()) {
                    continue;
                }
                if (Modifier.isStatic(mod)) {
                    if (!(method instanceof CachedMethod)) {
                        continue;
                    }
                    staticMethod(self, arr, (CachedMethod)method);
                }
                else {
                    if (method.getDeclaringClass().getTheClass() == Object.class && !method.getName().equals("toString")) {
                        continue;
                    }
                    final MixinInstanceMetaMethod metaMethod = new MixinInstanceMetaMethod(method, mixin);
                    arr.add(metaMethod);
                }
            }
        }
        for (final Object res : arr) {
            final MetaMethod metaMethod2 = (MetaMethod)res;
            if (metaMethod2.getDeclaringClass().isAssignableFrom(selfClass)) {
                mc.registerInstanceMethod(metaMethod2);
            }
            else {
                mc.registerSubclassInstanceMethod(metaMethod2);
            }
        }
    }
    
    private static void staticMethod(final MetaClass self, final List<MetaMethod> arr, final CachedMethod method) {
        final CachedClass[] paramTypes = method.getParameterTypes();
        if (paramTypes.length == 0) {
            return;
        }
        if (paramTypes[0].isAssignableFrom(self.getTheClass())) {
            NewInstanceMetaMethod metaMethod;
            if (paramTypes[0].getTheClass() == self.getTheClass()) {
                metaMethod = new NewInstanceMetaMethod(method);
            }
            else {
                metaMethod = new NewInstanceMetaMethod(method) {
                    @Override
                    public CachedClass getDeclaringClass() {
                        return ReflectionCache.getCachedClass(self.getTheClass());
                    }
                };
            }
            arr.add(metaMethod);
        }
        else if (self.getTheClass().isAssignableFrom(paramTypes[0].getTheClass())) {
            final NewInstanceMetaMethod metaMethod = new NewInstanceMetaMethod(method);
            arr.add(metaMethod);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MixinInMetaClass)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final MixinInMetaClass that = (MixinInMetaClass)o;
        if (this.mixinClass != null) {
            if (this.mixinClass.equals(that.mixinClass)) {
                return true;
            }
        }
        else if (that.mixinClass == null) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ((this.emc != null) ? this.emc.hashCode() : 0);
        result = 31 * result + ((this.mixinClass != null) ? this.mixinClass.hashCode() : 0);
        result = 31 * result + ((this.constructor != null) ? this.constructor.hashCode() : 0);
        return result;
    }
    
    static {
        MixinInMetaClass.softBundle = ReferenceBundle.getSoftBundle();
    }
}
