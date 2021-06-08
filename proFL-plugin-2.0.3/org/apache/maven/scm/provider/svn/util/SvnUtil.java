// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.util;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import org.codehaus.plexus.util.ReaderFactory;
import org.apache.maven.scm.providers.svn.settings.io.xpp3.SvnXpp3Reader;
import org.apache.maven.scm.providers.svn.settings.Settings;
import java.io.File;

public class SvnUtil
{
    protected static final String SVN_SETTINGS_FILENAME = "svn-settings.xml";
    public static final File DEFAULT_SETTINGS_DIRECTORY;
    private static File settingsDirectory;
    private static Settings settings;
    
    private SvnUtil() {
    }
    
    public static Settings getSettings() {
        if (SvnUtil.settings == null) {
            SvnUtil.settings = readSettings();
        }
        return SvnUtil.settings;
    }
    
    public static Settings readSettings() {
        final File settingsFile = getSettingsFile();
        if (settingsFile.exists()) {
            final SvnXpp3Reader reader = new SvnXpp3Reader();
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
        SvnUtil.settingsDirectory = directory;
        SvnUtil.settings = readSettings();
    }
    
    public static File getSettingsFile() {
        return new File(SvnUtil.settingsDirectory, "svn-settings.xml");
    }
    
    static {
        DEFAULT_SETTINGS_DIRECTORY = new File(System.getProperty("user.home"), ".scm");
        SvnUtil.settingsDirectory = SvnUtil.DEFAULT_SETTINGS_DIRECTORY;
    }
}
