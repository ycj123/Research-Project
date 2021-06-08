// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.apache.tools.ant.types.Reference;
import org.codehaus.groovy.tools.LoaderConfiguration;
import org.codehaus.groovy.tools.RootLoader;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.taskdefs.MatchingTask;

public class RootLoaderRef extends MatchingTask
{
    private String name;
    private Path taskClasspath;
    
    public void setRef(final String n) {
        this.name = n;
    }
    
    public void execute() throws BuildException {
        if (this.taskClasspath == null || this.taskClasspath.size() == 0) {
            throw new BuildException("no classpath given");
        }
        final Project project = this.getProject();
        final AntClassLoader loader = new AntClassLoader((ClassLoader)this.makeRoot(), true);
        project.addReference(this.name, (Object)loader);
    }
    
    private RootLoader makeRoot() {
        final String[] list = this.taskClasspath.list();
        final LoaderConfiguration lc = new LoaderConfiguration();
        for (int i = 0; i < list.length; ++i) {
            if (!list[i].matches(".*ant-[^/]*jar$")) {
                if (!list[i].matches(".*commons-logging-[^/]*jar$")) {
                    if (!list[i].matches(".*xerces-[^/]*jar$")) {
                        lc.addFile(list[i]);
                    }
                }
            }
        }
        return new RootLoader(lc);
    }
    
    public void setClasspath(final Path classpath) {
        if (this.taskClasspath == null) {
            this.taskClasspath = classpath;
        }
        else {
            this.taskClasspath.append(classpath);
        }
    }
    
    public void setClasspathRef(final Reference r) {
        this.createClasspath().setRefid(r);
    }
    
    public Path createClasspath() {
        if (this.taskClasspath == null) {
            this.taskClasspath = new Path(this.getProject());
        }
        return this.taskClasspath.createPath();
    }
}
