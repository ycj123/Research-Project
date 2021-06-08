// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.CachedMethod;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;
import java.lang.reflect.Constructor;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.MetaClass;
import java.util.Iterator;
import groovy.lang.MetaClassRegistryChangeEvent;
import groovy.lang.MetaClassRegistryChangeEventListener;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.ExpandoMetaClass;
import java.util.Map;
import org.codehaus.groovy.runtime.DefaultGroovyStaticMethods;
import org.codehaus.groovy.vmplugin.VMPluginFactory;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import groovy.lang.MetaMethod;
import java.util.List;
import org.codehaus.groovy.reflection.CachedClass;
import java.util.HashMap;
import org.codehaus.groovy.util.ReferenceBundle;
import org.codehaus.groovy.util.ManagedLinkedList;
import java.util.LinkedList;
import org.codehaus.groovy.util.FastArray;
import groovy.lang.MetaClassRegistry;

public class MetaClassRegistryImpl implements MetaClassRegistry
{
    private boolean useAccessible;
    private FastArray instanceMethods;
    private FastArray staticMethods;
    private LinkedList changeListenerList;
    private ManagedLinkedList metaClassInfo;
    public static final int LOAD_DEFAULT = 0;
    public static final int DONT_LOAD_DEFAULT = 1;
    private static MetaClassRegistry instanceInclude;
    private static MetaClassRegistry instanceExclude;
    private volatile MetaClassCreationHandle metaClassCreationHandle;
    
    public MetaClassRegistryImpl() {
        this(0, true);
    }
    
    public MetaClassRegistryImpl(final int loadDefault) {
        this(loadDefault, true);
    }
    
    public MetaClassRegistryImpl(final boolean useAccessible) {
        this(0, useAccessible);
    }
    
    public MetaClassRegistryImpl(final int loadDefault, final boolean useAccessible) {
        this.instanceMethods = new FastArray();
        this.staticMethods = new FastArray();
        this.changeListenerList = new LinkedList();
        this.metaClassInfo = new ManagedLinkedList(ReferenceBundle.getWeakBundle());
        this.metaClassCreationHandle = new MetaClassCreationHandle();
        this.useAccessible = useAccessible;
        if (loadDefault == 0) {
            final Map<CachedClass, List<MetaMethod>> map = new HashMap<CachedClass, List<MetaMethod>>();
            this.registerMethods(null, true, true, map);
            final Class[] additionals = DefaultGroovyMethods.additionals;
            for (int i = 0; i != additionals.length; ++i) {
                this.createMetaMethodFromClass(map, additionals[i]);
            }
            final Class[] arr$;
            final Class[] pluginDGMs = arr$ = VMPluginFactory.getPlugin().getPluginDefaultGroovyMethods();
            for (final Class plugin : arr$) {
                this.registerMethods(plugin, false, true, map);
            }
            this.registerMethods(DefaultGroovyStaticMethods.class, false, false, map);
            for (final Map.Entry<CachedClass, List<MetaMethod>> e : map.entrySet()) {
                final CachedClass cls = e.getKey();
                cls.setNewMopMethods(e.getValue());
            }
        }
        this.installMetaClassCreationHandle();
        final MetaClass emcMetaClass = this.metaClassCreationHandle.create(ExpandoMetaClass.class, this);
        emcMetaClass.initialize();
        ClassInfo.getClassInfo(ExpandoMetaClass.class).setStrongMetaClass(emcMetaClass);
        this.addMetaClassRegistryChangeEventListener(new MetaClassRegistryChangeEventListener() {
            public void updateConstantMetaClass(final MetaClassRegistryChangeEvent cmcu) {
                synchronized (MetaClassRegistryImpl.this.metaClassInfo) {
                    MetaClassRegistryImpl.this.metaClassInfo.add(cmcu.getNewMetaClass());
                }
            }
        });
    }
    
    private void installMetaClassCreationHandle() {
        try {
            final Class customMetaClassHandle = Class.forName("groovy.runtime.metaclass.CustomMetaClassCreationHandle");
            final Constructor customMetaClassHandleConstructor = customMetaClassHandle.getConstructor((Class[])new Class[0]);
            this.metaClassCreationHandle = customMetaClassHandleConstructor.newInstance(new Object[0]);
        }
        catch (ClassNotFoundException e2) {
            this.metaClassCreationHandle = new MetaClassCreationHandle();
        }
        catch (Exception e) {
            throw new GroovyRuntimeException("Could not instantiate custom Metaclass creation handle: " + e, e);
        }
    }
    
    private void registerMethods(final Class theClass, final boolean useMethodWrapper, final boolean useInstanceMethods, final Map<CachedClass, List<MetaMethod>> map) {
        if (useMethodWrapper) {
            try {
                final List<GeneratedMetaMethod.DgmMethodRecord> records = GeneratedMetaMethod.DgmMethodRecord.loadDgmInfo();
                for (final GeneratedMetaMethod.DgmMethodRecord record : records) {
                    final Class[] newParams = new Class[record.parameters.length - 1];
                    System.arraycopy(record.parameters, 1, newParams, 0, record.parameters.length - 1);
                    final MetaMethod method = new GeneratedMetaMethod.Proxy(record.className, record.methodName, ReflectionCache.getCachedClass(record.parameters[0]), record.returnType, newParams);
                    final CachedClass declClass = method.getDeclaringClass();
                    List<MetaMethod> arr = map.get(declClass);
                    if (arr == null) {
                        arr = new ArrayList<MetaMethod>(4);
                        map.put(declClass, arr);
                    }
                    arr.add(method);
                    this.instanceMethods.add(method);
                }
                return;
            }
            catch (Throwable e) {
                e.printStackTrace();
                throw new GroovyRuntimeException("Failed to register the DGM methods : " + e, e);
            }
        }
        final CachedMethod[] arr$;
        final CachedMethod[] methods = arr$ = ReflectionCache.getCachedClass(theClass).getMethods();
        for (final CachedMethod method2 : arr$) {
            final int mod = method2.getModifiers();
            if (Modifier.isStatic(mod) && Modifier.isPublic(mod) && method2.getCachedMethod().getAnnotation(Deprecated.class) == null) {
                final CachedClass[] paramTypes = method2.getParameterTypes();
                if (paramTypes.length > 0) {
                    List<MetaMethod> arr2 = map.get(paramTypes[0]);
                    if (arr2 == null) {
                        arr2 = new ArrayList<MetaMethod>(4);
                        map.put(paramTypes[0], arr2);
                    }
                    if (useInstanceMethods) {
                        final NewInstanceMetaMethod metaMethod = new NewInstanceMetaMethod(method2);
                        arr2.add(metaMethod);
                        this.instanceMethods.add(metaMethod);
                    }
                    else {
                        final NewStaticMetaMethod metaMethod2 = new NewStaticMetaMethod(method2);
                        arr2.add(metaMethod2);
                        this.staticMethods.add(metaMethod2);
                    }
                }
            }
        }
    }
    
    private void createMetaMethodFromClass(final Map<CachedClass, List<MetaMethod>> map, final Class aClass) {
        try {
            final MetaMethod method = aClass.newInstance();
            final CachedClass declClass = method.getDeclaringClass();
            List<MetaMethod> arr = map.get(declClass);
            if (arr == null) {
                arr = new ArrayList<MetaMethod>(4);
                map.put(declClass, arr);
            }
            arr.add(method);
            this.instanceMethods.add(method);
        }
        catch (InstantiationException e) {}
        catch (IllegalAccessException ex) {}
    }
    
    public final MetaClass getMetaClass(final Class theClass) {
        return ClassInfo.getClassInfo(theClass).getMetaClass();
    }
    
    public MetaClass getMetaClass(final Object obj) {
        return ClassInfo.getClassInfo(obj.getClass()).getMetaClass(obj);
    }
    
    private void setMetaClass(final Class theClass, final MetaClass oldMc, final MetaClass newMc) {
        final ClassInfo info = ClassInfo.getClassInfo(theClass);
        MetaClass mc = null;
        info.lock();
        try {
            mc = info.getStrongMetaClass();
            info.setStrongMetaClass(newMc);
        }
        finally {
            info.unlock();
        }
        if ((oldMc == null && mc != newMc) || (oldMc != null && mc != newMc && mc != oldMc)) {
            this.fireConstantMetaClassUpdate(theClass, newMc);
        }
    }
    
    public void removeMetaClass(final Class theClass) {
        this.setMetaClass(theClass, null, null);
    }
    
    public void setMetaClass(final Class theClass, final MetaClass theMetaClass) {
        this.setMetaClass(theClass, null, theMetaClass);
    }
    
    public void setMetaClass(final Object obj, final MetaClass theMetaClass) {
        final Class theClass = obj.getClass();
        final ClassInfo info = ClassInfo.getClassInfo(theClass);
        info.lock();
        try {
            info.setPerInstanceMetaClass(obj, theMetaClass);
        }
        finally {
            info.unlock();
        }
        this.fireConstantMetaClassUpdate(theClass, theMetaClass);
    }
    
    public boolean useAccessible() {
        return this.useAccessible;
    }
    
    public MetaClassCreationHandle getMetaClassCreationHandler() {
        return this.metaClassCreationHandle;
    }
    
    public void setMetaClassCreationHandle(final MetaClassCreationHandle handle) {
        if (handle == null) {
            throw new IllegalArgumentException("Cannot set MetaClassCreationHandle to null value!");
        }
        ClassInfo.clearModifiedExpandos();
        handle.setDisableCustomMetaClassLookup(this.metaClassCreationHandle.isDisableCustomMetaClassLookup());
        this.metaClassCreationHandle = handle;
    }
    
    public void addMetaClassRegistryChangeEventListener(final MetaClassRegistryChangeEventListener listener) {
        synchronized (this.changeListenerList) {
            this.changeListenerList.add(listener);
        }
    }
    
    public void removeMetaClassRegistryChangeEventListener(final MetaClassRegistryChangeEventListener listener) {
        synchronized (this.changeListenerList) {
            final Object first = this.changeListenerList.getFirst();
            this.changeListenerList.remove(listener);
            if (this.changeListenerList.size() == 0) {
                this.changeListenerList.addFirst(first);
            }
        }
    }
    
    protected void fireConstantMetaClassUpdate(final Class c, final MetaClass newMc) {
        final MetaClassRegistryChangeEventListener[] listener = this.getMetaClassRegistryChangeEventListeners();
        final MetaClassRegistryChangeEvent cmcu = new MetaClassRegistryChangeEvent(this, c, newMc);
        for (int i = 0; i < listener.length; ++i) {
            listener[i].updateConstantMetaClass(cmcu);
        }
    }
    
    public MetaClassRegistryChangeEventListener[] getMetaClassRegistryChangeEventListeners() {
        synchronized (this.changeListenerList) {
            return this.changeListenerList.toArray(new MetaClassRegistryChangeEventListener[this.changeListenerList.size()]);
        }
    }
    
    public static MetaClassRegistry getInstance(final int includeExtension) {
        if (includeExtension != 1) {
            if (MetaClassRegistryImpl.instanceInclude == null) {
                MetaClassRegistryImpl.instanceInclude = new MetaClassRegistryImpl();
            }
            return MetaClassRegistryImpl.instanceInclude;
        }
        if (MetaClassRegistryImpl.instanceExclude == null) {
            MetaClassRegistryImpl.instanceExclude = new MetaClassRegistryImpl(1);
        }
        return MetaClassRegistryImpl.instanceExclude;
    }
    
    public FastArray getInstanceMethods() {
        return this.instanceMethods;
    }
    
    public FastArray getStaticMethods() {
        return this.staticMethods;
    }
    
    public Iterator iterator() {
        final MetaClass[] refs;
        synchronized (this.metaClassInfo) {
            refs = this.metaClassInfo.toArray(new MetaClass[0]);
        }
        return new Iterator() {
            private int index = 0;
            private MetaClass currentMeta;
            private boolean hasNextCalled = false;
            private boolean hasNext = false;
            
            public boolean hasNext() {
                if (this.hasNextCalled) {
                    return this.hasNext;
                }
                this.hasNextCalled = true;
                if (this.index < refs.length) {
                    this.hasNext = true;
                    this.currentMeta = refs[this.index];
                    ++this.index;
                }
                else {
                    this.hasNext = false;
                }
                return this.hasNext;
            }
            
            private void ensureNext() {
                this.hasNext();
                this.hasNextCalled = false;
            }
            
            public Object next() {
                this.ensureNext();
                return this.currentMeta;
            }
            
            public void remove() {
                this.ensureNext();
                MetaClassRegistryImpl.this.setMetaClass(this.currentMeta.getTheClass(), this.currentMeta, null);
                this.currentMeta = null;
            }
        };
    }
}
