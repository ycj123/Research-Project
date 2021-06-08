// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassNode;

public class CastExpression extends Expression
{
    private final Expression expression;
    private boolean ignoreAutoboxing;
    private boolean coerce;
    
    public static CastExpression asExpression(final ClassNode type, final Expression expression) {
        final CastExpression answer = new CastExpression(type, expression);
        answer.setCoerce(true);
        return answer;
    }
    
    public CastExpression(final ClassNode type, final Expression expression) {
        this(type, expression, false);
    }
    
    public CastExpression(final ClassNode type, final Expression expression, final boolean ignoreAutoboxing) {
        this.ignoreAutoboxing = false;
        this.coerce = false;
        super.setType(type);
        this.expression = expression;
        this.ignoreAutoboxing = ignoreAutoboxing;
    }
    
    public boolean isIgnoringAutoboxing() {
        return this.ignoreAutoboxing;
    }
    
    public boolean isCoerce() {
        return this.coerce;
    }
    
    public void setCoerce(final boolean coerce) {
        this.coerce = coerce;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[(" + this.getType().getName() + ") " + this.expression + "]";
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitCastExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final CastExpression ret = new CastExpression(this.getType(), transformer.transform(this.expression));
        ret.setSourcePosition(this);
        ret.setCoerce(this.isCoerce());
        return ret;
    }
    
    @Override
    public String getText() {
        return "(" + this.getType() + ") " + this.expression.getText();
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    @Override
    public void setType(final ClassNode t) {
        super.setType(t);
    }
}
