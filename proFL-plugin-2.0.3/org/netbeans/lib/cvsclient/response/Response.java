// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

public interface Response
{
    void process(final LoggedDataInputStream p0, final ResponseServices p1) throws ResponseException;
    
    boolean isTerminalResponse();
}
