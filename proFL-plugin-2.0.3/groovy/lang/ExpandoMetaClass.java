// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.metaclass.MixedInMetaClass;
import org.codehaus.groovy.runtime.metaclass.OwnedMetaClass;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import org.codehaus.groovy.runtime.callsite.ConstructorMetaMethodSite;
import org.codehaus.groovy.runtime.callsite.PogoMetaClassSite;
import org.codehaus.groovy.runtime.callsite.StaticMetaClassSite;
import org.codehaus.groovy.runtime.callsite.PojoMetaClassSite;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.beans.Introspector;
import java.util.Collections;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.ArrayList;
import org.codehaus.groovy.runtime.metaclass.ThreadManagedMetaBeanProperty;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.runtime.DefaultCachedMethodKey;
import org.codehaus.groovy.runtime.metaclass.MetaMethodIndex;
import org.codehaus.groovy.util.FastArray;
import java.util.List;
import org.codehaus.groovy.runtime.metaclass.ClosureMetaMethod;
import org.codehaus.groovy.reflection.CachedClass;
import java.util.Iterator;
import org.codehaus.groovy.runtime.metaclass.MixinInstanceMetaMethod;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.Collection;
import org.codehaus.groovy.reflection.MixinInMetaClass;
import org.codehaus.groovy.runtime.metaclass.ClosureStaticMetaMethod;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.groovy.runtime.MethodKey;
import java.util.Map;
import java.util.Set;

public class ExpandoMetaClass extends MetaClassImpl implements GroovyObject
{
    private static final String META_CLASS = "metaClass";
    private static final String CLASS = "class";
    private static final String META_METHODS = "metaMethods";
    private static final String METHODS = "methods";
    private static final String PROPERTIES = "properties";
    public static final String STATIC_QUALIFIER = "static";
    public static final String CONSTRUCTOR = "constructor";
    private static final String CLASS_PROPERTY = "class";
    private static final String META_CLASS_PROPERTY = "metaClass";
    private static final String GROOVY_CONSTRUCTOR = "<init>";
    private MetaClass myMetaClass;
    private boolean allowChangesAfterInit;
    private boolean initialized;
    private boolean initCalled;
    private boolean modified;
    public boolean inRegistry;
    private final Set<MetaMethod> inheritedMetaMethods;
    private final Map<String, MetaProperty> beanPropertyCache;
    private final Map<String, MetaProperty> staticBeanPropertyCache;
    private final Map<MethodKey, MetaMethod> expandoMethods;
    private final ConcurrentHashMap expandoSubclassMethods;
    private final Map<String, MetaProperty> expandoProperties;
    private ClosureStaticMetaMethod invokeStaticMethodMethod;
    private final Set<MixinInMetaClass> mixinClasses;
    
    public Collection getExpandoSubclassMethods() {
        return this.expandoSubclassMethods.values();
    }
    
    public ExpandoMetaClass(final Class theClass) {
        super(GroovySystem.getMetaClassRegistry(), theClass);
        this.inheritedMetaMethods = new HashSet<MetaMethod>();
        this.beanPropertyCache = new ConcurrentHashMap<String, MetaProperty>();
        this.staticBeanPropertyCache = new ConcurrentHashMap<String, MetaProperty>();
        this.expandoMethods = new ConcurrentHashMap<MethodKey, MetaMethod>();
        this.expandoSubclassMethods = new ConcurrentHashMap();
        this.expandoProperties = new ConcurrentHashMap<String, MetaProperty>();
        this.mixinClasses = new LinkedHashSet<MixinInMetaClass>();
        this.myMetaClass = InvokerHelper.getMetaClass(this.getClass());
    }
    
    public ExpandoMetaClass(final Class theClass, final MetaMethod[] add) {
        super(GroovySystem.getMetaClassRegistry(), theClass, add);
        this.inheritedMetaMethods = new HashSet<MetaMethod>();
        this.beanPropertyCache = new ConcurrentHashMap<String, MetaProperty>();
        this.staticBeanPropertyCache = new ConcurrentHashMap<String, MetaProperty>();
        this.expandoMethods = new ConcurrentHashMap<MethodKey, MetaMethod>();
        this.expandoSubclassMethods = new ConcurrentHashMap();
        this.expandoProperties = new ConcurrentHashMap<String, MetaProperty>();
        this.mixinClasses = new LinkedHashSet<MixinInMetaClass>();
        this.myMetaClass = InvokerHelper.getMetaClass(this.getClass());
    }
    
    public ExpandoMetaClass(final Class theClass, final boolean register) {
        this(theClass);
        this.inRegistry = register;
    }
    
    public ExpandoMetaClass(final Class theClass, final boolean register, final MetaMethod[] add) {
        this(theClass, add);
        this.inRegistry = register;
    }
    
    public ExpandoMetaClass(final Class theClass, final boolean register, final boolean allowChangesAfterInit) {
        this(theClass);
        this.inRegistry = register;
        this.allowChangesAfterInit = allowChangesAfterInit;
    }
    
    public MetaMethod findMixinMethod(final String methodName, final Class[] arguments) {
        for (final MixinInMetaClass mixin : this.mixinClasses) {
            final CachedClass mixinClass = mixin.getMixinClass();
            MetaClass metaClass = mixinClass.classInfo.getMetaClassForClass();
            if (metaClass == null) {
                metaClass = GroovySystem.getMetaClassRegistry().getMetaClass(mixinClass.getTheClass());
            }
            MetaMethod metaMethod = metaClass.pickMethod(methodName, arguments);
            if (metaMethod == null && metaClass instanceof MetaClassImpl) {
                final MetaClassImpl mc = (MetaClassImpl)metaClass;
                for (CachedClass cl = mc.getTheCachedClass().getCachedSuperClass(); cl != null; cl = cl.getCachedSuperClass()) {
                    metaMethod = mc.getMethodWithoutCaching(cl.getTheClass(), methodName, arguments, false);
                    if (metaMethod != null) {
                        break;
                    }
                }
            }
            if (metaMethod != null) {
                final MetaMethod method = new MixinInstanceMetaMethod(metaMethod, mixin);
                if (method.getParameterTypes().length == 1 && !method.getParameterTypes()[0].isPrimitive) {
                    final MetaMethod noParam = this.pickMethod(methodName, new Class[0]);
                    if (noParam == null && arguments.length != 0) {
                        this.findMixinMethod(methodName, new Class[0]);
                    }
                }
                this.registerInstanceMethod(method);
                return method;
            }
        }
        return null;
    }
    
    @Override
    protected void onInvokeMethodFoundInHierarchy(final MetaMethod method) {
        this.invokeMethodMethod = method;
    }
    
    @Override
    protected void onSuperMethodFoundInHierarchy(final MetaMethod method) {
        this.addSuperMethodIfNotOverridden(method);
    }
    
    @Override
    protected void onSuperPropertyFoundInHierarchy(final MetaBeanProperty property) {
        this.addMetaBeanProperty(property);
    }
    
    @Override
    protected void onSetPropertyFoundInHierarchy(final MetaMethod method) {
        this.setPropertyMethod = method;
    }
    
    @Override
    protected void onGetPropertyFoundInHierarchy(final MetaMethod method) {
        this.getPropertyMethod = method;
    }
    
    @Override
    public synchronized boolean isModified() {
        return this.modified;
    }
    
    public void registerSubclassInstanceMethod(final String name, final Class klazz, final Closure closure) {
        final List<MetaMethod> list = ClosureMetaMethod.createMethodList(name, klazz, closure);
        for (final MetaMethod metaMethod : list) {
            this.registerSubclassInstanceMethod(metaMethod);
        }
    }
    
    public void registerSubclassInstanceMethod(final MetaMethod metaMethod) {
        this.modified = true;
        final String name = metaMethod.getName();
        final Object methodOrList = this.expandoSubclassMethods.get(name);
        if (methodOrList == null) {
            this.expandoSubclassMethods.put(name, metaMethod);
        }
        else if (methodOrList instanceof MetaMethod) {
            final FastArray arr = new FastArray(2);
            arr.add(methodOrList);
            arr.add(metaMethod);
            this.expandoSubclassMethods.put(name, arr);
        }
        else {
            ((FastArray)methodOrList).add(metaMethod);
        }
    }
    
    public void addMixinClass(final MixinInMetaClass mixin) {
        this.mixinClasses.add(mixin);
    }
    
    public Object castToMixedType(final Object obj, final Class type) {
        for (final MixinInMetaClass mixin : this.mixinClasses) {
            if (type.isAssignableFrom(mixin.getMixinClass().getTheClass())) {
                return mixin.getMixinInstance(obj);
            }
        }
        return null;
    }
    
    public static void enableGlobally() {
        ExpandoMetaClassCreationHandle.enable();
    }
    
    public static void disableGlobally() {
        ExpandoMetaClassCreationHandle.disable();
    }
    
    @Override
    public synchronized void initialize() {
        if (!this.isInitialized()) {
            super.initialize();
            this.setInitialized(true);
            this.initCalled = true;
        }
    }
    
    @Override
    protected synchronized boolean isInitialized() {
        return this.initialized;
    }
    
    protected synchronized void setInitialized(final boolean b) {
        this.initialized = b;
    }
    
    private void addSuperMethodIfNotOverridden(final MetaMethod metaMethodFromSuper) {
        this.performOperationOnMetaClass(new Callable() {
            public void call() {
                final MetaMethodIndex.Header header = ExpandoMetaClass.this.metaMethodIndex.getHeader(ExpandoMetaClass.this.theClass);
                final MetaMethodIndex.Entry methods = ExpandoMetaClass.this.metaMethodIndex.getOrPutMethods(metaMethodFromSuper.getName(), header);
                MetaMethod existing = null;
                try {
                    existing = ExpandoMetaClass.this.pickMethod(metaMethodFromSuper.getName(), metaMethodFromSuper.getNativeParameterTypes());
                }
                catch (GroovyRuntimeException ex) {}
                if (existing == null) {
                    this.addMethodWithKey(metaMethodFromSuper);
                }
                else {
                    final boolean isGroovyMethod = ExpandoMetaClass.this.getMetaMethods().contains(existing);
                    if (isGroovyMethod) {
                        this.addMethodWithKey(metaMethodFromSuper);
                    }
                    else if (ExpandoMetaClass.this.inheritedMetaMethods.contains(existing)) {
                        ExpandoMetaClass.this.inheritedMetaMethods.remove(existing);
                        this.addMethodWithKey(metaMethodFromSuper);
                    }
                }
            }
            
            private void addMethodWithKey(final MetaMethod metaMethodFromSuper) {
                ExpandoMetaClass.this.inheritedMetaMethods.add(metaMethodFromSuper);
                if (metaMethodFromSuper instanceof ClosureMetaMethod) {
                    final ClosureMetaMethod closureMethod = (ClosureMetaMethod)metaMethodFromSuper;
                    final Closure cloned = (Closure)closureMethod.getClosure().clone();
                    final String name = metaMethodFromSuper.getName();
                    final Class declaringClass = metaMethodFromSuper.getDeclaringClass().getTheClass();
                    final ClosureMetaMethod localMethod = ClosureMetaMethod.copy(closureMethod);
                    ExpandoMetaClass.this.addMetaMethod(localMethod);
                    final MethodKey key = new DefaultCachedMethodKey(declaringClass, name, localMethod.getParameterTypes(), false);
                    ExpandoMetaClass.this.checkIfGroovyObjectMethod(localMethod);
                    ExpandoMetaClass.this.expandoMethods.put(key, localMethod);
                }
            }
        });
    }
    
    @Override
    public Object invokeConstructor(final Object[] arguments) {
        final Class[] argClasses = MetaClassHelper.convertToTypeArray(arguments);
        final MetaMethod method = this.pickMethod("<init>", argClasses);
        if (method != null && method.getParameterTypes().length == arguments.length) {
            return method.invoke(this.theClass, arguments);
        }
        return super.invokeConstructor(arguments);
    }
    
    public MetaClass getMetaClass() {
        return this.myMetaClass;
    }
    
    public Object getProperty(final String property) {
        if (!isValidExpandoProperty(property)) {
            return this.myMetaClass.getProperty(this, property);
        }
        if (property.equals("static")) {
            return new ExpandoMetaProperty(property, true);
        }
        if (property.equals("constructor")) {
            return new ExpandoMetaConstructor();
        }
        if (this.myMetaClass.hasProperty(this, property) == null) {
            return new ExpandoMetaProperty(property);
        }
        return this.myMetaClass.getProperty(this, property);
    }
    
    public static boolean isValidExpandoProperty(final String property) {
        return !property.equals("metaClass") && !property.equals("class") && !property.equals("metaMethods") && !property.equals("methods") && !property.equals("properties");
    }
    
    public Object invokeMethod(final String name, final Object args) {
        final Object[] argsArr = (Object[])((args instanceof Object[]) ? args : new Object[] { args });
        final MetaMethod metaMethod = this.myMetaClass.getMetaMethod(name, argsArr);
        if (metaMethod != null) {
            return metaMethod.doMethodInvoke(this, argsArr);
        }
        if (argsArr.length == 2 && argsArr[0] instanceof Class && argsArr[1] instanceof Closure) {
            if (argsArr[0] == this.theClass) {
                this.registerInstanceMethod(name, (Closure)argsArr[1]);
            }
            else {
                this.registerSubclassInstanceMethod(name, (Class)argsArr[0], (Closure)argsArr[1]);
            }
            return null;
        }
        if (argsArr.length == 1 && argsArr[0] instanceof Closure) {
            this.registerInstanceMethod(name, (Closure)argsArr[0]);
            return null;
        }
        throw new MissingMethodException(name, this.getClass(), argsArr);
    }
    
    public void setMetaClass(final MetaClass metaClass) {
        this.myMetaClass = metaClass;
    }
    
    public void setProperty(String property, final Object newValue) {
        if (newValue instanceof Closure) {
            if (property.equals("constructor")) {
                property = "<init>";
            }
            final Closure callable = (Closure)newValue;
            final List<MetaMethod> list = ClosureMetaMethod.createMethodList(property, this.theClass, callable);
            for (final MetaMethod method : list) {
                this.registerInstanceMethod(method);
            }
        }
        else {
            this.registerBeanProperty(property, newValue);
        }
    }
    
    public ExpandoMetaClass define(final Closure closure) {
        final DefiningClosure definer = new DefiningClosure();
        final Object delegate = closure.getDelegate();
        closure.setDelegate(definer);
        closure.setResolveStrategy(1);
        closure.call(null);
        closure.setDelegate(delegate);
        definer.definition = false;
        return this;
    }
    
    protected synchronized void performOperationOnMetaClass(final Callable c) {
        try {
            if (this.allowChangesAfterInit) {
                this.setInitialized(false);
            }
            c.call();
        }
        finally {
            if (this.initCalled) {
                this.setInitialized(true);
            }
        }
    }
    
    public void registerBeanProperty(final String property, final Object newValue) {
        this.performOperationOnMetaClass(new Callable() {
            public void call() {
                final Class type = (newValue == null) ? Object.class : newValue.getClass();
                final MetaBeanProperty mbp = (newValue instanceof MetaBeanProperty) ? ((MetaBeanProperty)newValue) : new ThreadManagedMetaBeanProperty(ExpandoMetaClass.this.theClass, property, type, newValue);
                final MetaMethod getter = mbp.getGetter();
                final MethodKey getterKey = new DefaultCachedMethodKey(ExpandoMetaClass.this.theClass, getter.getName(), CachedClass.EMPTY_ARRAY, false);
                final MetaMethod setter = mbp.getSetter();
                final MethodKey setterKey = new DefaultCachedMethodKey(ExpandoMetaClass.this.theClass, setter.getName(), setter.getParameterTypes(), false);
                ExpandoMetaClass.this.addMetaMethod(getter);
                ExpandoMetaClass.this.addMetaMethod(setter);
                ExpandoMetaClass.this.expandoMethods.put(setterKey, setter);
                ExpandoMetaClass.this.expandoMethods.put(getterKey, getter);
                ExpandoMetaClass.this.expandoProperties.put(mbp.getName(), mbp);
                ExpandoMetaClass.this.addMetaBeanProperty(mbp);
                ExpandoMetaClass.this.performRegistryCallbacks();
            }
        });
    }
    
    public void registerInstanceMethod(final MetaMethod metaMethod) {
        final boolean inited = this.initCalled;
        this.performOperationOnMetaClass(new Callable() {
            public void call() {
                final String methodName = metaMethod.getName();
                ExpandoMetaClass.this.checkIfGroovyObjectMethod(metaMethod);
                final MethodKey key = new DefaultCachedMethodKey(ExpandoMetaClass.this.theClass, methodName, metaMethod.getParameterTypes(), false);
                if (ExpandoMetaClass.this.isInitialized()) {
                    throw new RuntimeException("Already initialized, cannot add new method: " + metaMethod);
                }
                ExpandoMetaClass.this.addMetaMethodToIndex(metaMethod, ExpandoMetaClass.this.metaMethodIndex.getHeader(ExpandoMetaClass.this.theClass));
                ExpandoMetaClass.this.dropMethodCache(methodName);
                ExpandoMetaClass.this.expandoMethods.put(key, metaMethod);
                if (inited && ExpandoMetaClass.this.isGetter(methodName, metaMethod.getParameterTypes())) {
                    final String propertyName = ExpandoMetaClass.this.getPropertyForGetter(methodName);
                    ExpandoMetaClass.this.registerBeanPropertyForMethod(metaMethod, propertyName, true, false);
                }
                else if (inited && ExpandoMetaClass.this.isSetter(methodName, metaMethod.getParameterTypes())) {
                    final String propertyName = ExpandoMetaClass.this.getPropertyForSetter(methodName);
                    ExpandoMetaClass.this.registerBeanPropertyForMethod(metaMethod, propertyName, false, false);
                }
                ExpandoMetaClass.this.performRegistryCallbacks();
            }
        });
    }
    
    public void registerInstanceMethod(final String name, final Closure closure) {
        final List<MetaMethod> list = ClosureMetaMethod.createMethodList(name, this.theClass, closure);
        for (final MetaMethod method : list) {
            this.registerInstanceMethod(method);
        }
    }
    
    @Override
    public List<MetaMethod> getMethods() {
        final List<MetaMethod> methodList = new ArrayList<MetaMethod>();
        methodList.addAll(this.expandoMethods.values());
        methodList.addAll(super.getMethods());
        return methodList;
    }
    
    @Override
    public List<MetaProperty> getProperties() {
        final List<MetaProperty> propertyList = new ArrayList<MetaProperty>();
        propertyList.addAll(super.getProperties());
        return propertyList;
    }
    
    private void performRegistryCallbacks() {
        final MetaClassRegistry registry = GroovySystem.getMetaClassRegistry();
        this.incVersion();
        if (!this.modified) {
            this.modified = true;
            if (this.inRegistry) {
                final MetaClass currMetaClass = registry.getMetaClass(this.theClass);
                if (!(currMetaClass instanceof ExpandoMetaClass) && currMetaClass instanceof AdaptingMetaClass) {
                    ((AdaptingMetaClass)currMetaClass).setAdaptee(this);
                }
                else {
                    registry.setMetaClass(this.theClass, this);
                }
            }
        }
    }
    
    private void registerBeanPropertyForMethod(final MetaMethod metaMethod, final String propertyName, final boolean getter, final boolean isStatic) {
        final Map<String, MetaProperty> propertyCache = isStatic ? this.staticBeanPropertyCache : this.beanPropertyCache;
        MetaBeanProperty beanProperty = propertyCache.get(propertyName);
        if (beanProperty == null) {
            if (getter) {
                beanProperty = new MetaBeanProperty(propertyName, Object.class, metaMethod, null);
            }
            else {
                beanProperty = new MetaBeanProperty(propertyName, Object.class, null, metaMethod);
            }
            propertyCache.put(propertyName, beanProperty);
        }
        else if (getter) {
            final MetaMethod setterMethod = beanProperty.getSetter();
            final Class type = (setterMethod != null) ? setterMethod.getParameterTypes()[0].getTheClass() : Object.class;
            beanProperty = new MetaBeanProperty(propertyName, type, metaMethod, setterMethod);
            propertyCache.put(propertyName, beanProperty);
        }
        else {
            final MetaMethod getterMethod = beanProperty.getGetter();
            beanProperty = new MetaBeanProperty(propertyName, metaMethod.getParameterTypes()[0].getTheClass(), getterMethod, metaMethod);
            propertyCache.put(propertyName, beanProperty);
        }
        this.expandoProperties.put(beanProperty.getName(), beanProperty);
        this.addMetaBeanProperty(beanProperty);
    }
    
    protected void registerStaticMethod(final String name, final Closure callable) {
        this.registerStaticMethod(name, callable, null);
    }
    
    protected void registerStaticMethod(final String name, final Closure callable, final Class[] paramTypes) {
        this.performOperationOnMetaClass(new Callable() {
            public void call() {
                String methodName;
                if (name.equals("methodMissing")) {
                    methodName = "$static_methodMissing";
                }
                else if (name.equals("propertyMissing")) {
                    methodName = "$static_propertyMissing";
                }
                else {
                    methodName = name;
                }
                ClosureStaticMetaMethod metaMethod = null;
                if (paramTypes != null) {
                    metaMethod = new ClosureStaticMetaMethod(methodName, ExpandoMetaClass.this.theClass, callable, paramTypes);
                }
                else {
                    metaMethod = new ClosureStaticMetaMethod(methodName, ExpandoMetaClass.this.theClass, callable);
                }
                if (methodName.equals("invokeMethod") && callable.getParameterTypes().length == 2) {
                    ExpandoMetaClass.this.invokeStaticMethodMethod = metaMethod;
                }
                else {
                    if (methodName.equals("methodMissing")) {
                        methodName = "$static_methodMissing";
                    }
                    final MethodKey key = new DefaultCachedMethodKey(ExpandoMetaClass.this.theClass, methodName, metaMethod.getParameterTypes(), false);
                    ExpandoMetaClass.this.addMetaMethod(metaMethod);
                    ExpandoMetaClass.this.dropStaticMethodCache(methodName);
                    if (ExpandoMetaClass.this.isGetter(methodName, metaMethod.getParameterTypes())) {
                        final String propertyName = ExpandoMetaClass.this.getPropertyForGetter(methodName);
                        ExpandoMetaClass.this.registerBeanPropertyForMethod(metaMethod, propertyName, true, true);
                    }
                    else if (ExpandoMetaClass.this.isSetter(methodName, metaMethod.getParameterTypes())) {
                        final String propertyName = ExpandoMetaClass.this.getPropertyForSetter(methodName);
                        ExpandoMetaClass.this.registerBeanPropertyForMethod(metaMethod, propertyName, false, true);
                    }
                    ExpandoMetaClass.this.performRegistryCallbacks();
                    ExpandoMetaClass.this.expandoMethods.put(key, metaMethod);
                }
            }
        });
    }
    
    @Override
    protected Object getSubclassMetaMethods(final String methodName) {
        if (!this.isModified()) {
            return null;
        }
        return this.expandoSubclassMethods.get(methodName);
    }
    
    public Class getJavaClass() {
        return this.theClass;
    }
    
    public void refreshInheritedMethods(final Set modifiedSuperExpandos) {
        for (final ExpandoMetaClass superExpando : modifiedSuperExpandos) {
            if (superExpando != this) {
                this.refreshInheritedMethods(superExpando);
            }
        }
    }
    
    private void refreshInheritedMethods(final ExpandoMetaClass superExpando) {
        final List<MetaMethod> metaMethods = superExpando.getExpandoMethods();
        for (final MetaMethod metaMethod : metaMethods) {
            if (metaMethod.isStatic()) {
                if (superExpando.getTheClass() != this.getTheClass()) {
                    continue;
                }
                this.registerStaticMethod(metaMethod.getName(), (Closure)((ClosureStaticMetaMethod)metaMethod).getClosure().clone());
            }
            else {
                this.addSuperMethodIfNotOverridden(metaMethod);
            }
        }
        final Collection<MetaProperty> metaProperties = superExpando.getExpandoProperties();
        for (final Object metaProperty : metaProperties) {
            final MetaBeanProperty property = (MetaBeanProperty)metaProperty;
            this.expandoProperties.put(property.getName(), property);
            this.addMetaBeanProperty(property);
        }
    }
    
    public List<MetaMethod> getExpandoMethods() {
        return Collections.unmodifiableList((List<? extends MetaMethod>)DefaultGroovyMethods.toList((Collection<? extends T>)this.expandoMethods.values()));
    }
    
    public Collection<MetaProperty> getExpandoProperties() {
        return Collections.unmodifiableCollection((Collection<? extends MetaProperty>)this.expandoProperties.values());
    }
    
    @Override
    public Object invokeMethod(final Class sender, final Object object, final String methodName, final Object[] originalArguments, final boolean isCallToSuper, final boolean fromInsideClass) {
        if (this.invokeMethodMethod != null) {
            return this.invokeMethodMethod.invoke(object, new Object[] { methodName, originalArguments });
        }
        return super.invokeMethod(sender, object, methodName, originalArguments, isCallToSuper, fromInsideClass);
    }
    
    @Override
    public Object invokeStaticMethod(final Object object, final String methodName, final Object[] arguments) {
        if (this.invokeStaticMethodMethod != null) {
            return this.invokeStaticMethodMethod.invoke(object, new Object[] { methodName, arguments });
        }
        return super.invokeStaticMethod(object, methodName, arguments);
    }
    
    @Override
    public Object getProperty(final Class sender, final Object object, final String name, final boolean useSuper, final boolean fromInsideClass) {
        if (this.hasOverrideGetProperty(name) && this.getJavaClass().isInstance(object)) {
            return this.getPropertyMethod.invoke(object, new Object[] { name });
        }
        if ("mixedIn".equals(name)) {
            return new MixedInAccessor(object, this.mixinClasses);
        }
        return super.getProperty(sender, object, name, useSuper, fromInsideClass);
    }
    
    @Override
    public Object getProperty(final Object object, final String name) {
        if (this.hasOverrideGetProperty(name) && this.getJavaClass().isInstance(object)) {
            return this.getPropertyMethod.invoke(object, new Object[] { name });
        }
        return super.getProperty(object, name);
    }
    
    private boolean hasOverrideGetProperty(final String name) {
        return this.getPropertyMethod != null && !name.equals("metaClass") && !name.equals("class");
    }
    
    @Override
    public void setProperty(final Class sender, final Object object, final String name, final Object newValue, final boolean useSuper, final boolean fromInsideClass) {
        if (this.setPropertyMethod != null && !name.equals("metaClass") && this.getJavaClass().isInstance(object)) {
            this.setPropertyMethod.invoke(object, new Object[] { name, newValue });
            return;
        }
        super.setProperty(sender, object, name, newValue, useSuper, fromInsideClass);
    }
    
    @Override
    public MetaProperty getMetaProperty(final String name) {
        final MetaProperty mp = this.expandoProperties.get(name);
        if (mp != null) {
            return mp;
        }
        return super.getMetaProperty(name);
    }
    
    public boolean hasMetaProperty(final String name) {
        return this.getMetaProperty(name) != null;
    }
    
    public boolean hasMetaMethod(final String name, final Class[] args) {
        return super.pickMethod(name, args) != null;
    }
    
    private static boolean isPropertyName(final String name) {
        return (name.length() > 0 && Character.isUpperCase(name.charAt(0))) || (name.length() > 1 && Character.isUpperCase(name.charAt(1)));
    }
    
    private boolean isGetter(String name, final CachedClass[] args) {
        if (name == null || name.length() == 0 || args == null) {
            return false;
        }
        if (args.length != 0) {
            return false;
        }
        if (name.startsWith("get")) {
            name = name.substring(3);
            return isPropertyName(name);
        }
        if (name.startsWith("is")) {
            name = name.substring(2);
            return isPropertyName(name);
        }
        return false;
    }
    
    private String getPropertyForGetter(final String getterName) {
        if (getterName == null || getterName.length() == 0) {
            return null;
        }
        if (getterName.startsWith("get")) {
            final String prop = getterName.substring(3);
            return this.convertPropertyName(prop);
        }
        if (getterName.startsWith("is")) {
            final String prop = getterName.substring(2);
            return this.convertPropertyName(prop);
        }
        return null;
    }
    
    private String convertPropertyName(final String prop) {
        if (Character.isDigit(prop.charAt(0))) {
            return prop;
        }
        return Introspector.decapitalize(prop);
    }
    
    public String getPropertyForSetter(final String setterName) {
        if (setterName == null || setterName.length() == 0) {
            return null;
        }
        if (setterName.startsWith("set")) {
            final String prop = setterName.substring(3);
            return this.convertPropertyName(prop);
        }
        return null;
    }
    
    public boolean isSetter(String name, final CachedClass[] args) {
        if (name == null || name.length() == 0 || args == null) {
            return false;
        }
        if (!name.startsWith("set")) {
            return false;
        }
        if (args.length != 1) {
            return false;
        }
        name = name.substring(3);
        return isPropertyName(name);
    }
    
    @Override
    public CallSite createPojoCallSite(final CallSite site, final Object receiver, final Object[] args) {
        if (this.invokeMethodMethod != null) {
            return new PojoMetaClassSite(site, this);
        }
        return super.createPojoCallSite(site, receiver, args);
    }
    
    @Override
    public CallSite createStaticSite(final CallSite site, final Object[] args) {
        if (this.invokeStaticMethodMethod != null) {
            return new StaticMetaClassSite(site, this);
        }
        return super.createStaticSite(site, args);
    }
    
    @Override
    public CallSite createPogoCallSite(final CallSite site, final Object[] args) {
        if (this.invokeMethodMethod != null) {
            return new PogoMetaClassSite(site, this);
        }
        return super.createPogoCallSite(site, args);
    }
    
    public CallSite createPogoCallCurrentSite(final CallSite site, final Class sender, final String name, final Object[] args) {
        if (this.invokeMethodMethod != null) {
            return new PogoMetaClassSite(site, this);
        }
        return super.createPogoCallCurrentSite(site, sender, args);
    }
    
    @Override
    public CallSite createConstructorSite(final CallSite site, final Object[] args) {
        final Class[] params = MetaClassHelper.convertToTypeArray(args);
        final MetaMethod method = this.pickMethod("<init>", params);
        if (method != null && method.getParameterTypes().length == args.length && method.getDeclaringClass().getTheClass().equals(this.getTheClass())) {
            return new ConstructorMetaMethodSite(site, this, method, params);
        }
        return super.createConstructorSite(site, args);
    }
    
    protected class ExpandoMetaProperty extends GroovyObjectSupport
    {
        protected String propertyName;
        protected boolean isStatic;
        
        protected ExpandoMetaProperty(final ExpandoMetaClass expandoMetaClass, final String name) {
            this(expandoMetaClass, name, false);
        }
        
        protected ExpandoMetaProperty(final String name, final boolean isStatic) {
            this.propertyName = name;
            this.isStatic = isStatic;
        }
        
        public String getPropertyName() {
            return this.propertyName;
        }
        
        public boolean isStatic() {
            return this.isStatic;
        }
        
        public Object leftShift(final Object arg) {
            this.registerIfClosure(arg, false);
            return this;
        }
        
        private void registerIfClosure(final Object arg, final boolean replace) {
            if (arg instanceof Closure) {
                final Closure callable = (Closure)arg;
                final List<MetaMethod> list = ClosureMetaMethod.createMethodList(this.propertyName, ExpandoMetaClass.this.theClass, callable);
                if (list.isEmpty() && this.isStatic) {
                    final Class[] paramTypes = callable.getParameterTypes();
                    this.registerStatic(callable, replace, paramTypes);
                    return;
                }
                for (final MetaMethod method : list) {
                    final Class[] paramTypes2 = method.getNativeParameterTypes();
                    if (this.isStatic) {
                        this.registerStatic(callable, replace, paramTypes2);
                    }
                    else {
                        this.registerInstance(method, replace, paramTypes2);
                    }
                }
            }
        }
        
        private void registerStatic(final Closure callable, final boolean replace, final Class[] paramTypes) {
            final Method foundMethod = this.checkIfMethodExists(ExpandoMetaClass.this.theClass, this.propertyName, paramTypes, true);
            if (foundMethod != null && !replace) {
                throw new GroovyRuntimeException("Cannot add new static method [" + this.propertyName + "] for arguments [" + DefaultGroovyMethods.inspect(paramTypes) + "]. It already exists!");
            }
            ExpandoMetaClass.this.registerStaticMethod(this.propertyName, callable, paramTypes);
        }
        
        private void registerInstance(final MetaMethod method, final boolean replace, final Class[] paramTypes) {
            final Method foundMethod = this.checkIfMethodExists(ExpandoMetaClass.this.theClass, this.propertyName, paramTypes, false);
            if (foundMethod != null && !replace) {
                throw new GroovyRuntimeException("Cannot add new method [" + this.propertyName + "] for arguments [" + DefaultGroovyMethods.inspect(paramTypes) + "]. It already exists!");
            }
            ExpandoMetaClass.this.registerInstanceMethod(method);
        }
        
        private Method checkIfMethodExists(final Class methodClass, final String methodName, final Class[] paramTypes, final boolean staticMethod) {
            Method foundMethod = null;
            final Method[] arr$;
            final Method[] methods = arr$ = methodClass.getMethods();
            for (final Method method : arr$) {
                if (method.getName().equals(methodName) && Modifier.isStatic(method.getModifiers()) == staticMethod && MetaClassHelper.parametersAreCompatible(paramTypes, method.getParameterTypes())) {
                    foundMethod = method;
                    break;
                }
            }
            return foundMethod;
        }
        
        @Override
        public Object getProperty(final String property) {
            this.propertyName = property;
            return this;
        }
        
        @Override
        public void setProperty(final String property, final Object newValue) {
            this.propertyName = property;
            this.registerIfClosure(newValue, true);
        }
    }
    
    protected class ExpandoMetaConstructor extends GroovyObjectSupport
    {
        public Object leftShift(final Closure c) {
            if (c != null) {
                final List<MetaMethod> list = ClosureMetaMethod.createMethodList("<init>", ExpandoMetaClass.this.theClass, c);
                for (final MetaMethod method : list) {
                    final Class[] paramTypes = method.getNativeParameterTypes();
                    final Constructor ctor = ExpandoMetaClass.this.retrieveConstructor(paramTypes);
                    if (ctor != null) {
                        throw new GroovyRuntimeException("Cannot add new constructor for arguments [" + DefaultGroovyMethods.inspect(paramTypes) + "]. It already exists!");
                    }
                    ExpandoMetaClass.this.registerInstanceMethod(method);
                }
            }
            return this;
        }
    }
    
    private class SubClassDefiningClosure extends GroovyObjectSupport
    {
        private final Class klazz;
        
        public SubClassDefiningClosure(final Class klazz) {
            this.klazz = klazz;
        }
        
        @Override
        public Object invokeMethod(final String name, final Object obj) {
            if (obj instanceof Object[]) {
                final Object[] args = (Object[])obj;
                if (args.length == 1 && args[0] instanceof Closure) {
                    ExpandoMetaClass.this.registerSubclassInstanceMethod(name, this.klazz, (Closure)args[0]);
                    return null;
                }
            }
            throw new MissingMethodException(name, this.getClass(), new Object[] { obj });
        }
    }
    
    private class DefiningClosure extends GroovyObjectSupport
    {
        boolean definition;
        
        private DefiningClosure() {
            this.definition = true;
        }
        
        public void mixin(final Class category) {
            this.mixin(Collections.singletonList(category));
        }
        
        public void mixin(final List categories) {
            DefaultGroovyMethods.mixin(ExpandoMetaClass.this, categories);
        }
        
        public void mixin(final Class[] categories) {
            DefaultGroovyMethods.mixin((MetaClass)ExpandoMetaClass.this, categories);
        }
        
        public void define(final Class subClass, final Closure closure) {
            final SubClassDefiningClosure definer = new SubClassDefiningClosure(subClass);
            closure.setDelegate(definer);
            closure.setResolveStrategy(1);
            closure.call(null);
        }
        
        @Override
        public Object invokeMethod(final String name, final Object obj) {
            try {
                return this.getMetaClass().invokeMethod(this, name, obj);
            }
            catch (MissingMethodException mme) {
                if (!(obj instanceof Object[])) {
                    throw mme;
                }
                if ("static".equals(name)) {
                    final StaticDefiningClosure staticDef = new StaticDefiningClosure();
                    final Closure c = (Closure)((Object[])obj)[0];
                    c.setDelegate(staticDef);
                    c.setResolveStrategy(3);
                    c.call(null);
                    return null;
                }
                final Object[] args = (Object[])obj;
                if (args.length == 1 && args[0] instanceof Closure) {
                    ExpandoMetaClass.this.registerInstanceMethod(name, (Closure)args[0]);
                }
                else if (args.length == 2 && args[0] instanceof Class && args[1] instanceof Closure) {
                    ExpandoMetaClass.this.registerSubclassInstanceMethod(name, (Class)args[0], (Closure)args[1]);
                }
                else {
                    ExpandoMetaClass.this.setProperty(name, ((Object[])obj)[0]);
                }
                return null;
            }
        }
        
        @Override
        public void setProperty(final String property, final Object newValue) {
            ExpandoMetaClass.this.setProperty(property, newValue);
        }
        
        @Override
        public Object getProperty(final String property) {
            if ("static".equals(property)) {
                return new StaticDefiningClosure();
            }
            if (this.definition) {
                return new ExpandoMetaProperty(property);
            }
            throw new MissingPropertyException(property, this.getClass());
        }
    }
    
    private class StaticDefiningClosure extends ExpandoMetaProperty
    {
        protected StaticDefiningClosure() {
            super("static", true);
        }
        
        @Override
        public Object invokeMethod(final String name, final Object obj) {
            if (obj instanceof Object[]) {
                final Object[] args = (Object[])obj;
                if (args.length == 1 && args[0] instanceof Closure) {
                    ExpandoMetaClass.this.registerStaticMethod(name, (Closure)args[0]);
                    return null;
                }
            }
            throw new MissingMethodException(name, this.getClass(), (obj instanceof Object[]) ? ((Object[])obj) : new Object[] { obj });
        }
    }
    
    private static class MixedInAccessor
    {
        private final Object object;
        private final Set<MixinInMetaClass> mixinClasses;
        
        public MixedInAccessor(final Object object, final Set<MixinInMetaClass> mixinClasses) {
            this.object = object;
            this.mixinClasses = mixinClasses;
        }
        
        public Object getAt(final Class key) {
            if (key.isAssignableFrom(this.object.getClass())) {
                return new GroovyObjectSupport() {
                    {
                        final MetaClass ownMetaClass = InvokerHelper.getMetaClass(MixedInAccessor.this.object.getClass());
                        this.setMetaClass(new OwnedMetaClass(ownMetaClass) {
                            @Override
                            protected Object getOwner() {
                                return MixedInAccessor.this.object;
                            }
                            
                            @Override
                            protected MetaClass getOwnerMetaClass(final Object owner) {
                                return this.getAdaptee();
                            }
                        });
                    }
                };
            }
            for (final MixinInMetaClass mixin : this.mixinClasses) {
                if (key.isAssignableFrom(mixin.getMixinClass().getTheClass())) {
                    return new GroovyObjectSupport() {
                        {
                            final Object mixedInInstance = mixin.getMixinInstance(MixedInAccessor.this.object);
                            this.setMetaClass(new OwnedMetaClass(InvokerHelper.getMetaClass(mixedInInstance), mixedInInstance) {
                                @Override
                                protected Object getOwner() {
                                    return mixin;
                                }
                                
                                @Override
                                protected MetaClass getOwnerMetaClass(final Object owner) {
                                    return ((MixedInMetaClass)this.getAdaptee()).getAdaptee();
                                }
                            });
                        }
                    };
                }
            }
            throw new RuntimeException("Class " + key + " isn't mixed in " + this.object.getClass());
        }
        
        public void putAt(final Class key, final Object value) {
            for (final MixinInMetaClass mixin : this.mixinClasses) {
                if (mixin.getMixinClass().getTheClass() == key) {
                    mixin.setMixinInstance(this.object, value);
                    return;
                }
            }
            throw new RuntimeException("Class " + key + " isn't mixed in " + this.object.getClass());
        }
    }
    
    private interface Callable
    {
        void call();
    }
}
