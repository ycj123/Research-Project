// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoaderConfiguration
{
    private static final String MAIN_PREFIX = "main is";
    private static final String LOAD_PREFIX = "load";
    private static final String PROP_PREFIX = "property";
    private List classPath;
    private String main;
    private boolean requireMain;
    private static final char WILDCARD = '*';
    private static final String ALL_WILDCARD = "**";
    private static final String MATCH_FILE_NAME = "\\\\E[^/]+?\\\\Q";
    private static final String MATCH_ALL = "\\\\E.+?\\\\Q";
    
    public LoaderConfiguration() {
        this.classPath = new ArrayList();
        this.requireMain = true;
    }
    
    public void configure(final InputStream is) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        int lineNumber = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                if (this.requireMain && this.main == null) {
                    throw new IOException("missing main class definition in config file");
                }
            }
            else {
                line = line.trim();
                ++lineNumber;
                if (line.startsWith("#")) {
                    continue;
                }
                if (line.length() == 0) {
                    continue;
                }
                if (line.startsWith("load")) {
                    String loadPath = line.substring("load".length()).trim();
                    loadPath = this.assignProperties(loadPath);
                    this.loadFilteredPath(loadPath);
                }
                else if (line.startsWith("main is")) {
                    if (this.main != null) {
                        throw new IOException("duplicate definition of main in line " + lineNumber + " : " + line);
                    }
                    this.main = line.substring("main is".length()).trim();
                }
                else {
                    if (!line.startsWith("property")) {
                        throw new IOException("unexpected line in " + lineNumber + " : " + line);
                    }
                    final String params = line.substring("property".length()).trim();
                    final int index = params.indexOf(61);
                    if (index == -1) {
                        throw new IOException("unexpected property format - expecting name=value" + lineNumber + " : " + line);
                    }
                    final String propName = params.substring(0, index);
                    final String propValue = this.assignProperties(params.substring(index + 1));
                    System.setProperty(propName, propValue);
                }
            }
        }
    }
    
    private String assignProperties(final String str) {
        int propertyIndexStart = 0;
        int propertyIndexEnd = 0;
        boolean requireProperty = false;
        String result = "";
        while (propertyIndexStart < str.length()) {
            final int i1 = str.indexOf("${", propertyIndexStart);
            final int i2 = str.indexOf("!{", propertyIndexStart);
            if (i1 == -1) {
                propertyIndexStart = i2;
            }
            else if (i2 == -1) {
                propertyIndexStart = i1;
            }
            else {
                propertyIndexStart = Math.min(i1, i2);
            }
            requireProperty = (propertyIndexStart == i2);
            if (propertyIndexStart == -1) {
                break;
            }
            result += str.substring(propertyIndexEnd, propertyIndexStart);
            propertyIndexEnd = str.indexOf("}", propertyIndexStart);
            if (propertyIndexEnd == -1) {
                break;
            }
            final String propertyKey = str.substring(propertyIndexStart + 2, propertyIndexEnd);
            String propertyValue = System.getProperty(propertyKey);
            if (propertyValue == null) {
                if (requireProperty) {
                    throw new IllegalArgumentException("Variable " + propertyKey + " in groovy-starter.conf references a non-existent System property! Try passing the property to the VM using -D" + propertyKey + "=myValue in JAVA_OPTS");
                }
                return null;
            }
            else {
                propertyValue = this.getSlashyPath(propertyValue);
                propertyValue = this.correctDoubleSlash(propertyValue, propertyIndexEnd, str);
                result += propertyValue;
                propertyIndexStart = ++propertyIndexEnd;
            }
        }
        if (propertyIndexStart == -1 || propertyIndexStart >= str.length()) {
            result += str.substring(propertyIndexEnd);
        }
        else if (propertyIndexEnd == -1) {
            result += str.substring(propertyIndexStart);
        }
        return result;
    }
    
    private String correctDoubleSlash(String propertyValue, final int propertyIndexEnd, final String str) {
        final int index = propertyIndexEnd + 1;
        if (index < str.length() && str.charAt(index) == '/' && propertyValue.endsWith("/") && propertyValue.length() > 0) {
            propertyValue = propertyValue.substring(0, propertyValue.length() - 1);
        }
        return propertyValue;
    }
    
    private void loadFilteredPath(String filter) {
        if (filter == null) {
            return;
        }
        filter = this.getSlashyPath(filter);
        int starIndex = filter.indexOf(42);
        if (starIndex == -1) {
            this.addFile(new File(filter));
            return;
        }
        final boolean recursive = filter.indexOf("**") != -1;
        if (filter.lastIndexOf(47) < starIndex) {
            starIndex = filter.lastIndexOf(47) + 1;
        }
        final String startDir = filter.substring(0, starIndex - 1);
        final File root = new File(startDir);
        filter = Pattern.quote(filter);
        filter = filter.replaceAll("\\*\\*", "\\\\E.+?\\\\Q");
        filter = filter.replaceAll("\\*", "\\\\E[^/]+?\\\\Q");
        final Pattern pattern = Pattern.compile(filter);
        final File[] files = root.listFiles();
        if (files != null) {
            this.findMatchingFiles(files, pattern, recursive);
        }
    }
    
    private void findMatchingFiles(final File[] files, final Pattern pattern, final boolean recursive) {
        for (int i = 0; i < files.length; ++i) {
            final File file = files[i];
            final String fileString = this.getSlashyPath(file.getPath());
            final Matcher m = pattern.matcher(fileString);
            if (m.matches() && file.isFile()) {
                this.addFile(file);
            }
            if (file.isDirectory() && recursive) {
                final File[] dirFiles = file.listFiles();
                if (dirFiles != null) {
                    this.findMatchingFiles(dirFiles, pattern, true);
                }
            }
        }
    }
    
    private String getSlashyPath(final String path) {
        String changedPath = path;
        if (File.separatorChar != '/') {
            changedPath = changedPath.replace(File.separatorChar, '/');
        }
        return changedPath;
    }
    
    private boolean parentPathDoesExist(final String path) {
        final File dir = new File(path).getParentFile();
        return dir.exists();
    }
    
    private String getParentPath(final String filter) {
        final int index = filter.lastIndexOf(47);
        if (index == -1) {
            return "";
        }
        return filter.substring(index + 1);
    }
    
    public void addFile(final File file) {
        if (file != null && file.exists()) {
            try {
                this.classPath.add(file.toURI().toURL());
            }
            catch (MalformedURLException e) {
                throw new AssertionError((Object)"converting an existing file to an url should have never thrown an exception!");
            }
        }
    }
    
    public void addFile(final String filename) {
        if (filename != null) {
            this.addFile(new File(filename));
        }
    }
    
    public void addClassPath(final String path) {
        final String[] arr$;
        final String[] paths = arr$ = path.split(File.pathSeparator);
        for (final String cpPath : arr$) {
            if (cpPath.endsWith("*")) {
                final File dir = new File(cpPath.substring(0, cpPath.length() - 1));
                final File[] files = dir.listFiles();
                if (files != null) {
                    for (final File file : files) {
                        if (file.isFile() && file.getName().endsWith(".jar")) {
                            this.addFile(file);
                        }
                    }
                }
            }
            else {
                this.addFile(new File(cpPath));
            }
        }
    }
    
    public URL[] getClassPathUrls() {
        return this.classPath.toArray(new URL[this.classPath.size()]);
    }
    
    public String getMainClass() {
        return this.main;
    }
    
    public void setMainClass(final String classname) {
        this.main = classname;
        this.requireMain = false;
    }
    
    public void setRequireMain(final boolean requireMain) {
        this.requireMain = requireMain;
    }
}
