// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command.consumer;

import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmFile;

public class ServerFileListConsumer extends FileListConsumer
{
    @Override
    protected ScmFile getScmFile(String filename) {
        if (filename.startsWith("$")) {
            filename = StringUtils.replace(filename, "$", "", -1);
        }
        final String path = this.currentDir + "/" + filename;
        return new ScmFile(path, ScmFileStatus.UNKNOWN);
    }
}
