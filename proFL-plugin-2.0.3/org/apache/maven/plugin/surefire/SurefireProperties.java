// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.maven.surefire.booter.Classpath;
import org.apache.maven.surefire.util.internal.StringUtils;
import java.util.List;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import org.apache.maven.surefire.booter.KeyValueSource;
import java.util.Properties;

public class SurefireProperties extends Properties implements KeyValueSource
{
    private final LinkedHashSet<Object> items;
    private static final Set<String> keysThatCannotBeUsedAsSystemProperties;
    
    public SurefireProperties() {
        this.items = new LinkedHashSet<Object>();
    }
    
    public SurefireProperties(final Properties source) {
        this.items = new LinkedHashSet<Object>();
        if (source != null) {
            this.putAll(source);
        }
    }
    
    public SurefireProperties(final KeyValueSource source) {
        this.items = new LinkedHashSet<Object>();
        if (source != null) {
            source.copyTo(this);
        }
    }
    
    @Override
    public synchronized Object put(final Object key, final Object value) {
        this.items.add(key);
        return super.put(key, value);
    }
    
    @Override
    public synchronized Object remove(final Object key) {
        this.items.remove(key);
        return super.remove(key);
    }
    
    @Override
    public synchronized void clear() {
        this.items.clear();
        super.clear();
    }
    
    @Override
    public synchronized Enumeration<Object> keys() {
        return Collections.enumeration(this.items);
    }
    
    public void copyPropertiesFrom(final Properties source) {
        if (source != null) {
            for (final Object key : source.keySet()) {
                final Object value = source.get(key);
                this.put(key, value);
            }
        }
    }
    
    public Iterable<Object> getStringKeySet() {
        return this.keySet();
    }
    
    public Set<Object> propertiesThatCannotBeSetASystemProperties() {
        final Set<Object> result = new HashSet<Object>();
        for (final Object key : this.getStringKeySet()) {
            if (SurefireProperties.keysThatCannotBeUsedAsSystemProperties.contains(key)) {
                result.add(key);
            }
        }
        return result;
    }
    
    public void copyToSystemProperties() {
        for (final Object o : this.items) {
            final String key = (String)o;
            final String value = this.getProperty(key);
            System.setProperty(key, value);
        }
    }
    
    static SurefireProperties calculateEffectiveProperties(final Properties systemProperties, final Map<String, String> systemPropertyVariables, final Properties userProperties, final SurefireProperties props) {
        final SurefireProperties result = new SurefireProperties();
        result.copyPropertiesFrom(systemProperties);
        result.copyPropertiesFrom(props);
        copyProperties(result, systemPropertyVariables);
        copyProperties(result, systemPropertyVariables);
        result.copyPropertiesFrom(userProperties);
        return result;
    }
    
    public static void copyProperties(final Properties target, final Map<String, String> source) {
        if (source != null) {
            for (final String key : source.keySet()) {
                final String value = source.get(key);
                if (value != null) {
                    target.setProperty(key, value);
                }
            }
        }
    }
    
    public void copyTo(final Map target) {
        for (final Object key : this.keySet()) {
            target.put(key, this.get(key));
        }
    }
    
    public void setProperty(final String key, final File file) {
        if (file != null) {
            this.setProperty(key, file.toString());
        }
    }
    
    public void setProperty(final String key, final Boolean aBoolean) {
        if (aBoolean != null) {
            this.setProperty(key, aBoolean.toString());
        }
    }
    
    public void addList(final List items, final String propertyPrefix) {
        if (items == null || items.size() == 0) {
            return;
        }
        int i = 0;
        for (final Object item : items) {
            if (item == null) {
                throw new NullPointerException(propertyPrefix + i + " has null value");
            }
            final String[] arr$;
            final String[] stringArray = arr$ = StringUtils.split(item.toString(), ",");
            for (final String aStringArray : arr$) {
                this.setProperty(propertyPrefix + i, aStringArray);
                ++i;
            }
        }
    }
    
    public void setClasspath(final String prefix, final Classpath classpath) {
        final List classpathElements = classpath.getClassPath();
        for (int i = 0; i < classpathElements.size(); ++i) {
            final String element = classpathElements.get(i);
            this.setProperty(prefix + i, element);
        }
    }
    
    private static SurefireProperties loadProperties(final InputStream inStream) throws IOException {
        final Properties p = new Properties();
        try {
            p.load(inStream);
        }
        finally {
            close(inStream);
        }
        return new SurefireProperties(p);
    }
    
    public static SurefireProperties loadProperties(final File file) throws IOException {
        if (file != null) {
            return loadProperties(new FileInputStream(file));
        }
        return new SurefireProperties();
    }
    
    private static void close(final InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        }
        catch (IOException ex) {}
    }
    
    public void setNullableProperty(final String key, final String value) {
        if (value != null) {
            super.setProperty(key, value);
        }
    }
    
    static {
        keysThatCannotBeUsedAsSystemProperties = new HashSet<String>() {
            {
                this.add("java.library.path");
                this.add("file.encoding");
                this.add("jdk.map.althashing.threshold");
            }
        };
    }
}
