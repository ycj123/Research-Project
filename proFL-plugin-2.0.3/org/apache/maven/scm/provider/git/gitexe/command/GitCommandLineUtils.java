// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.StreamConsumer;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;

public final class GitCommandLineUtils
{
    private GitCommandLineUtils() {
    }
    
    public static void addTarget(final Commandline cl, final List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        final File workingDirectory = cl.getWorkingDirectory();
        try {
            final String canonicalWorkingDirectory = workingDirectory.getCanonicalPath();
            for (final File file : files) {
                String relativeFile = file.getPath();
                final String canonicalFile = file.getCanonicalPath();
                if (canonicalFile.startsWith(canonicalWorkingDirectory)) {
                    relativeFile = canonicalFile.substring(canonicalWorkingDirectory.length());
                    if (relativeFile.startsWith(File.separator)) {
                        relativeFile = relativeFile.substring(File.separator.length());
                    }
                }
                cl.createArg().setValue(relativeFile);
            }
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Could not get canonical paths for workingDirectory = " + workingDirectory + " or files=" + files, ex);
        }
    }
    
    public static Commandline getBaseGitCommandLine(final File workingDirectory, final String command) {
        return getAnonymousBaseGitCommandLine(workingDirectory, command);
    }
    
    private static Commandline getAnonymousBaseGitCommandLine(final File workingDirectory, final String command) {
        if (command == null || command.length() == 0) {
            return null;
        }
        final Commandline cl = new AnonymousCommandLine();
        composeCommand(workingDirectory, command, cl);
        return cl;
    }
    
    private static void composeCommand(final File workingDirectory, final String command, final Commandline cl) {
        cl.setExecutable("git");
        cl.createArg().setValue(command);
        if (workingDirectory != null) {
            cl.setWorkingDirectory(workingDirectory.getAbsolutePath());
        }
    }
    
    public static int execute(final Commandline cl, final StreamConsumer consumer, final CommandLineUtils.StringStreamConsumer stderr, final ScmLogger logger) throws ScmException {
        if (logger.isInfoEnabled()) {
            logger.info("Executing: " + cl);
            logger.info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        return exitCode;
    }
    
    public static int execute(final Commandline cl, final CommandLineUtils.StringStreamConsumer stdout, final CommandLineUtils.StringStreamConsumer stderr, final ScmLogger logger) throws ScmException {
        if (logger.isInfoEnabled()) {
            logger.info("Executing: " + cl);
            logger.info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, stdout, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        return exitCode;
    }
}
