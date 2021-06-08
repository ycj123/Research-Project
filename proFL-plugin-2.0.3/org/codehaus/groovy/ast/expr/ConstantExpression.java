// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;

public class ConstantExpression extends Expression
{
    public static final ConstantExpression NULL;
    public static final ConstantExpression TRUE;
    public static final ConstantExpression FALSE;
    public static final ConstantExpression EMPTY_STRING;
    public static final ConstantExpression VOID;
    public static final ConstantExpression EMPTY_EXPRESSION;
    private Object value;
    private String constantName;
    
    public ConstantExpression(final Object value) {
        this.value = value;
        if (this.value != null) {
            this.setType(ClassHelper.make(value.getClass()));
        }
    }
    
    @Override
    public String toString() {
        return "ConstantExpression[" + this.value + "]";
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitConstantExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        return this;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    @Override
    public String getText() {
        return (this.value == null) ? "null" : this.value.toString();
    }
    
    public String getConstantName() {
        return this.constantName;
    }
    
    public void setConstantName(final String constantName) {
        this.constantName = constantName;
    }
    
    public boolean isNullExpression() {
        return this.value == null;
    }
    
    public boolean isTrueExpression() {
        return Boolean.TRUE.equals(this.value);
    }
    
    public boolean isFalseExpression() {
        return Boolean.FALSE.equals(this.value);
    }
    
    public boolean isEmptyStringExpression() {
        return "".equals(this.value);
    }
    
    static {
        NULL = new ConstantExpression(null);
        TRUE = new ConstantExpression(Boolean.TRUE);
        FALSE = new ConstantExpression(Boolean.FALSE);
        EMPTY_STRING = new ConstantExpression("");
        VOID = new ConstantExpression(Void.class);
        EMPTY_EXPRESSION = new ConstantExpression(null);
    }
}
