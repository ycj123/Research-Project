// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro.snippet;

import java.util.ArrayList;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.net.URL;

public class SnippetReader
{
    private static final String EOL;
    private URL source;
    
    public SnippetReader(final URL src) {
        this.source = src;
    }
    
    public StringBuffer readSnippet(final String snippetId) throws IOException {
        final List lines = this.readLines(snippetId);
        final int minIndent = this.minIndent(lines);
        final StringBuffer result = new StringBuffer();
        for (final String line : lines) {
            result.append(line.substring(minIndent));
            result.append(SnippetReader.EOL);
        }
        return result;
    }
    
    int minIndent(final List lines) {
        int minIndent = Integer.MAX_VALUE;
        for (final String line : lines) {
            minIndent = Math.min(minIndent, this.indent(line));
        }
        return minIndent;
    }
    
    int indent(final String line) {
        char[] chars;
        int indent;
        for (chars = line.toCharArray(), indent = 0; indent < chars.length && chars[indent] == ' '; ++indent) {}
        return indent;
    }
    
    private List readLines(final String snippetId) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(this.source.openStream()));
        final List lines = new ArrayList();
        try {
            boolean capture = false;
            String line;
            while ((line = reader.readLine()) != null) {
                if (this.isStart(snippetId, line)) {
                    capture = true;
                }
                else {
                    if (this.isEnd(snippetId, line)) {
                        break;
                    }
                    if (!capture) {
                        continue;
                    }
                    lines.add(line);
                }
            }
        }
        finally {
            reader.close();
        }
        return lines;
    }
    
    protected boolean isStart(final String snippetId, final String line) {
        return this.isDemarcator(snippetId, "START", line);
    }
    
    protected boolean isDemarcator(final String snippetId, final String what, final String line) {
        final String upper = line.toUpperCase();
        return upper.indexOf(what.toUpperCase()) != -1 && upper.indexOf("SNIPPET") != -1 && line.indexOf(snippetId) != -1;
    }
    
    protected boolean isEnd(final String snippetId, final String line) {
        return this.isDemarcator(snippetId, "END", line);
    }
    
    static {
        EOL = System.getProperty("line.separator");
    }
}
