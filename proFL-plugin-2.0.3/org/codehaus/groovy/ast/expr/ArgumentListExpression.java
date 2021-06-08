// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.Parameter;
import java.util.List;

public class ArgumentListExpression extends TupleExpression
{
    public static final Object[] EMPTY_ARRAY;
    public static final ArgumentListExpression EMPTY_ARGUMENTS;
    
    public ArgumentListExpression() {
    }
    
    public ArgumentListExpression(final List<Expression> expressions) {
        super(expressions);
    }
    
    public ArgumentListExpression(final Expression[] expressions) {
        super(expressions);
    }
    
    public ArgumentListExpression(final Parameter[] parameters) {
        for (int i = 0; i < parameters.length; ++i) {
            final Parameter parameter = parameters[i];
            this.addExpression(new VariableExpression(parameter.getName()));
        }
    }
    
    public ArgumentListExpression(final Expression expr) {
        super(expr);
    }
    
    public ArgumentListExpression(final Expression expr1, final Expression expr2) {
        super(expr1, expr2);
    }
    
    public ArgumentListExpression(final Expression expr1, final Expression expr2, final Expression expr3) {
        super(expr1, expr2, expr3);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new ArgumentListExpression(this.transformExpressions(this.getExpressions(), transformer));
        ret.setSourcePosition(this);
        return ret;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitArgumentlistExpression(this);
    }
    
    static {
        EMPTY_ARRAY = new Object[0];
        EMPTY_ARGUMENTS = new ArgumentListExpression();
    }
}
