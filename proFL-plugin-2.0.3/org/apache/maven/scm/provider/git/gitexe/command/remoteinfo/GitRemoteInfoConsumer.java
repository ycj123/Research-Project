// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.remoteinfo;

import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import org.apache.maven.scm.command.remoteinfo.RemoteInfoScmResult;
import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class GitRemoteInfoConsumer implements StreamConsumer
{
    private static final Pattern BRANCH_PATTERN;
    private static final Pattern TAGS_PATTERN;
    private ScmLogger logger;
    private RemoteInfoScmResult remoteInfoScmResult;
    
    public GitRemoteInfoConsumer(final ScmLogger logger, final String commandLine) {
        this.logger = logger;
        this.remoteInfoScmResult = new RemoteInfoScmResult(commandLine, new HashMap<String, String>(), new HashMap<String, String>());
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        Matcher matcher = GitRemoteInfoConsumer.BRANCH_PATTERN.matcher(line);
        if (matcher.matches()) {
            this.remoteInfoScmResult.getBranches().put(matcher.group(2), matcher.group(1));
        }
        matcher = GitRemoteInfoConsumer.TAGS_PATTERN.matcher(line);
        if (matcher.matches()) {
            this.remoteInfoScmResult.getTags().put(matcher.group(2), matcher.group(1));
        }
    }
    
    public RemoteInfoScmResult getRemoteInfoScmResult() {
        return this.remoteInfoScmResult;
    }
    
    static {
        BRANCH_PATTERN = Pattern.compile("^(.*)\\s+refs/heads/(.*)");
        TAGS_PATTERN = Pattern.compile("^(.*)\\s+refs/tags/(.*)");
    }
}
