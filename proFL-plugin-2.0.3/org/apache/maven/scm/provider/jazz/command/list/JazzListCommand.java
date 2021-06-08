// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.list;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.list.AbstractListCommand;

public class JazzListCommand extends AbstractListCommand
{
    @Override
    protected ListScmResult executeListCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final boolean recursive, final ScmVersion version) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing list command...");
        }
        final JazzScmProviderRepository jazzRepo = (JazzScmProviderRepository)repo;
        final JazzListConsumer listConsumer = new JazzListConsumer(repo, this.getLogger());
        final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final JazzScmCommand listCmd = this.createListCommand(jazzRepo, fileSet, recursive, version);
        final int status = listCmd.execute(listConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new ListScmResult(listCmd.getCommandString(), "Error code for Jazz SCM list command - " + status, errConsumer.getOutput(), false);
        }
        return new ListScmResult(listCmd.getCommandString(), listConsumer.getFiles());
    }
    
    public JazzScmCommand createListCommand(final JazzScmProviderRepository repo, final ScmFileSet fileSet, final boolean recursive, final ScmVersion version) {
        final JazzScmCommand command = new JazzScmCommand("list", "remotefiles", repo, fileSet, this.getLogger());
        command.addArgument(repo.getRepositoryWorkspace());
        command.addArgument(repo.getComponent());
        return command;
    }
}
