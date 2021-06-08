// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import java.util.Iterator;
import java.util.Collections;
import org.codehaus.groovy.ast.ClassNode;
import java.util.List;

public class ArrayExpression extends Expression
{
    private List<Expression> expressions;
    private List<Expression> sizeExpression;
    private ClassNode elementType;
    
    private static ClassNode makeArray(final ClassNode base, final List<Expression> sizeExpression) {
        ClassNode ret = base.makeArray();
        if (sizeExpression == null) {
            return ret;
        }
        for (int size = sizeExpression.size(), i = 1; i < size; ++i) {
            ret = ret.makeArray();
        }
        return ret;
    }
    
    public ArrayExpression(final ClassNode elementType, List<Expression> expressions, final List<Expression> sizeExpression) {
        super.setType(makeArray(elementType, sizeExpression));
        if (expressions == null) {
            expressions = Collections.emptyList();
        }
        this.elementType = elementType;
        this.expressions = expressions;
        this.sizeExpression = sizeExpression;
        for (final Object item : expressions) {
            if (item != null && !(item instanceof Expression)) {
                throw new ClassCastException("Item: " + item + " is not an Expression");
            }
        }
        if (sizeExpression != null) {
            for (final Object item : sizeExpression) {
                if (!(item instanceof Expression)) {
                    throw new ClassCastException("Item: " + item + " is not an Expression");
                }
            }
        }
    }
    
    public ArrayExpression(final ClassNode elementType, final List<Expression> expressions) {
        this(elementType, expressions, null);
    }
    
    public void addExpression(final Expression expression) {
        this.expressions.add(expression);
    }
    
    public List<Expression> getExpressions() {
        return this.expressions;
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitArrayExpression(this);
    }
    
    public boolean isDynamic() {
        return false;
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final List<Expression> exprList = this.transformExpressions(this.expressions, transformer);
        List<Expression> sizes = null;
        if (this.sizeExpression != null) {
            sizes = this.transformExpressions(this.sizeExpression, transformer);
        }
        final Expression ret = new ArrayExpression(this.elementType, exprList, sizes);
        ret.setSourcePosition(this);
        return ret;
    }
    
    public Expression getExpression(final int i) {
        return this.expressions.get(i);
    }
    
    public ClassNode getElementType() {
        return this.elementType;
    }
    
    @Override
    public String getText() {
        final StringBuffer buffer = new StringBuffer("[");
        boolean first = true;
        for (final Expression expression : this.expressions) {
            if (first) {
                first = false;
            }
            else {
                buffer.append(", ");
            }
            buffer.append(expression.getText());
        }
        buffer.append("]");
        return buffer.toString();
    }
    
    public List<Expression> getSizeExpression() {
        return this.sizeExpression;
    }
    
    @Override
    public String toString() {
        return super.toString() + this.expressions;
    }
}
