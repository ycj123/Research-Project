// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.expr.ExpressionTransformer;
import groovyjarjarasm.asm.MethodVisitor;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.Expression;

public abstract class BytecodeExpression extends Expression
{
    public static final BytecodeExpression NOP;
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitBytecodeExpression(this);
    }
    
    public abstract void visit(final MethodVisitor p0);
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        return this;
    }
    
    static {
        NOP = new BytecodeExpression() {
            @Override
            public void visit(final MethodVisitor visitor) {
            }
        };
    }
}
