// 
// Decompiled by Procyon v0.5.36
// 

package groovy.beans;

import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import java.beans.VetoableChangeListener;
import org.codehaus.groovy.ast.ClassHelper;
import java.beans.PropertyVetoException;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.control.ProcessingUnit;
import org.codehaus.groovy.control.messages.SimpleMessage;
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
import java.beans.VetoableChangeSupport;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.transform.GroovyASTTransformation;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class VetoableASTTransformation extends BindableASTTransformation
{
    protected static ClassNode constrainedClassNode;
    protected ClassNode vcsClassNode;
    
    public VetoableASTTransformation() {
        this.vcsClassNode = new ClassNode(VetoableChangeSupport.class);
    }
    
    public static boolean hasVetoableAnnotation(final AnnotatedNode node) {
        for (final AnnotationNode annotation : node.getAnnotations()) {
            if (VetoableASTTransformation.constrainedClassNode.equals(annotation.getClassNode())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (!(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException("Internal error: wrong types: $node.class / $parent.class");
        }
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (nodes[1] instanceof ClassNode) {
            this.addListenerToClass(source, node, (ClassNode)nodes[1]);
        }
        else {
            if ((((FieldNode)nodes[1]).getModifiers() & 0x10) != 0x0) {
                source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException("@groovy.beans.Vetoable cannot annotate a final property.", node.getLineNumber(), node.getColumnNumber()), source));
            }
            this.addListenerToProperty(source, node, (AnnotatedNode)nodes[1]);
        }
    }
    
    private void addListenerToProperty(final SourceUnit source, final AnnotationNode node, final AnnotatedNode parent) {
        final ClassNode declaringClass = parent.getDeclaringClass();
        final FieldNode field = (FieldNode)parent;
        final String fieldName = field.getName();
        for (final PropertyNode propertyNode : declaringClass.getProperties()) {
            final boolean bindable = BindableASTTransformation.hasBindableAnnotation(parent) || BindableASTTransformation.hasBindableAnnotation(parent.getDeclaringClass());
            if (propertyNode.getName().equals(fieldName)) {
                if (field.isStatic()) {
                    source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException("@groovy.beans.Vetoable cannot annotate a static property.", node.getLineNumber(), node.getColumnNumber()), source));
                }
                else {
                    this.createListenerSetter(source, node, bindable, declaringClass, propertyNode);
                }
                return;
            }
        }
        source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException("@groovy.beans.Vetoable must be on a property, not a field.  Try removing the private, protected, or public modifier.", node.getLineNumber(), node.getColumnNumber()), source));
    }
    
    private void addListenerToClass(final SourceUnit source, final AnnotationNode node, final ClassNode classNode) {
        final boolean bindable = BindableASTTransformation.hasBindableAnnotation(classNode);
        for (final PropertyNode propertyNode : classNode.getProperties()) {
            if (!hasVetoableAnnotation(propertyNode.getField()) && (propertyNode.getField().getModifiers() & 0x10) == 0x0 && !propertyNode.getField().isStatic()) {
                this.createListenerSetter(source, node, bindable || BindableASTTransformation.hasBindableAnnotation(propertyNode.getField()), classNode, propertyNode);
            }
        }
    }
    
    private void wrapSetterMethod(final ClassNode classNode, final boolean bindable, final String propertyName) {
        final String getterName = "get" + MetaClassHelper.capitalize(propertyName);
        final MethodNode setter = classNode.getSetterMethod("set" + MetaClassHelper.capitalize(propertyName));
        if (setter != null) {
            final Statement code = setter.getCode();
            final VariableExpression oldValue = new VariableExpression("$oldValue");
            final VariableExpression newValue = new VariableExpression("$newValue");
            final VariableExpression proposedValue = new VariableExpression(setter.getParameters()[0].getName());
            final BlockStatement block = new BlockStatement();
            block.addStatement(new ExpressionStatement(new DeclarationExpression(oldValue, Token.newSymbol(100, 0, 0), new MethodCallExpression(VariableExpression.THIS_EXPRESSION, getterName, ArgumentListExpression.EMPTY_ARGUMENTS))));
            block.addStatement(new ExpressionStatement(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "fireVetoableChange", new ArgumentListExpression(new Expression[] { new ConstantExpression(propertyName), oldValue, proposedValue }))));
            block.addStatement(code);
            if (bindable) {
                block.addStatement(new ExpressionStatement(new DeclarationExpression(newValue, Token.newSymbol(100, 0, 0), new MethodCallExpression(VariableExpression.THIS_EXPRESSION, getterName, ArgumentListExpression.EMPTY_ARGUMENTS))));
                block.addStatement(new ExpressionStatement(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "firePropertyChange", new ArgumentListExpression(new Expression[] { new ConstantExpression(propertyName), oldValue, newValue }))));
            }
            setter.setCode(block);
        }
    }
    
    private void createListenerSetter(final SourceUnit source, final AnnotationNode node, final boolean bindable, final ClassNode declaringClass, final PropertyNode propertyNode) {
        if (bindable && this.needsPropertyChangeSupport(declaringClass, source)) {
            this.addPropertyChangeSupport(declaringClass);
        }
        if (this.needsVetoableChangeSupport(declaringClass, source)) {
            this.addVetoableChangeSupport(declaringClass);
        }
        final String setterName = "set" + MetaClassHelper.capitalize(propertyNode.getName());
        if (declaringClass.getMethods(setterName).isEmpty()) {
            final Expression fieldExpression = new FieldExpression(propertyNode.getField());
            final BlockStatement setterBlock = new BlockStatement();
            setterBlock.addStatement(this.createConstrainedStatement(propertyNode, fieldExpression));
            if (bindable) {
                setterBlock.addStatement(this.createBindableStatement(propertyNode, fieldExpression));
            }
            else {
                setterBlock.addStatement(this.createSetStatement(fieldExpression));
            }
            this.createSetterMethod(declaringClass, propertyNode, setterName, setterBlock);
        }
        else {
            this.wrapSetterMethod(declaringClass, bindable, propertyNode.getName());
        }
    }
    
    protected Statement createConstrainedStatement(final PropertyNode propertyNode, final Expression fieldExpression) {
        return new ExpressionStatement(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "fireVetoableChange", new ArgumentListExpression(new Expression[] { new ConstantExpression(propertyNode.getName()), fieldExpression, new VariableExpression("value") })));
    }
    
    protected Statement createSetStatement(final Expression fieldExpression) {
        return new ExpressionStatement(new BinaryExpression(fieldExpression, Token.newSymbol(100, 0, 0), new VariableExpression("value")));
    }
    
    protected boolean needsVetoableChangeSupport(final ClassNode declaringClass, final SourceUnit sourceUnit) {
        boolean foundAdd = false;
        boolean foundRemove = false;
        boolean foundFire = false;
        for (ClassNode consideredClass = declaringClass; consideredClass != null; consideredClass = consideredClass.getSuperClass()) {
            for (final MethodNode method : consideredClass.getMethods()) {
                foundAdd = (foundAdd || (method.getName().equals("addVetoableChangeListener") && method.getParameters().length == 1));
                foundRemove = (foundRemove || (method.getName().equals("removeVetoableChangeListener") && method.getParameters().length == 1));
                foundFire = (foundFire || (method.getName().equals("fireVetoableChange") && method.getParameters().length == 3));
                if (foundAdd && foundRemove && foundFire) {
                    return false;
                }
            }
        }
        for (ClassNode consideredClass = declaringClass.getSuperClass(); consideredClass != null; consideredClass = consideredClass.getSuperClass()) {
            if (hasVetoableAnnotation(consideredClass)) {
                return false;
            }
            for (final FieldNode field : consideredClass.getFields()) {
                if (hasVetoableAnnotation(field)) {
                    return false;
                }
            }
        }
        if (foundAdd || foundRemove || foundFire) {
            sourceUnit.getErrorCollector().addErrorAndContinue(new SimpleMessage("@Vetoable cannot be processed on " + declaringClass.getName() + " because some but not all of addVetoableChangeListener, removeVetoableChange, and fireVetoableChange were declared in the current or super classes.", sourceUnit));
            return false;
        }
        return true;
    }
    
    @Override
    protected void createSetterMethod(final ClassNode declaringClass, final PropertyNode propertyNode, final String setterName, final Statement setterBlock) {
        final Parameter[] setterParameterTypes = { new Parameter(propertyNode.getType(), "value") };
        final ClassNode[] exceptions = { new ClassNode(PropertyVetoException.class) };
        final MethodNode setter = new MethodNode(setterName, propertyNode.getModifiers(), ClassHelper.VOID_TYPE, setterParameterTypes, exceptions, setterBlock);
        setter.setSynthetic(true);
        declaringClass.addMethod(setter);
    }
    
    protected void addVetoableChangeSupport(final ClassNode declaringClass) {
        final ClassNode vcsClassNode = ClassHelper.make(VetoableChangeSupport.class);
        final ClassNode vclClassNode = ClassHelper.make(VetoableChangeListener.class);
        final FieldNode vcsField = declaringClass.addField("this$vetoableChangeSupport", 4114, vcsClassNode, new ConstructorCallExpression(vcsClassNode, new ArgumentListExpression(new Expression[] { new VariableExpression("this") })));
        declaringClass.addMethod(new MethodNode("addVetoableChangeListener", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(vclClassNode, "listener") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(vcsField), "addVetoableChangeListener", new ArgumentListExpression(new Expression[] { new VariableExpression("listener") })))));
        declaringClass.addMethod(new MethodNode("addVetoableChangeListener", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(vclClassNode, "listener") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(vcsField), "addVetoableChangeListener", new ArgumentListExpression(new Expression[] { new VariableExpression("name"), new VariableExpression("listener") })))));
        declaringClass.addMethod(new MethodNode("removeVetoableChangeListener", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(vclClassNode, "listener") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(vcsField), "removeVetoableChangeListener", new ArgumentListExpression(new Expression[] { new VariableExpression("listener") })))));
        declaringClass.addMethod(new MethodNode("removeVetoableChangeListener", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(vclClassNode, "listener") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new FieldExpression(vcsField), "removeVetoableChangeListener", new ArgumentListExpression(new Expression[] { new VariableExpression("name"), new VariableExpression("listener") })))));
        declaringClass.addMethod(new MethodNode("fireVetoableChange", 4097, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name"), new Parameter(ClassHelper.OBJECT_TYPE, "oldValue"), new Parameter(ClassHelper.OBJECT_TYPE, "newValue") }, new ClassNode[] { new ClassNode(PropertyVetoException.class) }, new ExpressionStatement(new MethodCallExpression(new FieldExpression(vcsField), "fireVetoableChange", new ArgumentListExpression(new Expression[] { new VariableExpression("name"), new VariableExpression("oldValue"), new VariableExpression("newValue") })))));
        declaringClass.addMethod(new MethodNode("getVetoableChangeListeners", 4097, vclClassNode.makeArray(), Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, new ReturnStatement(new ExpressionStatement(new MethodCallExpression(new FieldExpression(vcsField), "getVetoableChangeListeners", ArgumentListExpression.EMPTY_ARGUMENTS)))));
        declaringClass.addMethod(new MethodNode("getVetoableChangeListeners", 4097, vclClassNode.makeArray(), new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "name") }, ClassNode.EMPTY_ARRAY, new ReturnStatement(new ExpressionStatement(new MethodCallExpression(new FieldExpression(vcsField), "getVetoableChangeListeners", new ArgumentListExpression(new Expression[] { new VariableExpression("name") }))))));
    }
    
    static {
        VetoableASTTransformation.constrainedClassNode = new ClassNode(Vetoable.class);
    }
}
