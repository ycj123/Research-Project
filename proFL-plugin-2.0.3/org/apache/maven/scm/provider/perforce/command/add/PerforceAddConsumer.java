// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.add;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class PerforceAddConsumer implements StreamConsumer
{
    private static final Pattern PATTERN;
    private static final String FILE_BEGIN_TOKEN = "//";
    private List<ScmFile> additions;
    
    public PerforceAddConsumer() {
        this.additions = new ArrayList<ScmFile>();
    }
    
    public List<ScmFile> getAdditions() {
        return this.additions;
    }
    
    public void consumeLine(final String line) {
        if (line.startsWith("... ")) {
            return;
        }
        if (!line.startsWith("//")) {
            throw new IllegalStateException("Unknown error: " + line);
        }
        final Matcher matcher = PerforceAddConsumer.PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new IllegalStateException("Unknown input: " + line);
        }
        this.additions.add(new ScmFile(matcher.group(1), ScmFileStatus.ADDED));
    }
    
    static {
        PATTERN = Pattern.compile("^([^#]+)#(\\d+) - (.*)");
    }
}
