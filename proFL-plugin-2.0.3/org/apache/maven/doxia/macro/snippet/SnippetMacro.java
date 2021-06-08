// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro.snippet;

import java.io.IOException;
import org.apache.maven.doxia.macro.MacroExecutionException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.sink.Sink;
import java.util.HashMap;
import java.util.Map;
import org.apache.maven.doxia.macro.AbstractMacro;

public class SnippetMacro extends AbstractMacro
{
    static final String EOL;
    private Map cache;
    private long timeout;
    private Map timeCached;
    private boolean debug;
    
    public SnippetMacro() {
        this.cache = new HashMap();
        this.timeout = 3600000L;
        this.timeCached = new HashMap();
        this.debug = false;
    }
    
    public void execute(final Sink sink, final MacroRequest request) throws MacroExecutionException {
        final String id = (String)request.getParameter("id");
        this.required(id, "id");
        final String urlParam = (String)request.getParameter("url");
        final String fileParam = (String)request.getParameter("file");
        StringBuffer snippet = null;
        Label_0186: {
            if (!StringUtils.isEmpty(urlParam)) {
                try {
                    final URL url = new URL(urlParam);
                    break Label_0186;
                }
                catch (MalformedURLException e2) {
                    throw new IllegalArgumentException(urlParam + " is a malformed URL");
                }
            }
            if (StringUtils.isEmpty(fileParam)) {
                throw new IllegalArgumentException("Either the 'url' or the 'file' param has to be given.");
            }
            File f = new File(fileParam);
            if (!f.isAbsolute()) {
                f = new File(request.getBasedir(), fileParam);
            }
            try {
                final URL url = f.toURL();
            }
            catch (MalformedURLException e3) {
                throw new IllegalArgumentException(urlParam + " is a malformed URL");
            }
            try {
                final URL url2;
                snippet = this.getSnippet(url2, id);
            }
            catch (IOException e) {
                throw new MacroExecutionException("Error reading snippet", e);
            }
        }
        sink.verbatim(true);
        sink.text(snippet.toString());
        sink.verbatim_();
    }
    
    private StringBuffer getSnippet(final URL url, final String id) throws IOException {
        final String cachedSnippet = (String)this.getCachedSnippet(url, id);
        StringBuffer result;
        if (cachedSnippet != null) {
            result = new StringBuffer(cachedSnippet);
            if (this.debug) {
                result.append("(Served from cache)");
            }
        }
        else {
            result = new SnippetReader(url).readSnippet(id);
            this.cacheSnippet(url, id, result.toString());
            if (this.debug) {
                result.append("(Fetched from url, cache content ").append(this.cache).append(")");
            }
        }
        return result;
    }
    
    private Object getCachedSnippet(final URL url, final String id) {
        if (this.isCacheTimedout(url, id)) {
            this.removeFromCache(url, id);
        }
        return this.cache.get(this.globalSnippetId(url, id));
    }
    
    boolean isCacheTimedout(final URL url, final String id) {
        return this.timeInCache(url, id) >= this.timeout;
    }
    
    long timeInCache(final URL url, final String id) {
        return System.currentTimeMillis() - this.getTimeCached(url, id);
    }
    
    long getTimeCached(final URL url, final String id) {
        final String globalId = this.globalSnippetId(url, id);
        return this.timeCached.containsKey(globalId) ? this.timeCached.get(globalId) : 0L;
    }
    
    private void removeFromCache(final URL url, final String id) {
        final String globalId = this.globalSnippetId(url, id);
        this.timeCached.remove(globalId);
        this.cache.remove(globalId);
    }
    
    private String globalSnippetId(final URL url, final String id) {
        return url + " " + id;
    }
    
    private void required(final String id, final String param) {
        if (id == null || "".equals(id)) {
            throw new IllegalArgumentException(param + " is a required parameter");
        }
    }
    
    public void cacheSnippet(final URL url, final String id, final String content) {
        this.cache.put(this.globalSnippetId(url, id), content);
        this.timeCached.put(this.globalSnippetId(url, id), new Long(System.currentTimeMillis()));
    }
    
    public void setCacheTimeout(final int time) {
        this.timeout = time;
    }
    
    static {
        EOL = System.getProperty("line.separator");
    }
}
