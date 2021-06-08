// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import java.util.ResourceBundle;
import java.util.MissingResourceException;
import org.netbeans.lib.cvsclient.util.BundleUtilities;

public class AuthenticationException extends Exception
{
    private Throwable underlyingThrowable;
    private String message;
    private String localizedMessage;
    
    public AuthenticationException(final String s, final String localizedMessage) {
        super(s);
        this.message = s;
        this.localizedMessage = localizedMessage;
    }
    
    public AuthenticationException(final String s, final Throwable cause, final String s2) {
        this(s, s2);
        this.initCause(cause);
    }
    
    public AuthenticationException(final Throwable cause, final String localizedMessage) {
        this.localizedMessage = localizedMessage;
        this.initCause(cause);
    }
    
    public Throwable getUnderlyingThrowable() {
        return this.getCause();
    }
    
    public String getLocalizedMessage() {
        if (this.localizedMessage == null) {
            return this.message;
        }
        return this.localizedMessage;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    protected static String getBundleString(final String key) {
        String string = null;
        try {
            final ResourceBundle resourceBundle = BundleUtilities.getResourceBundle(AuthenticationException.class, "Bundle");
            if (resourceBundle != null) {
                string = resourceBundle.getString(key);
            }
        }
        catch (MissingResourceException ex) {}
        return string;
    }
}
