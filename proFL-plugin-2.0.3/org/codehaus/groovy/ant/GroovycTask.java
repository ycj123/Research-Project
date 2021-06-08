// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.apache.tools.ant.DirectoryScanner;
import groovy.lang.GroovyClassLoader;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.SourceFileScanner;
import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.GlobPatternMapper;
import java.security.CodeSource;
import org.codehaus.groovy.control.CompilationUnit;

public class GroovycTask extends CompileTaskSupport
{
    protected boolean force;
    
    public void setForce(final boolean flag) {
        this.force = flag;
    }
    
    @Override
    protected void compile() {
        final Path path = this.getClasspath();
        if (path != null) {
            this.config.setClasspath(path.toString());
        }
        this.config.setTargetDirectory(this.destdir);
        final GroovyClassLoader gcl = this.createClassLoader();
        final CompilationUnit compilation = new CompilationUnit(this.config, null, gcl);
        final GlobPatternMapper mapper = new GlobPatternMapper();
        mapper.setFrom("*.groovy");
        mapper.setTo("*.class");
        int count = 0;
        final String[] list = this.src.list();
        for (int i = 0; i < list.length; ++i) {
            final File basedir = this.getProject().resolveFile(list[i]);
            if (!basedir.exists()) {
                throw new BuildException("Source directory does not exist: " + basedir, this.getLocation());
            }
            final DirectoryScanner scanner = this.getDirectoryScanner(basedir);
            final String[] includes = scanner.getIncludedFiles();
            if (this.force) {
                this.log.debug("Forcefully including all files from: " + basedir);
                for (int j = 0; j < includes.length; ++j) {
                    final File file = new File(basedir, includes[j]);
                    this.log.debug("    " + file);
                    compilation.addSource(file);
                    ++count;
                }
            }
            else {
                this.log.debug("Including changed files from: " + basedir);
                final SourceFileScanner sourceScanner = new SourceFileScanner((Task)this);
                final File[] files = sourceScanner.restrictAsFiles(includes, basedir, this.destdir, (FileNameMapper)mapper);
                for (int k = 0; k < files.length; ++k) {
                    this.log.debug("    " + files[k]);
                    compilation.addSource(files[k]);
                    ++count;
                }
            }
        }
        if (count > 0) {
            this.log.info("Compiling " + count + " source file" + ((count > 1) ? "s" : "") + " to " + this.destdir);
            compilation.compile();
        }
        else {
            this.log.info("No sources found to compile");
        }
    }
}
