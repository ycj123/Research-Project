// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.remove;

import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmException;
import com.mks.api.response.Response;
import org.apache.maven.scm.provider.integrity.Sandbox;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class IntegrityRemoveCommand extends AbstractRemoveCommand
{
    public RemoveScmResult executeRemoveCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        this.getLogger().info("Attempting to un-register sandbox in directory " + fileSet.getBasedir().getAbsolutePath());
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        RemoveScmResult result;
        try {
            final Sandbox siSandbox = iRepo.getSandbox();
            final Response res = siSandbox.drop();
            final int exitCode = res.getExitCode();
            final boolean success = exitCode == 0;
            result = new RemoveScmResult(res.getCommandString(), "", "Exit Code: " + exitCode, success);
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new RemoveScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        return result;
    }
}
