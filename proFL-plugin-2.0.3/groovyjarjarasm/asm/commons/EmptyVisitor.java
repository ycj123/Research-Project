// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.commons;

import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.Attribute;
import groovyjarjarasm.asm.AnnotationVisitor;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.FieldVisitor;
import groovyjarjarasm.asm.ClassVisitor;

public class EmptyVisitor implements ClassVisitor, FieldVisitor, MethodVisitor, AnnotationVisitor
{
    public void visit(final int n, final int n2, final String s, final String s2, final String s3, final String[] array) {
    }
    
    public void visitSource(final String s, final String s2) {
    }
    
    public void visitOuterClass(final String s, final String s2, final String s3) {
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        return this;
    }
    
    public void visitAttribute(final Attribute attribute) {
    }
    
    public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
    }
    
    public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
        return this;
    }
    
    public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
        return this;
    }
    
    public void visitEnd() {
    }
    
    public AnnotationVisitor visitAnnotationDefault() {
        return this;
    }
    
    public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
        return this;
    }
    
    public void visitCode() {
    }
    
    public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
    }
    
    public void visitInsn(final int n) {
    }
    
    public void visitIntInsn(final int n, final int n2) {
    }
    
    public void visitVarInsn(final int n, final int n2) {
    }
    
    public void visitTypeInsn(final int n, final String s) {
    }
    
    public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
    }
    
    public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
    }
    
    public void visitJumpInsn(final int n, final Label label) {
    }
    
    public void visitLabel(final Label label) {
    }
    
    public void visitLdcInsn(final Object o) {
    }
    
    public void visitIincInsn(final int n, final int n2) {
    }
    
    public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label[] array) {
    }
    
    public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
    }
    
    public void visitMultiANewArrayInsn(final String s, final int n) {
    }
    
    public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
    }
    
    public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
    }
    
    public void visitLineNumber(final int n, final Label label) {
    }
    
    public void visitMaxs(final int n, final int n2) {
    }
    
    public void visit(final String s, final Object o) {
    }
    
    public void visitEnum(final String s, final String s2, final String s3) {
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final String s2) {
        return this;
    }
    
    public AnnotationVisitor visitArray(final String s) {
        return this;
    }
}
