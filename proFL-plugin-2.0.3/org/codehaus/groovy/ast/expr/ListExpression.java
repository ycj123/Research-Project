// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import java.util.Iterator;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;
import java.util.ArrayList;
import java.util.List;

public class ListExpression extends Expression
{
    private List<Expression> expressions;
    private boolean wrapped;
    
    public ListExpression() {
        this(new ArrayList<Expression>());
    }
    
    public ListExpression(final List<Expression> expressions) {
        this.wrapped = false;
        this.expressions = expressions;
        this.setType(ClassHelper.LIST_TYPE);
    }
    
    public void addExpression(final Expression expression) {
        this.expressions.add(expression);
    }
    
    public List<Expression> getExpressions() {
        return this.expressions;
    }
    
    public void setWrapped(final boolean value) {
        this.wrapped = value;
    }
    
    public boolean isWrapped() {
        return this.wrapped;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitListExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new ListExpression(this.transformExpressions(this.getExpressions(), transformer));
        ret.setSourcePosition(this);
        return ret;
    }
    
    public Expression getExpression(final int i) {
        return this.expressions.get(i);
    }
    
    @Override
    public String getText() {
        final StringBuffer buffer = new StringBuffer("[");
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
        buffer.append("]");
        return buffer.toString();
    }
    
    @Override
    public String toString() {
        return super.toString() + this.expressions;
    }
}
