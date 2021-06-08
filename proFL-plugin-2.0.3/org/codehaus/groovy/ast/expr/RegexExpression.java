// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;

@Deprecated
public class RegexExpression extends Expression
{
    private final Expression string;
    
    public RegexExpression(final Expression string) {
        this.string = string;
        super.setType(ClassHelper.PATTERN_TYPE);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitRegexExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new RegexExpression(transformer.transform(this.string));
        ret.setSourcePosition(this);
        return ret;
    }
    
    public Expression getRegex() {
        return this.string;
    }
}
