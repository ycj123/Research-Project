// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.inventory;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.hg.command.HgConsumer;

public class HgListConsumer extends HgConsumer
{
    private List<ScmFile> files;
    
    public HgListConsumer(final ScmLogger logger) {
        super(logger);
        this.files = new ArrayList<ScmFile>();
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
        this.files.add(new ScmFile(trimmedLine, status));
    }
    
    public List<ScmFile> getFiles() {
        return this.files;
    }
}
