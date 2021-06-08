// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import java.util.Iterator;
import java.util.Map;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.AnnotationNode;

public class AnnotationConstantExpression extends ConstantExpression
{
    public AnnotationConstantExpression(final AnnotationNode node) {
        super(node);
        this.setType(node.getClassNode());
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        final AnnotationNode node = (AnnotationNode)this.getValue();
        final Map<String, Expression> attrs = node.getMembers();
        for (final Expression expr : attrs.values()) {
            expr.visit(visitor);
        }
    }
}
