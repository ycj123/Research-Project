// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.changelog;

import java.util.regex.Matcher;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.provider.jazz.command.consumer.AbstractRepositoryConsumer;

public class JazzHistoryConsumer extends AbstractRepositoryConsumer
{
    private static final Pattern CHANGESET_PATTERN;
    private List<ChangeSet> entries;
    
    public JazzHistoryConsumer(final ScmProviderRepository repo, final ScmLogger logger, final List<ChangeSet> entries) {
        super(repo, logger);
        this.entries = entries;
    }
    
    @Override
    public void consumeLine(final String line) {
        super.consumeLine(line);
        final Matcher matcher = JazzHistoryConsumer.CHANGESET_PATTERN.matcher(line);
        if (matcher.find()) {
            final String changesetAlias = matcher.group(1);
            final ChangeSet changeSet = new ChangeSet();
            changeSet.setRevision(changesetAlias);
            this.entries.add(changeSet);
        }
    }
    
    static {
        CHANGESET_PATTERN = Pattern.compile("\\((\\d+)\\) (.*)");
    }
}
