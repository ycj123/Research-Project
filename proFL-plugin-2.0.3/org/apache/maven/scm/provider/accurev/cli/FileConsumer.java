// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.regex.Matcher;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

class FileConsumer implements StreamConsumer
{
    private Pattern filePattern;
    private List<File> matchedFiles;
    public static final Pattern ADD_PATTERN;
    public static final Pattern UPDATE_PATTERN;
    public static final Pattern POPULATE_PATTERN;
    public static final Pattern PROMOTE_PATTERN;
    public static final Pattern STAT_PATTERN;
    public static final Pattern DEFUNCT_PATTERN;
    
    public FileConsumer(final List<File> matchedFilesAccumulator, final Pattern filematcher) {
        this.matchedFiles = matchedFilesAccumulator;
        this.filePattern = filematcher;
    }
    
    public void consumeLine(final String line) {
        final Matcher m = this.filePattern.matcher(line);
        if (m.matches()) {
            int i;
            String fileName;
            for (i = 1, fileName = null; fileName == null && i <= m.groupCount(); fileName = m.group(i++)) {}
            if (fileName != null) {
                this.matchedFiles.add(new File(fileName));
            }
        }
    }
    
    static {
        ADD_PATTERN = Pattern.compile("Added and kept element [/\\\\]\\.[/\\\\](\\S+)\\s*");
        UPDATE_PATTERN = Pattern.compile("Updating element [/\\\\]\\.[/\\\\](\\S+)\\s*|Content.*of \"(.*)\".*");
        POPULATE_PATTERN = Pattern.compile("Populating element [/\\\\]\\.[/\\\\](\\S+)\\s*");
        PROMOTE_PATTERN = Pattern.compile("Promoted element [/\\\\]\\.[/\\\\](\\S+)\\s*");
        STAT_PATTERN = Pattern.compile("[/\\\\]\\.[/\\\\](.*)");
        DEFUNCT_PATTERN = Pattern.compile("Removing \"(\\S+)\".*");
    }
}
