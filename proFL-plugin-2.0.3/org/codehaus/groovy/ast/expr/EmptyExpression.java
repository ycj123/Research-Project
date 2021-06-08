// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class EmptyExpression extends Expression
{
    public static final EmptyExpression INSTANCE;
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        return this;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
    }
    
    static {
        INSTANCE = new EmptyExpression();
    }
}
