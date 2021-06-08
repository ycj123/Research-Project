// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report.generator;

import org.mudebug.prapr.reloc.commons.lang.builder.ToStringBuilder;
import org.mudebug.prapr.reloc.commons.lang.builder.ToStringStyle;
import java.util.List;
import org.apache.maven.plugin.logging.Log;
import java.io.File;
import org.apache.maven.doxia.sink.Sink;
import java.util.Locale;

public class ReportGenerationContext
{
    private Locale locale;
    private Sink sink;
    private File reportsDataDirectory;
    private File siteDirectory;
    private Log logger;
    private List<String> sourceDataFormats;
    
    public ReportGenerationContext() {
    }
    
    public ReportGenerationContext(final Locale locale, final Sink sink, final File reportsDataDirectory, final File siteDirectory, final Log logger, final List<String> sourceDataFormats) {
        this.locale = locale;
        this.sink = sink;
        this.reportsDataDirectory = reportsDataDirectory;
        this.siteDirectory = siteDirectory;
        this.logger = logger;
        this.sourceDataFormats = sourceDataFormats;
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public void setLocale(final Locale locale) {
        this.locale = locale;
    }
    
    public Sink getSink() {
        return this.sink;
    }
    
    public void setSink(final Sink sink) {
        this.sink = sink;
    }
    
    public File getReportsDataDirectory() {
        return this.reportsDataDirectory;
    }
    
    public void setReportsDataDirectory(final File reportsDataDirectory) {
        this.reportsDataDirectory = reportsDataDirectory;
    }
    
    public File getSiteDirectory() {
        return this.siteDirectory;
    }
    
    public void setSiteDirectory(final File siteDirectory) {
        this.siteDirectory = siteDirectory;
    }
    
    public Log getLogger() {
        return this.logger;
    }
    
    public void setLogger(final Log logger) {
        this.logger = logger;
    }
    
    public List<String> getSourceDataFormats() {
        return this.sourceDataFormats;
    }
    
    public void setSourceDataFormats(final List<String> sourceDataFormats) {
        this.sourceDataFormats = sourceDataFormats;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
