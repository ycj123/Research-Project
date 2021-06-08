// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.BackingStoreException;
import org.codehaus.groovy.tools.shell.IO;

public class Preferences
{
    private static final java.util.prefs.Preferences STORE;
    public static IO.Verbosity verbosity;
    
    public static boolean getShowLastResult() {
        return Preferences.STORE.getBoolean("show-last-result", true);
    }
    
    public static boolean getSanitizeStackTrace() {
        return Preferences.STORE.getBoolean("sanitize-stack-trace", true);
    }
    
    public static String getEditor() {
        return Preferences.STORE.get("editor", System.getenv("EDITOR"));
    }
    
    public static String getParserFlavor() {
        return Preferences.STORE.get("parser-flavor", "rigid");
    }
    
    public static String[] keys() throws BackingStoreException {
        return Preferences.STORE.keys();
    }
    
    public static String get(final String name, final String defaultValue) {
        return Preferences.STORE.get(name, defaultValue);
    }
    
    public static String get(final String name) {
        return get(name, null);
    }
    
    public static void put(final String name, final String value) {
        Preferences.STORE.put(name, value);
    }
    
    public static void clear() throws BackingStoreException {
        Preferences.STORE.clear();
    }
    
    public static void addChangeListener(final PreferenceChangeListener listener) {
        Preferences.STORE.addPreferenceChangeListener(listener);
    }
    
    static {
        STORE = java.util.prefs.Preferences.userRoot().node("/org/codehaus/groovy/tools/shell");
        final String tmp = Preferences.STORE.get("verbosity", IO.Verbosity.INFO.name);
        try {
            Preferences.verbosity = IO.Verbosity.forName(tmp);
        }
        catch (IllegalArgumentException e) {
            Preferences.verbosity = IO.Verbosity.INFO;
            Preferences.STORE.remove("verbosity");
        }
        addChangeListener(new PreferenceChangeListener() {
            public void preferenceChange(final PreferenceChangeEvent event) {
                if (event.getKey().equals("verbosity")) {
                    String name = event.getNewValue();
                    if (name == null) {
                        name = IO.Verbosity.INFO.name;
                    }
                    try {
                        Preferences.verbosity = IO.Verbosity.forName(name);
                    }
                    catch (Exception e) {
                        event.getNode().put(event.getKey(), Preferences.verbosity.name);
                    }
                }
            }
        });
    }
}
