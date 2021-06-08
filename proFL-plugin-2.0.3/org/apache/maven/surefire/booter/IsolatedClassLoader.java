// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.util.HashSet;
import java.net.URL;
import java.util.Set;
import java.net.URLClassLoader;

public class IsolatedClassLoader extends URLClassLoader
{
    private final ClassLoader parent;
    private final Set<URL> urls;
    private final String roleName;
    private boolean childDelegation;
    private static final URL[] EMPTY_URL_ARRAY;
    
    public IsolatedClassLoader(final ClassLoader parent, final boolean childDelegation, final String roleName) {
        super(IsolatedClassLoader.EMPTY_URL_ARRAY, parent);
        this.parent = ClassLoader.getSystemClassLoader();
        this.urls = new HashSet<URL>();
        this.childDelegation = true;
        this.childDelegation = childDelegation;
        this.roleName = roleName;
    }
    
    public void addURL(final URL url) {
        if (!this.urls.contains(url)) {
            super.addURL(url);
            this.urls.add(url);
        }
    }
    
    @Override
    public synchronized Class loadClass(final String name) throws ClassNotFoundException {
        Class c;
        if (this.childDelegation) {
            c = this.findLoadedClass(name);
            ClassNotFoundException ex = null;
            if (c == null) {
                try {
                    c = this.findClass(name);
                }
                catch (ClassNotFoundException e) {
                    ex = e;
                    if (this.parent != null) {
                        c = this.parent.loadClass(name);
                    }
                }
            }
            if (c == null) {
                throw ex;
            }
        }
        else {
            c = super.loadClass(name);
        }
        return c;
    }
    
    @Override
    public String toString() {
        return "IsolatedClassLoader{roleName='" + this.roleName + '\'' + '}';
    }
    
    static {
        EMPTY_URL_ARRAY = new URL[0];
    }
}
