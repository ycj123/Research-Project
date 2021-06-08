// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.AnnotationConstantExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import java.util.List;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.MethodNode;
import java.lang.reflect.Field;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import java.lang.reflect.Modifier;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import java.util.Iterator;
import org.codehaus.groovy.vmplugin.VMPluginFactory;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.Map;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.ErrorCollector;
import org.codehaus.groovy.control.SourceUnit;

public class AnnotationVisitor
{
    private SourceUnit source;
    private ErrorCollector errorCollector;
    private AnnotationNode annotation;
    private ClassNode reportClass;
    
    public AnnotationVisitor(final SourceUnit source, final ErrorCollector errorCollector) {
        this.source = source;
        this.errorCollector = errorCollector;
    }
    
    public void setReportClass(final ClassNode cn) {
        this.reportClass = cn;
    }
    
    public AnnotationNode visit(final AnnotationNode node) {
        this.annotation = node;
        this.reportClass = node.getClassNode();
        if (!this.isValidAnnotationClass(node.getClassNode())) {
            this.addError("class " + node.getClassNode().getName() + " is not an annotation");
            return node;
        }
        if (!this.checkIfMandatoryAnnotationValuesPassed(node)) {
            return node;
        }
        if (!this.checkIfValidEnumConstsAreUsed(node)) {
            return node;
        }
        final Map<String, Expression> attributes = node.getMembers();
        for (final Map.Entry<String, Expression> entry : attributes.entrySet()) {
            final String attrName = entry.getKey();
            final Expression attrExpr = this.transformInlineConstants(entry.getValue());
            entry.setValue(attrExpr);
            final ClassNode attrType = this.getAttributeType(node, attrName);
            this.visitExpression(attrName, attrExpr, attrType);
        }
        VMPluginFactory.getPlugin().configureAnnotation(node);
        return this.annotation;
    }
    
    private boolean checkIfValidEnumConstsAreUsed(final AnnotationNode node) {
        boolean ok = true;
        final Map<String, Expression> attributes = node.getMembers();
        for (final Map.Entry<String, Expression> entry : attributes.entrySet()) {
            ok &= this.validateEnumConstant(entry.getValue());
        }
        return ok;
    }
    
    private boolean validateEnumConstant(final Expression exp) {
        if (exp instanceof PropertyExpression) {
            final PropertyExpression pe = (PropertyExpression)exp;
            final String name = pe.getPropertyAsString();
            if (pe.getObjectExpression() instanceof ClassExpression && name != null) {
                final ClassExpression ce = (ClassExpression)pe.getObjectExpression();
                final ClassNode type = ce.getType();
                if (type.isEnum()) {
                    boolean ok = false;
                    try {
                        if (type.isResolved()) {
                            ok = (Enum.valueOf((Class<Enum>)type.getTypeClass(), name) != null);
                        }
                        else {
                            final FieldNode enumField = type.getDeclaredField(name);
                            ok = (enumField != null && enumField.getType().equals(type));
                        }
                    }
                    catch (Exception ex) {}
                    if (!ok) {
                        this.addError("No enum const " + type.getName() + "." + name, pe);
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private Expression transformInlineConstants(final Expression exp) {
        if (exp instanceof PropertyExpression) {
            final PropertyExpression pe = (PropertyExpression)exp;
            if (pe.getObjectExpression() instanceof ClassExpression) {
                final ClassExpression ce = (ClassExpression)pe.getObjectExpression();
                final ClassNode type = ce.getType();
                if (type.isEnum() || !type.isResolved()) {
                    return exp;
                }
                try {
                    type.getFields();
                    final Field field = type.getTypeClass().getField(pe.getPropertyAsString());
                    if (field != null && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                        return new ConstantExpression(field.get(null));
                    }
                }
                catch (Exception ex) {}
            }
        }
        else if (exp instanceof ListExpression) {
            final ListExpression le = (ListExpression)exp;
            final ListExpression result = new ListExpression();
            for (final Expression e : le.getExpressions()) {
                result.addExpression(this.transformInlineConstants(e));
            }
            return result;
        }
        return exp;
    }
    
    private boolean checkIfMandatoryAnnotationValuesPassed(final AnnotationNode node) {
        boolean ok = true;
        final Map attributes = node.getMembers();
        final ClassNode classNode = node.getClassNode();
        for (final MethodNode mn : classNode.getMethods()) {
            final String methodName = mn.getName();
            if (mn.getCode() == null && !attributes.containsKey(methodName)) {
                this.addError("No explicit/default value found for annotation attribute '" + methodName + "' in annotation " + classNode, node);
                ok = false;
            }
        }
        return ok;
    }
    
    private ClassNode getAttributeType(final AnnotationNode node, final String attrName) {
        final ClassNode classNode = node.getClassNode();
        final List methods = classNode.getMethods(attrName);
        if (methods.size() == 0) {
            this.addError("'" + attrName + "'is not part of the annotation " + classNode, node);
            return ClassHelper.OBJECT_TYPE;
        }
        final MethodNode method = methods.get(0);
        return method.getReturnType();
    }
    
    private boolean isValidAnnotationClass(final ClassNode node) {
        return node.implementsInterface(ClassHelper.Annotation_TYPE);
    }
    
    protected void visitExpression(final String attrName, final Expression attrExp, final ClassNode attrType) {
        if (attrType.isArray()) {
            if (attrExp instanceof ListExpression) {
                final ListExpression le = (ListExpression)attrExp;
                this.visitListExpression(attrName, le, attrType.getComponentType());
            }
            else if (attrExp instanceof ClosureExpression) {
                this.addError("Annotation list attributes must use Groovy notation [el1, el2]", attrExp);
            }
            else {
                final ListExpression listExp = new ListExpression();
                listExp.addExpression(attrExp);
                if (this.annotation != null) {
                    this.annotation.setMember(attrName, listExp);
                }
                this.visitExpression(attrName, listExp, attrType);
            }
        }
        else if (ClassHelper.isPrimitiveType(attrType)) {
            this.visitConstantExpression(attrName, this.getConstantExpression(attrExp, attrType), ClassHelper.getWrapper(attrType));
        }
        else if (ClassHelper.STRING_TYPE.equals(attrType)) {
            this.visitConstantExpression(attrName, this.getConstantExpression(attrExp, attrType), ClassHelper.STRING_TYPE);
        }
        else if (ClassHelper.CLASS_Type.equals(attrType)) {
            if (!(attrExp instanceof ClassExpression)) {
                this.addError("Only classes can be used for attribute '" + attrName + "'", attrExp);
            }
        }
        else if (attrType.isDerivedFrom(ClassHelper.Enum_Type)) {
            if (attrExp instanceof PropertyExpression) {
                this.visitEnumExpression(attrName, (PropertyExpression)attrExp, attrType);
            }
            else {
                this.addError("Expected enum value for attribute " + attrName, attrExp);
            }
        }
        else if (this.isValidAnnotationClass(attrType)) {
            if (attrExp instanceof AnnotationConstantExpression) {
                this.visitAnnotationExpression(attrName, (AnnotationConstantExpression)attrExp, attrType);
            }
            else {
                this.addError("Expected annotation of type '" + attrType.getName() + "' for attribute " + attrName, attrExp);
            }
        }
        else {
            this.addError("Unexpected type " + attrType.getName(), attrExp);
        }
    }
    
    public void checkReturnType(final ClassNode attrType, final ASTNode node) {
        if (attrType.isArray()) {
            this.checkReturnType(attrType.getComponentType(), node);
        }
        else {
            if (ClassHelper.isPrimitiveType(attrType)) {
                return;
            }
            if (ClassHelper.STRING_TYPE.equals(attrType)) {
                return;
            }
            if (ClassHelper.CLASS_Type.equals(attrType)) {
                return;
            }
            if (attrType.isDerivedFrom(ClassHelper.Enum_Type)) {
                return;
            }
            if (this.isValidAnnotationClass(attrType)) {
                return;
            }
            this.addError("Unexpected return type " + attrType.getName(), node);
        }
    }
    
    private ConstantExpression getConstantExpression(final Expression exp, final ClassNode attrType) {
        if (exp instanceof ConstantExpression) {
            return (ConstantExpression)exp;
        }
        final String base = "expected '" + exp.getText() + "' to be an inline constant of type " + attrType.getName();
        if (exp instanceof PropertyExpression) {
            this.addError(base + " not a property expression", exp);
        }
        else if (exp instanceof VariableExpression && ((VariableExpression)exp).getAccessedVariable() instanceof FieldNode) {
            this.addError(base + " not a field expression", exp);
        }
        else {
            this.addError(base, exp);
        }
        return ConstantExpression.EMPTY_EXPRESSION;
    }
    
    protected void visitAnnotationExpression(final String attrName, final AnnotationConstantExpression expression, final ClassNode attrType) {
        final AnnotationNode annotationNode = (AnnotationNode)expression.getValue();
        final AnnotationVisitor visitor = new AnnotationVisitor(this.source, this.errorCollector);
        visitor.visit(annotationNode);
    }
    
    protected void visitListExpression(final String attrName, final ListExpression listExpr, final ClassNode elementType) {
        for (final Expression expression : listExpr.getExpressions()) {
            this.visitExpression(attrName, expression, elementType);
        }
    }
    
    protected void visitConstantExpression(final String attrName, final ConstantExpression constExpr, final ClassNode attrType) {
        if (!constExpr.getType().isDerivedFrom(attrType)) {
            this.addError("Attribute '" + attrName + "' should have type '" + attrType.getName() + "'; " + "but found type '" + constExpr.getType().getName() + "'", constExpr);
        }
    }
    
    protected void visitEnumExpression(final String attrName, final PropertyExpression propExpr, final ClassNode attrType) {
        if (!propExpr.getObjectExpression().getType().isDerivedFrom(attrType)) {
            this.addError("Attribute '" + attrName + "' should have type '" + attrType.getName() + "' (Enum), but found " + propExpr.getObjectExpression().getType().getName(), propExpr);
        }
    }
    
    protected void addError(final String msg) {
        this.addError(msg, this.annotation);
    }
    
    protected void addError(final String msg, final ASTNode expr) {
        this.errorCollector.addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException(msg + " in @" + this.reportClass.getName() + '\n', expr.getLineNumber(), expr.getColumnNumber()), this.source));
    }
    
    public void checkCircularReference(final ClassNode searchClass, final ClassNode attrType, final Expression startExp) {
        if (!this.isValidAnnotationClass(attrType)) {
            return;
        }
        if (!(startExp instanceof AnnotationConstantExpression)) {
            this.addError("Found '" + startExp.getText() + "' when expecting an Annotation Constant", startExp);
            return;
        }
        final AnnotationConstantExpression ace = (AnnotationConstantExpression)startExp;
        final AnnotationNode annotationNode = (AnnotationNode)ace.getValue();
        if (annotationNode.getClassNode().equals(searchClass)) {
            this.addError("Circular reference discovered in " + searchClass.getName(), startExp);
            return;
        }
        final ClassNode cn = annotationNode.getClassNode();
        for (final MethodNode method : cn.getMethods()) {
            if (method.getReturnType().equals(searchClass)) {
                this.addError("Circular reference discovered in " + cn.getName(), startExp);
            }
            final ReturnStatement code = (ReturnStatement)method.getCode();
            if (code == null) {
                continue;
            }
            this.checkCircularReference(searchClass, method.getReturnType(), code.getExpression());
        }
    }
}
