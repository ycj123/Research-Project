// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.util.LinkedList;

public class InnerClassNode extends ClassNode
{
    private ClassNode outerClass;
    private VariableScope scope;
    private boolean anonymous;
    
    public InnerClassNode(final ClassNode outerClass, final String name, final int modifiers, final ClassNode superClass) {
        this(outerClass, name, modifiers, superClass, ClassHelper.EMPTY_TYPE_ARRAY, MixinNode.EMPTY_ARRAY);
    }
    
    public InnerClassNode(final ClassNode outerClass, final String name, final int modifiers, final ClassNode superClass, final ClassNode[] interfaces, final MixinNode[] mixins) {
        super(name, modifiers, superClass, interfaces, mixins);
        this.outerClass = outerClass;
        if (outerClass.innerClasses == null) {
            outerClass.innerClasses = new LinkedList<InnerClassNode>();
        }
        outerClass.innerClasses.add(this);
    }
    
    @Override
    public ClassNode getOuterClass() {
        return this.outerClass;
    }
    
    @Override
    public FieldNode getOuterField(final String name) {
        return this.outerClass.getDeclaredField(name);
    }
    
    public VariableScope getVariableScope() {
        return this.scope;
    }
    
    public void setVariableScope(final VariableScope scope) {
        this.scope = scope;
    }
    
    public boolean isAnonymous() {
        return this.anonymous;
    }
    
    public void setAnonymous(final boolean anonymous) {
        this.anonymous = anonymous;
    }
}
