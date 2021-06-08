// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import java.util.Collections;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import java.util.Iterator;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.CodeVisitorSupport;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import groovy.lang.GroovyObject;
import groovyjarjarasm.asm.Label;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import java.util.Set;
import java.util.List;
import org.codehaus.groovy.ast.InnerClassNode;
import groovy.lang.GroovyObjectSupport;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.syntax.RuntimeParserException;
import java.lang.reflect.Modifier;
import org.codehaus.groovy.ast.expr.Expression;
import groovyjarjarasm.asm.MethodVisitor;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.Parameter;
import groovyjarjarasm.asm.Opcodes;
import org.codehaus.groovy.ast.GroovyClassVisitor;

public class Verifier implements GroovyClassVisitor, Opcodes
{
    public static final String __TIMESTAMP = "__timeStamp";
    public static final String __TIMESTAMP__ = "__timeStamp__239_neverHappen";
    private static final Parameter[] INVOKE_METHOD_PARAMS;
    private static final Parameter[] SET_PROPERTY_PARAMS;
    private static final Parameter[] GET_PROPERTY_PARAMS;
    private static final Parameter[] SET_METACLASS_PARAMS;
    private ClassNode classNode;
    private MethodNode methodNode;
    
    public ClassNode getClassNode() {
        return this.classNode;
    }
    
    public MethodNode getMethodNode() {
        return this.methodNode;
    }
    
    private FieldNode setMetaClassFieldIfNotExists(final ClassNode node, FieldNode metaClassField) {
        if (metaClassField != null) {
            return metaClassField;
        }
        final String classInternalName = BytecodeHelper.getClassInternalName(node);
        metaClassField = node.addField("metaClass", 4226, ClassHelper.METACLASS_TYPE, new BytecodeExpression() {
            @Override
            public void visit(final MethodVisitor mv) {
                mv.visitVarInsn(25, 0);
                mv.visitMethodInsn(182, classInternalName, "$getStaticMetaClass", "()Lgroovy/lang/MetaClass;");
            }
            
            @Override
            public ClassNode getType() {
                return ClassHelper.METACLASS_TYPE;
            }
        });
        metaClassField.setSynthetic(true);
        return metaClassField;
    }
    
    private FieldNode getMetaClassField(final ClassNode node) {
        FieldNode ret = node.getDeclaredField("metaClass");
        if (ret == null) {
            ClassNode current = node;
            while (current != ClassHelper.OBJECT_TYPE) {
                current = current.getSuperClass();
                if (current == null) {
                    break;
                }
                ret = current.getDeclaredField("metaClass");
                if (ret == null) {
                    continue;
                }
                if (Modifier.isPrivate(ret.getModifiers())) {
                    continue;
                }
                return ret;
            }
            return null;
        }
        final ClassNode mcFieldType = ret.getType();
        if (!mcFieldType.equals(ClassHelper.METACLASS_TYPE)) {
            throw new RuntimeParserException("The class " + node.getName() + " cannot declare field 'metaClass' of type " + mcFieldType.getName() + " as it needs to be of " + "the type " + ClassHelper.METACLASS_TYPE.getName() + " for internal groovy purposes", ret);
        }
        return ret;
    }
    
    public void visitClass(final ClassNode node) {
        this.classNode = node;
        if ((this.classNode.getModifiers() & 0x200) > 0) {
            final ConstructorNode dummy = new ConstructorNode(0, null);
            this.addInitialization(node, dummy);
            node.visitContents(this);
            return;
        }
        final ClassNode[] classNodes = this.classNode.getInterfaces();
        final List interfaces = new ArrayList();
        for (int i = 0; i < classNodes.length; ++i) {
            final ClassNode classNode = classNodes[i];
            interfaces.add(classNode.getName());
        }
        final Set interfaceSet = new HashSet(interfaces);
        if (interfaceSet.size() != interfaces.size()) {
            throw new RuntimeParserException("Duplicate interfaces in implements list: " + interfaces, this.classNode);
        }
        this.addDefaultParameterMethods(node);
        this.addDefaultParameterConstructors(node);
        final String classInternalName = BytecodeHelper.getClassInternalName(node);
        this.addStaticMetaClassField(node, classInternalName);
        final boolean knownSpecialCase = node.isDerivedFrom(ClassHelper.GSTRING_TYPE) || node.isDerivedFrom(ClassHelper.make(GroovyObjectSupport.class));
        if (!knownSpecialCase) {
            this.addGroovyObjectInterfaceAndMethods(node, classInternalName);
        }
        this.addDefaultConstructor(node);
        if (!(node instanceof InnerClassNode)) {
            this.addTimeStamp(node);
        }
        this.addInitialization(node);
        this.checkReturnInObjectInitializer(node.getObjectInitializerStatements());
        node.getObjectInitializerStatements().clear();
        this.addCovariantMethods(node);
        node.visitContents(this);
    }
    
    private void addDefaultConstructor(final ClassNode node) {
        if (!node.getDeclaredConstructors().isEmpty()) {
            return;
        }
        final BlockStatement empty = new BlockStatement();
        empty.setSourcePosition(node);
        final ConstructorNode constructor = new ConstructorNode(1, empty);
        constructor.setSourcePosition(node);
        constructor.setHasNoRealSourcePosition(true);
        node.addConstructor(constructor);
    }
    
    private void addStaticMetaClassField(final ClassNode node, final String classInternalName) {
        String _staticClassInfoFieldName;
        for (_staticClassInfoFieldName = "$staticClassInfo"; node.getDeclaredField(_staticClassInfoFieldName) != null; _staticClassInfoFieldName += "$") {}
        final String staticMetaClassFieldName = _staticClassInfoFieldName;
        final FieldNode staticMetaClassField = node.addField(staticMetaClassFieldName, 4106, ClassHelper.make(ClassInfo.class, false), null);
        staticMetaClassField.setSynthetic(true);
        node.addSyntheticMethod("$getStaticMetaClass", 4, ClassHelper.make(MetaClass.class), Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, new BytecodeSequence(new BytecodeInstruction() {
            @Override
            public void visit(final MethodVisitor mv) {
                mv.visitVarInsn(25, 0);
                mv.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                mv.visitMethodInsn(184, classInternalName, "$get$$class$" + classInternalName.replaceAll("\\/", "\\$"), "()Ljava/lang/Class;");
                final Label l1 = new Label();
                mv.visitJumpInsn(166, l1);
                mv.visitVarInsn(25, 0);
                mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/ScriptBytecodeAdapter", "initMetaClass", "(Ljava/lang/Object;)Lgroovy/lang/MetaClass;");
                mv.visitInsn(176);
                mv.visitLabel(l1);
                mv.visitFieldInsn(178, classInternalName, staticMetaClassFieldName, "Lorg/codehaus/groovy/reflection/ClassInfo;");
                mv.visitVarInsn(58, 1);
                mv.visitVarInsn(25, 1);
                final Label l2 = new Label();
                mv.visitJumpInsn(199, l2);
                mv.visitVarInsn(25, 0);
                mv.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                mv.visitMethodInsn(184, "org/codehaus/groovy/reflection/ClassInfo", "getClassInfo", "(Ljava/lang/Class;)Lorg/codehaus/groovy/reflection/ClassInfo;");
                mv.visitInsn(89);
                mv.visitVarInsn(58, 1);
                mv.visitFieldInsn(179, classInternalName, staticMetaClassFieldName, "Lorg/codehaus/groovy/reflection/ClassInfo;");
                mv.visitLabel(l2);
                mv.visitVarInsn(25, 1);
                mv.visitMethodInsn(182, "org/codehaus/groovy/reflection/ClassInfo", "getMetaClass", "()Lgroovy/lang/MetaClass;");
                mv.visitInsn(176);
            }
        }));
    }
    
    protected void addGroovyObjectInterfaceAndMethods(final ClassNode node, final String classInternalName) {
        if (!node.isDerivedFromGroovyObject()) {
            node.addInterface(ClassHelper.make(GroovyObject.class));
        }
        FieldNode metaClassField = this.getMetaClassField(node);
        if (!node.hasMethod("getMetaClass", Parameter.EMPTY_ARRAY)) {
            metaClassField = this.setMetaClassFieldIfNotExists(node, metaClassField);
            this.addMethod(node, !Modifier.isAbstract(node.getModifiers()), "getMetaClass", 1, ClassHelper.METACLASS_TYPE, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, new BytecodeSequence(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    final Label nullLabel = new Label();
                    mv.visitVarInsn(25, 0);
                    mv.visitFieldInsn(180, classInternalName, "metaClass", "Lgroovy/lang/MetaClass;");
                    mv.visitInsn(89);
                    mv.visitJumpInsn(198, nullLabel);
                    mv.visitInsn(176);
                    mv.visitLabel(nullLabel);
                    mv.visitInsn(87);
                    mv.visitVarInsn(25, 0);
                    mv.visitInsn(89);
                    mv.visitMethodInsn(182, classInternalName, "$getStaticMetaClass", "()Lgroovy/lang/MetaClass;");
                    mv.visitFieldInsn(181, classInternalName, "metaClass", "Lgroovy/lang/MetaClass;");
                    mv.visitVarInsn(25, 0);
                    mv.visitFieldInsn(180, classInternalName, "metaClass", "Lgroovy/lang/MetaClass;");
                    mv.visitInsn(176);
                }
            }));
        }
        final Parameter[] parameters = { new Parameter(ClassHelper.METACLASS_TYPE, "mc") };
        if (!node.hasMethod("setMetaClass", parameters)) {
            metaClassField = this.setMetaClassFieldIfNotExists(node, metaClassField);
            Statement setMetaClassCode;
            if (Modifier.isFinal(metaClassField.getModifiers())) {
                final ConstantExpression text = new ConstantExpression("cannot set read-only meta class");
                final ConstructorCallExpression cce = new ConstructorCallExpression(ClassHelper.make(IllegalArgumentException.class), text);
                setMetaClassCode = new ExpressionStatement(cce);
            }
            else {
                final List list = new ArrayList();
                list.add(new BytecodeInstruction() {
                    @Override
                    public void visit(final MethodVisitor mv) {
                        mv.visitVarInsn(25, 0);
                        mv.visitVarInsn(25, 1);
                        mv.visitFieldInsn(181, classInternalName, "metaClass", "Lgroovy/lang/MetaClass;");
                        mv.visitInsn(177);
                    }
                });
                setMetaClassCode = new BytecodeSequence(list);
            }
            this.addMethod(node, !Modifier.isAbstract(node.getModifiers()), "setMetaClass", 1, ClassHelper.VOID_TYPE, Verifier.SET_METACLASS_PARAMS, ClassNode.EMPTY_ARRAY, setMetaClassCode);
        }
        if (!node.hasMethod("invokeMethod", Verifier.INVOKE_METHOD_PARAMS)) {
            final VariableExpression vMethods = new VariableExpression("method");
            final VariableExpression vArguments = new VariableExpression("arguments");
            final VariableScope blockScope = new VariableScope();
            blockScope.putReferencedLocalVariable(vMethods);
            blockScope.putReferencedLocalVariable(vArguments);
            this.addMethod(node, !Modifier.isAbstract(node.getModifiers()), "invokeMethod", 1, ClassHelper.OBJECT_TYPE, Verifier.INVOKE_METHOD_PARAMS, ClassNode.EMPTY_ARRAY, new BytecodeSequence(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitVarInsn(25, 0);
                    mv.visitMethodInsn(182, classInternalName, "getMetaClass", "()Lgroovy/lang/MetaClass;");
                    mv.visitVarInsn(25, 0);
                    mv.visitVarInsn(25, 1);
                    mv.visitVarInsn(25, 2);
                    mv.visitMethodInsn(185, "groovy/lang/MetaClass", "invokeMethod", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;");
                    mv.visitInsn(176);
                }
            }));
        }
        if (!node.hasMethod("getProperty", Verifier.GET_PROPERTY_PARAMS)) {
            this.addMethod(node, !Modifier.isAbstract(node.getModifiers()), "getProperty", 1, ClassHelper.OBJECT_TYPE, Verifier.GET_PROPERTY_PARAMS, ClassNode.EMPTY_ARRAY, new BytecodeSequence(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitVarInsn(25, 0);
                    mv.visitMethodInsn(182, classInternalName, "getMetaClass", "()Lgroovy/lang/MetaClass;");
                    mv.visitVarInsn(25, 0);
                    mv.visitVarInsn(25, 1);
                    mv.visitMethodInsn(185, "groovy/lang/MetaClass", "getProperty", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;");
                    mv.visitInsn(176);
                }
            }));
        }
        if (!node.hasMethod("setProperty", Verifier.SET_PROPERTY_PARAMS)) {
            this.addMethod(node, !Modifier.isAbstract(node.getModifiers()), "setProperty", 1, ClassHelper.VOID_TYPE, Verifier.SET_PROPERTY_PARAMS, ClassNode.EMPTY_ARRAY, new BytecodeSequence(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitVarInsn(25, 0);
                    mv.visitMethodInsn(182, classInternalName, "getMetaClass", "()Lgroovy/lang/MetaClass;");
                    mv.visitVarInsn(25, 0);
                    mv.visitVarInsn(25, 1);
                    mv.visitVarInsn(25, 2);
                    mv.visitMethodInsn(185, "groovy/lang/MetaClass", "setProperty", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V");
                    mv.visitInsn(177);
                }
            }));
        }
    }
    
    protected void addMethod(final ClassNode node, final boolean shouldBeSynthetic, final String name, final int modifiers, final ClassNode returnType, final Parameter[] parameters, final ClassNode[] exceptions, final Statement code) {
        if (shouldBeSynthetic) {
            node.addSyntheticMethod(name, modifiers, returnType, parameters, exceptions, code);
        }
        else {
            node.addMethod(name, modifiers & 0xFFFFEFFF, returnType, parameters, exceptions, code);
        }
    }
    
    protected void addTimeStamp(final ClassNode node) {
        if (node.getDeclaredField("__timeStamp") == null) {
            FieldNode timeTagField = new FieldNode("__timeStamp", 4105, ClassHelper.Long_TYPE, node, new ConstantExpression(System.currentTimeMillis()));
            timeTagField.setSynthetic(true);
            node.addField(timeTagField);
            timeTagField = new FieldNode("__timeStamp__239_neverHappen" + String.valueOf(System.currentTimeMillis()), 4105, ClassHelper.Long_TYPE, node, new ConstantExpression(0L));
            timeTagField.setSynthetic(true);
            node.addField(timeTagField);
        }
    }
    
    private void checkReturnInObjectInitializer(final List init) {
        final CodeVisitorSupport cvs = new CodeVisitorSupport() {
            @Override
            public void visitReturnStatement(final ReturnStatement statement) {
                throw new RuntimeParserException("'return' is not allowed in object initializer", statement);
            }
            
            @Override
            public void visitClosureExpression(final ClosureExpression expression) {
            }
        };
        for (final Statement stm : init) {
            stm.visit(cvs);
        }
    }
    
    public void visitConstructor(final ConstructorNode node) {
        final CodeVisitorSupport checkSuper = new CodeVisitorSupport() {
            boolean firstMethodCall = true;
            String type = null;
            
            @Override
            public void visitMethodCallExpression(final MethodCallExpression call) {
                if (!this.firstMethodCall) {
                    return;
                }
                this.firstMethodCall = false;
                final String name = call.getMethodAsString();
                if (name == null) {
                    return;
                }
                if (!name.equals("super") && !name.equals("this")) {
                    return;
                }
                this.type = name;
                call.getArguments().visit(this);
                this.type = null;
            }
            
            @Override
            public void visitConstructorCallExpression(final ConstructorCallExpression call) {
                if (!call.isSpecialCall()) {
                    return;
                }
                this.type = call.getText();
                call.getArguments().visit(this);
                this.type = null;
            }
            
            @Override
            public void visitVariableExpression(final VariableExpression expression) {
                if (this.type == null) {
                    return;
                }
                final String name = expression.getName();
                if (!name.equals("this") && !name.equals("super")) {
                    return;
                }
                throw new RuntimeParserException("cannot reference " + name + " inside of " + this.type + "(....) before supertype constructor has been called", expression);
            }
        };
        final Statement s = node.getCode();
        if (s == null) {
            return;
        }
        s.visit(new VerifierCodeVisitor(this));
        s.visit(checkSuper);
    }
    
    public void visitMethod(final MethodNode node) {
        if (AsmClassGenerator.isMopMethod(node.getName())) {
            throw new RuntimeParserException("Found unexpected MOP methods in the class node for " + this.classNode.getName() + "(" + node.getName() + ")", this.classNode);
        }
        this.adjustTypesIfStaticMainMethod(this.methodNode = node);
        this.addReturnIfNeeded(node);
        final Statement statement = node.getCode();
        if (statement != null) {
            statement.visit(new VerifierCodeVisitor(this));
        }
    }
    
    private void adjustTypesIfStaticMainMethod(final MethodNode node) {
        if (node.getName().equals("main") && node.isStatic()) {
            final Parameter[] params = node.getParameters();
            if (params.length == 1) {
                final Parameter param = params[0];
                if (param.getType() == null || param.getType() == ClassHelper.OBJECT_TYPE) {
                    param.setType(ClassHelper.STRING_TYPE.makeArray());
                    final ClassNode returnType = node.getReturnType();
                    if (returnType == ClassHelper.OBJECT_TYPE) {
                        node.setReturnType(ClassHelper.VOID_TYPE);
                    }
                }
            }
        }
    }
    
    protected void addReturnIfNeeded(final MethodNode node) {
        ReturnAdder.addReturnIfNeeded(node);
    }
    
    public void visitField(final FieldNode node) {
    }
    
    private boolean methodNeedsReplacement(final MethodNode m) {
        return m == null || (m.getDeclaringClass() != this.getClassNode() && (m.getModifiers() & 0x10) == 0x0);
    }
    
    public void visitProperty(final PropertyNode node) {
        final String name = node.getName();
        final FieldNode field = node.getField();
        int propNodeModifiers = node.getModifiers();
        final String getterName = "get" + capitalize(name);
        final String setterName = "set" + capitalize(name);
        if ((propNodeModifiers & 0x40) != 0x0) {
            propNodeModifiers -= 64;
        }
        if ((propNodeModifiers & 0x80) != 0x0) {
            propNodeModifiers -= 128;
        }
        Statement getterBlock = node.getGetterBlock();
        if (getterBlock == null) {
            MethodNode getter = this.classNode.getGetterMethod(getterName);
            if (getter == null && ClassHelper.boolean_TYPE == node.getType()) {
                final String secondGetterName = "is" + capitalize(name);
                getter = this.classNode.getGetterMethod(secondGetterName);
            }
            if (!node.isPrivate() && this.methodNeedsReplacement(getter)) {
                getterBlock = this.createGetterBlock(node, field);
            }
        }
        Statement setterBlock = node.getSetterBlock();
        if (setterBlock == null) {
            final MethodNode setter = this.classNode.getSetterMethod(setterName);
            if (!node.isPrivate() && (propNodeModifiers & 0x10) == 0x0 && this.methodNeedsReplacement(setter)) {
                setterBlock = this.createSetterBlock(node, field);
            }
        }
        if (getterBlock != null) {
            final MethodNode getter2 = new MethodNode(getterName, propNodeModifiers, node.getType(), Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, getterBlock);
            getter2.setSynthetic(true);
            this.addPropertyMethod(getter2);
            this.visitMethod(getter2);
            if (ClassHelper.boolean_TYPE == node.getType() || ClassHelper.Boolean_TYPE == node.getType()) {
                final String secondGetterName2 = "is" + capitalize(name);
                final MethodNode secondGetter = new MethodNode(secondGetterName2, propNodeModifiers, node.getType(), Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, getterBlock);
                secondGetter.setSynthetic(true);
                this.addPropertyMethod(secondGetter);
                this.visitMethod(secondGetter);
            }
        }
        if (setterBlock != null) {
            final Parameter[] setterParameterTypes = { new Parameter(node.getType(), "value") };
            final MethodNode setter2 = new MethodNode(setterName, propNodeModifiers, ClassHelper.VOID_TYPE, setterParameterTypes, ClassNode.EMPTY_ARRAY, setterBlock);
            setter2.setSynthetic(true);
            this.addPropertyMethod(setter2);
            this.visitMethod(setter2);
        }
    }
    
    protected void addPropertyMethod(final MethodNode method) {
        this.classNode.addMethod(method);
    }
    
    protected void addDefaultParameterMethods(final ClassNode node) {
        final List methods = new ArrayList(node.getMethods());
        this.addDefaultParameters(methods, new DefaultArgsAction() {
            public void call(final ArgumentListExpression arguments, final Parameter[] newParams, final MethodNode method) {
                final MethodCallExpression expression = new MethodCallExpression(VariableExpression.THIS_EXPRESSION, method.getName(), arguments);
                expression.setImplicitThis(true);
                Statement code = null;
                if (method.isVoidMethod()) {
                    code = new ExpressionStatement(expression);
                }
                else {
                    code = new ReturnStatement(expression);
                }
                final MethodNode newMethod = new MethodNode(method.getName(), method.getModifiers(), method.getReturnType(), newParams, method.getExceptions(), code);
                final MethodNode oldMethod = node.getDeclaredMethod(method.getName(), newParams);
                if (oldMethod != null) {
                    throw new RuntimeParserException("The method with default parameters \"" + method.getTypeDescriptor() + "\" defines a method \"" + newMethod.getTypeDescriptor() + "\" that is already defined.", method);
                }
                Verifier.this.addPropertyMethod(newMethod);
                newMethod.setGenericsTypes(method.getGenericsTypes());
            }
        });
    }
    
    protected void addDefaultParameterConstructors(final ClassNode node) {
        final List methods = new ArrayList(node.getDeclaredConstructors());
        this.addDefaultParameters(methods, new DefaultArgsAction() {
            public void call(final ArgumentListExpression arguments, final Parameter[] newParams, final MethodNode method) {
                final ConstructorNode ctor = (ConstructorNode)method;
                final ConstructorCallExpression expression = new ConstructorCallExpression(ClassNode.THIS, arguments);
                final Statement code = new ExpressionStatement(expression);
                Verifier.this.addConstructor(newParams, ctor, code, node);
            }
        });
    }
    
    protected void addConstructor(final Parameter[] newParams, final ConstructorNode ctor, final Statement code, final ClassNode node) {
        node.addConstructor(ctor.getModifiers(), newParams, ctor.getExceptions(), code);
    }
    
    protected void addDefaultParameters(final List methods, final DefaultArgsAction action) {
        for (final MethodNode method : methods) {
            if (method.hasDefaultValue()) {
                this.addDefaultParameters(action, method);
            }
        }
    }
    
    protected void addDefaultParameters(final DefaultArgsAction action, final MethodNode method) {
        final Parameter[] parameters = method.getParameters();
        int counter = 0;
        final List paramValues = new ArrayList();
        final int size = parameters.length;
        for (int i = size - 1; i >= 0; --i) {
            final Parameter parameter = parameters[i];
            if (parameter != null && parameter.hasInitialExpression()) {
                paramValues.add(i);
                paramValues.add(new CastExpression(parameter.getType(), parameter.getInitialExpression()));
                ++counter;
            }
        }
        for (int j = 1; j <= counter; ++j) {
            final Parameter[] newParams = new Parameter[parameters.length - j];
            final ArgumentListExpression arguments = new ArgumentListExpression();
            int index = 0;
            int k = 1;
            for (int l = 0; l < parameters.length; ++l) {
                if (k > counter - j && parameters[l] != null && parameters[l].hasInitialExpression()) {
                    arguments.addExpression(new CastExpression(parameters[l].getType(), parameters[l].getInitialExpression()));
                    ++k;
                }
                else if (parameters[l] != null && parameters[l].hasInitialExpression()) {
                    newParams[index++] = parameters[l];
                    arguments.addExpression(new CastExpression(parameters[l].getType(), new VariableExpression(parameters[l].getName())));
                    ++k;
                }
                else {
                    newParams[index++] = parameters[l];
                    arguments.addExpression(new CastExpression(parameters[l].getType(), new VariableExpression(parameters[l].getName())));
                }
            }
            action.call(arguments, newParams, method);
        }
        for (int i = 0; i < parameters.length; ++i) {
            parameters[i].setInitialExpression(null);
        }
    }
    
    protected void addClosureCode(final InnerClassNode node) {
    }
    
    protected void addInitialization(final ClassNode node) {
        final Iterator iter = node.getDeclaredConstructors().iterator();
        while (iter.hasNext()) {
            this.addInitialization(node, iter.next());
        }
    }
    
    protected void addInitialization(final ClassNode node, final ConstructorNode constructorNode) {
        final Statement firstStatement = constructorNode.getFirstStatement();
        if (firstStatement instanceof BytecodeSequence) {
            return;
        }
        final ConstructorCallExpression first = this.getFirstIfSpecialConstructorCall(firstStatement);
        if (first != null && first.isThisCall()) {
            return;
        }
        final List statements = new ArrayList();
        final List staticStatements = new ArrayList();
        final boolean isEnum = node.isEnum();
        final List<Statement> initStmtsAfterEnumValuesInit = new ArrayList<Statement>();
        final Set explicitStaticPropsInEnum = new HashSet();
        if (isEnum) {
            for (final PropertyNode propNode : node.getProperties()) {
                if (!propNode.isSynthetic() && propNode.getField().isStatic()) {
                    explicitStaticPropsInEnum.add(propNode.getField().getName());
                }
            }
            for (final FieldNode fieldNode : node.getFields()) {
                if (!fieldNode.isSynthetic() && fieldNode.isStatic() && fieldNode.getType() != node) {
                    explicitStaticPropsInEnum.add(fieldNode.getName());
                }
            }
        }
        final Iterator iter = node.getFields().iterator();
        while (iter.hasNext()) {
            this.addFieldInitialization(statements, staticStatements, iter.next(), isEnum, initStmtsAfterEnumValuesInit, explicitStaticPropsInEnum);
        }
        statements.addAll(node.getObjectInitializerStatements());
        if (!statements.isEmpty()) {
            final Statement code = constructorNode.getCode();
            BlockStatement block = new BlockStatement();
            List otherStatements = block.getStatements();
            if (code instanceof BlockStatement) {
                block = (BlockStatement)code;
                otherStatements = block.getStatements();
            }
            else if (code != null) {
                otherStatements.add(code);
            }
            if (!otherStatements.isEmpty()) {
                if (first != null) {
                    otherStatements.remove(0);
                    statements.add(0, firstStatement);
                }
                final Statement stmtThis$0 = this.getImplicitThis$0StmtIfInnerClass(otherStatements);
                if (stmtThis$0 != null) {
                    statements.add(1, stmtThis$0);
                }
                statements.addAll(otherStatements);
            }
            final BlockStatement newBlock = new BlockStatement(statements, block.getVariableScope());
            newBlock.setSourcePosition(block);
            constructorNode.setCode(newBlock);
        }
        if (!staticStatements.isEmpty()) {
            if (isEnum) {
                staticStatements.removeAll(initStmtsAfterEnumValuesInit);
                node.addStaticInitializerStatements(staticStatements, true);
                if (!initStmtsAfterEnumValuesInit.isEmpty()) {
                    node.positionStmtsAfterEnumInitStmts(initStmtsAfterEnumValuesInit);
                }
            }
            else {
                node.addStaticInitializerStatements(staticStatements, true);
            }
        }
    }
    
    private Statement getImplicitThis$0StmtIfInnerClass(final List<Statement> otherStatements) {
        if (!(this.classNode instanceof InnerClassNode)) {
            return null;
        }
        for (final Statement stmt : otherStatements) {
            if (stmt instanceof BlockStatement) {
                final List<Statement> stmts = ((BlockStatement)stmt).getStatements();
                for (final Statement bstmt : stmts) {
                    if (bstmt instanceof ExpressionStatement) {
                        final Expression expr = ((ExpressionStatement)bstmt).getExpression();
                        if (!(expr instanceof BinaryExpression)) {
                            continue;
                        }
                        final Expression lExpr = ((BinaryExpression)expr).getLeftExpression();
                        if (lExpr instanceof FieldExpression && "this$0".equals(((FieldExpression)lExpr).getFieldName())) {
                            stmts.remove(bstmt);
                            return bstmt;
                        }
                        continue;
                    }
                }
            }
        }
        return null;
    }
    
    private ConstructorCallExpression getFirstIfSpecialConstructorCall(final Statement code) {
        if (code == null || !(code instanceof ExpressionStatement)) {
            return null;
        }
        final Expression expression = ((ExpressionStatement)code).getExpression();
        if (!(expression instanceof ConstructorCallExpression)) {
            return null;
        }
        final ConstructorCallExpression cce = (ConstructorCallExpression)expression;
        if (cce.isSpecialCall()) {
            return cce;
        }
        return null;
    }
    
    protected void addFieldInitialization(final List list, final List staticList, final FieldNode fieldNode, final boolean isEnumClassNode, final List initStmtsAfterEnumValuesInit, final Set explicitStaticPropsInEnum) {
        final Expression expression = fieldNode.getInitialExpression();
        if (expression != null) {
            final FieldExpression fe = new FieldExpression(fieldNode);
            if (fieldNode.getType().equals(ClassHelper.REFERENCE_TYPE) && (fieldNode.getModifiers() & 0x1000) != 0x0) {
                fe.setUseReferenceDirectly(true);
            }
            final ExpressionStatement statement = new ExpressionStatement(new BinaryExpression(fe, Token.newSymbol(100, fieldNode.getLineNumber(), fieldNode.getColumnNumber()), expression));
            if (fieldNode.isStatic()) {
                if (expression instanceof ConstantExpression) {
                    staticList.add(0, statement);
                }
                else {
                    staticList.add(statement);
                }
                fieldNode.setInitialValueExpression(null);
                if (isEnumClassNode && explicitStaticPropsInEnum.contains(fieldNode.getName())) {
                    initStmtsAfterEnumValuesInit.add(statement);
                }
            }
            else {
                list.add(statement);
            }
        }
    }
    
    public static String capitalize(final String name) {
        return MetaClassHelper.capitalize(name);
    }
    
    protected Statement createGetterBlock(final PropertyNode propertyNode, final FieldNode field) {
        return new BytecodeSequence(new BytecodeInstruction() {
            @Override
            public void visit(final MethodVisitor mv) {
                if (field.isStatic()) {
                    mv.visitFieldInsn(178, BytecodeHelper.getClassInternalName(Verifier.this.classNode), field.getName(), BytecodeHelper.getTypeDescription(field.getType()));
                }
                else {
                    mv.visitVarInsn(25, 0);
                    mv.visitFieldInsn(180, BytecodeHelper.getClassInternalName(Verifier.this.classNode), field.getName(), BytecodeHelper.getTypeDescription(field.getType()));
                }
                final BytecodeHelper helper = new BytecodeHelper(mv);
                helper.doReturn(field.getType());
            }
        });
    }
    
    protected Statement createSetterBlock(final PropertyNode propertyNode, final FieldNode field) {
        return new BytecodeSequence(new BytecodeInstruction() {
            @Override
            public void visit(final MethodVisitor mv) {
                final BytecodeHelper helper = new BytecodeHelper(mv);
                if (field.isStatic()) {
                    helper.load(field.getType(), 0);
                    mv.visitFieldInsn(179, BytecodeHelper.getClassInternalName(Verifier.this.classNode), field.getName(), BytecodeHelper.getTypeDescription(field.getType()));
                }
                else {
                    mv.visitVarInsn(25, 0);
                    helper.load(field.getType(), 1);
                    mv.visitFieldInsn(181, BytecodeHelper.getClassInternalName(Verifier.this.classNode), field.getName(), BytecodeHelper.getTypeDescription(field.getType()));
                }
                mv.visitInsn(177);
            }
        });
    }
    
    public void visitGenericType(final GenericsType genericsType) {
    }
    
    public static long getTimestamp(final Class clazz) {
        if (clazz.getClassLoader() instanceof GroovyClassLoader.InnerLoader) {
            final GroovyClassLoader.InnerLoader innerLoader = (GroovyClassLoader.InnerLoader)clazz.getClassLoader();
            return innerLoader.getTimeStamp();
        }
        final Field[] fields = clazz.getFields();
        for (int i = 0; i != fields.length; ++i) {
            if (Modifier.isStatic(fields[i].getModifiers())) {
                final String name = fields[i].getName();
                if (name.startsWith("__timeStamp__239_neverHappen")) {
                    try {
                        return Long.decode(name.substring("__timeStamp__239_neverHappen".length()));
                    }
                    catch (NumberFormatException e) {
                        return Long.MAX_VALUE;
                    }
                }
            }
        }
        return Long.MAX_VALUE;
    }
    
    protected void addCovariantMethods(final ClassNode classNode) {
        final Map methodsToAdd = new HashMap();
        final Map genericsSpec = new HashMap();
        final Map abstractMethods = new HashMap();
        final Map<String, MethodNode> allInterfaceMethods = new HashMap<String, MethodNode>();
        final ClassNode[] interfaces = classNode.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            final ClassNode iface = interfaces[i];
            final Map ifaceMethodsMap = iface.getDeclaredMethodsMap();
            abstractMethods.putAll(ifaceMethodsMap);
            allInterfaceMethods.putAll(ifaceMethodsMap);
        }
        this.collectSuperInterfaceMethods(classNode, allInterfaceMethods);
        final List declaredMethods = new ArrayList(classNode.getMethods());
        final Iterator methodsIterator = declaredMethods.iterator();
        while (methodsIterator.hasNext()) {
            final MethodNode m = methodsIterator.next();
            abstractMethods.remove(m.getTypeDescriptor());
            if (m.isStatic() || (!m.isPublic() && !m.isProtected())) {
                methodsIterator.remove();
            }
            final MethodNode intfMethod = allInterfaceMethods.get(m.getTypeDescriptor());
            if (intfMethod != null && (m.getModifiers() & 0x1000) == 0x0 && !m.isPublic() && !m.isStaticConstructor()) {
                throw new RuntimeParserException("The method " + m.getName() + " should be public as it implements the corresponding method from interface " + intfMethod.getDeclaringClass(), m);
            }
        }
        this.addCovariantMethods(classNode, declaredMethods, abstractMethods, methodsToAdd, genericsSpec);
        final Map declaredMethodsMap = new HashMap();
        if (methodsToAdd.size() > 0) {
            for (final MethodNode j : declaredMethods) {
                declaredMethodsMap.put(j.getTypeDescriptor(), j);
            }
        }
        for (final Map.Entry entry : methodsToAdd.entrySet()) {
            final MethodNode method = entry.getValue();
            final MethodNode mn = declaredMethodsMap.get(entry.getKey());
            if (mn != null && mn.getDeclaringClass().equals(classNode)) {
                continue;
            }
            this.addPropertyMethod(method);
        }
    }
    
    private void collectSuperInterfaceMethods(final ClassNode cn, final Map allInterfaceMethods) {
        final List cnInterfaces = Arrays.asList(cn.getInterfaces());
        for (ClassNode sn = cn.getSuperClass(); !sn.equals(ClassHelper.OBJECT_TYPE); sn = sn.getSuperClass()) {
            final ClassNode[] interfaces = sn.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                final ClassNode iface = interfaces[i];
                if (!cnInterfaces.contains(iface)) {
                    final Map ifaceMethodsMap = iface.getDeclaredMethodsMap();
                    allInterfaceMethods.putAll(ifaceMethodsMap);
                }
            }
        }
    }
    
    private void addCovariantMethods(final ClassNode classNode, final List declaredMethods, final Map abstractMethods, final Map methodsToAdd, final Map oldGenericsSpec) {
        final ClassNode sn = classNode.getUnresolvedSuperClass(false);
        if (sn != null) {
            final Map genericsSpec = this.createGenericsSpec(sn, oldGenericsSpec);
            final List classMethods = sn.getMethods();
            for (final MethodNode method : declaredMethods) {
                if (method.isStatic()) {
                    continue;
                }
                this.storeMissingCovariantMethods(classMethods, method, methodsToAdd, genericsSpec);
            }
            if (!abstractMethods.isEmpty()) {
                for (final MethodNode method : classMethods) {
                    if (method.isStatic()) {
                        continue;
                    }
                    this.storeMissingCovariantMethods(abstractMethods.values(), method, methodsToAdd, Collections.EMPTY_MAP);
                }
            }
            this.addCovariantMethods(sn.redirect(), declaredMethods, abstractMethods, methodsToAdd, genericsSpec);
        }
        final ClassNode[] interfaces = classNode.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            final List interfacesMethods = interfaces[i].getMethods();
            final Map genericsSpec2 = this.createGenericsSpec(interfaces[i], oldGenericsSpec);
            for (final MethodNode method2 : declaredMethods) {
                if (method2.isStatic()) {
                    continue;
                }
                this.storeMissingCovariantMethods(interfacesMethods, method2, methodsToAdd, genericsSpec2);
            }
            this.addCovariantMethods(interfaces[i], declaredMethods, abstractMethods, methodsToAdd, genericsSpec2);
        }
    }
    
    private MethodNode getCovariantImplementation(final MethodNode oldMethod, final MethodNode overridingMethod, final Map genericsSpec) {
        if (!oldMethod.getName().equals(overridingMethod.getName())) {
            return null;
        }
        if ((overridingMethod.getModifiers() & 0x40) != 0x0) {
            return null;
        }
        final boolean normalEqualParameters = this.equalParametersNormal(overridingMethod, oldMethod);
        final boolean genericEqualParameters = this.equalParametersWithGenerics(overridingMethod, oldMethod, genericsSpec);
        if (!normalEqualParameters && !genericEqualParameters) {
            return null;
        }
        final ClassNode mr = overridingMethod.getReturnType();
        final ClassNode omr = oldMethod.getReturnType();
        final boolean equalReturnType = mr.equals(omr);
        if (equalReturnType && normalEqualParameters) {
            return null;
        }
        final ClassNode testmr = this.correctToGenericsSpec(genericsSpec, omr);
        if (!this.isAssignable(mr, testmr)) {
            throw new RuntimeParserException("The return type of " + overridingMethod.getTypeDescriptor() + " in " + overridingMethod.getDeclaringClass().getName() + " is incompatible with " + oldMethod.getTypeDescriptor() + " in " + oldMethod.getDeclaringClass().getName(), overridingMethod);
        }
        if ((oldMethod.getModifiers() & 0x10) != 0x0) {
            throw new RuntimeParserException("Cannot override final method " + oldMethod.getTypeDescriptor() + " in " + oldMethod.getDeclaringClass().getName(), overridingMethod);
        }
        if (oldMethod.isStatic() != overridingMethod.isStatic()) {
            throw new RuntimeParserException("Cannot override method " + oldMethod.getTypeDescriptor() + " in " + oldMethod.getDeclaringClass().getName() + " with disparate static modifier", overridingMethod);
        }
        final MethodNode newMethod = new MethodNode(oldMethod.getName(), overridingMethod.getModifiers() | 0x1000 | 0x40, oldMethod.getReturnType().getPlainNodeReference(), this.cleanParameters(oldMethod.getParameters()), oldMethod.getExceptions(), null);
        final List instructions = new ArrayList(1);
        instructions.add(new BytecodeInstruction() {
            @Override
            public void visit(final MethodVisitor mv) {
                final BytecodeHelper helper = new BytecodeHelper(mv);
                mv.visitVarInsn(25, 0);
                final Parameter[] para = oldMethod.getParameters();
                final Parameter[] goal = overridingMethod.getParameters();
                for (int i = 0; i < para.length; ++i) {
                    helper.load(para[i].getType(), i + 1);
                    if (!para[i].getType().equals(goal[i].getType())) {
                        helper.doCast(goal[i].getType());
                    }
                }
                mv.visitMethodInsn(182, BytecodeHelper.getClassInternalName(Verifier.this.classNode), overridingMethod.getName(), BytecodeHelper.getMethodDescriptor(overridingMethod.getReturnType(), overridingMethod.getParameters()));
                helper.doReturn(oldMethod.getReturnType());
            }
        });
        newMethod.setCode(new BytecodeSequence(instructions));
        return newMethod;
    }
    
    private boolean isAssignable(final ClassNode node, final ClassNode testNode) {
        if (testNode.isInterface()) {
            if (node.isInterface()) {
                if (node.isDerivedFrom(testNode)) {
                    return true;
                }
            }
            else if (node.implementsInterface(testNode)) {
                return true;
            }
        }
        else if (node.isDerivedFrom(testNode)) {
            return true;
        }
        return false;
    }
    
    private Parameter[] cleanParameters(final Parameter[] parameters) {
        final Parameter[] params = new Parameter[parameters.length];
        for (int i = 0; i < params.length; ++i) {
            params[i] = new Parameter(parameters[i].getType().getPlainNodeReference(), parameters[i].getName());
        }
        return params;
    }
    
    private void storeMissingCovariantMethods(final Collection methods, final MethodNode method, final Map methodsToAdd, final Map genericsSpec) {
        for (final MethodNode toOverride : methods) {
            final MethodNode bridgeMethod = this.getCovariantImplementation(toOverride, method, genericsSpec);
            if (bridgeMethod == null) {
                continue;
            }
            methodsToAdd.put(bridgeMethod.getTypeDescriptor(), bridgeMethod);
        }
    }
    
    private ClassNode correctToGenericsSpec(final Map genericsSpec, final GenericsType type) {
        ClassNode ret = null;
        if (type.isPlaceholder()) {
            final String name = type.getName();
            ret = genericsSpec.get(name);
        }
        if (ret == null) {
            ret = type.getType();
        }
        return ret;
    }
    
    private ClassNode correctToGenericsSpec(final Map genericsSpec, ClassNode type) {
        if (type.isGenericsPlaceHolder()) {
            final String name = type.getGenericsTypes()[0].getName();
            type = genericsSpec.get(name);
        }
        if (type == null) {
            type = ClassHelper.OBJECT_TYPE;
        }
        return type;
    }
    
    private boolean equalParametersNormal(final MethodNode m1, final MethodNode m2) {
        final Parameter[] p1 = m1.getParameters();
        final Parameter[] p2 = m2.getParameters();
        if (p1.length != p2.length) {
            return false;
        }
        for (int i = 0; i < p2.length; ++i) {
            final ClassNode type = p2[i].getType();
            final ClassNode parameterType = p1[i].getType();
            if (!parameterType.equals(type)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean equalParametersWithGenerics(final MethodNode m1, final MethodNode m2, final Map genericsSpec) {
        final Parameter[] p1 = m1.getParameters();
        final Parameter[] p2 = m2.getParameters();
        if (p1.length != p2.length) {
            return false;
        }
        for (int i = 0; i < p2.length; ++i) {
            final ClassNode type = p2[i].getType();
            final ClassNode genericsType = this.correctToGenericsSpec(genericsSpec, type);
            final ClassNode parameterType = p1[i].getType();
            if (!parameterType.equals(genericsType)) {
                return false;
            }
        }
        return true;
    }
    
    private Map createGenericsSpec(final ClassNode current, final Map oldSpec) {
        final Map ret = new HashMap(oldSpec);
        final GenericsType[] sgts = current.getGenericsTypes();
        if (sgts != null) {
            final ClassNode[] spec = new ClassNode[sgts.length];
            for (int i = 0; i < spec.length; ++i) {
                spec[i] = this.correctToGenericsSpec(ret, sgts[i]);
            }
            final GenericsType[] newGts = current.redirect().getGenericsTypes();
            if (newGts == null) {
                return ret;
            }
            ret.clear();
            for (int j = 0; j < spec.length; ++j) {
                ret.put(newGts[j].getName(), spec[j]);
            }
        }
        return ret;
    }
    
    static {
        INVOKE_METHOD_PARAMS = new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "method"), new Parameter(ClassHelper.OBJECT_TYPE, "arguments") };
        SET_PROPERTY_PARAMS = new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "property"), new Parameter(ClassHelper.OBJECT_TYPE, "value") };
        GET_PROPERTY_PARAMS = new Parameter[] { new Parameter(ClassHelper.STRING_TYPE, "property") };
        SET_METACLASS_PARAMS = new Parameter[] { new Parameter(ClassHelper.METACLASS_TYPE, "mc") };
    }
    
    public interface DefaultArgsAction
    {
        void call(final ArgumentListExpression p0, final Parameter[] p1, final MethodNode p2);
    }
}
