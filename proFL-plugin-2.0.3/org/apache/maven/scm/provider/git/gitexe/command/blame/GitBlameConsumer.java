// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.blame;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import java.util.Date;
import java.util.Map;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import org.apache.maven.scm.util.AbstractConsumer;

public class GitBlameConsumer extends AbstractConsumer
{
    private static final String GIT_COMMITTER_PREFIX = "committer";
    private static final String GIT_COMMITTER = "committer ";
    private static final String GIT_COMMITTER_TIME = "committer-time ";
    private static final String GIT_AUTHOR = "author ";
    private List<BlameLine> lines;
    private Map<String, BlameLine> commitInfo;
    private boolean expectRevisionLine;
    private String revision;
    private String author;
    private String committer;
    private Date time;
    
    public GitBlameConsumer(final ScmLogger logger) {
        super(logger);
        this.lines = new ArrayList<BlameLine>();
        this.commitInfo = new HashMap<String, BlameLine>();
        this.expectRevisionLine = true;
        this.revision = null;
        this.author = null;
        this.committer = null;
        this.time = null;
    }
    
    public void consumeLine(final String line) {
        if (line == null) {
            return;
        }
        if (this.expectRevisionLine) {
            final String[] parts = line.split("\\s", 4);
            if (parts.length >= 1) {
                this.revision = parts[0];
                final BlameLine oldLine = this.commitInfo.get(this.revision);
                if (oldLine != null) {
                    this.author = oldLine.getAuthor();
                    this.committer = oldLine.getCommitter();
                    this.time = oldLine.getDate();
                }
                this.expectRevisionLine = false;
            }
        }
        else {
            if (line.startsWith("author ")) {
                this.author = line.substring("author ".length());
                return;
            }
            if (line.startsWith("committer ")) {
                this.committer = line.substring("committer ".length());
                return;
            }
            if (line.startsWith("committer-time ")) {
                final String timeStr = line.substring("committer-time ".length());
                this.time = new Date(Long.parseLong(timeStr) * 1000L);
                return;
            }
            if (line.startsWith("\t")) {
                final BlameLine blameLine = new BlameLine(this.time, this.revision, this.author, this.committer);
                this.getLines().add(blameLine);
                this.commitInfo.put(this.revision, blameLine);
                if (this.getLogger().isDebugEnabled()) {
                    final DateFormat df = DateFormat.getDateTimeInstance();
                    this.getLogger().debug(this.author + " " + df.format(this.time));
                }
                this.expectRevisionLine = true;
            }
        }
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
}
