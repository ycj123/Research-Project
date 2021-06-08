// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.update;

import org.apache.maven.scm.provider.integrity.command.changelog.IntegrityChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.apache.maven.scm.ScmException;
import com.mks.api.response.Result;
import com.mks.api.response.WorkItem;
import com.mks.api.response.WorkItemIterator;
import com.mks.api.response.Response;
import org.apache.maven.scm.provider.integrity.Sandbox;
import java.util.List;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class IntegrityUpdateCommand extends AbstractUpdateCommand
{
    public UpdateScmResult executeUpdateCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion) throws ScmException {
        this.getLogger().info("Attempting to synchronize sandbox in " + fileSet.getBasedir().getAbsolutePath());
        final List<ScmFile> updatedFiles = new ArrayList<ScmFile>();
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        final Sandbox siSandbox = iRepo.getSandbox();
        try {
            if (siSandbox.create()) {
                final Response res = siSandbox.resync();
                final WorkItemIterator wit = res.getWorkItems();
                while (wit.hasNext()) {
                    final WorkItem wi = wit.next();
                    if (wi.getModelType().equals("si.Member")) {
                        final Result message = wi.getResult();
                        this.getLogger().debug(wi.getDisplayId() + " " + ((null != message) ? message.getMessage() : ""));
                        if (null == message || message.getMessage().length() <= 0) {
                            continue;
                        }
                        updatedFiles.add(new ScmFile(wi.getDisplayId(), message.getMessage().equalsIgnoreCase("removed") ? ScmFileStatus.DELETED : ScmFileStatus.UPDATED));
                    }
                }
                return new UpdateScmResult(res.getCommandString(), updatedFiles);
            }
            return new UpdateScmResult("si resync", "Failed to synchronize workspace", "", false);
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            return new UpdateScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final IntegrityChangeLogCommand command = new IntegrityChangeLogCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
