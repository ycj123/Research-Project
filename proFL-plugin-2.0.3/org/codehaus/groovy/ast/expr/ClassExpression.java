// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassNode;

public class ClassExpression extends Expression
{
    public ClassExpression(final ClassNode type) {
        super.setType(type);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitClassExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        return this;
    }
    
    @Override
    public String getText() {
        return this.getType().getName();
    }
    
    @Override
    public String toString() {
        return super.toString() + "[type: " + this.getType().getName() + "]";
    }
}
