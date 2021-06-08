// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import java.util.HashMap;
import java.net.URL;
import java.util.Map;
import java.net.URLClassLoader;

public class RootLoader extends URLClassLoader
{
    private Map<String, Class> customClasses;
    
    private RootLoader(final ClassLoader parent) {
        this(new URL[0], parent);
    }
    
    public RootLoader(final URL[] urls, final ClassLoader parent) {
        super(urls, parent);
        this.customClasses = new HashMap<String, Class>();
        try {
            this.customClasses.put("org.w3c.dom.Node", super.loadClass("org.w3c.dom.Node", false));
        }
        catch (Exception ex) {}
    }
    
    private static ClassLoader chooseParent() {
        final ClassLoader cl = RootLoader.class.getClassLoader();
        if (cl != null) {
            return cl;
        }
        return ClassLoader.getSystemClassLoader();
    }
    
    public RootLoader(final LoaderConfiguration lc) {
        this(chooseParent());
        Thread.currentThread().setContextClassLoader(this);
        final URL[] arr$;
        final URL[] urls = arr$ = lc.getClassPathUrls();
        for (final URL url : arr$) {
            this.addURL(url);
        }
    }
    
    @Override
    protected Class loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
        Class c = this.findLoadedClass(name);
        if (c != null) {
            return c;
        }
        c = this.customClasses.get(name);
        if (c != null) {
            return c;
        }
        try {
            c = this.oldFindClass(name);
        }
        catch (ClassNotFoundException ex) {}
        if (c == null) {
            c = super.loadClass(name, resolve);
        }
        if (resolve) {
            this.resolveClass(c);
        }
        return c;
    }
    
    @Override
    public URL getResource(final String name) {
        URL url = this.findResource(name);
        if (url == null) {
            url = super.getResource(name);
        }
        return url;
    }
    
    public void addURL(final URL url) {
        super.addURL(url);
    }
    
    private Class oldFindClass(final String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
    
    @Override
    protected Class findClass(final String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
}
