// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import java.io.IOException;
import org.netbeans.lib.cvsclient.command.CommandAbortedException;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

public interface Connection
{
    LoggedDataInputStream getInputStream();
    
    LoggedDataOutputStream getOutputStream();
    
    void open() throws AuthenticationException, CommandAbortedException;
    
    void verify() throws AuthenticationException;
    
    void close() throws IOException;
    
    boolean isOpen();
    
    String getRepository();
    
    int getPort();
    
    void modifyInputStream(final ConnectionModifier p0) throws IOException;
    
    void modifyOutputStream(final ConnectionModifier p0) throws IOException;
}
