// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

public class BugLog
{
    private static BugLog instance;
    
    public static synchronized BugLog getInstance() {
        if (BugLog.instance == null) {
            BugLog.instance = new BugLog();
        }
        return BugLog.instance;
    }
    
    public static synchronized void setInstance(final BugLog instance) {
        BugLog.instance = instance;
    }
    
    public void showException(final Exception ex) {
        ex.printStackTrace();
    }
    
    public void assertTrue(final boolean b, final String s) {
        if (b) {
            return;
        }
        throw new BugException(s);
    }
    
    public void assertNotNull(final Object o) {
        if (o != null) {
            return;
        }
        throw new BugException("Value must not be null!");
    }
    
    public void bug(final String message) {
        new Exception(message).printStackTrace();
    }
    
    public static class BugException extends RuntimeException
    {
        public BugException(final String message) {
            super(message);
        }
    }
}
