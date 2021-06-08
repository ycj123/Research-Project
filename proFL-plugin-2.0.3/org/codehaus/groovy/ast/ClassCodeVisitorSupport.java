// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.ast.expr.VariableExpression;
import java.util.List;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.Map;
import java.util.Iterator;
import org.codehaus.groovy.ast.stmt.Statement;

public abstract class ClassCodeVisitorSupport extends CodeVisitorSupport implements GroovyClassVisitor
{
    public void visitClass(final ClassNode node) {
        this.visitAnnotations(node);
        this.visitPackage(node.getPackage());
        this.visitImports(node.getModule());
        node.visitContents(this);
        this.visitObjectInitializerStatements(node);
    }
    
    protected void visitObjectInitializerStatements(final ClassNode node) {
        for (final Statement element : node.getObjectInitializerStatements()) {
            element.visit(this);
        }
    }
    
    public void visitPackage(final PackageNode node) {
        if (node != null) {
            this.visitAnnotations(node);
            node.visit(this);
        }
    }
    
    public void visitImports(final ModuleNode node) {
        if (node != null) {
            for (final ImportNode importNode : node.getImports()) {
                this.visitAnnotations(importNode);
                importNode.visit(this);
            }
            for (final ImportNode importStarNode : node.getStarImports()) {
                this.visitAnnotations(importStarNode);
                importStarNode.visit(this);
            }
            for (final ImportNode importStaticNode : node.getStaticImports().values()) {
                this.visitAnnotations(importStaticNode);
                importStaticNode.visit(this);
            }
            for (final ImportNode importStaticStarNode : node.getStaticStarImports().values()) {
                this.visitAnnotations(importStaticStarNode);
                importStaticStarNode.visit(this);
            }
        }
    }
    
    public void visitAnnotations(final AnnotatedNode node) {
        final List<AnnotationNode> annotations = node.getAnnotations();
        if (annotations.isEmpty()) {
            return;
        }
        for (final AnnotationNode an : annotations) {
            if (an.isBuiltIn()) {
                continue;
            }
            for (final Map.Entry<String, Expression> member : an.getMembers().entrySet()) {
                member.getValue().visit(this);
            }
        }
    }
    
    protected void visitClassCodeContainer(final Statement code) {
        if (code != null) {
            code.visit(this);
        }
    }
    
    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        this.visitAnnotations(expression);
        super.visitVariableExpression(expression);
    }
    
    protected void visitConstructorOrMethod(final MethodNode node, final boolean isConstructor) {
        this.visitAnnotations(node);
        this.visitClassCodeContainer(node.getCode());
        for (final Parameter param : node.getParameters()) {
            this.visitAnnotations(param);
        }
    }
    
    public void visitConstructor(final ConstructorNode node) {
        this.visitConstructorOrMethod(node, true);
    }
    
    public void visitMethod(final MethodNode node) {
        this.visitConstructorOrMethod(node, false);
    }
    
    public void visitField(final FieldNode node) {
        this.visitAnnotations(node);
        final Expression init = node.getInitialExpression();
        if (init != null) {
            init.visit(this);
        }
    }
    
    public void visitProperty(final PropertyNode node) {
        this.visitAnnotations(node);
        Statement statement = node.getGetterBlock();
        this.visitClassCodeContainer(statement);
        statement = node.getSetterBlock();
        this.visitClassCodeContainer(statement);
        final Expression init = node.getInitialExpression();
        if (init != null) {
            init.visit(this);
        }
    }
    
    protected void addError(final String msg, final ASTNode expr) {
        final int line = expr.getLineNumber();
        final int col = expr.getColumnNumber();
        final SourceUnit source = this.getSourceUnit();
        source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException(msg + '\n', line, col), source));
    }
    
    protected abstract SourceUnit getSourceUnit();
    
    protected void visitStatement(final Statement statement) {
    }
    
    @Override
    public void visitAssertStatement(final AssertStatement statement) {
        this.visitStatement(statement);
        super.visitAssertStatement(statement);
    }
    
    @Override
    public void visitBlockStatement(final BlockStatement block) {
        this.visitStatement(block);
        super.visitBlockStatement(block);
    }
    
    @Override
    public void visitBreakStatement(final BreakStatement statement) {
        this.visitStatement(statement);
        super.visitBreakStatement(statement);
    }
    
    @Override
    public void visitCaseStatement(final CaseStatement statement) {
        this.visitStatement(statement);
        super.visitCaseStatement(statement);
    }
    
    @Override
    public void visitCatchStatement(final CatchStatement statement) {
        this.visitStatement(statement);
        super.visitCatchStatement(statement);
    }
    
    @Override
    public void visitContinueStatement(final ContinueStatement statement) {
        this.visitStatement(statement);
        super.visitContinueStatement(statement);
    }
    
    @Override
    public void visitDoWhileLoop(final DoWhileStatement loop) {
        this.visitStatement(loop);
        super.visitDoWhileLoop(loop);
    }
    
    @Override
    public void visitExpressionStatement(final ExpressionStatement statement) {
        this.visitStatement(statement);
        super.visitExpressionStatement(statement);
    }
    
    @Override
    public void visitForLoop(final ForStatement forLoop) {
        this.visitStatement(forLoop);
        super.visitForLoop(forLoop);
    }
    
    @Override
    public void visitIfElse(final IfStatement ifElse) {
        this.visitStatement(ifElse);
        super.visitIfElse(ifElse);
    }
    
    @Override
    public void visitReturnStatement(final ReturnStatement statement) {
        this.visitStatement(statement);
        super.visitReturnStatement(statement);
    }
    
    @Override
    public void visitSwitch(final SwitchStatement statement) {
        this.visitStatement(statement);
        super.visitSwitch(statement);
    }
    
    @Override
    public void visitSynchronizedStatement(final SynchronizedStatement statement) {
        this.visitStatement(statement);
        super.visitSynchronizedStatement(statement);
    }
    
    @Override
    public void visitThrowStatement(final ThrowStatement statement) {
        this.visitStatement(statement);
        super.visitThrowStatement(statement);
    }
    
    @Override
    public void visitTryCatchFinally(final TryCatchStatement statement) {
        this.visitStatement(statement);
        super.visitTryCatchFinally(statement);
    }
    
    @Override
    public void visitWhileLoop(final WhileStatement loop) {
        this.visitStatement(loop);
        super.visitWhileLoop(loop);
    }
}
