// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import java.util.Iterator;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.ast.VariableScope;

public class ClosureListExpression extends ListExpression
{
    private VariableScope scope;
    
    public ClosureListExpression(final List<Expression> expressions) {
        super(expressions);
        this.scope = new VariableScope();
    }
    
    public ClosureListExpression() {
        this(new ArrayList<Expression>(3));
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitClosureListExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new ClosureListExpression(this.transformExpressions(this.getExpressions(), transformer));
        ret.setSourcePosition(this);
        return ret;
    }
    
    public void setVariableScope(final VariableScope scope) {
        this.scope = scope;
    }
    
    public VariableScope getVariableScope() {
        return this.scope;
    }
    
    @Override
    public String getText() {
        final StringBuffer buffer = new StringBuffer("(");
        boolean first = true;
        for (final Expression expression : this.getExpressions()) {
            if (first) {
                first = false;
            }
            else {
                buffer.append("; ");
            }
            buffer.append(expression.getText());
        }
        buffer.append(")");
        return buffer.toString();
    }
}
