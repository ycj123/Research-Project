// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.checkin;

import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import java.util.List;
import java.io.File;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class StarteamCheckInCommand extends AbstractCheckInCommand implements StarteamCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final ScmVersion version) throws ScmException {
        String issueType = System.getProperty("maven.scm.issue.type");
        String issueValue = System.getProperty("maven.scm.issue.value");
        final String deprecatedIssue = System.getProperty("maven.scm.issue");
        if (deprecatedIssue != null && deprecatedIssue.trim().length() > 0) {
            issueType = "cr";
            issueValue = deprecatedIssue;
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamCheckInConsumer consumer = new StarteamCheckInConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final List<File> checkInFiles = fileSet.getFileList();
        if (checkInFiles.isEmpty()) {
            final Commandline cl = createCommandLine(repository, fileSet, message, version, issueType, issueValue);
            final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
            if (exitCode != 0) {
                return new CheckInScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
            }
        }
        else {
            for (int i = 0; i < checkInFiles.size(); ++i) {
                final ScmFileSet checkInFile = new ScmFileSet(fileSet.getBasedir(), checkInFiles.get(i));
                final Commandline cl2 = createCommandLine(repository, checkInFile, message, version, issueType, issueValue);
                final int exitCode2 = StarteamCommandLineUtils.executeCommandline(cl2, consumer, stderr, this.getLogger());
                if (exitCode2 != 0) {
                    return new CheckInScmResult(cl2.toString(), "The starteam command failed.", stderr.getOutput(), false);
                }
            }
        }
        return new CheckInScmResult(null, consumer.getCheckedInFiles());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet fileSet, final String message, final ScmVersion version, final String issueType, final String issueValue) {
        final List<String> args = new ArrayList<String>();
        if (message != null && message.length() != 0) {
            args.add("-r");
            args.add(message);
        }
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            args.add("-vl");
            args.add(version.getName());
        }
        if (issueType != null && issueType.trim().length() > 0) {
            args.add("-" + issueType.trim());
            if (issueValue != null && issueValue.trim().length() > 0) {
                args.add(issueValue.trim());
            }
        }
        boolean checkinDirectory = fileSet.getFileList().size() == 0;
        if (!checkinDirectory && fileSet.getFileList().size() != 0) {
            final File subFile = fileSet.getFileList().get(0);
            checkinDirectory = subFile.isDirectory();
        }
        if (checkinDirectory) {
            args.add("-f");
            args.add("NCI");
        }
        StarteamCommandLineUtils.addEOLOption(args);
        return StarteamCommandLineUtils.createStarteamCommandLine("ci", args, fileSet, repo);
    }
}
