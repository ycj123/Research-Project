// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.remove;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.perforce.command.AbstractPerforceConsumer;

public class PerforceRemoveConsumer extends AbstractPerforceConsumer implements StreamConsumer
{
    private static final String FILE_BEGIN_TOKEN = "//";
    private static final Pattern REVISION_PATTERN;
    private List<ScmFile> removals;
    private boolean error;
    
    public PerforceRemoveConsumer() {
        this.removals = new ArrayList<ScmFile>();
        this.error = false;
    }
    
    public List<ScmFile> getRemovals() {
        return this.removals;
    }
    
    public void consumeLine(final String line) {
        if (line.startsWith("... ")) {
            return;
        }
        if (!line.startsWith("//")) {
            this.error(line);
        }
        final Matcher matcher = PerforceRemoveConsumer.REVISION_PATTERN.matcher(line);
        if (!matcher.matches()) {
            this.error(line);
        }
        this.removals.add(new ScmFile(matcher.group(1), ScmFileStatus.DELETED));
    }
    
    private void error(final String line) {
        this.error = true;
        this.output.println(line);
    }
    
    public boolean isSuccess() {
        return !this.error;
    }
    
    static {
        REVISION_PATTERN = Pattern.compile("^([^#]+)#\\d+ - (.*)");
    }
}
