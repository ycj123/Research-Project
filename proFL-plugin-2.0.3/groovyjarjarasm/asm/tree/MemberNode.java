// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarasm.asm.tree;

import groovyjarjarasm.asm.Attribute;
import java.util.ArrayList;
import groovyjarjarasm.asm.AnnotationVisitor;
import java.util.List;

public abstract class MemberNode
{
    public List visibleAnnotations;
    public List invisibleAnnotations;
    public List attrs;
    
    protected MemberNode() {
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        final AnnotationNode annotationNode = new AnnotationNode(s);
        if (b) {
            if (this.visibleAnnotations == null) {
                this.visibleAnnotations = new ArrayList(1);
            }
            this.visibleAnnotations.add(annotationNode);
        }
        else {
            if (this.invisibleAnnotations == null) {
                this.invisibleAnnotations = new ArrayList(1);
            }
            this.invisibleAnnotations.add(annotationNode);
        }
        return annotationNode;
    }
    
    public void visitAttribute(final Attribute attribute) {
        if (this.attrs == null) {
            this.attrs = new ArrayList(1);
        }
        this.attrs.add(attribute);
    }
    
    public void visitEnd() {
    }
}
