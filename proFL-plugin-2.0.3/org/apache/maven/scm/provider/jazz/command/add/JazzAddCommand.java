// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.add;

import org.apache.maven.scm.provider.jazz.command.JazzScmCommand;
import java.util.Iterator;
import org.apache.maven.scm.command.status.StatusScmResult;
import java.util.List;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.jazz.command.status.JazzStatusCommand;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class JazzAddCommand extends AbstractAddCommand
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final boolean binary) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("Executing add command...");
        }
        return this.executeAddCommand(repo, fileSet);
    }
    
    public AddScmResult executeAddCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        final File baseDir = fileSet.getBasedir();
        final File parentFolder = (baseDir.getParentFile() != null) ? baseDir.getParentFile() : baseDir;
        final List<ScmFile> changedScmFiles = new ArrayList<ScmFile>();
        final List<File> changedFiles = new ArrayList<File>();
        List<ScmFile> commitedFiles = new ArrayList<ScmFile>();
        final JazzStatusCommand statusCmd = new JazzStatusCommand();
        statusCmd.setLogger(this.getLogger());
        final StatusScmResult statusCmdResult = statusCmd.executeStatusCommand(repo, fileSet);
        final List<ScmFile> statusScmFiles = statusCmdResult.getChangedFiles();
        for (final ScmFile file : statusScmFiles) {
            this.getLogger().debug("Iterating over statusScmFiles: " + file);
            if (file.getStatus() == ScmFileStatus.ADDED || file.getStatus() == ScmFileStatus.DELETED || file.getStatus() == ScmFileStatus.MODIFIED) {
                changedScmFiles.add(new ScmFile(file.getPath(), ScmFileStatus.CHECKED_IN));
                changedFiles.add(new File(parentFolder, file.getPath()));
            }
        }
        final List<File> files = fileSet.getFileList();
        if (files.size() == 0) {
            commitedFiles = changedScmFiles;
        }
        else {
            for (final File file2 : files) {
                if (this.fileExistsInFileList(file2, changedFiles)) {
                    commitedFiles.add(new ScmFile(file2.getPath(), ScmFileStatus.CHECKED_IN));
                }
            }
        }
        final JazzAddConsumer addConsumer = new JazzAddConsumer(repo, this.getLogger());
        final ErrorConsumer errConsumer = new ErrorConsumer(this.getLogger());
        final JazzScmCommand command = this.createAddCommand(repo, fileSet);
        final int status = command.execute(addConsumer, errConsumer);
        if (status != 0 || errConsumer.hasBeenFed()) {
            return new AddScmResult(command.getCommandString(), "Error code for Jazz SCM add (checkin) command - " + status, errConsumer.getOutput(), false);
        }
        return new AddScmResult(command.getCommandString(), addConsumer.getFiles());
    }
    
    public JazzScmCommand createAddCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) {
        final JazzScmCommand command = new JazzScmCommand("checkin", null, repo, false, fileSet, this.getLogger());
        final List<File> files = fileSet.getFileList();
        if (files != null && !files.isEmpty()) {
            for (final File file : files) {
                command.addArgument(file.getPath());
            }
        }
        else {
            command.addArgument(".");
        }
        return command;
    }
    
    private boolean fileExistsInFileList(final File file, final List<File> fileList) {
        boolean exists = false;
        for (final File changedFile : fileList) {
            if (changedFile.compareTo(file) == 0) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
