// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.blame;

import java.util.regex.Matcher;
import java.util.HashMap;
import org.apache.maven.scm.log.ScmLogger;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class PerforceFilelogConsumer extends AbstractConsumer
{
    private static final String PERFORCE_TIMESTAMP_PATTERN = "yyyy/MM/dd";
    private static final Pattern LINE_PATTERN;
    private Map<String, Date> dates;
    private Map<String, String> authors;
    
    public PerforceFilelogConsumer(final ScmLogger logger) {
        super(logger);
        this.dates = new HashMap<String, Date>();
        this.authors = new HashMap<String, String>();
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = PerforceFilelogConsumer.LINE_PATTERN.matcher(line);
        if (matcher.find()) {
            final String revision = matcher.group(1);
            final String dateTimeStr = matcher.group(2);
            final String author = matcher.group(3);
            final Date dateTime = this.parseDate(dateTimeStr, null, "yyyy/MM/dd");
            this.dates.put(revision, dateTime);
            this.authors.put(revision, author);
        }
    }
    
    public String getAuthor(final String revision) {
        return this.authors.get(revision);
    }
    
    public Date getDate(final String revision) {
        return this.dates.get(revision);
    }
    
    static {
        LINE_PATTERN = Pattern.compile("#(\\d+).*on (.*) by (.*)@");
    }
}
