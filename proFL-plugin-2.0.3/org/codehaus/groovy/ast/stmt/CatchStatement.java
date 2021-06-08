// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.Parameter;

public class CatchStatement extends Statement
{
    private Parameter variable;
    private Statement code;
    
    public CatchStatement(final Parameter variable, final Statement code) {
        this.variable = variable;
        this.code = code;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitCatchStatement(this);
    }
    
    public Statement getCode() {
        return this.code;
    }
    
    public ClassNode getExceptionType() {
        return this.variable.getType();
    }
    
    public Parameter getVariable() {
        return this.variable;
    }
    
    public void setCode(final Statement code) {
        this.code = code;
    }
}
