// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import java.io.IOException;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

public interface ConnectionModifier
{
    void modifyInputStream(final LoggedDataInputStream p0) throws IOException;
    
    void modifyOutputStream(final LoggedDataOutputStream p0) throws IOException;
}
