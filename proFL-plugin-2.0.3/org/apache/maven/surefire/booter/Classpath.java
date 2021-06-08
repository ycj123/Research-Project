// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.net.MalformedURLException;
import org.apache.maven.surefire.util.UrlUtils;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class Classpath implements Iterable<String>
{
    private final List<String> unmodifiableElements;
    
    public static Classpath join(final Classpath firstClasspath, final Classpath secondClasspath) {
        final LinkedHashSet<String> accumulated = new LinkedHashSet<String>();
        if (firstClasspath != null) {
            firstClasspath.addTo(accumulated);
        }
        if (secondClasspath != null) {
            secondClasspath.addTo(accumulated);
        }
        return new Classpath(accumulated);
    }
    
    private void addTo(final Collection<String> c) {
        c.addAll(this.unmodifiableElements);
    }
    
    private Classpath() {
        this.unmodifiableElements = Collections.emptyList();
    }
    
    public Classpath(final Classpath other, final String additionalElement) {
        final ArrayList<String> elems = new ArrayList<String>(other.unmodifiableElements);
        elems.add(additionalElement);
        this.unmodifiableElements = Collections.unmodifiableList((List<? extends String>)elems);
    }
    
    public Classpath(final Iterable<String> elements) {
        final List<String> newCp = new ArrayList<String>();
        for (final String element : elements) {
            newCp.add(element);
        }
        this.unmodifiableElements = Collections.unmodifiableList((List<? extends String>)newCp);
    }
    
    public static Classpath emptyClasspath() {
        return new Classpath();
    }
    
    public Classpath addClassPathElementUrl(final String path) {
        if (path == null) {
            throw new IllegalArgumentException("Null is not a valid class path element url.");
        }
        return this.unmodifiableElements.contains(path) ? this : new Classpath(this, path);
    }
    
    public List<String> getClassPath() {
        return this.unmodifiableElements;
    }
    
    public List<URL> getAsUrlList() throws MalformedURLException {
        final List<URL> urls = new ArrayList<URL>();
        for (final String url : this.unmodifiableElements) {
            final File f = new File(url);
            urls.add(UrlUtils.getURL(f));
        }
        return urls;
    }
    
    public void writeToSystemProperty(final String propertyName) {
        final StringBuilder sb = new StringBuilder();
        for (final String element : this.unmodifiableElements) {
            sb.append(element).append(File.pathSeparatorChar);
        }
        System.setProperty(propertyName, sb.toString());
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Classpath classpath = (Classpath)o;
        if (this.unmodifiableElements != null) {
            if (!this.unmodifiableElements.equals(classpath.unmodifiableElements)) {
                return false;
            }
        }
        else if (classpath.unmodifiableElements != null) {
            return false;
        }
        return true;
        b = false;
        return b;
    }
    
    public ClassLoader createClassLoader(final ClassLoader parent, final boolean childDelegation, final boolean enableAssertions, final String roleName) throws SurefireExecutionException {
        try {
            final List urls = this.getAsUrlList();
            final IsolatedClassLoader classLoader = new IsolatedClassLoader(parent, childDelegation, roleName);
            for (final Object url1 : urls) {
                final URL url2 = (URL)url1;
                classLoader.addURL(url2);
            }
            if (parent != null) {
                parent.setDefaultAssertionStatus(enableAssertions);
            }
            classLoader.setDefaultAssertionStatus(enableAssertions);
            return classLoader;
        }
        catch (MalformedURLException e) {
            throw new SurefireExecutionException("When creating classloader", e);
        }
    }
    
    @Override
    public int hashCode() {
        return (this.unmodifiableElements != null) ? this.unmodifiableElements.hashCode() : 0;
    }
    
    public String getLogMessage(final String descriptor) {
        final StringBuilder result = new StringBuilder();
        result.append(descriptor).append(" classpath:");
        for (final String element : this.unmodifiableElements) {
            result.append("  ").append(element);
        }
        return result.toString();
    }
    
    public String getCompactLogMessage(final String descriptor) {
        final StringBuilder result = new StringBuilder();
        result.append(descriptor).append(" classpath:");
        for (final String element : this.unmodifiableElements) {
            result.append("  ");
            if (element != null) {
                final int pos = element.lastIndexOf(File.separatorChar);
                if (pos >= 0) {
                    result.append(element.substring(pos + 1));
                }
                else {
                    result.append(element);
                }
            }
            else {
                result.append(element);
            }
        }
        return result.toString();
    }
    
    public Iterator<String> iterator() {
        return this.unmodifiableElements.iterator();
    }
}
