// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import groovyjarjarasm.asm.MethodAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.ast.CompileUnit;
import org.codehaus.groovy.ast.expr.ExpressionTransformer;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.runtime.MetaClassHelper;
import java.util.Collections;
import java.util.Comparator;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.RegexExpression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.syntax.RuntimeParserException;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.transform.powerassert.SourceTextNotAvailableException;
import org.codehaus.groovy.transform.powerassert.SourceText;
import org.codehaus.groovy.control.Janitor;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.EmptyExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.PropertyNode;
import groovyjarjarasm.asm.FieldVisitor;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.expr.AnnotationConstantExpression;
import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import groovyjarjarasm.asm.Type;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ClassExpression;
import java.util.LinkedList;
import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.AnnotationVisitor;
import java.util.Iterator;
import org.codehaus.groovy.ast.PackageNode;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import java.util.Collection;
import org.codehaus.groovy.ast.InterfaceHelperClassNode;
import java.util.ArrayList;
import java.util.HashSet;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import java.util.HashMap;
import java.util.List;
import org.codehaus.groovy.ast.GenericsType;
import groovyjarjarasm.asm.ClassWriter;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ConstructorNode;
import java.util.Map;
import org.codehaus.groovy.ast.ClassNode;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.ClassVisitor;

public class AsmClassGenerator extends ClassGenerator
{
    private AssertionTracker assertionTracker;
    private final ClassVisitor cv;
    private MethodVisitor mv;
    private GeneratorContext context;
    private String sourceFile;
    private ClassNode classNode;
    private ClassNode outermostClass;
    private String internalClassName;
    private String internalBaseClassName;
    private CompileStack compileStack;
    private boolean outputReturn;
    private boolean leftHandExpression;
    static final MethodCallerMultiAdapter invokeMethodOnCurrent;
    static final MethodCallerMultiAdapter invokeMethodOnSuper;
    static final MethodCallerMultiAdapter invokeMethod;
    static final MethodCallerMultiAdapter invokeStaticMethod;
    static final MethodCallerMultiAdapter invokeNew;
    static final MethodCallerMultiAdapter setField;
    static final MethodCallerMultiAdapter getField;
    static final MethodCallerMultiAdapter setGroovyObjectField;
    static final MethodCallerMultiAdapter getGroovyObjectField;
    static final MethodCallerMultiAdapter setFieldOnSuper;
    static final MethodCallerMultiAdapter getFieldOnSuper;
    static final MethodCallerMultiAdapter setProperty;
    static final MethodCallerMultiAdapter getProperty;
    static final MethodCallerMultiAdapter setGroovyObjectProperty;
    static final MethodCallerMultiAdapter getGroovyObjectProperty;
    static final MethodCallerMultiAdapter setPropertyOnSuper;
    static final MethodCallerMultiAdapter getPropertyOnSuper;
    static final MethodCaller iteratorNextMethod;
    static final MethodCaller iteratorHasNextMethod;
    static final MethodCaller assertFailedMethod;
    static final MethodCaller isCaseMethod;
    static final MethodCaller compareIdenticalMethod;
    static final MethodCaller compareNotIdenticalMethod;
    static final MethodCaller compareEqualMethod;
    static final MethodCaller compareNotEqualMethod;
    static final MethodCaller compareToMethod;
    static final MethodCaller compareLessThanMethod;
    static final MethodCaller compareLessThanEqualMethod;
    static final MethodCaller compareGreaterThanMethod;
    static final MethodCaller compareGreaterThanEqualMethod;
    static final MethodCaller findRegexMethod;
    static final MethodCaller matchRegexMethod;
    static final MethodCaller regexPattern;
    static final MethodCaller spreadMap;
    static final MethodCaller despreadList;
    static final MethodCaller getMethodPointer;
    static final MethodCaller invokeClosureMethod;
    static final MethodCaller unaryPlus;
    static final MethodCaller unaryMinus;
    static final MethodCaller bitwiseNegate;
    static final MethodCaller asTypeMethod;
    static final MethodCaller castToTypeMethod;
    static final MethodCaller createListMethod;
    static final MethodCaller createTupleMethod;
    static final MethodCaller createMapMethod;
    static final MethodCaller createRangeMethod;
    static final MethodCaller createPojoWrapperMethod;
    static final MethodCaller createGroovyObjectWrapperMethod;
    static final MethodCaller selectConstructorAndTransformArguments;
    private Map<String, ClassNode> referencedClasses;
    private boolean passingParams;
    private ConstructorNode constructorNode;
    private MethodNode methodNode;
    private BytecodeHelper helper;
    public static final boolean CREATE_DEBUG_INFO = true;
    public static final boolean CREATE_LINE_NUMBER_INFO = true;
    public static final boolean ASM_DEBUG = false;
    private int lineNumber;
    private ASTNode currentASTNode;
    private ClassWriter dummyClassWriter;
    private ClassNode interfaceClassLoadingClass;
    private boolean implicitThis;
    private Map<String, GenericsType> genericParameterNames;
    private ClassNode rightHandType;
    private static final String CONSTRUCTOR = "<$constructor$>";
    private List callSites;
    private int callSiteArrayVarIndex;
    private HashMap<ClosureExpression, ClassNode> closureClassMap;
    private SourceUnit source;
    private static final String DTT;
    private boolean specialCallWithinConstructor;
    private static String[] sig;
    private static final HashSet<String> names;
    private static final HashSet<String> basic;
    
    public AsmClassGenerator(final SourceUnit source, final GeneratorContext context, final ClassVisitor classVisitor, final ClassLoader classLoader, final String sourceFile) {
        super(classLoader);
        this.leftHandExpression = false;
        this.referencedClasses = new HashMap<String, ClassNode>();
        this.helper = new BytecodeHelper(null);
        this.lineNumber = -1;
        this.currentASTNode = null;
        this.dummyClassWriter = null;
        this.implicitThis = false;
        this.genericParameterNames = null;
        this.callSites = new ArrayList();
        this.specialCallWithinConstructor = false;
        this.source = source;
        this.context = context;
        this.cv = classVisitor;
        this.sourceFile = sourceFile;
        this.dummyClassWriter = new ClassWriter(1);
        new DummyClassGenerator(context, this.dummyClassWriter, classLoader, sourceFile);
        this.compileStack = new CompileStack();
        this.genericParameterNames = new HashMap<String, GenericsType>();
        this.closureClassMap = new HashMap<ClosureExpression, ClassNode>();
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    @Override
    public void visitClass(final ClassNode classNode) {
        try {
            this.callSites.clear();
            if (classNode instanceof InterfaceHelperClassNode) {
                final InterfaceHelperClassNode ihcn = (InterfaceHelperClassNode)classNode;
                this.callSites.addAll(ihcn.getCallSites());
            }
            this.referencedClasses.clear();
            this.classNode = classNode;
            this.outermostClass = null;
            this.internalClassName = BytecodeHelper.getClassInternalName(classNode);
            this.internalBaseClassName = BytecodeHelper.getClassInternalName(classNode.getSuperClass());
            this.cv.visit(this.getBytecodeVersion(), this.adjustedModifiers(classNode.getModifiers()), this.internalClassName, BytecodeHelper.getGenericsSignature(classNode), this.internalBaseClassName, BytecodeHelper.getClassInternalNames(classNode.getInterfaces()));
            this.cv.visitSource(this.sourceFile, null);
            if (classNode.getName().endsWith("package-info")) {
                final PackageNode packageNode = classNode.getPackage();
                if (packageNode != null) {
                    for (final AnnotationNode an : packageNode.getAnnotations()) {
                        if (an.isBuiltIn()) {
                            continue;
                        }
                        if (an.hasSourceRetention()) {
                            continue;
                        }
                        final AnnotationVisitor av = this.getAnnotationVisitor(classNode, an, this.cv);
                        this.visitAnnotationAttributes(an, av);
                        av.visitEnd();
                    }
                }
                this.cv.visitEnd();
                return;
            }
            this.visitAnnotations(classNode, this.cv);
            if (classNode.isInterface()) {
                ClassNode owner = classNode;
                if (owner instanceof InnerClassNode) {
                    owner = owner.getOuterClass();
                }
                final String outerClassName = owner.getName();
                final String name = outerClassName + "$" + this.context.getNextInnerClassIdx();
                this.interfaceClassLoadingClass = new InterfaceHelperClassNode(owner, name, 4128, ClassHelper.OBJECT_TYPE, this.callSites);
                super.visitClass(classNode);
                this.createInterfaceSyntheticStaticFields();
            }
            else {
                super.visitClass(classNode);
                if (!classNode.declaresInterface(ClassHelper.GENERATED_CLOSURE_Type)) {
                    this.createMopMethods();
                }
                this.generateCallSiteArray();
                this.createSyntheticStaticFields();
            }
            for (final ClassNode innerClass : this.innerClasses) {
                String innerClassName = innerClass.getName();
                final String innerClassInternalName = BytecodeHelper.getClassInternalName(innerClassName);
                final int index = innerClassName.lastIndexOf(36);
                if (index >= 0) {
                    innerClassName = innerClassName.substring(index + 1);
                }
                String outerClassName2 = this.internalClassName;
                final MethodNode enclosingMethod = innerClass.getEnclosingMethod();
                if (enclosingMethod != null) {
                    outerClassName2 = null;
                    innerClassName = null;
                }
                this.cv.visitInnerClass(innerClassInternalName, outerClassName2, innerClassName, this.adjustedModifiers(innerClass.getModifiers()));
            }
            this.cv.visitEnd();
        }
        catch (GroovyRuntimeException e) {
            e.setModule(classNode.getModule());
            throw e;
        }
    }
    
    private int adjustedModifiers(final int modifiers) {
        final boolean needsSuper = (modifiers & 0x200) == 0x0;
        return needsSuper ? (modifiers | 0x20) : modifiers;
    }
    
    private void generateCallSiteArray() {
        if (!this.classNode.isInterface()) {
            this.cv.visitField(4106, "$callSiteArray", "Ljava/lang/ref/SoftReference;", null, null);
            this.generateCreateCallSiteArray();
            this.generateGetCallSiteArray();
        }
    }
    
    private void generateGetCallSiteArray() {
        final int visibility = (this.classNode instanceof InterfaceHelperClassNode) ? 1 : 2;
        final MethodVisitor mv = this.cv.visitMethod(visibility + 4096 + 8, "$getCallSiteArray", "()[Lorg/codehaus/groovy/runtime/callsite/CallSite;", null, null);
        mv.visitCode();
        mv.visitFieldInsn(178, this.internalClassName, "$callSiteArray", "Ljava/lang/ref/SoftReference;");
        final Label l0 = new Label();
        mv.visitJumpInsn(198, l0);
        mv.visitFieldInsn(178, this.internalClassName, "$callSiteArray", "Ljava/lang/ref/SoftReference;");
        mv.visitMethodInsn(182, "java/lang/ref/SoftReference", "get", "()Ljava/lang/Object;");
        mv.visitTypeInsn(192, "org/codehaus/groovy/runtime/callsite/CallSiteArray");
        mv.visitInsn(89);
        mv.visitVarInsn(58, 0);
        final Label l2 = new Label();
        mv.visitJumpInsn(199, l2);
        mv.visitLabel(l0);
        mv.visitMethodInsn(184, this.internalClassName, "$createCallSiteArray", "()Lorg/codehaus/groovy/runtime/callsite/CallSiteArray;");
        mv.visitVarInsn(58, 0);
        mv.visitTypeInsn(187, "java/lang/ref/SoftReference");
        mv.visitInsn(89);
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, "java/lang/ref/SoftReference", "<init>", "(Ljava/lang/Object;)V");
        mv.visitFieldInsn(179, this.internalClassName, "$callSiteArray", "Ljava/lang/ref/SoftReference;");
        mv.visitLabel(l2);
        mv.visitVarInsn(25, 0);
        mv.visitFieldInsn(180, "org/codehaus/groovy/runtime/callsite/CallSiteArray", "array", "[Lorg/codehaus/groovy/runtime/callsite/CallSite;");
        mv.visitInsn(176);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }
    
    private void generateCreateCallSiteArray() {
        final List<String> callSiteInitMethods = new LinkedList<String>();
        int index = 0;
        int methodIndex = 0;
        final int size = this.callSites.size();
        final int maxArrayInit = 5000;
        while (index < size) {
            ++methodIndex;
            final String methodName = "$createCallSiteArray_" + methodIndex;
            callSiteInitMethods.add(methodName);
            final MethodVisitor mv = this.cv.visitMethod(4106, methodName, "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            int methodLimit = size;
            if (methodLimit - index > 5000) {
                methodLimit = index + 5000;
            }
            while (index < methodLimit) {
                mv.visitVarInsn(25, 0);
                mv.visitLdcInsn(index);
                mv.visitLdcInsn(this.callSites.get(index));
                mv.visitInsn(83);
                ++index;
            }
            mv.visitInsn(177);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        (this.mv = this.cv.visitMethod(4106, "$createCallSiteArray", "()Lorg/codehaus/groovy/runtime/callsite/CallSiteArray;", null, null)).visitCode();
        this.mv.visitLdcInsn(size);
        this.mv.visitTypeInsn(189, "java/lang/String");
        this.mv.visitVarInsn(58, 0);
        for (final String methodName2 : callSiteInitMethods) {
            this.mv.visitVarInsn(25, 0);
            this.mv.visitMethodInsn(184, this.internalClassName, methodName2, "([Ljava/lang/String;)V");
        }
        this.mv.visitTypeInsn(187, "org/codehaus/groovy/runtime/callsite/CallSiteArray");
        this.mv.visitInsn(89);
        this.visitClassExpression(new ClassExpression(this.classNode));
        this.mv.visitVarInsn(25, 0);
        this.mv.visitMethodInsn(183, "org/codehaus/groovy/runtime/callsite/CallSiteArray", "<init>", "(Ljava/lang/Class;[Ljava/lang/String;)V");
        this.mv.visitInsn(176);
        this.mv.visitMaxs(0, 0);
        this.mv.visitEnd();
    }
    
    public void visitGenericType(final GenericsType genericsType) {
        final ClassNode type = genericsType.getType();
        this.genericParameterNames.put(type.getName(), genericsType);
    }
    
    private void createMopMethods() {
        this.visitMopMethodList(this.classNode.getMethods(), true);
        this.visitMopMethodList(this.classNode.getSuperClass().getAllDeclaredMethods(), false);
    }
    
    private String[] buildExceptions(final ClassNode[] exceptions) {
        if (exceptions == null) {
            return null;
        }
        final String[] ret = new String[exceptions.length];
        for (int i = 0; i < exceptions.length; ++i) {
            ret[i] = BytecodeHelper.getClassInternalName(exceptions[i]);
        }
        return ret;
    }
    
    private void visitMopMethodList(final List methods, final boolean isThis) {
        class Key
        {
            int hash;
            String name = methodName;
            Parameter[] params = mn.getParameters();
            
            Key(final Parameter[] params) {
                this.hash = 0;
                this.hash = name.hashCode() << 2 + params.length;
            }
            
            @Override
            public int hashCode() {
                return this.hash;
            }
            
            @Override
            public boolean equals(final Object obj) {
                final Key other = (Key)obj;
                return other.name.equals(this.name) && AsmClassGenerator.this.equalParameterTypes(other.params, this.params);
            }
        }
        final HashMap<Key, MethodNode> mops = new HashMap<Key, MethodNode>();
        final LinkedList<MethodNode> mopCalls = new LinkedList<MethodNode>();
        for (final Object method : methods) {
            final MethodNode mn = (MethodNode)method;
            if ((mn.getModifiers() & 0x400) != 0x0) {
                continue;
            }
            if (mn.isStatic()) {
                continue;
            }
            if (isThis ^ (mn.getModifiers() & 0x5) == 0x0) {
                continue;
            }
            final String methodName = mn.getName();
            if (isMopMethod(methodName)) {
                mops.put(new Key(mn.getParameters()), mn);
            }
            else {
                if (methodName.startsWith("<")) {
                    continue;
                }
                final String name = getMopMethodName(mn, isThis);
                final Key key = new Key(mn.getParameters());
                if (mops.containsKey(key)) {
                    continue;
                }
                mops.put(key, mn);
                mopCalls.add(mn);
            }
        }
        this.generateMopCalls(mopCalls, isThis);
        mopCalls.clear();
        mops.clear();
    }
    
    private boolean equalParameterTypes(final Parameter[] p1, final Parameter[] p2) {
        if (p1.length != p2.length) {
            return false;
        }
        for (int i = 0; i < p1.length; ++i) {
            if (!p1[i].getType().equals(p2[i].getType())) {
                return false;
            }
        }
        return true;
    }
    
    private void generateMopCalls(final LinkedList<MethodNode> mopCalls, final boolean useThis) {
        for (final MethodNode method : mopCalls) {
            final String name = getMopMethodName(method, useThis);
            final Parameter[] parameters = method.getParameters();
            final String methodDescriptor = BytecodeHelper.getMethodDescriptor(method.getReturnType(), method.getParameters());
            (this.mv = this.cv.visitMethod(4097, name, methodDescriptor, null, null)).visitVarInsn(25, 0);
            int newRegister = 1;
            final BytecodeHelper helper = new BytecodeHelper(this.mv);
            for (final Parameter parameter : parameters) {
                final ClassNode type = parameter.getType();
                helper.load(parameter.getType(), newRegister);
                ++newRegister;
                if (type == ClassHelper.double_TYPE || type == ClassHelper.long_TYPE) {
                    ++newRegister;
                }
            }
            this.mv.visitMethodInsn(183, BytecodeHelper.getClassInternalName(method.getDeclaringClass()), method.getName(), methodDescriptor);
            helper.doReturn(method.getReturnType());
            this.mv.visitMaxs(0, 0);
            this.mv.visitEnd();
            this.classNode.addMethod(name, 4097, method.getReturnType(), parameters, null, null);
        }
    }
    
    public static String getMopMethodName(final MethodNode method, final boolean useThis) {
        ClassNode declaringNode = method.getDeclaringClass();
        int distance = 0;
        while (declaringNode != null) {
            ++distance;
            declaringNode = declaringNode.getSuperClass();
        }
        return (useThis ? "this" : "super") + "$" + distance + "$" + method.getName();
    }
    
    public static boolean isMopMethod(final String methodName) {
        return (methodName.startsWith("this$") || methodName.startsWith("super$")) && !methodName.contains("$dist$");
    }
    
    @Override
    protected void visitConstructorOrMethod(final MethodNode node, final boolean isConstructor) {
        this.lineNumber = -1;
        final Parameter[] parameters = node.getParameters();
        final String methodType = BytecodeHelper.getMethodDescriptor(node.getReturnType(), parameters);
        final String signature = BytecodeHelper.getGenericsMethodSignature(node);
        int modifiers = node.getModifiers();
        if (this.isVargs(node.getParameters())) {
            modifiers |= 0x80;
        }
        this.mv = this.cv.visitMethod(modifiers, node.getName(), methodType, signature, this.buildExceptions(node.getExceptions()));
        this.visitAnnotations(node, this.mv = new MyMethodAdapter());
        for (int i = 0; i < parameters.length; ++i) {
            this.visitParameterAnnotations(parameters[i], i, this.mv);
        }
        this.helper = new BytecodeHelper(this.mv);
        if (this.classNode.isAnnotationDefinition() && !node.isStaticConstructor()) {
            this.visitAnnotationDefault(node, this.mv);
        }
        else if (!node.isAbstract()) {
            final Statement code = node.getCode();
            if (code instanceof BytecodeSequence && ((BytecodeSequence)code).getInstructions().size() == 1 && ((BytecodeSequence)code).getInstructions().get(0) instanceof BytecodeInstruction) {
                ((BytecodeSequence)code).getInstructions().get(0).visit(this.mv);
            }
            else {
                this.visitStdMethod(node, isConstructor, parameters, code);
            }
            this.mv.visitInsn(0);
            this.mv.visitMaxs(0, 0);
        }
        this.mv.visitEnd();
    }
    
    private void visitStdMethod(final MethodNode node, final boolean isConstructor, final Parameter[] parameters, final Statement code) {
        if (isConstructor && (code == null || !((ConstructorNode)node).firstStatementIsSpecialConstructorCall())) {
            this.mv.visitVarInsn(25, 0);
            this.mv.visitMethodInsn(183, BytecodeHelper.getClassInternalName(this.classNode.getSuperClass()), "<init>", "()V");
        }
        this.compileStack.init(node.getVariableScope(), parameters, this.mv, this.classNode);
        if (this.isNotClinit()) {
            this.mv.visitMethodInsn(184, this.internalClassName, "$getCallSiteArray", "()[Lorg/codehaus/groovy/runtime/callsite/CallSite;");
            this.callSiteArrayVarIndex = this.compileStack.defineTemporaryVariable("$local$callSiteArray", ClassHelper.make(CallSite[].class), true);
        }
        super.visitConstructorOrMethod(node, isConstructor);
        if (!this.outputReturn || node.isVoidMethod()) {
            this.mv.visitInsn(177);
        }
        this.compileStack.clear();
    }
    
    void visitAnnotationDefaultExpression(final AnnotationVisitor av, final ClassNode type, final Expression exp) {
        if (type.isArray()) {
            final ListExpression list = (ListExpression)exp;
            final AnnotationVisitor avl = av.visitArray(null);
            final ClassNode componentType = type.getComponentType();
            for (final Expression lExp : list.getExpressions()) {
                this.visitAnnotationDefaultExpression(avl, componentType, lExp);
            }
        }
        else if (ClassHelper.isPrimitiveType(type) || type.equals(ClassHelper.STRING_TYPE)) {
            final ConstantExpression constExp = (ConstantExpression)exp;
            av.visit(null, constExp.getValue());
        }
        else if (ClassHelper.CLASS_Type.equals(type)) {
            final ClassNode clazz = exp.getType();
            final Type t = Type.getType(BytecodeHelper.getTypeDescription(clazz));
            av.visit(null, t);
        }
        else if (type.isDerivedFrom(ClassHelper.Enum_Type)) {
            final PropertyExpression pExp = (PropertyExpression)exp;
            final ClassExpression cExp = (ClassExpression)pExp.getObjectExpression();
            final String desc = BytecodeHelper.getTypeDescription(cExp.getType());
            final String name = pExp.getPropertyAsString();
            av.visitEnum(null, desc, name);
        }
        else {
            if (!type.implementsInterface(ClassHelper.Annotation_TYPE)) {
                throw new GroovyBugError("unexpected annotation type " + type.getName());
            }
            final AnnotationConstantExpression avExp = (AnnotationConstantExpression)exp;
            final AnnotationNode value = (AnnotationNode)avExp.getValue();
            final AnnotationVisitor avc = av.visitAnnotation(null, BytecodeHelper.getTypeDescription(avExp.getType()));
            this.visitAnnotationAttributes(value, avc);
        }
        av.visitEnd();
    }
    
    private void visitAnnotationDefault(final MethodNode node, final MethodVisitor mv) {
        if (!node.hasAnnotationDefault()) {
            return;
        }
        final Expression exp = ((ReturnStatement)node.getCode()).getExpression();
        final AnnotationVisitor av = mv.visitAnnotationDefault();
        this.visitAnnotationDefaultExpression(av, node.getReturnType(), exp);
    }
    
    private boolean isNotClinit() {
        return this.methodNode == null || !this.methodNode.getName().equals("<clinit>");
    }
    
    private boolean isVargs(final Parameter[] p) {
        if (p.length == 0) {
            return false;
        }
        final ClassNode clazz = p[p.length - 1].getType();
        return clazz.isArray();
    }
    
    @Override
    public void visitConstructor(final ConstructorNode node) {
        this.constructorNode = node;
        this.methodNode = null;
        this.outputReturn = false;
        super.visitConstructor(node);
    }
    
    @Override
    public void visitMethod(final MethodNode node) {
        this.constructorNode = null;
        this.methodNode = node;
        this.outputReturn = false;
        super.visitMethod(node);
    }
    
    @Override
    public void visitField(final FieldNode fieldNode) {
        this.onLineNumber(fieldNode, "visitField: " + fieldNode.getName());
        final ClassNode t = fieldNode.getType();
        final String signature = BytecodeHelper.getGenericsBounds(t);
        final FieldVisitor fv = this.cv.visitField(fieldNode.getModifiers(), fieldNode.getName(), BytecodeHelper.getTypeDescription(t), signature, null);
        this.visitAnnotations(fieldNode, fv);
        fv.visitEnd();
    }
    
    @Override
    public void visitProperty(final PropertyNode statement) {
        this.onLineNumber(statement, "visitProperty:" + statement.getField().getName());
        this.methodNode = null;
    }
    
    @Override
    protected void visitStatement(final Statement statement) {
        final String name = statement.getStatementLabel();
        if (name != null) {
            final Label label = this.compileStack.createLocalLabel(name);
            this.mv.visitLabel(label);
        }
    }
    
    @Override
    public void visitBlockStatement(final BlockStatement block) {
        this.visitStatement(block);
        this.compileStack.pushVariableScope(block.getVariableScope());
        super.visitBlockStatement(block);
        this.compileStack.pop();
    }
    
    private void visitExpressionOrStatement(final Object o) {
        if (o == EmptyExpression.INSTANCE) {
            return;
        }
        if (o instanceof Expression) {
            final Expression expr = (Expression)o;
            this.visitAndAutoboxBoolean(expr);
            if (this.isPopRequired(expr)) {
                this.mv.visitInsn(87);
            }
        }
        else {
            ((Statement)o).visit(this);
        }
    }
    
    private void visitForLoopWithClosureList(final ForStatement loop) {
        this.compileStack.pushLoop(loop.getVariableScope(), loop.getStatementLabel());
        final ClosureListExpression clExpr = (ClosureListExpression)loop.getCollectionExpression();
        this.compileStack.pushVariableScope(clExpr.getVariableScope());
        final List expressions = clExpr.getExpressions();
        final int size = expressions.size();
        final int condIndex = (size - 1) / 2;
        for (int i = 0; i < condIndex; ++i) {
            this.visitExpressionOrStatement(expressions.get(i));
        }
        final Label continueLabel = this.compileStack.getContinueLabel();
        final Label breakLabel = this.compileStack.getBreakLabel();
        final Label cond = new Label();
        this.mv.visitLabel(cond);
        final Expression condExpr = expressions.get(condIndex);
        if (condExpr == EmptyExpression.INSTANCE) {
            this.mv.visitIntInsn(16, 1);
        }
        else if (this.isComparisonExpression(condExpr)) {
            condExpr.visit(this);
        }
        else {
            this.visitAndAutoboxBoolean(condExpr);
            this.helper.unbox(ClassHelper.boolean_TYPE);
        }
        this.mv.visitJumpInsn(153, breakLabel);
        loop.getLoopBlock().visit(this);
        this.mv.visitLabel(continueLabel);
        for (int j = condIndex + 1; j < size; ++j) {
            this.visitExpressionOrStatement(expressions.get(j));
        }
        this.mv.visitJumpInsn(167, cond);
        this.mv.visitLabel(breakLabel);
        this.compileStack.pop();
        this.compileStack.pop();
    }
    
    @Override
    public void visitForLoop(final ForStatement loop) {
        this.onLineNumber(loop, "visitForLoop");
        this.visitStatement(loop);
        final Parameter loopVar = loop.getVariable();
        if (loopVar == ForStatement.FOR_LOOP_DUMMY) {
            this.visitForLoopWithClosureList(loop);
            return;
        }
        this.compileStack.pushLoop(loop.getVariableScope(), loop.getStatementLabel());
        final org.codehaus.groovy.classgen.Variable variable = this.compileStack.defineVariable(loop.getVariable(), false);
        final MethodCallExpression iterator = new MethodCallExpression(loop.getCollectionExpression(), "iterator", new ArgumentListExpression());
        iterator.visit(this);
        final int iteratorIdx = this.compileStack.defineTemporaryVariable("iterator", ClassHelper.make(Iterator.class), true);
        final Label continueLabel = this.compileStack.getContinueLabel();
        final Label breakLabel = this.compileStack.getBreakLabel();
        this.mv.visitLabel(continueLabel);
        this.mv.visitVarInsn(25, iteratorIdx);
        AsmClassGenerator.iteratorHasNextMethod.call(this.mv);
        this.mv.visitJumpInsn(153, breakLabel);
        this.mv.visitVarInsn(25, iteratorIdx);
        AsmClassGenerator.iteratorNextMethod.call(this.mv);
        this.helper.storeVar(variable);
        loop.getLoopBlock().visit(this);
        this.mv.visitJumpInsn(167, continueLabel);
        this.mv.visitLabel(breakLabel);
        this.compileStack.pop();
    }
    
    @Override
    public void visitWhileLoop(final WhileStatement loop) {
        this.onLineNumber(loop, "visitWhileLoop");
        this.visitStatement(loop);
        this.compileStack.pushLoop(loop.getStatementLabel());
        final Label continueLabel = this.compileStack.getContinueLabel();
        final Label breakLabel = this.compileStack.getBreakLabel();
        this.mv.visitLabel(continueLabel);
        final Expression bool = loop.getBooleanExpression();
        boolean boolHandled = false;
        if (bool instanceof ConstantExpression) {
            final ConstantExpression constant = (ConstantExpression)bool;
            if (constant.getValue() == Boolean.TRUE) {
                boolHandled = true;
            }
            else if (constant.getValue() == Boolean.FALSE) {
                boolHandled = true;
                this.mv.visitJumpInsn(167, breakLabel);
            }
        }
        if (!boolHandled) {
            bool.visit(this);
            this.mv.visitJumpInsn(153, breakLabel);
        }
        loop.getLoopBlock().visit(this);
        this.mv.visitJumpInsn(167, continueLabel);
        this.mv.visitLabel(breakLabel);
        this.compileStack.pop();
    }
    
    @Override
    public void visitDoWhileLoop(final DoWhileStatement loop) {
        this.onLineNumber(loop, "visitDoWhileLoop");
        this.visitStatement(loop);
        this.compileStack.pushLoop(loop.getStatementLabel());
        final Label breakLabel = this.compileStack.getBreakLabel();
        final Label continueLabel = this.compileStack.getContinueLabel();
        this.mv.visitLabel(continueLabel);
        loop.getLoopBlock().visit(this);
        loop.getBooleanExpression().visit(this);
        this.mv.visitJumpInsn(153, continueLabel);
        this.mv.visitLabel(breakLabel);
        this.compileStack.pop();
    }
    
    @Override
    public void visitIfElse(final IfStatement ifElse) {
        this.onLineNumber(ifElse, "visitIfElse");
        this.visitStatement(ifElse);
        ifElse.getBooleanExpression().visit(this);
        final Label l0 = new Label();
        this.mv.visitJumpInsn(153, l0);
        this.compileStack.pushBooleanExpression();
        ifElse.getIfBlock().visit(this);
        this.compileStack.pop();
        final Label l2 = new Label();
        this.mv.visitJumpInsn(167, l2);
        this.mv.visitLabel(l0);
        this.compileStack.pushBooleanExpression();
        ifElse.getElseBlock().visit(this);
        this.compileStack.pop();
        this.mv.visitLabel(l2);
    }
    
    @Override
    public void visitTernaryExpression(final TernaryExpression expression) {
        this.onLineNumber(expression, "visitTernaryExpression");
        BooleanExpression boolPart = expression.getBooleanExpression();
        Expression truePart = expression.getTrueExpression();
        Expression falsePart = expression.getFalseExpression();
        if (expression instanceof ElvisOperatorExpression) {
            this.visitAndAutoboxBoolean(expression.getTrueExpression());
            boolPart = new BooleanExpression(new BytecodeExpression() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitInsn(89);
                }
            });
            truePart = BytecodeExpression.NOP;
            final Expression oldFalse = falsePart;
            falsePart = new BytecodeExpression() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitInsn(87);
                    AsmClassGenerator.this.visitAndAutoboxBoolean(oldFalse);
                }
            };
        }
        boolPart.visit(this);
        final Label l0 = new Label();
        this.mv.visitJumpInsn(153, l0);
        this.compileStack.pushBooleanExpression();
        this.visitAndAutoboxBoolean(truePart);
        this.compileStack.pop();
        final Label l2 = new Label();
        this.mv.visitJumpInsn(167, l2);
        this.mv.visitLabel(l0);
        this.compileStack.pushBooleanExpression();
        this.visitAndAutoboxBoolean(falsePart);
        this.compileStack.pop();
        this.mv.visitLabel(l2);
    }
    
    @Override
    public void visitAssertStatement(final AssertStatement statement) {
        this.onLineNumber(statement, "visitAssertStatement");
        this.visitStatement(statement);
        boolean rewriteAssert = true;
        rewriteAssert = (statement.getMessageExpression() == ConstantExpression.NULL);
        final AssertionTracker oldTracker = this.assertionTracker;
        final Janitor janitor = new Janitor();
        final Label tryStart = new Label();
        if (rewriteAssert) {
            this.assertionTracker = new AssertionTracker();
            try {
                this.assertionTracker.sourceText = new SourceText(statement, this.source, janitor);
                this.mv.visitTypeInsn(187, "org/codehaus/groovy/transform/powerassert/ValueRecorder");
                this.mv.visitInsn(89);
                this.mv.visitMethodInsn(183, "org/codehaus/groovy/transform/powerassert/ValueRecorder", "<init>", "()V");
                this.assertionTracker.recorderIndex = this.compileStack.defineTemporaryVariable("recorder", true);
                this.mv.visitLabel(tryStart);
            }
            catch (SourceTextNotAvailableException e) {
                rewriteAssert = false;
                this.assertionTracker = oldTracker;
            }
        }
        final BooleanExpression booleanExpression = statement.getBooleanExpression();
        booleanExpression.visit(this);
        final Label exceptionThrower = new Label();
        this.mv.visitJumpInsn(153, exceptionThrower);
        if (rewriteAssert) {
            this.mv.visitVarInsn(25, this.assertionTracker.recorderIndex);
            this.mv.visitMethodInsn(182, "org/codehaus/groovy/transform/powerassert/ValueRecorder", "clear", "()V");
        }
        final Label afterAssert = new Label();
        this.mv.visitJumpInsn(167, afterAssert);
        this.mv.visitLabel(exceptionThrower);
        if (rewriteAssert) {
            this.mv.visitLdcInsn(this.assertionTracker.sourceText.getNormalizedText());
            this.mv.visitVarInsn(25, this.assertionTracker.recorderIndex);
            this.mv.visitMethodInsn(184, "org/codehaus/groovy/transform/powerassert/AssertionRenderer", "render", "(Ljava/lang/String;Lorg/codehaus/groovy/transform/powerassert/ValueRecorder;)Ljava/lang/String;");
        }
        else {
            this.writeSourclessAssertText(statement);
        }
        final AssertionTracker savedTracker = this.assertionTracker;
        this.assertionTracker = null;
        this.visitAndAutoboxBoolean(statement.getMessageExpression());
        AsmClassGenerator.assertFailedMethod.call(this.mv);
        if (rewriteAssert) {
            final Label tryEnd = new Label();
            this.mv.visitLabel(tryEnd);
            this.mv.visitJumpInsn(167, afterAssert);
            final Label catchAny = new Label();
            this.mv.visitLabel(catchAny);
            this.mv.visitVarInsn(25, savedTracker.recorderIndex);
            this.mv.visitMethodInsn(182, "org/codehaus/groovy/transform/powerassert/ValueRecorder", "clear", "()V");
            this.mv.visitInsn(191);
            this.compileStack.addExceptionBlock(tryStart, tryEnd, catchAny, null);
        }
        this.mv.visitLabel(afterAssert);
        this.assertionTracker = oldTracker;
        janitor.cleanup();
    }
    
    private void writeSourclessAssertText(final AssertStatement statement) {
        final BooleanExpression booleanExpression = statement.getBooleanExpression();
        final String expressionText = booleanExpression.getText();
        final List<String> list = new ArrayList<String>();
        this.addVariableNames(booleanExpression, list);
        if (list.isEmpty()) {
            this.mv.visitLdcInsn(expressionText);
        }
        else {
            boolean first = true;
            this.mv.visitTypeInsn(187, "java/lang/StringBuffer");
            this.mv.visitInsn(89);
            this.mv.visitLdcInsn(expressionText + ". Values: ");
            this.mv.visitMethodInsn(183, "java/lang/StringBuffer", "<init>", "(Ljava/lang/String;)V");
            final int tempIndex = this.compileStack.defineTemporaryVariable("assert", true);
            for (final String name : list) {
                String text = name + " = ";
                if (first) {
                    first = false;
                }
                else {
                    text = ", " + text;
                }
                this.mv.visitVarInsn(25, tempIndex);
                this.mv.visitLdcInsn(text);
                this.mv.visitMethodInsn(182, "java/lang/StringBuffer", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuffer;");
                this.mv.visitInsn(87);
                this.mv.visitVarInsn(25, tempIndex);
                new VariableExpression(name).visit(this);
                this.mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/InvokerHelper", "toString", "(Ljava/lang/Object;)Ljava/lang/String;");
                this.mv.visitMethodInsn(182, "java/lang/StringBuffer", "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;");
                this.mv.visitInsn(87);
            }
            this.mv.visitVarInsn(25, tempIndex);
            this.compileStack.removeVar(tempIndex);
        }
    }
    
    private void addVariableNames(final Expression expression, final List<String> list) {
        if (expression instanceof BooleanExpression) {
            final BooleanExpression boolExp = (BooleanExpression)expression;
            this.addVariableNames(boolExp.getExpression(), list);
        }
        else if (expression instanceof BinaryExpression) {
            final BinaryExpression binExp = (BinaryExpression)expression;
            this.addVariableNames(binExp.getLeftExpression(), list);
            this.addVariableNames(binExp.getRightExpression(), list);
        }
        else if (expression instanceof VariableExpression) {
            final VariableExpression varExp = (VariableExpression)expression;
            list.add(varExp.getName());
        }
    }
    
    @Override
    public void visitTryCatchFinally(final TryCatchStatement statement) {
        this.visitStatement(statement);
        final Statement tryStatement = statement.getTryStatement();
        final Statement finallyStatement = statement.getFinallyStatement();
        final Label tryStart = new Label();
        this.mv.visitLabel(tryStart);
        final CompileStack.BlockRecorder tryBlock = this.makeBlockRecorder(finallyStatement);
        tryBlock.startRange(tryStart);
        tryStatement.visit(this);
        final Label finallyStart = new Label();
        this.mv.visitJumpInsn(167, finallyStart);
        final Label tryEnd = new Label();
        this.mv.visitLabel(tryEnd);
        tryBlock.closeRange(tryEnd);
        this.compileStack.pop();
        final CompileStack.BlockRecorder catches = this.makeBlockRecorder(finallyStatement);
        for (final CatchStatement catchStatement : statement.getCatchStatements()) {
            final ClassNode exceptionType = catchStatement.getExceptionType();
            final String exceptionTypeInternalName = BytecodeHelper.getClassInternalName(exceptionType);
            final Label catchStart = new Label();
            this.mv.visitLabel(catchStart);
            catches.startRange(catchStart);
            this.compileStack.pushState();
            this.compileStack.defineVariable(catchStatement.getVariable(), true);
            catchStatement.visit(this);
            this.mv.visitInsn(0);
            this.compileStack.pop();
            final Label catchEnd = new Label();
            this.mv.visitLabel(catchEnd);
            catches.closeRange(catchEnd);
            this.mv.visitJumpInsn(167, finallyStart);
            this.compileStack.writeExceptionTable(tryBlock, catchStart, exceptionTypeInternalName);
        }
        final Label catchAny = new Label();
        this.compileStack.writeExceptionTable(tryBlock, catchAny, null);
        this.compileStack.writeExceptionTable(catches, catchAny, null);
        this.compileStack.pop();
        this.mv.visitLabel(finallyStart);
        finallyStatement.visit(this);
        this.mv.visitInsn(0);
        final Label skipCatchAll = new Label();
        this.mv.visitJumpInsn(167, skipCatchAll);
        this.mv.visitLabel(catchAny);
        final int anyExceptionIndex = this.compileStack.defineTemporaryVariable("exception", true);
        finallyStatement.visit(this);
        this.mv.visitVarInsn(25, anyExceptionIndex);
        this.mv.visitInsn(191);
        this.mv.visitLabel(skipCatchAll);
    }
    
    private CompileStack.BlockRecorder makeBlockRecorder(final Statement finallyStatement) {
        final CompileStack.BlockRecorder block = new CompileStack.BlockRecorder();
        final Runnable tryRunner = new Runnable() {
            public void run() {
                AsmClassGenerator.this.compileStack.pushBlockRecorderVisit(block);
                finallyStatement.visit(AsmClassGenerator.this);
                AsmClassGenerator.this.compileStack.popBlockRecorderVisit(block);
            }
        };
        block.excludedStatement = tryRunner;
        this.compileStack.pushBlockRecorder(block);
        return block;
    }
    
    @Override
    public void visitSwitch(final SwitchStatement statement) {
        this.onLineNumber(statement, "visitSwitch");
        this.visitStatement(statement);
        statement.getExpression().visit(this);
        final Label breakLabel = this.compileStack.pushSwitch();
        final int switchVariableIndex = this.compileStack.defineTemporaryVariable("switch", true);
        final List caseStatements = statement.getCaseStatements();
        final int caseCount = caseStatements.size();
        final Label[] labels = new Label[caseCount + 1];
        for (int i = 0; i < caseCount; ++i) {
            labels[i] = new Label();
        }
        int i = 0;
        for (final CaseStatement caseStatement : caseStatements) {
            this.visitCaseStatement(caseStatement, switchVariableIndex, labels[i], labels[i + 1]);
            ++i;
        }
        statement.getDefaultStatement().visit(this);
        this.mv.visitLabel(breakLabel);
        this.compileStack.pop();
    }
    
    @Override
    public void visitCaseStatement(final CaseStatement statement) {
    }
    
    public void visitCaseStatement(final CaseStatement statement, final int switchVariableIndex, final Label thisLabel, final Label nextLabel) {
        this.onLineNumber(statement, "visitCaseStatement");
        this.mv.visitVarInsn(25, switchVariableIndex);
        statement.getExpression().visit(this);
        AsmClassGenerator.isCaseMethod.call(this.mv);
        final Label l0 = new Label();
        this.mv.visitJumpInsn(153, l0);
        this.mv.visitLabel(thisLabel);
        statement.getCode().visit(this);
        if (nextLabel != null) {
            this.mv.visitJumpInsn(167, nextLabel);
        }
        this.mv.visitLabel(l0);
    }
    
    @Override
    public void visitBreakStatement(final BreakStatement statement) {
        this.onLineNumber(statement, "visitBreakStatement");
        this.visitStatement(statement);
        final String name = statement.getLabel();
        final Label breakLabel = this.compileStack.getNamedBreakLabel(name);
        this.compileStack.applyFinallyBlocks(breakLabel, true);
        this.mv.visitJumpInsn(167, breakLabel);
    }
    
    @Override
    public void visitContinueStatement(final ContinueStatement statement) {
        this.onLineNumber(statement, "visitContinueStatement");
        this.visitStatement(statement);
        final String name = statement.getLabel();
        Label continueLabel = this.compileStack.getContinueLabel();
        if (name != null) {
            continueLabel = this.compileStack.getNamedContinueLabel(name);
        }
        this.compileStack.applyFinallyBlocks(continueLabel, false);
        this.mv.visitJumpInsn(167, continueLabel);
    }
    
    @Override
    public void visitSynchronizedStatement(final SynchronizedStatement statement) {
        this.onLineNumber(statement, "visitSynchronizedStatement");
        this.visitStatement(statement);
        statement.getExpression().visit(this);
        final int index = this.compileStack.defineTemporaryVariable("synchronized", ClassHelper.Integer_TYPE, true);
        final Label synchronizedStart = new Label();
        final Label synchronizedEnd = new Label();
        final Label catchAll = new Label();
        this.mv.visitVarInsn(25, index);
        this.mv.visitInsn(194);
        this.mv.visitLabel(synchronizedStart);
        this.mv.visitInsn(0);
        final Runnable finallyPart = new Runnable() {
            public void run() {
                AsmClassGenerator.this.mv.visitVarInsn(25, index);
                AsmClassGenerator.this.mv.visitInsn(195);
            }
        };
        final CompileStack.BlockRecorder fb = new CompileStack.BlockRecorder(finallyPart);
        fb.startRange(synchronizedStart);
        this.compileStack.pushBlockRecorder(fb);
        statement.getCode().visit(this);
        fb.closeRange(catchAll);
        this.compileStack.writeExceptionTable(fb, catchAll, null);
        this.compileStack.pop();
        finallyPart.run();
        this.mv.visitJumpInsn(167, synchronizedEnd);
        this.mv.visitLabel(catchAll);
        finallyPart.run();
        this.mv.visitInsn(191);
        this.mv.visitLabel(synchronizedEnd);
    }
    
    @Override
    public void visitThrowStatement(final ThrowStatement statement) {
        this.onLineNumber(statement, "visitThrowStatement");
        this.visitStatement(statement);
        statement.getExpression().visit(this);
        this.mv.visitTypeInsn(192, "java/lang/Throwable");
        this.mv.visitInsn(191);
    }
    
    @Override
    public void visitReturnStatement(final ReturnStatement statement) {
        this.onLineNumber(statement, "visitReturnStatement");
        this.visitStatement(statement);
        ClassNode returnType;
        if (this.methodNode != null) {
            returnType = this.methodNode.getReturnType();
        }
        else {
            if (this.constructorNode == null) {
                throw new GroovyBugError("I spotted a return that is neither in a method nor in a constructor... I can not handle that");
            }
            returnType = this.constructorNode.getReturnType();
        }
        if (returnType == ClassHelper.VOID_TYPE) {
            if (!statement.isReturningNullOrVoid()) {
                this.throwException("Cannot use return statement with an expression on a method that returns void");
            }
            this.compileStack.applyBlockRecorder();
            this.mv.visitInsn(177);
            this.outputReturn = true;
            return;
        }
        final Expression expression = statement.getExpression();
        this.evaluateExpression(expression);
        if (returnType == ClassHelper.OBJECT_TYPE && expression.getType() != null && expression.getType() == ClassHelper.VOID_TYPE) {
            this.mv.visitInsn(1);
        }
        else {
            this.doConvertAndCast(returnType, expression, false, true, false);
        }
        if (this.compileStack.hasBlockRecorder()) {
            final int returnValueIdx = this.compileStack.defineTemporaryVariable("returnValue", ClassHelper.OBJECT_TYPE, true);
            this.compileStack.applyBlockRecorder();
            this.helper.load(ClassHelper.OBJECT_TYPE, returnValueIdx);
        }
        this.helper.unbox(returnType);
        this.helper.doReturn(returnType);
        this.outputReturn = true;
    }
    
    protected void doConvertAndCast(ClassNode type, final Expression expression, final boolean ignoreAutoboxing, final boolean forceCast, final boolean coerce) {
        final ClassNode expType = this.getExpressionType(expression);
        if (!ignoreAutoboxing && ClassHelper.isPrimitiveType(type)) {
            type = ClassHelper.getWrapper(type);
        }
        if (forceCast || (type != null && !expType.isDerivedFrom(type) && !expType.implementsInterface(type))) {
            this.doConvertAndCast(type, coerce);
        }
    }
    
    protected void evaluateExpression(final Expression expression) {
        this.visitAndAutoboxBoolean(expression);
        if (this.isPopRequired(expression)) {
            return;
        }
        final Expression assignExpr = this.createReturnLHSExpression(expression);
        if (assignExpr != null) {
            this.leftHandExpression = false;
            assignExpr.visit(this);
        }
    }
    
    @Override
    public void visitExpressionStatement(final ExpressionStatement statement) {
        this.onLineNumber(statement, "visitExpressionStatement: " + statement.getExpression().getClass().getName());
        this.visitStatement(statement);
        final Expression expression = statement.getExpression();
        this.visitAndAutoboxBoolean(expression);
        if (this.isPopRequired(expression)) {
            this.mv.visitInsn(87);
        }
    }
    
    @Override
    public void visitDeclarationExpression(final DeclarationExpression expression) {
        this.onLineNumber(expression, "visitDeclarationExpression: \"" + expression.getText() + "\"");
        this.evaluateEqual(expression, true);
    }
    
    @Override
    public void visitBinaryExpression(final BinaryExpression expression) {
        this.onLineNumber(expression, "visitBinaryExpression: \"" + expression.getOperation().getText() + "\" ");
        switch (expression.getOperation().getType()) {
            case 100: {
                this.evaluateEqual(expression, false);
                break;
            }
            case 121:
            case 122: {
                this.source.addError(new SyntaxException("Operators === and !== are not supported. Use this.is(that) instead", expression.getLineNumber(), expression.getColumnNumber()));
                break;
            }
            case 123: {
                this.evaluateBinaryExpression(AsmClassGenerator.compareEqualMethod, expression);
                break;
            }
            case 120: {
                this.evaluateBinaryExpression(AsmClassGenerator.compareNotEqualMethod, expression);
                break;
            }
            case 128: {
                this.evaluateCompareTo(expression);
                break;
            }
            case 126: {
                this.evaluateBinaryExpression(AsmClassGenerator.compareGreaterThanMethod, expression);
                break;
            }
            case 127: {
                this.evaluateBinaryExpression(AsmClassGenerator.compareGreaterThanEqualMethod, expression);
                break;
            }
            case 124: {
                this.evaluateBinaryExpression(AsmClassGenerator.compareLessThanMethod, expression);
                break;
            }
            case 125: {
                this.evaluateBinaryExpression(AsmClassGenerator.compareLessThanEqualMethod, expression);
                break;
            }
            case 164: {
                this.evaluateLogicalAndExpression(expression);
                break;
            }
            case 162: {
                this.evaluateLogicalOrExpression(expression);
                break;
            }
            case 341: {
                this.evaluateBinaryExpression("and", expression);
                break;
            }
            case 351: {
                this.evaluateBinaryExpressionWithAssignment("and", expression);
                break;
            }
            case 340: {
                this.evaluateBinaryExpression("or", expression);
                break;
            }
            case 350: {
                this.evaluateBinaryExpressionWithAssignment("or", expression);
                break;
            }
            case 342: {
                this.evaluateBinaryExpression("xor", expression);
                break;
            }
            case 352: {
                this.evaluateBinaryExpressionWithAssignment("xor", expression);
                break;
            }
            case 200: {
                this.evaluateBinaryExpression("plus", expression);
                break;
            }
            case 210: {
                this.evaluateBinaryExpressionWithAssignment("plus", expression);
                break;
            }
            case 201: {
                this.evaluateBinaryExpression("minus", expression);
                break;
            }
            case 211: {
                this.evaluateBinaryExpressionWithAssignment("minus", expression);
                break;
            }
            case 202: {
                this.evaluateBinaryExpression("multiply", expression);
                break;
            }
            case 212: {
                this.evaluateBinaryExpressionWithAssignment("multiply", expression);
                break;
            }
            case 203: {
                this.evaluateBinaryExpression("div", expression);
                break;
            }
            case 213: {
                this.evaluateBinaryExpressionWithAssignment("div", expression);
                break;
            }
            case 204: {
                this.evaluateBinaryExpression("intdiv", expression);
                break;
            }
            case 214: {
                this.evaluateBinaryExpressionWithAssignment("intdiv", expression);
                break;
            }
            case 205: {
                this.evaluateBinaryExpression("mod", expression);
                break;
            }
            case 215: {
                this.evaluateBinaryExpressionWithAssignment("mod", expression);
                break;
            }
            case 206: {
                this.evaluateBinaryExpression("power", expression);
                break;
            }
            case 216: {
                this.evaluateBinaryExpressionWithAssignment("power", expression);
                break;
            }
            case 280: {
                this.evaluateBinaryExpression("leftShift", expression);
                break;
            }
            case 285: {
                this.evaluateBinaryExpressionWithAssignment("leftShift", expression);
                break;
            }
            case 281: {
                this.evaluateBinaryExpression("rightShift", expression);
                break;
            }
            case 286: {
                this.evaluateBinaryExpressionWithAssignment("rightShift", expression);
                break;
            }
            case 282: {
                this.evaluateBinaryExpression("rightShiftUnsigned", expression);
                break;
            }
            case 287: {
                this.evaluateBinaryExpressionWithAssignment("rightShiftUnsigned", expression);
                break;
            }
            case 544: {
                this.evaluateInstanceof(expression);
                break;
            }
            case 90: {
                this.evaluateBinaryExpression(AsmClassGenerator.findRegexMethod, expression);
                break;
            }
            case 94: {
                this.evaluateBinaryExpression(AsmClassGenerator.matchRegexMethod, expression);
                break;
            }
            case 30: {
                if (this.leftHandExpression) {
                    this.throwException("Should not be called here. Possible reason: postfix operation on array.");
                    break;
                }
                this.evaluateBinaryExpression("getAt", expression);
                break;
            }
            case 573: {
                this.evaluateBinaryExpression(AsmClassGenerator.isCaseMethod, expression);
                break;
            }
            default: {
                this.throwException("Operation: " + expression.getOperation() + " not supported");
                break;
            }
        }
        this.record(expression.getOperation(), this.isComparisonExpression(expression));
    }
    
    private void load(final Expression exp) {
        final boolean wasLeft = this.leftHandExpression;
        this.leftHandExpression = false;
        this.visitAndAutoboxBoolean(exp);
        this.leftHandExpression = wasLeft;
    }
    
    @Override
    public void visitPostfixExpression(final PostfixExpression expression) {
        switch (expression.getOperation().getType()) {
            case 250: {
                this.evaluatePostfixMethod("next", expression.getExpression());
                break;
            }
            case 260: {
                this.evaluatePostfixMethod("previous", expression.getExpression());
                break;
            }
        }
        this.record(expression);
    }
    
    private void record(final Token token, final boolean unboxedValue) {
        if (this.assertionTracker == null) {
            return;
        }
        if (unboxedValue) {
            this.mv.visitInsn(89);
            this.helper.boxBoolean();
        }
        this.record(this.assertionTracker.sourceText.getNormalizedColumn(token.getStartLine(), token.getStartColumn()));
        if (unboxedValue) {
            this.mv.visitInsn(87);
        }
    }
    
    private void record(final Expression expression) {
        if (this.assertionTracker == null) {
            return;
        }
        this.record(this.assertionTracker.sourceText.getNormalizedColumn(expression.getLineNumber(), expression.getColumnNumber()));
    }
    
    private void recordBool(final Expression expression) {
        if (this.assertionTracker == null) {
            return;
        }
        this.mv.visitInsn(89);
        this.helper.boxBoolean();
        this.record(expression);
        this.mv.visitInsn(87);
    }
    
    private void record(final int normalizedColumn) {
        if (this.assertionTracker == null) {
            return;
        }
        this.mv.visitVarInsn(25, this.assertionTracker.recorderIndex);
        this.helper.swapWithObject(ClassHelper.OBJECT_TYPE);
        this.mv.visitLdcInsn(normalizedColumn);
        this.mv.visitMethodInsn(182, "org/codehaus/groovy/transform/powerassert/ValueRecorder", "record", "(Ljava/lang/Object;I)Ljava/lang/Object;");
    }
    
    private void throwException(final String s) {
        throw new RuntimeParserException(s, this.currentASTNode);
    }
    
    @Override
    public void visitPrefixExpression(final PrefixExpression expression) {
        switch (expression.getOperation().getType()) {
            case 250: {
                this.evaluatePrefixMethod("next", expression.getExpression());
                break;
            }
            case 260: {
                this.evaluatePrefixMethod("previous", expression.getExpression());
                break;
            }
        }
        this.record(expression);
    }
    
    @Override
    public void visitClosureExpression(final ClosureExpression expression) {
        ClassNode innerClass = this.closureClassMap.get(expression);
        if (innerClass == null) {
            innerClass = this.createClosureClass(expression);
            this.closureClassMap.put(expression, innerClass);
            this.addInnerClass(innerClass);
            innerClass.addInterface(ClassHelper.GENERATED_CLOSURE_Type);
        }
        final String innerClassinternalName = BytecodeHelper.getClassInternalName(innerClass);
        this.passingParams = true;
        final List constructors = innerClass.getDeclaredConstructors();
        final ConstructorNode node = constructors.get(0);
        final Parameter[] localVariableParams = node.getParameters();
        this.mv.visitTypeInsn(187, innerClassinternalName);
        this.mv.visitInsn(89);
        if ((this.isStaticMethod() || this.specialCallWithinConstructor) && !this.classNode.declaresInterface(ClassHelper.GENERATED_CLOSURE_Type)) {
            this.visitClassExpression(new ClassExpression(this.classNode));
            this.visitClassExpression(new ClassExpression(this.getOutermostClass()));
        }
        else {
            this.mv.visitVarInsn(25, 0);
            this.loadThis();
        }
        for (int i = 2; i < localVariableParams.length; ++i) {
            final Parameter param = localVariableParams[i];
            final String name = param.getName();
            if (!this.compileStack.containsVariable(name) && this.compileStack.getScope().isReferencedClassVariable(name)) {
                this.visitFieldExpression(new FieldExpression(this.classNode.getDeclaredField(name)));
            }
            else {
                org.codehaus.groovy.classgen.Variable v = this.compileStack.getVariable(name, !this.classNodeUsesReferences());
                if (v == null) {
                    final FieldNode field = this.classNode.getDeclaredField(name);
                    this.mv.visitVarInsn(25, 0);
                    this.mv.visitFieldInsn(180, this.internalClassName, name, BytecodeHelper.getTypeDescription(field.getType()));
                    param.setClosureSharedVariable(false);
                    v = this.compileStack.defineVariable(param, true);
                    param.setClosureSharedVariable(true);
                    v.setHolder(true);
                }
                this.mv.visitVarInsn(25, v.getIndex());
            }
        }
        this.passingParams = false;
        this.mv.visitMethodInsn(183, innerClassinternalName, "<init>", BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, localVariableParams));
    }
    
    private boolean classNodeUsesReferences() {
        final boolean ret = this.classNode.getSuperClass() == ClassHelper.CLOSURE_TYPE;
        if (ret) {
            return ret;
        }
        if (this.classNode instanceof InnerClassNode) {
            final InnerClassNode inner = (InnerClassNode)this.classNode;
            return inner.isAnonymous();
        }
        return false;
    }
    
    protected void loadThisOrOwner() {
        if (this.isInnerClass()) {
            this.visitFieldExpression(new FieldExpression(this.classNode.getDeclaredField("owner")));
        }
        else {
            this.loadThis();
        }
    }
    
    @Deprecated
    @Override
    public void visitRegexExpression(final RegexExpression expression) {
        expression.getRegex().visit(this);
        AsmClassGenerator.regexPattern.call(this.mv);
    }
    
    @Override
    public void visitConstantExpression(final ConstantExpression expression) {
        final String constantName = expression.getConstantName();
        if ((this.methodNode != null && this.methodNode.getName().equals("<clinit>")) || constantName == null) {
            final Object value = expression.getValue();
            this.helper.loadConstant(value);
        }
        else {
            this.mv.visitFieldInsn(178, this.internalClassName, constantName, BytecodeHelper.getTypeDescription(expression.getType()));
        }
    }
    
    @Override
    public void visitSpreadExpression(final SpreadExpression expression) {
        throw new GroovyBugError("SpreadExpression should not be visited here");
    }
    
    @Override
    public void visitSpreadMapExpression(final SpreadMapExpression expression) {
        final Expression subExpression = expression.getExpression();
        final AssertionTracker old = this.assertionTracker;
        this.assertionTracker = null;
        subExpression.visit(this);
        AsmClassGenerator.spreadMap.call(this.mv);
        this.assertionTracker = old;
    }
    
    @Override
    public void visitMethodPointerExpression(final MethodPointerExpression expression) {
        final Expression subExpression = expression.getExpression();
        subExpression.visit(this);
        this.loadDynamicName(expression.getMethodName());
        AsmClassGenerator.getMethodPointer.call(this.mv);
    }
    
    private void loadDynamicName(final Expression name) {
        if (name instanceof ConstantExpression) {
            final ConstantExpression ce = (ConstantExpression)name;
            final Object value = ce.getValue();
            if (value instanceof String) {
                this.helper.loadConstant(value);
                return;
            }
        }
        new CastExpression(ClassHelper.STRING_TYPE, name).visit(this);
    }
    
    @Override
    public void visitUnaryMinusExpression(final UnaryMinusExpression expression) {
        final Expression subExpression = expression.getExpression();
        subExpression.visit(this);
        AsmClassGenerator.unaryMinus.call(this.mv);
        this.record(expression);
    }
    
    @Override
    public void visitUnaryPlusExpression(final UnaryPlusExpression expression) {
        final Expression subExpression = expression.getExpression();
        subExpression.visit(this);
        AsmClassGenerator.unaryPlus.call(this.mv);
        this.record(expression);
    }
    
    @Override
    public void visitBitwiseNegationExpression(final BitwiseNegationExpression expression) {
        final Expression subExpression = expression.getExpression();
        subExpression.visit(this);
        AsmClassGenerator.bitwiseNegate.call(this.mv);
        this.record(expression);
    }
    
    @Override
    public void visitCastExpression(final CastExpression castExpression) {
        final ClassNode type = castExpression.getType();
        this.visitAndAutoboxBoolean(castExpression.getExpression());
        final ClassNode rht = this.rightHandType;
        this.rightHandType = castExpression.getExpression().getType();
        this.doConvertAndCast(type, castExpression.getExpression(), castExpression.isIgnoringAutoboxing(), false, castExpression.isCoerce());
        this.rightHandType = rht;
    }
    
    @Override
    public void visitNotExpression(final NotExpression expression) {
        final Expression subExpression = expression.getExpression();
        subExpression.visit(this);
        if (!this.isComparisonExpression(subExpression) && !(subExpression instanceof BooleanExpression)) {
            this.helper.unbox(Boolean.TYPE);
        }
        this.helper.negateBoolean();
        this.recordBool(expression);
    }
    
    @Override
    public void visitBooleanExpression(final BooleanExpression expression) {
        this.compileStack.pushBooleanExpression();
        expression.getExpression().visit(this);
        if (!this.isComparisonExpression(expression.getExpression())) {
            this.helper.unbox(Boolean.TYPE);
        }
        this.compileStack.pop();
    }
    
    private void makeInvokeMethodCall(final MethodCallExpression call, final boolean useSuper, final MethodCallerMultiAdapter adapter) {
        Expression objectExpression = call.getObjectExpression();
        if (!this.isStaticMethod() && !this.isStaticContext() && isThisExpression(call.getObjectExpression())) {
            objectExpression = new CastExpression(this.classNode, objectExpression);
        }
        final Expression messageName = new CastExpression(ClassHelper.STRING_TYPE, call.getMethod());
        if (useSuper) {
            final ClassNode superClass = this.isInClosure() ? this.getOutermostClass().getSuperClass() : this.classNode.getSuperClass();
            this.makeCall(new ClassExpression(superClass), objectExpression, messageName, call.getArguments(), adapter, call.isSafe(), call.isSpreadSafe(), false);
        }
        else {
            this.makeCall(objectExpression, messageName, call.getArguments(), adapter, call.isSafe(), call.isSpreadSafe(), call.isImplicitThis());
        }
    }
    
    private void makeCall(final Expression receiver, final Expression message, final Expression arguments, final MethodCallerMultiAdapter adapter, final boolean safe, final boolean spreadSafe, final boolean implicitThis) {
        final ClassNode cn = this.classNode;
        this.makeCall(new ClassExpression(cn), receiver, message, arguments, adapter, safe, spreadSafe, implicitThis);
    }
    
    private void makeCall(final ClassExpression sender, final Expression receiver, final Expression message, final Expression arguments, final MethodCallerMultiAdapter adapter, final boolean safe, final boolean spreadSafe, final boolean implicitThis) {
        if ((adapter == AsmClassGenerator.invokeMethod || adapter == AsmClassGenerator.invokeMethodOnCurrent || adapter == AsmClassGenerator.invokeStaticMethod) && !spreadSafe) {
            final String methodName = this.getMethodName(message);
            if (methodName != null) {
                this.makeCallSite(receiver, methodName, arguments, safe, implicitThis, adapter == AsmClassGenerator.invokeMethodOnCurrent, adapter == AsmClassGenerator.invokeStaticMethod);
                return;
            }
        }
        final boolean lhs = this.leftHandExpression;
        this.leftHandExpression = false;
        sender.visit(this);
        final boolean oldVal = this.implicitThis;
        this.implicitThis = implicitThis;
        this.visitAndAutoboxBoolean(receiver);
        this.implicitThis = oldVal;
        if (message != null) {
            message.visit(this);
        }
        final boolean containsSpreadExpression = containsSpreadExpression(arguments);
        final int numberOfArguments = containsSpreadExpression ? -1 : argumentSize(arguments);
        if (numberOfArguments > 0 || containsSpreadExpression) {
            ArgumentListExpression ae;
            if (arguments instanceof ArgumentListExpression) {
                ae = (ArgumentListExpression)arguments;
            }
            else if (arguments instanceof TupleExpression) {
                final TupleExpression te = (TupleExpression)arguments;
                ae = new ArgumentListExpression(te.getExpressions());
            }
            else {
                ae = new ArgumentListExpression();
                ae.addExpression(arguments);
            }
            if (containsSpreadExpression) {
                this.despreadList(ae.getExpressions(), true);
            }
            else {
                ae.visit(this);
            }
        }
        else if (numberOfArguments > 0) {
            final TupleExpression te2 = (TupleExpression)arguments;
            for (int i = 0; i < numberOfArguments; ++i) {
                final Expression argument = te2.getExpression(i);
                this.visitAndAutoboxBoolean(argument);
                if (argument instanceof CastExpression) {
                    this.loadWrapper(argument);
                }
            }
        }
        adapter.call(this.mv, numberOfArguments, safe, spreadSafe);
        this.leftHandExpression = lhs;
    }
    
    private void makeGetPropertySite(final Expression receiver, final String methodName, final boolean safe, final boolean implicitThis) {
        if (this.isNotClinit()) {
            this.mv.visitVarInsn(25, this.callSiteArrayVarIndex);
        }
        else {
            this.mv.visitMethodInsn(184, this.getClassName(), "$getCallSiteArray", "()[Lorg/codehaus/groovy/runtime/callsite/CallSite;");
        }
        final int index = this.allocateIndex(methodName);
        this.mv.visitLdcInsn(index);
        this.mv.visitInsn(50);
        final boolean lhs = this.leftHandExpression;
        this.leftHandExpression = false;
        final boolean oldVal = this.implicitThis;
        this.implicitThis = implicitThis;
        this.visitAndAutoboxBoolean(receiver);
        this.implicitThis = oldVal;
        if (!safe) {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "callGetProperty", "(Ljava/lang/Object;)Ljava/lang/Object;");
        }
        else {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "callGetPropertySafe", "(Ljava/lang/Object;)Ljava/lang/Object;");
        }
        this.leftHandExpression = lhs;
    }
    
    private void makeGroovyObjectGetPropertySite(final Expression receiver, final String methodName, final boolean safe, final boolean implicitThis) {
        if (this.isNotClinit()) {
            this.mv.visitVarInsn(25, this.callSiteArrayVarIndex);
        }
        else {
            this.mv.visitMethodInsn(184, this.getClassName(), "$getCallSiteArray", "()[Lorg/codehaus/groovy/runtime/callsite/CallSite;");
        }
        final int index = this.allocateIndex(methodName);
        this.mv.visitLdcInsn(index);
        this.mv.visitInsn(50);
        final boolean lhs = this.leftHandExpression;
        this.leftHandExpression = false;
        final boolean oldVal = this.implicitThis;
        this.implicitThis = implicitThis;
        this.visitAndAutoboxBoolean(receiver);
        this.implicitThis = oldVal;
        if (!safe) {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "callGroovyObjectGetProperty", "(Ljava/lang/Object;)Ljava/lang/Object;");
        }
        else {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "callGroovyObjectGetPropertySafe", "(Ljava/lang/Object;)Ljava/lang/Object;");
        }
        this.leftHandExpression = lhs;
    }
    
    private String getMethodName(final Expression message) {
        String methodName = null;
        if (message instanceof CastExpression) {
            final CastExpression msg = (CastExpression)message;
            if (msg.getType() == ClassHelper.STRING_TYPE) {
                final Expression methodExpr = msg.getExpression();
                if (methodExpr instanceof ConstantExpression) {
                    methodName = methodExpr.getText();
                }
            }
        }
        if (methodName == null && message instanceof ConstantExpression) {
            final ConstantExpression constantExpression = (ConstantExpression)message;
            methodName = constantExpression.getText();
        }
        return methodName;
    }
    
    private void makeCallSite(final Expression receiver, final String message, final Expression arguments, final boolean safe, final boolean implicitThis, final boolean callCurrent, final boolean callStatic) {
        if (this.isNotClinit()) {
            this.mv.visitVarInsn(25, this.callSiteArrayVarIndex);
        }
        else {
            this.mv.visitMethodInsn(184, this.getClassName(), "$getCallSiteArray", "()[Lorg/codehaus/groovy/runtime/callsite/CallSite;");
        }
        final int index = this.allocateIndex(message);
        this.mv.visitLdcInsn(index);
        this.mv.visitInsn(50);
        final boolean constructor = message.equals("<$constructor$>");
        final boolean lhs = this.leftHandExpression;
        this.leftHandExpression = false;
        final boolean oldVal = this.implicitThis;
        this.implicitThis = implicitThis;
        this.visitAndAutoboxBoolean(receiver);
        this.implicitThis = oldVal;
        final boolean containsSpreadExpression = containsSpreadExpression(arguments);
        int numberOfArguments = containsSpreadExpression ? -1 : argumentSize(arguments);
        if (numberOfArguments > 0 || containsSpreadExpression) {
            ArgumentListExpression ae;
            if (arguments instanceof ArgumentListExpression) {
                ae = (ArgumentListExpression)arguments;
            }
            else if (arguments instanceof TupleExpression) {
                final TupleExpression te = (TupleExpression)arguments;
                ae = new ArgumentListExpression(te.getExpressions());
            }
            else {
                ae = new ArgumentListExpression();
                ae.addExpression(arguments);
            }
            if (containsSpreadExpression) {
                numberOfArguments = -1;
                this.despreadList(ae.getExpressions(), true);
            }
            else {
                numberOfArguments = ae.getExpressions().size();
                for (int i = 0; i < numberOfArguments; ++i) {
                    final Expression argument = ae.getExpression(i);
                    this.visitAndAutoboxBoolean(argument);
                    if (argument instanceof CastExpression) {
                        this.loadWrapper(argument);
                    }
                }
            }
        }
        if (numberOfArguments != -1) {
            if (numberOfArguments > 4) {
                final String createArraySignature = getCreateArraySignature(numberOfArguments);
                this.mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/ArrayUtil", "createArray", createArraySignature);
            }
        }
        final String desc = getDescForParamNum(numberOfArguments);
        if (callStatic) {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "callStatic", "(Ljava/lang/Class;" + desc);
        }
        else if (constructor) {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "callConstructor", "(Ljava/lang/Object;" + desc);
        }
        else if (callCurrent) {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "callCurrent", "(Lgroovy/lang/GroovyObject;" + desc);
        }
        else if (safe) {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "callSafe", "(Ljava/lang/Object;" + desc);
        }
        else {
            this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "call", "(Ljava/lang/Object;" + desc);
        }
        this.leftHandExpression = lhs;
    }
    
    private static String getDescForParamNum(final int numberOfArguments) {
        switch (numberOfArguments) {
            case 0: {
                return ")Ljava/lang/Object;";
            }
            case 1: {
                return "Ljava/lang/Object;)Ljava/lang/Object;";
            }
            case 2: {
                return "Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;";
            }
            case 3: {
                return "Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;";
            }
            case 4: {
                return "Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;";
            }
            default: {
                return "[Ljava/lang/Object;)Ljava/lang/Object;";
            }
        }
    }
    
    private static String getCreateArraySignature(final int numberOfArguments) {
        if (AsmClassGenerator.sig[numberOfArguments] == null) {
            final StringBuilder sb = new StringBuilder("(");
            for (int i = 0; i != numberOfArguments; ++i) {
                sb.append("Ljava/lang/Object;");
            }
            sb.append(")[Ljava/lang/Object;");
            AsmClassGenerator.sig[numberOfArguments] = sb.toString();
        }
        return AsmClassGenerator.sig[numberOfArguments];
    }
    
    private void makeBinopCallSite(final Expression receiver, final String message, final Expression arguments) {
        this.prepareCallSite(message);
        final boolean lhs = this.leftHandExpression;
        this.leftHandExpression = false;
        final boolean oldVal = this.implicitThis;
        this.implicitThis = false;
        this.visitAndAutoboxBoolean(receiver);
        this.implicitThis = oldVal;
        this.visitAndAutoboxBoolean(arguments);
        this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "call", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        this.leftHandExpression = lhs;
    }
    
    private void prepareCallSite(final String message) {
        if (this.isNotClinit()) {
            this.mv.visitVarInsn(25, this.callSiteArrayVarIndex);
        }
        else {
            this.mv.visitMethodInsn(184, this.getClassName(), "$getCallSiteArray", "()[Lorg/codehaus/groovy/runtime/callsite/CallSite;");
        }
        final int index = this.allocateIndex(message);
        this.mv.visitLdcInsn(index);
        this.mv.visitInsn(50);
    }
    
    private String getClassName() {
        String className;
        if (!this.classNode.isInterface() || this.interfaceClassLoadingClass == null) {
            className = this.internalClassName;
        }
        else {
            className = BytecodeHelper.getClassInternalName(this.interfaceClassLoadingClass);
        }
        return className;
    }
    
    private int allocateIndex(final String name) {
        this.callSites.add(name);
        return this.callSites.size() - 1;
    }
    
    private void despreadList(final List<Expression> expressions, final boolean wrap) {
        final List<Expression> spreadIndexes = new ArrayList<Expression>();
        final List<Expression> spreadExpressions = new ArrayList<Expression>();
        final List<Expression> normalArguments = new ArrayList<Expression>();
        for (int i = 0; i < expressions.size(); ++i) {
            final Expression expr = expressions.get(i);
            if (!(expr instanceof SpreadExpression)) {
                normalArguments.add(expr);
            }
            else {
                spreadIndexes.add(new ConstantExpression(i - spreadExpressions.size()));
                spreadExpressions.add(((SpreadExpression)expr).getExpression());
            }
        }
        this.visitTupleExpression(new ArgumentListExpression(normalArguments), wrap);
        new TupleExpression(spreadExpressions).visit(this);
        new ArrayExpression(ClassHelper.int_TYPE, spreadIndexes, null).visit(this);
        AsmClassGenerator.despreadList.call(this.mv);
    }
    
    @Override
    public void visitMethodCallExpression(final MethodCallExpression call) {
        this.onLineNumber(call, "visitMethodCallExpression: \"" + call.getMethod() + "\":");
        if (this.isUnqualifiedClosureFieldCall(call)) {
            this.invokeClosure(call.getArguments(), call.getMethodAsString());
            this.record(call.getObjectExpression());
        }
        else {
            final boolean isSuperMethodCall = usesSuper(call);
            MethodCallerMultiAdapter adapter = AsmClassGenerator.invokeMethod;
            if (isThisExpression(call.getObjectExpression())) {
                adapter = AsmClassGenerator.invokeMethodOnCurrent;
            }
            if (isSuperMethodCall) {
                adapter = AsmClassGenerator.invokeMethodOnSuper;
            }
            if (this.isStaticInvocation(call)) {
                adapter = AsmClassGenerator.invokeStaticMethod;
            }
            this.makeInvokeMethodCall(call, isSuperMethodCall, adapter);
            this.record(call.getMethod());
        }
    }
    
    private boolean isUnqualifiedClosureFieldCall(final MethodCallExpression call) {
        final String methodName = call.getMethodAsString();
        if (methodName == null) {
            return false;
        }
        if (!call.isImplicitThis()) {
            return false;
        }
        if (!isThisExpression(call.getObjectExpression())) {
            return false;
        }
        final FieldNode field = this.classNode.getDeclaredField(methodName);
        if (field == null) {
            return false;
        }
        if (this.isStaticInvocation(call) && !field.isStatic()) {
            return false;
        }
        final Expression arguments = call.getArguments();
        return !this.classNode.hasPossibleMethod(methodName, arguments);
    }
    
    private void invokeClosure(final Expression arguments, final String methodName) {
        this.visitVariableExpression(new VariableExpression(methodName));
        if (arguments instanceof TupleExpression) {
            arguments.visit(this);
        }
        else {
            new TupleExpression(arguments).visit(this);
        }
        AsmClassGenerator.invokeClosureMethod.call(this.mv);
    }
    
    private boolean isStaticInvocation(final MethodCallExpression call) {
        return isThisExpression(call.getObjectExpression()) && (this.isStaticMethod() || (this.isStaticContext() && !call.isImplicitThis()));
    }
    
    protected boolean emptyArguments(final Expression arguments) {
        return argumentSize(arguments) == 0;
    }
    
    protected static boolean containsSpreadExpression(final Expression arguments) {
        List<Expression> args = null;
        if (arguments instanceof TupleExpression) {
            final TupleExpression tupleExpression = (TupleExpression)arguments;
            args = tupleExpression.getExpressions();
        }
        else {
            if (!(arguments instanceof ListExpression)) {
                return arguments instanceof SpreadExpression;
            }
            final ListExpression le = (ListExpression)arguments;
            args = le.getExpressions();
        }
        for (final Expression arg : args) {
            if (arg instanceof SpreadExpression) {
                return true;
            }
        }
        return false;
    }
    
    protected static int argumentSize(final Expression arguments) {
        if (arguments instanceof TupleExpression) {
            final TupleExpression tupleExpression = (TupleExpression)arguments;
            return tupleExpression.getExpressions().size();
        }
        return 1;
    }
    
    @Override
    public void visitStaticMethodCallExpression(final StaticMethodCallExpression call) {
        this.onLineNumber(call, "visitStaticMethodCallExpression: \"" + call.getMethod() + "\":");
        this.makeCall(new ClassExpression(call.getOwnerType()), new ConstantExpression(call.getMethod()), call.getArguments(), AsmClassGenerator.invokeStaticMethod, false, false, false);
        this.record(call);
    }
    
    private void addGeneratedClosureConstructorCall(final ConstructorCallExpression call) {
        this.mv.visitVarInsn(25, 0);
        final ClassNode callNode = this.classNode.getSuperClass();
        final TupleExpression arguments = (TupleExpression)call.getArguments();
        if (arguments.getExpressions().size() != 2) {
            throw new GroovyBugError("expected 2 arguments for closure constructor super call, but got" + arguments.getExpressions().size());
        }
        arguments.getExpression(0).visit(this);
        arguments.getExpression(1).visit(this);
        final Parameter p = new Parameter(ClassHelper.OBJECT_TYPE, "_p");
        final String descriptor = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, new Parameter[] { p, p });
        this.mv.visitMethodInsn(183, BytecodeHelper.getClassInternalName(callNode), "<init>", descriptor);
    }
    
    private void visitSpecialConstructorCall(final ConstructorCallExpression call) {
        if (this.classNode.declaresInterface(ClassHelper.GENERATED_CLOSURE_Type)) {
            this.addGeneratedClosureConstructorCall(call);
            return;
        }
        ClassNode callNode = this.classNode;
        if (call.isSuperCall()) {
            callNode = callNode.getSuperClass();
        }
        final List constructors = this.sortConstructors(call, callNode);
        call.getArguments().visit(this);
        this.mv.visitInsn(89);
        this.helper.pushConstant(constructors.size());
        this.visitClassExpression(new ClassExpression(callNode));
        AsmClassGenerator.selectConstructorAndTransformArguments.call(this.mv);
        this.mv.visitInsn(90);
        this.mv.visitInsn(4);
        this.mv.visitInsn(126);
        final Label afterIf = new Label();
        this.mv.visitJumpInsn(153, afterIf);
        this.mv.visitInsn(3);
        this.mv.visitInsn(50);
        this.mv.visitTypeInsn(192, "[Ljava/lang/Object;");
        this.mv.visitLabel(afterIf);
        this.mv.visitInsn(95);
        if (this.constructorNode != null) {
            this.mv.visitVarInsn(25, 0);
        }
        else {
            this.mv.visitTypeInsn(187, BytecodeHelper.getClassInternalName(callNode));
        }
        this.mv.visitInsn(95);
        this.mv.visitIntInsn(16, 8);
        this.mv.visitInsn(122);
        final Label[] targets = new Label[constructors.size()];
        final int[] indices = new int[constructors.size()];
        for (int i = 0; i < targets.length; ++i) {
            targets[i] = new Label();
            indices[i] = i;
        }
        final Label defaultLabel = new Label();
        final Label afterSwitch = new Label();
        this.mv.visitLookupSwitchInsn(defaultLabel, indices, targets);
        for (int j = 0; j < targets.length; ++j) {
            this.mv.visitLabel(targets[j]);
            if (this.constructorNode != null) {
                this.mv.visitInsn(95);
                this.mv.visitInsn(90);
            }
            else {
                this.mv.visitInsn(90);
                this.mv.visitInsn(93);
                this.mv.visitInsn(87);
            }
            final ConstructorNode cn = constructors.get(j);
            final String descriptor = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, cn.getParameters());
            final Parameter[] parameters = cn.getParameters();
            for (int p = 0; p < parameters.length; ++p) {
                this.mv.visitInsn(89);
                this.helper.pushConstant(p);
                this.mv.visitInsn(50);
                final ClassNode type = parameters[p].getType();
                if (ClassHelper.isPrimitiveType(type)) {
                    this.helper.unbox(type);
                }
                else {
                    this.helper.doCast(type);
                }
                this.helper.swapWithObject(type);
            }
            this.mv.visitInsn(87);
            this.mv.visitMethodInsn(183, BytecodeHelper.getClassInternalName(callNode), "<init>", descriptor);
            this.mv.visitJumpInsn(167, afterSwitch);
        }
        this.mv.visitLabel(defaultLabel);
        this.mv.visitTypeInsn(187, "java/lang/IllegalArgumentException");
        this.mv.visitInsn(89);
        this.mv.visitLdcInsn("illegal constructor number");
        this.mv.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
        this.mv.visitInsn(191);
        this.mv.visitLabel(afterSwitch);
        if (this.constructorNode == null) {
            this.mv.visitInsn(95);
        }
        this.mv.visitInsn(87);
    }
    
    private List sortConstructors(final ConstructorCallExpression call, final ClassNode callNode) {
        final List constructors = new ArrayList(callNode.getDeclaredConstructors());
        final Comparator comp = new Comparator() {
            public int compare(final Object arg0, final Object arg1) {
                final ConstructorNode c0 = (ConstructorNode)arg0;
                final ConstructorNode c2 = (ConstructorNode)arg1;
                final String descriptor0 = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, c0.getParameters());
                final String descriptor2 = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, c2.getParameters());
                return descriptor0.compareTo(descriptor2);
            }
        };
        Collections.sort((List<Object>)constructors, comp);
        return constructors;
    }
    
    @Override
    public void visitConstructorCallExpression(final ConstructorCallExpression call) {
        this.onLineNumber(call, "visitConstructorCallExpression: \"" + call.getType().getName() + "\":");
        if (call.isSpecialCall()) {
            this.specialCallWithinConstructor = true;
            this.visitSpecialConstructorCall(call);
            this.specialCallWithinConstructor = false;
            return;
        }
        Expression arguments = call.getArguments();
        if (arguments instanceof TupleExpression) {
            final TupleExpression tupleExpression = (TupleExpression)arguments;
            final int size = tupleExpression.getExpressions().size();
            if (size == 0) {
                arguments = MethodCallExpression.NO_ARGUMENTS;
            }
        }
        final Expression receiverClass = new ClassExpression(call.getType());
        this.makeCallSite(receiverClass, "<$constructor$>", arguments, false, false, false, false);
        this.record(call);
    }
    
    private static String makeFieldClassName(final ClassNode type) {
        final String internalName = BytecodeHelper.getClassInternalName(type);
        final StringBuffer ret = new StringBuffer(internalName.length());
        for (int i = 0; i < internalName.length(); ++i) {
            final char c = internalName.charAt(i);
            if (c == '/') {
                ret.append('$');
            }
            else if (c != ';') {
                ret.append(c);
            }
        }
        return ret.toString();
    }
    
    private static String getStaticFieldName(final ClassNode type) {
        ClassNode componentType = type;
        String prefix = "";
        while (componentType.isArray()) {
            prefix += "$";
            componentType = componentType.getComponentType();
        }
        if (prefix.length() != 0) {
            prefix = "array" + prefix;
        }
        final String name = prefix + "$class$" + makeFieldClassName(componentType);
        return name;
    }
    
    private void visitAttributeOrProperty(final PropertyExpression expression, final MethodCallerMultiAdapter adapter) {
        final Expression objectExpression = expression.getObjectExpression();
        if (isThisOrSuper(objectExpression)) {
            final String name = expression.getPropertyAsString();
            if (name != null) {
                FieldNode field = null;
                boolean privateSuperField = false;
                if (isSuperExpression(objectExpression)) {
                    field = this.classNode.getSuperClass().getDeclaredField(name);
                    if (field != null && (field.getModifiers() & 0x2) != 0x0) {
                        privateSuperField = true;
                    }
                }
                else if (this.isNotExplicitThisInClosure(expression.isImplicitThis())) {
                    field = this.classNode.getDeclaredField(name);
                }
                if (field != null && !privateSuperField) {
                    this.visitFieldExpression(new FieldExpression(field));
                    return;
                }
            }
            if (isSuperExpression(objectExpression)) {
                String prefix;
                if (this.leftHandExpression) {
                    prefix = "set";
                }
                else {
                    prefix = "get";
                }
                final String propName = prefix + MetaClassHelper.capitalize(name);
                this.visitMethodCallExpression(new MethodCallExpression(objectExpression, propName, MethodCallExpression.NO_ARGUMENTS));
                return;
            }
        }
        final String propName2 = expression.getPropertyAsString();
        if (expression.getObjectExpression() instanceof ClassExpression && propName2 != null && propName2.equals("this")) {
            final ClassNode type = expression.getObjectExpression().getType();
            ClassNode iterType = this.classNode;
            this.mv.visitVarInsn(25, 0);
            while (!iterType.equals(type)) {
                final String ownerName = BytecodeHelper.getClassInternalName(iterType);
                iterType = iterType.getOuterClass();
                final String typeName = BytecodeHelper.getTypeDescription(iterType);
                this.mv.visitFieldInsn(180, ownerName, "this$0", typeName);
            }
            return;
        }
        if (adapter == AsmClassGenerator.getProperty && !expression.isSpreadSafe() && propName2 != null) {
            this.makeGetPropertySite(objectExpression, propName2, expression.isSafe(), expression.isImplicitThis());
        }
        else if (adapter == AsmClassGenerator.getGroovyObjectProperty && !expression.isSpreadSafe() && propName2 != null) {
            this.makeGroovyObjectGetPropertySite(objectExpression, propName2, expression.isSafe(), expression.isImplicitThis());
        }
        else {
            this.makeCall(objectExpression, new CastExpression(ClassHelper.STRING_TYPE, expression.getProperty()), MethodCallExpression.NO_ARGUMENTS, adapter, expression.isSafe(), expression.isSpreadSafe(), expression.isImplicitThis());
        }
    }
    
    private boolean isStaticContext() {
        if (this.compileStack != null && this.compileStack.getScope() != null) {
            return this.compileStack.getScope().isInStaticContext();
        }
        return this.isInClosure() && this.constructorNode == null && (this.classNode.isStaticClass() || this.methodNode.isStatic());
    }
    
    @Override
    public void visitPropertyExpression(final PropertyExpression expression) {
        final Expression objectExpression = expression.getObjectExpression();
        MethodCallerMultiAdapter adapter;
        if (this.leftHandExpression) {
            adapter = AsmClassGenerator.setProperty;
            if (this.isGroovyObject(objectExpression)) {
                adapter = AsmClassGenerator.setGroovyObjectProperty;
            }
            if (this.isStaticContext() && isThisOrSuper(objectExpression)) {
                adapter = AsmClassGenerator.setProperty;
            }
        }
        else {
            adapter = AsmClassGenerator.getProperty;
            if (this.isGroovyObject(objectExpression)) {
                adapter = AsmClassGenerator.getGroovyObjectProperty;
            }
            if (this.isStaticContext() && isThisOrSuper(objectExpression)) {
                adapter = AsmClassGenerator.getProperty;
            }
        }
        this.visitAttributeOrProperty(expression, adapter);
        this.record(expression.getProperty());
    }
    
    @Override
    public void visitAttributeExpression(final AttributeExpression expression) {
        final Expression objectExpression = expression.getObjectExpression();
        MethodCallerMultiAdapter adapter;
        if (this.leftHandExpression) {
            adapter = AsmClassGenerator.setField;
            if (this.isGroovyObject(objectExpression)) {
                adapter = AsmClassGenerator.setGroovyObjectField;
            }
            if (usesSuper(expression)) {
                adapter = AsmClassGenerator.setFieldOnSuper;
            }
        }
        else {
            adapter = AsmClassGenerator.getField;
            if (this.isGroovyObject(objectExpression)) {
                adapter = AsmClassGenerator.getGroovyObjectField;
            }
            if (usesSuper(expression)) {
                adapter = AsmClassGenerator.getFieldOnSuper;
            }
        }
        this.visitAttributeOrProperty(expression, adapter);
        if (!this.leftHandExpression) {
            this.record(expression.getProperty());
        }
    }
    
    protected boolean isGroovyObject(final Expression objectExpression) {
        return isThisExpression(objectExpression) || (objectExpression.getType().isDerivedFromGroovyObject() && !(objectExpression instanceof ClassExpression));
    }
    
    @Override
    public void visitFieldExpression(final FieldExpression expression) {
        final FieldNode field = expression.getField();
        if (field.isStatic()) {
            if (this.leftHandExpression) {
                this.storeStaticField(expression);
            }
            else {
                this.loadStaticField(expression);
            }
        }
        else {
            if (this.leftHandExpression) {
                this.storeThisInstanceField(expression);
            }
            else {
                this.loadInstanceField(expression);
            }
            this.record(expression);
        }
    }
    
    public void loadStaticField(final FieldExpression fldExp) {
        final FieldNode field = fldExp.getField();
        final boolean holder = field.isHolder() && !this.isInClosureConstructor();
        final ClassNode type = field.getType();
        final String ownerName = field.getOwner().equals(this.classNode) ? this.internalClassName : BytecodeHelper.getClassInternalName(field.getOwner());
        if (holder) {
            this.mv.visitFieldInsn(178, ownerName, fldExp.getFieldName(), BytecodeHelper.getTypeDescription(type));
            this.mv.visitMethodInsn(182, "groovy/lang/Reference", "get", "()Ljava/lang/Object;");
        }
        else {
            this.mv.visitFieldInsn(178, ownerName, fldExp.getFieldName(), BytecodeHelper.getTypeDescription(type));
            if (ClassHelper.isPrimitiveType(type)) {
                this.helper.box(type);
            }
        }
    }
    
    public void loadInstanceField(final FieldExpression fldExp) {
        final FieldNode field = fldExp.getField();
        final boolean holder = field.isHolder() && !this.isInClosureConstructor();
        final ClassNode type = field.getType();
        final String ownerName = field.getOwner().equals(this.classNode) ? this.internalClassName : BytecodeHelper.getClassInternalName(field.getOwner());
        this.mv.visitVarInsn(25, 0);
        this.mv.visitFieldInsn(180, ownerName, fldExp.getFieldName(), BytecodeHelper.getTypeDescription(type));
        if (holder) {
            this.mv.visitMethodInsn(182, "groovy/lang/Reference", "get", "()Ljava/lang/Object;");
        }
        else if (ClassHelper.isPrimitiveType(type)) {
            this.helper.box(type);
        }
    }
    
    public void storeThisInstanceField(final FieldExpression expression) {
        final FieldNode field = expression.getField();
        final boolean holder = field.isHolder() && !this.isInClosureConstructor() && !expression.isUseReferenceDirectly();
        final ClassNode type = field.getType();
        final String ownerName = field.getOwner().equals(this.classNode) ? this.internalClassName : BytecodeHelper.getClassInternalName(field.getOwner());
        if (holder) {
            this.mv.visitVarInsn(25, 0);
            this.mv.visitFieldInsn(180, ownerName, expression.getFieldName(), BytecodeHelper.getTypeDescription(type));
            this.mv.visitInsn(95);
            this.mv.visitMethodInsn(182, "groovy/lang/Reference", "set", "(Ljava/lang/Object;)V");
        }
        else {
            if (this.isInClosureConstructor()) {
                this.helper.doCast(type);
            }
            else if (!ClassHelper.isPrimitiveType(type)) {
                this.doConvertAndCast(type);
            }
            this.mv.visitVarInsn(25, 0);
            this.mv.visitInsn(95);
            this.helper.unbox(type);
            this.helper.putField(field, ownerName);
        }
    }
    
    public void storeStaticField(final FieldExpression expression) {
        final FieldNode field = expression.getField();
        final boolean holder = field.isHolder() && !this.isInClosureConstructor();
        final ClassNode type = field.getType();
        final String ownerName = field.getOwner().equals(this.classNode) ? this.internalClassName : BytecodeHelper.getClassInternalName(field.getOwner());
        if (holder) {
            this.mv.visitFieldInsn(178, ownerName, expression.getFieldName(), BytecodeHelper.getTypeDescription(type));
            this.mv.visitInsn(95);
            this.mv.visitMethodInsn(182, "groovy/lang/Reference", "set", "(Ljava/lang/Object;)V");
        }
        else {
            this.helper.doCast(type);
            this.mv.visitFieldInsn(179, ownerName, expression.getFieldName(), BytecodeHelper.getTypeDescription(type));
        }
    }
    
    protected void visitOuterFieldExpression(final FieldExpression expression, final ClassNode outerClassNode, final int steps, final boolean first) {
        final FieldNode field = expression.getField();
        final boolean isStatic = field.isStatic();
        final int tempIdx = this.compileStack.defineTemporaryVariable(field, this.leftHandExpression && first);
        if (steps > 1 || !isStatic) {
            this.mv.visitVarInsn(25, 0);
            this.mv.visitFieldInsn(180, this.internalClassName, "owner", BytecodeHelper.getTypeDescription(outerClassNode));
        }
        if (steps == 1) {
            final int opcode = this.leftHandExpression ? (isStatic ? 179 : 181) : (isStatic ? 178 : 180);
            final String ownerName = BytecodeHelper.getClassInternalName(outerClassNode);
            if (this.leftHandExpression) {
                this.mv.visitVarInsn(25, tempIdx);
                final boolean holder = field.isHolder() && !this.isInClosureConstructor();
                if (!holder) {
                    this.doConvertAndCast(field.getType());
                }
            }
            this.mv.visitFieldInsn(opcode, ownerName, expression.getFieldName(), BytecodeHelper.getTypeDescription(field.getType()));
            if (!this.leftHandExpression && ClassHelper.isPrimitiveType(field.getType())) {
                this.helper.box(field.getType());
            }
        }
        else {
            this.visitOuterFieldExpression(expression, outerClassNode.getOuterClass(), steps - 1, false);
        }
    }
    
    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        final String variableName = expression.getName();
        ClassNode classNode = this.classNode;
        if (this.isInClosure()) {
            classNode = this.getOutermostClass();
        }
        if (variableName.equals("this")) {
            if (this.isStaticMethod() || (!this.implicitThis && this.isStaticContext())) {
                this.visitClassExpression(new ClassExpression(classNode));
            }
            else {
                this.loadThis();
            }
            return;
        }
        if (variableName.equals("super")) {
            if (this.isStaticMethod()) {
                this.visitClassExpression(new ClassExpression(classNode.getSuperClass()));
            }
            else {
                this.loadThis();
            }
            return;
        }
        final org.codehaus.groovy.classgen.Variable variable = this.compileStack.getVariable(variableName, false);
        if (variable == null) {
            this.processClassVariable(variableName);
        }
        else {
            this.processStackVariable(variable, expression.isUseReferenceDirectly());
        }
        if (!this.leftHandExpression) {
            this.record(expression);
        }
    }
    
    private void loadThis() {
        this.mv.visitVarInsn(25, 0);
        if (!this.implicitThis && this.isInClosure()) {
            this.mv.visitMethodInsn(182, "groovy/lang/Closure", "getThisObject", "()Ljava/lang/Object;");
        }
    }
    
    protected void processStackVariable(final org.codehaus.groovy.classgen.Variable variable, final boolean useReferenceDirectly) {
        if (this.leftHandExpression) {
            this.helper.storeVar(variable);
        }
        else {
            this.helper.loadVar(variable, useReferenceDirectly);
        }
    }
    
    protected void processClassVariable(final String name) {
        if (this.passingParams && this.isInScriptBody()) {
            this.mv.visitTypeInsn(187, "org/codehaus/groovy/runtime/ScriptReference");
            this.mv.visitInsn(89);
            this.loadThisOrOwner();
            this.mv.visitLdcInsn(name);
            this.mv.visitMethodInsn(183, "org/codehaus/groovy/runtime/ScriptReference", "<init>", "(Lgroovy/lang/Script;Ljava/lang/String;)V");
        }
        else {
            final PropertyExpression pexp = new PropertyExpression(VariableExpression.THIS_EXPRESSION, name);
            pexp.setImplicitThis(true);
            this.visitPropertyExpression(pexp);
        }
    }
    
    protected void processFieldAccess(final String name, final FieldNode field, final int steps) {
        final FieldExpression expression = new FieldExpression(field);
        if (steps == 0) {
            this.visitFieldExpression(expression);
        }
        else {
            this.visitOuterFieldExpression(expression, this.classNode.getOuterClass(), steps, true);
        }
    }
    
    protected boolean isInScriptBody() {
        return this.classNode.isScriptBody() || (this.classNode.isScript() && this.methodNode != null && this.methodNode.getName().equals("run"));
    }
    
    protected boolean isPopRequired(final Expression expression) {
        if (expression instanceof MethodCallExpression) {
            return expression.getType() != ClassHelper.VOID_TYPE;
        }
        if (expression instanceof DeclarationExpression) {
            final DeclarationExpression de = (DeclarationExpression)expression;
            return de.getLeftExpression() instanceof TupleExpression;
        }
        if (expression instanceof BinaryExpression) {
            final BinaryExpression binExp = (BinaryExpression)expression;
            binExp.getOperation().getType();
        }
        if (expression instanceof ConstructorCallExpression) {
            final ConstructorCallExpression cce = (ConstructorCallExpression)expression;
            return !cce.isSpecialCall();
        }
        return true;
    }
    
    protected void createInterfaceSyntheticStaticFields() {
        if (this.referencedClasses.isEmpty()) {
            return;
        }
        this.addInnerClass(this.interfaceClassLoadingClass);
        for (final String staticFieldName : this.referencedClasses.keySet()) {
            this.interfaceClassLoadingClass.addField(staticFieldName, 4104, ClassHelper.CLASS_Type, new ClassExpression(this.referencedClasses.get(staticFieldName)));
        }
    }
    
    protected void createSyntheticStaticFields() {
        for (final String staticFieldName : this.referencedClasses.keySet()) {
            final FieldNode fn = this.classNode.getDeclaredField(staticFieldName);
            if (fn != null) {
                final boolean type = fn.getType() == ClassHelper.CLASS_Type;
                final boolean modifiers = fn.getModifiers() == 4104;
                if (!type || !modifiers) {
                    String text = "";
                    if (!type) {
                        text = " with wrong type: " + fn.getType() + " (java.lang.Class needed)";
                    }
                    if (!modifiers) {
                        text = " with wrong modifiers: " + fn.getModifiers() + " (" + 4104 + " needed)";
                    }
                    this.throwException("tried to set a static syntethic field " + staticFieldName + " in " + this.classNode.getName() + " for class resolving, but found alreeady a node of that" + " name " + text);
                }
            }
            else {
                this.cv.visitField(4106, staticFieldName, "Ljava/lang/Class;", null, null);
            }
            (this.mv = this.cv.visitMethod(4106, "$get$" + staticFieldName, "()Ljava/lang/Class;", null, null)).visitCode();
            this.mv.visitFieldInsn(178, this.internalClassName, staticFieldName, "Ljava/lang/Class;");
            this.mv.visitInsn(89);
            final Label l0 = new Label();
            this.mv.visitJumpInsn(199, l0);
            this.mv.visitInsn(87);
            this.mv.visitLdcInsn(BytecodeHelper.getClassLoadingTypeDescription(this.referencedClasses.get(staticFieldName)));
            this.mv.visitMethodInsn(184, this.internalClassName, "class$", "(Ljava/lang/String;)Ljava/lang/Class;");
            this.mv.visitInsn(89);
            this.mv.visitFieldInsn(179, this.internalClassName, staticFieldName, "Ljava/lang/Class;");
            this.mv.visitLabel(l0);
            this.mv.visitInsn(176);
            this.mv.visitMaxs(0, 0);
            this.mv.visitEnd();
        }
        this.mv = this.cv.visitMethod(4104, "class$", "(Ljava/lang/String;)Ljava/lang/Class;", null, null);
        final Label l2 = new Label();
        this.mv.visitLabel(l2);
        this.mv.visitVarInsn(25, 0);
        this.mv.visitMethodInsn(184, "java/lang/Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
        final Label l3 = new Label();
        this.mv.visitLabel(l3);
        this.mv.visitInsn(176);
        final Label l4 = new Label();
        this.mv.visitLabel(l4);
        this.mv.visitVarInsn(58, 1);
        this.mv.visitTypeInsn(187, "java/lang/NoClassDefFoundError");
        this.mv.visitInsn(89);
        this.mv.visitVarInsn(25, 1);
        this.mv.visitMethodInsn(182, "java/lang/ClassNotFoundException", "getMessage", "()Ljava/lang/String;");
        this.mv.visitMethodInsn(183, "java/lang/NoClassDefFoundError", "<init>", "(Ljava/lang/String;)V");
        this.mv.visitInsn(191);
        this.mv.visitTryCatchBlock(l2, l4, l4, "java/lang/ClassNotFoundException");
        this.mv.visitMaxs(3, 2);
    }
    
    @Override
    public void visitClassExpression(final ClassExpression expression) {
        final ClassNode type = expression.getType();
        if (ClassHelper.isPrimitiveType(type)) {
            final ClassNode objectType = ClassHelper.getWrapper(type);
            this.mv.visitFieldInsn(178, BytecodeHelper.getClassInternalName(objectType), "TYPE", "Ljava/lang/Class;");
        }
        else {
            final String staticFieldName = getStaticFieldName(type);
            this.referencedClasses.put(staticFieldName, type);
            String internalClassName = this.internalClassName;
            if (this.classNode.isInterface()) {
                internalClassName = BytecodeHelper.getClassInternalName(this.interfaceClassLoadingClass);
                this.mv.visitFieldInsn(178, internalClassName, staticFieldName, "Ljava/lang/Class;");
            }
            else {
                this.mv.visitMethodInsn(184, internalClassName, "$get$" + staticFieldName, "()Ljava/lang/Class;");
            }
        }
    }
    
    @Override
    public void visitRangeExpression(final RangeExpression expression) {
        expression.getFrom().visit(this);
        expression.getTo().visit(this);
        this.helper.pushConstant(expression.isInclusive());
        AsmClassGenerator.createRangeMethod.call(this.mv);
    }
    
    @Override
    public void visitMapEntryExpression(final MapEntryExpression expression) {
        throw new GroovyBugError("MapEntryExpression should not be visited here");
    }
    
    @Override
    public void visitMapExpression(final MapExpression expression) {
        final List entries = expression.getMapEntryExpressions();
        final int size = entries.size();
        this.helper.pushConstant(size * 2);
        this.mv.visitTypeInsn(189, "java/lang/Object");
        int i = 0;
        for (final Object object : entries) {
            final MapEntryExpression entry = (MapEntryExpression)object;
            this.mv.visitInsn(89);
            this.helper.pushConstant(i++);
            this.visitAndAutoboxBoolean(entry.getKeyExpression());
            this.mv.visitInsn(83);
            this.mv.visitInsn(89);
            this.helper.pushConstant(i++);
            this.visitAndAutoboxBoolean(entry.getValueExpression());
            this.mv.visitInsn(83);
        }
        AsmClassGenerator.createMapMethod.call(this.mv);
    }
    
    @Override
    public void visitArgumentlistExpression(final ArgumentListExpression ale) {
        if (containsSpreadExpression(ale)) {
            this.despreadList(ale.getExpressions(), true);
        }
        else {
            this.visitTupleExpression(ale, true);
        }
    }
    
    @Override
    public void visitTupleExpression(final TupleExpression expression) {
        this.visitTupleExpression(expression, false);
    }
    
    private void visitTupleExpression(final TupleExpression expression, final boolean useWrapper) {
        final int size = expression.getExpressions().size();
        this.helper.pushConstant(size);
        this.mv.visitTypeInsn(189, "java/lang/Object");
        for (int i = 0; i < size; ++i) {
            this.mv.visitInsn(89);
            this.helper.pushConstant(i);
            final Expression argument = expression.getExpression(i);
            this.visitAndAutoboxBoolean(argument);
            if (useWrapper && argument instanceof CastExpression) {
                this.loadWrapper(argument);
            }
            this.mv.visitInsn(83);
        }
    }
    
    private void loadWrapper(final Expression argument) {
        final ClassNode goalClass = argument.getType();
        this.visitClassExpression(new ClassExpression(goalClass));
        if (goalClass.isDerivedFromGroovyObject()) {
            AsmClassGenerator.createGroovyObjectWrapperMethod.call(this.mv);
        }
        else {
            AsmClassGenerator.createPojoWrapperMethod.call(this.mv);
        }
    }
    
    @Override
    public void visitArrayExpression(final ArrayExpression expression) {
        final ClassNode elementType = expression.getElementType();
        String arrayTypeName = BytecodeHelper.getClassInternalName(elementType);
        final List<Expression> sizeExpression = expression.getSizeExpression();
        int size = 0;
        int dimensions = 0;
        if (sizeExpression != null) {
            for (final Expression element : sizeExpression) {
                if (element == ConstantExpression.EMPTY_EXPRESSION) {
                    break;
                }
                ++dimensions;
                this.visitAndAutoboxBoolean(element);
                this.helper.unbox(Integer.TYPE);
            }
        }
        else {
            size = expression.getExpressions().size();
            this.helper.pushConstant(size);
        }
        int storeIns = 83;
        if (sizeExpression != null) {
            arrayTypeName = BytecodeHelper.getTypeDescription(expression.getType());
            this.mv.visitMultiANewArrayInsn(arrayTypeName, dimensions);
        }
        else if (ClassHelper.isPrimitiveType(elementType)) {
            int primType = 0;
            if (elementType == ClassHelper.boolean_TYPE) {
                primType = 4;
                storeIns = 84;
            }
            else if (elementType == ClassHelper.char_TYPE) {
                primType = 5;
                storeIns = 85;
            }
            else if (elementType == ClassHelper.float_TYPE) {
                primType = 6;
                storeIns = 81;
            }
            else if (elementType == ClassHelper.double_TYPE) {
                primType = 7;
                storeIns = 82;
            }
            else if (elementType == ClassHelper.byte_TYPE) {
                primType = 8;
                storeIns = 84;
            }
            else if (elementType == ClassHelper.short_TYPE) {
                primType = 9;
                storeIns = 86;
            }
            else if (elementType == ClassHelper.int_TYPE) {
                primType = 10;
                storeIns = 79;
            }
            else if (elementType == ClassHelper.long_TYPE) {
                primType = 11;
                storeIns = 80;
            }
            this.mv.visitIntInsn(188, primType);
        }
        else {
            this.mv.visitTypeInsn(189, arrayTypeName);
        }
        for (int i = 0; i < size; ++i) {
            this.mv.visitInsn(89);
            this.helper.pushConstant(i);
            final Expression elementExpression = expression.getExpression(i);
            if (elementExpression == null) {
                ConstantExpression.NULL.visit(this);
            }
            else if (!elementType.equals(elementExpression.getType())) {
                this.visitCastExpression(new CastExpression(elementType, elementExpression, true));
            }
            else {
                this.visitAndAutoboxBoolean(elementExpression);
            }
            this.mv.visitInsn(storeIns);
        }
        if (sizeExpression == null && ClassHelper.isPrimitiveType(elementType)) {
            final int par = this.compileStack.defineTemporaryVariable("par", true);
            this.mv.visitVarInsn(25, par);
        }
    }
    
    @Override
    public void visitClosureListExpression(final ClosureListExpression expression) {
        this.compileStack.pushVariableScope(expression.getVariableScope());
        final List<Expression> expressions = expression.getExpressions();
        final int size = expressions.size();
        final LinkedList<DeclarationExpression> declarations = new LinkedList<DeclarationExpression>();
        for (int i = 0; i < size; ++i) {
            final Expression expr = expressions.get(i);
            if (expr instanceof DeclarationExpression) {
                declarations.add((DeclarationExpression)expr);
                final DeclarationExpression de = (DeclarationExpression)expr;
                final BinaryExpression be = new BinaryExpression(de.getLeftExpression(), de.getOperation(), de.getRightExpression());
                expressions.set(i, be);
                de.setRightExpression(ConstantExpression.NULL);
                this.visitDeclarationExpression(de);
            }
        }
        final LinkedList instructions = new LinkedList();
        final BytecodeSequence seq = new BytecodeSequence(instructions);
        final BlockStatement bs = new BlockStatement();
        bs.addStatement(seq);
        final Parameter closureIndex = new Parameter(ClassHelper.int_TYPE, "__closureIndex");
        final ClosureExpression ce = new ClosureExpression(new Parameter[] { closureIndex }, bs);
        ce.setVariableScope(expression.getVariableScope());
        instructions.add(ConstantExpression.NULL);
        final Label dflt = new Label();
        final Label tableEnd = new Label();
        final Label[] labels = new Label[size];
        instructions.add(new BytecodeInstruction() {
            @Override
            public void visit(final MethodVisitor mv) {
                mv.visitVarInsn(21, 1);
                mv.visitTableSwitchInsn(0, size - 1, dflt, labels);
            }
        });
        for (int j = 0; j < size; ++j) {
            final Label label = new Label();
            final Object expr2 = expressions.get(j);
            final boolean isStatement = expr2 instanceof Statement;
            labels[j] = label;
            instructions.add(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitLabel(label);
                    if (!isStatement) {
                        mv.visitInsn(87);
                    }
                }
            });
            instructions.add(expr2);
            instructions.add(new BytecodeInstruction() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitJumpInsn(167, tableEnd);
                }
            });
        }
        instructions.add(new BytecodeInstruction() {
            @Override
            public void visit(final MethodVisitor mv) {
                mv.visitLabel(dflt);
            }
        });
        final ConstantExpression text = new ConstantExpression("invalid index for closure");
        final ConstructorCallExpression cce = new ConstructorCallExpression(ClassHelper.make(IllegalArgumentException.class), text);
        final ThrowStatement ts = new ThrowStatement(cce);
        instructions.add(ts);
        instructions.add(new BytecodeInstruction() {
            @Override
            public void visit(final MethodVisitor mv) {
                mv.visitLabel(tableEnd);
                mv.visitInsn(176);
            }
        });
        this.visitClosureExpression(ce);
        this.helper.pushConstant(size);
        this.mv.visitTypeInsn(189, "java/lang/Object");
        final int listArrayVar = this.compileStack.defineTemporaryVariable("_listOfClosures", true);
        for (int k = 0; k < size; ++k) {
            this.mv.visitTypeInsn(187, "org/codehaus/groovy/runtime/CurriedClosure");
            this.mv.visitInsn(92);
            this.mv.visitInsn(95);
            this.helper.pushConstant(k);
            this.mv.visitMethodInsn(183, "org/codehaus/groovy/runtime/CurriedClosure", "<init>", "(Lgroovy/lang/Closure;I)V");
            this.mv.visitVarInsn(25, listArrayVar);
            this.mv.visitInsn(95);
            this.helper.pushConstant(k);
            this.mv.visitInsn(95);
            this.mv.visitInsn(83);
        }
        this.mv.visitInsn(87);
        this.mv.visitVarInsn(25, listArrayVar);
        AsmClassGenerator.createListMethod.call(this.mv);
        this.compileStack.removeVar(listArrayVar);
        this.compileStack.pop();
    }
    
    @Override
    public void visitBytecodeSequence(final BytecodeSequence bytecodeSequence) {
        final List instructions = bytecodeSequence.getInstructions();
        for (final Object part : instructions) {
            if (part == EmptyExpression.INSTANCE) {
                this.mv.visitInsn(1);
            }
            else if (part instanceof Expression) {
                this.visitAndAutoboxBoolean((Expression)part);
            }
            else if (part instanceof Statement) {
                final Statement stm = (Statement)part;
                stm.visit(this);
                this.mv.visitInsn(1);
            }
            else {
                final BytecodeInstruction runner = (BytecodeInstruction)part;
                runner.visit(this.mv);
            }
        }
    }
    
    @Override
    public void visitListExpression(final ListExpression expression) {
        this.onLineNumber(expression, "ListExpression");
        final int size = expression.getExpressions().size();
        final boolean containsSpreadExpression = containsSpreadExpression(expression);
        if (!containsSpreadExpression) {
            this.helper.pushConstant(size);
            this.mv.visitTypeInsn(189, "java/lang/Object");
            for (int i = 0; i < size; ++i) {
                this.mv.visitInsn(89);
                this.helper.pushConstant(i);
                this.visitAndAutoboxBoolean(expression.getExpression(i));
                this.mv.visitInsn(83);
            }
        }
        else {
            this.despreadList(expression.getExpressions(), false);
        }
        AsmClassGenerator.createListMethod.call(this.mv);
    }
    
    @Override
    public void visitGStringExpression(final GStringExpression expression) {
        this.mv.visitTypeInsn(187, "org/codehaus/groovy/runtime/GStringImpl");
        this.mv.visitInsn(89);
        int size = expression.getValues().size();
        this.helper.pushConstant(size);
        this.mv.visitTypeInsn(189, "java/lang/Object");
        for (int i = 0; i < size; ++i) {
            this.mv.visitInsn(89);
            this.helper.pushConstant(i);
            this.visitAndAutoboxBoolean(expression.getValue(i));
            this.mv.visitInsn(83);
        }
        final List strings = expression.getStrings();
        size = strings.size();
        this.helper.pushConstant(size);
        this.mv.visitTypeInsn(189, "java/lang/String");
        for (int j = 0; j < size; ++j) {
            this.mv.visitInsn(89);
            this.helper.pushConstant(j);
            this.mv.visitLdcInsn(strings.get(j).getValue());
            this.mv.visitInsn(83);
        }
        this.mv.visitMethodInsn(183, "org/codehaus/groovy/runtime/GStringImpl", "<init>", "([Ljava/lang/Object;[Ljava/lang/String;)V");
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
    }
    
    private void visitAnnotations(final AnnotatedNode targetNode, final Object visitor) {
        for (final AnnotationNode an : targetNode.getAnnotations()) {
            if (an.isBuiltIn()) {
                continue;
            }
            if (an.hasSourceRetention()) {
                continue;
            }
            final AnnotationVisitor av = this.getAnnotationVisitor(targetNode, an, visitor);
            this.visitAnnotationAttributes(an, av);
            av.visitEnd();
        }
    }
    
    private void visitParameterAnnotations(final Parameter parameter, final int paramNumber, final MethodVisitor mv) {
        for (final AnnotationNode an : parameter.getAnnotations()) {
            if (an.isBuiltIn()) {
                continue;
            }
            if (an.hasSourceRetention()) {
                continue;
            }
            final String annotationDescriptor = BytecodeHelper.getTypeDescription(an.getClassNode());
            final AnnotationVisitor av = mv.visitParameterAnnotation(paramNumber, annotationDescriptor, an.hasRuntimeRetention());
            this.visitAnnotationAttributes(an, av);
            av.visitEnd();
        }
    }
    
    private AnnotationVisitor getAnnotationVisitor(final AnnotatedNode targetNode, final AnnotationNode an, final Object visitor) {
        final String annotationDescriptor = BytecodeHelper.getTypeDescription(an.getClassNode());
        if (targetNode instanceof MethodNode) {
            return ((MethodVisitor)visitor).visitAnnotation(annotationDescriptor, an.hasRuntimeRetention());
        }
        if (targetNode instanceof FieldNode) {
            return ((FieldVisitor)visitor).visitAnnotation(annotationDescriptor, an.hasRuntimeRetention());
        }
        if (targetNode instanceof ClassNode) {
            return ((ClassVisitor)visitor).visitAnnotation(annotationDescriptor, an.hasRuntimeRetention());
        }
        this.throwException("Cannot create an AnnotationVisitor. Please report Groovy bug");
        return null;
    }
    
    private void visitAnnotationAttributes(final AnnotationNode an, final AnnotationVisitor av) {
        final Map<String, Object> constantAttrs = new HashMap<String, Object>();
        final Map<String, PropertyExpression> enumAttrs = new HashMap<String, PropertyExpression>();
        final Map<String, Object> atAttrs = new HashMap<String, Object>();
        final Map<String, ListExpression> arrayAttrs = new HashMap<String, ListExpression>();
        for (final String name : an.getMembers().keySet()) {
            final Expression expr = an.getMember(name);
            if (expr instanceof AnnotationConstantExpression) {
                atAttrs.put(name, ((AnnotationConstantExpression)expr).getValue());
            }
            else if (expr instanceof ConstantExpression) {
                constantAttrs.put(name, ((ConstantExpression)expr).getValue());
            }
            else if (expr instanceof ClassExpression) {
                constantAttrs.put(name, Type.getType(BytecodeHelper.getTypeDescription(expr.getType())));
            }
            else if (expr instanceof PropertyExpression) {
                enumAttrs.put(name, (PropertyExpression)expr);
            }
            else {
                if (!(expr instanceof ListExpression)) {
                    continue;
                }
                arrayAttrs.put(name, (ListExpression)expr);
            }
        }
        for (final Map.Entry entry : constantAttrs.entrySet()) {
            av.visit(entry.getKey(), entry.getValue());
        }
        for (final Map.Entry entry : enumAttrs.entrySet()) {
            final PropertyExpression propExp = entry.getValue();
            av.visitEnum(entry.getKey(), BytecodeHelper.getTypeDescription(propExp.getObjectExpression().getType()), String.valueOf(((ConstantExpression)propExp.getProperty()).getValue()));
        }
        for (final Map.Entry entry : atAttrs.entrySet()) {
            final AnnotationNode atNode = entry.getValue();
            final AnnotationVisitor av2 = av.visitAnnotation(entry.getKey(), BytecodeHelper.getTypeDescription(atNode.getClassNode()));
            this.visitAnnotationAttributes(atNode, av2);
            av2.visitEnd();
        }
        this.visitArrayAttributes(an, arrayAttrs, av);
    }
    
    private void visitArrayAttributes(final AnnotationNode an, final Map<String, ListExpression> arrayAttr, final AnnotationVisitor av) {
        if (arrayAttr.isEmpty()) {
            return;
        }
        for (final Map.Entry entry : arrayAttr.entrySet()) {
            final AnnotationVisitor av2 = av.visitArray(entry.getKey());
            final List<Expression> values = entry.getValue().getExpressions();
            if (!values.isEmpty()) {
                final int arrayElementType = this.determineCommonArrayType(values);
                for (final Expression exprChild : values) {
                    this.visitAnnotationArrayElement(exprChild, arrayElementType, av2);
                }
            }
            av2.visitEnd();
        }
    }
    
    private int determineCommonArrayType(final List values) {
        final Expression expr = values.get(0);
        int arrayElementType = -1;
        if (expr instanceof AnnotationConstantExpression) {
            arrayElementType = 1;
        }
        else if (expr instanceof ConstantExpression) {
            arrayElementType = 2;
        }
        else if (expr instanceof ClassExpression) {
            arrayElementType = 3;
        }
        else if (expr instanceof PropertyExpression) {
            arrayElementType = 4;
        }
        return arrayElementType;
    }
    
    private void visitAnnotationArrayElement(final Expression expr, final int arrayElementType, final AnnotationVisitor av) {
        switch (arrayElementType) {
            case 1: {
                final AnnotationNode atAttr = (AnnotationNode)((AnnotationConstantExpression)expr).getValue();
                final AnnotationVisitor av2 = av.visitAnnotation(null, BytecodeHelper.getTypeDescription(atAttr.getClassNode()));
                this.visitAnnotationAttributes(atAttr, av2);
                av2.visitEnd();
                break;
            }
            case 2: {
                av.visit(null, ((ConstantExpression)expr).getValue());
                break;
            }
            case 3: {
                av.visit(null, Type.getType(BytecodeHelper.getTypeDescription(expr.getType())));
                break;
            }
            case 4: {
                final PropertyExpression propExpr = (PropertyExpression)expr;
                av.visitEnum(null, BytecodeHelper.getTypeDescription(propExpr.getObjectExpression().getType()), String.valueOf(((ConstantExpression)propExpr.getProperty()).getValue()));
                break;
            }
        }
    }
    
    protected boolean addInnerClass(final ClassNode innerClass) {
        innerClass.setModule(this.classNode.getModule());
        return this.innerClasses.add(innerClass);
    }
    
    protected ClassNode createClosureClass(final ClosureExpression expression) {
        final ClassNode outerClass = this.getOutermostClass();
        final String name = outerClass.getName() + "$" + this.context.getNextClosureInnerName(outerClass, this.classNode, this.methodNode);
        final boolean staticMethodOrInStaticClass = this.isStaticMethod() || this.classNode.isStaticClass();
        Parameter[] parameters = expression.getParameters();
        if (parameters == null) {
            parameters = Parameter.EMPTY_ARRAY;
        }
        else if (parameters.length == 0) {
            final Parameter it = new Parameter(ClassHelper.OBJECT_TYPE, "it", ConstantExpression.NULL);
            parameters = new Parameter[] { it };
            final Variable ref = expression.getVariableScope().getDeclaredVariable("it");
            if (ref != null) {
                it.setClosureSharedVariable(ref.isClosureSharedVariable());
            }
        }
        final Parameter[] localVariableParams = this.getClosureSharedVariables(expression);
        this.removeInitialValues(localVariableParams);
        final InnerClassNode answer = new InnerClassNode(outerClass, name, 0, ClassHelper.CLOSURE_TYPE);
        answer.setEnclosingMethod(this.methodNode);
        answer.setSynthetic(true);
        answer.setUsingGenerics(outerClass.isUsingGenerics());
        if (staticMethodOrInStaticClass) {
            answer.setStaticClass(true);
        }
        if (this.isInScriptBody()) {
            answer.setScriptBody(true);
        }
        final MethodNode method = answer.addMethod("doCall", 1, ClassHelper.OBJECT_TYPE, parameters, ClassNode.EMPTY_ARRAY, expression.getCode());
        method.setSourcePosition(expression);
        final VariableScope varScope = expression.getVariableScope();
        if (varScope == null) {
            throw new RuntimeException("Must have a VariableScope by now! for expression: " + expression + " class: " + name);
        }
        method.setVariableScope(varScope.copy());
        if (parameters.length > 1 || (parameters.length == 1 && parameters[0].getType() != null && parameters[0].getType() != ClassHelper.OBJECT_TYPE)) {
            final MethodNode call = answer.addMethod("call", 1, ClassHelper.OBJECT_TYPE, parameters, ClassNode.EMPTY_ARRAY, new ReturnStatement(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "doCall", new ArgumentListExpression(parameters))));
            call.setSourcePosition(expression);
        }
        final BlockStatement block = new BlockStatement();
        final VariableExpression outer = new VariableExpression("_outerInstance");
        outer.setSourcePosition(expression);
        block.getVariableScope().putReferencedLocalVariable(outer);
        final VariableExpression thisObject = new VariableExpression("_thisObject");
        thisObject.setSourcePosition(expression);
        block.getVariableScope().putReferencedLocalVariable(thisObject);
        final TupleExpression conArgs = new TupleExpression(outer, thisObject);
        block.addStatement(new ExpressionStatement(new ConstructorCallExpression(ClassNode.SUPER, conArgs)));
        for (final Parameter param : localVariableParams) {
            final String paramName = param.getName();
            Expression initialValue = null;
            ClassNode type = param.getType();
            FieldNode paramField = null;
            initialValue = new VariableExpression(paramName);
            final ClassNode realType = type;
            type = ClassHelper.makeReference();
            param.setType(ClassHelper.makeReference());
            paramField = answer.addField(paramName, 2, type, initialValue);
            paramField.setHolder(true);
            final String methodName = Verifier.capitalize(paramName);
            final Expression fieldExp = new FieldExpression(paramField);
            answer.addMethod("get" + methodName, 1, realType, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, new ReturnStatement(fieldExp));
        }
        final Parameter[] params = new Parameter[2 + localVariableParams.length];
        params[0] = new Parameter(ClassHelper.OBJECT_TYPE, "_outerInstance");
        params[1] = new Parameter(ClassHelper.OBJECT_TYPE, "_thisObject");
        System.arraycopy(localVariableParams, 0, params, 2, localVariableParams.length);
        final ASTNode sn = answer.addConstructor(1, params, ClassNode.EMPTY_ARRAY, block);
        sn.setSourcePosition(expression);
        return answer;
    }
    
    private void removeInitialValues(final Parameter[] params) {
        for (int i = 0; i < params.length; ++i) {
            if (params[i].hasInitialExpression()) {
                params[i] = new Parameter(params[i].getType(), params[i].getName());
            }
        }
    }
    
    protected Parameter[] getClosureSharedVariables(final ClosureExpression ce) {
        final VariableScope scope = ce.getVariableScope();
        final Parameter[] ret = new Parameter[scope.getReferencedLocalVariablesCount()];
        int index = 0;
        final Iterator iter = scope.getReferencedLocalVariablesIterator();
        while (iter.hasNext()) {
            final Variable element = iter.next();
            final Parameter p = new Parameter(element.getType(), element.getName());
            ret[index] = p;
            ++index;
        }
        return ret;
    }
    
    protected ClassNode getOutermostClass() {
        if (this.outermostClass == null) {
            this.outermostClass = this.classNode;
            while (this.outermostClass instanceof InnerClassNode) {
                this.outermostClass = this.outermostClass.getOuterClass();
            }
        }
        return this.outermostClass;
    }
    
    protected void doConvertAndCast(final ClassNode type) {
        this.doConvertAndCast(type, false);
    }
    
    protected void doConvertAndCast(final ClassNode type, final boolean coerce) {
        if (type == ClassHelper.OBJECT_TYPE) {
            return;
        }
        if ((this.rightHandType == null || !this.rightHandType.isDerivedFrom(type) || !this.rightHandType.implementsInterface(type)) && this.isValidTypeForCast(type)) {
            this.visitClassExpression(new ClassExpression(type));
            if (coerce) {
                AsmClassGenerator.asTypeMethod.call(this.mv);
            }
            else {
                AsmClassGenerator.castToTypeMethod.call(this.mv);
            }
        }
        this.helper.doCast(type);
    }
    
    protected void evaluateLogicalOrExpression(final BinaryExpression expression) {
        this.visitBooleanExpression(new BooleanExpression(expression.getLeftExpression()));
        final Label l0 = new Label();
        final Label l2 = new Label();
        this.mv.visitJumpInsn(153, l0);
        this.mv.visitLabel(l2);
        this.visitConstantExpression(ConstantExpression.TRUE);
        final Label l3 = new Label();
        this.mv.visitJumpInsn(167, l3);
        this.mv.visitLabel(l0);
        this.visitBooleanExpression(new BooleanExpression(expression.getRightExpression()));
        this.mv.visitJumpInsn(154, l2);
        this.visitConstantExpression(ConstantExpression.FALSE);
        this.mv.visitLabel(l3);
    }
    
    protected void evaluateLogicalAndExpression(final BinaryExpression expression) {
        this.visitBooleanExpression(new BooleanExpression(expression.getLeftExpression()));
        final Label l0 = new Label();
        this.mv.visitJumpInsn(153, l0);
        this.visitBooleanExpression(new BooleanExpression(expression.getRightExpression()));
        this.mv.visitJumpInsn(153, l0);
        this.visitConstantExpression(ConstantExpression.TRUE);
        final Label l2 = new Label();
        this.mv.visitJumpInsn(167, l2);
        this.mv.visitLabel(l0);
        this.visitConstantExpression(ConstantExpression.FALSE);
        this.mv.visitLabel(l2);
    }
    
    protected void evaluateBinaryExpression(final String method, final BinaryExpression expression) {
        this.makeBinopCallSite(expression.getLeftExpression(), method, expression.getRightExpression());
    }
    
    protected void evaluateCompareTo(final BinaryExpression expression) {
        final Expression leftExpression = expression.getLeftExpression();
        leftExpression.visit(this);
        if (this.isComparisonExpression(leftExpression)) {
            this.helper.boxBoolean();
        }
        final Expression rightExpression = expression.getRightExpression();
        rightExpression.visit(this);
        if (this.isComparisonExpression(rightExpression)) {
            this.helper.boxBoolean();
        }
        AsmClassGenerator.compareToMethod.call(this.mv);
    }
    
    protected void evaluateBinaryExpressionWithAssignment(final String method, final BinaryExpression expression) {
        final Expression leftExpression = expression.getLeftExpression();
        if (leftExpression instanceof BinaryExpression) {
            final BinaryExpression leftBinExpr = (BinaryExpression)leftExpression;
            if (leftBinExpr.getOperation().getType() == 30) {
                this.prepareCallSite("putAt");
                this.prepareCallSite(method);
                this.prepareCallSite("getAt");
                this.visitAndAutoboxBoolean(leftBinExpr.getLeftExpression());
                this.visitAndAutoboxBoolean(leftBinExpr.getRightExpression());
                this.mv.visitInsn(94);
                this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "call", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
                this.visitAndAutoboxBoolean(expression.getRightExpression());
                this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "call", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
                final int resultVar = this.compileStack.defineTemporaryVariable("$result", true);
                this.mv.visitVarInsn(25, resultVar);
                this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "call", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
                this.mv.visitInsn(87);
                this.mv.visitVarInsn(25, resultVar);
                this.compileStack.removeVar(resultVar);
                return;
            }
        }
        this.evaluateBinaryExpression(method, expression);
        this.mv.visitInsn(89);
        this.doConvertAndCast(ClassHelper.getWrapper(leftExpression.getType()));
        this.leftHandExpression = true;
        this.evaluateExpression(leftExpression);
        this.leftHandExpression = false;
    }
    
    private void evaluateBinaryExpression(final MethodCaller compareMethod, final BinaryExpression expression) {
        final Expression leftExp = expression.getLeftExpression();
        final Expression rightExp = expression.getRightExpression();
        this.load(leftExp);
        this.load(rightExp);
        compareMethod.call(this.mv);
    }
    
    protected void evaluateEqual(final BinaryExpression expression, final boolean defineVariable) {
        final Expression leftExpression = expression.getLeftExpression();
        if (leftExpression instanceof BinaryExpression) {
            final BinaryExpression leftBinExpr = (BinaryExpression)leftExpression;
            if (leftBinExpr.getOperation().getType() == 30) {
                this.prepareCallSite("putAt");
                this.visitAndAutoboxBoolean(leftBinExpr.getLeftExpression());
                this.visitAndAutoboxBoolean(leftBinExpr.getRightExpression());
                this.visitAndAutoboxBoolean(expression.getRightExpression());
                final int resultVar = this.compileStack.defineTemporaryVariable("$result", true);
                this.mv.visitVarInsn(25, resultVar);
                this.mv.visitMethodInsn(185, "org/codehaus/groovy/runtime/callsite/CallSite", "call", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
                this.mv.visitInsn(87);
                this.mv.visitVarInsn(25, resultVar);
                this.compileStack.removeVar(resultVar);
                return;
            }
        }
        final Expression rightExpression = expression.getRightExpression();
        if (!(leftExpression instanceof TupleExpression)) {
            ClassNode type = null;
            if (expression instanceof DeclarationExpression) {
                type = leftExpression.getType();
            }
            else {
                type = this.getLHSType(leftExpression);
            }
            this.assignmentCastAndVisit(type, rightExpression);
        }
        else {
            this.visitAndAutoboxBoolean(rightExpression);
        }
        this.rightHandType = rightExpression.getType();
        this.leftHandExpression = true;
        if (leftExpression instanceof TupleExpression) {
            final TupleExpression tuple = (TupleExpression)leftExpression;
            int i = 0;
            final Expression lhsExpr = new BytecodeExpression() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitInsn(95);
                    mv.visitInsn(90);
                }
            };
            for (final Expression e : tuple.getExpressions()) {
                final VariableExpression var = (VariableExpression)e;
                final MethodCallExpression call = new MethodCallExpression(lhsExpr, "getAt", new ArgumentListExpression(new ConstantExpression(i)));
                final ClassNode type2 = this.getLHSType(var);
                this.assignmentCastAndVisit(type2, call);
                ++i;
                if (defineVariable) {
                    this.compileStack.defineVariable(var, true);
                }
                else {
                    this.visitVariableExpression(var);
                }
            }
        }
        else if (defineVariable) {
            final VariableExpression var2 = (VariableExpression)leftExpression;
            this.compileStack.defineVariable(var2, true);
        }
        else {
            this.mv.visitInsn(89);
            leftExpression.visit(this);
        }
        this.rightHandType = null;
        this.leftHandExpression = false;
    }
    
    private void assignmentCastAndVisit(final ClassNode type, final Expression rightExpression) {
        if (ClassHelper.isPrimitiveType(type)) {
            this.visitAndAutoboxBoolean(rightExpression);
        }
        else if (!rightExpression.getType().isDerivedFrom(type)) {
            this.visitCastExpression(new CastExpression(type, rightExpression));
        }
        else {
            this.visitAndAutoboxBoolean(rightExpression);
        }
    }
    
    protected ClassNode getLHSType(final Expression leftExpression) {
        if (leftExpression instanceof VariableExpression) {
            final VariableExpression varExp = (VariableExpression)leftExpression;
            ClassNode type = varExp.getType();
            if (this.isValidTypeForCast(type)) {
                return type;
            }
            final String variableName = varExp.getName();
            final org.codehaus.groovy.classgen.Variable variable = this.compileStack.getVariable(variableName, false);
            if (variable != null) {
                if (variable.isHolder()) {
                    return type;
                }
                if (variable.isProperty()) {
                    return variable.getType();
                }
                type = variable.getType();
                if (this.isValidTypeForCast(type)) {
                    return type;
                }
            }
            else {
                FieldNode field = this.classNode.getDeclaredField(variableName);
                if (field == null) {
                    field = this.classNode.getOuterField(variableName);
                }
                if (field != null) {
                    type = field.getType();
                    if (!field.isHolder() && this.isValidTypeForCast(type)) {
                        return type;
                    }
                }
            }
        }
        else if (leftExpression instanceof FieldExpression) {
            final FieldExpression fieldExp = (FieldExpression)leftExpression;
            final ClassNode type = fieldExp.getType();
            if (this.isValidTypeForCast(type)) {
                return type;
            }
        }
        return leftExpression.getType();
    }
    
    protected boolean isValidTypeForCast(final ClassNode type) {
        return type != ClassHelper.DYNAMIC_TYPE && type != ClassHelper.REFERENCE_TYPE;
    }
    
    @Override
    public void visitBytecodeExpression(final BytecodeExpression cle) {
        cle.visit(this.mv);
    }
    
    protected void visitAndAutoboxBoolean(final Expression expression) {
        expression.visit(this);
        if (this.isComparisonExpression(expression)) {
            this.helper.boxBoolean();
        }
    }
    
    private void execMethodAndStoreForSubscriptOperator(final String method, final Expression expression) {
        this.execMethodAndStoreForSubscriptOperator(method, expression, null);
    }
    
    private void execMethodAndStoreForSubscriptOperator(final String method, final Expression expression, final Expression getAtResultExp) {
        this.makeCallSite((getAtResultExp == null) ? expression : getAtResultExp, method, MethodCallExpression.NO_ARGUMENTS, false, false, false, false);
        if (expression instanceof BinaryExpression) {
            final BinaryExpression be = (BinaryExpression)expression;
            if (be.getOperation().getType() == 30) {
                this.mv.visitInsn(89);
                final int resultIdx = this.compileStack.defineTemporaryVariable("postfix_" + method, true);
                final BytecodeExpression result = new BytecodeExpression() {
                    @Override
                    public void visit(final MethodVisitor mv) {
                        mv.visitVarInsn(25, resultIdx);
                    }
                };
                final TupleExpression args = new ArgumentListExpression();
                args.addExpression(be.getRightExpression());
                args.addExpression(result);
                this.makeCallSite(be.getLeftExpression(), "putAt", args, false, false, false, false);
                this.mv.visitInsn(87);
                this.compileStack.removeVar(resultIdx);
            }
        }
        if (expression instanceof VariableExpression || expression instanceof FieldExpression || expression instanceof PropertyExpression) {
            this.mv.visitInsn(89);
            this.leftHandExpression = true;
            expression.visit(this);
            this.leftHandExpression = false;
        }
    }
    
    protected void evaluatePrefixMethod(final String method, final Expression expression) {
        this.execMethodAndStoreForSubscriptOperator(method, expression);
    }
    
    protected void evaluatePostfixMethod(final String method, final Expression expression) {
        boolean getAtOp = false;
        BinaryExpression be = null;
        Expression getAtResultExp = null;
        final String varName = "tmp_postfix_" + method;
        final int idx = this.compileStack.defineTemporaryVariable(varName, false);
        if (expression instanceof BinaryExpression) {
            be = (BinaryExpression)expression;
            if (be.getOperation().getType() == 30) {
                getAtOp = true;
                be.getRightExpression().visit(this);
                this.mv.visitVarInsn(58, idx);
                final BytecodeExpression newRightExp = new BytecodeExpression() {
                    @Override
                    public void visit(final MethodVisitor mv) {
                        mv.visitVarInsn(25, idx);
                    }
                };
                be.setRightExpression(newRightExp);
            }
        }
        expression.visit(this);
        final int tempIdx = this.compileStack.defineTemporaryVariable("postfix_" + method, true);
        if (getAtOp) {
            getAtResultExp = new BytecodeExpression() {
                @Override
                public void visit(final MethodVisitor mv) {
                    mv.visitVarInsn(25, tempIdx);
                }
            };
        }
        this.execMethodAndStoreForSubscriptOperator(method, expression, getAtResultExp);
        this.mv.visitInsn(87);
        this.mv.visitVarInsn(25, tempIdx);
        this.compileStack.removeVar(tempIdx);
        this.compileStack.removeVar(idx);
    }
    
    protected void evaluateInstanceof(final BinaryExpression expression) {
        this.visitAndAutoboxBoolean(expression.getLeftExpression());
        final Expression rightExp = expression.getRightExpression();
        if (rightExp instanceof ClassExpression) {
            final ClassExpression classExp = (ClassExpression)rightExp;
            final ClassNode classType = classExp.getType();
            final String classInternalName = BytecodeHelper.getClassInternalName(classType);
            this.mv.visitTypeInsn(193, classInternalName);
            return;
        }
        throw new RuntimeException("Right hand side of the instanceof keyword must be a class name, not: " + rightExp);
    }
    
    protected boolean argumentsUseStack(final Expression arguments) {
        return arguments instanceof TupleExpression || arguments instanceof ClosureExpression;
    }
    
    private static boolean isThisExpression(final Expression expression) {
        if (expression instanceof VariableExpression) {
            final VariableExpression varExp = (VariableExpression)expression;
            return varExp.getName().equals("this");
        }
        return false;
    }
    
    private static boolean isSuperExpression(final Expression expression) {
        if (expression instanceof VariableExpression) {
            final VariableExpression varExp = (VariableExpression)expression;
            return varExp.getName().equals("super");
        }
        return false;
    }
    
    private static boolean isThisOrSuper(final Expression expression) {
        return isThisExpression(expression) || isSuperExpression(expression);
    }
    
    protected Expression createReturnLHSExpression(final Expression expression) {
        if (expression instanceof BinaryExpression) {
            final BinaryExpression binExpr = (BinaryExpression)expression;
            if (binExpr.getOperation().isA(1100)) {
                return this.createReusableExpression(binExpr.getLeftExpression());
            }
        }
        return null;
    }
    
    protected Expression createReusableExpression(final Expression expression) {
        final ExpressionTransformer transformer = new ExpressionTransformer() {
            public Expression transform(final Expression expression) {
                if (expression instanceof PostfixExpression) {
                    final PostfixExpression postfixExp = (PostfixExpression)expression;
                    return postfixExp.getExpression();
                }
                if (expression instanceof PrefixExpression) {
                    final PrefixExpression prefixExp = (PrefixExpression)expression;
                    return prefixExp.getExpression();
                }
                return expression;
            }
        };
        return transformer.transform(expression.transformExpression(transformer));
    }
    
    protected boolean isComparisonExpression(final Expression expression) {
        if (expression instanceof BinaryExpression) {
            final BinaryExpression binExpr = (BinaryExpression)expression;
            switch (binExpr.getOperation().getType()) {
                case 94:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 544:
                case 573: {
                    return true;
                }
            }
        }
        else if (expression instanceof BooleanExpression) {
            return true;
        }
        return false;
    }
    
    protected void onLineNumber(final ASTNode statement, final String message) {
        if (statement == null) {
            return;
        }
        final int line = statement.getLineNumber();
        this.currentASTNode = statement;
        if (line < 0) {
            return;
        }
        if (line == this.lineNumber) {
            return;
        }
        this.lineNumber = line;
        if (this.mv != null) {
            final Label l = new Label();
            this.mv.visitLabel(l);
            this.mv.visitLineNumber(line, l);
        }
    }
    
    private boolean isInnerClass() {
        return this.classNode instanceof InnerClassNode;
    }
    
    protected boolean isFieldOrVariable(final String name) {
        return this.compileStack.containsVariable(name) || this.classNode.getDeclaredField(name) != null;
    }
    
    protected ClassNode getExpressionType(final Expression expression) {
        if (this.isComparisonExpression(expression)) {
            return ClassHelper.boolean_TYPE;
        }
        if (expression instanceof VariableExpression) {
            final VariableExpression varExpr = (VariableExpression)expression;
            if (varExpr.isThisExpression()) {
                return this.classNode;
            }
            if (varExpr.isSuperExpression()) {
                return this.classNode.getSuperClass();
            }
            final org.codehaus.groovy.classgen.Variable variable = this.compileStack.getVariable(varExpr.getName(), false);
            if (variable != null && !variable.isHolder()) {
                final ClassNode type = variable.getType();
                if (!variable.isDynamicTyped()) {
                    return type;
                }
            }
            if (variable == null) {
                final Variable var = this.compileStack.getScope().getReferencedClassVariable(varExpr.getName());
                if (var != null && !var.isDynamicTyped()) {
                    return var.getType();
                }
            }
        }
        return expression.getType();
    }
    
    protected boolean isInClosureConstructor() {
        return this.constructorNode != null && this.classNode.getOuterClass() != null && this.classNode.getSuperClass() == ClassHelper.CLOSURE_TYPE;
    }
    
    protected boolean isInClosure() {
        return this.classNode.getOuterClass() != null && this.classNode.getSuperClass() == ClassHelper.CLOSURE_TYPE;
    }
    
    protected boolean isNotExplicitThisInClosure(final boolean implicitThis) {
        return implicitThis || !this.isInClosure();
    }
    
    protected boolean isStaticMethod() {
        return this.methodNode != null && this.methodNode.isStatic();
    }
    
    protected CompileUnit getCompileUnit() {
        CompileUnit answer = this.classNode.getCompileUnit();
        if (answer == null) {
            answer = this.context.getCompileUnit();
        }
        return answer;
    }
    
    public static boolean usesSuper(final MethodCallExpression call) {
        final Expression expression = call.getObjectExpression();
        if (expression instanceof VariableExpression) {
            final VariableExpression varExp = (VariableExpression)expression;
            final String variable = varExp.getName();
            return variable.equals("super");
        }
        return false;
    }
    
    public static boolean usesSuper(final PropertyExpression pe) {
        final Expression expression = pe.getObjectExpression();
        if (expression instanceof VariableExpression) {
            final VariableExpression varExp = (VariableExpression)expression;
            final String variable = varExp.getName();
            return variable.equals("super");
        }
        return false;
    }
    
    protected int getBytecodeVersion() {
        if (!this.classNode.isUsingGenerics() && !this.classNode.isAnnotated() && !this.classNode.isAnnotationDefinition()) {
            return 47;
        }
        final String target = this.getCompileUnit().getConfig().getTargetBytecode();
        return "1.5".equals(target) ? 49 : 47;
    }
    
    static {
        invokeMethodOnCurrent = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeMethodOnCurrent", true, false);
        invokeMethodOnSuper = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeMethodOnSuper", true, false);
        invokeMethod = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeMethod", true, false);
        invokeStaticMethod = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeStaticMethod", true, true);
        invokeNew = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeNew", true, true);
        setField = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "setField", false, false);
        getField = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "getField", false, false);
        setGroovyObjectField = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "setGroovyObjectField", false, false);
        getGroovyObjectField = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "getGroovyObjectField", false, false);
        setFieldOnSuper = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "setFieldOnSuper", false, false);
        getFieldOnSuper = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "getFieldOnSuper", false, false);
        setProperty = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "setProperty", false, false);
        getProperty = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "getProperty", false, false);
        setGroovyObjectProperty = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "setGroovyObjectProperty", false, false);
        getGroovyObjectProperty = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "getGroovyObjectProperty", false, false);
        setPropertyOnSuper = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "setPropertyOnSuper", false, false);
        getPropertyOnSuper = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "getPropertyOnSuper", false, false);
        iteratorNextMethod = MethodCaller.newInterface(Iterator.class, "next");
        iteratorHasNextMethod = MethodCaller.newInterface(Iterator.class, "hasNext");
        assertFailedMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "assertFailed");
        isCaseMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "isCase");
        compareIdenticalMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareIdentical");
        compareNotIdenticalMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareNotIdentical");
        compareEqualMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareEqual");
        compareNotEqualMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareNotEqual");
        compareToMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareTo");
        compareLessThanMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareLessThan");
        compareLessThanEqualMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareLessThanEqual");
        compareGreaterThanMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareGreaterThan");
        compareGreaterThanEqualMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "compareGreaterThanEqual");
        findRegexMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "findRegex");
        matchRegexMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "matchRegex");
        regexPattern = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "regexPattern");
        spreadMap = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "spreadMap");
        despreadList = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "despreadList");
        getMethodPointer = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "getMethodPointer");
        invokeClosureMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "invokeClosure");
        unaryPlus = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "unaryPlus");
        unaryMinus = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "unaryMinus");
        bitwiseNegate = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "bitwiseNegate");
        asTypeMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "asType");
        castToTypeMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "castToType");
        createListMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "createList");
        createTupleMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "createTuple");
        createMapMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "createMap");
        createRangeMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "createRange");
        createPojoWrapperMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "createPojoWrapper");
        createGroovyObjectWrapperMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "createGroovyObjectWrapper");
        selectConstructorAndTransformArguments = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "selectConstructorAndTransformArguments");
        DTT = BytecodeHelper.getClassInternalName(DefaultTypeTransformation.class.getName());
        AsmClassGenerator.sig = new String[255];
        names = new HashSet<String>();
        basic = new HashSet<String>();
        Collections.addAll(AsmClassGenerator.names, new String[] { "plus", "minus", "multiply", "div", "compareTo", "or", "and", "xor", "intdiv", "mod", "leftShift", "rightShift", "rightShiftUnsigned" });
        Collections.addAll(AsmClassGenerator.basic, new String[] { "plus", "minus", "multiply", "div" });
    }
    
    private static class AssertionTracker
    {
        int recorderIndex;
        SourceText sourceText;
    }
    
    private class MyMethodAdapter extends MethodAdapter
    {
        private String boxingDesc;
        
        public MyMethodAdapter() {
            super(AsmClassGenerator.this.mv);
            this.boxingDesc = null;
        }
        
        private void dropBoxing() {
            if (this.boxingDesc != null) {
                super.visitMethodInsn(184, AsmClassGenerator.DTT, "box", this.boxingDesc);
                this.boxingDesc = null;
            }
        }
        
        @Override
        public void visitInsn(final int opcode) {
            this.dropBoxing();
            super.visitInsn(opcode);
        }
        
        @Override
        public void visitIntInsn(final int opcode, final int operand) {
            this.dropBoxing();
            super.visitIntInsn(opcode, operand);
        }
        
        @Override
        public void visitVarInsn(final int opcode, final int var) {
            this.dropBoxing();
            super.visitVarInsn(opcode, var);
        }
        
        @Override
        public void visitTypeInsn(final int opcode, final String desc) {
            this.dropBoxing();
            super.visitTypeInsn(opcode, desc);
        }
        
        @Override
        public void visitFieldInsn(final int opcode, final String owner, final String name, final String desc) {
            this.dropBoxing();
            super.visitFieldInsn(opcode, owner, name, desc);
        }
        
        @Override
        public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc) {
            if (this.boxing(opcode, owner, name)) {
                this.boxingDesc = desc;
                this.dropBoxing();
            }
            else if (this.unboxing(opcode, owner, name)) {
                if (this.boxingDesc != null) {
                    this.boxingDesc = null;
                }
                else {
                    super.visitMethodInsn(opcode, owner, name, desc);
                }
            }
            else {
                this.dropBoxing();
                super.visitMethodInsn(opcode, owner, name, desc);
            }
        }
        
        private boolean boxing(final int opcode, final String owner, final String name) {
            return opcode == 184 && owner.equals(AsmClassGenerator.DTT) && name.equals("box");
        }
        
        private boolean unboxing(final int opcode, final String owner, final String name) {
            return opcode == 184 && owner.equals(AsmClassGenerator.DTT) && name.endsWith("Unbox");
        }
        
        @Override
        public void visitJumpInsn(final int opcode, final Label label) {
            this.dropBoxing();
            super.visitJumpInsn(opcode, label);
        }
        
        @Override
        public void visitLabel(final Label label) {
            this.dropBoxing();
            super.visitLabel(label);
        }
        
        @Override
        public void visitLdcInsn(final Object cst) {
            this.dropBoxing();
            super.visitLdcInsn(cst);
        }
        
        @Override
        public void visitIincInsn(final int var, final int increment) {
            this.dropBoxing();
            super.visitIincInsn(var, increment);
        }
        
        @Override
        public void visitTableSwitchInsn(final int min, final int max, final Label dflt, final Label[] labels) {
            this.dropBoxing();
            super.visitTableSwitchInsn(min, max, dflt, labels);
        }
        
        @Override
        public void visitLookupSwitchInsn(final Label dflt, final int[] keys, final Label[] labels) {
            this.dropBoxing();
            super.visitLookupSwitchInsn(dflt, keys, labels);
        }
        
        @Override
        public void visitMultiANewArrayInsn(final String desc, final int dims) {
            this.dropBoxing();
            super.visitMultiANewArrayInsn(desc, dims);
        }
        
        @Override
        public void visitTryCatchBlock(final Label start, final Label end, final Label handler, final String type) {
            this.dropBoxing();
            super.visitTryCatchBlock(start, end, handler, type);
        }
    }
}
