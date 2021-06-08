// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import java.util.Iterator;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TupleExpression extends Expression
{
    private List<Expression> expressions;
    
    public TupleExpression() {
        this(0);
    }
    
    public TupleExpression(final Expression expr) {
        this(1);
        this.addExpression(expr);
    }
    
    public TupleExpression(final Expression expr1, final Expression expr2) {
        this(2);
        this.addExpression(expr1);
        this.addExpression(expr2);
    }
    
    public TupleExpression(final Expression expr1, final Expression expr2, final Expression expr3) {
        this(3);
        this.addExpression(expr1);
        this.addExpression(expr2);
        this.addExpression(expr3);
    }
    
    public TupleExpression(final int length) {
        this.expressions = new ArrayList<Expression>(length);
    }
    
    public TupleExpression(final List<Expression> expressions) {
        this.expressions = expressions;
    }
    
    public TupleExpression(final Expression[] expressionArray) {
        this();
        this.expressions.addAll(Arrays.asList(expressionArray));
    }
    
    public TupleExpression addExpression(final Expression expression) {
        this.expressions.add(expression);
        return this;
    }
    
    public List<Expression> getExpressions() {
        return this.expressions;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitTupleExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new TupleExpression(this.transformExpressions(this.getExpressions(), transformer));
        ret.setSourcePosition(this);
        return ret;
    }
    
    public Expression getExpression(final int i) {
        return this.expressions.get(i);
    }
    
    @Override
    public String getText() {
        final StringBuffer buffer = new StringBuffer("(");
        boolean first = true;
        for (final Expression expression : this.expressions) {
            if (first) {
                first = false;
            }
            else {
                buffer.append(", ");
            }
            buffer.append(expression.getText());
        }
        buffer.append(")");
        return buffer.toString();
    }
    
    @Override
    public String toString() {
        return super.toString() + this.expressions;
    }
}
