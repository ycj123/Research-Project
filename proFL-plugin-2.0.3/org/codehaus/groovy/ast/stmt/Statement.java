// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.ASTNode;

public class Statement extends ASTNode
{
    private String statementLabel;
    
    public Statement() {
        this.statementLabel = null;
    }
    
    public String getStatementLabel() {
        return this.statementLabel;
    }
    
    public void setStatementLabel(final String label) {
        this.statementLabel = label;
    }
    
    public boolean isEmpty() {
        return false;
    }
}
