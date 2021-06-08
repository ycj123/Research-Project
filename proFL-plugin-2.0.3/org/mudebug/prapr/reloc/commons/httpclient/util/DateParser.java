// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.util;

import java.text.ParseException;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class DateParser
{
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    private static final String[] DATE_PATTERNS;
    
    public static Date parseDate(final String dateValue) throws DateParseException {
        return parseDate(dateValue, DateParser.DATE_PATTERNS);
    }
    
    private static Date parseDate(final String dateValue, final String[] dateFormats) throws DateParseException {
        if (dateValue == null) {
            throw new IllegalArgumentException("dateValue is null");
        }
        SimpleDateFormat dateParser = null;
        int i = 0;
        while (i < dateFormats.length) {
            if (dateParser == null) {
                dateParser = new SimpleDateFormat(dateFormats[i], Locale.US);
                dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
            }
            else {
                dateParser.applyPattern(dateFormats[i]);
            }
            try {
                return dateParser.parse(dateValue);
            }
            catch (ParseException pe) {
                ++i;
            }
        }
        throw new DateParseException("Unable to parse the date " + dateValue);
    }
    
    private DateParser() {
    }
    
    static {
        DATE_PATTERNS = new String[] { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z" };
    }
}
