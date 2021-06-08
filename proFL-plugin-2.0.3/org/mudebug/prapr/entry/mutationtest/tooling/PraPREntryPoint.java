// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.mutationtest.tooling;

import org.pitest.classpath.ClassloaderByteArraySource;
import org.pitest.mutationtest.HistoryStore;
import org.pitest.classpath.ProjectClassPaths;
import org.pitest.coverage.execute.CoverageOptions;
import org.pitest.mutationtest.MutationResultListenerFactory;
import org.pitest.util.ResultOutputStrategy;
import org.pitest.mutationtest.incremental.WriterFactory;
import java.io.Reader;
import org.pitest.functional.Option;
import org.pitest.classpath.ClassPath;
import java.util.Collection;
import org.pitest.coverage.CoverageGenerator;
import org.pitest.mutationtest.tooling.MutationStrategies;
import org.pitest.mutationtest.incremental.ObjectOutputStreamHistoryStore;
import org.mudebug.prapr.entry.coverage.execute.PraPRCoverageGenerator;
import org.pitest.maven.PraPRReportOptions;
import org.pitest.util.Timings;
import org.pitest.classpath.CodeSource;
import org.pitest.process.JavaAgent;
import org.pitest.process.LaunchOptions;
import org.pitest.mutationtest.tooling.KnownLocationJavaAgentFinder;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.mutationtest.tooling.JarCreatingJarFinder;
import org.pitest.classpath.ClassPathByteArraySource;
import org.pitest.mutationtest.tooling.AnalysisResult;
import java.util.Map;
import org.pitest.mutationtest.config.SettingsFactory;
import org.pitest.mutationtest.config.ReportOptions;
import java.io.File;
import org.pitest.mutationtest.tooling.EntryPoint;

public class PraPREntryPoint extends EntryPoint
{
    @Override
    public AnalysisResult execute(final File baseDir, final ReportOptions data, final SettingsFactory settings, final Map<String, String> environmentVariables) {
        this.selectTestPlugin(data);
        final ClassPath cp = data.getClassPath();
        final Option<Reader> reader = data.createHistoryReader();
        final WriterFactory historyWriter = data.createHistoryWriter();
        final JavaAgent jac = new JarCreatingJarFinder(new ClassPathByteArraySource(cp));
        final KnownLocationJavaAgentFinder ja = new KnownLocationJavaAgentFinder(jac.getJarLocation().value());
        final ResultOutputStrategy reportOutput = settings.getOutputStrategy();
        final MutationResultListenerFactory reportFactory = settings.createListener();
        final CoverageOptions coverageOptions = settings.createCoverageOptions();
        final LaunchOptions launchOptions = new LaunchOptions(ja, settings.getJavaExecutable(), data.getJvmArgs(), environmentVariables);
        final ProjectClassPaths cps = data.getMutationClassPaths();
        final CodeSource code = new CodeSource(cps);
        final Timings timings = new Timings();
        final PraPRCoverageGenerator coverageDatabase = new PraPRCoverageGenerator(coverageOptions, baseDir, timings, settings.createCoverageExporter(), code, launchOptions, !data.isVerbose(), (PraPRReportOptions)data);
        final HistoryStore history = new ObjectOutputStreamHistoryStore(historyWriter, reader);
        final MutationStrategies strategies = new MutationStrategies(settings.createEngine(), history, coverageDatabase, reportFactory, reportOutput);
        final MutationCoverage report = new MutationCoverage(strategies, baseDir, code, data, settings, timings, coverageDatabase.getInferredFailingTests(), coverageDatabase.getAllTestsCount());
        try {
            return AnalysisResult.success(report.runReport());
        }
        catch (Exception e) {
            return AnalysisResult.fail(e);
        }
        finally {
            jac.close();
            ja.close();
            historyWriter.close();
        }
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
                return clSource.getBytes(clazz);
            }
        };
    }
    
    private void selectTestPlugin(final ReportOptions data) {
        if (data.getTestPlugin() == null || data.getTestPlugin().equals("")) {
            if (this.junit5PluginIsOnClasspath()) {
                data.setTestPlugin("junit5");
            }
            else {
                data.setTestPlugin("junit");
            }
        }
    }
    
    private boolean junit5PluginIsOnClasspath() {
        try {
            Class.forName("org.pitest.junit5.JUnit5TestPluginFactory");
            return true;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }
}
