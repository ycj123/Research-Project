// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

public interface HttpConnectionManager
{
    HttpConnection getConnection(final HostConfiguration p0);
    
    HttpConnection getConnection(final HostConfiguration p0, final long p1) throws HttpException;
    
    void releaseConnection(final HttpConnection p0);
}
