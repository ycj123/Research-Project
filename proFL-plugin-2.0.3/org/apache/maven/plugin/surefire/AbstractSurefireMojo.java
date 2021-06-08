// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import java.lang.reflect.Method;
import org.apache.maven.artifact.resolver.filter.ExcludesArtifactFilter;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import java.util.LinkedHashSet;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import java.util.Set;
import org.apache.maven.surefire.shade.org.apache.maven.shared.artifact.filter.PatternIncludesArtifactFilter;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;
import org.apache.maven.plugin.surefire.booterclient.ChecksumCalculator;
import java.util.Collections;
import org.apache.maven.model.Plugin;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nonnull;
import org.apache.maven.surefire.booter.Classpath;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.surefire.booter.ClasspathConfiguration;
import org.apache.maven.surefire.booter.StartupConfiguration;
import org.apache.maven.surefire.booter.TypeEncodedValue;
import org.apache.maven.surefire.testset.DirectoryScannerParameters;
import org.apache.maven.surefire.testset.TestRequest;
import org.apache.maven.surefire.testset.TestArtifactInfo;
import org.apache.maven.surefire.report.ReporterConfiguration;
import org.apache.maven.surefire.booter.ProviderConfiguration;
import org.apache.maven.surefire.util.RunOrder;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.apache.maven.surefire.booter.KeyValueSource;
import org.apache.maven.plugin.surefire.booterclient.ForkStarter;
import org.apache.maven.plugin.surefire.booterclient.ForkConfiguration;
import org.apache.maven.surefire.booter.ClassLoaderConfiguration;
import org.apache.maven.surefire.testset.RunOrderParameters;
import org.apache.maven.plugin.logging.Log;
import java.io.IOException;
import java.util.Iterator;
import org.apache.maven.surefire.testset.TestSetFailedException;
import org.apache.maven.surefire.booter.SurefireExecutionException;
import org.apache.maven.surefire.booter.SurefireBooterForkException;
import org.apache.maven.plugin.surefire.util.DependencyScanner;
import java.util.Arrays;
import org.apache.maven.plugin.surefire.util.DirectoryScanner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.surefire.util.DefaultScanResult;
import org.apache.maven.surefire.suite.RunResult;
import org.apache.maven.plugin.MojoFailureException;
import java.util.HashMap;
import org.apache.maven.toolchain.Toolchain;
import org.apache.maven.toolchain.ToolchainManager;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.artifact.Artifact;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.artifact.repository.ArtifactRepository;
import java.util.List;
import java.io.File;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.project.MavenProject;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugin.AbstractMojo;

public abstract class AbstractSurefireMojo extends AbstractMojo implements SurefireExecutionParameters
{
    @Parameter(defaultValue = "${plugin}", readonly = true)
    protected PluginDescriptor pluginDescriptor;
    @Parameter(property = "skipTests", defaultValue = "false")
    protected boolean skipTests;
    @Parameter(property = "maven.test.skip.exec")
    @Deprecated
    protected boolean skipExec;
    @Parameter(property = "maven.test.skip", defaultValue = "false")
    protected boolean skip;
    @Component
    protected MavenProject project;
    @Parameter(defaultValue = "${basedir}")
    protected File basedir;
    @Parameter(defaultValue = "${project.build.testOutputDirectory}")
    protected File testClassesDirectory;
    @Parameter(defaultValue = "${project.build.outputDirectory}")
    protected File classesDirectory;
    @Parameter(property = "maven.test.dependency.excludes")
    private String[] classpathDependencyExcludes;
    @Parameter(defaultValue = "")
    private String classpathDependencyScopeExclude;
    @Parameter(property = "maven.test.additionalClasspath")
    private String[] additionalClasspathElements;
    @Parameter(defaultValue = "${project.build.testSourceDirectory}", required = true)
    protected File testSourceDirectory;
    @Parameter
    protected File includesFile;
    @Parameter
    protected List<String> excludes;
    @Parameter
    protected File excludesFile;
    @Parameter(defaultValue = "${localRepository}", required = true, readonly = true)
    protected ArtifactRepository localRepository;
    @Parameter
    @Deprecated
    protected Properties systemProperties;
    @Parameter
    protected Map<String, String> systemPropertyVariables;
    @Parameter
    protected File systemPropertiesFile;
    @Parameter
    protected Properties properties;
    @Parameter(property = "plugin.artifactMap", required = true, readonly = true)
    protected Map<String, Artifact> pluginArtifactMap;
    @Parameter(property = "project.artifactMap", readonly = true, required = true)
    protected Map<String, Artifact> projectArtifactMap;
    @Parameter(property = "surefire.reportNameSuffix", defaultValue = "")
    protected String reportNameSuffix;
    @Parameter(property = "maven.test.redirectTestOutputToFile", defaultValue = "false")
    protected boolean redirectTestOutputToFile;
    @Parameter(property = "failIfNoTests")
    protected Boolean failIfNoTests;
    @Parameter(property = "forkMode", defaultValue = "once")
    protected String forkMode;
    @Parameter(property = "jvm")
    protected String jvm;
    @Parameter(property = "argLine")
    protected String argLine;
    @Parameter
    protected Map<String, String> environmentVariables;
    @Parameter(property = "basedir")
    protected File workingDirectory;
    @Parameter(property = "childDelegation", defaultValue = "false")
    protected boolean childDelegation;
    @Parameter(property = "groups")
    protected String groups;
    @Parameter(property = "excludedGroups")
    protected String excludedGroups;
    @Parameter
    protected File[] suiteXmlFiles;
    @Parameter(property = "junitArtifactName", defaultValue = "junit:junit")
    protected String junitArtifactName;
    @Parameter(property = "testNGArtifactName", defaultValue = "org.testng:testng")
    protected String testNGArtifactName;
    @Parameter(property = "threadCount")
    protected int threadCount;
    @Parameter(property = "forkCount", defaultValue = "1")
    private String forkCount;
    @Parameter(property = "reuseForks", defaultValue = "true")
    private boolean reuseForks;
    @Parameter(property = "perCoreThreadCount", defaultValue = "true")
    protected boolean perCoreThreadCount;
    @Parameter(property = "useUnlimitedThreads", defaultValue = "false")
    protected boolean useUnlimitedThreads;
    @Parameter(property = "parallel")
    protected String parallel;
    @Parameter(property = "parallelOptimized", defaultValue = "true")
    protected boolean parallelOptimized;
    @Parameter(property = "threadCountSuites", defaultValue = "0")
    protected int threadCountSuites;
    @Parameter(property = "threadCountClasses", defaultValue = "0")
    protected int threadCountClasses;
    @Parameter(property = "threadCountMethods", defaultValue = "0")
    protected int threadCountMethods;
    @Parameter(property = "trimStackTrace", defaultValue = "true")
    protected boolean trimStackTrace;
    @Component
    protected ArtifactResolver artifactResolver;
    @Component
    protected ArtifactFactory artifactFactory;
    @Parameter(defaultValue = "${project.pluginArtifactRepositories}")
    protected List<ArtifactRepository> remoteRepositories;
    @Component
    protected ArtifactMetadataSource metadataSource;
    @Parameter(property = "disableXmlReport", defaultValue = "false")
    protected boolean disableXmlReport;
    @Parameter(property = "enableAssertions", defaultValue = "true")
    protected boolean enableAssertions;
    @Component
    protected MavenSession session;
    @Parameter(property = "objectFactory")
    protected String objectFactory;
    @Parameter(defaultValue = "${session.parallel}", readonly = true)
    protected Boolean parallelMavenExecution;
    @Parameter(defaultValue = "filesystem")
    protected String runOrder;
    @Parameter(property = "dependenciesToScan")
    private String[] dependenciesToScan;
    @Component
    protected ToolchainManager toolchainManager;
    private Artifact surefireBooterArtifact;
    private Toolchain toolchain;
    private int effectiveForkCount;
    public static final String THREAD_NUMBER_PLACEHOLDER = "${surefire.threadNumber}";
    public static final String FORK_NUMBER_PLACEHOLDER = "${surefire.forkNumber}";
    private SurefireDependencyResolver dependencyResolver;
    
    public AbstractSurefireMojo() {
        this.environmentVariables = new HashMap<String, String>();
        this.effectiveForkCount = -1;
    }
    
    protected abstract String getPluginName();
    
    public void execute() throws MojoExecutionException, MojoFailureException {
        this.setupStuff();
        if (this.verifyParameters() && !this.hasExecutedBefore()) {
            final DefaultScanResult scan = this.scanForTestClasses();
            if (!this.isValidSuiteXmlFileConfig() && scan.isEmpty()) {
                if (this.getEffectiveFailIfNoTests()) {
                    throw new MojoFailureException("No tests were executed!  (Set -DfailIfNoTests=false to ignore this error.)");
                }
                this.handleSummary(RunResult.noTestsRun(), null);
            }
            else {
                this.logReportsDirectory();
                this.executeAfterPreconditionsChecked(scan);
            }
        }
    }
    
    private void setupStuff() {
        this.createDependencyResolver();
        this.surefireBooterArtifact = this.getSurefireBooterArtifact();
        this.toolchain = this.getToolchain();
    }
    
    private DefaultScanResult scanForTestClasses() {
        final DefaultScanResult scan = this.scanDirectories();
        final DefaultScanResult scanDeps = this.scanDependencies();
        return scan.append(scanDeps);
    }
    
    private DefaultScanResult scanDirectories() {
        return new DirectoryScanner(this.getTestClassesDirectory(), this.getIncludeList(), this.getExcludeList(), this.getSpecificTests()).scan();
    }
    
    private DefaultScanResult scanDependencies() {
        if (this.getDependenciesToScan() == null) {
            return null;
        }
        try {
            return new DependencyScanner(DependencyScanner.filter(this.project.getTestArtifacts(), Arrays.asList(this.getDependenciesToScan())), this.getIncludeList(), this.getExcludeList(), this.getSpecificTests()).scan();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    boolean verifyParameters() throws MojoFailureException, MojoExecutionException {
        this.setProperties(new SurefireProperties(this.getProperties()));
        if (this.isSkipExecution()) {
            this.getLog().info("Tests are skipped.");
            return false;
        }
        final String jvmToUse = this.getJvm();
        if (this.toolchain != null) {
            this.getLog().info("Toolchain in " + this.getPluginName() + "-plugin: " + this.toolchain);
            if (jvmToUse != null) {
                this.getLog().warn("Toolchains are ignored, 'executable' parameter is set to " + jvmToUse);
            }
        }
        if (!this.getTestClassesDirectory().exists() && (this.getDependenciesToScan() == null || this.getDependenciesToScan().length == 0)) {
            if (Boolean.TRUE.equals(this.getFailIfNoTests())) {
                throw new MojoFailureException("No tests to run!");
            }
            this.getLog().info("No tests to run.");
        }
        else {
            this.convertDeprecatedForkMode();
            this.ensureWorkingDirectoryExists();
            this.ensureParallelRunningCompatibility();
            this.ensureThreadCountWithPerThread();
            this.warnIfUselessUseSystemClassLoaderParameter();
            this.warnIfDefunctGroupsCombinations();
        }
        return true;
    }
    
    protected abstract boolean isSkipExecution();
    
    protected void executeAfterPreconditionsChecked(final DefaultScanResult scanResult) throws MojoExecutionException, MojoFailureException {
        final List<ProviderInfo> providers = this.createProviders();
        RunResult current = RunResult.noTestsRun();
        Exception firstForkException = null;
        for (final ProviderInfo provider : providers) {
            try {
                current = current.aggregate(this.executeProvider(provider, scanResult));
            }
            catch (SurefireBooterForkException e) {
                if (firstForkException != null) {
                    continue;
                }
                firstForkException = e;
            }
            catch (SurefireExecutionException e2) {
                if (firstForkException != null) {
                    continue;
                }
                firstForkException = e2;
            }
            catch (TestSetFailedException e3) {
                if (firstForkException != null) {
                    continue;
                }
                firstForkException = e3;
            }
        }
        if (firstForkException != null) {
            current = RunResult.failure(current, firstForkException);
        }
        this.handleSummary(current, firstForkException);
    }
    
    private void createDependencyResolver() {
        this.dependencyResolver = new SurefireDependencyResolver(this.getArtifactResolver(), this.getArtifactFactory(), this.getLog(), this.getLocalRepository(), this.getRemoteRepositories(), this.getMetadataSource(), this.getPluginName());
    }
    
    protected List<ProviderInfo> createProviders() throws MojoFailureException, MojoExecutionException {
        final Artifact junitDepArtifact = this.getJunitDepArtifact();
        final ProviderList wellKnownProviders = new ProviderList(new DynamicProviderInfo(null), new ProviderInfo[] { new TestNgProviderInfo(this.getTestNgArtifact()), new JUnitCoreProviderInfo(this.getJunitArtifact(), junitDepArtifact), new JUnit4ProviderInfo(this.getJunitArtifact(), junitDepArtifact), new JUnit3ProviderInfo() });
        return wellKnownProviders.resolve(this.getLog());
    }
    
    private SurefireProperties setupProperties() {
        SurefireProperties sysProps = null;
        try {
            sysProps = SurefireProperties.loadProperties(this.systemPropertiesFile);
        }
        catch (IOException e) {
            final String msg = "The system property file '" + this.systemPropertiesFile.getAbsolutePath() + "' can't be read.";
            if (this.getLog().isDebugEnabled()) {
                this.getLog().warn(msg, e);
            }
            else {
                this.getLog().warn(msg);
            }
        }
        final SurefireProperties result = SurefireProperties.calculateEffectiveProperties(this.getSystemProperties(), this.getSystemPropertyVariables(), this.getUserProperties(), sysProps);
        result.setProperty("basedir", this.getBasedir().getAbsolutePath());
        result.setProperty("user.dir", this.getWorkingDirectory().getAbsolutePath());
        result.setProperty("localRepository", this.getLocalRepository().getBasedir());
        for (final Object o : result.propertiesThatCannotBeSetASystemProperties()) {
            this.getLog().warn(o + " cannot be set as system property, use <argLine>-D" + o + "=...<argLine> instead");
        }
        if (this.getLog().isDebugEnabled()) {
            this.showToLog(result, this.getLog(), "system property");
        }
        return result;
    }
    
    public void showToLog(final SurefireProperties props, final Log log, final String setting) {
        for (final Object key : props.getStringKeySet()) {
            final String value = props.getProperty((String)key);
            log.debug("Setting " + setting + " [" + key + "]=[" + value + "]");
        }
    }
    
    private RunResult executeProvider(final ProviderInfo provider, final DefaultScanResult scanResult) throws MojoExecutionException, MojoFailureException, SurefireExecutionException, SurefireBooterForkException, TestSetFailedException {
        final SurefireProperties effectiveProperties = this.setupProperties();
        final ClassLoaderConfiguration classLoaderConfiguration = this.getClassLoaderConfiguration(this.isForking());
        final RunOrderParameters runOrderParameters = new RunOrderParameters(this.getRunOrder(), this.getStatisticsFileName(this.getConfigChecksum()));
        RunResult result;
        if (this.isNotForking()) {
            createCopyAndReplaceForkNumPlaceholder(effectiveProperties, 1).copyToSystemProperties();
            final InPluginVMSurefireStarter surefireStarter = this.createInprocessStarter(provider, classLoaderConfiguration, runOrderParameters);
            result = surefireStarter.runSuitesInProcess(scanResult);
        }
        else {
            final ForkConfiguration forkConfiguration = this.getForkConfiguration();
            if (this.getLog().isDebugEnabled()) {
                this.showMap(this.getEnvironmentVariables(), "environment variable");
            }
            final Properties originalSystemProperties = (Properties)System.getProperties().clone();
            try {
                final ForkStarter forkStarter = this.createForkStarter(provider, forkConfiguration, classLoaderConfiguration, runOrderParameters, this.getLog());
                result = forkStarter.run(effectiveProperties, scanResult);
            }
            finally {
                System.setProperties(originalSystemProperties);
                this.cleanupForkConfiguration(forkConfiguration);
            }
        }
        return result;
    }
    
    public static SurefireProperties createCopyAndReplaceForkNumPlaceholder(final SurefireProperties effectiveSystemProperties, final int threadNumber) {
        final SurefireProperties filteredProperties = new SurefireProperties((KeyValueSource)effectiveSystemProperties);
        final String threadNumberString = String.valueOf(threadNumber);
        for (final Map.Entry<Object, Object> entry : effectiveSystemProperties.entrySet()) {
            if (entry.getValue() instanceof String) {
                String value = entry.getValue();
                value = value.replace("${surefire.threadNumber}", threadNumberString);
                value = value.replace("${surefire.forkNumber}", threadNumberString);
                filteredProperties.put(entry.getKey(), value);
            }
        }
        return filteredProperties;
    }
    
    protected void cleanupForkConfiguration(final ForkConfiguration forkConfiguration) {
        if (!this.getLog().isDebugEnabled() && forkConfiguration != null) {
            final File tempDirectory = forkConfiguration.getTempDirectory();
            try {
                FileUtils.deleteDirectory(tempDirectory);
            }
            catch (IOException ioe) {
                this.getLog().warn("Could not delete temp direcotry " + tempDirectory + " because " + ioe.getMessage());
            }
        }
    }
    
    protected abstract void handleSummary(final RunResult p0, final Exception p1) throws MojoExecutionException, MojoFailureException;
    
    protected void logReportsDirectory() {
        this.getLog().info(StringUtils.capitalizeFirstLetter(this.getPluginName()) + " report directory: " + this.getReportsDirectory());
    }
    
    final Toolchain getToolchain() {
        Toolchain tc = null;
        if (this.getToolchainManager() != null) {
            tc = this.getToolchainManager().getToolchainFromBuildContext("jdk", this.getSession());
        }
        return tc;
    }
    
    private void convertTestNGParameters() throws MojoExecutionException {
        if (this.getParallel() != null) {
            this.getProperties().setProperty("parallel", this.getParallel());
        }
        this.convertGroupParameters();
        if (this.getThreadCount() > 0) {
            this.getProperties().setProperty("threadcount", Integer.toString(this.getThreadCount()));
        }
        if (this.getObjectFactory() != null) {
            this.getProperties().setProperty("objectfactory", this.getObjectFactory());
        }
        if (this.getTestClassesDirectory() != null) {
            this.getProperties().setProperty("testng.test.classpath", this.getTestClassesDirectory().getAbsolutePath());
        }
        final Artifact testNgArtifact = this.getTestNgArtifact();
        if (testNgArtifact != null) {
            final DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion(testNgArtifact.getVersion());
            this.getProperties().setProperty("testng.configurator", getConfiguratorName(defaultArtifactVersion));
        }
    }
    
    private static String getConfiguratorName(final ArtifactVersion version) throws MojoExecutionException {
        try {
            VersionRange range = VersionRange.createFromVersionSpec("[4.7,5.1]");
            if (range.containsVersion(version)) {
                return "org.apache.maven.surefire.testng.conf.TestNG4751Configurator";
            }
            range = VersionRange.createFromVersionSpec("[5.2]");
            if (range.containsVersion(version)) {
                return "org.apache.maven.surefire.testng.conf.TestNG52Configurator";
            }
            range = VersionRange.createFromVersionSpec("[5.3,6.4]");
            if (range.containsVersion(version)) {
                return "org.apache.maven.surefire.testng.conf.TestNGMapConfigurator";
            }
            range = VersionRange.createFromVersionSpec("[6.5,)");
            if (range.containsVersion(version)) {
                return "org.apache.maven.surefire.testng.conf.TestNG652Configurator";
            }
            throw new MojoExecutionException("Unknown TestNG version " + version);
        }
        catch (InvalidVersionSpecificationException invsex) {
            throw new MojoExecutionException("Bug in plugin. Please report it with the attached stacktrace", invsex);
        }
    }
    
    private void convertGroupParameters() {
        if (this.getExcludedGroups() != null) {
            this.getProperties().setProperty("excludegroups", this.getExcludedGroups());
        }
        if (this.getGroups() != null) {
            this.getProperties().setProperty("groups", this.getGroups());
        }
    }
    
    protected boolean isAnyConcurrencySelected() {
        return this.getParallel() != null && this.getParallel().trim().length() > 0;
    }
    
    protected boolean isAnyGroupsSelected() {
        return this.getGroups() != null || this.getExcludedGroups() != null;
    }
    
    private void convertJunitCoreParameters() throws MojoExecutionException {
        checkThreadCountEntity(this.getThreadCountSuites(), "suites");
        checkThreadCountEntity(this.getThreadCountClasses(), "classes");
        checkThreadCountEntity(this.getThreadCountMethods(), "methods");
        final String usedParallel = (this.getParallel() != null) ? this.getParallel() : "none";
        if (!"none".equals(usedParallel)) {
            this.checkNonForkedThreads(this.parallel);
        }
        final String usedThreadCount = Integer.toString(this.getThreadCount());
        this.getProperties().setProperty("parallel", usedParallel);
        this.getProperties().setProperty("threadcount", usedThreadCount);
        this.getProperties().setProperty("perCoreThreadCount", Boolean.toString(this.getPerCoreThreadCount()));
        this.getProperties().setProperty("useUnlimitedThreads", Boolean.toString(this.getUseUnlimitedThreads()));
        this.getProperties().setProperty("threadcountsuites", Integer.toString(this.getThreadCountSuites()));
        this.getProperties().setProperty("threadcountclasses", Integer.toString(this.getThreadCountClasses()));
        this.getProperties().setProperty("threadcountmethods", Integer.toString(this.getThreadCountMethods()));
        this.getProperties().setProperty("paralleltimeout", Double.toString(this.getParallelTestsTimeoutInSeconds()));
        this.getProperties().setProperty("paralleltimeoutforced", Double.toString(this.getParallelTestsTimeoutForcedInSeconds()));
        this.getProperties().setProperty("paralleloptimization", Boolean.toString(this.isParallelOptimized()));
        final String message = "parallel='" + usedParallel + '\'' + ", perCoreThreadCount=" + this.getPerCoreThreadCount() + ", threadCount=" + usedThreadCount + ", useUnlimitedThreads=" + this.getUseUnlimitedThreads() + ", threadCountSuites=" + this.getThreadCountSuites() + ", threadCountClasses=" + this.getThreadCountClasses() + ", threadCountMethods=" + this.getThreadCountMethods() + ", parallelOptimized=" + this.isParallelOptimized();
        this.getLog().info(message);
    }
    
    private void checkNonForkedThreads(final String parallel) throws MojoExecutionException {
        if ("suites".equals(parallel)) {
            if (!this.getUseUnlimitedThreads() && !(this.getThreadCount() > 0 ^ this.getThreadCountSuites() > 0)) {
                throw new MojoExecutionException("Use threadCount or threadCountSuites > 0 or useUnlimitedThreads=true for parallel='suites'");
            }
            this.setThreadCountClasses(0);
            this.setThreadCountMethods(0);
        }
        else if ("classes".equals(parallel)) {
            if (!this.getUseUnlimitedThreads() && !(this.getThreadCount() > 0 ^ this.getThreadCountClasses() > 0)) {
                throw new MojoExecutionException("Use threadCount or threadCountClasses > 0 or useUnlimitedThreads=true for parallel='classes'");
            }
            this.setThreadCountSuites(0);
            this.setThreadCountMethods(0);
        }
        else if ("methods".equals(parallel)) {
            if (!this.getUseUnlimitedThreads() && !(this.getThreadCount() > 0 ^ this.getThreadCountMethods() > 0)) {
                throw new MojoExecutionException("Use threadCount or threadCountMethods > 0 or useUnlimitedThreads=true for parallel='methods'");
            }
            this.setThreadCountSuites(0);
            this.setThreadCountClasses(0);
        }
        else if ("suitesAndClasses".equals(parallel)) {
            if (!this.getUseUnlimitedThreads() && !this.onlyThreadCount() && (this.getThreadCountSuites() <= 0 || this.getThreadCountClasses() <= 0 || this.getThreadCount() != 0 || this.getThreadCountMethods() != 0) && (this.getThreadCount() <= 0 || this.getThreadCountSuites() <= 0 || this.getThreadCountClasses() <= 0 || this.getThreadCountMethods() != 0) && (this.getThreadCount() <= 0 || this.getThreadCountSuites() <= 0 || this.getThreadCount() <= this.getThreadCountSuites() || this.getThreadCountClasses() != 0 || this.getThreadCountMethods() != 0)) {
                throw new MojoExecutionException("Use useUnlimitedThreads=true, or only threadCount > 0, or (threadCountSuites > 0 and threadCountClasses > 0), or (threadCount > 0 and threadCountSuites > 0 and threadCountClasses > 0) or (threadCount > 0 and threadCountSuites > 0 and threadCount > threadCountSuites) for parallel='suitesAndClasses' or 'both'");
            }
            this.setThreadCountMethods(0);
        }
        else if ("suitesAndMethods".equals(parallel)) {
            if (!this.getUseUnlimitedThreads() && !this.onlyThreadCount() && (this.getThreadCountSuites() <= 0 || this.getThreadCountMethods() <= 0 || this.getThreadCount() != 0 || this.getThreadCountClasses() != 0) && (this.getThreadCount() <= 0 || this.getThreadCountSuites() <= 0 || this.getThreadCountMethods() <= 0 || this.getThreadCountClasses() != 0) && (this.getThreadCount() <= 0 || this.getThreadCountSuites() <= 0 || this.getThreadCount() <= this.getThreadCountSuites() || this.getThreadCountClasses() != 0 || this.getThreadCountMethods() != 0)) {
                throw new MojoExecutionException("Use useUnlimitedThreads=true, or only threadCount > 0, or (threadCountSuites > 0 and threadCountMethods > 0), or (threadCount > 0 and threadCountSuites > 0 and threadCountMethods > 0), or (threadCount > 0 and threadCountSuites > 0 and threadCount > threadCountSuites) for parallel='suitesAndMethods'");
            }
            this.setThreadCountClasses(0);
        }
        else if ("both".equals(parallel) || "classesAndMethods".equals(parallel)) {
            if (!this.getUseUnlimitedThreads() && !this.onlyThreadCount() && (this.getThreadCountClasses() <= 0 || this.getThreadCountMethods() <= 0 || this.getThreadCount() != 0 || this.getThreadCountSuites() != 0) && (this.getThreadCount() <= 0 || this.getThreadCountClasses() <= 0 || this.getThreadCountMethods() <= 0 || this.getThreadCountSuites() != 0) && (this.getThreadCount() <= 0 || this.getThreadCountClasses() <= 0 || this.getThreadCount() <= this.getThreadCountClasses() || this.getThreadCountSuites() != 0 || this.getThreadCountMethods() != 0)) {
                throw new MojoExecutionException("Use useUnlimitedThreads=true, or only threadCount > 0, or (threadCountClasses > 0 and threadCountMethods > 0), or (threadCount > 0 and threadCountClasses > 0 and threadCountMethods > 0), or (threadCount > 0 and threadCountClasses > 0 and threadCount > threadCountClasses) for parallel='both' or parallel='classesAndMethods'");
            }
            this.setThreadCountSuites(0);
        }
        else {
            if (!"all".equals(parallel)) {
                throw new MojoExecutionException("Illegal parallel='" + parallel + "'");
            }
            if (!this.getUseUnlimitedThreads() && !this.onlyThreadCount() && (this.getThreadCountSuites() <= 0 || this.getThreadCountClasses() <= 0 || this.getThreadCountMethods() <= 0) && (this.getThreadCount() <= 0 || this.getThreadCountSuites() <= 0 || this.getThreadCountClasses() <= 0 || this.getThreadCountMethods() != 0 || this.getThreadCount() <= this.getThreadCountSuites() + this.getThreadCountClasses())) {
                throw new MojoExecutionException("Use useUnlimitedThreads=true, or only threadCount > 0, or (threadCountSuites > 0 and threadCountClasses > 0 and threadCountMethods > 0), or every thread-count is specified, or (threadCount > 0 and threadCountSuites > 0 and threadCountClasses > 0 and threadCount > threadCountSuites + threadCountClasses) for parallel='all'");
            }
        }
    }
    
    private boolean onlyThreadCount() {
        return this.getThreadCount() > 0 && this.getThreadCountSuites() == 0 && this.getThreadCountClasses() == 0 && this.getThreadCountMethods() == 0;
    }
    
    private static void checkThreadCountEntity(final int count, final String entity) throws MojoExecutionException {
        if (count < 0) {
            throw new MojoExecutionException("parallel maven execution does not allow negative thread-count" + entity);
        }
    }
    
    private boolean isJunit47Compatible(final Artifact artifact) {
        return this.dependencyResolver.isWithinVersionSpec(artifact, "[4.7,)");
    }
    
    private boolean isAnyJunit4(final Artifact artifact) {
        return this.dependencyResolver.isWithinVersionSpec(artifact, "[4.0,)");
    }
    
    static boolean isForkModeNever(final String forkMode) {
        return "never".equals(forkMode);
    }
    
    boolean isForking() {
        return 0 < this.getEffectiveForkCount();
    }
    
    String getEffectiveForkMode() {
        final String forkMode1 = this.getForkMode();
        if (this.toolchain != null && isForkModeNever(forkMode1)) {
            return "once";
        }
        return ForkConfiguration.getEffectiveForkMode(forkMode1);
    }
    
    private List<RunOrder> getRunOrders() {
        final String runOrderString = this.getRunOrder();
        final RunOrder[] runOrder = (runOrderString == null) ? RunOrder.DEFAULT : RunOrder.valueOfMulti(runOrderString);
        return Arrays.asList(runOrder);
    }
    
    private boolean requiresRunHistory() {
        final List<RunOrder> runOrders = this.getRunOrders();
        return runOrders.contains(RunOrder.BALANCED) || runOrders.contains(RunOrder.FAILEDFIRST);
    }
    
    private boolean getEffectiveFailIfNoTests() {
        if (!this.isSpecificTestSpecified()) {
            return this.getFailIfNoTests() != null && this.getFailIfNoTests();
        }
        if (this.getFailIfNoSpecifiedTests() != null) {
            return this.getFailIfNoSpecifiedTests();
        }
        return this.getFailIfNoTests() == null || this.getFailIfNoTests();
    }
    
    private ProviderConfiguration createProviderConfiguration(final RunOrderParameters runOrderParameters) throws MojoExecutionException, MojoFailureException {
        final ReporterConfiguration reporterConfiguration = new ReporterConfiguration(this.getReportsDirectory(), this.isTrimStackTrace());
        final Artifact testNgArtifact = this.getTestNgArtifact();
        DirectoryScannerParameters directoryScannerParameters = null;
        final boolean isTestNg = testNgArtifact != null;
        final TestArtifactInfo testNg = isTestNg ? new TestArtifactInfo(testNgArtifact.getVersion(), testNgArtifact.getClassifier()) : null;
        final List<File> testXml = (this.getSuiteXmlFiles() != null) ? Arrays.asList(this.getSuiteXmlFiles()) : null;
        final TestRequest testSuiteDefinition = new TestRequest(testXml, this.getTestSourceDirectory(), this.getTest(), this.getTestMethod());
        boolean failIfNoTests;
        if (this.isValidSuiteXmlFileConfig() && this.getTest() == null) {
            failIfNoTests = (this.getFailIfNoTests() != null && this.getFailIfNoTests());
            if (!isTestNg) {
                throw new MojoExecutionException("suiteXmlFiles is configured, but there is no TestNG dependency");
            }
        }
        else {
            if (this.isSpecificTestSpecified()) {
                failIfNoTests = this.getEffectiveFailIfNoTests();
                this.setFailIfNoTests(failIfNoTests);
            }
            else {
                failIfNoTests = (this.getFailIfNoTests() != null && this.getFailIfNoTests());
            }
            final List<String> includes = this.getIncludeList();
            final List<String> excludes = this.getExcludeList();
            final List<String> specificTests = this.getSpecificTests();
            directoryScannerParameters = new DirectoryScannerParameters(this.getTestClassesDirectory(), includes, excludes, specificTests, failIfNoTests, this.getRunOrder());
        }
        final Properties providerProperties = this.getProperties();
        return new ProviderConfiguration(directoryScannerParameters, runOrderParameters, failIfNoTests, reporterConfiguration, testNg, testSuiteDefinition, providerProperties, null, false);
    }
    
    public String getStatisticsFileName(final String configurationHash) {
        return this.getReportsDirectory().getParentFile().getParentFile() + File.separator + ".surefire-" + configurationHash;
    }
    
    StartupConfiguration createStartupConfiguration(final ProviderInfo provider, final ClassLoaderConfiguration classLoaderConfiguration) throws MojoExecutionException, MojoFailureException {
        try {
            provider.addProviderProperties();
            final String providerName = provider.getProviderName();
            Classpath providerClasspath = ClasspathCache.getCachedClassPath(providerName);
            if (providerClasspath == null) {
                providerClasspath = provider.getProviderClasspath();
                ClasspathCache.setCachedClasspath(providerName, providerClasspath);
            }
            final Artifact surefireArtifact = this.getCommonArtifact();
            final Classpath inprocClassPath = providerClasspath.addClassPathElementUrl(surefireArtifact.getFile().getAbsolutePath()).addClassPathElementUrl(this.getApiArtifact().getFile().getAbsolutePath());
            final Classpath testClasspath = this.generateTestClasspath();
            this.getLog().debug(testClasspath.getLogMessage("test"));
            this.getLog().debug(providerClasspath.getLogMessage("provider"));
            this.getLog().debug(testClasspath.getCompactLogMessage("test(compact)"));
            this.getLog().debug(providerClasspath.getCompactLogMessage("provider(compact)"));
            final ClasspathConfiguration classpathConfiguration = new ClasspathConfiguration(testClasspath, providerClasspath, inprocClassPath, this.effectiveIsEnableAssertions(), this.isChildDelegation());
            return new StartupConfiguration(providerName, classpathConfiguration, classLoaderConfiguration, this.isForking(), false);
        }
        catch (ArtifactResolutionException e) {
            throw new MojoExecutionException("Unable to generate classpath: " + e, e);
        }
        catch (ArtifactNotFoundException e2) {
            throw new MojoExecutionException("Unable to generate classpath: " + e2, e2);
        }
        catch (InvalidVersionSpecificationException e3) {
            throw new MojoExecutionException("Unable to generate classpath: " + e3, e3);
        }
    }
    
    private Artifact getCommonArtifact() {
        return this.getPluginArtifactMap().get("org.apache.maven.surefire:maven-surefire-common");
    }
    
    private Artifact getApiArtifact() {
        return this.getPluginArtifactMap().get("org.apache.maven.surefire:surefire-api");
    }
    
    private StartupReportConfiguration getStartupReportConfiguration(final String configChecksum) {
        return new StartupReportConfiguration(this.isUseFile(), this.isPrintSummary(), this.getReportFormat(), this.isRedirectTestOutputToFile(), this.isDisableXmlReport(), this.getReportsDirectory(), this.isTrimStackTrace(), this.getReportNameSuffix(), configChecksum, this.requiresRunHistory());
    }
    
    private boolean isSpecificTestSpecified() {
        return this.getTest() != null;
    }
    
    private boolean isValidSuiteXmlFileConfig() {
        return this.getSuiteXmlFiles() != null && this.getSuiteXmlFiles().length > 0;
    }
    
    @Nonnull
    private List<String> readListFromFile(@Nonnull final File file) {
        this.getLog().debug("Reading list from: " + file);
        if (!file.exists()) {
            throw new RuntimeException("Failed to load list from file: " + file);
        }
        List<String> list;
        try {
            list = FileUtils.loadFile(file);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load list from file: " + file, e);
        }
        if (this.getLog().isDebugEnabled()) {
            this.getLog().debug("List contents:");
            for (final String entry : list) {
                this.getLog().debug("  " + entry);
            }
        }
        return list;
    }
    
    private void maybeAppendList(final List<String> base, final List<String> list) {
        if (list != null) {
            base.addAll(list);
        }
    }
    
    @Nonnull
    private List<String> getExcludeList() {
        List<String> excludes = null;
        if (this.isSpecificTestSpecified()) {
            excludes = new ArrayList<String>();
        }
        else {
            if (this.getExcludesFile() != null) {
                excludes = this.readListFromFile(this.getExcludesFile());
            }
            if (excludes == null) {
                excludes = this.getExcludes();
            }
            else {
                this.maybeAppendList(excludes, this.getExcludes());
            }
            if (excludes == null || excludes.size() == 0) {
                excludes = Arrays.asList("**/*$*");
            }
        }
        return this.filterNulls(excludes);
    }
    
    private List<String> getIncludeList() {
        List<String> includes = null;
        if (this.isSpecificTestSpecified() && !this.isMultipleExecutionBlocksDetected()) {
            includes = this.getSpecificTests();
        }
        else {
            if (this.getIncludesFile() != null) {
                includes = this.readListFromFile(this.getIncludesFile());
            }
            if (includes == null) {
                includes = this.getIncludes();
            }
            else {
                this.maybeAppendList(includes, this.getIncludes());
            }
        }
        if (includes == null || includes.size() == 0) {
            includes = Arrays.asList(this.getDefaultIncludes());
        }
        return this.filterNulls(includes);
    }
    
    @Nonnull
    private List<String> filterNulls(@Nonnull final List<String> toFilter) {
        final List<String> result = new ArrayList<String>(toFilter.size());
        for (final String item : toFilter) {
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }
    
    private boolean isMultipleExecutionBlocksDetected() {
        final MavenProject project = this.getProject();
        if (project != null) {
            final String key = this.getPluginDescriptor().getPluginLookupKey();
            final Plugin plugin = project.getBuild().getPluginsAsMap().get(key);
            if (plugin != null) {
                final List executions = plugin.getExecutions();
                return executions != null && executions.size() > 1;
            }
        }
        return false;
    }
    
    private List<String> getSpecificTests() {
        if (!this.isSpecificTestSpecified()) {
            return Collections.emptyList();
        }
        final List<String> specificTests = new ArrayList<String>();
        final String[] arr$;
        final String[] testRegexes = arr$ = StringUtils.split(this.getTest(), ",");
        for (String testRegex : arr$) {
            final String testRegexe = testRegex;
            if (testRegex.endsWith(".java")) {
                testRegex = testRegex.substring(0, testRegex.length() - 5);
            }
            testRegex = testRegex.replace('.', '/');
            specificTests.add("**/" + testRegex + ".java");
        }
        return specificTests;
    }
    
    private Artifact getTestNgArtifact() throws MojoExecutionException {
        final Artifact artifact = this.getProjectArtifactMap().get(this.getTestNGArtifactName());
        if (artifact != null) {
            final VersionRange range = this.createVersionRange();
            if (!range.containsVersion(new DefaultArtifactVersion(artifact.getVersion()))) {
                throw new MojoExecutionException("TestNG support requires version 4.7 or above. You have declared version " + artifact.getVersion());
            }
        }
        return artifact;
    }
    
    private VersionRange createVersionRange() {
        try {
            return VersionRange.createFromVersionSpec("[4.7,)");
        }
        catch (InvalidVersionSpecificationException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Artifact getJunitArtifact() {
        return this.getProjectArtifactMap().get(this.getJunitArtifactName());
    }
    
    private Artifact getJunitDepArtifact() {
        return this.getProjectArtifactMap().get("junit:junit-dep");
    }
    
    protected ForkStarter createForkStarter(final ProviderInfo provider, final ForkConfiguration forkConfiguration, final ClassLoaderConfiguration classLoaderConfiguration, final RunOrderParameters runOrderParameters, final Log log) throws MojoExecutionException, MojoFailureException {
        final StartupConfiguration startupConfiguration = this.createStartupConfiguration(provider, classLoaderConfiguration);
        final String configChecksum = this.getConfigChecksum();
        final StartupReportConfiguration startupReportConfiguration = this.getStartupReportConfiguration(configChecksum);
        final ProviderConfiguration providerConfiguration = this.createProviderConfiguration(runOrderParameters);
        return new ForkStarter(providerConfiguration, startupConfiguration, forkConfiguration, this.getForkedProcessTimeoutInSeconds(), startupReportConfiguration, log);
    }
    
    protected InPluginVMSurefireStarter createInprocessStarter(final ProviderInfo provider, final ClassLoaderConfiguration classLoaderConfiguration, final RunOrderParameters runOrderParameters) throws MojoExecutionException, MojoFailureException {
        final StartupConfiguration startupConfiguration = this.createStartupConfiguration(provider, classLoaderConfiguration);
        final String configChecksum = this.getConfigChecksum();
        final StartupReportConfiguration startupReportConfiguration = this.getStartupReportConfiguration(configChecksum);
        final ProviderConfiguration providerConfiguration = this.createProviderConfiguration(runOrderParameters);
        return new InPluginVMSurefireStarter(startupConfiguration, providerConfiguration, startupReportConfiguration);
    }
    
    protected ForkConfiguration getForkConfiguration() {
        final File tmpDir = this.getSurefireTempDir();
        tmpDir.mkdirs();
        final Artifact shadeFire = this.getPluginArtifactMap().get("org.apache.maven.surefire:surefire-shadefire");
        final Classpath bootClasspathConfiguration = this.getArtifactClasspath((shadeFire != null) ? shadeFire : this.surefireBooterArtifact);
        return new ForkConfiguration(bootClasspathConfiguration, tmpDir, this.getEffectiveDebugForkedProcess(), this.getEffectiveJvm(), (this.getWorkingDirectory() != null) ? this.getWorkingDirectory() : this.getBasedir(), this.getProject().getModel().getProperties(), this.getArgLine(), this.getEnvironmentVariables(), this.getLog().isDebugEnabled(), this.getEffectiveForkCount(), this.reuseForks);
    }
    
    private void convertDeprecatedForkMode() {
        final String effectiveForkMode = this.getEffectiveForkMode();
        if ("perthread".equals(effectiveForkMode)) {
            this.forkCount = String.valueOf(this.threadCount);
        }
        else if ("never".equals(effectiveForkMode)) {
            this.forkCount = "0";
        }
        else if ("always".equals(effectiveForkMode)) {
            this.forkCount = "1";
            this.reuseForks = false;
        }
        if (!"once".equals(this.getForkMode())) {
            this.getLog().warn("The parameter forkMode is deprecated since version 2.14. Use forkCount and reuseForks instead.");
        }
    }
    
    protected int getEffectiveForkCount() {
        if (this.effectiveForkCount < 0) {
            try {
                this.effectiveForkCount = this.convertWithCoreCount(this.forkCount);
            }
            catch (NumberFormatException ex) {}
            if (this.effectiveForkCount < 0) {
                throw new IllegalArgumentException("Fork count " + this.forkCount.trim() + " is not a legal value.");
            }
        }
        return this.effectiveForkCount;
    }
    
    protected int convertWithCoreCount(final String count) {
        final String trimmed = count.trim();
        if (!trimmed.endsWith("C")) {
            return Integer.parseInt(trimmed);
        }
        final double multiplier = Double.parseDouble(trimmed.substring(0, trimmed.length() - 1));
        final double calculated = multiplier * Runtime.getRuntime().availableProcessors();
        if (calculated > 0.0) {
            return Math.max((int)calculated, 1);
        }
        return 0;
    }
    
    private String getEffectiveDebugForkedProcess() {
        final String debugForkedProcess = this.getDebugForkedProcess();
        if ("true".equals(debugForkedProcess)) {
            return "-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005";
        }
        return debugForkedProcess;
    }
    
    private String getEffectiveJvm() {
        String jvmToUse = this.getJvm();
        if (this.toolchain != null && jvmToUse == null) {
            jvmToUse = this.toolchain.findTool("java");
        }
        if (StringUtils.isEmpty(jvmToUse)) {
            jvmToUse = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            this.getLog().debug("Using JVM: " + jvmToUse);
        }
        return jvmToUse;
    }
    
    private Artifact getSurefireBooterArtifact() {
        final Artifact artifact = this.getPluginArtifactMap().get("org.apache.maven.surefire:surefire-booter");
        if (artifact == null) {
            throw new RuntimeException("Unable to locate surefire-booter in the list of plugin artifacts");
        }
        artifact.isSnapshot();
        return artifact;
    }
    
    private File getSurefireTempDir() {
        return new File(this.getReportsDirectory().getParentFile(), "surefire");
    }
    
    private String getConfigChecksum() {
        final ChecksumCalculator checksum = new ChecksumCalculator();
        checksum.add(this.getPluginName());
        checksum.add(this.isSkipTests());
        checksum.add(this.isSkipExec());
        checksum.add(this.isSkip());
        checksum.add(this.getTestClassesDirectory());
        checksum.add(this.getClassesDirectory());
        checksum.add(this.getClasspathDependencyExcludes());
        checksum.add(this.getClasspathDependencyScopeExclude());
        checksum.add(this.getAdditionalClasspathElements());
        checksum.add(this.getReportsDirectory());
        checksum.add(this.getTestSourceDirectory());
        checksum.add(this.getTest());
        checksum.add(this.getIncludes());
        checksum.add(this.getExcludes());
        checksum.add(this.getLocalRepository());
        checksum.add(this.getSystemProperties());
        checksum.add(this.getSystemPropertyVariables());
        checksum.add(this.getSystemPropertiesFile());
        checksum.add(this.getProperties());
        checksum.add(this.isPrintSummary());
        checksum.add(this.getReportFormat());
        checksum.add(this.getReportNameSuffix());
        checksum.add(this.isUseFile());
        checksum.add(this.isRedirectTestOutputToFile());
        checksum.add(this.getForkMode());
        checksum.add(this.getForkCount());
        checksum.add(this.isReuseForks());
        checksum.add(this.getJvm());
        checksum.add(this.getArgLine());
        checksum.add(this.getDebugForkedProcess());
        checksum.add(this.getForkedProcessTimeoutInSeconds());
        checksum.add(this.getParallelTestsTimeoutInSeconds());
        checksum.add(this.getParallelTestsTimeoutForcedInSeconds());
        checksum.add(this.getEnvironmentVariables());
        checksum.add(this.getWorkingDirectory());
        checksum.add(this.isChildDelegation());
        checksum.add(this.getGroups());
        checksum.add(this.getExcludedGroups());
        checksum.add(this.getSuiteXmlFiles());
        checksum.add(this.getJunitArtifact());
        checksum.add(this.getTestNGArtifactName());
        checksum.add(this.getThreadCount());
        checksum.add(this.getThreadCountSuites());
        checksum.add(this.getThreadCountClasses());
        checksum.add(this.getThreadCountMethods());
        checksum.add(this.getPerCoreThreadCount());
        checksum.add(this.getUseUnlimitedThreads());
        checksum.add(this.getParallel());
        checksum.add(this.isParallelOptimized());
        checksum.add(this.isTrimStackTrace());
        checksum.add(this.getRemoteRepositories());
        checksum.add(this.isDisableXmlReport());
        checksum.add(this.isUseSystemClassLoader());
        checksum.add(this.isUseManifestOnlyJar());
        checksum.add(this.isEnableAssertions());
        checksum.add(this.getObjectFactory());
        checksum.add(this.getFailIfNoTests());
        checksum.add(this.getRunOrder());
        checksum.add(this.getDependenciesToScan());
        this.addPluginSpecificChecksumItems(checksum);
        return checksum.getSha1();
    }
    
    protected void addPluginSpecificChecksumItems(final ChecksumCalculator checksum) {
    }
    
    protected boolean hasExecutedBefore() {
        final String configChecksum = this.getConfigChecksum();
        final Map<String, String> pluginContext = (Map<String, String>)this.getPluginContext();
        if (pluginContext.containsKey(configChecksum)) {
            this.getLog().info("Skipping execution of surefire because it has already been run for this configuration");
            return true;
        }
        pluginContext.put(configChecksum, configChecksum);
        return false;
    }
    
    protected ClassLoaderConfiguration getClassLoaderConfiguration(final boolean isForking) {
        return isForking ? new ClassLoaderConfiguration(this.isUseSystemClassLoader(), this.isUseManifestOnlyJar()) : new ClassLoaderConfiguration(false, false);
    }
    
    protected abstract String[] getDefaultIncludes();
    
    Classpath generateTestClasspath() throws InvalidVersionSpecificationException, MojoFailureException, ArtifactResolutionException, ArtifactNotFoundException, MojoExecutionException {
        final List<String> classpath = new ArrayList<String>(2 + this.getProject().getArtifacts().size());
        classpath.add(this.getTestClassesDirectory().getAbsolutePath());
        classpath.add(this.getClassesDirectory().getAbsolutePath());
        Set<Artifact> classpathArtifacts = (Set<Artifact>)this.getProject().getArtifacts();
        if (this.getClasspathDependencyScopeExclude() != null && !this.getClasspathDependencyScopeExclude().equals("")) {
            final ArtifactFilter dependencyFilter = new ScopeArtifactFilter(this.getClasspathDependencyScopeExclude());
            classpathArtifacts = this.filterArtifacts(classpathArtifacts, dependencyFilter);
        }
        if (this.getClasspathDependencyExcludes() != null) {
            final ArtifactFilter dependencyFilter = new PatternIncludesArtifactFilter(Arrays.asList(this.getClasspathDependencyExcludes()));
            classpathArtifacts = this.filterArtifacts(classpathArtifacts, dependencyFilter);
        }
        for (final Artifact artifact : classpathArtifacts) {
            if (artifact.getArtifactHandler().isAddedToClasspath()) {
                final File file = artifact.getFile();
                if (file == null) {
                    continue;
                }
                classpath.add(file.getPath());
            }
        }
        if (this.getAdditionalClasspathElements() != null) {
            for (final String classpathElement : this.getAdditionalClasspathElements()) {
                if (classpathElement != null) {
                    classpath.add(classpathElement);
                }
            }
        }
        if (this.getTestNgArtifact() != null) {
            this.addTestNgUtilsArtifacts(classpath);
        }
        return new Classpath(classpath);
    }
    
    void addTestNgUtilsArtifacts(final List<String> classpath) throws ArtifactResolutionException, ArtifactNotFoundException {
        final Artifact surefireArtifact = this.getPluginArtifactMap().get("org.apache.maven.surefire:surefire-booter");
        final String surefireVersion = surefireArtifact.getBaseVersion();
        final Artifact[] arr$;
        final Artifact[] extraTestNgArtifacts = arr$ = new Artifact[] { this.getArtifactFactory().createArtifact("org.apache.maven.surefire", "surefire-testng-utils", surefireVersion, "runtime", "jar"), this.getArtifactFactory().createArtifact("org.apache.maven.surefire", "surefire-grouper", surefireVersion, "runtime", "jar") };
        for (final Artifact artifact : arr$) {
            this.getArtifactResolver().resolve(artifact, this.getRemoteRepositories(), this.getLocalRepository());
            final String path = artifact.getFile().getPath();
            classpath.add(path);
        }
    }
    
    private Set<Artifact> filterArtifacts(final Set<Artifact> artifacts, final ArtifactFilter filter) {
        final Set<Artifact> filteredArtifacts = new LinkedHashSet<Artifact>();
        for (final Artifact artifact : artifacts) {
            if (!filter.include(artifact)) {
                filteredArtifacts.add(artifact);
            }
        }
        return filteredArtifacts;
    }
    
    private void showMap(final Map<?, ?> map, final String setting) {
        for (final Object o : map.keySet()) {
            final String key = (String)o;
            final String value = (String)map.get(key);
            this.getLog().debug("Setting " + setting + " [" + key + "]=[" + value + "]");
        }
    }
    
    private ArtifactResolutionResult resolveArtifact(final Artifact filteredArtifact, final Artifact providerArtifact) {
        ArtifactFilter filter = null;
        if (filteredArtifact != null) {
            filter = new ExcludesArtifactFilter(Collections.singletonList(filteredArtifact.getGroupId() + ":" + filteredArtifact.getArtifactId()));
        }
        final Artifact originatingArtifact = this.getArtifactFactory().createBuildArtifact("dummy", "dummy", "1.0", "jar");
        try {
            return this.getArtifactResolver().resolveTransitively(Collections.singleton(providerArtifact), originatingArtifact, this.getLocalRepository(), this.getRemoteRepositories(), this.getMetadataSource(), filter);
        }
        catch (ArtifactResolutionException e) {
            throw new RuntimeException(e);
        }
        catch (ArtifactNotFoundException e2) {
            throw new RuntimeException(e2);
        }
    }
    
    private Classpath getArtifactClasspath(final Artifact surefireArtifact) {
        Classpath existing = ClasspathCache.getCachedClassPath(surefireArtifact.getArtifactId());
        if (existing == null) {
            final ArtifactResolutionResult result = this.resolveArtifact(null, surefireArtifact);
            final List<String> items = new ArrayList<String>();
            for (final Object o : result.getArtifacts()) {
                final Artifact artifact = (Artifact)o;
                this.getLog().debug("Adding to " + this.getPluginName() + " booter test classpath: " + artifact.getFile().getAbsolutePath() + " Scope: " + artifact.getScope());
                items.add(artifact.getFile().getAbsolutePath());
            }
            existing = new Classpath(items);
            ClasspathCache.setCachedClasspath(surefireArtifact.getArtifactId(), existing);
        }
        return existing;
    }
    
    private Properties getUserProperties() {
        Properties props = null;
        try {
            final Method getUserProperties = this.getSession().getClass().getMethod("getUserProperties", (Class<?>[])new Class[0]);
            props = (Properties)getUserProperties.invoke(this.getSession(), new Object[0]);
        }
        catch (Exception e) {
            final String msg = "Build uses Maven 2.0.x, cannot propagate system properties from command line to tests (cf. SUREFIRE-121)";
            if (this.getLog().isDebugEnabled()) {
                this.getLog().warn(msg, e);
            }
            else {
                this.getLog().warn(msg);
            }
        }
        if (props == null) {
            props = new Properties();
        }
        return props;
    }
    
    void ensureWorkingDirectoryExists() throws MojoFailureException {
        if (this.getWorkingDirectory() == null) {
            throw new MojoFailureException("workingDirectory cannot be null");
        }
        if (!this.getWorkingDirectory().exists() && !this.getWorkingDirectory().mkdirs()) {
            throw new MojoFailureException("Cannot create workingDirectory " + this.getWorkingDirectory());
        }
        if (!this.getWorkingDirectory().isDirectory()) {
            throw new MojoFailureException("workingDirectory " + this.getWorkingDirectory() + " exists and is not a directory");
        }
    }
    
    void ensureParallelRunningCompatibility() throws MojoFailureException {
        if (this.isMavenParallel() && this.isNotForking()) {
            throw new MojoFailureException("parallel maven execution is not compatible with surefire forkCount 0");
        }
    }
    
    void ensureThreadCountWithPerThread() throws MojoFailureException {
        if ("perthread".equals(this.getEffectiveForkMode()) && this.getThreadCount() < 1) {
            throw new MojoFailureException("Fork mode perthread requires a thread count");
        }
    }
    
    void warnIfUselessUseSystemClassLoaderParameter() {
        if (this.isUseSystemClassLoader() && this.isNotForking()) {
            this.getLog().warn("useSystemClassloader setting has no effect when not forking");
        }
    }
    
    private boolean isNotForking() {
        return !this.isForking();
    }
    
    void warnIfDefunctGroupsCombinations() throws MojoFailureException, MojoExecutionException {
        if (!this.isAnyGroupsSelected()) {
            return;
        }
        if (this.getTestNgArtifact() != null) {
            return;
        }
        final Artifact junitArtifact = this.getJunitArtifact();
        final boolean junit47Compatible = this.isJunit47Compatible(junitArtifact);
        if (junit47Compatible) {
            return;
        }
        if (junitArtifact != null) {
            throw new MojoFailureException("groups/excludedGroups are specified but JUnit version on classpath is too old to support groups. Check your dependency:tree to see if your project is picking up an old junit version");
        }
        throw new MojoFailureException("groups/excludedGroups require TestNG or JUnit48+ on project test classpath");
    }
    
    public abstract List<String> getIncludes();
    
    public File getIncludesFile() {
        return this.includesFile;
    }
    
    public abstract void setIncludes(final List<String> p0);
    
    public List<String> getExcludes() {
        return this.excludes;
    }
    
    public File getExcludesFile() {
        return this.excludesFile;
    }
    
    public void setExcludes(final List<String> excludes) {
        this.excludes = excludes;
    }
    
    public ArtifactRepository getLocalRepository() {
        return this.localRepository;
    }
    
    public void setLocalRepository(final ArtifactRepository localRepository) {
        this.localRepository = localRepository;
    }
    
    public Properties getSystemProperties() {
        return this.systemProperties;
    }
    
    public void setSystemProperties(final Properties systemProperties) {
        this.systemProperties = systemProperties;
    }
    
    public Map<String, String> getSystemPropertyVariables() {
        return this.systemPropertyVariables;
    }
    
    public void setSystemPropertyVariables(final Map<String, String> systemPropertyVariables) {
        this.systemPropertyVariables = systemPropertyVariables;
    }
    
    public File getSystemPropertiesFile() {
        return this.systemPropertiesFile;
    }
    
    public void setSystemPropertiesFile(final File systemPropertiesFile) {
        this.systemPropertiesFile = systemPropertiesFile;
    }
    
    public Properties getProperties() {
        return this.properties;
    }
    
    public void setProperties(final Properties properties) {
        this.properties = properties;
    }
    
    public Map<String, Artifact> getPluginArtifactMap() {
        return this.pluginArtifactMap;
    }
    
    public void setPluginArtifactMap(final Map<String, Artifact> pluginArtifactMap) {
        this.pluginArtifactMap = pluginArtifactMap;
    }
    
    public Map<String, Artifact> getProjectArtifactMap() {
        return this.projectArtifactMap;
    }
    
    public void setProjectArtifactMap(final Map<String, Artifact> projectArtifactMap) {
        this.projectArtifactMap = projectArtifactMap;
    }
    
    public String getReportNameSuffix() {
        return this.reportNameSuffix;
    }
    
    public void setReportNameSuffix(final String reportNameSuffix) {
        this.reportNameSuffix = reportNameSuffix;
    }
    
    public boolean isRedirectTestOutputToFile() {
        return this.redirectTestOutputToFile;
    }
    
    public void setRedirectTestOutputToFile(final boolean redirectTestOutputToFile) {
        this.redirectTestOutputToFile = redirectTestOutputToFile;
    }
    
    public Boolean getFailIfNoTests() {
        return this.failIfNoTests;
    }
    
    public void setFailIfNoTests(final Boolean failIfNoTests) {
        this.failIfNoTests = failIfNoTests;
    }
    
    public String getForkMode() {
        return this.forkMode;
    }
    
    public void setForkMode(final String forkMode) {
        this.forkMode = forkMode;
    }
    
    public String getJvm() {
        return this.jvm;
    }
    
    public String getArgLine() {
        return this.argLine;
    }
    
    public void setArgLine(final String argLine) {
        this.argLine = argLine;
    }
    
    public Map<String, String> getEnvironmentVariables() {
        return this.environmentVariables;
    }
    
    public void setEnvironmentVariables(final Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }
    
    public File getWorkingDirectory() {
        return this.workingDirectory;
    }
    
    public void setWorkingDirectory(final File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
    
    public boolean isChildDelegation() {
        return this.childDelegation;
    }
    
    public void setChildDelegation(final boolean childDelegation) {
        this.childDelegation = childDelegation;
    }
    
    public String getGroups() {
        return this.groups;
    }
    
    public void setGroups(final String groups) {
        this.groups = groups;
    }
    
    public String getExcludedGroups() {
        return this.excludedGroups;
    }
    
    public void setExcludedGroups(final String excludedGroups) {
        this.excludedGroups = excludedGroups;
    }
    
    public File[] getSuiteXmlFiles() {
        return this.suiteXmlFiles;
    }
    
    public void setSuiteXmlFiles(final File[] suiteXmlFiles) {
        this.suiteXmlFiles = suiteXmlFiles;
    }
    
    public String getJunitArtifactName() {
        return this.junitArtifactName;
    }
    
    public void setJunitArtifactName(final String junitArtifactName) {
        this.junitArtifactName = junitArtifactName;
    }
    
    public String getTestNGArtifactName() {
        return this.testNGArtifactName;
    }
    
    public void setTestNGArtifactName(final String testNGArtifactName) {
        this.testNGArtifactName = testNGArtifactName;
    }
    
    public int getThreadCount() {
        return this.threadCount;
    }
    
    public void setThreadCount(final int threadCount) {
        this.threadCount = threadCount;
    }
    
    public boolean getPerCoreThreadCount() {
        return this.perCoreThreadCount;
    }
    
    public void setPerCoreThreadCount(final boolean perCoreThreadCount) {
        this.perCoreThreadCount = perCoreThreadCount;
    }
    
    public boolean getUseUnlimitedThreads() {
        return this.useUnlimitedThreads;
    }
    
    public void setUseUnlimitedThreads(final boolean useUnlimitedThreads) {
        this.useUnlimitedThreads = useUnlimitedThreads;
    }
    
    public String getParallel() {
        return this.parallel;
    }
    
    public void setParallel(final String parallel) {
        this.parallel = parallel;
    }
    
    public boolean isParallelOptimized() {
        return this.parallelOptimized;
    }
    
    public void setParallelOptimized(final boolean parallelOptimized) {
        this.parallelOptimized = parallelOptimized;
    }
    
    public int getThreadCountSuites() {
        return this.threadCountSuites;
    }
    
    public void setThreadCountSuites(final int threadCountSuites) {
        this.threadCountSuites = threadCountSuites;
    }
    
    public int getThreadCountClasses() {
        return this.threadCountClasses;
    }
    
    public void setThreadCountClasses(final int threadCountClasses) {
        this.threadCountClasses = threadCountClasses;
    }
    
    public int getThreadCountMethods() {
        return this.threadCountMethods;
    }
    
    public void setThreadCountMethods(final int threadCountMethods) {
        this.threadCountMethods = threadCountMethods;
    }
    
    public boolean isTrimStackTrace() {
        return this.trimStackTrace;
    }
    
    public void setTrimStackTrace(final boolean trimStackTrace) {
        this.trimStackTrace = trimStackTrace;
    }
    
    public ArtifactResolver getArtifactResolver() {
        return this.artifactResolver;
    }
    
    public void setArtifactResolver(final ArtifactResolver artifactResolver) {
        this.artifactResolver = artifactResolver;
    }
    
    public ArtifactFactory getArtifactFactory() {
        return this.artifactFactory;
    }
    
    public void setArtifactFactory(final ArtifactFactory artifactFactory) {
        this.artifactFactory = artifactFactory;
    }
    
    public List<ArtifactRepository> getRemoteRepositories() {
        return this.remoteRepositories;
    }
    
    public void setRemoteRepositories(final List<ArtifactRepository> remoteRepositories) {
        this.remoteRepositories = remoteRepositories;
    }
    
    public ArtifactMetadataSource getMetadataSource() {
        return this.metadataSource;
    }
    
    public void setMetadataSource(final ArtifactMetadataSource metadataSource) {
        this.metadataSource = metadataSource;
    }
    
    public boolean isDisableXmlReport() {
        return this.disableXmlReport;
    }
    
    public void setDisableXmlReport(final boolean disableXmlReport) {
        this.disableXmlReport = disableXmlReport;
    }
    
    public boolean isEnableAssertions() {
        return this.enableAssertions;
    }
    
    public boolean effectiveIsEnableAssertions() {
        if (this.getArgLine() != null) {
            final List<String> args = Arrays.asList(this.getArgLine().split(" "));
            if (args.contains("-da") || args.contains("-disableassertions")) {
                return false;
            }
        }
        return this.isEnableAssertions();
    }
    
    public void setEnableAssertions(final boolean enableAssertions) {
        this.enableAssertions = enableAssertions;
    }
    
    public MavenSession getSession() {
        return this.session;
    }
    
    public void setSession(final MavenSession session) {
        this.session = session;
    }
    
    public String getObjectFactory() {
        return this.objectFactory;
    }
    
    public void setObjectFactory(final String objectFactory) {
        this.objectFactory = objectFactory;
    }
    
    public ToolchainManager getToolchainManager() {
        return this.toolchainManager;
    }
    
    public void setToolchainManager(final ToolchainManager toolchainManager) {
        this.toolchainManager = toolchainManager;
    }
    
    public boolean isMavenParallel() {
        return this.parallelMavenExecution != null && this.parallelMavenExecution;
    }
    
    public String getRunOrder() {
        return this.runOrder;
    }
    
    public void setRunOrder(final String runOrder) {
        this.runOrder = runOrder;
    }
    
    public String[] getDependenciesToScan() {
        return this.dependenciesToScan;
    }
    
    public void setDependenciesToScan(final String[] dependenciesToScan) {
        this.dependenciesToScan = dependenciesToScan;
    }
    
    public PluginDescriptor getPluginDescriptor() {
        return this.pluginDescriptor;
    }
    
    public MavenProject getProject() {
        return this.project;
    }
    
    public void setProject(final MavenProject project) {
        this.project = project;
    }
    
    public File getTestSourceDirectory() {
        return this.testSourceDirectory;
    }
    
    public void setTestSourceDirectory(final File testSourceDirectory) {
        this.testSourceDirectory = testSourceDirectory;
    }
    
    public String getForkCount() {
        return this.forkCount;
    }
    
    public boolean isReuseForks() {
        return this.reuseForks;
    }
    
    public String[] getAdditionalClasspathElements() {
        return this.additionalClasspathElements;
    }
    
    public void setAdditionalClasspathElements(final String[] additionalClasspathElements) {
        this.additionalClasspathElements = additionalClasspathElements;
    }
    
    public String[] getClasspathDependencyExcludes() {
        return this.classpathDependencyExcludes;
    }
    
    public void setClasspathDependencyExcludes(final String[] classpathDependencyExcludes) {
        this.classpathDependencyExcludes = classpathDependencyExcludes;
    }
    
    public String getClasspathDependencyScopeExclude() {
        return this.classpathDependencyScopeExclude;
    }
    
    public void setClasspathDependencyScopeExclude(final String classpathDependencyScopeExclude) {
        this.classpathDependencyScopeExclude = classpathDependencyScopeExclude;
    }
    
    class TestNgProviderInfo implements ProviderInfo
    {
        private final Artifact testNgArtifact;
        
        TestNgProviderInfo(final Artifact testNgArtifact) {
            this.testNgArtifact = testNgArtifact;
        }
        
        @Nonnull
        public String getProviderName() {
            return "org.apache.maven.surefire.testng.TestNGProvider";
        }
        
        public boolean isApplicable() {
            return this.testNgArtifact != null;
        }
        
        public void addProviderProperties() throws MojoExecutionException {
            AbstractSurefireMojo.this.convertTestNGParameters();
        }
        
        public Classpath getProviderClasspath() throws ArtifactResolutionException, ArtifactNotFoundException {
            final Artifact surefireArtifact = AbstractSurefireMojo.this.getPluginArtifactMap().get("org.apache.maven.surefire:surefire-booter");
            return AbstractSurefireMojo.this.dependencyResolver.getProviderClasspath("surefire-testng", surefireArtifact.getBaseVersion(), this.testNgArtifact);
        }
    }
    
    class JUnit3ProviderInfo implements ProviderInfo
    {
        @Nonnull
        public String getProviderName() {
            return "org.apache.maven.surefire.junit.JUnit3Provider";
        }
        
        public boolean isApplicable() {
            return true;
        }
        
        public void addProviderProperties() throws MojoExecutionException {
        }
        
        public Classpath getProviderClasspath() throws ArtifactResolutionException, ArtifactNotFoundException {
            return AbstractSurefireMojo.this.dependencyResolver.getProviderClasspath("surefire-junit3", AbstractSurefireMojo.this.surefireBooterArtifact.getBaseVersion(), null);
        }
    }
    
    class JUnit4ProviderInfo implements ProviderInfo
    {
        private final Artifact junitArtifact;
        private final Artifact junitDepArtifact;
        
        JUnit4ProviderInfo(final Artifact junitArtifact, final Artifact junitDepArtifact) {
            this.junitArtifact = junitArtifact;
            this.junitDepArtifact = junitDepArtifact;
        }
        
        @Nonnull
        public String getProviderName() {
            return "org.apache.maven.surefire.junit4.JUnit4Provider";
        }
        
        public boolean isApplicable() {
            return this.junitDepArtifact != null || AbstractSurefireMojo.this.isAnyJunit4(this.junitArtifact);
        }
        
        public void addProviderProperties() throws MojoExecutionException {
        }
        
        public Classpath getProviderClasspath() throws ArtifactResolutionException, ArtifactNotFoundException {
            return AbstractSurefireMojo.this.dependencyResolver.getProviderClasspath("surefire-junit4", AbstractSurefireMojo.this.surefireBooterArtifact.getBaseVersion(), null);
        }
    }
    
    class JUnitCoreProviderInfo implements ProviderInfo
    {
        private final Artifact junitArtifact;
        private final Artifact junitDepArtifact;
        
        JUnitCoreProviderInfo(final Artifact junitArtifact, final Artifact junitDepArtifact) {
            this.junitArtifact = junitArtifact;
            this.junitDepArtifact = junitDepArtifact;
        }
        
        @Nonnull
        public String getProviderName() {
            return "org.apache.maven.surefire.junitcore.JUnitCoreProvider";
        }
        
        private boolean is47CompatibleJunitDep() {
            return this.junitDepArtifact != null && AbstractSurefireMojo.this.isJunit47Compatible(this.junitDepArtifact);
        }
        
        public boolean isApplicable() {
            final boolean isJunitArtifact47 = AbstractSurefireMojo.this.isAnyJunit4(this.junitArtifact) && AbstractSurefireMojo.this.isJunit47Compatible(this.junitArtifact);
            final boolean isAny47ProvidersForcers = AbstractSurefireMojo.this.isAnyConcurrencySelected() || AbstractSurefireMojo.this.isAnyGroupsSelected();
            return isAny47ProvidersForcers && (isJunitArtifact47 || this.is47CompatibleJunitDep());
        }
        
        public void addProviderProperties() throws MojoExecutionException {
            AbstractSurefireMojo.this.convertJunitCoreParameters();
            AbstractSurefireMojo.this.convertGroupParameters();
        }
        
        public Classpath getProviderClasspath() throws ArtifactResolutionException, ArtifactNotFoundException {
            return AbstractSurefireMojo.this.dependencyResolver.getProviderClasspath("surefire-junit47", AbstractSurefireMojo.this.surefireBooterArtifact.getBaseVersion(), null);
        }
    }
    
    public class DynamicProviderInfo implements ConfigurableProviderInfo
    {
        final String providerName;
        
        DynamicProviderInfo(final String providerName) {
            this.providerName = providerName;
        }
        
        public ProviderInfo instantiate(final String providerName) {
            return new DynamicProviderInfo(providerName);
        }
        
        @Nonnull
        public String getProviderName() {
            return this.providerName;
        }
        
        public boolean isApplicable() {
            return true;
        }
        
        public void addProviderProperties() throws MojoExecutionException {
            AbstractSurefireMojo.this.convertJunitCoreParameters();
            AbstractSurefireMojo.this.convertTestNGParameters();
        }
        
        public Classpath getProviderClasspath() throws ArtifactResolutionException, ArtifactNotFoundException {
            final Map<String, Artifact> pluginArtifactMap = AbstractSurefireMojo.this.getPluginArtifactMap();
            final Artifact plugin = pluginArtifactMap.get("org.apache.maven.plugins:maven-surefire-plugin");
            return AbstractSurefireMojo.this.dependencyResolver.addProviderToClasspath(pluginArtifactMap, plugin);
        }
    }
}
