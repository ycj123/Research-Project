// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.stmt.Statement;
import groovyjarjarasm.asm.Opcodes;

public class PropertyNode extends AnnotatedNode implements Opcodes, Variable
{
    private FieldNode field;
    private Statement getterBlock;
    private Statement setterBlock;
    private final int modifiers;
    private boolean closureShare;
    
    public PropertyNode(final String name, final int modifiers, final ClassNode type, final ClassNode owner, final Expression initialValueExpression, final Statement getterBlock, final Statement setterBlock) {
        this(new FieldNode(name, modifiers & 0x8, type, owner, initialValueExpression), modifiers, getterBlock, setterBlock);
    }
    
    public PropertyNode(final FieldNode field, final int modifiers, final Statement getterBlock, final Statement setterBlock) {
        this.closureShare = false;
        this.field = field;
        this.modifiers = modifiers;
        this.getterBlock = getterBlock;
        this.setterBlock = setterBlock;
    }
    
    public Statement getGetterBlock() {
        return this.getterBlock;
    }
    
    public Expression getInitialExpression() {
        return this.field.getInitialExpression();
    }
    
    public void setGetterBlock(final Statement getterBlock) {
        this.getterBlock = getterBlock;
    }
    
    public void setSetterBlock(final Statement setterBlock) {
        this.setterBlock = setterBlock;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    public String getName() {
        return this.field.getName();
    }
    
    public Statement getSetterBlock() {
        return this.setterBlock;
    }
    
    public ClassNode getType() {
        return this.field.getType();
    }
    
    public void setType(final ClassNode t) {
        this.field.setType(t);
    }
    
    public FieldNode getField() {
        return this.field;
    }
    
    public void setField(final FieldNode fn) {
        this.field = fn;
    }
    
    public boolean isPrivate() {
        return (this.modifiers & 0x2) != 0x0;
    }
    
    public boolean isPublic() {
        return (this.modifiers & 0x1) != 0x0;
    }
    
    public boolean isStatic() {
        return (this.modifiers & 0x8) != 0x0;
    }
    
    public boolean hasInitialExpression() {
        return this.field.hasInitialExpression();
    }
    
    public boolean isInStaticContext() {
        return this.field.isInStaticContext();
    }
    
    public boolean isDynamicTyped() {
        return this.field.isDynamicTyped();
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
}
