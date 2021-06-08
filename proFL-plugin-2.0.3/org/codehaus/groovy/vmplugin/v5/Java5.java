// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin.v5;

import org.codehaus.groovy.ast.PackageNode;
import java.lang.reflect.Constructor;
import org.codehaus.groovy.ast.Parameter;
import java.lang.reflect.Field;
import org.codehaus.groovy.ast.CompileUnit;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.MethodNode;
import java.lang.reflect.Array;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.codehaus.groovy.ast.expr.ClassExpression;
import java.util.List;
import java.util.Iterator;
import java.lang.annotation.ElementType;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ListExpression;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import java.lang.annotation.Retention;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import java.lang.annotation.Annotation;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.GroovyBugError;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.lang.reflect.Type;
import org.codehaus.groovy.ast.GenericsType;
import java.lang.reflect.TypeVariable;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.vmplugin.VMPlugin;

public class Java5 implements VMPlugin
{
    private static final Class[] PLUGIN_DGM;
    
    public void setAdditionalClassInformation(final ClassNode cn) {
        this.setGenericsTypes(cn);
    }
    
    private void setGenericsTypes(final ClassNode cn) {
        final TypeVariable[] tvs = cn.getTypeClass().getTypeParameters();
        final GenericsType[] gts = this.configureTypeVariable(tvs);
        cn.setGenericsTypes(gts);
    }
    
    private GenericsType[] configureTypeVariable(final TypeVariable[] tvs) {
        if (tvs.length == 0) {
            return null;
        }
        final GenericsType[] gts = new GenericsType[tvs.length];
        for (int i = 0; i < tvs.length; ++i) {
            gts[i] = this.configureTypeVariableDefinition(tvs[i]);
        }
        return gts;
    }
    
    private GenericsType configureTypeVariableDefinition(final TypeVariable tv) {
        final ClassNode base = this.configureTypeVariableReference(tv);
        final ClassNode redirect = base.redirect();
        base.setRedirect(null);
        final Type[] tBounds = tv.getBounds();
        GenericsType gt;
        if (tBounds.length == 0) {
            gt = new GenericsType(base);
        }
        else {
            final ClassNode[] cBounds = this.configureTypes(tBounds);
            gt = new GenericsType(base, cBounds, null);
            gt.setName(base.getName());
            gt.setPlaceholder(true);
        }
        base.setRedirect(redirect);
        return gt;
    }
    
    private ClassNode[] configureTypes(final Type[] types) {
        if (types.length == 0) {
            return null;
        }
        final ClassNode[] nodes = new ClassNode[types.length];
        for (int i = 0; i < types.length; ++i) {
            nodes[i] = this.configureType(types[i]);
        }
        return nodes;
    }
    
    private ClassNode configureType(final Type type) {
        if (type instanceof WildcardType) {
            return this.configureWildcardType((WildcardType)type);
        }
        if (type instanceof ParameterizedType) {
            return this.configureParameterizedType((ParameterizedType)type);
        }
        if (type instanceof GenericArrayType) {
            return this.configureGenericArray((GenericArrayType)type);
        }
        if (type instanceof TypeVariable) {
            return this.configureTypeVariableReference((TypeVariable)type);
        }
        if (type instanceof Class) {
            return this.configureClass((Class)type);
        }
        throw new GroovyBugError("unknown type: " + type + " := " + type.getClass());
    }
    
    private ClassNode configureClass(final Class c) {
        if (c.isPrimitive()) {
            return ClassHelper.make(c);
        }
        return ClassHelper.makeWithoutCaching(c, false);
    }
    
    private ClassNode configureGenericArray(final GenericArrayType genericArrayType) {
        final Type component = genericArrayType.getGenericComponentType();
        final ClassNode node = this.configureType(component);
        return node.makeArray();
    }
    
    private ClassNode configureWildcardType(final WildcardType wildcardType) {
        final ClassNode base = ClassHelper.makeWithoutCaching("?");
        base.setRedirect(ClassHelper.OBJECT_TYPE);
        final ClassNode[] lowers = this.configureTypes(wildcardType.getLowerBounds());
        ClassNode lower = null;
        if (lower != null) {
            lower = lowers[0];
        }
        final ClassNode[] upper = this.configureTypes(wildcardType.getUpperBounds());
        final GenericsType t = new GenericsType(base, upper, lower);
        t.setWildcard(true);
        final ClassNode ref = ClassHelper.makeWithoutCaching(Object.class, false);
        ref.setGenericsTypes(new GenericsType[] { t });
        return ref;
    }
    
    private ClassNode configureParameterizedType(final ParameterizedType parameterizedType) {
        final ClassNode base = this.configureType(parameterizedType.getRawType());
        final GenericsType[] gts = this.configureTypeArguments(parameterizedType.getActualTypeArguments());
        base.setGenericsTypes(gts);
        return base;
    }
    
    private ClassNode configureTypeVariableReference(final TypeVariable tv) {
        final ClassNode cn = ClassHelper.makeWithoutCaching(tv.getName());
        cn.setGenericsPlaceHolder(true);
        final ClassNode cn2 = ClassHelper.makeWithoutCaching(tv.getName());
        cn2.setGenericsPlaceHolder(true);
        final GenericsType[] gts = { new GenericsType(cn2) };
        cn.setGenericsTypes(gts);
        cn.setRedirect(ClassHelper.OBJECT_TYPE);
        return cn;
    }
    
    private GenericsType[] configureTypeArguments(final Type[] ta) {
        if (ta.length == 0) {
            return null;
        }
        final GenericsType[] gts = new GenericsType[ta.length];
        for (int i = 0; i < ta.length; ++i) {
            final ClassNode t = this.configureType(ta[i]);
            if (ta[i] instanceof WildcardType) {
                final GenericsType[] gen = t.getGenericsTypes();
                gts[i] = gen[0];
            }
            else {
                gts[i] = new GenericsType(t);
            }
        }
        return gts;
    }
    
    public Class[] getPluginDefaultGroovyMethods() {
        return Java5.PLUGIN_DGM;
    }
    
    private void setAnnotationMetaData(final Annotation[] annotations, final AnnotatedNode an) {
        for (final Annotation annotation : annotations) {
            final AnnotationNode node = new AnnotationNode(ClassHelper.make(annotation.annotationType()));
            this.configureAnnotation(node, annotation);
            an.addAnnotation(node);
        }
    }
    
    private void configureAnnotationFromDefinition(final AnnotationNode definition, final AnnotationNode root) {
        final ClassNode type = definition.getClassNode();
        if (!type.isResolved()) {
            return;
        }
        final Class clazz = type.getTypeClass();
        if (clazz == Retention.class) {
            final Expression exp = definition.getMember("value");
            if (!(exp instanceof PropertyExpression)) {
                return;
            }
            final PropertyExpression pe = (PropertyExpression)exp;
            final String name = pe.getPropertyAsString();
            final RetentionPolicy policy = RetentionPolicy.valueOf(name);
            this.setRetentionPolicy(policy, root);
        }
        else if (clazz == Target.class) {
            final Expression exp = definition.getMember("value");
            if (!(exp instanceof ListExpression)) {
                return;
            }
            final ListExpression le = (ListExpression)exp;
            int bitmap = 0;
            for (final Expression e : le.getExpressions()) {
                final PropertyExpression element = (PropertyExpression)e;
                final String name2 = element.getPropertyAsString();
                final ElementType value = ElementType.valueOf(name2);
                bitmap |= this.getElementCode(value);
            }
            root.setAllowedTargets(bitmap);
        }
    }
    
    public void configureAnnotation(final AnnotationNode node) {
        final ClassNode type = node.getClassNode();
        final List<AnnotationNode> annotations = type.getAnnotations();
        for (final AnnotationNode an : annotations) {
            this.configureAnnotationFromDefinition(an, node);
        }
        this.configureAnnotationFromDefinition(node, node);
    }
    
    private void configureAnnotation(final AnnotationNode node, final Annotation annotation) {
        final Class type = annotation.annotationType();
        if (type == Retention.class) {
            final Retention r = (Retention)annotation;
            final RetentionPolicy value = r.value();
            this.setRetentionPolicy(value, node);
            node.setMember("value", new PropertyExpression(new ClassExpression(ClassHelper.makeWithoutCaching(RetentionPolicy.class, false)), value.toString()));
        }
        else if (type == Target.class) {
            final Target t = (Target)annotation;
            final ElementType[] elements = t.value();
            final ListExpression elementExprs = new ListExpression();
            for (final ElementType element : elements) {
                elementExprs.addExpression(new PropertyExpression(new ClassExpression(ClassHelper.ELEMENT_TYPE_TYPE), element.name()));
            }
            node.setMember("value", elementExprs);
        }
        else {
            final Method[] declaredMethods = type.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; ++i) {
                final Method declaredMethod = declaredMethods[i];
                try {
                    final Object value2 = declaredMethod.invoke(annotation, new Object[0]);
                    final Expression valueExpression = this.annotationValueToExpression(value2);
                    if (valueExpression != null) {
                        node.setMember(declaredMethod.getName(), valueExpression);
                    }
                }
                catch (IllegalAccessException e) {}
                catch (InvocationTargetException ex) {}
            }
        }
    }
    
    private Expression annotationValueToExpression(final Object value) {
        if (value == null || value instanceof String || value instanceof Number || value instanceof Character || value instanceof Boolean) {
            return new ConstantExpression(value);
        }
        if (value instanceof Class) {
            return new ClassExpression(ClassHelper.makeWithoutCaching((Class)value));
        }
        if (value.getClass().isArray()) {
            final ListExpression elementExprs = new ListExpression();
            for (int len = Array.getLength(value), i = 0; i != len; ++i) {
                elementExprs.addExpression(this.annotationValueToExpression(Array.get(value, i)));
            }
            return elementExprs;
        }
        return null;
    }
    
    private void setRetentionPolicy(final RetentionPolicy value, final AnnotationNode node) {
        switch (value) {
            case RUNTIME: {
                node.setRuntimeRetention(true);
                break;
            }
            case SOURCE: {
                node.setSourceRetention(true);
                break;
            }
            case CLASS: {
                node.setClassRetention(true);
                break;
            }
            default: {
                throw new GroovyBugError("unsupported Retention " + value);
            }
        }
    }
    
    private int getElementCode(final ElementType value) {
        switch (value) {
            case TYPE: {
                return 1;
            }
            case CONSTRUCTOR: {
                return 2;
            }
            case METHOD: {
                return 4;
            }
            case FIELD: {
                return 8;
            }
            case PARAMETER: {
                return 16;
            }
            case LOCAL_VARIABLE: {
                return 32;
            }
            case ANNOTATION_TYPE: {
                return 64;
            }
            case PACKAGE: {
                return 128;
            }
            default: {
                throw new GroovyBugError("unsupported Target " + value);
            }
        }
    }
    
    private void setMethodDefaultValue(final MethodNode mn, final Method m) {
        final Object defaultValue = m.getDefaultValue();
        mn.setCode(new ReturnStatement(new ConstantExpression(defaultValue)));
        mn.setAnnotationDefault(true);
    }
    
    public void configureClassNode(final CompileUnit compileUnit, final ClassNode classNode) {
        final Class clazz = classNode.getTypeClass();
        final Field[] arr$;
        final Field[] fields = arr$ = clazz.getDeclaredFields();
        for (final Field f : arr$) {
            final ClassNode ret = this.makeClassNode(compileUnit, f.getGenericType(), f.getType());
            classNode.addField(f.getName(), f.getModifiers(), ret, null);
        }
        final Method[] arr$2;
        final Method[] methods = arr$2 = clazz.getDeclaredMethods();
        for (final Method m : arr$2) {
            final ClassNode ret2 = this.makeClassNode(compileUnit, m.getGenericReturnType(), m.getReturnType());
            final Parameter[] params = this.makeParameters(compileUnit, m.getGenericParameterTypes(), m.getParameterTypes());
            final ClassNode[] exceptions = this.makeClassNodes(compileUnit, m.getGenericExceptionTypes(), m.getExceptionTypes());
            final MethodNode mn = new MethodNode(m.getName(), m.getModifiers(), ret2, params, exceptions, null);
            this.setMethodDefaultValue(mn, m);
            this.setAnnotationMetaData(m.getAnnotations(), mn);
            mn.setGenericsTypes(this.configureTypeVariable(m.getTypeParameters()));
            classNode.addMethod(mn);
        }
        final Constructor[] arr$3;
        final Constructor[] constructors = arr$3 = clazz.getDeclaredConstructors();
        for (final Constructor ctor : arr$3) {
            final Parameter[] params = this.makeParameters(compileUnit, ctor.getGenericParameterTypes(), ctor.getParameterTypes());
            final ClassNode[] exceptions = this.makeClassNodes(compileUnit, ctor.getGenericExceptionTypes(), ctor.getExceptionTypes());
            classNode.addConstructor(ctor.getModifiers(), params, exceptions, null);
        }
        final Class sc = clazz.getSuperclass();
        if (sc != null) {
            classNode.setUnresolvedSuperClass(this.makeClassNode(compileUnit, clazz.getGenericSuperclass(), sc));
        }
        this.makeInterfaceTypes(compileUnit, classNode, clazz);
        this.setAnnotationMetaData(classNode.getTypeClass().getAnnotations(), classNode);
        final PackageNode packageNode = classNode.getPackage();
        if (packageNode != null) {
            this.setAnnotationMetaData(classNode.getTypeClass().getPackage().getAnnotations(), packageNode);
        }
    }
    
    private void makeInterfaceTypes(final CompileUnit cu, final ClassNode classNode, final Class clazz) {
        final Type[] interfaceTypes = clazz.getGenericInterfaces();
        if (interfaceTypes.length == 0) {
            classNode.setInterfaces(ClassNode.EMPTY_ARRAY);
        }
        else {
            final Class[] interfaceClasses = clazz.getInterfaces();
            final ClassNode[] ret = new ClassNode[interfaceTypes.length];
            for (int i = 0; i < interfaceTypes.length; ++i) {
                ret[i] = this.makeClassNode(cu, interfaceTypes[i], interfaceClasses[i]);
            }
            classNode.setInterfaces(ret);
        }
    }
    
    private ClassNode[] makeClassNodes(final CompileUnit cu, final Type[] types, final Class[] cls) {
        final ClassNode[] nodes = new ClassNode[types.length];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = this.makeClassNode(cu, types[i], cls[i]);
        }
        return nodes;
    }
    
    private ClassNode makeClassNode(final CompileUnit cu, final Type t, final Class c) {
        ClassNode back = null;
        if (cu != null) {
            back = cu.getClass(c.getName());
        }
        if (back == null) {
            back = ClassHelper.make(c);
        }
        if (!(t instanceof Class)) {
            final ClassNode front = this.configureType(t);
            front.setRedirect(back);
            return front;
        }
        return back;
    }
    
    private Parameter[] makeParameters(final CompileUnit cu, final Type[] types, final Class[] cls) {
        Parameter[] params = Parameter.EMPTY_ARRAY;
        if (types.length > 0) {
            params = new Parameter[types.length];
            for (int i = 0; i < params.length; ++i) {
                params[i] = this.makeParameter(cu, types[i], cls[i], i);
            }
        }
        return params;
    }
    
    private Parameter makeParameter(final CompileUnit cu, final Type type, final Class cl, final int idx) {
        final ClassNode cn = this.makeClassNode(cu, type, cl);
        return new Parameter(cn, "param" + idx);
    }
    
    static {
        PLUGIN_DGM = new Class[] { PluginDefaultGroovyMethods.class };
    }
}
