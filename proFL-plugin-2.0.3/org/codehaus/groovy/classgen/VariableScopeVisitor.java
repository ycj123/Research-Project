// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import java.util.List;
import java.util.Map;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import java.beans.Introspector;
import org.codehaus.groovy.ast.ClassHelper;
import java.util.Iterator;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.DynamicVariable;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.Parameter;
import java.util.LinkedList;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public class VariableScopeVisitor extends ClassCodeVisitorSupport
{
    private VariableScope currentScope;
    private VariableScope headScope;
    private ClassNode currentClass;
    private SourceUnit source;
    private boolean inClosure;
    private boolean inPropertyExpression;
    private boolean isSpecialConstructorCall;
    private boolean inConstructor;
    private LinkedList stateStack;
    
    public VariableScopeVisitor(final SourceUnit source) {
        this.currentScope = null;
        this.headScope = new VariableScope();
        this.currentClass = null;
        this.inClosure = false;
        this.inPropertyExpression = false;
        this.isSpecialConstructorCall = false;
        this.inConstructor = false;
        this.stateStack = new LinkedList();
        this.source = source;
        this.currentScope = this.headScope;
    }
    
    private void pushState(final boolean isStatic) {
        this.stateStack.add(new StateStackElement());
        (this.currentScope = new VariableScope(this.currentScope)).setInStaticContext(isStatic);
    }
    
    private void pushState() {
        this.pushState(this.currentScope.isInStaticContext());
    }
    
    private void popState() {
        if (this.inClosure) {
            this.currentScope.setInStaticContext(false);
        }
        final StateStackElement element = this.stateStack.removeLast();
        this.currentScope = element.scope;
        this.currentClass = element.clazz;
        this.inClosure = element.closure;
        this.inConstructor = element.inConstructor;
    }
    
    private void declare(final Parameter[] parameters, final ASTNode node) {
        for (final Parameter parameter : parameters) {
            if (parameter.hasInitialExpression()) {
                parameter.getInitialExpression().visit(this);
            }
            this.declare(parameter, node);
        }
    }
    
    private void declare(final VariableExpression vex) {
        vex.setInStaticContext(this.currentScope.isInStaticContext());
        this.declare(vex, vex);
        vex.setAccessedVariable(vex);
    }
    
    private void declare(final Variable var, final ASTNode expr) {
        String scopeType = "scope";
        String variableType = "variable";
        if (expr.getClass() == FieldNode.class) {
            scopeType = "class";
            variableType = "field";
        }
        else if (expr.getClass() == PropertyNode.class) {
            scopeType = "class";
            variableType = "property";
        }
        final StringBuffer msg = new StringBuffer();
        msg.append("The current ").append(scopeType);
        msg.append(" already contains a ").append(variableType);
        msg.append(" of the name ").append(var.getName());
        if (this.currentScope.getDeclaredVariable(var.getName()) != null) {
            this.addError(msg.toString(), expr);
            return;
        }
        for (VariableScope scope = this.currentScope.getParent(); scope != null; scope = scope.getParent()) {
            if (scope.getClassScope() != null) {
                break;
            }
            if (scope.getDeclaredVariable(var.getName()) != null) {
                this.addError(msg.toString(), expr);
                break;
            }
        }
        this.currentScope.putDeclaredVariable(var);
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    private Variable findClassMember(final ClassNode cn, final String name) {
        if (cn == null) {
            return null;
        }
        if (cn.isScript()) {
            return new DynamicVariable(name, false);
        }
        for (final FieldNode fn : cn.getFields()) {
            if (fn.getName().equals(name)) {
                return fn;
            }
        }
        for (final MethodNode mn : cn.getMethods()) {
            final String pName = this.getPropertyName(mn);
            if (pName != null && pName.equals(name)) {
                return new PropertyNode(pName, mn.getModifiers(), this.getPropertyType(mn), cn, null, null, null);
            }
        }
        for (final PropertyNode pn : cn.getProperties()) {
            if (pn.getName().equals(name)) {
                return pn;
            }
        }
        final Variable ret = this.findClassMember(cn.getSuperClass(), name);
        if (ret != null) {
            return ret;
        }
        return this.findClassMember(cn.getOuterClass(), name);
    }
    
    private ClassNode getPropertyType(final MethodNode m) {
        if (m.getReturnType() != ClassHelper.VOID_TYPE) {
            return m.getReturnType();
        }
        return m.getParameters()[0].getType();
    }
    
    private String getPropertyName(final MethodNode m) {
        final String name = m.getName();
        if (!name.startsWith("set") && !name.startsWith("get")) {
            return null;
        }
        String pname = name.substring(3);
        if (pname.length() == 0) {
            return null;
        }
        pname = Introspector.decapitalize(pname);
        if (name.startsWith("get") && (m.getReturnType() == ClassHelper.VOID_TYPE || m.getParameters().length != 0)) {
            return null;
        }
        if (name.startsWith("set") && m.getParameters().length != 1) {
            return null;
        }
        return pname;
    }
    
    private Variable checkVariableNameForDeclaration(final String name, final Expression expression) {
        if ("super".equals(name) || "this".equals(name)) {
            return null;
        }
        VariableScope scope = this.currentScope;
        Variable var = new DynamicVariable(name, this.currentScope.isInStaticContext());
        while (true) {
            Variable var2 = scope.getDeclaredVariable(var.getName());
            if (var2 != null) {
                var = var2;
                break;
            }
            var2 = scope.getReferencedLocalVariable(var.getName());
            if (var2 != null) {
                var = var2;
                break;
            }
            var2 = scope.getReferencedClassVariable(var.getName());
            if (var2 != null) {
                var = var2;
                break;
            }
            final ClassNode classScope = scope.getClassScope();
            if (classScope != null) {
                final Variable member = this.findClassMember(classScope, var.getName());
                if (member != null) {
                    final boolean staticScope = this.currentScope.isInStaticContext() || this.isSpecialConstructorCall;
                    final boolean staticMember = member.isInStaticContext();
                    if (!staticScope || staticMember) {
                        var = member;
                    }
                    break;
                }
                break;
            }
            else {
                scope = scope.getParent();
            }
        }
        VariableScope end;
        for (end = scope, scope = this.currentScope; scope != end; scope = scope.getParent()) {
            if (end.isClassScope() || (end.isReferencedClassVariable(name) && end.getDeclaredVariable(name) == null)) {
                scope.putReferencedClassVariable(var);
            }
            else {
                var.setClosureSharedVariable(var.isClosureSharedVariable() || this.inClosure);
                scope.putReferencedLocalVariable(var);
            }
        }
        return var;
    }
    
    private void checkPropertyOnExplicitThis(final PropertyExpression pe) {
        if (!this.currentScope.isInStaticContext()) {
            return;
        }
        final Expression object = pe.getObjectExpression();
        if (!(object instanceof VariableExpression)) {
            return;
        }
        final VariableExpression ve = (VariableExpression)object;
        if (!ve.getName().equals("this")) {
            return;
        }
        final String name = pe.getPropertyAsString();
        if (name.equals("class")) {
            return;
        }
        if (name == null) {
            return;
        }
        final Variable member = this.findClassMember(this.currentClass, name);
        if (member == null) {
            return;
        }
        this.checkVariableContextAccess(member, pe);
    }
    
    private void checkVariableContextAccess(final Variable v, final Expression expr) {
        if (this.inPropertyExpression || v.isInStaticContext() || !this.currentScope.isInStaticContext()) {
            return;
        }
        final String msg = v.getName() + " is declared in a dynamic context, but you tried to" + " access it from a static context.";
        this.addError(msg, expr);
        final DynamicVariable v2 = new DynamicVariable(v.getName(), this.currentScope.isInStaticContext());
        this.currentScope.putDeclaredVariable(v2);
    }
    
    @Override
    public void visitBlockStatement(final BlockStatement block) {
        this.pushState();
        block.setVariableScope(this.currentScope);
        super.visitBlockStatement(block);
        this.popState();
    }
    
    @Override
    public void visitForLoop(final ForStatement forLoop) {
        this.pushState();
        forLoop.setVariableScope(this.currentScope);
        final Parameter p = forLoop.getVariable();
        p.setInStaticContext(this.currentScope.isInStaticContext());
        if (p != ForStatement.FOR_LOOP_DUMMY) {
            this.declare(p, forLoop);
        }
        super.visitForLoop(forLoop);
        this.popState();
    }
    
    @Override
    public void visitDeclarationExpression(final DeclarationExpression expression) {
        expression.getRightExpression().visit(this);
        if (expression.isMultipleAssignmentDeclaration()) {
            final ArgumentListExpression list = (ArgumentListExpression)expression.getLeftExpression();
            for (final Expression e : list.getExpressions()) {
                final VariableExpression exp = (VariableExpression)e;
                this.declare(exp);
            }
        }
        else {
            this.declare(expression.getVariableExpression());
        }
    }
    
    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        final String name = expression.getName();
        final Variable v = this.checkVariableNameForDeclaration(name, expression);
        if (v == null) {
            return;
        }
        expression.setAccessedVariable(v);
        this.checkVariableContextAccess(v, expression);
    }
    
    @Override
    public void visitPropertyExpression(final PropertyExpression expression) {
        final boolean ipe = this.inPropertyExpression;
        this.inPropertyExpression = true;
        expression.getObjectExpression().visit(this);
        this.inPropertyExpression = false;
        expression.getProperty().visit(this);
        this.checkPropertyOnExplicitThis(expression);
        this.inPropertyExpression = ipe;
    }
    
    @Override
    public void visitClosureExpression(final ClosureExpression expression) {
        this.pushState();
        this.inClosure = true;
        expression.setVariableScope(this.currentScope);
        if (expression.isParameterSpecified()) {
            final Parameter[] arr$;
            final Parameter[] parameters = arr$ = expression.getParameters();
            for (final Parameter parameter : arr$) {
                parameter.setInStaticContext(this.currentScope.isInStaticContext());
                if (parameter.hasInitialExpression()) {
                    parameter.getInitialExpression().visit(this);
                }
                this.declare(parameter, expression);
            }
        }
        else if (expression.getParameters() != null) {
            final Parameter var = new Parameter(ClassHelper.OBJECT_TYPE, "it");
            var.setInStaticContext(this.currentScope.isInStaticContext());
            this.currentScope.putDeclaredVariable(var);
        }
        super.visitClosureExpression(expression);
        this.popState();
    }
    
    @Override
    public void visitCatchStatement(final CatchStatement statement) {
        this.pushState();
        final Parameter p = statement.getVariable();
        p.setInStaticContext(this.currentScope.isInStaticContext());
        this.declare(p, statement);
        super.visitCatchStatement(statement);
        this.popState();
    }
    
    @Override
    public void visitFieldExpression(final FieldExpression expression) {
        final String name = expression.getFieldName();
        final Variable v = this.checkVariableNameForDeclaration(name, expression);
        this.checkVariableContextAccess(v, expression);
    }
    
    @Override
    public void visitClass(final ClassNode node) {
        if (node instanceof InnerClassNode) {
            final InnerClassNode in = (InnerClassNode)node;
            if (in.isAnonymous()) {
                return;
            }
        }
        this.pushState();
        this.currentClass = node;
        this.currentScope.setClassScope(node);
        super.visitClass(node);
        this.popState();
    }
    
    @Override
    protected void visitConstructorOrMethod(final MethodNode node, final boolean isConstructor) {
        this.pushState(node.isStatic());
        this.inConstructor = isConstructor;
        node.setVariableScope(this.currentScope);
        final Parameter[] arr$;
        final Parameter[] parameters = arr$ = node.getParameters();
        for (final Parameter parameter : arr$) {
            this.visitAnnotations(parameter);
        }
        this.declare(node.getParameters(), node);
        super.visitConstructorOrMethod(node, isConstructor);
        this.popState();
    }
    
    @Override
    public void visitMethodCallExpression(final MethodCallExpression call) {
        if (call.isImplicitThis() && call.getMethod() instanceof ConstantExpression) {
            final ConstantExpression methodNameConstant = (ConstantExpression)call.getMethod();
            final Object value = methodNameConstant.getText();
            if (!(value instanceof String)) {
                throw new GroovyBugError("tried to make a method call with a non-String constant method name.");
            }
            final String methodName = (String)value;
            final Variable v = this.checkVariableNameForDeclaration(methodName, call);
            if (v != null && !(v instanceof DynamicVariable)) {
                this.checkVariableContextAccess(v, call);
            }
            if (v instanceof VariableExpression || v instanceof Parameter) {
                final VariableExpression object = new VariableExpression(v);
                object.setSourcePosition(methodNameConstant);
                call.setObjectExpression(object);
                final ConstantExpression method = new ConstantExpression("call");
                method.setSourcePosition(methodNameConstant);
                call.setMethod(method);
            }
        }
        super.visitMethodCallExpression(call);
    }
    
    @Override
    public void visitConstructorCallExpression(final ConstructorCallExpression call) {
        this.isSpecialConstructorCall = call.isSpecialCall();
        super.visitConstructorCallExpression(call);
        this.isSpecialConstructorCall = false;
        if (!call.isUsingAnonymousInnerClass()) {
            return;
        }
        this.pushState();
        final InnerClassNode innerClass = (InnerClassNode)call.getType();
        innerClass.setVariableScope(this.currentScope);
        for (final MethodNode method : innerClass.getMethods()) {
            Parameter[] parameters = method.getParameters();
            if (parameters.length == 0) {
                parameters = null;
            }
            final ClosureExpression cl = new ClosureExpression(parameters, method.getCode());
            this.visitClosureExpression(cl);
        }
        final boolean ic = this.inClosure;
        this.inClosure = true;
        for (final FieldNode field : innerClass.getFields()) {
            final Expression expression = field.getInitialExpression();
            if (expression != null) {
                expression.visit(this);
            }
        }
        for (final Statement statement : innerClass.getObjectInitializerStatements()) {
            statement.visit(this);
        }
        this.inClosure = ic;
        this.popState();
    }
    
    @Override
    public void visitProperty(final PropertyNode node) {
        this.pushState(node.isStatic());
        super.visitProperty(node);
        this.popState();
    }
    
    @Override
    public void visitField(final FieldNode node) {
        this.pushState(node.isStatic());
        super.visitField(node);
        this.popState();
    }
    
    @Override
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
                final Expression annMemberValue = member.getValue();
                annMemberValue.visit(this);
            }
        }
    }
    
    private class StateStackElement
    {
        VariableScope scope;
        ClassNode clazz;
        boolean closure;
        boolean inConstructor;
        
        StateStackElement() {
            this.scope = VariableScopeVisitor.this.currentScope;
            this.clazz = VariableScopeVisitor.this.currentClass;
            this.closure = VariableScopeVisitor.this.inClosure;
            this.inConstructor = VariableScopeVisitor.this.inConstructor;
        }
    }
}
