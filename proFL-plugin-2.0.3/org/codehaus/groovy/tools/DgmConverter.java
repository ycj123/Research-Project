// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.reflection.CachedClass;
import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.MethodVisitor;
import java.io.IOException;
import java.util.List;
import java.io.FileOutputStream;
import groovyjarjarasm.asm.ClassWriter;
import org.codehaus.groovy.reflection.GeneratedMetaMethod;
import java.util.Collection;
import java.util.Collections;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.CachedMethod;
import java.util.ArrayList;
import org.codehaus.groovy.runtime.ProcessGroovyMethods;
import org.codehaus.groovy.runtime.DateGroovyMethods;
import org.codehaus.groovy.runtime.EncodingGroovyMethods;
import org.codehaus.groovy.runtime.XmlGroovyMethods;
import org.codehaus.groovy.runtime.SqlGroovyMethods;
import org.codehaus.groovy.runtime.SwingGroovyMethods;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.classgen.BytecodeHelper;
import groovyjarjarasm.asm.Opcodes;

public class DgmConverter implements Opcodes
{
    private static BytecodeHelper helper;
    
    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        final Class[] classes = { DefaultGroovyMethods.class, SwingGroovyMethods.class, SqlGroovyMethods.class, XmlGroovyMethods.class, EncodingGroovyMethods.class, DateGroovyMethods.class, ProcessGroovyMethods.class };
        final List<CachedMethod> cachedMethodsList = new ArrayList<CachedMethod>();
        for (final Class aClass : classes) {
            Collections.addAll(cachedMethodsList, ReflectionCache.getCachedClass(aClass).getMethods());
        }
        final CachedMethod[] cachedMethods = cachedMethodsList.toArray(new CachedMethod[cachedMethodsList.size()]);
        final List<GeneratedMetaMethod.DgmMethodRecord> records = new ArrayList<GeneratedMetaMethod.DgmMethodRecord>();
        int i = 0;
        int cur = 0;
        while (i < cachedMethods.length) {
            final CachedMethod method = cachedMethods[i];
            if (method.isStatic()) {
                if (method.isPublic()) {
                    if (method.getCachedMethod().getAnnotation(Deprecated.class) == null) {
                        if (method.getParameterTypes().length != 0) {
                            final Class returnType = method.getReturnType();
                            final String className = "org/codehaus/groovy/runtime/dgm$" + cur;
                            final GeneratedMetaMethod.DgmMethodRecord record = new GeneratedMetaMethod.DgmMethodRecord();
                            records.add(record);
                            record.methodName = method.getName();
                            record.returnType = method.getReturnType();
                            record.parameters = method.getNativeParameterTypes();
                            record.className = className;
                            final ClassWriter cw = new ClassWriter(1);
                            cw.visit(47, 1, className, null, "org/codehaus/groovy/reflection/GeneratedMetaMethod", null);
                            createConstructor(cw);
                            final String methodDescriptor = BytecodeHelper.getMethodDescriptor(returnType, method.getNativeParameterTypes());
                            createInvokeMethod(method, cw, returnType, methodDescriptor);
                            createDoMethodInvokeMethod(method, cw, className, returnType, methodDescriptor);
                            createIsValidMethodMethod(method, cw, className);
                            cw.visitEnd();
                            final byte[] bytes = cw.toByteArray();
                            final FileOutputStream fileOutputStream = new FileOutputStream("target/classes/" + className + ".class");
                            fileOutputStream.write(bytes);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            ++cur;
                        }
                    }
                }
            }
            ++i;
        }
        GeneratedMetaMethod.DgmMethodRecord.saveDgmInfo(records, "target/classes/META-INF/dgminfo");
    }
    
    private static void createConstructor(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(1, "<init>", "(Ljava/lang/String;Lorg/codehaus/groovy/reflection/CachedClass;Ljava/lang/Class;[Ljava/lang/Class;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitVarInsn(25, 1);
        mv.visitVarInsn(25, 2);
        mv.visitVarInsn(25, 3);
        mv.visitVarInsn(25, 4);
        mv.visitMethodInsn(183, "org/codehaus/groovy/reflection/GeneratedMetaMethod", "<init>", "(Ljava/lang/String;Lorg/codehaus/groovy/reflection/CachedClass;Ljava/lang/Class;[Ljava/lang/Class;)V");
        mv.visitInsn(177);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private static void createIsValidMethodMethod(final CachedMethod method, final ClassWriter cw, final String className) {
        if (method.getParamsCount() == 2 && method.getParameterTypes()[0].isNumber && method.getParameterTypes()[1].isNumber) {
            final MethodVisitor mv = cw.visitMethod(1, "isValidMethod", "([Ljava/lang/Class;)Z", null, null);
            mv.visitCode();
            mv.visitVarInsn(25, 1);
            final Label l0 = new Label();
            mv.visitJumpInsn(198, l0);
            mv.visitVarInsn(25, 0);
            mv.visitMethodInsn(182, className, "getParameterTypes", "()[Lorg/codehaus/groovy/reflection/CachedClass;");
            mv.visitInsn(3);
            mv.visitInsn(50);
            mv.visitVarInsn(25, 1);
            mv.visitInsn(3);
            mv.visitInsn(50);
            mv.visitMethodInsn(182, "org/codehaus/groovy/reflection/CachedClass", "isAssignableFrom", "(Ljava/lang/Class;)Z");
            final Label l2 = new Label();
            mv.visitJumpInsn(153, l2);
            mv.visitLabel(l0);
            mv.visitInsn(4);
            final Label l3 = new Label();
            mv.visitJumpInsn(167, l3);
            mv.visitLabel(l2);
            mv.visitInsn(3);
            mv.visitLabel(l3);
            mv.visitInsn(172);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
    }
    
    private static void createDoMethodInvokeMethod(final CachedMethod method, final ClassWriter cw, final String className, final Class returnType, final String methodDescriptor) {
        final MethodVisitor mv = cw.visitMethod(17, "doMethodInvoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        DgmConverter.helper = new BytecodeHelper(mv);
        mv.visitCode();
        if (method.getParamsCount() == 2 && method.getParameterTypes()[0].isNumber && method.getParameterTypes()[1].isNumber) {
            mv.visitVarInsn(25, 1);
            DgmConverter.helper.doCast(method.getParameterTypes()[0].getTheClass());
            mv.visitVarInsn(25, 0);
            mv.visitMethodInsn(182, className, "getParameterTypes", "()[Lorg/codehaus/groovy/reflection/CachedClass;");
            mv.visitInsn(3);
            mv.visitInsn(50);
            mv.visitVarInsn(25, 2);
            mv.visitInsn(3);
            mv.visitInsn(50);
            mv.visitMethodInsn(182, "org/codehaus/groovy/reflection/CachedClass", "coerceArgument", "(Ljava/lang/Object;)Ljava/lang/Object;");
            final Class type = method.getParameterTypes()[1].getTheClass();
            if (type.isPrimitive()) {
                DgmConverter.helper.unbox(type);
            }
            else {
                DgmConverter.helper.doCast(type);
            }
        }
        else {
            mv.visitVarInsn(25, 0);
            mv.visitVarInsn(25, 2);
            mv.visitMethodInsn(182, className, "coerceArgumentsToClasses", "([Ljava/lang/Object;)[Ljava/lang/Object;");
            mv.visitVarInsn(58, 2);
            mv.visitVarInsn(25, 1);
            DgmConverter.helper.doCast(method.getParameterTypes()[0].getTheClass());
            loadParameters(method, 2, mv);
        }
        mv.visitMethodInsn(184, BytecodeHelper.getClassInternalName(method.getDeclaringClass().getTheClass()), method.getName(), methodDescriptor);
        DgmConverter.helper.box(returnType);
        if (method.getReturnType() == Void.TYPE) {
            mv.visitInsn(1);
        }
        mv.visitInsn(176);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private static void createInvokeMethod(final CachedMethod method, final ClassWriter cw, final Class returnType, final String methodDescriptor) {
        final MethodVisitor mv = cw.visitMethod(1, "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        DgmConverter.helper = new BytecodeHelper(mv);
        mv.visitCode();
        mv.visitVarInsn(25, 1);
        DgmConverter.helper.doCast(method.getParameterTypes()[0].getTheClass());
        loadParameters(method, 2, mv);
        mv.visitMethodInsn(184, BytecodeHelper.getClassInternalName(method.getDeclaringClass().getTheClass()), method.getName(), methodDescriptor);
        DgmConverter.helper.box(returnType);
        if (method.getReturnType() == Void.TYPE) {
            mv.visitInsn(1);
        }
        mv.visitInsn(176);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    protected static void loadParameters(final CachedMethod method, final int argumentIndex, final MethodVisitor mv) {
        final CachedClass[] parameters = method.getParameterTypes();
        for (int size = parameters.length - 1, i = 0; i < size; ++i) {
            mv.visitVarInsn(25, argumentIndex);
            DgmConverter.helper.pushConstant(i);
            mv.visitInsn(50);
            final Class type = parameters[i + 1].getTheClass();
            if (type.isPrimitive()) {
                DgmConverter.helper.unbox(type);
            }
            else {
                DgmConverter.helper.doCast(type);
            }
        }
    }
}
