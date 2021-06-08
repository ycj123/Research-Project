// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.ast.CodeVisitorSupport;

public class SqlWhereVisitor extends CodeVisitorSupport
{
    private final StringBuffer buffer;
    private final List parameters;
    
    public SqlWhereVisitor() {
        this.buffer = new StringBuffer();
        this.parameters = new ArrayList();
    }
    
    public String getWhere() {
        return this.buffer.toString();
    }
    
    @Override
    public void visitReturnStatement(final ReturnStatement statement) {
        statement.getExpression().visit(this);
    }
    
    @Override
    public void visitBinaryExpression(final BinaryExpression expression) {
        final Expression left = expression.getLeftExpression();
        final Expression right = expression.getRightExpression();
        final boolean leaf = right instanceof ConstantExpression;
        if (!leaf) {
            this.buffer.append("(");
        }
        left.visit(this);
        this.buffer.append(" ");
        final Token token = expression.getOperation();
        this.buffer.append(this.tokenAsSql(token));
        this.buffer.append(" ");
        right.visit(this);
        if (!leaf) {
            this.buffer.append(")");
        }
    }
    
    @Override
    public void visitBooleanExpression(final BooleanExpression expression) {
        expression.getExpression().visit(this);
    }
    
    @Override
    public void visitConstantExpression(final ConstantExpression expression) {
        this.getParameters().add(expression.getValue());
        this.buffer.append("?");
    }
    
    @Override
    public void visitPropertyExpression(final PropertyExpression expression) {
        this.buffer.append(expression.getPropertyAsString());
    }
    
    public List getParameters() {
        return this.parameters;
    }
    
    protected String tokenAsSql(final Token token) {
        switch (token.getType()) {
            case 123: {
                return "=";
            }
            case 164: {
                return "and";
            }
            case 162: {
                return "or";
            }
            default: {
                return token.getText();
            }
        }
    }
}
