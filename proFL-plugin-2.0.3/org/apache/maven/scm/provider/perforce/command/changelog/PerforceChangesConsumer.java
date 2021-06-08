// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.changelog;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmException;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import java.util.regex.Pattern;
import java.util.List;
import org.apache.maven.scm.util.AbstractConsumer;

public class PerforceChangesConsumer extends AbstractConsumer
{
    private List<String> entries;
    private static final Pattern PATTERN;
    
    public PerforceChangesConsumer(final ScmLogger logger) {
        super(logger);
        this.entries = new ArrayList<String>();
    }
    
    public List<String> getChanges() throws ScmException {
        return this.entries;
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = PerforceChangesConsumer.PATTERN.matcher(line);
        if (matcher.find()) {
            this.entries.add(matcher.group(1));
        }
    }
    
    static {
        PATTERN = Pattern.compile("^Change (\\d+) on (.*) by (.*)@");
    }
}
