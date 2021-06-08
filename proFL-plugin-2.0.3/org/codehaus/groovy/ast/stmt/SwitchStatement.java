// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.ast.expr.Expression;

public class SwitchStatement extends Statement
{
    private Expression expression;
    private List<CaseStatement> caseStatements;
    private Statement defaultStatement;
    
    public SwitchStatement(final Expression expression) {
        this(expression, EmptyStatement.INSTANCE);
    }
    
    public SwitchStatement(final Expression expression, final Statement defaultStatement) {
        this.caseStatements = new ArrayList<CaseStatement>();
        this.expression = expression;
        this.defaultStatement = defaultStatement;
    }
    
    public SwitchStatement(final Expression expression, final List<CaseStatement> caseStatements, final Statement defaultStatement) {
        this.caseStatements = new ArrayList<CaseStatement>();
        this.expression = expression;
        this.caseStatements = caseStatements;
        this.defaultStatement = defaultStatement;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitSwitch(this);
    }
    
    public List<CaseStatement> getCaseStatements() {
        return this.caseStatements;
    }
    
    public Expression getExpression() {
        return this.expression;
    }
    
    public void setExpression(final Expression e) {
        this.expression = e;
    }
    
    public Statement getDefaultStatement() {
        return this.defaultStatement;
    }
    
    public void setDefaultStatement(final Statement defaultStatement) {
        this.defaultStatement = defaultStatement;
    }
    
    public void addCase(final CaseStatement caseStatement) {
        this.caseStatements.add(caseStatement);
    }
    
    public CaseStatement getCaseStatement(final int idx) {
        if (idx >= 0 && idx < this.caseStatements.size()) {
            return this.caseStatements.get(idx);
        }
        return null;
    }
}
