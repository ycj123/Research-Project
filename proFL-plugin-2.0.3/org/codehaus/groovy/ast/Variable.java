// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.ast.expr.Expression;

public interface Variable
{
    ClassNode getType();
    
    ClassNode getOriginType();
    
    String getName();
    
    Expression getInitialExpression();
    
    boolean hasInitialExpression();
    
    boolean isInStaticContext();
    
    boolean isDynamicTyped();
    
    boolean isClosureSharedVariable();
    
    void setClosureSharedVariable(final boolean p0);
}
