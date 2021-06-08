// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.update;

import java.util.regex.Matcher;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class GitLatestRevisionCommandConsumer extends AbstractConsumer
{
    private static final Pattern LATESTREV_PATTERN;
    private String latestRevision;
    
    public GitLatestRevisionCommandConsumer(final ScmLogger logger) {
        super(logger);
    }
    
    public void consumeLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("GitLatestRevisionCommandConsumer consumeLine : " + line);
        }
        if (line == null || StringUtils.isEmpty(line)) {
            return;
        }
        this.processGetLatestRevision(line);
    }
    
    public String getLatestRevision() {
        return this.latestRevision;
    }
    
    private void processGetLatestRevision(final String line) {
        final Matcher matcher = GitLatestRevisionCommandConsumer.LATESTREV_PATTERN.matcher(line);
        if (matcher.matches()) {
            this.latestRevision = matcher.group(1);
        }
    }
    
    static {
        LATESTREV_PATTERN = Pattern.compile("^commit \\s*(.*)");
    }
}
