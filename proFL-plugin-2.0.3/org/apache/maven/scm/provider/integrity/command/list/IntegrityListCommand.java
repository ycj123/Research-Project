// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.list;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.integrity.Member;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.list.AbstractListCommand;

public class IntegrityListCommand extends AbstractListCommand
{
    public ListScmResult executeListCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final boolean recursive, final ScmVersion scmVersion) throws ScmException {
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        this.getLogger().info("Listing all files in project " + iRepo.getConfigruationPath());
        ListScmResult result;
        try {
            final List<Member> projectMembers = iRepo.getProject().listFiles(fileSet.getBasedir().getAbsolutePath());
            final List<ScmFile> scmFileList = new ArrayList<ScmFile>();
            for (final Member siMember : projectMembers) {
                scmFileList.add(new ScmFile(siMember.getTargetFilePath(), ScmFileStatus.UNKNOWN));
            }
            result = new ListScmResult(scmFileList, new ScmResult("si viewproject", "", "", true));
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().debug(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new ListScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        return result;
    }
}
