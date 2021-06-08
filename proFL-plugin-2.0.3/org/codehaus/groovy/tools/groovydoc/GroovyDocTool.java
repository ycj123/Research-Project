// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.io.File;
import org.codehaus.groovy.groovydoc.GroovyRootDoc;
import java.io.IOException;
import groovyjarjarantlr.TokenStreamException;
import groovyjarjarantlr.RecognitionException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

public class GroovyDocTool
{
    private final GroovyRootDocBuilder rootDocBuilder;
    private final GroovyDocTemplateEngine templateEngine;
    private Properties properties;
    
    public GroovyDocTool(final String[] sourcepaths) {
        this(null, sourcepaths, null);
    }
    
    public GroovyDocTool(final ResourceManager resourceManager, final String[] sourcepaths, final String classTemplate) {
        this(resourceManager, sourcepaths, new String[0], new String[0], new String[] { classTemplate }, new ArrayList<LinkArgument>(), new Properties());
    }
    
    public GroovyDocTool(final ResourceManager resourceManager, final String[] sourcepaths, final String[] docTemplates, final String[] packageTemplates, final String[] classTemplates, final List<LinkArgument> links, final Properties properties) {
        this.rootDocBuilder = new GroovyRootDocBuilder(this, sourcepaths, links, properties);
        this.properties = properties;
        if (resourceManager == null) {
            this.templateEngine = null;
        }
        else {
            this.templateEngine = new GroovyDocTemplateEngine(this, resourceManager, docTemplates, packageTemplates, classTemplates, properties);
        }
    }
    
    public void add(final List<String> filenames) throws RecognitionException, TokenStreamException, IOException {
        if (this.templateEngine != null) {
            System.out.println("Loading source files for " + filenames);
        }
        this.rootDocBuilder.buildTree(filenames);
    }
    
    public GroovyRootDoc getRootDoc() {
        return this.rootDocBuilder.getRootDoc();
    }
    
    public void renderToOutput(final OutputTool output, final String destdir) throws Exception {
        if ("true".equals(this.properties.getProperty("privateScope"))) {
            this.properties.setProperty("packageScope", "true");
        }
        if ("true".equals(this.properties.getProperty("packageScope"))) {
            this.properties.setProperty("protectedScope", "true");
        }
        if ("true".equals(this.properties.getProperty("protectedScope"))) {
            this.properties.setProperty("publicScope", "true");
        }
        if (this.templateEngine != null) {
            final GroovyDocWriter writer = new GroovyDocWriter(this, output, this.templateEngine, this.properties);
            final GroovyRootDoc rootDoc = this.rootDocBuilder.getRootDoc();
            writer.writeRoot(rootDoc, destdir);
            writer.writePackages(rootDoc, destdir);
            writer.writeClasses(rootDoc, destdir);
            return;
        }
        throw new UnsupportedOperationException("No template engine was found");
    }
    
    String getPath(final String filename) {
        String path = new File(filename).getParent();
        if (path == null || path.length() == 1) {
            path = "DefaultPackage";
        }
        return path;
    }
    
    String getFile(final String filename) {
        return new File(filename).getName();
    }
}
