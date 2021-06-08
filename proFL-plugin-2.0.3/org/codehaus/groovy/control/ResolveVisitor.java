// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import org.codehaus.groovy.ast.AnnotationNode;
import java.lang.reflect.Modifier;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.DynamicVariable;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ExpressionTransformer;
import org.codehaus.groovy.ast.expr.AnnotationConstantExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.ExceptionMessage;
import org.codehaus.groovy.GroovyBugError;
import java.util.List;
import org.codehaus.groovy.ast.CompileUnit;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.ast.ModuleNode;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import org.codehaus.groovy.classgen.Verifier;
import java.util.Iterator;
import java.util.LinkedList;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.ClassHelper;
import java.util.LinkedHashMap;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.MethodNode;
import java.util.HashSet;
import java.util.HashMap;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.FieldNode;
import java.util.Set;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.VariableScope;
import java.util.Map;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ClassCodeExpressionTransformer;

public class ResolveVisitor extends ClassCodeExpressionTransformer
{
    private ClassNode currentClass;
    public static final String[] DEFAULT_IMPORTS;
    private CompilationUnit compilationUnit;
    private Map cachedClasses;
    private static final Object NO_CLASS;
    private static final Object SCRIPT;
    private SourceUnit source;
    private VariableScope currentScope;
    private boolean isTopLevelProperty;
    private boolean inPropertyExpression;
    private boolean inClosure;
    private boolean isSpecialConstructorCall;
    private Map<String, GenericsType> genericParameterNames;
    private Set<FieldNode> fieldTypesChecked;
    private boolean checkingVariableTypeInDeclaration;
    private ImportNode currImportNode;
    
    public ResolveVisitor(final CompilationUnit cu) {
        this.cachedClasses = new HashMap();
        this.isTopLevelProperty = true;
        this.inPropertyExpression = false;
        this.inClosure = false;
        this.isSpecialConstructorCall = false;
        this.genericParameterNames = new HashMap<String, GenericsType>();
        this.fieldTypesChecked = new HashSet<FieldNode>();
        this.checkingVariableTypeInDeclaration = false;
        this.currImportNode = null;
        this.compilationUnit = cu;
    }
    
    public void startResolving(final ClassNode node, final SourceUnit source) {
        this.source = source;
        this.visitClass(node);
    }
    
    @Override
    protected void visitConstructorOrMethod(final MethodNode node, final boolean isConstructor) {
        final VariableScope oldScope = this.currentScope;
        this.currentScope = node.getVariableScope();
        final Map<String, GenericsType> oldPNames = this.genericParameterNames;
        this.genericParameterNames = new HashMap<String, GenericsType>(this.genericParameterNames);
        this.resolveGenericsHeader(node.getGenericsTypes());
        final Parameter[] arr$;
        final Parameter[] paras = arr$ = node.getParameters();
        for (final Parameter p : arr$) {
            p.setInitialExpression(this.transform(p.getInitialExpression()));
            this.resolveOrFail(p.getType(), p.getType());
            this.visitAnnotations(p);
        }
        final ClassNode[] arr$2;
        final ClassNode[] exceptions = arr$2 = node.getExceptions();
        for (final ClassNode t : arr$2) {
            this.resolveOrFail(t, node);
        }
        this.resolveOrFail(node.getReturnType(), node);
        super.visitConstructorOrMethod(node, isConstructor);
        this.genericParameterNames = oldPNames;
        this.currentScope = oldScope;
    }
    
    @Override
    public void visitField(final FieldNode node) {
        final ClassNode t = node.getType();
        if (!this.fieldTypesChecked.contains(node)) {
            this.resolveOrFail(t, node);
        }
        super.visitField(node);
    }
    
    @Override
    public void visitProperty(final PropertyNode node) {
        final ClassNode t = node.getType();
        this.resolveOrFail(t, node);
        super.visitProperty(node);
        this.fieldTypesChecked.add(node.getField());
    }
    
    private boolean resolveToInner(final ClassNode type) {
        if (type instanceof ConstructedClassWithPackage) {
            return false;
        }
        final String saved;
        String name = saved = type.getName();
        while (true) {
            final int len = name.lastIndexOf(46);
            if (len == -1) {
                if (this.resolveToInnerEnum(type)) {
                    return true;
                }
                type.setName(saved);
                return false;
            }
            else {
                name = name.substring(0, len) + "$" + name.substring(len + 1);
                type.setName(name);
                if (this.resolve(type)) {
                    return true;
                }
                continue;
            }
        }
    }
    
    private boolean resolveToInnerEnum(final ClassNode type) {
        final String name = type.getName();
        if (this.currentClass != type && !name.contains(".") && type.getClass().equals(ClassNode.class)) {
            type.setName(this.currentClass.getName() + "$" + name);
            if (this.resolve(type)) {
                return true;
            }
        }
        return false;
    }
    
    private void resolveOrFail(final ClassNode type, final String msg, final ASTNode node) {
        if (this.resolve(type)) {
            return;
        }
        if (this.resolveToInner(type)) {
            return;
        }
        this.addError("unable to resolve class " + type.getName() + " " + msg, node);
    }
    
    private void resolveOrFail(final ClassNode type, final ASTNode node, final boolean prefereImports) {
        this.resolveGenericsTypes(type.getGenericsTypes());
        if (prefereImports && this.resolveAliasFromModule(type)) {
            return;
        }
        this.resolveOrFail(type, node);
    }
    
    private void resolveOrFail(final ClassNode type, final ASTNode node) {
        this.resolveOrFail(type, "", node);
    }
    
    private boolean resolve(final ClassNode type) {
        return this.resolve(type, true, true, true);
    }
    
    private boolean resolve(final ClassNode type, final boolean testModuleImports, final boolean testDefaultImports, final boolean testStaticInnerClasses) {
        this.resolveGenericsTypes(type.getGenericsTypes());
        if (type.isResolved() || type.isPrimaryClassNode()) {
            return true;
        }
        if (type.isArray()) {
            final ClassNode element = type.getComponentType();
            final boolean resolved = this.resolve(element, testModuleImports, testDefaultImports, testStaticInnerClasses);
            if (resolved) {
                final ClassNode cn = element.makeArray();
                type.setRedirect(cn);
            }
            return resolved;
        }
        if (this.currentClass == type) {
            return true;
        }
        if (this.genericParameterNames.get(type.getName()) != null) {
            final GenericsType gt = this.genericParameterNames.get(type.getName());
            type.setRedirect(gt.getType());
            type.setGenericsTypes(new GenericsType[] { gt });
            type.setGenericsPlaceHolder(true);
            return true;
        }
        if (this.currentClass.getNameWithoutPackage().equals(type.getName())) {
            type.setRedirect(this.currentClass);
            return true;
        }
        return this.resolveNestedClass(type) || this.resolveFromModule(type, testModuleImports) || this.resolveFromCompileUnit(type) || this.resolveFromDefaultImports(type, testDefaultImports) || this.resolveFromStaticInnerClasses(type, testStaticInnerClasses) || this.resolveFromClassCache(type) || this.resolveToClass(type) || this.resolveToScript(type);
    }
    
    private boolean resolveNestedClass(final ClassNode type) {
        final Map<String, ClassNode> hierClasses = new LinkedHashMap<String, ClassNode>();
        for (ClassNode classToCheck = this.currentClass; classToCheck != ClassHelper.OBJECT_TYPE && classToCheck != null && !hierClasses.containsKey(classToCheck.getName()); classToCheck = classToCheck.getSuperClass()) {
            hierClasses.put(classToCheck.getName(), classToCheck);
        }
        for (final ClassNode classToCheck2 : hierClasses.values()) {
            final String name = classToCheck2.getName() + "$" + type.getName();
            final ClassNode val = ClassHelper.make(name);
            if (this.resolveFromCompileUnit(val)) {
                type.setRedirect(val);
                return true;
            }
        }
        if (!(this.currentClass instanceof InnerClassNode)) {
            return false;
        }
        final LinkedList<ClassNode> outerClasses = new LinkedList<ClassNode>();
        for (ClassNode outer = this.currentClass.getOuterClass(); outer != null; outer = outer.getOuterClass()) {
            outerClasses.addFirst(outer);
        }
        for (final ClassNode testNode : outerClasses) {
            final String name = testNode.getName() + "$" + type.getName();
            final ClassNode val = ClassHelper.make(name);
            if (this.resolveFromCompileUnit(val)) {
                type.setRedirect(val);
                return true;
            }
        }
        return false;
    }
    
    private boolean resolveFromClassCache(final ClassNode type) {
        final String name = type.getName();
        final Object val = this.cachedClasses.get(name);
        if (val == null || val == ResolveVisitor.NO_CLASS) {
            return false;
        }
        type.setRedirect((ClassNode)val);
        return true;
    }
    
    private long getTimeStamp(final Class cls) {
        return Verifier.getTimestamp(cls);
    }
    
    private boolean isSourceNewer(final URL source, final Class cls) {
        try {
            long lastMod;
            if (source.getProtocol().equals("file")) {
                final String path = source.getPath().replace('/', File.separatorChar).replace('|', ':');
                final File file = new File(path);
                lastMod = file.lastModified();
            }
            else {
                final URLConnection conn = source.openConnection();
                lastMod = conn.getLastModified();
                conn.getInputStream().close();
            }
            return lastMod > this.getTimeStamp(cls);
        }
        catch (IOException e) {
            return false;
        }
    }
    
    private boolean resolveToScript(final ClassNode type) {
        final String name = type.getName();
        if (type instanceof LowerCaseClass) {
            this.cachedClasses.put(name, ResolveVisitor.NO_CLASS);
        }
        if (this.cachedClasses.get(name) == ResolveVisitor.NO_CLASS) {
            return false;
        }
        if (this.cachedClasses.get(name) == ResolveVisitor.SCRIPT) {
            this.cachedClasses.put(name, ResolveVisitor.NO_CLASS);
        }
        if (name.startsWith("java.")) {
            return type.isResolved();
        }
        if (name.indexOf(36) != -1) {
            return type.isResolved();
        }
        final ModuleNode module = this.currentClass.getModule();
        if (module.hasPackageName() && name.indexOf(46) == -1) {
            return type.isResolved();
        }
        final GroovyClassLoader gcl = this.compilationUnit.getClassLoader();
        URL url = null;
        try {
            url = gcl.getResourceLoader().loadGroovySource(name);
        }
        catch (MalformedURLException ex) {}
        if (url != null) {
            if (type.isResolved()) {
                final Class cls = type.getTypeClass();
                if (!this.isSourceNewer(url, cls)) {
                    return true;
                }
                this.cachedClasses.remove(type.getName());
                type.setRedirect(null);
            }
            final SourceUnit su = this.compilationUnit.addSource(url);
            this.currentClass.getCompileUnit().addClassNodeToCompile(type, su);
            return true;
        }
        return type.isResolved();
    }
    
    private String replaceLastPoint(String name) {
        final int lastPoint = name.lastIndexOf(46);
        name = new StringBuffer().append(name.substring(0, lastPoint)).append("$").append(name.substring(lastPoint + 1)).toString();
        return name;
    }
    
    private boolean resolveFromStaticInnerClasses(final ClassNode type, boolean testStaticInnerClasses) {
        if (type instanceof LowerCaseClass) {
            return false;
        }
        testStaticInnerClasses &= type.hasPackageName();
        if (testStaticInnerClasses) {
            if (type instanceof ConstructedClassWithPackage) {
                final ConstructedClassWithPackage tmp = (ConstructedClassWithPackage)type;
                final String savedName = tmp.className;
                tmp.className = this.replaceLastPoint(savedName);
                if (this.resolve(tmp, false, true, true)) {
                    type.setRedirect(tmp.redirect());
                    return true;
                }
                tmp.className = savedName;
            }
            else {
                final String savedName2 = type.getName();
                final String replacedPointType = this.replaceLastPoint(savedName2);
                type.setName(replacedPointType);
                if (this.resolve(type, false, true, true)) {
                    return true;
                }
                type.setName(savedName2);
            }
        }
        return false;
    }
    
    private boolean resolveFromDefaultImports(final ClassNode type, boolean testDefaultImports) {
        testDefaultImports &= !type.hasPackageName();
        testDefaultImports &= !(type instanceof LowerCaseClass);
        if (testDefaultImports) {
            for (int i = 0, size = ResolveVisitor.DEFAULT_IMPORTS.length; i < size; ++i) {
                final String packagePrefix = ResolveVisitor.DEFAULT_IMPORTS[i];
                final String name = type.getName();
                final ConstructedClassWithPackage tmp = new ConstructedClassWithPackage(packagePrefix, name);
                if (this.resolve(tmp, false, false, false)) {
                    type.setRedirect(tmp.redirect());
                    return true;
                }
            }
            final String name2 = type.getName();
            if (name2.equals("BigInteger")) {
                type.setRedirect(ClassHelper.BigInteger_TYPE);
                return true;
            }
            if (name2.equals("BigDecimal")) {
                type.setRedirect(ClassHelper.BigDecimal_TYPE);
                return true;
            }
        }
        return false;
    }
    
    private boolean resolveFromCompileUnit(final ClassNode type) {
        final CompileUnit compileUnit = this.currentClass.getCompileUnit();
        if (compileUnit == null) {
            return false;
        }
        final ClassNode cuClass = compileUnit.getClass(type.getName());
        if (cuClass != null) {
            if (type != cuClass) {
                type.setRedirect(cuClass);
            }
            return true;
        }
        return false;
    }
    
    private void ambiguousClass(final ClassNode type, final ClassNode iType, final String name) {
        if (type.getName().equals(iType.getName())) {
            this.addError("reference to " + name + " is ambiguous, both class " + type.getName() + " and " + iType.getName() + " match", type);
        }
        else {
            type.setRedirect(iType);
        }
    }
    
    private boolean resolveAliasFromModule(final ClassNode type) {
        if (type instanceof ConstructedClassWithPackage) {
            return false;
        }
        final ModuleNode module = this.currentClass.getModule();
        if (module == null) {
            return false;
        }
        String pname;
        final String name = pname = type.getName();
        int index = name.length();
        while (true) {
            pname = name.substring(0, index);
            ClassNode aliasedNode = null;
            ImportNode importNode = module.getImport(pname);
            if (importNode != null && importNode != this.currImportNode) {
                aliasedNode = importNode.getType();
            }
            if (aliasedNode == null) {
                importNode = module.getStaticImports().get(pname);
                if (importNode != null && importNode != this.currImportNode) {
                    final ClassNode tmp = ClassHelper.make(importNode.getType().getName() + "$" + importNode.getFieldName());
                    if (this.resolve(tmp, false, false, true) && (tmp.getModifiers() & 0x8) != 0x0) {
                        type.setRedirect(tmp.redirect());
                        return true;
                    }
                }
            }
            if (aliasedNode != null) {
                if (pname.length() == name.length()) {
                    type.setRedirect(aliasedNode);
                    return true;
                }
                final String className = aliasedNode.getNameWithoutPackage() + '$' + name.substring(pname.length() + 1).replace('.', '$');
                final ConstructedClassWithPackage tmp2 = new ConstructedClassWithPackage(aliasedNode.getPackageName() + ".", className);
                if (this.resolve(tmp2, true, true, false)) {
                    type.setRedirect(tmp2.redirect());
                    return true;
                }
            }
            index = pname.lastIndexOf(46);
            if (index == -1) {
                return false;
            }
        }
    }
    
    private boolean resolveFromModule(final ClassNode type, final boolean testModuleImports) {
        if (type instanceof LowerCaseClass) {
            return this.resolveAliasFromModule(type);
        }
        final String name = type.getName();
        final ModuleNode module = this.currentClass.getModule();
        if (module == null) {
            return false;
        }
        boolean newNameUsed = false;
        if (!type.hasPackageName() && module.hasPackageName() && !(type instanceof ConstructedClassWithPackage)) {
            type.setName(module.getPackageName() + name);
            newNameUsed = true;
        }
        final List<ClassNode> moduleClasses = module.getClasses();
        for (final ClassNode mClass : moduleClasses) {
            if (mClass.getName().equals(type.getName())) {
                if (mClass != type) {
                    type.setRedirect(mClass);
                }
                return true;
            }
        }
        if (newNameUsed) {
            type.setName(name);
        }
        if (testModuleImports) {
            if (this.resolveAliasFromModule(type)) {
                return true;
            }
            if (module.hasPackageName()) {
                final ConstructedClassWithPackage tmp = new ConstructedClassWithPackage(module.getPackageName(), name);
                if (this.resolve(tmp, false, false, false)) {
                    this.ambiguousClass(type, tmp, name);
                    type.setRedirect(tmp.redirect());
                    return true;
                }
            }
            for (final ImportNode importNode : module.getStaticImports().values()) {
                if (importNode.getFieldName().equals(name)) {
                    final ClassNode tmp2 = ClassHelper.make(importNode.getType().getName() + "$" + name);
                    if (this.resolve(tmp2, false, false, true) && (tmp2.getModifiers() & 0x8) != 0x0) {
                        type.setRedirect(tmp2.redirect());
                        return true;
                    }
                    continue;
                }
            }
            for (final ImportNode importNode : module.getStarImports()) {
                final String packagePrefix = importNode.getPackageName();
                final ConstructedClassWithPackage tmp3 = new ConstructedClassWithPackage(packagePrefix, name);
                if (this.resolve(tmp3, false, false, true)) {
                    this.ambiguousClass(type, tmp3, name);
                    type.setRedirect(tmp3.redirect());
                    return true;
                }
            }
            for (final ImportNode importNode : module.getStaticStarImports().values()) {
                final ClassNode tmp2 = ClassHelper.make(importNode.getClassName() + "$" + name);
                if (this.resolve(tmp2, false, false, true) && (tmp2.getModifiers() & 0x8) != 0x0) {
                    this.ambiguousClass(type, tmp2, name);
                    type.setRedirect(tmp2.redirect());
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean resolveToClass(final ClassNode type) {
        final String name = type.getName();
        if (type instanceof LowerCaseClass) {
            this.cachedClasses.put(name, ResolveVisitor.NO_CLASS);
        }
        final Object cached = this.cachedClasses.get(name);
        if (cached == ResolveVisitor.NO_CLASS) {
            return false;
        }
        if (cached == ResolveVisitor.SCRIPT) {
            throw new GroovyBugError("name " + name + " was marked as script, but was not resolved as such");
        }
        if (cached != null) {
            return true;
        }
        if (this.currentClass.getModule().hasPackageName() && name.indexOf(46) == -1) {
            return false;
        }
        final GroovyClassLoader loader = this.compilationUnit.getClassLoader();
        Class cls;
        try {
            cls = loader.loadClass(name, false, true);
        }
        catch (ClassNotFoundException cnfe) {
            this.cachedClasses.put(name, ResolveVisitor.SCRIPT);
            return false;
        }
        catch (CompilationFailedException cfe) {
            this.compilationUnit.getErrorCollector().addErrorAndContinue(new ExceptionMessage(cfe, true, this.source));
            return false;
        }
        if (cls == null) {
            return false;
        }
        final ClassNode cn = ClassHelper.make(cls);
        this.cachedClasses.put(name, cn);
        type.setRedirect(cn);
        return cls.getClassLoader() == loader;
    }
    
    @Override
    public Expression transform(final Expression exp) {
        if (exp == null) {
            return null;
        }
        Expression ret = null;
        if (exp instanceof VariableExpression) {
            ret = this.transformVariableExpression((VariableExpression)exp);
        }
        else if (exp.getClass() == PropertyExpression.class) {
            ret = this.transformPropertyExpression((PropertyExpression)exp);
        }
        else if (exp instanceof DeclarationExpression) {
            ret = this.transformDeclarationExpression((DeclarationExpression)exp);
        }
        else if (exp instanceof BinaryExpression) {
            ret = this.transformBinaryExpression((BinaryExpression)exp);
        }
        else if (exp instanceof MethodCallExpression) {
            ret = this.transformMethodCallExpression((MethodCallExpression)exp);
        }
        else if (exp instanceof ClosureExpression) {
            ret = this.transformClosureExpression((ClosureExpression)exp);
        }
        else if (exp instanceof ConstructorCallExpression) {
            ret = this.transformConstructorCallExpression((ConstructorCallExpression)exp);
        }
        else if (exp instanceof AnnotationConstantExpression) {
            ret = this.transformAnnotationConstantExpression((AnnotationConstantExpression)exp);
        }
        else {
            this.resolveOrFail(exp.getType(), exp);
            ret = exp.transformExpression(this);
        }
        if (ret != null && ret != exp) {
            ret.setSourcePosition(exp);
        }
        return ret;
    }
    
    private String lookupClassName(final PropertyExpression pe) {
        boolean doInitialClassTest = true;
        String name = "";
        Expression it = pe;
        while (it != null) {
            if (it instanceof VariableExpression) {
                final VariableExpression ve = (VariableExpression)it;
                if (ve.isSuperExpression() || ve.isThisExpression()) {
                    return null;
                }
                final String varName = ve.getName();
                if (!doInitialClassTest) {
                    name = varName + "." + name;
                    break;
                }
                if (!this.testVanillaNameForClass(varName)) {
                    return null;
                }
                doInitialClassTest = false;
                name = varName;
                break;
            }
            else {
                if (it.getClass() != PropertyExpression.class) {
                    return null;
                }
                final PropertyExpression current = (PropertyExpression)it;
                final String propertyPart = current.getPropertyAsString();
                if (propertyPart == null || propertyPart.equals("class")) {
                    return null;
                }
                if (doInitialClassTest) {
                    if (!this.testVanillaNameForClass(propertyPart)) {
                        return null;
                    }
                    doInitialClassTest = false;
                    name = propertyPart;
                }
                else {
                    name = propertyPart + "." + name;
                }
                it = ((PropertyExpression)it).getObjectExpression();
            }
        }
        if (name.length() == 0) {
            return null;
        }
        return name;
    }
    
    private Expression correctClassClassChain(final PropertyExpression pe) {
        final LinkedList<Expression> stack = new LinkedList<Expression>();
        ClassExpression found = null;
        for (Expression it = pe; it != null; it = ((PropertyExpression)it).getObjectExpression()) {
            if (it instanceof ClassExpression) {
                found = (ClassExpression)it;
                break;
            }
            if (it.getClass() != PropertyExpression.class) {
                return pe;
            }
            stack.addFirst(it);
        }
        if (found == null) {
            return pe;
        }
        if (stack.isEmpty()) {
            return pe;
        }
        Object stackElement = stack.removeFirst();
        if (stackElement.getClass() != PropertyExpression.class) {
            return pe;
        }
        final PropertyExpression classPropertyExpression = (PropertyExpression)stackElement;
        final String propertyNamePart = classPropertyExpression.getPropertyAsString();
        if (propertyNamePart == null || !propertyNamePart.equals("class")) {
            return pe;
        }
        found.setSourcePosition(classPropertyExpression);
        if (stack.isEmpty()) {
            return found;
        }
        stackElement = stack.removeFirst();
        if (stackElement.getClass() != PropertyExpression.class) {
            return pe;
        }
        final PropertyExpression classPropertyExpressionContainer = (PropertyExpression)stackElement;
        classPropertyExpressionContainer.setObjectExpression(found);
        return pe;
    }
    
    protected Expression transformPropertyExpression(PropertyExpression pe) {
        final boolean itlp = this.isTopLevelProperty;
        final boolean ipe = this.inPropertyExpression;
        Expression objectExpression = pe.getObjectExpression();
        this.inPropertyExpression = true;
        this.isTopLevelProperty = (objectExpression.getClass() != PropertyExpression.class);
        objectExpression = this.transform(objectExpression);
        this.inPropertyExpression = false;
        final Expression property = this.transform(pe.getProperty());
        this.isTopLevelProperty = itlp;
        this.inPropertyExpression = ipe;
        final boolean spreadSafe = pe.isSpreadSafe();
        final PropertyExpression old = pe;
        pe = new PropertyExpression(objectExpression, property, pe.isSafe());
        pe.setSpreadSafe(spreadSafe);
        pe.setSourcePosition(old);
        final String className = this.lookupClassName(pe);
        if (className != null) {
            final ClassNode type = ClassHelper.make(className);
            if (this.resolve(type)) {
                final Expression ret = new ClassExpression(type);
                ret.setSourcePosition(pe);
                return ret;
            }
        }
        if (objectExpression instanceof ClassExpression && pe.getPropertyAsString() != null) {
            final ClassExpression ce = (ClassExpression)objectExpression;
            final ClassNode type2 = ClassHelper.make(ce.getType().getName() + "$" + pe.getPropertyAsString());
            if (this.resolve(type2, false, false, false)) {
                final Expression ret2 = new ClassExpression(type2);
                ret2.setSourcePosition(ce);
                return ret2;
            }
        }
        Expression ret3 = pe;
        this.checkThisAndSuperAsPropertyAccess(pe);
        if (this.isTopLevelProperty) {
            ret3 = this.correctClassClassChain(pe);
        }
        return ret3;
    }
    
    private void checkThisAndSuperAsPropertyAccess(final PropertyExpression expression) {
        if (expression.isImplicitThis()) {
            return;
        }
        final String prop = expression.getPropertyAsString();
        if (prop == null) {
            return;
        }
        if (!prop.equals("this") && !prop.equals("super")) {
            return;
        }
        if (prop.equals("super")) {
            this.addError("Inner classes referencing outer classes using super is not supported yet.", expression);
        }
        if (!(expression.getObjectExpression() instanceof ClassExpression)) {
            this.addError("The usage of '.this' or '.super' requires an explicit class in front.", expression);
            return;
        }
        if (!(this.currentClass instanceof InnerClassNode)) {
            this.addError("The usage of '.this' and '.super' is only allowed in a inner class", expression);
            return;
        }
        ClassNode type;
        ClassNode iterType;
        for (type = expression.getObjectExpression().getType(), iterType = this.currentClass; iterType != null && !iterType.equals(type); iterType = iterType.getOuterClass()) {}
        if (iterType == null) {
            this.addError("The class '" + type.getName() + "' needs to be an " + "outer class of '" + this.currentClass.getName() + "'.", expression);
        }
        if ((this.currentClass.getModifiers() & 0x8) == 0x0) {
            return;
        }
        if (!this.currentScope.isInStaticContext()) {
            return;
        }
        this.addError("The usage of '.this' and '.super' is only in nonstatic context", expression);
    }
    
    protected Expression transformVariableExpression(final VariableExpression ve) {
        final Variable v = ve.getAccessedVariable();
        if (!(v instanceof DynamicVariable) && !this.checkingVariableTypeInDeclaration) {
            return ve;
        }
        if (v instanceof DynamicVariable) {
            final String name = ve.getName();
            ClassNode t = ClassHelper.make(name);
            boolean isClass = t.isResolved();
            if (!isClass) {
                if (Character.isLowerCase(name.charAt(0))) {
                    t = new LowerCaseClass(name);
                }
                isClass = this.resolve(t);
                if (!isClass) {
                    isClass = this.resolveToInnerEnum(t);
                }
            }
            if (isClass) {
                for (VariableScope scope = this.currentScope; scope != null && !scope.isRoot(); scope = scope.getParent()) {
                    if (scope.isRoot()) {
                        break;
                    }
                    if (scope.removeReferencedClassVariable(ve.getName()) == null) {
                        break;
                    }
                }
                final ClassExpression ce = new ClassExpression(t);
                ce.setSourcePosition(ve);
                return ce;
            }
        }
        this.resolveOrFail(ve.getType(), ve);
        return ve;
    }
    
    private boolean testVanillaNameForClass(final String name) {
        return name != null && name.length() != 0 && !Character.isLowerCase(name.charAt(0));
    }
    
    protected Expression transformBinaryExpression(final BinaryExpression be) {
        final Expression left = this.transform(be.getLeftExpression());
        final int type = be.getOperation().getType();
        if ((type == 1100 || type == 100) && left instanceof ClassExpression) {
            final ClassExpression ce = (ClassExpression)left;
            String error = "you tried to assign a value to the class '" + ce.getType().getName() + "'";
            if (ce.getType().isScript()) {
                error += ". Do you have a script with this name?";
            }
            this.addError(error, be.getLeftExpression());
            return be;
        }
        if (left instanceof ClassExpression) {
            if (be.getRightExpression() instanceof ListExpression) {
                final ListExpression list = (ListExpression)be.getRightExpression();
                if (list.getExpressions().isEmpty()) {
                    final ClassExpression ce2 = new ClassExpression(left.getType().makeArray());
                    ce2.setSourcePosition(be);
                    return ce2;
                }
                boolean map = true;
                for (final Expression expression : list.getExpressions()) {
                    if (!(expression instanceof MapEntryExpression)) {
                        map = false;
                        break;
                    }
                }
                if (map) {
                    final MapExpression me = new MapExpression();
                    for (final Expression expression2 : list.getExpressions()) {
                        me.addMapEntryExpression((MapEntryExpression)expression2);
                    }
                    me.setSourcePosition(list);
                    final CastExpression ce3 = new CastExpression(left.getType(), me);
                    ce3.setSourcePosition(be);
                    return ce3;
                }
            }
            if (be.getRightExpression() instanceof MapEntryExpression) {
                final MapExpression me2 = new MapExpression();
                me2.addMapEntryExpression((MapEntryExpression)be.getRightExpression());
                me2.setSourcePosition(be.getRightExpression());
                final CastExpression ce4 = new CastExpression(left.getType(), me2);
                ce4.setSourcePosition(be);
                return ce4;
            }
        }
        final Expression right = this.transform(be.getRightExpression());
        be.setLeftExpression(left);
        be.setRightExpression(right);
        return be;
    }
    
    protected Expression transformClosureExpression(final ClosureExpression ce) {
        final boolean oldInClosure = this.inClosure;
        this.inClosure = true;
        final Parameter[] paras = ce.getParameters();
        if (paras != null) {
            for (final Parameter para : paras) {
                final ClassNode t = para.getType();
                this.resolveOrFail(t, ce);
                if (para.hasInitialExpression()) {
                    final Object initialVal = para.getInitialExpression();
                    if (initialVal instanceof Expression) {
                        para.setInitialExpression(this.transform((Expression)initialVal));
                    }
                }
                this.visitAnnotations(para);
            }
        }
        final Statement code = ce.getCode();
        if (code != null) {
            code.visit(this);
        }
        this.inClosure = oldInClosure;
        return ce;
    }
    
    protected Expression transformConstructorCallExpression(final ConstructorCallExpression cce) {
        final ClassNode type = cce.getType();
        this.resolveOrFail(type, cce);
        if (Modifier.isAbstract(type.getModifiers())) {
            this.addError("You cannot create an instance from the abstract " + this.getDescription(type) + ".", cce);
        }
        this.isSpecialConstructorCall = cce.isSpecialCall();
        final Expression ret = cce.transformExpression(this);
        this.isSpecialConstructorCall = false;
        return ret;
    }
    
    private String getDescription(final ClassNode node) {
        return (node.isInterface() ? "interface" : "class") + " '" + node.getName() + "'";
    }
    
    protected Expression transformMethodCallExpression(final MethodCallExpression mce) {
        final Expression args = this.transform(mce.getArguments());
        final Expression method = this.transform(mce.getMethod());
        final Expression object = this.transform(mce.getObjectExpression());
        this.resolveGenericsTypes(mce.getGenericsTypes());
        final MethodCallExpression result = new MethodCallExpression(object, method, args);
        result.setSafe(mce.isSafe());
        result.setImplicitThis(mce.isImplicitThis());
        result.setSpreadSafe(mce.isSpreadSafe());
        result.setSourcePosition(mce);
        result.setGenericsTypes(mce.getGenericsTypes());
        return result;
    }
    
    protected Expression transformDeclarationExpression(final DeclarationExpression de) {
        final Expression oldLeft = de.getLeftExpression();
        this.checkingVariableTypeInDeclaration = true;
        final Expression left = this.transform(oldLeft);
        this.checkingVariableTypeInDeclaration = false;
        if (left instanceof ClassExpression) {
            final ClassExpression ce = (ClassExpression)left;
            this.addError("you tried to assign a value to the class " + ce.getType().getName(), oldLeft);
            return de;
        }
        final Expression right = this.transform(de.getRightExpression());
        if (right == de.getRightExpression()) {
            return de;
        }
        final DeclarationExpression newDeclExpr = new DeclarationExpression(left, de.getOperation(), right);
        newDeclExpr.setSourcePosition(de);
        return newDeclExpr;
    }
    
    protected Expression transformAnnotationConstantExpression(final AnnotationConstantExpression ace) {
        final AnnotationNode an = (AnnotationNode)ace.getValue();
        final ClassNode type = an.getClassNode();
        this.resolveOrFail(type, ", unable to find class for annotation", an);
        for (final Map.Entry<String, Expression> member : an.getMembers().entrySet()) {
            member.setValue(this.transform(member.getValue()));
        }
        return ace;
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
        final List<AnnotationNode> annotations = node.getAnnotations();
        if (annotations.isEmpty()) {
            return;
        }
        final Map<String, AnnotationNode> tmpAnnotations = new HashMap<String, AnnotationNode>();
        for (final AnnotationNode an : annotations) {
            if (an.isBuiltIn()) {
                continue;
            }
            final ClassNode annType = an.getClassNode();
            this.resolveOrFail(annType, ",  unable to find class for annotation", an);
            for (final Map.Entry<String, Expression> member : an.getMembers().entrySet()) {
                Expression newValue = this.transform(member.getValue());
                newValue = this.transformInlineConstants(newValue);
                member.setValue(newValue);
                this.checkAnnotationMemberValue(newValue);
            }
            if (!annType.isResolved()) {
                continue;
            }
            final Class annTypeClass = annType.getTypeClass();
            final Retention retAnn = annTypeClass.getAnnotation(Retention.class);
            if (retAnn == null || !retAnn.value().equals(RetentionPolicy.RUNTIME)) {
                continue;
            }
            final AnnotationNode anyPrevAnnNode = tmpAnnotations.put(annTypeClass.getName(), an);
            if (anyPrevAnnNode == null) {
                continue;
            }
            this.addError("Cannot specify duplicate annotation on the same member : " + annType.getName(), an);
        }
    }
    
    private Expression transformInlineConstants(final Expression exp) {
        if (exp instanceof PropertyExpression) {
            final PropertyExpression pe = (PropertyExpression)exp;
            if (pe.getObjectExpression() instanceof ClassExpression) {
                final ClassExpression ce = (ClassExpression)pe.getObjectExpression();
                final ClassNode type = ce.getType();
                if (type.isEnum()) {
                    return exp;
                }
                final FieldNode fn = type.getField(pe.getPropertyAsString());
                if (fn != null && !fn.isEnum() && fn.isStatic() && fn.isFinal() && fn.getInitialValueExpression() instanceof ConstantExpression) {
                    return fn.getInitialValueExpression();
                }
            }
        }
        else {
            if (exp instanceof ListExpression) {
                final ListExpression le = (ListExpression)exp;
                final ListExpression result = new ListExpression();
                for (final Expression e : le.getExpressions()) {
                    result.addExpression(this.transformInlineConstants(e));
                }
                return result;
            }
            if (exp instanceof AnnotationConstantExpression) {
                final ConstantExpression ce2 = (ConstantExpression)exp;
                if (ce2.getValue() instanceof AnnotationNode) {
                    final AnnotationNode an = (AnnotationNode)ce2.getValue();
                    for (final Map.Entry<String, Expression> member : an.getMembers().entrySet()) {
                        member.setValue(this.transformInlineConstants(member.getValue()));
                    }
                }
            }
        }
        return exp;
    }
    
    private void checkAnnotationMemberValue(final Expression newValue) {
        if (newValue instanceof PropertyExpression) {
            final PropertyExpression pe = (PropertyExpression)newValue;
            if (!(pe.getObjectExpression() instanceof ClassExpression)) {
                this.addError("unable to find class '" + pe.getText() + "' for annotation attribute constant", pe.getObjectExpression());
            }
        }
        else if (newValue instanceof ListExpression) {
            final ListExpression le = (ListExpression)newValue;
            for (final Expression e : le.getExpressions()) {
                this.checkAnnotationMemberValue(e);
            }
        }
    }
    
    @Override
    public void visitClass(final ClassNode node) {
        final ClassNode oldNode = this.currentClass;
        final Map<String, GenericsType> oldPNames = this.genericParameterNames;
        this.genericParameterNames = new HashMap<String, GenericsType>(this.genericParameterNames);
        this.currentClass = node;
        this.resolveGenericsHeader(node.getGenericsTypes());
        final ModuleNode module = node.getModule();
        if (!module.hasImportsResolved()) {
            final List l = module.getImports();
            for (final ImportNode importNode : module.getImports()) {
                this.currImportNode = importNode;
                final ClassNode type = importNode.getType();
                if (this.resolve(type, false, false, true)) {
                    this.currImportNode = null;
                }
                else {
                    this.currImportNode = null;
                    this.addError("unable to resolve class " + type.getName(), type);
                }
            }
            for (final ImportNode importNode : module.getStaticStarImports().values()) {
                final ClassNode type = importNode.getType();
                if (this.resolve(type, false, false, true)) {
                    continue;
                }
                if (type.getPackageName() == null && node.getPackageName() != null) {
                    final String oldTypeName = type.getName();
                    type.setName(node.getPackageName() + "." + oldTypeName);
                    if (this.resolve(type, false, false, true)) {
                        continue;
                    }
                    type.setName(oldTypeName);
                }
                this.addError("unable to resolve class " + type.getName(), type);
            }
            for (final ImportNode importNode : module.getStaticImports().values()) {
                final ClassNode type = importNode.getType();
                if (this.resolve(type, true, true, true)) {
                    continue;
                }
                this.addError("unable to resolve class " + type.getName(), type);
            }
            for (final ImportNode importNode : module.getStaticStarImports().values()) {
                final ClassNode type = importNode.getType();
                if (this.resolve(type, true, true, true)) {
                    continue;
                }
                this.addError("unable to resolve class " + type.getName(), type);
            }
            module.setImportsResolved(true);
        }
        final ClassNode sn = node.getUnresolvedSuperClass();
        if (sn != null) {
            this.resolveOrFail(sn, node, true);
        }
        for (final ClassNode anInterface : node.getInterfaces()) {
            this.resolveOrFail(anInterface, node, true);
        }
        this.checkCyclicInheritence(node, node.getUnresolvedSuperClass(), node.getInterfaces());
        super.visitClass(node);
        this.genericParameterNames = oldPNames;
        this.currentClass = oldNode;
    }
    
    private void checkCyclicInheritence(final ClassNode originalNode, final ClassNode parentToCompare, final ClassNode[] interfacesToCompare) {
        if (!originalNode.isInterface()) {
            if (parentToCompare == null) {
                return;
            }
            if (originalNode == parentToCompare.redirect()) {
                this.addError("Cyclic inheritance involving " + parentToCompare.getName() + " in class " + originalNode.getName(), originalNode);
                return;
            }
            if (interfacesToCompare != null && interfacesToCompare.length > 0) {
                for (final ClassNode intfToCompare : interfacesToCompare) {
                    if (originalNode == intfToCompare.redirect()) {
                        this.addError("Cycle detected: the type " + originalNode.getName() + " cannot implement itself", originalNode);
                        return;
                    }
                }
            }
            if (parentToCompare == ClassHelper.OBJECT_TYPE) {
                return;
            }
            this.checkCyclicInheritence(originalNode, parentToCompare.getUnresolvedSuperClass(), null);
        }
        else {
            if (interfacesToCompare == null || interfacesToCompare.length <= 0) {
                return;
            }
            for (final ClassNode intfToCompare : interfacesToCompare) {
                if (originalNode == intfToCompare.redirect()) {
                    this.addError("Cyclic inheritance involving " + intfToCompare.getName() + " in interface " + originalNode.getName(), originalNode);
                    return;
                }
            }
            for (final ClassNode intf : interfacesToCompare) {
                this.checkCyclicInheritence(originalNode, null, intf.getInterfaces());
            }
        }
    }
    
    @Override
    public void visitCatchStatement(final CatchStatement cs) {
        this.resolveOrFail(cs.getExceptionType(), cs);
        if (cs.getExceptionType() == ClassHelper.DYNAMIC_TYPE) {
            cs.getVariable().setType(ClassHelper.make(Exception.class));
        }
        super.visitCatchStatement(cs);
    }
    
    @Override
    public void visitForLoop(final ForStatement forLoop) {
        this.resolveOrFail(forLoop.getVariableType(), forLoop);
        super.visitForLoop(forLoop);
    }
    
    @Override
    public void visitBlockStatement(final BlockStatement block) {
        final VariableScope oldScope = this.currentScope;
        this.currentScope = block.getVariableScope();
        super.visitBlockStatement(block);
        this.currentScope = oldScope;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    private void resolveGenericsTypes(final GenericsType[] types) {
        if (types == null) {
            return;
        }
        this.currentClass.setUsingGenerics(true);
        for (final GenericsType type : types) {
            this.resolveGenericsType(type);
        }
    }
    
    private void resolveGenericsHeader(final GenericsType[] types) {
        if (types == null) {
            return;
        }
        this.currentClass.setUsingGenerics(true);
        for (final GenericsType type : types) {
            final ClassNode classNode = type.getType();
            final String name = type.getName();
            final ClassNode[] bounds = type.getUpperBounds();
            if (bounds != null) {
                boolean nameAdded = false;
                for (final ClassNode upperBound : bounds) {
                    if ((!nameAdded && upperBound != null) || !this.resolve(classNode)) {
                        this.genericParameterNames.put(name, type);
                        type.setPlaceholder(true);
                        classNode.setRedirect(upperBound);
                        nameAdded = true;
                    }
                    this.resolveOrFail(upperBound, classNode);
                }
            }
            else {
                this.genericParameterNames.put(name, type);
                classNode.setRedirect(ClassHelper.OBJECT_TYPE);
                type.setPlaceholder(true);
            }
        }
    }
    
    private void resolveGenericsType(final GenericsType genericsType) {
        if (genericsType.isResolved()) {
            return;
        }
        this.currentClass.setUsingGenerics(true);
        final ClassNode type = genericsType.getType();
        final String name = type.getName();
        final ClassNode[] bounds = genericsType.getUpperBounds();
        if (!this.genericParameterNames.containsKey(name)) {
            if (bounds != null) {
                for (final ClassNode upperBound : bounds) {
                    this.resolveOrFail(upperBound, genericsType);
                    type.setRedirect(upperBound);
                    this.resolveGenericsTypes(upperBound.getGenericsTypes());
                }
            }
            else if (genericsType.isWildcard()) {
                type.setRedirect(ClassHelper.OBJECT_TYPE);
            }
            else {
                this.resolveOrFail(type, genericsType);
            }
        }
        else {
            final GenericsType gt = this.genericParameterNames.get(name);
            type.setRedirect(gt.getType());
            genericsType.setPlaceholder(true);
        }
        if (genericsType.getLowerBound() != null) {
            this.resolveOrFail(genericsType.getLowerBound(), genericsType);
        }
        this.resolveGenericsTypes(type.getGenericsTypes());
        genericsType.setResolved(genericsType.getType().isResolved());
    }
    
    static {
        DEFAULT_IMPORTS = new String[] { "java.lang.", "java.io.", "java.net.", "java.util.", "groovy.lang.", "groovy.util." };
        NO_CLASS = new Object();
        SCRIPT = new Object();
    }
    
    private static class ConstructedClassWithPackage extends ClassNode
    {
        String prefix;
        String className;
        
        public ConstructedClassWithPackage(final String pkg, final String name) {
            super(pkg + name, 1, ClassHelper.OBJECT_TYPE);
            this.isPrimaryNode = false;
            this.prefix = pkg;
            this.className = name;
        }
        
        @Override
        public String getName() {
            if (this.redirect() != this) {
                return super.getName();
            }
            return this.prefix + this.className;
        }
        
        @Override
        public boolean hasPackageName() {
            if (this.redirect() != this) {
                return super.hasPackageName();
            }
            return this.className.indexOf(46) != -1;
        }
        
        @Override
        public String setName(final String name) {
            if (this.redirect() != this) {
                return super.setName(name);
            }
            throw new GroovyBugError("ConstructedClassWithPackage#setName should not be called");
        }
    }
    
    private static class LowerCaseClass extends ClassNode
    {
        String className;
        
        public LowerCaseClass(final String name) {
            super(name, 1, ClassHelper.OBJECT_TYPE);
            this.isPrimaryNode = false;
            this.className = name;
        }
        
        @Override
        public String getName() {
            if (this.redirect() != this) {
                return super.getName();
            }
            return this.className;
        }
        
        @Override
        public boolean hasPackageName() {
            return this.redirect() != this && super.hasPackageName();
        }
        
        @Override
        public String setName(final String name) {
            if (this.redirect() != this) {
                return super.setName(name);
            }
            throw new GroovyBugError("ConstructedClassWithPackage#setName should not be called");
        }
    }
}
