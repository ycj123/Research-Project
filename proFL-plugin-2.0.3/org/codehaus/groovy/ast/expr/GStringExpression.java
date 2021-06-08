// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast.expr;

import java.util.Iterator;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ClassHelper;
import java.util.ArrayList;
import java.util.List;

public class GStringExpression extends Expression
{
    private String verbatimText;
    private List<ConstantExpression> strings;
    private List<Expression> values;
    
    public GStringExpression(final String verbatimText) {
        this.strings = new ArrayList<ConstantExpression>();
        this.values = new ArrayList<Expression>();
        this.verbatimText = verbatimText;
        super.setType(ClassHelper.GSTRING_TYPE);
    }
    
    public GStringExpression(final String verbatimText, final List<ConstantExpression> strings, final List<Expression> values) {
        this.strings = new ArrayList<ConstantExpression>();
        this.values = new ArrayList<Expression>();
        this.verbatimText = verbatimText;
        this.strings = strings;
        this.values = values;
        super.setType(ClassHelper.GSTRING_TYPE);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
        visitor.visitGStringExpression(this);
    }
    
    @Override
    public Expression transformExpression(final ExpressionTransformer transformer) {
        final Expression ret = new GStringExpression(this.verbatimText, this.transformExpressions(this.strings, transformer, ConstantExpression.class), this.transformExpressions(this.values, transformer));
        ret.setSourcePosition(this);
        return ret;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[strings: " + this.strings + " values: " + this.values + "]";
    }
    
    @Override
    public String getText() {
        return this.verbatimText;
    }
    
    public List<ConstantExpression> getStrings() {
        return this.strings;
    }
    
    public List<Expression> getValues() {
        return this.values;
    }
    
    public void addString(final ConstantExpression text) {
        if (text == null) {
            throw new NullPointerException("Cannot add a null text expression");
        }
        this.strings.add(text);
    }
    
    public void addValue(final Expression value) {
        if (this.strings.size() == 0) {
            this.strings.add(ConstantExpression.EMPTY_STRING);
        }
        this.values.add(value);
    }
    
    public Expression getValue(final int idx) {
        return this.values.get(idx);
    }
    
    public boolean isConstantString() {
        return this.values.isEmpty();
    }
    
    public Expression asConstantString() {
        final StringBuffer buffer = new StringBuffer();
        for (final ConstantExpression expression : this.strings) {
            final Object value = expression.getValue();
            if (value != null) {
                buffer.append(value);
            }
        }
        return new ConstantExpression(buffer.toString());
    }
}
