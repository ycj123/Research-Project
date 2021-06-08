// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.log.DefaultLog;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.ScmException;
import java.io.File;

public class BazaarConfig
{
    private static final float BAZAAR_REQ = 0.7f;
    private static final float PYTHON_REQ = 2.4f;
    private static final String BAZAAR_VERSION_TAG = "bzr (bazaar-ng) ";
    private static final String BAZAAR_INSTALL_URL = "'http://bazaar-vcs.org/Installation'";
    private static final String PYTHON_EXEC = "python";
    private static final String PYTHON_VERSION = "-V";
    private static final String PYTHON_VERSION_TAG = "Python ";
    private static final String PARAMIKO = "\"import paramiko\"";
    private static final String CCRYPT = "\"import Crypto\"";
    private static final String CELEMENTREE = "\"import cElementTree\"";
    private VersionConsumer bazaarVersion;
    private VersionConsumer pythonVersion;
    private boolean cElementTree;
    private boolean paramiko;
    private boolean cCrypt;
    
    BazaarConfig(final File workingDir) {
        this.bazaarVersion = new VersionConsumer((String)null);
        this.pythonVersion = new VersionConsumer((String)null);
        this.cElementTree = false;
        this.paramiko = false;
        this.cCrypt = false;
        try {
            this.pythonVersion = getPythonVersion(workingDir);
            this.paramiko = this.checkPyModules(workingDir, "\"import paramiko\"");
            this.cCrypt = this.checkPyModules(workingDir, "\"import Crypto\"");
            this.cElementTree = this.checkPyModules(workingDir, "\"import cElementTree\"");
            this.bazaarVersion = getBazaarVersion(workingDir);
        }
        catch (ScmException ex) {}
    }
    
    private boolean checkPyModules(final File workingDir, final String cmd) {
        final PythonConsumer consumer = new PythonConsumer();
        int exitCode;
        try {
            final Commandline cmdLine = buildPythonCmd(workingDir, new String[] { "-c", cmd });
            exitCode = BazaarUtils.executeCmd(consumer, cmdLine);
        }
        catch (ScmException e) {
            exitCode = -1;
        }
        return exitCode == 0 && consumer.getConsumedAndClear().equals("");
    }
    
    private boolean isInstalled() {
        return this.pythonVersion.isVersionOk(2.4f) && this.bazaarVersion.isVersionOk(0.7f);
    }
    
    private boolean isComplete() {
        return this.isInstalled() && this.cElementTree && this.paramiko && this.cCrypt;
    }
    
    public static VersionConsumer getBazaarVersion(final File workingDir) throws ScmException {
        final String[] versionCmd = { "version" };
        final VersionConsumer consumer = new VersionConsumer("bzr (bazaar-ng) ");
        final Commandline cmd = BazaarUtils.buildCmd(workingDir, versionCmd);
        BazaarUtils.executeCmd(consumer, cmd);
        return consumer;
    }
    
    public static VersionConsumer getPythonVersion(final File workingDir) throws ScmException {
        final String[] versionCmd = { "-V" };
        final VersionConsumer consumer = new VersionConsumer("Python ");
        final Commandline cmd = buildPythonCmd(workingDir, versionCmd);
        BazaarUtils.executeCmd(consumer, cmd);
        return consumer;
    }
    
    private static Commandline buildPythonCmd(final File workingDir, final String[] cmdAndArgs) throws ScmException {
        final Commandline cmd = new Commandline();
        cmd.setExecutable("python");
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
    
    private String getInstalledStr() {
        if (this.isComplete()) {
            return "valid and complete.";
        }
        return (this.isInstalled() ? "incomplete. " : "invalid. ") + "Consult " + "'http://bazaar-vcs.org/Installation'";
    }
    
    public String toString(final File workingDir) {
        final boolean bzrOk = this.bazaarVersion.isVersionOk(0.7f);
        final boolean pyOk = this.pythonVersion.isVersionOk(2.4f);
        return "\n  Your Bazaar installation seems to be " + this.getInstalledStr() + "\n    Python version: " + this.pythonVersion.getVersion() + (pyOk ? " (OK)" : " (May be INVALID)") + "\n    Bazaar version: " + this.bazaarVersion.getVersion() + (bzrOk ? " (OK)" : " (May be INVALID)") + "\n    Paramiko installed: " + this.paramiko + " (For remote access eg. sftp) " + "\n    cCrypt installed: " + this.cCrypt + " (For remote access eg. sftp) " + "\n    cElementTree installed: " + this.cElementTree + " (Not mandatory) " + "\n";
    }
    
    private static class VersionConsumer extends BazaarConsumer
    {
        private static final Pattern VERSION_PATTERN;
        private final String versionTag;
        private String versionStr;
        private float version;
        
        VersionConsumer(final String aVersionTag) {
            super(new DefaultLog());
            this.versionStr = "NA";
            this.version = -1.0f;
            this.versionTag = aVersionTag;
        }
        
        @Override
        public void doConsume(final ScmFileStatus status, final String line) {
            if (line.startsWith(this.versionTag)) {
                this.versionStr = line.substring(this.versionTag.length());
            }
        }
        
        String getVersion() {
            return this.versionStr;
        }
        
        boolean isVersionOk(final float min) {
            final Matcher matcher = VersionConsumer.VERSION_PATTERN.matcher(this.versionStr);
            if (matcher.find()) {
                final String subStr = this.versionStr.substring(matcher.start(), matcher.end());
                try {
                    this.version = Float.valueOf(subStr);
                }
                catch (NumberFormatException e) {
                    if (this.getLogger().isErrorEnabled()) {
                        this.getLogger().error("Regexp for version did not result in a number: " + subStr, e);
                    }
                }
            }
            return min <= this.version;
        }
        
        static {
            VERSION_PATTERN = Pattern.compile("[\\d]+.?[\\d]*");
        }
    }
    
    private static class PythonConsumer extends BazaarConsumer
    {
        private String consumed;
        
        PythonConsumer() {
            super(new DefaultLog());
            this.consumed = "";
        }
        
        @Override
        public void doConsume(final ScmFileStatus status, final String line) {
            this.consumed = line;
        }
        
        String getConsumedAndClear() {
            final String tmp = this.consumed;
            this.consumed = "";
            return tmp;
        }
    }
}
