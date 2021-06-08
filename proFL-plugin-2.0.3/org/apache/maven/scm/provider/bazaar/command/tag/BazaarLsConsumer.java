// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.tag;

import java.util.LinkedList;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;

class BazaarLsConsumer extends BazaarConsumer
{
    private File repositoryRoot;
    private List<ScmFile> files;
    
    public BazaarLsConsumer(final ScmLogger logger, final File repositoryRoot, final ScmFileStatus wantedStatus) {
        super(logger);
        this.files = new LinkedList<ScmFile>();
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
        if (trimmedLine.endsWith(File.separator)) {
            return;
        }
        final String path = new File(this.repositoryRoot, trimmedLine).toString();
        this.files.add(new ScmFile(path, ScmFileStatus.TAGGED));
    }
    
    public List<ScmFile> getListedFiles() {
        return this.files;
    }
}
