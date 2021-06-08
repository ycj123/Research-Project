// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import groovy.lang.Closure;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class MethodPointerExpression extends Expression
{
    private Expression expression;
    private Expression methodName;
    
    public MethodPointerExpression(final Expression expression, final Expression methodName) {
        this.expression = expression;
        this.methodName = methodName;
    }
    
    public Expression getExpression() {
        if (this.expression == null) {
            return VariableExpression.THIS_EXPRESSION;
        }
        return this.expression;
    }
    
    public Expression getMethodName() {
        return this.methodName;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitMethodPointerExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression mname = transformer.transform(this.methodName);
        Expression ret;
        if (this.expression == null) {
            ret = new MethodPointerExpression(VariableExpression.THIS_EXPRESSION, mname);
        }
        else {
            ret = new MethodPointerExpression(transformer.transform(this.expression), mname);
        }
        ret.setSourcePosition(this);
        return ret;
    }
    
    @Override
    public String getText() {
        if (this.expression == null) {
            return "&" + this.methodName;
        }
        return this.expression.getText() + ".&" + this.methodName.getText();
    }
    
    @Override
    public ClassNode getType() {
        return ClassHelper.CLOSURE_TYPE;
    }
    
    public boolean isDynamic() {
        return false;
    }
    
    public Class getTypeClass() {
        return Closure.class;
    }
}
