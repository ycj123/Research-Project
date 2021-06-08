// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.ast.GenericsType;

public class MethodCallExpression extends Expression
{
    private Expression objectExpression;
    private Expression method;
    private Expression arguments;
    private boolean spreadSafe;
    private boolean safe;
    private boolean implicitThis;
    private GenericsType[] genericsTypes;
    private boolean usesGenerics;
    public static final Expression NO_ARGUMENTS;
    
    @Deprecated
    public MetaMethod getMetaMethod() {
        return null;
    }
    
    public MethodCallExpression(final Expression objectExpression, final String method, final Expression arguments) {
        this(objectExpression, new ConstantExpression(method), arguments);
    }
    
    public MethodCallExpression(final Expression objectExpression, final Expression method, final Expression arguments) {
        this.spreadSafe = false;
        this.safe = false;
        this.genericsTypes = null;
        this.usesGenerics = false;
        this.objectExpression = objectExpression;
        this.method = method;
        if (!(arguments instanceof TupleExpression)) {
            this.arguments = new TupleExpression(arguments);
        }
        else {
            this.arguments = arguments;
        }
        this.setType(ClassHelper.DYNAMIC_TYPE);
        this.setImplicitThis(true);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitMethodCallExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final MethodCallExpression answer = new MethodCallExpression(transformer.transform(this.objectExpression), transformer.transform(this.method), transformer.transform(this.arguments));
        answer.setSafe(this.safe);
        answer.setSpreadSafe(this.spreadSafe);
        answer.setImplicitThis(this.implicitThis);
        answer.setSourcePosition(this);
        return answer;
    }
    
    public Expression getArguments() {
        return this.arguments;
    }
    
    public void setArguments(final Expression arguments) {
        if (!(arguments instanceof TupleExpression)) {
            this.arguments = new TupleExpression(arguments);
        }
        else {
            this.arguments = arguments;
        }
    }
    
    public Expression getMethod() {
        return this.method;
    }
    
    public void setMethod(final Expression method) {
        this.method = method;
    }
    
    public String getMethodAsString() {
        if (!(this.method instanceof ConstantExpression)) {
            return null;
        }
        final ConstantExpression constant = (ConstantExpression)this.method;
        return constant.getText();
    }
    
    public void setObjectExpression(final Expression objectExpression) {
        this.objectExpression = objectExpression;
    }
    
    public Expression getObjectExpression() {
        return this.objectExpression;
    }
    
    @Override
    public String getText() {
        return this.objectExpression.getText() + "." + this.method.getText() + this.arguments.getText();
    }
    
    public boolean isSafe() {
        return this.safe;
    }
    
    public void setSafe(final boolean safe) {
        this.safe = safe;
    }
    
    public boolean isSpreadSafe() {
        return this.spreadSafe;
    }
    
    public void setSpreadSafe(final boolean value) {
        this.spreadSafe = value;
    }
    
    public boolean isImplicitThis() {
        return this.implicitThis;
    }
    
    public void setImplicitThis(final boolean implicitThis) {
        this.implicitThis = implicitThis;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[object: " + this.objectExpression + " method: " + this.method + " arguments: " + this.arguments + "]";
    }
    
    @Deprecated
    public void setMetaMethod(final MetaMethod mmeth) {
    }
    
    public GenericsType[] getGenericsTypes() {
        return this.genericsTypes;
    }
    
    public void setGenericsTypes(final GenericsType[] genericsTypes) {
        this.usesGenerics = (this.usesGenerics || genericsTypes != null);
        this.genericsTypes = genericsTypes;
    }
    
    public boolean isUsingGenerics() {
        return this.usesGenerics;
    }
    
    static {
        NO_ARGUMENTS = new TupleExpression();
    }
}
