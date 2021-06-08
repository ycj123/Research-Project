// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import org.apache.maven.project.MavenProject;
import java.util.Iterator;
import org.apache.maven.artifact.Artifact;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.io.File;
import java.util.Collection;
import org.apache.maven.plugin.MojoExecutionException;
import org.pitest.mutationtest.config.ReportOptions;
import org.pitest.mutationtest.tooling.CombinedStatistics;
import org.pitest.functional.Option;
import org.apache.maven.plugins.annotations.Parameter;
import java.util.ArrayList;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@org.apache.maven.plugins.annotations.Mojo(name = "praprM", defaultPhase = LifecyclePhase.VERIFY, requiresDependencyResolution = ResolutionScope.TEST, threadSafe = true)
@Execute(phase = LifecyclePhase.TEST_COMPILE)
public class PraPRMultiMojo extends PraPRMojo
{
    @Parameter(property = "targetModules")
    protected ArrayList<String> targetModules;
    @Parameter(property = "excludedModules")
    protected ArrayList<String> excludedModules;
    
    @Override
    protected RunDecision shouldRun() {
        final String projectName = this.getProject().getArtifactId();
        ModuleUtils.initialize(this);
        this.updateTargetClasses();
        this.updateTargetTests();
        final RunDecision decision = super.shouldRun();
        if (!this.isInTargetModules(projectName)) {
            final String message = projectName + " is not a target module";
            decision.addReason(message);
        }
        if (this.isInExcludedModules(projectName)) {
            final String message = projectName + " is an excluded module";
            decision.addReason(message);
        }
        return decision;
    }
    
    @Override
    protected Option<CombinedStatistics> analyse() throws MojoExecutionException {
        final ReportOptions data = this.preanalyse();
        data.setFailWhenNoMutations(false);
        this.modifyPraPRReportOptions(data);
        return Option.some(this.goalStrategy.execute(this.detectBaseDir(), data, this.plugins, this.getEnvironmentVariables()));
    }
    
    public void modifyPraPRReportOptions(final ReportOptions data) {
        final List<String> rawSourceDirs = this.getTransitiveSourceDirs();
        if (rawSourceDirs != null && !rawSourceDirs.isEmpty()) {
            final List<File> sourceDirs = ModuleUtils.namesToFiles(rawSourceDirs);
            data.setSourceDirs(sourceDirs);
        }
        final Set<String> codePaths = this.getTransitiveCodePaths();
        if (data.getCodePaths() != null) {
            final Set<String> origCodePaths = new HashSet<String>(data.getCodePaths());
            codePaths.addAll(origCodePaths);
        }
        data.setCodePaths(codePaths);
        final List<String> classPathElements = this.getTransitiveClassPathElements();
        if (data.getClassPathElements() != null) {
            final List<String> origClassPathElements = new ArrayList<String>(data.getClassPathElements());
            ModuleUtils.addToList(classPathElements, origClassPathElements);
        }
        data.setClassPathElements(classPathElements);
    }
    
    public List<String> getTransitiveSourceDirs() {
        final List<String> transitiveDirs = new ArrayList<String>();
        final Set<Artifact> dependencies = (Set<Artifact>)this.getProject().getArtifacts();
        this.addProjectSourceDirs(transitiveDirs, this.getProject());
        for (final Artifact dependency : dependencies) {
            final MavenProject curProject = ModuleUtils.getMavenProjectFromName(dependency.getArtifactId());
            if (curProject != null) {
                this.addProjectSourceDirs(transitiveDirs, curProject);
            }
        }
        return transitiveDirs;
    }
    
    public void addProjectSourceDirs(final List<String> allDirs, final MavenProject project) {
        allDirs.addAll(project.getCompileSourceRoots());
        allDirs.addAll(project.getTestCompileSourceRoots());
    }
    
    public Set<String> getTransitiveCodePaths() {
        final Set<String> codePathSet = new HashSet<String>();
        final Set<Artifact> dependencies = (Set<Artifact>)this.getProject().getArtifacts();
        for (final Artifact dependency : dependencies) {
            final MavenProject project = ModuleUtils.getMavenProjectFromName(dependency.getArtifactId());
            if (project != null) {
                codePathSet.add(project.getBuild().getOutputDirectory());
            }
        }
        return codePathSet;
    }
    
    public List<String> getTransitiveClassPathElements() {
        final List<String> classPathElements = new ArrayList<String>();
        final Set<Artifact> dependencies = (Set<Artifact>)this.getProject().getArtifacts();
        for (final Artifact dependency : dependencies) {
            final MavenProject project = ModuleUtils.getMavenProjectFromName(dependency.getArtifactId());
            if (project != null) {
                classPathElements.add(project.getBuild().getOutputDirectory());
                classPathElements.add(project.getBuild().getTestOutputDirectory());
            }
            if (!dependency.getType().equals("pom")) {
                classPathElements.add(dependency.getFile().getAbsolutePath());
            }
        }
        return classPathElements;
    }
    
    public void updateTargetClasses() {
        final List<String> originalTargetClasses = this.getTargetClasses();
        final ArrayList<String> targetClasses = new ArrayList<String>();
        if (originalTargetClasses != null && originalTargetClasses.size() > 0) {
            return;
        }
        targetClasses.addAll(ModuleUtils.getSrcClasses(this.getProject()));
        final List<MavenProject> moduleList = ModuleUtils.getDependingModules(this.getProject());
        for (int i = 0; i < moduleList.size(); ++i) {
            final MavenProject module = moduleList.get(i);
            final List<String> classList = ModuleUtils.getSrcClasses(module);
            ModuleUtils.addToList(targetClasses, classList);
        }
        this.targetClasses = targetClasses;
    }
    
    public void updateTargetTests() {
        final List<String> targetTests = this.getTargetTests();
        if (targetTests == null || targetTests.isEmpty()) {
            this.targetTests = ModuleUtils.getTestClasses(this.getProject());
        }
    }
    
    public boolean isInTargetModules(final String name) {
        if (this.targetModules.size() == 0) {
            return true;
        }
        for (final String targetModule : this.targetModules) {
            if (targetModule.equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isInExcludedModules(final String name) {
        if (this.excludedModules.size() == 0) {
            return false;
        }
        for (final String excludedModule : this.excludedModules) {
            if (excludedModule.equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public Boolean hasCompileSourceRoots() {
        if (ModuleUtils.exists(this.getProject().getCompileSourceRoots())) {
            return true;
        }
        final List<MavenProject> dependencies = ModuleUtils.getDependingModules(this.getProject());
        for (final MavenProject dependency : dependencies) {
            if (ModuleUtils.exists(dependency.getCompileSourceRoots())) {
                return true;
            }
        }
        return false;
    }
    
    public Boolean hasTestCompileSourceRoots() {
        return ModuleUtils.exists(this.getProject().getTestCompileSourceRoots());
    }
}
