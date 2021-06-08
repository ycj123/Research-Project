// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.checkout;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;

public class BazaarCheckOutConsumer extends BazaarConsumer
{
    private final File workingDirectory;
    private final List<ScmFile> checkedOut;
    
    public BazaarCheckOutConsumer(final ScmLogger logger, final File workingDirectory) {
        super(logger);
        this.checkedOut = new ArrayList<ScmFile>();
        this.workingDirectory = workingDirectory;
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String line) {
        final File file = new File(this.workingDirectory, line);
        if (file.isFile()) {
            this.checkedOut.add(new ScmFile(line, ScmFileStatus.CHECKED_OUT));
        }
    }
    
    public List<ScmFile> getCheckedOutFiles() {
        return this.checkedOut;
    }
}
