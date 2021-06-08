// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassNode;

public class ConstructorCallExpression extends Expression
{
    private final Expression arguments;
    private boolean usesAnonymousInnerClass;
    
    public ConstructorCallExpression(final ClassNode type, final Expression arguments) {
        super.setType(type);
        if (!(arguments instanceof TupleExpression)) {
            this.arguments = new TupleExpression(arguments);
        }
        else {
            this.arguments = arguments;
        }
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitConstructorCallExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression args = transformer.transform(this.arguments);
        final ConstructorCallExpression ret = new ConstructorCallExpression(this.getType(), args);
        ret.setSourcePosition(this);
        ret.setUsingAnonymousInnerClass(this.isUsingAnonymousInnerClass());
        return ret;
    }
    
    public Expression getArguments() {
        return this.arguments;
    }
    
    @Override
    public String getText() {
        String text = null;
        if (this.isSuperCall()) {
            text = "super ";
        }
        else if (this.isThisCall()) {
            text = "this ";
        }
        else {
            text = "new " + this.getType().getName();
        }
        return text + this.arguments.getText();
    }
    
    @Override
    public String toString() {
        return super.toString() + "[type: " + this.getType() + " arguments: " + this.arguments + "]";
    }
    
    public boolean isSuperCall() {
        return this.getType() == ClassNode.SUPER;
    }
    
    public boolean isSpecialCall() {
        return this.isThisCall() || this.isSuperCall();
    }
    
    public boolean isThisCall() {
        return this.getType() == ClassNode.THIS;
    }
    
    public void setUsingAnonymousInnerClass(final boolean usage) {
        this.usesAnonymousInnerClass = usage;
    }
    
    public boolean isUsingAnonymousInnerClass() {
        return this.usesAnonymousInnerClass;
    }
    
    @Deprecated
    public boolean isUsingAnnonymousInnerClass() {
        return this.isUsingAnonymousInnerClass();
    }
}
