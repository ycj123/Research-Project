// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.cookie;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.logging.Log;

public abstract class CookiePolicy
{
    private static final String SYSTEM_PROPERTY = "apache.commons.httpclient.cookiespec";
    public static final int COMPATIBILITY = 0;
    public static final int NETSCAPE_DRAFT = 1;
    public static final int RFC2109 = 2;
    private static int defaultPolicy;
    protected static final Log LOG;
    
    public static int getDefaultPolicy() {
        return CookiePolicy.defaultPolicy;
    }
    
    public static void setDefaultPolicy(final int policy) {
        CookiePolicy.defaultPolicy = policy;
    }
    
    public static CookieSpec getSpecByPolicy(final int policy) {
        switch (policy) {
            case 0: {
                return new CookieSpecBase();
            }
            case 1: {
                return new NetscapeDraftSpec();
            }
            case 2: {
                return new RFC2109Spec();
            }
            default: {
                return getSpecByPolicy(CookiePolicy.defaultPolicy);
            }
        }
    }
    
    public static CookieSpec getDefaultSpec() {
        return getSpecByPolicy(CookiePolicy.defaultPolicy);
    }
    
    public static CookieSpec getSpecByVersion(final int ver) {
        switch (ver) {
            case 0: {
                return new NetscapeDraftSpec();
            }
            case 1: {
                return new RFC2109Spec();
            }
            default: {
                return getDefaultSpec();
            }
        }
    }
    
    public static CookieSpec getCompatibilitySpec() {
        return getSpecByPolicy(0);
    }
    
    static {
        CookiePolicy.defaultPolicy = 2;
        LOG = LogFactory.getLog(CookiePolicy.class);
        String s = null;
        try {
            s = System.getProperty("apache.commons.httpclient.cookiespec");
        }
        catch (SecurityException ex) {}
        if ("COMPATIBILITY".equalsIgnoreCase(s)) {
            setDefaultPolicy(0);
        }
        else if ("NETSCAPE_DRAFT".equalsIgnoreCase(s)) {
            setDefaultPolicy(1);
        }
        else if ("RFC2109".equalsIgnoreCase(s)) {
            setDefaultPolicy(2);
        }
        else {
            if (s != null) {
                CookiePolicy.LOG.warn("Unrecognized cookiespec property '" + s + "' - using default");
            }
            setDefaultPolicy(CookiePolicy.defaultPolicy);
        }
    }
}
