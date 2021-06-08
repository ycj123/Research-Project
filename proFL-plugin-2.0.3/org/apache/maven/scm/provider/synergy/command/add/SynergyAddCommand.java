// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.add;

import java.util.List;
import java.util.Iterator;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class SynergyAddCommand extends AbstractAddCommand implements SynergyCommand
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, String message, final boolean binary) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing add command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("basedir: " + fileSet.getBasedir());
        }
        if (message == null || message.equals("")) {
            message = "Maven SCM Synergy provider: adding file(s) to project " + repo.getProjectSpec();
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), null);
        try {
            final int taskNum = SynergyUtil.createTask(this.getLogger(), message, repo.getProjectRelease(), true, ccmAddr);
            final String projectSpec = SynergyUtil.getWorkingProject(this.getLogger(), repo.getProjectSpec(), repo.getUser(), ccmAddr);
            if (projectSpec == null) {
                throw new ScmException("You should checkout a working project first");
            }
            final File waPath = SynergyUtil.getWorkArea(this.getLogger(), projectSpec, ccmAddr);
            final File destPath = new File(waPath, repo.getProjectName());
            for (final File source : fileSet.getFileList()) {
                final File dest = new File(destPath, SynergyUtil.removePrefix(fileSet.getBasedir(), source));
                if (!source.equals(dest)) {
                    if (this.getLogger().isDebugEnabled()) {
                        this.getLogger().debug("Copy file [" + source + "] to Synergy Work Area [" + dest + "].");
                    }
                    try {
                        FileUtils.copyFile(source, dest);
                    }
                    catch (IOException e) {
                        throw new ScmException("Unable to copy file in Work Area", e);
                    }
                }
                SynergyUtil.create(this.getLogger(), dest, message, ccmAddr);
            }
            SynergyUtil.checkinTask(this.getLogger(), taskNum, message, ccmAddr);
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        final List<ScmFile> scmFiles = new ArrayList<ScmFile>(fileSet.getFileList().size());
        for (final File f : fileSet.getFileList()) {
            scmFiles.add(new ScmFile(f.getPath(), ScmFileStatus.ADDED));
        }
        return new AddScmResult("", scmFiles);
    }
}
