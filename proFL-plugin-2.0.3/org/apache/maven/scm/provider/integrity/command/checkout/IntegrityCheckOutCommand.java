// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.checkout;

import org.apache.maven.scm.ScmException;
import com.mks.api.response.Result;
import com.mks.api.response.WorkItem;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.Response;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.provider.integrity.Sandbox;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class IntegrityCheckOutCommand extends AbstractCheckOutCommand
{
    public CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final boolean recursive) throws ScmException {
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        CheckOutScmResult result;
        try {
            this.getLogger().info("Attempting to checkout source for project " + iRepo.getProject().getConfigurationPath());
            final String checkoutDir = System.getProperty("checkoutDirectory");
            Sandbox siSandbox;
            if (null != checkoutDir && checkoutDir.length() > 0) {
                siSandbox = new Sandbox(iRepo.getAPISession(), iRepo.getProject(), checkoutDir);
                iRepo.setSandbox(siSandbox);
            }
            else {
                siSandbox = iRepo.getSandbox();
            }
            this.getLogger().info("Sandbox location is " + siSandbox.getSandboxDir());
            if (siSandbox.create()) {
                final Response res = siSandbox.resync();
                final WorkItemIterator wit = res.getWorkItems();
                while (wit.hasNext()) {
                    final WorkItem wi = wit.next();
                    if (wi.getModelType().equals("si.Member")) {
                        final Result message = wi.getResult();
                        this.getLogger().debug(wi.getDisplayId() + " " + ((null != message) ? message.getMessage() : ""));
                    }
                }
                final int exitCode = res.getExitCode();
                final boolean success = exitCode == 0;
                result = new CheckOutScmResult(res.getCommandString(), "", "Exit Code: " + exitCode, success);
            }
            else {
                result = new CheckOutScmResult("si createsandbox", "Failed to create sandbox!", "", false);
            }
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new CheckOutScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        return result;
    }
}
