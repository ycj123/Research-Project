// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.syntax.Token;

public class DeclarationExpression extends BinaryExpression
{
    public DeclarationExpression(final VariableExpression left, final Token operation, final Expression right) {
        super(left, operation, right);
    }
    
    public DeclarationExpression(final Expression left, final Token operation, final Expression right) {
        super(left, operation, right);
        this.check(left, right);
    }
    
    private void check(final Expression left, final Expression right) {
        if (!(left instanceof VariableExpression)) {
            if (!(left instanceof TupleExpression)) {
                throw new GroovyBugError("illegal left expression for declaration: " + left);
            }
            final TupleExpression tuple = (TupleExpression)left;
            if (tuple.getExpressions().size() == 0) {
                throw new GroovyBugError("one element required for left side");
            }
        }
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitDeclarationExpression(this);
    }
    
    public VariableExpression getVariableExpression() {
        return (VariableExpression)this.getLeftExpression();
    }
    
    @Override
    public void setLeftExpression(final Expression leftExpression) {
        this.check(leftExpression, this.getRightExpression());
        super.setLeftExpression(leftExpression);
    }
    
    @Override
    public void setRightExpression(final Expression rightExpression) {
        this.check(this.getLeftExpression(), rightExpression);
        super.setRightExpression(rightExpression);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression left = this.getLeftExpression();
        final Expression ret = new DeclarationExpression(transformer.transform(this.getLeftExpression()), this.getOperation(), transformer.transform(this.getRightExpression()));
        ret.setSourcePosition(this);
        return ret;
    }
    
    public boolean isMultipleAssignmentDeclaration() {
        return this.getLeftExpression() instanceof ArgumentListExpression;
    }
}
