// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.checkout;

import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import java.io.File;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class SynergyCheckOutCommand extends AbstractCheckOutCommand implements SynergyCommand
{
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion version, final boolean recursive) throws ScmException {
        if (fileSet.getFileList().size() != 0) {
            throw new ScmException("This provider doesn't support checking out subsets of a project");
        }
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing checkout command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(fileSet.toString());
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), null);
        File waPath;
        try {
            String projectSpec = SynergyUtil.getWorkingProject(this.getLogger(), repo.getProjectSpec(), repo.getUser(), ccmAddr);
            if (projectSpec != null) {
                if (this.getLogger().isInfoEnabled()) {
                    this.getLogger().info("A working project already exists [" + projectSpec + "].");
                }
                SynergyUtil.synchronize(this.getLogger(), projectSpec, ccmAddr);
            }
            else {
                SynergyUtil.checkoutProject(this.getLogger(), null, repo.getProjectSpec(), version, repo.getProjectPurpose(), repo.getProjectRelease(), ccmAddr);
                projectSpec = SynergyUtil.getWorkingProject(this.getLogger(), repo.getProjectSpec(), repo.getUser(), ccmAddr);
                if (this.getLogger().isInfoEnabled()) {
                    this.getLogger().info("A new working project [" + projectSpec + "] was created.");
                }
            }
            SynergyUtil.reconfigure(this.getLogger(), projectSpec, ccmAddr);
            waPath = SynergyUtil.getWorkArea(this.getLogger(), projectSpec, ccmAddr);
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        final File source = new File(waPath, repo.getProjectName());
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("We will now copy files from Synergy Work Area [" + source + "] to expected folder [" + fileSet.getBasedir() + "]");
        }
        try {
            FileUtils.copyDirectoryStructure(source, fileSet.getBasedir());
        }
        catch (IOException e1) {
            throw new ScmException("Unable to copy directory structure", e1);
        }
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("We will list content of checkout directory.");
        }
        final List<ScmFile> files = new ArrayList<ScmFile>();
        try {
            final List<File> realFiles = (List<File>)FileUtils.getFiles(fileSet.getBasedir(), null, "_ccmwaid.inf");
            for (final File f : realFiles) {
                files.add(new ScmFile(f.getPath(), ScmFileStatus.CHECKED_OUT));
            }
        }
        catch (IOException e2) {
            throw new ScmException("Unable to list files in checkout directory", e2);
        }
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("checkout command end successfully ...");
        }
        return new CheckOutScmResult(files, new ScmResult("multiple commandline", "OK", "OK", true));
    }
}
