// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.commons;

import groovyjarjarasm.asm.Attribute;
import groovyjarjarasm.asm.AnnotationVisitor;
import groovyjarjarasm.asm.FieldVisitor;

public class RemappingFieldAdapter implements FieldVisitor
{
    private final FieldVisitor fv;
    private final Remapper remapper;
    
    public RemappingFieldAdapter(final FieldVisitor fv, final Remapper remapper) {
        this.fv = fv;
        this.remapper = remapper;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        final AnnotationVisitor visitAnnotation = this.fv.visitAnnotation(s, b);
        return (visitAnnotation == null) ? null : new RemappingAnnotationAdapter(visitAnnotation, this.remapper);
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.fv.visitAttribute(attribute);
    }
    
    public void visitEnd() {
        this.fv.visitEnd();
    }
}
