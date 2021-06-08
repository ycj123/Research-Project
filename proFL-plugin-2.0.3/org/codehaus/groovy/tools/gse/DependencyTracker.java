// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.gse;

import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import java.util.Iterator;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.SourceUnit;
import java.util.Set;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public class DependencyTracker extends ClassCodeVisitorSupport
{
    private Set<String> current;
    private SourceUnit source;
    private StringSetMap cache;
    
    public DependencyTracker(final SourceUnit source, final StringSetMap cache) {
        this.source = source;
        this.cache = cache;
    }
    
    private void addToCache(final ClassNode node) {
        if (!node.isPrimaryClassNode()) {
            return;
        }
        this.current.add(node.getName());
    }
    
    private void addToCache(final ClassNode[] nodes) {
        if (nodes == null) {
            return;
        }
        for (final ClassNode node : nodes) {
            this.addToCache(node);
        }
    }
    
    @Override
    public void visitClass(final ClassNode node) {
        final Set<String> old = this.current;
        this.current = this.cache.get(node.getName());
        this.addToCache(node);
        this.addToCache(node.getSuperClass());
        this.addToCache(node.getInterfaces());
        super.visitClass(node);
        this.current = old;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    @Override
    public void visitClassExpression(final ClassExpression expression) {
        super.visitClassExpression(expression);
        this.addToCache(expression.getType());
    }
    
    @Override
    public void visitField(final FieldNode node) {
        super.visitField(node);
        this.addToCache(node.getType());
    }
    
    @Override
    public void visitMethod(final MethodNode node) {
        for (final Parameter p : node.getParameters()) {
            this.addToCache(p.getType());
        }
        this.addToCache(node.getReturnType());
        this.addToCache(node.getExceptions());
        super.visitMethod(node);
    }
    
    @Override
    public void visitArrayExpression(final ArrayExpression expression) {
        super.visitArrayExpression(expression);
        this.addToCache(expression.getType());
    }
    
    @Override
    public void visitCastExpression(final CastExpression expression) {
        super.visitCastExpression(expression);
        this.addToCache(expression.getType());
    }
    
    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        super.visitVariableExpression(expression);
        this.addToCache(expression.getType());
    }
    
    @Override
    public void visitCatchStatement(final CatchStatement statement) {
        super.visitCatchStatement(statement);
        this.addToCache(statement.getVariable().getType());
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
        super.visitAnnotations(node);
        for (final AnnotationNode an : node.getAnnotations()) {
            this.addToCache(an.getClassNode());
        }
    }
    
    @Override
    public void visitConstructorCallExpression(final ConstructorCallExpression call) {
        super.visitConstructorCallExpression(call);
        this.addToCache(call.getType());
    }
}
