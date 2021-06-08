// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.methods;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.httpclient.Header;
import java.util.StringTokenizer;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConnection;
import org.mudebug.prapr.reloc.commons.httpclient.HttpState;
import java.util.Enumeration;
import java.util.Vector;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.httpclient.HttpMethodBase;

public class OptionsMethod extends HttpMethodBase
{
    private static final Log LOG;
    private Vector methodsAllowed;
    
    public OptionsMethod() {
        this.methodsAllowed = new Vector();
    }
    
    public OptionsMethod(final String uri) {
        super(uri);
        this.methodsAllowed = new Vector();
    }
    
    public String getName() {
        return "OPTIONS";
    }
    
    public boolean isAllowed(final String method) {
        this.checkUsed();
        return this.methodsAllowed.contains(method);
    }
    
    public Enumeration getAllowedMethods() {
        this.checkUsed();
        return this.methodsAllowed.elements();
    }
    
    protected void processResponseHeaders(final HttpState state, final HttpConnection conn) {
        OptionsMethod.LOG.trace("enter OptionsMethod.processResponseHeaders(HttpState, HttpConnection)");
        final Header allowHeader = this.getResponseHeader("allow");
        if (allowHeader != null) {
            final String allowHeaderValue = allowHeader.getValue();
            final StringTokenizer tokenizer = new StringTokenizer(allowHeaderValue, ",");
            while (tokenizer.hasMoreElements()) {
                final String methodAllowed = tokenizer.nextToken().trim().toUpperCase();
                this.methodsAllowed.addElement(methodAllowed);
            }
        }
    }
    
    public boolean needContentLength() {
        return false;
    }
    
    static {
        LOG = LogFactory.getLog(OptionsMethod.class);
    }
}
