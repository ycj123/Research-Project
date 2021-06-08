// 
// Decompiled by Procyon v0.5.36
// 

package groovy.beans;

import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import java.beans.PropertyChangeListener;
import org.codehaus.groovy.control.ProcessingUnit;
import org.codehaus.groovy.control.messages.SimpleMessage;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import java.util.Iterator;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import java.beans.PropertyChangeSupport;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import groovyjarjarasm.asm.Opcodes;
import org.codehaus.groovy.transform.ASTTransformation;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class BindableASTTransformation implements ASTTransformation, Opcodes
{
    protected static ClassNode boundClassNode;
    protected ClassNode pcsClassNode;
    
    public BindableASTTransformation() {
        this.pcsClassNode = new ClassNode(PropertyChangeSupport.class);
    }
    
    public static boolean hasBindableAnnotation(final AnnotatedNode node) {
        for (final AnnotationNode annotation : node.getAnnotations()) {
            if (BindableASTTransformation.boundClassNode.equals(annotation.getClassNode())) {
                return true;
            }
        }
        return false;
    }
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (!(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException("Internal error: wrong types: $node.class / $parent.class");
        }
        final AnnotationNode node = (AnnotationNode)nodes[0];
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        if (VetoableASTTransformation.hasVetoableAnnotation(parent)) {
            return;
        }
        final ClassNode declaringClass = parent.getDeclaringClass();
        if (parent instanceof FieldNode) {
            if ((((FieldNode)parent).getModifiers() & 0x10) != 0x0) {
                source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException("@groovy.beans.Bindable cannot annotate a final property.", node.getLineNumber(), node.getColumnNumber()), source));
            }
            if (VetoableASTTransformation.hasVetoableAnnotation(parent.getDeclaringClass())) {
                return;
            }
            this.addListenerToProperty(source, node, declaringClass, (FieldNode)parent);
        }
        else if (parent instanceof ClassNode) {
            this.addListenerToClass(source, node, (ClassNode)parent);
        }
    }
    
    private void addListenerToProperty(final SourceUnit source, final AnnotationNode node, final ClassNode declaringClass, final FieldNode field) {
        final String fieldName = field.getName();
        for (final PropertyNode propertyNode : declaringClass.getProperties()) {
            if (propertyNode.getName().equals(fieldName)) {
                if (field.isStatic()) {
                    source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException("@groovy.beans.Bindable cannot annotate a static property.", node.getLineNumber(), node.getColumnNumber()), source));
                }
                else {
                    if (this.needsPropertyChangeSupport(declaringClass, source)) {
                        this.addPropertyChangeSupport(declaringClass);
                    }
                    this.createListenerSetter(source, node, declaringClass, propertyNode);
                }
                return;
            }
        }
        source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException("@groovy.beans.Bindable must be on a property, not a field.  Try removing the private, protected, or public modifier.", node.getLineNumber(), node.getColumnNumber()), source));
    }
    
    private void addListenerToClass(final SourceUnit source, final AnnotationNode node, final ClassNode classNode) {
        if (this.needsPropertyChangeSupport(classNode, source)) {
            this.addPropertyChangeSupport(classNode);
        }
        for (final PropertyNode propertyNode : classNode.getProperties()) {
            final FieldNode field = propertyNode.getField();
            if (!hasBindableAnnotation(field) && (field.getModifiers() & 0x10) == 0x0 && !field.isStatic()) {
                if (VetoableASTTransformation.hasVetoableAnnotation(field)) {
                    continue;
                }
                this.createListenerSetter(source, node, classNode, propertyNode);
            }
        }
    }
    
    private void wrapSetterMethod(final ClassNode classNode, final String propertyName) {
        final String getterName = "get" + MetaClassHelper.capitalize(propertyName);
        final MethodNode setter = classNode.getSetterMethod("set" + MetaClassHelper.capitalize(propertyName));
        if (setter != null) {
            final Statement code = setter.getCode();
            final VariableExpression oldValue = new VariableExpression("$oldValue");
            final VariableExpression newValue = new VariableExpression("$newValue");
            final BlockStatement block = new BlockStatement();
            block.addStatement(new ExpressionStatement(new DeclarationExpression(oldValue, Token.newSymbol(100, 0, 0), new MethodCallExpression(VariableExpression.THIS_EXPRESSION, getterName, ArgumentListExpression.EMPTY_ARGUMENTS))));
            block.addStatement(code);
            block.addStatement(new ExpressionStatement(new DeclarationExpression(newValue, Token.newSymbol(100, 0, 0), new MethodCallExpression(VariableExpression.THIS_EXPRESSION, getterName, ArgumentListExpression.EMPTY_ARGUMENTS))));
            block.addStatement(new ExpressionStatement(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "firePropertyChange", new ArgumentListExpression(new Expression[] { new ConstantExpression(propertyName), oldValue, newValue }))));
            setter.setCode(block);
        }
    }
    
    private void createListenerSetter(final SourceUnit source, final AnnotationNode node, final ClassNode classNode, final PropertyNode propertyNode) {
        final String setterName = "set" + MetaClassHelper.capitalize(propertyNode.getName());
        if (classNode.getMethods(setterName).isEmpty()) {
            final Expression fieldExpression = new FieldExpression(propertyNode.getField());
            final Statement setterBlock = this.createBindableStatement(propertyNode, fieldExpression);
            this.createSetterMethod(classNode, propertyNode, setterName, setterBlock);
        }
        else {
            this.wrapSetterMethod(classNode, propertyNode.getName());
        }
    }
    
    protected Statement createBindableStatement(final PropertyNode propertyNode, final Expression fieldExpression) {
        return new ExpressionStatement(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "firePropertyChange", new ArgumentListExpression(new Expression[] { new ConstantExpression(propertyNode.getName()), fieldExpression, new BinaryExpression(fieldExpression, Token.newSymbol(100, 0, 0), new VariableExpression("value")) })));
    }
    
    protected void createSetterMethod(final ClassNode declaringClass, final PropertyNode propertyNode, final String setterName, final Statement setterBlock) {
        final Parameter[] setterParameterTypes = { new Parameter(propertyNode.getType(), "value") };
        final MethodNode setter = new MethodNode(setterName, propertyNode.getModifiers(), ClassHelper.VOID_TYPE, setterParameterTypes, ClassNode.EMPTY_ARRAY, setterBlock);
        setter.setSynthetic(true);
        declaringClass.addMethod(setter);
    }
    
    protected boolean needsPropertyChangeSupport(final ClassNode declaringClass, final SourceUnit sourceUnit) {
        boolean foundAdd = false;
        boolean foundRemove = false;
        boolean foundFire = false;
        for (ClassNode consideredClass = declaringClass; consideredClass != null; consideredClass = consideredClass.getSuperClass()) {
            for (final MethodNode method : consideredClass.getMethods()) {
                foundAdd = (foundAdd || (method.getName().equals("addPropertyChangeListener") && method.getParameters().length == 1));
                foundRemove = (foundRemove || (method.getName().equals("removePropertyChangeListener") && method.getParameters().length == 1));
                foundFire = (foundFire || (method.getName().equals("firePropertyChange") && method.getParameters().length == 3));
                if (foundAdd && foundRemove && foundFire) {
                    return false;
                }
            }
        }
        for (ClassNode consideredClass = declaringClass.getSuperClass(); consideredClass != null; consideredClass = consideredClass.getSuperClass()) {
            if (hasBindableAnnotation(consideredClass)) {
                return false;
            }
            for (final FieldNode field : consideredClass.getFields()) {
                if (hasBindableAnnotation(field)) {
                    return false;
                }
            }
        }
        if (foundAdd || foundRemove || foundFire) {
            sourceUnit.getErrorCollector().addErrorAndContinue(new SimpleMessage("@Bindable cannot be processed on " + declaringClass.getName() + " because some but not all of addPropertyChangeListener, removePropertyChange, and firePropertyChange were declared in the current or super classes.", sourceUnit));
            return false;
        }
        return true;
    }
    
    protected void addPropertyChangeSupport(final ClassNode declaringClass) {
        final ClassNode pcsClassNode = ClassHelper.make(PropertyChangeSupport.class);
        final ClassNode pclClassNode = ClassHelper.make(PropertyChangeListener.class);
        final FieldNode pcsField = declaringClass.addField("this$propertyChangeSupport", 4114, pcsClassNode, new ConstructorCallExpression(pcsClassNode, new ArgumentListExpression(new Expression[] { new VariableExpression("this") })));
        declaringClass.addMethod(new MethodNode("addPropertyChangeListener", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(pclClassNode, "listener") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(pcsField), "addPropertyChangeListener", new ArgumentListExpression(new Expression[] { new VariableExpression("listener") })))));
        declaringClass.addMethod(new MethodNode("addPropertyChangeListener", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(pclClassNode, "listener") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(pcsField), "addPropertyChangeListener", new ArgumentListExpression(new Expression[] { new VariableExpression("name"), new VariableExpression("listener") })))));
        declaringClass.addMethod(new MethodNode("removePropertyChangeListener", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(pclClassNode, "listener") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(pcsField), "removePropertyChangeListener", new ArgumentListExpression(new Expression[] { new VariableExpression("listener") })))));
        declaringClass.addMethod(new MethodNode("removePropertyChangeListener", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(pclClassNode, "listener") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(pcsField), "removePropertyChangeListener", new ArgumentListExpression(new Expression[] { new VariableExpression("name"), new VariableExpression("listener") })))));
        declaringClass.addMethod(new MethodNode("firePropertyChange", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(ClassHelper.OBJECT_TYPE, "oldValue"), new Parameter(ClassHelper.OBJECT_TYPE, "newValue") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(pcsField), "firePropertyChange", new ArgumentListExpression(new Expression[] { new VariableExpression("name"), new VariableExpression("oldValue"), new VariableExpression("newValue") })))));
        declaringClass.addMethod(new MethodNode("getPropertyChangeListeners", 4097, pclClassNode.makeArray(), Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, new ReturnStatement(new ExpressionStatement(new MethodCallExpression(new FieldExpression(pcsField), "getPropertyChangeListeners", ArgumentListExpression.EMPTY_ARGUMENTS)))));
        declaringClass.addMethod(new MethodNode("getPropertyChangeListeners", 4097, pclClassNode.makeArray(), new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name") }, ClassNode.EMPTY_ARRAY, new ReturnStatement(new ExpressionStatement(new MethodCallExpression(new FieldExpression(pcsField), "getPropertyChangeListeners", new ArgumentListExpression(new Expression[] { new VariableExpression("name") }))))));
    }
    
    static {
        BindableASTTransformation.boundClassNode = new ClassNode(Bindable.class);
    }
}
