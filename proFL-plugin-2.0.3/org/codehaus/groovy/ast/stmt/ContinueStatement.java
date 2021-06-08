// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class ContinueStatement extends Statement
{
    private String label;
    
    public ContinueStatement() {
        this(null);
    }
    
    public ContinueStatement(final String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitContinueStatement(this);
    }
}
