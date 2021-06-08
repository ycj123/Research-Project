// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.edit;

import java.util.List;
import java.util.Iterator;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import java.util.LinkedList;
import org.apache.maven.scm.ScmException;
import java.io.File;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.edit.AbstractEditCommand;

public class SynergyEditCommand extends AbstractEditCommand implements SynergyCommand
{
    @Override
    protected ScmResult executeEditCommand(final ScmProviderRepository repository, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing edit command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(fileSet.toString());
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), null);
        try {
            final String projectSpec = SynergyUtil.getWorkingProject(this.getLogger(), repo.getProjectSpec(), repo.getUser(), ccmAddr);
            final File waPath = SynergyUtil.getWorkArea(this.getLogger(), projectSpec, ccmAddr);
            final File sourcePath = new File(waPath, repo.getProjectName());
            if (projectSpec == null) {
                throw new ScmException("You should checkout project first");
            }
            final int taskNum = SynergyUtil.createTask(this.getLogger(), "Maven SCM Synergy provider: edit command for project " + repo.getProjectSpec(), repo.getProjectRelease(), true, ccmAddr);
            if (this.getLogger().isInfoEnabled()) {
                this.getLogger().info("Task " + taskNum + " was created to perform checkout.");
            }
            for (final File dest : fileSet.getFileList()) {
                final File f = dest;
                final File source = new File(sourcePath, SynergyUtil.removePrefix(fileSet.getBasedir(), f));
                final List<File> list = new LinkedList<File>();
                list.add(source);
                SynergyUtil.checkoutFiles(this.getLogger(), list, ccmAddr);
                if (!source.equals(dest)) {
                    if (this.getLogger().isDebugEnabled()) {
                        this.getLogger().debug("Copy file [" + source + "] to expected folder [" + dest + "].");
                    }
                    try {
                        FileUtils.copyFile(source, dest);
                    }
                    catch (IOException e) {
                        throw new ScmException("Unable to copy file from Work Area", e);
                    }
                }
            }
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        final List<ScmFile> scmFiles = new ArrayList<ScmFile>(fileSet.getFileList().size());
        for (final File f2 : fileSet.getFileList()) {
            scmFiles.add(new ScmFile(f2.getPath(), ScmFileStatus.EDITED));
        }
        return new EditScmResult("", scmFiles);
    }
}
