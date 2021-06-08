// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

public class MixinNode extends ClassNode
{
    public static final MixinNode[] EMPTY_ARRAY;
    
    public MixinNode(final String name, final int modifiers, final ClassNode superType) {
        this(name, modifiers, superType, ClassHelper.EMPTY_TYPE_ARRAY);
    }
    
    public MixinNode(final String name, final int modifiers, final ClassNode superType, final ClassNode[] interfaces) {
        super(name, modifiers, superType, interfaces, MixinNode.EMPTY_ARRAY);
    }
    
    static {
        EMPTY_ARRAY = new MixinNode[0];
    }
}
