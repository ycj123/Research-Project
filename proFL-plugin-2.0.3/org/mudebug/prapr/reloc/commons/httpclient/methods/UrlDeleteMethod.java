// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import java.net.MalformedURLException;
import org.mudebug.prapr.reloc.commons.httpclient.util.URIUtil;
import org.mudebug.prapr.reloc.commons.httpclient.HttpUrlMethod;

public class UrlDeleteMethod extends DeleteMethod implements HttpUrlMethod
{
    private String url;
    
    public UrlDeleteMethod() {
    }
    
    public UrlDeleteMethod(final String url) throws MalformedURLException {
        super(URIUtil.getPath(url));
        this.setUrl(url);
    }
    
    public void setUrl(final String url) throws MalformedURLException {
        this.setPath(URIUtil.getPath(url));
        this.url = url;
        final String query = URIUtil.getQuery(url);
        if (query != null && query.length() > 0) {
            super.setQueryString(query);
        }
    }
    
    public String getUrl() {
        return this.url;
    }
}
