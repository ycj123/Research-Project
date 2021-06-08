// 
// Decompiled by Procyon v0.5.36
// 

package groovy.servlet;

import java.util.Locale;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import org.codehaus.groovy.GroovyBugError;
import java.io.OutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import org.codehaus.groovy.runtime.MethodClosure;
import groovy.xml.MarkupBuilder;
import java.util.Enumeration;
import java.util.Map;
import java.util.LinkedHashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import groovy.lang.Binding;

public class ServletBinding extends Binding
{
    private boolean initialized;
    
    public ServletBinding(final HttpServletRequest request, final HttpServletResponse response, final ServletContext context) {
        super.setVariable("request", request);
        super.setVariable("response", response);
        super.setVariable("context", context);
        super.setVariable("application", context);
        super.setVariable("session", request.getSession(false));
        final Map params = new LinkedHashMap();
        final Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            final String name = names.nextElement();
            if (!super.getVariables().containsKey(name)) {
                final String[] values = request.getParameterValues(name);
                if (values.length == 1) {
                    params.put(name, values[0]);
                }
                else {
                    params.put(name, values);
                }
            }
        }
        super.setVariable("params", params);
        final Map<String, String> headers = new LinkedHashMap<String, String>();
        final Enumeration names2 = request.getHeaderNames();
        while (names2.hasMoreElements()) {
            final String headerName = names2.nextElement();
            final String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        super.setVariable("headers", headers);
    }
    
    @Override
    public void setVariable(final String name, final Object value) {
        this.lazyInit();
        this.validateArgs(name, "Can't bind variable to");
        this.excludeReservedName(name, "out");
        this.excludeReservedName(name, "sout");
        this.excludeReservedName(name, "html");
        this.excludeReservedName(name, "forward");
        this.excludeReservedName(name, "include");
        this.excludeReservedName(name, "redirect");
        super.setVariable(name, value);
    }
    
    @Override
    public Map getVariables() {
        this.lazyInit();
        return super.getVariables();
    }
    
    @Override
    public Object getVariable(final String name) {
        this.lazyInit();
        this.validateArgs(name, "No variable with");
        return super.getVariable(name);
    }
    
    private void lazyInit() {
        if (this.initialized) {
            return;
        }
        this.initialized = true;
        final HttpServletResponse response = (HttpServletResponse)super.getVariable("response");
        final ServletContext context = (ServletContext)super.getVariable("context");
        final ServletOutput output = new ServletOutput(response);
        super.setVariable("out", output.getWriter());
        super.setVariable("sout", output.getOutputStream());
        super.setVariable("html", new MarkupBuilder(output.getWriter()));
        MethodClosure c = new MethodClosure(this, "forward");
        super.setVariable("forward", c);
        c = new MethodClosure(this, "include");
        super.setVariable("include", c);
        c = new MethodClosure(this, "redirect");
        super.setVariable("redirect", c);
    }
    
    private void validateArgs(final String name, final String message) {
        if (name == null) {
            throw new IllegalArgumentException(message + " null key.");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException(message + " blank key name. [length=0]");
        }
    }
    
    private void excludeReservedName(final String name, final String reservedName) {
        if (reservedName.equals(name)) {
            throw new IllegalArgumentException("Can't bind variable to key named '" + name + "'.");
        }
    }
    
    public void forward(final String path) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest)super.getVariable("request");
        final HttpServletResponse response = (HttpServletResponse)super.getVariable("response");
        final RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
    
    public void include(final String path) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest)super.getVariable("request");
        final HttpServletResponse response = (HttpServletResponse)super.getVariable("response");
        final RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.include((ServletRequest)request, (ServletResponse)response);
    }
    
    public void redirect(final String location) throws IOException {
        final HttpServletResponse response = (HttpServletResponse)super.getVariable("response");
        response.sendRedirect(location);
    }
    
    private static class InvalidOutputStream extends OutputStream
    {
        @Override
        public void write(final int b) {
            throw new GroovyBugError("Any write calls to this stream are invalid!");
        }
    }
    
    private static class ServletOutput
    {
        private HttpServletResponse response;
        private ServletOutputStream outputStream;
        private PrintWriter writer;
        
        public ServletOutput(final HttpServletResponse response) {
            this.response = response;
        }
        
        private ServletOutputStream getResponseStream() throws IOException {
            if (this.writer != null) {
                throw new IllegalStateException("The variable 'out' or 'html' have been used already. Use either out/html or sout, not both.");
            }
            if (this.outputStream == null) {
                this.outputStream = this.response.getOutputStream();
            }
            return this.outputStream;
        }
        
        public ServletOutputStream getOutputStream() {
            return new ServletOutputStream() {
                public void write(final int b) throws IOException {
                    ServletOutput.this.getResponseStream().write(b);
                }
                
                public void close() throws IOException {
                    ServletOutput.this.getResponseStream().close();
                }
                
                public void flush() throws IOException {
                    ServletOutput.this.getResponseStream().flush();
                }
                
                public void write(final byte[] b) throws IOException {
                    ServletOutput.this.getResponseStream().write(b);
                }
                
                public void write(final byte[] b, final int off, final int len) throws IOException {
                    ServletOutput.this.getResponseStream().write(b, off, len);
                }
            };
        }
        
        private PrintWriter getResponseWriter() {
            if (this.outputStream != null) {
                throw new IllegalStateException("The variable 'sout' have been used already. Use either out/html or sout, not both.");
            }
            if (this.writer == null) {
                try {
                    this.writer = this.response.getWriter();
                }
                catch (IOException ioe) {
                    this.writer = new PrintWriter(new ByteArrayOutputStream());
                    throw new IllegalStateException("unable to get response writer", ioe);
                }
            }
            return this.writer;
        }
        
        public PrintWriter getWriter() {
            return new PrintWriter(new InvalidOutputStream()) {
                @Override
                public boolean checkError() {
                    return ServletOutput.this.getResponseWriter().checkError();
                }
                
                @Override
                public void close() {
                    ServletOutput.this.getResponseWriter().close();
                }
                
                @Override
                public void flush() {
                    ServletOutput.this.getResponseWriter().flush();
                }
                
                @Override
                public void write(final char[] buf) {
                    ServletOutput.this.getResponseWriter().write(buf);
                }
                
                @Override
                public void write(final char[] buf, final int off, final int len) {
                    ServletOutput.this.getResponseWriter().write(buf, off, len);
                }
                
                @Override
                public void write(final int c) {
                    ServletOutput.this.getResponseWriter().write(c);
                }
                
                @Override
                public void write(final String s, final int off, final int len) {
                    ServletOutput.this.getResponseWriter().write(s, off, len);
                }
                
                @Override
                public void println() {
                    ServletOutput.this.getResponseWriter().println();
                }
                
                @Override
                public PrintWriter format(final String format, final Object... args) {
                    ServletOutput.this.getResponseWriter().format(format, args);
                    return this;
                }
                
                @Override
                public PrintWriter format(final Locale l, final String format, final Object... args) {
                    ServletOutput.this.getResponseWriter().format(l, format, args);
                    return this;
                }
            };
        }
    }
}
