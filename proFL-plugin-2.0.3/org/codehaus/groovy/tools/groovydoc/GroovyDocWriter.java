// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.util.Iterator;
import org.codehaus.groovy.groovydoc.GroovyPackageDoc;
import java.io.File;
import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import org.codehaus.groovy.groovydoc.GroovyRootDoc;
import java.util.Properties;

public class GroovyDocWriter
{
    private GroovyDocTool tool;
    private OutputTool output;
    private GroovyDocTemplateEngine templateEngine;
    private static final String FS = "/";
    private Properties properties;
    
    public GroovyDocWriter(final GroovyDocTool tool, final OutputTool output, final GroovyDocTemplateEngine templateEngine, final Properties properties) {
        this.tool = tool;
        this.output = output;
        this.templateEngine = templateEngine;
        this.properties = properties;
    }
    
    public void writeClasses(final GroovyRootDoc rootDoc, final String destdir) throws Exception {
        for (final GroovyClassDoc classDoc : rootDoc.classes()) {
            this.writeClassToOutput(classDoc, destdir);
        }
    }
    
    public void writeClassToOutput(final GroovyClassDoc classDoc, final String destdir) throws Exception {
        if (classDoc.isPublic() || (classDoc.isProtected() && "true".equals(this.properties.getProperty("protectedScope"))) || (classDoc.isPackagePrivate() && "true".equals(this.properties.getProperty("packageScope"))) || "true".equals(this.properties.getProperty("privateScope"))) {
            final String destFileName = destdir + "/" + classDoc.getFullPathName() + ".html";
            System.out.println("Generating " + destFileName);
            final String renderedSrc = this.templateEngine.applyClassTemplates(classDoc);
            this.output.writeToOutput(destFileName, renderedSrc);
        }
    }
    
    public void writePackages(final GroovyRootDoc rootDoc, final String destdir) throws Exception {
        for (final GroovyPackageDoc packageDoc : rootDoc.specifiedPackages()) {
            if (!new File(packageDoc.name()).isAbsolute()) {
                this.output.makeOutputArea(destdir + "/" + packageDoc.name());
                this.writePackageToOutput(packageDoc, destdir);
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (final GroovyPackageDoc packageDoc2 : rootDoc.specifiedPackages()) {
            sb.append(packageDoc2.nameWithDots());
            sb.append("\n");
        }
        final String destFileName = destdir + "/" + "package-list";
        System.out.println("Generating " + destFileName);
        this.output.writeToOutput(destFileName, sb.toString());
    }
    
    public void writePackageToOutput(final GroovyPackageDoc packageDoc, final String destdir) throws Exception {
        final Iterator<String> templates = this.templateEngine.packageTemplatesIterator();
        while (templates.hasNext()) {
            final String template = templates.next();
            final String renderedSrc = this.templateEngine.applyPackageTemplate(template, packageDoc);
            final String destFileName = destdir + "/" + packageDoc.name() + "/" + this.tool.getFile(template);
            System.out.println("Generating " + destFileName);
            this.output.writeToOutput(destFileName, renderedSrc);
        }
    }
    
    public void writeRoot(final GroovyRootDoc rootDoc, final String destdir) throws Exception {
        this.output.makeOutputArea(destdir);
        this.writeRootDocToOutput(rootDoc, destdir);
    }
    
    public void writeRootDocToOutput(final GroovyRootDoc rootDoc, final String destdir) throws Exception {
        final Iterator<String> templates = this.templateEngine.docTemplatesIterator();
        while (templates.hasNext()) {
            final String template = templates.next();
            final String destFileName = destdir + "/" + this.tool.getFile(template);
            System.out.println("Generating " + destFileName);
            if (this.hasBinaryExtension(template)) {
                this.templateEngine.copyBinaryResource(template, destFileName);
            }
            else {
                final String renderedSrc = this.templateEngine.applyRootDocTemplate(template, rootDoc);
                this.output.writeToOutput(destFileName, renderedSrc);
            }
        }
    }
    
    private boolean hasBinaryExtension(final String template) {
        return template.endsWith(".gif") || template.endsWith(".ico");
    }
}
