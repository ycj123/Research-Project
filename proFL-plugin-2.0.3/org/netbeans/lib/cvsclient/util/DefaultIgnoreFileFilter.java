// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.File;
import java.util.List;

public class DefaultIgnoreFileFilter implements IgnoreFileFilter
{
    private final List patterns;
    private final List localPatterns;
    private boolean processGlobalPatterns;
    private boolean processLocalPatterns;
    private File lastDirectory;
    
    public DefaultIgnoreFileFilter() {
        this.patterns = new LinkedList();
        this.localPatterns = new LinkedList();
        this.processGlobalPatterns = true;
        this.processLocalPatterns = false;
        this.lastDirectory = null;
    }
    
    public DefaultIgnoreFileFilter(final List list) {
        this.patterns = new LinkedList();
        this.localPatterns = new LinkedList();
        this.processGlobalPatterns = true;
        this.processLocalPatterns = false;
        this.lastDirectory = null;
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.addPattern(new SimpleStringPattern(iterator.next().toString()));
        }
    }
    
    public void addPattern(final StringPattern stringPattern) {
        if (stringPattern.toString().equals("!")) {
            this.clearPatterns();
        }
        else {
            this.patterns.add(stringPattern);
        }
    }
    
    public void addPattern(final String s) {
        if (s.equals("!")) {
            this.clearPatterns();
        }
        else {
            this.patterns.add(new SimpleStringPattern(s));
        }
    }
    
    public void clearPatterns() {
        this.patterns.clear();
    }
    
    public boolean shouldBeIgnored(final File lastDirectory, final String s) {
        if (this.lastDirectory != lastDirectory) {
            this.lastDirectory = lastDirectory;
            this.processGlobalPatterns = true;
            this.processLocalPatterns = false;
            this.localPatterns.clear();
            final File file = new File(lastDirectory.getPath() + File.separator + ".cvsignore");
            if (file.exists()) {
                try {
                    final Iterator iterator = parseCvsIgnoreFile(file).iterator();
                    while (iterator.hasNext()) {
                        final String string = iterator.next().toString();
                        if (string.equals("!")) {
                            this.processGlobalPatterns = false;
                            this.localPatterns.clear();
                        }
                        else {
                            this.localPatterns.add(new SimpleStringPattern(string));
                        }
                    }
                }
                catch (IOException ex) {}
            }
            this.processLocalPatterns = (this.localPatterns.size() > 0);
        }
        if (this.processGlobalPatterns) {
            final Iterator<StringPattern> iterator2 = this.patterns.iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next().doesMatch(s)) {
                    return true;
                }
            }
        }
        if (this.processLocalPatterns) {
            final Iterator<StringPattern> iterator3 = this.localPatterns.iterator();
            while (iterator3.hasNext()) {
                if (iterator3.next().doesMatch(s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List parseCvsIgnoreFile(final File file) throws IOException, FileNotFoundException {
        BufferedReader bufferedReader = null;
        final LinkedList<String> list = new LinkedList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, " ", false);
                while (stringTokenizer.hasMoreTokens()) {
                    list.add(stringTokenizer.nextToken());
                }
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return list;
    }
}
