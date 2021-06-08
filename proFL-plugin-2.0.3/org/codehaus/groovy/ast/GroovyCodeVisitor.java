// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.classgen.BytecodeExpression;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.RegexExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.BlockStatement;

public interface GroovyCodeVisitor
{
    void visitBlockStatement(final BlockStatement p0);
    
    void visitForLoop(final ForStatement p0);
    
    void visitWhileLoop(final WhileStatement p0);
    
    void visitDoWhileLoop(final DoWhileStatement p0);
    
    void visitIfElse(final IfStatement p0);
    
    void visitExpressionStatement(final ExpressionStatement p0);
    
    void visitReturnStatement(final ReturnStatement p0);
    
    void visitAssertStatement(final AssertStatement p0);
    
    void visitTryCatchFinally(final TryCatchStatement p0);
    
    void visitSwitch(final SwitchStatement p0);
    
    void visitCaseStatement(final CaseStatement p0);
    
    void visitBreakStatement(final BreakStatement p0);
    
    void visitContinueStatement(final ContinueStatement p0);
    
    void visitThrowStatement(final ThrowStatement p0);
    
    void visitSynchronizedStatement(final SynchronizedStatement p0);
    
    void visitCatchStatement(final CatchStatement p0);
    
    void visitMethodCallExpression(final MethodCallExpression p0);
    
    void visitStaticMethodCallExpression(final StaticMethodCallExpression p0);
    
    void visitConstructorCallExpression(final ConstructorCallExpression p0);
    
    void visitTernaryExpression(final TernaryExpression p0);
    
    void visitShortTernaryExpression(final ElvisOperatorExpression p0);
    
    void visitBinaryExpression(final BinaryExpression p0);
    
    void visitPrefixExpression(final PrefixExpression p0);
    
    void visitPostfixExpression(final PostfixExpression p0);
    
    void visitBooleanExpression(final BooleanExpression p0);
    
    void visitClosureExpression(final ClosureExpression p0);
    
    void visitTupleExpression(final TupleExpression p0);
    
    void visitMapExpression(final MapExpression p0);
    
    void visitMapEntryExpression(final MapEntryExpression p0);
    
    void visitListExpression(final ListExpression p0);
    
    void visitRangeExpression(final RangeExpression p0);
    
    void visitPropertyExpression(final PropertyExpression p0);
    
    void visitAttributeExpression(final AttributeExpression p0);
    
    void visitFieldExpression(final FieldExpression p0);
    
    void visitMethodPointerExpression(final MethodPointerExpression p0);
    
    void visitConstantExpression(final ConstantExpression p0);
    
    void visitClassExpression(final ClassExpression p0);
    
    void visitVariableExpression(final VariableExpression p0);
    
    void visitDeclarationExpression(final DeclarationExpression p0);
    
    @Deprecated
    void visitRegexExpression(final RegexExpression p0);
    
    void visitGStringExpression(final GStringExpression p0);
    
    void visitArrayExpression(final ArrayExpression p0);
    
    void visitSpreadExpression(final SpreadExpression p0);
    
    void visitSpreadMapExpression(final SpreadMapExpression p0);
    
    void visitNotExpression(final NotExpression p0);
    
    void visitUnaryMinusExpression(final UnaryMinusExpression p0);
    
    void visitUnaryPlusExpression(final UnaryPlusExpression p0);
    
    void visitBitwiseNegationExpression(final BitwiseNegationExpression p0);
    
    void visitCastExpression(final CastExpression p0);
    
    void visitArgumentlistExpression(final ArgumentListExpression p0);
    
    void visitClosureListExpression(final ClosureListExpression p0);
    
    void visitBytecodeExpression(final BytecodeExpression p0);
}
