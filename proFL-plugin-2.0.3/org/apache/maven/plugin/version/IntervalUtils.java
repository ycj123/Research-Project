// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.version;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public final class IntervalUtils
{
    private static final String PERIOD_PART_PATTERN = "[0-9]+[WwDdHhMm]?";
    private static final Map PART_TYPE_CONTRIBUTIONS;
    
    private IntervalUtils() {
    }
    
    public static boolean isExpired(final String intervalSpec, final Date lastChecked) {
        if ("never".equalsIgnoreCase(intervalSpec)) {
            return false;
        }
        if ("always".equalsIgnoreCase(intervalSpec)) {
            return true;
        }
        if (intervalSpec != null && intervalSpec.toLowerCase().startsWith("interval:") && intervalSpec.length() > "interval:".length()) {
            final String intervalPart = intervalSpec.substring("interval:".length());
            final long period = parseInterval(intervalPart);
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis() - period);
            final Date test = cal.getTime();
            return lastChecked == null || test.after(lastChecked);
        }
        throw new IllegalArgumentException("Invalid interval specification: '" + intervalSpec + "'");
    }
    
    public static long parseInterval(final String interval) {
        final Matcher partMatcher = Pattern.compile("[0-9]+[WwDdHhMm]?").matcher(interval);
        long period = 0L;
        while (partMatcher.find()) {
            final String part = partMatcher.group();
            period += getPartPeriod(part);
        }
        return period;
    }
    
    private static long getPartPeriod(final String part) {
        char type = part.charAt(part.length() - 1);
        String coefficientPart;
        if (Character.isLetter(type)) {
            coefficientPart = part.substring(0, part.length() - 1);
        }
        else {
            coefficientPart = part;
            type = 'm';
        }
        final int coefficient = Integer.parseInt(coefficientPart);
        final Long period = IntervalUtils.PART_TYPE_CONTRIBUTIONS.get("" + Character.toLowerCase(type));
        long result = 0L;
        if (period != null) {
            result = coefficient * period;
        }
        return result;
    }
    
    static {
        final Map contributions = new HashMap();
        contributions.put("w", new Long(604800000L));
        contributions.put("d", new Long(86400000L));
        contributions.put("h", new Long(3600000L));
        contributions.put("m", new Long(60000L));
        PART_TYPE_CONTRIBUTIONS = contributions;
    }
}
