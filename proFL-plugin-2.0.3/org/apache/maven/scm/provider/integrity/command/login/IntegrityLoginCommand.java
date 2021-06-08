// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.login;

import org.apache.maven.scm.ScmException;
import com.mks.api.response.Response;
import org.apache.maven.scm.provider.integrity.APISession;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.provider.integrity.Sandbox;
import org.apache.maven.scm.provider.integrity.Project;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.login.LoginScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.login.AbstractLoginCommand;

public class IntegrityLoginCommand extends AbstractLoginCommand
{
    @Override
    public LoginScmResult executeLoginCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters params) throws ScmException {
        this.getLogger().info("Attempting to connect with the MKS Integrity Server");
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        final APISession api = iRepo.getAPISession();
        LoginScmResult result;
        try {
            final Response res = api.connect(iRepo.getHost(), iRepo.getPort(), iRepo.getUser(), iRepo.getPassword());
            final int exitCode = res.getExitCode();
            final boolean success = exitCode == 0;
            result = new LoginScmResult(res.getCommandString(), "", "Exit Code: " + exitCode, success);
            final Project siProject = new Project(api, iRepo.getConfigruationPath());
            final Sandbox siSandbox = new Sandbox(api, siProject, fileSet.getBasedir().getAbsolutePath());
            iRepo.setProject(siProject);
            iRepo.setSandbox(siSandbox);
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new LoginScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        return result;
    }
}
