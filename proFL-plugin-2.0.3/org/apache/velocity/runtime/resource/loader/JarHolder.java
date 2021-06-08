// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.loader;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.Log;
import java.net.JarURLConnection;
import java.util.jar.JarFile;

public class JarHolder
{
    private String urlpath;
    private JarFile theJar;
    private JarURLConnection conn;
    private Log log;
    
    public JarHolder(final RuntimeServices rs, final String urlpath) {
        this.urlpath = null;
        this.theJar = null;
        this.conn = null;
        this.log = null;
        this.log = rs.getLog();
        this.urlpath = urlpath;
        this.init();
        if (this.log.isDebugEnabled()) {
            this.log.debug("JarHolder: initialized JAR: " + urlpath);
        }
    }
    
    public void init() {
        try {
            if (this.log.isDebugEnabled()) {
                this.log.debug("JarHolder: attempting to connect to " + this.urlpath);
            }
            final URL url = new URL(this.urlpath);
            (this.conn = (JarURLConnection)url.openConnection()).setAllowUserInteraction(false);
            this.conn.setDoInput(true);
            this.conn.setDoOutput(false);
            this.conn.connect();
            this.theJar = this.conn.getJarFile();
        }
        catch (IOException ioe) {
            this.log.error("JarHolder: error establishing connection to JAR at \"" + this.urlpath + "\"", ioe);
        }
    }
    
    public void close() {
        try {
            this.theJar.close();
        }
        catch (Exception e) {
            this.log.error("JarHolder: error closing the JAR file", e);
        }
        this.theJar = null;
        this.conn = null;
        this.log.trace("JarHolder: JAR file closed");
    }
    
    public InputStream getResource(final String theentry) throws ResourceNotFoundException {
        InputStream data = null;
        try {
            final JarEntry entry = this.theJar.getJarEntry(theentry);
            if (entry != null) {
                data = this.theJar.getInputStream(entry);
            }
        }
        catch (Exception fnfe) {
            this.log.error("JarHolder: getResource() error", fnfe);
            throw new ResourceNotFoundException(fnfe);
        }
        return data;
    }
    
    public Hashtable getEntries() {
        final Hashtable allEntries = new Hashtable(559);
        final Enumeration all = this.theJar.entries();
        while (all.hasMoreElements()) {
            final JarEntry je = all.nextElement();
            if (!je.isDirectory()) {
                allEntries.put(je.getName(), this.urlpath);
            }
        }
        return allEntries;
    }
    
    public String getUrlPath() {
        return this.urlpath;
    }
}
