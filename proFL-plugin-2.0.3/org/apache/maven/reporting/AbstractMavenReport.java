// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.reporting;

import java.io.Writer;
import org.apache.maven.doxia.siterenderer.sink.SiteRendererSink;
import org.codehaus.plexus.util.IOUtil;
import java.io.IOException;
import org.apache.maven.doxia.siterenderer.RendererException;
import org.codehaus.plexus.util.WriterFactory;
import org.apache.maven.doxia.site.decoration.DecorationModel;
import org.apache.maven.doxia.siterenderer.SiteRenderingContext;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.reporting.sink.SinkFactory;
import org.apache.maven.project.MavenProject;
import org.apache.maven.doxia.siterenderer.Renderer;
import java.io.File;
import java.util.Locale;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.plugin.AbstractMojo;

public abstract class AbstractMavenReport extends AbstractMojo implements MavenReport
{
    private Sink sink;
    private Locale locale;
    private File reportOutputDirectory;
    
    public AbstractMavenReport() {
        this.locale = Locale.ENGLISH;
    }
    
    protected abstract Renderer getSiteRenderer();
    
    protected abstract String getOutputDirectory();
    
    protected abstract MavenProject getProject();
    
    public void execute() throws MojoExecutionException {
        SiteRendererSink sink;
        try {
            final String outputDirectory = this.getOutputDirectory();
            sink = SinkFactory.createSink(new File(outputDirectory), this.getOutputName() + ".html");
            this.generate(sink, Locale.getDefault());
        }
        catch (MavenReportException e) {
            throw new MojoExecutionException("An error has occurred in " + this.getName(this.locale) + " report generation.", e);
        }
        final File outputHtml = new File(this.getOutputDirectory(), this.getOutputName() + ".html");
        outputHtml.getParentFile().mkdirs();
        Writer writer = null;
        try {
            final SiteRenderingContext context = new SiteRenderingContext();
            context.setDecoration(new DecorationModel());
            context.setTemplateName("org/apache/maven/doxia/siterenderer/resources/default-site.vm");
            context.setLocale(this.locale);
            writer = WriterFactory.newXmlWriter(outputHtml);
            this.getSiteRenderer().generateDocument(writer, sink, context);
        }
        catch (RendererException e2) {
            throw new MojoExecutionException("An error has occurred in " + this.getName(Locale.ENGLISH) + " report generation.", e2);
        }
        catch (IOException e3) {
            throw new MojoExecutionException("An error has occurred in " + this.getName(Locale.ENGLISH) + " report generation.", e3);
        }
        finally {
            IOUtil.close(writer);
        }
    }
    
    public void generate(final org.codehaus.doxia.sink.Sink sink, final Locale locale) throws MavenReportException {
        if (sink == null) {
            throw new MavenReportException("You must specify a sink.");
        }
        this.sink = sink;
        this.executeReport(locale);
        this.closeReport();
    }
    
    protected abstract void executeReport(final Locale p0) throws MavenReportException;
    
    protected void closeReport() {
        this.getSink().close();
    }
    
    public String getCategoryName() {
        return "Project Reports";
    }
    
    public File getReportOutputDirectory() {
        if (this.reportOutputDirectory == null) {
            this.reportOutputDirectory = new File(this.getOutputDirectory());
        }
        return this.reportOutputDirectory;
    }
    
    public void setReportOutputDirectory(final File reportOutputDirectory) {
        this.reportOutputDirectory = reportOutputDirectory;
    }
    
    public Sink getSink() {
        return this.sink;
    }
    
    public boolean isExternalReport() {
        return false;
    }
    
    public boolean canGenerateReport() {
        return true;
    }
}
