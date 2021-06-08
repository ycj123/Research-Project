// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.status;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileStatus;
import com.mks.api.response.Item;
import java.io.File;
import com.mks.api.response.WorkItem;
import java.util.Collection;
import org.apache.maven.scm.provider.integrity.Sandbox;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class IntegrityStatusCommand extends AbstractStatusCommand
{
    public StatusScmResult executeStatusCommand(final ScmProviderRepository repository, final ScmFileSet fileSet) throws ScmException {
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        this.getLogger().info("Status of files changed in sandbox " + fileSet.getBasedir().getAbsolutePath());
        StatusScmResult result;
        try {
            final List<ScmFile> scmFileList = new ArrayList<ScmFile>();
            final Sandbox siSandbox = iRepo.getSandbox();
            final String excludes = Sandbox.formatFilePatterns(fileSet.getExcludes());
            final String includes = Sandbox.formatFilePatterns(fileSet.getIncludes());
            final List<ScmFile> newMemberList = siSandbox.getNewMembers(excludes, includes);
            scmFileList.addAll(newMemberList);
            final List<WorkItem> changeList = siSandbox.getChangeList();
            for (final WorkItem wi : changeList) {
                final File memberFile = new File(wi.getField("name").getValueAsString());
                if (siSandbox.hasWorkingFile((Item)wi.getField("wfdelta").getValue())) {
                    scmFileList.add(new ScmFile(memberFile.getAbsolutePath(), ScmFileStatus.UPDATED));
                }
                else {
                    scmFileList.add(new ScmFile(memberFile.getAbsolutePath(), ScmFileStatus.DELETED));
                }
            }
            if (scmFileList.size() == 0) {
                this.getLogger().info("No local changes found!");
            }
            result = new StatusScmResult(scmFileList, new ScmResult("si viewsandbox", "", "", true));
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().debug(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new StatusScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        return result;
    }
}
