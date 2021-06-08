// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import org.apache.maven.surefire.util.ReflectionUtils;
import org.apache.maven.surefire.providerapi.ProviderParameters;
import org.apache.maven.surefire.providerapi.SurefireProvider;
import org.apache.maven.surefire.testset.TestSetFailedException;
import org.apache.maven.surefire.report.ReporterFactory;
import org.apache.maven.surefire.suite.RunResult;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import org.apache.maven.surefire.report.StackTraceWriter;
import org.apache.maven.surefire.report.LegacyPojoStackTraceWriter;
import java.io.FileInputStream;
import java.io.File;

public class ForkedBooter
{
    private static final long SYSTEM_EXIT_TIMEOUT = 30000L;
    
    public static void main(final String[] args) throws Throwable {
        final PrintStream originalOut = System.out;
        try {
            if (args.length > 1) {
                SystemPropertyManager.setSystemProperties(new File(args[1]));
            }
            final File surefirePropertiesFile = new File(args[0]);
            final InputStream stream = surefirePropertiesFile.exists() ? new FileInputStream(surefirePropertiesFile) : null;
            final BooterDeserializer booterDeserializer = new BooterDeserializer(stream);
            final ProviderConfiguration providerConfiguration = booterDeserializer.deserialize();
            final StartupConfiguration startupConfiguration = booterDeserializer.getProviderConfiguration();
            final TypeEncodedValue forkedTestSet = providerConfiguration.getTestForFork();
            final boolean readTestsFromInputStream = providerConfiguration.isReadTestsFromInStream();
            final ClasspathConfiguration classpathConfiguration = startupConfiguration.getClasspathConfiguration();
            if (startupConfiguration.isManifestOnlyJarRequestedAndUsable()) {
                classpathConfiguration.trickClassPathWhenManifestOnlyClasspath();
            }
            Thread.currentThread().getContextClassLoader().setDefaultAssertionStatus(classpathConfiguration.isEnableAssertions());
            startupConfiguration.writeSurefireTestClasspathProperty();
            Object testSet;
            if (forkedTestSet != null) {
                testSet = forkedTestSet.getDecodedValue(Thread.currentThread().getContextClassLoader());
            }
            else if (readTestsFromInputStream) {
                testSet = new LazyTestsToRun(System.in, originalOut);
            }
            else {
                testSet = null;
            }
            try {
                runSuitesInProcess(testSet, startupConfiguration, providerConfiguration, originalOut);
            }
            catch (InvocationTargetException t) {
                final LegacyPojoStackTraceWriter stackTraceWriter = new LegacyPojoStackTraceWriter("test subystem", "no method", t.getTargetException());
                final StringBuilder stringBuilder = new StringBuilder();
                ForkingRunListener.encode(stringBuilder, stackTraceWriter, false);
                originalOut.println("X,0," + stringBuilder.toString());
            }
            catch (Throwable t2) {
                final StackTraceWriter stackTraceWriter2 = new LegacyPojoStackTraceWriter("test subystem", "no method", t2);
                final StringBuilder stringBuilder = new StringBuilder();
                ForkingRunListener.encode(stringBuilder, stackTraceWriter2, false);
                originalOut.println("X,0," + stringBuilder.toString());
            }
            originalOut.println("Z,0,BYE!");
            originalOut.flush();
            exit(0);
        }
        catch (Throwable t3) {
            t3.printStackTrace(System.err);
            exit(1);
        }
    }
    
    private static void exit(final int returnCode) {
        launchLastDitchDaemonShutdownThread(returnCode);
        System.exit(returnCode);
    }
    
    private static RunResult runSuitesInProcess(final Object testSet, final StartupConfiguration startupConfiguration, final ProviderConfiguration providerConfiguration, final PrintStream originalSystemOut) throws SurefireExecutionException, TestSetFailedException, InvocationTargetException {
        final ReporterFactory factory = createForkingReporterFactory(providerConfiguration, originalSystemOut);
        return invokeProviderInSameClassLoader(testSet, factory, providerConfiguration, true, startupConfiguration, false);
    }
    
    private static ReporterFactory createForkingReporterFactory(final ProviderConfiguration providerConfiguration, final PrintStream originalSystemOut) {
        final Boolean trimStackTrace = providerConfiguration.getReporterConfiguration().isTrimStackTrace();
        return SurefireReflector.createForkingReporterFactoryInCurrentClassLoader(trimStackTrace, originalSystemOut);
    }
    
    private static void launchLastDitchDaemonShutdownThread(final int returnCode) {
        final Thread lastExit = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(30000L);
                    Runtime.getRuntime().halt(returnCode);
                }
                catch (InterruptedException ex) {}
            }
        });
        lastExit.setDaemon(true);
        lastExit.start();
    }
    
    public static RunResult invokeProviderInSameClassLoader(final Object testSet, final Object factory, final ProviderConfiguration providerConfiguration, final boolean insideFork, final StartupConfiguration startupConfiguration1, final boolean restoreStreams) throws TestSetFailedException, InvocationTargetException {
        final PrintStream orgSystemOut = System.out;
        final PrintStream orgSystemErr = System.err;
        final SurefireProvider provider = createProviderInCurrentClassloader(startupConfiguration1, insideFork, providerConfiguration, factory);
        try {
            return provider.invoke(testSet);
        }
        finally {
            if (restoreStreams && System.getSecurityManager() == null) {
                System.setOut(orgSystemOut);
                System.setErr(orgSystemErr);
            }
        }
    }
    
    public static SurefireProvider createProviderInCurrentClassloader(final StartupConfiguration startupConfiguration1, final boolean isInsideFork, final ProviderConfiguration providerConfiguration, final Object reporterManagerFactory1) {
        final BaseProviderFactory bpf = new BaseProviderFactory((ReporterFactory)reporterManagerFactory1, isInsideFork);
        bpf.setTestRequest(providerConfiguration.getTestSuiteDefinition());
        bpf.setReporterConfiguration(providerConfiguration.getReporterConfiguration());
        final ClassLoader clasLoader = Thread.currentThread().getContextClassLoader();
        bpf.setClassLoaders(clasLoader);
        bpf.setTestArtifactInfo(providerConfiguration.getTestArtifact());
        bpf.setProviderProperties(providerConfiguration.getProviderProperties());
        bpf.setRunOrderParameters(providerConfiguration.getRunOrderParameters());
        bpf.setDirectoryScannerParameters(providerConfiguration.getDirScannerParams());
        return (SurefireProvider)ReflectionUtils.instantiateOneArg(clasLoader, startupConfiguration1.getActualClassName(), ProviderParameters.class, bpf);
    }
}
