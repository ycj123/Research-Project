// 
// Decompiled by Procyon v0.5.36
// 

package groovy.servlet;

import groovy.util.ResourceConnector;
import java.io.IOException;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import groovy.util.ScriptException;
import groovy.util.ResourceException;
import groovy.lang.Binding;
import groovy.lang.Closure;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import groovy.util.GroovyScriptEngine;

public class GroovyServlet extends AbstractHttpServlet
{
    private GroovyScriptEngine gse;
    
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.gse = this.createGroovyScriptEngine();
        this.servletContext.log("Groovy servlet initialized on " + this.gse + ".");
    }
    
    public void service(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String scriptUri = this.getScriptUri(request);
        response.setContentType("text/html; charset=" + this.encoding);
        final ServletBinding binding = new ServletBinding(request, response, this.servletContext);
        this.setVariables(binding);
        try {
            final Closure closure = new Closure(this.gse) {
                @Override
                public Object call() {
                    try {
                        return ((GroovyScriptEngine)this.getDelegate()).run(scriptUri, binding);
                    }
                    catch (ResourceException e) {
                        throw new RuntimeException(e);
                    }
                    catch (ScriptException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            };
            GroovyCategorySupport.use(ServletCategory.class, closure);
        }
        catch (RuntimeException runtimeException) {
            final StringBuffer error = new StringBuffer("GroovyServlet Error: ");
            error.append(" script: '");
            error.append(scriptUri);
            error.append("': ");
            final Throwable e = runtimeException.getCause();
            if (e == null) {
                error.append(" Script processing failed.");
                error.append(runtimeException.getMessage());
                if (runtimeException.getStackTrace().length > 0) {
                    error.append(runtimeException.getStackTrace()[0].toString());
                }
                this.servletContext.log(error.toString());
                System.err.println(error.toString());
                runtimeException.printStackTrace(System.err);
                response.sendError(500, error.toString());
                return;
            }
            if (e instanceof ResourceException) {
                error.append(" Script not found, sending 404.");
                this.servletContext.log(error.toString());
                System.err.println(error.toString());
                response.sendError(404);
                return;
            }
            this.servletContext.log("An error occurred processing the request", (Throwable)runtimeException);
            error.append(e.getMessage());
            if (e.getStackTrace().length > 0) {
                error.append(e.getStackTrace()[0].toString());
            }
            this.servletContext.log(e.toString());
            System.err.println(e.toString());
            runtimeException.printStackTrace(System.err);
            response.sendError(500, e.toString());
        }
    }
    
    protected GroovyScriptEngine createGroovyScriptEngine() {
        return new GroovyScriptEngine(this);
    }
}
