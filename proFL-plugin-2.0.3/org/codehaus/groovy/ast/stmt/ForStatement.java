// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.Parameter;

public class ForStatement extends Statement
{
    public static final Parameter FOR_LOOP_DUMMY;
    private Parameter variable;
    private Expression collectionExpression;
    private Statement loopBlock;
    private VariableScope scope;
    
    public ForStatement(final Parameter variable, final Expression collectionExpression, final Statement loopBlock) {
        this.variable = variable;
        this.collectionExpression = collectionExpression;
        this.loopBlock = loopBlock;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitForLoop(this);
    }
    
    public Expression getCollectionExpression() {
        return this.collectionExpression;
    }
    
    public Statement getLoopBlock() {
        return this.loopBlock;
    }
    
    public Parameter getVariable() {
        return this.variable;
    }
    
    public ClassNode getVariableType() {
        return this.variable.getType();
    }
    
    public void setCollectionExpression(final Expression collectionExpression) {
        this.collectionExpression = collectionExpression;
    }
    
    public void setVariableScope(final VariableScope variableScope) {
        this.scope = variableScope;
    }
    
    public VariableScope getVariableScope() {
        return this.scope;
    }
    
    public void setLoopBlock(final Statement loopBlock) {
        this.loopBlock = loopBlock;
    }
    
    static {
        FOR_LOOP_DUMMY = new Parameter(ClassHelper.OBJECT_TYPE, "forLoopDummyParameter");
    }
}
