// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.apache.tools.ant.types.FileSet;
import java.util.NoSuchElementException;
import java.io.File;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import java.util.Iterator;

public class FileIterator implements Iterator
{
    private Iterator fileSetIterator;
    private Project project;
    private DirectoryScanner ds;
    private String[] files;
    private int fileIndex;
    private File nextFile;
    private boolean nextObjectSet;
    private boolean iterateDirectories;
    
    public FileIterator(final Project project, final Iterator fileSetIterator) {
        this(project, fileSetIterator, false);
    }
    
    public FileIterator(final Project project, final Iterator fileSetIterator, final boolean iterateDirectories) {
        this.fileIndex = -1;
        this.nextObjectSet = false;
        this.iterateDirectories = false;
        this.project = project;
        this.fileSetIterator = fileSetIterator;
        this.iterateDirectories = iterateDirectories;
    }
    
    public boolean hasNext() {
        return this.nextObjectSet || this.setNextObject();
    }
    
    public Object next() {
        if (!this.nextObjectSet && !this.setNextObject()) {
            throw new NoSuchElementException();
        }
        this.nextObjectSet = false;
        return this.nextFile;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    private boolean setNextObject() {
        while (true) {
            if (this.ds == null) {
                if (!this.fileSetIterator.hasNext()) {
                    return false;
                }
                final FileSet fs = this.fileSetIterator.next();
                (this.ds = fs.getDirectoryScanner(this.project)).scan();
                if (this.iterateDirectories) {
                    this.files = this.ds.getIncludedDirectories();
                }
                else {
                    this.files = this.ds.getIncludedFiles();
                }
                if (this.files.length <= 0) {
                    this.ds = null;
                    continue;
                }
                this.fileIndex = -1;
            }
            if (this.ds != null && this.files != null) {
                if (++this.fileIndex < this.files.length) {
                    this.nextFile = new File(this.ds.getBasedir(), this.files[this.fileIndex]);
                    return this.nextObjectSet = true;
                }
                this.ds = null;
            }
        }
    }
}
