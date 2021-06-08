// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.util.ArrayList;
import org.codehaus.plexus.util.StringUtils;
import java.util.Collection;
import java.util.Arrays;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ChangeSet;
import java.util.LinkedHashSet;
import org.apache.maven.scm.ScmBranch;
import org.apache.maven.scm.command.changelog.ChangeLogScmRequest;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.repository.ScmRepository;
import org.apache.maven.scm.ScmException;
import java.util.Set;
import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import org.apache.maven.plugin.MojoExecutionException;
import org.pitest.mutationtest.config.ReportOptions;
import java.util.Map;
import java.util.HashMap;
import org.pitest.mutationtest.tooling.CombinedStatistics;
import org.pitest.functional.Option;
import org.apache.maven.project.MavenProject;
import org.pitest.mutationtest.config.PluginServices;
import org.apache.maven.artifact.Artifact;
import org.pitest.functional.predicate.Predicate;
import java.io.File;
import org.apache.maven.plugins.annotations.Parameter;
import java.util.HashSet;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.scm.manager.ScmManager;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@org.apache.maven.plugins.annotations.Mojo(name = "scmMutationCoverage", defaultPhase = LifecyclePhase.VERIFY, requiresDependencyResolution = ResolutionScope.TEST, threadSafe = true)
public class ScmMojo extends AbstractPitMojo
{
    private static final int NO_LIMIT = -1;
    @Component
    private ScmManager manager;
    @Parameter(property = "include")
    private HashSet<String> include;
    @Parameter(defaultValue = "false", property = "analyseLastCommit")
    private boolean analyseLastCommit;
    @Parameter(property = "originBranch")
    private String originBranch;
    @Parameter(property = "destinationBranch", defaultValue = "master")
    private String destinationBranch;
    @Parameter(property = "connectionType", defaultValue = "connection")
    private String connectionType;
    @Parameter(property = "basedir", required = true)
    private File basedir;
    @Parameter(property = "project.parent.basedir")
    private File scmRootDir;
    
    public ScmMojo(final RunPitStrategy executionStrategy, final ScmManager manager, final Predicate<Artifact> filter, final PluginServices plugins, final boolean analyseLastCommit, final Predicate<MavenProject> nonEmptyProjectCheck) {
        super(executionStrategy, filter, plugins, nonEmptyProjectCheck);
        this.manager = manager;
        this.analyseLastCommit = analyseLastCommit;
    }
    
    public ScmMojo() {
    }
    
    @Override
    protected Option<CombinedStatistics> analyse() throws MojoExecutionException {
        this.targetClasses = makeConcreteList(this.findModifiedClassNames());
        if (this.targetClasses.isEmpty()) {
            this.getLog().info("No modified files found - nothing to mutation test, analyseLastCommit=" + this.analyseLastCommit);
            return (Option<CombinedStatistics>)Option.none();
        }
        this.logClassNames();
        this.defaultTargetTestsToGroupNameIfNoValueSet();
        final ReportOptions data = new MojoToReportOptionsConverter(this, new SurefireConfigConverter(), this.filter).convert();
        data.setFailWhenNoMutations(false);
        return Option.some(this.goalStrategy.execute(this.detectBaseDir(), data, this.plugins, new HashMap<String, String>()));
    }
    
    private void defaultTargetTestsToGroupNameIfNoValueSet() {
        if (this.getTargetTests() == null || this.getTargetTests().isEmpty()) {
            this.targetTests = makeConcreteList(Collections.singletonList(this.getProject().getGroupId() + "*"));
        }
    }
    
    private void logClassNames() {
        for (final String each : this.targetClasses) {
            this.getLog().info("Will mutate changed class " + each);
        }
    }
    
    private List<String> findModifiedClassNames() throws MojoExecutionException {
        final File sourceRoot = new File(this.project.getBuild().getSourceDirectory());
        final List<String> modifiedPaths = FCollection.map(this.findModifiedPaths(), this.pathByScmDir());
        return (List<String>)FCollection.flatMap((Iterable<?>)modifiedPaths, (F<Object, ? extends Iterable<Object>>)new PathToJavaClassConverter(sourceRoot.getAbsolutePath()));
    }
    
    private F<String, String> pathByScmDir() {
        return new F<String, String>() {
            @Override
            public String apply(final String a) {
                return ScmMojo.this.scmRootDir.getAbsolutePath() + "/" + a;
            }
        };
    }
    
    private Set<String> findModifiedPaths() throws MojoExecutionException {
        try {
            final ScmRepository repository = this.manager.makeScmRepository(this.getSCMConnection());
            final File scmRoot = this.scmRoot();
            this.getLog().info("Scm root dir is " + scmRoot);
            final Set<ScmFileStatus> statusToInclude = this.makeStatusSet();
            Set<String> modifiedPaths;
            if (this.analyseLastCommit) {
                modifiedPaths = this.lastCommitChanges(statusToInclude, repository, scmRoot);
            }
            else if (this.originBranch != null && this.destinationBranch != null) {
                modifiedPaths = this.changesBetweenBranchs(this.originBranch, this.destinationBranch, statusToInclude, repository, scmRoot);
            }
            else {
                modifiedPaths = this.localChanges(statusToInclude, repository, scmRoot);
            }
            return modifiedPaths;
        }
        catch (ScmException e) {
            throw new MojoExecutionException("Error while querying scm", e);
        }
    }
    
    private Set<String> lastCommitChanges(final Set<ScmFileStatus> statusToInclude, final ScmRepository repository, final File scmRoot) throws ScmException {
        final ChangeLogScmRequest scmRequest = new ChangeLogScmRequest(repository, new ScmFileSet(scmRoot));
        scmRequest.setLimit(1);
        return this.pathsAffectedByChange(scmRequest, statusToInclude, 1);
    }
    
    private Set<String> changesBetweenBranchs(final String origine, final String destination, final Set<ScmFileStatus> statusToInclude, final ScmRepository repository, final File scmRoot) throws ScmException {
        final ChangeLogScmRequest scmRequest = new ChangeLogScmRequest(repository, new ScmFileSet(scmRoot));
        scmRequest.setScmBranch(new ScmBranch(destination + ".." + origine));
        return this.pathsAffectedByChange(scmRequest, statusToInclude, -1);
    }
    
    private Set<String> pathsAffectedByChange(final ChangeLogScmRequest scmRequest, final Set<ScmFileStatus> statusToInclude, final int limit) throws ScmException {
        final Set<String> affected = new LinkedHashSet<String>();
        final ChangeLogScmResult changeLogScmResult = this.manager.changeLog(scmRequest);
        if (changeLogScmResult.isSuccess()) {
            final List<ChangeSet> changeSets = this.limit(changeLogScmResult.getChangeLog().getChangeSets(), limit);
            for (final ChangeSet change : changeSets) {
                final List<ChangeFile> files = change.getFiles();
                for (final ChangeFile changeFile : files) {
                    if (statusToInclude.contains(changeFile.getAction())) {
                        affected.add(changeFile.getName());
                    }
                }
            }
        }
        return affected;
    }
    
    private Set<String> localChanges(final Set<ScmFileStatus> statusToInclude, final ScmRepository repository, final File scmRoot) throws ScmException {
        final StatusScmResult status = this.manager.status(repository, new ScmFileSet(scmRoot));
        final Set<String> affected = new LinkedHashSet<String>();
        for (final ScmFile file : status.getChangedFiles()) {
            if (statusToInclude.contains(file.getStatus())) {
                affected.add(file.getPath());
            }
        }
        return affected;
    }
    
    private List<ChangeSet> limit(final List<ChangeSet> changeSets, final int limit) {
        if (limit < 0) {
            return changeSets;
        }
        return changeSets.subList(0, limit);
    }
    
    private Set<ScmFileStatus> makeStatusSet() {
        if (this.include == null || this.include.isEmpty()) {
            return new HashSet<ScmFileStatus>(Arrays.asList(ScmStatus.ADDED.getStatus(), ScmStatus.MODIFIED.getStatus()));
        }
        final Set<ScmFileStatus> s = new HashSet<ScmFileStatus>();
        FCollection.mapTo(this.include, stringToMavenScmStatus(), s);
        return s;
    }
    
    private static F<String, ScmFileStatus> stringToMavenScmStatus() {
        return new F<String, ScmFileStatus>() {
            @Override
            public ScmFileStatus apply(final String a) {
                return ScmStatus.valueOf(a.toUpperCase()).getStatus();
            }
        };
    }
    
    private File scmRoot() {
        if (this.scmRootDir != null) {
            return this.scmRootDir;
        }
        return this.basedir;
    }
    
    private String getSCMConnection() throws MojoExecutionException {
        if (this.project.getScm() == null) {
            throw new MojoExecutionException("No SCM Connection configured.");
        }
        final String scmConnection = this.project.getScm().getConnection();
        if ("connection".equalsIgnoreCase(this.connectionType) && StringUtils.isNotEmpty(scmConnection)) {
            return scmConnection;
        }
        final String scmDeveloper = this.project.getScm().getDeveloperConnection();
        if ("developerconnection".equalsIgnoreCase(this.connectionType) && StringUtils.isNotEmpty(scmDeveloper)) {
            return scmDeveloper;
        }
        throw new MojoExecutionException("SCM Connection is not set.");
    }
    
    public void setConnectionType(final String connectionType) {
        this.connectionType = connectionType;
    }
    
    public void setScmRootDir(final File scmRootDir) {
        this.scmRootDir = scmRootDir;
    }
    
    private static ArrayList<String> makeConcreteList(final List<String> list) {
        return new ArrayList<String>(list);
    }
}
