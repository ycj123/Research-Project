// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class BreakStatement extends Statement
{
    private String label;
    
    public BreakStatement() {
        this(null);
    }
    
    public BreakStatement(final String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitBreakStatement(this);
    }
}
