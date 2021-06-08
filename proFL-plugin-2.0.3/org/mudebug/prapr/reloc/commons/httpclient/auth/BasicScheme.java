// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.auth;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.httpclient.util.Base64;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConstants;
import org.mudebug.prapr.reloc.commons.httpclient.UsernamePasswordCredentials;
import org.mudebug.prapr.reloc.commons.httpclient.Credentials;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class BasicScheme extends RFC2617Scheme
{
    private static final Log LOG;
    
    public BasicScheme(final String challenge) throws MalformedChallengeException {
        super(challenge);
    }
    
    public String getSchemeName() {
        return "basic";
    }
    
    public String authenticate(final Credentials credentials, final String method, final String uri) throws AuthenticationException {
        BasicScheme.LOG.trace("enter BasicScheme.authenticate(Credentials, String, String)");
        UsernamePasswordCredentials usernamepassword = null;
        try {
            usernamepassword = (UsernamePasswordCredentials)credentials;
        }
        catch (ClassCastException e) {
            throw new AuthenticationException("Credentials cannot be used for basic authentication: " + credentials.getClass().getName());
        }
        return authenticate(usernamepassword);
    }
    
    public static String authenticate(final UsernamePasswordCredentials credentials) {
        BasicScheme.LOG.trace("enter BasicScheme.authenticate(UsernamePasswordCredentials)");
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }
        final StringBuffer buffer = new StringBuffer();
        buffer.append(credentials.getUserName());
        buffer.append(":");
        buffer.append(credentials.getPassword());
        return "Basic " + HttpConstants.getAsciiString(Base64.encode(HttpConstants.getContentBytes(buffer.toString())));
    }
    
    static {
        LOG = LogFactory.getLog(BasicScheme.class);
    }
}
