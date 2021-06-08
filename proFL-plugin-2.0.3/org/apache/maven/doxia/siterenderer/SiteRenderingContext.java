// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.siterenderer;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.apache.maven.doxia.site.decoration.DecorationModel;
import java.util.Locale;
import java.util.Map;

public class SiteRenderingContext
{
    private static final String DEFAULT_INPUT_ENCODING = "UTF-8";
    private static final String DEFAULT_OUTPUT_ENCODING = "UTF-8";
    private String inputEncoding;
    private String outputEncoding;
    private String templateName;
    private ClassLoader templateClassLoader;
    private Map templateProperties;
    private Locale locale;
    private DecorationModel decoration;
    private String defaultWindowTitle;
    private File skinJarFile;
    private boolean usingDefaultTemplate;
    private List siteDirectories;
    private Map moduleExcludes;
    private List modules;
    
    public SiteRenderingContext() {
        this.inputEncoding = "UTF-8";
        this.outputEncoding = "UTF-8";
        this.locale = Locale.getDefault();
        this.siteDirectories = new ArrayList();
        this.modules = new ArrayList();
    }
    
    public String getTemplateName() {
        return this.templateName;
    }
    
    public ClassLoader getTemplateClassLoader() {
        return this.templateClassLoader;
    }
    
    public void setTemplateClassLoader(final ClassLoader templateClassLoader) {
        this.templateClassLoader = templateClassLoader;
    }
    
    public Map getTemplateProperties() {
        return this.templateProperties;
    }
    
    public void setTemplateProperties(final Map templateProperties) {
        this.templateProperties = Collections.unmodifiableMap((Map<?, ?>)templateProperties);
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public void setLocale(final Locale locale) {
        this.locale = locale;
    }
    
    public DecorationModel getDecoration() {
        return this.decoration;
    }
    
    public void setDecoration(final DecorationModel decoration) {
        this.decoration = decoration;
    }
    
    public void setDefaultWindowTitle(final String defaultWindowTitle) {
        this.defaultWindowTitle = defaultWindowTitle;
    }
    
    public String getDefaultWindowTitle() {
        return this.defaultWindowTitle;
    }
    
    public File getSkinJarFile() {
        return this.skinJarFile;
    }
    
    public void setSkinJarFile(final File skinJarFile) {
        this.skinJarFile = skinJarFile;
    }
    
    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }
    
    public void setUsingDefaultTemplate(final boolean usingDefaultTemplate) {
        this.usingDefaultTemplate = usingDefaultTemplate;
    }
    
    public boolean isUsingDefaultTemplate() {
        return this.usingDefaultTemplate;
    }
    
    public void addSiteDirectory(final File file) {
        this.siteDirectories.add(file);
    }
    
    public void addModuleDirectory(final File file, final String moduleParserId) {
        this.modules.add(new ModuleReference(moduleParserId, file));
    }
    
    public List getSiteDirectories() {
        return this.siteDirectories;
    }
    
    public List getModules() {
        return this.modules;
    }
    
    public Map getModuleExcludes() {
        return this.moduleExcludes;
    }
    
    public void setModuleExcludes(final Map moduleExcludes) {
        this.moduleExcludes = moduleExcludes;
    }
    
    public String getInputEncoding() {
        return this.inputEncoding;
    }
    
    public void setInputEncoding(final String inputEncoding) {
        this.inputEncoding = inputEncoding;
    }
    
    public String getOutputEncoding() {
        return this.outputEncoding;
    }
    
    public void setOutputEncoding(final String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }
}
