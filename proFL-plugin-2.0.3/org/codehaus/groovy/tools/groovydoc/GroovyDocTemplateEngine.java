// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Closeable;
import org.codehaus.groovy.runtime.DefaultGroovyMethodsSupport;
import java.io.IOException;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.FileOutputStream;
import java.util.Iterator;
import org.codehaus.groovy.groovydoc.GroovyRootDoc;
import org.codehaus.groovy.groovydoc.GroovyPackageDoc;
import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import groovy.text.GStringTemplateEngine;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import groovy.text.Template;
import java.util.Map;
import java.util.Properties;
import groovy.text.TemplateEngine;

public class GroovyDocTemplateEngine
{
    private TemplateEngine engine;
    private GroovyDocTool tool;
    private ResourceManager resourceManager;
    private Properties properties;
    private Map<String, Template> docTemplates;
    private List<String> docTemplatePaths;
    private Map<String, Template> packageTemplates;
    private List<String> packageTemplatePaths;
    private Map<String, Template> classTemplates;
    private List<String> classTemplatePaths;
    
    public GroovyDocTemplateEngine(final GroovyDocTool tool, final ResourceManager resourceManager, final String classTemplate) {
        this(tool, resourceManager, new String[0], new String[0], new String[] { classTemplate }, new Properties());
    }
    
    public GroovyDocTemplateEngine(final GroovyDocTool tool, final ResourceManager resourceManager, final String[] docTemplates, final String[] packageTemplates, final String[] classTemplates, final Properties properties) {
        this.tool = tool;
        this.resourceManager = resourceManager;
        this.properties = properties;
        this.docTemplatePaths = Arrays.asList(docTemplates);
        this.packageTemplatePaths = Arrays.asList(packageTemplates);
        this.classTemplatePaths = Arrays.asList(classTemplates);
        this.docTemplates = new HashMap<String, Template>();
        this.packageTemplates = new HashMap<String, Template>();
        this.classTemplates = new HashMap<String, Template>();
        this.engine = new GStringTemplateEngine();
    }
    
    String applyClassTemplates(final GroovyClassDoc classDoc) {
        final String templatePath = this.classTemplatePaths.get(0);
        String templateWithBindingApplied = "";
        try {
            Template t = this.classTemplates.get(templatePath);
            if (t == null) {
                t = this.engine.createTemplate(this.resourceManager.getReader(templatePath));
                this.classTemplates.put(templatePath, t);
            }
            final Map<String, Object> binding = new HashMap<String, Object>();
            binding.put("classDoc", classDoc);
            binding.put("props", this.properties);
            templateWithBindingApplied = t.make(binding).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return templateWithBindingApplied;
    }
    
    String applyPackageTemplate(final String template, final GroovyPackageDoc packageDoc) {
        String templateWithBindingApplied = "";
        try {
            Template t = this.packageTemplates.get(template);
            if (t == null) {
                t = this.engine.createTemplate(this.resourceManager.getReader(template));
                this.packageTemplates.put(template, t);
            }
            final Map<String, Object> binding = new HashMap<String, Object>();
            binding.put("packageDoc", packageDoc);
            binding.put("props", this.properties);
            templateWithBindingApplied = t.make(binding).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return templateWithBindingApplied;
    }
    
    String applyRootDocTemplate(final String template, final GroovyRootDoc rootDoc) {
        String templateWithBindingApplied = "";
        try {
            Template t = this.docTemplates.get(template);
            if (t == null) {
                t = this.engine.createTemplate(this.resourceManager.getReader(template));
                this.docTemplates.put(template, t);
            }
            final Map<String, Object> binding = new HashMap<String, Object>();
            binding.put("rootDoc", rootDoc);
            binding.put("props", this.properties);
            templateWithBindingApplied = t.make(binding).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return templateWithBindingApplied;
    }
    
    Iterator<String> classTemplatesIterator() {
        return this.classTemplatePaths.iterator();
    }
    
    Iterator<String> packageTemplatesIterator() {
        return this.packageTemplatePaths.iterator();
    }
    
    Iterator<String> docTemplatesIterator() {
        return this.docTemplatePaths.iterator();
    }
    
    public void copyBinaryResource(final String template, final String destFileName) {
        if (this.resourceManager instanceof ClasspathResourceManager) {
            OutputStream outputStream = null;
            try {
                final InputStream inputStream = ((ClasspathResourceManager)this.resourceManager).getInputStream(template);
                outputStream = new FileOutputStream(destFileName);
                DefaultGroovyMethods.leftShift(outputStream, inputStream);
            }
            catch (IOException e) {
                System.err.println("Resource " + template + " skipped due to: " + e.getMessage());
            }
            catch (NullPointerException e2) {
                System.err.println("Resource " + template + " not found so skipped");
            }
            finally {
                DefaultGroovyMethodsSupport.closeQuietly(outputStream);
            }
        }
    }
}
