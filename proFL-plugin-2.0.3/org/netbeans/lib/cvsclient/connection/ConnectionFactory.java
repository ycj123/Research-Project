// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import org.netbeans.lib.cvsclient.CVSRoot;

public class ConnectionFactory
{
    protected ConnectionFactory() {
    }
    
    public static Connection getConnection(final String s) throws IllegalArgumentException {
        return getConnection(CVSRoot.parse(s));
    }
    
    public static Connection getConnection(final CVSRoot obj) throws IllegalArgumentException {
        if (obj.isLocal()) {
            final LocalConnection localConnection = new LocalConnection();
            localConnection.setRepository(obj.getRepository());
            return localConnection;
        }
        if ("pserver" == obj.getMethod()) {
            return new PServerConnection(obj);
        }
        throw new IllegalArgumentException("Unrecognized CVS Root: " + obj);
    }
}
