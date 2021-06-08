// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.blame;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public class JazzBlameCommand extends AbstractBlameCommand
{
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String filename) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing blame command...");
        }
        final JazzScmCommand blameCmd = this.createBlameCommand(repo, fileSet, filename);
        final JazzBlameConsumer blameConsumer = new JazzBlameConsumer(repo, this.getLogger());
        final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final int status = blameCmd.execute(blameConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new BlameScmResult(blameCmd.getCommandString(), "Error code for Jazz SCM blame command - " + status, errConsumer.getOutput(), false);
        }
        return new BlameScmResult(blameCmd.getCommandString(), blameConsumer.getLines());
    }
    
    public JazzScmCommand createBlameCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String filename) {
        final JazzScmCommand command = new JazzScmCommand("annotate", null, repo, false, fileSet, this.getLogger());
        command.addArgument(filename);
        return command;
    }
}
