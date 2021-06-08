// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;

public interface HttpMethod
{
    String getName();
    
    HostConfiguration getHostConfiguration();
    
    void setPath(final String p0);
    
    String getPath();
    
    URI getURI() throws URIException;
    
    void setStrictMode(final boolean p0);
    
    boolean isStrictMode();
    
    void setRequestHeader(final String p0, final String p1);
    
    void setRequestHeader(final Header p0);
    
    void addRequestHeader(final String p0, final String p1);
    
    void addRequestHeader(final Header p0);
    
    Header getRequestHeader(final String p0);
    
    void removeRequestHeader(final String p0);
    
    boolean getFollowRedirects();
    
    void setFollowRedirects(final boolean p0);
    
    void setQueryString(final String p0);
    
    void setQueryString(final NameValuePair[] p0);
    
    String getQueryString();
    
    Header[] getRequestHeaders();
    
    boolean validate();
    
    int getStatusCode();
    
    String getStatusText();
    
    Header[] getResponseHeaders();
    
    Header getResponseHeader(final String p0);
    
    Header[] getResponseFooters();
    
    Header getResponseFooter(final String p0);
    
    byte[] getResponseBody();
    
    String getResponseBodyAsString();
    
    InputStream getResponseBodyAsStream() throws IOException;
    
    boolean hasBeenUsed();
    
    int execute(final HttpState p0, final HttpConnection p1) throws HttpException, IOException;
    
    void recycle();
    
    void releaseConnection();
    
    void addResponseFooter(final Header p0);
    
    StatusLine getStatusLine();
    
    boolean getDoAuthentication();
    
    void setDoAuthentication(final boolean p0);
}
