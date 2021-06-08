// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.loader;

import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;
import java.util.List;
import org.apache.velocity.util.StringUtils;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import java.util.HashMap;
import java.util.Map;

public class JarResourceLoader extends ResourceLoader
{
    private Map entryDirectory;
    private Map jarfiles;
    
    public JarResourceLoader() {
        this.entryDirectory = new HashMap(559);
        this.jarfiles = new HashMap(89);
    }
    
    public void init(final ExtendedProperties configuration) {
        this.log.trace("JarResourceLoader : initialization starting.");
        Vector paths = configuration.getVector("path");
        StringUtils.trimStrings(paths);
        if (paths == null || paths.size() == 0) {
            paths = configuration.getVector("resource.path");
            StringUtils.trimStrings(paths);
            if (paths != null && paths.size() > 0) {
                this.log.info("JarResourceLoader : you are using a deprecated configuration property for the JarResourceLoader -> '<name>.resource.loader.resource.path'. Please change to the conventional '<name>.resource.loader.path'.");
            }
        }
        if (paths != null) {
            this.log.debug("JarResourceLoader # of paths : " + paths.size());
            for (int i = 0; i < paths.size(); ++i) {
                this.loadJar(paths.get(i));
            }
        }
        this.log.trace("JarResourceLoader : initialization complete.");
    }
    
    private void loadJar(String path) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("JarResourceLoader : trying to load \"" + path + "\"");
        }
        if (path == null) {
            this.log.error("JarResourceLoader : can not load JAR - JAR path is null");
        }
        if (!path.startsWith("jar:")) {
            this.log.error("JarResourceLoader : JAR path must start with jar: -> see java.net.JarURLConnection for information");
        }
        if (!path.endsWith("!/")) {
            path += "!/";
        }
        this.closeJar(path);
        final JarHolder temp = new JarHolder(this.rsvc, path);
        this.addEntries(temp.getEntries());
        this.jarfiles.put(temp.getUrlPath(), temp);
    }
    
    private void closeJar(final String path) {
        if (this.jarfiles.containsKey(path)) {
            final JarHolder theJar = this.jarfiles.get(path);
            theJar.close();
        }
    }
    
    private void addEntries(final Hashtable entries) {
        this.entryDirectory.putAll(entries);
    }
    
    public InputStream getResourceStream(final String source) throws ResourceNotFoundException {
        InputStream results = null;
        if (org.mudebug.prapr.reloc.commons.lang.StringUtils.isEmpty(source)) {
            throw new ResourceNotFoundException("Need to have a resource!");
        }
        String normalizedPath = StringUtils.normalizePath(source);
        if (normalizedPath == null || normalizedPath.length() == 0) {
            final String msg = "JAR resource error : argument " + normalizedPath + " contains .. and may be trying to access " + "content outside of template root.  Rejected.";
            this.log.error("JarResourceLoader : " + msg);
            throw new ResourceNotFoundException(msg);
        }
        if (normalizedPath.startsWith("/")) {
            normalizedPath = normalizedPath.substring(1);
        }
        if (this.entryDirectory.containsKey(normalizedPath)) {
            final String jarurl = this.entryDirectory.get(normalizedPath);
            if (this.jarfiles.containsKey(jarurl)) {
                final JarHolder holder = this.jarfiles.get(jarurl);
                results = holder.getResource(normalizedPath);
                return results;
            }
        }
        throw new ResourceNotFoundException("JarResourceLoader Error: cannot find resource " + source);
    }
    
    public boolean isSourceModified(final Resource resource) {
        return true;
    }
    
    public long getLastModified(final Resource resource) {
        return 0L;
    }
}
