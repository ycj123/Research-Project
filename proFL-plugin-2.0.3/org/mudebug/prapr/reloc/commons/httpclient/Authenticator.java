// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.httpclient.auth.AuthScheme;
import org.mudebug.prapr.reloc.commons.httpclient.auth.HttpAuthenticator;
import java.util.ArrayList;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class Authenticator
{
    private static final Log LOG;
    public static final String WWW_AUTH = "WWW-Authenticate";
    public static final String WWW_AUTH_RESP = "Authorization";
    public static final String PROXY_AUTH = "Proxy-Authenticate";
    public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
    
    public static boolean authenticate(final HttpMethod method, final HttpState state) throws HttpException, UnsupportedOperationException {
        Authenticator.LOG.trace("enter Authenticator.authenticate(HttpMethod, HttpState)");
        return authenticate(method, state, false);
    }
    
    public static boolean authenticateProxy(final HttpMethod method, final HttpState state) throws HttpException, UnsupportedOperationException {
        Authenticator.LOG.trace("enter Authenticator.authenticateProxy(HttpMethod, HttpState)");
        return authenticate(method, state, true);
    }
    
    private static boolean authenticate(final HttpMethod method, final HttpState state, final boolean proxy) throws HttpException, UnsupportedOperationException {
        Authenticator.LOG.trace("enter Authenticator.authenticate(HttpMethod, HttpState, Header, String)");
        return authenticate(method, null, state, proxy);
    }
    
    private static boolean authenticate(final HttpMethod method, final HttpConnection conn, final HttpState state, final boolean proxy) throws HttpException, UnsupportedOperationException {
        final String challengeheader = proxy ? "Proxy-Authenticate" : "WWW-Authenticate";
        Header[] headers = method.getResponseHeaders();
        ArrayList headerlist = new ArrayList();
        for (int i = 0; i < headers.length; ++i) {
            final Header header = headers[i];
            if (header.getName().equalsIgnoreCase(challengeheader)) {
                headerlist.add(header);
            }
        }
        headers = headerlist.toArray(new Header[headerlist.size()]);
        headerlist = null;
        if (headers.length == 0) {
            if (!state.isAuthenticationPreemptive()) {
                return false;
            }
            Authenticator.LOG.debug("Preemptively sending default basic credentials");
            if (proxy) {
                return HttpAuthenticator.authenticateProxyDefault(method, conn, state);
            }
            return HttpAuthenticator.authenticateDefault(method, conn, state);
        }
        else {
            final AuthScheme authscheme = HttpAuthenticator.selectAuthScheme(headers);
            if (Authenticator.LOG.isDebugEnabled()) {
                Authenticator.LOG.debug("Using " + authscheme.getSchemeName() + " authentication scheme");
            }
            if (proxy) {
                return HttpAuthenticator.authenticateProxy(authscheme, method, conn, state);
            }
            return HttpAuthenticator.authenticate(authscheme, method, conn, state);
        }
    }
    
    static {
        LOG = LogFactory.getLog(Authenticator.class);
    }
}
