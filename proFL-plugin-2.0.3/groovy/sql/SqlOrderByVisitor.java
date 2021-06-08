// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.CodeVisitorSupport;

public class SqlOrderByVisitor extends CodeVisitorSupport
{
    private StringBuffer buffer;
    
    public SqlOrderByVisitor() {
        this.buffer = new StringBuffer();
    }
    
    public String getOrderBy() {
        return this.buffer.toString();
    }
    
    @Override
    public void visitReturnStatement(final ReturnStatement statement) {
        statement.getExpression().visit(this);
    }
    
    @Override
    public void visitPropertyExpression(final PropertyExpression expression) {
        this.buffer.append(expression.getPropertyAsString());
    }
}
