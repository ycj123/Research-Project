// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.unedit;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.perforce.command.AbstractPerforceConsumer;

public class PerforceUnEditConsumer extends AbstractPerforceConsumer implements StreamConsumer
{
    private static final Pattern REVISION_PATTERN;
    private static final int STATE_NORMAL = 1;
    private static final int STATE_ERROR = 2;
    private int currentState;
    private List<ScmFile> edits;
    
    public PerforceUnEditConsumer() {
        this.currentState = 1;
        this.edits = new ArrayList<ScmFile>();
    }
    
    public List<ScmFile> getEdits() {
        return this.edits;
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = PerforceUnEditConsumer.REVISION_PATTERN.matcher(line);
        if (this.currentState != 2 && matcher.matches()) {
            this.edits.add(new ScmFile(matcher.group(1), ScmFileStatus.UNKNOWN));
            return;
        }
        this.error(line);
    }
    
    private void error(final String line) {
        this.currentState = 2;
        this.output.println(line);
    }
    
    public boolean isSuccess() {
        return this.currentState == 1;
    }
    
    static {
        REVISION_PATTERN = Pattern.compile("^([^#]+)#\\d+ - (.*)");
    }
}
