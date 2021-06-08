// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.protocol;

import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;

public interface ProtocolSocketFactory
{
    Socket createSocket(final String p0, final int p1, final InetAddress p2, final int p3) throws IOException, UnknownHostException;
    
    Socket createSocket(final String p0, final int p1) throws IOException, UnknownHostException;
}
