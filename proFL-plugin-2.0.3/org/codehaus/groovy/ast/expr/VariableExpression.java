// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.Variable;

public class VariableExpression extends Expression implements Variable
{
    public static final VariableExpression THIS_EXPRESSION;
    public static final VariableExpression SUPER_EXPRESSION;
    private String variable;
    private boolean inStaticContext;
    private boolean isDynamicTyped;
    private Variable accessedVariable;
    boolean closureShare;
    boolean useRef;
    private ClassNode originType;
    
    public Variable getAccessedVariable() {
        return this.accessedVariable;
    }
    
    public void setAccessedVariable(final Variable origin) {
        this.accessedVariable = origin;
    }
    
    public VariableExpression(final String variable, final ClassNode type) {
        this.isDynamicTyped = false;
        this.closureShare = false;
        this.useRef = false;
        this.variable = variable;
        this.originType = type;
        this.setType(ClassHelper.getWrapper(type));
    }
    
    public VariableExpression(final String variable) {
        this(variable, ClassHelper.DYNAMIC_TYPE);
    }
    
    public VariableExpression(final Variable variable) {
        this(variable.getName(), variable.getOriginType());
        this.setAccessedVariable(variable);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitVariableExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        return this;
    }
    
    @Override
    public String getText() {
        return this.variable;
    }
    
    public String getName() {
        return this.variable;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[variable: " + this.variable + (this.isDynamicTyped() ? "" : (" type: " + this.getType())) + "]";
    }
    
    public Expression getInitialExpression() {
        return null;
    }
    
    public boolean hasInitialExpression() {
        return false;
    }
    
    public boolean isInStaticContext() {
        if (this.accessedVariable != null && this.accessedVariable != this) {
            return this.accessedVariable.isInStaticContext();
        }
        return this.inStaticContext;
    }
    
    public void setInStaticContext(final boolean inStaticContext) {
        this.inStaticContext = inStaticContext;
    }
    
    @Override
    public void setType(final ClassNode cn) {
        super.setType(cn);
        this.isDynamicTyped |= (ClassHelper.DYNAMIC_TYPE == cn);
    }
    
    public boolean isDynamicTyped() {
        if (this.accessedVariable != null && this.accessedVariable != this) {
            return this.accessedVariable.isDynamicTyped();
        }
        return this.isDynamicTyped;
    }
    
    public boolean isClosureSharedVariable() {
        if (this.accessedVariable != null && this.accessedVariable != this) {
            return this.accessedVariable.isClosureSharedVariable();
        }
        return this.closureShare;
    }
    
    public void setClosureSharedVariable(final boolean inClosure) {
        this.closureShare = inClosure;
    }
    
    public void setUseReferenceDirectly(final boolean useRef) {
        this.useRef = useRef;
    }
    
    public boolean isUseReferenceDirectly() {
        return this.useRef;
    }
    
    @Override
    public ClassNode getType() {
        if (this.accessedVariable != null && this.accessedVariable != this) {
            return this.accessedVariable.getType();
        }
        return super.getType();
    }
    
    public ClassNode getOriginType() {
        return this.originType;
    }
    
    public boolean isThisExpression() {
        return "this".equals(this.variable);
    }
    
    public boolean isSuperExpression() {
        return "super".equals(this.variable);
    }
    
    static {
        THIS_EXPRESSION = new VariableExpression("this", ClassHelper.DYNAMIC_TYPE);
        SUPER_EXPRESSION = new VariableExpression("super", ClassHelper.DYNAMIC_TYPE);
    }
}
