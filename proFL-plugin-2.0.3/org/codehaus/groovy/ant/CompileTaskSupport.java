// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import java.io.Writer;
import java.io.PrintWriter;
import org.codehaus.groovy.tools.ErrorReporter;
import java.io.StringWriter;
import groovy.lang.GroovyClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.Task;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.io.File;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.taskdefs.MatchingTask;

public abstract class CompileTaskSupport extends MatchingTask
{
    protected final LoggingHelper log;
    protected Path src;
    protected File destdir;
    protected Path classpath;
    protected CompilerConfiguration config;
    protected boolean failOnError;
    
    public CompileTaskSupport() {
        this.log = new LoggingHelper((Task)this);
        this.config = new CompilerConfiguration();
        this.failOnError = true;
    }
    
    public void setFailonerror(final boolean fail) {
        this.failOnError = fail;
    }
    
    public boolean getFailonerror() {
        return this.failOnError;
    }
    
    public Path createSrc() {
        if (this.src == null) {
            this.src = new Path(this.getProject());
        }
        return this.src.createPath();
    }
    
    public void setSrcdir(final Path dir) {
        assert dir != null;
        if (this.src == null) {
            this.src = dir;
        }
        else {
            this.src.append(dir);
        }
    }
    
    public Path getSrcdir() {
        return this.src;
    }
    
    public void setDestdir(final File dir) {
        assert dir != null;
        this.destdir = dir;
    }
    
    public void setClasspath(final Path path) {
        assert path != null;
        if (this.classpath == null) {
            this.classpath = path;
        }
        else {
            this.classpath.append(path);
        }
    }
    
    public Path getClasspath() {
        return this.classpath;
    }
    
    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(this.getProject());
        }
        return this.classpath.createPath();
    }
    
    public void setClasspathRef(final Reference r) {
        assert r != null;
        this.createClasspath().setRefid(r);
    }
    
    public CompilerConfiguration createConfiguration() {
        return this.config;
    }
    
    protected void validate() throws BuildException {
        if (this.src == null) {
            throw new BuildException("Missing attribute: srcdir (or one or more nested <src> elements).", this.getLocation());
        }
        if (this.destdir == null) {
            throw new BuildException("Missing attribute: destdir", this.getLocation());
        }
        if (!this.destdir.exists()) {
            throw new BuildException("Destination directory does not exist: " + this.destdir, this.getLocation());
        }
    }
    
    protected GroovyClassLoader createClassLoader() {
        final ClassLoader parent = ClassLoader.getSystemClassLoader();
        final GroovyClassLoader gcl = new GroovyClassLoader(parent, this.config);
        final Path path = this.getClasspath();
        if (path != null) {
            final String[] filePaths = path.list();
            for (int i = 0; i < filePaths.length; ++i) {
                final String filePath = filePaths[i];
                gcl.addClasspath(filePath);
            }
        }
        return gcl;
    }
    
    protected void handleException(final Exception e) throws BuildException {
        assert e != null;
        final StringWriter writer = new StringWriter();
        new ErrorReporter(e, false).write(new PrintWriter(writer));
        final String message = writer.toString();
        if (this.failOnError) {
            throw new BuildException(message, (Throwable)e, this.getLocation());
        }
        this.log.error(message);
    }
    
    public void execute() throws BuildException {
        this.validate();
        try {
            this.compile();
        }
        catch (Exception e) {
            this.handleException(e);
        }
    }
    
    protected abstract void compile() throws Exception;
}
