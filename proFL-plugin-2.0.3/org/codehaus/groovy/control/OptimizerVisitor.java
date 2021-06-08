// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.ExpressionTransformer;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;
import org.codehaus.groovy.ast.FieldNode;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ClassCodeExpressionTransformer;

public class OptimizerVisitor extends ClassCodeExpressionTransformer
{
    private ClassNode currentClass;
    private SourceUnit source;
    private Map const2Var;
    private List<FieldNode> missingFields;
    
    public OptimizerVisitor(final CompilationUnit cu) {
        this.const2Var = new HashMap();
        this.missingFields = new LinkedList<FieldNode>();
    }
    
    public void visitClass(final ClassNode node, final SourceUnit source) {
        this.currentClass = node;
        this.source = source;
        this.const2Var.clear();
        this.missingFields.clear();
        super.visitClass(node);
        this.addMissingFields();
    }
    
    private void addMissingFields() {
        for (final Object missingField : this.missingFields) {
            final FieldNode f = (FieldNode)missingField;
            this.currentClass.addField(f);
        }
    }
    
    private void setConstField(final ConstantExpression constantExpression) {
        final Object n = constantExpression.getValue();
        if (!(n instanceof Number) && !(n instanceof Character)) {
            return;
        }
        FieldNode field = this.const2Var.get(n);
        if (field != null) {
            constantExpression.setConstantName(field.getName());
            return;
        }
        final String name = "$const$" + this.const2Var.size();
        field = this.currentClass.getDeclaredField(name);
        if (field == null) {
            field = new FieldNode(name, 4122, constantExpression.getType(), this.currentClass, constantExpression);
            field.setSynthetic(true);
            this.missingFields.add(field);
        }
        constantExpression.setConstantName(field.getName());
        this.const2Var.put(n, field);
    }
    
    @Override
    public Expression transform(final Expression exp) {
        if (exp == null) {
            return null;
        }
        if (!this.currentClass.isInterface() && exp.getClass() == ConstantExpression.class) {
            this.setConstField((ConstantExpression)exp);
        }
        return exp.transformExpression(this);
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    @Override
    public void visitClosureExpression(final ClosureExpression expression) {
    }
}
