// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli;

public class CommandLineException extends Exception
{
    public CommandLineException(final String message) {
        super(message);
    }
    
    public CommandLineException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
