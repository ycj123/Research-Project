// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.GroovySystem;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.DelegatingMetaClass;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.Iterator;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.GroovyShell;
import groovy.lang.Binding;
import java.util.Collection;
import org.codehaus.groovy.runtime.ConversionHandler;
import java.lang.reflect.Modifier;
import groovy.lang.GroovyObjectSupport;
import java.util.HashMap;
import groovy.lang.Closure;
import java.util.Map;
import groovy.lang.GroovyObject;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.List;

public class ProxyGenerator
{
    public static final ProxyGenerator INSTANCE;
    private ClassLoader override;
    private boolean debug;
    private boolean emptyMethods;
    private List<Method> objectMethods;
    private List<Method> groovyObjectMethods;
    
    public ProxyGenerator() {
        this.override = null;
        this.debug = false;
        this.emptyMethods = false;
        this.objectMethods = this.getInheritedMethods(Object.class, new ArrayList<Method>());
        this.groovyObjectMethods = this.getInheritedMethods(GroovyObject.class, new ArrayList<Method>());
    }
    
    public boolean getDebug() {
        return this.debug;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public boolean getEmptyMethods() {
        return this.emptyMethods;
    }
    
    public void setEmptyMethods(final boolean emptyMethods) {
        this.emptyMethods = emptyMethods;
    }
    
    public ClassLoader getOverride() {
        return this.override;
    }
    
    public void setOverride(final ClassLoader override) {
        this.override = override;
    }
    
    public GroovyObject instantiateAggregateFromBaseClass(final Class clazz) {
        return this.instantiateAggregateFromBaseClass((Map)null, clazz);
    }
    
    public GroovyObject instantiateAggregateFromBaseClass(final Map map, final Class clazz) {
        return this.instantiateAggregateFromBaseClass(map, clazz, null);
    }
    
    public GroovyObject instantiateAggregateFromBaseClass(final Closure cl, final Class clazz) {
        final Map<String, Closure> m = new HashMap<String, Closure>();
        m.put("*", cl);
        return this.instantiateAggregateFromBaseClass(m, clazz, null);
    }
    
    public GroovyObject instantiateAggregateFromBaseClass(final Class clazz, final Object[] constructorArgs) {
        return this.instantiateAggregate(null, null, clazz, constructorArgs);
    }
    
    public GroovyObject instantiateAggregateFromBaseClass(final Map map, final Class clazz, final Object[] constructorArgs) {
        return this.instantiateAggregate(map, null, clazz, constructorArgs);
    }
    
    public GroovyObject instantiateAggregateFromInterface(final Class clazz) {
        return this.instantiateAggregateFromInterface(null, clazz);
    }
    
    public GroovyObject instantiateAggregateFromInterface(final Map map, final Class clazz) {
        final List<Class> interfaces = new ArrayList<Class>();
        interfaces.add(clazz);
        return this.instantiateAggregate(map, interfaces);
    }
    
    public GroovyObject instantiateAggregate(final List<Class> interfaces) {
        return this.instantiateAggregate(null, interfaces);
    }
    
    public GroovyObject instantiateAggregate(final Map closureMap, final List<Class> interfaces) {
        return this.instantiateAggregate(closureMap, interfaces, null);
    }
    
    public GroovyObject instantiateAggregate(final Map closureMap, final List<Class> interfaces, final Class clazz) {
        return this.instantiateAggregate(closureMap, interfaces, clazz, null);
    }
    
    public GroovyObject instantiateAggregate(final Map closureMap, final List<Class> interfaces, final Class clazz, final Object[] constructorArgs) {
        Map map = new HashMap();
        if (closureMap != null) {
            map = closureMap;
        }
        List<Class> interfacesToImplement;
        if (interfaces == null) {
            interfacesToImplement = new ArrayList<Class>();
        }
        else {
            interfacesToImplement = interfaces;
        }
        Class baseClass = GroovyObjectSupport.class;
        if (clazz != null) {
            baseClass = clazz;
        }
        final boolean hasArgs = constructorArgs != null && constructorArgs.length > 0;
        final String name = this.shortName(baseClass.getName()) + "_groovyProxy";
        final StringBuffer buffer = new StringBuffer();
        buffer.append("class ").append(name);
        if (clazz != null) {
            buffer.append(" extends ").append(baseClass.getName());
        }
        for (int i = 0; i < interfacesToImplement.size(); ++i) {
            final Class thisInterface = interfacesToImplement.get(i);
            if (i == 0) {
                buffer.append(" implements ");
            }
            else {
                buffer.append(", ");
            }
            buffer.append(thisInterface.getName());
        }
        buffer.append(" {\n").append("    private closureMap\n    ");
        buffer.append(name).append("(map");
        if (hasArgs) {
            buffer.append(", args");
        }
        buffer.append(") {\n");
        buffer.append("        super(");
        if (hasArgs) {
            buffer.append("*args");
        }
        buffer.append(")\n");
        buffer.append("        this.closureMap = map\n");
        buffer.append("    }\n");
        final Map<String, Method> selectedMethods = new HashMap<String, Method>();
        final List<Method> publicAndProtectedMethods = this.getInheritedMethods(baseClass, new ArrayList<Method>());
        final boolean closureIndicator = map.containsKey("*");
        for (final Method method : publicAndProtectedMethods) {
            if (method.getName().indexOf(36) == -1 && !Modifier.isFinal(method.getModifiers()) && !ConversionHandler.isCoreObjectMethod(method)) {
                if (this.containsEquivalentMethod(selectedMethods.values(), method)) {
                    continue;
                }
                if (map.containsKey(method.getName()) || closureIndicator) {
                    selectedMethods.put(method.getName(), method);
                    this.addOverridingMapCall(buffer, method, closureIndicator);
                }
                else {
                    if (!Modifier.isAbstract(method.getModifiers())) {
                        continue;
                    }
                    selectedMethods.put(method.getName(), method);
                    this.addMapOrDummyCall(map, buffer, method);
                }
            }
        }
        final List<Method> interfaceMethods = new ArrayList<Method>();
        for (final Class thisInterface2 : interfacesToImplement) {
            this.getInheritedMethods(thisInterface2, interfaceMethods);
        }
        for (final Method method2 : interfaceMethods) {
            if (!this.containsEquivalentMethod(publicAndProtectedMethods, method2)) {
                selectedMethods.put(method2.getName(), method2);
                this.addMapOrDummyCall(map, buffer, method2);
            }
        }
        for (final Object o : map.keySet()) {
            final String methodName = (String)o;
            if (methodName.indexOf(36) == -1) {
                if (methodName.indexOf(42) != -1) {
                    continue;
                }
                if (selectedMethods.keySet().contains(methodName)) {
                    continue;
                }
                this.addNewMapCall(buffer, methodName);
            }
        }
        buffer.append("}\n").append("new ").append(name);
        buffer.append("(map");
        if (hasArgs) {
            buffer.append(", constructorArgs");
        }
        buffer.append(")");
        final Binding binding = new Binding();
        binding.setVariable("map", map);
        binding.setVariable("constructorArgs", constructorArgs);
        ClassLoader cl = (this.override != null) ? this.override : baseClass.getClassLoader();
        if (clazz == null && interfacesToImplement.size() > 0) {
            final Class c = interfacesToImplement.get(0);
            cl = c.getClassLoader();
        }
        final GroovyShell shell = new GroovyShell(cl, binding);
        if (this.debug) {
            System.out.println("proxy source:\n------------------\n" + buffer.toString() + "\n------------------");
        }
        try {
            return (GroovyObject)shell.evaluate(buffer.toString());
        }
        catch (MultipleCompilationErrorsException err) {
            throw new GroovyRuntimeException("Error creating proxy: " + err.getMessage());
        }
    }
    
    public GroovyObject instantiateDelegate(final Object delegate) {
        return this.instantiateDelegate(null, delegate);
    }
    
    public GroovyObject instantiateDelegate(final List<Class> interfaces, final Object delegate) {
        return this.instantiateDelegate(null, interfaces, delegate);
    }
    
    public GroovyObject instantiateDelegate(final Map closureMap, final List<Class> interfaces, final Object delegate) {
        return this.instantiateDelegateWithBaseClass(closureMap, interfaces, delegate, null);
    }
    
    public GroovyObject instantiateDelegateWithBaseClass(final Map closureMap, final List<Class> interfaces, final Object delegate) {
        return this.instantiateDelegateWithBaseClass(closureMap, interfaces, delegate, delegate.getClass());
    }
    
    public GroovyObject instantiateDelegateWithBaseClass(final Map closureMap, final List<Class> interfaces, final Object delegate, final Class baseClass) {
        final String name = this.shortName(delegate.getClass().getName()) + "_delegateProxy";
        return this.instantiateDelegateWithBaseClass(closureMap, interfaces, delegate, baseClass, name);
    }
    
    public GroovyObject instantiateDelegateWithBaseClass(final Map closureMap, final List<Class> interfaces, final Object delegate, final Class baseClass, final String name) {
        Map map = new HashMap();
        if (closureMap != null) {
            map = closureMap;
        }
        final List<String> selectedMethods = new ArrayList<String>();
        List<Class> interfacesToImplement;
        if (interfaces == null) {
            interfacesToImplement = new ArrayList<Class>();
        }
        else {
            interfacesToImplement = interfaces;
        }
        final StringBuffer buffer = new StringBuffer();
        buffer.append("import org.codehaus.groovy.runtime.InvokerHelper\nclass ").append(name);
        if (baseClass != null) {
            buffer.append(" extends ").append(baseClass.getName());
        }
        for (int i = 0; i < interfacesToImplement.size(); ++i) {
            final Class thisInterface = interfacesToImplement.get(i);
            if (i == 0) {
                buffer.append(" implements ");
            }
            else {
                buffer.append(", ");
            }
            buffer.append(thisInterface.getName());
        }
        buffer.append(" {\n").append("    private delegate\n").append("    private closureMap\n    ");
        buffer.append(name).append("(map, delegate) {\n");
        buffer.append("        this.closureMap = map\n");
        buffer.append("        this.delegate = delegate\n");
        buffer.append("    }\n");
        final List<Method> interfaceMethods = new ArrayList<Method>();
        for (final Class thisInterface2 : interfacesToImplement) {
            this.getInheritedMethods(thisInterface2, interfaceMethods);
        }
        for (final Method method : interfaceMethods) {
            if (!this.containsEquivalentMethod(this.objectMethods, method) && !this.containsEquivalentMethod(this.groovyObjectMethods, method)) {
                selectedMethods.add(method.getName());
                this.addWrappedCall(buffer, method, map);
            }
        }
        final List<Method> additionalMethods = this.getInheritedMethods(delegate.getClass(), new ArrayList<Method>());
        for (final Method method2 : additionalMethods) {
            if (method2.getName().indexOf(36) != -1) {
                continue;
            }
            if (this.containsEquivalentMethod(interfaceMethods, method2) || this.containsEquivalentMethod(this.objectMethods, method2) || this.containsEquivalentMethod(this.groovyObjectMethods, method2)) {
                continue;
            }
            selectedMethods.add(method2.getName());
            this.addWrappedCall(buffer, method2, map);
        }
        for (final Object o : map.keySet()) {
            final String methodName = (String)o;
            if (selectedMethods.contains(methodName)) {
                continue;
            }
            this.addNewMapCall(buffer, methodName);
        }
        buffer.append("}\n").append("new ").append(name);
        buffer.append("(map, delegate)");
        final Binding binding = new Binding();
        binding.setVariable("map", map);
        binding.setVariable("delegate", delegate);
        final ClassLoader cl = (this.override != null) ? this.override : delegate.getClass().getClassLoader();
        final GroovyShell shell = new GroovyShell(cl, binding);
        if (this.debug) {
            System.out.println("proxy source:\n------------------\n" + buffer.toString() + "\n------------------");
        }
        try {
            return (GroovyObject)shell.evaluate(buffer.toString());
        }
        catch (MultipleCompilationErrorsException err) {
            throw new GroovyRuntimeException("Error creating proxy: " + err.getMessage());
        }
    }
    
    private void addWrappedCall(final StringBuffer buffer, final Method method, final Map map) {
        if (map.containsKey(method.getName())) {
            this.addOverridingMapCall(buffer, method, false);
        }
        else {
            final Class[] parameterTypes = this.addMethodPrefix(buffer, method);
            this.addWrappedMethodBody(buffer, method, parameterTypes);
            this.addMethodSuffix(buffer);
        }
    }
    
    private boolean containsEquivalentMethod(final Collection<Method> publicAndProtectedMethods, final Method candidate) {
        for (final Method method : publicAndProtectedMethods) {
            if (candidate.getName().equals(method.getName()) && candidate.getReturnType().equals(method.getReturnType()) && this.hasMatchingParameterTypes(candidate, method)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasMatchingParameterTypes(final Method method, final Method candidate) {
        final Class[] candidateParamTypes = candidate.getParameterTypes();
        final Class[] methodParamTypes = method.getParameterTypes();
        if (candidateParamTypes.length != methodParamTypes.length) {
            return false;
        }
        for (int i = 0; i < methodParamTypes.length; ++i) {
            if (!candidateParamTypes[i].equals(methodParamTypes[i])) {
                return false;
            }
        }
        return true;
    }
    
    private List<Method> getInheritedMethods(final Class baseClass, final List<Method> methods) {
        methods.addAll(DefaultGroovyMethods.toList(baseClass.getMethods()));
        for (Class currentClass = baseClass; currentClass != null; currentClass = currentClass.getSuperclass()) {
            final Method[] arr$;
            final Method[] protectedMethods = arr$ = currentClass.getDeclaredMethods();
            for (final Method method : arr$) {
                if (method.getName().indexOf(36) == -1) {
                    if (Modifier.isProtected(method.getModifiers()) && !this.containsEquivalentMethod(methods, method)) {
                        methods.add(method);
                    }
                }
            }
        }
        return methods;
    }
    
    private void addNewMapCall(final StringBuffer buffer, final String methodName) {
        buffer.append("    def ").append(methodName).append("(Object[] args) {\n").append("        this.@closureMap['").append(methodName).append("'] (*args)\n    }\n");
    }
    
    private void addOverridingMapCall(final StringBuffer buffer, final Method method, final boolean closureIndicator) {
        final Class[] parameterTypes = this.addMethodPrefix(buffer, method);
        this.addMethodBody(buffer, closureIndicator ? "*" : method.getName(), parameterTypes);
        this.addMethodSuffix(buffer);
    }
    
    private void addMapOrDummyCall(final Map map, final StringBuffer buffer, final Method method) {
        final Class[] parameterTypes = this.addMethodPrefix(buffer, method);
        if (map.containsKey(method.getName())) {
            this.addMethodBody(buffer, method.getName(), parameterTypes);
        }
        else if (!this.emptyMethods) {
            this.addUnsupportedBody(buffer);
        }
        this.addMethodSuffix(buffer);
    }
    
    private void addUnsupportedBody(final StringBuffer buffer) {
        buffer.append("throw new UnsupportedOperationException()");
    }
    
    private Class[] addMethodPrefix(final StringBuffer buffer, final Method method) {
        buffer.append("    ").append(this.getSimpleName(method.getReturnType())).append(" ").append(method.getName()).append("(");
        final Class[] parameterTypes = method.getParameterTypes();
        for (int parameterTypeIndex = 0; parameterTypeIndex < parameterTypes.length; ++parameterTypeIndex) {
            final Class parameter = parameterTypes[parameterTypeIndex];
            if (parameterTypeIndex != 0) {
                buffer.append(", ");
            }
            buffer.append(this.getSimpleName(parameter)).append(" ").append("p").append(parameterTypeIndex);
        }
        buffer.append(") { ");
        return parameterTypes;
    }
    
    private void addMethodBody(final StringBuffer buffer, final String method, final Class[] parameterTypes) {
        buffer.append("this.@closureMap['").append(method).append("'] (");
        for (int j = 0; j < parameterTypes.length; ++j) {
            if (j != 0) {
                buffer.append(", ");
            }
            buffer.append("p").append(j);
        }
        buffer.append(")");
    }
    
    private void addWrappedMethodBody(final StringBuffer buffer, final Method method, final Class[] parameterTypes) {
        buffer.append("\n        Object[] args = [");
        for (int j = 0; j < parameterTypes.length; ++j) {
            if (j != 0) {
                buffer.append(", ");
            }
            buffer.append("p").append(j);
        }
        buffer.append("]\n        ");
        buffer.append("InvokerHelper.invokeMethod(delegate, '").append(method.getName()).append("', args)\n");
    }
    
    private void addMethodSuffix(final StringBuffer buffer) {
        buffer.append("    }\n");
    }
    
    public String getSimpleName(final Class c) {
        if (c.isArray()) {
            int dimension;
            Class componentClass;
            for (dimension = 0, componentClass = c; componentClass.isArray(); componentClass = componentClass.getComponentType(), ++dimension) {}
            return componentClass.getName().replaceAll("\\$", "\\.") + DefaultGroovyMethods.multiply("[]", dimension);
        }
        return c.getName().replaceAll("\\$", "\\.");
    }
    
    public String shortName(final String name) {
        final int index = name.lastIndexOf(46);
        if (index == -1) {
            return name;
        }
        return name.substring(index + 1, name.length());
    }
    
    private static void setMetaClass(final MetaClass metaClass) {
        final MetaClass newMetaClass = new DelegatingMetaClass(metaClass) {
            @Override
            public Object invokeStaticMethod(final Object object, final String methodName, final Object[] arguments) {
                return InvokerHelper.invokeMethod(ProxyGenerator.INSTANCE, methodName, arguments);
            }
        };
        GroovySystem.getMetaClassRegistry().setMetaClass(ProxyGenerator.class, newMetaClass);
    }
    
    static {
        INSTANCE = new ProxyGenerator();
        setMetaClass(GroovySystem.getMetaClassRegistry().getMetaClass(ProxyGenerator.class));
    }
}
