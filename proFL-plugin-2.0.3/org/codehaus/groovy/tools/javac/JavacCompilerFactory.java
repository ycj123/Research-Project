// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.javac;

import org.codehaus.groovy.control.CompilerConfiguration;

public class JavacCompilerFactory implements JavaCompilerFactory
{
    public JavaCompiler createCompiler(final CompilerConfiguration config) {
        return new JavacJavaCompiler(config);
    }
}
