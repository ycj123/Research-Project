// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

public class FileReporterUtils
{
    public static String stripIllegalFilenameChars(final String original) {
        String result = original;
        final String illegalChars = getOSSpecificIllegalChars();
        for (int i = 0; i < illegalChars.length(); ++i) {
            result = result.replace(illegalChars.charAt(i), '_');
        }
        return result;
    }
    
    private static String getOSSpecificIllegalChars() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            return "\\/:*?\"<>|\u0000";
        }
        return "/\u0000";
    }
}
