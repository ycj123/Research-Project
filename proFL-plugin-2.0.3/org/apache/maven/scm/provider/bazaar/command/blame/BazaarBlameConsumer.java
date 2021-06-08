// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.blame;

import java.util.Date;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;

public class BazaarBlameConsumer extends BazaarConsumer
{
    private static final String BAZAAR_TIMESTAMP_PATTERN = "yyyyMMdd";
    private List<BlameLine> lines;
    
    public BazaarBlameConsumer(final ScmLogger logger) {
        super(logger);
        this.lines = new ArrayList<BlameLine>();
    }
    
    @Override
    public void doConsume(final ScmFileStatus status, final String trimmedLine) {
        String annotation = trimmedLine.substring(0, trimmedLine.indexOf(124)).trim();
        final String dateStr = annotation.substring(annotation.lastIndexOf(32) + 1);
        annotation = annotation.substring(0, annotation.lastIndexOf(32));
        final String author = annotation.substring(annotation.lastIndexOf(32) + 1);
        annotation = annotation.substring(0, annotation.lastIndexOf(32));
        final String revision = annotation.trim();
        final Date date = this.parseDate(dateStr, null, "yyyyMMdd");
        this.lines.add(new BlameLine(date, revision, author));
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
}
