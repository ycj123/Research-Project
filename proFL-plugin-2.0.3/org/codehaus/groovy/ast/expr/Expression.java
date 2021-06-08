// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.GroovyBugError;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.AnnotatedNode;

public abstract class Expression extends AnnotatedNode
{
    private ClassNode type;
    
    public Expression() {
        this.type = ClassHelper.DYNAMIC_TYPE;
    }
    
    public abstract Expression transformExpression(final ExpressionTransformer p0);
    
    protected List<Expression> transformExpressions(final List<? extends Expression> expressions, final ExpressionTransformer transformer) {
        final List<Expression> list = new ArrayList<Expression>(expressions.size());
        for (final Expression expr : expressions) {
            list.add(transformer.transform(expr));
        }
        return list;
    }
    
    protected <T extends Expression> List<T> transformExpressions(final List<? extends Expression> expressions, final ExpressionTransformer transformer, final Class<T> transformedType) {
        final List<T> list = new ArrayList<T>(expressions.size());
        for (final Expression expr : expressions) {
            final Expression transformed = transformer.transform(expr);
            if (!transformedType.isInstance(transformed)) {
                throw new GroovyBugError(String.format("Transformed expression should have type %s but has type %s", transformedType, transformed.getClass()));
            }
            list.add(transformedType.cast(transformed));
        }
        return list;
    }
    
    public ClassNode getType() {
        return this.type;
    }
    
    public void setType(final ClassNode t) {
        this.type = t;
    }
}
