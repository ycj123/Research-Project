// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar;

import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import java.util.List;
import java.util.Map;

public final class BazaarUtils
{
    private static final Map<String, List<Integer>> EXITCODEMAP;
    private static final List<Integer> DEFAULTEEXITCODES;
    
    private BazaarUtils() {
    }
    
    public static ScmResult execute(final BazaarConsumer consumer, final ScmLogger logger, final File workingDir, final String[] cmdAndArgs) throws ScmException {
        try {
            final Commandline cmd = buildCmd(workingDir, cmdAndArgs);
            if (logger.isInfoEnabled()) {
                logger.info("EXECUTING: " + cmd);
            }
            final int exitCode = executeCmd(consumer, cmd);
            List<Integer> exitCodes = BazaarUtils.DEFAULTEEXITCODES;
            if (BazaarUtils.EXITCODEMAP.containsKey(cmdAndArgs[0])) {
                exitCodes = BazaarUtils.EXITCODEMAP.get(cmdAndArgs[0]);
            }
            final boolean success = exitCodes.contains(exitCode);
            String providerMsg = "Execution of bazaar command succeded";
            if (!success) {
                final BazaarConfig config = new BazaarConfig(workingDir);
                providerMsg = "\nEXECUTION FAILED\n  Execution of cmd : " + cmdAndArgs[0] + " failed with exit code: " + exitCode + "." + "\n  Working directory was: " + "\n    " + workingDir.getAbsolutePath() + config.toString(workingDir) + "\n";
                if (logger.isErrorEnabled()) {
                    logger.error(providerMsg);
                }
            }
            return new ScmResult(cmd.toString(), providerMsg, consumer.getStdErr(), success);
        }
        catch (ScmException se) {
            String msg = "EXECUTION FAILED\n  Execution failed before invoking the Bazaar command. Last exception:\n    " + se.getMessage();
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
        cmd.setExecutable("bzr");
        cmd.setWorkingDirectory(workingDir.getAbsolutePath());
        cmd.addArguments(cmdAndArgs);
        if (!workingDir.exists()) {
            final boolean success = workingDir.mkdirs();
            if (!success) {
                final String msg = "Working directory did not exist and it couldn't be created: " + workingDir;
                throw new ScmException(msg);
            }
        }
        return cmd;
    }
    
    static int executeCmd(final BazaarConsumer consumer, final Commandline cmd) throws ScmException {
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
        return execute(new BazaarConsumer(logger), logger, workingDir, cmdAndArgs);
    }
    
    public static String[] expandCommandLine(final String[] cmdAndArgs, final ScmFileSet additionalFiles) {
        final List<File> files = additionalFiles.getFileList();
        final String[] cmd = new String[files.size() + cmdAndArgs.length];
        System.arraycopy(cmdAndArgs, 0, cmd, 0, cmdAndArgs.length);
        for (int i = 0; i < files.size(); ++i) {
            final String file = files.get(i).getPath().replace('\\', File.separatorChar);
            cmd[i + cmdAndArgs.length] = file;
        }
        return cmd;
    }
    
    public static int getCurrentRevisionNumber(final ScmLogger logger, final File workingDir) throws ScmException {
        final String[] revCmd = { "revno" };
        final BazaarRevNoConsumer consumer = new BazaarRevNoConsumer(logger);
        execute(consumer, logger, workingDir, revCmd);
        return consumer.getCurrentRevisionNumber();
    }
    
    static {
        EXITCODEMAP = new HashMap<String, List<Integer>>();
        (DEFAULTEEXITCODES = new ArrayList<Integer>()).add(0);
        final List<Integer> diffExitCodes = new ArrayList<Integer>();
        diffExitCodes.add(0);
        diffExitCodes.add(1);
        diffExitCodes.add(2);
        BazaarUtils.EXITCODEMAP.put("diff", diffExitCodes);
    }
    
    private static class BazaarRevNoConsumer extends BazaarConsumer
    {
        private int revNo;
        
        BazaarRevNoConsumer(final ScmLogger logger) {
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
}
