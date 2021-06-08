// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.callsite.MetaClassConstructorSite;
import org.codehaus.groovy.runtime.callsite.ConstructorSite;
import org.codehaus.groovy.runtime.callsite.PogoMetaClassSite;
import org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite;
import org.codehaus.groovy.runtime.callsite.StaticMetaClassSite;
import org.codehaus.groovy.runtime.callsite.StaticMetaMethodSite;
import org.codehaus.groovy.runtime.callsite.PojoMetaClassSite;
import org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite;
import java.beans.EventSetDescriptor;
import java.security.PrivilegedActionException;
import java.beans.IntrospectionException;
import java.security.PrivilegedExceptionAction;
import java.beans.BeanInfo;
import org.codehaus.groovy.reflection.ParameterTypes;
import org.codehaus.groovy.runtime.typehandling.NumberMathModificationInfo;
import org.codehaus.groovy.runtime.metaclass.MixinInstanceMetaMethod;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import groovyjarjarasm.asm.ClassVisitor;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.codehaus.groovy.runtime.ConvertedClosure;
import org.codehaus.groovy.runtime.wrappers.Wrapper;
import org.codehaus.groovy.GroovyBugError;
import java.beans.Introspector;
import java.lang.reflect.Modifier;
import java.beans.PropertyDescriptor;
import org.codehaus.groovy.util.ComplexKeyHashMap;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;
import org.codehaus.groovy.reflection.CachedField;
import org.codehaus.groovy.classgen.BytecodeHelper;
import org.codehaus.groovy.runtime.metaclass.MethodSelectionException;
import org.codehaus.groovy.reflection.CachedConstructor;
import java.lang.reflect.Constructor;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.metaclass.MetaClassRegistryImpl;
import org.codehaus.groovy.runtime.metaclass.TransformMetaMethod;
import org.codehaus.groovy.runtime.CurriedClosure;
import org.codehaus.groovy.runtime.MethodClosure;
import org.codehaus.groovy.runtime.metaclass.MissingMethodExceptionNoStack;
import org.codehaus.groovy.runtime.metaclass.MissingMethodExecutionFailed;
import org.codehaus.groovy.runtime.metaclass.MissingPropertyExceptionNoStack;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.codehaus.groovy.runtime.metaclass.ClosureMetaMethod;
import org.codehaus.groovy.runtime.metaclass.NewStaticMetaMethod;
import org.codehaus.groovy.runtime.metaclass.NewInstanceMetaMethod;
import java.lang.reflect.Method;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.Comparator;
import org.codehaus.groovy.runtime.metaclass.NewMetaMethod;
import org.codehaus.groovy.reflection.CachedMethod;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;
import org.codehaus.groovy.runtime.MetaClassHelper;
import java.util.Collection;
import java.util.Arrays;
import org.codehaus.groovy.reflection.ReflectionCache;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.groovy.runtime.metaclass.MetaMethodIndex;
import java.util.Set;
import java.util.List;
import org.codehaus.groovy.util.FastArray;
import java.util.Map;
import org.codehaus.groovy.util.SingleKeyHashMap;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.reflection.CachedClass;

public class MetaClassImpl implements MetaClass, MutableMetaClass
{
    private static final String CLOSURE_CALL_METHOD = "call";
    private static final String CLOSURE_DO_CALL_METHOD = "doCall";
    private static final String CLOSURE_CURRY_METHOD = "curry";
    protected static final String STATIC_METHOD_MISSING = "$static_methodMissing";
    protected static final String STATIC_PROPERTY_MISSING = "$static_propertyMissing";
    protected static final String METHOD_MISSING = "methodMissing";
    protected static final String PROPERTY_MISSING = "propertyMissing";
    private static final String GET_PROPERTY_METHOD = "getProperty";
    private static final String SET_PROPERTY_METHOD = "setProperty";
    protected static final String INVOKE_METHOD_METHOD = "invokeMethod";
    private static final Class[] METHOD_MISSING_ARGS;
    private static final Class[] GETTER_MISSING_ARGS;
    private static final Class[] SETTER_MISSING_ARGS;
    protected final Class theClass;
    protected final CachedClass theCachedClass;
    private static final MetaMethod[] EMPTY;
    protected MetaMethod getPropertyMethod;
    protected MetaMethod invokeMethodMethod;
    protected MetaMethod setPropertyMethod;
    protected MetaClassRegistry registry;
    protected final boolean isGroovyObject;
    protected final boolean isMap;
    private ClassNode classNode;
    private final Index classPropertyIndex;
    private Index classPropertyIndexForSuper;
    private final SingleKeyHashMap staticPropertyIndex;
    private final Map<String, MetaMethod> listeners;
    private FastArray constructors;
    private final List<MetaMethod> allMethods;
    private boolean initialized;
    private final MetaProperty arrayLengthProperty;
    private static final MetaMethod AMBIGUOUS_LISTENER_METHOD;
    private static final Object[] EMPTY_ARGUMENTS;
    private final Set<MetaMethod> newGroovyMethodsSet;
    private MetaMethod genericGetMethod;
    private MetaMethod genericSetMethod;
    private MetaMethod propertyMissingGet;
    private MetaMethod propertyMissingSet;
    private MetaMethod methodMissing;
    private MetaMethodIndex.Header mainClassMethodHeader;
    protected final MetaMethodIndex metaMethodIndex;
    private final MetaMethod[] myNewMetaMethods;
    private final MetaMethod[] additionalMetaMethods;
    private static final HashMap<String, String> propNames;
    private static final SingleKeyHashMap.Copier NAME_INDEX_COPIER;
    private static final SingleKeyHashMap.Copier METHOD_INDEX_COPIER;
    
    public final CachedClass getTheCachedClass() {
        return this.theCachedClass;
    }
    
    public MetaClassImpl(final Class theClass, final MetaMethod[] add) {
        this.classPropertyIndex = new MethodIndex();
        this.classPropertyIndexForSuper = new MethodIndex();
        this.staticPropertyIndex = new SingleKeyHashMap();
        this.listeners = new HashMap<String, MetaMethod>();
        this.allMethods = new ArrayList<MetaMethod>();
        this.arrayLengthProperty = new MetaArrayLengthProperty();
        this.newGroovyMethodsSet = new HashSet<MetaMethod>();
        this.theClass = theClass;
        this.theCachedClass = ReflectionCache.getCachedClass(theClass);
        this.isGroovyObject = GroovyObject.class.isAssignableFrom(theClass);
        this.isMap = Map.class.isAssignableFrom(theClass);
        this.registry = GroovySystem.getMetaClassRegistry();
        this.metaMethodIndex = new MetaMethodIndex(this.theCachedClass);
        final MetaMethod[] metaMethods = this.theCachedClass.getNewMetaMethods();
        if (add != null && add.length != 0) {
            final ArrayList<MetaMethod> arr = new ArrayList<MetaMethod>();
            arr.addAll(Arrays.asList(metaMethods));
            arr.addAll(Arrays.asList(add));
            this.myNewMetaMethods = arr.toArray(new MetaMethod[arr.size()]);
            this.additionalMetaMethods = metaMethods;
        }
        else {
            this.myNewMetaMethods = metaMethods;
            this.additionalMetaMethods = MetaClassImpl.EMPTY;
        }
    }
    
    public MetaClassImpl(final Class theClass) {
        this(theClass, null);
    }
    
    public MetaClassImpl(final MetaClassRegistry registry, final Class theClass, final MetaMethod[] add) {
        this(theClass, add);
        this.registry = registry;
        this.constructors = new FastArray(this.theCachedClass.getConstructors());
    }
    
    public MetaClassImpl(final MetaClassRegistry registry, final Class theClass) {
        this(registry, theClass, null);
    }
    
    public List respondsTo(final Object obj, final String name, final Object[] argTypes) {
        final Class[] classes = MetaClassHelper.castArgumentsToClassArray(argTypes);
        final MetaMethod m = this.getMetaMethod(name, classes);
        final List<MetaMethod> methods = new ArrayList<MetaMethod>();
        if (m != null) {
            methods.add(m);
        }
        return methods;
    }
    
    public List respondsTo(final Object obj, final String name) {
        final Object o = this.getMethods(this.getTheClass(), name, false);
        if (o instanceof FastArray) {
            return ((FastArray)o).toList();
        }
        return Collections.singletonList(o);
    }
    
    public MetaProperty hasProperty(final Object obj, final String name) {
        return this.getMetaProperty(name);
    }
    
    public MetaProperty getMetaProperty(final String name) {
        SingleKeyHashMap propertyMap = this.classPropertyIndex.getNotNull(this.theCachedClass);
        if (propertyMap.containsKey(name)) {
            return (MetaProperty)propertyMap.get(name);
        }
        if (this.staticPropertyIndex.containsKey(name)) {
            return (MetaProperty)this.staticPropertyIndex.get(name);
        }
        propertyMap = this.classPropertyIndexForSuper.getNotNull(this.theCachedClass);
        if (propertyMap.containsKey(name)) {
            return (MetaProperty)propertyMap.get(name);
        }
        for (CachedClass superClass = this.theCachedClass; superClass != null && superClass != ReflectionCache.OBJECT_CLASS; superClass = superClass.getCachedSuperClass()) {
            final MetaBeanProperty property = this.findPropertyInClassHierarchy(name, superClass);
            if (property != null) {
                this.onSuperPropertyFoundInHierarchy(property);
                return property;
            }
        }
        return null;
    }
    
    public MetaMethod getStaticMetaMethod(final String name, final Object[] argTypes) {
        final Class[] classes = MetaClassHelper.castArgumentsToClassArray(argTypes);
        return this.pickStaticMethod(name, classes);
    }
    
    public MetaMethod getMetaMethod(final String name, final Object[] argTypes) {
        final Class[] classes = MetaClassHelper.castArgumentsToClassArray(argTypes);
        return this.pickMethod(name, classes);
    }
    
    public Class getTheClass() {
        return this.theClass;
    }
    
    public boolean isGroovyObject() {
        return this.isGroovyObject;
    }
    
    private void fillMethodIndex() {
        this.mainClassMethodHeader = this.metaMethodIndex.getHeader(this.theClass);
        final LinkedList superClasses = this.getSuperClasses();
        final CachedClass firstGroovySuper = this.calcFirstGroovySuperClass(superClasses);
        final Set<CachedClass> interfaces = this.theCachedClass.getInterfaces();
        this.addInterfaceMethods(interfaces);
        this.populateMethods(superClasses, firstGroovySuper);
        this.inheritInterfaceNewMetaMethods(interfaces);
        if (this.isGroovyObject) {
            this.metaMethodIndex.copyMethodsToSuper();
            this.connectMultimethods(superClasses, firstGroovySuper);
            this.removeMultimethodsOverloadedWithPrivateMethods();
            this.replaceWithMOPCalls(this.theCachedClass.mopMethods);
        }
    }
    
    private void populateMethods(final LinkedList superClasses, final CachedClass firstGroovySuper) {
        final Iterator iter = superClasses.iterator();
        MetaMethodIndex.Header header = this.metaMethodIndex.getHeader(firstGroovySuper.getTheClass());
        while (iter.hasNext()) {
            final CachedClass c = iter.next();
            final CachedMethod[] arr$;
            final CachedMethod[] cachedMethods = arr$ = c.getMethods();
            for (final CachedMethod metaMethod : arr$) {
                this.addToAllMethodsIfPublic(metaMethod);
                if (!metaMethod.isPrivate() || c == firstGroovySuper) {
                    this.addMetaMethodToIndex(metaMethod, header);
                }
            }
            final MetaMethod[] arr$2;
            final MetaMethod[] cachedMethods2 = arr$2 = this.getNewMetaMethods(c);
            for (final MetaMethod method : arr$2) {
                if (!this.newGroovyMethodsSet.contains(method)) {
                    this.newGroovyMethodsSet.add(method);
                    this.addMetaMethodToIndex(method, header);
                }
            }
            if (c == firstGroovySuper) {
                break;
            }
        }
        MetaMethodIndex.Header last = header;
        while (iter.hasNext()) {
            final CachedClass c = iter.next();
            header = this.metaMethodIndex.getHeader(c.getTheClass());
            if (last != null) {
                this.metaMethodIndex.copyNonPrivateMethods(last, header);
            }
            last = header;
            for (final CachedMethod metaMethod : c.getMethods()) {
                this.addToAllMethodsIfPublic(metaMethod);
                this.addMetaMethodToIndex(metaMethod, header);
            }
            for (final MetaMethod method2 : this.getNewMetaMethods(c)) {
                if (!this.newGroovyMethodsSet.contains(method2)) {
                    this.newGroovyMethodsSet.add(method2);
                    this.addMetaMethodToIndex(method2, header);
                }
            }
        }
    }
    
    private MetaMethod[] getNewMetaMethods(final CachedClass c) {
        if (this.theCachedClass != c) {
            return c.getNewMetaMethods();
        }
        return this.myNewMetaMethods;
    }
    
    private void addInterfaceMethods(final Set<CachedClass> interfaces) {
        final MetaMethodIndex.Header header = this.metaMethodIndex.getHeader(this.theClass);
        for (final CachedClass c : interfaces) {
            final CachedMethod[] m = c.getMethods();
            for (int i = 0; i != m.length; ++i) {
                final MetaMethod method = m[i];
                this.addMetaMethodToIndex(method, header);
            }
        }
    }
    
    protected LinkedList<CachedClass> getSuperClasses() {
        final LinkedList<CachedClass> superClasses = new LinkedList<CachedClass>();
        if (this.theClass.isInterface()) {
            superClasses.addFirst(ReflectionCache.OBJECT_CLASS);
        }
        else {
            for (CachedClass c = this.theCachedClass; c != null; c = c.getCachedSuperClass()) {
                superClasses.addFirst(c);
            }
            if (this.theCachedClass.isArray && this.theClass != Object[].class && !this.theClass.getComponentType().isPrimitive()) {
                superClasses.addFirst(ReflectionCache.OBJECT_ARRAY_CLASS);
            }
        }
        return superClasses;
    }
    
    private void removeMultimethodsOverloadedWithPrivateMethods() {
        final MethodIndexAction mia = new MethodIndexAction() {
            @Override
            public boolean skipClass(final Class clazz) {
                return clazz == MetaClassImpl.this.theClass;
            }
            
            @Override
            public void methodNameAction(final Class clazz, final MetaMethodIndex.Entry e) {
                if (e.methods == null) {
                    return;
                }
                boolean hasPrivate = false;
                if (e.methods instanceof FastArray) {
                    final FastArray methods = (FastArray)e.methods;
                    final int len = methods.size();
                    final Object[] data = methods.getArray();
                    for (int i = 0; i != len; ++i) {
                        final MetaMethod method = (MetaMethod)data[i];
                        if (method.isPrivate() && clazz == method.getDeclaringClass().getTheClass()) {
                            hasPrivate = true;
                            break;
                        }
                    }
                }
                else {
                    final MetaMethod method2 = (MetaMethod)e.methods;
                    if (method2.isPrivate() && clazz == method2.getDeclaringClass().getTheClass()) {
                        hasPrivate = true;
                    }
                }
                if (!hasPrivate) {
                    return;
                }
                final Object o = e.methodsForSuper;
                if (o instanceof FastArray) {
                    e.methods = ((FastArray)o).copy();
                }
                else {
                    e.methods = o;
                }
            }
        };
        mia.iterate();
    }
    
    private void replaceWithMOPCalls(final CachedMethod[] mopMethods) {
        if (!this.isGroovyObject) {
            return;
        }
        class MOPIter extends MethodIndexAction
        {
            boolean useThis;
            
            public boolean skipClass(final CachedClass clazz) {
                return !this.useThis && clazz == MetaClassImpl.this.theCachedClass;
            }
            
            @Override
            public void methodNameAction(final Class clazz, final MetaMethodIndex.Entry e) {
                if (this.useThis) {
                    if (e.methods == null) {
                        return;
                    }
                    if (e.methods instanceof FastArray) {
                        final FastArray methods = (FastArray)e.methods;
                        this.processFastArray(methods);
                    }
                    else {
                        final MetaMethod method = (MetaMethod)e.methods;
                        if (method instanceof NewMetaMethod) {
                            return;
                        }
                        if (this.useThis ^ (method.getModifiers() & 0x5) == 0x0) {
                            return;
                        }
                        final String mopName = method.getMopName();
                        final int index = Arrays.binarySearch(mopMethods, mopName, CachedClass.CachedMethodComparatorWithString.INSTANCE);
                        if (index >= 0) {
                            int from;
                            for (from = index; from > 0 && mopMethods[from - 1].getName().equals(mopName); --from) {}
                            int to;
                            for (to = index; to < mopMethods.length - 1 && mopMethods[to + 1].getName().equals(mopName); ++to) {}
                            final int matchingMethod = MetaClassImpl.this.findMatchingMethod(mopMethods, from, to, method);
                            if (matchingMethod != -1) {
                                e.methods = mopMethods[matchingMethod];
                            }
                        }
                    }
                }
                else {
                    if (e.methodsForSuper == null) {
                        return;
                    }
                    if (e.methodsForSuper instanceof FastArray) {
                        final FastArray methods = (FastArray)e.methodsForSuper;
                        this.processFastArray(methods);
                    }
                    else {
                        final MetaMethod method = (MetaMethod)e.methodsForSuper;
                        if (method instanceof NewMetaMethod) {
                            return;
                        }
                        if (this.useThis ^ (method.getModifiers() & 0x5) == 0x0) {
                            return;
                        }
                        final String mopName = method.getMopName();
                        final int index = Arrays.binarySearch(mopMethods, mopName, CachedClass.CachedMethodComparatorWithString.INSTANCE);
                        if (index >= 0) {
                            int from;
                            for (from = index; from > 0 && mopMethods[from - 1].getName().equals(mopName); --from) {}
                            int to;
                            for (to = index; to < mopMethods.length - 1 && mopMethods[to + 1].getName().equals(mopName); ++to) {}
                            final int matchingMethod = MetaClassImpl.this.findMatchingMethod(mopMethods, from, to, method);
                            if (matchingMethod != -1) {
                                e.methodsForSuper = mopMethods[matchingMethod];
                            }
                        }
                    }
                }
            }
            
            private void processFastArray(final FastArray methods) {
                final int len = methods.size();
                final Object[] data = methods.getArray();
                for (int i = 0; i != len; ++i) {
                    final MetaMethod method = (MetaMethod)data[i];
                    if (!(method instanceof NewMetaMethod)) {
                        if (!(this.useThis ^ (method.getModifiers() & 0x5) == 0x0)) {
                            final String mopName = method.getMopName();
                            final int index = Arrays.binarySearch(mopMethods, mopName, CachedClass.CachedMethodComparatorWithString.INSTANCE);
                            if (index >= 0) {
                                int from;
                                for (from = index; from > 0 && mopMethods[from - 1].getName().equals(mopName); --from) {}
                                int to;
                                for (to = index; to < mopMethods.length - 1 && mopMethods[to + 1].getName().equals(mopName); ++to) {}
                                final int matchingMethod = MetaClassImpl.this.findMatchingMethod(mopMethods, from, to, method);
                                if (matchingMethod != -1) {
                                    methods.set(i, mopMethods[matchingMethod]);
                                }
                            }
                        }
                    }
                }
            }
        }
        final MOPIter iter = new MOPIter();
        iter.useThis = false;
        iter.iterate();
        iter.useThis = true;
        iter.iterate();
    }
    
    private void inheritInterfaceNewMetaMethods(final Set<CachedClass> interfaces) {
        for (final CachedClass cls : interfaces) {
            final MetaMethod[] arr$;
            final MetaMethod[] methods = arr$ = this.getNewMetaMethods(cls);
            for (final MetaMethod method : arr$) {
                if (!this.newGroovyMethodsSet.contains(method)) {
                    this.newGroovyMethodsSet.add(method);
                }
                this.addMetaMethodToIndex(method, this.mainClassMethodHeader);
            }
        }
    }
    
    private void connectMultimethods(List superClasses, final CachedClass firstGroovyClass) {
        superClasses = DefaultGroovyMethods.reverse(superClasses);
        MetaMethodIndex.Header last = null;
        for (final CachedClass c : superClasses) {
            final MetaMethodIndex.Header methodIndex = this.metaMethodIndex.getHeader(c.getTheClass());
            if (last != null) {
                this.metaMethodIndex.copyNonPrivateNonNewMetaMethods(last, methodIndex);
            }
            last = methodIndex;
            if (c == firstGroovyClass) {
                break;
            }
        }
    }
    
    private CachedClass calcFirstGroovySuperClass(final Collection superClasses) {
        if (this.theCachedClass.isInterface) {
            return ReflectionCache.OBJECT_CLASS;
        }
        CachedClass firstGroovy = null;
        final Iterator iter = superClasses.iterator();
        while (iter.hasNext()) {
            final CachedClass c = iter.next();
            if (GroovyObject.class.isAssignableFrom(c.getTheClass())) {
                firstGroovy = c;
                break;
            }
        }
        if (firstGroovy == null) {
            firstGroovy = this.theCachedClass;
        }
        else if (firstGroovy.getTheClass() == GroovyObjectSupport.class && iter.hasNext()) {
            firstGroovy = iter.next();
            if (firstGroovy.getTheClass() == Closure.class && iter.hasNext()) {
                firstGroovy = iter.next();
            }
        }
        return GroovyObject.class.isAssignableFrom(firstGroovy.getTheClass()) ? firstGroovy.getCachedSuperClass() : firstGroovy;
    }
    
    private Object getMethods(final Class sender, final String name, final boolean isCallToSuper) {
        final MetaMethodIndex.Entry entry = this.metaMethodIndex.getMethods(sender, name);
        Object answer;
        if (entry == null) {
            answer = FastArray.EMPTY_LIST;
        }
        else if (isCallToSuper) {
            answer = entry.methodsForSuper;
        }
        else {
            answer = entry.methods;
        }
        if (answer == null) {
            answer = FastArray.EMPTY_LIST;
        }
        if (!isCallToSuper) {
            final List used = GroovyCategorySupport.getCategoryMethods(name);
            if (used != null) {
                FastArray arr;
                if (answer instanceof MetaMethod) {
                    arr = new FastArray();
                    arr.add(answer);
                }
                else {
                    arr = ((FastArray)answer).copy();
                }
                for (final MetaMethod element : used) {
                    if (!element.getDeclaringClass().getTheClass().isAssignableFrom(sender)) {
                        continue;
                    }
                    this.filterMatchingMethodForCategory(arr, element);
                }
                answer = arr;
            }
        }
        return answer;
    }
    
    private Object getStaticMethods(final Class sender, final String name) {
        final MetaMethodIndex.Entry entry = this.metaMethodIndex.getMethods(sender, name);
        if (entry == null) {
            return FastArray.EMPTY_LIST;
        }
        final Object answer = entry.staticMethods;
        if (answer == null) {
            return FastArray.EMPTY_LIST;
        }
        return answer;
    }
    
    public boolean isModified() {
        return false;
    }
    
    public void addNewInstanceMethod(final Method method) {
        final CachedMethod cachedMethod = CachedMethod.find(method);
        final NewInstanceMetaMethod newMethod = new NewInstanceMetaMethod(cachedMethod);
        final CachedClass declaringClass = newMethod.getDeclaringClass();
        this.addNewInstanceMethodToIndex(newMethod, this.metaMethodIndex.getHeader(declaringClass.getTheClass()));
    }
    
    private void addNewInstanceMethodToIndex(final MetaMethod newMethod, final MetaMethodIndex.Header header) {
        if (!this.newGroovyMethodsSet.contains(newMethod)) {
            this.newGroovyMethodsSet.add(newMethod);
            this.addMetaMethodToIndex(newMethod, header);
        }
    }
    
    public void addNewStaticMethod(final Method method) {
        final CachedMethod cachedMethod = CachedMethod.find(method);
        final NewStaticMetaMethod newMethod = new NewStaticMetaMethod(cachedMethod);
        final CachedClass declaringClass = newMethod.getDeclaringClass();
        this.addNewStaticMethodToIndex(newMethod, this.metaMethodIndex.getHeader(declaringClass.getTheClass()));
    }
    
    private void addNewStaticMethodToIndex(final MetaMethod newMethod, final MetaMethodIndex.Header header) {
        if (!this.newGroovyMethodsSet.contains(newMethod)) {
            this.newGroovyMethodsSet.add(newMethod);
            this.addMetaMethodToIndex(newMethod, header);
        }
    }
    
    public Object invokeMethod(final Object object, final String methodName, final Object arguments) {
        if (arguments == null) {
            return this.invokeMethod(object, methodName, MetaClassHelper.EMPTY_ARRAY);
        }
        if (arguments instanceof Tuple) {
            final Tuple tuple = (Tuple)arguments;
            return this.invokeMethod(object, methodName, tuple.toArray());
        }
        if (arguments instanceof Object[]) {
            return this.invokeMethod(object, methodName, (Object[])arguments);
        }
        return this.invokeMethod(object, methodName, new Object[] { arguments });
    }
    
    public Object invokeMissingMethod(final Object instance, final String methodName, final Object[] arguments) {
        return this.invokeMissingMethod(instance, methodName, arguments, null, false);
    }
    
    public Object invokeMissingProperty(final Object instance, final String propertyName, final Object optionalValue, final boolean isGetter) {
        final Class theClass = (instance instanceof Class) ? ((Class)instance) : instance.getClass();
        CachedClass superClass = this.theCachedClass;
        while (superClass != null && superClass != ReflectionCache.OBJECT_CLASS) {
            final MetaBeanProperty property = this.findPropertyInClassHierarchy(propertyName, superClass);
            if (property != null) {
                this.onSuperPropertyFoundInHierarchy(property);
                if (!isGetter) {
                    property.setProperty(instance, optionalValue);
                    return null;
                }
                return property.getProperty(instance);
            }
            else {
                superClass = superClass.getCachedSuperClass();
            }
        }
        if (isGetter) {
            final Class[] getPropertyArgs = { String.class };
            final MetaMethod method = findMethodInClassHierarchy(instance.getClass(), "getProperty", getPropertyArgs, this);
            if (method != null && method instanceof ClosureMetaMethod) {
                this.onGetPropertyFoundInHierarchy(method);
                return method.invoke(instance, new Object[] { propertyName });
            }
        }
        else {
            final Class[] setPropertyArgs = { String.class, Object.class };
            final MetaMethod method = findMethodInClassHierarchy(instance.getClass(), "setProperty", setPropertyArgs, this);
            if (method != null && method instanceof ClosureMetaMethod) {
                this.onSetPropertyFoundInHierarchy(method);
                return method.invoke(instance, new Object[] { propertyName, optionalValue });
            }
        }
        try {
            if (!(instance instanceof Class)) {
                if (isGetter && this.propertyMissingGet != null) {
                    return this.propertyMissingGet.invoke(instance, new Object[] { propertyName });
                }
                if (this.propertyMissingSet != null) {
                    return this.propertyMissingSet.invoke(instance, new Object[] { propertyName, optionalValue });
                }
            }
        }
        catch (InvokerInvocationException iie) {
            boolean shouldHandle = isGetter && this.propertyMissingGet != null;
            if (!shouldHandle) {
                shouldHandle = (!isGetter && this.propertyMissingSet != null);
            }
            if (shouldHandle && iie.getCause() instanceof MissingPropertyException) {
                throw (MissingPropertyException)iie.getCause();
            }
            throw iie;
        }
        if (instance instanceof Class && theClass != Class.class) {
            final MetaProperty metaProperty = InvokerHelper.getMetaClass(Class.class).hasProperty(instance, propertyName);
            if (metaProperty != null) {
                if (isGetter) {
                    return metaProperty.getProperty(instance);
                }
                metaProperty.setProperty(instance, optionalValue);
                return null;
            }
        }
        throw new MissingPropertyExceptionNoStack(propertyName, theClass);
    }
    
    private Object invokeMissingMethod(final Object instance, final String methodName, final Object[] arguments, final RuntimeException original, final boolean isCallToSuper) {
        if (!isCallToSuper) {
            Class instanceKlazz = instance.getClass();
            if (this.theClass != instanceKlazz && this.theClass.isAssignableFrom(instanceKlazz)) {
                instanceKlazz = this.theClass;
            }
            final Class[] argClasses = MetaClassHelper.castArgumentsToClassArray(arguments);
            MetaMethod method = this.findMixinMethod(methodName, argClasses);
            if (method != null) {
                this.onMixinMethodFound(method);
                return method.invoke(instance, arguments);
            }
            method = findMethodInClassHierarchy(instanceKlazz, methodName, argClasses, this);
            if (method != null) {
                this.onSuperMethodFoundInHierarchy(method);
                return method.invoke(instance, arguments);
            }
            final Class[] invokeMethodArgs = { String.class, Object[].class };
            method = findMethodInClassHierarchy(instanceKlazz, "invokeMethod", invokeMethodArgs, this);
            if (method != null && method instanceof ClosureMetaMethod) {
                this.onInvokeMethodFoundInHierarchy(method);
                return method.invoke(instance, invokeMethodArgs);
            }
        }
        if (this.methodMissing != null) {
            try {
                return this.methodMissing.invoke(instance, new Object[] { methodName, arguments });
            }
            catch (InvokerInvocationException iie) {
                if (this.methodMissing instanceof ClosureMetaMethod && iie.getCause() instanceof MissingMethodException) {
                    final MissingMethodException mme = (MissingMethodException)iie.getCause();
                    throw new MissingMethodExecutionFailed(mme.getMethod(), mme.getClass(), mme.getArguments(), mme.isStatic(), mme);
                }
                throw iie;
            }
            catch (MissingMethodException mme2) {
                if (this.methodMissing instanceof ClosureMetaMethod) {
                    throw new MissingMethodExecutionFailed(mme2.getMethod(), mme2.getClass(), mme2.getArguments(), mme2.isStatic(), mme2);
                }
                throw mme2;
            }
        }
        if (original != null) {
            throw original;
        }
        throw new MissingMethodExceptionNoStack(methodName, this.theClass, arguments, false);
    }
    
    protected void onSuperPropertyFoundInHierarchy(final MetaBeanProperty property) {
    }
    
    protected void onMixinMethodFound(final MetaMethod method) {
    }
    
    protected void onSuperMethodFoundInHierarchy(final MetaMethod method) {
    }
    
    protected void onInvokeMethodFoundInHierarchy(final MetaMethod method) {
    }
    
    protected void onSetPropertyFoundInHierarchy(final MetaMethod method) {
    }
    
    protected void onGetPropertyFoundInHierarchy(final MetaMethod method) {
    }
    
    protected Object invokeStaticMissingProperty(final Object instance, final String propertyName, final Object optionalValue, final boolean isGetter) {
        final MetaClass mc = (instance instanceof Class) ? this.registry.getMetaClass((Class)instance) : this;
        if (isGetter) {
            final MetaMethod propertyMissing = mc.getMetaMethod("$static_propertyMissing", MetaClassImpl.GETTER_MISSING_ARGS);
            if (propertyMissing != null) {
                return propertyMissing.invoke(instance, new Object[] { propertyName });
            }
        }
        else {
            final MetaMethod propertyMissing = mc.getMetaMethod("$static_propertyMissing", MetaClassImpl.SETTER_MISSING_ARGS);
            if (propertyMissing != null) {
                return propertyMissing.invoke(instance, new Object[] { propertyName, optionalValue });
            }
        }
        if (instance instanceof Class) {
            throw new MissingPropertyException(propertyName, (Class)instance);
        }
        throw new MissingPropertyException(propertyName, this.theClass);
    }
    
    public Object invokeMethod(final Object object, final String methodName, final Object[] originalArguments) {
        return this.invokeMethod(this.theClass, object, methodName, originalArguments, false, false);
    }
    
    public Object invokeMethod(final Class sender, final Object object, String methodName, final Object[] originalArguments, final boolean isCallToSuper, final boolean fromInsideClass) {
        this.checkInitalised();
        if (object == null) {
            throw new NullPointerException("Cannot invoke method: " + methodName + " on null object");
        }
        final Object[] arguments = (originalArguments == null) ? MetaClassImpl.EMPTY_ARGUMENTS : originalArguments;
        MetaMethod method = this.getMethodWithCaching(sender, methodName, arguments, isCallToSuper);
        MetaClassHelper.unwrap(arguments);
        if (method == null) {
            method = this.tryListParamMetaMethod(sender, methodName, isCallToSuper, arguments);
        }
        final boolean isClosure = object instanceof Closure;
        if (isClosure) {
            final Closure closure = (Closure)object;
            final Object owner = closure.getOwner();
            if ("call".equals(methodName) || "doCall".equals(methodName)) {
                final Class objectClass = object.getClass();
                if (objectClass == MethodClosure.class) {
                    final MethodClosure mc = (MethodClosure)object;
                    methodName = mc.getMethod();
                    final Class ownerClass = (owner instanceof Class) ? ((Class)owner) : owner.getClass();
                    final MetaClass ownerMetaClass = this.registry.getMetaClass(ownerClass);
                    return ownerMetaClass.invokeMethod(ownerClass, owner, methodName, arguments, false, false);
                }
                if (objectClass == CurriedClosure.class) {
                    final CurriedClosure cc = (CurriedClosure)object;
                    final Object[] curriedArguments = cc.getUncurriedArguments(arguments);
                    final Class ownerClass2 = (owner instanceof Class) ? ((Class)owner) : owner.getClass();
                    final MetaClass ownerMetaClass2 = this.registry.getMetaClass(ownerClass2);
                    return ownerMetaClass2.invokeMethod(owner, methodName, curriedArguments);
                }
                if (method == null) {
                    this.invokeMissingMethod(object, methodName, arguments);
                }
            }
            else if ("curry".equals(methodName)) {
                return closure.curry(arguments);
            }
            final Object delegate = closure.getDelegate();
            final boolean isClosureNotOwner = owner != closure;
            final int resolveStrategy = closure.getResolveStrategy();
            final Class[] argClasses = MetaClassHelper.convertToTypeArray(arguments);
            switch (resolveStrategy) {
                case 4: {
                    method = closure.getMetaClass().pickMethod(methodName, argClasses);
                    if (method != null) {
                        return method.invoke(closure, arguments);
                    }
                    break;
                }
                case 3: {
                    if (method != null || delegate == closure || delegate == null) {
                        break;
                    }
                    final MetaClass delegateMetaClass = this.lookupObjectMetaClass(delegate);
                    method = delegateMetaClass.pickMethod(methodName, argClasses);
                    if (method != null) {
                        return delegateMetaClass.invokeMethod(delegate, methodName, originalArguments);
                    }
                    if (delegate != closure && delegate instanceof GroovyObject) {
                        return this.invokeMethodOnGroovyObject(methodName, originalArguments, delegate);
                    }
                    break;
                }
                case 2: {
                    if (method == null && owner != closure) {
                        final MetaClass ownerMetaClass2 = this.lookupObjectMetaClass(owner);
                        return ownerMetaClass2.invokeMethod(owner, methodName, originalArguments);
                    }
                    break;
                }
                case 1: {
                    if (method == null && delegate != closure && delegate != null) {
                        final MetaClass delegateMetaClass = this.lookupObjectMetaClass(delegate);
                        method = delegateMetaClass.pickMethod(methodName, argClasses);
                        if (method != null) {
                            return delegateMetaClass.invokeMethod(delegate, methodName, originalArguments);
                        }
                    }
                    if (method == null && owner != closure) {
                        final MetaClass ownerMetaClass2 = this.lookupObjectMetaClass(owner);
                        method = ownerMetaClass2.pickMethod(methodName, argClasses);
                        if (method != null) {
                            return ownerMetaClass2.invokeMethod(owner, methodName, originalArguments);
                        }
                    }
                    if (method != null || resolveStrategy == 4) {
                        break;
                    }
                    MissingMethodException last = null;
                    if (delegate != closure && delegate instanceof GroovyObject) {
                        try {
                            return this.invokeMethodOnGroovyObject(methodName, originalArguments, delegate);
                        }
                        catch (MissingMethodException mme) {
                            if (last == null) {
                                last = mme;
                            }
                        }
                    }
                    if (isClosureNotOwner && owner instanceof GroovyObject) {
                        try {
                            return this.invokeMethodOnGroovyObject(methodName, originalArguments, owner);
                        }
                        catch (MissingMethodException mme) {
                            last = mme;
                        }
                    }
                    if (last != null) {
                        return this.invokeMissingMethod(object, methodName, originalArguments, last, isCallToSuper);
                    }
                    break;
                }
                default: {
                    if (method == null && owner != closure) {
                        final MetaClass ownerMetaClass2 = this.lookupObjectMetaClass(owner);
                        method = ownerMetaClass2.pickMethod(methodName, argClasses);
                        if (method != null) {
                            return ownerMetaClass2.invokeMethod(owner, methodName, originalArguments);
                        }
                    }
                    if (method == null && delegate != closure && delegate != null) {
                        final MetaClass delegateMetaClass = this.lookupObjectMetaClass(delegate);
                        method = delegateMetaClass.pickMethod(methodName, argClasses);
                        if (method != null) {
                            return delegateMetaClass.invokeMethod(delegate, methodName, originalArguments);
                        }
                    }
                    if (method != null || resolveStrategy == 4) {
                        break;
                    }
                    MissingMethodException last = null;
                    if (isClosureNotOwner && owner instanceof GroovyObject) {
                        try {
                            return this.invokeMethodOnGroovyObject(methodName, originalArguments, owner);
                        }
                        catch (MissingMethodException mme) {
                            if (!methodName.equals(mme.getMethod())) {
                                throw mme;
                            }
                            if (last == null) {
                                last = mme;
                            }
                        }
                        catch (InvokerInvocationException iie) {
                            if (!(iie.getCause() instanceof MissingMethodException)) {
                                throw iie;
                            }
                            final MissingMethodException mme2 = (MissingMethodException)iie.getCause();
                            if (!methodName.equals(mme2.getMethod())) {
                                throw iie;
                            }
                            if (last == null) {
                                last = mme2;
                            }
                        }
                    }
                    if (delegate != closure && delegate instanceof GroovyObject) {
                        try {
                            return this.invokeMethodOnGroovyObject(methodName, originalArguments, delegate);
                        }
                        catch (MissingMethodException mme) {
                            last = mme;
                        }
                        catch (InvokerInvocationException iie) {
                            if (!(iie.getCause() instanceof MissingMethodException)) {
                                throw iie;
                            }
                            last = (MissingMethodException)iie.getCause();
                        }
                    }
                    if (last != null) {
                        return this.invokeMissingMethod(object, methodName, originalArguments, last, isCallToSuper);
                    }
                    break;
                }
            }
        }
        if (method != null) {
            return method.doMethodInvoke(object, arguments);
        }
        return this.invokePropertyOrMissing(object, methodName, originalArguments, fromInsideClass, isCallToSuper);
    }
    
    private MetaMethod tryListParamMetaMethod(final Class sender, final String methodName, final boolean isCallToSuper, final Object[] arguments) {
        MetaMethod method = null;
        if (arguments.length == 1 && arguments[0] instanceof List) {
            final Object[] newArguments = ((List)arguments[0]).toArray();
            method = this.getMethodWithCaching(sender, methodName, newArguments, isCallToSuper);
            if (method != null) {
                method = new TransformMetaMethod(method) {
                    @Override
                    public Object invoke(final Object object, Object[] arguments) {
                        final Object firstArgument = arguments[0];
                        final List list = (List)firstArgument;
                        arguments = list.toArray();
                        return super.invoke(object, arguments);
                    }
                };
            }
        }
        return method;
    }
    
    private Object invokePropertyOrMissing(final Object object, final String methodName, final Object[] originalArguments, final boolean fromInsideClass, final boolean isCallToSuper) {
        Object value = null;
        final MetaProperty metaProperty = this.getMetaProperty(methodName, false);
        if (metaProperty != null) {
            value = metaProperty.getProperty(object);
        }
        else if (object instanceof Map) {
            value = ((Map)object).get(methodName);
        }
        if (value instanceof Closure) {
            final Closure closure = (Closure)value;
            final MetaClass delegateMetaClass = closure.getMetaClass();
            return delegateMetaClass.invokeMethod(closure.getClass(), closure, "doCall", originalArguments, false, fromInsideClass);
        }
        if (object instanceof Script) {
            final Object bindingVar = ((Script)object).getBinding().getVariables().get(methodName);
            if (bindingVar != null) {
                final MetaClass bindingVarMC = ((MetaClassRegistryImpl)this.registry).getMetaClass(bindingVar);
                return bindingVarMC.invokeMethod(bindingVar, "call", originalArguments);
            }
        }
        return this.invokeMissingMethod(object, methodName, originalArguments, null, isCallToSuper);
    }
    
    private MetaClass lookupObjectMetaClass(final Object object) {
        if (object instanceof GroovyObject) {
            final GroovyObject go = (GroovyObject)object;
            return go.getMetaClass();
        }
        Class ownerClass = object.getClass();
        if (ownerClass == Class.class) {
            ownerClass = (Class)object;
        }
        final MetaClass metaClass = this.registry.getMetaClass(ownerClass);
        return metaClass;
    }
    
    private Object invokeMethodOnGroovyObject(final String methodName, final Object[] originalArguments, final Object owner) {
        final GroovyObject go = (GroovyObject)owner;
        return go.invokeMethod(methodName, originalArguments);
    }
    
    public MetaMethod getMethodWithCaching(final Class sender, final String methodName, final Object[] arguments, final boolean isCallToSuper) {
        if (!isCallToSuper && GroovyCategorySupport.hasCategoryInCurrentThread()) {
            return this.getMethodWithoutCaching(sender, methodName, MetaClassHelper.convertToTypeArray(arguments), isCallToSuper);
        }
        final MetaMethodIndex.Entry e = this.metaMethodIndex.getMethods(sender, methodName);
        if (e == null) {
            return null;
        }
        return isCallToSuper ? this.getSuperMethodWithCaching(arguments, e) : this.getNormalMethodWithCaching(arguments, e);
    }
    
    private static boolean sameClasses(final Class[] params, final Class[] arguments, final boolean weakNullCheck) {
        if (params == null) {
            return false;
        }
        if (params.length != arguments.length) {
            return false;
        }
        for (int i = params.length - 1; i >= 0; --i) {
            final Object arg = arguments[i];
            if (arg != null) {
                if (params[i] != arguments[i]) {
                    return false;
                }
            }
            else if (!weakNullCheck) {
                return false;
            }
        }
        return true;
    }
    
    private MetaMethod getMethodWithCachingInternal(final Class sender, final CallSite site, final Class[] params) {
        if (site.getUsage().get() != 0 && GroovyCategorySupport.hasCategoryInCurrentThread()) {
            return this.getMethodWithoutCaching(sender, site.getName(), params, false);
        }
        final MetaMethodIndex.Entry e = this.metaMethodIndex.getMethods(sender, site.getName());
        if (e == null) {
            return null;
        }
        final Object methods = e.methods;
        if (methods == null) {
            return null;
        }
        MetaMethodIndex.CacheEntry cacheEntry = e.cachedMethod;
        if (cacheEntry != null && sameClasses(cacheEntry.params, params, methods instanceof MetaMethod)) {
            return cacheEntry.method;
        }
        cacheEntry = new MetaMethodIndex.CacheEntry();
        cacheEntry.params = params;
        cacheEntry.method = (MetaMethod)this.chooseMethod(e.name, methods, params);
        e.cachedMethod = cacheEntry;
        return cacheEntry.method;
    }
    
    private MetaMethod getSuperMethodWithCaching(final Object[] arguments, final MetaMethodIndex.Entry e) {
        if (e.methodsForSuper == null) {
            return null;
        }
        MetaMethodIndex.CacheEntry cacheEntry = e.cachedMethodForSuper;
        if (cacheEntry != null && MetaClassHelper.sameClasses(cacheEntry.params, arguments, e.methodsForSuper instanceof MetaMethod)) {
            final MetaMethod method = cacheEntry.method;
            if (method != null) {
                return method;
            }
        }
        cacheEntry = new MetaMethodIndex.CacheEntry();
        final Class[] classes = MetaClassHelper.convertToTypeArray(arguments);
        cacheEntry.params = classes;
        cacheEntry.method = (MetaMethod)this.chooseMethod(e.name, e.methodsForSuper, classes);
        if (cacheEntry.method.isAbstract()) {
            cacheEntry.method = null;
        }
        e.cachedMethodForSuper = cacheEntry;
        return cacheEntry.method;
    }
    
    private MetaMethod getNormalMethodWithCaching(final Object[] arguments, final MetaMethodIndex.Entry e) {
        final Object methods = e.methods;
        if (methods == null) {
            return null;
        }
        MetaMethodIndex.CacheEntry cacheEntry = e.cachedMethod;
        if (cacheEntry != null && MetaClassHelper.sameClasses(cacheEntry.params, arguments, methods instanceof MetaMethod)) {
            final MetaMethod method = cacheEntry.method;
            if (method != null) {
                return method;
            }
        }
        cacheEntry = new MetaMethodIndex.CacheEntry();
        final Class[] classes = MetaClassHelper.convertToTypeArray(arguments);
        cacheEntry.params = classes;
        cacheEntry.method = (MetaMethod)this.chooseMethod(e.name, methods, classes);
        e.cachedMethod = cacheEntry;
        return cacheEntry.method;
    }
    
    public Constructor retrieveConstructor(final Class[] arguments) {
        CachedConstructor constructor = (CachedConstructor)this.chooseMethod("<init>", this.constructors, arguments);
        if (constructor != null) {
            return constructor.cachedConstructor;
        }
        constructor = (CachedConstructor)this.chooseMethod("<init>", this.constructors, arguments);
        if (constructor != null) {
            return constructor.cachedConstructor;
        }
        return null;
    }
    
    public MetaMethod retrieveStaticMethod(final String methodName, final Object[] arguments) {
        final MetaMethodIndex.Entry e = this.metaMethodIndex.getMethods(this.theClass, methodName);
        if (e == null) {
            return this.pickStaticMethod(methodName, MetaClassHelper.convertToTypeArray(arguments));
        }
        MetaMethodIndex.CacheEntry cacheEntry = e.cachedStaticMethod;
        if (cacheEntry != null && MetaClassHelper.sameClasses(cacheEntry.params, arguments, e.staticMethods instanceof MetaMethod)) {
            return cacheEntry.method;
        }
        cacheEntry = new MetaMethodIndex.CacheEntry();
        final Class[] classes = MetaClassHelper.convertToTypeArray(arguments);
        cacheEntry.params = classes;
        cacheEntry.method = this.pickStaticMethod(methodName, classes);
        e.cachedStaticMethod = cacheEntry;
        return cacheEntry.method;
    }
    
    public MetaMethod getMethodWithoutCaching(final Class sender, final String methodName, final Class[] arguments, final boolean isCallToSuper) {
        MetaMethod method = null;
        final Object methods = this.getMethods(sender, methodName, isCallToSuper);
        if (methods != null) {
            method = (MetaMethod)this.chooseMethod(methodName, methods, arguments);
        }
        return method;
    }
    
    public Object invokeStaticMethod(final Object object, final String methodName, Object[] arguments) {
        this.checkInitalised();
        final Class sender = (object instanceof Class) ? ((Class)object) : object.getClass();
        if (sender != this.theClass) {
            final MetaClass mc = this.registry.getMetaClass(sender);
            return mc.invokeStaticMethod(sender, methodName, arguments);
        }
        if (sender == Class.class) {
            return this.invokeMethod(object, methodName, arguments);
        }
        if (arguments == null) {
            arguments = MetaClassImpl.EMPTY_ARGUMENTS;
        }
        MetaMethod method = this.retrieveStaticMethod(methodName, arguments);
        if (method != null) {
            MetaClassHelper.unwrap(arguments);
            return method.doMethodInvoke(object, arguments);
        }
        Object prop = null;
        try {
            prop = this.getProperty(this.theClass, this.theClass, methodName, false, false);
        }
        catch (MissingPropertyException ex) {}
        if (prop instanceof Closure) {
            return this.invokeStaticClosureProperty(arguments, prop);
        }
        final Object[] originalArguments = arguments.clone();
        MetaClassHelper.unwrap(arguments);
        Class superClass = sender.getSuperclass();
        final Class[] argClasses = MetaClassHelper.convertToTypeArray(arguments);
        while (superClass != Object.class && superClass != null) {
            final MetaClass mc2 = this.registry.getMetaClass(superClass);
            method = mc2.getStaticMetaMethod(methodName, argClasses);
            if (method != null) {
                return method.doMethodInvoke(object, arguments);
            }
            try {
                prop = mc2.getProperty(superClass, superClass, methodName, false, false);
            }
            catch (MissingPropertyException ex2) {}
            if (prop instanceof Closure) {
                return this.invokeStaticClosureProperty(originalArguments, prop);
            }
            superClass = superClass.getSuperclass();
        }
        if (prop != null) {
            final MetaClass propMC = this.registry.getMetaClass(prop.getClass());
            return propMC.invokeMethod(prop, "call", arguments);
        }
        return this.invokeStaticMissingMethod(sender, methodName, arguments);
    }
    
    private Object invokeStaticClosureProperty(final Object[] originalArguments, final Object prop) {
        final Closure closure = (Closure)prop;
        final MetaClass delegateMetaClass = closure.getMetaClass();
        return delegateMetaClass.invokeMethod(closure.getClass(), closure, "doCall", originalArguments, false, false);
    }
    
    private Object invokeStaticMissingMethod(final Class sender, final String methodName, final Object[] arguments) {
        final MetaMethod metaMethod = this.getStaticMetaMethod("$static_methodMissing", MetaClassImpl.METHOD_MISSING_ARGS);
        if (metaMethod != null) {
            return metaMethod.invoke(sender, new Object[] { methodName, arguments });
        }
        throw new MissingMethodException(methodName, sender, arguments, true);
    }
    
    private MetaMethod pickStaticMethod(final String methodName, final Class[] arguments) {
        MetaMethod method = null;
        MethodSelectionException mse = null;
        final Object methods = this.getStaticMethods(this.theClass, methodName);
        Label_0056: {
            if (methods instanceof FastArray) {
                if (((FastArray)methods).isEmpty()) {
                    break Label_0056;
                }
            }
            try {
                method = (MetaMethod)this.chooseMethod(methodName, methods, arguments);
            }
            catch (MethodSelectionException msex) {
                mse = msex;
            }
        }
        if (method == null && this.theClass != Class.class) {
            final MetaClass classMetaClass = this.registry.getMetaClass(Class.class);
            method = classMetaClass.pickMethod(methodName, arguments);
        }
        if (method == null) {
            method = (MetaMethod)this.chooseMethod(methodName, methods, MetaClassHelper.convertToTypeArray(arguments));
        }
        if (method == null && mse != null) {
            throw mse;
        }
        return method;
    }
    
    @Deprecated
    public Object invokeConstructorAt(final Class at, final Object[] arguments) {
        return this.invokeConstructor(arguments);
    }
    
    public Object invokeConstructor(final Object[] arguments) {
        return this.invokeConstructor(this.theClass, arguments);
    }
    
    public int selectConstructorAndTransformArguments(final int numberOfConstructors, Object[] arguments) {
        if (numberOfConstructors != this.constructors.size()) {
            throw new IncompatibleClassChangeError("the number of constructors during runtime and compile time for " + this.theClass.getName() + " do not match. Expected " + numberOfConstructors + " but got " + this.constructors.size());
        }
        if (arguments == null) {
            arguments = MetaClassImpl.EMPTY_ARGUMENTS;
        }
        final Class[] argClasses = MetaClassHelper.convertToTypeArray(arguments);
        MetaClassHelper.unwrap(arguments);
        CachedConstructor constructor = (CachedConstructor)this.chooseMethod("<init>", this.constructors, argClasses);
        if (constructor == null) {
            constructor = (CachedConstructor)this.chooseMethod("<init>", this.constructors, argClasses);
        }
        if (constructor == null) {
            throw new GroovyRuntimeException("Could not find matching constructor for: " + this.theClass.getName() + "(" + InvokerHelper.toTypeString(arguments) + ")");
        }
        final List l = new ArrayList(this.constructors.toList());
        final Comparator comp = new Comparator() {
            public int compare(final Object arg0, final Object arg1) {
                final CachedConstructor c0 = (CachedConstructor)arg0;
                final CachedConstructor c2 = (CachedConstructor)arg1;
                final String descriptor0 = BytecodeHelper.getMethodDescriptor(Void.TYPE, c0.getNativeParameterTypes());
                final String descriptor2 = BytecodeHelper.getMethodDescriptor(Void.TYPE, c2.getNativeParameterTypes());
                return descriptor0.compareTo(descriptor2);
            }
        };
        Collections.sort((List<Object>)l, comp);
        int found = -1;
        for (int i = 0; i < l.size(); ++i) {
            if (l.get(i) == constructor) {
                found = i;
                break;
            }
        }
        return 0x0 | found << 8;
    }
    
    protected void checkInitalised() {
        if (!this.isInitialized()) {
            throw new IllegalStateException("initialize must be called for meta class of " + this.theClass + "(" + this.getClass() + ") " + "to complete initialisation process " + "before any invocation or field/property " + "access can be done");
        }
    }
    
    private Object invokeConstructor(final Class at, Object[] arguments) {
        this.checkInitalised();
        if (arguments == null) {
            arguments = MetaClassImpl.EMPTY_ARGUMENTS;
        }
        final Class[] argClasses = MetaClassHelper.convertToTypeArray(arguments);
        MetaClassHelper.unwrap(arguments);
        CachedConstructor constructor = (CachedConstructor)this.chooseMethod("<init>", this.constructors, argClasses);
        if (constructor != null) {
            return constructor.doConstructorInvoke(arguments);
        }
        if (arguments.length == 1) {
            final Object firstArgument = arguments[0];
            if (firstArgument instanceof Map) {
                constructor = (CachedConstructor)this.chooseMethod("<init>", this.constructors, MetaClassHelper.EMPTY_TYPE_ARRAY);
                if (constructor != null) {
                    final Object bean = constructor.doConstructorInvoke(MetaClassHelper.EMPTY_ARRAY);
                    this.setProperties(bean, (Map)firstArgument);
                    return bean;
                }
            }
        }
        throw new GroovyRuntimeException("Could not find matching constructor for: " + this.theClass.getName() + "(" + InvokerHelper.toTypeString(arguments) + ")");
    }
    
    public void setProperties(final Object bean, final Map map) {
        this.checkInitalised();
        for (final Map.Entry entry : map.entrySet()) {
            final String key = entry.getKey().toString();
            final Object value = entry.getValue();
            this.setProperty(bean, key, value);
        }
    }
    
    public Object getProperty(final Class sender, final Object object, final String name, final boolean useSuper, final boolean fromInsideClass) {
        final boolean isStatic = this.theClass != Class.class && object instanceof Class;
        if (isStatic && object != this.theClass) {
            final MetaClass mc = this.registry.getMetaClass((Class)object);
            return mc.getProperty(sender, object, name, useSuper, false);
        }
        this.checkInitalised();
        if (!isStatic && this.isMap) {
            return ((Map)object).get(name);
        }
        MetaMethod method = null;
        Object[] arguments = MetaClassImpl.EMPTY_ARGUMENTS;
        MetaProperty mp = this.getMetaProperty(sender, name, useSuper, isStatic);
        if (mp != null && mp instanceof MetaBeanProperty) {
            final MetaBeanProperty mbp = (MetaBeanProperty)mp;
            method = mbp.getGetter();
            mp = mbp.getField();
        }
        if (!useSuper && !isStatic && GroovyCategorySupport.hasCategoryInCurrentThread()) {
            final String getterName = GroovyCategorySupport.getPropertyCategoryGetterName(name);
            if (getterName != null) {
                final MetaMethod categoryMethod = this.getCategoryMethodGetter(sender, getterName, false);
                if (categoryMethod != null) {
                    method = categoryMethod;
                }
            }
        }
        if (method == null && mp != null) {
            try {
                return mp.getProperty(object);
            }
            catch (IllegalArgumentException e) {
                mp = null;
            }
        }
        if (method == null && !useSuper && !isStatic && GroovyCategorySupport.hasCategoryInCurrentThread()) {
            method = this.getCategoryMethodGetter(sender, "get", true);
            if (method != null) {
                arguments = new Object[] { name };
            }
        }
        if (method == null && this.genericGetMethod != null && (this.genericGetMethod.isStatic() || !isStatic)) {
            arguments = new Object[] { name };
            method = this.genericGetMethod;
        }
        if (method != null) {
            return method.doMethodInvoke(object, arguments);
        }
        if (this.theClass != Class.class && object instanceof Class) {
            final MetaClass mc2 = this.registry.getMetaClass(Class.class);
            return mc2.getProperty(Class.class, object, name, useSuper, false);
        }
        if (object instanceof Collection) {
            return DefaultGroovyMethods.getAt((Collection)object, name);
        }
        if (object instanceof Object[]) {
            return DefaultGroovyMethods.getAt(Arrays.asList((Object[])object), name);
        }
        final MetaMethod addListenerMethod = this.listeners.get(name);
        if (addListenerMethod != null) {
            return null;
        }
        if (isStatic || object instanceof Class) {
            return this.invokeStaticMissingProperty(object, name, null, true);
        }
        return this.invokeMissingProperty(object, name, null, true);
    }
    
    public MetaProperty getEffectiveGetMetaProperty(final Class sender, final Object object, final String name, final boolean useSuper) {
        final boolean isStatic = this.theClass != Class.class && object instanceof Class;
        if (isStatic && object != this.theClass) {
            return new MetaProperty(name, Object.class) {
                final MetaClass mc = MetaClassImpl.this.registry.getMetaClass((Class)object);
                
                @Override
                public Object getProperty(final Object object) {
                    return this.mc.getProperty(sender, object, this.name, useSuper, false);
                }
                
                @Override
                public void setProperty(final Object object, final Object newValue) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        this.checkInitalised();
        if (!isStatic && this.isMap) {
            return new MetaProperty(name, Object.class) {
                @Override
                public Object getProperty(final Object object) {
                    return ((Map)object).get(this.name);
                }
                
                @Override
                public void setProperty(final Object object, final Object newValue) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        MetaMethod method = null;
        MetaProperty mp = this.getMetaProperty(sender, name, useSuper, isStatic);
        if (mp != null && mp instanceof MetaBeanProperty) {
            final MetaBeanProperty mbp = (MetaBeanProperty)mp;
            method = mbp.getGetter();
            mp = mbp.getField();
        }
        if (!useSuper && !isStatic && GroovyCategorySupport.hasCategoryInCurrentThread()) {
            final String getterName = GroovyCategorySupport.getPropertyCategoryGetterName(name);
            if (getterName != null) {
                final MetaMethod categoryMethod = this.getCategoryMethodGetter(sender, getterName, false);
                if (categoryMethod != null) {
                    method = categoryMethod;
                }
            }
        }
        if (method != null) {
            return new GetBeanMethodMetaProperty(name, method);
        }
        if (mp != null) {
            return mp;
        }
        if (!useSuper && !isStatic && GroovyCategorySupport.hasCategoryInCurrentThread()) {
            method = this.getCategoryMethodGetter(sender, "get", true);
            if (method != null) {
                return new GetMethodMetaProperty(name, method);
            }
        }
        if (this.genericGetMethod != null && (this.genericGetMethod.isStatic() || !isStatic)) {
            method = this.genericGetMethod;
            if (method != null) {
                return new GetMethodMetaProperty(name, method);
            }
        }
        if (this.theClass != Class.class && object instanceof Class) {
            return new MetaProperty(name, Object.class) {
                @Override
                public Object getProperty(final Object object) {
                    final MetaClass mc = MetaClassImpl.this.registry.getMetaClass(Class.class);
                    return mc.getProperty(Class.class, object, this.name, useSuper, false);
                }
                
                @Override
                public void setProperty(final Object object, final Object newValue) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        if (object instanceof Collection) {
            return new MetaProperty(name, Object.class) {
                @Override
                public Object getProperty(final Object object) {
                    return DefaultGroovyMethods.getAt((Collection)object, this.name);
                }
                
                @Override
                public void setProperty(final Object object, final Object newValue) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        if (object instanceof Object[]) {
            return new MetaProperty(name, Object.class) {
                @Override
                public Object getProperty(final Object object) {
                    return DefaultGroovyMethods.getAt(Arrays.asList((Object[])object), this.name);
                }
                
                @Override
                public void setProperty(final Object object, final Object newValue) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        final MetaMethod addListenerMethod = this.listeners.get(name);
        if (addListenerMethod != null) {
            return new MetaProperty(name, Object.class) {
                @Override
                public Object getProperty(final Object object) {
                    return null;
                }
                
                @Override
                public void setProperty(final Object object, final Object newValue) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        if (isStatic || object instanceof Class) {
            return new MetaProperty(name, Object.class) {
                @Override
                public Object getProperty(final Object object) {
                    return MetaClassImpl.this.invokeStaticMissingProperty(object, this.name, null, true);
                }
                
                @Override
                public void setProperty(final Object object, final Object newValue) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        return new MetaProperty(name, Object.class) {
            @Override
            public Object getProperty(final Object object) {
                return MetaClassImpl.this.invokeMissingProperty(object, this.name, null, true);
            }
            
            @Override
            public void setProperty(final Object object, final Object newValue) {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    private MetaMethod getCategoryMethodGetter(final Class sender, final String name, final boolean useLongVersion) {
        final List possibleGenericMethods = GroovyCategorySupport.getCategoryMethods(name);
        if (possibleGenericMethods != null) {
            for (final MetaMethod mmethod : possibleGenericMethods) {
                if (!mmethod.getDeclaringClass().getTheClass().isAssignableFrom(sender)) {
                    continue;
                }
                final CachedClass[] paramTypes = mmethod.getParameterTypes();
                if (useLongVersion) {
                    if (paramTypes.length == 1 && paramTypes[0].getTheClass() == String.class) {
                        return mmethod;
                    }
                    continue;
                }
                else {
                    if (paramTypes.length == 0) {
                        return mmethod;
                    }
                    continue;
                }
            }
        }
        return null;
    }
    
    private MetaMethod getCategoryMethodSetter(final Class sender, final String name, final boolean useLongVersion) {
        final List possibleGenericMethods = GroovyCategorySupport.getCategoryMethods(name);
        if (possibleGenericMethods != null) {
            for (final MetaMethod mmethod : possibleGenericMethods) {
                if (!mmethod.getDeclaringClass().getTheClass().isAssignableFrom(sender)) {
                    continue;
                }
                final CachedClass[] paramTypes = mmethod.getParameterTypes();
                if (useLongVersion) {
                    if (paramTypes.length == 2 && paramTypes[0].getTheClass() == String.class) {
                        return mmethod;
                    }
                    continue;
                }
                else {
                    if (paramTypes.length == 1) {
                        return mmethod;
                    }
                    continue;
                }
            }
        }
        return null;
    }
    
    public List<MetaProperty> getProperties() {
        this.checkInitalised();
        final SingleKeyHashMap propertyMap = this.classPropertyIndex.getNullable(this.theCachedClass);
        final List ret = new ArrayList(propertyMap.size());
        final ComplexKeyHashMap.EntryIterator iter = propertyMap.getEntrySetIterator();
        while (iter.hasNext()) {
            final MetaProperty element = (MetaProperty)((SingleKeyHashMap.Entry)iter.next()).value;
            if (element instanceof CachedField) {
                continue;
            }
            if (element instanceof MetaBeanProperty) {
                final MetaBeanProperty mp = (MetaBeanProperty)element;
                boolean setter = true;
                boolean getter = true;
                if (mp.getGetter() == null || mp.getGetter() instanceof GeneratedMetaMethod || mp.getGetter() instanceof NewInstanceMetaMethod) {
                    getter = false;
                }
                if (mp.getSetter() == null || mp.getSetter() instanceof GeneratedMetaMethod || mp.getSetter() instanceof NewInstanceMetaMethod) {
                    setter = false;
                }
                if (!setter && !getter) {
                    continue;
                }
            }
            ret.add(element);
        }
        return (List<MetaProperty>)ret;
    }
    
    private MetaMethod findPropertyMethod(final Object methodOrList, final boolean isGetter, final boolean booleanGetter) {
        if (methodOrList == null) {
            return null;
        }
        Object ret = null;
        if (methodOrList instanceof MetaMethod) {
            final MetaMethod element = (MetaMethod)methodOrList;
            if (!isGetter && element.getParameterTypes().length == 1) {
                ret = this.addElementToList(ret, element);
            }
            if (isGetter && element.getReturnType() != Void.class && element.getReturnType() != Void.TYPE && (!booleanGetter || element.getReturnType() == Boolean.class || element.getReturnType() == Boolean.TYPE) && element.getParameterTypes().length == 0) {
                ret = this.addElementToList(ret, element);
            }
        }
        else {
            final FastArray methods = (FastArray)methodOrList;
            final int len = methods.size();
            final Object[] data = methods.getArray();
            for (int i = 0; i != len; ++i) {
                final MetaMethod element2 = (MetaMethod)data[i];
                if (!isGetter && element2.getParameterTypes().length == 1) {
                    ret = this.addElementToList(ret, element2);
                }
                if (isGetter && element2.getReturnType() != Void.class && element2.getReturnType() != Void.TYPE && element2.getParameterTypes().length == 0) {
                    ret = this.addElementToList(ret, element2);
                }
            }
        }
        if (ret == null) {
            return null;
        }
        if (ret instanceof MetaMethod) {
            return (MetaMethod)ret;
        }
        MetaMethod method = null;
        int distance = -1;
        for (final MetaMethod element3 : (List)ret) {
            Class c;
            if (isGetter) {
                c = element3.getReturnType();
            }
            else {
                c = element3.getParameterTypes()[0].getTheClass();
            }
            final int localDistance = distanceToObject(c);
            if (distance == -1 || distance > localDistance) {
                distance = localDistance;
                method = element3;
            }
        }
        return method;
    }
    
    private Object addElementToList(Object ret, final MetaMethod element) {
        if (ret == null) {
            ret = element;
        }
        else if (ret instanceof List) {
            ((List)ret).add(element);
        }
        else {
            final List list = new LinkedList();
            list.add(ret);
            list.add(element);
            ret = list;
        }
        return ret;
    }
    
    private static int distanceToObject(Class c) {
        int count;
        for (count = 0; c != null; c = c.getSuperclass(), ++count) {}
        return count;
    }
    
    private void setupProperties(final PropertyDescriptor[] propertyDescriptors) {
        if (this.theCachedClass.isInterface) {
            final LinkedList<CachedClass> superClasses = new LinkedList<CachedClass>();
            superClasses.add(ReflectionCache.OBJECT_CLASS);
            final Set interfaces = this.theCachedClass.getInterfaces();
            this.classPropertyIndexForSuper = this.classPropertyIndex;
            final SingleKeyHashMap cPI = this.classPropertyIndex.getNotNull(this.theCachedClass);
            for (final CachedClass iclass : interfaces) {
                final SingleKeyHashMap iPropertyIndex = cPI;
                this.addFields(iclass, iPropertyIndex);
                this.classPropertyIndex.put(iclass, iPropertyIndex);
            }
            this.classPropertyIndex.put(ReflectionCache.OBJECT_CLASS, cPI);
            this.applyPropertyDescriptors(propertyDescriptors);
            this.applyStrayPropertyMethods(superClasses, this.classPropertyIndex, true);
            this.makeStaticPropertyIndex();
        }
        else {
            final LinkedList<CachedClass> superClasses = this.getSuperClasses();
            final Set interfaces = this.theCachedClass.getInterfaces();
            if (this.theCachedClass.isArray) {
                final SingleKeyHashMap map = new SingleKeyHashMap();
                map.put("length", this.arrayLengthProperty);
                this.classPropertyIndex.put(this.theCachedClass, map);
            }
            this.inheritStaticInterfaceFields(superClasses, interfaces);
            this.inheritFields(superClasses);
            this.applyPropertyDescriptors(propertyDescriptors);
            this.applyStrayPropertyMethods(superClasses, this.classPropertyIndex, true);
            this.applyStrayPropertyMethods(superClasses, this.classPropertyIndexForSuper, false);
            this.copyClassPropertyIndexForSuper(this.classPropertyIndexForSuper);
            this.makeStaticPropertyIndex();
        }
    }
    
    private void makeStaticPropertyIndex() {
        final SingleKeyHashMap propertyMap = this.classPropertyIndex.getNotNull(this.theCachedClass);
        final ComplexKeyHashMap.EntryIterator iter = propertyMap.getEntrySetIterator();
        while (iter.hasNext()) {
            final SingleKeyHashMap.Entry entry = (SingleKeyHashMap.Entry)iter.next();
            MetaProperty mp = (MetaProperty)entry.getValue();
            if (mp instanceof CachedField) {
                final CachedField mfp = (CachedField)mp;
                if (!mfp.isStatic()) {
                    continue;
                }
            }
            else {
                if (!(mp instanceof MetaBeanProperty)) {
                    continue;
                }
                final MetaProperty result = this.establishStaticMetaProperty(mp);
                if (result == null) {
                    continue;
                }
                mp = result;
            }
            this.staticPropertyIndex.put(entry.getKey(), mp);
        }
    }
    
    private MetaProperty establishStaticMetaProperty(final MetaProperty mp) {
        final MetaBeanProperty mbp = (MetaBeanProperty)mp;
        MetaProperty result = null;
        final MetaMethod getterMethod = mbp.getGetter();
        final MetaMethod setterMethod = mbp.getSetter();
        final CachedField metaField = mbp.getField();
        final boolean getter = getterMethod == null || getterMethod.isStatic();
        final boolean setter = setterMethod == null || setterMethod.isStatic();
        final boolean field = metaField == null || metaField.isStatic();
        if (!getter && !setter && !field) {
            return result;
        }
        final String propertyName = mbp.getName();
        final Class propertyType = mbp.getType();
        if (setter && getter) {
            if (field) {
                result = mbp;
            }
            else {
                result = new MetaBeanProperty(propertyName, propertyType, getterMethod, setterMethod);
            }
        }
        else if (getter && !setter) {
            if (getterMethod == null) {
                result = metaField;
            }
            else {
                final MetaBeanProperty newmp = new MetaBeanProperty(propertyName, propertyType, getterMethod, null);
                if (field) {
                    newmp.setField(metaField);
                }
                result = newmp;
            }
        }
        else if (setter && !getter) {
            if (setterMethod == null) {
                result = metaField;
            }
            else {
                final MetaBeanProperty newmp = new MetaBeanProperty(propertyName, propertyType, null, setterMethod);
                if (field) {
                    newmp.setField(metaField);
                }
                result = newmp;
            }
        }
        else {
            result = metaField;
        }
        return result;
    }
    
    private void copyClassPropertyIndexForSuper(final Index dest) {
        final ComplexKeyHashMap.EntryIterator iter = this.classPropertyIndex.getEntrySetIterator();
        while (iter.hasNext()) {
            final SingleKeyHashMap.Entry entry = (SingleKeyHashMap.Entry)iter.next();
            final SingleKeyHashMap newVal = new SingleKeyHashMap();
            dest.put((CachedClass)entry.getKey(), newVal);
        }
    }
    
    private void inheritStaticInterfaceFields(final LinkedList superClasses, final Set interfaces) {
        for (final CachedClass iclass : interfaces) {
            final SingleKeyHashMap iPropertyIndex = this.classPropertyIndex.getNotNull(iclass);
            this.addFields(iclass, iPropertyIndex);
            for (final CachedClass sclass : superClasses) {
                if (!iclass.getTheClass().isAssignableFrom(sclass.getTheClass())) {
                    continue;
                }
                final SingleKeyHashMap sPropertyIndex = this.classPropertyIndex.getNotNull(sclass);
                this.copyNonPrivateFields(iPropertyIndex, sPropertyIndex);
            }
        }
    }
    
    private void inheritFields(final LinkedList<CachedClass> superClasses) {
        SingleKeyHashMap last = null;
        for (final CachedClass klass : superClasses) {
            final SingleKeyHashMap propertyIndex = this.classPropertyIndex.getNotNull(klass);
            if (last != null) {
                this.copyNonPrivateFields(last, propertyIndex);
            }
            last = propertyIndex;
            this.addFields(klass, propertyIndex);
        }
    }
    
    private void addFields(final CachedClass klass, final SingleKeyHashMap propertyIndex) {
        final CachedField[] arr$;
        final CachedField[] fields = arr$ = klass.getFields();
        for (final CachedField field : arr$) {
            propertyIndex.put(field.getName(), field);
        }
    }
    
    private void copyNonPrivateFields(final SingleKeyHashMap from, final SingleKeyHashMap to) {
        final ComplexKeyHashMap.EntryIterator iter = from.getEntrySetIterator();
        while (iter.hasNext()) {
            final SingleKeyHashMap.Entry entry = (SingleKeyHashMap.Entry)iter.next();
            final CachedField mfp = (CachedField)entry.getValue();
            if (!Modifier.isPublic(mfp.getModifiers()) && !Modifier.isProtected(mfp.getModifiers())) {
                continue;
            }
            to.put(entry.getKey(), mfp);
        }
    }
    
    private void applyStrayPropertyMethods(final LinkedList<CachedClass> superClasses, final Index classPropertyIndex, final boolean isThis) {
        for (final CachedClass klass : superClasses) {
            final MetaMethodIndex.Header header = this.metaMethodIndex.getHeader(klass.getTheClass());
            final SingleKeyHashMap propertyIndex = classPropertyIndex.getNotNull(klass);
            for (MetaMethodIndex.Entry e = header.head; e != null; e = e.nextClassEntry) {
                final String methodName = e.name;
                if (methodName.length() >= 3) {
                    if (methodName.startsWith("is") || methodName.length() >= 4) {
                        final boolean isGetter = methodName.startsWith("get") || methodName.startsWith("is");
                        final boolean isBooleanGetter = methodName.startsWith("is");
                        final boolean isSetter = methodName.startsWith("set");
                        if (isGetter || isSetter) {
                            final MetaMethod propertyMethod = this.findPropertyMethod(isThis ? e.methods : e.methodsForSuper, isGetter, isBooleanGetter);
                            if (propertyMethod != null) {
                                final String propName = this.getPropName(methodName);
                                this.createMetaBeanProperty(propertyIndex, propName, isGetter, propertyMethod);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private String getPropName(final String methodName) {
        final String name = MetaClassImpl.propNames.get(methodName);
        if (name != null) {
            return name;
        }
        synchronized (MetaClassImpl.propNames) {
            final String stripped = methodName.startsWith("is") ? methodName.substring(2) : methodName.substring(3);
            final String propName = Introspector.decapitalize(stripped);
            MetaClassImpl.propNames.put(methodName, propName);
            return propName;
        }
    }
    
    private void createMetaBeanProperty(final SingleKeyHashMap propertyIndex, final String propName, final boolean isGetter, final MetaMethod propertyMethod) {
        MetaProperty mp = (MetaProperty)propertyIndex.get(propName);
        if (mp == null) {
            if (isGetter) {
                mp = new MetaBeanProperty(propName, propertyMethod.getReturnType(), propertyMethod, null);
            }
            else {
                mp = new MetaBeanProperty(propName, propertyMethod.getParameterTypes()[0].getTheClass(), null, propertyMethod);
            }
        }
        else {
            MetaBeanProperty mbp;
            CachedField mfp;
            if (mp instanceof MetaBeanProperty) {
                mbp = (MetaBeanProperty)mp;
                mfp = mbp.getField();
            }
            else {
                if (!(mp instanceof CachedField)) {
                    throw new GroovyBugError("unknown MetaProperty class used. Class is " + mp.getClass());
                }
                mfp = (CachedField)mp;
                mbp = new MetaBeanProperty(propName, mfp.getType(), null, null);
            }
            if (isGetter && mbp.getGetter() == null) {
                mbp.setGetter(propertyMethod);
            }
            else if (!isGetter && mbp.getSetter() == null) {
                mbp.setSetter(propertyMethod);
            }
            mbp.setField(mfp);
            mp = mbp;
        }
        propertyIndex.put(propName, mp);
    }
    
    protected void applyPropertyDescriptors(final PropertyDescriptor[] propertyDescriptors) {
        for (final PropertyDescriptor pd : propertyDescriptors) {
            if (pd.getPropertyType() != null) {
                Method method = pd.getReadMethod();
                MetaMethod getter;
                if (method != null) {
                    final CachedMethod cachedGetter = CachedMethod.find(method);
                    getter = ((cachedGetter == null) ? null : this.findMethod(cachedGetter));
                }
                else {
                    getter = null;
                }
                method = pd.getWriteMethod();
                MetaMethod setter;
                if (method != null) {
                    final CachedMethod cachedSetter = CachedMethod.find(method);
                    setter = ((cachedSetter == null) ? null : this.findMethod(cachedSetter));
                }
                else {
                    setter = null;
                }
                final MetaBeanProperty mp = new MetaBeanProperty(pd.getName(), pd.getPropertyType(), getter, setter);
                this.addMetaBeanProperty(mp);
            }
        }
    }
    
    public void addMetaBeanProperty(final MetaBeanProperty mp) {
        final MetaProperty staticProperty = this.establishStaticMetaProperty(mp);
        if (staticProperty != null) {
            this.staticPropertyIndex.put(mp.getName(), mp);
        }
        else {
            final SingleKeyHashMap propertyMap = this.classPropertyIndex.getNotNull(this.theCachedClass);
            final MetaProperty old = (MetaProperty)propertyMap.get(mp.getName());
            if (old != null) {
                CachedField field;
                if (old instanceof MetaBeanProperty) {
                    field = ((MetaBeanProperty)old).getField();
                }
                else {
                    field = (CachedField)old;
                }
                mp.setField(field);
            }
            propertyMap.put(mp.getName(), mp);
        }
    }
    
    public void setProperty(final Class sender, final Object object, final String name, Object newValue, final boolean useSuper, final boolean fromInsideClass) {
        this.checkInitalised();
        final boolean isStatic = this.theClass != Class.class && object instanceof Class;
        if (isStatic && object != this.theClass) {
            final MetaClass mc = this.registry.getMetaClass((Class)object);
            mc.getProperty(sender, object, name, useSuper, fromInsideClass);
            return;
        }
        if (newValue instanceof Wrapper) {
            newValue = ((Wrapper)newValue).unwrap();
        }
        MetaMethod method = null;
        Object[] arguments = null;
        final MetaProperty mp = this.getMetaProperty(sender, name, useSuper, isStatic);
        MetaProperty field = null;
        if (mp != null) {
            if (mp instanceof MetaBeanProperty) {
                final MetaBeanProperty mbp = (MetaBeanProperty)mp;
                method = mbp.getSetter();
                final MetaProperty f = mbp.getField();
                if (method != null || (f != null && !Modifier.isFinal(f.getModifiers()))) {
                    arguments = new Object[] { newValue };
                    field = f;
                }
            }
            else {
                field = mp;
            }
        }
        if (!useSuper && !isStatic && GroovyCategorySupport.hasCategoryInCurrentThread() && name.length() > 0) {
            final String getterName = GroovyCategorySupport.getPropertyCategorySetterName(name);
            if (getterName != null) {
                final MetaMethod categoryMethod = this.getCategoryMethodSetter(sender, getterName, false);
                if (categoryMethod != null) {
                    method = categoryMethod;
                    arguments = new Object[] { newValue };
                }
            }
        }
        boolean ambiguousListener = false;
        if (method == null) {
            method = this.listeners.get(name);
            ambiguousListener = (method == MetaClassImpl.AMBIGUOUS_LISTENER_METHOD);
            if (method != null && !ambiguousListener && newValue instanceof Closure) {
                final Object proxy = Proxy.newProxyInstance(this.theClass.getClassLoader(), new Class[] { method.getParameterTypes()[0].getTheClass() }, new ConvertedClosure((Closure)newValue, name));
                arguments = new Object[] { proxy };
                newValue = proxy;
            }
            else {
                method = null;
            }
        }
        if (method == null && field != null) {
            if (Modifier.isFinal(field.getModifiers())) {
                throw new ReadOnlyPropertyException(name, this.theClass);
            }
            if (!this.isMap || !this.isPrivateOrPkgPrivate(field.getModifiers())) {
                field.setProperty(object, newValue);
                return;
            }
        }
        if (method == null && !useSuper && !isStatic && GroovyCategorySupport.hasCategoryInCurrentThread()) {
            method = this.getCategoryMethodSetter(sender, "set", true);
            if (method != null) {
                arguments = new Object[] { name, newValue };
            }
        }
        if (method == null && this.genericSetMethod != null && (this.genericSetMethod.isStatic() || !isStatic)) {
            arguments = new Object[] { name, newValue };
            method = this.genericSetMethod;
        }
        if (method != null) {
            if (arguments.length == 1) {
                newValue = DefaultTypeTransformation.castToType(newValue, method.getParameterTypes()[0].getTheClass());
                arguments[0] = newValue;
            }
            else {
                newValue = DefaultTypeTransformation.castToType(newValue, method.getParameterTypes()[1].getTheClass());
                arguments[1] = newValue;
            }
            method.doMethodInvoke(object, arguments);
            return;
        }
        if (!isStatic && this.isMap) {
            ((Map)object).put(name, newValue);
            return;
        }
        if (ambiguousListener) {
            throw new GroovyRuntimeException("There are multiple listeners for the property " + name + ". Please do not use the bean short form to access this listener.");
        }
        if (mp != null) {
            throw new ReadOnlyPropertyException(name, this.theClass);
        }
        this.invokeMissingProperty(object, name, newValue, false);
    }
    
    private boolean isPrivateOrPkgPrivate(final int mod) {
        return !Modifier.isProtected(mod) && !Modifier.isPublic(mod);
    }
    
    private MetaProperty getMetaProperty(final Class _clazz, final String name, final boolean useSuper, final boolean useStatic) {
        if (_clazz == this.theClass) {
            return this.getMetaProperty(name, useStatic);
        }
        CachedClass clazz = ReflectionCache.getCachedClass(_clazz);
        while (true) {
            SingleKeyHashMap propertyMap;
            if (useStatic) {
                propertyMap = this.staticPropertyIndex;
            }
            else if (useSuper) {
                propertyMap = this.classPropertyIndexForSuper.getNullable(clazz);
            }
            else {
                propertyMap = this.classPropertyIndex.getNullable(clazz);
            }
            if (propertyMap != null) {
                return (MetaProperty)propertyMap.get(name);
            }
            if (clazz == this.theCachedClass) {
                return null;
            }
            clazz = this.theCachedClass;
        }
    }
    
    private MetaProperty getMetaProperty(final String name, final boolean useStatic) {
        final CachedClass clazz = this.theCachedClass;
        SingleKeyHashMap propertyMap;
        if (useStatic) {
            propertyMap = this.staticPropertyIndex;
        }
        else {
            propertyMap = this.classPropertyIndex.getNullable(clazz);
        }
        if (propertyMap == null) {
            return null;
        }
        return (MetaProperty)propertyMap.get(name);
    }
    
    public Object getAttribute(final Class sender, final Object receiver, final String messageName, final boolean useSuper) {
        return this.getAttribute(receiver, messageName);
    }
    
    public Object getAttribute(final Class sender, final Object object, final String attribute, final boolean useSuper, final boolean fromInsideClass) {
        this.checkInitalised();
        final boolean isStatic = this.theClass != Class.class && object instanceof Class;
        if (isStatic && object != this.theClass) {
            final MetaClass mc = this.registry.getMetaClass((Class)object);
            return mc.getAttribute(sender, object, attribute, useSuper);
        }
        MetaProperty mp = this.getMetaProperty(sender, attribute, useSuper, isStatic);
        if (mp != null) {
            if (mp instanceof MetaBeanProperty) {
                final MetaBeanProperty mbp = (MetaBeanProperty)mp;
                mp = mbp.getField();
            }
            try {
                if (mp != null) {
                    return mp.getProperty(object);
                }
            }
            catch (Exception e) {
                throw new GroovyRuntimeException("Cannot read field: " + attribute, e);
            }
        }
        throw new MissingFieldException(attribute, this.theClass);
    }
    
    public void setAttribute(final Class sender, final Object object, final String attribute, final Object newValue, final boolean useSuper, final boolean fromInsideClass) {
        this.checkInitalised();
        final boolean isStatic = this.theClass != Class.class && object instanceof Class;
        if (isStatic && object != this.theClass) {
            final MetaClass mc = this.registry.getMetaClass((Class)object);
            mc.setAttribute(sender, object, attribute, newValue, useSuper, fromInsideClass);
            return;
        }
        MetaProperty mp = this.getMetaProperty(sender, attribute, useSuper, isStatic);
        if (mp != null) {
            if (mp instanceof MetaBeanProperty) {
                final MetaBeanProperty mbp = (MetaBeanProperty)mp;
                mp = mbp.getField();
            }
            if (mp != null) {
                mp.setProperty(object, newValue);
                return;
            }
        }
        throw new MissingFieldException(attribute, this.theClass);
    }
    
    public ClassNode getClassNode() {
        if (this.classNode == null && GroovyObject.class.isAssignableFrom(this.theClass)) {
            String groovyFile = this.theClass.getName();
            final int idx = groovyFile.indexOf(36);
            if (idx > 0) {
                groovyFile = groovyFile.substring(0, idx);
            }
            groovyFile = groovyFile.replace('.', '/') + ".groovy";
            URL url = this.theClass.getClassLoader().getResource(groovyFile);
            if (url == null) {
                url = Thread.currentThread().getContextClassLoader().getResource(groovyFile);
            }
            if (url != null) {
                try {
                    final CompilationUnit.ClassgenCallback search = new CompilationUnit.ClassgenCallback() {
                        @Override
                        public void call(final ClassVisitor writer, final ClassNode node) {
                            if (node.getName().equals(MetaClassImpl.this.theClass.getName())) {
                                MetaClassImpl.this.classNode = node;
                            }
                        }
                    };
                    final ClassLoader parent = this.theClass.getClassLoader();
                    final GroovyClassLoader gcl = AccessController.doPrivileged((PrivilegedAction<GroovyClassLoader>)new PrivilegedAction() {
                        public Object run() {
                            return new GroovyClassLoader(parent);
                        }
                    });
                    final CompilationUnit unit = new CompilationUnit();
                    unit.setClassgenCallback(search);
                    unit.addSource(url);
                    unit.compile(7);
                }
                catch (Exception e) {
                    throw new GroovyRuntimeException("Exception thrown parsing: " + groovyFile + ". Reason: " + e, e);
                }
            }
        }
        return this.classNode;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[" + this.theClass + "]";
    }
    
    public void addMetaMethod(final MetaMethod method) {
        if (this.isInitialized()) {
            throw new RuntimeException("Already initialized, cannot add new method: " + method);
        }
        final CachedClass declaringClass = method.getDeclaringClass();
        this.addMetaMethodToIndex(method, this.metaMethodIndex.getHeader(declaringClass.getTheClass()));
    }
    
    protected void addMetaMethodToIndex(final MetaMethod method, final MetaMethodIndex.Header header) {
        this.checkIfStdMethod(method);
        final String name = method.getName();
        final MetaMethodIndex.Entry e = this.metaMethodIndex.getOrPutMethods(name, header);
        if (method.isStatic()) {
            e.staticMethods = this.metaMethodIndex.addMethodToList(e.staticMethods, method);
        }
        e.methods = this.metaMethodIndex.addMethodToList(e.methods, method);
    }
    
    protected final void checkIfGroovyObjectMethod(final MetaMethod metaMethod) {
        if (metaMethod instanceof ClosureMetaMethod || metaMethod instanceof MixinInstanceMetaMethod) {
            if (this.isGetPropertyMethod(metaMethod)) {
                this.getPropertyMethod = metaMethod;
            }
            else if (this.isInvokeMethod(metaMethod)) {
                this.invokeMethodMethod = metaMethod;
            }
            else if (this.isSetPropertyMethod(metaMethod)) {
                this.setPropertyMethod = metaMethod;
            }
        }
    }
    
    private boolean isSetPropertyMethod(final MetaMethod metaMethod) {
        return "setProperty".equals(metaMethod.getName()) && metaMethod.getParameterTypes().length == 2;
    }
    
    private boolean isGetPropertyMethod(final MetaMethod metaMethod) {
        return "getProperty".equals(metaMethod.getName());
    }
    
    private boolean isInvokeMethod(final MetaMethod metaMethod) {
        return "invokeMethod".equals(metaMethod.getName()) && metaMethod.getParameterTypes().length == 2;
    }
    
    private void checkIfStdMethod(final MetaMethod method) {
        this.checkIfGroovyObjectMethod(method);
        if (this.isGenericGetMethod(method) && this.genericGetMethod == null) {
            this.genericGetMethod = method;
        }
        else if (MetaClassHelper.isGenericSetMethod(method) && this.genericSetMethod == null) {
            this.genericSetMethod = method;
        }
        if (method.getName().equals("propertyMissing")) {
            final CachedClass[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1) {
                this.propertyMissingGet = method;
            }
        }
        if (this.propertyMissingSet == null && method.getName().equals("propertyMissing")) {
            final CachedClass[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 2) {
                this.propertyMissingSet = method;
            }
        }
        if (method.getName().equals("methodMissing")) {
            final CachedClass[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 2 && parameterTypes[0].getTheClass() == String.class && parameterTypes[1].getTheClass() == Object.class) {
                this.methodMissing = method;
            }
        }
        if (this.theCachedClass.isNumber) {
            NumberMathModificationInfo.instance.checkIfStdMethod(method);
        }
    }
    
    protected boolean isInitialized() {
        return this.initialized;
    }
    
    private Boolean getMatchKindForCategory(final MetaMethod aMethod, final MetaMethod categoryMethod) {
        final CachedClass[] params1 = aMethod.getParameterTypes();
        final CachedClass[] params2 = categoryMethod.getParameterTypes();
        if (params1.length != params2.length) {
            return Boolean.FALSE;
        }
        for (int i = 0; i < params1.length; ++i) {
            if (params1[i] != params2[i]) {
                return Boolean.FALSE;
            }
        }
        final Class aMethodClass = aMethod.getDeclaringClass().getTheClass();
        final Class categoryMethodClass = categoryMethod.getDeclaringClass().getTheClass();
        if (aMethodClass == categoryMethodClass) {
            return Boolean.TRUE;
        }
        final boolean match = aMethodClass.isAssignableFrom(categoryMethodClass);
        if (match) {
            return Boolean.TRUE;
        }
        return null;
    }
    
    private void filterMatchingMethodForCategory(final FastArray list, final MetaMethod method) {
        final int len = list.size();
        if (len == 0) {
            list.add(method);
            return;
        }
        final Object[] data = list.getArray();
        for (int j = 0; j != len; ++j) {
            final MetaMethod aMethod = (MetaMethod)data[j];
            final Boolean match = this.getMatchKindForCategory(aMethod, method);
            if (match == Boolean.TRUE) {
                list.set(j, method);
                return;
            }
            if (match == null) {
                return;
            }
        }
        list.add(method);
    }
    
    private int findMatchingMethod(final CachedMethod[] data, final int from, final int to, final MetaMethod method) {
        for (int j = from; j <= to; ++j) {
            final CachedMethod aMethod = data[j];
            final CachedClass[] params1 = aMethod.getParameterTypes();
            final CachedClass[] params2 = method.getParameterTypes();
            if (params1.length == params2.length) {
                boolean matches = true;
                for (int i = 0; i < params1.length; ++i) {
                    if (params1[i] != params2[i]) {
                        matches = false;
                        break;
                    }
                }
                if (matches) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    private MetaMethod findMethod(final CachedMethod aMethod) {
        final Object methods = this.getMethods(this.theClass, aMethod.getName(), false);
        if (methods instanceof FastArray) {
            final FastArray m = (FastArray)methods;
            final int len = m.size;
            final Object[] data = m.getArray();
            for (int i = 0; i != len; ++i) {
                final MetaMethod method = (MetaMethod)data[i];
                if (method.isMethod(aMethod)) {
                    return method;
                }
            }
        }
        else {
            final MetaMethod method2 = (MetaMethod)methods;
            if (method2.getName().equals(aMethod.getName()) && method2.getReturnType().equals(aMethod.getReturnType()) && MetaMethod.equal(method2.getParameterTypes(), aMethod.getParameterTypes())) {
                return method2;
            }
        }
        synchronized (aMethod.cachedClass) {
            return aMethod;
        }
    }
    
    protected Object chooseMethod(final String methodName, final Object methodOrList, final Class[] arguments) {
        final Object method = this.chooseMethodInternal(methodName, methodOrList, arguments);
        if (method instanceof GeneratedMetaMethod.Proxy) {
            return ((GeneratedMetaMethod.Proxy)method).proxy();
        }
        return method;
    }
    
    private Object chooseMethodInternal(final String methodName, final Object methodOrList, final Class[] arguments) {
        if (methodOrList instanceof MetaMethod) {
            if (((ParameterTypes)methodOrList).isValidMethod(arguments)) {
                return methodOrList;
            }
            return null;
        }
        else {
            final FastArray methods = (FastArray)methodOrList;
            if (methods == null) {
                return null;
            }
            final int methodCount = methods.size();
            if (methodCount <= 0) {
                return null;
            }
            if (methodCount == 1) {
                final Object method = methods.get(0);
                if (((ParameterTypes)method).isValidMethod(arguments)) {
                    return method;
                }
                return null;
            }
            else {
                Object answer;
                if (arguments == null || arguments.length == 0) {
                    answer = MetaClassHelper.chooseEmptyMethodParams(methods);
                }
                else if (arguments.length == 1 && arguments[0] == null) {
                    answer = MetaClassHelper.chooseMostGeneralMethodWith1NullParam(methods);
                }
                else {
                    Object matchingMethods = null;
                    final int len = methods.size;
                    final Object[] data = methods.getArray();
                    for (int i = 0; i != len; ++i) {
                        final Object method2 = data[i];
                        if (((ParameterTypes)method2).isValidMethod(arguments)) {
                            if (matchingMethods == null) {
                                matchingMethods = method2;
                            }
                            else if (matchingMethods instanceof ArrayList) {
                                ((ArrayList)matchingMethods).add(method2);
                            }
                            else {
                                final ArrayList arr = new ArrayList(4);
                                arr.add(matchingMethods);
                                arr.add(method2);
                                matchingMethods = arr;
                            }
                        }
                    }
                    if (matchingMethods == null) {
                        return null;
                    }
                    if (!(matchingMethods instanceof ArrayList)) {
                        return matchingMethods;
                    }
                    return this.chooseMostSpecificParams(methodName, (List)matchingMethods, arguments);
                }
                if (answer != null) {
                    return answer;
                }
                throw new MethodSelectionException(methodName, methods, arguments);
            }
        }
    }
    
    private Object chooseMostSpecificParams(final String name, final List matchingMethods, final Class[] arguments) {
        long matchesDistance = -1L;
        final LinkedList matches = new LinkedList();
        for (final Object method : matchingMethods) {
            final ParameterTypes paramTypes = (ParameterTypes)method;
            final long dist = MetaClassHelper.calculateParameterDistance(arguments, paramTypes);
            if (dist == 0L) {
                return method;
            }
            if (matches.size() == 0) {
                matches.add(method);
                matchesDistance = dist;
            }
            else if (dist < matchesDistance) {
                matchesDistance = dist;
                matches.clear();
                matches.add(method);
            }
            else {
                if (dist != matchesDistance) {
                    continue;
                }
                matches.add(method);
            }
        }
        if (matches.size() == 1) {
            return matches.getFirst();
        }
        if (matches.size() == 0) {
            return null;
        }
        String msg = "Ambiguous method overloading for method ";
        msg = msg + this.theClass.getName() + "#" + name;
        msg += ".\nCannot resolve which method to invoke for ";
        msg += InvokerHelper.toString(arguments);
        msg += " due to overlapping prototypes between:";
        final Iterator iter2 = matches.iterator();
        while (iter2.hasNext()) {
            final Class[] types = iter2.next().getNativeParameterTypes();
            msg = msg + "\n\t" + InvokerHelper.toString(types);
        }
        throw new GroovyRuntimeException(msg);
    }
    
    private boolean isGenericGetMethod(final MetaMethod method) {
        if (method.getName().equals("get")) {
            final CachedClass[] parameterTypes = method.getParameterTypes();
            return parameterTypes.length == 1 && parameterTypes[0].getTheClass() == String.class;
        }
        return false;
    }
    
    public synchronized void initialize() {
        if (!this.isInitialized()) {
            this.fillMethodIndex();
            this.addProperties();
            this.initialized = true;
        }
    }
    
    private void addProperties() {
        BeanInfo info;
        try {
            if (this.isBeanDerivative(this.theClass)) {
                info = AccessController.doPrivileged((PrivilegedExceptionAction<BeanInfo>)new PrivilegedExceptionAction() {
                    public Object run() throws IntrospectionException {
                        return Introspector.getBeanInfo(MetaClassImpl.this.theClass, 3);
                    }
                });
            }
            else {
                info = AccessController.doPrivileged((PrivilegedExceptionAction<BeanInfo>)new PrivilegedExceptionAction() {
                    public Object run() throws IntrospectionException {
                        return Introspector.getBeanInfo(MetaClassImpl.this.theClass);
                    }
                });
            }
        }
        catch (PrivilegedActionException pae) {
            throw new GroovyRuntimeException("exception during bean introspection", pae.getException());
        }
        final PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
        this.setupProperties(descriptors);
        final EventSetDescriptor[] arr$;
        final EventSetDescriptor[] eventDescriptors = arr$ = info.getEventSetDescriptors();
        for (final EventSetDescriptor descriptor : arr$) {
            final Method[] arr$2;
            final Method[] listenerMethods = arr$2 = descriptor.getListenerMethods();
            for (final Method listenerMethod : arr$2) {
                final MetaMethod metaMethod = CachedMethod.find(descriptor.getAddListenerMethod());
                this.addToAllMethodsIfPublic(metaMethod);
                final String name = listenerMethod.getName();
                if (this.listeners.containsKey(name)) {
                    this.listeners.put(name, MetaClassImpl.AMBIGUOUS_LISTENER_METHOD);
                }
                else {
                    this.listeners.put(name, metaMethod);
                }
            }
        }
    }
    
    private boolean isBeanDerivative(final Class theClass) {
        for (Class next = theClass; next != null; next = next.getSuperclass()) {
            if (Arrays.asList(next.getInterfaces()).contains(BeanInfo.class)) {
                return true;
            }
        }
        return false;
    }
    
    private void addToAllMethodsIfPublic(final MetaMethod metaMethod) {
        if (Modifier.isPublic(metaMethod.getModifiers())) {
            this.allMethods.add(metaMethod);
        }
    }
    
    public List<MetaMethod> getMethods() {
        return this.allMethods;
    }
    
    public List<MetaMethod> getMetaMethods() {
        return new ArrayList<MetaMethod>(this.newGroovyMethodsSet);
    }
    
    protected void dropStaticMethodCache(final String name) {
        this.metaMethodIndex.clearCaches(name);
    }
    
    protected void dropMethodCache(final String name) {
        this.metaMethodIndex.clearCaches(name);
    }
    
    public CallSite createPojoCallSite(final CallSite site, final Object receiver, final Object[] args) {
        if (!(this instanceof AdaptingMetaClass)) {
            final Class[] params = MetaClassHelper.convertToTypeArray(args);
            final MetaMethod metaMethod = this.getMethodWithCachingInternal(this.getTheClass(), site, params);
            if (metaMethod != null) {
                return PojoMetaMethodSite.createPojoMetaMethodSite(site, this, metaMethod, params, receiver, args);
            }
        }
        return new PojoMetaClassSite(site, this);
    }
    
    public CallSite createStaticSite(final CallSite site, final Object[] args) {
        if (!(this instanceof AdaptingMetaClass)) {
            final Class[] params = MetaClassHelper.convertToTypeArray(args);
            final MetaMethod metaMethod = this.retrieveStaticMethod(site.getName(), args);
            if (metaMethod != null) {
                return StaticMetaMethodSite.createStaticMetaMethodSite(site, this, metaMethod, params, args);
            }
        }
        return new StaticMetaClassSite(site, this);
    }
    
    public CallSite createPogoCallSite(final CallSite site, final Object[] args) {
        if (site.getUsage().get() == 0 && !(this instanceof AdaptingMetaClass)) {
            final Class[] params = MetaClassHelper.convertToTypeArray(args);
            final MetaMethod metaMethod = this.getMethodWithCachingInternal(this.theClass, site, params);
            if (metaMethod != null) {
                return PogoMetaMethodSite.createPogoMetaMethodSite(site, this, metaMethod, params, args);
            }
        }
        return new PogoMetaClassSite(site, this);
    }
    
    public CallSite createPogoCallCurrentSite(final CallSite site, final Class sender, final Object[] args) {
        if (site.getUsage().get() == 0 && !(this instanceof AdaptingMetaClass)) {
            final Class[] params = MetaClassHelper.convertToTypeArray(args);
            final MetaMethod metaMethod = this.getMethodWithCachingInternal(sender, site, params);
            if (metaMethod != null) {
                return PogoMetaMethodSite.createPogoMetaMethodSite(site, this, metaMethod, params, args);
            }
        }
        return new PogoMetaClassSite(site, this);
    }
    
    public CallSite createConstructorSite(final CallSite site, final Object[] args) {
        if (!(this instanceof AdaptingMetaClass)) {
            final Class[] params = MetaClassHelper.convertToTypeArray(args);
            CachedConstructor constructor = (CachedConstructor)this.chooseMethod("<init>", this.constructors, params);
            if (constructor != null) {
                return ConstructorSite.createConstructorSite(site, this, constructor, params, args);
            }
            if (args.length == 1 && args[0] instanceof Map) {
                constructor = (CachedConstructor)this.chooseMethod("<init>", this.constructors, MetaClassHelper.EMPTY_TYPE_ARRAY);
                if (constructor != null) {
                    return new ConstructorSite.NoParamSite(site, this, constructor, params);
                }
            }
        }
        return new MetaClassConstructorSite(site, this);
    }
    
    public ClassInfo getClassInfo() {
        return this.theCachedClass.classInfo;
    }
    
    public int getVersion() {
        return this.theCachedClass.classInfo.getVersion();
    }
    
    public void incVersion() {
        this.theCachedClass.classInfo.incVersion();
    }
    
    public MetaMethod[] getAdditionalMetaMethods() {
        return this.additionalMetaMethods;
    }
    
    protected MetaBeanProperty findPropertyInClassHierarchy(final String propertyName, final CachedClass theClass) {
        MetaBeanProperty property = null;
        if (theClass == null) {
            return null;
        }
        final CachedClass superClass = theClass.getCachedSuperClass();
        if (superClass == null) {
            return null;
        }
        final MetaClass metaClass = this.registry.getMetaClass(superClass.getTheClass());
        if (metaClass instanceof MutableMetaClass) {
            property = this.getMetaPropertyFromMutableMetaClass(propertyName, metaClass);
            if (property == null) {
                if (superClass != ReflectionCache.OBJECT_CLASS) {
                    property = this.findPropertyInClassHierarchy(propertyName, superClass);
                }
                if (property == null) {
                    final Class[] interfaces = theClass.getTheClass().getInterfaces();
                    property = this.searchInterfacesForMetaProperty(propertyName, interfaces);
                }
            }
        }
        return property;
    }
    
    private MetaBeanProperty searchInterfacesForMetaProperty(final String propertyName, final Class[] interfaces) {
        MetaBeanProperty property = null;
        for (final Class anInterface : interfaces) {
            final MetaClass metaClass = this.registry.getMetaClass(anInterface);
            if (metaClass instanceof MutableMetaClass) {
                property = this.getMetaPropertyFromMutableMetaClass(propertyName, metaClass);
                if (property != null) {
                    break;
                }
            }
            final Class[] superInterfaces = anInterface.getInterfaces();
            if (superInterfaces.length > 0) {
                property = this.searchInterfacesForMetaProperty(propertyName, superInterfaces);
                if (property != null) {
                    break;
                }
            }
        }
        return property;
    }
    
    private MetaBeanProperty getMetaPropertyFromMutableMetaClass(final String propertyName, final MetaClass metaClass) {
        final boolean isModified = ((MutableMetaClass)metaClass).isModified();
        if (isModified) {
            final MetaProperty metaProperty = metaClass.getMetaProperty(propertyName);
            if (metaProperty instanceof MetaBeanProperty) {
                return (MetaBeanProperty)metaProperty;
            }
        }
        return null;
    }
    
    protected MetaMethod findMixinMethod(final String methodName, final Class[] arguments) {
        return null;
    }
    
    protected static MetaMethod findMethodInClassHierarchy(final Class instanceKlazz, final String methodName, final Class[] arguments, final MetaClass metaClass) {
        if (metaClass instanceof MetaClassImpl) {
            boolean check = false;
            for (final ClassInfo ci : ((MetaClassImpl)metaClass).theCachedClass.getHierarchy()) {
                final MetaClass aClass = ci.getStrongMetaClass();
                if (aClass instanceof MutableMetaClass && ((MutableMetaClass)aClass).isModified()) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                return null;
            }
        }
        MetaMethod method = null;
        Class superClass;
        if (metaClass.getTheClass().isArray() && !metaClass.getTheClass().getComponentType().isPrimitive() && metaClass.getTheClass().getComponentType() != Object.class) {
            superClass = Object[].class;
        }
        else {
            superClass = metaClass.getTheClass().getSuperclass();
        }
        if (superClass != null) {
            final MetaClass superMetaClass = GroovySystem.getMetaClassRegistry().getMetaClass(superClass);
            method = findMethodInClassHierarchy(instanceKlazz, methodName, arguments, superMetaClass);
        }
        else if (metaClass.getTheClass().isInterface()) {
            final MetaClass superMetaClass = GroovySystem.getMetaClassRegistry().getMetaClass(Object.class);
            method = findMethodInClassHierarchy(instanceKlazz, methodName, arguments, superMetaClass);
        }
        method = findSubClassMethod(instanceKlazz, methodName, arguments, metaClass, method);
        final MetaMethod infMethod = searchInterfacesForMetaMethod(instanceKlazz, methodName, arguments, metaClass);
        if (infMethod != null) {
            if (method == null) {
                method = infMethod;
            }
            else {
                method = mostSpecific(method, infMethod, instanceKlazz);
            }
        }
        method = findOwnMethod(instanceKlazz, methodName, arguments, metaClass, method);
        return method;
    }
    
    private static MetaMethod findSubClassMethod(final Class instanceKlazz, final String methodName, final Class[] arguments, final MetaClass metaClass, MetaMethod method) {
        if (metaClass instanceof MetaClassImpl) {
            final Object list = ((MetaClassImpl)metaClass).getSubclassMetaMethods(methodName);
            if (list != null) {
                if (list instanceof MetaMethod) {
                    final MetaMethod m = (MetaMethod)list;
                    if (m.getDeclaringClass().getTheClass().isAssignableFrom(instanceKlazz) && m.isValidExactMethod(arguments)) {
                        if (method == null) {
                            method = m;
                        }
                        else {
                            method = mostSpecific(method, m, instanceKlazz);
                        }
                    }
                }
                else {
                    final FastArray arr = (FastArray)list;
                    for (int i = 0; i != arr.size(); ++i) {
                        final MetaMethod j = (MetaMethod)arr.get(i);
                        if (j.getDeclaringClass().getTheClass().isAssignableFrom(instanceKlazz) && j.isValidExactMethod(arguments)) {
                            if (method == null) {
                                method = j;
                            }
                            else {
                                method = mostSpecific(method, j, instanceKlazz);
                            }
                        }
                    }
                }
            }
        }
        return method;
    }
    
    private static MetaMethod mostSpecific(final MetaMethod method, final MetaMethod newMethod, final Class instanceKlazz) {
        final Class newMethodC = newMethod.getDeclaringClass().getTheClass();
        final Class methodC = method.getDeclaringClass().getTheClass();
        if (!newMethodC.isAssignableFrom(instanceKlazz)) {
            return method;
        }
        if (newMethodC == methodC) {
            return newMethod;
        }
        if (newMethodC.isAssignableFrom(methodC)) {
            return method;
        }
        if (methodC.isAssignableFrom(newMethodC)) {
            return newMethod;
        }
        return newMethod;
    }
    
    private static MetaMethod searchInterfacesForMetaMethod(final Class instanceKlazz, final String methodName, final Class[] arguments, final MetaClass metaClass) {
        final Class[] interfaces = metaClass.getTheClass().getInterfaces();
        MetaMethod method = null;
        for (final Class anInterface : interfaces) {
            final MetaClass infMetaClass = GroovySystem.getMetaClassRegistry().getMetaClass(anInterface);
            final MetaMethod infMethod = searchInterfacesForMetaMethod(instanceKlazz, methodName, arguments, infMetaClass);
            if (infMethod != null) {
                if (method == null) {
                    method = infMethod;
                }
                else {
                    method = mostSpecific(method, infMethod, instanceKlazz);
                }
            }
        }
        method = findSubClassMethod(instanceKlazz, methodName, arguments, metaClass, method);
        method = findOwnMethod(instanceKlazz, methodName, arguments, metaClass, method);
        return method;
    }
    
    protected static MetaMethod findOwnMethod(final Class instanceKlazz, final String methodName, final Class[] arguments, final MetaClass metaClass, MetaMethod method) {
        if (instanceKlazz == metaClass.getTheClass()) {
            return method;
        }
        final MetaMethod ownMethod = metaClass.pickMethod(methodName, arguments);
        if (ownMethod != null) {
            if (method == null) {
                method = ownMethod;
            }
            else {
                method = mostSpecific(method, ownMethod, instanceKlazz);
            }
        }
        return method;
    }
    
    protected Object getSubclassMetaMethods(final String methodName) {
        return null;
    }
    
    public Object getProperty(final Object object, final String property) {
        return this.getProperty(this.theClass, object, property, false, false);
    }
    
    public void setProperty(final Object object, final String property, final Object newValue) {
        this.setProperty(this.theClass, object, property, newValue, false, false);
    }
    
    public Object getAttribute(final Object object, final String attribute) {
        return this.getAttribute(this.theClass, object, attribute, false, false);
    }
    
    public void setAttribute(final Object object, final String attribute, final Object newValue) {
        this.setAttribute(this.theClass, object, attribute, newValue, false, false);
    }
    
    public MetaMethod pickMethod(final String methodName, final Class[] arguments) {
        return this.getMethodWithoutCaching(this.theClass, methodName, arguments, false);
    }
    
    @Deprecated
    protected MetaMethod retrieveMethod(final String methodName, final Class[] arguments) {
        return this.pickMethod(methodName, arguments);
    }
    
    protected void clearInvocationCaches() {
        this.metaMethodIndex.clearCaches();
    }
    
    static {
        METHOD_MISSING_ARGS = new Class[] { String.class, Object.class };
        GETTER_MISSING_ARGS = new Class[] { String.class };
        SETTER_MISSING_ARGS = MetaClassImpl.METHOD_MISSING_ARGS;
        EMPTY = new MetaMethod[0];
        AMBIGUOUS_LISTENER_METHOD = new DummyMetaMethod();
        EMPTY_ARGUMENTS = new Object[0];
        propNames = new HashMap<String, String>(1024);
        NAME_INDEX_COPIER = new SingleKeyHashMap.Copier() {
            public Object copy(final Object value) {
                if (value instanceof FastArray) {
                    return ((FastArray)value).copy();
                }
                return value;
            }
        };
        METHOD_INDEX_COPIER = new SingleKeyHashMap.Copier() {
            public Object copy(final Object value) {
                return SingleKeyHashMap.copy(new SingleKeyHashMap(false), (SingleKeyHashMap)value, MetaClassImpl.NAME_INDEX_COPIER);
            }
        };
    }
    
    private abstract class MethodIndexAction
    {
        public void iterate() {
            final ComplexKeyHashMap.Entry[] table = MetaClassImpl.this.metaMethodIndex.methodHeaders.getTable();
            for (int len = table.length, i = 0; i != len; ++i) {
                for (SingleKeyHashMap.Entry classEntry = (SingleKeyHashMap.Entry)table[i]; classEntry != null; classEntry = (SingleKeyHashMap.Entry)classEntry.next) {
                    final Class clazz = (Class)classEntry.getKey();
                    if (!this.skipClass(clazz)) {
                        final MetaMethodIndex.Header header = (MetaMethodIndex.Header)classEntry.getValue();
                        for (MetaMethodIndex.Entry nameEntry = header.head; nameEntry != null; nameEntry = nameEntry.nextClassEntry) {
                            this.methodNameAction(clazz, nameEntry);
                        }
                    }
                }
            }
        }
        
        public abstract void methodNameAction(final Class p0, final MetaMethodIndex.Entry p1);
        
        public boolean skipClass(final Class clazz) {
            return false;
        }
    }
    
    class MethodIndex extends Index
    {
        public MethodIndex(final boolean b) {
            super(false);
        }
        
        public MethodIndex(final int size) {
            super(size);
        }
        
        public MethodIndex() {
        }
        
        MethodIndex copy() {
            return (MethodIndex)SingleKeyHashMap.copy(new MethodIndex(false), this, MetaClassImpl.METHOD_INDEX_COPIER);
        }
        
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
    
    public static class Index extends SingleKeyHashMap
    {
        public Index(final int size) {
        }
        
        public Index() {
        }
        
        public Index(final boolean size) {
            super(false);
        }
        
        public SingleKeyHashMap getNotNull(final CachedClass key) {
            final Entry res = this.getOrPut(key);
            if (res.value == null) {
                res.value = new SingleKeyHashMap();
            }
            return (SingleKeyHashMap)res.value;
        }
        
        public void put(final CachedClass key, final SingleKeyHashMap value) {
            this.getOrPut(key).value = value;
        }
        
        public SingleKeyHashMap getNullable(final CachedClass clazz) {
            return (SingleKeyHashMap)this.get(clazz);
        }
        
        public boolean checkEquals(final ComplexKeyHashMap.Entry e, final Object key) {
            return ((Entry)e).key.equals(key);
        }
    }
    
    private static class DummyMetaMethod extends MetaMethod
    {
        @Override
        public int getModifiers() {
            return 0;
        }
        
        @Override
        public String getName() {
            return null;
        }
        
        @Override
        public Class getReturnType() {
            return null;
        }
        
        @Override
        public CachedClass getDeclaringClass() {
            return null;
        }
        
        public ParameterTypes getParamTypes() {
            return null;
        }
        
        @Override
        public Object invoke(final Object object, final Object[] arguments) {
            return null;
        }
    }
    
    private static class GetMethodMetaProperty extends MetaProperty
    {
        private final MetaMethod theMethod;
        
        public GetMethodMetaProperty(final String name, final MetaMethod theMethod) {
            super(name, Object.class);
            this.theMethod = theMethod;
        }
        
        @Override
        public Object getProperty(final Object object) {
            return this.theMethod.doMethodInvoke(object, new Object[] { this.name });
        }
        
        @Override
        public void setProperty(final Object object, final Object newValue) {
            throw new UnsupportedOperationException();
        }
    }
    
    private static class GetBeanMethodMetaProperty extends MetaProperty
    {
        private final MetaMethod theMethod;
        
        public GetBeanMethodMetaProperty(final String name, final MetaMethod theMethod) {
            super(name, Object.class);
            this.theMethod = theMethod;
        }
        
        @Override
        public Object getProperty(final Object object) {
            return this.theMethod.doMethodInvoke(object, MetaClassImpl.EMPTY_ARGUMENTS);
        }
        
        @Override
        public void setProperty(final Object object, final Object newValue) {
            throw new UnsupportedOperationException();
        }
    }
}
