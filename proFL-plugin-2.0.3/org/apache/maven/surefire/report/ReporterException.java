// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

public class ReporterException extends RuntimeException
{
    public ReporterException(final String message, final Exception nested) {
        super(message, nested);
    }
}
