// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.io.File;
import java.util.ArrayList;
import org.pitest.util.ResultOutputStrategy;
import org.mudebug.prapr.core.SuspStrategy;
import java.util.Collection;
import org.mudebug.prapr.core.SuspCheckerType;
import org.pitest.mutationtest.config.ReportOptions;

public class PraPRReportOptions extends ReportOptions
{
    private SuspCheckerType mutateSuspStmt;
    private Collection<String> failingTests;
    private boolean reorderTestCases;
    private SuspStrategy suspStrategy;
    private ResultOutputStrategy outStrategy;
    private boolean verboseReport;
    
    public PraPRReportOptions() {
        this.failingTests = new ArrayList<String>();
    }
    
    public PraPRReportOptions(final ReportOptions reportOptions) {
        this.failingTests = new ArrayList<String>();
        this.setTargetClasses(reportOptions.getTargetClasses());
        this.setExcludedMethods(reportOptions.getExcludedMethods());
        this.setExcludedClasses(reportOptions.getExcludedClasses());
        this.setExcludedTestClasses(reportOptions.getExcludedTestClasses());
        this.setCodePaths(reportOptions.getCodePaths());
        this.setReportDir(this.makePraPRReportDirectory(reportOptions.getReportDir()));
        this.setHistoryInputLocation(reportOptions.getHistoryInputLocation());
        this.setHistoryOutputLocation(reportOptions.getHistoryOutputLocation());
        this.setSourceDirs(reportOptions.getSourceDirs());
        this.setMutators(reportOptions.getMutators());
        this.setFeatures(reportOptions.getFeatures());
        this.setDependencyAnalysisMaxDistance(reportOptions.getDependencyAnalysisMaxDistance());
        this.addChildJVMArgs(reportOptions.getJvmArgs());
        this.setNumberOfThreads(reportOptions.getNumberOfThreads());
        this.setTimeoutFactor(reportOptions.getTimeoutFactor());
        this.setTimeoutConstant(reportOptions.getTimeoutConstant());
        this.setTargetTests(reportOptions.getTargetTests());
        this.setLoggingClasses(reportOptions.getLoggingClasses());
        this.setVerbose(reportOptions.isVerbose());
        this.setFailWhenNoMutations(reportOptions.shouldFailWhenNoMutations());
        this.addOutputFormats(reportOptions.getOutputFormats());
        this.setGroupConfig(reportOptions.getGroupConfig());
        this.setMutationUnitSize(reportOptions.getMutationUnitSize());
        this.setShouldCreateTimestampedReports(reportOptions.shouldCreateTimeStampedReports());
        this.setDetectInlinedCode(reportOptions.isDetectInlinedCode());
        this.setExportLineCoverage(reportOptions.shouldExportLineCoverage());
        this.setMutationThreshold(reportOptions.getMutationThreshold());
        this.setCoverageThreshold(reportOptions.getCoverageThreshold());
        this.setMutationEngine(reportOptions.getMutationEngine());
        this.setJavaExecutable(reportOptions.getJavaExecutable());
        this.setIncludeLaunchClasspath(reportOptions.isIncludeLaunchClasspath());
        this.setFreeFormProperties(reportOptions.getFreeFormProperties());
        this.setMaximumAllowedSurvivors(reportOptions.getMaximumAllowedSurvivors());
        this.setExcludedRunners(reportOptions.getExcludedRunners());
        this.setIncludedTestMethods(reportOptions.getIncludedTestMethods());
        this.setTestPlugin(reportOptions.getTestPlugin());
        this.setClassPathElements(reportOptions.getClassPathElements());
    }
    
    private String makePraPRReportDirectory(final String pitReportDirectory) {
        final File pit = new File(pitReportDirectory);
        return new File(pit.getParentFile(), "prapr-reports").getAbsolutePath();
    }
    
    public SuspCheckerType getMutateSuspStmt() {
        return this.mutateSuspStmt;
    }
    
    public void setMutateSuspStmt(final SuspCheckerType mutateSuspStmt) {
        this.mutateSuspStmt = mutateSuspStmt;
    }
    
    public Collection<String> getFailingTests() {
        return this.failingTests;
    }
    
    public void addFailingTests(final Collection<String> failingTests) {
        this.failingTests.addAll(failingTests);
    }
    
    public boolean shouldReorderTestCases() {
        return this.reorderTestCases;
    }
    
    public void setReorderTestCases(final boolean reorderTestCases) {
        this.reorderTestCases = reorderTestCases;
    }
    
    public SuspStrategy getSuspStrategy() {
        return this.suspStrategy;
    }
    
    public void setSuspStrategy(final SuspStrategy suspStrategy) {
        this.suspStrategy = suspStrategy;
    }
    
    public boolean isVerboseReport() {
        return this.verboseReport;
    }
    
    public void setVerboseReport(final boolean verboseReport) {
        this.verboseReport = verboseReport;
    }
    
    public ResultOutputStrategy getOutStrategy() {
        return this.outStrategy;
    }
    
    public void setOutStrategy(final ResultOutputStrategy outStrategy) {
        this.outStrategy = outStrategy;
    }
}
