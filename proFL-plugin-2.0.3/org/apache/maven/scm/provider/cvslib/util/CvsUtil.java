// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.util;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import org.codehaus.plexus.util.ReaderFactory;
import org.apache.maven.scm.providers.cvslib.settings.io.xpp3.CvsXpp3Reader;
import org.apache.maven.scm.providers.cvslib.settings.Settings;
import java.io.File;

public class CvsUtil
{
    protected static final String CVS_SETTINGS_FILENAME = "cvs-settings.xml";
    public static final File DEFAULT_SETTINGS_DIRECTORY;
    private static File settingsDirectory;
    private static Settings settings;
    
    private CvsUtil() {
    }
    
    public static Settings getSettings() {
        if (CvsUtil.settings == null) {
            CvsUtil.settings = readSettings();
        }
        return CvsUtil.settings;
    }
    
    public static Settings readSettings() {
        final File settingsFile = getSettingsFile();
        if (settingsFile.exists()) {
            final CvsXpp3Reader reader = new CvsXpp3Reader();
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
        CvsUtil.settingsDirectory = directory;
        CvsUtil.settings = readSettings();
    }
    
    public static File getSettingsFile() {
        return new File(CvsUtil.settingsDirectory, "cvs-settings.xml");
    }
    
    static {
        DEFAULT_SETTINGS_DIRECTORY = new File(System.getProperty("user.home"), ".scm");
        CvsUtil.settingsDirectory = CvsUtil.DEFAULT_SETTINGS_DIRECTORY;
    }
}
