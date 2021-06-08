// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilationUnit;
import java.io.File;
import org.codehaus.groovy.control.CompilerConfiguration;

public class Compiler
{
    public static final Compiler DEFAULT;
    private CompilerConfiguration configuration;
    
    public Compiler() {
        this.configuration = null;
        this.configuration = null;
    }
    
    public Compiler(final CompilerConfiguration configuration) {
        this.configuration = null;
        this.configuration = configuration;
    }
    
    public void compile(final File file) throws CompilationFailedException {
        final CompilationUnit unit = new CompilationUnit(this.configuration);
        unit.addSource(file);
        unit.compile();
    }
    
    public void compile(final File[] files) throws CompilationFailedException {
        final CompilationUnit unit = new CompilationUnit(this.configuration);
        unit.addSources(files);
        unit.compile();
    }
    
    public void compile(final String[] files) throws CompilationFailedException {
        final CompilationUnit unit = new CompilationUnit(this.configuration);
        unit.addSources(files);
        unit.compile();
    }
    
    public void compile(final String name, final String code) throws CompilationFailedException {
        final CompilationUnit unit = new CompilationUnit(this.configuration);
        unit.addSource(new SourceUnit(name, code, this.configuration, unit.getClassLoader(), unit.getErrorCollector()));
        unit.compile();
    }
    
    static {
        DEFAULT = new Compiler();
    }
}
