// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.CompileUnit;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.MethodNode;
import java.util.Iterator;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.ast.GroovyClassVisitor;
import org.codehaus.groovy.ast.ClassNode;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.ClassVisitor;

public class DummyClassGenerator extends ClassGenerator
{
    private ClassVisitor cv;
    private MethodVisitor mv;
    private GeneratorContext context;
    private String sourceFile;
    private ClassNode classNode;
    private String internalClassName;
    private String internalBaseClassName;
    
    public DummyClassGenerator(final GeneratorContext context, final ClassVisitor classVisitor, final ClassLoader classLoader, final String sourceFile) {
        super(classLoader);
        this.context = context;
        this.cv = classVisitor;
        this.sourceFile = sourceFile;
    }
    
    @Override
    public void visitClass(final ClassNode classNode) {
        try {
            this.classNode = classNode;
            this.internalClassName = BytecodeHelper.getClassInternalName(classNode);
            this.internalBaseClassName = BytecodeHelper.getClassInternalName(classNode.getSuperClass());
            this.cv.visit(47, classNode.getModifiers(), this.internalClassName, null, this.internalBaseClassName, BytecodeHelper.getClassInternalNames(classNode.getInterfaces()));
            classNode.visitContents(this);
            for (final ClassNode innerClassType : this.innerClasses) {
                final ClassNode innerClass = innerClassType;
                final String innerClassInternalName = BytecodeHelper.getClassInternalName(innerClassType);
                String outerClassName = this.internalClassName;
                final MethodNode enclosingMethod = innerClass.getEnclosingMethod();
                if (enclosingMethod != null) {
                    outerClassName = null;
                }
                this.cv.visitInnerClass(innerClassInternalName, outerClassName, innerClassType.getName(), innerClass.getModifiers());
            }
            this.cv.visitEnd();
        }
        catch (GroovyRuntimeException e) {
            e.setModule(classNode.getModule());
            throw e;
        }
    }
    
    @Override
    public void visitConstructor(final ConstructorNode node) {
        this.visitParameters(node, node.getParameters());
        final String methodType = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, node.getParameters());
        (this.mv = this.cv.visitMethod(node.getModifiers(), "<init>", methodType, null, null)).visitTypeInsn(187, "java/lang/RuntimeException");
        this.mv.visitInsn(89);
        this.mv.visitLdcInsn("not intended for execution");
        this.mv.visitMethodInsn(183, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V");
        this.mv.visitInsn(191);
        this.mv.visitMaxs(0, 0);
    }
    
    @Override
    public void visitMethod(final MethodNode node) {
        this.visitParameters(node, node.getParameters());
        final String methodType = BytecodeHelper.getMethodDescriptor(node.getReturnType(), node.getParameters());
        (this.mv = this.cv.visitMethod(node.getModifiers(), node.getName(), methodType, null, null)).visitTypeInsn(187, "java/lang/RuntimeException");
        this.mv.visitInsn(89);
        this.mv.visitLdcInsn("not intended for execution");
        this.mv.visitMethodInsn(183, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V");
        this.mv.visitInsn(191);
        this.mv.visitMaxs(0, 0);
    }
    
    @Override
    public void visitField(final FieldNode fieldNode) {
        this.cv.visitField(fieldNode.getModifiers(), fieldNode.getName(), BytecodeHelper.getTypeDescription(fieldNode.getType()), null, null);
    }
    
    @Override
    public void visitProperty(final PropertyNode statement) {
    }
    
    protected CompileUnit getCompileUnit() {
        CompileUnit answer = this.classNode.getCompileUnit();
        if (answer == null) {
            answer = this.context.getCompileUnit();
        }
        return answer;
    }
    
    protected void visitParameters(final ASTNode node, final Parameter[] parameters) {
        for (int i = 0, size = parameters.length; i < size; ++i) {
            this.visitParameter(node, parameters[i]);
        }
    }
    
    protected void visitParameter(final ASTNode node, final Parameter parameter) {
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
    }
}
