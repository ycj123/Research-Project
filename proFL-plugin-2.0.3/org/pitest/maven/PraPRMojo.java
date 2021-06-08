// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import org.mudebug.prapr.core.commons.TestCaseUtil;
import java.util.List;
import org.apache.maven.plugin.logging.Log;
import java.util.Collection;
import org.mudebug.prapr.core.SuspStrategy;
import org.mudebug.prapr.core.SuspStrategyImpl;
import org.mudebug.prapr.core.SuspCheckerType;
import org.mudebug.prapr.core.commons.TextStyleUtil;
import org.apache.maven.plugin.MojoExecutionException;
import org.pitest.mutationtest.config.ReportOptions;
import org.pitest.mutationtest.tooling.CombinedStatistics;
import org.pitest.functional.Option;
import org.apache.maven.project.MavenProject;
import org.apache.maven.artifact.Artifact;
import org.pitest.functional.predicate.Predicate;
import org.pitest.mutationtest.config.PluginServices;
import java.util.ArrayList;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@org.apache.maven.plugins.annotations.Mojo(name = "prapr", defaultPhase = LifecyclePhase.VERIFY, requiresDependencyResolution = ResolutionScope.TEST, threadSafe = true)
@Execute(phase = LifecyclePhase.TEST_COMPILE)
public class PraPRMojo extends AbstractPitMojo
{
    @Parameter(property = "mutateSuspStmt", defaultValue = "DEFAULT")
    private String mutateSuspStmt;
    @Parameter(property = "failingTests")
    private ArrayList<String> failingTests;
    @Parameter(property = "reorderTestCases", defaultValue = "true")
    private boolean reorderTestCases;
    @Parameter(property = "suspStrategy", defaultValue = "OCHIAI")
    private String suspStrategy;
    @Parameter(property = "verboseReport", defaultValue = "true")
    private boolean verboseReport;
    
    public PraPRMojo() {
        super(new RunPraPRStrategy(), new DependencyFilter(new PluginServices(PraPRMojo.class.getClassLoader())), new PluginServices(PraPRMojo.class.getClassLoader()), new NonEmptyProjectCheck());
    }
    
    @Override
    protected Option<CombinedStatistics> analyse() throws MojoExecutionException {
        final PraPRReportOptions data = this.preanalyse();
        return Option.some(this.goalStrategy.execute(this.detectBaseDir(), data, this.plugins, this.getEnvironmentVariables()));
    }
    
    protected PraPRReportOptions preanalyse() {
        final Log log = this.getLog();
        log.info(">>>>>>");
        log.info(TextStyleUtil.underlined(TextStyleUtil.bold("PraPR")) + TextStyleUtil.underlined(" (Practical Program Repair via Bytecode Mutation)"));
        log.info("(C) 2019 " + TextStyleUtil.bold("Ali Ghanbari") + ", Samuel Benton, and Lingming Zhang");
        log.info("<<<<<<");
        if (this.getFailingTests().isEmpty()) {
            log.info("No failing tests specified. PraPR is going to infer failing test cases.");
        }
        else {
            this.sanitizeTestCaseNames();
        }
        final List<String> outputFormats = this.getOutputFormats();
        if (outputFormats.isEmpty()) {
            log.info("No output format is specified. PraPR is going to produce LOG and COMPRESSED-XML reports.");
            outputFormats.add("LOG");
            outputFormats.add("COMPRESSED-XML");
        }
        final List<String> activatedMutators = this.getMutators();
        if (activatedMutators.isEmpty()) {
            log.info("No mutator is activated. PraPR is going to activate ALL the mutators.");
            activatedMutators.add("ALL");
        }
        if (this.verboseReport) {
            log.info("PraPR verbose report is activated.");
        }
        final ReportOptions pitReportOptions = new MojoToReportOptionsConverter(this, new SurefireConfigConverter(), this.filter).convert();
        final PraPRReportOptions data = new PraPRReportOptions(pitReportOptions);
        data.setMutationEngine("prapr");
        data.setMutateSuspStmt(SuspCheckerType.valueOf(this.mutateSuspStmt));
        data.setSuspStrategy(SuspStrategyImpl.valueOf(this.suspStrategy));
        data.addFailingTests(this.getFailingTests());
        data.setReorderTestCases(this.reorderTestCases);
        data.setVerboseReport(this.verboseReport);
        return data;
    }
    
    private void sanitizeTestCaseNames() {
        for (int i = 0; i < this.getFailingTests().size(); ++i) {
            this.getFailingTests().set(i, TestCaseUtil.sanitizeTestName(this.getFailingTests().get(i)));
        }
    }
    
    protected ArrayList<String> getFailingTests() {
        return this.failingTests;
    }
    
    protected void setFailingTests(final ArrayList<String> failingTests) {
        this.failingTests = failingTests;
    }
}
