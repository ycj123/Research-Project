// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.util.EnumMap;
import java.util.Collections;
import java.util.LinkedHashSet;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import groovy.lang.GroovyObject;
import java.util.ListIterator;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import org.codehaus.groovy.vmplugin.VMPluginFactory;
import java.lang.reflect.Array;
import org.codehaus.groovy.GroovyBugError;
import java.util.Set;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.control.CompilePhase;
import java.util.Map;
import java.util.LinkedList;
import org.codehaus.groovy.ast.stmt.Statement;
import java.util.List;
import groovyjarjarasm.asm.Opcodes;

public class ClassNode extends AnnotatedNode implements Opcodes
{
    public static final ClassNode[] EMPTY_ARRAY;
    public static final ClassNode THIS;
    public static final ClassNode SUPER;
    private String name;
    private int modifiers;
    private ClassNode[] interfaces;
    private MixinNode[] mixins;
    private List<ConstructorNode> constructors;
    private List<Statement> objectInitializers;
    private MapOfLists methods;
    private List<MethodNode> methodsList;
    private LinkedList<FieldNode> fields;
    private List<PropertyNode> properties;
    private Map<String, FieldNode> fieldIndex;
    private ModuleNode module;
    private CompileUnit compileUnit;
    private boolean staticClass;
    private boolean scriptBody;
    private boolean script;
    private ClassNode superClass;
    protected boolean isPrimaryNode;
    protected List<InnerClassNode> innerClasses;
    private Map<CompilePhase, Map<Class<? extends ASTTransformation>, Set<ASTNode>>> transformInstances;
    protected Object lazyInitLock;
    protected Class clazz;
    private boolean lazyInitDone;
    private ClassNode componentType;
    private ClassNode redirect;
    private boolean annotated;
    private GenericsType[] genericsTypes;
    private boolean usesGenerics;
    private boolean placeholder;
    private MethodNode enclosingMethod;
    
    public ClassNode redirect() {
        if (this.redirect == null) {
            return this;
        }
        return this.redirect.redirect();
    }
    
    public void setRedirect(ClassNode cn) {
        if (this.isPrimaryNode) {
            throw new GroovyBugError("tried to set a redirect for a primary ClassNode (" + this.getName() + "->" + cn.getName() + ").");
        }
        if (cn != null) {
            cn = cn.redirect();
        }
        if (cn == this) {
            return;
        }
        this.redirect = cn;
    }
    
    public ClassNode makeArray() {
        if (this.redirect != null) {
            final ClassNode res = this.redirect().makeArray();
            res.componentType = this;
            return res;
        }
        ClassNode cn;
        if (this.clazz != null) {
            final Class ret = Array.newInstance(this.clazz, 0).getClass();
            cn = new ClassNode(ret, this);
        }
        else {
            cn = new ClassNode(this);
        }
        return cn;
    }
    
    public boolean isPrimaryClassNode() {
        return this.redirect().isPrimaryNode || (this.componentType != null && this.componentType.isPrimaryClassNode());
    }
    
    private ClassNode(final ClassNode componentType) {
        this(componentType.getName() + "[]", 1, ClassHelper.OBJECT_TYPE);
        this.componentType = componentType.redirect();
        this.isPrimaryNode = false;
    }
    
    private ClassNode(final Class c, final ClassNode componentType) {
        this(c);
        this.componentType = componentType;
        this.isPrimaryNode = false;
    }
    
    public ClassNode(final Class c) {
        this(c.getName(), c.getModifiers(), null, null, MixinNode.EMPTY_ARRAY);
        this.clazz = c;
        this.lazyInitDone = false;
        final CompileUnit cu = this.getCompileUnit();
        if (cu != null) {
            cu.addClass(this);
        }
        this.isPrimaryNode = false;
    }
    
    private void lazyClassInit() {
        synchronized (this.lazyInitLock) {
            if (this.redirect != null) {
                throw new GroovyBugError("lazyClassInit called on a proxy ClassNode, that must not happen.A redirect() call is missing somewhere!");
            }
            if (this.lazyInitDone) {
                return;
            }
            VMPluginFactory.getPlugin().configureClassNode(this.compileUnit, this);
            this.lazyInitDone = true;
        }
    }
    
    public MethodNode getEnclosingMethod() {
        return this.redirect().enclosingMethod;
    }
    
    public void setEnclosingMethod(final MethodNode enclosingMethod) {
        this.redirect().enclosingMethod = enclosingMethod;
    }
    
    public ClassNode(final String name, final int modifiers, final ClassNode superClass) {
        this(name, modifiers, superClass, ClassNode.EMPTY_ARRAY, MixinNode.EMPTY_ARRAY);
    }
    
    public ClassNode(final String name, final int modifiers, final ClassNode superClass, final ClassNode[] interfaces, final MixinNode[] mixins) {
        this.staticClass = false;
        this.scriptBody = false;
        this.lazyInitLock = new Object();
        this.lazyInitDone = true;
        this.componentType = null;
        this.redirect = null;
        this.genericsTypes = null;
        this.usesGenerics = false;
        this.enclosingMethod = null;
        this.name = name;
        this.modifiers = modifiers;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.mixins = mixins;
        this.isPrimaryNode = true;
        if (superClass != null) {
            this.usesGenerics = superClass.isUsingGenerics();
        }
        if (!this.usesGenerics && interfaces != null) {
            for (final ClassNode anInterface : interfaces) {
                this.usesGenerics = (this.usesGenerics || anInterface.isUsingGenerics());
            }
        }
        this.methods = new MapOfLists();
        this.methodsList = new ArrayList<MethodNode>();
    }
    
    public void setSuperClass(final ClassNode superClass) {
        this.redirect().superClass = superClass;
    }
    
    public List<FieldNode> getFields() {
        if (!this.redirect().lazyInitDone) {
            this.redirect().lazyClassInit();
        }
        if (this.redirect != null) {
            return this.redirect().getFields();
        }
        if (this.fields == null) {
            this.fields = new LinkedList<FieldNode>();
        }
        return this.fields;
    }
    
    public ClassNode[] getInterfaces() {
        if (!this.redirect().lazyInitDone) {
            this.redirect().lazyClassInit();
        }
        if (this.redirect != null) {
            return this.redirect().getInterfaces();
        }
        return this.interfaces;
    }
    
    public void setInterfaces(final ClassNode[] interfaces) {
        if (this.redirect != null) {
            this.redirect().setInterfaces(interfaces);
        }
        else {
            this.interfaces = interfaces;
        }
    }
    
    public MixinNode[] getMixins() {
        return this.redirect().mixins;
    }
    
    public List<MethodNode> getMethods() {
        if (!this.redirect().lazyInitDone) {
            this.redirect().lazyClassInit();
        }
        if (this.redirect != null) {
            return this.redirect().getMethods();
        }
        return this.methodsList;
    }
    
    public List<MethodNode> getAbstractMethods() {
        final List<MethodNode> result = new ArrayList<MethodNode>(3);
        for (final MethodNode method : this.getDeclaredMethodsMap().values()) {
            if (method.isAbstract()) {
                result.add(method);
            }
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }
    
    public List<MethodNode> getAllDeclaredMethods() {
        return new ArrayList<MethodNode>(this.getDeclaredMethodsMap().values());
    }
    
    public Set<ClassNode> getAllInterfaces() {
        final Set<ClassNode> res = new HashSet<ClassNode>();
        this.getAllInterfaces(res);
        return res;
    }
    
    private void getAllInterfaces(final Set<ClassNode> res) {
        if (this.isInterface()) {
            res.add(this);
        }
        for (final ClassNode anInterface : this.getInterfaces()) {
            res.add(anInterface);
            anInterface.getAllInterfaces(res);
        }
    }
    
    public Map<String, MethodNode> getDeclaredMethodsMap() {
        final ClassNode parent = this.getSuperClass();
        Map<String, MethodNode> result = null;
        if (parent != null) {
            result = parent.getDeclaredMethodsMap();
        }
        else {
            result = new HashMap<String, MethodNode>();
        }
        for (final ClassNode iface : this.getInterfaces()) {
            final Map<String, MethodNode> ifaceMethodsMap = iface.getDeclaredMethodsMap();
            for (final String methSig : ifaceMethodsMap.keySet()) {
                if (!result.containsKey(methSig)) {
                    final MethodNode methNode = ifaceMethodsMap.get(methSig);
                    result.put(methSig, methNode);
                }
            }
        }
        for (final MethodNode method : this.getMethods()) {
            final String sig = method.getTypeDescriptor();
            result.put(sig, method);
        }
        return result;
    }
    
    public String getName() {
        return this.redirect().name;
    }
    
    public String getUnresolvedName() {
        return this.name;
    }
    
    public String setName(final String name) {
        return this.redirect().name = name;
    }
    
    public int getModifiers() {
        return this.redirect().modifiers;
    }
    
    public void setModifiers(final int modifiers) {
        this.redirect().modifiers = modifiers;
    }
    
    public List<PropertyNode> getProperties() {
        final ClassNode r = this.redirect();
        if (r.properties == null) {
            r.properties = new ArrayList<PropertyNode>();
        }
        return r.properties;
    }
    
    public List<ConstructorNode> getDeclaredConstructors() {
        if (!this.redirect().lazyInitDone) {
            this.redirect().lazyClassInit();
        }
        final ClassNode r = this.redirect();
        if (r.constructors == null) {
            r.constructors = new ArrayList<ConstructorNode>();
        }
        return r.constructors;
    }
    
    public ModuleNode getModule() {
        return this.redirect().module;
    }
    
    public PackageNode getPackage() {
        return (this.getModule() == null) ? null : this.getModule().getPackage();
    }
    
    public void setModule(final ModuleNode module) {
        this.redirect().module = module;
        if (module != null) {
            this.redirect().compileUnit = module.getUnit();
        }
    }
    
    public void addField(final FieldNode node) {
        final ClassNode r = this.redirect();
        node.setDeclaringClass(r);
        node.setOwner(r);
        if (r.fields == null) {
            r.fields = new LinkedList<FieldNode>();
        }
        if (r.fieldIndex == null) {
            r.fieldIndex = new HashMap<String, FieldNode>();
        }
        r.fields.add(node);
        r.fieldIndex.put(node.getName(), node);
    }
    
    public void addFieldFirst(final FieldNode node) {
        final ClassNode r = this.redirect();
        node.setDeclaringClass(r);
        node.setOwner(r);
        if (r.fields == null) {
            r.fields = new LinkedList<FieldNode>();
        }
        if (r.fieldIndex == null) {
            r.fieldIndex = new HashMap<String, FieldNode>();
        }
        r.fields.addFirst(node);
        r.fieldIndex.put(node.getName(), node);
    }
    
    public void addProperty(final PropertyNode node) {
        node.setDeclaringClass(this.redirect());
        final FieldNode field = node.getField();
        this.addField(field);
        final ClassNode r = this.redirect();
        if (r.properties == null) {
            r.properties = new ArrayList<PropertyNode>();
        }
        r.properties.add(node);
    }
    
    public PropertyNode addProperty(final String name, final int modifiers, final ClassNode type, final Expression initialValueExpression, final Statement getterBlock, final Statement setterBlock) {
        for (final PropertyNode pn : this.getProperties()) {
            if (pn.getName().equals(name)) {
                if (pn.getInitialExpression() == null && initialValueExpression != null) {
                    pn.getField().setInitialValueExpression(initialValueExpression);
                }
                if (pn.getGetterBlock() == null && getterBlock != null) {
                    pn.setGetterBlock(getterBlock);
                }
                if (pn.getSetterBlock() == null && setterBlock != null) {
                    pn.setSetterBlock(setterBlock);
                }
                return pn;
            }
        }
        final PropertyNode node = new PropertyNode(name, modifiers, type, this.redirect(), initialValueExpression, getterBlock, setterBlock);
        this.addProperty(node);
        return node;
    }
    
    public boolean hasProperty(final String name) {
        return this.getProperty(name) != null;
    }
    
    public PropertyNode getProperty(final String name) {
        for (final PropertyNode pn : this.getProperties()) {
            if (pn.getName().equals(name)) {
                return pn;
            }
        }
        return null;
    }
    
    public void addConstructor(final ConstructorNode node) {
        node.setDeclaringClass(this);
        final ClassNode r = this.redirect();
        if (r.constructors == null) {
            r.constructors = new ArrayList<ConstructorNode>();
        }
        r.constructors.add(node);
    }
    
    public ConstructorNode addConstructor(final int modifiers, final Parameter[] parameters, final ClassNode[] exceptions, final Statement code) {
        final ConstructorNode node = new ConstructorNode(modifiers, parameters, exceptions, code);
        this.addConstructor(node);
        return node;
    }
    
    public void addMethod(final MethodNode node) {
        node.setDeclaringClass(this);
        this.redirect().methodsList.add(node);
        this.redirect().methods.put(node.getName(), node);
    }
    
    public MethodNode addMethod(final String name, final int modifiers, final ClassNode returnType, final Parameter[] parameters, final ClassNode[] exceptions, final Statement code) {
        final MethodNode other = this.getDeclaredMethod(name, parameters);
        if (other != null) {
            return other;
        }
        final MethodNode node = new MethodNode(name, modifiers, returnType, parameters, exceptions, code);
        this.addMethod(node);
        return node;
    }
    
    public boolean hasDeclaredMethod(final String name, final Parameter[] parameters) {
        final MethodNode other = this.getDeclaredMethod(name, parameters);
        return other != null;
    }
    
    public boolean hasMethod(final String name, final Parameter[] parameters) {
        final MethodNode other = this.getMethod(name, parameters);
        return other != null;
    }
    
    public MethodNode addSyntheticMethod(final String name, final int modifiers, final ClassNode returnType, final Parameter[] parameters, final ClassNode[] exceptions, final Statement code) {
        final MethodNode answer = this.addMethod(name, modifiers | 0x1000, returnType, parameters, exceptions, code);
        answer.setSynthetic(true);
        return answer;
    }
    
    public FieldNode addField(final String name, final int modifiers, final ClassNode type, final Expression initialValue) {
        final FieldNode node = new FieldNode(name, modifiers, type, this.redirect(), initialValue);
        this.addField(node);
        return node;
    }
    
    public FieldNode addFieldFirst(final String name, final int modifiers, final ClassNode type, final Expression initialValue) {
        final FieldNode node = new FieldNode(name, modifiers, type, this.redirect(), initialValue);
        this.addFieldFirst(node);
        return node;
    }
    
    public void addInterface(final ClassNode type) {
        boolean skip = false;
        final ClassNode[] arr$;
        final ClassNode[] interfaces = arr$ = this.redirect().interfaces;
        for (final ClassNode existing : arr$) {
            if (type.equals(existing)) {
                skip = true;
            }
        }
        if (!skip) {
            final ClassNode[] newInterfaces = new ClassNode[interfaces.length + 1];
            System.arraycopy(interfaces, 0, newInterfaces, 0, interfaces.length);
            newInterfaces[interfaces.length] = type;
            this.redirect().interfaces = newInterfaces;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this.redirect != null) {
            return this.redirect().equals(o);
        }
        final ClassNode cn = (ClassNode)o;
        return cn.getName().equals(this.getName());
    }
    
    @Override
    public int hashCode() {
        if (this.redirect != null) {
            return this.redirect().hashCode();
        }
        return this.getName().hashCode();
    }
    
    public void addMixin(final MixinNode mixin) {
        final MixinNode[] mixins = this.redirect().mixins;
        boolean skip = false;
        for (final MixinNode existing : mixins) {
            if (mixin.equals(existing)) {
                skip = true;
            }
        }
        if (!skip) {
            final MixinNode[] newMixins = new MixinNode[mixins.length + 1];
            System.arraycopy(mixins, 0, newMixins, 0, mixins.length);
            newMixins[mixins.length] = mixin;
            this.redirect().mixins = newMixins;
        }
    }
    
    public FieldNode getDeclaredField(final String name) {
        final ClassNode r = this.redirect();
        if (r.fieldIndex == null) {
            r.fieldIndex = new HashMap<String, FieldNode>();
        }
        return r.fieldIndex.get(name);
    }
    
    public FieldNode getField(final String name) {
        for (ClassNode node = this; node != null; node = node.getSuperClass()) {
            final FieldNode fn = node.getDeclaredField(name);
            if (fn != null) {
                return fn;
            }
        }
        return null;
    }
    
    public FieldNode getOuterField(final String name) {
        return null;
    }
    
    public ClassNode getOuterClass() {
        return null;
    }
    
    public void addObjectInitializerStatements(final Statement statements) {
        this.getObjectInitializerStatements().add(statements);
    }
    
    public List<Statement> getObjectInitializerStatements() {
        if (this.objectInitializers == null) {
            this.objectInitializers = new ArrayList<Statement>();
        }
        return this.objectInitializers;
    }
    
    private MethodNode getOrAddStaticConstructorNode() {
        MethodNode method = null;
        final List declaredMethods = this.getDeclaredMethods("<clinit>");
        if (declaredMethods.isEmpty()) {
            method = this.addMethod("<clinit>", 8, ClassHelper.VOID_TYPE, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, new BlockStatement());
            method.setSynthetic(true);
        }
        else {
            method = declaredMethods.get(0);
        }
        return method;
    }
    
    public void addStaticInitializerStatements(final List<Statement> staticStatements, final boolean fieldInit) {
        final MethodNode method = this.getOrAddStaticConstructorNode();
        BlockStatement block = null;
        final Statement statement = method.getCode();
        if (statement == null) {
            block = new BlockStatement();
        }
        else if (statement instanceof BlockStatement) {
            block = (BlockStatement)statement;
        }
        else {
            block = new BlockStatement();
            block.addStatement(statement);
        }
        if (!fieldInit) {
            block.addStatements(staticStatements);
        }
        else {
            final List<Statement> blockStatements = block.getStatements();
            staticStatements.addAll(blockStatements);
            blockStatements.clear();
            blockStatements.addAll(staticStatements);
        }
    }
    
    public void positionStmtsAfterEnumInitStmts(final List<Statement> staticFieldStatements) {
        final MethodNode method = this.getOrAddStaticConstructorNode();
        final Statement statement = method.getCode();
        if (statement instanceof BlockStatement) {
            final BlockStatement block = (BlockStatement)statement;
            final List<Statement> blockStatements = block.getStatements();
            final ListIterator<Statement> litr = blockStatements.listIterator();
            while (litr.hasNext()) {
                final Statement stmt = litr.next();
                if (stmt instanceof ExpressionStatement && ((ExpressionStatement)stmt).getExpression() instanceof BinaryExpression) {
                    final BinaryExpression bExp = (BinaryExpression)((ExpressionStatement)stmt).getExpression();
                    if (!(bExp.getLeftExpression() instanceof FieldExpression)) {
                        continue;
                    }
                    final FieldExpression fExp = (FieldExpression)bExp.getLeftExpression();
                    if (!fExp.getFieldName().equals("$VALUES")) {
                        continue;
                    }
                    for (final Statement tmpStmt : staticFieldStatements) {
                        litr.add(tmpStmt);
                    }
                }
            }
        }
    }
    
    public List<MethodNode> getDeclaredMethods(final String name) {
        if (!this.redirect().lazyInitDone) {
            this.redirect().lazyClassInit();
        }
        if (this.redirect != null) {
            return this.redirect().getDeclaredMethods(name);
        }
        return this.methods.getNotNull(name);
    }
    
    public List<MethodNode> getMethods(final String name) {
        final List<MethodNode> answer = new ArrayList<MethodNode>();
        for (ClassNode node = this; node != null; node = node.getSuperClass()) {
            answer.addAll(node.getDeclaredMethods(name));
        }
        return answer;
    }
    
    public MethodNode getDeclaredMethod(final String name, final Parameter[] parameters) {
        for (final MethodNode method : this.getDeclaredMethods(name)) {
            if (this.parametersEqual(method.getParameters(), parameters)) {
                return method;
            }
        }
        return null;
    }
    
    public MethodNode getMethod(final String name, final Parameter[] parameters) {
        for (final MethodNode method : this.getMethods(name)) {
            if (this.parametersEqual(method.getParameters(), parameters)) {
                return method;
            }
        }
        return null;
    }
    
    public boolean isDerivedFrom(final ClassNode type) {
        if (this.equals(ClassHelper.VOID_TYPE)) {
            return type.equals(ClassHelper.VOID_TYPE);
        }
        if (type.equals(ClassHelper.OBJECT_TYPE)) {
            return true;
        }
        for (ClassNode node = this; node != null; node = node.getSuperClass()) {
            if (type.equals(node)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isDerivedFromGroovyObject() {
        return this.implementsInterface(ClassHelper.make(GroovyObject.class));
    }
    
    public boolean implementsInterface(final ClassNode classNode) {
        ClassNode node = this.redirect();
        while (!node.declaresInterface(classNode)) {
            node = node.getSuperClass();
            if (node == null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean declaresInterface(final ClassNode classNode) {
        ClassNode[] arr$;
        final ClassNode[] interfaces = arr$ = this.redirect().getInterfaces();
        for (final ClassNode cn : arr$) {
            if (cn.equals(classNode)) {
                return true;
            }
        }
        arr$ = interfaces;
        for (final ClassNode cn : arr$) {
            if (cn.declaresInterface(classNode)) {
                return true;
            }
        }
        return false;
    }
    
    public ClassNode getSuperClass() {
        if (!this.lazyInitDone && !this.isResolved()) {
            throw new GroovyBugError("ClassNode#getSuperClass for " + this.getName() + " called before class resolving");
        }
        ClassNode sn = this.redirect().getUnresolvedSuperClass();
        if (sn != null) {
            sn = sn.redirect();
        }
        return sn;
    }
    
    public ClassNode getUnresolvedSuperClass() {
        return this.getUnresolvedSuperClass(true);
    }
    
    public ClassNode getUnresolvedSuperClass(final boolean useRedirect) {
        if (!useRedirect) {
            return this.superClass;
        }
        if (!this.redirect().lazyInitDone) {
            this.redirect().lazyClassInit();
        }
        return this.redirect().superClass;
    }
    
    public void setUnresolvedSuperClass(final ClassNode sn) {
        this.superClass = sn;
    }
    
    public ClassNode[] getUnresolvedInterfaces() {
        return this.getUnresolvedInterfaces(true);
    }
    
    public ClassNode[] getUnresolvedInterfaces(final boolean useRedirect) {
        if (!useRedirect) {
            return this.interfaces;
        }
        if (!this.redirect().lazyInitDone) {
            this.redirect().lazyClassInit();
        }
        return this.redirect().interfaces;
    }
    
    public CompileUnit getCompileUnit() {
        if (this.redirect != null) {
            return this.redirect().getCompileUnit();
        }
        if (this.compileUnit == null && this.module != null) {
            this.compileUnit = this.module.getUnit();
        }
        return this.compileUnit;
    }
    
    protected void setCompileUnit(final CompileUnit cu) {
        if (this.redirect != null) {
            this.redirect().setCompileUnit(cu);
        }
        if (this.compileUnit != null) {
            this.compileUnit = cu;
        }
    }
    
    protected boolean parametersEqual(final Parameter[] a, final Parameter[] b) {
        if (a.length == b.length) {
            boolean answer = true;
            for (int i = 0; i < a.length; ++i) {
                if (!a[i].getType().equals(b[i].getType())) {
                    answer = false;
                    break;
                }
            }
            return answer;
        }
        return false;
    }
    
    public String getPackageName() {
        final int idx = this.getName().lastIndexOf(46);
        if (idx > 0) {
            return this.getName().substring(0, idx);
        }
        return null;
    }
    
    public String getNameWithoutPackage() {
        final int idx = this.getName().lastIndexOf(46);
        if (idx > 0) {
            return this.getName().substring(idx + 1);
        }
        return this.getName();
    }
    
    public void visitContents(final GroovyClassVisitor visitor) {
        for (final PropertyNode pn : this.getProperties()) {
            visitor.visitProperty(pn);
        }
        for (final FieldNode fn : this.getFields()) {
            visitor.visitField(fn);
        }
        for (final ConstructorNode cn : this.getDeclaredConstructors()) {
            visitor.visitConstructor(cn);
        }
        for (final MethodNode mn : this.getMethods()) {
            visitor.visitMethod(mn);
        }
    }
    
    public MethodNode getGetterMethod(final String getterName) {
        for (final MethodNode method : this.getDeclaredMethods(getterName)) {
            if (getterName.equals(method.getName()) && ClassHelper.VOID_TYPE != method.getReturnType() && method.getParameters().length == 0) {
                return method;
            }
        }
        final ClassNode parent = this.getSuperClass();
        if (parent != null) {
            return parent.getGetterMethod(getterName);
        }
        return null;
    }
    
    public MethodNode getSetterMethod(final String setterName) {
        for (final MethodNode method : this.getDeclaredMethods(setterName)) {
            if (setterName.equals(method.getName()) && ClassHelper.VOID_TYPE == method.getReturnType() && method.getParameters().length == 1) {
                return method;
            }
        }
        final ClassNode parent = this.getSuperClass();
        if (parent != null) {
            return parent.getSetterMethod(setterName);
        }
        return null;
    }
    
    public boolean isStaticClass() {
        return this.redirect().staticClass;
    }
    
    public void setStaticClass(final boolean staticClass) {
        this.redirect().staticClass = staticClass;
    }
    
    public boolean isScriptBody() {
        return this.redirect().scriptBody;
    }
    
    public void setScriptBody(final boolean scriptBody) {
        this.redirect().scriptBody = scriptBody;
    }
    
    public boolean isScript() {
        return this.redirect().script || this.isDerivedFrom(ClassHelper.SCRIPT_TYPE);
    }
    
    public void setScript(final boolean script) {
        this.redirect().script = script;
    }
    
    @Override
    public String toString() {
        String ret = this.getName();
        if (this.genericsTypes != null) {
            ret += " <";
            for (int i = 0; i < this.genericsTypes.length; ++i) {
                if (i != 0) {
                    ret += ", ";
                }
                final GenericsType genericsType = this.genericsTypes[i];
                ret += this.genericTypeAsString(genericsType);
            }
            ret += ">";
        }
        if (this.redirect != null) {
            ret = ret + " -> " + this.redirect().toString();
        }
        return ret;
    }
    
    private String genericTypeAsString(final GenericsType genericsType) {
        String ret = genericsType.getName();
        if (genericsType.getUpperBounds() != null) {
            ret += " extends ";
            for (int i = 0; i < genericsType.getUpperBounds().length; ++i) {
                final ClassNode classNode = genericsType.getUpperBounds()[i];
                if (classNode.equals(this)) {
                    ret += classNode.getName();
                }
                else {
                    ret += classNode.toString();
                }
                if (i + 1 < genericsType.getUpperBounds().length) {
                    ret += " & ";
                }
            }
        }
        else if (genericsType.getLowerBound() != null) {
            final ClassNode classNode2 = genericsType.getLowerBound();
            if (classNode2.equals(this)) {
                ret = ret + " super " + classNode2.getName();
            }
            else {
                ret = ret + " super " + classNode2;
            }
        }
        return ret;
    }
    
    public boolean hasPossibleMethod(final String name, final Expression arguments) {
        int count = 0;
        if (arguments instanceof TupleExpression) {
            final TupleExpression tuple = (TupleExpression)arguments;
            count = tuple.getExpressions().size();
        }
        ClassNode node = this;
        do {
            for (final MethodNode method : this.getMethods(name)) {
                if (method.getParameters().length == count && !method.isStatic()) {
                    return true;
                }
            }
            node = node.getSuperClass();
        } while (node != null);
        return false;
    }
    
    public MethodNode tryFindPossibleMethod(final String name, final Expression arguments) {
        int count = 0;
        if (arguments instanceof TupleExpression) {
            final TupleExpression tuple = (TupleExpression)arguments;
            count = tuple.getExpressions().size();
            MethodNode res = null;
            ClassNode node = this;
            final TupleExpression args = (TupleExpression)arguments;
            do {
                for (final MethodNode method : node.getMethods(name)) {
                    if (method.getParameters().length == count) {
                        boolean match = true;
                        for (int i = 0; i != count; ++i) {
                            if (!args.getType().isDerivedFrom(method.getParameters()[i].getType())) {
                                match = false;
                                break;
                            }
                        }
                        if (!match) {
                            continue;
                        }
                        if (res == null) {
                            res = method;
                        }
                        else {
                            if (res.getParameters().length != count) {
                                return null;
                            }
                            if (node.equals(this)) {
                                return null;
                            }
                            match = true;
                            for (int i = 0; i != count; ++i) {
                                if (!res.getParameters()[i].getType().equals(method.getParameters()[i].getType())) {
                                    match = false;
                                    break;
                                }
                            }
                            if (!match) {
                                return null;
                            }
                            continue;
                        }
                    }
                }
                node = node.getSuperClass();
            } while (node != null);
            return res;
        }
        return null;
    }
    
    public boolean hasPossibleStaticMethod(final String name, final Expression arguments) {
        int count = 0;
        if (arguments instanceof TupleExpression) {
            final TupleExpression tuple = (TupleExpression)arguments;
            count = tuple.getExpressions().size();
        }
        else if (arguments instanceof MapExpression) {
            count = 1;
        }
        for (final MethodNode method : this.getMethods(name)) {
            if (method.isStatic()) {
                final Parameter[] parameters = method.getParameters();
                if (parameters.length == count) {
                    return true;
                }
                if (parameters.length > 0 && parameters[parameters.length - 1].getType().isArray() && count >= parameters.length - 1) {
                    return true;
                }
                int nonDefaultParameters = 0;
                for (final Parameter parameter : parameters) {
                    if (!parameter.hasInitialExpression()) {
                        ++nonDefaultParameters;
                    }
                }
                if (count < parameters.length && nonDefaultParameters <= count) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    public boolean isInterface() {
        return (this.getModifiers() & 0x200) > 0;
    }
    
    public boolean isResolved() {
        return this.redirect().clazz != null || (this.componentType != null && this.componentType.isResolved());
    }
    
    public boolean isArray() {
        return this.componentType != null;
    }
    
    public ClassNode getComponentType() {
        return this.componentType;
    }
    
    public Class getTypeClass() {
        final Class c = this.redirect().clazz;
        if (c != null) {
            return c;
        }
        final ClassNode component = this.redirect().componentType;
        if (component != null && component.isResolved()) {
            final ClassNode cn = component.makeArray();
            this.setRedirect(cn);
            return this.redirect().clazz;
        }
        throw new GroovyBugError("ClassNode#getTypeClass for " + this.getName() + " is called before the type class is set ");
    }
    
    public boolean hasPackageName() {
        return this.redirect().name.indexOf(46) > 0;
    }
    
    public void setAnnotated(final boolean flag) {
        this.annotated = flag;
    }
    
    public boolean isAnnotated() {
        return this.annotated;
    }
    
    public GenericsType[] getGenericsTypes() {
        return this.genericsTypes;
    }
    
    public void setGenericsTypes(final GenericsType[] genericsTypes) {
        this.usesGenerics = (this.usesGenerics || genericsTypes != null);
        this.genericsTypes = genericsTypes;
    }
    
    public void setGenericsPlaceHolder(final boolean b) {
        this.usesGenerics = (this.usesGenerics || b);
        this.placeholder = b;
    }
    
    public boolean isGenericsPlaceHolder() {
        return this.placeholder;
    }
    
    public boolean isUsingGenerics() {
        return this.usesGenerics;
    }
    
    public void setUsingGenerics(final boolean b) {
        this.usesGenerics = b;
    }
    
    public ClassNode getPlainNodeReference() {
        if (ClassHelper.isPrimitiveType(this)) {
            return this;
        }
        final ClassNode n = new ClassNode(this.getName(), this.getModifiers(), this.getSuperClass(), null, null);
        n.isPrimaryNode = false;
        n.setRedirect(this.redirect);
        n.componentType = this.redirect().getComponentType();
        return n;
    }
    
    public boolean isAnnotationDefinition() {
        return this.redirect().isPrimaryNode && this.isInterface() && (this.getModifiers() & 0x2000) != 0x0;
    }
    
    @Override
    public List<AnnotationNode> getAnnotations() {
        if (this.redirect != null) {
            return this.redirect.getAnnotations();
        }
        this.lazyClassInit();
        return super.getAnnotations();
    }
    
    @Override
    public List<AnnotationNode> getAnnotations(final ClassNode type) {
        if (this.redirect != null) {
            return this.redirect.getAnnotations(type);
        }
        this.lazyClassInit();
        return super.getAnnotations(type);
    }
    
    public void addTransform(final Class<? extends ASTTransformation> transform, final ASTNode node) {
        final GroovyASTTransformation annotation = transform.getAnnotation(GroovyASTTransformation.class);
        Set<ASTNode> nodes = this.getTransformInstances().get(annotation.phase()).get(transform);
        if (nodes == null) {
            nodes = new LinkedHashSet<ASTNode>();
            this.getTransformInstances().get(annotation.phase()).put(transform, nodes);
        }
        nodes.add(node);
    }
    
    public Map<Class<? extends ASTTransformation>, Set<ASTNode>> getTransforms(final CompilePhase phase) {
        return this.getTransformInstances().get(phase);
    }
    
    public void renameField(final String oldName, final String newName) {
        final ClassNode r = this.redirect();
        if (r.fieldIndex == null) {
            r.fieldIndex = new HashMap<String, FieldNode>();
        }
        final Map<String, FieldNode> index = r.fieldIndex;
        index.put(newName, index.remove(oldName));
    }
    
    public void removeField(final String oldName) {
        final ClassNode r = this.redirect();
        if (r.fieldIndex == null) {
            r.fieldIndex = new HashMap<String, FieldNode>();
        }
        final Map<String, FieldNode> index = r.fieldIndex;
        r.fields.remove(index.get(oldName));
        index.remove(oldName);
    }
    
    public boolean isEnum() {
        return (this.getModifiers() & 0x4000) != 0x0;
    }
    
    public Iterator<InnerClassNode> getInnerClasses() {
        return ((this.innerClasses == null) ? Collections.emptyList() : this.innerClasses).iterator();
    }
    
    private Map<CompilePhase, Map<Class<? extends ASTTransformation>, Set<ASTNode>>> getTransformInstances() {
        if (this.transformInstances == null) {
            this.transformInstances = new EnumMap<CompilePhase, Map<Class<? extends ASTTransformation>, Set<ASTNode>>>(CompilePhase.class);
            for (final CompilePhase phase : CompilePhase.values()) {
                this.transformInstances.put(phase, new HashMap<Class<? extends ASTTransformation>, Set<ASTNode>>());
            }
        }
        return this.transformInstances;
    }
    
    static {
        EMPTY_ARRAY = new ClassNode[0];
        THIS = new ClassNode(Object.class);
        SUPER = new ClassNode(Object.class);
    }
    
    private static class MapOfLists
    {
        private Map<Object, List<MethodNode>> map;
        
        private MapOfLists() {
            this.map = new HashMap<Object, List<MethodNode>>();
        }
        
        public List<MethodNode> get(final Object key) {
            return this.map.get(key);
        }
        
        public List<MethodNode> getNotNull(final Object key) {
            List<MethodNode> ret = this.get(key);
            if (ret == null) {
                ret = Collections.emptyList();
            }
            return ret;
        }
        
        public void put(final Object key, final MethodNode value) {
            if (this.map.containsKey(key)) {
                this.get(key).add(value);
            }
            else {
                final ArrayList<MethodNode> list = new ArrayList<MethodNode>(2);
                list.add(value);
                this.map.put(key, list);
            }
        }
    }
}
