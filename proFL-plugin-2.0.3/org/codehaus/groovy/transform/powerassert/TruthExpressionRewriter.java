// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

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
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.syntax.Types;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.RegexExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.classgen.BytecodeExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.GroovyCodeVisitor;

public class TruthExpressionRewriter implements GroovyCodeVisitor
{
    private final SourceText sourceText;
    private final AssertionRewriter assertionRewriter;
    private Expression result;
    
    private TruthExpressionRewriter(final SourceText sourceText, final AssertionRewriter assertionRewriter) {
        this.sourceText = sourceText;
        this.assertionRewriter = assertionRewriter;
    }
    
    public static Expression rewrite(final Expression truthExpr, final SourceText sourceText, final AssertionRewriter assertionRewriter) {
        return new TruthExpressionRewriter(sourceText, assertionRewriter).rewrite(truthExpr);
    }
    
    public void visitMethodCallExpression(final MethodCallExpression expr) {
        final MethodCallExpression conversion = new MethodCallExpression(expr.isImplicitThis() ? expr.getObjectExpression() : this.rewrite(expr.getObjectExpression()), this.rewrite(expr.getMethod()), this.rewrite(expr.getArguments()));
        conversion.setSafe(expr.isSafe());
        conversion.setSpreadSafe(expr.isSpreadSafe());
        conversion.setSourcePosition(expr);
        this.result = this.record(conversion, expr.getMethod());
    }
    
    public void visitStaticMethodCallExpression(final StaticMethodCallExpression expr) {
        final StaticMethodCallExpression conversion = new StaticMethodCallExpression(expr.getOwnerType(), expr.getMethod(), this.rewrite(expr.getArguments()));
        conversion.setSourcePosition(expr);
        conversion.setMetaMethod(expr.getMetaMethod());
        this.result = this.record(conversion);
    }
    
    public void visitBytecodeExpression(final BytecodeExpression expr) {
        unsupported();
    }
    
    public void visitArgumentlistExpression(final ArgumentListExpression expr) {
        final ArgumentListExpression conversion = new ArgumentListExpression(this.rewriteAll(expr.getExpressions()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitPropertyExpression(final PropertyExpression expr) {
        final PropertyExpression conversion = new PropertyExpression(expr.isImplicitThis() ? expr.getObjectExpression() : this.rewrite(expr.getObjectExpression()), expr.getProperty(), expr.isSafe());
        conversion.setSourcePosition(expr);
        conversion.setSpreadSafe(expr.isSpreadSafe());
        conversion.setStatic(expr.isStatic());
        conversion.setImplicitThis(expr.isImplicitThis());
        this.result = this.record(conversion, expr.getProperty());
    }
    
    public void visitAttributeExpression(final AttributeExpression expr) {
        final AttributeExpression conversion = new AttributeExpression(expr.isImplicitThis() ? expr.getObjectExpression() : this.rewrite(expr.getObjectExpression()), expr.getProperty(), expr.isSafe());
        conversion.setSourcePosition(expr);
        conversion.setSpreadSafe(expr.isSpreadSafe());
        conversion.setStatic(expr.isStatic());
        conversion.setImplicitThis(expr.isImplicitThis());
        this.result = this.record(conversion, expr.getProperty());
    }
    
    public void visitFieldExpression(final FieldExpression expr) {
        this.result = this.record(expr);
    }
    
    public void visitMethodPointerExpression(final MethodPointerExpression expr) {
        final MethodPointerExpression conversion = new MethodPointerExpression(this.rewrite(expr.getExpression()), this.rewrite(expr.getMethodName()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitVariableExpression(final VariableExpression expr) {
        this.result = this.record(expr);
    }
    
    public void visitDeclarationExpression(final DeclarationExpression expr) {
        unsupported();
    }
    
    @Deprecated
    public void visitRegexExpression(final RegexExpression expr) {
        unsupported();
    }
    
    public void visitBinaryExpression(final BinaryExpression expr) {
        final Expression left = expr.getLeftExpression();
        final Expression right = expr.getRightExpression();
        final Token op = expr.getOperation();
        final BinaryExpression conversion = new BinaryExpression(Types.ofType(op.getType(), 1100) ? left : this.rewrite(left), op, this.rewrite(right));
        conversion.setSourcePosition(expr);
        this.result = (Types.ofType(op.getType(), 1500) ? this.record(conversion, this.sourceText.getNormalizedColumn(op.getText(), right.getLineNumber(), right.getColumnNumber())) : this.record(conversion, op));
    }
    
    public void visitConstantExpression(final ConstantExpression expr) {
        this.result = expr;
    }
    
    public void visitClassExpression(final ClassExpression expr) {
        this.result = expr;
    }
    
    public void visitUnaryMinusExpression(final UnaryMinusExpression expr) {
        final UnaryMinusExpression conversion = new UnaryMinusExpression(this.rewrite(expr.getExpression()));
        conversion.setSourcePosition(expr);
        this.result = this.record(conversion);
    }
    
    public void visitUnaryPlusExpression(final UnaryPlusExpression expr) {
        final UnaryPlusExpression conversion = new UnaryPlusExpression(this.rewrite(expr.getExpression()));
        conversion.setSourcePosition(expr);
        this.result = this.record(conversion);
    }
    
    public void visitBitwiseNegationExpression(final BitwiseNegationExpression expr) {
        final BitwiseNegationExpression conversion = new BitwiseNegationExpression(this.rewrite(expr.getExpression()));
        conversion.setSourcePosition(expr);
        this.result = this.record(conversion);
    }
    
    public void visitCastExpression(final CastExpression expr) {
        final CastExpression conversion = new CastExpression(expr.getType(), this.rewrite(expr.getExpression()), expr.isIgnoringAutoboxing());
        conversion.setSourcePosition(expr);
        conversion.setCoerce(expr.isCoerce());
        this.result = conversion;
    }
    
    public void visitClosureListExpression(final ClosureListExpression expr) {
        this.result = expr;
    }
    
    public void visitNotExpression(final NotExpression expr) {
        final NotExpression conversion = new NotExpression(this.rewrite(expr.getExpression()));
        conversion.setSourcePosition(expr);
        this.result = this.record(conversion);
    }
    
    public void visitListExpression(final ListExpression expr) {
        final ListExpression conversion = new ListExpression(this.rewriteAll(expr.getExpressions()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitRangeExpression(final RangeExpression expr) {
        final RangeExpression conversion = new RangeExpression(this.rewrite(expr.getFrom()), this.rewrite(expr.getTo()), expr.isInclusive());
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitMapExpression(final MapExpression expr) {
        final MapExpression conversion = new MapExpression(this.rewriteAllCompatibly(expr.getMapEntryExpressions()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitMapEntryExpression(final MapEntryExpression expr) {
        final MapEntryExpression conversion = new MapEntryExpression(this.rewrite(expr.getKeyExpression()), this.rewrite(expr.getValueExpression()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitConstructorCallExpression(final ConstructorCallExpression expr) {
        final ConstructorCallExpression conversion = new ConstructorCallExpression(expr.getType(), this.rewrite(expr.getArguments()));
        conversion.setSourcePosition(expr);
        this.result = this.record(conversion);
    }
    
    public void visitGStringExpression(final GStringExpression expr) {
        final GStringExpression conversion = new GStringExpression(expr.getText(), expr.getStrings(), this.rewriteAll(expr.getValues()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitArrayExpression(final ArrayExpression expr) {
        final ArrayExpression conversion = new ArrayExpression(expr.getElementType(), this.rewriteAll(expr.getExpressions()), this.rewriteAll(expr.getSizeExpression()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitSpreadExpression(final SpreadExpression expr) {
        final SpreadExpression conversion = new SpreadExpression(this.rewrite(expr.getExpression()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitSpreadMapExpression(final SpreadMapExpression expr) {
        this.result = expr;
    }
    
    public void visitTernaryExpression(final TernaryExpression expr) {
        final TernaryExpression conversion = new TernaryExpression(new BooleanExpression(this.rewrite(expr.getBooleanExpression().getExpression())), this.rewrite(expr.getTrueExpression()), this.rewrite(expr.getFalseExpression()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitShortTernaryExpression(final ElvisOperatorExpression expr) {
        final ElvisOperatorExpression conversion = new ElvisOperatorExpression(this.rewrite(expr.getBooleanExpression().getExpression()), this.rewrite(expr.getFalseExpression()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    public void visitPrefixExpression(final PrefixExpression expr) {
        final PrefixExpression conversion = new PrefixExpression(expr.getOperation(), this.unrecord(this.rewrite(expr.getExpression())));
        conversion.setSourcePosition(expr);
        this.result = this.record(conversion);
    }
    
    public void visitPostfixExpression(final PostfixExpression expr) {
        final PostfixExpression conversion = new PostfixExpression(this.unrecord(this.rewrite(expr.getExpression())), expr.getOperation());
        conversion.setSourcePosition(expr);
        this.result = this.record(conversion);
    }
    
    public void visitBooleanExpression(final BooleanExpression expr) {
        this.result = this.rewrite(expr.getExpression());
    }
    
    public void visitClosureExpression(final ClosureExpression expr) {
        expr.getCode().visit(this.assertionRewriter);
        this.result = expr;
    }
    
    public void visitTupleExpression(final TupleExpression expr) {
        final TupleExpression conversion = new TupleExpression(this.rewriteAll(expr.getExpressions()));
        conversion.setSourcePosition(expr);
        this.result = conversion;
    }
    
    private Expression record(final Expression value, final int column) {
        return new MethodCallExpression(AssertionRewriter.recorderVariable, "record", new ArgumentListExpression(value, new ConstantExpression(column)));
    }
    
    private Expression record(final Expression value) {
        return this.record(value, this.sourceText.getNormalizedColumn(value.getLineNumber(), value.getColumnNumber()));
    }
    
    private Expression record(final Expression value, final ASTNode node) {
        return this.record(value, this.sourceText.getNormalizedColumn(node.getLineNumber(), node.getColumnNumber()));
    }
    
    private Expression record(final Expression value, final Token token) {
        return this.record(value, this.sourceText.getNormalizedColumn(token.getStartLine(), token.getStartColumn()));
    }
    
    private Expression unrecord(final Expression expr) {
        if (!(expr instanceof MethodCallExpression)) {
            return expr;
        }
        final MethodCallExpression methodExpr = (MethodCallExpression)expr;
        final Expression targetExpr = methodExpr.getObjectExpression();
        if (!(targetExpr instanceof VariableExpression)) {
            return expr;
        }
        final VariableExpression var = (VariableExpression)targetExpr;
        if (var != AssertionRewriter.recorderVariable) {
            return expr;
        }
        if (!methodExpr.getMethodAsString().equals("record")) {
            return expr;
        }
        return ((ArgumentListExpression)methodExpr.getArguments()).getExpression(0);
    }
    
    private <T extends Expression> T rewriteCompatibly(final T expr) {
        expr.visit(this);
        return (T)this.result;
    }
    
    private Expression rewrite(final Expression expr) {
        return this.rewriteCompatibly(expr);
    }
    
    private <T extends Expression> List<T> rewriteAllCompatibly(final List<T> exprs) {
        final List<T> result = new ArrayList<T>(exprs.size());
        for (final T expr : exprs) {
            result.add(this.rewriteCompatibly(expr));
        }
        return result;
    }
    
    private List<Expression> rewriteAll(final List<Expression> exprs) {
        return this.rewriteAllCompatibly(exprs);
    }
    
    private static void unsupported() {
        throw new UnsupportedOperationException();
    }
    
    public void visitBlockStatement(final BlockStatement stat) {
        unsupported();
    }
    
    public void visitForLoop(final ForStatement stat) {
        unsupported();
    }
    
    public void visitWhileLoop(final WhileStatement stat) {
        unsupported();
    }
    
    public void visitDoWhileLoop(final DoWhileStatement stat) {
        unsupported();
    }
    
    public void visitIfElse(final IfStatement stat) {
        unsupported();
    }
    
    public void visitExpressionStatement(final ExpressionStatement stat) {
        unsupported();
    }
    
    public void visitReturnStatement(final ReturnStatement stat) {
        unsupported();
    }
    
    public void visitAssertStatement(final AssertStatement stat) {
        unsupported();
    }
    
    public void visitTryCatchFinally(final TryCatchStatement stat) {
        unsupported();
    }
    
    public void visitSwitch(final SwitchStatement stat) {
        unsupported();
    }
    
    public void visitCaseStatement(final CaseStatement stat) {
        unsupported();
    }
    
    public void visitBreakStatement(final BreakStatement stat) {
        unsupported();
    }
    
    public void visitContinueStatement(final ContinueStatement stat) {
        unsupported();
    }
    
    public void visitThrowStatement(final ThrowStatement stat) {
        unsupported();
    }
    
    public void visitSynchronizedStatement(final SynchronizedStatement stat) {
        unsupported();
    }
    
    public void visitCatchStatement(final CatchStatement stat) {
        unsupported();
    }
}
