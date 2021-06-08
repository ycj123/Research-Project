// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import groovyjarjarasm.asm.ClassWriter;
import java.lang.reflect.Constructor;
import org.codehaus.groovy.classgen.BytecodeHelper;
import groovyjarjarasm.asm.MethodVisitor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import groovyjarjarasm.asm.Opcodes;

public class MethodHandleFactory implements Opcodes
{
    private static final String[] EXCEPTIONS;
    
    public static MethodHandle unreflect(final Method method) {
        if (SunClassLoader.sunVM != null || checkAccessable(method)) {
            return createCompiledMethodHandle(method, ClassInfo.getClassInfo(method.getDeclaringClass()).getArtifactClassLoader());
        }
        return new ReflectiveMethodHandle(method);
    }
    
    private static MethodHandle unreflect(final Method method, final ClassLoaderForClassArtifacts loader) {
        if (SunClassLoader.sunVM != null || checkAccessable(method)) {
            return createCompiledMethodHandle(method, loader);
        }
        return new ReflectiveMethodHandle(method);
    }
    
    private static boolean checkAccessable(final Method method) {
        if (!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            return false;
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        }
        for (final Class paramType : method.getParameterTypes()) {
            if (!Modifier.isPublic(paramType.getModifiers())) {
                return false;
            }
        }
        return true;
    }
    
    public static void genLoadParameters(final int argumentIndex, final MethodVisitor mv, final BytecodeHelper helper, final Method method) {
        final Class<?>[] parameters = method.getParameterTypes();
        for (int size = parameters.length, i = 0; i < size; ++i) {
            mv.visitVarInsn(25, argumentIndex);
            helper.pushConstant(i);
            mv.visitInsn(50);
            final Class type = parameters[i];
            if (type.isPrimitive()) {
                helper.unbox(type);
            }
            else {
                helper.doCast(type);
            }
        }
    }
    
    public static void genLoadParametersDirect(final int argumentIndex, final MethodVisitor mv, final BytecodeHelper helper, final Method method) {
        final Class<?>[] parameters = method.getParameterTypes();
        for (int size = parameters.length, i = 0; i < size; ++i) {
            mv.visitVarInsn(25, argumentIndex + i);
            final Class type = parameters[i];
            if (type.isPrimitive()) {
                helper.unbox(type);
            }
            else {
                helper.doCast(type);
            }
        }
    }
    
    public static void genLoadParametersPrimitiveDirect(final int argumentIndex, final MethodVisitor mv, final BytecodeHelper helper, final Method method) {
        final Class<?>[] parameters = method.getParameterTypes();
        for (int size = parameters.length, idx = 0, i = 0; i < size; ++i, ++idx) {
            final Class type = parameters[i];
            if (type == Double.TYPE) {
                mv.visitVarInsn(24, idx++);
            }
            else if (type == Float.TYPE) {
                mv.visitVarInsn(23, idx);
            }
            else if (type == Long.TYPE) {
                mv.visitVarInsn(22, idx++);
            }
            else if (type == Boolean.TYPE || type == Character.TYPE || type == Byte.TYPE || type == Integer.TYPE || type == Short.TYPE) {
                mv.visitVarInsn(21, idx);
            }
            else {
                mv.visitVarInsn(25, idx);
                helper.doCast(type);
            }
        }
    }
    
    private static MethodHandle createCompiledMethodHandle(final Method method, final ClassLoaderForClassArtifacts loader) {
        try {
            final Constructor c = compileMethodHandle(method, loader);
            if (c != null) {
                return c.newInstance(new Object[0]);
            }
        }
        catch (Throwable t) {}
        return new ReflectiveMethodHandle(method);
    }
    
    private static Constructor compileMethodHandle(final Method cachedMethod, final ClassLoaderForClassArtifacts loader) {
        final ClassWriter cw = new ClassWriter(1);
        final String name = loader.createClassName(cachedMethod);
        final byte[] bytes = genMethodHandle(cachedMethod, cw, name);
        return loader.defineClassAndGetConstructor(name, bytes);
    }
    
    private static byte[] genMethodHandle(final Method method, final ClassWriter cw, final String name) {
        cw.visit(48, 1, name.replace('.', '/'), null, "org/codehaus/groovy/reflection/MethodHandle", null);
        genConstructor(cw, "org/codehaus/groovy/reflection/MethodHandle");
        genInvokeXxxWithArray(cw, method);
        genInvokeWithFixedParams(cw, method);
        genInvokeWithFixedPrimitiveParams(cw, method);
        cw.visitEnd();
        return cw.toByteArray();
    }
    
    private static void genConstructor(final ClassWriter cw, final String superClass) {
        final MethodVisitor mv = cw.visitMethod(1, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, superClass, "<init>", "()V");
        mv.visitInsn(177);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    public static void genInvokeXxxWithArray(final ClassWriter cw, final Method method) {
        final MethodVisitor mv = cw.visitMethod(1, "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null, MethodHandleFactory.EXCEPTIONS);
        mv.visitCode();
        final BytecodeHelper helper = new BytecodeHelper(mv);
        final Class callClass = method.getDeclaringClass();
        final boolean useInterface = callClass.isInterface();
        final String type = BytecodeHelper.getClassInternalName(callClass.getName());
        final String descriptor = BytecodeHelper.getMethodDescriptor(method.getReturnType(), method.getParameterTypes());
        if (Modifier.isStatic(method.getModifiers())) {
            genLoadParameters(2, mv, helper, method);
            mv.visitMethodInsn(184, type, method.getName(), descriptor);
        }
        else {
            mv.visitVarInsn(25, 1);
            helper.doCast(callClass);
            genLoadParameters(2, mv, helper, method);
            mv.visitMethodInsn(useInterface ? 185 : 182, type, method.getName(), descriptor);
        }
        helper.box(method.getReturnType());
        if (method.getReturnType() == Void.TYPE) {
            mv.visitInsn(1);
        }
        mv.visitInsn(176);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private static void genInvokeWithFixedParams(final ClassWriter cw, final Method method) {
        final int pc = method.getParameterTypes().length;
        if (pc <= 4) {
            final StringBuilder pdescb = new StringBuilder();
            for (int i = 0; i != pc; ++i) {
                pdescb.append("Ljava/lang/Object;");
            }
            final String pdesc = pdescb.toString();
            final MethodVisitor mv = cw.visitMethod(1, "invoke", "(Ljava/lang/Object;" + pdesc + ")Ljava/lang/Object;", null, MethodHandleFactory.EXCEPTIONS);
            mv.visitCode();
            final BytecodeHelper helper = new BytecodeHelper(mv);
            final Class callClass = method.getDeclaringClass();
            final boolean useInterface = callClass.isInterface();
            final String type = BytecodeHelper.getClassInternalName(callClass.getName());
            final String descriptor = BytecodeHelper.getMethodDescriptor(method.getReturnType(), method.getParameterTypes());
            if (Modifier.isStatic(method.getModifiers())) {
                genLoadParametersDirect(2, mv, helper, method);
                mv.visitMethodInsn(184, type, method.getName(), descriptor);
            }
            else {
                mv.visitVarInsn(25, 1);
                helper.doCast(callClass);
                genLoadParametersDirect(2, mv, helper, method);
                mv.visitMethodInsn(useInterface ? 185 : 182, type, method.getName(), descriptor);
            }
            helper.box(method.getReturnType());
            if (method.getReturnType() == Void.TYPE) {
                mv.visitInsn(1);
            }
            mv.visitInsn(176);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
    }
    
    private static void genInvokeWithFixedPrimitiveParams(final ClassWriter cw, final Method method) {
        final Class<?>[] pt = method.getParameterTypes();
        final int pc = pt.length;
        if (pc > 0 && pc <= 3) {
            final StringBuilder pdescb = new StringBuilder();
            boolean hasPrimitive = false;
            for (int i = 0; i != pc; ++i) {
                if (pt[i].isPrimitive()) {
                    hasPrimitive = true;
                    pdescb.append(BytecodeHelper.getTypeDescription(pt[i]));
                }
                else {
                    pdescb.append("Ljava/lang/Object;");
                }
            }
            if (!hasPrimitive) {
                return;
            }
            final String pdesc = pdescb.toString();
            final MethodVisitor mv = cw.visitMethod(1, "invoke", "(Ljava/lang/Object;" + pdesc + ")Ljava/lang/Object;", null, MethodHandleFactory.EXCEPTIONS);
            mv.visitCode();
            final BytecodeHelper helper = new BytecodeHelper(mv);
            final Class callClass = method.getDeclaringClass();
            final boolean useInterface = callClass.isInterface();
            final String type = BytecodeHelper.getClassInternalName(callClass.getName());
            final String descriptor = BytecodeHelper.getMethodDescriptor(method.getReturnType(), method.getParameterTypes());
            if (Modifier.isStatic(method.getModifiers())) {
                genLoadParametersPrimitiveDirect(2, mv, helper, method);
                mv.visitMethodInsn(184, type, method.getName(), descriptor);
            }
            else {
                mv.visitVarInsn(25, 1);
                helper.doCast(callClass);
                genLoadParametersPrimitiveDirect(2, mv, helper, method);
                mv.visitMethodInsn(useInterface ? 185 : 182, type, method.getName(), descriptor);
            }
            helper.box(method.getReturnType());
            if (method.getReturnType() == Void.TYPE) {
                mv.visitInsn(1);
            }
            mv.visitInsn(176);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
    }
    
    static {
        EXCEPTIONS = new String[] { "java/lang/Throwable" };
    }
    
    private static class ReflectiveMethodHandle extends MethodHandle
    {
        private final Method method;
        
        public ReflectiveMethodHandle(final Method method) {
            (this.method = method).setAccessible(true);
        }
        
        @Override
        public Object invoke(final Object receiver, final Object[] args) throws Throwable {
            return this.method.invoke(receiver, args);
        }
    }
}
