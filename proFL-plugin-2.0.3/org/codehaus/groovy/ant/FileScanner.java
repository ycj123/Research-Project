// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.apache.tools.ant.types.FileSet;
import java.util.Iterator;
import org.apache.tools.ant.Project;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.Task;

public class FileScanner extends Task
{
    private List filesets;
    
    public FileScanner() {
        this.filesets = new ArrayList();
    }
    
    public FileScanner(final Project project) {
        this.filesets = new ArrayList();
        this.setProject(project);
    }
    
    public Iterator iterator() {
        return new FileIterator(this.getProject(), this.filesets.iterator());
    }
    
    public Iterator directories() {
        return new FileIterator(this.getProject(), this.filesets.iterator(), true);
    }
    
    public boolean hasFiles() {
        return this.filesets.size() > 0;
    }
    
    public void clear() {
        this.filesets.clear();
    }
    
    public void addFileset(final FileSet set) {
        this.filesets.add(set);
    }
}
