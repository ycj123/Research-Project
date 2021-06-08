// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.texen.ant;

import java.util.Date;
import java.io.Writer;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.texen.Generator;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import java.util.Iterator;
import java.io.InputStream;
import java.io.FileInputStream;
import org.apache.velocity.util.StringUtils;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import java.io.File;
import java.util.StringTokenizer;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import org.apache.tools.ant.Task;

public class TexenTask extends Task
{
    private static final String ERR_MSG_FRAGMENT = ". For more information consult the velocity log, or invoke ant with the -debug flag.";
    protected String controlTemplate;
    protected String templatePath;
    protected String outputDirectory;
    protected String outputFile;
    protected String outputEncoding;
    protected String inputEncoding;
    protected ExtendedProperties contextProperties;
    protected boolean useClasspath;
    protected String logFile;
    protected String useResourceLoaderCache;
    protected String resourceLoaderModificationCheckInterval;
    
    public TexenTask() {
        this.useResourceLoaderCache = "false";
        this.resourceLoaderModificationCheckInterval = "2";
    }
    
    public void setControlTemplate(final String controlTemplate) {
        this.controlTemplate = controlTemplate;
    }
    
    public String getControlTemplate() {
        return this.controlTemplate;
    }
    
    public void setTemplatePath(final String templatePath) throws Exception {
        final StringBuffer resolvedPath = new StringBuffer();
        final StringTokenizer st = new StringTokenizer(templatePath, ",");
        while (st.hasMoreTokens()) {
            final File fullPath = this.project.resolveFile(st.nextToken());
            resolvedPath.append(fullPath.getCanonicalPath());
            if (st.hasMoreTokens()) {
                resolvedPath.append(",");
            }
        }
        this.templatePath = resolvedPath.toString();
        System.out.println(templatePath);
    }
    
    public String getTemplatePath() {
        return this.templatePath;
    }
    
    public void setOutputDirectory(final File outputDirectory) {
        try {
            this.outputDirectory = outputDirectory.getCanonicalPath();
        }
        catch (IOException ioe) {
            throw new BuildException((Throwable)ioe);
        }
    }
    
    public String getOutputDirectory() {
        return this.outputDirectory;
    }
    
    public void setOutputFile(final String outputFile) {
        this.outputFile = outputFile;
    }
    
    public void setOutputEncoding(final String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }
    
    public void setInputEncoding(final String inputEncoding) {
        this.inputEncoding = inputEncoding;
    }
    
    public String getOutputFile() {
        return this.outputFile;
    }
    
    public void setLogFile(final String log) {
        this.logFile = log;
    }
    
    public String getLogFile() {
        return this.logFile;
    }
    
    public void setContextProperties(final String file) {
        final String[] sources = StringUtils.split(file, ",");
        this.contextProperties = new ExtendedProperties();
        for (int i = 0; i < sources.length; ++i) {
            ExtendedProperties source = new ExtendedProperties();
            try {
                final File fullPath = this.project.resolveFile(sources[i]);
                this.log("Using contextProperties file: " + fullPath);
                source.load(new FileInputStream(fullPath));
            }
            catch (IOException e) {
                final ClassLoader classLoader = this.getClass().getClassLoader();
                try {
                    final InputStream inputStream = classLoader.getResourceAsStream(sources[i]);
                    if (inputStream == null) {
                        throw new BuildException("Context properties file " + sources[i] + " could not be found in the file system or on the classpath!");
                    }
                    source.load(inputStream);
                }
                catch (IOException ioe) {
                    source = null;
                }
            }
            if (source != null) {
                final Iterator j = source.getKeys();
                while (j.hasNext()) {
                    final String name = j.next();
                    final String value = StringUtils.nullTrim(source.getString(name));
                    this.contextProperties.setProperty(name, value);
                }
            }
        }
    }
    
    public ExtendedProperties getContextProperties() {
        return this.contextProperties;
    }
    
    public void setUseClasspath(final boolean useClasspath) {
        this.useClasspath = useClasspath;
    }
    
    public void setUseResourceLoaderCache(final String useResourceLoaderCache) {
        this.useResourceLoaderCache = useResourceLoaderCache;
    }
    
    public void setResourceLoaderModificationCheckInterval(final String resourceLoaderModificationCheckInterval) {
        this.resourceLoaderModificationCheckInterval = resourceLoaderModificationCheckInterval;
    }
    
    public Context initControlContext() throws Exception {
        return new VelocityContext();
    }
    
    public void execute() throws BuildException {
        if (this.templatePath == null && !this.useClasspath) {
            throw new BuildException("The template path needs to be defined if you are not using the classpath for locating templates!");
        }
        if (this.controlTemplate == null) {
            throw new BuildException("The control template needs to be defined!");
        }
        if (this.outputDirectory == null) {
            throw new BuildException("The output directory needs to be defined!");
        }
        if (this.outputFile == null) {
            throw new BuildException("The output file needs to be defined!");
        }
        final VelocityEngine ve = new VelocityEngine();
        try {
            if (this.templatePath != null) {
                this.log("Using templatePath: " + this.templatePath, 3);
                ve.setProperty("file.resource.loader.path", this.templatePath);
            }
            if (this.useClasspath) {
                this.log("Using classpath");
                ve.addProperty("resource.loader", "classpath");
                ve.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
                ve.setProperty("classpath.resource.loader.cache", this.useResourceLoaderCache);
                ve.setProperty("classpath.resource.loader.modificationCheckInterval", this.resourceLoaderModificationCheckInterval);
            }
            if (this.logFile != null) {
                ve.setProperty("runtime.log", this.logFile);
            }
            ve.init();
            final Generator generator = Generator.getInstance();
            generator.setVelocityEngine(ve);
            generator.setOutputPath(this.outputDirectory);
            generator.setInputEncoding(this.inputEncoding);
            generator.setOutputEncoding(this.outputEncoding);
            if (this.templatePath != null) {
                generator.setTemplatePath(this.templatePath);
            }
            final File file = new File(this.outputDirectory);
            if (!file.exists()) {
                file.mkdirs();
            }
            final String path = this.outputDirectory + File.separator + this.outputFile;
            this.log("Generating to file " + path, 2);
            final Writer writer = generator.getWriter(path, this.outputEncoding);
            final Context c = this.initControlContext();
            this.populateInitialContext(c);
            if (this.contextProperties != null) {
                final Iterator i = this.contextProperties.getKeys();
                while (i.hasNext()) {
                    String property = i.next();
                    String value = StringUtils.nullTrim(this.contextProperties.getString(property));
                    try {
                        c.put(property, new Integer(value));
                    }
                    catch (NumberFormatException nfe) {
                        final String booleanString = this.contextProperties.testBoolean(value);
                        if (booleanString != null) {
                            c.put(property, Boolean.valueOf(booleanString));
                        }
                        else {
                            if (property.endsWith("file.contents")) {
                                value = StringUtils.fileContentsToString(this.project.resolveFile(value).getCanonicalPath());
                                property = property.substring(0, property.indexOf("file.contents") - 1);
                            }
                            c.put(property, value);
                        }
                    }
                }
            }
            writer.write(generator.parse(this.controlTemplate, c));
            writer.flush();
            writer.close();
            generator.shutdown();
            this.cleanup();
        }
        catch (BuildException e) {
            throw e;
        }
        catch (MethodInvocationException e2) {
            throw new BuildException("Exception thrown by '" + e2.getReferenceName() + "." + e2.getMethodName() + "'" + ". For more information consult the velocity log, or invoke ant with the -debug flag.", e2.getWrappedThrowable());
        }
        catch (ParseErrorException e3) {
            throw new BuildException("Velocity syntax error. For more information consult the velocity log, or invoke ant with the -debug flag.", (Throwable)e3);
        }
        catch (ResourceNotFoundException e4) {
            throw new BuildException("Resource not found. For more information consult the velocity log, or invoke ant with the -debug flag.", (Throwable)e4);
        }
        catch (Exception e5) {
            throw new BuildException("Generation failed. For more information consult the velocity log, or invoke ant with the -debug flag.", (Throwable)e5);
        }
    }
    
    protected void populateInitialContext(final Context context) throws Exception {
        context.put("now", new Date().toString());
    }
    
    protected void cleanup() throws Exception {
    }
}
