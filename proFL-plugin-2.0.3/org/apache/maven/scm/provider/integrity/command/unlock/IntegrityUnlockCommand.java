// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.unlock;

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
import org.apache.maven.scm.command.unlock.AbstractUnlockCommand;

public class IntegrityUnlockCommand extends AbstractUnlockCommand
{
    private String filename;
    
    public IntegrityUnlockCommand(final String filename) {
        this.filename = filename;
    }
    
    public ScmResult executeUnlockCommand(final ScmProviderRepository repository, final File workingDirectory) throws ScmException {
        this.getLogger().info("Attempting to unlock file: " + this.filename);
        if (null == this.filename || this.filename.length() == 0) {
            throw new ScmException("A single filename is required to execute the unlock command!");
        }
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        ScmResult result;
        try {
            final Sandbox siSandbox = iRepo.getSandbox();
            final File memberFile = new File(workingDirectory.getAbsoluteFile() + File.separator + this.filename);
            final Response res = siSandbox.unlock(memberFile, this.filename);
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
        this.filename = parameters.getString(CommandParameter.FILE);
        return this.executeUnlockCommand(repository, fileSet.getBasedir());
    }
}
