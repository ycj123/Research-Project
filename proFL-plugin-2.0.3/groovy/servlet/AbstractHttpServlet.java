// 
// Decompiled by Procyon v0.5.36
// 

package groovy.servlet;

import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import groovy.util.ResourceException;
import java.net.URLConnection;
import java.util.regex.Matcher;
import javax.servlet.ServletContext;
import groovy.util.ResourceConnector;
import javax.servlet.http.HttpServlet;

public abstract class AbstractHttpServlet extends HttpServlet implements ResourceConnector
{
    public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
    public static final String INC_PATH_INFO = "javax.servlet.include.path_info";
    public static final String INC_REQUEST_URI = "javax.servlet.include.request_uri";
    public static final String INC_SERVLET_PATH = "javax.servlet.include.servlet_path";
    protected ServletContext servletContext;
    protected Matcher resourceNameMatcher;
    protected String resourceNameReplacement;
    protected boolean resourceNameReplaceAll;
    protected boolean verbose;
    protected String encoding;
    protected boolean reflection;
    private boolean logGROOVY861;
    
    public AbstractHttpServlet() {
        this.encoding = "UTF-8";
        this.servletContext = null;
        this.resourceNameMatcher = null;
        this.resourceNameReplacement = null;
        this.resourceNameReplaceAll = true;
        this.verbose = false;
        this.reflection = false;
        this.logGROOVY861 = false;
    }
    
    public URLConnection getResourceConnection(String name) throws ResourceException {
        final String basePath = this.servletContext.getRealPath("/");
        if (name.startsWith(basePath)) {
            name = name.substring(basePath.length());
        }
        name = name.replaceAll("\\\\", "/");
        if (name.startsWith("/")) {
            name = name.substring(1);
        }
        try {
            String tryScriptName = "/" + name;
            URL url = this.servletContext.getResource(tryScriptName);
            if (url == null) {
                tryScriptName = "/WEB-INF/groovy/" + name;
                url = this.servletContext.getResource("/WEB-INF/groovy/" + name);
            }
            if (url == null) {
                throw new ResourceException("Resource \"" + name + "\" not found!");
            }
            url = new URL("file", "", this.servletContext.getRealPath(tryScriptName));
            return url.openConnection();
        }
        catch (IOException e) {
            throw new ResourceException("Problems getting resource named \"" + name + "\"!", e);
        }
    }
    
    private boolean isFile(final URL ret) {
        return ret != null && ret.getProtocol().equals("file");
    }
    
    protected String getScriptUri(final HttpServletRequest request) {
        if (this.logGROOVY861) {
            this.log("Logging request class and its class loader:");
            this.log(" c = request.getClass() :\"" + request.getClass() + "\"");
            this.log(" l = c.getClassLoader() :\"" + request.getClass().getClassLoader() + "\"");
            this.log(" l.getClass()           :\"" + request.getClass().getClassLoader().getClass() + "\"");
            this.logGROOVY861 = this.verbose;
        }
        String uri = null;
        String info = null;
        uri = (String)request.getAttribute("javax.servlet.include.servlet_path");
        if (uri != null) {
            info = (String)request.getAttribute("javax.servlet.include.path_info");
            if (info != null) {
                uri += info;
            }
            return this.applyResourceNameMatcher(uri);
        }
        uri = request.getServletPath();
        info = request.getPathInfo();
        if (info != null) {
            uri += info;
        }
        return this.applyResourceNameMatcher(uri);
    }
    
    private String applyResourceNameMatcher(final String aUri) {
        String uri = aUri;
        final Matcher matcher = this.resourceNameMatcher;
        if (matcher != null) {
            matcher.reset(uri);
            String replaced;
            if (this.resourceNameReplaceAll) {
                replaced = matcher.replaceAll(this.resourceNameReplacement);
            }
            else {
                replaced = matcher.replaceFirst(this.resourceNameReplacement);
            }
            if (!uri.equals(replaced)) {
                if (this.verbose) {
                    this.log("Replaced resource name \"" + uri + "\" with \"" + replaced + "\".");
                }
                uri = replaced;
            }
        }
        return uri;
    }
    
    protected File getScriptUriAsFile(final HttpServletRequest request) {
        final String uri = this.getScriptUri(request);
        final String real = this.servletContext.getRealPath(uri);
        return new File(real).getAbsoluteFile();
    }
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.servletContext = config.getServletContext();
        String value = config.getInitParameter("verbose");
        if (value != null) {
            this.verbose = Boolean.valueOf(value);
        }
        value = config.getInitParameter("encoding");
        if (value != null) {
            this.encoding = value;
        }
        if (this.verbose) {
            this.log("Parsing init parameters...");
        }
        final String regex = config.getInitParameter("resource.name.regex");
        if (regex != null) {
            final String replacement = config.getInitParameter("resource.name.replacement");
            if (replacement == null) {
                final Exception npex = new NullPointerException("resource.name.replacement");
                final String message = "Init-param 'resource.name.replacement' not specified!";
                this.log(message, (Throwable)npex);
                throw new ServletException(message, (Throwable)npex);
            }
            final int flags = 0;
            this.resourceNameMatcher = Pattern.compile(regex, flags).matcher("");
            this.resourceNameReplacement = replacement;
            final String all = config.getInitParameter("resource.name.replace.all");
            if (all != null) {
                this.resourceNameReplaceAll = Boolean.valueOf(all);
            }
        }
        value = config.getInitParameter("logGROOVY861");
        if (value != null) {
            this.logGROOVY861 = Boolean.valueOf(value);
        }
        if (this.verbose) {
            this.log("(Abstract) init done. Listing some parameter name/value pairs:");
            this.log("verbose = " + this.verbose);
            this.log("reflection = " + this.reflection);
            this.log("logGROOVY861 = " + this.logGROOVY861);
            if (this.resourceNameMatcher != null) {
                this.log("resource.name.regex = " + this.resourceNameMatcher.pattern().pattern());
            }
            else {
                this.log("resource.name.regex = null");
            }
            this.log("resource.name.replacement = " + this.resourceNameReplacement);
        }
    }
    
    protected void setVariables(final ServletBinding binding) {
    }
}
