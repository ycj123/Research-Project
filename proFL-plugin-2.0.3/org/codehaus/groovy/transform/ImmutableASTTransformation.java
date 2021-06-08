// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import groovy.lang.ReadOnlyPropertyException;
import org.codehaus.groovy.util.HashCodeHelper;
import java.util.Date;
import java.util.HashMap;
import groovy.lang.Immutable;
import groovy.lang.MetaClass;
import groovy.lang.MissingPropertyException;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.Map;
import java.lang.annotation.Annotation;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.Collection;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.MethodNode;
import java.util.Iterator;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.PropertyNode;
import java.util.ArrayList;
import java.util.Arrays;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.ClassNode;
import java.util.List;
import org.codehaus.groovy.control.CompilePhase;
import groovyjarjarasm.asm.Opcodes;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class ImmutableASTTransformation implements ASTTransformation, Opcodes
{
    private static List<String> immutableList;
    private static final Class MY_CLASS;
    private static final ClassNode MY_TYPE;
    private static final String MY_TYPE_NAME;
    private static final ClassNode OBJECT_TYPE;
    private static final ClassNode HASHMAP_TYPE;
    private static final ClassNode MAP_TYPE;
    private static final ClassNode DATE_TYPE;
    private static final ClassNode CLONEABLE_TYPE;
    private static final ClassNode COLLECTION_TYPE;
    private static final ClassNode HASHUTIL_TYPE;
    private static final ClassNode STRINGBUFFER_TYPE;
    private static final ClassNode READONLYEXCEPTION_TYPE;
    private static final ClassNode DGM_TYPE;
    private static final ClassNode INVOKER_TYPE;
    private static final ClassNode SELF_TYPE;
    private static final Token COMPARE_EQUAL;
    private static final Token COMPARE_NOT_EQUAL;
    private static final Token ASSIGN;
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        if (nodes.length != 2 || !(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
            throw new RuntimeException("Internal error: expecting [AnnotationNode, AnnotatedNode] but got: " + Arrays.asList(nodes));
        }
        final AnnotatedNode parent = (AnnotatedNode)nodes[1];
        final AnnotationNode node = (AnnotationNode)nodes[0];
        if (!ImmutableASTTransformation.MY_TYPE.equals(node.getClassNode())) {
            return;
        }
        final List<PropertyNode> newNodes = new ArrayList<PropertyNode>();
        if (parent instanceof ClassNode) {
            final ClassNode cNode = (ClassNode)parent;
            final String cName = cNode.getName();
            if (cNode.isInterface()) {
                throw new RuntimeException("Error processing interface '" + cName + "'. " + ImmutableASTTransformation.MY_TYPE_NAME + " not allowed for interfaces.");
            }
            if ((cNode.getModifiers() & 0x10) == 0x0) {
                cNode.setModifiers(cNode.getModifiers() | 0x10);
            }
            final List<PropertyNode> pList = this.getInstanceProperties(cNode);
            for (final PropertyNode pNode : pList) {
                this.adjustPropertyForImmutability(pNode, newNodes);
            }
            for (final PropertyNode pNode : newNodes) {
                cNode.getProperties().remove(pNode);
                this.addProperty(cNode, pNode);
            }
            final List<FieldNode> fList = cNode.getFields();
            for (final FieldNode fNode : fList) {
                this.ensureNotPublic(cName, fNode);
            }
            this.createConstructor(cNode);
            this.createHashCode(cNode);
            this.createEquals(cNode);
            this.createToString(cNode);
        }
    }
    
    private boolean hasDeclaredMethod(final ClassNode cNode, final String name, final int argsCount) {
        final List<MethodNode> ms = cNode.getDeclaredMethods(name);
        for (final MethodNode m : ms) {
            final Parameter[] paras = m.getParameters();
            if (paras != null && paras.length == argsCount) {
                return true;
            }
        }
        return false;
    }
    
    private void ensureNotPublic(final String cNode, final FieldNode fNode) {
        final String fName = fNode.getName();
        if (fNode.isPublic() && !fName.contains("$")) {
            throw new RuntimeException("Public field '" + fName + "' not allowed for " + ImmutableASTTransformation.MY_TYPE_NAME + " class '" + cNode + "'.");
        }
    }
    
    private void createHashCode(final ClassNode cNode) {
        final boolean hasExistingHashCode = this.hasDeclaredMethod(cNode, "hashCode", 0);
        if (hasExistingHashCode && this.hasDeclaredMethod(cNode, "_hashCode", 0)) {
            return;
        }
        final FieldNode hashField = cNode.addField("$hash$code", 4098, ClassHelper.int_TYPE, null);
        final BlockStatement body = new BlockStatement();
        final Expression hash = new VariableExpression(hashField);
        final List<PropertyNode> list = this.getInstanceProperties(cNode);
        body.addStatement(new IfStatement(this.isZeroExpr(hash), this.calculateHashStatements(hash, list), new EmptyStatement()));
        body.addStatement(new ReturnStatement(hash));
        cNode.addMethod(new MethodNode(hasExistingHashCode ? "_hashCode" : "hashCode", hasExistingHashCode ? 2 : 1, ClassHelper.int_TYPE, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body));
    }
    
    private void createToString(final ClassNode cNode) {
        final boolean hasExistingToString = this.hasDeclaredMethod(cNode, "toString", 0);
        if (hasExistingToString && this.hasDeclaredMethod(cNode, "_toString", 0)) {
            return;
        }
        final BlockStatement body = new BlockStatement();
        final List<PropertyNode> list = this.getInstanceProperties(cNode);
        final Expression result = new VariableExpression("_result");
        final Expression init = new ConstructorCallExpression(ImmutableASTTransformation.STRINGBUFFER_TYPE, MethodCallExpression.NO_ARGUMENTS);
        body.addStatement(new ExpressionStatement(new DeclarationExpression(result, ImmutableASTTransformation.ASSIGN, init)));
        body.addStatement(this.append(result, new ConstantExpression(cNode.getName())));
        body.addStatement(this.append(result, new ConstantExpression("(")));
        boolean first = true;
        for (final PropertyNode pNode : list) {
            if (first) {
                first = false;
            }
            else {
                body.addStatement(this.append(result, new ConstantExpression(", ")));
            }
            body.addStatement(new IfStatement(new BooleanExpression(new VariableExpression(cNode.getField("$map$constructor"))), this.toStringPropertyName(result, pNode.getName()), new EmptyStatement()));
            final Expression fieldExpr = new VariableExpression(pNode.getField());
            body.addStatement(this.append(result, new StaticMethodCallExpression(ImmutableASTTransformation.INVOKER_TYPE, "toString", fieldExpr)));
        }
        body.addStatement(this.append(result, new ConstantExpression(")")));
        body.addStatement(new ReturnStatement(new MethodCallExpression(result, "toString", MethodCallExpression.NO_ARGUMENTS)));
        cNode.addMethod(new MethodNode(hasExistingToString ? "_toString" : "toString", hasExistingToString ? 2 : 1, ClassHelper.STRING_TYPE, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body));
    }
    
    private Statement toStringPropertyName(final Expression result, final String fName) {
        final BlockStatement body = new BlockStatement();
        body.addStatement(this.append(result, new ConstantExpression(fName)));
        body.addStatement(this.append(result, new ConstantExpression(":")));
        return body;
    }
    
    private ExpressionStatement append(final Expression result, final Expression expr) {
        return new ExpressionStatement(new MethodCallExpression(result, "append", expr));
    }
    
    private Statement calculateHashStatements(final Expression hash, final List<PropertyNode> list) {
        final BlockStatement body = new BlockStatement();
        final Expression result = new VariableExpression("_result");
        final Expression init = new StaticMethodCallExpression(ImmutableASTTransformation.HASHUTIL_TYPE, "initHash", MethodCallExpression.NO_ARGUMENTS);
        body.addStatement(new ExpressionStatement(new DeclarationExpression(result, ImmutableASTTransformation.ASSIGN, init)));
        for (final PropertyNode pNode : list) {
            final Expression fieldExpr = new VariableExpression(pNode.getField());
            final Expression args = new TupleExpression(result, fieldExpr);
            final Expression current = new StaticMethodCallExpression(ImmutableASTTransformation.HASHUTIL_TYPE, "updateHash", args);
            body.addStatement(this.assignStatement(result, current));
        }
        body.addStatement(this.assignStatement(hash, result));
        return body;
    }
    
    private void createEquals(final ClassNode cNode) {
        final boolean hasExistingEquals = this.hasDeclaredMethod(cNode, "equals", 1);
        if (hasExistingEquals && this.hasDeclaredMethod(cNode, "_equals", 1)) {
            return;
        }
        final BlockStatement body = new BlockStatement();
        final Expression other = new VariableExpression("other");
        body.addStatement(this.returnFalseIfNull(other));
        body.addStatement(this.returnFalseIfWrongType(cNode, other));
        body.addStatement(this.returnTrueIfIdentical(VariableExpression.THIS_EXPRESSION, other));
        body.addStatement(new ExpressionStatement(new BinaryExpression(other, ImmutableASTTransformation.ASSIGN, new CastExpression(cNode, other))));
        final List<PropertyNode> list = this.getInstanceProperties(cNode);
        for (final PropertyNode pNode : list) {
            body.addStatement(this.returnFalseIfPropertyNotEqual(pNode, other));
        }
        body.addStatement(new ReturnStatement(ConstantExpression.TRUE));
        final Parameter[] params = { new Parameter(ImmutableASTTransformation.OBJECT_TYPE, "other") };
        cNode.addMethod(new MethodNode(hasExistingEquals ? "_equals" : "equals", hasExistingEquals ? 2 : 1, ClassHelper.boolean_TYPE, params, ClassNode.EMPTY_ARRAY, body));
    }
    
    private Statement returnFalseIfWrongType(final ClassNode cNode, final Expression other) {
        return new IfStatement(this.notEqualClasses(cNode, other), new ReturnStatement(ConstantExpression.FALSE), new EmptyStatement());
    }
    
    private IfStatement returnFalseIfNull(final Expression other) {
        return new IfStatement(this.equalsNullExpr(other), new ReturnStatement(ConstantExpression.FALSE), new EmptyStatement());
    }
    
    private IfStatement returnTrueIfIdentical(final Expression self, final Expression other) {
        return new IfStatement(this.identicalExpr(self, other), new ReturnStatement(ConstantExpression.TRUE), new EmptyStatement());
    }
    
    private Statement returnFalseIfPropertyNotEqual(final PropertyNode pNode, final Expression other) {
        return new IfStatement(this.notEqualsExpr(pNode, other), new ReturnStatement(ConstantExpression.FALSE), new EmptyStatement());
    }
    
    private void addProperty(final ClassNode cNode, final PropertyNode pNode) {
        final FieldNode fn = pNode.getField();
        cNode.getFields().remove(fn);
        cNode.addProperty(pNode.getName(), pNode.getModifiers() | 0x10, pNode.getType(), pNode.getInitialExpression(), pNode.getGetterBlock(), pNode.getSetterBlock());
        final FieldNode newfn = cNode.getField(fn.getName());
        cNode.getFields().remove(newfn);
        cNode.addField(fn);
    }
    
    private void createConstructor(final ClassNode cNode) {
        final FieldNode constructorField = cNode.addField("$map$constructor", 4098, ClassHelper.boolean_TYPE, null);
        final Expression constructorStyle = new VariableExpression(constructorField);
        if (cNode.getDeclaredConstructors().size() != 0) {
            throw new RuntimeException("Explicit constructors not allowed for " + ImmutableASTTransformation.MY_TYPE_NAME + " class: " + cNode.getNameWithoutPackage());
        }
        final List<PropertyNode> list = this.getInstanceProperties(cNode);
        final boolean specialHashMapCase = list.size() == 1 && list.get(0).getField().getType().equals(ImmutableASTTransformation.HASHMAP_TYPE);
        if (specialHashMapCase) {
            this.createConstructorMapSpecial(cNode, constructorStyle, list);
        }
        else {
            this.createConstructorMap(cNode, constructorStyle, list);
            this.createConstructorOrdered(cNode, constructorStyle, list);
        }
    }
    
    private List<PropertyNode> getInstanceProperties(final ClassNode cNode) {
        final List<PropertyNode> result = new ArrayList<PropertyNode>();
        for (final PropertyNode pNode : cNode.getProperties()) {
            if (!pNode.isStatic()) {
                result.add(pNode);
            }
        }
        return result;
    }
    
    private void createConstructorMapSpecial(final ClassNode cNode, final Expression constructorStyle, final List<PropertyNode> list) {
        final BlockStatement body = new BlockStatement();
        body.addStatement(this.createConstructorStatementMapSpecial(list.get(0).getField()));
        this.createConstructorMapCommon(cNode, constructorStyle, body);
    }
    
    private void createConstructorMap(final ClassNode cNode, final Expression constructorStyle, final List<PropertyNode> list) {
        final BlockStatement body = new BlockStatement();
        for (final PropertyNode pNode : list) {
            body.addStatement(this.createConstructorStatement(cNode, pNode));
        }
        final Expression checkArgs = new ArgumentListExpression(new VariableExpression("this"), new VariableExpression("args"));
        body.addStatement(new ExpressionStatement(new StaticMethodCallExpression(ImmutableASTTransformation.SELF_TYPE, "checkPropNames", checkArgs)));
        this.createConstructorMapCommon(cNode, constructorStyle, body);
    }
    
    private void createConstructorMapCommon(final ClassNode cNode, final Expression constructorStyle, final BlockStatement body) {
        final List<FieldNode> fList = cNode.getFields();
        for (final FieldNode fNode : fList) {
            if (fNode.isPublic()) {
                continue;
            }
            if (cNode.getProperty(fNode.getName()) != null) {
                continue;
            }
            if (fNode.isFinal() && fNode.isStatic()) {
                continue;
            }
            if (fNode.getName().contains("$")) {
                continue;
            }
            if (fNode.isFinal() && fNode.getInitialExpression() != null) {
                body.addStatement(this.checkFinalArgNotOverridden(cNode, fNode));
            }
            body.addStatement(this.createConstructorStatementDefault(fNode));
        }
        body.addStatement(this.assignStatement(constructorStyle, ConstantExpression.TRUE));
        final Parameter[] params = { new Parameter(ImmutableASTTransformation.HASHMAP_TYPE, "args") };
        cNode.addConstructor(new ConstructorNode(1, params, ClassNode.EMPTY_ARRAY, new IfStatement(this.equalsNullExpr(new VariableExpression("args")), new EmptyStatement(), body)));
    }
    
    private Statement checkFinalArgNotOverridden(final ClassNode cNode, final FieldNode fNode) {
        final String name = fNode.getName();
        final Expression value = this.findArg(name);
        return new IfStatement(this.equalsNullExpr(value), new EmptyStatement(), new ThrowStatement(new ConstructorCallExpression(ImmutableASTTransformation.READONLYEXCEPTION_TYPE, new ArgumentListExpression(new ConstantExpression(name), new ConstantExpression(cNode.getName())))));
    }
    
    private void createConstructorOrdered(final ClassNode cNode, final Expression constructorStyle, final List<PropertyNode> list) {
        final MapExpression argMap = new MapExpression();
        final Parameter[] orderedParams = new Parameter[list.size()];
        int index = 0;
        for (final PropertyNode pNode : list) {
            orderedParams[index++] = new Parameter(pNode.getField().getType(), pNode.getField().getName());
            argMap.addMapEntryExpression(new ConstantExpression(pNode.getName()), new VariableExpression(pNode.getName()));
        }
        final BlockStatement orderedBody = new BlockStatement();
        orderedBody.addStatement(new ExpressionStatement(new ConstructorCallExpression(ClassNode.THIS, new ArgumentListExpression(new CastExpression(ImmutableASTTransformation.HASHMAP_TYPE, argMap)))));
        orderedBody.addStatement(this.assignStatement(constructorStyle, ConstantExpression.FALSE));
        cNode.addConstructor(new ConstructorNode(1, orderedParams, ClassNode.EMPTY_ARRAY, orderedBody));
    }
    
    private Statement createConstructorStatement(final ClassNode cNode, final PropertyNode pNode) {
        final FieldNode fNode = pNode.getField();
        final ClassNode fieldType = fNode.getType();
        Statement statement;
        if (fieldType.isArray() || fieldType.implementsInterface(ImmutableASTTransformation.CLONEABLE_TYPE)) {
            statement = this.createConstructorStatementArrayOrCloneable(fNode);
        }
        else if (fieldType.isDerivedFrom(ImmutableASTTransformation.DATE_TYPE)) {
            statement = this.createConstructorStatementDate(fNode);
        }
        else if (this.isOrImplements(fieldType, ImmutableASTTransformation.COLLECTION_TYPE) || this.isOrImplements(fieldType, ImmutableASTTransformation.MAP_TYPE)) {
            statement = this.createConstructorStatementCollection(fNode);
        }
        else if (this.isKnownImmutable(fieldType)) {
            statement = this.createConstructorStatementDefault(fNode);
        }
        else {
            if (fieldType.isResolved()) {
                throw new RuntimeException(createErrorMessage(cNode.getName(), fNode.getName(), fieldType.getName(), "compiling"));
            }
            statement = this.createConstructorStatementGuarded(cNode, fNode);
        }
        return statement;
    }
    
    private boolean isOrImplements(final ClassNode fieldType, final ClassNode interfaceType) {
        return fieldType.equals(interfaceType) || fieldType.implementsInterface(interfaceType);
    }
    
    private Statement createConstructorStatementGuarded(final ClassNode cNode, final FieldNode fNode) {
        final Expression fieldExpr = new VariableExpression(fNode);
        Expression initExpr = fNode.getInitialValueExpression();
        if (initExpr == null) {
            initExpr = ConstantExpression.NULL;
        }
        final Expression unknown = this.findArg(fNode.getName());
        return new IfStatement(this.equalsNullExpr(unknown), new IfStatement(this.equalsNullExpr(initExpr), new EmptyStatement(), this.assignStatement(fieldExpr, this.checkUnresolved(cNode, fNode, initExpr))), this.assignStatement(fieldExpr, this.checkUnresolved(cNode, fNode, unknown)));
    }
    
    private Expression checkUnresolved(final ClassNode cNode, final FieldNode fNode, final Expression value) {
        final Expression args = new TupleExpression(new ConstantExpression(cNode.getName()), new ConstantExpression(fNode.getName()), value);
        return new StaticMethodCallExpression(ImmutableASTTransformation.SELF_TYPE, "checkImmutable", args);
    }
    
    private Statement createConstructorStatementCollection(final FieldNode fNode) {
        final Expression fieldExpr = new VariableExpression(fNode);
        Expression initExpr = fNode.getInitialValueExpression();
        if (initExpr == null) {
            initExpr = ConstantExpression.NULL;
        }
        final Expression collection = this.findArg(fNode.getName());
        return new IfStatement(this.equalsNullExpr(collection), new IfStatement(this.equalsNullExpr(initExpr), new EmptyStatement(), this.assignStatement(fieldExpr, this.cloneCollectionExpr(initExpr))), this.assignStatement(fieldExpr, this.cloneCollectionExpr(collection)));
    }
    
    private Statement createConstructorStatementMapSpecial(final FieldNode fNode) {
        final Expression fieldExpr = new VariableExpression(fNode);
        Expression initExpr = fNode.getInitialValueExpression();
        if (initExpr == null) {
            initExpr = ConstantExpression.NULL;
        }
        final Expression namedArgs = this.findArg(fNode.getName());
        final Expression baseArgs = new VariableExpression("args");
        return new IfStatement(this.equalsNullExpr(baseArgs), new IfStatement(this.equalsNullExpr(initExpr), new EmptyStatement(), this.assignStatement(fieldExpr, this.cloneCollectionExpr(initExpr))), new IfStatement(this.equalsNullExpr(namedArgs), new IfStatement(this.isTrueExpr(new MethodCallExpression(baseArgs, "containsKey", new ConstantExpression(fNode.getName()))), this.assignStatement(fieldExpr, namedArgs), this.assignStatement(fieldExpr, this.cloneCollectionExpr(baseArgs))), new IfStatement(this.isOneExpr(new MethodCallExpression(baseArgs, "size", MethodCallExpression.NO_ARGUMENTS)), this.assignStatement(fieldExpr, this.cloneCollectionExpr(namedArgs)), this.assignStatement(fieldExpr, this.cloneCollectionExpr(baseArgs)))));
    }
    
    private boolean isKnownImmutable(final ClassNode fieldType) {
        return fieldType.isResolved() && (fieldType.isEnum() || ClassHelper.isPrimitiveType(fieldType) || inImmutableList(fieldType.getName()));
    }
    
    private static boolean inImmutableList(final String typeName) {
        return ImmutableASTTransformation.immutableList.contains(typeName);
    }
    
    private Statement createConstructorStatementDefault(final FieldNode fNode) {
        final String name = fNode.getName();
        final Expression fieldExpr = new PropertyExpression(VariableExpression.THIS_EXPRESSION, name);
        Expression initExpr = fNode.getInitialValueExpression();
        if (initExpr == null) {
            initExpr = ConstantExpression.NULL;
        }
        final Expression value = this.findArg(fNode.getName());
        return new IfStatement(this.equalsNullExpr(value), new IfStatement(this.equalsNullExpr(initExpr), new EmptyStatement(), this.assignStatement(fieldExpr, initExpr)), this.assignStatement(fieldExpr, value));
    }
    
    private Statement createConstructorStatementArrayOrCloneable(final FieldNode fNode) {
        final Expression fieldExpr = new VariableExpression(fNode);
        Expression initExpr = fNode.getInitialValueExpression();
        if (initExpr == null) {
            initExpr = ConstantExpression.NULL;
        }
        final Expression array = this.findArg(fNode.getName());
        return new IfStatement(this.equalsNullExpr(array), new IfStatement(this.equalsNullExpr(initExpr), this.assignStatement(fieldExpr, ConstantExpression.NULL), this.assignStatement(fieldExpr, this.cloneArrayOrCloneableExpr(initExpr))), this.assignStatement(fieldExpr, this.cloneArrayOrCloneableExpr(array)));
    }
    
    private Statement createConstructorStatementDate(final FieldNode fNode) {
        final Expression fieldExpr = new VariableExpression(fNode);
        Expression initExpr = fNode.getInitialValueExpression();
        if (initExpr == null) {
            initExpr = ConstantExpression.NULL;
        }
        final Expression date = this.findArg(fNode.getName());
        return new IfStatement(this.equalsNullExpr(date), new IfStatement(this.equalsNullExpr(initExpr), this.assignStatement(fieldExpr, ConstantExpression.NULL), this.assignStatement(fieldExpr, this.cloneDateExpr(initExpr))), this.assignStatement(fieldExpr, this.cloneDateExpr(date)));
    }
    
    private Expression cloneDateExpr(final Expression origDate) {
        return new ConstructorCallExpression(ImmutableASTTransformation.DATE_TYPE, new MethodCallExpression(origDate, "getTime", MethodCallExpression.NO_ARGUMENTS));
    }
    
    private Statement assignStatement(final Expression fieldExpr, final Expression value) {
        return new ExpressionStatement(this.assignExpr(fieldExpr, value));
    }
    
    private Expression assignExpr(final Expression fieldExpr, final Expression value) {
        return new BinaryExpression(fieldExpr, ImmutableASTTransformation.ASSIGN, value);
    }
    
    private BooleanExpression equalsNullExpr(final Expression argExpr) {
        return new BooleanExpression(new BinaryExpression(argExpr, ImmutableASTTransformation.COMPARE_EQUAL, ConstantExpression.NULL));
    }
    
    private BooleanExpression isTrueExpr(final Expression argExpr) {
        return new BooleanExpression(new BinaryExpression(argExpr, ImmutableASTTransformation.COMPARE_EQUAL, ConstantExpression.TRUE));
    }
    
    private BooleanExpression isZeroExpr(final Expression expr) {
        return new BooleanExpression(new BinaryExpression(expr, ImmutableASTTransformation.COMPARE_EQUAL, new ConstantExpression(0)));
    }
    
    private BooleanExpression isOneExpr(final Expression expr) {
        return new BooleanExpression(new BinaryExpression(expr, ImmutableASTTransformation.COMPARE_EQUAL, new ConstantExpression(1)));
    }
    
    private BooleanExpression notEqualsExpr(final PropertyNode pNode, final Expression other) {
        final Expression fieldExpr = new VariableExpression(pNode.getField());
        final Expression otherExpr = new PropertyExpression(other, pNode.getField().getName());
        return new BooleanExpression(new BinaryExpression(fieldExpr, ImmutableASTTransformation.COMPARE_NOT_EQUAL, otherExpr));
    }
    
    private BooleanExpression identicalExpr(final Expression self, final Expression other) {
        return new BooleanExpression(new MethodCallExpression(self, "is", new ArgumentListExpression(other)));
    }
    
    private BooleanExpression notEqualClasses(final ClassNode cNode, final Expression other) {
        return new BooleanExpression(new BinaryExpression(new ClassExpression(cNode), ImmutableASTTransformation.COMPARE_NOT_EQUAL, new MethodCallExpression(other, "getClass", MethodCallExpression.NO_ARGUMENTS)));
    }
    
    private Expression findArg(final String fName) {
        return new PropertyExpression(new VariableExpression("args"), fName);
    }
    
    private void adjustPropertyForImmutability(final PropertyNode pNode, final List<PropertyNode> newNodes) {
        final FieldNode fNode = pNode.getField();
        fNode.setModifiers((pNode.getModifiers() & 0xFFFFFFFE) | 0x10 | 0x2);
        this.adjustPropertyNode(pNode, this.createGetterBody(fNode));
        newNodes.add(pNode);
    }
    
    private void adjustPropertyNode(final PropertyNode pNode, final Statement getterBody) {
        pNode.setSetterBlock(null);
        pNode.setGetterBlock(getterBody);
    }
    
    private Statement createGetterBody(final FieldNode fNode) {
        final BlockStatement body = new BlockStatement();
        final ClassNode fieldType = fNode.getType();
        Statement statement;
        if (fieldType.isArray() || fieldType.implementsInterface(ImmutableASTTransformation.CLONEABLE_TYPE)) {
            statement = this.createGetterBodyArrayOrCloneable(fNode);
        }
        else if (fieldType.isDerivedFrom(ImmutableASTTransformation.DATE_TYPE)) {
            statement = this.createGetterBodyDate(fNode);
        }
        else {
            statement = this.createGetterBodyDefault(fNode);
        }
        body.addStatement(statement);
        return body;
    }
    
    private Statement createGetterBodyDefault(final FieldNode fNode) {
        final Expression fieldExpr = new VariableExpression(fNode);
        return new ExpressionStatement(fieldExpr);
    }
    
    private static String createErrorMessage(final String className, final String fieldName, final String typeName, final String mode) {
        return ImmutableASTTransformation.MY_TYPE_NAME + " processor doesn't know how to handle field '" + fieldName + "' of type '" + prettyTypeName(typeName) + "' while " + mode + " class " + className + ".\n" + ImmutableASTTransformation.MY_TYPE_NAME + " classes currently only support properties with known immutable types " + "or types where special handling achieves immutable behavior, including:\n" + "- Strings, primitive types, wrapper types, BigInteger and BigDecimal, enums\n" + "- other " + ImmutableASTTransformation.MY_TYPE_NAME + " classes and known immutables (java.awt.Color, java.net.URI)\n" + "- Cloneable classes, collections, maps and arrays, and other classes with special handling (java.util.Date)\n" + "Other restrictions apply, please see the groovydoc for " + ImmutableASTTransformation.MY_TYPE_NAME + " for further details";
    }
    
    private static String prettyTypeName(final String name) {
        return name.equals("java.lang.Object") ? (name + " or def") : name;
    }
    
    private Statement createGetterBodyArrayOrCloneable(final FieldNode fNode) {
        final Expression fieldExpr = new VariableExpression(fNode);
        final Expression expression = this.cloneArrayOrCloneableExpr(fieldExpr);
        return this.safeExpression(fieldExpr, expression);
    }
    
    private Expression cloneArrayOrCloneableExpr(final Expression fieldExpr) {
        return new MethodCallExpression(fieldExpr, "clone", MethodCallExpression.NO_ARGUMENTS);
    }
    
    private Expression cloneCollectionExpr(final Expression fieldExpr) {
        return new StaticMethodCallExpression(ImmutableASTTransformation.DGM_TYPE, "asImmutable", fieldExpr);
    }
    
    private Statement createGetterBodyDate(final FieldNode fNode) {
        final Expression fieldExpr = new VariableExpression(fNode);
        final Expression expression = this.cloneDateExpr(fieldExpr);
        return this.safeExpression(fieldExpr, expression);
    }
    
    private Statement safeExpression(final Expression fieldExpr, final Expression expression) {
        return new IfStatement(this.equalsNullExpr(fieldExpr), new ExpressionStatement(fieldExpr), new ExpressionStatement(expression));
    }
    
    public static Object checkImmutable(final String className, final String fieldName, final Object field) {
        if (field == null || field instanceof Enum || inImmutableList(field.getClass().getName())) {
            return field;
        }
        if (field instanceof Collection) {
            return DefaultGroovyMethods.asImmutable((Collection<?>)field);
        }
        if (field.getClass().getAnnotation((Class<Annotation>)ImmutableASTTransformation.MY_CLASS) != null) {
            return field;
        }
        final String typeName = field.getClass().getName();
        throw new RuntimeException(createErrorMessage(className, fieldName, typeName, "constructing"));
    }
    
    public static void checkPropNames(final Object instance, final Map<String, Object> args) {
        final MetaClass metaClass = InvokerHelper.getMetaClass(instance);
        for (final String k : args.keySet()) {
            if (metaClass.hasProperty(instance, k) == null) {
                throw new MissingPropertyException(k, instance.getClass());
            }
        }
    }
    
    static {
        ImmutableASTTransformation.immutableList = Arrays.asList("java.lang.Boolean", "java.lang.Byte", "java.lang.Character", "java.lang.Double", "java.lang.Float", "java.lang.Integer", "java.lang.Long", "java.lang.Short", "java.lang.String", "java.math.BigInteger", "java.math.BigDecimal", "java.awt.Color", "java.net.URI");
        MY_CLASS = Immutable.class;
        MY_TYPE = new ClassNode(ImmutableASTTransformation.MY_CLASS);
        MY_TYPE_NAME = "@" + ImmutableASTTransformation.MY_TYPE.getNameWithoutPackage();
        OBJECT_TYPE = new ClassNode(Object.class);
        HASHMAP_TYPE = new ClassNode(HashMap.class);
        MAP_TYPE = new ClassNode(Map.class);
        DATE_TYPE = new ClassNode(Date.class);
        CLONEABLE_TYPE = new ClassNode(Cloneable.class);
        COLLECTION_TYPE = new ClassNode(Collection.class);
        HASHUTIL_TYPE = new ClassNode(HashCodeHelper.class);
        STRINGBUFFER_TYPE = new ClassNode(StringBuffer.class);
        READONLYEXCEPTION_TYPE = new ClassNode(ReadOnlyPropertyException.class);
        DGM_TYPE = new ClassNode(DefaultGroovyMethods.class);
        INVOKER_TYPE = new ClassNode(InvokerHelper.class);
        SELF_TYPE = new ClassNode(ImmutableASTTransformation.class);
        COMPARE_EQUAL = Token.newSymbol(123, -1, -1);
        COMPARE_NOT_EQUAL = Token.newSymbol(120, -1, -1);
        ASSIGN = Token.newSymbol(100, -1, -1);
    }
}
