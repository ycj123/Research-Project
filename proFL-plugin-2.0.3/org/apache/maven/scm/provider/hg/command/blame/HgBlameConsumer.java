// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.blame;

import java.util.Date;
import java.util.Locale;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import org.apache.maven.scm.provider.hg.command.HgConsumer;

public class HgBlameConsumer extends HgConsumer
{
    private List<BlameLine> lines;
    private static final String HG_TIMESTAMP_PATTERN = "EEE MMM dd HH:mm:ss yyyy Z";
    
    public HgBlameConsumer(final ScmLogger logger) {
        super(logger);
        this.lines = new ArrayList<BlameLine>();
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
        String annotation;
        if (trimmedLine.indexOf(": ") > -1) {
            annotation = trimmedLine.substring(0, trimmedLine.indexOf(": ")).trim();
        }
        else {
            annotation = trimmedLine.substring(0, trimmedLine.lastIndexOf(":")).trim();
        }
        final String author = annotation.substring(0, annotation.indexOf(32));
        annotation = annotation.substring(annotation.indexOf(32) + 1).trim();
        final String revision = annotation.substring(0, annotation.indexOf(32));
        final String dateStr;
        annotation = (dateStr = annotation.substring(annotation.indexOf(32) + 1).trim());
        final Date dateTime = this.parseDate(dateStr, null, "EEE MMM dd HH:mm:ss yyyy Z", Locale.ENGLISH);
        this.lines.add(new BlameLine(dateTime, revision, author));
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
}
