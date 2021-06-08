// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import org.codehaus.plexus.util.ReaderFactory;
import org.apache.maven.scm.providers.vss.settings.io.xpp3.VssXpp3Reader;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import java.util.Iterator;
import org.apache.maven.scm.ScmFileSet;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.providers.vss.settings.Settings;
import java.io.File;

public final class VssCommandLineUtils
{
    private static File scmConfDir;
    private static Settings settings;
    
    private VssCommandLineUtils() {
    }
    
    public static void addFiles(final Commandline cl, final ScmFileSet fileSet) {
        for (final File file : fileSet.getFileList()) {
            cl.createArg().setValue(file.getPath().replace('\\', '/'));
        }
    }
    
    public static Commandline getBaseVssCommandLine(final File workingDirectory, final String cmd, final VssScmProviderRepository repository) {
        final Commandline cl = new Commandline();
        cl.setExecutable("ss");
        cl.setWorkingDirectory(workingDirectory.getAbsolutePath());
        if (!StringUtils.isEmpty(repository.getUser())) {
            cl.createArg().setValue("-Y");
            final StringBuilder sb = new StringBuilder(repository.getUser());
            if (!StringUtils.isEmpty(repository.getPassword())) {
                sb.append(",").append(repository.getPassword());
            }
            cl.createArg().setValue(sb.toString());
        }
        return cl;
    }
    
    public static int executeCommandline(final Commandline cl, final StreamConsumer consumer, final CommandLineUtils.StringStreamConsumer stderr, final ScmLogger logger) throws ScmException {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Executing: " + cl);
                logger.info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
            }
            final int exitcode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
            if (logger.isDebugEnabled()) {
                logger.debug("VSS Command Exit_Code: " + exitcode);
            }
            return exitcode;
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
    }
    
    public static Settings getSettings() {
        if (VssCommandLineUtils.settings == null) {
            VssCommandLineUtils.settings = readSettings();
        }
        return VssCommandLineUtils.settings;
    }
    
    public static Settings readSettings() {
        Settings settings = null;
        final File settingsFile = getScmConfFile();
        if (settingsFile.exists()) {
            final VssXpp3Reader reader = new VssXpp3Reader();
            try {
                settings = reader.read(ReaderFactory.newXmlReader(settingsFile));
            }
            catch (FileNotFoundException e2) {}
            catch (IOException e3) {}
            catch (XmlPullParserException e) {
                final String message = settingsFile.getAbsolutePath() + " isn't well formed. SKIPPED." + e.getMessage();
                System.err.println(message);
            }
        }
        final String vssDirectory = System.getProperty("vssDirectory");
        if (StringUtils.isNotEmpty(vssDirectory)) {
            if (settings == null) {
                settings = new Settings();
            }
            settings.setVssDirectory(vssDirectory);
        }
        return settings;
    }
    
    protected static File getScmConfDir() {
        return VssCommandLineUtils.scmConfDir;
    }
    
    protected static void setScmConfDir(final File directory) {
        VssCommandLineUtils.scmConfDir = directory;
        VssCommandLineUtils.settings = readSettings();
    }
    
    public static String getSsDir() {
        String ssDir = "";
        if (getSettings() != null) {
            final String ssDir2 = getSettings().getVssDirectory();
            if (ssDir2 != null) {
                ssDir = StringUtils.replace(ssDir2, "\\", "/");
                if (!ssDir.endsWith("/")) {
                    ssDir += "/";
                }
            }
        }
        return ssDir;
    }
    
    public static File getScmConfFile() {
        return new File(VssCommandLineUtils.scmConfDir, "vss-settings.xml");
    }
    
    static {
        VssCommandLineUtils.scmConfDir = new File(System.getProperty("user.home"), ".scm");
    }
}
