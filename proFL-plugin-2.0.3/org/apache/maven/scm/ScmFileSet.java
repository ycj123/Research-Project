// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.DirectoryScanner;
import java.util.Arrays;
import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.Serializable;

public class ScmFileSet implements Serializable
{
    private static final long serialVersionUID = -5978597349974797556L;
    private static final String DELIMITER = ",";
    private static final String DEFAULT_EXCLUDES;
    private final File basedir;
    private String includes;
    private String excludes;
    private final List<File> files;
    
    public ScmFileSet(final File basedir) {
        this(basedir, new ArrayList<File>(0));
    }
    
    public ScmFileSet(final File basedir, final File file) {
        this(basedir, new File[] { file });
    }
    
    public ScmFileSet(final File basedir, final String includes, String excludes) throws IOException {
        this.basedir = basedir;
        if (excludes != null && excludes.length() > 0) {
            excludes = excludes + "," + ScmFileSet.DEFAULT_EXCLUDES;
        }
        else {
            excludes = ScmFileSet.DEFAULT_EXCLUDES;
        }
        final List<File> fileList = (List<File>)FileUtils.getFiles(basedir, includes, excludes, false);
        this.files = fileList;
        this.includes = includes;
        this.excludes = excludes;
    }
    
    public ScmFileSet(final File basedir, final String includes) throws IOException {
        this(basedir, includes, null);
    }
    
    @Deprecated
    public ScmFileSet(final File basedir, final File[] files) {
        this(basedir, Arrays.asList(files));
    }
    
    public ScmFileSet(final File basedir, final List<File> files) {
        if (basedir == null) {
            throw new NullPointerException("basedir must not be null");
        }
        if (files == null) {
            throw new NullPointerException("files must not be null");
        }
        this.basedir = basedir;
        this.files = files;
    }
    
    public File getBasedir() {
        return this.basedir;
    }
    
    @Deprecated
    public File[] getFiles() {
        return this.files.toArray(new File[this.files.size()]);
    }
    
    public List<File> getFileList() {
        return this.files;
    }
    
    public String getIncludes() {
        return this.includes;
    }
    
    public String getExcludes() {
        return this.excludes;
    }
    
    @Override
    public String toString() {
        return "basedir = " + this.basedir + "; files = " + this.files;
    }
    
    static {
        DEFAULT_EXCLUDES = StringUtils.join(DirectoryScanner.DEFAULTEXCLUDES, ",");
    }
}
