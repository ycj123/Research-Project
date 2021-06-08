// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import com.mks.api.VersionNumber;

public final class APIVersion implements VersionNumber
{
    private static String apiReleaseVersion;
    private int majorVersion;
    private int minorVersion;
    
    public static String getAPIReleaseVersion() {
        if (APIVersion.apiReleaseVersion == null) {
            try {
                final ResourceBundle rb = ResourceBundle.getBundle("com.mks.api.version");
                APIVersion.apiReleaseVersion = rb.getString("MKS_API_VERSION");
            }
            catch (Throwable t) {}
        }
        return APIVersion.apiReleaseVersion;
    }
    
    public static String format(final int majorVersion, final int minorVersion) {
        if (majorVersion <= 0) {
            return getAPIReleaseVersion();
        }
        return MessageFormat.format("{0,number,#}.{1,number,#} 000-00 0", new Integer(majorVersion), new Integer(minorVersion));
    }
    
    public APIVersion(final int major, final int minor) {
        this.majorVersion = major;
        this.minorVersion = minor;
    }
    
    public int getMajor() {
        return this.majorVersion;
    }
    
    public int getMinor() {
        return this.minorVersion;
    }
    
    public String toVersionString() {
        return format(this.majorVersion, this.minorVersion);
    }
    
    public String toString() {
        return this.toVersionString();
    }
}
