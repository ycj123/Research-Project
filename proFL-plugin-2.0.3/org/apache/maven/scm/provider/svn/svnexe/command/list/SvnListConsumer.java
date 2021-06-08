// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.list;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class SvnListConsumer implements StreamConsumer
{
    private List<ScmFile> files;
    
    public SvnListConsumer() {
        this.files = new ArrayList<ScmFile>();
    }
    
    public void consumeLine(final String line) {
        this.files.add(new ScmFile(line, ScmFileStatus.CHECKED_IN));
    }
    
    public List<ScmFile> getFiles() {
        return this.files;
    }
}
