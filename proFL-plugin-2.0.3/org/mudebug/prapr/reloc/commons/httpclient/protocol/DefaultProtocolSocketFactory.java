// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.protocol;

import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;

public class DefaultProtocolSocketFactory implements ProtocolSocketFactory
{
    private static final DefaultProtocolSocketFactory factory;
    
    static DefaultProtocolSocketFactory getSocketFactory() {
        return DefaultProtocolSocketFactory.factory;
    }
    
    public Socket createSocket(final String host, final int port, final InetAddress clientHost, final int clientPort) throws IOException, UnknownHostException {
        return new Socket(host, port, clientHost, clientPort);
    }
    
    public Socket createSocket(final String host, final int port) throws IOException, UnknownHostException {
        return new Socket(host, port);
    }
    
    public boolean equals(final Object obj) {
        return obj != null && obj.getClass().equals(DefaultProtocolSocketFactory.class);
    }
    
    public int hashCode() {
        return DefaultProtocolSocketFactory.class.hashCode();
    }
    
    static {
        factory = new DefaultProtocolSocketFactory();
    }
}
