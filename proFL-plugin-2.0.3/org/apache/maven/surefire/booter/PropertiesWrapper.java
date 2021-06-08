// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.util.Map;
import org.apache.maven.surefire.util.internal.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesWrapper implements KeyValueSource
{
    private final Properties properties;
    
    public PropertiesWrapper(final Properties properties) {
        if (properties == null) {
            throw new IllegalStateException("Properties cannot be null");
        }
        this.properties = properties;
    }
    
    public Properties getProperties() {
        return this.properties;
    }
    
    public void setAsSystemProperties() {
        for (final Object o : this.properties.keySet()) {
            final String key = (String)o;
            System.setProperty(key, this.properties.getProperty(key));
        }
    }
    
    public String getProperty(final String key) {
        return this.properties.getProperty(key);
    }
    
    public boolean getBooleanProperty(final String propertyName) {
        return Boolean.valueOf(this.properties.getProperty(propertyName));
    }
    
    public Boolean getBooleanObjectProperty(final String propertyName) {
        return Boolean.valueOf(this.properties.getProperty(propertyName));
    }
    
    public File getFileProperty(final String key) {
        final String property = this.getProperty(key);
        if (property == null) {
            return null;
        }
        final TypeEncodedValue typeEncodedValue = new TypeEncodedValue(File.class.getName(), property);
        return (File)typeEncodedValue.getDecodedValue();
    }
    
    public List<String> getStringList(final String propertyPrefix) {
        final List<String> result = new ArrayList<String>();
        String value;
        for (int i = 0; (value = this.getProperty(propertyPrefix + i)) != null; ++i) {
            result.add(value);
        }
        return result;
    }
    
    public TypeEncodedValue getTypeEncodedValue(final String key) {
        final String typeEncoded = this.getProperty(key);
        if (typeEncoded == null) {
            return null;
        }
        final int typeSep = typeEncoded.indexOf("|");
        final String type = typeEncoded.substring(0, typeSep);
        final String value = typeEncoded.substring(typeSep + 1);
        return new TypeEncodedValue(type, value);
    }
    
    Classpath getClasspath(final String prefix) {
        final List<String> elements = this.getStringList(prefix);
        return new Classpath(elements);
    }
    
    public void setClasspath(final String prefix, final Classpath classpath) {
        final List classpathElements = classpath.getClassPath();
        for (int i = 0; i < classpathElements.size(); ++i) {
            final String element = classpathElements.get(i);
            this.setProperty(prefix + i, element);
        }
    }
    
    public void setProperty(final String key, final String value) {
        if (value != null) {
            this.properties.setProperty(key, value);
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
                this.properties.setProperty(propertyPrefix + i, aStringArray);
                ++i;
            }
        }
    }
    
    public void copyTo(final Map target) {
        for (final Object key : this.properties.keySet()) {
            target.put(key, this.properties.get(key));
        }
    }
}
