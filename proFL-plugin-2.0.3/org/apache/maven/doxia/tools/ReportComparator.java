// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.tools;

import java.text.Collator;
import org.apache.maven.reporting.MavenReport;
import java.util.Locale;
import java.util.Comparator;

public class ReportComparator implements Comparator
{
    private final Locale locale;
    
    public ReportComparator(final Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("locale should be defined");
        }
        this.locale = locale;
    }
    
    public int compare(final Object o1, final Object o2) {
        final MavenReport r1 = (MavenReport)o1;
        final MavenReport r2 = (MavenReport)o2;
        final Collator collator = Collator.getInstance(this.locale);
        return collator.compare(r1.getName(this.locale), r2.getName(this.locale));
    }
}
