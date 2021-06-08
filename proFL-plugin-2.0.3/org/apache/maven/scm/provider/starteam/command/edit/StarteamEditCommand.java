// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.edit;

import java.util.ArrayList;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.edit.AbstractEditCommand;

public class StarteamEditCommand extends AbstractEditCommand implements StarteamCommand
{
    @Override
    protected ScmResult executeEditCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamEditConsumer consumer = new StarteamEditConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final List<File> editFiles = fileSet.getFileList();
        if (editFiles.size() == 0) {
            final Commandline cl = createCommandLine(repository, fileSet);
            final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
            if (exitCode != 0) {
                return new EditScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
            }
        }
        else {
            for (int i = 0; i < editFiles.size(); ++i) {
                final ScmFileSet editFile = new ScmFileSet(fileSet.getBasedir(), editFiles.get(i));
                final Commandline cl2 = createCommandLine(repository, editFile);
                final int exitCode2 = StarteamCommandLineUtils.executeCommandline(cl2, consumer, stderr, this.getLogger());
                if (exitCode2 != 0) {
                    return new EditScmResult(cl2.toString(), "The starteam command failed.", stderr.getOutput(), false);
                }
            }
        }
        return new EditScmResult(null, consumer.getEditedFiles());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet dirOrFile) {
        final List<String> args = new ArrayList<String>();
        args.add("-l");
        return StarteamCommandLineUtils.createStarteamCommandLine("lck", args, dirOrFile, repo);
    }
}
