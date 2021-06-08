// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.maven.scm.provider.hg.command.inventory.HgChangeSet;
import org.apache.maven.scm.provider.hg.command.inventory.HgOutgoingConsumer;
import java.util.Iterator;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.log.DefaultLog;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import java.util.List;
import java.util.Map;

public final class HgUtils
{
    private static final Map<String, List<Integer>> EXIT_CODE_MAP;
    private static final List<Integer> DEFAULT_EXIT_CODES;
    
    private HgUtils() {
    }
    
    public static ScmResult execute(final HgConsumer consumer, final ScmLogger logger, final File workingDir, final String[] cmdAndArgs) throws ScmException {
        try {
            final Commandline cmd = buildCmd(workingDir, cmdAndArgs);
            if (logger.isInfoEnabled()) {
                logger.info("EXECUTING: " + maskPassword(cmd));
            }
            final int exitCode = executeCmd(consumer, cmd);
            List<Integer> exitCodes = HgUtils.DEFAULT_EXIT_CODES;
            if (HgUtils.EXIT_CODE_MAP.containsKey(cmdAndArgs[0])) {
                exitCodes = HgUtils.EXIT_CODE_MAP.get(cmdAndArgs[0]);
            }
            final boolean success = exitCodes.contains(exitCode);
            String providerMsg = "Execution of hg command succeded";
            if (!success) {
                final HgConfig config = new HgConfig(workingDir);
                providerMsg = "\nEXECUTION FAILED\n  Execution of cmd : " + cmdAndArgs[0] + " failed with exit code: " + exitCode + "." + "\n  Working directory was: " + "\n    " + workingDir.getAbsolutePath() + config.toString(workingDir) + "\n";
                if (logger.isErrorEnabled()) {
                    logger.error(providerMsg);
                }
            }
            return new ScmResult(cmd.toString(), providerMsg, consumer.getStdErr(), success);
        }
        catch (ScmException se) {
            String msg = "EXECUTION FAILED\n  Execution failed before invoking the Hg command. Last exception:\n    " + se.getMessage();
            if (se.getCause() != null) {
                msg = msg + "\n  Nested exception:\n    " + se.getCause().getMessage();
            }
            if (logger.isErrorEnabled()) {
                logger.error(msg);
            }
            throw se;
        }
    }
    
    static Commandline buildCmd(final File workingDir, final String[] cmdAndArgs) throws ScmException {
        final Commandline cmd = new Commandline();
        cmd.setExecutable("hg");
        cmd.addArguments(cmdAndArgs);
        if (workingDir != null) {
            cmd.setWorkingDirectory(workingDir.getAbsolutePath());
            if (!workingDir.exists()) {
                final boolean success = workingDir.mkdirs();
                if (!success) {
                    final String msg = "Working directory did not exist and it couldn't be created: " + workingDir;
                    throw new ScmException(msg);
                }
            }
        }
        return cmd;
    }
    
    static int executeCmd(final HgConsumer consumer, final Commandline cmd) throws ScmException {
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cmd, consumer, consumer);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Command could not be executed: " + cmd, ex);
        }
        return exitCode;
    }
    
    public static ScmResult execute(final File workingDir, final String[] cmdAndArgs) throws ScmException {
        final ScmLogger logger = new DefaultLog();
        return execute(new HgConsumer(logger), logger, workingDir, cmdAndArgs);
    }
    
    public static String[] expandCommandLine(final String[] cmdAndArgs, final ScmFileSet additionalFiles) {
        final List<File> filesList = additionalFiles.getFileList();
        final String[] cmd = new String[filesList.size() + cmdAndArgs.length];
        System.arraycopy(cmdAndArgs, 0, cmd, 0, cmdAndArgs.length);
        int i = 0;
        for (final File scmFile : filesList) {
            final String file = scmFile.getPath().replace('\\', File.separatorChar);
            cmd[i + cmdAndArgs.length] = file;
            ++i;
        }
        return cmd;
    }
    
    public static int getCurrentRevisionNumber(final ScmLogger logger, final File workingDir) throws ScmException {
        final String[] revCmd = { "id" };
        final HgRevNoConsumer consumer = new HgRevNoConsumer(logger);
        execute(consumer, logger, workingDir, revCmd);
        return consumer.getCurrentRevisionNumber();
    }
    
    public static String getCurrentBranchName(final ScmLogger logger, final File workingDir) throws ScmException {
        final String[] branchnameCmd = { "branch" };
        final HgBranchnameConsumer consumer = new HgBranchnameConsumer(logger);
        execute(consumer, logger, workingDir, branchnameCmd);
        return consumer.getBranchName();
    }
    
    public static boolean differentOutgoingBranchFound(final ScmLogger logger, final File workingDir, final String workingbranchName) throws ScmException {
        final String[] outCmd = { "outgoing" };
        final HgOutgoingConsumer outConsumer = new HgOutgoingConsumer(logger);
        final ScmResult outResult = execute(outConsumer, logger, workingDir, outCmd);
        final List<HgChangeSet> changes = outConsumer.getChanges();
        if (outResult.isSuccess()) {
            for (final HgChangeSet set : changes) {
                if (set.getBranch() != null) {
                    logger.warn("A different branch than " + workingbranchName + " was found in outgoing changes, branch name was " + set.getBranch() + ". Only local branch named " + workingbranchName + " will be pushed.");
                    return true;
                }
            }
        }
        return false;
    }
    
    public static String maskPassword(final Commandline cl) {
        String clString = cl.toString();
        final int pos = clString.indexOf(64);
        if (pos > 0) {
            clString = clString.replaceAll(":\\w+@", ":*****@");
        }
        return clString;
    }
    
    static {
        EXIT_CODE_MAP = new HashMap<String, List<Integer>>();
        (DEFAULT_EXIT_CODES = new ArrayList<Integer>()).add(0);
        final List<Integer> diffExitCodes = new ArrayList<Integer>(3);
        diffExitCodes.add(0);
        diffExitCodes.add(1);
        diffExitCodes.add(2);
        HgUtils.EXIT_CODE_MAP.put("diff", diffExitCodes);
        final List<Integer> outgoingExitCodes = new ArrayList<Integer>(2);
        outgoingExitCodes.add(0);
        outgoingExitCodes.add(1);
        HgUtils.EXIT_CODE_MAP.put("outgoing", outgoingExitCodes);
    }
    
    private static class HgRevNoConsumer extends HgConsumer
    {
        private int revNo;
        
        HgRevNoConsumer(final ScmLogger logger) {
            super(logger);
        }
        
        @Override
        public void doConsume(final ScmFileStatus status, final String line) {
            try {
                this.revNo = Integer.valueOf(line);
            }
            catch (NumberFormatException ex) {}
        }
        
        int getCurrentRevisionNumber() {
            return this.revNo;
        }
    }
    
    private static class HgBranchnameConsumer extends HgConsumer
    {
        private String branchName;
        
        HgBranchnameConsumer(final ScmLogger logger) {
            super(logger);
        }
        
        @Override
        public void doConsume(final ScmFileStatus status, final String trimmedLine) {
            this.branchName = String.valueOf(trimmedLine);
        }
        
        String getBranchName() {
            return this.branchName;
        }
    }
}
