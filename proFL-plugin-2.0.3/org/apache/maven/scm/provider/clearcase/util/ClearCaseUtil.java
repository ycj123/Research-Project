// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.util;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import org.codehaus.plexus.util.ReaderFactory;
import org.apache.maven.scm.providers.clearcase.settings.io.xpp3.ClearcaseXpp3Reader;
import org.apache.maven.scm.providers.clearcase.settings.Settings;
import java.util.ResourceBundle;
import java.io.File;

public final class ClearCaseUtil
{
    protected static final String CLEARCASE_SETTINGS_FILENAME = "clearcase-settings.xml";
    public static final File DEFAULT_SETTINGS_DIRECTORY;
    private static File settingsDirectory;
    private static final String RESOURCE_FILENAME = "org.apache.maven.scm.provider.clearcase.command.clearcase";
    private static final ResourceBundle RESOURCE_BUNDLE;
    private static Settings settings;
    
    private ClearCaseUtil() {
    }
    
    public static String getLocalizedResource(final String key) {
        return ClearCaseUtil.RESOURCE_BUNDLE.getString(key);
    }
    
    public static Settings getSettings() {
        if (ClearCaseUtil.settings == null) {
            ClearCaseUtil.settings = readSettings();
        }
        return ClearCaseUtil.settings;
    }
    
    public static Settings readSettings() {
        File settingsFile = new File(ClearCaseUtil.settingsDirectory, "clearcase-settings.xml");
        if (!settingsFile.exists()) {
            final File scmGlobalDir = new File(System.getProperty("maven.home"), "conf");
            settingsFile = new File(scmGlobalDir, "clearcase-settings.xml");
        }
        if (settingsFile.exists()) {
            final ClearcaseXpp3Reader reader = new ClearcaseXpp3Reader();
            try {
                return reader.read(ReaderFactory.newXmlReader(settingsFile));
            }
            catch (FileNotFoundException e2) {}
            catch (IOException e3) {}
            catch (XmlPullParserException e) {
                final String message = settingsFile.getAbsolutePath() + " isn't well formed. SKIPPED." + e.getMessage();
                System.out.println(message);
            }
        }
        return new Settings();
    }
    
    public static void setSettingsDirectory(final File directory) {
        ClearCaseUtil.settingsDirectory = directory;
        ClearCaseUtil.settings = readSettings();
    }
    
    static {
        DEFAULT_SETTINGS_DIRECTORY = new File(System.getProperty("user.home"), ".scm");
        ClearCaseUtil.settingsDirectory = ClearCaseUtil.DEFAULT_SETTINGS_DIRECTORY;
        RESOURCE_BUNDLE = ResourceBundle.getBundle("org.apache.maven.scm.provider.clearcase.command.clearcase");
    }
}
