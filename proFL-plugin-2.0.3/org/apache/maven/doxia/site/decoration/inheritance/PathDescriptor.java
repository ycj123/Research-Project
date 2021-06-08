// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration.inheritance;

import org.codehaus.plexus.util.StringUtils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PathDescriptor
{
    private final URL baseUrl;
    private final URL pathUrl;
    private final String relativePath;
    
    public PathDescriptor(final String path) throws MalformedURLException {
        this((URL)null, path);
    }
    
    public PathDescriptor(final String base, final String path) throws MalformedURLException {
        this(buildBaseUrl(base), path);
    }
    
    public PathDescriptor(final URL baseUrl, final String path) throws MalformedURLException {
        this.baseUrl = baseUrl;
        URL pathURL = null;
        String relPath = null;
        Label_0065: {
            try {
                pathURL = new URL(path);
            }
            catch (MalformedURLException e) {
                try {
                    pathURL = buildUrl(baseUrl, path);
                }
                catch (MalformedURLException e2) {
                    if (path != null && path.startsWith("/")) {
                        relPath = path.substring(1);
                        break Label_0065;
                    }
                    relPath = path;
                }
            }
        }
        this.pathUrl = pathURL;
        this.relativePath = relPath;
    }
    
    private static URL buildBaseUrl(final String base) throws MalformedURLException {
        if (base == null) {
            return null;
        }
        try {
            return new URL(base);
        }
        catch (MalformedURLException e) {
            return new File(base).toURI().toURL();
        }
    }
    
    private static URL buildUrl(final URL baseUrl, final String path) throws MalformedURLException {
        if (baseUrl == null) {
            throw new MalformedURLException("Base is null!");
        }
        if (path == null) {
            return baseUrl;
        }
        if (baseUrl.getProtocol().equals("file")) {
            return new File(baseUrl.getFile(), path).toURI().toURL();
        }
        if (path.startsWith("/") && baseUrl.getPath().endsWith("/")) {
            return new URL(baseUrl, path.substring(1));
        }
        return new URL(baseUrl, path);
    }
    
    public boolean isFile() {
        return this.isRelative() || this.pathUrl.getProtocol().equals("file");
    }
    
    public boolean isRelative() {
        return this.pathUrl == null;
    }
    
    public URL getBaseUrl() {
        return this.baseUrl;
    }
    
    public URL getPathUrl() {
        return this.pathUrl;
    }
    
    public String getPath() {
        if (this.getPathUrl() == null) {
            return this.relativePath;
        }
        if (this.isFile()) {
            return StringUtils.stripEnd(this.getPathUrl().getPath(), "/");
        }
        return this.getPathUrl().getPath();
    }
    
    public String getLocation() {
        if (!this.isFile()) {
            return this.getPathUrl().toExternalForm();
        }
        if (this.getPathUrl() != null) {
            return StringUtils.stripEnd(this.getPathUrl().getFile(), "/");
        }
        return this.relativePath;
    }
    
    public String toString() {
        final StringBuffer res = new StringBuffer(StringUtils.isNotEmpty(this.relativePath) ? this.relativePath : String.valueOf(this.pathUrl));
        res.append(" (Base: ").append(this.baseUrl).append(") Location: ").append(this.getLocation());
        return res.toString();
    }
}
