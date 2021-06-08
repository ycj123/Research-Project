// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.siterenderer;

import java.util.Arrays;
import org.codehaus.plexus.util.DirectoryScanner;
import java.io.InputStream;
import java.util.Enumeration;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.zip.ZipFile;
import org.apache.maven.doxia.site.decoration.DecorationModel;
import org.apache.velocity.Template;
import java.util.Locale;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.PathTool;
import java.text.DateFormat;
import java.util.Date;
import org.apache.velocity.VelocityContext;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import org.apache.velocity.context.Context;
import org.apache.maven.doxia.parser.Parser;
import java.io.Reader;
import org.apache.maven.doxia.parser.ParseException;
import org.apache.maven.doxia.parser.manager.ParserNotFoundException;
import org.apache.maven.doxia.sink.Sink;
import org.codehaus.plexus.util.ReaderFactory;
import java.io.StringReader;
import java.io.StringWriter;
import org.codehaus.plexus.velocity.SiteResourceLoader;
import org.apache.maven.doxia.siterenderer.sink.SiteRendererSink;
import org.codehaus.plexus.util.IOUtil;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.List;
import org.codehaus.plexus.util.Os;
import org.apache.maven.doxia.module.xhtml.decoration.render.RenderingContext;
import org.codehaus.plexus.util.FileUtils;
import java.util.ArrayList;
import org.apache.maven.doxia.module.site.manager.SiteModuleNotFoundException;
import org.apache.maven.doxia.module.site.SiteModule;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;
import java.util.Collection;
import org.codehaus.plexus.i18n.I18N;
import org.apache.maven.doxia.Doxia;
import org.apache.maven.doxia.module.site.manager.SiteModuleManager;
import org.codehaus.plexus.velocity.VelocityComponent;
import org.codehaus.plexus.logging.AbstractLogEnabled;

public class DefaultSiteRenderer extends AbstractLogEnabled implements Renderer
{
    private VelocityComponent velocity;
    private SiteModuleManager siteModuleManager;
    private Doxia doxia;
    private I18N i18n;
    private static final String RESOURCE_DIR = "org/apache/maven/doxia/siterenderer/resources";
    private static final String DEFAULT_TEMPLATE = "org/apache/maven/doxia/siterenderer/resources/default-site.vm";
    private static final String SKIN_TEMPLATE_LOCATION = "META-INF/maven/site.vm";
    
    public void render(final Collection documents, final SiteRenderingContext siteRenderingContext, final File outputDirectory) throws RendererException, IOException {
        this.renderModule(documents, siteRenderingContext, outputDirectory);
        for (final File siteDirectory : siteRenderingContext.getSiteDirectories()) {
            this.copyResources(siteRenderingContext, new File(siteDirectory, "resources"), outputDirectory);
        }
    }
    
    public Map locateDocumentFiles(final SiteRenderingContext siteRenderingContext) throws IOException, RendererException {
        final Map files = new LinkedHashMap();
        final Map moduleExcludes = siteRenderingContext.getModuleExcludes();
        for (final File siteDirectory : siteRenderingContext.getSiteDirectories()) {
            if (siteDirectory.exists()) {
                for (final SiteModule module : this.siteModuleManager.getSiteModules()) {
                    final File moduleBasedir = new File(siteDirectory, module.getSourceDirectory());
                    if (moduleExcludes != null && moduleExcludes.containsKey(module.getParserId())) {
                        this.addModuleFiles(moduleBasedir, module, moduleExcludes.get(module.getParserId()), files);
                    }
                    else {
                        this.addModuleFiles(moduleBasedir, module, null, files);
                    }
                }
            }
        }
        for (final ModuleReference module2 : siteRenderingContext.getModules()) {
            try {
                if (moduleExcludes != null && moduleExcludes.containsKey(module2.getParserId())) {
                    this.addModuleFiles(module2.getBasedir(), this.siteModuleManager.getSiteModule(module2.getParserId()), moduleExcludes.get(module2.getParserId()), files);
                }
                else {
                    this.addModuleFiles(module2.getBasedir(), this.siteModuleManager.getSiteModule(module2.getParserId()), null, files);
                }
            }
            catch (SiteModuleNotFoundException e) {
                throw new RendererException("Unable to find module: " + e.getMessage(), e);
            }
        }
        return files;
    }
    
    private void addModuleFiles(final File moduleBasedir, final SiteModule module, final String excludes, final Map files) throws IOException, RendererException {
        if (moduleBasedir.exists()) {
            final List docs = new ArrayList();
            docs.addAll(FileUtils.getFileNames(moduleBasedir, "**/*." + module.getExtension(), excludes, false));
            docs.addAll(FileUtils.getFileNames(moduleBasedir, "**/*." + module.getExtension() + ".vm", excludes, false));
            for (final String doc : docs) {
                final RenderingContext context = new RenderingContext(moduleBasedir, doc, module.getParserId(), module.getExtension());
                if (doc.endsWith(".vm")) {
                    context.setAttribute("velocity", "true");
                }
                final String key = context.getOutputName();
                if (files.containsKey(key)) {
                    final DocumentRenderer renderer = files.get(key);
                    final RenderingContext originalContext = renderer.getRenderingContext();
                    final File originalDoc = new File(originalContext.getBasedir(), originalContext.getInputName());
                    throw new RendererException("Files '" + doc + "' clashes with existing '" + originalDoc + "'.");
                }
                for (final Map.Entry entry : files.entrySet()) {
                    if (entry.getKey().toString().toLowerCase().equals(key.toLowerCase())) {
                        final DocumentRenderer renderer2 = files.get(entry.getKey());
                        final RenderingContext originalContext2 = renderer2.getRenderingContext();
                        final File originalDoc2 = new File(originalContext2.getBasedir(), originalContext2.getInputName());
                        if (Os.isFamily("windows")) {
                            throw new RendererException("Files '" + doc + "' clashes with existing '" + originalDoc2 + "'.");
                        }
                        this.getLogger().warn("Files '" + doc + "' could clashes with existing '" + originalDoc2 + "'.");
                    }
                }
                files.put(key, new DoxiaDocumentRenderer(context));
            }
        }
    }
    
    private void renderModule(final Collection docs, final SiteRenderingContext siteRenderingContext, final File outputDirectory) throws IOException, RendererException {
        for (final DocumentRenderer docRenderer : docs) {
            final RenderingContext renderingContext = docRenderer.getRenderingContext();
            final File outputFile = new File(outputDirectory, docRenderer.getOutputName());
            final File inputFile = new File(renderingContext.getBasedir(), renderingContext.getInputName());
            boolean modified = false;
            if (!outputFile.exists() || inputFile.lastModified() > outputFile.lastModified()) {
                modified = true;
            }
            if (modified || docRenderer.isOverwrite()) {
                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdirs();
                }
                this.getLogger().debug("Generating " + outputFile);
                final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile), siteRenderingContext.getOutputEncoding());
                try {
                    docRenderer.renderDocument(writer, this, siteRenderingContext);
                }
                finally {
                    IOUtil.close(writer);
                }
            }
            else {
                this.getLogger().debug(inputFile + " unchanged, not regenerating...");
            }
        }
    }
    
    public void renderDocument(final Writer writer, final RenderingContext renderingContext, final SiteRenderingContext context) throws RendererException, FileNotFoundException, UnsupportedEncodingException {
        final SiteRendererSink sink = new SiteRendererSink(renderingContext);
        final File doc = new File(renderingContext.getBasedir(), renderingContext.getInputName());
        try {
            Reader reader = null;
            final Parser parser = this.doxia.getParser(renderingContext.getParserId());
            if (renderingContext.getAttribute("velocity") != null) {
                final String resource = doc.getAbsolutePath();
                try {
                    SiteResourceLoader.setResource(resource);
                    final Context vc = this.createContext(sink, context);
                    final StringWriter sw = new StringWriter();
                    this.velocity.getEngine().mergeTemplate(resource, context.getInputEncoding(), vc, sw);
                    reader = new StringReader(sw.toString());
                }
                catch (Exception e) {
                    if (this.getLogger().isDebugEnabled()) {
                        this.getLogger().error("Error parsing " + resource + " as a velocity template, using as text.", e);
                    }
                    else {
                        this.getLogger().error("Error parsing " + resource + " as a velocity template, using as text.");
                    }
                }
            }
            else {
                switch (parser.getType()) {
                    case 2: {
                        reader = ReaderFactory.newXmlReader(doc);
                        break;
                    }
                    default: {
                        reader = ReaderFactory.newReader(doc, context.getInputEncoding());
                        break;
                    }
                }
            }
            this.doxia.parse(reader, renderingContext.getParserId(), sink);
            this.generateDocument(writer, sink, context);
        }
        catch (ParserNotFoundException e2) {
            throw new RendererException("Error getting a parser for " + doc + ": " + e2.getMessage());
        }
        catch (ParseException e3) {
            this.getLogger().error("Error parsing " + doc + ": line [" + e3.getLineNumber() + "] " + e3.getMessage(), e3);
        }
        catch (IOException e4) {
            this.getLogger().error("Error parsing " + doc + " to detect encoding", e4);
        }
        finally {
            sink.flush();
            sink.close();
        }
    }
    
    private Context createContext(final SiteRendererSink sink, final SiteRenderingContext siteRenderingContext) {
        final VelocityContext context = new VelocityContext();
        final RenderingContext renderingContext = sink.getRenderingContext();
        context.put("relativePath", renderingContext.getRelativePath());
        context.put("authors", sink.getAuthors());
        String title = "";
        if (siteRenderingContext.getDecoration().getName() != null) {
            title = siteRenderingContext.getDecoration().getName();
        }
        else if (siteRenderingContext.getDefaultWindowTitle() != null) {
            title = siteRenderingContext.getDefaultWindowTitle();
        }
        if (title.length() > 0) {
            title += " - ";
        }
        title += sink.getTitle();
        context.put("title", title);
        context.put("bodyContent", sink.getBody());
        context.put("decoration", siteRenderingContext.getDecoration());
        context.put("currentDate", new Date());
        final Locale locale = siteRenderingContext.getLocale();
        context.put("dateFormat", DateFormat.getDateInstance(2, locale));
        final String currentFileName = renderingContext.getOutputName().replace('\\', '/');
        context.put("currentFileName", currentFileName);
        context.put("alignedFileName", PathTool.calculateLink(currentFileName, renderingContext.getRelativePath()));
        context.put("locale", locale);
        final Map templateProperties = siteRenderingContext.getTemplateProperties();
        if (templateProperties != null) {
            for (final String key : templateProperties.keySet()) {
                context.put(key, templateProperties.get(key));
            }
        }
        context.put("PathTool", new PathTool());
        context.put("FileUtils", new FileUtils());
        context.put("StringUtils", new StringUtils());
        context.put("i18n", this.i18n);
        return context;
    }
    
    public void generateDocument(final Writer writer, final SiteRendererSink sink, final SiteRenderingContext siteRenderingContext) throws RendererException {
        final Context context = this.createContext(sink, siteRenderingContext);
        this.writeTemplate(writer, context, siteRenderingContext);
    }
    
    private void writeTemplate(final Writer writer, final Context context, final SiteRenderingContext siteContext) throws RendererException {
        ClassLoader old = null;
        if (siteContext.getTemplateClassLoader() != null) {
            old = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(siteContext.getTemplateClassLoader());
        }
        try {
            this.processTemplate(siteContext.getTemplateName(), context, writer);
        }
        finally {
            IOUtil.close(writer);
            if (old != null) {
                Thread.currentThread().setContextClassLoader(old);
            }
        }
    }
    
    private void processTemplate(final String templateName, final Context context, final Writer writer) throws RendererException {
        Template template;
        try {
            template = this.velocity.getEngine().getTemplate(templateName);
        }
        catch (Exception e) {
            throw new RendererException("Could not find the template '" + templateName);
        }
        try {
            template.merge(context, writer);
        }
        catch (Exception e) {
            throw new RendererException("Error while generating code.", e);
        }
    }
    
    public SiteRenderingContext createContextForSkin(final File skinFile, final Map attributes, final DecorationModel decoration, final String defaultWindowTitle, final Locale locale) throws IOException {
        final SiteRenderingContext context = new SiteRenderingContext();
        final ZipFile zipFile = new ZipFile(skinFile);
        try {
            if (zipFile.getEntry("META-INF/maven/site.vm") != null) {
                context.setTemplateName("META-INF/maven/site.vm");
                context.setTemplateClassLoader(new URLClassLoader(new URL[] { skinFile.toURL() }));
            }
            else {
                context.setTemplateName("org/apache/maven/doxia/siterenderer/resources/default-site.vm");
                context.setTemplateClassLoader(this.getClass().getClassLoader());
                context.setUsingDefaultTemplate(true);
            }
        }
        finally {
            this.closeZipFile(zipFile);
        }
        context.setTemplateProperties(attributes);
        context.setLocale(locale);
        context.setDecoration(decoration);
        context.setDefaultWindowTitle(defaultWindowTitle);
        context.setSkinJarFile(skinFile);
        return context;
    }
    
    public SiteRenderingContext createContextForTemplate(final File templateFile, final File skinFile, final Map attributes, final DecorationModel decoration, final String defaultWindowTitle, final Locale locale) throws MalformedURLException {
        final SiteRenderingContext context = new SiteRenderingContext();
        context.setTemplateName(templateFile.getName());
        context.setTemplateClassLoader(new URLClassLoader(new URL[] { templateFile.getParentFile().toURI().toURL() }));
        context.setTemplateProperties(attributes);
        context.setLocale(locale);
        context.setDecoration(decoration);
        context.setDefaultWindowTitle(defaultWindowTitle);
        context.setSkinJarFile(skinFile);
        return context;
    }
    
    private void closeZipFile(final ZipFile zipFile) {
        try {
            zipFile.close();
        }
        catch (IOException ex) {}
    }
    
    public void copyResources(final SiteRenderingContext siteRenderingContext, final File resourcesDirectory, final File outputDirectory) throws IOException {
        if (siteRenderingContext.getSkinJarFile() != null) {
            final ZipFile file = new ZipFile(siteRenderingContext.getSkinJarFile());
            try {
                final Enumeration e = file.entries();
                while (e.hasMoreElements()) {
                    final ZipEntry entry = e.nextElement();
                    if (!entry.getName().startsWith("META-INF/")) {
                        final File destFile = new File(outputDirectory, entry.getName());
                        if (!entry.isDirectory()) {
                            destFile.getParentFile().mkdirs();
                            this.copyFileFromZip(file, entry, destFile);
                        }
                        else {
                            destFile.mkdirs();
                        }
                    }
                }
            }
            finally {
                file.close();
            }
        }
        if (siteRenderingContext.isUsingDefaultTemplate()) {
            final InputStream resourceList = this.getClass().getClassLoader().getResourceAsStream("org/apache/maven/doxia/siterenderer/resources/resources.txt");
            if (resourceList != null) {
                final LineNumberReader reader = new LineNumberReader(new InputStreamReader(resourceList));
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    final InputStream is = this.getClass().getClassLoader().getResourceAsStream("org/apache/maven/doxia/siterenderer/resources/" + line);
                    if (is == null) {
                        throw new IOException("The resource " + line + " doesn't exist.");
                    }
                    final File outputFile = new File(outputDirectory, line);
                    if (!outputFile.getParentFile().exists()) {
                        outputFile.getParentFile().mkdirs();
                    }
                    final FileOutputStream w = new FileOutputStream(outputFile);
                    IOUtil.copy(is, w);
                    IOUtil.close(is);
                    IOUtil.close(w);
                }
            }
        }
        if (resourcesDirectory != null && resourcesDirectory.exists()) {
            this.copyDirectory(resourcesDirectory, outputDirectory);
        }
        final File siteCssFile = new File(outputDirectory, "/css/site.css");
        if (!siteCssFile.exists()) {
            final File cssDirectory = new File(outputDirectory, "/css/");
            final boolean created = cssDirectory.mkdirs();
            if (created && this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("The directory '" + cssDirectory.getAbsolutePath() + "' did not exist. It was created.");
            }
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("The file '" + siteCssFile.getAbsolutePath() + "' does not exists. Creating an empty file.");
            }
            final FileWriter w2 = new FileWriter(siteCssFile);
            w2.write("/* You can override this file with your own styles */");
            IOUtil.close(w2);
        }
    }
    
    private void copyFileFromZip(final ZipFile file, final ZipEntry entry, final File destFile) throws IOException {
        final FileOutputStream fos = new FileOutputStream(destFile);
        try {
            IOUtil.copy(file.getInputStream(entry), fos);
        }
        finally {
            IOUtil.close(fos);
        }
    }
    
    protected void copyDirectory(final File source, final File destination) throws IOException {
        if (source.exists()) {
            final DirectoryScanner scanner = new DirectoryScanner();
            final String[] includedResources = { "**/**" };
            scanner.setIncludes(includedResources);
            scanner.addDefaultExcludes();
            scanner.setBasedir(source);
            scanner.scan();
            final List includedFiles = Arrays.asList(scanner.getIncludedFiles());
            for (final String name : includedFiles) {
                final File sourceFile = new File(source, name);
                final File destinationFile = new File(destination, name);
                FileUtils.copyFile(sourceFile, destinationFile);
            }
        }
    }
}
