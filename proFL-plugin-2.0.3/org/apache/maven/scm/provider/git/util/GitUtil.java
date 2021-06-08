// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.util;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import org.codehaus.plexus.util.ReaderFactory;
import org.apache.maven.scm.providers.gitlib.settings.io.xpp3.GitXpp3Reader;
import org.apache.maven.scm.providers.gitlib.settings.Settings;
import java.io.File;

public class GitUtil
{
    protected static final String GIT_SETTINGS_FILENAME = "git-settings.xml";
    public static final File DEFAULT_SETTINGS_DIRECTORY;
    private static File settingsDirectory;
    private static Settings settings;
    
    private GitUtil() {
    }
    
    public static Settings getSettings() {
        if (GitUtil.settings == null) {
            GitUtil.settings = readSettings();
        }
        return GitUtil.settings;
    }
    
    public static Settings readSettings() {
        final File settingsFile = getSettingsFile();
        if (settingsFile.exists()) {
            final GitXpp3Reader reader = new GitXpp3Reader();
            try {
                return reader.read(ReaderFactory.newXmlReader(settingsFile));
            }
            catch (FileNotFoundException e2) {}
            catch (IOException e3) {}
            catch (XmlPullParserException e) {
                final String message = settingsFile.getAbsolutePath() + " isn't well formed. SKIPPED." + e.getMessage();
                System.err.println(message);
            }
        }
        return new Settings();
    }
    
    public static void setSettingsDirectory(final File directory) {
        GitUtil.settingsDirectory = directory;
        GitUtil.settings = readSettings();
    }
    
    public static File getSettingsFile() {
        return new File(GitUtil.settingsDirectory, "git-settings.xml");
    }
    
    static {
        DEFAULT_SETTINGS_DIRECTORY = new File(System.getProperty("user.home"), ".scm");
        GitUtil.settingsDirectory = GitUtil.DEFAULT_SETTINGS_DIRECTORY;
    }
}
