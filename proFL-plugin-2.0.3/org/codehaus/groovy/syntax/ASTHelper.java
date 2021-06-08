// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

import java.util.HashMap;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.PackageNode;
import java.util.List;
import org.codehaus.groovy.ast.AnnotationNode;
import java.util.ArrayList;
import java.util.Map;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.control.SourceUnit;

public class ASTHelper
{
    private SourceUnit controller;
    private ClassLoader classLoader;
    protected ModuleNode output;
    private String packageName;
    protected static Map resolutions;
    
    public ASTHelper(final SourceUnit controller, final ClassLoader classLoader) {
        this();
        this.controller = controller;
        this.classLoader = classLoader;
    }
    
    public ASTHelper() {
    }
    
    public String getPackageName() {
        return this.packageName;
    }
    
    public void setPackageName(final String packageName) {
        this.setPackage(packageName, new ArrayList<AnnotationNode>());
    }
    
    public PackageNode setPackage(String packageName, final List<AnnotationNode> annotations) {
        this.packageName = packageName;
        if (packageName != null && packageName.length() > 0) {
            packageName += '.';
        }
        final PackageNode packageNode = new PackageNode(packageName);
        packageNode.addAnnotations(annotations);
        this.output.setPackage(packageNode);
        return packageNode;
    }
    
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public void setClassLoader(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public SourceUnit getController() {
        return this.controller;
    }
    
    public void setController(final SourceUnit controller) {
        this.controller = controller;
    }
    
    public static String dot(final String base, final String name) {
        if (base != null && base.length() > 0) {
            return base + "." + name;
        }
        return name;
    }
    
    protected void makeModule() {
        this.output = new ModuleNode(this.controller);
        ASTHelper.resolutions.clear();
    }
    
    protected String dot(final String base) {
        return dot(base, "");
    }
    
    protected void addImport(final ClassNode type, final String name, final String aliasName) {
        this.addImport(type, name, aliasName, new ArrayList<AnnotationNode>());
    }
    
    protected void addImport(final ClassNode type, final String name, String aliasName, final List<AnnotationNode> annotations) {
        if (aliasName == null) {
            aliasName = name;
        }
        this.output.addImport(aliasName, type, annotations);
    }
    
    protected void addStaticImport(final ClassNode type, final String name, final String alias) {
        this.addStaticImport(type, name, alias, new ArrayList<AnnotationNode>());
    }
    
    protected void addStaticImport(final ClassNode type, final String name, String alias, final List<AnnotationNode> annotations) {
        if (alias == null) {
            alias = name;
        }
        this.output.addStaticImport(type, name, alias, annotations);
    }
    
    protected void addStaticStarImport(final ClassNode type, final String importClass) {
        this.addStaticStarImport(type, importClass, new ArrayList<AnnotationNode>());
    }
    
    protected void addStaticStarImport(final ClassNode type, final String importClass, final List<AnnotationNode> annotations) {
        this.output.addStaticStarImport(importClass, type, annotations);
    }
    
    protected void addStarImport(final String importPackage) {
        this.addStarImport(importPackage, new ArrayList<AnnotationNode>());
    }
    
    protected void addStarImport(final String importPackage, final List<AnnotationNode> annotations) {
        this.output.addStarImport(this.dot(importPackage), annotations);
    }
    
    static {
        ASTHelper.resolutions = new HashMap();
    }
}
