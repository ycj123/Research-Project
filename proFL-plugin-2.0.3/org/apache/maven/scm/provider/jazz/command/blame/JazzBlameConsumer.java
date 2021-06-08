// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.blame;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.TimeZone;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import java.text.SimpleDateFormat;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.provider.jazz.command.consumer.AbstractRepositoryConsumer;

public class JazzBlameConsumer extends AbstractRepositoryConsumer
{
    private static final String JAZZ_TIMESTAMP_PATTERN = "yyyy-MM-dd";
    private static final Pattern LINE_PATTERN;
    private List<BlameLine> fLines;
    private SimpleDateFormat dateFormat;
    
    public JazzBlameConsumer(final ScmProviderRepository repository, final ScmLogger logger) {
        super(repository, logger);
        this.fLines = new ArrayList<BlameLine>();
        (this.dateFormat = new SimpleDateFormat("yyyy-MM-dd")).setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    @Override
    public void consumeLine(final String line) {
        super.consumeLine(line);
        final Matcher matcher = JazzBlameConsumer.LINE_PATTERN.matcher(line);
        if (matcher.matches()) {
            final String lineNumberStr = matcher.group(1);
            final String owner = matcher.group(2);
            final String changeSetNumberStr = matcher.group(3);
            final String dateStr = matcher.group(4);
            final Date date = this.parseDate(dateStr, "yyyy-MM-dd", null);
            this.fLines.add(new BlameLine(date, changeSetNumberStr, owner));
        }
    }
    
    public List<BlameLine> getLines() {
        return this.fLines;
    }
    
    static {
        LINE_PATTERN = Pattern.compile("(\\d+) (.*) \\((\\d+)\\) (\\d+-\\d+-\\d+) (.*)");
    }
}
