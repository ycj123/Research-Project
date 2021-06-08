// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.remove;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class GitRemoveConsumer implements StreamConsumer
{
    private static final Pattern REMOVED_PATTERN;
    private ScmLogger logger;
    private List<ScmFile> removedFiles;
    
    public GitRemoveConsumer(final ScmLogger logger) {
        this.removedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (line.length() <= 2) {
            return;
        }
        final Matcher matcher = GitRemoveConsumer.REMOVED_PATTERN.matcher(line);
        if (matcher.matches()) {
            final String file = matcher.group(1);
            this.removedFiles.add(new ScmFile(file, ScmFileStatus.DELETED));
            return;
        }
        if (this.logger.isInfoEnabled()) {
            this.logger.info("could not parse line: " + line);
        }
    }
    
    public List<ScmFile> getRemovedFiles() {
        return this.removedFiles;
    }
    
    static {
        REMOVED_PATTERN = Pattern.compile("^rm\\s'(.*)'");
    }
}
