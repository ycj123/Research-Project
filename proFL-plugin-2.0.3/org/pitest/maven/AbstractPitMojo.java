// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.util.Collections;
import java.util.List;
import org.pitest.mutationtest.config.ReportOptions;
import org.pitest.mutationtest.statistics.MutationStatistics;
import org.pitest.coverage.CoverageSummary;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;
import java.util.logging.Handler;
import java.util.logging.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.MojoExecutionException;
import org.pitest.functional.Option;
import java.util.Iterator;
import org.pitest.mutationtest.tooling.CombinedStatistics;
import org.pitest.plugin.ClientClasspathPlugin;
import org.pitest.plugin.ToolClasspathPlugin;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import org.apache.maven.plugins.annotations.Parameter;
import org.pitest.mutationtest.config.PluginServices;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.pitest.functional.predicate.Predicate;
import org.apache.maven.plugin.AbstractMojo;

public class AbstractPitMojo extends AbstractMojo
{
    private final Predicate<MavenProject> notEmptyProject;
    protected final Predicate<Artifact> filter;
    protected final PluginServices plugins;
    @Parameter(property = "testPlugin", defaultValue = "")
    private String testPlugin;
    @Parameter(property = "targetClasses")
    protected ArrayList<String> targetClasses;
    @Parameter(property = "targetTests")
    protected ArrayList<String> targetTests;
    @Parameter(property = "excludedMethods")
    private ArrayList<String> excludedMethods;
    @Parameter(property = "excludedClasses")
    private ArrayList<String> excludedClasses;
    @Parameter(property = "excludedTestClasses")
    private ArrayList<String> excludedTestClasses;
    @Parameter(property = "avoidCallsTo")
    private ArrayList<String> avoidCallsTo;
    @Parameter(defaultValue = "${project.build.directory}/pit-reports", property = "reportsDirectory")
    private File reportsDirectory;
    @Parameter(property = "historyOutputFile")
    private File historyOutputFile;
    @Parameter(property = "historyInputFile")
    private File historyInputFile;
    @Parameter(defaultValue = "false", property = "withHistory")
    private boolean withHistory;
    @Parameter(defaultValue = "-1", property = "maxDependencyDistance")
    private int maxDependencyDistance;
    @Parameter(defaultValue = "1", property = "threads")
    private int threads;
    @Parameter(defaultValue = "false", property = "mutateStaticInitializers")
    private boolean mutateStaticInitializers;
    @Parameter(defaultValue = "true", property = "detectInlinedCode")
    private boolean detectInlinedCode;
    @Parameter(property = "mutators")
    private ArrayList<String> mutators;
    @Parameter(property = "features")
    private ArrayList<String> features;
    @Parameter(defaultValue = "1.25", property = "timeoutFactor")
    private float timeoutFactor;
    @Parameter(defaultValue = "3000", property = "timeoutConstant")
    private long timeoutConstant;
    @Parameter(defaultValue = "-1", property = "maxMutationsPerClass")
    private int maxMutationsPerClass;
    @Parameter
    private ArrayList<String> jvmArgs;
    @Parameter(property = "outputFormats")
    private ArrayList<String> outputFormats;
    @Parameter(defaultValue = "false", property = "verbose")
    private boolean verbose;
    @Parameter(defaultValue = "true", property = "failWhenNoMutations")
    private boolean failWhenNoMutations;
    @Parameter(defaultValue = "true", property = "timestampedReports")
    private boolean timestampedReports;
    @Parameter(property = "excludedGroups")
    private ArrayList<String> excludedGroups;
    @Parameter(property = "includedGroups")
    private ArrayList<String> includedGroups;
    @Parameter(property = "includedTestMethods")
    private ArrayList<String> includedTestMethods;
    @Parameter(property = "mutationUnitSize")
    private int mutationUnitSize;
    @Parameter(defaultValue = "false", property = "exportLineCoverage")
    private boolean exportLineCoverage;
    @Parameter(defaultValue = "0", property = "mutationThreshold")
    private int mutationThreshold;
    @Parameter(defaultValue = "-1", property = "maxSurviving")
    private int maxSurviving;
    @Parameter(defaultValue = "0", property = "coverageThreshold")
    private int coverageThreshold;
    @Parameter
    private String jvm;
    @Parameter(defaultValue = "gregor", property = "mutationEngine")
    private String mutationEngine;
    @Parameter(property = "additionalClasspathElements")
    private ArrayList<String> additionalClasspathElements;
    @Parameter(property = "classpathDependencyExcludes")
    private ArrayList<String> classpathDependencyExcludes;
    @Parameter(property = "excludedRunners")
    private ArrayList<String> excludedRunners;
    @Parameter(defaultValue = "false")
    private boolean skip;
    @Parameter(defaultValue = "true")
    private boolean parseSurefireConfig;
    @Parameter(defaultValue = "false")
    private boolean skipTests;
    @Parameter(defaultValue = "false", property = "useSlf4j")
    private boolean useSlf4j;
    @Parameter
    private Map<String, String> pluginConfiguration;
    @Parameter
    private Map<String, String> environmentVariables;
    @Parameter(property = "project", readonly = true, required = true)
    protected MavenProject project;
    @Parameter(property = "plugin.artifactMap", readonly = true, required = true)
    private Map<String, Artifact> pluginArtifactMap;
    protected final GoalStrategy goalStrategy;
    
    public AbstractPitMojo() {
        this(new RunPitStrategy(), new DependencyFilter(new PluginServices(AbstractPitMojo.class.getClassLoader())), new PluginServices(AbstractPitMojo.class.getClassLoader()), new NonEmptyProjectCheck());
    }
    
    public AbstractPitMojo(final GoalStrategy strategy, final Predicate<Artifact> filter, final PluginServices plugins, final Predicate<MavenProject> emptyProjectCheck) {
        this.maxSurviving = -1;
        this.environmentVariables = new HashMap<String, String>();
        this.goalStrategy = strategy;
        this.filter = filter;
        this.plugins = plugins;
        this.notEmptyProject = emptyProjectCheck;
    }
    
    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {
        this.switchLogging();
        final RunDecision shouldRun = this.shouldRun();
        if (shouldRun.shouldRun()) {
            for (final ToolClasspathPlugin each : this.plugins.findToolClasspathPlugins()) {
                this.getLog().info("Found plugin : " + each.description());
            }
            for (final ClientClasspathPlugin each2 : this.plugins.findClientClasspathPlugins()) {
                this.getLog().info("Found shared classpath plugin : " + each2.description());
            }
            final Option<CombinedStatistics> result = this.analyse();
            if (result.hasSome()) {
                this.throwErrorIfScoreBelowThreshold(result.value().getMutationStatistics());
                this.throwErrorIfMoreThanMaximumSurvivors(result.value().getMutationStatistics());
                this.throwErrorIfCoverageBelowThreshold(result.value().getCoverageSummary());
            }
        }
        else {
            this.getLog().info("Skipping project because:");
            for (final String reason : shouldRun.getReasons()) {
                this.getLog().info("  - " + reason);
            }
        }
    }
    
    private void switchLogging() {
        if (this.useSlf4j) {
            SLF4JBridgeHandler.removeHandlersForRootLogger();
            SLF4JBridgeHandler.install();
            Logger.getLogger("PIT").addHandler(new SLF4JBridgeHandler());
            SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
        }
    }
    
    private void throwErrorIfCoverageBelowThreshold(final CoverageSummary coverageSummary) throws MojoFailureException {
        if (this.coverageThreshold != 0 && coverageSummary.getCoverage() < this.coverageThreshold) {
            throw new MojoFailureException("Line coverage of " + coverageSummary.getCoverage() + "(" + coverageSummary.getNumberOfCoveredLines() + "/" + coverageSummary.getNumberOfLines() + ") is below threshold of " + this.coverageThreshold);
        }
    }
    
    private void throwErrorIfScoreBelowThreshold(final MutationStatistics result) throws MojoFailureException {
        if (this.mutationThreshold != 0 && result.getPercentageDetected() < this.mutationThreshold) {
            throw new MojoFailureException("Mutation score of " + result.getPercentageDetected() + " is below threshold of " + this.mutationThreshold);
        }
    }
    
    private void throwErrorIfMoreThanMaximumSurvivors(final MutationStatistics result) throws MojoFailureException {
        if (this.maxSurviving >= 0 && result.getTotalSurvivingMutations() > this.maxSurviving) {
            throw new MojoFailureException("Had " + result.getTotalSurvivingMutations() + " surviving mutants, but only " + this.maxSurviving + " survivors allowed");
        }
    }
    
    protected Option<CombinedStatistics> analyse() throws MojoExecutionException {
        final ReportOptions data = new MojoToReportOptionsConverter(this, new SurefireConfigConverter(), this.filter).convert();
        return Option.some(this.goalStrategy.execute(this.detectBaseDir(), data, this.plugins, this.environmentVariables));
    }
    
    protected File detectBaseDir() {
        final MavenProject executionProject = this.project.getExecutionProject();
        if (executionProject == null) {
            return null;
        }
        return executionProject.getBasedir();
    }
    
    public List<String> getTargetClasses() {
        return this.targetClasses;
    }
    
    public List<String> getTargetTests() {
        return this.targetTests;
    }
    
    public List<String> getExcludedMethods() {
        return this.excludedMethods;
    }
    
    public List<String> getExcludedClasses() {
        return this.excludedClasses;
    }
    
    public List<String> getAvoidCallsTo() {
        return this.avoidCallsTo;
    }
    
    public File getReportsDirectory() {
        return this.reportsDirectory;
    }
    
    public int getMaxDependencyDistance() {
        return this.maxDependencyDistance;
    }
    
    public int getThreads() {
        return this.threads;
    }
    
    public boolean isMutateStaticInitializers() {
        return this.mutateStaticInitializers;
    }
    
    public List<String> getMutators() {
        return this.mutators;
    }
    
    public float getTimeoutFactor() {
        return this.timeoutFactor;
    }
    
    public long getTimeoutConstant() {
        return this.timeoutConstant;
    }
    
    public ArrayList<String> getExcludedTestClasses() {
        return this.excludedTestClasses;
    }
    
    public int getMaxMutationsPerClass() {
        return this.maxMutationsPerClass;
    }
    
    public List<String> getJvmArgs() {
        return this.jvmArgs;
    }
    
    public List<String> getOutputFormats() {
        return this.outputFormats;
    }
    
    public boolean isVerbose() {
        return this.verbose;
    }
    
    public MavenProject getProject() {
        return this.project;
    }
    
    public Map<String, Artifact> getPluginArtifactMap() {
        return this.pluginArtifactMap;
    }
    
    public boolean isFailWhenNoMutations() {
        return this.failWhenNoMutations;
    }
    
    public List<String> getExcludedGroups() {
        return this.excludedGroups;
    }
    
    public List<String> getIncludedGroups() {
        return this.includedGroups;
    }
    
    public List<String> getIncludedTestMethods() {
        return this.includedTestMethods;
    }
    
    public int getMutationUnitSize() {
        return this.mutationUnitSize;
    }
    
    public boolean isTimestampedReports() {
        return this.timestampedReports;
    }
    
    public boolean isDetectInlinedCode() {
        return this.detectInlinedCode;
    }
    
    public void setTimestampedReports(final boolean timestampedReports) {
        this.timestampedReports = timestampedReports;
    }
    
    public File getHistoryOutputFile() {
        return this.historyOutputFile;
    }
    
    public File getHistoryInputFile() {
        return this.historyInputFile;
    }
    
    public boolean isExportLineCoverage() {
        return this.exportLineCoverage;
    }
    
    protected RunDecision shouldRun() {
        final RunDecision decision = new RunDecision();
        if (this.skip) {
            decision.addReason("Execution of PIT should be skipped.");
        }
        if (this.skipTests) {
            decision.addReason("Test execution should be skipped (-DskipTests).");
        }
        if ("pom".equalsIgnoreCase(this.project.getPackaging())) {
            decision.addReason("Packaging is POM.");
        }
        if (!this.notEmptyProject.apply(this.project)) {
            decision.addReason("Project has no tests, it is empty.");
        }
        return decision;
    }
    
    public String getMutationEngine() {
        return this.mutationEngine;
    }
    
    public String getJavaExecutable() {
        return this.jvm;
    }
    
    public void setJavaExecutable(final String javaExecutable) {
        this.jvm = javaExecutable;
    }
    
    public List<String> getAdditionalClasspathElements() {
        return this.additionalClasspathElements;
    }
    
    public List<String> getClasspathDependencyExcludes() {
        return this.classpathDependencyExcludes;
    }
    
    public boolean isParseSurefireConfig() {
        return this.parseSurefireConfig;
    }
    
    public Map<String, String> getPluginProperties() {
        return this.pluginConfiguration;
    }
    
    public Map<String, String> getEnvironmentVariables() {
        return this.environmentVariables;
    }
    
    public boolean useHistory() {
        return this.withHistory;
    }
    
    public ArrayList<String> getExcludedRunners() {
        return this.excludedRunners;
    }
    
    public ArrayList<String> getFeatures() {
        return this.features;
    }
    
    public String getTestPlugin() {
        return this.testPlugin;
    }
    
    static class RunDecision
    {
        private List<String> reasons;
        
        RunDecision() {
            this.reasons = new ArrayList<String>(4);
        }
        
        boolean shouldRun() {
            return this.reasons.isEmpty();
        }
        
        public void addReason(final String reason) {
            this.reasons.add(reason);
        }
        
        public List<String> getReasons() {
            return Collections.unmodifiableList((List<? extends String>)this.reasons);
        }
    }
}
