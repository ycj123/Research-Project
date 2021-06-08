// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient;

import org.apache.maven.surefire.providerapi.SurefireProvider;
import org.apache.maven.surefire.booter.ClasspathConfiguration;
import org.apache.maven.surefire.booter.ProviderFactory;
import org.apache.maven.plugin.surefire.CommonReflector;
import org.apache.maven.surefire.report.StackTraceWriter;
import org.apache.maven.plugin.surefire.booterclient.lazytestprovider.OutputStreamFlushableCommandline;
import java.io.File;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.CommandLineException;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.CommandLineTimeOutException;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.Commandline;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.CommandLineUtils;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.StreamConsumer;
import org.apache.maven.plugin.surefire.booterclient.output.ThreadedStreamConsumer;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.ShutdownHookUtils;
import java.io.InputStream;
import org.apache.maven.plugin.surefire.booterclient.lazytestprovider.FlushReceiverProvider;
import org.apache.maven.surefire.booter.Classpath;
import java.io.IOException;
import org.apache.maven.surefire.booter.SystemPropertyManager;
import org.apache.maven.plugin.surefire.AbstractSurefireMojo;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.ArrayList;
import org.apache.maven.surefire.booter.SurefireExecutionException;
import org.apache.maven.surefire.booter.SurefireBooterForkException;
import java.util.Properties;
import org.apache.maven.plugin.surefire.booterclient.lazytestprovider.TestProvidingInputStream;
import org.apache.maven.surefire.booter.KeyValueSource;
import org.apache.maven.surefire.booter.PropertiesWrapper;
import org.apache.maven.plugin.surefire.booterclient.output.ForkClient;
import org.apache.maven.surefire.suite.RunResult;
import org.apache.maven.surefire.util.DefaultScanResult;
import org.apache.maven.plugin.surefire.SurefireProperties;
import org.apache.maven.plugin.surefire.report.DefaultReporterFactory;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.surefire.StartupReportConfiguration;
import org.apache.maven.surefire.booter.StartupConfiguration;
import org.apache.maven.surefire.booter.ProviderConfiguration;

public class ForkStarter
{
    private final int forkedProcessTimeoutInSeconds;
    private final ProviderConfiguration providerConfiguration;
    private final StartupConfiguration startupConfiguration;
    private final ForkConfiguration forkConfiguration;
    private final StartupReportConfiguration startupReportConfiguration;
    private Log log;
    private final DefaultReporterFactory defaultReporterFactory;
    private static volatile int systemPropertiesFileCounter;
    
    public ForkStarter(final ProviderConfiguration providerConfiguration, final StartupConfiguration startupConfiguration, final ForkConfiguration forkConfiguration, final int forkedProcessTimeoutInSeconds, final StartupReportConfiguration startupReportConfiguration, final Log log) {
        this.forkConfiguration = forkConfiguration;
        this.providerConfiguration = providerConfiguration;
        this.forkedProcessTimeoutInSeconds = forkedProcessTimeoutInSeconds;
        this.startupConfiguration = startupConfiguration;
        this.startupReportConfiguration = startupReportConfiguration;
        this.log = log;
        this.defaultReporterFactory = new DefaultReporterFactory(startupReportConfiguration);
    }
    
    public RunResult run(final SurefireProperties effectiveSystemProperties, final DefaultScanResult scanResult) throws SurefireBooterForkException, SurefireExecutionException {
        RunResult result;
        try {
            final Properties providerProperties = this.providerConfiguration.getProviderProperties();
            scanResult.writeTo(providerProperties);
            if (this.isForkOnce()) {
                final ForkClient forkClient = new ForkClient(this.defaultReporterFactory, this.startupReportConfiguration.getTestVmSystemProperties());
                result = this.fork(null, new PropertiesWrapper(providerProperties), forkClient, effectiveSystemProperties, null);
            }
            else if (this.forkConfiguration.isReuseForks()) {
                result = this.runSuitesForkOnceMultiple(effectiveSystemProperties, this.forkConfiguration.getForkCount());
            }
            else {
                result = this.runSuitesForkPerTestSet(effectiveSystemProperties, this.forkConfiguration.getForkCount());
            }
        }
        finally {
            this.defaultReporterFactory.close();
        }
        return result;
    }
    
    private boolean isForkOnce() {
        return this.forkConfiguration.isReuseForks() && 1 == this.forkConfiguration.getForkCount();
    }
    
    private RunResult runSuitesForkOnceMultiple(final SurefireProperties effectiveSystemProperties, final int forkCount) throws SurefireBooterForkException {
        final ArrayList<Future<RunResult>> results = new ArrayList<Future<RunResult>>(forkCount);
        final ExecutorService executorService = new ThreadPoolExecutor(forkCount, forkCount, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(forkCount));
        try {
            RunResult globalResult = new RunResult(0, 0, 0, 0);
            final List<Class<?>> suites = new ArrayList<Class<?>>();
            final Iterator<Class<?>> suitesIterator = this.getSuitesIterator();
            while (suitesIterator.hasNext()) {
                suites.add(suitesIterator.next());
            }
            final Queue<String> messageQueue = new ConcurrentLinkedQueue<String>();
            for (final Class<?> clazz : suites) {
                messageQueue.add(clazz.getName());
            }
            for (int forkNum = 0; forkNum < forkCount && forkNum < suites.size(); ++forkNum) {
                final Callable<RunResult> pf = new Callable<RunResult>() {
                    public RunResult call() throws Exception {
                        final TestProvidingInputStream testProvidingInputStream = new TestProvidingInputStream(messageQueue);
                        final ForkClient forkClient = new ForkClient(ForkStarter.this.defaultReporterFactory, ForkStarter.this.startupReportConfiguration.getTestVmSystemProperties(), testProvidingInputStream);
                        return ForkStarter.this.fork(null, new PropertiesWrapper(ForkStarter.this.providerConfiguration.getProviderProperties()), forkClient, effectiveSystemProperties, testProvidingInputStream);
                    }
                };
                results.add(executorService.submit(pf));
            }
            for (final Future<RunResult> result : results) {
                try {
                    final RunResult cur = result.get();
                    if (cur == null) {
                        throw new SurefireBooterForkException("No results for " + result.toString());
                    }
                    globalResult = globalResult.aggregate(cur);
                }
                catch (InterruptedException e) {
                    throw new SurefireBooterForkException("Interrupted", e);
                }
                catch (ExecutionException e2) {
                    throw new SurefireBooterForkException("ExecutionException", e2);
                }
            }
            return globalResult;
        }
        finally {
            this.closeExecutor(executorService);
        }
    }
    
    private RunResult runSuitesForkPerTestSet(final SurefireProperties effectiveSystemProperties, final int forkCount) throws SurefireBooterForkException {
        final ArrayList<Future<RunResult>> results = new ArrayList<Future<RunResult>>(500);
        final ExecutorService executorService = new ThreadPoolExecutor(forkCount, forkCount, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        try {
            RunResult globalResult = new RunResult(0, 0, 0, 0);
            final Iterator<Class<?>> suites = this.getSuitesIterator();
            while (suites.hasNext()) {
                final Object testSet = suites.next();
                final Callable<RunResult> pf = new Callable<RunResult>() {
                    public RunResult call() throws Exception {
                        final ForkClient forkClient = new ForkClient(ForkStarter.this.defaultReporterFactory, ForkStarter.this.startupReportConfiguration.getTestVmSystemProperties());
                        return ForkStarter.this.fork(testSet, new PropertiesWrapper(ForkStarter.this.providerConfiguration.getProviderProperties()), forkClient, effectiveSystemProperties, null);
                    }
                };
                results.add(executorService.submit(pf));
            }
            for (final Future<RunResult> result : results) {
                try {
                    final RunResult cur = result.get();
                    if (cur == null) {
                        throw new SurefireBooterForkException("No results for " + result.toString());
                    }
                    globalResult = globalResult.aggregate(cur);
                }
                catch (InterruptedException e) {
                    throw new SurefireBooterForkException("Interrupted", e);
                }
                catch (ExecutionException e2) {
                    throw new SurefireBooterForkException("ExecutionException", e2);
                }
            }
            return globalResult;
        }
        finally {
            this.closeExecutor(executorService);
        }
    }
    
    private void closeExecutor(final ExecutorService executorService) throws SurefireBooterForkException {
        executorService.shutdown();
        try {
            executorService.awaitTermination(3600L, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            throw new SurefireBooterForkException("Interrupted", e);
        }
    }
    
    private RunResult fork(final Object testSet, final KeyValueSource providerProperties, final ForkClient forkClient, final SurefireProperties effectiveSystemProperties, final TestProvidingInputStream testProvidingInputStream) throws SurefireBooterForkException {
        final int forkNumber = ForkNumberBucket.drawNumber();
        try {
            return this.fork(testSet, providerProperties, forkClient, effectiveSystemProperties, forkNumber, testProvidingInputStream);
        }
        finally {
            ForkNumberBucket.returnNumber(forkNumber);
        }
    }
    
    private RunResult fork(final Object testSet, final KeyValueSource providerProperties, final ForkClient forkClient, final SurefireProperties effectiveSystemProperties, final int forkNumber, final TestProvidingInputStream testProvidingInputStream) throws SurefireBooterForkException {
        File systPropsFile = null;
        File surefireProperties;
        try {
            final BooterSerializer booterSerializer = new BooterSerializer(this.forkConfiguration);
            surefireProperties = booterSerializer.serialize(providerProperties, this.providerConfiguration, this.startupConfiguration, testSet, null != testProvidingInputStream);
            if (effectiveSystemProperties != null) {
                final SurefireProperties filteredProperties = AbstractSurefireMojo.createCopyAndReplaceForkNumPlaceholder(effectiveSystemProperties, forkNumber);
                systPropsFile = SystemPropertyManager.writePropertiesFile(filteredProperties, this.forkConfiguration.getTempDirectory(), "surefire_" + ForkStarter.systemPropertiesFileCounter++, this.forkConfiguration.isDebug());
            }
        }
        catch (IOException e) {
            throw new SurefireBooterForkException("Error creating properties files for forking", e);
        }
        final Classpath bootClasspathConfiguration = this.startupConfiguration.isProviderMainClass() ? this.startupConfiguration.getClasspathConfiguration().getProviderClasspath() : this.forkConfiguration.getBootClasspath();
        final Classpath bootClasspath = Classpath.join(Classpath.join(bootClasspathConfiguration, this.startupConfiguration.getClasspathConfiguration().getTestClasspath()), this.startupConfiguration.getClasspathConfiguration().getProviderClasspath());
        if (this.log.isDebugEnabled()) {
            this.log.debug(bootClasspath.getLogMessage("boot"));
            this.log.debug(bootClasspath.getCompactLogMessage("boot(compact)"));
        }
        final OutputStreamFlushableCommandline cli = this.forkConfiguration.createCommandLine(bootClasspath.getClassPath(), this.startupConfiguration, forkNumber);
        InputStreamCloser inputStreamCloser;
        Thread inputStreamCloserHook;
        if (testProvidingInputStream != null) {
            testProvidingInputStream.setFlushReceiverProvider(cli);
            inputStreamCloser = new InputStreamCloser(testProvidingInputStream);
            inputStreamCloserHook = new Thread(inputStreamCloser);
            ShutdownHookUtils.addShutDownHook(inputStreamCloserHook);
        }
        else {
            inputStreamCloser = null;
            inputStreamCloserHook = null;
        }
        cli.createArg().setFile(surefireProperties);
        if (systPropsFile != null) {
            cli.createArg().setFile(systPropsFile);
        }
        final ThreadedStreamConsumer threadedStreamConsumer = new ThreadedStreamConsumer(forkClient);
        if (this.forkConfiguration.isDebug()) {
            System.out.println("Forking command line: " + cli);
        }
        RunResult runResult = null;
        try {
            final int timeout = (this.forkedProcessTimeoutInSeconds > 0) ? this.forkedProcessTimeoutInSeconds : 0;
            final int result = CommandLineUtils.executeCommandLine(cli, testProvidingInputStream, threadedStreamConsumer, threadedStreamConsumer, timeout, inputStreamCloser);
            if (result != 0) {
                throw new SurefireBooterForkException("Error occurred in starting fork, check output in log");
            }
            return runResult;
        }
        catch (CommandLineTimeOutException e3) {
            runResult = RunResult.timeout(this.defaultReporterFactory.getGlobalRunStatistics().getRunResult());
        }
        catch (CommandLineException e2) {
            runResult = RunResult.failure(this.defaultReporterFactory.getGlobalRunStatistics().getRunResult(), e2);
            throw new SurefireBooterForkException("Error while executing forked tests.", e2.getCause());
        }
        finally {
            threadedStreamConsumer.close();
            if (inputStreamCloser != null) {
                inputStreamCloser.run();
                ShutdownHookUtils.removeShutdownHook(inputStreamCloserHook);
            }
            if (runResult == null) {
                runResult = this.defaultReporterFactory.getGlobalRunStatistics().getRunResult();
            }
            if (!runResult.isTimeout()) {
                final StackTraceWriter errorInFork = forkClient.getErrorInFork();
                if (errorInFork != null) {
                    throw new RuntimeException("There was an error in the forked process\n" + errorInFork.writeTraceToString());
                }
                if (!forkClient.isSaidGoodBye()) {
                    throw new RuntimeException("The forked VM terminated without properly saying goodbye. VM crash or System.exit called?\nCommand was " + cli.toString());
                }
            }
            forkClient.close(runResult.isTimeout());
        }
        return runResult;
    }
    
    private Iterator<Class<?>> getSuitesIterator() throws SurefireBooterForkException {
        try {
            final ClasspathConfiguration classpathConfiguration = this.startupConfiguration.getClasspathConfiguration();
            final ClassLoader unifiedClassLoader = classpathConfiguration.createMergedClassLoader();
            final CommonReflector commonReflector = new CommonReflector(unifiedClassLoader);
            final Object reporterFactory = commonReflector.createReportingReporterFactory(this.startupReportConfiguration);
            final ProviderFactory providerFactory = new ProviderFactory(this.startupConfiguration, this.providerConfiguration, unifiedClassLoader, reporterFactory);
            final SurefireProvider surefireProvider = providerFactory.createProvider(false);
            return (Iterator<Class<?>>)surefireProvider.getSuites();
        }
        catch (SurefireExecutionException e) {
            throw new SurefireBooterForkException("Unable to create classloader to find test suites", e);
        }
    }
    
    static {
        ForkStarter.systemPropertiesFileCounter = 0;
    }
    
    private final class InputStreamCloser implements Runnable
    {
        private InputStream testProvidingInputStream;
        
        public InputStreamCloser(final InputStream testProvidingInputStream) {
            this.testProvidingInputStream = testProvidingInputStream;
        }
        
        public synchronized void run() {
            if (this.testProvidingInputStream != null) {
                try {
                    this.testProvidingInputStream.close();
                }
                catch (IOException ex) {}
                this.testProvidingInputStream = null;
            }
        }
    }
}
