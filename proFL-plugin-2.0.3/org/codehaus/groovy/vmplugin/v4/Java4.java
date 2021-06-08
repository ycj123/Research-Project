// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin.v4;

import org.codehaus.groovy.ast.Parameter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.CompileUnit;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.vmplugin.VMPlugin;

public class Java4 implements VMPlugin
{
    private static Class[] EMPTY_CLASS_ARRAY;
    
    public void setAdditionalClassInformation(final ClassNode c) {
    }
    
    public Class[] getPluginDefaultGroovyMethods() {
        return Java4.EMPTY_CLASS_ARRAY;
    }
    
    public void configureAnnotation(final AnnotationNode an) {
    }
    
    public void configureClassNode(final CompileUnit compileUnit, final ClassNode classNode) {
        final Class clazz = classNode.getTypeClass();
        final Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            classNode.addField(fields[i].getName(), fields[i].getModifiers(), classNode, null);
        }
        final Method[] methods = clazz.getDeclaredMethods();
        for (int j = 0; j < methods.length; ++j) {
            final Method m = methods[j];
            final MethodNode mn = new MethodNode(m.getName(), m.getModifiers(), ClassHelper.make(m.getReturnType()), this.createParameters(m.getParameterTypes()), ClassHelper.make((Class[])m.getExceptionTypes()), null);
            classNode.addMethod(mn);
        }
        final Constructor[] constructors = clazz.getDeclaredConstructors();
        for (int k = 0; k < constructors.length; ++k) {
            final Constructor ctor = constructors[k];
            classNode.addConstructor(ctor.getModifiers(), this.createParameters(ctor.getParameterTypes()), ClassHelper.make((Class[])ctor.getExceptionTypes()), null);
        }
        final Class sc = clazz.getSuperclass();
        if (sc != null) {
            classNode.setUnresolvedSuperClass(this.getPrimaryClassNode(compileUnit, sc));
        }
        this.buildInterfaceTypes(compileUnit, classNode, clazz);
    }
    
    private ClassNode getPrimaryClassNode(final CompileUnit compileUnit, final Class clazz) {
        ClassNode result = null;
        if (compileUnit != null) {
            result = compileUnit.getClass(clazz.getName());
        }
        if (result == null) {
            result = ClassHelper.make(clazz);
        }
        return result;
    }
    
    private void buildInterfaceTypes(final CompileUnit compileUnit, final ClassNode classNode, final Class c) {
        final Class[] interfaces = c.getInterfaces();
        final ClassNode[] ret = new ClassNode[interfaces.length];
        for (int i = 0; i < interfaces.length; ++i) {
            ret[i] = this.getPrimaryClassNode(compileUnit, interfaces[i]);
        }
        classNode.setInterfaces(ret);
    }
    
    private Parameter[] createParameters(final Class[] types) {
        Parameter[] parameters = Parameter.EMPTY_ARRAY;
        final int size = types.length;
        if (size > 0) {
            parameters = new Parameter[size];
            for (int i = 0; i < size; ++i) {
                parameters[i] = this.createParameter(types[i], i);
            }
        }
        return parameters;
    }
    
    private Parameter createParameter(final Class parameterType, final int idx) {
        return new Parameter(ClassHelper.make(parameterType), "param" + idx);
    }
    
    static {
        Java4.EMPTY_CLASS_ARRAY = new Class[0];
    }
}
