// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command;

import org.apache.maven.scm.provider.starteam.util.StarteamUtil;
import java.io.IOException;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.ScmException;
import java.io.File;
import org.apache.maven.scm.ScmFileSet;
import java.util.Iterator;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.providers.starteam.settings.Settings;

public final class StarteamCommandLineUtils
{
    private static Settings settings;
    
    private StarteamCommandLineUtils() {
    }
    
    public static Commandline createStarteamBaseCommandLine(final String action, final StarteamScmProviderRepository repo) {
        final Commandline cl = new Commandline();
        cl.createArg().setValue("stcmd");
        cl.createArg().setValue(action);
        cl.createArg().setValue("-x");
        cl.createArg().setValue("-nologo");
        cl.createArg().setValue("-stop");
        return cl;
    }
    
    private static Commandline addCommandlineArguments(final Commandline cl, final List<String> args) {
        if (args == null) {
            return cl;
        }
        for (final String arg : args) {
            cl.createArg().setValue(arg);
        }
        return cl;
    }
    
    public static Commandline createStarteamCommandLine(final String action, final List<String> args, final ScmFileSet scmFileSet, final StarteamScmProviderRepository repo) {
        final Commandline cl = createStarteamBaseCommandLine(action, repo);
        if (scmFileSet.getFileList().size() == 0) {
            cl.createArg().setValue("-p");
            cl.createArg().setValue(repo.getFullUrl());
            cl.createArg().setValue("-fp");
            cl.createArg().setValue(scmFileSet.getBasedir().getAbsolutePath().replace('\\', '/'));
            cl.createArg().setValue("-is");
            addCompressionOption(cl);
            addCommandlineArguments(cl, args);
            return cl;
        }
        final File fileInFileSet = scmFileSet.getFileList().get(0);
        File workingDirectory;
        final File subFile = workingDirectory = new File(scmFileSet.getBasedir(), fileInFileSet.getPath());
        String scmUrl = repo.getFullUrl() + "/" + fileInFileSet.getPath().replace('\\', '/');
        if (!subFile.isDirectory()) {
            workingDirectory = subFile.getParentFile();
            if (fileInFileSet.getParent() != null) {
                scmUrl = repo.getFullUrl() + "/" + fileInFileSet.getParent().replace('\\', '/');
            }
            else {
                scmUrl = repo.getFullUrl();
            }
        }
        cl.createArg().setValue("-p");
        cl.createArg().setValue(scmUrl);
        cl.createArg().setValue("-fp");
        cl.createArg().setValue(workingDirectory.getPath().replace('\\', '/'));
        cl.setWorkingDirectory(workingDirectory.getPath());
        if (subFile.isDirectory()) {
            cl.createArg().setValue("-is");
        }
        addCompressionOption(cl);
        addCommandlineArguments(cl, args);
        if (!subFile.isDirectory()) {
            cl.createArg().setValue(subFile.getName());
        }
        return cl;
    }
    
    public static void addCompressionOption(final Commandline cl) {
        if (StarteamCommandLineUtils.settings.isCompressionEnable()) {
            cl.createArg().setValue("-cmp");
        }
    }
    
    public static void addEOLOption(final List<String> args) {
        if (StarteamCommandLineUtils.settings.getEol() != null) {
            args.add("-eol");
            args.add(StarteamCommandLineUtils.settings.getEol());
        }
    }
    
    public static String toJavaPath(final String path) {
        return path.replace('\\', '/');
    }
    
    public static String displayCommandlineWithoutPassword(final Commandline cl) throws ScmException {
        String retStr = "";
        final String fullStr = cl.toString();
        final int usernamePos = fullStr.indexOf("-p ") + 3;
        if (usernamePos == 2) {
            throw new ScmException("Invalid command line");
        }
        retStr = fullStr.substring(0, usernamePos);
        final int passwordStartPos = fullStr.indexOf(58);
        if (passwordStartPos == -1) {
            throw new ScmException("Invalid command line");
        }
        final int passwordEndPos = fullStr.indexOf(64);
        if (passwordEndPos == -1) {
            throw new ScmException("Invalid command line");
        }
        retStr += fullStr.substring(usernamePos, passwordStartPos);
        retStr += fullStr.substring(passwordEndPos);
        return retStr;
    }
    
    public static int executeCommandline(final Commandline cl, final StreamConsumer consumer, final CommandLineUtils.StringStreamConsumer stderr, final ScmLogger logger) throws ScmException {
        if (logger.isInfoEnabled()) {
            logger.info("Command line: " + displayCommandlineWithoutPassword(cl));
        }
        try {
            return CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
    }
    
    public static String getRelativeChildDirectory(final String parent, final String child) {
        try {
            final String childPath = new File(child).getCanonicalFile().getPath().replace('\\', '/');
            final String parentPath = new File(parent).getCanonicalFile().getPath().replace('\\', '/');
            if (!childPath.startsWith(parentPath)) {
                throw new IllegalStateException();
            }
            final String retDir = "." + childPath.substring(parentPath.length());
            return retDir;
        }
        catch (IOException e) {
            throw new IllegalStateException("Unable to convert to canonical path of either " + parent + " or " + child);
        }
    }
    
    static {
        StarteamCommandLineUtils.settings = StarteamUtil.getSettings();
    }
}
