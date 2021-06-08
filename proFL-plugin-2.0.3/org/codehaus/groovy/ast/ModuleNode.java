// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.io.File;
import groovy.lang.Binding;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.ast.stmt.Statement;
import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import org.codehaus.groovy.control.SourceUnit;
import java.util.Map;
import java.util.List;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import groovyjarjarasm.asm.Opcodes;

public class ModuleNode extends ASTNode implements Opcodes
{
    private BlockStatement statementBlock;
    List<ClassNode> classes;
    private List<MethodNode> methods;
    private Map<String, ImportNode> imports;
    private List<ImportNode> starImports;
    private Map<String, ImportNode> staticImports;
    private Map<String, ImportNode> staticStarImports;
    private CompileUnit unit;
    private PackageNode packageNode;
    private String description;
    private boolean createClassForStatements;
    private transient SourceUnit context;
    private boolean importsResolved;
    private ClassNode scriptDummy;
    private String mainClassName;
    
    public ModuleNode(final SourceUnit context) {
        this.statementBlock = new BlockStatement();
        this.classes = new LinkedList<ClassNode>();
        this.methods = new ArrayList<MethodNode>();
        this.imports = new HashMap<String, ImportNode>();
        this.starImports = new ArrayList<ImportNode>();
        this.staticImports = new LinkedHashMap<String, ImportNode>();
        this.staticStarImports = new LinkedHashMap<String, ImportNode>();
        this.createClassForStatements = true;
        this.importsResolved = false;
        this.mainClassName = null;
        this.context = context;
    }
    
    public ModuleNode(final CompileUnit unit) {
        this.statementBlock = new BlockStatement();
        this.classes = new LinkedList<ClassNode>();
        this.methods = new ArrayList<MethodNode>();
        this.imports = new HashMap<String, ImportNode>();
        this.starImports = new ArrayList<ImportNode>();
        this.staticImports = new LinkedHashMap<String, ImportNode>();
        this.staticStarImports = new LinkedHashMap<String, ImportNode>();
        this.createClassForStatements = true;
        this.importsResolved = false;
        this.mainClassName = null;
        this.unit = unit;
    }
    
    public BlockStatement getStatementBlock() {
        return this.statementBlock;
    }
    
    public List<MethodNode> getMethods() {
        return this.methods;
    }
    
    public List<ClassNode> getClasses() {
        if (this.createClassForStatements && (!this.statementBlock.isEmpty() || !this.methods.isEmpty() || this.isPackageInfo())) {
            final ClassNode mainClass = this.createStatementsClass();
            this.mainClassName = mainClass.getName();
            this.createClassForStatements = false;
            this.classes.add(0, mainClass);
            mainClass.setModule(this);
            this.addToCompileUnit(mainClass);
        }
        return this.classes;
    }
    
    private boolean isPackageInfo() {
        return this.context != null && this.context.getName() != null && this.context.getName().endsWith("package-info.groovy");
    }
    
    public List<ImportNode> getImports() {
        return new ArrayList<ImportNode>(this.imports.values());
    }
    
    @Deprecated
    public List<String> getImportPackages() {
        final List<String> result = new ArrayList<String>();
        for (final ImportNode importStarNode : this.starImports) {
            result.add(importStarNode.getPackageName());
        }
        return result;
    }
    
    public List<ImportNode> getStarImports() {
        return this.starImports;
    }
    
    public ClassNode getImportType(final String alias) {
        final ImportNode importNode = this.imports.get(alias);
        return (importNode == null) ? null : importNode.getType();
    }
    
    public ImportNode getImport(final String alias) {
        return this.imports.get(alias);
    }
    
    public void addImport(final String alias, final ClassNode type) {
        this.addImport(alias, type, new ArrayList<AnnotationNode>());
    }
    
    public void addImport(final String alias, final ClassNode type, final List<AnnotationNode> annotations) {
        final ImportNode importNode = new ImportNode(type, alias);
        this.imports.put(alias, importNode);
        importNode.addAnnotations(annotations);
    }
    
    @Deprecated
    public String[] addImportPackage(final String packageName) {
        this.addStarImport(packageName);
        return new String[0];
    }
    
    public void addStarImport(final String packageName) {
        this.addStarImport(packageName, new ArrayList<AnnotationNode>());
    }
    
    public void addStarImport(final String packageName, final List<AnnotationNode> annotations) {
        final ImportNode importNode = new ImportNode(packageName);
        importNode.addAnnotations(annotations);
        this.starImports.add(importNode);
    }
    
    public void addStatement(final Statement node) {
        this.statementBlock.addStatement(node);
    }
    
    public void addClass(final ClassNode node) {
        if (this.classes.isEmpty()) {
            this.mainClassName = node.getName();
        }
        this.classes.add(node);
        node.setModule(this);
        this.addToCompileUnit(node);
    }
    
    private void addToCompileUnit(final ClassNode node) {
        if (this.unit != null) {
            this.unit.addClass(node);
        }
    }
    
    public void addMethod(final MethodNode node) {
        this.methods.add(node);
    }
    
    @Override
    public void visit(final GroovyCodeVisitor visitor) {
    }
    
    public String getPackageName() {
        return (this.packageNode == null) ? null : this.packageNode.getName();
    }
    
    public PackageNode getPackage() {
        return this.packageNode;
    }
    
    public void setPackage(final PackageNode packageNode) {
        this.packageNode = packageNode;
    }
    
    public void setPackageName(final String packageName) {
        this.packageNode = new PackageNode(packageName);
    }
    
    public boolean hasPackageName() {
        return this.packageNode != null && this.packageNode.getName() != null;
    }
    
    public boolean hasPackage() {
        return this.packageNode != null;
    }
    
    public SourceUnit getContext() {
        return this.context;
    }
    
    public String getDescription() {
        if (this.context != null) {
            return this.context.getName();
        }
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public CompileUnit getUnit() {
        return this.unit;
    }
    
    void setUnit(final CompileUnit unit) {
        this.unit = unit;
    }
    
    public ClassNode getScriptClassDummy() {
        if (this.scriptDummy != null) {
            this.setScriptBaseClassFromConfig(this.scriptDummy);
            return this.scriptDummy;
        }
        String name = this.getPackageName();
        if (name == null) {
            name = "";
        }
        if (this.getDescription() == null) {
            throw new RuntimeException("Cannot generate main(String[]) class for statements when we have no file description");
        }
        name += this.extractClassFromFileDescription();
        ClassNode classNode;
        if (this.isPackageInfo()) {
            classNode = new ClassNode(name, 1536, ClassHelper.OBJECT_TYPE);
        }
        else {
            classNode = new ClassNode(name, 1, ClassHelper.SCRIPT_TYPE);
            this.setScriptBaseClassFromConfig(classNode);
            classNode.setScript(true);
            classNode.setScriptBody(true);
        }
        return this.scriptDummy = classNode;
    }
    
    private void setScriptBaseClassFromConfig(final ClassNode cn) {
        if (this.unit != null) {
            final String baseClassName = this.unit.getConfig().getScriptBaseClass();
            if (baseClassName != null && !cn.getSuperClass().getName().equals(baseClassName)) {
                cn.setSuperClass(ClassHelper.make(baseClassName));
            }
        }
    }
    
    protected ClassNode createStatementsClass() {
        final ClassNode classNode = this.getScriptClassDummy();
        if (classNode.getName().endsWith("package-info")) {
            return classNode;
        }
        this.handleMainMethodIfPresent(this.methods);
        classNode.addMethod(new MethodNode("main", 9, ClassHelper.VOID_TYPE, new Parameter[] { new Parameter(ClassHelper.STRING_TYPE.makeArray(), "args") }, ClassNode.EMPTY_ARRAY, new ExpressionStatement(new MethodCallExpression(new ClassExpression(ClassHelper.make(InvokerHelper.class)), "runScript", new ArgumentListExpression(new ClassExpression(classNode), new VariableExpression("args"))))));
        classNode.addMethod(new MethodNode("run", 1, ClassHelper.OBJECT_TYPE, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, this.statementBlock));
        classNode.addConstructor(1, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, new BlockStatement());
        final Statement stmt = new ExpressionStatement(new MethodCallExpression(new VariableExpression("super"), "setBinding", new ArgumentListExpression(new VariableExpression("context"))));
        classNode.addConstructor(1, new Parameter[] { new Parameter(ClassHelper.make(Binding.class), "context") }, ClassNode.EMPTY_ARRAY, stmt);
        for (final MethodNode node : this.methods) {
            final int modifiers = node.getModifiers();
            if ((modifiers & 0x400) != 0x0) {
                throw new RuntimeException("Cannot use abstract methods in a script, they are only available inside classes. Method: " + node.getName());
            }
            node.setModifiers(modifiers);
            classNode.addMethod(node);
        }
        return classNode;
    }
    
    private void handleMainMethodIfPresent(final List methods) {
        boolean found = false;
        final Iterator iter = methods.iterator();
        while (iter.hasNext()) {
            final MethodNode node = iter.next();
            if (node.getName().equals("main")) {
                final int modifiers = node.getModifiers();
                if (!node.isStatic() || node.getParameters().length != 1) {
                    continue;
                }
                final ClassNode argType = node.getParameters()[0].getType();
                final ClassNode retType = node.getReturnType();
                final boolean argTypeMatches = argType.equals(ClassHelper.OBJECT_TYPE) || argType.getName().contains("String[]");
                final boolean retTypeMatches = retType == ClassHelper.VOID_TYPE || retType == ClassHelper.OBJECT_TYPE;
                if (!retTypeMatches || !argTypeMatches) {
                    continue;
                }
                if (found) {
                    throw new RuntimeException("Repetitive main method found.");
                }
                found = true;
                if (this.statementBlock.isEmpty()) {
                    this.addStatement(node.getCode());
                }
                iter.remove();
            }
        }
    }
    
    protected String extractClassFromFileDescription() {
        String answer = this.getDescription();
        final int slashIdx = answer.lastIndexOf(47);
        int separatorIdx = answer.lastIndexOf(File.separatorChar);
        final int dotIdx = answer.lastIndexOf(46);
        if (dotIdx > 0 && dotIdx > Math.max(slashIdx, separatorIdx)) {
            answer = answer.substring(0, dotIdx);
        }
        if (slashIdx >= 0) {
            answer = answer.substring(slashIdx + 1);
        }
        separatorIdx = answer.lastIndexOf(File.separatorChar);
        if (separatorIdx >= 0) {
            answer = answer.substring(separatorIdx + 1);
        }
        return answer;
    }
    
    public boolean isEmpty() {
        return this.classes.isEmpty() && this.statementBlock.getStatements().isEmpty();
    }
    
    public void sortClasses() {
        if (this.isEmpty()) {
            return;
        }
        final List<ClassNode> classes = this.getClasses();
        final LinkedList<ClassNode> sorted = new LinkedList<ClassNode>();
        int level = 1;
        while (!classes.isEmpty()) {
            final Iterator<ClassNode> cni = classes.iterator();
            while (cni.hasNext()) {
                ClassNode sn;
                final ClassNode cn = sn = cni.next();
                for (int i = 0; sn != null && i < level; sn = sn.getSuperClass(), ++i) {}
                if (sn != null && sn.isPrimaryClassNode()) {
                    continue;
                }
                cni.remove();
                sorted.addLast(cn);
            }
            ++level;
        }
        this.classes = sorted;
    }
    
    public boolean hasImportsResolved() {
        return this.importsResolved;
    }
    
    public void setImportsResolved(final boolean importsResolved) {
        this.importsResolved = importsResolved;
    }
    
    @Deprecated
    public Map<String, ClassNode> getStaticImportAliases() {
        final Map<String, ClassNode> result = new HashMap<String, ClassNode>();
        for (final Map.Entry<String, ImportNode> entry : this.staticImports.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getType());
        }
        return result;
    }
    
    @Deprecated
    public Map<String, ClassNode> getStaticImportClasses() {
        final Map<String, ClassNode> result = new HashMap<String, ClassNode>();
        for (final Map.Entry<String, ImportNode> entry : this.staticStarImports.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getType());
        }
        return result;
    }
    
    @Deprecated
    public Map<String, String> getStaticImportFields() {
        final Map<String, String> result = new HashMap<String, String>();
        for (final Map.Entry<String, ImportNode> entry : this.staticImports.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getFieldName());
        }
        return result;
    }
    
    public Map<String, ImportNode> getStaticImports() {
        return this.staticImports;
    }
    
    public Map<String, ImportNode> getStaticStarImports() {
        return this.staticStarImports;
    }
    
    @Deprecated
    public void addStaticMethodOrField(final ClassNode type, final String fieldName, final String alias) {
        this.addStaticImport(type, fieldName, alias);
    }
    
    public void addStaticImport(final ClassNode type, final String fieldName, final String alias) {
        this.addStaticImport(type, fieldName, alias, new ArrayList<AnnotationNode>());
    }
    
    public void addStaticImport(final ClassNode type, final String fieldName, final String alias, final List<AnnotationNode> annotations) {
        final ImportNode node = new ImportNode(type, fieldName, alias);
        node.addAnnotations(annotations);
        this.staticImports.put(alias, node);
    }
    
    @Deprecated
    public void addStaticImportClass(final String name, final ClassNode type) {
        this.addStaticStarImport(name, type);
    }
    
    public void addStaticStarImport(final String name, final ClassNode type) {
        this.addStaticStarImport(name, type, new ArrayList<AnnotationNode>());
    }
    
    public void addStaticStarImport(final String name, final ClassNode type, final List<AnnotationNode> annotations) {
        final ImportNode node = new ImportNode(type);
        node.addAnnotations(annotations);
        this.staticStarImports.put(name, node);
    }
    
    public String getMainClassName() {
        return this.mainClassName;
    }
}
