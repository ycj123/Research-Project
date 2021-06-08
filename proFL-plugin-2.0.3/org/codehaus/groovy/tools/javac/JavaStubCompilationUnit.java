// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.javac;

import java.net.URL;
import java.util.Map;
import org.codehaus.groovy.control.CompilationFailedException;
import java.io.FileNotFoundException;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.SourceUnit;
import java.security.CodeSource;
import java.io.File;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.CompilationUnit;

public class JavaStubCompilationUnit extends CompilationUnit
{
    private static final String DOT_GROOVY = ".groovy";
    private final JavaStubGenerator stubGenerator;
    private int stubCount;
    
    public JavaStubCompilationUnit(final CompilerConfiguration config, final GroovyClassLoader gcl, File destDir) {
        super(config, null, gcl);
        assert config != null;
        final Map options = config.getJointCompilationOptions();
        if (destDir == null) {
            destDir = options.get("stubDir");
        }
        final boolean useJava5 = config.getTargetBytecode().equals("1.5");
        this.stubGenerator = new JavaStubGenerator(destDir, false, useJava5);
        this.addPhaseOperation(new PrimaryClassNodeOperation() {
            @Override
            public void call(final SourceUnit source, final GeneratorContext context, final ClassNode node) throws CompilationFailedException {
                try {
                    JavaStubCompilationUnit.this.stubGenerator.generateClass(node);
                    JavaStubCompilationUnit.this.stubCount++;
                }
                catch (FileNotFoundException e) {
                    source.addException(e);
                }
            }
        }, 4);
    }
    
    public JavaStubCompilationUnit(final CompilerConfiguration config, final GroovyClassLoader gcl) {
        this(config, gcl, (File)null);
    }
    
    public int getStubCount() {
        return this.stubCount;
    }
    
    @Override
    public void compile() throws CompilationFailedException {
        this.stubCount = 0;
        super.compile(4);
    }
    
    @Override
    public void configure(final CompilerConfiguration config) {
        super.configure(config);
        final File targetDir = config.getTargetDirectory();
        if (targetDir != null) {
            final String classOutput = targetDir.getAbsolutePath();
            this.getClassLoader().addClasspath(classOutput);
        }
    }
    
    @Override
    public SourceUnit addSource(final File file) {
        if (file.getName().toLowerCase().endsWith(".groovy")) {
            return super.addSource(file);
        }
        return null;
    }
    
    @Override
    public SourceUnit addSource(final URL url) {
        if (url.getPath().toLowerCase().endsWith(".groovy")) {
            return super.addSource(url);
        }
        return null;
    }
    
    @Deprecated
    public void addSourceFile(final File file) {
        this.addSource(file);
    }
}
