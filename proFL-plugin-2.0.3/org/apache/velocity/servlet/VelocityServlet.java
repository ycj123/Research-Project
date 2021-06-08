// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.velocity.VelocityContext;
import java.io.UnsupportedEncodingException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import javax.servlet.ServletOutputStream;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.apache.velocity.io.VelocityWriter;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletContext;
import java.util.Properties;
import org.apache.velocity.app.Velocity;
import javax.servlet.ServletException;
import org.apache.velocity.runtime.RuntimeSingleton;
import javax.servlet.ServletConfig;
import org.apache.velocity.util.SimplePool;
import javax.servlet.http.HttpServlet;

public abstract class VelocityServlet extends HttpServlet
{
    public static final String REQUEST = "req";
    public static final String RESPONSE = "res";
    public static final String CONTENT_TYPE = "default.contentType";
    public static final String DEFAULT_CONTENT_TYPE = "text/html";
    public static final String DEFAULT_OUTPUT_ENCODING = "ISO-8859-1";
    private static String defaultContentType;
    protected static final String INIT_PROPS_KEY = "org.apache.velocity.properties";
    private static final String OLD_INIT_PROPS_KEY = "properties";
    private static SimplePool writerPool;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.initVelocity(config);
        VelocityServlet.defaultContentType = RuntimeSingleton.getString("default.contentType", "text/html");
    }
    
    protected void initVelocity(final ServletConfig config) throws ServletException {
        try {
            final Properties props = this.loadConfiguration(config);
            Velocity.init(props);
        }
        catch (Exception e) {
            throw new ServletException("Error initializing Velocity: " + e, (Throwable)e);
        }
    }
    
    protected Properties loadConfiguration(final ServletConfig config) throws IOException, FileNotFoundException {
        String propsFile = config.getInitParameter("org.apache.velocity.properties");
        if (propsFile == null || propsFile.length() == 0) {
            final ServletContext sc = config.getServletContext();
            propsFile = config.getInitParameter("properties");
            if (propsFile == null || propsFile.length() == 0) {
                propsFile = sc.getInitParameter("org.apache.velocity.properties");
                if (propsFile == null || propsFile.length() == 0) {
                    propsFile = sc.getInitParameter("properties");
                    if (propsFile != null && propsFile.length() > 0) {
                        sc.log("Use of the properties initialization parameter 'properties' has been deprecated by 'org.apache.velocity.properties'");
                    }
                }
            }
            else {
                sc.log("Use of the properties initialization parameter 'properties' has been deprecated by 'org.apache.velocity.properties'");
            }
        }
        final Properties p = new Properties();
        if (propsFile != null) {
            p.load(this.getServletContext().getResourceAsStream(propsFile));
        }
        return p;
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doRequest(request, response);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doRequest(request, response);
    }
    
    protected void doRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        Context context = null;
        try {
            context = this.createContext(request, response);
            this.setContentType(request, response);
            final Template template = this.handleRequest(request, response, context);
            if (template == null) {
                return;
            }
            this.mergeTemplate(template, context, response);
        }
        catch (Exception e) {
            this.error(request, response, e);
        }
        finally {
            this.requestCleanup(request, response, context);
        }
    }
    
    protected void requestCleanup(final HttpServletRequest request, final HttpServletResponse response, final Context context) {
    }
    
    protected void mergeTemplate(final Template template, final Context context, final HttpServletResponse response) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, IOException, UnsupportedEncodingException, Exception {
        final ServletOutputStream output = response.getOutputStream();
        VelocityWriter vw = null;
        final String encoding = response.getCharacterEncoding();
        try {
            vw = (VelocityWriter)VelocityServlet.writerPool.get();
            if (vw == null) {
                vw = new VelocityWriter(new OutputStreamWriter((OutputStream)output, encoding), 4096, true);
            }
            else {
                vw.recycle(new OutputStreamWriter((OutputStream)output, encoding));
            }
            template.merge(context, vw);
        }
        finally {
            if (vw != null) {
                try {
                    vw.flush();
                }
                catch (IOException ex) {}
                vw.recycle(null);
                VelocityServlet.writerPool.put(vw);
            }
        }
    }
    
    protected void setContentType(final HttpServletRequest request, final HttpServletResponse response) {
        String contentType = VelocityServlet.defaultContentType;
        final int index = contentType.lastIndexOf(59) + 1;
        if (index <= 0 || (index < contentType.length() && contentType.indexOf("charset", index) == -1)) {
            final String encoding = this.chooseCharacterEncoding(request);
            if (!"ISO-8859-1".equalsIgnoreCase(encoding)) {
                contentType = contentType + "; charset=" + encoding;
            }
        }
        response.setContentType(contentType);
    }
    
    protected String chooseCharacterEncoding(final HttpServletRequest request) {
        return RuntimeSingleton.getString("output.encoding", "ISO-8859-1");
    }
    
    protected Context createContext(final HttpServletRequest request, final HttpServletResponse response) {
        final VelocityContext context = new VelocityContext();
        context.put("req", request);
        context.put("res", response);
        return context;
    }
    
    public Template getTemplate(final String name) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.getTemplate(name);
    }
    
    public Template getTemplate(final String name, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        return RuntimeSingleton.getTemplate(name, encoding);
    }
    
    protected Template handleRequest(final HttpServletRequest request, final HttpServletResponse response, final Context ctx) throws Exception {
        final Template t = this.handleRequest(ctx);
        if (t == null) {
            throw new Exception("handleRequest(Context) returned null - no template selected!");
        }
        return t;
    }
    
    protected Template handleRequest(final Context ctx) throws Exception {
        throw new Exception("You must override VelocityServlet.handleRequest( Context)  or VelocityServlet.handleRequest( HttpServletRequest,  HttpServletResponse, Context)");
    }
    
    protected void error(final HttpServletRequest request, final HttpServletResponse response, final Exception cause) throws ServletException, IOException {
        final StringBuffer html = new StringBuffer();
        html.append("<html>");
        html.append("<title>Error</title>");
        html.append("<body bgcolor=\"#ffffff\">");
        html.append("<h2>VelocityServlet: Error processing the template</h2>");
        html.append("<pre>");
        final String why = cause.getMessage();
        if (why != null && why.trim().length() > 0) {
            html.append(why);
            html.append("<br>");
        }
        final StringWriter sw = new StringWriter();
        cause.printStackTrace(new PrintWriter(sw));
        html.append(sw.toString());
        html.append("</pre>");
        html.append("</body>");
        html.append("</html>");
        response.getOutputStream().print(html.toString());
    }
    
    static {
        VelocityServlet.writerPool = new SimplePool(40);
    }
}
