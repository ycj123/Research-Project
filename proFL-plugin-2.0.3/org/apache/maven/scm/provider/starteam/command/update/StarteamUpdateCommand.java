// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.update;

import org.codehaus.plexus.util.cli.DefaultConsumer;
import org.apache.maven.scm.provider.starteam.command.changelog.StarteamChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import java.util.List;
import java.io.File;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.command.checkout.StarteamCheckOutConsumer;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class StarteamUpdateCommand extends AbstractUpdateCommand implements StarteamCommand
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamCheckOutConsumer consumer = new StarteamCheckOutConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final List<File> updateFiles = fileSet.getFileList();
        if (updateFiles.size() == 0) {
            final Commandline cl = createCommandLine(repository, fileSet, version);
            final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
            if (exitCode != 0) {
                return new UpdateScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
            }
            final String doDeleteLocal = System.getProperty("maven.scm.starteam.deleteLocal");
            if ("true".equalsIgnoreCase(doDeleteLocal)) {
                this.deleteLocal(repository, fileSet, version);
            }
        }
        else {
            for (int i = 0; i < updateFiles.size(); ++i) {
                final File updateFile = updateFiles.get(i);
                final ScmFileSet scmFileSet = new ScmFileSet(fileSet.getBasedir(), updateFile);
                final Commandline cl2 = createCommandLine(repository, scmFileSet, version);
                final int exitCode2 = StarteamCommandLineUtils.executeCommandline(cl2, consumer, stderr, this.getLogger());
                if (exitCode2 != 0) {
                    return new UpdateScmResult(cl2.toString(), "The starteam command failed.", stderr.getOutput(), false);
                }
            }
        }
        return new UpdateScmResult(null, consumer.getCheckedOutFiles());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version) {
        final List<String> args = new ArrayList<String>();
        args.add("-merge");
        args.add("-neverprompt");
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            args.add("-vl");
            args.add(version.getName());
        }
        StarteamCommandLineUtils.addEOLOption(args);
        return StarteamCommandLineUtils.createStarteamCommandLine("co", args, fileSet, repo);
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final StarteamChangeLogCommand command = new StarteamChangeLogCommand();
        command.setLogger(this.getLogger());
        return command;
    }
    
    private void deleteLocal(final StarteamScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        if (fileSet.getFileList().size() != 0) {
            return;
        }
        final Commandline cl = createDeleteLocalCommand(repo, fileSet, version);
        final StreamConsumer consumer = new DefaultConsumer();
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            throw new ScmException("Error executing delete-local: " + stderr.toString());
        }
    }
    
    public static Commandline createDeleteLocalCommand(final StarteamScmProviderRepository repo, final ScmFileSet dir, final ScmVersion version) {
        final List<String> args = new ArrayList<String>();
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            args.add("-cfgl");
            args.add(version.getName());
        }
        args.add("-filter");
        args.add("N");
        return StarteamCommandLineUtils.createStarteamCommandLine("delete-local", args, dir, repo);
    }
}
