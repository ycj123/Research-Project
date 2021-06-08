// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

import java.io.PrintStream;

public class DefaultConsoleReporter implements ConsoleLogger
{
    private final PrintStream systemOut;
    
    public DefaultConsoleReporter(final PrintStream systemOut) {
        this.systemOut = systemOut;
    }
    
    public void info(final String message) {
        this.systemOut.println(message);
    }
}
