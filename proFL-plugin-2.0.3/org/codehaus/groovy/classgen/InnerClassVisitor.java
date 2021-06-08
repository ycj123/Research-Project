// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import java.util.Iterator;
import java.util.ArrayList;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.ASTNode;
import java.util.List;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.ConstructorNode;
import groovyjarjarasm.asm.MethodVisitor;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.SourceUnit;
import groovyjarjarasm.asm.Opcodes;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public class InnerClassVisitor extends ClassCodeVisitorSupport implements Opcodes
{
    private final SourceUnit sourceUnit;
    private ClassNode classNode;
    private static final int PUBLIC_SYNTHETIC = 4097;
    private FieldNode thisField;
    private MethodNode currentMethod;
    private FieldNode currentField;
    private boolean processingObjInitStatements;
    
    public InnerClassVisitor(final CompilationUnit cu, final SourceUnit su) {
        this.thisField = null;
        this.sourceUnit = su;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.sourceUnit;
    }
    
    @Override
    public void visitClass(final ClassNode node) {
        this.classNode = node;
        this.thisField = null;
        InnerClassNode innerClass = null;
        if (!node.isEnum() && !node.isInterface() && node instanceof InnerClassNode) {
            innerClass = (InnerClassNode)node;
            if (!this.isStatic(innerClass) && innerClass.getVariableScope() == null) {
                this.thisField = innerClass.addField("this$0", 4097, node.getOuterClass(), null);
            }
            if (innerClass.getVariableScope() == null && innerClass.getDeclaredConstructors().isEmpty()) {
                innerClass.addConstructor(4097, new Parameter[0], null, null);
            }
        }
        super.visitClass(node);
        if (node.isEnum() || node.isInterface()) {
            return;
        }
        this.addDispatcherMethods();
        if (innerClass == null) {
            return;
        }
        if (node.getSuperClass().isInterface()) {
            node.addInterface(node.getUnresolvedSuperClass());
            node.setUnresolvedSuperClass(ClassHelper.OBJECT_TYPE);
        }
        this.addDefaultMethods(innerClass);
    }
    
    @Override
    protected void visitObjectInitializerStatements(final ClassNode node) {
        this.processingObjInitStatements = true;
        super.visitObjectInitializerStatements(node);
        this.processingObjInitStatements = false;
    }
    
    private boolean isStatic(final InnerClassNode node) {
        final VariableScope scope = node.getVariableScope();
        if (scope != null) {
            return scope.isInStaticContext();
        }
        return (node.getModifiers() & 0x8) != 0x0;
    }
    
    private void addDefaultMethods(final InnerClassNode node) {
        final boolean isStatic = this.isStatic(node);
        final String classInternalName = BytecodeHelper.getClassInternalName(node);
        final String outerClassInternalName = this.getInternalName(node.getOuterClass(), isStatic);
        final String outerClassDescriptor = this.getTypeDescriptor(node.getOuterClass(), isStatic);
        final int objectDistance = this.getObjectDistance(node.getOuterClass());
        Parameter[] parameters = { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(ClassHelper.OBJECT_TYPE, "args") };
        MethodNode method = node.addSyntheticMethod("methodMissing", 1, ClassHelper.OBJECT_TYPE, parameters, ClassNode.EMPTY_ARRAY, null);
        BlockStatement block = new BlockStatement();
        if (isStatic) {
            this.setMethodDispatcherCode(block, new ClassExpression(node.getOuterClass()), parameters);
        }
        else {
            block.addStatement(new BytecodeSequence(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitVarInsn(25, 0);
                    mv.visitFieldInsn(180, classInternalName, "this$0", outerClassDescriptor);
                    mv.visitVarInsn(25, 1);
                    mv.visitVarInsn(25, 2);
                    mv.visitMethodInsn(182, outerClassInternalName, "this$dist$invoke$" + objectDistance, "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;");
                    mv.visitInsn(176);
                }
            }));
        }
        method.setCode(block);
        parameters = new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(ClassHelper.OBJECT_TYPE, "val") };
        method = node.addSyntheticMethod("propertyMissing", 1, ClassHelper.VOID_TYPE, parameters, ClassNode.EMPTY_ARRAY, null);
        block = new BlockStatement();
        if (isStatic) {
            this.setPropertySetDispatcher(block, new ClassExpression(node.getOuterClass()), parameters);
        }
        else {
            block.addStatement(new BytecodeSequence(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitVarInsn(25, 0);
                    mv.visitFieldInsn(180, classInternalName, "this$0", outerClassDescriptor);
                    mv.visitVarInsn(25, 1);
                    mv.visitVarInsn(25, 2);
                    mv.visitMethodInsn(182, outerClassInternalName, "this$dist$set$" + objectDistance, "(Ljava/lang/String;Ljava/lang/Object;)V");
                    mv.visitInsn(177);
                }
            }));
        }
        method.setCode(block);
        parameters = new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name") };
        method = node.addSyntheticMethod("propertyMissing", 1, ClassHelper.OBJECT_TYPE, parameters, ClassNode.EMPTY_ARRAY, null);
        block = new BlockStatement();
        if (isStatic) {
            this.setPropertyGetterDispatcher(block, new ClassExpression(node.getOuterClass()), parameters);
        }
        else {
            block.addStatement(new BytecodeSequence(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitVarInsn(25, 0);
                    mv.visitFieldInsn(180, classInternalName, "this$0", outerClassDescriptor);
                    mv.visitVarInsn(25, 1);
                    mv.visitMethodInsn(182, outerClassInternalName, "this$dist$get$" + objectDistance, "(Ljava/lang/String;)Ljava/lang/Object;");
                    mv.visitInsn(176);
                }
            }));
        }
        method.setCode(block);
    }
    
    private String getTypeDescriptor(final ClassNode node, final boolean isStatic) {
        return BytecodeHelper.getTypeDescription(this.getClassNode(node, isStatic));
    }
    
    private String getInternalName(final ClassNode node, final boolean isStatic) {
        return BytecodeHelper.getClassInternalName(this.getClassNode(node, isStatic));
    }
    
    @Override
    public void visitConstructor(final ConstructorNode node) {
        this.addThisReference(node);
        super.visitConstructor(node);
    }
    
    private boolean shouldHandleImplicitThisForInnerClass(final ClassNode cn) {
        if (cn.isEnum() || cn.isInterface()) {
            return false;
        }
        if ((cn.getModifiers() & 0x8) != 0x0) {
            return false;
        }
        if (!(cn instanceof InnerClassNode)) {
            return false;
        }
        final InnerClassNode innerClass = (InnerClassNode)cn;
        return innerClass.getVariableScope() == null && (innerClass.getModifiers() & 0x8) == 0x0;
    }
    
    private void addThisReference(final ConstructorNode node) {
        if (!this.shouldHandleImplicitThisForInnerClass(this.classNode)) {
            return;
        }
        final Statement code = node.getCode();
        final Parameter[] params = node.getParameters();
        final Parameter[] newParams = new Parameter[params.length + 1];
        System.arraycopy(params, 0, newParams, 1, params.length);
        final Parameter thisPara = new Parameter(this.classNode.getOuterClass(), this.getUniqueName(params, node));
        newParams[0] = thisPara;
        node.setParameters(newParams);
        final Statement firstStatement = node.getFirstStatement();
        BlockStatement block = null;
        if (code == null) {
            block = new BlockStatement();
        }
        else if (!(code instanceof BlockStatement)) {
            block = new BlockStatement();
            block.addStatement(code);
        }
        else {
            block = (BlockStatement)code;
        }
        final BlockStatement newCode = new BlockStatement();
        addFieldInit(thisPara, this.thisField, newCode);
        ConstructorCallExpression cce = this.getFirstIfSpecialConstructorCall(block);
        if (cce == null) {
            cce = new ConstructorCallExpression(ClassNode.SUPER, new TupleExpression());
            block.getStatements().add(0, new ExpressionStatement(cce));
        }
        if (this.shouldImplicitlyPassThisPara(cce)) {
            final TupleExpression args = (TupleExpression)cce.getArguments();
            final List<Expression> expressions = args.getExpressions();
            final VariableExpression ve = new VariableExpression(thisPara.getName());
            ve.setAccessedVariable(thisPara);
            expressions.add(0, ve);
        }
        if (cce.isSuperCall()) {
            block.getStatements().add(1, newCode);
        }
        node.setCode(block);
    }
    
    private boolean shouldImplicitlyPassThisPara(final ConstructorCallExpression cce) {
        boolean pass = false;
        final ClassNode superCN = this.classNode.getSuperClass();
        if (cce.isThisCall()) {
            pass = true;
        }
        else if (cce.isSuperCall() && !superCN.isEnum() && !superCN.isInterface() && superCN instanceof InnerClassNode) {
            final InnerClassNode superInnerCN = (InnerClassNode)superCN;
            if (!this.isStatic(superInnerCN) && superCN.getOuterClass().equals(this.classNode.getOuterClass())) {
                pass = true;
            }
        }
        return pass;
    }
    
    private String getUniqueName(final Parameter[] params, final ConstructorNode node) {
        String namePrefix = "$p";
        int i = 0;
    Label_0007:
        while (i < 100) {
            namePrefix += "$";
            for (final Parameter p : params) {
                if (p.getName().equals(namePrefix)) {
                    ++i;
                    continue Label_0007;
                }
            }
            return namePrefix;
        }
        this.addError("unable to find a unique prefix name for synthetic this reference", node);
        return namePrefix;
    }
    
    private ConstructorCallExpression getFirstIfSpecialConstructorCall(final BlockStatement code) {
        if (code == null) {
            return null;
        }
        final List<Statement> statementList = code.getStatements();
        if (statementList.isEmpty()) {
            return null;
        }
        final Statement statement = statementList.get(0);
        if (!(statement instanceof ExpressionStatement)) {
            return null;
        }
        final Expression expression = ((ExpressionStatement)statement).getExpression();
        if (!(expression instanceof ConstructorCallExpression)) {
            return null;
        }
        final ConstructorCallExpression cce = (ConstructorCallExpression)expression;
        if (cce.isSpecialCall()) {
            return cce;
        }
        return null;
    }
    
    @Override
    protected void visitConstructorOrMethod(final MethodNode node, final boolean isConstructor) {
        super.visitConstructorOrMethod(this.currentMethod = node, isConstructor);
        this.currentMethod = null;
    }
    
    @Override
    public void visitField(final FieldNode node) {
        super.visitField(this.currentField = node);
        this.currentField = null;
    }
    
    @Override
    public void visitProperty(final PropertyNode node) {
        final FieldNode field = node.getField();
        final Expression init = field.getInitialExpression();
        field.setInitialValueExpression(null);
        super.visitProperty(node);
        field.setInitialValueExpression(init);
    }
    
    @Override
    public void visitConstructorCallExpression(final ConstructorCallExpression call) {
        super.visitConstructorCallExpression(call);
        if (!call.isUsingAnonymousInnerClass()) {
            this.passThisReference(call);
            return;
        }
        final InnerClassNode innerClass = (InnerClassNode)call.getType();
        if (!innerClass.getDeclaredConstructors().isEmpty()) {
            return;
        }
        if ((innerClass.getModifiers() & 0x8) != 0x0) {
            return;
        }
        final VariableScope scope = innerClass.getVariableScope();
        if (scope == null) {
            return;
        }
        final boolean isStatic = scope.isInStaticContext();
        final List<Expression> expressions = ((TupleExpression)call.getArguments()).getExpressions();
        final BlockStatement block = new BlockStatement();
        final int additionalParamCount = 1 + scope.getReferencedLocalVariablesCount();
        final List parameters = new ArrayList(expressions.size() + additionalParamCount);
        final List superCallArguments = new ArrayList(expressions.size());
        int pCount = additionalParamCount;
        for (final Expression expr : expressions) {
            ++pCount;
            final Parameter param = new Parameter(ClassHelper.OBJECT_TYPE, "p" + pCount);
            parameters.add(param);
            superCallArguments.add(new VariableExpression(param));
        }
        final ConstructorCallExpression cce = new ConstructorCallExpression(ClassNode.SUPER, new TupleExpression(superCallArguments));
        block.addStatement(new ExpressionStatement(cce));
        pCount = 0;
        expressions.add(pCount, VariableExpression.THIS_EXPRESSION);
        final ClassNode outerClassType = this.getClassNode(innerClass.getOuterClass(), isStatic);
        final Parameter thisParameter = new Parameter(outerClassType, "p" + pCount);
        parameters.add(pCount, thisParameter);
        addFieldInit(thisParameter, this.thisField = innerClass.addField("this$0", 4097, outerClassType, null), block);
        final Iterator it = scope.getReferencedLocalVariablesIterator();
        while (it.hasNext()) {
            ++pCount;
            final Variable var = it.next();
            final VariableExpression ve = new VariableExpression(var);
            ve.setClosureSharedVariable(true);
            ve.setUseReferenceDirectly(true);
            expressions.add(pCount, ve);
            final Parameter p = new Parameter(ClassHelper.REFERENCE_TYPE, "p" + pCount);
            parameters.add(pCount, p);
            final VariableExpression initial = new VariableExpression(p);
            initial.setUseReferenceDirectly(true);
            final FieldNode pField = innerClass.addFieldFirst(ve.getName(), 4097, ClassHelper.REFERENCE_TYPE, initial);
            pField.setHolder(true);
        }
        innerClass.addConstructor(4096, parameters.toArray(new Parameter[0]), ClassNode.EMPTY_ARRAY, block);
    }
    
    private void passThisReference(final ConstructorCallExpression call) {
        final ClassNode cn = call.getType().redirect();
        if (!this.shouldHandleImplicitThisForInnerClass(cn)) {
            return;
        }
        boolean isInStaticContext = true;
        if (this.currentMethod != null) {
            isInStaticContext = this.currentMethod.getVariableScope().isInStaticContext();
        }
        else if (this.currentField != null) {
            isInStaticContext = this.currentField.isStatic();
        }
        else if (this.processingObjInitStatements) {
            isInStaticContext = false;
        }
        if (isInStaticContext) {
            final Expression args = call.getArguments();
            if (args instanceof TupleExpression && ((TupleExpression)args).getExpressions().isEmpty()) {
                this.addError("No enclosing instance passed in constructor call of a non-static inner class", call);
            }
            return;
        }
        ClassNode parent = this.classNode;
        int level = 0;
        while (parent != null && parent != cn.getOuterClass()) {
            ++level;
            parent = parent.getOuterClass();
        }
        if (parent == null) {
            return;
        }
        final Expression argsExp = call.getArguments();
        if (argsExp instanceof TupleExpression) {
            final TupleExpression argsListExp = (TupleExpression)argsExp;
            Expression this2 = VariableExpression.THIS_EXPRESSION;
            for (int i = 0; i != level; ++i) {
                this2 = new PropertyExpression(this2, "this$0");
            }
            argsListExp.getExpressions().add(0, this2);
        }
    }
    
    private ClassNode getClassNode(ClassNode node, final boolean isStatic) {
        if (isStatic) {
            node = ClassHelper.CLASS_Type;
        }
        return node;
    }
    
    private void addDispatcherMethods() {
        final int objectDistance = this.getObjectDistance(this.classNode);
        Parameter[] parameters = { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(ClassHelper.OBJECT_TYPE, "args") };
        MethodNode method = this.classNode.addSyntheticMethod("this$dist$invoke$" + objectDistance, 4097, ClassHelper.OBJECT_TYPE, parameters, ClassNode.EMPTY_ARRAY, null);
        BlockStatement block = new BlockStatement();
        this.setMethodDispatcherCode(block, VariableExpression.THIS_EXPRESSION, parameters);
        method.setCode(block);
        parameters = new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(ClassHelper.OBJECT_TYPE, "value") };
        method = this.classNode.addSyntheticMethod("this$dist$set$" + objectDistance, 4097, ClassHelper.VOID_TYPE, parameters, ClassNode.EMPTY_ARRAY, null);
        block = new BlockStatement();
        this.setPropertySetDispatcher(block, VariableExpression.THIS_EXPRESSION, parameters);
        method.setCode(block);
        parameters = new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name") };
        method = this.classNode.addSyntheticMethod("this$dist$get$" + objectDistance, 4097, ClassHelper.OBJECT_TYPE, parameters, ClassNode.EMPTY_ARRAY, null);
        block = new BlockStatement();
        this.setPropertyGetterDispatcher(block, VariableExpression.THIS_EXPRESSION, parameters);
        method.setCode(block);
    }
    
    private void setPropertyGetterDispatcher(final BlockStatement block, final Expression thiz, final Parameter[] parameters) {
        final List gStringStrings = new ArrayList();
        gStringStrings.add(new ConstantExpression(""));
        gStringStrings.add(new ConstantExpression(""));
        final List gStringValues = new ArrayList();
        gStringValues.add(new VariableExpression(parameters[0]));
        block.addStatement(new ReturnStatement(new AttributeExpression(thiz, new GStringExpression("$name", gStringStrings, gStringValues))));
    }
    
    private void setPropertySetDispatcher(final BlockStatement block, final Expression thiz, final Parameter[] parameters) {
        final List gStringStrings = new ArrayList();
        gStringStrings.add(new ConstantExpression(""));
        gStringStrings.add(new ConstantExpression(""));
        final List gStringValues = new ArrayList();
        gStringValues.add(new VariableExpression(parameters[0]));
        block.addStatement(new ExpressionStatement(new BinaryExpression(new AttributeExpression(thiz, new GStringExpression("$name", gStringStrings, gStringValues)), Token.newSymbol(100, -1, -1), new VariableExpression(parameters[1]))));
    }
    
    private void setMethodDispatcherCode(final BlockStatement block, final Expression thiz, final Parameter[] parameters) {
        final List gStringStrings = new ArrayList();
        gStringStrings.add(new ConstantExpression(""));
        gStringStrings.add(new ConstantExpression(""));
        final List gStringValues = new ArrayList();
        gStringValues.add(new VariableExpression(parameters[0]));
        block.addStatement(new ReturnStatement(new MethodCallExpression(thiz, new GStringExpression("$name", gStringStrings, gStringValues), new ArgumentListExpression(new SpreadExpression(new VariableExpression(parameters[1]))))));
    }
    
    private static void addFieldInit(final Parameter p, final FieldNode fn, final BlockStatement block) {
        final VariableExpression ve = new VariableExpression(p);
        final FieldExpression fe = new FieldExpression(fn);
        block.addStatement(new ExpressionStatement(new BinaryExpression(fe, Token.newSymbol(100, -1, -1), ve)));
    }
    
    private int getObjectDistance(ClassNode node) {
        int count = 1;
        while (node != null && node != ClassHelper.OBJECT_TYPE) {
            ++count;
            node = node.getSuperClass();
        }
        return count;
    }
}
