// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.stmt;

import java.util.Iterator;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import org.codehaus.groovy.ast.VariableScope;
import java.util.List;

public class BlockStatement extends Statement
{
    private List<Statement> statements;
    private VariableScope scope;
    
    public BlockStatement() {
        this(new ArrayList<Statement>(), new VariableScope());
    }
    
    public BlockStatement(final List<Statement> statements, final VariableScope scope) {
        this.statements = new ArrayList<Statement>();
        this.statements = statements;
        this.scope = scope;
    }
    
    public BlockStatement(final Statement[] statements, final VariableScope scope) {
        (this.statements = new ArrayList<Statement>()).addAll(Arrays.asList(statements));
        this.scope = scope;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitBlockStatement(this);
    }
    
    public List<Statement> getStatements() {
        return this.statements;
    }
    
    public void addStatement(final Statement statement) {
        this.statements.add(statement);
    }
    
    public void addStatements(final List<Statement> listOfStatements) {
        this.statements.addAll(listOfStatements);
    }
    
    @Override
    public String toString() {
        return super.toString() + this.statements;
    }
    
    @Override
    public String getText() {
        final StringBuffer buffer = new StringBuffer("{ ");
        boolean first = true;
        for (final Statement statement : this.statements) {
            if (first) {
                first = false;
            }
            else {
                buffer.append("; ");
            }
            buffer.append(statement.getText());
        }
        buffer.append(" }");
        return buffer.toString();
    }
    
    @Override
    public boolean isEmpty() {
        return this.statements.isEmpty();
    }
    
    public void setVariableScope(final VariableScope scope) {
        this.scope = scope;
    }
    
    public VariableScope getVariableScope() {
        return this.scope;
    }
}
