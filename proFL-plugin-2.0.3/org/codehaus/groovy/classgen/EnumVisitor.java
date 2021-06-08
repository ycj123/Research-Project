// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.CodeVisitorSupport;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.ConstructorNode;
import java.util.Collection;
import java.util.Iterator;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.EnumConstantClassNode;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import java.util.ArrayList;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import java.util.List;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public class EnumVisitor extends ClassCodeVisitorSupport
{
    private static final int FS = 24;
    private static final int PS = 9;
    private static final int PUBLIC_FS = 25;
    private static final int PRIVATE_FS = 26;
    private final CompilationUnit compilationUnit;
    private final SourceUnit sourceUnit;
    
    public EnumVisitor(final CompilationUnit cu, final SourceUnit su) {
        this.compilationUnit = cu;
        this.sourceUnit = su;
    }
    
    @Override
    public void visitClass(final ClassNode node) {
        if (!node.isEnum()) {
            return;
        }
        this.completeEnum(node);
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.sourceUnit;
    }
    
    private void completeEnum(final ClassNode enumClass) {
        final boolean isAic = this.isAnonymousInnerClass(enumClass);
        FieldNode minValue = null;
        FieldNode maxValue = null;
        FieldNode values = null;
        if (!isAic) {
            values = new FieldNode("$VALUES", 4122, enumClass.makeArray(), enumClass, null);
            values.setSynthetic(true);
            this.addMethods(enumClass, values);
            minValue = new FieldNode("MIN_VALUE", 25, enumClass, enumClass, null);
            maxValue = new FieldNode("MAX_VALUE", 25, enumClass, enumClass, null);
        }
        this.addInit(enumClass, minValue, maxValue, values, isAic);
    }
    
    private void addMethods(final ClassNode enumClass, final FieldNode values) {
        final List methods = enumClass.getMethods();
        boolean hasNext = false;
        boolean hasPrevious = false;
        for (int i = 0; i < methods.size(); ++i) {
            final MethodNode m = methods.get(i);
            if (m.getName().equals("next") && m.getParameters().length == 0) {
                hasNext = true;
            }
            if (m.getName().equals("previous") && m.getParameters().length == 0) {
                hasPrevious = true;
            }
            if (hasNext && hasPrevious) {
                break;
            }
        }
        final MethodNode valuesMethod = new MethodNode("values", 25, enumClass.makeArray(), new Parameter[0], ClassNode.EMPTY_ARRAY, null);
        valuesMethod.setSynthetic(true);
        final BlockStatement code = new BlockStatement();
        code.addStatement(new ReturnStatement(new MethodCallExpression(new FieldExpression(values), "clone", MethodCallExpression.NO_ARGUMENTS)));
        valuesMethod.setCode(code);
        enumClass.addMethod(valuesMethod);
        if (!hasNext) {
            final Token assign = Token.newSymbol(100, -1, -1);
            final Token ge = Token.newSymbol(127, -1, -1);
            final MethodNode nextMethod = new MethodNode("next", 4097, enumClass, new Parameter[0], ClassNode.EMPTY_ARRAY, null);
            nextMethod.setSynthetic(true);
            final BlockStatement code2 = new BlockStatement();
            final BlockStatement ifStatement = new BlockStatement();
            ifStatement.addStatement(new ExpressionStatement(new BinaryExpression(new VariableExpression("ordinal"), assign, new ConstantExpression(0))));
            code2.addStatement(new ExpressionStatement(new DeclarationExpression(new VariableExpression("ordinal"), assign, new MethodCallExpression(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "ordinal", MethodCallExpression.NO_ARGUMENTS), "next", MethodCallExpression.NO_ARGUMENTS))));
            code2.addStatement(new IfStatement(new BooleanExpression(new BinaryExpression(new VariableExpression("ordinal"), ge, new MethodCallExpression(new FieldExpression(values), "size", MethodCallExpression.NO_ARGUMENTS))), ifStatement, EmptyStatement.INSTANCE));
            code2.addStatement(new ReturnStatement(new MethodCallExpression(new FieldExpression(values), "getAt", new VariableExpression("ordinal"))));
            nextMethod.setCode(code2);
            enumClass.addMethod(nextMethod);
        }
        if (!hasPrevious) {
            final Token assign = Token.newSymbol(100, -1, -1);
            final Token lt = Token.newSymbol(124, -1, -1);
            final MethodNode nextMethod = new MethodNode("previous", 4097, enumClass, new Parameter[0], ClassNode.EMPTY_ARRAY, null);
            nextMethod.setSynthetic(true);
            final BlockStatement code2 = new BlockStatement();
            final BlockStatement ifStatement = new BlockStatement();
            ifStatement.addStatement(new ExpressionStatement(new BinaryExpression(new VariableExpression("ordinal"), assign, new MethodCallExpression(new MethodCallExpression(new FieldExpression(values), "size", MethodCallExpression.NO_ARGUMENTS), "minus", new ConstantExpression(1)))));
            code2.addStatement(new ExpressionStatement(new DeclarationExpression(new VariableExpression("ordinal"), assign, new MethodCallExpression(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "ordinal", MethodCallExpression.NO_ARGUMENTS), "previous", MethodCallExpression.NO_ARGUMENTS))));
            code2.addStatement(new IfStatement(new BooleanExpression(new BinaryExpression(new VariableExpression("ordinal"), lt, new ConstantExpression(0))), ifStatement, EmptyStatement.INSTANCE));
            code2.addStatement(new ReturnStatement(new MethodCallExpression(new FieldExpression(values), "getAt", new VariableExpression("ordinal"))));
            nextMethod.setCode(code2);
            enumClass.addMethod(nextMethod);
        }
        final Parameter stringParameter = new Parameter(ClassHelper.STRING_TYPE, "name");
        final MethodNode valueOfMethod = new MethodNode("valueOf", 9, enumClass, new Parameter[] { stringParameter }, ClassNode.EMPTY_ARRAY, null);
        final ArgumentListExpression callArguments = new ArgumentListExpression();
        callArguments.addExpression(new ClassExpression(enumClass));
        callArguments.addExpression(new VariableExpression("name"));
        final BlockStatement code2 = new BlockStatement();
        code2.addStatement(new ReturnStatement(new MethodCallExpression(new ClassExpression(ClassHelper.Enum_Type), "valueOf", callArguments)));
        valueOfMethod.setCode(code2);
        valueOfMethod.setSynthetic(true);
        enumClass.addMethod(valueOfMethod);
    }
    
    private void addInit(final ClassNode enumClass, final FieldNode minValue, final FieldNode maxValue, final FieldNode values, final boolean isAic) {
        this.addConstructor(enumClass);
        final Parameter[] parameter = { new Parameter(ClassHelper.OBJECT_TYPE.makeArray(), "para") };
        final MethodNode initMethod = new MethodNode("$INIT", 4121, enumClass, parameter, ClassNode.EMPTY_ARRAY, null);
        initMethod.setSynthetic(true);
        final ConstructorCallExpression cce = new ConstructorCallExpression(ClassNode.THIS, new ArgumentListExpression(new SpreadExpression(new VariableExpression("para"))));
        final BlockStatement code = new BlockStatement();
        code.addStatement(new ReturnStatement(cce));
        initMethod.setCode(code);
        enumClass.addMethod(initMethod);
        final List fields = enumClass.getFields();
        final List arrayInit = new ArrayList();
        int value = -1;
        final Token assign = Token.newSymbol(100, -1, -1);
        final List block = new ArrayList();
        FieldNode tempMin = null;
        FieldNode tempMax = null;
        for (final FieldNode field : fields) {
            if ((field.getModifiers() & 0x4000) == 0x0) {
                continue;
            }
            ++value;
            if (tempMin == null) {
                tempMin = field;
            }
            tempMax = field;
            ClassNode enumBase = enumClass;
            final ArgumentListExpression args = new ArgumentListExpression();
            args.addExpression(new ConstantExpression(field.getName()));
            args.addExpression(new ConstantExpression(value));
            if (field.getInitialExpression() != null) {
                final ListExpression oldArgs = (ListExpression)field.getInitialExpression();
                for (final Expression exp : oldArgs.getExpressions()) {
                    if (exp instanceof MapEntryExpression) {
                        final String msg = "The usage of a map entry expression to initialize an Enum is currently not supported, please use an explicit map instead.";
                        this.sourceUnit.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException(msg + '\n', exp.getLineNumber(), exp.getColumnNumber()), this.sourceUnit));
                    }
                    else {
                        InnerClassNode inner = null;
                        if (exp instanceof ClassExpression) {
                            final ClassExpression clazzExp = (ClassExpression)exp;
                            final ClassNode ref = clazzExp.getType();
                            if (ref instanceof EnumConstantClassNode) {
                                inner = (InnerClassNode)ref;
                            }
                        }
                        if (inner != null && inner.getVariableScope() == null) {
                            enumBase = inner;
                            initMethod.setModifiers(initMethod.getModifiers() & 0xFFFFFFEF);
                        }
                        else {
                            args.addExpression(exp);
                        }
                    }
                }
            }
            field.setInitialValueExpression(null);
            block.add(new ExpressionStatement(new BinaryExpression(new FieldExpression(field), assign, new StaticMethodCallExpression(enumBase, "$INIT", args))));
            arrayInit.add(new FieldExpression(field));
        }
        if (!isAic) {
            if (tempMin != null) {
                block.add(new ExpressionStatement(new BinaryExpression(new FieldExpression(minValue), assign, new FieldExpression(tempMin))));
                block.add(new ExpressionStatement(new BinaryExpression(new FieldExpression(maxValue), assign, new FieldExpression(tempMax))));
                enumClass.addField(minValue);
                enumClass.addField(maxValue);
            }
            block.add(new ExpressionStatement(new BinaryExpression(new FieldExpression(values), assign, new ArrayExpression(enumClass, arrayInit))));
            enumClass.addField(values);
        }
        enumClass.addStaticInitializerStatements(block, true);
    }
    
    private boolean isAnonymousInnerClass(final ClassNode enumClass) {
        if (!(enumClass instanceof EnumConstantClassNode)) {
            return false;
        }
        final InnerClassNode ic = (InnerClassNode)enumClass;
        return ic.getVariableScope() == null;
    }
    
    private void addConstructor(final ClassNode enumClass) {
        final List ctors = new ArrayList(enumClass.getDeclaredConstructors());
        if (ctors.size() == 0) {
            final ConstructorNode init = new ConstructorNode(1, new Parameter[0], ClassNode.EMPTY_ARRAY, new BlockStatement());
            enumClass.addConstructor(init);
            ctors.add(init);
        }
        final Iterator iterator = ctors.iterator();
        while (iterator.hasNext()) {
            boolean chainedThisConstructorCall = false;
            final ConstructorNode ctor = iterator.next();
            ConstructorCallExpression cce = null;
            if (ctor.firstStatementIsSpecialConstructorCall()) {
                final Statement code = ctor.getFirstStatement();
                cce = (ConstructorCallExpression)((ExpressionStatement)code).getExpression();
                if (cce.isSuperCall()) {
                    continue;
                }
                chainedThisConstructorCall = true;
            }
            final Parameter[] oldP = ctor.getParameters();
            final Parameter[] newP = new Parameter[oldP.length + 2];
            final String stringParameterName = this.getUniqueVariableName("__str", ctor.getCode());
            newP[0] = new Parameter(ClassHelper.STRING_TYPE, stringParameterName);
            final String intParameterName = this.getUniqueVariableName("__int", ctor.getCode());
            newP[1] = new Parameter(ClassHelper.int_TYPE, intParameterName);
            System.arraycopy(oldP, 0, newP, 2, oldP.length);
            ctor.setParameters(newP);
            if (chainedThisConstructorCall) {
                final TupleExpression args = (TupleExpression)cce.getArguments();
                final List<Expression> argsExprs = args.getExpressions();
                argsExprs.add(0, new VariableExpression(stringParameterName));
                argsExprs.add(1, new VariableExpression(intParameterName));
            }
            else {
                cce = new ConstructorCallExpression(ClassNode.SUPER, new ArgumentListExpression(new VariableExpression(stringParameterName), new VariableExpression(intParameterName)));
                final BlockStatement code2 = new BlockStatement();
                code2.addStatement(new ExpressionStatement(cce));
                final Statement oldCode = ctor.getCode();
                if (oldCode != null) {
                    code2.addStatement(oldCode);
                }
                ctor.setCode(code2);
            }
        }
    }
    
    private String getUniqueVariableName(final String name, final Statement code) {
        if (code == null) {
            return name;
        }
        final Object[] found = { null };
        final CodeVisitorSupport cv = new CodeVisitorSupport() {
            @Override
            public void visitVariableExpression(final VariableExpression expression) {
                if (expression.getName().equals(name)) {
                    found[0] = Boolean.TRUE;
                }
            }
        };
        code.visit(cv);
        if (found[0] != null) {
            return this.getUniqueVariableName("_" + name, code);
        }
        return name;
    }
}
