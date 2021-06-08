// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.PrintStream;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.CharArrayWriter;

abstract class StackTrace
{
    public static String getStackTrace() {
        final CharArrayWriter out = new CharArrayWriter();
        printStackTrace(new PrintWriter(out));
        return out.toString();
    }
    
    public static void printStackTrace(final PrintWriter out) {
        try {
            throw new Exception();
        }
        catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
    public static void printStackTrace(final PrintStream out) {
        try {
            throw new Exception();
        }
        catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
    public static String getCaller(int level) {
        final String s = getStackTrace();
        final char[] dst = new char[s.length()];
        s.getChars(0, s.length(), dst, 0);
        final BufferedReader r = new BufferedReader(new CharArrayReader(dst));
        String result = "<unknown>";
        try {
            r.readLine();
            r.readLine();
            r.readLine();
            r.readLine();
            r.readLine();
            do {
                result = r.readLine();
            } while (level-- > 0);
            int i = result.indexOf("at ");
            if (i > 0) {
                result = result.substring(i + 3);
            }
            i = result.lastIndexOf("(");
            if (i > 0) {
                result = result.substring(0, i);
            }
        }
        catch (Exception e) {}
        finally {
            try {
                r.close();
            }
            catch (Exception ex) {}
        }
        return result;
    }
    
    private static ThreadGroup getMasterThreadGroup() {
        ThreadGroup g = Thread.currentThread().getThreadGroup();
        for (ThreadGroup parent = g.getParent(); parent != null; parent = g.getParent()) {
            g = parent;
        }
        return g;
    }
    
    public static void printThreads() {
        printThreads(null, 0);
    }
    
    private static void printThreads(ThreadGroup master, final int level) {
        if (master == null) {
            master = getMasterThreadGroup();
        }
        System.out.println(indent(level) + "Group: " + master.getName());
        final ThreadGroup[] gl = new ThreadGroup[master.activeGroupCount() + 100];
        for (int n = master.enumerate(gl, false), i = 0; i < n; ++i) {
            printThreads(gl[i], level + 1);
        }
        final Thread[] tl = new Thread[master.activeCount() + 100];
        for (int n = master.enumerate(tl, false), j = 0; j < n; ++j) {
            printThread(tl[j], level + 1);
        }
    }
    
    private static String indent(int level) {
        String s = "";
        while (level-- > 0) {
            s += ">";
        }
        return s;
    }
    
    private static void printThread(final Thread t, final int level) {
        System.out.println(indent(level) + "Thread: " + t.getName());
    }
}
