// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command.consumer;

import java.text.DateFormat;
import java.util.TimeZone;
import java.util.Locale;
import java.util.Date;
import java.util.regex.Matcher;
import java.text.ParseException;
import org.apache.maven.scm.ChangeFile;
import java.util.regex.Pattern;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import org.apache.maven.scm.util.AbstractConsumer;

public class TfsChangeLogConsumer extends AbstractConsumer
{
    private static final String PATTERN = "^[^:]*:[ \t]([0-9]*)\n[^:]*:[ \t](.*)\n[^:]*:[ \t](.*)\n[^:]*:((?:\n.*)*)\n\n[^\n :]*:(?=\n  )((?:\n[ \t]+.*)*)";
    private static final String PATTERN_ITEM = "\n  ([^$]+) (\\$/.*)";
    private List<ChangeSet> logs;
    private String buffer;
    boolean fed;
    
    public TfsChangeLogConsumer(final ScmLogger logger) {
        super(logger);
        this.logs = new ArrayList<ChangeSet>();
        this.buffer = "";
        this.fed = false;
    }
    
    public void consumeLine(final String line) {
        this.fed = true;
        if (line.startsWith("-----")) {
            this.addChangeLog();
        }
        this.buffer = this.buffer + line + "\n";
    }
    
    public List<ChangeSet> getLogs() {
        this.addChangeLog();
        return this.logs;
    }
    
    private void addChangeLog() {
        if (!this.buffer.equals("")) {
            final Pattern p = Pattern.compile("^[^:]*:[ \t]([0-9]*)\n[^:]*:[ \t](.*)\n[^:]*:[ \t](.*)\n[^:]*:((?:\n.*)*)\n\n[^\n :]*:(?=\n  )((?:\n[ \t]+.*)*)");
            final Matcher m = p.matcher(this.buffer);
            if (m.find()) {
                final String revision = m.group(1).trim();
                final String username = m.group(2).trim();
                final String dateString = m.group(3).trim();
                final String comment = m.group(4).trim();
                final Pattern itemPattern = Pattern.compile("\n  ([^$]+) (\\$/.*)");
                final Matcher itemMatcher = itemPattern.matcher(m.group(5));
                final List<ChangeFile> files = new ArrayList<ChangeFile>();
                while (itemMatcher.find()) {
                    final ChangeFile file = new ChangeFile(itemMatcher.group(2).trim(), revision);
                    files.add(file);
                }
                Date date;
                try {
                    date = parseDate(dateString);
                }
                catch (ParseException e) {
                    this.getLogger().error("Date parse error", e);
                    throw new RuntimeException(e);
                }
                final ChangeSet change = new ChangeSet(date, comment, username, files);
                this.logs.add(change);
            }
            this.buffer = "";
        }
    }
    
    public boolean hasBeenFed() {
        return this.fed;
    }
    
    protected static Date parseDate(final String dateString) throws ParseException {
        Date date = null;
        try {
            date = new Date(Date.parse(dateString));
        }
        catch (IllegalArgumentException ex) {}
        if (date == null) {
            final DateFormat[] formats = createDateFormatsForLocaleAndTimeZone(null, null);
            return parseWithFormats(dateString, formats);
        }
        return date;
    }
    
    private static Date parseWithFormats(final String input, final DateFormat[] formats) throws ParseException {
        ParseException parseException = null;
        int i = 0;
        while (i < formats.length) {
            try {
                return formats[i].parse(input);
            }
            catch (ParseException ex) {
                parseException = ex;
                ++i;
                continue;
            }
            break;
        }
        throw parseException;
    }
    
    private static DateFormat[] createDateFormatsForLocaleAndTimeZone(Locale locale, TimeZone timeZone) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        final List<DateFormat> formats = new ArrayList<DateFormat>();
        for (int dateStyle = 0; dateStyle <= 3; ++dateStyle) {
            for (int timeStyle = 0; timeStyle <= 3; ++timeStyle) {
                final DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
                if (timeZone != null) {
                    df.setTimeZone(timeZone);
                }
                formats.add(df);
            }
        }
        for (int dateStyle = 0; dateStyle <= 3; ++dateStyle) {
            final DateFormat df2 = DateFormat.getDateInstance(dateStyle, locale);
            df2.setTimeZone(timeZone);
            formats.add(df2);
        }
        return formats.toArray(new DateFormat[formats.size()]);
    }
}
