// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class TernaryExpression extends Expression
{
    private BooleanExpression booleanExpression;
    private Expression trueExpression;
    private Expression falseExpression;
    
    public TernaryExpression(final BooleanExpression booleanExpression, final Expression trueExpression, final Expression falseExpression) {
        this.booleanExpression = booleanExpression;
        this.trueExpression = trueExpression;
        this.falseExpression = falseExpression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitTernaryExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new TernaryExpression((BooleanExpression)transformer.transform(this.booleanExpression), transformer.transform(this.trueExpression), transformer.transform(this.falseExpression));
        ret.setSourcePosition(this);
        return ret;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[" + this.booleanExpression + " ? " + this.trueExpression + " : " + this.falseExpression + "]";
    }
    
    public BooleanExpression getBooleanExpression() {
        return this.booleanExpression;
    }
    
    public Expression getFalseExpression() {
        return this.falseExpression;
    }
    
    public Expression getTrueExpression() {
        return this.trueExpression;
    }
    
    @Override
    public String getText() {
        return "(" + this.booleanExpression.getText() + ") ? " + this.trueExpression.getText() + " : " + this.falseExpression.getText();
    }
    
    @Override
    public ClassNode getType() {
        return ClassHelper.OBJECT_TYPE;
    }
}
