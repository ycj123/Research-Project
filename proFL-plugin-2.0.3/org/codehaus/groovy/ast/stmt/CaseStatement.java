// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.Expression;

public class CaseStatement extends Statement
{
    private Statement code;
    private Expression expression;
    
    public CaseStatement(final Expression expression, final Statement code) {
        this.expression = expression;
        this.code = code;
    }
    
    public Statement getCode() {
        return this.code;
    }
    
    public void setCode(final Statement code) {
        this.code = code;
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    public void setExpression(final Expression e) {
        this.expression = e;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitCaseStatement(this);
    }
    
    @Override
    public String toString() {
        return super.toString() + "[expression: " + this.expression + "; code: " + this.code + "]";
    }
}
