// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands.edit;

import org.apache.maven.scm.provider.vss.commands.changelog.VssHistoryCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import java.io.File;
import java.io.IOException;
import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.vss.commands.VssCommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.edit.AbstractEditCommand;

public class VssEditCommand extends AbstractEditCommand
{
    @Override
    protected ScmResult executeEditCommand(final ScmProviderRepository repository, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing checkout command...");
        }
        final VssScmProviderRepository repo = (VssScmProviderRepository)repository;
        final List<Commandline> commandLines = this.buildCmdLine(repo, fileSet);
        final VssEditConsumer consumer = new VssEditConsumer(repo, this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final StringBuilder sb = new StringBuilder();
        final List<ScmFile> updatedFiles = new ArrayList<ScmFile>();
        for (final Commandline cl : commandLines) {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
            }
            final int exitCode = VssCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
            if (exitCode != 0) {
                final String error = stderr.getOutput();
                if (this.getLogger().isDebugEnabled()) {
                    this.getLogger().debug("VSS returns error: [" + error + "] return code: [" + exitCode + "]");
                }
                if (error.indexOf("A writable copy of") < 0) {
                    return new EditScmResult(cl.toString(), "The vss command failed.", error, false);
                }
                if (this.getLogger().isWarnEnabled()) {
                    this.getLogger().warn(error);
                    break;
                }
                break;
            }
            else {
                sb.append(cl.toString() + '\n');
                updatedFiles.addAll(consumer.getUpdatedFiles());
            }
        }
        return new EditScmResult(sb.toString(), updatedFiles);
    }
    
    public List<Commandline> buildCmdLine(final VssScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        final List<File> files = fileSet.getFileList();
        final List<Commandline> commands = new ArrayList<Commandline>();
        if (files.size() > 0) {
            String base;
            try {
                base = fileSet.getBasedir().getCanonicalPath();
            }
            catch (IOException e) {
                throw new ScmException("Invalid canonical path", e);
            }
            for (final File file : files) {
                final Commandline command = new Commandline();
                try {
                    command.addSystemEnvironment();
                }
                catch (Exception e2) {
                    throw new ScmException("Can't add system environment.", e2);
                }
                command.addEnvironment("SSDIR", repo.getVssdir());
                final String ssDir = VssCommandLineUtils.getSsDir();
                command.setExecutable(ssDir + "ss");
                command.createArg().setValue("Checkout");
                try {
                    final String absolute = file.getCanonicalPath();
                    final int index = absolute.indexOf(base);
                    String relative;
                    if (index >= 0) {
                        relative = absolute.substring(index + base.length());
                    }
                    else {
                        relative = file.getPath();
                    }
                    relative = relative.replace('\\', '/');
                    if (!relative.startsWith("/")) {
                        relative = '/' + relative;
                    }
                    final String relativeFolder = relative.substring(0, relative.lastIndexOf(47));
                    command.setWorkingDirectory(new File(fileSet.getBasedir().getAbsolutePath() + File.separatorChar + relativeFolder).getCanonicalPath());
                    command.createArg().setValue("$" + repo.getProject() + relative);
                }
                catch (IOException e3) {
                    throw new ScmException("Invalid canonical path", e3);
                }
                if (repo.getUserPassword() != null) {
                    command.createArg().setValue("-Y" + repo.getUserPassword());
                }
                command.createArg().setValue("-I-");
                commands.add(command);
            }
        }
        else {
            final Commandline command2 = new Commandline();
            command2.setWorkingDirectory(fileSet.getBasedir().getAbsolutePath());
            try {
                command2.addSystemEnvironment();
            }
            catch (Exception e4) {
                throw new ScmException("Can't add system environment.", e4);
            }
            command2.addEnvironment("SSDIR", repo.getVssdir());
            final String ssDir2 = VssCommandLineUtils.getSsDir();
            command2.setExecutable(ssDir2 + "ss");
            command2.createArg().setValue("Checkout");
            command2.createArg().setValue("$" + repo.getProject());
            command2.createArg().setValue("-R");
            if (repo.getUserPassword() != null) {
                command2.createArg().setValue("-Y" + repo.getUserPassword());
            }
            command2.createArg().setValue("-I-");
            commands.add(command2);
        }
        return commands;
    }
    
    protected ChangeLogCommand getChangeLogCommand() {
        final VssHistoryCommand command = new VssHistoryCommand();
        command.setLogger(this.getLogger());
        return command;
    }
}
