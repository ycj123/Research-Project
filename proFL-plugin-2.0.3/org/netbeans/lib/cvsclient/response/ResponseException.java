// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import org.netbeans.lib.cvsclient.command.CommandException;

public class ResponseException extends CommandException
{
    public ResponseException(final Exception ex) {
        super(ex, ex.getLocalizedMessage());
    }
    
    public ResponseException(final Exception ex, final String s) {
        super(ex, s);
    }
    
    public ResponseException(final String s, final String s2) {
        super(s, s2);
    }
}
