// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public final class Logger
{
    private static OutputStream outLogStream;
    private static OutputStream inLogStream;
    private static final String LOG_PROPERTY = "cvsClientLog";
    private static boolean logging;
    
    public static void setLogging(final String s) {
        Logger.logging = (s != null);
        try {
            if (Logger.logging) {
                if (s.equals("system")) {
                    Logger.outLogStream = System.err;
                    Logger.inLogStream = System.err;
                }
                else {
                    Logger.outLogStream = new BufferedOutputStream(new FileOutputStream(s + ".out"));
                    Logger.inLogStream = new BufferedOutputStream(new FileOutputStream(s + ".in"));
                }
            }
        }
        catch (IOException obj) {
            System.err.println("Unable to create log files: " + obj);
            System.err.println("Logging DISABLED");
            Logger.logging = false;
            try {
                if (Logger.outLogStream != null) {
                    Logger.outLogStream.close();
                }
            }
            catch (IOException ex) {}
            try {
                if (Logger.inLogStream != null) {
                    Logger.inLogStream.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    public static void logInput(final byte[] array) {
        logInput(array, 0, array.length);
    }
    
    public static void logInput(final byte[] b, final int off, final int len) {
        if (!Logger.logging) {
            return;
        }
        try {
            Logger.inLogStream.write(b, off, len);
            Logger.inLogStream.flush();
        }
        catch (IOException obj) {
            System.err.println("Could not write to log file: " + obj);
            System.err.println("Logging DISABLED.");
            Logger.logging = false;
        }
    }
    
    public static void logInput(final char c) {
        if (!Logger.logging) {
            return;
        }
        try {
            Logger.inLogStream.write(c);
            Logger.inLogStream.flush();
        }
        catch (IOException obj) {
            System.err.println("Could not write to log file: " + obj);
            System.err.println("Logging DISABLED.");
            Logger.logging = false;
        }
    }
    
    public static void logOutput(final byte[] b) {
        if (!Logger.logging) {
            return;
        }
        try {
            Logger.outLogStream.write(b);
            Logger.outLogStream.flush();
        }
        catch (IOException obj) {
            System.err.println("Could not write to log file: " + obj);
            System.err.println("Logging DISABLED.");
            Logger.logging = false;
        }
    }
    
    static {
        setLogging(System.getProperty("cvsClientLog"));
    }
}
