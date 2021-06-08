// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.classgen.BytecodeExpression;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.RegexExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.List;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import java.util.Iterator;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.BlockStatement;

public abstract class CodeVisitorSupport implements GroovyCodeVisitor
{
    public void visitBlockStatement(final BlockStatement block) {
        for (final Statement statement : block.getStatements()) {
            statement.visit(this);
        }
    }
    
    public void visitForLoop(final ForStatement forLoop) {
        forLoop.getCollectionExpression().visit(this);
        forLoop.getLoopBlock().visit(this);
    }
    
    public void visitWhileLoop(final WhileStatement loop) {
        loop.getBooleanExpression().visit(this);
        loop.getLoopBlock().visit(this);
    }
    
    public void visitDoWhileLoop(final DoWhileStatement loop) {
        loop.getLoopBlock().visit(this);
        loop.getBooleanExpression().visit(this);
    }
    
    public void visitIfElse(final IfStatement ifElse) {
        ifElse.getBooleanExpression().visit(this);
        ifElse.getIfBlock().visit(this);
        final Statement elseBlock = ifElse.getElseBlock();
        if (elseBlock instanceof EmptyStatement) {
            this.visitEmptyStatement((EmptyStatement)elseBlock);
        }
        else {
            elseBlock.visit(this);
        }
    }
    
    public void visitExpressionStatement(final ExpressionStatement statement) {
        statement.getExpression().visit(this);
    }
    
    public void visitReturnStatement(final ReturnStatement statement) {
        statement.getExpression().visit(this);
    }
    
    public void visitAssertStatement(final AssertStatement statement) {
        statement.getBooleanExpression().visit(this);
        statement.getMessageExpression().visit(this);
    }
    
    public void visitTryCatchFinally(final TryCatchStatement statement) {
        statement.getTryStatement().visit(this);
        for (final CatchStatement catchStatement : statement.getCatchStatements()) {
            catchStatement.visit(this);
        }
        final Statement finallyStatement = statement.getFinallyStatement();
        if (finallyStatement instanceof EmptyStatement) {
            this.visitEmptyStatement((EmptyStatement)finallyStatement);
        }
        else {
            finallyStatement.visit(this);
        }
    }
    
    protected void visitEmptyStatement(final EmptyStatement statement) {
    }
    
    public void visitSwitch(final SwitchStatement statement) {
        statement.getExpression().visit(this);
        for (final CaseStatement caseStatement : statement.getCaseStatements()) {
            caseStatement.visit(this);
        }
        statement.getDefaultStatement().visit(this);
    }
    
    public void visitCaseStatement(final CaseStatement statement) {
        statement.getExpression().visit(this);
        statement.getCode().visit(this);
    }
    
    public void visitBreakStatement(final BreakStatement statement) {
    }
    
    public void visitContinueStatement(final ContinueStatement statement) {
    }
    
    public void visitSynchronizedStatement(final SynchronizedStatement statement) {
        statement.getExpression().visit(this);
        statement.getCode().visit(this);
    }
    
    public void visitThrowStatement(final ThrowStatement statement) {
        statement.getExpression().visit(this);
    }
    
    public void visitMethodCallExpression(final MethodCallExpression call) {
        call.getObjectExpression().visit(this);
        call.getMethod().visit(this);
        call.getArguments().visit(this);
    }
    
    public void visitStaticMethodCallExpression(final StaticMethodCallExpression call) {
        call.getArguments().visit(this);
    }
    
    public void visitConstructorCallExpression(final ConstructorCallExpression call) {
        call.getArguments().visit(this);
    }
    
    public void visitBinaryExpression(final BinaryExpression expression) {
        expression.getLeftExpression().visit(this);
        expression.getRightExpression().visit(this);
    }
    
    public void visitTernaryExpression(final TernaryExpression expression) {
        expression.getBooleanExpression().visit(this);
        expression.getTrueExpression().visit(this);
        expression.getFalseExpression().visit(this);
    }
    
    public void visitShortTernaryExpression(final ElvisOperatorExpression expression) {
        this.visitTernaryExpression(expression);
    }
    
    public void visitPostfixExpression(final PostfixExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitPrefixExpression(final PrefixExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitBooleanExpression(final BooleanExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitNotExpression(final NotExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitClosureExpression(final ClosureExpression expression) {
        expression.getCode().visit(this);
    }
    
    public void visitTupleExpression(final TupleExpression expression) {
        this.visitListOfExpressions(expression.getExpressions());
    }
    
    public void visitListExpression(final ListExpression expression) {
        this.visitListOfExpressions(expression.getExpressions());
    }
    
    public void visitArrayExpression(final ArrayExpression expression) {
        this.visitListOfExpressions(expression.getExpressions());
        this.visitListOfExpressions(expression.getSizeExpression());
    }
    
    public void visitMapExpression(final MapExpression expression) {
        this.visitListOfExpressions(expression.getMapEntryExpressions());
    }
    
    public void visitMapEntryExpression(final MapEntryExpression expression) {
        expression.getKeyExpression().visit(this);
        expression.getValueExpression().visit(this);
    }
    
    public void visitRangeExpression(final RangeExpression expression) {
        expression.getFrom().visit(this);
        expression.getTo().visit(this);
    }
    
    public void visitSpreadExpression(final SpreadExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitSpreadMapExpression(final SpreadMapExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitMethodPointerExpression(final MethodPointerExpression expression) {
        expression.getExpression().visit(this);
        expression.getMethodName().visit(this);
    }
    
    public void visitUnaryMinusExpression(final UnaryMinusExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitUnaryPlusExpression(final UnaryPlusExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitBitwiseNegationExpression(final BitwiseNegationExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitCastExpression(final CastExpression expression) {
        expression.getExpression().visit(this);
    }
    
    public void visitConstantExpression(final ConstantExpression expression) {
    }
    
    public void visitClassExpression(final ClassExpression expression) {
    }
    
    public void visitVariableExpression(final VariableExpression expression) {
    }
    
    public void visitDeclarationExpression(final DeclarationExpression expression) {
        this.visitBinaryExpression(expression);
    }
    
    public void visitPropertyExpression(final PropertyExpression expression) {
        expression.getObjectExpression().visit(this);
        expression.getProperty().visit(this);
    }
    
    public void visitAttributeExpression(final AttributeExpression expression) {
        expression.getObjectExpression().visit(this);
        expression.getProperty().visit(this);
    }
    
    public void visitFieldExpression(final FieldExpression expression) {
    }
    
    @Deprecated
    public void visitRegexExpression(final RegexExpression expression) {
    }
    
    public void visitGStringExpression(final GStringExpression expression) {
        this.visitListOfExpressions(expression.getStrings());
        this.visitListOfExpressions(expression.getValues());
    }
    
    protected void visitListOfExpressions(final List<? extends Expression> list) {
        if (list == null) {
            return;
        }
        for (final Expression expression : list) {
            if (expression instanceof SpreadExpression) {
                final Expression spread = ((SpreadExpression)expression).getExpression();
                spread.visit(this);
            }
            else {
                expression.visit(this);
            }
        }
    }
    
    public void visitCatchStatement(final CatchStatement statement) {
        statement.getCode().visit(this);
    }
    
    public void visitArgumentlistExpression(final ArgumentListExpression ale) {
        this.visitTupleExpression(ale);
    }
    
    public void visitClosureListExpression(final ClosureListExpression cle) {
        this.visitListOfExpressions(cle.getExpressions());
    }
    
    public void visitBytecodeExpression(final BytecodeExpression cle) {
    }
}
