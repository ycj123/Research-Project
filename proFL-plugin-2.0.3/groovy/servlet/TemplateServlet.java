// 
// Decompiled by Procyon v0.5.36
// 

package groovy.servlet;

import java.util.Date;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import groovy.text.SimpleTemplateEngine;
import javax.servlet.ServletConfig;
import java.io.Reader;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import groovy.text.Template;
import java.io.File;
import java.util.WeakHashMap;
import groovy.text.TemplateEngine;
import java.util.Map;

public class TemplateServlet extends AbstractHttpServlet
{
    private final Map cache;
    private TemplateEngine engine;
    private boolean generateBy;
    private String fileEncodingParamVal;
    private static final String GROOVY_SOURCE_ENCODING = "groovy.source.encoding";
    
    public TemplateServlet() {
        this.cache = new WeakHashMap();
        this.engine = null;
        this.generateBy = true;
        this.fileEncodingParamVal = null;
    }
    
    protected Template getTemplate(final File file) throws ServletException {
        final String key = file.getAbsolutePath();
        Template template = null;
        if (this.verbose) {
            this.log("Looking for cached template by key \"" + key + "\"");
        }
        final TemplateCacheEntry entry = this.cache.get(key);
        if (entry != null) {
            if (entry.validate(file)) {
                if (this.verbose) {
                    this.log("Cache hit! " + entry);
                }
                template = entry.template;
            }
            else if (this.verbose) {
                this.log("Cached template needs recompiliation!");
            }
        }
        else if (this.verbose) {
            this.log("Cache miss.");
        }
        if (template == null) {
            if (this.verbose) {
                this.log("Creating new template from file " + file + "...");
            }
            final String fileEncoding = (this.fileEncodingParamVal != null) ? this.fileEncodingParamVal : System.getProperty("groovy.source.encoding");
            Reader reader = null;
            try {
                Reader reader2;
                if (fileEncoding == null) {
                    reader2 = new FileReader(file);
                }
                else {
                    final FileInputStream in;
                    reader2 = new InputStreamReader(in, fileEncoding);
                    in = new FileInputStream(file);
                }
                reader = reader2;
                template = this.engine.createTemplate(reader);
            }
            catch (Exception e) {
                throw new ServletException("Creation of template failed: " + e, (Throwable)e);
            }
            finally {
                if (reader != null) {
                    try {
                        reader.close();
                    }
                    catch (IOException ex) {}
                }
            }
            this.cache.put(key, new TemplateCacheEntry(file, template, this.verbose));
            if (this.verbose) {
                this.log("Created and added template to cache. [key=" + key + "]");
            }
        }
        if (template == null) {
            throw new ServletException("Template is null? Should not happen here!");
        }
        return template;
    }
    
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.engine = this.initTemplateEngine(config);
        if (this.engine == null) {
            throw new ServletException("Template engine not instantiated.");
        }
        String value = config.getInitParameter("generated.by");
        if (value != null) {
            this.generateBy = Boolean.valueOf(value);
        }
        value = config.getInitParameter("groovy.source.encoding");
        if (value != null) {
            this.fileEncodingParamVal = value;
        }
        this.log("Servlet " + this.getClass().getName() + " initialized on " + this.engine.getClass());
    }
    
    protected TemplateEngine initTemplateEngine(final ServletConfig config) {
        final String name = config.getInitParameter("template.engine");
        if (name == null) {
            return new SimpleTemplateEngine();
        }
        try {
            return (TemplateEngine)Class.forName(name).newInstance();
        }
        catch (InstantiationException e) {
            this.log("Could not instantiate template engine: " + name, (Throwable)e);
        }
        catch (IllegalAccessException e2) {
            this.log("Could not access template engine class: " + name, (Throwable)e2);
        }
        catch (ClassNotFoundException e3) {
            this.log("Could not find template engine class: " + name, (Throwable)e3);
        }
        return null;
    }
    
    public void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (this.verbose) {
            this.log("Creating/getting cached template...");
        }
        final File file = super.getScriptUriAsFile(request);
        final String name = file.getName();
        if (!file.exists()) {
            response.sendError(404);
            return;
        }
        if (!file.canRead()) {
            response.sendError(403, "Can not read \"" + name + "\"!");
            return;
        }
        long getMillis = System.currentTimeMillis();
        final Template template = this.getTemplate(file);
        getMillis = System.currentTimeMillis() - getMillis;
        final ServletBinding binding = new ServletBinding(request, response, this.servletContext);
        this.setVariables(binding);
        response.setContentType("text/html; charset=" + this.encoding);
        response.setStatus(200);
        Writer out = (Writer)binding.getVariable("out");
        if (out == null) {
            out = response.getWriter();
        }
        if (this.verbose) {
            this.log("Making template \"" + name + "\"...");
        }
        long makeMillis = System.currentTimeMillis();
        template.make(binding.getVariables()).writeTo(out);
        makeMillis = System.currentTimeMillis() - makeMillis;
        if (this.generateBy) {
            final StringBuffer sb = new StringBuffer(100);
            sb.append("\n<!-- Generated by Groovy TemplateServlet [create/get=");
            sb.append(Long.toString(getMillis));
            sb.append(" ms, make=");
            sb.append(Long.toString(makeMillis));
            sb.append(" ms] -->\n");
            out.write(sb.toString());
        }
        response.flushBuffer();
        if (this.verbose) {
            this.log("Template \"" + name + "\" request responded. [create/get=" + getMillis + " ms, make=" + makeMillis + " ms]");
        }
    }
    
    private static class TemplateCacheEntry
    {
        Date date;
        long hit;
        long lastModified;
        long length;
        Template template;
        
        public TemplateCacheEntry(final File file, final Template template) {
            this(file, template, false);
        }
        
        public TemplateCacheEntry(final File file, final Template template, final boolean timestamp) {
            if (file == null) {
                throw new NullPointerException("file");
            }
            if (template == null) {
                throw new NullPointerException("template");
            }
            if (timestamp) {
                this.date = new Date(System.currentTimeMillis());
            }
            else {
                this.date = null;
            }
            this.hit = 0L;
            this.lastModified = file.lastModified();
            this.length = file.length();
            this.template = template;
        }
        
        public boolean validate(final File file) {
            if (file == null) {
                throw new NullPointerException("file");
            }
            if (file.lastModified() != this.lastModified) {
                return false;
            }
            if (file.length() != this.length) {
                return false;
            }
            ++this.hit;
            return true;
        }
        
        @Override
        public String toString() {
            if (this.date == null) {
                return "Hit #" + this.hit;
            }
            return "Hit #" + this.hit + " since " + this.date;
        }
    }
}
