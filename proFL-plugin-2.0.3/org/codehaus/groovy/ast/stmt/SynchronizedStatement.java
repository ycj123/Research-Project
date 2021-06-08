// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.Expression;

public class SynchronizedStatement extends Statement
{
    private Statement code;
    private Expression expression;
    
    public SynchronizedStatement(final Expression expression, final Statement code) {
        this.expression = expression;
        this.code = code;
    }
    
    public Statement getCode() {
        return this.code;
    }
    
    public void setCode(final Statement statement) {
        this.code = statement;
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitSynchronizedStatement(this);
    }
    
    public void setExpression(final Expression expression) {
        this.expression = expression;
    }
}
