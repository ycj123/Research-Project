// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.util.Map;
import java.util.Properties;
import java.util.Arrays;
import org.pitest.classinfo.ClassName;
import java.util.Set;
import java.util.HashSet;
import org.pitest.classpath.DirectoryClassPathRoot;
import org.pitest.util.Glob;
import org.pitest.testapi.TestGroupConfig;
import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import java.util.Collection;
import java.util.ArrayList;
import org.pitest.mutationtest.config.ReportOptions;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.artifact.Artifact;
import org.pitest.functional.predicate.Predicate;

public class MojoToReportOptionsConverter
{
    private final AbstractPitMojo mojo;
    private final Predicate<Artifact> dependencyFilter;
    private final Log log;
    private final SurefireConfigConverter surefireConverter;
    
    public MojoToReportOptionsConverter(final AbstractPitMojo mojo, final SurefireConfigConverter surefireConverter, final Predicate<Artifact> dependencyFilter) {
        this.mojo = mojo;
        this.dependencyFilter = dependencyFilter;
        this.log = mojo.getLog();
        this.surefireConverter = surefireConverter;
    }
    
    public ReportOptions convert() {
        final List<String> classPath = new ArrayList<String>();
        try {
            classPath.addAll(this.mojo.getProject().getTestClasspathElements());
        }
        catch (DependencyResolutionRequiredException e1) {
            this.log.info(e1);
        }
        this.addOwnDependenciesToClassPath(classPath);
        classPath.addAll(this.mojo.getAdditionalClasspathElements());
        for (final Object artifact : this.mojo.getProject().getArtifacts()) {
            final Artifact dependency = (Artifact)artifact;
            if (this.mojo.getClasspathDependencyExcludes().contains(dependency.getGroupId() + ":" + dependency.getArtifactId())) {
                classPath.remove(dependency.getFile().getPath());
            }
        }
        final ReportOptions option = this.parseReportOptions(classPath);
        return this.updateFromSurefire(option);
    }
    
    private ReportOptions parseReportOptions(final List<String> classPath) {
        final ReportOptions data = new ReportOptions();
        if (this.mojo.getProject().getBuild() != null) {
            this.log.info("Mutating from " + this.mojo.getProject().getBuild().getOutputDirectory());
            data.setCodePaths(Collections.singleton(this.mojo.getProject().getBuild().getOutputDirectory()));
        }
        data.setTestPlugin(this.mojo.getTestPlugin());
        data.setClassPathElements(classPath);
        data.setDependencyAnalysisMaxDistance(this.mojo.getMaxDependencyDistance());
        data.setFailWhenNoMutations(this.shouldFailWhenNoMutations());
        data.setTargetClasses(this.determineTargetClasses());
        data.setTargetTests(this.determineTargetTests());
        data.setExcludedMethods(this.mojo.getExcludedMethods());
        data.setExcludedClasses(this.mojo.getExcludedClasses());
        data.setExcludedTestClasses(this.globStringsToPredicates(this.mojo.getExcludedTestClasses()));
        data.setNumberOfThreads(this.mojo.getThreads());
        data.setExcludedRunners(this.mojo.getExcludedRunners());
        data.setReportDir(this.mojo.getReportsDirectory().getAbsolutePath());
        data.setVerbose(this.mojo.isVerbose());
        if (this.mojo.getJvmArgs() != null) {
            data.addChildJVMArgs(this.mojo.getJvmArgs());
        }
        data.setMutators(this.determineMutators());
        data.setFeatures(this.determineFeatures());
        data.setTimeoutConstant(this.mojo.getTimeoutConstant());
        data.setTimeoutFactor(this.mojo.getTimeoutFactor());
        if (this.hasValue(this.mojo.getAvoidCallsTo())) {
            data.setLoggingClasses(this.mojo.getAvoidCallsTo());
        }
        final List<String> sourceRoots = new ArrayList<String>();
        sourceRoots.addAll(this.mojo.getProject().getCompileSourceRoots());
        sourceRoots.addAll(this.mojo.getProject().getTestCompileSourceRoots());
        data.setSourceDirs(this.stringsTofiles(sourceRoots));
        data.addOutputFormats(this.determineOutputFormats());
        this.setTestGroups(data);
        data.setMutationUnitSize(this.mojo.getMutationUnitSize());
        data.setShouldCreateTimestampedReports(this.mojo.isTimestampedReports());
        data.setDetectInlinedCode(this.mojo.isDetectInlinedCode());
        this.determineHistory(data);
        data.setExportLineCoverage(this.mojo.isExportLineCoverage());
        data.setMutationEngine(this.mojo.getMutationEngine());
        data.setJavaExecutable(this.mojo.getJavaExecutable());
        data.setFreeFormProperties(this.createPluginProperties());
        data.setIncludedTestMethods(this.mojo.getIncludedTestMethods());
        return data;
    }
    
    private void determineHistory(final ReportOptions data) {
        if (this.mojo.useHistory()) {
            this.useHistoryFileInTempDir(data);
        }
        else {
            data.setHistoryInputLocation(this.mojo.getHistoryInputFile());
            data.setHistoryOutputLocation(this.mojo.getHistoryOutputFile());
        }
    }
    
    private void useHistoryFileInTempDir(final ReportOptions data) {
        final String tempDir = System.getProperty("java.io.tmpdir");
        final MavenProject project = this.mojo.project;
        final String name = project.getGroupId() + "." + project.getArtifactId() + "." + project.getVersion() + "_pitest_history.bin";
        final File historyFile = new File(tempDir, name);
        this.log.info("Will read and write history at " + historyFile);
        if (this.mojo.getHistoryInputFile() == null) {
            data.setHistoryInputLocation(historyFile);
        }
        if (this.mojo.getHistoryOutputFile() == null) {
            data.setHistoryOutputLocation(historyFile);
        }
    }
    
    private ReportOptions updateFromSurefire(final ReportOptions option) {
        final Collection<Plugin> plugins = this.lookupPlugin("org.apache.maven.plugins:maven-surefire-plugin");
        if (!this.mojo.isParseSurefireConfig()) {
            return option;
        }
        if (plugins.isEmpty()) {
            this.log.warn("Could not find surefire configuration in pom");
            return option;
        }
        final Plugin surefire = plugins.iterator().next();
        if (surefire != null) {
            return this.surefireConverter.update(option, (Xpp3Dom)surefire.getConfiguration());
        }
        return option;
    }
    
    private Collection<Plugin> lookupPlugin(final String key) {
        final List<Plugin> plugins = (List<Plugin>)this.mojo.getProject().getBuildPlugins();
        return FCollection.filter(plugins, hasKey(key));
    }
    
    private static F<Plugin, Boolean> hasKey(final String key) {
        return new F<Plugin, Boolean>() {
            @Override
            public Boolean apply(final Plugin a) {
                return a.getKey().equals(key);
            }
        };
    }
    
    private boolean shouldFailWhenNoMutations() {
        return this.mojo.isFailWhenNoMutations();
    }
    
    private void setTestGroups(final ReportOptions data) {
        final TestGroupConfig conf = new TestGroupConfig(this.mojo.getExcludedGroups(), this.mojo.getIncludedGroups());
        data.setGroupConfig(conf);
    }
    
    private void addOwnDependenciesToClassPath(final List<String> classPath) {
        for (final Artifact dependency : this.filteredDependencies()) {
            this.log.info("Adding " + dependency.getGroupId() + ":" + dependency.getArtifactId() + " to SUT classpath");
            classPath.add(dependency.getFile().getAbsolutePath());
        }
    }
    
    private Collection<Predicate<String>> globStringsToPredicates(final List<String> excludedMethods) {
        return FCollection.map(excludedMethods, Glob.toGlobPredicate());
    }
    
    private Collection<Predicate<String>> determineTargetTests() {
        return FCollection.map(this.mojo.getTargetTests(), Glob.toGlobPredicate());
    }
    
    private Collection<Artifact> filteredDependencies() {
        return (Collection<Artifact>)FCollection.filter(this.mojo.getPluginArtifactMap().values(), (F<Object, Boolean>)this.dependencyFilter);
    }
    
    private Collection<String> determineMutators() {
        if (this.mojo.getMutators() != null) {
            return this.mojo.getMutators();
        }
        return (Collection<String>)Collections.emptyList();
    }
    
    private Collection<String> determineFeatures() {
        if (this.mojo.getFeatures() != null) {
            return this.mojo.getFeatures();
        }
        return (Collection<String>)Collections.emptyList();
    }
    
    private Collection<String> determineTargetClasses() {
        return this.useConfiguredTargetClassesOrFindOccupiedPackages(this.mojo.getTargetClasses());
    }
    
    private Collection<String> useConfiguredTargetClassesOrFindOccupiedPackages(final Collection<String> filters) {
        if (!this.hasValue(filters)) {
            this.mojo.getLog().info("Defaulting target classes to match packages in build directory");
            return this.findOccupiedPackages();
        }
        return filters;
    }
    
    private Collection<String> findOccupiedPackages() {
        final String outputDirName = this.mojo.getProject().getBuild().getOutputDirectory();
        final File outputDir = new File(outputDirName);
        if (outputDir.exists()) {
            final DirectoryClassPathRoot root = new DirectoryClassPathRoot(outputDir);
            final Set<String> occupiedPackages = new HashSet<String>();
            FCollection.mapTo(root.classNames(), classToPackageGlob(), occupiedPackages);
            return occupiedPackages;
        }
        return (Collection<String>)Collections.emptyList();
    }
    
    private static F<String, String> classToPackageGlob() {
        return new F<String, String>() {
            @Override
            public String apply(final String a) {
                return ClassName.fromString(a).getPackage().asJavaName() + ".*";
            }
        };
    }
    
    private Collection<File> stringsTofiles(final List<String> sourceRoots) {
        return FCollection.map(sourceRoots, this.stringToFile());
    }
    
    private F<String, File> stringToFile() {
        return new F<String, File>() {
            @Override
            public File apply(final String a) {
                return new File(a);
            }
        };
    }
    
    private Collection<String> determineOutputFormats() {
        if (this.hasValue(this.mojo.getOutputFormats())) {
            return this.mojo.getOutputFormats();
        }
        return Arrays.asList("HTML");
    }
    
    private boolean hasValue(final Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }
    
    private Properties createPluginProperties() {
        final Properties p = new Properties();
        if (this.mojo.getPluginProperties() != null) {
            p.putAll(this.mojo.getPluginProperties());
        }
        return p;
    }
}
