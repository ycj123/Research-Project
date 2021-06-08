// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.changelog;

import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import java.util.List;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.ChangeSet;
import java.util.ArrayList;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class JazzChangeLogCommand extends AbstractChangeLogCommand
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        if (branch != null && StringUtils.isNotEmpty(branch.getName())) {
            throw new ScmException("This SCM provider doesn't support branches.");
        }
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing changelog command...");
        }
        final List<ChangeSet> changeSets = new ArrayList<ChangeSet>();
        final JazzScmCommand historyCommand = this.createHistoryCommand(repo, fileSet);
        final JazzHistoryConsumer changeLogConsumer = new JazzHistoryConsumer(repo, this.getLogger(), changeSets);
        ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        int status = historyCommand.execute(changeLogConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new ChangeLogScmResult(historyCommand.getCommandString(), "Error code for Jazz SCM history command - " + status, errConsumer.getOutput(), false);
        }
        final JazzScmCommand listChangesetsCommand = this.createListChangesetCommand(repo, fileSet, changeSets);
        final JazzListChangesetConsumer listChangesetConsumer = new JazzListChangesetConsumer(repo, this.getLogger(), changeSets, datePattern);
        errConsumer = new ErrorConsumer(this.getLogger());
        status = listChangesetsCommand.execute(listChangesetConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new ChangeLogScmResult(listChangesetsCommand.getCommandString(), "Error code for Jazz SCM list changesets command - " + status, errConsumer.getOutput(), false);
        }
        final ChangeLogSet changeLogSet = new ChangeLogSet(changeSets, startDate, endDate);
        return new ChangeLogScmResult(historyCommand.getCommandString(), changeLogSet);
    }
    
    protected JazzScmCommand createHistoryCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) {
        final JazzScmCommand command = new JazzScmCommand("history", repo, fileSet, this.getLogger());
        command.addArgument("--maximum");
        command.addArgument("10000000");
        return command;
    }
    
    protected JazzScmCommand createListChangesetCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final List<ChangeSet> changeSets) {
        final JazzScmProviderRepository jazzRepo = (JazzScmProviderRepository)repo;
        final JazzScmCommand command = new JazzScmCommand("list", "changesets", repo, fileSet, this.getLogger());
        command.addArgument("--workspace");
        command.addArgument(jazzRepo.getWorkspace());
        for (int i = 0; i < changeSets.size(); ++i) {
            command.addArgument(changeSets.get(i).getRevision());
        }
        return command;
    }
}
