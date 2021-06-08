// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.extension;

import org.codehaus.plexus.util.xml.Xpp3Dom;
import java.io.InputStream;
import java.io.Reader;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import java.io.InputStreamReader;
import java.util.jar.JarFile;
import java.io.File;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.context.Context;
import java.util.Map;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.apache.maven.wagon.Wagon;
import org.codehaus.classworlds.ClassRealm;
import org.codehaus.classworlds.ClassWorld;
import org.codehaus.classworlds.NoSuchRealmException;
import org.codehaus.classworlds.DuplicateRealmException;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.codehaus.plexus.PlexusContainerException;
import java.util.Iterator;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import java.util.Set;
import org.apache.maven.artifact.metadata.ResolutionGroup;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import org.apache.maven.plugin.DefaultPluginManager;
import org.apache.maven.artifact.metadata.ArtifactMetadataRetrievalException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.ArtifactUtils;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.project.MavenProject;
import org.apache.maven.model.Extension;
import org.apache.maven.MavenArtifactFilterManager;
import org.codehaus.plexus.PlexusContainer;
import org.apache.maven.artifact.manager.WagonManager;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultExtensionManager extends AbstractLogEnabled implements ExtensionManager, Contextualizable
{
    private ArtifactResolver artifactResolver;
    private ArtifactFactory artifactFactory;
    private ArtifactMetadataSource artifactMetadataSource;
    private DefaultPlexusContainer container;
    private ArtifactFilter artifactFilter;
    private WagonManager wagonManager;
    private PlexusContainer extensionContainer;
    private static final String CONTAINER_NAME = "extensions";
    
    public DefaultExtensionManager() {
        this.artifactFilter = MavenArtifactFilterManager.createStandardFilter();
    }
    
    public void addExtension(final Extension extension, final MavenProject project, final ArtifactRepository localRepository) throws ArtifactResolutionException, PlexusContainerException, ArtifactNotFoundException {
        final String extensionId = ArtifactUtils.versionlessKey(extension.getGroupId(), extension.getArtifactId());
        this.getLogger().debug("Initialising extension: " + extensionId);
        final Artifact artifact = project.getExtensionArtifactMap().get(extensionId);
        if (artifact != null) {
            final ArtifactFilter filter = new ProjectArtifactExceptionFilter(this.artifactFilter, project.getArtifact());
            ResolutionGroup resolutionGroup;
            try {
                resolutionGroup = this.artifactMetadataSource.retrieve(artifact, localRepository, project.getRemoteArtifactRepositories());
            }
            catch (ArtifactMetadataRetrievalException e) {
                throw new ArtifactResolutionException("Unable to download metadata from repository for plugin '" + artifact.getId() + "': " + e.getMessage(), artifact, e);
            }
            DefaultPluginManager.checkPlexusUtils(resolutionGroup, this.artifactFactory);
            final Set dependencies = new HashSet(resolutionGroup.getArtifacts());
            dependencies.add(artifact);
            final ArtifactResolutionResult result = this.artifactResolver.resolveTransitively(dependencies, project.getArtifact(), Collections.EMPTY_MAP, localRepository, project.getRemoteArtifactRepositories(), this.artifactMetadataSource, filter);
            final Set artifacts = result.getArtifacts();
            if (this.extensionContainsLifeycle(artifact.getFile())) {
                for (final Artifact a : artifacts) {
                    if (this.artifactFilter.include(a)) {
                        this.getLogger().debug("Adding extension to core container: " + a.getFile());
                        this.container.addJarResource(a.getFile());
                    }
                }
            }
            else if (artifacts.size() == 2) {
                for (Artifact a : artifacts) {
                    if (!a.getArtifactId().equals("plexus-utils")) {
                        a = project.replaceWithActiveArtifact(a);
                        this.getLogger().debug("Adding extension to core container: " + a.getFile());
                        this.container.addJarResource(a.getFile());
                    }
                }
            }
            else {
                if (this.extensionContainer == null) {
                    this.extensionContainer = this.createContainer();
                }
                for (Artifact a : result.getArtifacts()) {
                    a = project.replaceWithActiveArtifact(a);
                    this.getLogger().debug("Adding to extension classpath: " + a.getFile());
                    this.extensionContainer.addJarResource(a.getFile());
                }
                if (this.getLogger().isDebugEnabled()) {
                    this.extensionContainer.getContainerRealm().display();
                }
            }
        }
    }
    
    private PlexusContainer createContainer() throws PlexusContainerException {
        final DefaultPlexusContainer child = new DefaultPlexusContainer();
        final ClassWorld classWorld = this.container.getClassWorld();
        child.setClassWorld(classWorld);
        ClassRealm childRealm = null;
        final String childRealmId = "plexus.core.child-container[extensions]";
        try {
            childRealm = classWorld.getRealm(childRealmId);
        }
        catch (NoSuchRealmException e) {
            try {
                childRealm = classWorld.newRealm(childRealmId);
            }
            catch (DuplicateRealmException impossibleError) {
                this.getLogger().error("An impossible error has occurred. After getRealm() failed, newRealm() produced duplication error on same id!", impossibleError);
            }
        }
        childRealm.setParent(this.container.getContainerRealm());
        child.setCoreRealm(childRealm);
        child.setName("extensions");
        child.setLoggerManager(this.container.getLoggerManager());
        child.initialize();
        child.start();
        return child;
    }
    
    public void registerWagons() {
        if (this.extensionContainer != null) {
            try {
                final Map wagons = this.extensionContainer.lookupMap(Wagon.ROLE);
                this.wagonManager.registerWagons(wagons.keySet(), this.extensionContainer);
            }
            catch (ComponentLookupException ex) {}
        }
    }
    
    public void contextualize(final Context context) throws ContextException {
        this.container = (DefaultPlexusContainer)context.get("plexus");
    }
    
    private boolean extensionContainsLifeycle(final File extension) {
        try {
            final JarFile f = new JarFile(extension);
            final InputStream is = f.getInputStream(f.getEntry("META-INF/plexus/components.xml"));
            if (is == null) {
                return false;
            }
            final Xpp3Dom dom = Xpp3DomBuilder.build(new InputStreamReader(is));
            final Xpp3Dom[] components = dom.getChild("components").getChildren("component");
            for (int i = 0; i < components.length; ++i) {
                if (components[i].getChild("role").getValue().equals("org.apache.maven.lifecycle.mapping.LifecycleMapping")) {
                    return true;
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    private static final class ProjectArtifactExceptionFilter implements ArtifactFilter
    {
        private ArtifactFilter passThroughFilter;
        private String projectDependencyConflictId;
        
        ProjectArtifactExceptionFilter(final ArtifactFilter passThroughFilter, final Artifact projectArtifact) {
            this.passThroughFilter = passThroughFilter;
            this.projectDependencyConflictId = projectArtifact.getDependencyConflictId();
        }
        
        public boolean include(final Artifact artifact) {
            final String depConflictId = artifact.getDependencyConflictId();
            return this.projectDependencyConflictId.equals(depConflictId) || this.passThroughFilter.include(artifact);
        }
    }
}
