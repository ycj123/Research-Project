// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.texen;

import java.util.Iterator;
import org.apache.velocity.util.ClassUtils;
import java.util.Enumeration;
import org.apache.velocity.VelocityContext;
import java.io.File;
import java.io.StringWriter;
import org.apache.velocity.Template;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import org.apache.velocity.app.VelocityEngine;
import java.util.Hashtable;
import org.apache.velocity.context.Context;
import java.util.Properties;

public class Generator
{
    public static final String OUTPUT_PATH = "output.path";
    public static final String TEMPLATE_PATH = "template.path";
    private static final String DEFAULT_TEXEN_PROPERTIES = "org/apache/velocity/texen/defaults/texen.properties";
    private Properties props;
    private Context controlContext;
    private Hashtable writers;
    private static Generator instance;
    protected String outputEncoding;
    protected String inputEncoding;
    protected VelocityEngine ve;
    
    private Generator() {
        this.props = new Properties();
        this.writers = new Hashtable();
        this.setDefaultProps();
    }
    
    public static Generator getInstance() {
        return Generator.instance;
    }
    
    public void setVelocityEngine(final VelocityEngine ve) {
        this.ve = ve;
    }
    
    public Generator(final String propFile) {
        this.props = new Properties();
        this.writers = new Hashtable();
        try {
            BufferedInputStream bi = null;
            try {
                bi = new BufferedInputStream(new FileInputStream(propFile));
                this.props.load(bi);
            }
            finally {
                if (bi != null) {
                    bi.close();
                }
            }
        }
        catch (IOException e) {
            System.err.println("Could not load " + propFile + ", falling back to defaults. (" + e.getMessage() + ")");
            this.setDefaultProps();
        }
    }
    
    public Generator(final Properties props) {
        this.props = new Properties();
        this.writers = new Hashtable();
        this.props = (Properties)props.clone();
    }
    
    protected void setDefaultProps() {
        final ClassLoader classLoader = VelocityEngine.class.getClassLoader();
        try {
            InputStream inputStream = null;
            try {
                inputStream = classLoader.getResourceAsStream("org/apache/velocity/texen/defaults/texen.properties");
                this.props.load(inputStream);
            }
            finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Cannot get default properties: " + ioe.getMessage());
        }
    }
    
    public void setTemplatePath(final String templatePath) {
        this.props.put("template.path", templatePath);
    }
    
    public String getTemplatePath() {
        return this.props.getProperty("template.path");
    }
    
    public void setOutputPath(final String outputPath) {
        this.props.put("output.path", outputPath);
    }
    
    public String getOutputPath() {
        return this.props.getProperty("output.path");
    }
    
    public void setOutputEncoding(final String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }
    
    public void setInputEncoding(final String inputEncoding) {
        this.inputEncoding = inputEncoding;
    }
    
    public Writer getWriter(final String path, final String encoding) throws Exception {
        Writer writer;
        if (encoding == null || encoding.length() == 0 || encoding.equals("8859-1") || encoding.equals("8859_1")) {
            writer = new FileWriter(path);
        }
        else {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), encoding));
        }
        return writer;
    }
    
    public Template getTemplate(final String templateName, final String encoding) throws Exception {
        Template template;
        if (encoding == null || encoding.length() == 0 || encoding.equals("8859-1") || encoding.equals("8859_1")) {
            template = this.ve.getTemplate(templateName);
        }
        else {
            template = this.ve.getTemplate(templateName, encoding);
        }
        return template;
    }
    
    public String parse(final String inputTemplate, final String outputFile) throws Exception {
        return this.parse(inputTemplate, outputFile, null, null);
    }
    
    public String parse(final String inputTemplate, final String outputFile, final String objectID, final Object object) throws Exception {
        return this.parse(inputTemplate, null, outputFile, null, objectID, object);
    }
    
    public String parse(final String inputTemplate, final String inputEncoding, final String outputFile, final String outputEncoding, final String objectID, final Object object) throws Exception {
        if (objectID != null && object != null) {
            this.controlContext.put(objectID, object);
        }
        final Template template = this.getTemplate(inputTemplate, (inputEncoding != null) ? inputEncoding : this.inputEncoding);
        if (outputFile == null || outputFile.equals("")) {
            final StringWriter sw = new StringWriter();
            template.merge(this.controlContext, sw);
            return sw.toString();
        }
        Writer writer = null;
        if (this.writers.get(outputFile) == null) {
            writer = this.getWriter(this.getOutputPath() + File.separator + outputFile, (outputEncoding != null) ? outputEncoding : this.outputEncoding);
            this.writers.put(outputFile, writer);
        }
        else {
            writer = this.writers.get(outputFile);
        }
        final VelocityContext vc = new VelocityContext(this.controlContext);
        template.merge(vc, writer);
        return "";
    }
    
    public String parse(final String controlTemplate, final Context controlContext) throws Exception {
        this.fillContextDefaults(this.controlContext = controlContext);
        this.fillContextProperties(this.controlContext);
        final Template template = this.getTemplate(controlTemplate, this.inputEncoding);
        final StringWriter sw = new StringWriter();
        template.merge(controlContext, sw);
        return sw.toString();
    }
    
    protected Context getContext(final Hashtable objs) {
        this.fillContextHash(this.controlContext, objs);
        return this.controlContext;
    }
    
    protected void fillContextHash(final Context context, final Hashtable objs) {
        final Enumeration enumeration = objs.keys();
        while (enumeration.hasMoreElements()) {
            final String key = enumeration.nextElement().toString();
            context.put(key, objs.get(key));
        }
    }
    
    protected void fillContextDefaults(final Context context) {
        context.put("generator", Generator.instance);
        context.put("outputDirectory", this.getOutputPath());
    }
    
    protected void fillContextProperties(final Context context) {
        final Enumeration enumeration = this.props.propertyNames();
        while (enumeration.hasMoreElements()) {
            final String nm = enumeration.nextElement();
            if (nm.startsWith("context.objects.")) {
                final String contextObj = this.props.getProperty(nm);
                final int colon = nm.lastIndexOf(46);
                final String contextName = nm.substring(colon + 1);
                try {
                    final Object o = ClassUtils.getNewInstance(contextObj);
                    context.put(contextName, o);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void shutdown() {
        for (final Writer writer : this.writers.values()) {
            try {
                writer.flush();
            }
            catch (IOException ex) {}
            try {
                writer.close();
            }
            catch (IOException ex2) {}
        }
        this.writers.clear();
    }
    
    static {
        Generator.instance = new Generator();
    }
}
