// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.lock;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import com.mks.api.response.Response;
import org.apache.maven.scm.provider.integrity.Sandbox;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.lock.AbstractLockCommand;

public class IntegrityLockCommand extends AbstractLockCommand
{
    public ScmResult executeLockCommand(final ScmProviderRepository repository, final File workingDirectory, final String filename) throws ScmException {
        this.getLogger().info("Attempting to lock file: " + filename);
        if (null == filename || filename.length() == 0) {
            throw new ScmException("A single filename is required to execute the lock command!");
        }
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        ScmResult result;
        try {
            final Sandbox siSandbox = iRepo.getSandbox();
            final File memberFile = new File(workingDirectory.getAbsoluteFile() + File.separator + filename);
            final Response res = siSandbox.lock(memberFile, filename);
            final int exitCode = res.getExitCode();
            final boolean success = exitCode == 0;
            result = new ScmResult(res.getCommandString(), "", "Exit Code: " + exitCode, success);
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new ScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        return result;
    }
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return this.executeLockCommand(repository, fileSet.getBasedir(), parameters.getString(CommandParameter.FILE));
    }
}
