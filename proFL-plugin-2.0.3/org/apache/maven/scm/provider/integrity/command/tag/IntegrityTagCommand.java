// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.tag;

import org.apache.maven.scm.ScmResult;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import groovy.lang.Binding;
import org.apache.maven.scm.ScmException;
import com.mks.api.response.WorkItem;
import com.mks.api.response.Response;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.codehaus.groovy.control.CompilationFailedException;
import org.apache.maven.scm.provider.integrity.Project;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class IntegrityTagCommand extends AbstractTagCommand
{
    public TagScmResult executeTagCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String tagName, final ScmTagParameters scmTagParameters) throws ScmException {
        this.getLogger().info("Attempting to checkpoint project associated with sandbox " + fileSet.getBasedir().getAbsolutePath());
        final String message = scmTagParameters.getMessage();
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        TagScmResult result;
        try {
            final String chkptLabel = this.evalGroovyExpression(tagName);
            Project.validateTag(chkptLabel);
            final String msg = (null == message || message.length() == 0) ? System.getProperty("message") : message;
            final Project siProject = iRepo.getProject();
            if (!siProject.isBuild()) {
                final Response res = siProject.checkpoint(msg, chkptLabel);
                final int exitCode = res.getExitCode();
                final boolean success = exitCode == 0;
                final WorkItem wi = res.getWorkItem(siProject.getConfigurationPath());
                final String chkpt = wi.getResult().getField("resultant").getItem().getId();
                this.getLogger().info("Successfully checkpointed project " + siProject.getConfigurationPath() + " with label '" + chkptLabel + "', new revision is " + chkpt);
                result = new TagScmResult(res.getCommandString(), wi.getResult().getMessage(), "Exit Code: " + exitCode, success);
            }
            else {
                this.getLogger().error("Cannot checkpoint a build project configuration: " + siProject.getConfigurationPath() + "!");
                result = new TagScmResult("si checkpoint", "Cannot checkpoint a build project configuration!", "", false);
            }
        }
        catch (CompilationFailedException cfe) {
            this.getLogger().error("Groovy Compilation Exception: " + cfe.getMessage());
            result = new TagScmResult("si checkpoint", cfe.getMessage(), "", false);
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new TagScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        catch (Exception e) {
            this.getLogger().error("Failed to checkpoint project! " + e.getMessage());
            result = new TagScmResult("si checkpoint", e.getMessage(), "", false);
        }
        return result;
    }
    
    public String evalGroovyExpression(final String expression) {
        final Binding binding = new Binding();
        binding.setVariable("env", System.getenv());
        binding.setVariable("sys", System.getProperties());
        final CompilerConfiguration config = new CompilerConfiguration();
        final GroovyShell shell = new GroovyShell(binding, config);
        final Object result = shell.evaluate("return \"" + expression + "\"");
        if (result == null) {
            return "";
        }
        return result.toString().trim();
    }
}
