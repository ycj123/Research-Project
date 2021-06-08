// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.FieldNode;
import java.util.Iterator;
import java.util.List;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.MethodNode;
import java.lang.reflect.Modifier;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public class ClassCompletionVerifier extends ClassCodeVisitorSupport
{
    private ClassNode currentClass;
    private SourceUnit source;
    private boolean inConstructor;
    private boolean inStaticConstructor;
    
    public ClassCompletionVerifier(final SourceUnit source) {
        this.inConstructor = false;
        this.inStaticConstructor = false;
        this.source = source;
    }
    
    public ClassNode getClassNode() {
        return this.currentClass;
    }
    
    @Override
    public void visitClass(final ClassNode node) {
        final ClassNode oldClass = this.currentClass;
        this.checkImplementsAndExtends(this.currentClass = node);
        if (this.source != null && !this.source.getErrorCollector().hasErrors()) {
            this.checkClassForIncorrectModifiers(node);
            this.checkClassForOverwritingFinal(node);
            this.checkMethodsForIncorrectModifiers(node);
            this.checkMethodsForOverridingFinal(node);
            this.checkNoAbstractMethodsNonabstractClass(node);
        }
        super.visitClass(node);
        this.currentClass = oldClass;
    }
    
    private void checkNoAbstractMethodsNonabstractClass(final ClassNode node) {
        if (Modifier.isAbstract(node.getModifiers())) {
            return;
        }
        final List<MethodNode> abstractMethods = node.getAbstractMethods();
        if (abstractMethods == null) {
            return;
        }
        for (final MethodNode method : abstractMethods) {
            this.addError("Can't have an abstract method in a non-abstract class. The " + this.getDescription(node) + " must be declared abstract or" + " the " + this.getDescription(method) + " must be implemented.", node);
        }
    }
    
    private void checkClassForIncorrectModifiers(final ClassNode node) {
        this.checkClassForAbstractAndFinal(node);
        this.checkClassForOtherModifiers(node);
    }
    
    private void checkClassForAbstractAndFinal(final ClassNode node) {
        if (!Modifier.isAbstract(node.getModifiers())) {
            return;
        }
        if (!Modifier.isFinal(node.getModifiers())) {
            return;
        }
        if (node.isInterface()) {
            this.addError("The " + this.getDescription(node) + " must not be final. It is by definition abstract.", node);
        }
        else {
            this.addError("The " + this.getDescription(node) + " must not be both final and abstract.", node);
        }
    }
    
    private void checkClassForOtherModifiers(final ClassNode node) {
        this.checkClassForModifier(node, Modifier.isTransient(node.getModifiers()), "transient");
        this.checkClassForModifier(node, Modifier.isVolatile(node.getModifiers()), "volatile");
        this.checkClassForModifier(node, Modifier.isNative(node.getModifiers()), "native");
    }
    
    private void checkMethodForModifier(final MethodNode node, final boolean condition, final String modifierName) {
        if (!condition) {
            return;
        }
        this.addError("The " + this.getDescription(node) + " has an incorrect modifier " + modifierName + ".", node);
    }
    
    private void checkClassForModifier(final ClassNode node, final boolean condition, final String modifierName) {
        if (!condition) {
            return;
        }
        this.addError("The " + this.getDescription(node) + " has an incorrect modifier " + modifierName + ".", node);
    }
    
    private String getDescription(final ClassNode node) {
        return (node.isInterface() ? "interface" : "class") + " '" + node.getName() + "'";
    }
    
    private String getDescription(final MethodNode node) {
        return "method '" + node.getTypeDescriptor() + "'";
    }
    
    private String getDescription(final FieldNode node) {
        return "field '" + node.getName() + "'";
    }
    
    private void checkAbstractDeclaration(final MethodNode methodNode) {
        if (!Modifier.isAbstract(methodNode.getModifiers())) {
            return;
        }
        if (Modifier.isAbstract(this.currentClass.getModifiers())) {
            return;
        }
        this.addError("Can't have an abstract method in a non-abstract class. The " + this.getDescription(this.currentClass) + " must be declared abstract or the method '" + methodNode.getTypeDescriptor() + "' must not be abstract.", methodNode);
    }
    
    private void checkClassForOverwritingFinal(final ClassNode cn) {
        final ClassNode superCN = cn.getSuperClass();
        if (superCN == null) {
            return;
        }
        if (!Modifier.isFinal(superCN.getModifiers())) {
            return;
        }
        final StringBuffer msg = new StringBuffer();
        msg.append("You are not allowed to overwrite the final ");
        msg.append(this.getDescription(superCN));
        msg.append(".");
        this.addError(msg.toString(), cn);
    }
    
    private void checkImplementsAndExtends(final ClassNode node) {
        ClassNode cn = node.getSuperClass();
        if (cn.isInterface() && !node.isInterface()) {
            this.addError("You are not allowed to extend the " + this.getDescription(cn) + ", use implements instead.", node);
        }
        final ClassNode[] arr$ = node.getInterfaces();
        for (int len$ = arr$.length, i$ = 0; i$ < len$; ++i$) {
            final ClassNode anInterface = cn = arr$[i$];
            if (!cn.isInterface()) {
                this.addError("You are not allowed to implement the " + this.getDescription(cn) + ", use extends instead.", node);
            }
        }
    }
    
    private void checkMethodsForIncorrectModifiers(final ClassNode cn) {
        if (!cn.isInterface()) {
            return;
        }
        for (final MethodNode method : cn.getMethods()) {
            if (Modifier.isFinal(method.getModifiers())) {
                this.addError("The " + this.getDescription(method) + " from " + this.getDescription(cn) + " must not be final. It is by definition abstract.", method);
            }
            if (Modifier.isStatic(method.getModifiers()) && !this.isConstructor(method)) {
                this.addError("The " + this.getDescription(method) + " from " + this.getDescription(cn) + " must not be static. Only fields may be static in an interface.", method);
            }
        }
    }
    
    private boolean isConstructor(final MethodNode method) {
        return method.getName().equals("<clinit>");
    }
    
    private void checkMethodsForOverridingFinal(final ClassNode cn) {
        for (final MethodNode method : cn.getMethods()) {
            final Parameter[] params = method.getParameters();
            for (final MethodNode superMethod : cn.getSuperClass().getMethods(method.getName())) {
                final Parameter[] superParams = superMethod.getParameters();
                if (!this.hasEqualParameterTypes(params, superParams)) {
                    continue;
                }
                if (!Modifier.isFinal(superMethod.getModifiers())) {
                    break;
                }
                this.addInvalidUseOfFinalError(method, params, superMethod.getDeclaringClass());
            }
        }
    }
    
    private void addInvalidUseOfFinalError(final MethodNode method, final Parameter[] parameters, final ClassNode superCN) {
        final StringBuffer msg = new StringBuffer();
        msg.append("You are not allowed to override the final method ").append(method.getName());
        msg.append("(");
        boolean needsComma = false;
        for (final Parameter parameter : parameters) {
            if (needsComma) {
                msg.append(",");
            }
            else {
                needsComma = true;
            }
            msg.append(parameter.getType());
        }
        msg.append(") from ").append(this.getDescription(superCN));
        msg.append(".");
        this.addError(msg.toString(), method);
    }
    
    private boolean hasEqualParameterTypes(final Parameter[] first, final Parameter[] second) {
        if (first.length != second.length) {
            return false;
        }
        for (int i = 0; i < first.length; ++i) {
            final String ft = first[i].getType().getName();
            final String st = second[i].getType().getName();
            if (!ft.equals(st)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    @Override
    public void visitMethod(final MethodNode node) {
        this.inConstructor = false;
        this.inStaticConstructor = node.isStaticConstructor();
        this.checkAbstractDeclaration(node);
        this.checkRepetitiveMethod(node);
        this.checkOverloadingPrivateAndPublic(node);
        this.checkMethodModifiers(node);
        super.visitMethod(node);
    }
    
    private void checkMethodModifiers(final MethodNode node) {
        if ((this.currentClass.getModifiers() & 0x200) != 0x0) {
            this.checkMethodForModifier(node, Modifier.isStrict(node.getModifiers()), "strictfp");
            this.checkMethodForModifier(node, Modifier.isSynchronized(node.getModifiers()), "synchronized");
            this.checkMethodForModifier(node, Modifier.isNative(node.getModifiers()), "native");
        }
    }
    
    private void checkOverloadingPrivateAndPublic(final MethodNode node) {
        if (this.isConstructor(node)) {
            return;
        }
        boolean hasPrivate = false;
        boolean hasPublic = false;
        for (final MethodNode method : this.currentClass.getMethods(node.getName())) {
            if (method == node) {
                continue;
            }
            if (!method.getDeclaringClass().equals(node.getDeclaringClass())) {
                continue;
            }
            final int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                hasPublic = true;
            }
            else {
                hasPrivate = true;
            }
        }
        if (hasPrivate && hasPublic) {
            this.addError("Mixing private and public/protected methods of the same name causes multimethods to be disabled and is forbidden to avoid surprising behaviour. Renaming the private methods will solve the problem.", node);
        }
    }
    
    private void checkRepetitiveMethod(final MethodNode node) {
        if (this.isConstructor(node)) {
            return;
        }
        for (final MethodNode method : this.currentClass.getMethods(node.getName())) {
            if (method == node) {
                continue;
            }
            if (!method.getDeclaringClass().equals(node.getDeclaringClass())) {
                continue;
            }
            final Parameter[] p1 = node.getParameters();
            final Parameter[] p2 = method.getParameters();
            if (p1.length != p2.length) {
                continue;
            }
            this.addErrorIfParamsAndReturnTypeEqual(p2, p1, node, method);
        }
    }
    
    private void addErrorIfParamsAndReturnTypeEqual(final Parameter[] p2, final Parameter[] p1, final MethodNode node, final MethodNode element) {
        boolean isEqual = true;
        for (int i = 0; i < p2.length; ++i) {
            isEqual &= p1[i].getType().equals(p2[i].getType());
        }
        isEqual &= node.getReturnType().equals(element.getReturnType());
        if (isEqual) {
            this.addError("Repetitive method name/signature for " + this.getDescription(node) + " in " + this.getDescription(this.currentClass) + ".", node);
        }
    }
    
    @Override
    public void visitField(final FieldNode node) {
        if (this.currentClass.getDeclaredField(node.getName()) != node) {
            this.addError("The " + this.getDescription(node) + " is declared multiple times.", node);
        }
        this.checkInterfaceFieldModifiers(node);
        super.visitField(node);
    }
    
    @Override
    public void visitProperty(final PropertyNode node) {
        this.checkDuplicateProperties(node);
        super.visitProperty(node);
    }
    
    private void checkDuplicateProperties(final PropertyNode node) {
        final ClassNode cn = node.getDeclaringClass();
        final String name = node.getName();
        final String getterName = "get" + MetaClassHelper.capitalize(name);
        if (Character.isUpperCase(name.charAt(0))) {
            for (final PropertyNode propNode : cn.getProperties()) {
                final String otherName = propNode.getField().getName();
                final String otherGetterName = "get" + MetaClassHelper.capitalize(otherName);
                if (node != propNode && getterName.equals(otherGetterName)) {
                    final String msg = "The field " + name + " and " + otherName + " on the class " + cn.getName() + " will result in duplicate JavaBean properties, which is not allowed";
                    this.addError(msg, node);
                }
            }
        }
    }
    
    private void checkInterfaceFieldModifiers(final FieldNode node) {
        if (!this.currentClass.isInterface()) {
            return;
        }
        if ((node.getModifiers() & 0x19) == 0x0) {
            this.addError("The " + this.getDescription(node) + " is not 'public final static' but is defined in the " + this.getDescription(this.currentClass) + ".", node);
        }
    }
    
    @Override
    public void visitBinaryExpression(final BinaryExpression expression) {
        if (expression.getOperation().getType() == 30 && expression.getRightExpression() instanceof MapEntryExpression) {
            this.addError("You tried to use a map entry for an index operation, this is not allowed. Maybe something should be set in parentheses or a comma is missing?", expression.getRightExpression());
        }
        super.visitBinaryExpression(expression);
        switch (expression.getOperation().getType()) {
            case 100:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 285:
            case 286:
            case 287:
            case 350:
            case 351:
            case 352: {
                this.checkFinalFieldAccess(expression.getLeftExpression());
                break;
            }
        }
    }
    
    private void checkFinalFieldAccess(final Expression expression) {
        if (!(expression instanceof VariableExpression) && !(expression instanceof PropertyExpression)) {
            return;
        }
        Variable v = null;
        if (expression instanceof VariableExpression) {
            final VariableExpression ve = (VariableExpression)expression;
            v = ve.getAccessedVariable();
        }
        else {
            final PropertyExpression propExp = (PropertyExpression)expression;
            final Expression objectExpression = propExp.getObjectExpression();
            if (objectExpression instanceof VariableExpression) {
                final VariableExpression varExp = (VariableExpression)objectExpression;
                if (varExp.isThisExpression()) {
                    v = this.currentClass.getDeclaredField(propExp.getPropertyAsString());
                }
            }
        }
        if (v instanceof FieldNode) {
            final FieldNode fn = (FieldNode)v;
            final int modifiers = fn.getModifiers();
            final boolean isFinal = (modifiers & 0x10) != 0x0;
            final boolean isStatic = (modifiers & 0x8) != 0x0;
            final boolean error = isFinal && ((isStatic && !this.inStaticConstructor) || (!isStatic && !this.inConstructor));
            if (error) {
                this.addError("cannot modify" + (isStatic ? " static" : "") + " final field '" + fn.getName() + "' outside of " + (isStatic ? "static initialization block." : "constructor."), expression);
            }
        }
    }
    
    @Override
    public void visitConstructor(final ConstructorNode node) {
        this.inConstructor = true;
        this.inStaticConstructor = node.isStaticConstructor();
        super.visitConstructor(node);
    }
    
    @Override
    public void visitCatchStatement(final CatchStatement cs) {
        if (!cs.getExceptionType().isDerivedFrom(ClassHelper.make(Throwable.class))) {
            this.addError("Catch statement parameter type is not a subclass of Throwable.", cs);
        }
        super.visitCatchStatement(cs);
    }
    
    @Override
    public void visitMethodCallExpression(final MethodCallExpression mce) {
        super.visitMethodCallExpression(mce);
        final Expression aexp = mce.getArguments();
        if (aexp instanceof TupleExpression) {
            final TupleExpression arguments = (TupleExpression)aexp;
            for (final Expression e : arguments.getExpressions()) {
                this.checkForInvalidDeclaration(e);
            }
        }
        else {
            this.checkForInvalidDeclaration(aexp);
        }
    }
    
    private void checkForInvalidDeclaration(final Expression exp) {
        if (!(exp instanceof DeclarationExpression)) {
            return;
        }
        this.addError("Invalid use of declaration inside method call.", exp);
    }
    
    @Override
    public void visitConstantExpression(final ConstantExpression expression) {
        super.visitConstantExpression(expression);
        this.checkStringExceedingMaximumLength(expression);
    }
    
    @Override
    public void visitGStringExpression(final GStringExpression expression) {
        super.visitGStringExpression(expression);
        for (final ConstantExpression ce : expression.getStrings()) {
            this.checkStringExceedingMaximumLength(ce);
        }
    }
    
    private void checkStringExceedingMaximumLength(final ConstantExpression expression) {
        final Object value = expression.getValue();
        if (value instanceof String) {
            final String s = (String)value;
            if (s.length() > 65535) {
                this.addError("String too long. The given string is " + s.length() + " Unicode code units long, but only a maximum of 65535 is allowed.", expression);
            }
        }
    }
}
