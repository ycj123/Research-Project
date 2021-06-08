// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.branch;

import org.apache.maven.scm.ScmException;
import com.mks.api.response.Response;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import java.util.List;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.provider.integrity.Project;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.branch.AbstractBranchCommand;

public class IntegrityBranchCommand extends AbstractBranchCommand
{
    public BranchScmResult executeBranchCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String branchName, final String message) throws ScmException {
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        final Project siProject = iRepo.getProject();
        this.getLogger().info("Attempting to branch project " + siProject.getProjectName() + " using branch name '" + branchName + "'");
        BranchScmResult result;
        try {
            Project.validateTag(branchName);
            final Response res = siProject.createDevPath(branchName);
            final int exitCode = res.getExitCode();
            final boolean success = exitCode == 0;
            final ScmResult scmResult = new ScmResult(res.getCommandString(), "", "Exit Code: " + exitCode, success);
            result = new BranchScmResult(new ArrayList<ScmFile>(), scmResult);
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new BranchScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        catch (Exception e) {
            this.getLogger().error("Failed to checkpoint project! " + e.getMessage());
            result = new BranchScmResult("si createdevpath", e.getMessage(), "", false);
        }
        return result;
    }
}
