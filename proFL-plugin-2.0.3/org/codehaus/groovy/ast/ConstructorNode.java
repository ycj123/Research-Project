// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.Statement;

public class ConstructorNode extends MethodNode
{
    public ConstructorNode(final int modifiers, final Statement code) {
        this(modifiers, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, code);
    }
    
    public ConstructorNode(final int modifiers, final Parameter[] parameters, final ClassNode[] exceptions, final Statement code) {
        super("<init>", modifiers, ClassHelper.VOID_TYPE, parameters, exceptions, code);
        final VariableScope scope = new VariableScope();
        for (int i = 0; i < parameters.length; ++i) {
            scope.putDeclaredVariable(parameters[i]);
        }
        this.setVariableScope(scope);
    }
    
    public boolean firstStatementIsSpecialConstructorCall() {
        final Statement code = this.getFirstStatement();
        if (code == null || !(code instanceof ExpressionStatement)) {
            return false;
        }
        final Expression expression = ((ExpressionStatement)code).getExpression();
        if (!(expression instanceof ConstructorCallExpression)) {
            return false;
        }
        final ConstructorCallExpression cce = (ConstructorCallExpression)expression;
        return cce.isSpecialCall();
    }
}
