// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.callsite;

import org.codehaus.groovy.ast.ClassHelper;
import groovy.lang.GroovyRuntimeException;
import java.lang.reflect.Modifier;
import org.codehaus.groovy.reflection.CachedClass;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.groovy.classgen.BytecodeHelper;
import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.MethodVisitor;
import org.codehaus.groovy.reflection.CachedMethod;
import groovyjarjarasm.asm.ClassWriter;

public class CallSiteGenerator
{
    private static final String GRE;
    
    private CallSiteGenerator() {
    }
    
    private static MethodVisitor writeMethod(final ClassWriter cw, final String name, final int argumentCount, final String superClass, final CachedMethod cachedMethod, final String receiverType, final String parameterDescription, final boolean useArray) {
        final MethodVisitor mv = cw.visitMethod(1, "call" + name, "(L" + receiverType + ";" + parameterDescription + ")Ljava/lang/Object;", null, null);
        mv.visitCode();
        final Label tryStart = new Label();
        mv.visitLabel(tryStart);
        for (int i = 0; i < argumentCount; ++i) {
            mv.visitVarInsn(25, i);
        }
        mv.visitMethodInsn(182, superClass, "checkCall", "(Ljava/lang/Object;" + parameterDescription + ")Z");
        final Label l0 = new Label();
        mv.visitJumpInsn(153, l0);
        final BytecodeHelper helper = new BytecodeHelper(mv);
        final Class callClass = cachedMethod.getDeclaringClass().getTheClass();
        final boolean useInterface = callClass.isInterface();
        final String type = BytecodeHelper.getClassInternalName(callClass.getName());
        final String descriptor = BytecodeHelper.getMethodDescriptor(cachedMethod.getReturnType(), cachedMethod.getNativeParameterTypes());
        int invokeMethodCode = 182;
        if (cachedMethod.isStatic()) {
            invokeMethodCode = 184;
        }
        else {
            mv.visitVarInsn(25, 1);
            helper.doCast(callClass);
            if (useInterface) {
                invokeMethodCode = 185;
            }
        }
        final Method method = cachedMethod.setAccessible();
        final Class<?>[] parameters = method.getParameterTypes();
        for (int size = parameters.length, j = 0; j < size; ++j) {
            if (useArray) {
                mv.visitVarInsn(25, 2);
                helper.pushConstant(j);
                mv.visitInsn(50);
            }
            else {
                mv.visitVarInsn(25, j + 2);
            }
            final Class parameterType = parameters[j];
            if (parameterType.isPrimitive()) {
                helper.unbox(parameterType);
            }
            else {
                helper.doCast(parameterType);
            }
        }
        mv.visitMethodInsn(invokeMethodCode, type, cachedMethod.getName(), descriptor);
        helper.box(cachedMethod.getReturnType());
        if (cachedMethod.getReturnType() == Void.TYPE) {
            mv.visitInsn(1);
        }
        mv.visitInsn(176);
        mv.visitLabel(l0);
        for (int j = 0; j < argumentCount; ++j) {
            mv.visitVarInsn(25, j);
        }
        if (!useArray) {
            mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/ArrayUtil", "createArray", "(" + parameterDescription + ")[Ljava/lang/Object;");
        }
        mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/callsite/CallSiteArray", "defaultCall" + name, "(Lorg/codehaus/groovy/runtime/callsite/CallSite;L" + receiverType + ";[Ljava/lang/Object;)Ljava/lang/Object;");
        mv.visitInsn(176);
        final Label tryEnd = new Label();
        mv.visitLabel(tryEnd);
        final Label catchStart = new Label();
        mv.visitLabel(catchStart);
        mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/ScriptBytecodeAdapter", "unwrap", "(Lgroovy/lang/GroovyRuntimeException;)Ljava/lang/Throwable;");
        mv.visitInsn(191);
        mv.visitTryCatchBlock(tryStart, tryEnd, catchStart, CallSiteGenerator.GRE);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        return mv;
    }
    
    public static void genCallWithFixedParams(final ClassWriter cw, final String name, final String superClass, final CachedMethod cachedMethod, final String receiverType) {
        if (cachedMethod.getParamsCount() > 4) {
            return;
        }
        final StringBuilder pdescb = new StringBuilder();
        final int pc = cachedMethod.getParamsCount();
        for (int i = 0; i != pc; ++i) {
            pdescb.append("Ljava/lang/Object;");
        }
        writeMethod(cw, name, pc + 2, superClass, cachedMethod, receiverType, pdescb.toString(), false);
    }
    
    public static void genCallXxxWithArray(final ClassWriter cw, final String name, final String superClass, final CachedMethod cachedMethod, final String receiverType) {
        writeMethod(cw, name, 3, superClass, cachedMethod, receiverType, "[Ljava/lang/Object;", true);
    }
    
    private static void genConstructor(final ClassWriter cw, final String superClass) {
        final MethodVisitor mv = cw.visitMethod(1, "<init>", "(Lorg/codehaus/groovy/runtime/callsite/CallSite;Lgroovy/lang/MetaClassImpl;Lgroovy/lang/MetaMethod;[Ljava/lang/Class;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitVarInsn(25, 1);
        mv.visitVarInsn(25, 2);
        mv.visitVarInsn(25, 3);
        mv.visitVarInsn(25, 4);
        mv.visitMethodInsn(183, superClass, "<init>", "(Lorg/codehaus/groovy/runtime/callsite/CallSite;Lgroovy/lang/MetaClassImpl;Lgroovy/lang/MetaMethod;[Ljava/lang/Class;)V");
        mv.visitInsn(177);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    public static byte[] genPogoMetaMethodSite(final CachedMethod cachedMethod, final ClassWriter cw, final String name) {
        cw.visit(48, 4097, name.replace('.', '/'), null, "org/codehaus/groovy/runtime/callsite/PogoMetaMethodSite", null);
        genConstructor(cw, "org/codehaus/groovy/runtime/callsite/PogoMetaMethodSite");
        genCallXxxWithArray(cw, "Current", "org/codehaus/groovy/runtime/callsite/PogoMetaMethodSite", cachedMethod, "groovy/lang/GroovyObject");
        genCallXxxWithArray(cw, "", "org/codehaus/groovy/runtime/callsite/PogoMetaMethodSite", cachedMethod, "java/lang/Object");
        genCallWithFixedParams(cw, "Current", "org/codehaus/groovy/runtime/callsite/PogoMetaMethodSite", cachedMethod, "groovy/lang/GroovyObject");
        genCallWithFixedParams(cw, "", "org/codehaus/groovy/runtime/callsite/PogoMetaMethodSite", cachedMethod, "java/lang/Object");
        cw.visitEnd();
        return cw.toByteArray();
    }
    
    public static byte[] genPojoMetaMethodSite(final CachedMethod cachedMethod, final ClassWriter cw, final String name) {
        cw.visit(48, 4097, name.replace('.', '/'), null, "org/codehaus/groovy/runtime/callsite/PojoMetaMethodSite", null);
        genConstructor(cw, "org/codehaus/groovy/runtime/callsite/PojoMetaMethodSite");
        genCallXxxWithArray(cw, "", "org/codehaus/groovy/runtime/callsite/PojoMetaMethodSite", cachedMethod, "java/lang/Object");
        genCallWithFixedParams(cw, "", "org/codehaus/groovy/runtime/callsite/PojoMetaMethodSite", cachedMethod, "java/lang/Object");
        cw.visitEnd();
        return cw.toByteArray();
    }
    
    public static byte[] genStaticMetaMethodSite(final CachedMethod cachedMethod, final ClassWriter cw, final String name) {
        cw.visit(48, 4097, name.replace('.', '/'), null, "org/codehaus/groovy/runtime/callsite/StaticMetaMethodSite", null);
        genConstructor(cw, "org/codehaus/groovy/runtime/callsite/StaticMetaMethodSite");
        genCallXxxWithArray(cw, "", "org/codehaus/groovy/runtime/callsite/StaticMetaMethodSite", cachedMethod, "java/lang/Object");
        genCallXxxWithArray(cw, "Static", "org/codehaus/groovy/runtime/callsite/StaticMetaMethodSite", cachedMethod, "java/lang/Class");
        genCallWithFixedParams(cw, "", "org/codehaus/groovy/runtime/callsite/StaticMetaMethodSite", cachedMethod, "java/lang/Object");
        genCallWithFixedParams(cw, "Static", "org/codehaus/groovy/runtime/callsite/StaticMetaMethodSite", cachedMethod, "java/lang/Class");
        cw.visitEnd();
        return cw.toByteArray();
    }
    
    public static Constructor compilePogoMethod(final CachedMethod cachedMethod) {
        final ClassWriter cw = new ClassWriter(1);
        final CachedClass declClass = cachedMethod.getDeclaringClass();
        final CallSiteClassLoader callSiteLoader = declClass.getCallSiteLoader();
        final String name = callSiteLoader.createClassName(cachedMethod.setAccessible());
        final byte[] bytes = genPogoMetaMethodSite(cachedMethod, cw, name);
        return callSiteLoader.defineClassAndGetConstructor(name, bytes);
    }
    
    public static Constructor compilePojoMethod(final CachedMethod cachedMethod) {
        final ClassWriter cw = new ClassWriter(1);
        final CachedClass declClass = cachedMethod.getDeclaringClass();
        final CallSiteClassLoader callSiteLoader = declClass.getCallSiteLoader();
        final String name = callSiteLoader.createClassName(cachedMethod.setAccessible());
        final byte[] bytes = genPojoMetaMethodSite(cachedMethod, cw, name);
        return callSiteLoader.defineClassAndGetConstructor(name, bytes);
    }
    
    public static Constructor compileStaticMethod(final CachedMethod cachedMethod) {
        final ClassWriter cw = new ClassWriter(1);
        final CachedClass declClass = cachedMethod.getDeclaringClass();
        final CallSiteClassLoader callSiteLoader = declClass.getCallSiteLoader();
        final String name = callSiteLoader.createClassName(cachedMethod.setAccessible());
        final byte[] bytes = genStaticMetaMethodSite(cachedMethod, cw, name);
        return callSiteLoader.defineClassAndGetConstructor(name, bytes);
    }
    
    public static boolean isCompilable(final CachedMethod method) {
        return GroovySunClassLoader.sunVM != null || (Modifier.isPublic(method.cachedClass.getModifiers()) && method.isPublic() && publicParams(method));
    }
    
    private static boolean publicParams(final CachedMethod method) {
        for (final Class nativeParamType : method.getNativeParameterTypes()) {
            if (!Modifier.isPublic(nativeParamType.getModifiers())) {
                return false;
            }
        }
        return true;
    }
    
    static {
        GRE = BytecodeHelper.getClassInternalName(ClassHelper.make(GroovyRuntimeException.class));
    }
}
