// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.export;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.integrity.Member;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.export.AbstractExportCommand;

public class IntegrityExportCommand extends AbstractExportCommand
{
    public ExportScmResult executeExportCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion, final String outputDirectory) throws ScmException {
        String exportDir = outputDirectory;
        exportDir = ((null != exportDir && exportDir.length() > 0) ? exportDir : fileSet.getBasedir().getAbsolutePath());
        this.getLogger().info("Attempting to export files to " + exportDir);
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        ExportScmResult result;
        try {
            boolean exportSuccess = true;
            final List<Member> projectMembers = iRepo.getProject().listFiles(exportDir);
            final List<ScmFile> scmFileList = new ArrayList<ScmFile>();
            for (final Member siMember : projectMembers) {
                try {
                    this.getLogger().info("Attempting to export file: " + siMember.getTargetFilePath() + " at revision " + siMember.getRevision());
                    siMember.checkout(iRepo.getAPISession());
                    scmFileList.add(new ScmFile(siMember.getTargetFilePath(), ScmFileStatus.UNKNOWN));
                }
                catch (APIException ae) {
                    exportSuccess = false;
                    final ExceptionHandler eh = new ExceptionHandler(ae);
                    this.getLogger().error("MKS API Exception: " + eh.getMessage());
                    this.getLogger().debug(eh.getCommand() + " exited with return code " + eh.getExitCode());
                }
            }
            this.getLogger().info("Exported " + scmFileList.size() + " files out of a total of " + projectMembers.size() + " files!");
            if (exportSuccess) {
                result = new ExportScmResult("si co", scmFileList);
            }
            else {
                result = new ExportScmResult("si co", "Failed to export all files!", "", exportSuccess);
            }
        }
        catch (APIException aex) {
            final ExceptionHandler eh2 = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh2.getMessage());
            this.getLogger().debug(eh2.getCommand() + " exited with return code " + eh2.getExitCode());
            result = new ExportScmResult(eh2.getCommand(), eh2.getMessage(), "Exit Code: " + eh2.getExitCode(), false);
        }
        return result;
    }
}
