// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

public class SimpleReportEntry implements ReportEntry
{
    private final String source;
    private final String name;
    private final StackTraceWriter stackTraceWriter;
    private final Integer elapsed;
    private final String message;
    
    public SimpleReportEntry(final String source, final String name) {
        this(source, name, null, null);
    }
    
    private SimpleReportEntry(final String source, final String name, final StackTraceWriter stackTraceWriter) {
        this(source, name, stackTraceWriter, null);
    }
    
    public SimpleReportEntry(final String source, final String name, final Integer elapsed) {
        this(source, name, null, elapsed);
    }
    
    protected SimpleReportEntry(String source, String name, final StackTraceWriter stackTraceWriter, final Integer elapsed, final String message) {
        if (source == null) {
            source = "null";
        }
        if (name == null) {
            name = "null";
        }
        this.source = source;
        this.name = name;
        this.stackTraceWriter = stackTraceWriter;
        this.message = message;
        this.elapsed = elapsed;
    }
    
    public SimpleReportEntry(final String source, final String name, final StackTraceWriter stackTraceWriter, final Integer elapsed) {
        this(source, name, stackTraceWriter, elapsed, safeGetMessage(stackTraceWriter));
    }
    
    public static SimpleReportEntry ignored(final String source, final String name, final String message) {
        return new SimpleReportEntry(source, name, null, null, message);
    }
    
    public static SimpleReportEntry withException(final String source, final String name, final StackTraceWriter stackTraceWriter) {
        return new SimpleReportEntry(source, name, stackTraceWriter);
    }
    
    private static String safeGetMessage(final StackTraceWriter stackTraceWriter) {
        try {
            return (stackTraceWriter != null && stackTraceWriter.getThrowable() != null) ? stackTraceWriter.getThrowable().getMessage() : null;
        }
        catch (Throwable t) {
            return t.getMessage();
        }
    }
    
    public String getSourceName() {
        return this.source;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getGroup() {
        return null;
    }
    
    public StackTraceWriter getStackTraceWriter() {
        return this.stackTraceWriter;
    }
    
    public Integer getElapsed() {
        return this.elapsed;
    }
    
    @Override
    public String toString() {
        return "ReportEntry{source='" + this.source + '\'' + ", name='" + this.name + '\'' + ", stackTraceWriter=" + this.stackTraceWriter + ", elapsed=" + this.elapsed + ",message=" + this.message + '}';
    }
    
    public String getMessage() {
        return this.message;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final SimpleReportEntry that = (SimpleReportEntry)o;
        Label_0062: {
            if (this.elapsed != null) {
                if (this.elapsed.equals(that.elapsed)) {
                    break Label_0062;
                }
            }
            else if (that.elapsed == null) {
                break Label_0062;
            }
            return false;
        }
        Label_0095: {
            if (this.name != null) {
                if (this.name.equals(that.name)) {
                    break Label_0095;
                }
            }
            else if (that.name == null) {
                break Label_0095;
            }
            return false;
        }
        Label_0128: {
            if (this.source != null) {
                if (this.source.equals(that.source)) {
                    break Label_0128;
                }
            }
            else if (that.source == null) {
                break Label_0128;
            }
            return false;
        }
        if (this.stackTraceWriter != null) {
            if (this.stackTraceWriter.equals(that.stackTraceWriter)) {
                return true;
            }
        }
        else if (that.stackTraceWriter == null) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int result = (this.source != null) ? this.source.hashCode() : 0;
        result = 31 * result + ((this.name != null) ? this.name.hashCode() : 0);
        result = 31 * result + ((this.stackTraceWriter != null) ? this.stackTraceWriter.hashCode() : 0);
        result = 31 * result + ((this.elapsed != null) ? this.elapsed.hashCode() : 0);
        return result;
    }
    
    public String getNameWithGroup() {
        return this.getName();
    }
}
