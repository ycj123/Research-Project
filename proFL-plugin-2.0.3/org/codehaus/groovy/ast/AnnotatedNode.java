// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnnotatedNode extends ASTNode
{
    private List<AnnotationNode> annotations;
    private boolean synthetic;
    ClassNode declaringClass;
    private boolean hasNoRealSourcePositionFlag;
    
    public AnnotatedNode() {
        this.annotations = Collections.emptyList();
    }
    
    public List<AnnotationNode> getAnnotations() {
        return this.annotations;
    }
    
    public List<AnnotationNode> getAnnotations(final ClassNode type) {
        final List<AnnotationNode> ret = new ArrayList<AnnotationNode>(this.annotations.size());
        for (final AnnotationNode node : this.annotations) {
            if (type.equals(node.getClassNode())) {
                ret.add(node);
            }
        }
        return ret;
    }
    
    public void addAnnotation(final AnnotationNode value) {
        this.checkInit();
        this.annotations.add(value);
    }
    
    private void checkInit() {
        if (this.annotations == Collections.EMPTY_LIST) {
            this.annotations = new ArrayList<AnnotationNode>(3);
        }
    }
    
    public void addAnnotations(final List<AnnotationNode> annotations) {
        for (final AnnotationNode node : annotations) {
            this.addAnnotation(node);
        }
    }
    
    public boolean isSynthetic() {
        return this.synthetic;
    }
    
    public void setSynthetic(final boolean synthetic) {
        this.synthetic = synthetic;
    }
    
    public ClassNode getDeclaringClass() {
        return this.declaringClass;
    }
    
    public void setDeclaringClass(final ClassNode declaringClass) {
        this.declaringClass = declaringClass;
    }
    
    public boolean hasNoRealSourcePosition() {
        return this.hasNoRealSourcePositionFlag;
    }
    
    public void setHasNoRealSourcePosition(final boolean value) {
        this.hasNoRealSourcePositionFlag = value;
    }
}
