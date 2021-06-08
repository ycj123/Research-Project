// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

import org.apache.maven.surefire.util.internal.StringUtils;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LegacyPojoStackTraceWriter implements StackTraceWriter
{
    private final Throwable t;
    private final String testClass;
    private final String testMethod;
    
    public LegacyPojoStackTraceWriter(final String testClass, final String testMethod, final Throwable t) {
        this.testClass = testClass;
        this.testMethod = testMethod;
        this.t = t;
    }
    
    public String writeTraceToString() {
        final StringWriter w = new StringWriter();
        if (this.t != null) {
            this.t.printStackTrace(new PrintWriter(w));
            w.flush();
        }
        return w.toString();
    }
    
    public String smartTrimmedStackTrace() {
        final StringBuilder result = new StringBuilder();
        result.append(this.testClass);
        result.append("#");
        result.append(this.testMethod);
        final SafeThrowable throwable = this.getThrowable();
        if (throwable.getTarget() instanceof AssertionError) {
            result.append(" ");
            result.append(getTruncatedMessage(throwable.getMessage(), 77 - result.length()));
        }
        else {
            final Throwable target = throwable.getTarget();
            if (target != null) {
                result.append(" ");
                result.append(target.getClass().getSimpleName());
                result.append(getTruncatedMessage(throwable.getMessage(), 77 - result.length()));
            }
        }
        return result.toString();
    }
    
    private static String getTruncatedMessage(final String msg, final int i) {
        if (i < 0) {
            return "";
        }
        if (msg == null) {
            return "";
        }
        final String substring = msg.substring(0, Math.min(i, msg.length()));
        if (i < msg.length()) {
            return " " + substring + "...";
        }
        return " " + substring;
    }
    
    public String writeTrimmedTraceToString() {
        final String text = this.writeTraceToString();
        final String marker = "at " + this.testClass + "." + this.testMethod;
        final String[] lines = StringUtils.split(text, "\n");
        int lastLine = lines.length - 1;
        int causedByLine = -1;
        for (int i = 1; i < lines.length; ++i) {
            final String line = lines[i].trim();
            if (line.startsWith(marker)) {
                lastLine = i;
            }
            else if (line.startsWith("Caused by")) {
                causedByLine = i;
                break;
            }
        }
        final StringBuilder trace = new StringBuilder();
        for (int j = 0; j <= lastLine; ++j) {
            trace.append(lines[j]);
            trace.append("\n");
        }
        if (causedByLine != -1) {
            for (int j = causedByLine; j < lines.length; ++j) {
                trace.append(lines[j]);
                trace.append("\n");
            }
        }
        return trace.toString();
    }
    
    public SafeThrowable getThrowable() {
        return new SafeThrowable(this.t);
    }
}
