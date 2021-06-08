// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.util;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.codehaus.plexus.util.StringUtils;
import java.util.Locale;
import java.util.Date;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public abstract class AbstractConsumer implements StreamConsumer
{
    private ScmLogger logger;
    
    public AbstractConsumer(final ScmLogger logger) {
        this.setLogger(logger);
    }
    
    public ScmLogger getLogger() {
        return this.logger;
    }
    
    public void setLogger(final ScmLogger logger) {
        this.logger = logger;
    }
    
    protected Date parseDate(final String date, final String userPattern, final String defaultPattern) {
        return this.parseDate(date, userPattern, defaultPattern, null);
    }
    
    protected Date parseDate(final String date, final String userPattern, final String defaultPattern, final Locale locale) {
        String patternUsed = null;
        DateFormat format;
        if (StringUtils.isNotEmpty(userPattern)) {
            format = new SimpleDateFormat(userPattern);
            patternUsed = userPattern;
        }
        else if (StringUtils.isNotEmpty(defaultPattern)) {
            if (locale != null) {
                format = new SimpleDateFormat(defaultPattern, locale);
            }
            else {
                format = new SimpleDateFormat(defaultPattern);
            }
            patternUsed = defaultPattern;
        }
        else {
            format = DateFormat.getDateInstance(3, Locale.ENGLISH);
            patternUsed = " DateFormat.SHORT ";
        }
        try {
            return format.parse(date);
        }
        catch (ParseException e) {
            if (this.getLogger() != null && this.getLogger().isWarnEnabled()) {
                this.getLogger().warn("skip ParseException: " + e.getMessage() + " during parsing date " + date + " with pattern " + patternUsed + " with Locale " + ((locale == null) ? Locale.ENGLISH : locale), e);
            }
            return null;
        }
    }
}
