// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.cookie;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.util.List;
import java.util.LinkedList;
import org.mudebug.prapr.reloc.commons.httpclient.util.DateParseException;
import org.mudebug.prapr.reloc.commons.httpclient.util.DateParser;
import org.mudebug.prapr.reloc.commons.httpclient.Header;
import org.mudebug.prapr.reloc.commons.httpclient.NameValuePair;
import java.util.Date;
import org.mudebug.prapr.reloc.commons.httpclient.HttpException;
import org.mudebug.prapr.reloc.commons.httpclient.HeaderElement;
import org.mudebug.prapr.reloc.commons.httpclient.Cookie;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class CookieSpecBase implements CookieSpec
{
    protected static final Log LOG;
    
    public Cookie[] parse(String host, final int port, String path, final boolean secure, final String header) throws MalformedCookieException {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, Header)");
        if (host == null) {
            throw new IllegalArgumentException("Host of origin may not be null");
        }
        if (host.trim().equals("")) {
            throw new IllegalArgumentException("Host of origin may not be blank");
        }
        if (port < 0) {
            throw new IllegalArgumentException("Invalid port: " + port);
        }
        if (path == null) {
            throw new IllegalArgumentException("Path of origin may not be null.");
        }
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null.");
        }
        if (path.trim().equals("")) {
            path = "/";
        }
        host = host.toLowerCase();
        HeaderElement[] headerElements = null;
        try {
            headerElements = HeaderElement.parse(header);
        }
        catch (HttpException e) {
            throw new MalformedCookieException(e.getMessage());
        }
        String defaultPath = path;
        int lastSlashIndex = defaultPath.lastIndexOf("/");
        if (lastSlashIndex >= 0) {
            if (lastSlashIndex == 0) {
                lastSlashIndex = 1;
            }
            defaultPath = defaultPath.substring(0, lastSlashIndex);
        }
        final Cookie[] cookies = new Cookie[headerElements.length];
        for (int i = 0; i < headerElements.length; ++i) {
            final HeaderElement headerelement = headerElements[i];
            Cookie cookie = null;
            try {
                cookie = new Cookie(host, headerelement.getName(), headerelement.getValue(), defaultPath, null, false);
            }
            catch (IllegalArgumentException e2) {
                throw new MalformedCookieException(e2.getMessage());
            }
            final NameValuePair[] parameters = headerelement.getParameters();
            if (parameters != null) {
                for (int j = 0; j < parameters.length; ++j) {
                    this.parseAttribute(parameters[j], cookie);
                }
            }
            cookies[i] = cookie;
        }
        return cookies;
    }
    
    public Cookie[] parse(final String host, final int port, final String path, final boolean secure, final Header header) throws MalformedCookieException {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, String)");
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null.");
        }
        return this.parse(host, port, path, secure, header.getValue());
    }
    
    public void parseAttribute(final NameValuePair attribute, final Cookie cookie) throws MalformedCookieException {
        if (attribute == null) {
            throw new IllegalArgumentException("Attribute may not be null.");
        }
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null.");
        }
        final String paramName = attribute.getName().toLowerCase();
        String paramValue = attribute.getValue();
        if (paramName.equals("path")) {
            if (paramValue == null || paramValue.trim().equals("")) {
                paramValue = "/";
            }
            cookie.setPath(paramValue);
            cookie.setPathAttributeSpecified(true);
        }
        else if (paramName.equals("domain")) {
            if (paramValue == null) {
                throw new MalformedCookieException("Missing value for domain attribute");
            }
            if (paramValue.trim().equals("")) {
                throw new MalformedCookieException("Blank value for domain attribute");
            }
            cookie.setDomain(paramValue);
            cookie.setDomainAttributeSpecified(true);
        }
        else if (paramName.equals("max-age")) {
            if (paramValue == null) {
                throw new MalformedCookieException("Missing value for max-age attribute");
            }
            int age;
            try {
                age = Integer.parseInt(paramValue);
            }
            catch (NumberFormatException e) {
                throw new MalformedCookieException("Invalid max-age attribute: " + e.getMessage());
            }
            cookie.setExpiryDate(new Date(System.currentTimeMillis() + age * 1000L));
        }
        else if (paramName.equals("secure")) {
            cookie.setSecure(true);
        }
        else if (paramName.equals("comment")) {
            cookie.setComment(paramValue);
        }
        else if (paramName.equals("expires")) {
            if (paramValue == null) {
                throw new MalformedCookieException("Missing value for expires attribute");
            }
            if (paramValue.length() > 1 && paramValue.startsWith("'") && paramValue.endsWith("'")) {
                paramValue = paramValue.substring(1, paramValue.length() - 1);
            }
            try {
                cookie.setExpiryDate(DateParser.parseDate(paramValue));
            }
            catch (DateParseException dpe) {
                CookieSpecBase.LOG.debug("Error parsing cookie date", dpe);
                throw new MalformedCookieException("Unable to parse expiration date parameter: " + paramValue);
            }
        }
        else if (CookieSpecBase.LOG.isDebugEnabled()) {
            CookieSpecBase.LOG.debug("Unrecognized cookie attribute: " + attribute.toString());
        }
    }
    
    public void validate(String host, final int port, String path, final boolean secure, final Cookie cookie) throws MalformedCookieException {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.validate(String, port, path, boolean, Cookie)");
        if (host == null) {
            throw new IllegalArgumentException("Host of origin may not be null");
        }
        if (host.trim().equals("")) {
            throw new IllegalArgumentException("Host of origin may not be blank");
        }
        if (port < 0) {
            throw new IllegalArgumentException("Invalid port: " + port);
        }
        if (path == null) {
            throw new IllegalArgumentException("Path of origin may not be null.");
        }
        if (path.trim().equals("")) {
            path = "/";
        }
        host = host.toLowerCase();
        if (cookie.getVersion() < 0) {
            throw new MalformedCookieException("Illegal version number " + cookie.getValue());
        }
        if (host.indexOf(".") >= 0) {
            if (!host.endsWith(cookie.getDomain())) {
                String s = cookie.getDomain();
                if (s.startsWith(".")) {
                    s = s.substring(1, s.length());
                }
                if (!host.equals(s)) {
                    throw new MalformedCookieException("Illegal domain attribute \"" + cookie.getDomain() + "\". Domain of origin: \"" + host + "\"");
                }
            }
        }
        else if (!host.equals(cookie.getDomain())) {
            throw new MalformedCookieException("Illegal domain attribute \"" + cookie.getDomain() + "\". Domain of origin: \"" + host + "\"");
        }
        if (!path.startsWith(cookie.getPath())) {
            throw new MalformedCookieException("Illegal path attribute \"" + cookie.getPath() + "\". Path of origin: \"" + path + "\"");
        }
    }
    
    public boolean match(String host, final int port, String path, final boolean secure, final Cookie cookie) {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie");
        if (host == null) {
            throw new IllegalArgumentException("Host of origin may not be null");
        }
        if (host.trim().equals("")) {
            throw new IllegalArgumentException("Host of origin may not be blank");
        }
        if (port < 0) {
            throw new IllegalArgumentException("Invalid port: " + port);
        }
        if (path == null) {
            throw new IllegalArgumentException("Path of origin may not be null.");
        }
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null");
        }
        if (path.trim().equals("")) {
            path = "/";
        }
        host = host.toLowerCase();
        if (cookie.getDomain() == null) {
            CookieSpecBase.LOG.warn("Invalid cookie state: domain not specified");
            return false;
        }
        if (cookie.getPath() == null) {
            CookieSpecBase.LOG.warn("Invalid cookie state: path not specified");
            return false;
        }
        return (cookie.getExpiryDate() == null || cookie.getExpiryDate().after(new Date())) && domainMatch(host, cookie.getDomain()) && pathMatch(path, cookie.getPath()) && (!cookie.getSecure() || secure);
    }
    
    private static boolean domainMatch(final String host, final String domain) {
        final boolean match = host.equals(domain) || (domain.startsWith(".") && host.endsWith(domain));
        return match;
    }
    
    private static boolean pathMatch(final String path, final String topmostPath) {
        boolean match = path.startsWith(topmostPath);
        if (match && path.length() != topmostPath.length() && !topmostPath.endsWith("/")) {
            match = (path.charAt(topmostPath.length()) == CookieSpec.PATH_DELIM_CHAR);
        }
        return match;
    }
    
    public Cookie[] match(String host, final int port, String path, final boolean secure, final Cookie[] cookies) {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie[])");
        if (host == null) {
            throw new IllegalArgumentException("Host of origin may not be null");
        }
        if (host.trim().equals("")) {
            throw new IllegalArgumentException("Host of origin may not be blank");
        }
        if (port < 0) {
            throw new IllegalArgumentException("Invalid port: " + port);
        }
        if (path == null) {
            throw new IllegalArgumentException("Path of origin may not be null.");
        }
        if (cookies == null) {
            throw new IllegalArgumentException("Cookie array may not be null");
        }
        if (path.trim().equals("")) {
            path = "/";
        }
        host = host.toLowerCase();
        if (cookies.length <= 0) {
            return null;
        }
        final List matching = new LinkedList();
        for (int i = 0; i < cookies.length; ++i) {
            if (this.match(host, port, path, secure, cookies[i])) {
                addInPathOrder(matching, cookies[i]);
            }
        }
        return matching.toArray(new Cookie[matching.size()]);
    }
    
    private static void addInPathOrder(final List list, final Cookie addCookie) {
        int i;
        Cookie c;
        for (i = 0, i = 0; i < list.size(); ++i) {
            c = list.get(i);
            if (addCookie.compare(addCookie, c) > 0) {
                break;
            }
        }
        list.add(i, addCookie);
    }
    
    public String formatCookie(final Cookie cookie) {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.formatCookie(Cookie)");
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null");
        }
        final StringBuffer buf = new StringBuffer();
        buf.append(cookie.getName());
        buf.append("=");
        final String s = cookie.getValue();
        if (s != null) {
            buf.append(s);
        }
        return buf.toString();
    }
    
    public String formatCookies(final Cookie[] cookies) throws IllegalArgumentException {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.formatCookies(Cookie[])");
        if (cookies == null) {
            throw new IllegalArgumentException("Cookie array may not be null");
        }
        if (cookies.length == 0) {
            throw new IllegalArgumentException("Cookie array may not be empty");
        }
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < cookies.length; ++i) {
            if (i > 0) {
                buffer.append("; ");
            }
            buffer.append(this.formatCookie(cookies[i]));
        }
        return buffer.toString();
    }
    
    public Header formatCookieHeader(final Cookie[] cookies) {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie[])");
        return new Header("Cookie", this.formatCookies(cookies));
    }
    
    public Header formatCookieHeader(final Cookie cookie) {
        CookieSpecBase.LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie)");
        return new Header("Cookie", this.formatCookie(cookie));
    }
    
    static {
        LOG = LogFactory.getLog(CookieSpec.class);
    }
}
