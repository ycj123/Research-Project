// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class SystemPropertyManager
{
    public static PropertiesWrapper loadProperties(final InputStream inStream) throws IOException {
        final Properties p = new Properties();
        try {
            p.load(inStream);
        }
        finally {
            close(inStream);
        }
        return new PropertiesWrapper(p);
    }
    
    private static PropertiesWrapper loadProperties(final File file) throws IOException {
        return loadProperties(new FileInputStream(file));
    }
    
    public static void setSystemProperties(final File file) throws IOException {
        final PropertiesWrapper p = loadProperties(file);
        p.setAsSystemProperties();
    }
    
    public static File writePropertiesFile(final Properties properties, final File tempDirectory, final String name, final boolean keepForkFiles) throws IOException {
        final File file = File.createTempFile(name, "tmp", tempDirectory);
        if (!keepForkFiles) {
            file.deleteOnExit();
        }
        writePropertiesFile(file, name, properties);
        return file;
    }
    
    public static void writePropertiesFile(final File file, final String name, final Properties properties) throws IOException {
        final FileOutputStream out = new FileOutputStream(file);
        try {
            properties.store(out, name);
        }
        finally {
            out.close();
        }
    }
    
    public static void close(final InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        }
        catch (IOException ex) {}
    }
}
