// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report;

import org.pitest.maven.report.generator.ReportGenerationContext;
import org.apache.maven.reporting.MavenReportException;
import org.pitest.util.PitError;
import java.util.Locale;
import org.pitest.maven.report.generator.ReportGenerationManager;
import java.util.List;
import java.io.File;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.doxia.siterenderer.Renderer;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.reporting.AbstractMavenReport;

@org.apache.maven.plugins.annotations.Mojo(name = "report", defaultPhase = LifecyclePhase.SITE)
public class PitReportMojo extends AbstractMavenReport
{
    @Component
    private Renderer siteRenderer;
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;
    @Parameter(property = "pit.report.skip", defaultValue = "false")
    private boolean skip;
    @Parameter(property = "reportsDirectory", defaultValue = "${project.build.directory}/pit-reports")
    private File reportsDirectory;
    @Parameter(property = "pit.report.sourceDataFormats", defaultValue = "HTML")
    private List<String> sourceDataFormats;
    @Parameter(property = "pit.report.name", defaultValue = "PIT Test Report")
    private String siteReportName;
    @Parameter(property = "pit.report.description", defaultValue = "Report of the pit test coverage")
    private String siteReportDescription;
    @Parameter(property = "pit.report.outputdir", defaultValue = "pit-reports")
    private String siteReportDirectory;
    private ReportGenerationManager reportGenerationManager;
    
    public PitReportMojo() {
        this.reportGenerationManager = new ReportGenerationManager();
    }
    
    @Override
    public String getOutputName() {
        return this.siteReportDirectory + File.separator + "index";
    }
    
    @Override
    public String getName(final Locale locale) {
        return this.siteReportName;
    }
    
    @Override
    public String getDescription(final Locale locale) {
        return this.siteReportDescription;
    }
    
    @Override
    protected Renderer getSiteRenderer() {
        return this.siteRenderer;
    }
    
    @Override
    protected String getOutputDirectory() {
        return this.reportsDirectory.getAbsolutePath();
    }
    
    @Override
    protected MavenProject getProject() {
        return this.project;
    }
    
    @Override
    protected void executeReport(final Locale locale) throws MavenReportException {
        this.getLog().debug("PitReportMojo - starting");
        if (!this.reportsDirectory.exists()) {
            throw new PitError("could not find reports directory [" + this.reportsDirectory + "]");
        }
        if (!this.reportsDirectory.canRead()) {
            throw new PitError("reports directory [" + this.reportsDirectory + "] not readable");
        }
        if (!this.reportsDirectory.isDirectory()) {
            throw new PitError("reports directory [" + this.reportsDirectory + "] is actually a file, it must be a directory");
        }
        this.reportGenerationManager.generateSiteReport(this.buildReportGenerationContext(locale));
        this.getLog().debug("PitReportMojo - ending");
    }
    
    @Override
    public boolean canGenerateReport() {
        return !this.skip;
    }
    
    @Override
    public boolean isExternalReport() {
        return true;
    }
    
    public boolean isSkip() {
        return this.skip;
    }
    
    public File getReportsDirectory() {
        return this.reportsDirectory;
    }
    
    public List<String> getSourceDataFormats() {
        return this.sourceDataFormats;
    }
    
    private ReportGenerationContext buildReportGenerationContext(final Locale locale) {
        return new ReportGenerationContext(locale, this.getSink(), this.reportsDirectory, new File(this.getReportOutputDirectory().getAbsolutePath() + File.separator + this.siteReportDirectory), this.getLog(), this.getSourceDataFormats());
    }
}
