// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.unedit;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class ClearCaseUnEditConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> unEditFiles;
    
    public ClearCaseUnEditConsumer(final ScmLogger logger) {
        this.unEditFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (line.indexOf("Checkout cancelled") > -1) {
            final int beginIndex = line.indexOf(34);
            if (beginIndex != -1) {
                final String fileName = line.substring(beginIndex + 1, line.indexOf(34, beginIndex + 1));
                this.unEditFiles.add(new ScmFile(fileName, ScmFileStatus.UNKNOWN));
            }
        }
    }
    
    public List<ScmFile> getUnEditFiles() {
        return this.unEditFiles;
    }
}
