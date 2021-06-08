// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.texen.util;

import java.io.InputStream;
import java.io.FileInputStream;
import java.util.StringTokenizer;
import org.apache.velocity.texen.Generator;
import java.util.Properties;

public class PropertiesUtil
{
    public Properties load(final String propertiesFile) {
        Properties properties = null;
        final String templatePath = Generator.getInstance().getTemplatePath();
        try {
            if (templatePath != null) {
                properties = this.loadFromTemplatePath(propertiesFile);
            }
            else {
                properties = this.loadFromClassPath(propertiesFile);
            }
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            throw new RuntimeException("Could not load properties: " + e2.getMessage());
        }
        return properties;
    }
    
    protected Properties loadFromTemplatePath(final String propertiesFile) throws Exception {
        final Properties properties = new Properties();
        final String templatePath = Generator.getInstance().getTemplatePath();
        final StringTokenizer st = new StringTokenizer(templatePath, ",");
        if (st.hasMoreTokens()) {
            final String templateDir = st.nextToken();
            InputStream stream = null;
            try {
                String fullPath = propertiesFile;
                if (!fullPath.startsWith(templateDir)) {
                    fullPath = templateDir + "/" + propertiesFile;
                }
                stream = new FileInputStream(fullPath);
                properties.load(stream);
            }
            finally {
                if (stream != null) {
                    stream.close();
                }
            }
        }
        return properties;
    }
    
    protected Properties loadFromClassPath(final String propertiesName) throws Exception {
        final Properties properties = new Properties();
        final ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = null;
        try {
            final String propertiesFile = propertiesName.startsWith("$generator") ? propertiesName.substring("$generator.templatePath/".length()) : propertiesName;
            inputStream = classLoader.getResourceAsStream(propertiesFile);
            properties.load(inputStream);
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return properties;
    }
}
