// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.GroovyClassLoader;
import groovy.lang.MetaClass;
import groovy.lang.Script;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.metaclass.MissingMethodExceptionNoStack;
import java.util.logging.Level;
import org.codehaus.groovy.runtime.MetaClassHelper;
import java.util.Collections;
import groovy.lang.MissingPropertyException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import groovy.lang.Closure;
import java.util.Map;
import java.util.LinkedList;
import java.util.logging.Logger;
import groovy.lang.Binding;

public abstract class FactoryBuilderSupport extends Binding
{
    public static final String CURRENT_FACTORY = "_CURRENT_FACTORY_";
    public static final String PARENT_FACTORY = "_PARENT_FACTORY_";
    public static final String PARENT_NODE = "_PARENT_NODE_";
    public static final String CURRENT_NODE = "_CURRENT_NODE_";
    public static final String PARENT_CONTEXT = "_PARENT_CONTEXT_";
    public static final String PARENT_NAME = "_PARENT_NAME_";
    public static final String CURRENT_NAME = "_CURRENT_NAME_";
    public static final String OWNER = "owner";
    public static final String PARENT_BUILDER = "_PARENT_BUILDER_";
    public static final String CURRENT_BUILDER = "_CURRENT_BUILDER_";
    public static final String CHILD_BUILDER = "_CHILD_BUILDER_";
    private static final Logger LOG;
    private ThreadLocal<LinkedList<Map<String, Object>>> contexts;
    protected LinkedList<Closure> attributeDelegates;
    private List<Closure> disposalClosures;
    private Map<String, Factory> factories;
    private Closure nameMappingClosure;
    private ThreadLocal<FactoryBuilderSupport> localProxyBuilder;
    private FactoryBuilderSupport globalProxyBuilder;
    protected LinkedList<Closure> preInstantiateDelegates;
    protected LinkedList<Closure> postInstantiateDelegates;
    protected LinkedList<Closure> postNodeCompletionDelegates;
    protected Map<String, Closure[]> explicitProperties;
    protected Map<String, Closure> explicitMethods;
    protected Map<String, Set<String>> registrationGroup;
    protected String registrationGroupName;
    protected boolean autoRegistrationRunning;
    protected boolean autoRegistrationComplete;
    
    public static void checkValueIsNull(final Object value, final Object name) {
        if (value != null) {
            throw new RuntimeException("'" + name + "' elements do not accept a value argument.");
        }
    }
    
    public static boolean checkValueIsType(final Object value, final Object name, final Class type) {
        if (value == null) {
            return false;
        }
        if (type.isAssignableFrom(value.getClass())) {
            return true;
        }
        throw new RuntimeException("The value argument of '" + name + "' must be of type " + type.getName());
    }
    
    public static boolean checkValueIsTypeNotString(final Object value, final Object name, final Class type) {
        if (value == null) {
            return false;
        }
        if (type.isAssignableFrom(value.getClass())) {
            return true;
        }
        if (value instanceof String) {
            return false;
        }
        throw new RuntimeException("The value argument of '" + name + "' must be of type " + type.getName() + " or a String.");
    }
    
    public FactoryBuilderSupport() {
        this(false);
    }
    
    public FactoryBuilderSupport(final boolean init) {
        this.contexts = new ThreadLocal<LinkedList<Map<String, Object>>>();
        this.attributeDelegates = new LinkedList<Closure>();
        this.disposalClosures = new ArrayList<Closure>();
        this.factories = new HashMap<String, Factory>();
        this.localProxyBuilder = new ThreadLocal<FactoryBuilderSupport>();
        this.preInstantiateDelegates = new LinkedList<Closure>();
        this.postInstantiateDelegates = new LinkedList<Closure>();
        this.postNodeCompletionDelegates = new LinkedList<Closure>();
        this.explicitProperties = new HashMap<String, Closure[]>();
        this.explicitMethods = new HashMap<String, Closure>();
        this.registrationGroup = new HashMap<String, Set<String>>();
        this.registrationGroupName = "";
        this.autoRegistrationRunning = false;
        this.autoRegistrationComplete = false;
        this.globalProxyBuilder = this;
        this.registrationGroup.put(this.registrationGroupName, new TreeSet<String>());
        if (init) {
            this.autoRegisterNodes();
        }
    }
    
    private Set<String> getRegistrationGroup(final String name) {
        Set<String> group = this.registrationGroup.get(name);
        if (group == null) {
            group = new TreeSet<String>();
            this.registrationGroup.put(name, group);
        }
        return group;
    }
    
    public void autoRegisterNodes() {
        synchronized (this) {
            if (this.autoRegistrationRunning || this.autoRegistrationComplete) {
                return;
            }
        }
        this.autoRegistrationRunning = true;
        try {
            this.callAutoRegisterMethods(this.getClass());
        }
        finally {
            this.autoRegistrationComplete = true;
            this.autoRegistrationRunning = false;
        }
    }
    
    private void callAutoRegisterMethods(final Class declaredClass) {
        if (declaredClass == null) {
            return;
        }
        this.callAutoRegisterMethods(declaredClass.getSuperclass());
        for (final Method method : declaredClass.getDeclaredMethods()) {
            if (method.getName().startsWith("register") && method.getParameterTypes().length == 0) {
                this.registrationGroupName = method.getName().substring("register".length());
                this.registrationGroup.put(this.registrationGroupName, new TreeSet<String>());
                try {
                    if (Modifier.isPublic(method.getModifiers())) {
                        method.invoke(this, new Object[0]);
                    }
                }
                catch (IllegalAccessException e) {
                    throw new RuntimeException("Could not init " + this.getClass().getName() + " because of an access error in " + declaredClass.getName() + "." + method.getName(), e);
                }
                catch (InvocationTargetException e2) {
                    throw new RuntimeException("Could not init " + this.getClass().getName() + " because of an exception in " + declaredClass.getName() + "." + method.getName(), e2);
                }
                finally {
                    this.registrationGroupName = "";
                }
            }
        }
    }
    
    @Deprecated
    public FactoryBuilderSupport(final Closure nameMappingClosure) {
        this.contexts = new ThreadLocal<LinkedList<Map<String, Object>>>();
        this.attributeDelegates = new LinkedList<Closure>();
        this.disposalClosures = new ArrayList<Closure>();
        this.factories = new HashMap<String, Factory>();
        this.localProxyBuilder = new ThreadLocal<FactoryBuilderSupport>();
        this.preInstantiateDelegates = new LinkedList<Closure>();
        this.postInstantiateDelegates = new LinkedList<Closure>();
        this.postNodeCompletionDelegates = new LinkedList<Closure>();
        this.explicitProperties = new HashMap<String, Closure[]>();
        this.explicitMethods = new HashMap<String, Closure>();
        this.registrationGroup = new HashMap<String, Set<String>>();
        this.registrationGroupName = "";
        this.autoRegistrationRunning = false;
        this.autoRegistrationComplete = false;
        this.nameMappingClosure = nameMappingClosure;
    }
    
    @Override
    public Object getVariable(final String name) {
        return this.getProxyBuilder().doGetVariable(name);
    }
    
    private Object doGetVariable(final String name) {
        return super.getVariable(name);
    }
    
    @Override
    public void setVariable(final String name, final Object value) {
        this.getProxyBuilder().doSetVariable(name, value);
    }
    
    private void doSetVariable(final String name, final Object value) {
        super.setVariable(name, value);
    }
    
    @Override
    public Map getVariables() {
        return this.getProxyBuilder().doGetVariables();
    }
    
    private Map doGetVariables() {
        return super.getVariables();
    }
    
    @Override
    public Object getProperty(final String property) {
        try {
            return this.getProxyBuilder().doGetProperty(property);
        }
        catch (MissingPropertyException mpe) {
            if (this.getContext() != null && this.getContext().containsKey(property)) {
                return this.getContext().get(property);
            }
            return this.getMetaClass().getProperty(this, property);
        }
    }
    
    private Object doGetProperty(final String property) {
        final Closure[] accessors = this.resolveExplicitProperty(property);
        if (accessors == null) {
            return super.getProperty(property);
        }
        if (accessors[0] == null) {
            throw new MissingPropertyException(property + " is declared as write only");
        }
        return accessors[0].call();
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        this.getProxyBuilder().doSetProperty(property, newValue);
    }
    
    private void doSetProperty(final String property, final Object newValue) {
        final Closure[] accessors = this.resolveExplicitProperty(property);
        if (accessors != null) {
            if (accessors[1] == null) {
                throw new MissingPropertyException(property + " is declared as read only");
            }
            accessors[1].call(newValue);
        }
        else {
            super.setProperty(property, newValue);
        }
    }
    
    public Map<String, Factory> getFactories() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends Factory>)this.getProxyBuilder().factories);
    }
    
    public Map<String, Closure> getExplicitMethods() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends Closure>)this.getProxyBuilder().explicitMethods);
    }
    
    public Map<String, Closure[]> getExplicitProperties() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends Closure[]>)this.getProxyBuilder().explicitProperties);
    }
    
    public Map<String, Factory> getLocalFactories() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends Factory>)this.factories);
    }
    
    public Map<String, Closure> getLocalExplicitMethods() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends Closure>)this.explicitMethods);
    }
    
    public Map<String, Closure[]> getLocalExplicitProperties() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends Closure[]>)this.explicitProperties);
    }
    
    public Set<String> getRegistrationGroups() {
        return Collections.unmodifiableSet((Set<? extends String>)this.registrationGroup.keySet());
    }
    
    public Set<String> getRegistrationGroupItems(final String group) {
        final Set<String> groupSet = this.registrationGroup.get(group);
        if (groupSet != null) {
            return Collections.unmodifiableSet((Set<? extends String>)groupSet);
        }
        return Collections.emptySet();
    }
    
    public List<Closure> getAttributeDelegates() {
        return Collections.unmodifiableList((List<? extends Closure>)this.attributeDelegates);
    }
    
    public List<Closure> getPreInstantiateDelegates() {
        return Collections.unmodifiableList((List<? extends Closure>)this.preInstantiateDelegates);
    }
    
    public List<Closure> getPostInstantiateDelegates() {
        return Collections.unmodifiableList((List<? extends Closure>)this.postInstantiateDelegates);
    }
    
    public List<Closure> getPostNodeCompletionDelegates() {
        return Collections.unmodifiableList((List<? extends Closure>)this.postNodeCompletionDelegates);
    }
    
    public Map<String, Object> getContext() {
        final LinkedList<Map<String, Object>> contexts = this.getProxyBuilder().contexts.get();
        if (contexts != null && !contexts.isEmpty()) {
            return contexts.getFirst();
        }
        return null;
    }
    
    public Object getCurrent() {
        return this.getContextAttribute("_CURRENT_NODE_");
    }
    
    public Factory getCurrentFactory() {
        return (Factory)this.getContextAttribute("_CURRENT_FACTORY_");
    }
    
    public String getCurrentName() {
        return (String)this.getContextAttribute("_CURRENT_NAME_");
    }
    
    public FactoryBuilderSupport getCurrentBuilder() {
        return (FactoryBuilderSupport)this.getContextAttribute("_CURRENT_BUILDER_");
    }
    
    public Object getParentNode() {
        return this.getContextAttribute("_PARENT_NODE_");
    }
    
    public Factory getParentFactory() {
        return (Factory)this.getContextAttribute("_PARENT_FACTORY_");
    }
    
    public Map getParentContext() {
        return (Map)this.getContextAttribute("_PARENT_CONTEXT_");
    }
    
    public String getParentName() {
        return (String)this.getContextAttribute("_PARENT_NAME_");
    }
    
    public FactoryBuilderSupport getChildBuilder() {
        return (FactoryBuilderSupport)this.getContextAttribute("_CHILD_BUILDER_");
    }
    
    public Object getContextAttribute(final String key) {
        final Map context = this.getContext();
        if (context != null) {
            return context.get(key);
        }
        return null;
    }
    
    public Object invokeMethod(final String methodName) {
        return this.getProxyBuilder().invokeMethod(methodName, null);
    }
    
    @Override
    public Object invokeMethod(final String methodName, final Object args) {
        final Object name = this.getProxyBuilder().getName(methodName);
        final Object previousContext = this.getProxyBuilder().getContext();
        Object result;
        try {
            result = this.getProxyBuilder().doInvokeMethod(methodName, name, args);
        }
        catch (RuntimeException e) {
            if (this.getContexts().contains(previousContext)) {
                for (Map<String, Object> context = this.getProxyBuilder().getContext(); context != null && context != previousContext; context = this.getProxyBuilder().getContext()) {
                    this.getProxyBuilder().popContext();
                }
            }
            throw e;
        }
        return result;
    }
    
    public Closure addAttributeDelegate(final Closure attrDelegate) {
        this.getProxyBuilder().attributeDelegates.addFirst(attrDelegate);
        return attrDelegate;
    }
    
    public void removeAttributeDelegate(final Closure attrDelegate) {
        this.getProxyBuilder().attributeDelegates.remove(attrDelegate);
    }
    
    public Closure addPreInstantiateDelegate(final Closure delegate) {
        this.getProxyBuilder().preInstantiateDelegates.addFirst(delegate);
        return delegate;
    }
    
    public void removePreInstantiateDelegate(final Closure delegate) {
        this.getProxyBuilder().preInstantiateDelegates.remove(delegate);
    }
    
    public Closure addPostInstantiateDelegate(final Closure delegate) {
        this.getProxyBuilder().postInstantiateDelegates.addFirst(delegate);
        return delegate;
    }
    
    public void removePostInstantiateDelegate(final Closure delegate) {
        this.getProxyBuilder().postInstantiateDelegates.remove(delegate);
    }
    
    public Closure addPostNodeCompletionDelegate(final Closure delegate) {
        this.getProxyBuilder().postNodeCompletionDelegates.addFirst(delegate);
        return delegate;
    }
    
    public void removePostNodeCompletionDelegate(final Closure delegate) {
        this.getProxyBuilder().postNodeCompletionDelegates.remove(delegate);
    }
    
    public void registerExplicitProperty(final String name, final Closure getter, final Closure setter) {
        this.registerExplicitProperty(name, this.registrationGroupName, getter, setter);
    }
    
    public void registerExplicitProperty(final String name, final String groupName, final Closure getter, final Closure setter) {
        if (getter != null) {
            getter.setDelegate(this);
        }
        if (setter != null) {
            setter.setDelegate(this);
        }
        this.explicitProperties.put(name, new Closure[] { getter, setter });
        final String methodNameBase = MetaClassHelper.capitalize(name);
        if (getter != null) {
            this.getRegistrationGroup(groupName).add("get" + methodNameBase);
        }
        if (setter != null) {
            this.getRegistrationGroup(groupName).add("set" + methodNameBase);
        }
    }
    
    public void registerExplicitMethod(final String name, final Closure closure) {
        this.registerExplicitMethod(name, this.registrationGroupName, closure);
    }
    
    public void registerExplicitMethod(final String name, final String groupName, final Closure closure) {
        closure.setDelegate(this);
        this.explicitMethods.put(name, closure);
        this.getRegistrationGroup(groupName).add(name);
    }
    
    public void registerBeanFactory(final String theName, final Class beanClass) {
        this.registerBeanFactory(theName, this.registrationGroupName, beanClass);
    }
    
    public void registerBeanFactory(final String theName, final String groupName, final Class beanClass) {
        this.getProxyBuilder().registerFactory(theName, new AbstractFactory() {
            public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map properties) throws InstantiationException, IllegalAccessException {
                if (FactoryBuilderSupport.checkValueIsTypeNotString(value, name, beanClass)) {
                    return value;
                }
                return beanClass.newInstance();
            }
        });
        this.getRegistrationGroup(groupName).add(theName);
    }
    
    public void registerFactory(final String name, final Factory factory) {
        this.registerFactory(name, this.registrationGroupName, factory);
    }
    
    public void registerFactory(final String name, final String groupName, final Factory factory) {
        this.getProxyBuilder().factories.put(name, factory);
        this.getRegistrationGroup(groupName).add(name);
        factory.onFactoryRegistration(this, name, groupName);
    }
    
    protected Object createNode(final Object name, final Map attributes, final Object value) {
        final Factory factory = this.getProxyBuilder().resolveFactory(name, attributes, value);
        if (factory == null) {
            FactoryBuilderSupport.LOG.log(Level.WARNING, "Could not find match for name '" + name + "'");
            throw new MissingMethodExceptionNoStack((String)name, Object.class, new Object[] { attributes, value });
        }
        this.getProxyBuilder().getContext().put("_CURRENT_FACTORY_", factory);
        this.getProxyBuilder().getContext().put("_CURRENT_NAME_", String.valueOf(name));
        this.getProxyBuilder().preInstantiate(name, attributes, value);
        Object node;
        try {
            node = factory.newInstance(this.getProxyBuilder().getChildBuilder(), name, value, attributes);
            if (node == null) {
                FactoryBuilderSupport.LOG.log(Level.WARNING, "Factory for name '" + name + "' returned null");
                return null;
            }
            if (FactoryBuilderSupport.LOG.isLoggable(Level.FINE)) {
                FactoryBuilderSupport.LOG.fine("For name: " + name + " created node: " + node);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create component for '" + name + "' reason: " + e, e);
        }
        this.getProxyBuilder().postInstantiate(name, attributes, node);
        this.getProxyBuilder().handleNodeAttributes(node, attributes);
        return node;
    }
    
    protected Factory resolveFactory(final Object name, final Map attributes, final Object value) {
        this.getProxyBuilder().getContext().put("_CHILD_BUILDER_", this.getProxyBuilder());
        return this.getProxyBuilder().factories.get(name);
    }
    
    protected Closure resolveExplicitMethod(final String methodName, final Object args) {
        return this.explicitMethods.get(methodName);
    }
    
    protected Closure[] resolveExplicitProperty(final String propertyName) {
        return this.explicitProperties.get(propertyName);
    }
    
    private Object doInvokeMethod(final String methodName, final Object name, final Object args) {
        final Reference explicitResult = new Reference();
        if (this.checkExplicitMethod(methodName, args, explicitResult)) {
            return explicitResult.get();
        }
        return this.dispathNodeCall(name, args);
    }
    
    protected boolean checkExplicitMethod(final String methodName, final Object args, final Reference result) {
        final Closure explicitMethod = this.resolveExplicitMethod(methodName, args);
        if (explicitMethod != null) {
            if (args instanceof Object[]) {
                result.set(explicitMethod.call((Object[])args));
            }
            else {
                result.set(explicitMethod.call(args));
            }
            return true;
        }
        return false;
    }
    
    protected Object dispathNodeCall(final Object name, final Object args) {
        Closure closure = null;
        List list = InvokerHelper.asList(args);
        boolean needToPopContext;
        if (this.getProxyBuilder().getContexts().isEmpty()) {
            this.getProxyBuilder().newContext();
            needToPopContext = true;
        }
        else {
            needToPopContext = false;
        }
        Object node;
        try {
            Map namedArgs = Collections.EMPTY_MAP;
            if (list.size() > 0 && list.get(0) instanceof LinkedHashMap) {
                namedArgs = list.get(0);
                list = list.subList(1, list.size());
            }
            if (list.size() > 0 && list.get(list.size() - 1) instanceof Closure) {
                closure = list.get(list.size() - 1);
                list = list.subList(0, list.size() - 1);
            }
            Object arg;
            if (list.size() == 0) {
                arg = null;
            }
            else if (list.size() == 1) {
                arg = list.get(0);
            }
            else {
                arg = list;
            }
            node = this.getProxyBuilder().createNode(name, namedArgs, arg);
            final Object current = this.getProxyBuilder().getCurrent();
            if (current != null) {
                this.getProxyBuilder().setParent(current, node);
            }
            if (closure != null) {
                final Factory parentFactory = this.getProxyBuilder().getCurrentFactory();
                if (parentFactory.isLeaf()) {
                    throw new RuntimeException("'" + name + "' doesn't support nesting.");
                }
                boolean processContent = true;
                if (parentFactory.isHandlesNodeChildren()) {
                    processContent = parentFactory.onNodeChildren(this, node, closure);
                }
                if (processContent) {
                    final String parentName = this.getProxyBuilder().getCurrentName();
                    final Map parentContext = this.getProxyBuilder().getContext();
                    this.getProxyBuilder().newContext();
                    try {
                        this.getProxyBuilder().getContext().put("owner", closure.getOwner());
                        this.getProxyBuilder().getContext().put("_CURRENT_NODE_", node);
                        this.getProxyBuilder().getContext().put("_PARENT_FACTORY_", parentFactory);
                        this.getProxyBuilder().getContext().put("_PARENT_NODE_", current);
                        this.getProxyBuilder().getContext().put("_PARENT_CONTEXT_", parentContext);
                        this.getProxyBuilder().getContext().put("_PARENT_NAME_", parentName);
                        this.getProxyBuilder().getContext().put("_PARENT_BUILDER_", parentContext.get("_CURRENT_BUILDER_"));
                        this.getProxyBuilder().getContext().put("_CURRENT_BUILDER_", parentContext.get("_CHILD_BUILDER_"));
                        this.getProxyBuilder().setClosureDelegate(closure, node);
                        closure.call();
                    }
                    finally {
                        this.getProxyBuilder().popContext();
                    }
                }
            }
            this.getProxyBuilder().nodeCompleted(current, node);
            node = this.getProxyBuilder().postNodeCompletion(current, node);
        }
        finally {
            if (needToPopContext) {
                this.getProxyBuilder().popContext();
            }
        }
        return node;
    }
    
    public Object getName(final String methodName) {
        if (this.getProxyBuilder().nameMappingClosure != null) {
            return this.getProxyBuilder().nameMappingClosure.call(methodName);
        }
        return methodName;
    }
    
    protected FactoryBuilderSupport getProxyBuilder() {
        final FactoryBuilderSupport proxy = this.localProxyBuilder.get();
        if (proxy == null) {
            return this.globalProxyBuilder;
        }
        return proxy;
    }
    
    protected void setProxyBuilder(final FactoryBuilderSupport proxyBuilder) {
        this.globalProxyBuilder = proxyBuilder;
    }
    
    public Closure getNameMappingClosure() {
        return this.nameMappingClosure;
    }
    
    public void setNameMappingClosure(final Closure nameMappingClosure) {
        this.nameMappingClosure = nameMappingClosure;
    }
    
    protected void handleNodeAttributes(final Object node, final Map attributes) {
        if (node == null) {
            return;
        }
        for (final Closure attrDelegate : this.getProxyBuilder().attributeDelegates) {
            FactoryBuilderSupport builder = this;
            if (attrDelegate.getOwner() instanceof FactoryBuilderSupport) {
                builder = (FactoryBuilderSupport)attrDelegate.getOwner();
            }
            else if (attrDelegate.getDelegate() instanceof FactoryBuilderSupport) {
                builder = (FactoryBuilderSupport)attrDelegate.getDelegate();
            }
            attrDelegate.call(new Object[] { builder, node, attributes });
        }
        if (this.getProxyBuilder().getCurrentFactory().onHandleNodeAttributes(this.getProxyBuilder().getChildBuilder(), node, attributes)) {
            this.getProxyBuilder().setNodeAttributes(node, attributes);
        }
    }
    
    protected void newContext() {
        this.getContexts().addFirst(new HashMap<String, Object>());
    }
    
    protected void nodeCompleted(final Object parent, final Object node) {
        this.getProxyBuilder().getCurrentFactory().onNodeCompleted(this.getProxyBuilder().getChildBuilder(), parent, node);
    }
    
    protected Map<String, Object> popContext() {
        if (!this.getProxyBuilder().getContexts().isEmpty()) {
            return this.getProxyBuilder().getContexts().removeFirst();
        }
        return null;
    }
    
    protected void postInstantiate(final Object name, final Map attributes, final Object node) {
        for (final Closure postInstantiateDelegate : this.getProxyBuilder().postInstantiateDelegates) {
            postInstantiateDelegate.call(new Object[] { this, attributes, node });
        }
    }
    
    protected Object postNodeCompletion(final Object parent, final Object node) {
        for (final Closure postNodeCompletionDelegate : this.getProxyBuilder().postNodeCompletionDelegates) {
            postNodeCompletionDelegate.call(new Object[] { this, parent, node });
        }
        return node;
    }
    
    protected void preInstantiate(final Object name, final Map attributes, final Object value) {
        for (final Closure preInstantiateDelegate : this.getProxyBuilder().preInstantiateDelegates) {
            preInstantiateDelegate.call(new Object[] { this, attributes, value });
        }
    }
    
    protected void reset() {
        this.getProxyBuilder().getContexts().clear();
    }
    
    protected void setClosureDelegate(final Closure closure, final Object node) {
        closure.setDelegate(this);
    }
    
    protected void setNodeAttributes(final Object node, final Map attributes) {
        for (final Map.Entry entry : attributes.entrySet()) {
            final String property = entry.getKey().toString();
            final Object value = entry.getValue();
            InvokerHelper.setProperty(node, property, value);
        }
    }
    
    protected void setParent(final Object parent, final Object child) {
        this.getProxyBuilder().getCurrentFactory().setParent(this.getProxyBuilder().getChildBuilder(), parent, child);
        final Factory parentFactory = this.getProxyBuilder().getParentFactory();
        if (parentFactory != null) {
            parentFactory.setChild(this.getProxyBuilder().getCurrentBuilder(), parent, child);
        }
    }
    
    protected LinkedList<Map<String, Object>> getContexts() {
        LinkedList<Map<String, Object>> contexts = this.getProxyBuilder().contexts.get();
        if (contexts == null) {
            contexts = new LinkedList<Map<String, Object>>();
            this.getProxyBuilder().contexts.set(contexts);
        }
        return contexts;
    }
    
    protected Map<String, Object> getContinuationData() {
        final Map<String, Object> data = new HashMap<String, Object>();
        data.put("proxyBuilder", this.localProxyBuilder.get());
        data.put("contexts", this.contexts.get());
        return data;
    }
    
    protected void restoreFromContinuationData(final Map<String, Object> data) {
        this.localProxyBuilder.set(data.get("proxyBuilder"));
        this.contexts.set(data.get("contexts"));
    }
    
    public Object build(final Class viewClass) {
        if (Script.class.isAssignableFrom(viewClass)) {
            final Script script = InvokerHelper.createScript(viewClass, this);
            return this.build(script);
        }
        throw new RuntimeException("Only scripts can be executed via build(Class)");
    }
    
    public Object build(final Script script) {
        final MetaClass scriptMetaClass = script.getMetaClass();
        script.setMetaClass(new FactoryInterceptorMetaClass(scriptMetaClass, this));
        script.setBinding(this);
        return script.run();
    }
    
    public Object build(final String script, final GroovyClassLoader loader) {
        return this.build(loader.parseClass(script));
    }
    
    public Object withBuilder(final FactoryBuilderSupport builder, final Closure closure) {
        if (builder == null || closure == null) {
            return null;
        }
        Object result = null;
        final Object previousContext = this.getProxyBuilder().getContext();
        final FactoryBuilderSupport previousProxyBuilder = this.localProxyBuilder.get();
        try {
            this.localProxyBuilder.set(builder);
            closure.setDelegate(builder);
            result = closure.call();
        }
        catch (RuntimeException e) {
            this.localProxyBuilder.set(previousProxyBuilder);
            if (this.getProxyBuilder().getContexts().contains(previousContext)) {
                for (Map<String, Object> context = this.getProxyBuilder().getContext(); context != null && context != previousContext; context = this.getProxyBuilder().getContext()) {
                    this.getProxyBuilder().popContext();
                }
            }
            throw e;
        }
        finally {
            this.localProxyBuilder.set(previousProxyBuilder);
        }
        return result;
    }
    
    public Object withBuilder(final FactoryBuilderSupport builder, final String name, final Closure closure) {
        if (name == null) {
            return null;
        }
        final Object result = this.getProxyBuilder().withBuilder(builder, closure);
        return this.getProxyBuilder().invokeMethod(name, new Object[] { result });
    }
    
    public Object withBuilder(final Map attributes, final FactoryBuilderSupport builder, final String name, final Closure closure) {
        if (name == null) {
            return null;
        }
        final Object result = this.getProxyBuilder().withBuilder(builder, closure);
        return this.getProxyBuilder().invokeMethod(name, new Object[] { attributes, result });
    }
    
    public void addDisposalClosure(final Closure closure) {
        this.disposalClosures.add(closure);
    }
    
    public void dispose() {
        for (int i = this.disposalClosures.size() - 1; i >= 0; --i) {
            this.disposalClosures.get(i).call();
        }
    }
    
    static {
        LOG = Logger.getLogger(FactoryBuilderSupport.class.getName());
    }
}
