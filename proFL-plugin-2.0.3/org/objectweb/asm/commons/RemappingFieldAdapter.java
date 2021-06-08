// 
// Decompiled by Procyon v0.5.36
// 

package org.objectweb.asm.commons;

import org.objectweb.asm.TypePath;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;

@Deprecated
public class RemappingFieldAdapter extends FieldVisitor
{
    private final Remapper remapper;
    
    public RemappingFieldAdapter(final FieldVisitor fv, final Remapper remapper) {
        this(393216, fv, remapper);
    }
    
    protected RemappingFieldAdapter(final int api, final FieldVisitor fv, final Remapper remapper) {
        super(api, fv);
        this.remapper = remapper;
    }
    
    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
        final AnnotationVisitor av = this.fv.visitAnnotation(this.remapper.mapDesc(desc), visible);
        return (av == null) ? null : new RemappingAnnotationAdapter(av, this.remapper);
    }
    
    @Override
    public AnnotationVisitor visitTypeAnnotation(final int typeRef, final TypePath typePath, final String desc, final boolean visible) {
        final AnnotationVisitor av = super.visitTypeAnnotation(typeRef, typePath, this.remapper.mapDesc(desc), visible);
        return (av == null) ? null : new RemappingAnnotationAdapter(av, this.remapper);
    }
}
