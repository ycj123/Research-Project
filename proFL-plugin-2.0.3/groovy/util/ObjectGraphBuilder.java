// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.GString;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import groovy.lang.MetaProperty;
import java.util.Iterator;
import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.lang.MissingPropertyException;
import groovy.lang.Closure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ObjectGraphBuilder extends FactoryBuilderSupport
{
    public static final String NODE_CLASS = "_NODE_CLASS_";
    public static final String NODE_NAME = "_NODE_NAME_";
    public static final String OBJECT_ID = "_OBJECT_ID_";
    public static final String LAZY_REF = "_LAZY_REF_";
    public static final String CLASSNAME_RESOLVER_KEY = "name";
    public static final String CLASSNAME_RESOLVER_REFLECTION = "reflection";
    public static final String CLASSNAME_RESOLVER_REFLECTION_ROOT = "root";
    private static final Pattern PLURAL_IES_PATTERN;
    private ChildPropertySetter childPropertySetter;
    private ClassNameResolver classNameResolver;
    private IdentifierResolver identifierResolver;
    private NewInstanceResolver newInstanceResolver;
    private ObjectFactory objectFactory;
    private ObjectBeanFactory objectBeanFactory;
    private ObjectRefFactory objectRefFactory;
    private ReferenceResolver referenceResolver;
    private RelationNameResolver relationNameResolver;
    private Map<String, Class> resolvedClasses;
    private ClassLoader classLoader;
    private boolean lazyReferencesAllowed;
    private List<NodeReference> lazyReferences;
    private String beanFactoryName;
    
    public ObjectGraphBuilder() {
        this.objectFactory = new ObjectFactory();
        this.objectBeanFactory = new ObjectBeanFactory();
        this.objectRefFactory = new ObjectRefFactory();
        this.resolvedClasses = new HashMap<String, Class>();
        this.lazyReferencesAllowed = true;
        this.lazyReferences = new ArrayList<NodeReference>();
        this.beanFactoryName = "bean";
        this.classNameResolver = new DefaultClassNameResolver();
        this.newInstanceResolver = new DefaultNewInstanceResolver();
        this.relationNameResolver = new DefaultRelationNameResolver();
        this.childPropertySetter = new DefaultChildPropertySetter();
        this.identifierResolver = new DefaultIdentifierResolver();
        this.referenceResolver = new DefaultReferenceResolver();
        this.addPostNodeCompletionDelegate(new Closure(this, this) {
            public void doCall(final ObjectGraphBuilder builder, final Object parent, final Object node) {
                if (parent == null) {
                    builder.resolveLazyReferences();
                    builder.dispose();
                }
            }
        });
    }
    
    public String getBeanFactoryName() {
        return this.beanFactoryName;
    }
    
    public ChildPropertySetter getChildPropertySetter() {
        return this.childPropertySetter;
    }
    
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public ClassNameResolver getClassNameResolver() {
        return this.classNameResolver;
    }
    
    public NewInstanceResolver getNewInstanceResolver() {
        return this.newInstanceResolver;
    }
    
    public RelationNameResolver getRelationNameResolver() {
        return this.relationNameResolver;
    }
    
    public boolean isLazyReferencesAllowed() {
        return this.lazyReferencesAllowed;
    }
    
    public void setBeanFactoryName(final String beanFactoryName) {
        this.beanFactoryName = beanFactoryName;
    }
    
    public void setChildPropertySetter(final Object childPropertySetter) {
        if (childPropertySetter instanceof ChildPropertySetter) {
            this.childPropertySetter = (ChildPropertySetter)childPropertySetter;
        }
        else if (childPropertySetter instanceof Closure) {
            final ObjectGraphBuilder self = this;
            this.childPropertySetter = new ChildPropertySetter() {
                public void setChild(final Object parent, final Object child, final String parentName, final String propertyName) {
                    final Closure cls = (Closure)childPropertySetter;
                    cls.setDelegate(self);
                    cls.call(new Object[] { parent, child, parentName, propertyName });
                }
            };
        }
        else {
            this.childPropertySetter = new DefaultChildPropertySetter();
        }
    }
    
    public void setClassLoader(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public void setClassNameResolver(final Object classNameResolver) {
        if (classNameResolver instanceof ClassNameResolver) {
            this.classNameResolver = (ClassNameResolver)classNameResolver;
        }
        else if (classNameResolver instanceof String) {
            this.classNameResolver = new ClassNameResolver() {
                public String resolveClassname(final String classname) {
                    return makeClassName((String)classNameResolver, classname);
                }
            };
        }
        else if (classNameResolver instanceof Closure) {
            final ObjectGraphBuilder self = this;
            this.classNameResolver = new ClassNameResolver() {
                public String resolveClassname(final String classname) {
                    final Closure cls = (Closure)classNameResolver;
                    cls.setDelegate(self);
                    return (String)cls.call(new Object[] { classname });
                }
            };
        }
        else if (classNameResolver instanceof Map) {
            final Map classNameResolverOptions = (Map)classNameResolver;
            final String resolverName = classNameResolverOptions.get("name");
            if (resolverName == null) {
                throw new RuntimeException("key 'name' not defined");
            }
            if (!"reflection".equals(resolverName)) {
                throw new RuntimeException("unknown class name resolver " + resolverName);
            }
            final String root = classNameResolverOptions.get("root");
            if (root == null) {
                throw new RuntimeException("key 'root' not defined");
            }
            this.classNameResolver = new ReflectionClassNameResolver(root);
        }
        else {
            this.classNameResolver = new DefaultClassNameResolver();
        }
    }
    
    public void setIdentifierResolver(final Object identifierResolver) {
        if (identifierResolver instanceof IdentifierResolver) {
            this.identifierResolver = (IdentifierResolver)identifierResolver;
        }
        else if (identifierResolver instanceof String) {
            this.identifierResolver = new IdentifierResolver() {
                public String getIdentifierFor(final String nodeName) {
                    return (String)identifierResolver;
                }
            };
        }
        else if (identifierResolver instanceof Closure) {
            final ObjectGraphBuilder self = this;
            this.identifierResolver = new IdentifierResolver() {
                public String getIdentifierFor(final String nodeName) {
                    final Closure cls = (Closure)identifierResolver;
                    cls.setDelegate(self);
                    return (String)cls.call(new Object[] { nodeName });
                }
            };
        }
        else {
            this.identifierResolver = new DefaultIdentifierResolver();
        }
    }
    
    public void setLazyReferencesAllowed(final boolean lazyReferencesAllowed) {
        this.lazyReferencesAllowed = lazyReferencesAllowed;
    }
    
    public void setNewInstanceResolver(final Object newInstanceResolver) {
        if (newInstanceResolver instanceof NewInstanceResolver) {
            this.newInstanceResolver = (NewInstanceResolver)newInstanceResolver;
        }
        else if (newInstanceResolver instanceof Closure) {
            final ObjectGraphBuilder self = this;
            this.newInstanceResolver = new NewInstanceResolver() {
                public Object newInstance(final Class klass, final Map attributes) throws InstantiationException, IllegalAccessException {
                    final Closure cls = (Closure)newInstanceResolver;
                    cls.setDelegate(self);
                    return cls.call(new Object[] { klass, attributes });
                }
            };
        }
        else {
            this.newInstanceResolver = new DefaultNewInstanceResolver();
        }
    }
    
    public void setReferenceResolver(final Object referenceResolver) {
        if (referenceResolver instanceof ReferenceResolver) {
            this.referenceResolver = (ReferenceResolver)referenceResolver;
        }
        else if (referenceResolver instanceof String) {
            this.referenceResolver = new ReferenceResolver() {
                public String getReferenceFor(final String nodeName) {
                    return (String)referenceResolver;
                }
            };
        }
        else if (referenceResolver instanceof Closure) {
            final ObjectGraphBuilder self = this;
            this.referenceResolver = new ReferenceResolver() {
                public String getReferenceFor(final String nodeName) {
                    final Closure cls = (Closure)referenceResolver;
                    cls.setDelegate(self);
                    return (String)cls.call(new Object[] { nodeName });
                }
            };
        }
        else {
            this.referenceResolver = new DefaultReferenceResolver();
        }
    }
    
    public void setRelationNameResolver(final RelationNameResolver relationNameResolver) {
        this.relationNameResolver = ((relationNameResolver != null) ? relationNameResolver : new DefaultRelationNameResolver());
    }
    
    @Override
    protected void postInstantiate(final Object name, final Map attributes, final Object node) {
        super.postInstantiate(name, attributes, node);
        final Map context = this.getContext();
        final String objectId = context.get("_OBJECT_ID_");
        if (objectId != null && node != null) {
            this.setVariable(objectId, node);
        }
    }
    
    @Override
    protected void preInstantiate(final Object name, final Map attributes, final Object value) {
        super.preInstantiate(name, attributes, value);
        final Map context = this.getContext();
        context.put("_OBJECT_ID_", attributes.remove(this.identifierResolver.getIdentifierFor((String)name)));
    }
    
    @Override
    protected Factory resolveFactory(final Object name, final Map attributes, final Object value) {
        final Factory factory = super.resolveFactory(name, attributes, value);
        if (factory != null) {
            return factory;
        }
        if (attributes.get(this.referenceResolver.getReferenceFor((String)name)) != null) {
            return this.objectRefFactory;
        }
        if (this.beanFactoryName != null && this.beanFactoryName.equals(name)) {
            return this.objectBeanFactory;
        }
        return this.objectFactory;
    }
    
    private void resolveLazyReferences() {
        if (!this.lazyReferencesAllowed) {
            return;
        }
        for (final NodeReference ref : this.lazyReferences) {
            if (ref.parent == null) {
                continue;
            }
            Object child = null;
            try {
                child = this.getProperty(ref.refId);
            }
            catch (MissingPropertyException ex) {}
            if (child == null) {
                throw new IllegalArgumentException("There is no valid node for reference " + ref.parentName + "." + ref.childName + "=" + ref.refId);
            }
            this.childPropertySetter.setChild(ref.parent, child, ref.parentName, this.relationNameResolver.resolveChildRelationName(ref.parentName, ref.parent, ref.childName, child));
            final String propertyName = this.relationNameResolver.resolveParentRelationName(ref.parentName, ref.parent, ref.childName, child);
            final MetaProperty metaProperty = InvokerHelper.getMetaClass(child).hasProperty(child, propertyName);
            if (metaProperty == null) {
                continue;
            }
            metaProperty.setProperty(child, ref.parent);
        }
    }
    
    private static String makeClassName(final String root, final String name) {
        return root + "." + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    
    static {
        PLURAL_IES_PATTERN = Pattern.compile(".*[^aeiouy]y", 2);
    }
    
    public static class DefaultChildPropertySetter implements ChildPropertySetter
    {
        public void setChild(final Object parent, final Object child, final String parentName, final String propertyName) {
            try {
                final Object property = InvokerHelper.getProperty(parent, propertyName);
                if (property != null && Collection.class.isAssignableFrom(property.getClass())) {
                    ((Collection)property).add(child);
                }
                else {
                    InvokerHelper.setProperty(parent, propertyName, child);
                }
            }
            catch (MissingPropertyException ex) {}
        }
    }
    
    public static class DefaultClassNameResolver implements ClassNameResolver
    {
        public String resolveClassname(final String classname) {
            if (classname.length() == 1) {
                return classname.toUpperCase();
            }
            return classname.substring(0, 1).toUpperCase() + classname.substring(1);
        }
    }
    
    public class ReflectionClassNameResolver implements ClassNameResolver
    {
        private final String root;
        
        public ReflectionClassNameResolver(final String root) {
            this.root = root;
        }
        
        public String resolveClassname(final String classname) {
            final Object currentNode = ObjectGraphBuilder.this.getContext().get("_CURRENT_NODE_");
            if (currentNode == null) {
                return makeClassName(this.root, classname);
            }
            try {
                Class klass = currentNode.getClass().getDeclaredField(classname).getType();
                if (Collection.class.isAssignableFrom(klass)) {
                    final Type type = currentNode.getClass().getDeclaredField(classname).getGenericType();
                    if (!(type instanceof ParameterizedType)) {
                        throw new RuntimeException("collection field " + classname + " must be genericised");
                    }
                    final ParameterizedType ptype = (ParameterizedType)type;
                    final Type[] actualTypeArguments = ptype.getActualTypeArguments();
                    if (actualTypeArguments.length != 1) {
                        throw new RuntimeException("can't determine class name for collection field " + classname + " with multiple generics");
                    }
                    final Type typeArgument = actualTypeArguments[0];
                    if (!(typeArgument instanceof Class)) {
                        throw new RuntimeException("can't instantiate collection field " + classname + " elements as they aren't a class");
                    }
                    klass = (Class)actualTypeArguments[0];
                }
                return klass.getName();
            }
            catch (NoSuchFieldException e) {
                throw new RuntimeException("can't find field " + classname + " for node class " + currentNode.getClass().getName(), e);
            }
        }
    }
    
    public static class DefaultIdentifierResolver implements IdentifierResolver
    {
        public String getIdentifierFor(final String nodeName) {
            return "id";
        }
    }
    
    public static class DefaultNewInstanceResolver implements NewInstanceResolver
    {
        public Object newInstance(final Class klass, final Map attributes) throws InstantiationException, IllegalAccessException {
            return klass.newInstance();
        }
    }
    
    public static class DefaultReferenceResolver implements ReferenceResolver
    {
        public String getReferenceFor(final String nodeName) {
            return "refId";
        }
    }
    
    public static class DefaultRelationNameResolver implements RelationNameResolver
    {
        public String resolveChildRelationName(final String parentName, final Object parent, final String childName, final Object child) {
            final boolean matchesIESRule = ObjectGraphBuilder.PLURAL_IES_PATTERN.matcher(childName).matches();
            final String childNamePlural = matchesIESRule ? (childName.substring(0, childName.length() - 1) + "ies") : (childName + "s");
            final MetaProperty metaProperty = InvokerHelper.getMetaClass(parent).hasProperty(parent, childNamePlural);
            return (metaProperty != null) ? childNamePlural : childName;
        }
        
        public String resolveParentRelationName(final String parentName, final Object parent, final String childName, final Object child) {
            return parentName;
        }
    }
    
    private static class ObjectFactory extends AbstractFactory
    {
        public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map properties) throws InstantiationException, IllegalAccessException {
            final ObjectGraphBuilder ogbuilder = (ObjectGraphBuilder)builder;
            final String classname = ogbuilder.classNameResolver.resolveClassname((String)name);
            final Class klass = this.resolveClass(builder, classname, name, value, properties);
            final Map context = builder.getContext();
            context.put("_NODE_NAME_", name);
            context.put("_NODE_CLASS_", klass);
            return this.resolveInstance(builder, name, value, klass, properties);
        }
        
        protected Class resolveClass(final FactoryBuilderSupport builder, final String classname, final Object name, final Object value, final Map properties) throws InstantiationException, IllegalAccessException {
            final ObjectGraphBuilder ogbuilder = (ObjectGraphBuilder)builder;
            Class klass = ogbuilder.resolvedClasses.get(classname);
            if (klass == null) {
                klass = this.loadClass(ogbuilder.classLoader, classname);
                if (klass == null) {
                    klass = this.loadClass(ogbuilder.getClass().getClassLoader(), classname);
                }
                if (klass == null) {
                    try {
                        klass = Class.forName(classname);
                    }
                    catch (ClassNotFoundException ex) {}
                }
                if (klass == null) {
                    klass = this.loadClass(Thread.currentThread().getContextClassLoader(), classname);
                }
                if (klass == null) {
                    throw new RuntimeException(new ClassNotFoundException(classname));
                }
                ogbuilder.resolvedClasses.put(classname, klass);
            }
            return klass;
        }
        
        protected Object resolveInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Class klass, final Map properties) throws InstantiationException, IllegalAccessException {
            final ObjectGraphBuilder ogbuilder = (ObjectGraphBuilder)builder;
            if (value != null && klass.isAssignableFrom(value.getClass())) {
                return value;
            }
            return ogbuilder.newInstanceResolver.newInstance(klass, properties);
        }
        
        @Override
        public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
            if (child == null) {
                return;
            }
            final ObjectGraphBuilder ogbuilder = (ObjectGraphBuilder)builder;
            if (parent != null) {
                final Map context = ogbuilder.getContext();
                final Map parentContext = ogbuilder.getParentContext();
                String parentName = null;
                final String childName = context.get("_NODE_NAME_");
                if (parentContext != null) {
                    parentName = parentContext.get("_NODE_NAME_");
                }
                final String propertyName = ogbuilder.relationNameResolver.resolveParentRelationName(parentName, parent, childName, child);
                final MetaProperty metaProperty = InvokerHelper.getMetaClass(child).hasProperty(child, propertyName);
                if (metaProperty != null) {
                    metaProperty.setProperty(child, parent);
                }
            }
        }
        
        @Override
        public void setParent(final FactoryBuilderSupport builder, final Object parent, final Object child) {
            if (child == null) {
                return;
            }
            final ObjectGraphBuilder ogbuilder = (ObjectGraphBuilder)builder;
            if (parent != null) {
                final Map context = ogbuilder.getContext();
                final Map parentContext = ogbuilder.getParentContext();
                String parentName = null;
                final String childName = context.get("_NODE_NAME_");
                if (parentContext != null) {
                    parentName = parentContext.get("_NODE_NAME_");
                }
                ogbuilder.childPropertySetter.setChild(parent, child, parentName, ogbuilder.relationNameResolver.resolveChildRelationName(parentName, parent, childName, child));
            }
        }
        
        protected Class loadClass(final ClassLoader classLoader, final String classname) {
            if (classLoader == null || classname == null) {
                return null;
            }
            try {
                return classLoader.loadClass(classname);
            }
            catch (ClassNotFoundException e) {
                return null;
            }
        }
    }
    
    private static class ObjectBeanFactory extends ObjectFactory
    {
        @Override
        public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map properties) throws InstantiationException, IllegalAccessException {
            if (value == null) {
                return super.newInstance(builder, name, value, properties);
            }
            Object bean = null;
            Class klass = null;
            final Map context = builder.getContext();
            if (value instanceof String || value instanceof GString) {
                throw new IllegalArgumentException("ObjectGraphBuilder." + ((ObjectGraphBuilder)builder).getBeanFactoryName() + "() does not accept String nor GString as value.");
            }
            if (value instanceof Class) {
                klass = (Class)value;
                bean = this.resolveInstance(builder, name, value, klass, properties);
            }
            else {
                klass = value.getClass();
                bean = value;
            }
            String nodename = klass.getSimpleName();
            if (nodename.length() > 1) {
                nodename = nodename.substring(0, 1).toLowerCase() + nodename.substring(1);
            }
            else {
                nodename = nodename.toLowerCase();
            }
            context.put("_NODE_NAME_", nodename);
            context.put("_NODE_CLASS_", klass);
            return bean;
        }
    }
    
    private static class ObjectRefFactory extends ObjectFactory
    {
        @Override
        public boolean isLeaf() {
            return true;
        }
        
        @Override
        public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map properties) throws InstantiationException, IllegalAccessException {
            final ObjectGraphBuilder ogbuilder = (ObjectGraphBuilder)builder;
            final String refProperty = ogbuilder.referenceResolver.getReferenceFor((String)name);
            final Object refId = properties.remove(refProperty);
            Object object = null;
            Boolean lazy = Boolean.FALSE;
            if (refId instanceof String) {
                try {
                    object = ogbuilder.getProperty((String)refId);
                }
                catch (MissingPropertyException ex) {}
                if (object == null) {
                    if (!ogbuilder.isLazyReferencesAllowed()) {
                        throw new IllegalArgumentException("There is no previous node with " + ogbuilder.identifierResolver.getIdentifierFor((String)name) + "=" + refId);
                    }
                    lazy = Boolean.TRUE;
                }
            }
            else {
                object = refId;
            }
            if (!properties.isEmpty()) {
                throw new IllegalArgumentException("You can not modify the properties of a referenced object.");
            }
            final Map context = ogbuilder.getContext();
            context.put("_NODE_NAME_", name);
            context.put("_LAZY_REF_", lazy);
            if (lazy) {
                final Map parentContext = ogbuilder.getParentContext();
                Object parent = null;
                String parentName = null;
                final String childName = (String)name;
                if (parentContext != null) {
                    parent = context.get("_CURRENT_NODE_");
                    parentName = parentContext.get("_NODE_NAME_");
                }
                ogbuilder.lazyReferences.add(new NodeReference(parent, parentName, childName, (String)refId));
            }
            else {
                context.put("_NODE_CLASS_", object.getClass());
            }
            return object;
        }
        
        @Override
        public void setChild(final FactoryBuilderSupport builder, final Object parent, final Object child) {
            final Boolean lazy = builder.getContext().get("_LAZY_REF_");
            if (!lazy) {
                super.setChild(builder, parent, child);
            }
        }
        
        @Override
        public void setParent(final FactoryBuilderSupport builder, final Object parent, final Object child) {
            final Boolean lazy = builder.getContext().get("_LAZY_REF_");
            if (!lazy) {
                super.setParent(builder, parent, child);
            }
        }
    }
    
    private static class NodeReference
    {
        private final Object parent;
        private final String parentName;
        private final String childName;
        private final String refId;
        
        private NodeReference(final Object parent, final String parentName, final String childName, final String refId) {
            this.parent = parent;
            this.parentName = parentName;
            this.childName = childName;
            this.refId = refId;
        }
        
        @Override
        public String toString() {
            return "[parentName=" + this.parentName + ", childName=" + this.childName + ", refId=" + this.refId + "]";
        }
    }
    
    public interface ClassNameResolver
    {
        String resolveClassname(final String p0);
    }
    
    public interface NewInstanceResolver
    {
        Object newInstance(final Class p0, final Map p1) throws InstantiationException, IllegalAccessException;
    }
    
    public interface RelationNameResolver
    {
        String resolveChildRelationName(final String p0, final Object p1, final String p2, final Object p3);
        
        String resolveParentRelationName(final String p0, final Object p1, final String p2, final Object p3);
    }
    
    public interface ChildPropertySetter
    {
        void setChild(final Object p0, final Object p1, final String p2, final String p3);
    }
    
    public interface ReferenceResolver
    {
        String getReferenceFor(final String p0);
    }
    
    public interface IdentifierResolver
    {
        String getIdentifierFor(final String p0);
    }
}
