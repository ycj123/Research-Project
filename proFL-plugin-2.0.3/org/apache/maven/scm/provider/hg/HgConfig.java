// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg;

import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.log.DefaultLog;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.ScmException;
import java.io.File;

public class HgConfig
{
    private static final String HG_REQ = "0.9.2";
    private static final String HG_VERSION_TAG = "ercurial Distributed SCM (version ";
    private static final String HG_INSTALL_URL = "'http://www.selenic.com/mercurial/wiki/index.cgi/Download'";
    private HgVersionConsumer hgVersion;
    
    HgConfig(final File workingDir) {
        this.hgVersion = new HgVersionConsumer((String)null);
        try {
            this.hgVersion = getHgVersion(workingDir);
        }
        catch (ScmException ex) {}
    }
    
    private boolean isInstalled() {
        return this.hgVersion.isVersionOk("0.9.2");
    }
    
    private boolean isComplete() {
        return this.isInstalled();
    }
    
    public static HgVersionConsumer getHgVersion(final File workingDir) throws ScmException {
        final String[] versionCmd = { "version" };
        final HgVersionConsumer consumer = new HgVersionConsumer("ercurial Distributed SCM (version ");
        final Commandline cmd = HgUtils.buildCmd(workingDir, versionCmd);
        HgUtils.executeCmd(consumer, cmd);
        return consumer;
    }
    
    private static boolean compareVersion(final String version1, final String version2) {
        String v1 = version1;
        String v2 = version2;
        final int l1 = version1.length();
        final int l2 = version2.length();
        if (l1 > l2) {
            for (int x = l2; x >= l1; --x) {
                v2 += ' ';
            }
        }
        if (l2 > l1) {
            for (int x = l1; x <= l2; ++x) {
                v1 += ' ';
            }
        }
        return v2.compareTo(v1) >= 0;
    }
    
    private String getInstalledStr() {
        if (this.isComplete()) {
            return "valid and complete.";
        }
        return (this.isInstalled() ? "incomplete. " : "invalid. ") + "Consult " + "'http://www.selenic.com/mercurial/wiki/index.cgi/Download'";
    }
    
    public String toString(final File workingDir) {
        final boolean hgOk = this.hgVersion.isVersionOk("0.9.2");
        return "\n  Your Hg installation seems to be " + this.getInstalledStr() + "\n    Hg version: " + this.hgVersion.getVersion() + (hgOk ? " (OK)" : " (May be INVALID)") + "\n";
    }
    
    private static class HgVersionConsumer extends HgConsumer
    {
        private String versionStr;
        private String versionTag;
        
        HgVersionConsumer(final String versionTag) {
            super(new DefaultLog());
            this.versionStr = "NA";
            this.versionTag = versionTag;
        }
        
        @Override
        public void doConsume(final ScmFileStatus status, final String line) {
            if (line.startsWith(this.versionTag)) {
                final String[] elements = line.split(" ");
                this.versionStr = elements[elements.length - 1].split("\\)")[0];
            }
        }
        
        String getVersion() {
            return this.versionStr;
        }
        
        boolean isVersionOk(final String version) {
            return compareVersion(version, this.versionStr);
        }
    }
}
