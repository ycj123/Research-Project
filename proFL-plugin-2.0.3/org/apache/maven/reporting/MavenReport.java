// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.reporting;

import java.io.File;
import java.util.Locale;
import org.codehaus.doxia.sink.Sink;

public interface MavenReport
{
    public static final String ROLE = MavenReport.class.getName();
    public static final String CATEGORY_PROJECT_INFORMATION = "Project Info";
    public static final String CATEGORY_PROJECT_REPORTS = "Project Reports";
    
    void generate(final Sink p0, final Locale p1) throws MavenReportException;
    
    String getOutputName();
    
    String getCategoryName();
    
    String getName(final Locale p0);
    
    String getDescription(final Locale p0);
    
    void setReportOutputDirectory(final File p0);
    
    File getReportOutputDirectory();
    
    boolean isExternalReport();
    
    boolean canGenerateReport();
}
