// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.auth;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.httpclient.Credentials;
import org.mudebug.prapr.reloc.commons.httpclient.UsernamePasswordCredentials;
import org.mudebug.prapr.reloc.commons.httpclient.HttpState;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConnection;
import org.mudebug.prapr.reloc.commons.httpclient.HttpMethod;
import java.util.Map;
import java.util.HashMap;
import org.mudebug.prapr.reloc.commons.httpclient.Header;
import org.mudebug.prapr.reloc.commons.logging.Log;

public final class HttpAuthenticator
{
    private static final Log LOG;
    public static final String WWW_AUTH = "WWW-Authenticate";
    public static final String WWW_AUTH_RESP = "Authorization";
    public static final String PROXY_AUTH = "Proxy-Authenticate";
    public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
    
    public static AuthScheme selectAuthScheme(final Header[] challenges) throws MalformedChallengeException {
        HttpAuthenticator.LOG.trace("enter HttpAuthenticator.selectAuthScheme(Header[])");
        if (challenges == null) {
            throw new IllegalArgumentException("Array of challenges may not be null");
        }
        if (challenges.length == 0) {
            throw new IllegalArgumentException("Array of challenges may not be empty");
        }
        String challenge = null;
        final Map challengemap = new HashMap(challenges.length);
        for (int i = 0; i < challenges.length; ++i) {
            challenge = challenges[i].getValue();
            final String s = AuthChallengeParser.extractScheme(challenge);
            challengemap.put(s, challenge);
        }
        challenge = challengemap.get("ntlm");
        if (challenge != null) {
            return new NTLMScheme(challenge);
        }
        challenge = challengemap.get("digest");
        if (challenge != null) {
            return new DigestScheme(challenge);
        }
        challenge = challengemap.get("basic");
        if (challenge != null) {
            return new BasicScheme(challenge);
        }
        throw new UnsupportedOperationException("Authentication scheme(s) not supported: " + challengemap.toString());
    }
    
    private static boolean doAuthenticateDefault(final HttpMethod method, final HttpConnection conn, final HttpState state, final boolean proxy) throws AuthenticationException {
        if (method == null) {
            throw new IllegalArgumentException("HTTP method may not be null");
        }
        if (state == null) {
            throw new IllegalArgumentException("HTTP state may not be null");
        }
        String host = null;
        if (conn != null) {
            host = (proxy ? conn.getProxyHost() : conn.getHost());
        }
        final Credentials credentials = proxy ? state.getProxyCredentials(null, host) : state.getCredentials(null, host);
        if (credentials == null) {
            if (HttpAuthenticator.LOG.isWarnEnabled()) {
                HttpAuthenticator.LOG.warn("Default credentials for " + host + " not available");
            }
            return false;
        }
        if (!(credentials instanceof UsernamePasswordCredentials)) {
            throw new AuthenticationException("Credentials cannot be used for basic authentication: " + credentials.toString());
        }
        final String auth = BasicScheme.authenticate((UsernamePasswordCredentials)credentials);
        if (auth != null) {
            final String s = proxy ? "Proxy-Authorization" : "Authorization";
            method.setRequestHeader(s, auth);
            return true;
        }
        return false;
    }
    
    public static boolean authenticateDefault(final HttpMethod method, final HttpConnection conn, final HttpState state) throws AuthenticationException {
        HttpAuthenticator.LOG.trace("enter HttpAuthenticator.authenticateDefault(HttpMethod, HttpConnection, HttpState)");
        return doAuthenticateDefault(method, conn, state, false);
    }
    
    public static boolean authenticateProxyDefault(final HttpMethod method, final HttpConnection conn, final HttpState state) throws AuthenticationException {
        HttpAuthenticator.LOG.trace("enter HttpAuthenticator.authenticateProxyDefault(HttpMethod, HttpState)");
        return doAuthenticateDefault(method, conn, state, true);
    }
    
    private static boolean doAuthenticate(final AuthScheme authscheme, final HttpMethod method, final HttpConnection conn, final HttpState state, final boolean proxy) throws AuthenticationException {
        if (authscheme == null) {
            throw new IllegalArgumentException("Authentication scheme may not be null");
        }
        if (method == null) {
            throw new IllegalArgumentException("HTTP method may not be null");
        }
        if (state == null) {
            throw new IllegalArgumentException("HTTP state may not be null");
        }
        String host = null;
        if (conn != null) {
            if (proxy) {
                host = conn.getProxyHost();
            }
            else {
                host = conn.getVirtualHost();
                if (host == null) {
                    host = conn.getHost();
                }
            }
        }
        String realm = authscheme.getRealm();
        if (HttpAuthenticator.LOG.isDebugEnabled()) {
            final StringBuffer buffer = new StringBuffer();
            buffer.append("Authenticating with the ");
            if (realm == null) {
                buffer.append("default");
            }
            else {
                buffer.append('\'');
                buffer.append(realm);
                buffer.append('\'');
            }
            buffer.append(" authentication realm at ");
            buffer.append(host);
            HttpAuthenticator.LOG.debug(buffer.toString());
        }
        if (realm == null) {
            realm = host;
        }
        final Credentials credentials = proxy ? state.getProxyCredentials(realm, host) : state.getCredentials(realm, host);
        if (credentials == null) {
            throw new AuthenticationException("No credentials available for the '" + authscheme.getRealm() + "' authentication realm at " + host);
        }
        final String auth = authscheme.authenticate(credentials, method.getName(), method.getPath());
        if (auth != null) {
            final String s = proxy ? "Proxy-Authorization" : "Authorization";
            method.setRequestHeader(s, auth);
            return true;
        }
        return false;
    }
    
    public static boolean authenticate(final AuthScheme authscheme, final HttpMethod method, final HttpConnection conn, final HttpState state) throws AuthenticationException {
        HttpAuthenticator.LOG.trace("enter HttpAuthenticator.authenticate(AuthScheme, HttpMethod, HttpConnection, HttpState)");
        return doAuthenticate(authscheme, method, conn, state, false);
    }
    
    public static boolean authenticateProxy(final AuthScheme authscheme, final HttpMethod method, final HttpConnection conn, final HttpState state) throws AuthenticationException {
        HttpAuthenticator.LOG.trace("enter HttpAuthenticator.authenticateProxy(AuthScheme, HttpMethod, HttpState)");
        return doAuthenticate(authscheme, method, conn, state, true);
    }
    
    static {
        LOG = LogFactory.getLog(HttpAuthenticator.class);
    }
}
