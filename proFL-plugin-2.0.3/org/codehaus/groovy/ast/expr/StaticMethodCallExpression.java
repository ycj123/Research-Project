// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.ast.ClassNode;

public class StaticMethodCallExpression extends Expression
{
    private ClassNode ownerType;
    private String method;
    private Expression arguments;
    private MetaMethod metaMethod;
    
    public StaticMethodCallExpression(final ClassNode type, final String method, final Expression arguments) {
        this.metaMethod = null;
        this.ownerType = type;
        this.method = method;
        this.arguments = arguments;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitStaticMethodCallExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new StaticMethodCallExpression(this.getOwnerType(), this.method, transformer.transform(this.arguments));
        ret.setSourcePosition(this);
        return ret;
    }
    
    public Expression getArguments() {
        return this.arguments;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    @Override
    public String getText() {
        return this.getOwnerType().getName() + "." + this.method + this.arguments.getText();
    }
    
    @Override
    public String toString() {
        return super.toString() + "[" + this.getOwnerType().getName() + "#" + this.method + " arguments: " + this.arguments + "]";
    }
    
    public ClassNode getOwnerType() {
        return this.ownerType;
    }
    
    public void setOwnerType(final ClassNode ownerType) {
        this.ownerType = ownerType;
    }
    
    public void setMetaMethod(final MetaMethod metaMethod) {
        this.metaMethod = metaMethod;
    }
    
    public MetaMethod getMetaMethod() {
        return this.metaMethod;
    }
}
