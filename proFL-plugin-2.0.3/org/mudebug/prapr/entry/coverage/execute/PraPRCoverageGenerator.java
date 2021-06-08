// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.coverage.execute;

import java.io.Serializable;
import org.pitest.util.Log;
import org.pitest.coverage.CoverageDatabase;
import org.mudebug.prapr.core.WeakSuspChecker;
import org.mudebug.prapr.core.DefaultSuspChecker;
import org.mudebug.prapr.core.DummySuspChecker;
import org.mudebug.prapr.core.SuspChecker;
import org.pitest.classinfo.ClassName;
import org.pitest.functional.Option;
import org.pitest.testapi.Description;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Iterator;
import org.mudebug.prapr.core.commons.TestCaseUtil;
import org.pitest.functional.prelude.Prelude;
import org.pitest.functional.F;
import java.io.IOException;
import java.net.ServerSocket;
import org.pitest.coverage.CoverageResult;
import org.pitest.functional.SideEffect1;
import java.util.List;
import org.pitest.util.PitError;
import org.pitest.util.ExitCode;
import org.pitest.coverage.execute.CoverageProcess;
import org.pitest.process.ProcessArgs;
import org.pitest.util.SocketFinder;
import org.pitest.functional.FCollection;
import org.pitest.mutationtest.config.TestPluginArguments;
import org.pitest.classinfo.ClassInfo;
import org.pitest.util.Unchecked;
import org.pitest.help.PitHelpError;
import org.pitest.coverage.BlockCoverage;
import org.pitest.coverage.LineMap;
import org.pitest.coverage.analysis.LineMapper;
import org.pitest.coverage.CoverageData;
import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.HashSet;
import org.pitest.maven.PraPRReportOptions;
import org.pitest.coverage.TestInfo;
import org.pitest.coverage.BlockLocation;
import java.util.Map;
import java.util.Set;
import org.pitest.process.LaunchOptions;
import org.pitest.classpath.CodeSource;
import org.pitest.coverage.CoverageExporter;
import org.pitest.util.Timings;
import java.io.File;
import org.pitest.coverage.execute.CoverageOptions;
import java.util.logging.Logger;
import org.pitest.coverage.CoverageGenerator;

public class PraPRCoverageGenerator implements CoverageGenerator
{
    private static final Logger LOG;
    private final CoverageOptions coverageOptions;
    private final File workingDir;
    private final Timings timings;
    private final CoverageExporter exporter;
    private final CodeSource code;
    private final LaunchOptions launchOptions;
    private final boolean showProgress;
    private final boolean shouldInferFailingTests;
    private final Set<String> failingTests;
    private int allTestsCount;
    private final Map<BlockLocation, Set<TestInfo>> blockCoverage;
    private final PraPRReportOptions reportOptions;
    
    public PraPRCoverageGenerator(final CoverageOptions coverageOptions, final File workingDir, final Timings timings, final CoverageExporter exporter, final CodeSource code, final LaunchOptions launchOptions, final boolean showProgress, final PraPRReportOptions reportOptions) {
        this.timings = timings;
        this.exporter = exporter;
        this.code = code;
        this.coverageOptions = coverageOptions;
        this.workingDir = workingDir;
        this.launchOptions = launchOptions;
        this.showProgress = showProgress;
        this.shouldInferFailingTests = reportOptions.getFailingTests().isEmpty();
        this.failingTests = new HashSet<String>(reportOptions.getFailingTests());
        this.allTestsCount = 0;
        this.blockCoverage = new LinkedHashMap<BlockLocation, Set<TestInfo>>();
        this.reportOptions = reportOptions;
    }
    
    @Override
    public CoverageData calculateCoverage() {
        try {
            final long t0 = System.currentTimeMillis();
            this.timings.registerStart(Timings.Stage.SCAN_CLASS_PATH);
            final Collection<ClassInfo> tests = this.code.getTests();
            this.timings.registerEnd(Timings.Stage.SCAN_CLASS_PATH);
            final CoverageData coverage = new CoverageData(this.code, new LineMapper(this.code));
            this.timings.registerStart(Timings.Stage.COVERAGE);
            this.gatherCoverageData(tests, coverage);
            this.timings.registerEnd(Timings.Stage.COVERAGE);
            final long time = (System.currentTimeMillis() - t0) / 1000L;
            PraPRCoverageGenerator.LOG.info("Calculated coverage in " + time + " seconds.");
            this.exporter.recordCoverage(coverage.createCoverage());
            return coverage;
        }
        catch (PitHelpError phe) {
            throw phe;
        }
        catch (Exception e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    @Override
    public TestPluginArguments getConfiguration() {
        return this.coverageOptions.getPitConfig();
    }
    
    @Override
    public LaunchOptions getLaunchOptions() {
        return this.launchOptions;
    }
    
    private void gatherCoverageData(final Collection<ClassInfo> tests, final CoverageData coverage) throws IOException, InterruptedException {
        final List<String> filteredTests = FCollection.map(tests, classInfoToName());
        final SideEffect1<CoverageResult> handler = this.decoratedResultProcessor(coverage);
        final SocketFinder sf = new SocketFinder();
        final ServerSocket socket = sf.getNextAvailableServerSocket();
        final ProcessArgs processArgs = ProcessArgs.withClassPath(this.code.getClassPath()).andBaseDir(this.workingDir).andLaunchOptions(this.launchOptions).andStderr(logInfo()).andStdout(this.captureStandardOutIfVerbose());
        final CoverageProcess process = new CoverageProcess(processArgs, this.coverageOptions, socket, filteredTests, handler);
        process.start();
        final ExitCode exitCode = process.waitToDie();
        if (exitCode == ExitCode.JUNIT_ISSUE) {
            PraPRCoverageGenerator.LOG.severe("Error generating coverage. Please check that your classpath contains JUnit 4.6 or above.");
            throw new PitError("Coverage generation minion exited abnormally. Please check the classpath.");
        }
        if (!exitCode.isOk()) {
            PraPRCoverageGenerator.LOG.severe("Coverage generator Minion exited abnormally due to " + exitCode);
            throw new PitError("Coverage generation minion exited abnormally!");
        }
        PraPRCoverageGenerator.LOG.fine("Coverage generator Minion exited ok");
    }
    
    private static F<ClassInfo, String> classInfoToName() {
        return new F<ClassInfo, String>() {
            @Override
            public String apply(final ClassInfo classInfo) {
                return classInfo.getName().asInternalName();
            }
        };
    }
    
    private SideEffect1<String> captureStandardOutIfVerbose() {
        if (this.coverageOptions.isVerbose()) {
            return log();
        }
        return Prelude.noSideEffect(String.class);
    }
    
    private static SideEffect1<String> log() {
        return new SideEffect1<String>() {
            @Override
            public void apply(final String a) {
                PraPRCoverageGenerator.LOG.fine("MINION : " + a);
            }
        };
    }
    
    private static SideEffect1<String> logInfo() {
        return new SideEffect1<String>() {
            @Override
            public void apply(final String a) {
                PraPRCoverageGenerator.LOG.info("MINION : " + a);
            }
        };
    }
    
    private SideEffect1<CoverageResult> decoratedResultProcessor(final CoverageData coverageData) {
        return new SideEffect1<CoverageResult>() {
            final SideEffect1<CoverageResult> resultProcessor = PraPRCoverageGenerator.this.resultProcessor(coverageData);
            
            @Override
            public void apply(final CoverageResult coverageResult) {
                PraPRCoverageGenerator.this.allTestsCount++;
                if (!coverageResult.isGreenTest() && PraPRCoverageGenerator.this.shouldInferFailingTests) {
                    final String sanitizedName = TestCaseUtil.sanitizeTestName(coverageResult.getTestUnitDescription().getQualifiedName());
                    PraPRCoverageGenerator.this.failingTests.add(sanitizedName);
                }
                final TestInfo ti = this.createTestInfo(coverageResult.getTestUnitDescription(), coverageResult.getExecutionTime(), coverageResult.getNumberOfCoveredBlocks());
                for (final BlockLocation each : coverageResult.getCoverage()) {
                    this.addTestsToBlockMap(ti, each);
                }
                this.resultProcessor.apply(coverageResult);
            }
            
            private void addTestsToBlockMap(final TestInfo ti, final BlockLocation each) {
                Set<TestInfo> tests = PraPRCoverageGenerator.this.blockCoverage.get(each);
                if (tests == null) {
                    tests = new TreeSet<TestInfo>(new TestInfoNameComparator());
                    PraPRCoverageGenerator.this.blockCoverage.put(each, tests);
                }
                tests.add(ti);
            }
            
            private TestInfo createTestInfo(final Description description, final int executionTime, final int linesCovered) {
                final Option<ClassName> testee = PraPRCoverageGenerator.this.code.findTestee(description.getFirstTestClass());
                return new TestInfo(description.getFirstTestClass(), description.getQualifiedName(), executionTime, testee, linesCovered);
            }
        };
    }
    
    private SideEffect1<CoverageResult> resultProcessor(final CoverageData coverage) {
        return new SideEffect1<CoverageResult>() {
            private final String[] spinner = { "\b/", "\b-", "\b\\", "\b|" };
            private int i = 0;
            
            @Override
            public void apply(final CoverageResult cr) {
                coverage.calculateClassCoverage(cr);
                if (PraPRCoverageGenerator.this.showProgress) {
                    System.out.printf("%s", this.spinner[this.i % this.spinner.length]);
                }
                ++this.i;
            }
        };
    }
    
    public Set<String> getInferredFailingTests() {
        return this.failingTests;
    }
    
    public int getAllTestsCount() {
        return this.allTestsCount;
    }
    
    public SuspChecker getSuspChecker() {
        switch (this.reportOptions.getMutateSuspStmt()) {
            case NONE: {
                return new DummySuspChecker(this.failingTests);
            }
            case DEFAULT: {
                return new DefaultSuspChecker(this.failingTests, this.blockCoverage.entrySet());
            }
            case WEAK: {
                return new WeakSuspChecker(this.failingTests, this.blockCoverage.entrySet());
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    static {
        LOG = Log.getLogger();
    }
    
    private static class TestInfoNameComparator implements Comparator<TestInfo>, Serializable
    {
        private static final long serialVersionUID = 1L;
        
        @Override
        public int compare(final TestInfo lhs, final TestInfo rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    }
}
