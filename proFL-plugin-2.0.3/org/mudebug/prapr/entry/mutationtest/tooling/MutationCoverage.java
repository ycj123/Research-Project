// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.mutationtest.tooling;

import java.util.logging.Level;
import org.pitest.functional.Option;
import org.pitest.classpath.ClassloaderByteArraySource;
import org.pitest.mutationtest.HistoryStore;
import java.lang.reflect.Field;
import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.mutationtest.build.MutationTestUnit;
import org.pitest.mutationtest.build.MutationGrouper;
import org.pitest.mutationtest.MutationAnalyser;
import org.pitest.mutationtest.build.MutationInterceptor;
import org.pitest.mutationtest.build.TestPrioritiser;
import org.pitest.mutationtest.build.MutationTestBuilder;
import org.pitest.mutationtest.TimeoutLengthStrategy;
import org.pitest.mutationtest.build.WorkerFactory;
import org.pitest.mutationtest.build.PercentAndConstantTimeoutStrategy;
import org.pitest.mutationtest.incremental.CodeHistory;
import org.pitest.mutationtest.incremental.IncrementalAnalyser;
import org.pitest.mutationtest.incremental.DefaultCodeHistory;
import org.pitest.mutationtest.build.MutationSource;
import org.mudebug.prapr.entry.mutationtest.build.PraPRTestPrioritizer;
import org.pitest.mutationtest.MutationConfig;
import java.io.PrintStream;
import org.pitest.mutationtest.statistics.Score;
import org.pitest.util.StringUtil;
import org.pitest.coverage.TestInfo;
import java.util.HashSet;
import org.pitest.classinfo.HierarchicalClassId;
import java.util.Set;
import org.pitest.functional.FCollection;
import org.pitest.classinfo.ClassInfo;
import org.pitest.classinfo.ClassName;
import org.pitest.mutationtest.ListenerArguments;
import org.pitest.mutationtest.tooling.SpinnerListener;
import org.pitest.mutationtest.incremental.HistoryListener;
import org.pitest.mutationtest.SourceLocator;
import org.mudebug.prapr.entry.mutationtest.AugmentedListenerArguments;
import org.pitest.mutationtest.tooling.SmartSourceLocator;
import org.pitest.help.PitHelpError;
import org.pitest.help.Help;
import java.util.Iterator;
import java.util.Collections;
import java.io.IOException;
import org.pitest.mutationtest.build.MutationAnalysisUnit;
import org.pitest.mutationtest.MutationResultListener;
import org.pitest.mutationtest.engine.MutationEngine;
import org.pitest.mutationtest.EngineArguments;
import org.mudebug.prapr.core.SuspChecker;
import java.util.List;
import org.pitest.coverage.CoverageDatabase;
import org.pitest.coverage.CoverageGenerator;
import org.pitest.mutationtest.execute.MutationAnalysisExecutor;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.classpath.ClassPathByteArraySource;
import org.mudebug.prapr.core.mutationtest.AugmentedEngineArguments;
import org.mudebug.prapr.entry.coverage.execute.PraPRCoverageGenerator;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import java.util.ArrayList;
import org.pitest.mutationtest.statistics.MutationStatisticsListener;
import org.pitest.util.Log;
import org.pitest.mutationtest.tooling.CombinedStatistics;
import org.pitest.mutationtest.config.ReportOptions;
import java.util.Collection;
import org.pitest.mutationtest.config.SettingsFactory;
import java.io.File;
import org.pitest.classpath.CodeSource;
import org.pitest.util.Timings;
import org.pitest.mutationtest.tooling.MutationStrategies;
import org.pitest.maven.PraPRReportOptions;
import java.util.logging.Logger;

public class MutationCoverage
{
    private static final int MB = 1048576;
    private static final Logger LOG;
    private final PraPRReportOptions data;
    private final MutationStrategies strategies;
    private final Timings timings;
    private final CodeSource code;
    private final File baseDir;
    private final SettingsFactory settings;
    private final Collection<String> failingTests;
    private final int allTestsCount;
    
    public MutationCoverage(final MutationStrategies strategies, final File baseDir, final CodeSource code, final ReportOptions data, final SettingsFactory settings, final Timings timings, final Collection<String> failingTests, final int allTestsCount) {
        this.strategies = strategies;
        this.data = (PraPRReportOptions)data;
        this.settings = settings;
        this.timings = timings;
        this.code = code;
        this.baseDir = baseDir;
        this.failingTests = failingTests;
        this.allTestsCount = allTestsCount;
    }
    
    public CombinedStatistics runReport() throws IOException {
        Log.setVerbose(this.data.isVerbose());
        final Runtime runtime = Runtime.getRuntime();
        if (!this.data.isVerbose()) {
            MutationCoverage.LOG.info("Verbose logging is disabled. If you encounter an problem please enable it before reporting an issue.");
        }
        MutationCoverage.LOG.fine("Running report with " + this.data);
        MutationCoverage.LOG.fine("System class path is " + System.getProperty("java.class.path"));
        MutationCoverage.LOG.fine("Maximum available memory is " + runtime.maxMemory() / 1048576L + " mb");
        final long t0 = System.currentTimeMillis();
        this.verifyBuildSuitableForMutationTesting();
        this.checkExcludedRunners();
        final CoverageGenerator coverageGenerator = this.coverage();
        final CoverageDatabase coverageData = coverageGenerator.calculateCoverage();
        this.data.addFailingTests(this.failingTests);
        this.data.setOutStrategy(this.strategies.output());
        MutationCoverage.LOG.fine("Used memory after coverage calculation " + (runtime.totalMemory() - runtime.freeMemory()) / 1048576L + " mb");
        MutationCoverage.LOG.fine("Free Memory after coverage calculation " + runtime.freeMemory() / 1048576L + " mb");
        final MutationStatisticsListener stats = new MutationStatisticsListener();
        Log.getLogger().info("Collecting some general info about the program...");
        final List<File> expandedClassPathElements = new ArrayList<File>();
        this.expandClassPathElements(this.data.getClassPathElements(), expandedClassPathElements);
        final GlobalInfo classHierarchy = GlobalInfo.construct(expandedClassPathElements);
        Log.getLogger().info("DONE");
        final SuspChecker suspChecker = ((PraPRCoverageGenerator)coverageGenerator).getSuspChecker();
        final EngineArguments args = AugmentedEngineArguments.arguments().withExcludedMethods(this.data.getExcludedMethods()).withMutators(this.data.getMutators()).withSuspChecker(suspChecker).withClassHierarchy(classHierarchy);
        final MutationEngine engine = this.strategies.factory().createEngine(args);
        final ClassByteArraySource cbas = this.fallbackToClassLoader(new ClassPathByteArraySource(this.data.getClassPath()));
        final List<MutationResultListener> config = this.createConfig(t0, coverageData, stats, engine, cbas);
        this.history().initialize();
        this.timings.registerStart(Timings.Stage.BUILD_MUTATION_TESTS);
        final List<MutationAnalysisUnit> tus = this.buildMutationTests(coverageData, engine, cbas, args, suspChecker);
        this.timings.registerEnd(Timings.Stage.BUILD_MUTATION_TESTS);
        MutationCoverage.LOG.info("Created  " + tus.size() + " mutation test units");
        this.checkMutationsFound(tus);
        this.recordClassPath(coverageData);
        MutationCoverage.LOG.fine("Used memory before analysis start " + (runtime.totalMemory() - runtime.freeMemory()) / 1048576L + " mb");
        MutationCoverage.LOG.fine("Free Memory before analysis start " + runtime.freeMemory() / 1048576L + " mb");
        final MutationAnalysisExecutor mae = new MutationAnalysisExecutor(this.numberOfThreads(), config);
        this.timings.registerStart(Timings.Stage.RUN_MUTATION_TESTS);
        mae.run(tus);
        this.timings.registerEnd(Timings.Stage.RUN_MUTATION_TESTS);
        MutationCoverage.LOG.info("Completed in " + this.timeSpan(t0));
        this.printStats(stats);
        return new CombinedStatistics(stats.getStatistics(), coverageData.createSummary());
    }
    
    private void expandClassPathElements(final Collection<String> classPathElements, final List<File> out) {
        for (final String cpe : classPathElements) {
            final File cpeFile = new File(cpe);
            if (cpeFile.isDirectory()) {
                final File[] arr$;
                final File[] files = arr$ = cpeFile.listFiles();
                for (final File file : arr$) {
                    if (file.isDirectory()) {
                        this.expandClassPathElements(Collections.singletonList(file.getAbsolutePath()), out);
                    }
                    else if (file.getName().endsWith(".class")) {
                        out.add(file);
                    }
                }
            }
            else {
                if (!cpeFile.getName().endsWith(".jar")) {
                    continue;
                }
                out.add(cpeFile);
            }
        }
    }
    
    private void checkExcludedRunners() {
        final Collection<String> excludedRunners = this.data.getExcludedRunners();
        if (!excludedRunners.isEmpty()) {
            try {
                Class.forName("org.junit.runner.RunWith");
            }
            catch (ClassNotFoundException e) {
                throw new PitHelpError(Help.NO_JUNIT_EXCLUDE_RUNNERS, new Object[0]);
            }
        }
    }
    
    private int numberOfThreads() {
        return Math.max(1, this.data.getNumberOfThreads());
    }
    
    private List<MutationResultListener> createConfig(final long t0, final CoverageDatabase coverageData, final MutationStatisticsListener stats, final MutationEngine engine, final ClassByteArraySource cbas) {
        final List<MutationResultListener> ls = new ArrayList<MutationResultListener>();
        ls.add(stats);
        final ListenerArguments args = new AugmentedListenerArguments(this.strategies.output(), coverageData, new SmartSourceLocator(this.data.getSourceDirs()), engine, cbas, t0, this.data.getSuspStrategy(), this.failingTests, this.allTestsCount, this.data.isVerboseReport());
        final MutationResultListener mutationReportListener = this.strategies.listenerFactory().getListener(this.data.getFreeFormProperties(), args);
        ls.add(mutationReportListener);
        ls.add(new HistoryListener(this.history()));
        if (!this.data.isVerbose()) {
            ls.add(new SpinnerListener(System.out));
        }
        return ls;
    }
    
    private void recordClassPath(final CoverageDatabase coverageData) {
        final Set<ClassName> allClassNames = this.getAllClassesAndTests(coverageData);
        final Collection<HierarchicalClassId> ids = FCollection.map(this.code.getClassInfo(allClassNames), ClassInfo.toFullClassId());
        this.history().recordClassPath(ids, coverageData);
    }
    
    private Set<ClassName> getAllClassesAndTests(final CoverageDatabase coverageData) {
        final Set<ClassName> names = new HashSet<ClassName>();
        for (final ClassName each : this.code.getCodeUnderTestNames()) {
            names.add(each);
            FCollection.mapTo(coverageData.getTestsForClass(each), TestInfo.toDefiningClassName(), names);
        }
        return names;
    }
    
    private void verifyBuildSuitableForMutationTesting() {
        this.strategies.buildVerifier().verify(this.code);
    }
    
    private void printStats(final MutationStatisticsListener stats) {
        final PrintStream ps = System.out;
        ps.println(StringUtil.separatorLine('='));
        ps.println("- Timings");
        ps.println(StringUtil.separatorLine('='));
        this.timings.report(ps);
        ps.println(StringUtil.separatorLine('='));
        ps.println("- Statistics");
        ps.println(StringUtil.separatorLine('='));
        stats.getStatistics().report(ps);
        ps.println(StringUtil.separatorLine('='));
        ps.println("- Mutators");
        ps.println(StringUtil.separatorLine('='));
        for (final Score each : stats.getStatistics().getScores()) {
            each.report(ps);
            ps.println(StringUtil.separatorLine());
        }
    }
    
    private List<MutationAnalysisUnit> buildMutationTests(final CoverageDatabase coverageData, final MutationEngine engine, final ClassByteArraySource bas, final EngineArguments args, final SuspChecker suspChecker) {
        final MutationConfig mutationConfig = new MutationConfig(engine, this.coverage().getLaunchOptions());
        TestPrioritiser testPrioritiser;
        if (this.data.shouldReorderTestCases()) {
            testPrioritiser = new PraPRTestPrioritizer(coverageData, this.failingTests);
        }
        else {
            testPrioritiser = this.settings.getTestPrioritiser().makeTestPrioritiser(this.data.getFreeFormProperties(), this.code, coverageData);
        }
        final MutationInterceptor interceptor = this.settings.getInterceptor().createInterceptor(this.data, bas);
        final MutationSource source = new MutationSource(mutationConfig, testPrioritiser, bas, interceptor);
        final MutationAnalyser analyser = new IncrementalAnalyser(new DefaultCodeHistory(this.code, this.history()), coverageData);
        final WorkerFactory wf = new WorkerFactory(this.baseDir, this.coverage().getConfiguration(), mutationConfig, args, new PercentAndConstantTimeoutStrategy(this.data.getTimeoutFactor(), this.data.getTimeoutConstant()), this.data.isVerbose(), this.data.getClassPath().getLocalClassPath());
        final MutationGrouper grouper = this.settings.getMutationGrouper().makeFactory(this.data.getFreeFormProperties(), this.code, this.data.getNumberOfThreads(), this.data.getMutationUnitSize());
        final MutationTestBuilder builder = new MutationTestBuilder(wf, analyser, source, grouper);
        final List<MutationAnalysisUnit> result = builder.createMutationTestUnits(this.code.getCodeUnderTestNames());
        this.filterOutNotHitMutations(result, suspChecker);
        return result;
    }
    
    private void filterOutNotHitMutations(final List<MutationAnalysisUnit> result, final SuspChecker suspChecker) {
        try {
            int allMutations = 0;
            int filteredOutMutations = 0;
            for (final MutationAnalysisUnit mau : result) {
                if (mau instanceof MutationTestUnit) {
                    allMutations += mau.priority();
                    final Field availableMutationsField = MutationTestUnit.class.getDeclaredField("availableMutations");
                    availableMutationsField.setAccessible(true);
                    final Collection availableMutations = (Collection)availableMutationsField.get(mau);
                    final Iterator it = availableMutations.iterator();
                    while (it.hasNext()) {
                        if (!suspChecker.isHit(it.next())) {
                            ++filteredOutMutations;
                            it.remove();
                        }
                    }
                }
            }
            Log.getLogger().info(String.format("*** %d/%d MUTATIONS HAVE BEEN FILTERED OUT.", filteredOutMutations, allMutations));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void checkMutationsFound(final List<MutationAnalysisUnit> tus) {
        if (tus.isEmpty()) {
            if (this.data.shouldFailWhenNoMutations()) {
                throw new PitHelpError(Help.NO_MUTATIONS_FOUND, new Object[0]);
            }
            MutationCoverage.LOG.warning(Help.NO_MUTATIONS_FOUND.toString());
        }
    }
    
    private String timeSpan(final long t0) {
        return "" + (System.currentTimeMillis() - t0) / 1000L + " seconds";
    }
    
    private CoverageGenerator coverage() {
        return this.strategies.coverage();
    }
    
    private HistoryStore history() {
        return this.strategies.history();
    }
    
    private ClassByteArraySource fallbackToClassLoader(final ClassByteArraySource bas) {
        final ClassByteArraySource clSource = ClassloaderByteArraySource.fromContext();
        return new ClassByteArraySource() {
            @Override
            public Option<byte[]> getBytes(final String clazz) {
                final Option<byte[]> maybeBytes = bas.getBytes(clazz);
                if (maybeBytes.hasSome()) {
                    return maybeBytes;
                }
                MutationCoverage.LOG.log(Level.FINE, "Could not find " + clazz + " on classpath for analysis. Falling back to classloader");
                return clSource.getBytes(clazz);
            }
        };
    }
    
    static {
        LOG = Log.getLogger();
    }
}
