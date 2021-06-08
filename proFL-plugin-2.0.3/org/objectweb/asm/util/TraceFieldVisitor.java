// 
// Decompiled by Procyon v0.5.36
// 

package org.objectweb.asm.util;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.TypePath;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;

public final class TraceFieldVisitor extends FieldVisitor
{
    public final Printer p;
    
    public TraceFieldVisitor(final Printer p) {
        this(null, p);
    }
    
    public TraceFieldVisitor(final FieldVisitor fv, final Printer p) {
        super(393216, fv);
        this.p = p;
    }
    
    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
        final Printer p = this.p.visitFieldAnnotation(desc, visible);
        final AnnotationVisitor av = (this.fv == null) ? null : this.fv.visitAnnotation(desc, visible);
        return new TraceAnnotationVisitor(av, p);
    }
    
    @Override
    public AnnotationVisitor visitTypeAnnotation(final int typeRef, final TypePath typePath, final String desc, final boolean visible) {
        final Printer p = this.p.visitFieldTypeAnnotation(typeRef, typePath, desc, visible);
        final AnnotationVisitor av = (this.fv == null) ? null : this.fv.visitTypeAnnotation(typeRef, typePath, desc, visible);
        return new TraceAnnotationVisitor(av, p);
    }
    
    @Override
    public void visitAttribute(final Attribute attr) {
        this.p.visitFieldAttribute(attr);
        super.visitAttribute(attr);
    }
    
    @Override
    public void visitEnd() {
        this.p.visitFieldEnd();
        super.visitEnd();
    }
}
