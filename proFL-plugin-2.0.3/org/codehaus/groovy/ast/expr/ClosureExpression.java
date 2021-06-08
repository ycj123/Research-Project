// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.Parameter;

public class ClosureExpression extends Expression
{
    private Parameter[] parameters;
    private Statement code;
    private VariableScope variableScope;
    
    public ClosureExpression(final Parameter[] parameters, final Statement code) {
        this.parameters = parameters;
        this.code = code;
        super.setType(ClassHelper.CLOSURE_TYPE);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitClosureExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        return this;
    }
    
    @Override
    public String toString() {
        return super.toString() + InvokerHelper.toString(this.parameters) + "{ " + this.code + " }";
    }
    
    public Statement getCode() {
        return this.code;
    }
    
    public void setCode(final Statement code) {
        this.code = code;
    }
    
    public Parameter[] getParameters() {
        return this.parameters;
    }
    
    public boolean isParameterSpecified() {
        return this.parameters != null && this.parameters.length > 0;
    }
    
    public VariableScope getVariableScope() {
        return this.variableScope;
    }
    
    public void setVariableScope(final VariableScope variableScope) {
        this.variableScope = variableScope;
    }
}
