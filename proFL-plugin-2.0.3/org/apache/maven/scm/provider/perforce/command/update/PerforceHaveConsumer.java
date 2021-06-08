// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.update;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class PerforceHaveConsumer extends AbstractConsumer
{
    private String have;
    private static final Pattern REVISION_PATTERN;
    
    public PerforceHaveConsumer(final ScmLogger logger) {
        super(logger);
    }
    
    public String getHave() throws ScmException {
        return this.have;
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = PerforceHaveConsumer.REVISION_PATTERN.matcher(line);
        if (matcher.find()) {
            this.have = matcher.group(1);
        }
    }
    
    static {
        REVISION_PATTERN = Pattern.compile("^Change (\\d+) on (.*) by (.*)@");
    }
}
