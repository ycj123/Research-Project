// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.checkout;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class ClearCaseCheckOutConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> checkedOutFiles;
    
    public ClearCaseCheckOutConsumer(final ScmLogger logger) {
        this.checkedOutFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("line " + line);
        }
        this.checkedOutFiles.add(new ScmFile(line, ScmFileStatus.CHECKED_OUT));
    }
    
    public List<ScmFile> getCheckedOutFiles() {
        return this.checkedOutFiles;
    }
}
