// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.GroovyClassVisitor;
import org.codehaus.groovy.ast.AnnotatedNode;
import java.util.Iterator;
import java.util.List;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.control.Janitor;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.expr.VariableExpression;

public class AssertionRewriter extends StatementReplacingVisitorSupport
{
    static final VariableExpression recorderVariable;
    private static final ClassNode verifierClass;
    private static final ClassNode recorderClass;
    private final SourceUnit sourceUnit;
    private final Janitor janitor;
    private boolean assertFound;
    
    private AssertionRewriter(final SourceUnit sourceUnit) {
        this.janitor = new Janitor();
        this.sourceUnit = sourceUnit;
    }
    
    public static void rewrite(final SourceUnit sourceUnit) {
        new AssertionRewriter(sourceUnit).visitModule();
    }
    
    private void visitModule() {
        final ModuleNode module = this.sourceUnit.getAST();
        try {
            final List<ClassNode> classes = module.getClasses();
            for (final ClassNode clazz : classes) {
                this.visitClass(clazz);
            }
        }
        finally {
            this.janitor.cleanup();
        }
    }
    
    @Override
    public void visitClass(final ClassNode node) {
        this.visitAnnotations(node);
        node.visitContents(this);
        this.visitInstanceInitializer(node.getObjectInitializerStatements());
    }
    
    private void visitInstanceInitializer(final List<Statement> stats) {
        final boolean old = this.assertFound;
        this.assertFound = false;
        for (final Statement stat : stats) {
            stat.visit(this);
        }
        if (this.assertFound) {
            defineRecorderVariable(stats);
        }
        this.assertFound = old;
    }
    
    @Override
    public void visitConstructor(final ConstructorNode constructor) {
        final boolean old = this.assertFound;
        this.assertFound = false;
        super.visitConstructor(constructor);
        if (this.assertFound) {
            defineRecorderVariable((BlockStatement)constructor.getCode());
        }
        this.assertFound = old;
    }
    
    @Override
    public void visitMethod(final MethodNode method) {
        final boolean old = this.assertFound;
        this.assertFound = false;
        super.visitMethod(method);
        if (this.assertFound) {
            defineRecorderVariable((BlockStatement)method.getCode());
        }
        this.assertFound = old;
    }
    
    @Override
    public void visitClosureExpression(final ClosureExpression expr) {
        final boolean old = this.assertFound;
        this.assertFound = false;
        super.visitClosureExpression(expr);
        if (this.assertFound) {
            defineRecorderVariable((BlockStatement)expr.getCode());
        }
        this.assertFound = old;
    }
    
    @Override
    public void visitAssertStatement(final AssertStatement stat) {
        super.visitAssertStatement(stat);
        this.rewriteAssertion(stat);
    }
    
    private void rewriteAssertion(final AssertStatement stat) {
        if (stat.getMessageExpression() != ConstantExpression.NULL) {
            return;
        }
        SourceText text;
        try {
            text = new SourceText(stat, this.sourceUnit, this.janitor);
        }
        catch (SourceTextNotAvailableException e) {
            return;
        }
        this.assertFound = true;
        final ExpressionStatement verifyCall = new ExpressionStatement(new MethodCallExpression(new ClassExpression(AssertionRewriter.verifierClass), "verify", new ArgumentListExpression(TruthExpressionRewriter.rewrite(stat.getBooleanExpression(), text, this), new ConstantExpression(text.getNormalizedText()), AssertionRewriter.recorderVariable)));
        final BlockStatement tryBlock = new BlockStatement();
        tryBlock.addStatement(verifyCall);
        tryBlock.setSourcePosition(stat);
        final TryCatchStatement tryCatchStat = new TryCatchStatement(tryBlock, new ExpressionStatement(new MethodCallExpression(AssertionRewriter.recorderVariable, "clear", ArgumentListExpression.EMPTY_ARGUMENTS)));
        this.replaceVisitedStatementWith(tryCatchStat);
    }
    
    private static void defineRecorderVariable(final BlockStatement block) {
        defineRecorderVariable(block.getStatements());
    }
    
    private static void defineRecorderVariable(final List<Statement> stats) {
        final int insertPos = startsWithConstructorCall(stats) ? 1 : 0;
        stats.add(insertPos, new ExpressionStatement(new DeclarationExpression(AssertionRewriter.recorderVariable, Token.newSymbol(100, -1, -1), new ConstructorCallExpression(AssertionRewriter.recorderClass, ArgumentListExpression.EMPTY_ARGUMENTS))));
    }
    
    private static boolean startsWithConstructorCall(final List<Statement> stats) {
        if (stats.size() == 0) {
            return false;
        }
        final Statement stat = stats.get(0);
        return stat instanceof ExpressionStatement && ((ExpressionStatement)stat).getExpression() instanceof ConstructorCallExpression;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        throw new UnsupportedOperationException("getSourceUnit");
    }
    
    static {
        recorderVariable = new VariableExpression("$valueRecorder");
        verifierClass = ClassHelper.makeWithoutCaching(AssertionVerifier.class);
        recorderClass = ClassHelper.makeWithoutCaching(ValueRecorder.class);
    }
}
