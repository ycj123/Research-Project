// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import java.util.Iterator;
import org.codehaus.groovy.reflection.ParameterTypes;
import java.util.ArrayList;
import java.beans.PropertyDescriptor;
import org.codehaus.groovy.runtime.callsite.PogoMetaClassSite;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import groovy.lang.MetaBeanProperty;
import java.util.Collection;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.CachedMethod;
import org.codehaus.groovy.reflection.CachedField;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.MissingPropertyException;
import groovy.lang.ProxyMetaClass;
import java.util.LinkedList;
import groovy.lang.GroovyObject;
import groovy.lang.MissingMethodException;
import java.util.List;
import org.codehaus.groovy.runtime.MetaClassHelper;
import groovy.lang.ExpandoMetaClass;
import groovy.lang.Closure;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.runtime.wrappers.Wrapper;
import groovy.lang.MetaProperty;
import java.util.HashMap;
import groovy.lang.MetaClassRegistry;
import groovy.lang.MetaClass;
import java.util.Map;
import org.codehaus.groovy.util.FastArray;
import groovy.lang.MetaClassImpl;

public final class ClosureMetaClass extends MetaClassImpl
{
    private boolean initialized;
    private final FastArray closureMethods;
    private Map attributes;
    private MethodChooser chooser;
    private volatile boolean attributeInitDone;
    private static final MetaClassImpl CLOSURE_METACLASS;
    private static MetaClassImpl classMetaClass;
    private static final Object[] EMPTY_ARGUMENTS;
    private static final String CLOSURE_CALL_METHOD = "call";
    private static final String CLOSURE_DO_CALL_METHOD = "doCall";
    private static final String CLOSURE_CURRY_METHOD = "curry";
    
    private static synchronized MetaClass getStaticMetaClass() {
        if (ClosureMetaClass.classMetaClass == null) {
            (ClosureMetaClass.classMetaClass = new MetaClassImpl(Class.class)).initialize();
        }
        return ClosureMetaClass.classMetaClass;
    }
    
    public ClosureMetaClass(final MetaClassRegistry registry, final Class theClass) {
        super(registry, theClass);
        this.closureMethods = new FastArray(3);
        this.attributes = new HashMap();
        this.attributeInitDone = false;
    }
    
    @Override
    public MetaProperty getMetaProperty(final String name) {
        return ClosureMetaClass.CLOSURE_METACLASS.getMetaProperty(name);
    }
    
    private void unwrap(final Object[] arguments) {
        for (int i = 0; i != arguments.length; ++i) {
            if (arguments[i] instanceof Wrapper) {
                arguments[i] = ((Wrapper)arguments[i]).unwrap();
            }
        }
    }
    
    private MetaMethod pickClosureMethod(final Class[] argClasses) {
        final Object answer = this.chooser.chooseMethod(argClasses, false);
        return (MetaMethod)answer;
    }
    
    private MetaMethod getDelegateMethod(final Closure closure, final Object delegate, final String methodName, final Class[] argClasses) {
        if (delegate == closure || delegate == null) {
            return null;
        }
        if (delegate instanceof Class) {
            final MetaClass delegateMetaClass = this.registry.getMetaClass((Class)delegate);
            return delegateMetaClass.getStaticMetaMethod(methodName, argClasses);
        }
        final MetaClass delegateMetaClass = this.lookupObjectMetaClass(delegate);
        MetaMethod method = delegateMetaClass.pickMethod(methodName, argClasses);
        if (method != null) {
            return method;
        }
        if (delegateMetaClass instanceof ExpandoMetaClass) {
            method = ((ExpandoMetaClass)delegateMetaClass).findMixinMethod(methodName, argClasses);
            if (method != null) {
                this.onMixinMethodFound(method);
                return method;
            }
        }
        if (delegateMetaClass instanceof MetaClassImpl) {
            method = MetaClassImpl.findMethodInClassHierarchy(this.getTheClass(), methodName, argClasses, this);
            if (method != null) {
                this.onSuperMethodFoundInHierarchy(method);
                return method;
            }
        }
        return method;
    }
    
    @Override
    public Object invokeMethod(final Class sender, final Object object, final String methodName, final Object[] originalArguments, final boolean isCallToSuper, final boolean fromInsideClass) {
        this.checkInitalised();
        if (object == null) {
            throw new NullPointerException("Cannot invoke method: " + methodName + " on null object");
        }
        final Object[] arguments = this.makeArguments(originalArguments, methodName);
        final Class[] argClasses = MetaClassHelper.convertToTypeArray(arguments);
        this.unwrap(arguments);
        final Closure closure = (Closure)object;
        MetaMethod method;
        if ("doCall".equals(methodName) || "call".equals(methodName)) {
            method = this.pickClosureMethod(argClasses);
            if (method == null && arguments.length == 1 && arguments[0] instanceof List) {
                final Object[] newArguments = ((List)arguments[0]).toArray();
                final Class[] newArgClasses = MetaClassHelper.convertToTypeArray(newArguments);
                method = this.pickClosureMethod(newArgClasses);
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
            if (method == null) {
                throw new MissingMethodException(methodName, this.theClass, arguments, false);
            }
        }
        else {
            if ("curry".equals(methodName)) {
                return closure.curry(arguments);
            }
            method = ClosureMetaClass.CLOSURE_METACLASS.pickMethod(methodName, argClasses);
        }
        if (method != null) {
            return method.doMethodInvoke(object, arguments);
        }
        MissingMethodException last = null;
        Object callObject = object;
        Label_0727: {
            if (method == null) {
                final Object owner = closure.getOwner();
                final Object delegate = closure.getDelegate();
                final Object thisObject = closure.getThisObject();
                final int resolveStrategy = closure.getResolveStrategy();
                boolean invokeOnDelegate = false;
                boolean invokeOnOwner = false;
                boolean ownerFirst = true;
                switch (resolveStrategy) {
                    case 4: {
                        break;
                    }
                    case 3: {
                        method = this.getDelegateMethod(closure, delegate, methodName, argClasses);
                        callObject = delegate;
                        if (method == null) {
                            invokeOnDelegate = (delegate != closure && delegate instanceof GroovyObject);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        method = this.getDelegateMethod(closure, owner, methodName, argClasses);
                        callObject = owner;
                        if (method == null) {
                            invokeOnOwner = (owner != closure && owner instanceof GroovyObject);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        method = this.getDelegateMethod(closure, delegate, methodName, argClasses);
                        callObject = delegate;
                        if (method == null) {
                            method = this.getDelegateMethod(closure, owner, methodName, argClasses);
                            callObject = owner;
                        }
                        if (method == null) {
                            invokeOnDelegate = (delegate != closure && delegate instanceof GroovyObject);
                            invokeOnOwner = (owner != closure && owner instanceof GroovyObject);
                            ownerFirst = false;
                            break;
                        }
                        break;
                    }
                    default: {
                        method = this.getDelegateMethod(closure, thisObject, methodName, argClasses);
                        callObject = thisObject;
                        if (method == null) {
                            final LinkedList list = new LinkedList();
                            Closure currentClosure;
                            for (Object current = closure; current != thisObject; current = currentClosure.getOwner()) {
                                currentClosure = (Closure)current;
                                if (currentClosure.getDelegate() != null) {
                                    list.add(current);
                                }
                            }
                            while (!list.isEmpty() && method == null) {
                                final Closure closureWithDelegate = list.removeLast();
                                final Object currentDelegate = closureWithDelegate.getDelegate();
                                method = this.getDelegateMethod(closureWithDelegate, currentDelegate, methodName, argClasses);
                                callObject = currentDelegate;
                            }
                        }
                        if (method == null) {
                            invokeOnDelegate = (delegate != closure && delegate instanceof GroovyObject);
                            invokeOnOwner = (owner != closure && owner instanceof GroovyObject);
                            break;
                        }
                        break;
                    }
                }
                if (method == null) {
                    if (!invokeOnOwner) {
                        if (!invokeOnDelegate) {
                            break Label_0727;
                        }
                    }
                    try {
                        if (ownerFirst) {
                            return this.invokeOnDelegationObjects(invokeOnOwner, owner, invokeOnDelegate, delegate, methodName, arguments);
                        }
                        return this.invokeOnDelegationObjects(invokeOnDelegate, delegate, invokeOnOwner, owner, methodName, arguments);
                    }
                    catch (MissingMethodException mme) {
                        last = mme;
                    }
                }
            }
        }
        if (method != null) {
            final MetaClass metaClass = this.registry.getMetaClass(callObject.getClass());
            if (metaClass instanceof ProxyMetaClass) {
                return metaClass.invokeMethod(callObject, methodName, arguments);
            }
            return method.doMethodInvoke(callObject, arguments);
        }
        else {
            Object value = null;
            try {
                value = this.getProperty(object, methodName);
            }
            catch (MissingPropertyException ex) {}
            if (value instanceof Closure) {
                final Closure cl = (Closure)value;
                final MetaClass delegateMetaClass = cl.getMetaClass();
                return delegateMetaClass.invokeMethod(cl.getClass(), closure, "doCall", originalArguments, false, fromInsideClass);
            }
            if (last != null) {
                throw last;
            }
            throw new MissingMethodException(methodName, this.theClass, arguments, false);
        }
    }
    
    private Object[] makeArguments(final Object[] arguments, final String methodName) {
        if (arguments == null) {
            return ClosureMetaClass.EMPTY_ARGUMENTS;
        }
        return arguments;
    }
    
    private static Throwable unwrap(final GroovyRuntimeException gre) {
        Throwable th = gre;
        if (th.getCause() != null && th.getCause() != gre) {
            th = th.getCause();
        }
        if (th != gre && th instanceof GroovyRuntimeException) {
            return unwrap((GroovyRuntimeException)th);
        }
        return th;
    }
    
    private Object invokeOnDelegationObjects(final boolean invoke1, final Object o1, final boolean invoke2, final Object o2, final String methodName, final Object[] args) {
        MissingMethodException first = null;
        if (invoke1) {
            final GroovyObject go = (GroovyObject)o1;
            try {
                return go.invokeMethod(methodName, args);
            }
            catch (MissingMethodException mme) {
                first = mme;
            }
            catch (GroovyRuntimeException gre) {
                final Throwable th = unwrap(gre);
                if (!(th instanceof MissingMethodException) || !methodName.equals(((MissingMethodException)th).getMethod())) {
                    throw gre;
                }
                first = (MissingMethodException)th;
            }
        }
        if (invoke2 && (!invoke1 || o1 != o2)) {
            final GroovyObject go = (GroovyObject)o2;
            try {
                return go.invokeMethod(methodName, args);
            }
            catch (MissingMethodException mme) {
                if (first == null) {
                    first = mme;
                }
            }
            catch (GroovyRuntimeException gre) {
                final Throwable th = unwrap(gre);
                if (!(th instanceof MissingMethodException)) {
                    throw gre;
                }
                first = (MissingMethodException)th;
            }
        }
        throw first;
    }
    
    private synchronized void initAttributes() {
        if (!this.attributes.isEmpty()) {
            return;
        }
        this.attributes.put("!", null);
        final CachedField[] fieldArray = this.theCachedClass.getFields();
        for (int i = 0; i < fieldArray.length; ++i) {
            this.attributes.put(fieldArray[i].getName(), fieldArray[i]);
        }
        this.attributeInitDone = !this.attributes.isEmpty();
    }
    
    @Override
    public synchronized void initialize() {
        if (!this.isInitialized()) {
            final CachedMethod[] methodArray = this.theCachedClass.getMethods();
            synchronized (this.theCachedClass) {
                for (int i = 0; i < methodArray.length; ++i) {
                    final CachedMethod cachedMethod = methodArray[i];
                    if (cachedMethod.getName().equals("doCall")) {
                        final MetaMethod method = cachedMethod;
                        this.closureMethods.add(method);
                    }
                }
            }
            this.assignMethodChooser();
            this.initialized = true;
        }
    }
    
    private void assignMethodChooser() {
        if (this.closureMethods.size() == 1) {
            final MetaMethod doCall = (MetaMethod)this.closureMethods.get(0);
            final CachedClass[] c = doCall.getParameterTypes();
            final int length = c.length;
            if (length == 0) {
                this.chooser = new MethodChooser() {
                    public Object chooseMethod(final Class[] arguments, final boolean coerce) {
                        if (arguments.length == 0) {
                            return doCall;
                        }
                        return null;
                    }
                };
            }
            else if (length == 1 && c[0].getTheClass() == Object.class) {
                this.chooser = new MethodChooser() {
                    public Object chooseMethod(final Class[] arguments, final boolean coerce) {
                        if (arguments.length < 2) {
                            return doCall;
                        }
                        return null;
                    }
                };
            }
            else {
                boolean allObject = true;
                for (int i = 0; i < c.length - 1; ++i) {
                    if (c[i].getTheClass() != Object.class) {
                        allObject = false;
                        break;
                    }
                }
                if (allObject && c[c.length - 1].getTheClass() == Object.class) {
                    this.chooser = new MethodChooser() {
                        public Object chooseMethod(final Class[] arguments, final boolean coerce) {
                            if (arguments.length == c.length) {
                                return doCall;
                            }
                            return null;
                        }
                    };
                }
                else if (allObject && c[c.length - 1].getTheClass() == Object[].class) {
                    final int minimumLength = c.length - 2;
                    this.chooser = new MethodChooser() {
                        public Object chooseMethod(final Class[] arguments, final boolean coerce) {
                            if (arguments.length > minimumLength) {
                                return doCall;
                            }
                            return null;
                        }
                    };
                }
                else {
                    this.chooser = new MethodChooser() {
                        public Object chooseMethod(final Class[] arguments, final boolean coerce) {
                            if (doCall.isValidMethod(arguments)) {
                                return doCall;
                            }
                            return null;
                        }
                    };
                }
            }
        }
        else if (this.closureMethods.size() == 2) {
            MetaMethod m0 = null;
            MetaMethod m2 = null;
            for (int j = 0; j != this.closureMethods.size(); ++j) {
                final MetaMethod k = (MetaMethod)this.closureMethods.get(j);
                final CachedClass[] c2 = k.getParameterTypes();
                if (c2.length == 0) {
                    m0 = k;
                }
                else if (c2.length == 1 && c2[0].getTheClass() == Object.class) {
                    m2 = k;
                }
            }
            if (m0 != null && m2 != null) {
                this.chooser = new StandardClosureChooser(m0, m2);
            }
        }
        if (this.chooser == null) {
            this.chooser = new NormalMethodChooser(this.theClass, this.closureMethods);
        }
    }
    
    private MetaClass lookupObjectMetaClass(final Object object) {
        if (object instanceof GroovyObject) {
            final GroovyObject go = (GroovyObject)object;
            return go.getMetaClass();
        }
        Class ownerClass = object.getClass();
        if (ownerClass == Class.class) {
            ownerClass = (Class)object;
            return this.registry.getMetaClass(ownerClass);
        }
        final MetaClass metaClass = InvokerHelper.getMetaClass(object);
        return metaClass;
    }
    
    @Override
    public List<MetaMethod> getMethods() {
        final List<MetaMethod> answer = ClosureMetaClass.CLOSURE_METACLASS.getMetaMethods();
        answer.addAll(this.closureMethods.toList());
        return answer;
    }
    
    @Override
    public List<MetaMethod> getMetaMethods() {
        return ClosureMetaClass.CLOSURE_METACLASS.getMetaMethods();
    }
    
    @Override
    public List<MetaProperty> getProperties() {
        return ClosureMetaClass.CLOSURE_METACLASS.getProperties();
    }
    
    @Override
    public MetaMethod pickMethod(final String name, Class[] argTypes) {
        if (argTypes == null) {
            argTypes = MetaClassHelper.EMPTY_CLASS_ARRAY;
        }
        if (name.equals("call") || name.equals("doCall")) {
            return this.pickClosureMethod(argTypes);
        }
        return ClosureMetaClass.CLOSURE_METACLASS.getMetaMethod(name, argTypes);
    }
    
    public MetaMethod retrieveStaticMethod(final String methodName, final Class[] arguments) {
        return null;
    }
    
    @Override
    protected boolean isInitialized() {
        return this.initialized;
    }
    
    @Override
    public MetaMethod getStaticMetaMethod(final String name, final Object[] args) {
        return ClosureMetaClass.CLOSURE_METACLASS.getStaticMetaMethod(name, args);
    }
    
    public MetaMethod getStaticMetaMethod(final String name, final Class[] argTypes) {
        return ClosureMetaClass.CLOSURE_METACLASS.getStaticMetaMethod(name, argTypes);
    }
    
    @Override
    public Object getProperty(final Class sender, final Object object, final String name, final boolean useSuper, final boolean fromInsideClass) {
        if (object instanceof Class) {
            return getStaticMetaClass().getProperty(sender, object, name, useSuper, fromInsideClass);
        }
        return ClosureMetaClass.CLOSURE_METACLASS.getProperty(sender, object, name, useSuper, fromInsideClass);
    }
    
    @Override
    public Object getAttribute(final Class sender, final Object object, final String attribute, final boolean useSuper, final boolean fromInsideClass) {
        if (object instanceof Class) {
            return getStaticMetaClass().getAttribute(sender, object, attribute, useSuper);
        }
        if (!this.attributeInitDone) {
            this.initAttributes();
        }
        final CachedField mfp = this.attributes.get(attribute);
        if (mfp == null) {
            return ClosureMetaClass.CLOSURE_METACLASS.getAttribute(sender, object, attribute, useSuper);
        }
        return mfp.getProperty(object);
    }
    
    @Override
    public void setAttribute(final Class sender, final Object object, final String attribute, final Object newValue, final boolean useSuper, final boolean fromInsideClass) {
        if (object instanceof Class) {
            getStaticMetaClass().setAttribute(sender, object, attribute, newValue, useSuper, fromInsideClass);
        }
        else {
            if (!this.attributeInitDone) {
                this.initAttributes();
            }
            final CachedField mfp = this.attributes.get(attribute);
            if (mfp == null) {
                ClosureMetaClass.CLOSURE_METACLASS.setAttribute(sender, object, attribute, newValue, useSuper, fromInsideClass);
            }
            else {
                mfp.setProperty(object, newValue);
            }
        }
    }
    
    @Override
    public Object invokeStaticMethod(final Object object, final String methodName, final Object[] arguments) {
        return getStaticMetaClass().invokeMethod(Class.class, object, methodName, arguments, false, false);
    }
    
    @Override
    public void setProperty(final Class sender, final Object object, final String name, final Object newValue, final boolean useSuper, final boolean fromInsideClass) {
        if (object instanceof Class) {
            getStaticMetaClass().setProperty(sender, object, name, newValue, useSuper, fromInsideClass);
        }
        else {
            ClosureMetaClass.CLOSURE_METACLASS.setProperty(sender, object, name, newValue, useSuper, fromInsideClass);
        }
    }
    
    public MetaMethod getMethodWithoutCaching(final int index, final Class sender, final String methodName, final Class[] arguments, final boolean isCallToSuper) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setProperties(final Object bean, final Map map) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addMetaBeanProperty(final MetaBeanProperty mp) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addMetaMethod(final MetaMethod method) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addNewInstanceMethod(final Method method) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addNewStaticMethod(final Method method) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Constructor retrieveConstructor(final Class[] arguments) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public CallSite createPojoCallSite(final CallSite site, final Object receiver, final Object[] args) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public CallSite createPogoCallSite(final CallSite site, final Object[] args) {
        return new PogoMetaClassSite(site, this);
    }
    
    @Override
    public CallSite createPogoCallCurrentSite(final CallSite site, final Class sender, final Object[] args) {
        return new PogoMetaClassSite(site, this);
    }
    
    @Override
    public List respondsTo(final Object obj, final String name, final Object[] argTypes) {
        this.loadMetaInfo();
        return super.respondsTo(obj, name, argTypes);
    }
    
    @Override
    public List respondsTo(final Object obj, final String name) {
        this.loadMetaInfo();
        return super.respondsTo(obj, name);
    }
    
    private synchronized void loadMetaInfo() {
        if (this.metaMethodIndex.isEmpty()) {
            this.initialized = false;
            super.initialize();
            this.initialized = true;
        }
    }
    
    @Override
    protected void applyPropertyDescriptors(final PropertyDescriptor[] propertyDescriptors) {
    }
    
    static {
        EMPTY_ARGUMENTS = new Object[0];
        (CLOSURE_METACLASS = new MetaClassImpl(Closure.class)).initialize();
    }
    
    private static class StandardClosureChooser implements MethodChooser
    {
        private final MetaMethod doCall0;
        private final MetaMethod doCall1;
        
        StandardClosureChooser(final MetaMethod m0, final MetaMethod m1) {
            this.doCall0 = m0;
            this.doCall1 = m1;
        }
        
        public Object chooseMethod(final Class[] arguments, final boolean coerce) {
            if (arguments.length == 0) {
                return this.doCall0;
            }
            if (arguments.length == 1) {
                return this.doCall1;
            }
            return null;
        }
    }
    
    private static class NormalMethodChooser implements MethodChooser
    {
        private final FastArray methods;
        final Class theClass;
        
        NormalMethodChooser(final Class theClass, final FastArray methods) {
            this.theClass = theClass;
            this.methods = methods;
        }
        
        public Object chooseMethod(final Class[] arguments, final boolean coerce) {
            if (arguments.length == 0) {
                return MetaClassHelper.chooseEmptyMethodParams(this.methods);
            }
            if (arguments.length == 1 && arguments[0] == null) {
                return MetaClassHelper.chooseMostGeneralMethodWith1NullParam(this.methods);
            }
            final List matchingMethods = new ArrayList();
            final int len = this.methods.size();
            final Object[] data = this.methods.getArray();
            for (int i = 0; i != len; ++i) {
                final Object method = data[i];
                if (((ParameterTypes)method).isValidMethod(arguments)) {
                    matchingMethods.add(method);
                }
            }
            if (matchingMethods.isEmpty()) {
                return null;
            }
            if (matchingMethods.size() == 1) {
                return matchingMethods.get(0);
            }
            return this.chooseMostSpecificParams("doCall", matchingMethods, arguments);
        }
        
        private Object chooseMostSpecificParams(final String name, final List matchingMethods, final Class[] arguments) {
            long matchesDistance = -1L;
            final LinkedList matches = new LinkedList();
            for (final Object method : matchingMethods) {
                final ParameterTypes parameterTypes = (ParameterTypes)method;
                final Class[] paramTypes = parameterTypes.getNativeParameterTypes();
                if (!MetaClassHelper.parametersAreCompatible(arguments, paramTypes)) {
                    continue;
                }
                final long dist = MetaClassHelper.calculateParameterDistance(arguments, parameterTypes);
                if (dist == 0L) {
                    return method;
                }
                if (matches.isEmpty()) {
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
            if (matches.isEmpty()) {
                return null;
            }
            String msg = "Ambiguous method overloading for method ";
            msg = msg + this.theClass.getName() + "#" + name;
            msg += ".\nCannot resolve which method to invoke for ";
            msg += InvokerHelper.toString(arguments);
            msg += " due to overlapping prototypes between:";
            final Iterator iter2 = matches.iterator();
            while (iter2.hasNext()) {
                final CachedClass[] types = iter2.next().getParameterTypes();
                msg = msg + "\n\t" + InvokerHelper.toString(types);
            }
            throw new GroovyRuntimeException(msg);
        }
    }
    
    private interface MethodChooser
    {
        Object chooseMethod(final Class[] p0, final boolean p1);
    }
}
