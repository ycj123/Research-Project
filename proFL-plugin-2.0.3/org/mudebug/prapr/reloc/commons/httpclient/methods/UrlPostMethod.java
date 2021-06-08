// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import java.net.MalformedURLException;
import org.mudebug.prapr.reloc.commons.httpclient.util.URIUtil;
import org.mudebug.prapr.reloc.commons.httpclient.HttpUrlMethod;

public class UrlPostMethod extends PostMethod implements HttpUrlMethod
{
    private String url;
    
    public UrlPostMethod() {
    }
    
    public UrlPostMethod(final String url) throws MalformedURLException {
        super(URIUtil.getPath(url));
        this.setUrl(url);
    }
    
    public UrlPostMethod(final String url, final String tempDir) throws MalformedURLException {
        super(URIUtil.getPath(url), tempDir);
        this.setUrl(url);
    }
    
    public UrlPostMethod(final String url, final String tempDir, final String tempFile) throws MalformedURLException {
        super(URIUtil.getPath(url), tempDir, tempFile);
        this.setUrl(url);
    }
    
    public void setUrl(final String url) throws MalformedURLException {
        super.setPath(URIUtil.getPath(url));
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
