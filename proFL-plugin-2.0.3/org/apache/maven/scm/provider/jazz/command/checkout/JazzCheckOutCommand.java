// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.checkout;

import org.apache.maven.scm.ScmBranch;
import org.apache.maven.scm.ScmTag;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class JazzCheckOutCommand extends AbstractCheckOutCommand
{
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion scmVersion, final boolean recursive) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing checkout command...");
        }
        final JazzScmProviderRepository jazzRepo = (JazzScmProviderRepository)repo;
        final JazzScmCommand checkoutCmd = this.createJazzLoadCommand(jazzRepo, fileSet, scmVersion);
        final JazzCheckOutConsumer checkoutConsumer = new JazzCheckOutConsumer(repo, this.getLogger());
        final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final int status = checkoutCmd.execute(checkoutConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new CheckOutScmResult(checkoutCmd.getCommandString(), "Error code for Jazz SCM checkout (load) command - " + status, errConsumer.getOutput(), false);
        }
        return new CheckOutScmResult(checkoutCmd.getCommandString(), checkoutConsumer.getCheckedOutFiles());
    }
    
    public JazzScmCommand createJazzLoadCommand(final JazzScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion scmVersion) {
        final JazzScmCommand command = new JazzScmCommand("load", "--force", repo, fileSet, this.getLogger());
        if (fileSet != null) {
            command.addArgument("--dir");
            command.addArgument(fileSet.getBasedir().getAbsolutePath());
        }
        String workspace = repo.getRepositoryWorkspace();
        if (scmVersion != null && StringUtils.isNotEmpty(scmVersion.getName())) {
            if (scmVersion instanceof ScmTag) {
                workspace = scmVersion.getName();
            }
            else if (scmVersion instanceof ScmBranch) {
                workspace = scmVersion.getName();
            }
        }
        command.addArgument(workspace);
        return command;
    }
}
