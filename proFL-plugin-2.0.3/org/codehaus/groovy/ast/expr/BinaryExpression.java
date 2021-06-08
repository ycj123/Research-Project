// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.syntax.Token;

public class BinaryExpression extends Expression
{
    private Expression leftExpression;
    private Expression rightExpression;
    private final Token operation;
    
    public BinaryExpression(final Expression leftExpression, final Token operation, final Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.operation = operation;
        this.rightExpression = rightExpression;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[" + this.leftExpression + this.operation + this.rightExpression + "]";
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitBinaryExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new BinaryExpression(transformer.transform(this.leftExpression), this.operation, transformer.transform(this.rightExpression));
        ret.setSourcePosition(this);
        return ret;
    }
    
    public Expression getLeftExpression() {
        return this.leftExpression;
    }
    
    public void setLeftExpression(final Expression leftExpression) {
        this.leftExpression = leftExpression;
    }
    
    public void setRightExpression(final Expression rightExpression) {
        this.rightExpression = rightExpression;
    }
    
    public Token getOperation() {
        return this.operation;
    }
    
    public Expression getRightExpression() {
        return this.rightExpression;
    }
    
    @Override
    public String getText() {
        if (this.operation.getType() == 30) {
            return this.leftExpression.getText() + "[" + this.rightExpression.getText() + "]";
        }
        return "(" + this.leftExpression.getText() + " " + this.operation.getText() + " " + this.rightExpression.getText() + ")";
    }
    
    public static BinaryExpression newAssignmentExpression(final Variable variable, final Expression rhs) {
        final VariableExpression lhs = new VariableExpression(variable);
        final Token operator = Token.newPlaceholder(100);
        return new BinaryExpression(lhs, operator, rhs);
    }
    
    public static BinaryExpression newInitializationExpression(final String variable, final ClassNode type, final Expression rhs) {
        final VariableExpression lhs = new VariableExpression(variable);
        if (type != null) {
            lhs.setType(type);
        }
        final Token operator = Token.newPlaceholder(100);
        return new BinaryExpression(lhs, operator, rhs);
    }
}
