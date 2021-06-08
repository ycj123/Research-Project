// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.lang.reflect.Field;
import org.codehaus.groovy.ast.expr.Expression;
import groovyjarjarasm.asm.Opcodes;

public class FieldNode extends AnnotatedNode implements Opcodes, Variable
{
    private String name;
    private int modifiers;
    private ClassNode type;
    private ClassNode owner;
    private Expression initialValueExpression;
    private boolean dynamicTyped;
    private boolean holder;
    private boolean closureShare;
    
    public static FieldNode newStatic(final Class theClass, final String name) throws SecurityException, NoSuchFieldException {
        final Field field = theClass.getField(name);
        final ClassNode fldType = ClassHelper.make(field.getType());
        return new FieldNode(name, 9, fldType, ClassHelper.make(theClass), null);
    }
    
    public FieldNode(final String name, final int modifiers, final ClassNode type, final ClassNode owner, final Expression initialValueExpression) {
        this.closureShare = false;
        this.name = name;
        this.modifiers = modifiers;
        this.type = type;
        if (this.type == ClassHelper.DYNAMIC_TYPE && initialValueExpression != null) {
            this.setType(initialValueExpression.getType());
        }
        this.setType(type);
        this.owner = owner;
        this.initialValueExpression = initialValueExpression;
    }
    
    public Expression getInitialExpression() {
        return this.initialValueExpression;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    public String getName() {
        return this.name;
    }
    
    public ClassNode getType() {
        return this.type;
    }
    
    public void setType(final ClassNode type) {
        this.type = type;
        this.dynamicTyped |= (type == ClassHelper.DYNAMIC_TYPE);
    }
    
    public ClassNode getOwner() {
        return this.owner;
    }
    
    public boolean isHolder() {
        return this.holder;
    }
    
    public void setHolder(final boolean holder) {
        this.holder = holder;
    }
    
    public boolean isDynamicTyped() {
        return this.dynamicTyped;
    }
    
    public void setModifiers(final int modifiers) {
        this.modifiers = modifiers;
    }
    
    public boolean isStatic() {
        return (this.modifiers & 0x8) != 0x0;
    }
    
    public boolean isEnum() {
        return (this.modifiers & 0x4000) != 0x0;
    }
    
    public boolean isFinal() {
        return (this.modifiers & 0x10) != 0x0;
    }
    
    public boolean isPublic() {
        return (this.modifiers & 0x1) != 0x0;
    }
    
    public void setOwner(final ClassNode owner) {
        this.owner = owner;
    }
    
    public boolean hasInitialExpression() {
        return this.initialValueExpression != null;
    }
    
    public boolean isInStaticContext() {
        return this.isStatic();
    }
    
    public Expression getInitialValueExpression() {
        return this.initialValueExpression;
    }
    
    public void setInitialValueExpression(final Expression initialValueExpression) {
        this.initialValueExpression = initialValueExpression;
    }
    
    public boolean isClosureSharedVariable() {
        return false;
    }
    
    public void setClosureSharedVariable(final boolean inClosure) {
        this.closureShare = inClosure;
    }
    
    public ClassNode getOriginType() {
        return this.getType();
    }
    
    public void rename(final String name) {
        this.declaringClass.renameField(this.name, name);
        this.name = name;
    }
}
