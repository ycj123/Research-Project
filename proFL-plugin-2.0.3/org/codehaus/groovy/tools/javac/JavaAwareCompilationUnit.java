// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.javac;

import java.util.Iterator;
import java.util.List;
import org.codehaus.groovy.ast.ModuleNode;
import java.util.Map;
import java.io.FileNotFoundException;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.classgen.VariableScopeVisitor;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.SourceUnit;
import java.security.CodeSource;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.io.File;
import java.util.LinkedList;
import org.codehaus.groovy.control.CompilationUnit;

public class JavaAwareCompilationUnit extends CompilationUnit
{
    private LinkedList javaSources;
    private JavaStubGenerator stubGenerator;
    private JavaCompilerFactory compilerFactory;
    private File generationGoal;
    private boolean keepStubs;
    
    public JavaAwareCompilationUnit(final CompilerConfiguration configuration) {
        this(configuration, null);
    }
    
    public JavaAwareCompilationUnit(final CompilerConfiguration configuration, final GroovyClassLoader groovyClassLoader) {
        super(configuration, null, groovyClassLoader);
        this.compilerFactory = new JavacCompilerFactory();
        this.javaSources = new LinkedList();
        final Map options = configuration.getJointCompilationOptions();
        this.generationGoal = options.get("stubDir");
        final boolean useJava5 = configuration.getTargetBytecode().equals("1.5");
        this.stubGenerator = new JavaStubGenerator(this.generationGoal, false, useJava5);
        this.keepStubs = Boolean.TRUE.equals(options.get("keepStubs"));
        this.addPhaseOperation(new PrimaryClassNodeOperation() {
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode node) throws CompilationFailedException {
                if (JavaAwareCompilationUnit.this.javaSources.size() != 0) {
                    final VariableScopeVisitor scopeVisitor = new VariableScopeVisitor(source);
                    scopeVisitor.visitClass(node);
                    new JavaAwareResolveVisitor(JavaAwareCompilationUnit.this).startResolving(node, source);
                }
            }
        }, 3);
        this.addPhaseOperation(new PrimaryClassNodeOperation() {
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) throws CompilationFailedException {
                try {
                    if (JavaAwareCompilationUnit.this.javaSources.size() != 0) {
                        JavaAwareCompilationUnit.this.stubGenerator.generateClass(classNode);
                    }
                }
                catch (FileNotFoundException fnfe) {
                    source.addException(fnfe);
                }
            }
        }, 3);
    }
    
    @Override
    public void gotoPhase(final int phase) throws CompilationFailedException {
        super.gotoPhase(phase);
        if (phase == 4 && this.javaSources.size() > 0) {
            for (final ModuleNode module : this.getAST().getModules()) {
                module.setImportsResolved(false);
            }
            try {
                final JavaCompiler compiler = this.compilerFactory.createCompiler(this.getConfiguration());
                compiler.compile(this.javaSources, this);
            }
            finally {
                if (!this.keepStubs) {
                    this.stubGenerator.clean();
                }
                this.javaSources.clear();
            }
        }
    }
    
    @Override
    public void configure(final CompilerConfiguration configuration) {
        super.configure(configuration);
        final File targetDir = configuration.getTargetDirectory();
        if (targetDir != null) {
            final String classOutput = targetDir.getAbsolutePath();
            this.getClassLoader().addClasspath(classOutput);
        }
    }
    
    private void addJavaSource(final File file) {
        final String path = file.getAbsolutePath();
        for (final String su : this.javaSources) {
            if (path.equals(su)) {
                return;
            }
        }
        this.javaSources.add(path);
    }
    
    @Override
    public void addSources(final String[] paths) {
        for (int i = 0; i < paths.length; ++i) {
            final File file = new File(paths[i]);
            if (file.getName().endsWith(".java")) {
                this.addJavaSource(file);
            }
            else {
                this.addSource(file);
            }
        }
    }
    
    @Override
    public void addSources(final File[] files) {
        for (int i = 0; i < files.length; ++i) {
            if (files[i].getName().endsWith(".java")) {
                this.addJavaSource(files[i]);
            }
            else {
                this.addSource(files[i]);
            }
        }
    }
    
    public JavaCompilerFactory getCompilerFactory() {
        return this.compilerFactory;
    }
    
    public void setCompilerFactory(final JavaCompilerFactory compilerFactory) {
        this.compilerFactory = compilerFactory;
    }
}
