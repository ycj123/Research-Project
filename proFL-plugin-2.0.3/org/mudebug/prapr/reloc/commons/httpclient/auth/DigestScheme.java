// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.auth;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.httpclient.HttpConstants;
import java.security.MessageDigest;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.httpclient.UsernamePasswordCredentials;
import org.mudebug.prapr.reloc.commons.httpclient.Credentials;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class DigestScheme extends RFC2617Scheme
{
    private static final Log LOG;
    private static final char[] HEXADECIMAL;
    
    public String getID() {
        String id = this.getRealm();
        final String nonce = this.getParameter("nonce");
        if (nonce != null) {
            id = id + "-" + nonce;
        }
        return id;
    }
    
    public DigestScheme(final String challenge) throws MalformedChallengeException {
        super(challenge);
        if (this.getParameter("realm") == null) {
            throw new MalformedChallengeException("realm missing");
        }
        if (this.getParameter("nonce") == null) {
            throw new MalformedChallengeException("nonce missing");
        }
        this.getParameters().put("nc", "00000001");
    }
    
    public String getSchemeName() {
        return "digest";
    }
    
    public String authenticate(final Credentials credentials, final String method, final String uri) throws AuthenticationException {
        DigestScheme.LOG.trace("enter DigestScheme.authenticate(Credentials, String, String)");
        UsernamePasswordCredentials usernamepassword = null;
        try {
            usernamepassword = (UsernamePasswordCredentials)credentials;
        }
        catch (ClassCastException e) {
            throw new AuthenticationException("Credentials cannot be used for digest authentication: " + credentials.getClass().getName());
        }
        this.getParameters().put("cnonce", createCnonce());
        this.getParameters().put("methodname", method);
        this.getParameters().put("uri", uri);
        return authenticate(usernamepassword, this.getParameters());
    }
    
    public static String authenticate(final UsernamePasswordCredentials credentials, final Map params) throws AuthenticationException {
        DigestScheme.LOG.trace("enter DigestScheme.authenticate(UsernamePasswordCredentials, Map)");
        final String digest = createDigest(credentials.getUserName(), credentials.getPassword(), params);
        return "Digest " + createDigestHeader(credentials.getUserName(), params, digest);
    }
    
    public static String createDigest(final String uname, final String pwd, final Map params) throws AuthenticationException {
        DigestScheme.LOG.trace("enter DigestScheme.createDigest(String, String, Map)");
        final String digAlg = "MD5";
        final String uri = params.get("uri");
        final String realm = params.get("realm");
        final String nonce = params.get("nonce");
        final String nc = params.get("nc");
        final String cnonce = params.get("cnonce");
        String qop = params.get("qop");
        final String method = params.get("methodname");
        String algorithm = params.get("algorithm");
        if (algorithm == null) {
            algorithm = "MD5";
        }
        if (qop != null) {
            qop = "auth";
        }
        MessageDigest md5Helper;
        try {
            md5Helper = MessageDigest.getInstance("MD5");
        }
        catch (Exception e) {
            throw new AuthenticationException("Unsupported algorithm in HTTP Digest authentication: MD5");
        }
        String a1 = null;
        if (algorithm.equals("MD5")) {
            a1 = uname + ":" + realm + ":" + pwd;
        }
        else if (algorithm.equals("MD5-sess")) {
            final String tmp = encode(md5Helper.digest(HttpConstants.getContentBytes(uname + ":" + realm + ":" + pwd)));
            a1 = tmp + ":" + nonce + ":" + cnonce;
        }
        else {
            DigestScheme.LOG.warn("Unhandled algorithm " + algorithm + " requested");
            a1 = uname + ":" + realm + ":" + pwd;
        }
        final String md5a1 = encode(md5Helper.digest(HttpConstants.getContentBytes(a1)));
        final String a2 = method + ":" + uri;
        final String md5a2 = encode(md5Helper.digest(HttpConstants.getBytes(a2)));
        String serverDigestValue;
        if (qop == null) {
            DigestScheme.LOG.debug("Using null qop method");
            serverDigestValue = md5a1 + ":" + nonce + ":" + md5a2;
        }
        else {
            DigestScheme.LOG.debug("Using qop method " + qop);
            serverDigestValue = md5a1 + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop + ":" + md5a2;
        }
        final String serverDigest = encode(md5Helper.digest(HttpConstants.getBytes(serverDigestValue)));
        return serverDigest;
    }
    
    public static String createDigestHeader(final String uname, final Map params, final String digest) {
        DigestScheme.LOG.trace("enter DigestScheme.createDigestHeader(String, Map, String)");
        final StringBuffer sb = new StringBuffer();
        final String uri = params.get("uri");
        final String realm = params.get("realm");
        final String nonce = params.get("nonce");
        final String nc = params.get("nc");
        final String cnonce = params.get("cnonce");
        final String opaque = params.get("opaque");
        final String response = digest;
        String qop = params.get("qop");
        final String algorithm = params.get("algorithm");
        if (qop != null) {
            qop = "auth";
        }
        sb.append("username=\"" + uname + "\"").append(", realm=\"" + realm + "\"").append(", nonce=\"" + nonce + "\"").append(", uri=\"" + uri + "\"").append((qop == null) ? "" : (", qop=\"" + qop + "\"")).append((algorithm == null) ? "" : (", algorithm=\"" + algorithm + "\"")).append((qop == null) ? "" : (", nc=" + nc)).append((qop == null) ? "" : (", cnonce=\"" + cnonce + "\"")).append(", response=\"" + response + "\"").append((opaque == null) ? "" : (", opaque=\"" + opaque + "\""));
        return sb.toString();
    }
    
    private static String encode(final byte[] binaryData) {
        DigestScheme.LOG.trace("enter DigestScheme.encode(byte[])");
        if (binaryData.length != 16) {
            return null;
        }
        final char[] buffer = new char[32];
        for (int i = 0; i < 16; ++i) {
            final int low = binaryData[i] & 0xF;
            final int high = (binaryData[i] & 0xF0) >> 4;
            buffer[i * 2] = DigestScheme.HEXADECIMAL[high];
            buffer[i * 2 + 1] = DigestScheme.HEXADECIMAL[low];
        }
        return new String(buffer);
    }
    
    public static String createCnonce() throws AuthenticationException {
        DigestScheme.LOG.trace("enter DigestScheme.createCnonce()");
        final String digAlg = "MD5";
        MessageDigest md5Helper;
        try {
            md5Helper = MessageDigest.getInstance("MD5");
        }
        catch (Exception e) {
            throw new AuthenticationException("Unsupported algorithm in HTTP Digest authentication: MD5");
        }
        String cnonce = Long.toString(System.currentTimeMillis());
        cnonce = encode(md5Helper.digest(HttpConstants.getBytes(cnonce)));
        return cnonce;
    }
    
    static {
        LOG = LogFactory.getLog(DigestScheme.class);
        HEXADECIMAL = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
}
