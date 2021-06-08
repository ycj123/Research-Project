// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import java.net.MalformedURLException;

public interface HttpUrlMethod extends HttpMethod
{
    void setUrl(final String p0) throws MalformedURLException;
    
    String getUrl();
}
