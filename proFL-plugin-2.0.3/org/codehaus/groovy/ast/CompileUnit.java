// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.syntax.SyntaxException;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import org.codehaus.groovy.control.SourceUnit;
import java.security.CodeSource;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.util.Map;
import java.util.List;

public class CompileUnit
{
    private final List<ModuleNode> modules;
    private Map<String, ClassNode> classes;
    private CompilerConfiguration config;
    private GroovyClassLoader classLoader;
    private CodeSource codeSource;
    private Map<String, ClassNode> classesToCompile;
    private Map<String, SourceUnit> classNameToSource;
    
    public CompileUnit(final GroovyClassLoader classLoader, final CompilerConfiguration config) {
        this(classLoader, null, config);
    }
    
    public CompileUnit(final GroovyClassLoader classLoader, final CodeSource codeSource, final CompilerConfiguration config) {
        this.modules = new ArrayList<ModuleNode>();
        this.classes = new HashMap<String, ClassNode>();
        this.classesToCompile = new HashMap<String, ClassNode>();
        this.classNameToSource = new HashMap<String, SourceUnit>();
        this.classLoader = classLoader;
        this.config = config;
        this.codeSource = codeSource;
    }
    
    public List<ModuleNode> getModules() {
        return this.modules;
    }
    
    public void addModule(final ModuleNode node) {
        if (node == null) {
            return;
        }
        this.modules.add(node);
        node.setUnit(this);
        this.addClasses(node.getClasses());
    }
    
    public ClassNode getClass(final String name) {
        final ClassNode cn = this.classes.get(name);
        if (cn != null) {
            return cn;
        }
        return this.classesToCompile.get(name);
    }
    
    public List getClasses() {
        final List<ClassNode> answer = new ArrayList<ClassNode>();
        for (final ModuleNode module : this.modules) {
            answer.addAll(module.getClasses());
        }
        return answer;
    }
    
    public CompilerConfiguration getConfig() {
        return this.config;
    }
    
    public GroovyClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public CodeSource getCodeSource() {
        return this.codeSource;
    }
    
    void addClasses(final List<ClassNode> classList) {
        for (final ClassNode node : classList) {
            this.addClass(node);
        }
    }
    
    public void addClass(ClassNode node) {
        node = node.redirect();
        final String name = node.getName();
        final ClassNode stored = this.classes.get(name);
        if (stored != null && stored != node) {
            final SourceUnit nodeSource = node.getModule().getContext();
            final SourceUnit storedSource = stored.getModule().getContext();
            String txt = "Invalid duplicate class definition of class " + node.getName() + " : ";
            if (nodeSource == storedSource) {
                txt = txt + "The source " + nodeSource.getName() + " contains at least two definitions of the class " + node.getName() + ".\n";
                if (node.isScriptBody() || stored.isScriptBody()) {
                    txt += "One of the classes is a explicit generated class using the class statement, the other is a class generated from the script body based on the file name. Solutions are to change the file name or to change the class name.\n";
                }
            }
            else {
                txt = txt + "The sources " + nodeSource.getName() + " and " + storedSource.getName() + " are containing both a class of the name " + node.getName() + ".\n";
            }
            nodeSource.getErrorCollector().addErrorAndContinue(new SyntaxErrorMessage(new SyntaxException(txt, node.getLineNumber(), node.getColumnNumber()), nodeSource));
        }
        this.classes.put(name, node);
        if (this.classesToCompile.containsKey(name)) {
            final ClassNode cn = this.classesToCompile.get(name);
            cn.setRedirect(node);
            this.classesToCompile.remove(name);
        }
    }
    
    public void addClassNodeToCompile(final ClassNode node, final SourceUnit location) {
        this.classesToCompile.put(node.getName(), node);
        this.classNameToSource.put(node.getName(), location);
    }
    
    public SourceUnit getScriptSourceLocation(final String className) {
        return this.classNameToSource.get(className);
    }
    
    public boolean hasClassNodeToCompile() {
        return !this.classesToCompile.isEmpty();
    }
    
    public Iterator<String> iterateClassNodeToCompile() {
        return this.classesToCompile.keySet().iterator();
    }
}
