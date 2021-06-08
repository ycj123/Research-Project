// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import java.lang.reflect.Method;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.Type;
import groovyjarjarasm.asm.Opcodes;

public class MethodCaller implements Opcodes
{
    private int opcode;
    private String internalName;
    private String name;
    private Class theClass;
    private String methodDescriptor;
    
    public static MethodCaller newStatic(final Class theClass, final String name) {
        return new MethodCaller(184, theClass, name);
    }
    
    public static MethodCaller newInterface(final Class theClass, final String name) {
        return new MethodCaller(185, theClass, name);
    }
    
    public static MethodCaller newVirtual(final Class theClass, final String name) {
        return new MethodCaller(182, theClass, name);
    }
    
    public MethodCaller(final int opcode, final Class theClass, final String name) {
        this.opcode = opcode;
        this.internalName = Type.getInternalName(theClass);
        this.theClass = theClass;
        this.name = name;
    }
    
    public void call(final MethodVisitor methodVisitor) {
        methodVisitor.visitMethodInsn(this.opcode, this.internalName, this.name, this.getMethodDescriptor());
    }
    
    public String getMethodDescriptor() {
        if (this.methodDescriptor == null) {
            final Method method = this.getMethod();
            this.methodDescriptor = Type.getMethodDescriptor(method);
        }
        return this.methodDescriptor;
    }
    
    protected Method getMethod() {
        final Method[] methods = this.theClass.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            if (method.getName().equals(this.name)) {
                return method;
            }
        }
        throw new ClassGeneratorException("Could not find method: " + this.name + " on class: " + this.theClass);
    }
}
