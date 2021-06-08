// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.tools;

import java.util.Map;
import org.apache.maven.project.MavenProject;
import java.io.File;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.doxia.site.decoration.DecorationModel;
import java.util.List;
import org.apache.maven.artifact.repository.ArtifactRepository;
import java.util.Locale;

public interface SiteTool
{
    public static final String ROLE = ((SiteTool$1.class$org$apache$maven$doxia$tools$SiteTool == null) ? (SiteTool$1.class$org$apache$maven$doxia$tools$SiteTool = SiteTool$1.class$("org.apache.maven.doxia.tools.SiteTool")) : SiteTool$1.class$org$apache$maven$doxia$tools$SiteTool).getName();
    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    
    Artifact getSkinArtifactFromRepository(final ArtifactRepository p0, final List p1, final DecorationModel p2) throws SiteToolException;
    
    Artifact getDefaultSkinArtifact(final ArtifactRepository p0, final List p1) throws SiteToolException;
    
    String getRelativePath(final String p0, final String p1);
    
    File getSiteDescriptorFromBasedir(final String p0, final File p1, final Locale p2);
    
    File getSiteDescriptorFromRepository(final MavenProject p0, final ArtifactRepository p1, final List p2, final Locale p3) throws SiteToolException;
    
    DecorationModel getDecorationModel(final MavenProject p0, final List p1, final ArtifactRepository p2, final List p3, final String p4, final Locale p5, final String p6, final String p7) throws SiteToolException;
    
    void populateReportsMenu(final DecorationModel p0, final Locale p1, final Map p2);
    
    String getInterpolatedSiteDescriptorContent(final Map p0, final MavenProject p1, final String p2, final String p3, final String p4) throws SiteToolException;
    
    MavenProject getParentProject(final MavenProject p0, final List p1, final ArtifactRepository p2);
    
    void populateParentMenu(final DecorationModel p0, final Locale p1, final MavenProject p2, final MavenProject p3, final boolean p4);
    
    void populateProjectParentMenu(final DecorationModel p0, final Locale p1, final MavenProject p2, final MavenProject p3, final boolean p4);
    
    void populateModules(final MavenProject p0, final List p1, final ArtifactRepository p2, final DecorationModel p3, final Locale p4, final boolean p5) throws SiteToolException;
    
    void populateModulesMenu(final MavenProject p0, final List p1, final ArtifactRepository p2, final DecorationModel p3, final Locale p4, final boolean p5) throws SiteToolException;
    
    List getAvailableLocales(final String p0);
    
    Locale codeToLocale(final String p0);
}
