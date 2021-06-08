// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report;

import org.apache.maven.model.Dependency;
import java.util.Arrays;
import org.apache.maven.artifact.Artifact;
import org.pitest.maven.DependencyFilter;
import org.pitest.mutationtest.config.PluginServices;
import org.codehaus.plexus.util.FileUtils;
import org.pitest.functional.FCollection;
import org.pitest.functional.F;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.Iterator;
import java.util.Collection;
import org.apache.maven.reporting.MavenReportException;
import org.pitest.util.ResultOutputStrategy;
import org.pitest.mutationtest.config.ReportDirCreationStrategy;
import org.pitest.mutationtest.config.DirectoryResultOutputStrategy;
import org.pitest.mutationtest.config.UndatedReportDirCreationStrategy;
import org.pitest.aggregate.ReportAggregator;
import java.util.Locale;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import java.util.List;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@org.apache.maven.plugins.annotations.Mojo(name = "report-aggregate", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
public class PitAggregationMojo extends PitReportMojo
{
    private static final String MUTATION_RESULT_FILTER = "target/pit-reports/mutations.xml";
    private static final String LINECOVERAGE_FILTER = "target/pit-reports/linecoverage.xml";
    @Parameter(property = "reactorProjects", readonly = true)
    private List<MavenProject> reactorProjects;
    
    @Override
    public String getDescription(final Locale locale) {
        return this.getName(locale) + " Coverage Report.";
    }
    
    @Override
    protected void executeReport(final Locale locale) throws MavenReportException {
        try {
            final Collection<MavenProject> allProjects = this.findDependencies();
            final ReportAggregator.Builder reportAggregationBuilder = ReportAggregator.builder();
            for (final MavenProject proj : allProjects) {
                this.addProjectFiles(reportAggregationBuilder, proj);
            }
            final ReportAggregator reportAggregator = reportAggregationBuilder.resultOutputStrategy(new DirectoryResultOutputStrategy(this.getReportsDirectory().getAbsolutePath(), new UndatedReportDirCreationStrategy())).build();
            reportAggregator.aggregateReport();
        }
        catch (Exception e) {
            throw new MavenReportException(e.getMessage(), e);
        }
    }
    
    private void addProjectFiles(final ReportAggregator.Builder reportAggregationBuilder, final MavenProject proj) throws IOException, Exception {
        final File projectBaseDir = proj.getBasedir();
        List<File> files = this.getProjectFilesByFilter(projectBaseDir, "target/pit-reports/mutations.xml");
        for (final File file : files) {
            reportAggregationBuilder.addMutationResultsFile(file);
        }
        files = this.getProjectFilesByFilter(projectBaseDir, "target/pit-reports/linecoverage.xml");
        for (final File file : files) {
            reportAggregationBuilder.addLineCoverageFile(file);
        }
        files = this.convertToRootDirs(proj.getCompileSourceRoots(), proj.getTestCompileSourceRoots());
        for (final File file : files) {
            reportAggregationBuilder.addSourceCodeDirectory(file);
        }
        files = this.getCompiledDirs(proj);
        for (final File file : files) {
            reportAggregationBuilder.addCompiledCodeDirectory(file);
        }
    }
    
    private List<File> convertToRootDirs(final List... directoryLists) {
        final List<String> roots = new ArrayList<String>();
        for (final List directoryList : directoryLists) {
            roots.addAll(directoryList);
        }
        return (List<File>)FCollection.map(roots, (F<Object, Object>)new F<String, File>() {
            @Override
            public File apply(final String a) {
                return new File(a);
            }
        });
    }
    
    private List<File> getProjectFilesByFilter(final File projectBaseDir, final String filter) throws IOException {
        final List<File> files = (List<File>)FileUtils.getFiles(projectBaseDir, filter, "");
        return (files == null) ? new ArrayList<File>() : files;
    }
    
    private List<File> getCompiledDirs(final MavenProject project) throws Exception {
        final List<String> sourceRoots = new ArrayList<String>();
        for (final Object artifactObj : FCollection.filter(project.getPluginArtifactMap().values(), (F<Object, Boolean>)new DependencyFilter(new PluginServices(PitAggregationMojo.class.getClassLoader())))) {
            final Artifact artifact = (Artifact)artifactObj;
            sourceRoots.add(artifact.getFile().getAbsolutePath());
        }
        return this.convertToRootDirs(project.getTestClasspathElements(), Arrays.asList(project.getBuild().getOutputDirectory(), project.getBuild().getTestOutputDirectory()), sourceRoots);
    }
    
    private List<MavenProject> findDependencies() {
        final List<MavenProject> result = new ArrayList<MavenProject>();
        final List<String> scopeList = Arrays.asList("compile", "runtime", "provided", "test");
        for (final Object dependencyObject : this.getProject().getDependencies()) {
            final Dependency dependency = (Dependency)dependencyObject;
            if (scopeList.contains(dependency.getScope())) {
                final MavenProject project = this.findProjectFromReactor(dependency);
                if (project == null) {
                    continue;
                }
                result.add(project);
            }
        }
        return result;
    }
    
    private MavenProject findProjectFromReactor(final Dependency d) {
        for (final MavenProject p : this.reactorProjects) {
            if (p.getGroupId().equals(d.getGroupId()) && p.getArtifactId().equals(d.getArtifactId()) && p.getVersion().equals(d.getVersion())) {
                return p;
            }
        }
        return null;
    }
}
