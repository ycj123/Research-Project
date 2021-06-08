// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import groovy.lang.GroovyRuntimeException;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.util.FastArray;
import groovy.lang.MetaClass;
import groovy.lang.ExpandoMetaClass;
import org.codehaus.groovy.classgen.BytecodeHelper;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedAction;
import java.lang.reflect.Field;
import java.util.Set;
import groovy.lang.MetaMethod;
import java.util.LinkedList;
import org.codehaus.groovy.runtime.callsite.CallSiteClassLoader;
import org.codehaus.groovy.util.LazyReference;
import org.codehaus.groovy.util.ReferenceBundle;

public class CachedClass
{
    private final Class cachedClass;
    public ClassInfo classInfo;
    private static ReferenceBundle softBundle;
    private final LazyReference<CachedField[]> fields;
    private LazyReference<CachedConstructor[]> constructors;
    private LazyReference<CachedMethod[]> methods;
    private LazyReference<CachedClass> cachedSuperClass;
    private final LazyReference<CallSiteClassLoader> callSiteClassLoader;
    private final LazyReference<LinkedList<ClassInfo>> hierarchy;
    static final MetaMethod[] EMPTY;
    int hashCode;
    public CachedMethod[] mopMethods;
    public static final CachedClass[] EMPTY_ARRAY;
    private final LazyReference<Set<CachedClass>> declaredInterfaces;
    private final LazyReference<Set<CachedClass>> interfaces;
    public final boolean isArray;
    public final boolean isPrimitive;
    public final int modifiers;
    int distance;
    public final boolean isInterface;
    public final boolean isNumber;
    
    public CachedClass(final Class klazz, final ClassInfo classInfo) {
        this.fields = new LazyReference<CachedField[]>(CachedClass.softBundle) {
            @Override
            public CachedField[] initValue() {
                final Field[] declaredFields = AccessController.doPrivileged((PrivilegedAction<Field[]>)new PrivilegedAction() {
                    public Object run() {
                        final Field[] df = CachedClass.this.getTheClass().getDeclaredFields();
                        try {
                            AccessibleObject.setAccessible(df, true);
                        }
                        catch (SecurityException ex) {}
                        return df;
                    }
                });
                final CachedField[] fields = new CachedField[declaredFields.length];
                for (int i = 0; i != fields.length; ++i) {
                    fields[i] = new CachedField(declaredFields[i]);
                }
                return fields;
            }
        };
        this.constructors = new LazyReference<CachedConstructor[]>(CachedClass.softBundle) {
            @Override
            public CachedConstructor[] initValue() {
                final Constructor[] declaredConstructors = AccessController.doPrivileged((PrivilegedAction<Constructor[]>)new PrivilegedAction() {
                    public Object run() {
                        return CachedClass.this.getTheClass().getDeclaredConstructors();
                    }
                });
                final CachedConstructor[] constructors = new CachedConstructor[declaredConstructors.length];
                for (int i = 0; i != constructors.length; ++i) {
                    constructors[i] = new CachedConstructor(CachedClass.this, declaredConstructors[i]);
                }
                return constructors;
            }
        };
        this.methods = new LazyReference<CachedMethod[]>(CachedClass.softBundle) {
            @Override
            public CachedMethod[] initValue() {
                final Method[] declaredMethods = AccessController.doPrivileged((PrivilegedAction<Method[]>)new PrivilegedAction() {
                    public Object run() {
                        final Method[] dm = CachedClass.this.getTheClass().getDeclaredMethods();
                        try {
                            AccessibleObject.setAccessible(dm, true);
                        }
                        catch (SecurityException ex) {}
                        return dm;
                    }
                });
                final List<CachedMethod> methods = new ArrayList<CachedMethod>(declaredMethods.length);
                final List<CachedMethod> mopMethods = new ArrayList<CachedMethod>(declaredMethods.length);
                for (int i = 0; i != declaredMethods.length; ++i) {
                    final CachedMethod cachedMethod = new CachedMethod(CachedClass.this, declaredMethods[i]);
                    final String name = cachedMethod.getName();
                    if (name.indexOf(43) < 0) {
                        if (name.startsWith("this$") || name.startsWith("super$")) {
                            mopMethods.add(cachedMethod);
                        }
                        else {
                            methods.add(cachedMethod);
                        }
                    }
                }
                final CachedMethod[] resMethods = methods.toArray(new CachedMethod[methods.size()]);
                Arrays.sort(resMethods);
                final CachedClass superClass = CachedClass.this.getCachedSuperClass();
                if (superClass != null) {
                    superClass.getMethods();
                    final CachedMethod[] superMopMethods = superClass.mopMethods;
                    for (int j = 0; j != superMopMethods.length; ++j) {
                        mopMethods.add(superMopMethods[j]);
                    }
                }
                Arrays.sort(CachedClass.this.mopMethods = mopMethods.toArray(new CachedMethod[mopMethods.size()]), CachedMethodComparatorByName.INSTANCE);
                return resMethods;
            }
        };
        this.cachedSuperClass = new LazyReference<CachedClass>(CachedClass.softBundle) {
            @Override
            public CachedClass initValue() {
                if (!CachedClass.this.isArray) {
                    return ReflectionCache.getCachedClass(CachedClass.this.getTheClass().getSuperclass());
                }
                if (CachedClass.this.cachedClass.getComponentType().isPrimitive() || CachedClass.this.cachedClass.getComponentType() == Object.class) {
                    return ReflectionCache.OBJECT_CLASS;
                }
                return ReflectionCache.OBJECT_ARRAY_CLASS;
            }
        };
        this.callSiteClassLoader = new LazyReference<CallSiteClassLoader>(CachedClass.softBundle) {
            @Override
            public CallSiteClassLoader initValue() {
                return AccessController.doPrivileged((PrivilegedAction<CallSiteClassLoader>)new PrivilegedAction<CallSiteClassLoader>() {
                    public CallSiteClassLoader run() {
                        return new CallSiteClassLoader(CachedClass.this.cachedClass);
                    }
                });
            }
        };
        this.hierarchy = new LazyReference<LinkedList<ClassInfo>>(CachedClass.softBundle) {
            @Override
            public LinkedList<ClassInfo> initValue() {
                final Set<ClassInfo> res = new LinkedHashSet<ClassInfo>();
                res.add(CachedClass.this.classInfo);
                for (final CachedClass iface : CachedClass.this.getDeclaredInterfaces()) {
                    res.addAll(iface.getHierarchy());
                }
                final CachedClass superClass = CachedClass.this.getCachedSuperClass();
                if (superClass != null) {
                    res.addAll(superClass.getHierarchy());
                }
                if (CachedClass.this.isInterface) {
                    res.add(ReflectionCache.OBJECT_CLASS.classInfo);
                }
                return new LinkedList<ClassInfo>(res);
            }
        };
        this.declaredInterfaces = new LazyReference<Set<CachedClass>>(CachedClass.softBundle) {
            @Override
            public Set<CachedClass> initValue() {
                final Set<CachedClass> res = new HashSet<CachedClass>(0);
                final Class[] arr$;
                final Class[] classes = arr$ = CachedClass.this.getTheClass().getInterfaces();
                for (final Class cls : arr$) {
                    res.add(ReflectionCache.getCachedClass(cls));
                }
                return res;
            }
        };
        this.interfaces = new LazyReference<Set<CachedClass>>(CachedClass.softBundle) {
            @Override
            public Set<CachedClass> initValue() {
                final Set<CachedClass> res = new HashSet<CachedClass>(0);
                if (CachedClass.this.getTheClass().isInterface()) {
                    res.add(CachedClass.this);
                }
                final Class[] arr$;
                final Class[] classes = arr$ = CachedClass.this.getTheClass().getInterfaces();
                for (final Class cls : arr$) {
                    final CachedClass aClass = ReflectionCache.getCachedClass(cls);
                    if (!res.contains(aClass)) {
                        res.addAll(aClass.getInterfaces());
                    }
                }
                final CachedClass superClass = CachedClass.this.getCachedSuperClass();
                if (superClass != null) {
                    res.addAll(superClass.getInterfaces());
                }
                return res;
            }
        };
        this.distance = -1;
        this.cachedClass = klazz;
        this.classInfo = classInfo;
        this.isArray = klazz.isArray();
        this.isPrimitive = klazz.isPrimitive();
        this.modifiers = klazz.getModifiers();
        this.isInterface = klazz.isInterface();
        this.isNumber = Number.class.isAssignableFrom(klazz);
        for (final CachedClass inf : this.getInterfaces()) {
            ReflectionCache.isAssignableFrom(klazz, inf.cachedClass);
        }
        for (CachedClass cur = this; cur != null; cur = cur.getCachedSuperClass()) {
            ReflectionCache.setAssignableFrom(cur.cachedClass, klazz);
        }
    }
    
    public CachedClass getCachedSuperClass() {
        return this.cachedSuperClass.get();
    }
    
    public Set<CachedClass> getInterfaces() {
        return this.interfaces.get();
    }
    
    public Set<CachedClass> getDeclaredInterfaces() {
        return this.declaredInterfaces.get();
    }
    
    public CachedMethod[] getMethods() {
        return this.methods.get();
    }
    
    public CachedField[] getFields() {
        return this.fields.get();
    }
    
    public CachedConstructor[] getConstructors() {
        return this.constructors.get();
    }
    
    public CachedMethod searchMethods(final String name, final CachedClass[] parameterTypes) {
        final CachedMethod[] methods = this.getMethods();
        CachedMethod res = null;
        for (final CachedMethod m : methods) {
            if (m.getName().equals(name) && ReflectionCache.arrayContentsEq(parameterTypes, m.getParameterTypes()) && (res == null || res.getReturnType().isAssignableFrom(m.getReturnType()))) {
                res = m;
            }
        }
        return res;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    public Object coerceArgument(final Object argument) {
        return argument;
    }
    
    public int getSuperClassDistance() {
        synchronized (this.getTheClass()) {
            if (this.distance == -1) {
                int distance = 0;
                for (Class klazz = this.getTheClass(); klazz != null; klazz = klazz.getSuperclass()) {
                    ++distance;
                }
                this.distance = distance;
            }
            return this.distance;
        }
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = super.hashCode();
            if (this.hashCode == 0) {
                this.hashCode = -889274690;
            }
        }
        return this.hashCode;
    }
    
    public boolean isPrimitive() {
        return this.isPrimitive;
    }
    
    public boolean isVoid() {
        return this.getTheClass() == Void.TYPE;
    }
    
    public boolean isInterface() {
        return this.isInterface;
    }
    
    public void doCast(final BytecodeHelper helper) {
        helper.doCast(this.getTheClass());
    }
    
    public String getName() {
        return this.getTheClass().getName();
    }
    
    public String getTypeDescription() {
        return BytecodeHelper.getTypeDescription(this.getTheClass());
    }
    
    public final Class getTheClass() {
        return this.cachedClass;
    }
    
    public MetaMethod[] getNewMetaMethods() {
        final List<MetaMethod> arr = new ArrayList<MetaMethod>();
        arr.addAll(Arrays.asList(this.classInfo.newMetaMethods));
        final MetaClass metaClass = this.classInfo.getStrongMetaClass();
        if (metaClass != null && metaClass instanceof ExpandoMetaClass) {
            arr.addAll(((ExpandoMetaClass)metaClass).getExpandoMethods());
        }
        if (this.isInterface) {
            final MetaClass mc = ReflectionCache.OBJECT_CLASS.classInfo.getStrongMetaClass();
            this.addSubclassExpandos(arr, mc);
        }
        else {
            for (CachedClass cls = this; cls != null; cls = cls.getCachedSuperClass()) {
                final MetaClass mc2 = cls.classInfo.getStrongMetaClass();
                this.addSubclassExpandos(arr, mc2);
            }
        }
        for (final CachedClass inf : this.getInterfaces()) {
            final MetaClass mc3 = inf.classInfo.getStrongMetaClass();
            this.addSubclassExpandos(arr, mc3);
        }
        return arr.toArray(new MetaMethod[arr.size()]);
    }
    
    private void addSubclassExpandos(final List<MetaMethod> arr, final MetaClass mc) {
        if (mc != null && mc instanceof ExpandoMetaClass) {
            final ExpandoMetaClass emc = (ExpandoMetaClass)mc;
            for (final Object mm : emc.getExpandoSubclassMethods()) {
                if (mm instanceof MetaMethod) {
                    final MetaMethod method = (MetaMethod)mm;
                    if (method.getDeclaringClass() != this) {
                        continue;
                    }
                    arr.add(method);
                }
                else {
                    final FastArray farr = (FastArray)mm;
                    for (int i = 0; i != farr.size; ++i) {
                        final MetaMethod method2 = (MetaMethod)farr.get(i);
                        if (method2.getDeclaringClass() == this) {
                            arr.add(method2);
                        }
                    }
                }
            }
        }
    }
    
    public void setNewMopMethods(final List<MetaMethod> arr) {
        final MetaClass metaClass = this.classInfo.getStrongMetaClass();
        if (metaClass == null) {
            this.classInfo.setWeakMetaClass(null);
            this.updateSetNewMopMethods(arr);
            return;
        }
        if (metaClass.getClass() == MetaClassImpl.class) {
            this.classInfo.setStrongMetaClass(null);
            this.updateSetNewMopMethods(arr);
            this.classInfo.setStrongMetaClass(new MetaClassImpl(metaClass.getTheClass()));
            return;
        }
        if (metaClass.getClass() == ExpandoMetaClass.class) {
            this.classInfo.setStrongMetaClass(null);
            this.updateSetNewMopMethods(arr);
            final ExpandoMetaClass newEmc = new ExpandoMetaClass(metaClass.getTheClass());
            newEmc.initialize();
            this.classInfo.setStrongMetaClass(newEmc);
            return;
        }
        throw new GroovyRuntimeException("Can't add methods to class " + this.getTheClass().getName() + ". Strong custom meta class already set.");
    }
    
    private void updateSetNewMopMethods(final List<MetaMethod> arr) {
        if (arr != null) {
            final MetaMethod[] metaMethods = arr.toArray(new MetaMethod[arr.size()]);
            this.classInfo.dgmMetaMethods = metaMethods;
            this.classInfo.newMetaMethods = metaMethods;
        }
        else {
            this.classInfo.newMetaMethods = this.classInfo.dgmMetaMethods;
        }
    }
    
    public void addNewMopMethods(final List<MetaMethod> arr) {
        final MetaClass metaClass = this.classInfo.getStrongMetaClass();
        if (metaClass == null) {
            this.classInfo.setWeakMetaClass(null);
            this.updateAddNewMopMethods(arr);
            return;
        }
        if (metaClass.getClass() == MetaClassImpl.class) {
            this.classInfo.setStrongMetaClass(null);
            this.updateAddNewMopMethods(arr);
            this.classInfo.setStrongMetaClass(new MetaClassImpl(metaClass.getTheClass()));
            return;
        }
        if (metaClass.getClass() == ExpandoMetaClass.class) {
            final ExpandoMetaClass emc = (ExpandoMetaClass)metaClass;
            this.classInfo.setStrongMetaClass(null);
            this.updateAddNewMopMethods(arr);
            final ExpandoMetaClass newEmc = new ExpandoMetaClass(metaClass.getTheClass());
            for (final MetaMethod mm : emc.getExpandoMethods()) {
                newEmc.registerInstanceMethod(mm);
            }
            newEmc.initialize();
            this.classInfo.setStrongMetaClass(newEmc);
            return;
        }
        throw new GroovyRuntimeException("Can't add methods to class " + this.getTheClass().getName() + ". Strong custom meta class already set.");
    }
    
    private void updateAddNewMopMethods(final List<MetaMethod> arr) {
        final List<MetaMethod> res = new ArrayList<MetaMethod>();
        res.addAll(Arrays.asList(this.classInfo.newMetaMethods));
        res.addAll(arr);
        this.classInfo.newMetaMethods = res.toArray(new MetaMethod[res.size()]);
    }
    
    public boolean isAssignableFrom(final Class argument) {
        return argument == null || ReflectionCache.isAssignableFrom(this.getTheClass(), argument);
    }
    
    public boolean isDirectlyAssignable(final Object argument) {
        return ReflectionCache.isAssignableFrom(this.getTheClass(), argument.getClass());
    }
    
    public CallSiteClassLoader getCallSiteLoader() {
        return this.callSiteClassLoader.get();
    }
    
    public Collection<ClassInfo> getHierarchy() {
        return this.hierarchy.get();
    }
    
    @Override
    public String toString() {
        return this.cachedClass.toString();
    }
    
    public CachedClass getCachedClass() {
        return this;
    }
    
    static {
        CachedClass.softBundle = ReferenceBundle.getSoftBundle();
        EMPTY = new MetaMethod[0];
        EMPTY_ARRAY = new CachedClass[0];
    }
    
    public static class CachedMethodComparatorByName implements Comparator
    {
        public static final Comparator INSTANCE;
        
        public int compare(final Object o1, final Object o2) {
            return ((CachedMethod)o1).getName().compareTo(((CachedMethod)o2).getName());
        }
        
        static {
            INSTANCE = new CachedMethodComparatorByName();
        }
    }
    
    public static class CachedMethodComparatorWithString implements Comparator
    {
        public static final Comparator INSTANCE;
        
        public int compare(final Object o1, final Object o2) {
            if (o1 instanceof CachedMethod) {
                return ((CachedMethod)o1).getName().compareTo((String)o2);
            }
            return ((String)o1).compareTo(((CachedMethod)o2).getName());
        }
        
        static {
            INSTANCE = new CachedMethodComparatorWithString();
        }
    }
}
