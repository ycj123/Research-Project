// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.commons;

import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.Opcodes;
import groovyjarjarasm.asm.MethodAdapter;

public class CodeSizeEvaluator extends MethodAdapter implements Opcodes
{
    private int minSize;
    private int maxSize;
    
    public CodeSizeEvaluator(final MethodVisitor methodVisitor) {
        super(methodVisitor);
    }
    
    public int getMinSize() {
        return this.minSize;
    }
    
    public int getMaxSize() {
        return this.maxSize;
    }
    
    public void visitInsn(final int n) {
        ++this.minSize;
        ++this.maxSize;
        if (this.mv != null) {
            this.mv.visitInsn(n);
        }
    }
    
    public void visitIntInsn(final int n, final int n2) {
        if (n == 17) {
            this.minSize += 3;
            this.maxSize += 3;
        }
        else {
            this.minSize += 2;
            this.maxSize += 2;
        }
        if (this.mv != null) {
            this.mv.visitIntInsn(n, n2);
        }
    }
    
    public void visitVarInsn(final int n, final int n2) {
        if (n2 < 4 && n != 169) {
            ++this.minSize;
            ++this.maxSize;
        }
        else if (n2 >= 256) {
            this.minSize += 4;
            this.maxSize += 4;
        }
        else {
            this.minSize += 2;
            this.maxSize += 2;
        }
        if (this.mv != null) {
            this.mv.visitVarInsn(n, n2);
        }
    }
    
    public void visitTypeInsn(final int n, final String s) {
        this.minSize += 3;
        this.maxSize += 3;
        if (this.mv != null) {
            this.mv.visitTypeInsn(n, s);
        }
    }
    
    public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
        this.minSize += 3;
        this.maxSize += 3;
        if (this.mv != null) {
            this.mv.visitFieldInsn(n, s, s2, s3);
        }
    }
    
    public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
        if (n == 185 || n == 186) {
            this.minSize += 5;
            this.maxSize += 5;
        }
        else {
            this.minSize += 3;
            this.maxSize += 3;
        }
        if (this.mv != null) {
            this.mv.visitMethodInsn(n, s, s2, s3);
        }
    }
    
    public void visitJumpInsn(final int n, final Label label) {
        this.minSize += 3;
        if (n == 167 || n == 168) {
            this.maxSize += 5;
        }
        else {
            this.maxSize += 8;
        }
        if (this.mv != null) {
            this.mv.visitJumpInsn(n, label);
        }
    }
    
    public void visitLdcInsn(final Object o) {
        if (o instanceof Long || o instanceof Double) {
            this.minSize += 3;
            this.maxSize += 3;
        }
        else {
            this.minSize += 2;
            this.maxSize += 3;
        }
        if (this.mv != null) {
            this.mv.visitLdcInsn(o);
        }
    }
    
    public void visitIincInsn(final int n, final int n2) {
        if (n > 255 || n2 > 127 || n2 < -128) {
            this.minSize += 6;
            this.maxSize += 6;
        }
        else {
            this.minSize += 3;
            this.maxSize += 3;
        }
        if (this.mv != null) {
            this.mv.visitIincInsn(n, n2);
        }
    }
    
    public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label[] array) {
        this.minSize += 13 + array.length * 4;
        this.maxSize += 16 + array.length * 4;
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(n, n2, label, array);
        }
    }
    
    public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
        this.minSize += 9 + array.length * 8;
        this.maxSize += 12 + array.length * 8;
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(label, array, array2);
        }
    }
    
    public void visitMultiANewArrayInsn(final String s, final int n) {
        this.minSize += 4;
        this.maxSize += 4;
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(s, n);
        }
    }
}
