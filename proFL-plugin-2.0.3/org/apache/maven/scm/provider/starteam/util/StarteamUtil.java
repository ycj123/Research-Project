// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.util;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import org.codehaus.plexus.util.ReaderFactory;
import org.apache.maven.scm.providers.starteam.settings.io.xpp3.StarteamXpp3Reader;
import org.apache.maven.scm.providers.starteam.settings.Settings;
import java.io.File;

public final class StarteamUtil
{
    protected static final String STARTEAM_SETTINGS_FILENAME = "starteam-settings.xml";
    public static final File DEFAULT_SETTINGS_DIRECTORY;
    private static File settingsDirectory;
    private static Settings settings;
    
    private StarteamUtil() {
    }
    
    public static Settings getSettings() {
        if (StarteamUtil.settings == null) {
            StarteamUtil.settings = readSettings();
        }
        return StarteamUtil.settings;
    }
    
    public static Settings readSettings() {
        final File settingsFile = getSettingsFile();
        if (settingsFile.exists()) {
            final StarteamXpp3Reader reader = new StarteamXpp3Reader();
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
    
    public static File getSettingsFile() {
        return new File(StarteamUtil.settingsDirectory, "starteam-settings.xml");
    }
    
    public static void setSettingsDirectory(final File directory) {
        StarteamUtil.settingsDirectory = directory;
    }
    
    static {
        DEFAULT_SETTINGS_DIRECTORY = new File(System.getProperty("user.home"), ".scm");
        StarteamUtil.settingsDirectory = StarteamUtil.DEFAULT_SETTINGS_DIRECTORY;
    }
}
