// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import java.util.List;
import groovy.transform.IndexedProperty;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.GroovyBugError;
import java.util.Arrays;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import groovyjarjarasm.asm.Opcodes;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class IndexedPropertyASTTransformation implements ASTTransformation, Opcodes
{
    private static final Class MY_CLASS;
    private static final ClassNode MY_TYPE;
    private static final String MY_TYPE_NAME;
    private static final ClassNode LIST_TYPE;
    private static final ClassNode OBJECT_TYPE;
    private static final Token ASSIGN;
    private static final Token INDEX;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new GroovyBugError("Internal error: expecting [AnnotationNode, AnnotatedNode] but got: " + Arrays.asList(nodes));
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (!IndexedPropertyASTTransformation.MY_TYPE.equals(node.getClassNode())) {
            return;
        }
        if (parent instanceof FieldNode) {
            final FieldNode fNode = (FieldNode)parent;
            final ClassNode cNode = fNode.getDeclaringClass();
            if (cNode.getProperty(fNode.getName()) == null) {
                this.addError("Error during " + IndexedPropertyASTTransformation.MY_TYPE_NAME + " processing. Field '" + fNode.getName() + "' doesn't appear to be a property; incorrect visibility?", fNode, source);
                return;
            }
            final ClassNode fType = fNode.getType();
            if (fType.isArray()) {
                this.addArraySetter(fNode);
                this.addArrayGetter(fNode);
            }
            else if (fType.isDerivedFrom(IndexedPropertyASTTransformation.LIST_TYPE)) {
                this.addListSetter(fNode);
                this.addListGetter(fNode);
            }
            else {
                this.addError("Error during " + IndexedPropertyASTTransformation.MY_TYPE_NAME + " processing. Non-Indexable property '" + fNode.getName() + "' found. Type must be array or list but found " + fType.getName(), fNode, source);
            }
        }
    }
    
    private void addListGetter(final FieldNode fNode) {
        this.addGetter(fNode, this.getComponentTypeForList(fNode.getType()));
    }
    
    private void addListSetter(final FieldNode fNode) {
        this.addSetter(fNode, this.getComponentTypeForList(fNode.getType()));
    }
    
    private void addArrayGetter(final FieldNode fNode) {
        this.addGetter(fNode, fNode.getType().getComponentType());
    }
    
    private void addArraySetter(final FieldNode fNode) {
        this.addSetter(fNode, fNode.getType().getComponentType());
    }
    
    private void addGetter(final FieldNode fNode, final ClassNode componentType) {
        final ClassNode cNode = fNode.getDeclaringClass();
        final BlockStatement body = new BlockStatement();
        final Parameter[] params = { new Parameter(ClassHelper.int_TYPE, "index") };
        body.addStatement(new ExpressionStatement(new BinaryExpression(new VariableExpression(fNode.getName()), IndexedPropertyASTTransformation.INDEX, new VariableExpression(params[0]))));
        cNode.addMethod(this.makeName(fNode, "get"), this.getModifiers(fNode), componentType, params, null, body);
    }
    
    private void addSetter(final FieldNode fNode, final ClassNode componentType) {
        final ClassNode cNode = fNode.getDeclaringClass();
        final BlockStatement body = new BlockStatement();
        final Parameter[] params = { new Parameter(ClassHelper.int_TYPE, "index"), new Parameter(componentType, "value") };
        body.addStatement(new ExpressionStatement(new BinaryExpression(new BinaryExpression(new VariableExpression(fNode.getName()), IndexedPropertyASTTransformation.INDEX, new VariableExpression(params[0])), IndexedPropertyASTTransformation.ASSIGN, new VariableExpression(params[1]))));
        cNode.addMethod(this.makeName(fNode, "set"), this.getModifiers(fNode), ClassHelper.VOID_TYPE, params, null, body);
    }
    
    private ClassNode getComponentTypeForList(final ClassNode fType) {
        if (fType.isUsingGenerics() && fType.getGenericsTypes().length == 1) {
            return fType.getGenericsTypes()[0].getType();
        }
        return IndexedPropertyASTTransformation.OBJECT_TYPE;
    }
    
    private int getModifiers(final FieldNode fNode) {
        int mods = 1;
        if (fNode.isStatic()) {
            mods |= 0x8;
        }
        return mods;
    }
    
    private String makeName(final FieldNode fNode, final String prefix) {
        return prefix + MetaClassHelper.capitalize(fNode.getName());
    }
    
    private void addError(final String msg, final ASTNode expr, final SourceUnit source) {
        final int line = expr.getLineNumber();
        final int col = expr.getColumnNumber();
        source.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException(msg + '\n', line, col), source));
    }
    
    static {
        MY_CLASS = IndexedProperty.class;
        MY_TYPE = new ClassNode(IndexedPropertyASTTransformation.MY_CLASS);
        MY_TYPE_NAME = "@" + IndexedPropertyASTTransformation.MY_TYPE.getNameWithoutPackage();
        LIST_TYPE = new ClassNode(List.class);
        OBJECT_TYPE = new ClassNode(Object.class);
        ASSIGN = Token.newSymbol("=", -1, -1);
        INDEX = Token.newSymbol("[", -1, -1);
    }
}
