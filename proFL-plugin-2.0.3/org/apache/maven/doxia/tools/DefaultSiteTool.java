// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.tools;

import org.apache.maven.reporting.MavenReport;
import java.util.Comparator;
import java.util.Collection;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.StringReader;
import org.apache.maven.doxia.site.decoration.io.xpp3.DecorationXpp3Reader;
import java.io.Reader;
import org.codehaus.plexus.util.ReaderFactory;
import org.mudebug.prapr.reloc.commons.io.FilenameUtils;
import java.util.Collections;
import java.util.Arrays;
import org.apache.maven.model.Model;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.profiles.ProfileManager;
import org.codehaus.plexus.util.interpolation.MapBasedValueSource;
import org.codehaus.plexus.util.interpolation.ObjectBasedValueSource;
import org.codehaus.plexus.util.interpolation.ValueSource;
import org.codehaus.plexus.util.interpolation.EnvarBasedValueSource;
import org.codehaus.plexus.util.interpolation.RegexBasedInterpolator;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.site.decoration.MenuItem;
import org.apache.maven.doxia.site.decoration.Menu;
import java.util.Map;
import org.apache.maven.doxia.site.decoration.Banner;
import org.codehaus.plexus.util.IOUtil;
import java.util.HashMap;
import java.io.IOException;
import org.apache.maven.project.MavenProject;
import java.util.Locale;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.doxia.site.decoration.Skin;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.doxia.site.decoration.DecorationModel;
import java.util.List;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.project.MavenProjectBuilder;
import org.apache.maven.doxia.site.decoration.inheritance.DecorationModelInheritanceAssembler;
import org.codehaus.plexus.i18n.I18N;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultSiteTool extends AbstractLogEnabled implements SiteTool
{
    private ArtifactResolver artifactResolver;
    private ArtifactFactory artifactFactory;
    protected I18N i18n;
    protected DecorationModelInheritanceAssembler assembler;
    protected MavenProjectBuilder mavenProjectBuilder;
    
    public Artifact getSkinArtifactFromRepository(final ArtifactRepository localRepository, final List remoteArtifactRepositories, final DecorationModel decoration) throws SiteToolException {
        if (localRepository == null) {
            throw new IllegalArgumentException("The parameter 'localRepository' can not be null");
        }
        if (remoteArtifactRepositories == null) {
            throw new IllegalArgumentException("The parameter 'remoteArtifactRepositories' can not be null");
        }
        if (decoration == null) {
            throw new IllegalArgumentException("The parameter 'decoration' can not be null");
        }
        Skin skin = decoration.getSkin();
        if (skin == null) {
            skin = Skin.getDefaultSkin();
        }
        String version = skin.getVersion();
        Artifact artifact;
        try {
            if (version == null) {
                version = "RELEASE";
            }
            final VersionRange versionSpec = VersionRange.createFromVersionSpec(version);
            artifact = this.artifactFactory.createDependencyArtifact(skin.getGroupId(), skin.getArtifactId(), versionSpec, "jar", null, null);
            this.artifactResolver.resolve(artifact, remoteArtifactRepositories, localRepository);
        }
        catch (InvalidVersionSpecificationException e) {
            throw new SiteToolException("InvalidVersionSpecificationException: The skin version '" + version + "' is not valid: " + e.getMessage());
        }
        catch (ArtifactResolutionException e2) {
            throw new SiteToolException("ArtifactResolutionException: Unable to find skin", e2);
        }
        catch (ArtifactNotFoundException e3) {
            throw new SiteToolException("ArtifactNotFoundException: The skin does not exist: " + e3.getMessage());
        }
        return artifact;
    }
    
    public Artifact getDefaultSkinArtifact(final ArtifactRepository localRepository, final List remoteArtifactRepositories) throws SiteToolException {
        return this.getSkinArtifactFromRepository(localRepository, remoteArtifactRepositories, new DecorationModel());
    }
    
    public String getRelativePath(final String to, final String from) {
        if (to == null) {
            throw new IllegalArgumentException("The parameter 'to' can not be null");
        }
        if (from == null) {
            throw new IllegalArgumentException("The parameter 'from' can not be null");
        }
        URL toUrl = null;
        URL fromUrl = null;
        String toPath = to;
        String fromPath = from;
        try {
            toUrl = new URL(to);
        }
        catch (MalformedURLException e) {
            try {
                toUrl = new File(getNormalizedPath(to)).toURL();
            }
            catch (MalformedURLException e2) {
                this.getLogger().warn("Unable to load a URL for '" + to + "': " + e.getMessage());
            }
        }
        try {
            fromUrl = new URL(from);
        }
        catch (MalformedURLException e) {
            try {
                fromUrl = new File(getNormalizedPath(from)).toURL();
            }
            catch (MalformedURLException e2) {
                this.getLogger().warn("Unable to load a URL for '" + from + "': " + e.getMessage());
            }
        }
        if (toUrl != null && fromUrl != null) {
            if (!toUrl.getProtocol().equalsIgnoreCase(fromUrl.getProtocol()) || !toUrl.getHost().equalsIgnoreCase(fromUrl.getHost()) || toUrl.getPort() != fromUrl.getPort()) {
                return to;
            }
            toPath = toUrl.getFile();
            fromPath = fromUrl.getFile();
        }
        else if ((toUrl != null && fromUrl == null) || (toUrl == null && fromUrl != null)) {
            return to;
        }
        toPath = new File(toPath).getPath();
        fromPath = new File(fromPath).getPath();
        if (toPath.matches("^\\[a-zA-Z]:")) {
            toPath = toPath.substring(1);
        }
        if (fromPath.matches("^\\[a-zA-Z]:")) {
            fromPath = fromPath.substring(1);
        }
        if (toPath.startsWith(":", 1)) {
            toPath = toPath.substring(0, 1).toLowerCase() + toPath.substring(1);
        }
        if (fromPath.startsWith(":", 1)) {
            fromPath = fromPath.substring(0, 1).toLowerCase() + fromPath.substring(1);
        }
        if (toPath.startsWith(":", 1) && fromPath.startsWith(":", 1) && !toPath.substring(0, 1).equals(fromPath.substring(0, 1))) {
            return to;
        }
        if ((toPath.startsWith(":", 1) && !fromPath.startsWith(":", 1)) || (!toPath.startsWith(":", 1) && fromPath.startsWith(":", 1))) {
            return to;
        }
        StringTokenizer toTokeniser = new StringTokenizer(toPath, File.separator);
        StringTokenizer fromTokeniser = new StringTokenizer(fromPath, File.separator);
        int count = 0;
        while (toTokeniser.hasMoreTokens() && fromTokeniser.hasMoreTokens()) {
            if (File.separatorChar == '\\') {
                if (!fromTokeniser.nextToken().equalsIgnoreCase(toTokeniser.nextToken())) {
                    break;
                }
            }
            else if (!fromTokeniser.nextToken().equals(toTokeniser.nextToken())) {
                break;
            }
            ++count;
        }
        toTokeniser = new StringTokenizer(toPath, File.separator);
        fromTokeniser = new StringTokenizer(fromPath, File.separator);
        while (count-- > 0) {
            fromTokeniser.nextToken();
            toTokeniser.nextToken();
        }
        String relativePath = "";
        while (fromTokeniser.hasMoreTokens()) {
            fromTokeniser.nextToken();
            relativePath += "..";
            if (fromTokeniser.hasMoreTokens()) {
                relativePath += File.separatorChar;
            }
        }
        if (relativePath.length() != 0 && toTokeniser.hasMoreTokens()) {
            relativePath += File.separatorChar;
        }
        while (toTokeniser.hasMoreTokens()) {
            relativePath += toTokeniser.nextToken();
            if (toTokeniser.hasMoreTokens()) {
                relativePath += File.separatorChar;
            }
        }
        if (!relativePath.equals(to)) {
            this.getLogger().debug("Mapped url: " + to + " to relative path: " + relativePath);
        }
        return relativePath;
    }
    
    public File getSiteDescriptorFromBasedir(String siteDirectory, final File basedir, Locale locale) {
        if (basedir == null) {
            throw new IllegalArgumentException("The parameter 'basedir' can not be null");
        }
        if (siteDirectory == null) {
            siteDirectory = "src/site";
        }
        if (locale == null) {
            locale = new Locale("");
        }
        final File siteDir = new File(basedir, siteDirectory);
        File siteDescriptor = new File(siteDir, "site_" + locale.getLanguage() + ".xml");
        if (!siteDescriptor.isFile()) {
            siteDescriptor = new File(siteDir, "site.xml");
        }
        return siteDescriptor;
    }
    
    public File getSiteDescriptorFromRepository(final MavenProject project, final ArtifactRepository localRepository, final List remoteArtifactRepositories, Locale locale) throws SiteToolException {
        if (project == null) {
            throw new IllegalArgumentException("The parameter 'project' can not be null");
        }
        if (localRepository == null) {
            throw new IllegalArgumentException("The parameter 'localRepository' can not be null");
        }
        if (remoteArtifactRepositories == null) {
            throw new IllegalArgumentException("The parameter 'remoteArtifactRepositories' can not be null");
        }
        if (locale == null) {
            locale = new Locale("");
        }
        try {
            return this.resolveSiteDescriptor(project, localRepository, remoteArtifactRepositories, locale);
        }
        catch (ArtifactNotFoundException e) {
            this.getLogger().debug("ArtifactNotFoundException: Unable to locate site descriptor: " + e);
            return null;
        }
        catch (ArtifactResolutionException e2) {
            throw new SiteToolException("ArtifactResolutionException: Unable to locate site descriptor: " + e2.getMessage());
        }
        catch (IOException e3) {
            throw new SiteToolException("IOException: Unable to locate site descriptor: " + e3.getMessage());
        }
    }
    
    public DecorationModel getDecorationModel(final MavenProject project, final List reactorProjects, final ArtifactRepository localRepository, final List repositories, final String siteDirectory, Locale locale, final String inputEncoding, final String outputEncoding) throws SiteToolException {
        if (project == null) {
            throw new IllegalArgumentException("The parameter 'project' can not be null");
        }
        if (reactorProjects == null) {
            throw new IllegalArgumentException("The parameter 'reactorProjects' can not be null");
        }
        if (localRepository == null) {
            throw new IllegalArgumentException("The parameter 'localRepository' can not be null");
        }
        if (repositories == null) {
            throw new IllegalArgumentException("The parameter 'repositories' can not be null");
        }
        if (inputEncoding == null) {
            throw new IllegalArgumentException("The parameter 'inputEncoding' can not be null");
        }
        if (outputEncoding == null) {
            throw new IllegalArgumentException("The parameter 'outputEncoding' can not be null");
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        final Map props = new HashMap();
        props.put("reports", "<menu ref=\"reports\"/>\n");
        props.put("modules", "<menu ref=\"modules\"/>\n");
        DecorationModel decorationModel = this.getDecorationModel(project, reactorProjects, localRepository, repositories, siteDirectory, locale, props, inputEncoding, outputEncoding);
        if (decorationModel == null) {
            String siteDescriptorContent;
            try {
                siteDescriptorContent = IOUtil.toString(this.getClass().getResourceAsStream("/default-site.xml"), "UTF-8");
            }
            catch (IOException e) {
                throw new SiteToolException("Error reading default site descriptor: " + e.getMessage(), e);
            }
            siteDescriptorContent = this.getInterpolatedSiteDescriptorContent(props, project, siteDescriptorContent, inputEncoding, outputEncoding);
            decorationModel = this.readDecorationModel(siteDescriptorContent);
        }
        final MavenProject parentProject = this.getParentProject(project, reactorProjects, localRepository);
        if (parentProject != null) {
            this.populateParentMenu(decorationModel, locale, project, parentProject, true);
        }
        this.populateModulesMenu(project, reactorProjects, localRepository, decorationModel, locale, true);
        if (decorationModel.getBannerLeft() == null) {
            final Banner banner = new Banner();
            banner.setName(project.getName());
            decorationModel.setBannerLeft(banner);
        }
        if (project.getUrl() != null) {
            this.assembler.resolvePaths(decorationModel, project.getUrl());
        }
        else {
            this.getLogger().warn("No URL defined for the project - decoration links will not be resolved");
        }
        return decorationModel;
    }
    
    public void populateReportsMenu(final DecorationModel decorationModel, Locale locale, final Map categories) {
        if (decorationModel == null) {
            throw new IllegalArgumentException("The parameter 'decorationModel' can not be null");
        }
        if (categories == null) {
            throw new IllegalArgumentException("The parameter 'categories' can not be null");
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        final Menu menu = decorationModel.getMenuRef("reports");
        if (menu != null) {
            if (menu.getName() == null) {
                menu.setName(this.i18n.getString("site-tool", locale, "decorationModel.menu.projectdocumentation"));
            }
            boolean found = false;
            if (menu.getItems().isEmpty()) {
                List categoryReports = categories.get("Project Info");
                if (!isEmptyList(categoryReports)) {
                    final MenuItem item = this.createCategoryMenu(this.i18n.getString("site-tool", locale, "decorationModel.menu.projectinformation"), "/project-info.html", categoryReports, locale);
                    menu.getItems().add(item);
                    found = true;
                }
                categoryReports = categories.get("Project Reports");
                if (!isEmptyList(categoryReports)) {
                    final MenuItem item = this.createCategoryMenu(this.i18n.getString("site-tool", locale, "decorationModel.menu.projectreports"), "/project-reports.html", categoryReports, locale);
                    menu.getItems().add(item);
                    found = true;
                }
            }
            if (!found) {
                decorationModel.removeMenuRef("reports");
            }
        }
    }
    
    public String getInterpolatedSiteDescriptorContent(final Map props, final MavenProject project, String siteDescriptorContent, final String inputEncoding, final String outputEncoding) throws SiteToolException {
        if (props == null) {
            throw new IllegalArgumentException("The parameter 'props' can not be null");
        }
        if (project == null) {
            throw new IllegalArgumentException("The parameter 'project' can not be null");
        }
        if (siteDescriptorContent == null) {
            throw new IllegalArgumentException("The parameter 'siteDescriptorContent' can not be null");
        }
        if (inputEncoding == null) {
            throw new IllegalArgumentException("The parameter 'inputEncoding' can not be null");
        }
        if (outputEncoding == null) {
            throw new IllegalArgumentException("The parameter 'outputEncoding' can not be null");
        }
        final Map modulesProps = new HashMap();
        modulesProps.put("modules", "<menu ref=\"modules\"/>");
        siteDescriptorContent = StringUtils.interpolate(siteDescriptorContent, modulesProps);
        final RegexBasedInterpolator interpolator = new RegexBasedInterpolator();
        try {
            interpolator.addValueSource((ValueSource)new EnvarBasedValueSource());
        }
        catch (IOException e) {
            throw new SiteToolException("IOException: cannot interpolate environment properties: " + e.getMessage(), e);
        }
        interpolator.addValueSource((ValueSource)new ObjectBasedValueSource(project));
        interpolator.addValueSource((ValueSource)new MapBasedValueSource(project.getProperties()));
        siteDescriptorContent = interpolator.interpolate(siteDescriptorContent, "project");
        props.put("inputEncoding", inputEncoding);
        props.put("outputEncoding", outputEncoding);
        props.put("parentProject", "<menu ref=\"parent\"/>");
        props.put("reports", "<menu ref=\"reports\"/>");
        return StringUtils.interpolate(siteDescriptorContent, props);
    }
    
    public MavenProject getParentProject(final MavenProject project, final List reactorProjects, final ArtifactRepository localRepository) {
        if (project == null) {
            throw new IllegalArgumentException("The parameter 'project' can not be null");
        }
        if (reactorProjects == null) {
            throw new IllegalArgumentException("The parameter 'reactorProjects' can not be null");
        }
        if (localRepository == null) {
            throw new IllegalArgumentException("The parameter 'localRepository' can not be null");
        }
        MavenProject parentProject = null;
        final MavenProject origParent = project.getParent();
        if (origParent != null) {
            for (final MavenProject reactorProject : reactorProjects) {
                if (reactorProject.getGroupId().equals(origParent.getGroupId()) && reactorProject.getArtifactId().equals(origParent.getArtifactId()) && reactorProject.getVersion().equals(origParent.getVersion())) {
                    parentProject = reactorProject;
                    break;
                }
            }
            if (parentProject == null && project.getBasedir() != null) {
                try {
                    File pomFile = new File(project.getBasedir(), project.getModel().getParent().getRelativePath());
                    if (pomFile.isDirectory()) {
                        pomFile = new File(pomFile, "pom.xml");
                    }
                    pomFile = new File(getNormalizedPath(pomFile.getPath()));
                    final MavenProject mavenProject = this.mavenProjectBuilder.build(pomFile, localRepository, null);
                    if (mavenProject.getGroupId().equals(origParent.getGroupId()) && mavenProject.getArtifactId().equals(origParent.getArtifactId()) && mavenProject.getVersion().equals(origParent.getVersion())) {
                        parentProject = mavenProject;
                    }
                }
                catch (ProjectBuildingException e) {
                    this.getLogger().info("Unable to load parent project from a relative path: " + e.getMessage());
                }
            }
            if (parentProject == null) {
                try {
                    parentProject = this.mavenProjectBuilder.buildFromRepository(project.getParentArtifact(), project.getRemoteArtifactRepositories(), localRepository);
                    this.getLogger().info("Parent project loaded from repository.");
                }
                catch (ProjectBuildingException e) {
                    this.getLogger().warn("Unable to load parent project from repository: " + e.getMessage());
                }
            }
            if (parentProject == null) {
                parentProject = origParent;
            }
        }
        return parentProject;
    }
    
    public void populateParentMenu(final DecorationModel decorationModel, Locale locale, final MavenProject project, final MavenProject parentProject, final boolean keepInheritedRefs) {
        if (decorationModel == null) {
            throw new IllegalArgumentException("The parameter 'decorationModel' can not be null");
        }
        if (project == null) {
            throw new IllegalArgumentException("The parameter 'project' can not be null");
        }
        if (parentProject == null) {
            throw new IllegalArgumentException("The parameter 'parentProject' can not be null");
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        final Menu menu = decorationModel.getMenuRef("parent");
        if (menu == null) {
            return;
        }
        if (!keepInheritedRefs || !menu.isInheritAsRef()) {
            String parentUrl = parentProject.getUrl();
            if (parentUrl != null) {
                if (parentUrl.endsWith("/")) {
                    parentUrl += "index.html";
                }
                else {
                    parentUrl += "/index.html";
                }
                parentUrl = this.getRelativePath(parentUrl, project.getUrl());
                if (menu.getName() == null) {
                    menu.setName(this.i18n.getString("site-tool", locale, "decorationModel.menu.parentproject"));
                }
                final MenuItem item = new MenuItem();
                item.setName(parentProject.getName());
                item.setHref(parentUrl);
                menu.addItem(item);
            }
            else {
                decorationModel.removeMenuRef("parent");
            }
        }
    }
    
    public void populateProjectParentMenu(final DecorationModel decorationModel, final Locale locale, final MavenProject project, final MavenProject parentProject, final boolean keepInheritedRefs) {
        this.populateParentMenu(decorationModel, locale, project, parentProject, keepInheritedRefs);
    }
    
    public void populateModules(final MavenProject project, final List reactorProjects, final ArtifactRepository localRepository, final DecorationModel decorationModel, final Locale locale, final boolean keepInheritedRefs) throws SiteToolException {
        this.populateModulesMenu(project, reactorProjects, localRepository, decorationModel, locale, keepInheritedRefs);
    }
    
    public void populateModulesMenu(final MavenProject project, final List reactorProjects, final ArtifactRepository localRepository, final DecorationModel decorationModel, Locale locale, final boolean keepInheritedRefs) throws SiteToolException {
        if (project == null) {
            throw new IllegalArgumentException("The parameter 'project' can not be null");
        }
        if (reactorProjects == null) {
            throw new IllegalArgumentException("The parameter 'reactorProjects' can not be null");
        }
        if (localRepository == null) {
            throw new IllegalArgumentException("The parameter 'localRepository' can not be null");
        }
        if (decorationModel == null) {
            throw new IllegalArgumentException("The parameter 'decorationModel' can not be null");
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        final Menu menu = decorationModel.getMenuRef("modules");
        if (menu == null) {
            return;
        }
        if (!keepInheritedRefs || !menu.isInheritAsRef()) {
            if (project.getModules().size() > 0) {
                final List projects = reactorProjects;
                if (menu.getName() == null) {
                    menu.setName(this.i18n.getString("site-tool", locale, "decorationModel.menu.projectmodules"));
                }
                if (projects.size() == 1) {
                    this.getLogger().debug("Attempting to load module information from local filesystem");
                    final List models = new ArrayList(project.getModules().size());
                    for (final String module : project.getModules()) {
                        final File f = new File(project.getBasedir(), module + "/pom.xml");
                        Model model = null;
                        Label_0331: {
                            if (f.exists()) {
                                try {
                                    model = this.mavenProjectBuilder.build(f, localRepository, null).getModel();
                                    break Label_0331;
                                }
                                catch (ProjectBuildingException e) {
                                    throw new SiteToolException("Unable to read local module-POM", e);
                                }
                            }
                            this.getLogger().warn("No filesystem module-POM available");
                            model = new Model();
                            model.setName(module);
                            model.setUrl(module);
                        }
                        models.add(model);
                    }
                    this.populateModulesMenuItemsFromModels(project, models, menu);
                }
                else {
                    this.populateModulesMenuItemsFromReactorProjects(project, reactorProjects, menu);
                }
            }
            else {
                decorationModel.removeMenuRef("modules");
            }
        }
    }
    
    public List getAvailableLocales(final String locales) {
        List localesList = new ArrayList();
        if (locales != null) {
            final String[] localesArray = StringUtils.split(locales, ",");
            for (int i = 0; i < localesArray.length; ++i) {
                final Locale locale = this.codeToLocale(localesArray[i]);
                if (locale != null) {
                    if (!Arrays.asList(Locale.getAvailableLocales()).contains(locale)) {
                        if (this.getLogger().isWarnEnabled()) {
                            this.getLogger().warn("The locale parsed defined by '" + locale + "' is not available in this Java Virtual Machine (" + System.getProperty("java.version") + " from " + System.getProperty("java.vendor") + ") - IGNORING");
                        }
                    }
                    else if (!locale.getLanguage().equals(DefaultSiteTool.DEFAULT_LOCALE.getLanguage()) && !this.i18n.getBundle("site-tool", locale).getLocale().getLanguage().equals(locale.getLanguage())) {
                        final StringBuffer sb = new StringBuffer();
                        sb.append("The locale '").append(locale).append("' (");
                        sb.append(locale.getDisplayName(Locale.ENGLISH));
                        sb.append(") is not currently support by Maven - IGNORING. ");
                        sb.append("\n");
                        sb.append("Contribution are welcome and greatly appreciated! ");
                        sb.append("\n");
                        sb.append("If you want to contribute a new translation, please visit ");
                        sb.append("http://maven.apache.org/plugins/maven-site-plugin/i18n.html ");
                        sb.append("for detailed instructions.");
                        if (this.getLogger().isWarnEnabled()) {
                            this.getLogger().warn(sb.toString());
                        }
                    }
                    else {
                        localesList.add(locale);
                    }
                }
            }
        }
        if (localesList.isEmpty()) {
            localesList = Collections.singletonList(DefaultSiteTool.DEFAULT_LOCALE);
        }
        return localesList;
    }
    
    public Locale codeToLocale(final String localeCode) {
        if (localeCode == null) {
            return null;
        }
        if ("default".equalsIgnoreCase(localeCode)) {
            return Locale.getDefault();
        }
        String language = "";
        String country = "";
        String variant = "";
        final StringTokenizer tokenizer = new StringTokenizer(localeCode, "_");
        final int maxTokens = 3;
        if (tokenizer.countTokens() > 3) {
            if (this.getLogger().isWarnEnabled()) {
                this.getLogger().warn("Invalid java.util.Locale format for '" + localeCode + "' entry - IGNORING");
            }
            return null;
        }
        if (tokenizer.hasMoreTokens()) {
            language = tokenizer.nextToken();
            if (tokenizer.hasMoreTokens()) {
                country = tokenizer.nextToken();
                if (tokenizer.hasMoreTokens()) {
                    variant = tokenizer.nextToken();
                }
            }
        }
        return new Locale(language, country, variant);
    }
    
    protected static String getNormalizedPath(final String path) {
        String normalized = FilenameUtils.normalize(path);
        if (normalized == null) {
            normalized = path;
        }
        return (normalized == null) ? null : normalized.replace('\\', '/');
    }
    
    private File resolveSiteDescriptor(final MavenProject project, final ArtifactRepository localRepository, final List repositories, final Locale locale) throws IOException, ArtifactResolutionException, ArtifactNotFoundException {
        Artifact artifact = this.artifactFactory.createArtifactWithClassifier(project.getGroupId(), project.getArtifactId(), project.getVersion(), "xml", "site_" + locale.getLanguage());
        boolean found = false;
        File result;
        try {
            this.artifactResolver.resolve(artifact, repositories, localRepository);
            result = artifact.getFile();
            if (result.length() > 0L) {
                found = true;
            }
            else {
                this.getLogger().debug("Skipped site descriptor for locale " + locale.getLanguage());
            }
        }
        catch (ArtifactNotFoundException e) {
            this.getLogger().debug("Unable to locate site descriptor for locale " + locale.getLanguage() + ": " + e);
            result = new File(localRepository.getBasedir(), localRepository.pathOf(artifact));
            result.getParentFile().mkdirs();
            result.createNewFile();
        }
        if (!found) {
            artifact = this.artifactFactory.createArtifactWithClassifier(project.getGroupId(), project.getArtifactId(), project.getVersion(), "xml", "site");
            try {
                this.artifactResolver.resolve(artifact, repositories, localRepository);
            }
            catch (ArtifactNotFoundException e) {
                result = new File(localRepository.getBasedir(), localRepository.pathOf(artifact));
                result.getParentFile().mkdirs();
                result.createNewFile();
                throw e;
            }
            result = artifact.getFile();
            if (result.length() == 0L) {
                this.getLogger().debug("Skipped remote site descriptor check");
                result = null;
            }
        }
        return result;
    }
    
    private DecorationModel getDecorationModel(final MavenProject project, final List reactorProjects, final ArtifactRepository localRepository, final List repositories, final String siteDirectory, final Locale locale, final Map origProps, final String inputEncoding, final String outputEncoding) throws SiteToolException {
        final Map props = new HashMap(origProps);
        File siteDescriptor = null;
        Label_0083: {
            if (project.getBasedir() == null) {
                try {
                    siteDescriptor = this.getSiteDescriptorFromRepository(project, localRepository, repositories, locale);
                    break Label_0083;
                }
                catch (SiteToolException e) {
                    throw new SiteToolException("The site descriptor cannot be resolved from the repository: " + e.getMessage(), e);
                }
            }
            siteDescriptor = this.getSiteDescriptorFromBasedir(siteDirectory, project.getBasedir(), locale);
        }
        String siteDescriptorContent = null;
        try {
            if (siteDescriptor != null && siteDescriptor.exists()) {
                this.getLogger().debug("Reading site descriptor from " + siteDescriptor);
                final Reader siteDescriptorReader = ReaderFactory.newXmlReader(siteDescriptor);
                siteDescriptorContent = IOUtil.toString(siteDescriptorReader);
            }
        }
        catch (IOException e2) {
            throw new SiteToolException("The site descriptor cannot be read!", e2);
        }
        DecorationModel decoration = null;
        if (siteDescriptorContent != null) {
            siteDescriptorContent = this.getInterpolatedSiteDescriptorContent(props, project, siteDescriptorContent, inputEncoding, outputEncoding);
            decoration = this.readDecorationModel(siteDescriptorContent);
        }
        final MavenProject parentProject = this.getParentProject(project, reactorProjects, localRepository);
        if (parentProject != null) {
            this.getLogger().debug("Parent project loaded ...");
            final DecorationModel parent = this.getDecorationModel(parentProject, reactorProjects, localRepository, repositories, siteDirectory, locale, props, inputEncoding, outputEncoding);
            if (decoration == null) {
                decoration = parent;
            }
            else {
                this.assembler.assembleModelInheritance(project.getName(), decoration, parent, project.getUrl(), (parentProject.getUrl() == null) ? project.getUrl() : parentProject.getUrl());
            }
        }
        if (decoration != null && decoration.getSkin() != null) {
            this.getLogger().debug("Skin used: " + decoration.getSkin());
        }
        return decoration;
    }
    
    private DecorationModel readDecorationModel(final String siteDescriptorContent) throws SiteToolException {
        DecorationModel decoration;
        try {
            decoration = new DecorationXpp3Reader().read(new StringReader(siteDescriptorContent));
        }
        catch (XmlPullParserException e) {
            throw new SiteToolException("Error parsing site descriptor", e);
        }
        catch (IOException e2) {
            throw new SiteToolException("Error reading site descriptor", e2);
        }
        return decoration;
    }
    
    private void populateModulesMenuItemsFromReactorProjects(final MavenProject project, final List reactorProjects, final Menu menu) {
        for (final MavenProject moduleProject : this.getModuleProjects(project, reactorProjects, 1)) {
            this.appendMenuItem(project, menu, moduleProject.getName(), moduleProject.getUrl(), moduleProject.getArtifactId());
        }
    }
    
    private List getModuleProjects(final MavenProject project, final List reactorProjects, final int levels) {
        final List moduleProjects = new ArrayList();
        final boolean infinite = levels == -1;
        if (reactorProjects != null && (infinite || levels > 0)) {
            for (final MavenProject reactorProject : reactorProjects) {
                if (this.isModuleOfProject(project, reactorProject)) {
                    moduleProjects.add(reactorProject);
                    moduleProjects.addAll(this.getModuleProjects(reactorProject, reactorProjects, infinite ? levels : (levels - 1)));
                }
            }
        }
        return moduleProjects;
    }
    
    private boolean isModuleOfProject(final MavenProject parentProject, final MavenProject potentialModule) {
        boolean result = false;
        final List modules = parentProject.getModules();
        if (modules != null && parentProject != potentialModule) {
            final File parentBaseDir = parentProject.getBasedir();
            for (final String module : modules) {
                final File moduleBaseDir = new File(parentBaseDir, module);
                try {
                    final String lhs = potentialModule.getBasedir().getCanonicalPath();
                    final String rhs = moduleBaseDir.getCanonicalPath();
                    if (lhs.equals(rhs)) {
                        result = true;
                        break;
                    }
                    continue;
                }
                catch (IOException e) {
                    this.getLogger().error("Error encountered when trying to resolve canonical module paths: " + e.getMessage());
                }
            }
        }
        return result;
    }
    
    private void populateModulesMenuItemsFromModels(final MavenProject project, final List models, final Menu menu) {
        if (models != null && models.size() > 1) {
            for (final Model model : models) {
                final String reactorUrl = model.getUrl();
                final String name = model.getName();
                this.appendMenuItem(project, menu, name, reactorUrl, model.getArtifactId());
            }
        }
    }
    
    private void appendMenuItem(final MavenProject project, final Menu menu, final String name, final String href, final String defaultHref) {
        String selectedHref = href;
        if (selectedHref == null) {
            selectedHref = defaultHref;
        }
        final MenuItem item = new MenuItem();
        item.setName(name);
        final String baseUrl = project.getUrl();
        if (baseUrl != null) {
            selectedHref = this.getRelativePath(selectedHref, baseUrl);
        }
        if (selectedHref.endsWith("/")) {
            item.setHref(selectedHref + "index.html");
        }
        else {
            item.setHref(selectedHref + "/index.html");
        }
        menu.addItem(item);
    }
    
    private MenuItem createCategoryMenu(final String name, final String href, final List categoryReports, final Locale locale) {
        final MenuItem item = new MenuItem();
        item.setName(name);
        item.setCollapse(true);
        item.setHref(href);
        Collections.sort((List<Object>)categoryReports, new ReportComparator(locale));
        for (final MavenReport report : categoryReports) {
            final MenuItem subitem = new MenuItem();
            subitem.setName(report.getName(locale));
            subitem.setHref(report.getOutputName() + ".html");
            item.getItems().add(subitem);
        }
        return item;
    }
    
    private static boolean isEmptyList(final List list) {
        return list == null || list.isEmpty();
    }
}
