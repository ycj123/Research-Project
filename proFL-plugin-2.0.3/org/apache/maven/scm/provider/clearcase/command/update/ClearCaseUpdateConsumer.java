// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.update;

import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.clearcase.util.ClearCaseUtil;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class ClearCaseUpdateConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> updatedFiles;
    
    public ClearCaseUpdateConsumer(final ScmLogger logger) {
        this.updatedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (line.indexOf(ClearCaseUtil.getLocalizedResource("loading")) > -1) {
            final int beginIndex = line.indexOf(34);
            if (beginIndex != -1) {
                final String fileName = line.substring(beginIndex + 1, line.indexOf(34, beginIndex + 1));
                this.updatedFiles.add(new ScmFile(fileName, ScmFileStatus.UPDATED));
            }
        }
    }
    
    public List<ScmFile> getUpdatedFiles() {
        return this.updatedFiles;
    }
}
