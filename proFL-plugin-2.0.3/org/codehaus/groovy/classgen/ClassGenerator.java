// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.ClassNode;
import java.util.LinkedList;
import groovyjarjarasm.asm.Opcodes;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public abstract class ClassGenerator extends ClassCodeVisitorSupport implements Opcodes
{
    protected ClassLoader classLoader;
    protected LinkedList<ClassNode> innerClasses;
    public static final int asmJDKVersion = 47;
    
    public ClassGenerator(final ClassLoader classLoader) {
        this.innerClasses = new LinkedList<ClassNode>();
        this.classLoader = classLoader;
    }
    
    public LinkedList<ClassNode> getInnerClasses() {
        return this.innerClasses;
    }
    
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return null;
    }
    
    public void visitBytecodeSequence(final BytecodeSequence bytecodeSequence) {
    }
}
