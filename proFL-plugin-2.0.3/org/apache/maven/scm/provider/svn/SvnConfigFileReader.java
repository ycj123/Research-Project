// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn;

import org.codehaus.plexus.util.IOUtil;
import java.io.IOException;
import org.codehaus.plexus.util.StringUtils;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.plexus.util.Os;
import java.io.File;

public class SvnConfigFileReader
{
    private File configDirectory;
    
    public File getConfigDirectory() {
        if (this.configDirectory == null) {
            if (Os.isFamily("windows")) {
                this.configDirectory = new File(System.getProperty("user.home"), "Application Data/Subversion");
            }
            else {
                this.configDirectory = new File(System.getProperty("user.home"), ".subversion");
            }
        }
        return this.configDirectory;
    }
    
    public void setConfigDirectory(final File configDirectory) {
        this.configDirectory = configDirectory;
    }
    
    public String getProperty(final String group, final String propertyName) {
        final List<String> lines = this.getConfLines();
        boolean inGroup = false;
        final Iterator<String> i = lines.iterator();
        while (i.hasNext()) {
            final String line = i.next().trim();
            if (!inGroup) {
                if (!("[" + group + "]").equals(line)) {
                    continue;
                }
                inGroup = true;
            }
            else {
                if (line.startsWith("[") && line.endsWith("]")) {
                    return null;
                }
                if (line.startsWith("#")) {
                    continue;
                }
                if (line.indexOf(61) < 0) {
                    continue;
                }
                final String property = line.substring(0, line.indexOf(61)).trim();
                if (!property.equals(propertyName)) {
                    continue;
                }
                final String value = line.substring(line.indexOf(61) + 1);
                return value.trim();
            }
        }
        return null;
    }
    
    private List<String> getConfLines() {
        final List<String> lines = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            if (this.getConfigDirectory().exists()) {
                reader = new BufferedReader(new FileReader(new File(this.getConfigDirectory(), "config")));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith("#") && StringUtils.isNotEmpty(line)) {
                        lines.add(line);
                    }
                }
            }
        }
        catch (IOException e) {
            lines.clear();
        }
        finally {
            IOUtil.close(reader);
            reader = null;
        }
        return lines;
    }
}
