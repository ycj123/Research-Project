// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.apache.tools.ant.DirectoryScanner;
import groovy.lang.GroovyClassLoader;
import java.io.File;
import org.apache.tools.ant.BuildException;
import org.codehaus.groovy.tools.javac.JavaStubCompilationUnit;

public class GenerateStubsTask extends CompileTaskSupport
{
    @Override
    protected void compile() {
        final GroovyClassLoader gcl = this.createClassLoader();
        final JavaStubCompilationUnit cu = new JavaStubCompilationUnit(this.config, gcl, this.destdir);
        int count = 0;
        final String[] list = this.src.list();
        for (int i = 0; i < list.length; ++i) {
            final File basedir = this.getProject().resolveFile(list[i]);
            if (!basedir.exists()) {
                throw new BuildException("Source directory does not exist: " + basedir, this.getLocation());
            }
            final DirectoryScanner scanner = this.getDirectoryScanner(basedir);
            final String[] includes = scanner.getIncludedFiles();
            this.log.debug("Including files from: " + basedir);
            for (int j = 0; j < includes.length; ++j) {
                this.log.debug("    " + includes[j]);
                final File file = new File(basedir, includes[j]);
                cu.addSource(file);
                if (!includes[j].endsWith(".java")) {
                    ++count;
                }
            }
        }
        if (count > 0) {
            this.log.info("Generating " + count + " Java stub" + ((count > 1) ? "s" : "") + " to " + this.destdir);
            cu.compile();
            this.log.info("Generated " + cu.getStubCount() + " Java stub(s)");
        }
        else {
            this.log.info("No sources found for stub generation");
        }
    }
}
