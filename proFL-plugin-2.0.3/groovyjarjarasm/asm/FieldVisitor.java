// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm;

public interface FieldVisitor
{
    AnnotationVisitor visitAnnotation(final String p0, final boolean p1);
    
    void visitAttribute(final Attribute p0);
    
    void visitEnd();
}
