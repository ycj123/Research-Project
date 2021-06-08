// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.mkdir;

import java.util.List;
import com.mks.api.response.Response;
import java.util.Iterator;
import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.ScmException;
import java.io.File;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.mkdir.AbstractMkdirCommand;

public class IntegrityMkdirCommand extends AbstractMkdirCommand
{
    public MkdirScmResult executeMkdirCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message, final boolean createInLocal) throws ScmException {
        String dirPath = "";
        final Iterator<File> fit = fileSet.getFileList().iterator();
        if (fit.hasNext()) {
            dirPath = fit.next().getPath().replace('\\', '/');
        }
        if (null == dirPath || dirPath.length() == 0) {
            throw new ScmException("A relative directory path is required to execute this command!");
        }
        this.getLogger().info("Creating subprojects one per directory, as required for " + dirPath);
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        MkdirScmResult result;
        try {
            final Response res = iRepo.getSandbox().createSubproject(dirPath);
            final String subProject = res.getWorkItems().next().getResult().getField("resultant").getItem().getDisplayId();
            final List<ScmFile> createdDirs = new ArrayList<ScmFile>();
            createdDirs.add(new ScmFile(subProject, ScmFileStatus.ADDED));
            final int exitCode = res.getExitCode();
            final boolean success = exitCode == 0;
            this.getLogger().info("Successfully created subproject " + subProject);
            result = new MkdirScmResult(createdDirs, new ScmResult(res.getCommandString(), "", "Exit Code: " + exitCode, success));
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new MkdirScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        return result;
    }
}
