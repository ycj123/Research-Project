// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.tag;

import java.util.regex.Matcher;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.perforce.command.AbstractPerforceConsumer;

public class PerforceTagConsumer extends AbstractPerforceConsumer implements StreamConsumer
{
    private static final Pattern LABEL_PATTERN;
    private static final Pattern SYNC_PATTERN;
    public static final int STATE_CREATE = 1;
    public static final int STATE_SYNC = 2;
    public static final int STATE_ERROR = 3;
    private int currentState;
    private List<ScmFile> tagged;
    
    public PerforceTagConsumer() {
        this.currentState = 1;
        this.tagged = new ArrayList<ScmFile>();
    }
    
    public List<ScmFile> getTagged() {
        return this.tagged;
    }
    
    public void consumeLine(final String line) {
        switch (this.currentState) {
            case 1: {
                if (!PerforceTagConsumer.LABEL_PATTERN.matcher(line).matches()) {
                    this.error(line);
                    break;
                }
                this.currentState = 2;
                break;
            }
            case 2: {
                final Matcher matcher = PerforceTagConsumer.SYNC_PATTERN.matcher(line);
                if (!matcher.matches()) {
                    this.error(line);
                    break;
                }
                this.tagged.add(new ScmFile(matcher.group(1), ScmFileStatus.TAGGED));
                break;
            }
            default: {
                this.error(line);
                break;
            }
        }
    }
    
    private void error(final String line) {
        this.currentState = 3;
        this.output.println(line);
    }
    
    public boolean isSuccess() {
        return this.currentState == 2;
    }
    
    static {
        LABEL_PATTERN = Pattern.compile("^Label ([^ ]+) saved.$");
        SYNC_PATTERN = Pattern.compile("^([^#]+)#\\d+ - (.*)");
    }
}
