// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.tag;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class ClearCaseTagConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> taggedFiles;
    
    public ClearCaseTagConsumer(final ScmLogger logger) {
        this.taggedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        final int beginIndexTag = line.indexOf(34);
        if (beginIndexTag != -1) {
            final int endIndexTag = line.indexOf(34, beginIndexTag + 1);
            if (endIndexTag != -1) {
                final int beginIndex = line.indexOf(34, endIndexTag + 1);
                if (beginIndex != -1) {
                    final String fileName = line.substring(beginIndex + 1, line.indexOf(34, beginIndex + 1));
                    this.taggedFiles.add(new ScmFile(fileName, ScmFileStatus.TAGGED));
                }
            }
        }
    }
    
    public List<ScmFile> getTaggedFiles() {
        return this.taggedFiles;
    }
}
