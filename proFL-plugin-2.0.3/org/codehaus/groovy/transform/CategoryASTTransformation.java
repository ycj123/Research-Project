// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.stmt.ForStatement;
import java.util.Iterator;
import java.util.List;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.MethodNode;
import java.util.Collection;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.ClassCodeExpressionTransformer;
import groovy.lang.Reference;
import org.codehaus.groovy.ast.FieldNode;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.Arrays;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.control.CompilePhase;
import groovyjarjarasm.asm.Opcodes;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class CategoryASTTransformation implements ASTTransformation, Opcodes
{
    private static final VariableExpression THIS_EXPRESSION;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof ClassNode)) {
            throw new RuntimeException("Internal error: expecting [AnnotationNode, ClassNode] but got: " + Arrays.asList(nodes));
        }
        final AnnotationNode annotation = (AnnotationNode)nodes[0];
        final ClassNode parent = (ClassNode)nodes[1];
        final ClassNode targetClass = this.getTargetClass(source, annotation);
        final LinkedList<Set<String>> varStack = new LinkedList<Set<String>>();
        final Set<String> names = new HashSet<String>();
        for (final FieldNode field : parent.getFields()) {
            names.add(field.getName());
        }
        varStack.add(names);
        final Reference parameter = new Reference();
        final ClassCodeExpressionTransformer expressionTransformer = new ClassCodeExpressionTransformer() {
            @Override
            protected SourceUnit getSourceUnit() {
                return source;
            }
            
            private void addVariablesToStack(final Parameter[] params) {
                final Set<String> names = new HashSet<String>();
                names.addAll(varStack.getLast());
                for (final Parameter param : params) {
                    names.add(param.getName());
                }
                varStack.add(names);
            }
            
            @Override
            public void visitMethod(final MethodNode node) {
                this.addVariablesToStack(node.getParameters());
                super.visitMethod(node);
                varStack.removeLast();
            }
            
            @Override
            public void visitBlockStatement(final BlockStatement block) {
                final Set<String> names = new HashSet<String>();
                names.addAll(varStack.getLast());
                varStack.add(names);
                super.visitBlockStatement(block);
                varStack.remove(names);
            }
            
            @Override
            public void visitClosureExpression(final ClosureExpression ce) {
                this.addVariablesToStack(ce.getParameters());
                super.visitClosureExpression(ce);
                varStack.removeLast();
            }
            
            @Override
            public void visitDeclarationExpression(final DeclarationExpression expression) {
                if (expression.isMultipleAssignmentDeclaration()) {
                    final TupleExpression te = (TupleExpression)expression.getLeftExpression();
                    final List<Expression> list = te.getExpressions();
                    for (final Expression arg : list) {
                        final VariableExpression ve = (VariableExpression)arg;
                        varStack.getLast().add(ve.getName());
                    }
                }
                else {
                    final VariableExpression ve2 = expression.getVariableExpression();
                    varStack.getLast().add(ve2.getName());
                }
                super.visitDeclarationExpression(expression);
            }
            
            @Override
            public void visitForLoop(final ForStatement forLoop) {
                final Expression exp = forLoop.getCollectionExpression();
                exp.visit(this);
                final Parameter loopParam = forLoop.getVariable();
                if (loopParam != null) {
                    varStack.getLast().add(loopParam.getName());
                }
                super.visitForLoop(forLoop);
            }
            
            @Override
            public void visitExpressionStatement(final ExpressionStatement es) {
                final Expression exp = es.getExpression();
                if (exp instanceof DeclarationExpression) {
                    exp.visit(this);
                }
                super.visitExpressionStatement(es);
            }
            
            @Override
            public Expression transform(final Expression exp) {
                if (exp instanceof VariableExpression) {
                    final VariableExpression ve = (VariableExpression)exp;
                    if (ve.getName().equals("this")) {
                        return CategoryASTTransformation.THIS_EXPRESSION;
                    }
                    if (!varStack.getLast().contains(ve.getName())) {
                        return new PropertyExpression(CategoryASTTransformation.THIS_EXPRESSION, ve.getName());
                    }
                }
                else if (exp instanceof PropertyExpression) {
                    final PropertyExpression pe = (PropertyExpression)exp;
                    if (pe.getObjectExpression() instanceof VariableExpression) {
                        final VariableExpression vex = (VariableExpression)pe.getObjectExpression();
                        if (vex.isThisExpression()) {
                            pe.setObjectExpression(CategoryASTTransformation.THIS_EXPRESSION);
                            return pe;
                        }
                    }
                }
                else if (exp instanceof ClosureExpression) {
                    final ClosureExpression ce = (ClosureExpression)exp;
                    ce.getVariableScope().putReferencedLocalVariable(parameter.get());
                    Parameter[] params = ce.getParameters();
                    if (params == null) {
                        params = new Parameter[0];
                    }
                    else if (params.length == 0) {
                        params = new Parameter[] { new Parameter(ClassHelper.OBJECT_TYPE, "it") };
                    }
                    this.addVariablesToStack(params);
                    ce.getCode().visit(this);
                    varStack.removeLast();
                }
                return super.transform(exp);
            }
        };
        for (final MethodNode method : parent.getMethods()) {
            if (!method.isStatic()) {
                method.setModifiers(method.getModifiers() | 0x8);
                final Parameter[] origParams = method.getParameters();
                final Parameter[] newParams = new Parameter[origParams.length + 1];
                final Parameter p = new Parameter(targetClass, "$this");
                p.setClosureSharedVariable(true);
                parameter.set(newParams[0] = p);
                System.arraycopy(origParams, 0, newParams, 1, origParams.length);
                method.setParameters(newParams);
                expressionTransformer.visitMethod(method);
            }
        }
    }
    
    private ClassNode getTargetClass(final SourceUnit source, final AnnotationNode annotation) {
        final Expression value = annotation.getMember("value");
        if (value == null || !(value instanceof ClassExpression)) {
            source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException("@groovy.lang.Category must define 'value' which is the class to apply this category to", annotation.getLineNumber(), annotation.getColumnNumber()), source));
        }
        return (value != null) ? value.getType() : null;
    }
    
    static {
        (THIS_EXPRESSION = new VariableExpression("$this")).setClosureSharedVariable(true);
    }
}
