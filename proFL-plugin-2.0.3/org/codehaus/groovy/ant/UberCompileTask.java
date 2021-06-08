// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.apache.tools.ant.taskdefs.Javac;
import java.io.IOException;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Reference;
import java.io.File;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.Task;

public class UberCompileTask extends Task
{
    private final LoggingHelper log;
    private Path src;
    private File destdir;
    private Path classpath;
    private GenStubsAdapter genStubsTask;
    private GroovycAdapter groovycTask;
    private JavacAdapter javacTask;
    
    public UberCompileTask() {
        this.log = new LoggingHelper(this);
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
    
    public GenStubsAdapter createGeneratestubs() {
        if (this.genStubsTask == null) {
            (this.genStubsTask = new GenStubsAdapter()).setProject(this.getProject());
        }
        return this.genStubsTask;
    }
    
    public GroovycAdapter createGroovyc() {
        if (this.groovycTask == null) {
            (this.groovycTask = new GroovycAdapter()).setProject(this.getProject());
        }
        return this.groovycTask;
    }
    
    public JavacAdapter createJavac() {
        if (this.javacTask == null) {
            (this.javacTask = new JavacAdapter()).setProject(this.getProject());
        }
        return this.javacTask;
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
    
    public void execute() throws BuildException {
        this.validate();
        final GenStubsAdapter genstubs = this.createGeneratestubs();
        genstubs.classpath = this.classpath;
        genstubs.src = this.src;
        if (genstubs.destdir == null) {
            genstubs.destdir = this.createTempDir();
        }
        FileSet fileset = genstubs.getFileSet();
        if (!fileset.hasPatterns()) {
            genstubs.createInclude().setName("**/*.java");
            genstubs.createInclude().setName("**/*.groovy");
        }
        final JavacAdapter javac = this.createJavac();
        javac.setSrcdir(this.src);
        javac.setDestdir(this.destdir);
        javac.setClasspath(this.classpath);
        fileset = javac.getFileSet();
        if (!fileset.hasPatterns()) {
            javac.createInclude().setName("**/*.java");
        }
        javac.createSrc().createPathElement().setLocation(genstubs.destdir);
        final GroovycAdapter groovyc = this.createGroovyc();
        groovyc.classpath = this.classpath;
        groovyc.src = this.src;
        groovyc.destdir = this.destdir;
        groovyc.force = true;
        fileset = groovyc.getFileSet();
        if (!fileset.hasPatterns()) {
            groovyc.createInclude().setName("**/*.groovy");
        }
        genstubs.execute();
        javac.execute();
        groovyc.execute();
    }
    
    private File createTempDir() {
        try {
            final File dir = File.createTempFile("groovy-", "stubs");
            dir.delete();
            dir.mkdirs();
            return dir;
        }
        catch (IOException e) {
            throw new BuildException((Throwable)e, this.getLocation());
        }
    }
    
    private class GenStubsAdapter extends GenerateStubsTask
    {
        public FileSet getFileSet() {
            return super.getImplicitFileSet();
        }
        
        public String getTaskName() {
            return UberCompileTask.this.getTaskName() + ":genstubs";
        }
    }
    
    private class JavacAdapter extends Javac
    {
        public FileSet getFileSet() {
            return super.getImplicitFileSet();
        }
        
        public String getTaskName() {
            return UberCompileTask.this.getTaskName() + ":javac";
        }
    }
    
    private class GroovycAdapter extends GroovycTask
    {
        public FileSet getFileSet() {
            return super.getImplicitFileSet();
        }
        
        public String getTaskName() {
            return UberCompileTask.this.getTaskName() + ":groovyc";
        }
    }
}
