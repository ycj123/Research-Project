// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

public class CategorizedReportEntry extends SimpleReportEntry implements ReportEntry
{
    private static final String GROUP_PREFIX = " (of ";
    private static final String GROUP_SUFIX = ")";
    private final String group;
    
    public CategorizedReportEntry(final String source, final String name, final String group) {
        this(source, name, group, null, null);
    }
    
    public CategorizedReportEntry(final String source, final String name, final String group, final StackTraceWriter stackTraceWriter, final Integer elapsed) {
        super(source, name, stackTraceWriter, elapsed);
        this.group = group;
    }
    
    public CategorizedReportEntry(final String source, final String name, final String group, final StackTraceWriter stackTraceWriter, final Integer elapsed, final String message) {
        super(source, name, stackTraceWriter, elapsed, message);
        this.group = group;
    }
    
    public static ReportEntry reportEntry(final String source, final String name, final String group, final StackTraceWriter stackTraceWriter, final Integer elapsed, final String message) {
        return (group != null) ? new CategorizedReportEntry(source, name, group, stackTraceWriter, elapsed, message) : new SimpleReportEntry(source, name, stackTraceWriter, elapsed, message);
    }
    
    @Override
    public String getGroup() {
        return this.group;
    }
    
    @Override
    public String getNameWithGroup() {
        final StringBuilder result = new StringBuilder();
        result.append(this.getName());
        if (this.getGroup() != null && !this.getName().equals(this.getGroup())) {
            result.append(" (of ");
            result.append(this.getGroup());
            result.append(")");
        }
        return result.toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final CategorizedReportEntry that = (CategorizedReportEntry)o;
        if (this.group != null) {
            if (!this.group.equals(that.group)) {
                return false;
            }
        }
        else if (that.group != null) {
            return false;
        }
        return true;
        b = false;
        return b;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ((this.group != null) ? this.group.hashCode() : 0);
        return result;
    }
}
