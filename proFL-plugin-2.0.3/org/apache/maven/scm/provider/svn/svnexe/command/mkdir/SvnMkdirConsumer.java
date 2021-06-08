// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.mkdir;

import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class SvnMkdirConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private static final String COMMITTED_REVISION_TOKEN = "Committed revision";
    private int revision;
    private List<ScmFile> createdDirs;
    
    public SvnMkdirConsumer(final ScmLogger logger) {
        this.createdDirs = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (StringUtils.isBlank(line)) {
            return;
        }
        final String statusString = line.substring(0, 1);
        if (line.startsWith("Committed revision")) {
            final String revisionString = line.substring("Committed revision".length() + 1, line.length() - 1);
            this.revision = Integer.parseInt(revisionString);
            return;
        }
        if (statusString.equals("A")) {
            final String file = line.substring(3);
            final ScmFileStatus status = ScmFileStatus.ADDED;
            this.createdDirs.add(new ScmFile(file, status));
            return;
        }
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Unknown line: '" + line + "'");
        }
    }
    
    public int getRevision() {
        return this.revision;
    }
    
    public List<ScmFile> getCreatedDirs() {
        return this.createdDirs;
    }
}
