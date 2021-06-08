// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.update;

import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.FileUtils;
import org.apache.maven.scm.provider.synergy.command.changelog.SynergyChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import java.util.List;
import java.io.IOException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import java.io.File;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class SynergyUpdateCommand extends AbstractUpdateCommand implements SynergyCommand
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing update command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("basedir: " + fileSet.getBasedir());
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), null);
        File waPath;
        try {
            final String projectSpec = SynergyUtil.getWorkingProject(this.getLogger(), repo.getProjectSpec(), repo.getUser(), ccmAddr);
            SynergyUtil.reconfigureProperties(this.getLogger(), projectSpec, ccmAddr);
            SynergyUtil.reconfigure(this.getLogger(), projectSpec, ccmAddr);
            waPath = SynergyUtil.getWorkArea(this.getLogger(), projectSpec, ccmAddr);
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        final File source = new File(waPath, repo.getProjectName());
        final List<ScmFile> modifications = new ArrayList<ScmFile>();
        if (!source.equals(fileSet.getBasedir())) {
            if (this.getLogger().isInfoEnabled()) {
                this.getLogger().info("We will copy modified files from Synergy Work Area [" + source + "] to expected folder [" + fileSet.getBasedir() + "]");
            }
            try {
                copyDirectoryStructure(source, fileSet.getBasedir(), modifications);
            }
            catch (IOException e1) {
                throw new ScmException("Unable to copy directory structure", e1);
            }
        }
        return new UpdateScmResult("ccm reconcile -uwa ...", modifications);
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final SynergyChangeLogCommand changeLogCmd = new SynergyChangeLogCommand();
        changeLogCmd.setLogger(this.getLogger());
        return changeLogCmd;
    }
    
    public static void copyDirectoryStructure(final File sourceDirectory, final File destinationDirectory, final List<ScmFile> modifications) throws IOException {
        if (!sourceDirectory.exists()) {
            throw new IOException("Source directory doesn't exists (" + sourceDirectory.getAbsolutePath() + ").");
        }
        final File[] files = sourceDirectory.listFiles();
        final String sourcePath = sourceDirectory.getAbsolutePath();
        for (final File file : files) {
            String dest = file.getAbsolutePath();
            dest = dest.substring(sourcePath.length() + 1);
            File destination = new File(destinationDirectory, dest);
            if (file.isFile()) {
                if (file.lastModified() != destination.lastModified()) {
                    destination = destination.getParentFile();
                    FileUtils.copyFileToDirectory(file, destination);
                    modifications.add(new ScmFile(file.getAbsolutePath(), ScmFileStatus.UPDATED));
                }
            }
            else {
                if (!file.isDirectory()) {
                    throw new IOException("Unknown file type: " + file.getAbsolutePath());
                }
                if (!destination.exists() && !destination.mkdirs()) {
                    throw new IOException("Could not create destination directory '" + destination.getAbsolutePath() + "'.");
                }
                copyDirectoryStructure(file, destination, modifications);
            }
        }
    }
}
