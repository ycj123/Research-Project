// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

import org.apache.velocity.util.StringUtils;
import java.util.StringTokenizer;
import org.apache.velocity.Template;
import java.util.Iterator;
import org.jdom.JDOMException;
import org.xml.sax.SAXParseException;
import org.apache.velocity.context.Context;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Date;
import org.jdom.output.Format;
import org.apache.velocity.VelocityContext;
import org.apache.tools.ant.DirectoryScanner;
import org.jdom.Document;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.jdom.JDOMFactory;
import java.util.LinkedList;
import java.util.List;
import org.apache.velocity.app.VelocityEngine;
import java.io.File;
import org.jdom.input.SAXBuilder;
import org.apache.tools.ant.taskdefs.MatchingTask;

public class AnakiaTask extends MatchingTask
{
    SAXBuilder builder;
    private File destDir;
    File baseDir;
    private String style;
    private long styleSheetLastModified;
    private String projectAttribute;
    private File projectFile;
    private long projectFileLastModified;
    private boolean lastModifiedCheck;
    private String extension;
    private String templatePath;
    private File velocityPropertiesFile;
    private VelocityEngine ve;
    private List contexts;
    
    public AnakiaTask() {
        this.destDir = null;
        this.baseDir = null;
        this.style = null;
        this.styleSheetLastModified = 0L;
        this.projectAttribute = null;
        this.projectFile = null;
        this.projectFileLastModified = 0L;
        this.lastModifiedCheck = true;
        this.extension = ".html";
        this.templatePath = null;
        this.velocityPropertiesFile = null;
        this.ve = new VelocityEngine();
        this.contexts = new LinkedList();
        (this.builder = new SAXBuilder()).setFactory((JDOMFactory)new AnakiaJDOMFactory());
    }
    
    public void setBasedir(final File dir) {
        this.baseDir = dir;
    }
    
    public void setDestdir(final File dir) {
        this.destDir = dir;
    }
    
    public void setExtension(final String extension) {
        this.extension = extension;
    }
    
    public void setStyle(final String style) {
        this.style = style;
    }
    
    public void setProjectFile(final String projectAttribute) {
        this.projectAttribute = projectAttribute;
    }
    
    public void setTemplatePath(final File templatePath) {
        try {
            this.templatePath = templatePath.getCanonicalPath();
        }
        catch (IOException ioe) {
            throw new BuildException((Throwable)ioe);
        }
    }
    
    public void setVelocityPropertiesFile(final File velocityPropertiesFile) {
        this.velocityPropertiesFile = velocityPropertiesFile;
    }
    
    public void setLastModifiedCheck(final String lastmod) {
        if (lastmod.equalsIgnoreCase("false") || lastmod.equalsIgnoreCase("no") || lastmod.equalsIgnoreCase("off")) {
            this.lastModifiedCheck = false;
        }
    }
    
    public void execute() throws BuildException {
        if (this.baseDir == null) {
            this.baseDir = this.project.resolveFile(".");
        }
        if (this.destDir == null) {
            final String msg = "destdir attribute must be set!";
            throw new BuildException(msg);
        }
        if (this.style == null) {
            throw new BuildException("style attribute must be set!");
        }
        if (this.velocityPropertiesFile == null) {
            this.velocityPropertiesFile = new File("velocity.properties");
        }
        if (!this.velocityPropertiesFile.exists() && this.templatePath == null) {
            throw new BuildException("No template path and could not locate velocity.properties file: " + this.velocityPropertiesFile.getAbsolutePath());
        }
        this.log("Transforming into: " + this.destDir.getAbsolutePath(), 2);
        if (this.projectAttribute != null && this.projectAttribute.length() > 0) {
            this.projectFile = new File(this.baseDir, this.projectAttribute);
            if (this.projectFile.exists()) {
                this.projectFileLastModified = this.projectFile.lastModified();
            }
            else {
                this.log("Project file is defined, but could not be located: " + this.projectFile.getAbsolutePath(), 2);
                this.projectFile = null;
            }
        }
        Document projectDocument = null;
        try {
            if (this.velocityPropertiesFile.exists()) {
                final String file = this.velocityPropertiesFile.getAbsolutePath();
                final ExtendedProperties config = new ExtendedProperties(file);
                this.ve.setExtendedProperties(config);
            }
            if (this.templatePath != null && this.templatePath.length() > 0) {
                this.ve.setProperty("file.resource.loader.path", this.templatePath);
            }
            this.ve.init();
            this.styleSheetLastModified = this.ve.getTemplate(this.style).getLastModified();
            if (this.projectFile != null) {
                projectDocument = this.builder.build(this.projectFile);
            }
        }
        catch (Exception e) {
            this.log("Error: " + e.toString(), 2);
            throw new BuildException((Throwable)e);
        }
        final DirectoryScanner scanner = this.getDirectoryScanner(this.baseDir);
        final String[] list = scanner.getIncludedFiles();
        for (int i = 0; i < list.length; ++i) {
            this.process(list[i], projectDocument);
        }
    }
    
    private void process(final String xmlFile, final Document projectDocument) throws BuildException {
        File outFile = null;
        File inFile = null;
        Writer writer = null;
        try {
            inFile = new File(this.baseDir, xmlFile);
            outFile = new File(this.destDir, xmlFile.substring(0, xmlFile.lastIndexOf(46)) + this.extension);
            if (!this.lastModifiedCheck || inFile.lastModified() > outFile.lastModified() || this.styleSheetLastModified > outFile.lastModified() || this.projectFileLastModified > outFile.lastModified() || this.userContextsModifed(outFile.lastModified())) {
                this.ensureDirectoryFor(outFile);
                this.log("Input:  " + xmlFile, 2);
                final Document root = this.builder.build(inFile);
                final VelocityContext context = new VelocityContext();
                String encoding = (String)this.ve.getProperty("output.encoding");
                if (encoding == null || encoding.length() == 0 || encoding.equals("8859-1") || encoding.equals("8859_1")) {
                    encoding = "ISO-8859-1";
                }
                final Format f = Format.getRawFormat();
                f.setEncoding(encoding);
                final OutputWrapper ow = new OutputWrapper(f);
                context.put("root", root.getRootElement());
                context.put("xmlout", ow);
                context.put("relativePath", this.getRelativePath(xmlFile));
                context.put("treeWalk", new TreeWalker());
                context.put("xpath", new XPathTool());
                context.put("escape", new Escape());
                context.put("date", new Date());
                if (projectDocument != null) {
                    context.put("project", projectDocument.getRootElement());
                }
                for (final Context subContext : this.contexts) {
                    if (subContext == null) {
                        throw new BuildException("Found an undefined SubContext!");
                    }
                    if (subContext.getContextDocument() == null) {
                        throw new BuildException("Could not build a subContext for " + subContext.getName());
                    }
                    context.put(subContext.getName(), subContext.getContextDocument().getRootElement());
                }
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), encoding));
                final Template template = this.ve.getTemplate(this.style);
                template.merge(context, writer);
                this.log("Output: " + outFile, 2);
            }
        }
        catch (JDOMException e) {
            outFile.delete();
            if (e.getCause() != null) {
                final Throwable rootCause = e.getCause();
                if (rootCause instanceof SAXParseException) {
                    System.out.println("");
                    System.out.println("Error: " + rootCause.getMessage());
                    System.out.println("       Line: " + ((SAXParseException)rootCause).getLineNumber() + " Column: " + ((SAXParseException)rootCause).getColumnNumber());
                    System.out.println("");
                }
                else {
                    rootCause.printStackTrace();
                }
            }
            else {
                e.printStackTrace();
            }
        }
        catch (Throwable e2) {
            if (outFile != null) {
                outFile.delete();
            }
            e2.printStackTrace();
        }
        finally {
            if (writer != null) {
                try {
                    writer.flush();
                }
                catch (IOException ex) {}
                try {
                    writer.close();
                }
                catch (IOException ex2) {}
            }
        }
    }
    
    private String getRelativePath(final String file) {
        if (file == null || file.length() == 0) {
            return "";
        }
        final StringTokenizer st = new StringTokenizer(file, "/\\");
        final int slashCount = st.countTokens() - 1;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < slashCount; ++i) {
            sb.append("../");
        }
        if (sb.toString().length() > 0) {
            return StringUtils.chop(sb.toString(), 1);
        }
        return ".";
    }
    
    private void ensureDirectoryFor(final File targetFile) throws BuildException {
        final File directory = new File(targetFile.getParent());
        if (!directory.exists() && !directory.mkdirs()) {
            throw new BuildException("Unable to create directory: " + directory.getAbsolutePath());
        }
    }
    
    private boolean userContextsModifed(final long lastModified) {
        for (final Context ctx : this.contexts) {
            if (ctx.getLastModified() > lastModified) {
                return true;
            }
        }
        return false;
    }
    
    public Context createContext() {
        final Context context = new Context();
        this.contexts.add(context);
        return context;
    }
    
    public class Context
    {
        private String name;
        private Document contextDoc;
        private String file;
        
        public Context() {
            this.contextDoc = null;
        }
        
        public String getName() {
            return this.name;
        }
        
        public void setName(final String name) {
            if (name.equals("relativePath") || name.equals("treeWalk") || name.equals("xpath") || name.equals("escape") || name.equals("date") || name.equals("project")) {
                throw new IllegalArgumentException("Context name '" + name + "' is reserved by Anakia");
            }
            this.name = name;
        }
        
        public void setFile(final String file) {
            this.file = file;
        }
        
        public long getLastModified() {
            return new File(AnakiaTask.this.baseDir, this.file).lastModified();
        }
        
        public Document getContextDocument() {
            if (this.contextDoc == null) {
                final File contextFile = new File(AnakiaTask.this.baseDir, this.file);
                try {
                    this.contextDoc = AnakiaTask.this.builder.build(contextFile);
                }
                catch (Exception e) {
                    throw new BuildException((Throwable)e);
                }
            }
            return this.contextDoc;
        }
    }
}
