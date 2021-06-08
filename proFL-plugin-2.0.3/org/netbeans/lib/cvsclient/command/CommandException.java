// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import org.netbeans.lib.cvsclient.util.BundleUtilities;
import java.io.PrintWriter;
import java.io.PrintStream;

public class CommandException extends Exception
{
    private Exception underlyingException;
    private String localizedMessage;
    private String message;
    
    public CommandException(final Exception underlyingException, final String localizedMessage) {
        this.underlyingException = underlyingException;
        this.localizedMessage = localizedMessage;
    }
    
    public CommandException(final String s, final String localizedMessage) {
        super(s);
        this.message = s;
        this.localizedMessage = localizedMessage;
    }
    
    public Exception getUnderlyingException() {
        return this.underlyingException;
    }
    
    public void printStackTrace() {
        if (this.underlyingException != null) {
            this.underlyingException.printStackTrace();
        }
        else {
            super.printStackTrace();
        }
    }
    
    public void printStackTrace(final PrintStream printStream) {
        if (this.underlyingException != null) {
            this.underlyingException.printStackTrace(printStream);
        }
        else {
            super.printStackTrace(printStream);
        }
    }
    
    public void printStackTrace(final PrintWriter printWriter) {
        if (this.underlyingException != null) {
            this.underlyingException.printStackTrace(printWriter);
        }
        else {
            super.printStackTrace(printWriter);
        }
    }
    
    public String getLocalizedMessage() {
        if (this.localizedMessage == null) {
            return this.message;
        }
        return this.localizedMessage;
    }
    
    public String getMessage() {
        if (this.message == null) {
            return this.localizedMessage;
        }
        return this.message;
    }
    
    protected static String getBundleString(final String key) {
        String string = null;
        try {
            final ResourceBundle resourceBundle = BundleUtilities.getResourceBundle(CommandException.class, "Bundle");
            if (resourceBundle != null) {
                string = resourceBundle.getString(key);
            }
        }
        catch (MissingResourceException ex) {}
        return string;
    }
    
    public static String getLocalMessage(final String s) {
        return getLocalMessage(s, null);
    }
    
    public static String getLocalMessage(final String s, final Object[] arguments) {
        String pattern = getBundleString(s);
        if (pattern == null) {
            return null;
        }
        if (arguments != null) {
            pattern = MessageFormat.format(pattern, arguments);
        }
        return pattern;
    }
}
