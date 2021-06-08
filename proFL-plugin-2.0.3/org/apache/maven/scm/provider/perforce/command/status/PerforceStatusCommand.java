// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.status;

import java.io.File;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import java.util.regex.Matcher;
import java.util.Iterator;
import org.apache.maven.scm.provider.perforce.command.PerforceVerbMapper;
import java.util.regex.Pattern;
import java.util.ArrayList;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class PerforceStatusCommand extends AbstractStatusCommand implements PerforceCommand
{
    private String actualLocation;
    
    @Override
    protected StatusScmResult executeStatusCommand(final ScmProviderRepository repo, final ScmFileSet files) throws ScmException {
        final PerforceScmProviderRepository prepo = (PerforceScmProviderRepository)repo;
        this.actualLocation = PerforceScmProvider.getRepoPath(this.getLogger(), prepo, files.getBasedir());
        final PerforceStatusConsumer consumer = new PerforceStatusConsumer();
        final Commandline command = this.readOpened(prepo, files, consumer);
        if (consumer.isSuccess()) {
            final List<ScmFile> scmfiles = createResults(this.actualLocation, consumer);
            return new StatusScmResult(command.toString(), scmfiles);
        }
        return new StatusScmResult(command.toString(), "Unable to get status", consumer.getOutput(), consumer.isSuccess());
    }
    
    public static List<ScmFile> createResults(final String repoPath, final PerforceStatusConsumer consumer) {
        final List<ScmFile> results = new ArrayList<ScmFile>();
        final List<String> files = consumer.getDepotfiles();
        final Pattern re = Pattern.compile("([^#]+)#\\d+ - ([^ ]+) .*");
        for (final String filepath : files) {
            final Matcher matcher = re.matcher(filepath);
            if (!matcher.matches()) {
                System.err.println("Skipping " + filepath);
            }
            else {
                final String path = matcher.group(1);
                final String verb = matcher.group(2);
                final ScmFile scmfile = new ScmFile(path.substring(repoPath.length() + 1).trim(), PerforceVerbMapper.toStatus(verb));
                results.add(scmfile);
            }
        }
        return results;
    }
    
    private Commandline readOpened(final PerforceScmProviderRepository prepo, final ScmFileSet files, final PerforceStatusConsumer consumer) {
        final Commandline cl = createOpenedCommandLine(prepo, files.getBasedir(), this.actualLocation);
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(PerforceScmProvider.clean("Executing " + cl.toString()));
            }
            final CommandLineUtils.StringStreamConsumer err = new CommandLineUtils.StringStreamConsumer();
            final int exitCode = CommandLineUtils.executeCommandLine(cl, consumer, err);
            if (exitCode != 0) {
                final String cmdLine = CommandLineUtils.toString(cl.getCommandline());
                final StringBuilder msg = new StringBuilder("Exit code: " + exitCode + " - " + err.getOutput());
                msg.append('\n');
                msg.append("Command line was:" + cmdLine);
                throw new CommandLineException(msg.toString());
            }
        }
        catch (CommandLineException e) {
            if (this.getLogger().isErrorEnabled()) {
                this.getLogger().error("CommandLineException " + e.getMessage(), e);
            }
        }
        return cl;
    }
    
    public static Commandline createOpenedCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final String location) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("opened");
        command.createArg().setValue(PerforceScmProvider.getCanonicalRepoPath(location));
        return command;
    }
}
