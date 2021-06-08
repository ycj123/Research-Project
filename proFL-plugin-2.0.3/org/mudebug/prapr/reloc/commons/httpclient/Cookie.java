// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.text.Collator;
import java.util.Locale;
import org.mudebug.prapr.reloc.commons.httpclient.cookie.CookieSpec;
import org.mudebug.prapr.reloc.commons.httpclient.cookie.CookiePolicy;
import org.mudebug.prapr.reloc.commons.logging.Log;
import java.text.RuleBasedCollator;
import java.util.Date;
import java.util.Comparator;
import java.io.Serializable;

public class Cookie extends NameValuePair implements Serializable, Comparator
{
    private String cookieComment;
    private String cookieDomain;
    private Date cookieExpiryDate;
    private String cookiePath;
    private boolean isSecure;
    private boolean hasPathAttribute;
    private boolean hasDomainAttribute;
    private int cookieVersion;
    private static final RuleBasedCollator STRING_COLLATOR;
    private static final Log LOG;
    
    public Cookie() {
        this(null, "noname", null, null, null, false);
    }
    
    public Cookie(final String domain, final String name, final String value) {
        this(domain, name, value, null, null, false);
    }
    
    public Cookie(final String domain, final String name, final String value, final String path, final Date expires, final boolean secure) {
        super(name, value);
        this.hasPathAttribute = false;
        this.hasDomainAttribute = false;
        this.cookieVersion = 0;
        Cookie.LOG.trace("enter Cookie(String, String, String, String, Date, boolean)");
        if (name == null) {
            throw new IllegalArgumentException("Cookie name may not be null");
        }
        if (name.trim().equals("")) {
            throw new IllegalArgumentException("Cookie name may not be blank");
        }
        this.setPath(path);
        this.setDomain(domain);
        this.setExpiryDate(expires);
        this.setSecure(secure);
    }
    
    public Cookie(final String domain, final String name, final String value, final String path, final int maxAge, final boolean secure) {
        this(domain, name, value, path, null, secure);
        if (maxAge < -1) {
            throw new IllegalArgumentException("Invalid max age:  " + Integer.toString(maxAge));
        }
        if (maxAge >= 0) {
            this.setExpiryDate(new Date(System.currentTimeMillis() + maxAge * 1000L));
        }
    }
    
    public String getComment() {
        return this.cookieComment;
    }
    
    public void setComment(final String comment) {
        this.cookieComment = comment;
    }
    
    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }
    
    public void setExpiryDate(final Date expiryDate) {
        this.cookieExpiryDate = expiryDate;
    }
    
    public boolean isPersistent() {
        return null != this.cookieExpiryDate;
    }
    
    public String getDomain() {
        return this.cookieDomain;
    }
    
    public void setDomain(String domain) {
        if (domain != null) {
            final int ndx = domain.indexOf(":");
            if (ndx != -1) {
                domain = domain.substring(0, ndx);
            }
            this.cookieDomain = domain.toLowerCase();
        }
    }
    
    public String getPath() {
        return this.cookiePath;
    }
    
    public void setPath(final String path) {
        this.cookiePath = path;
    }
    
    public boolean getSecure() {
        return this.isSecure;
    }
    
    public void setSecure(final boolean secure) {
        this.isSecure = secure;
    }
    
    public int getVersion() {
        return this.cookieVersion;
    }
    
    public void setVersion(final int version) {
        this.cookieVersion = version;
    }
    
    public boolean isExpired() {
        return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= System.currentTimeMillis();
    }
    
    public boolean isExpired(final Date now) {
        return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= now.getTime();
    }
    
    public void setPathAttributeSpecified(final boolean value) {
        this.hasPathAttribute = value;
    }
    
    public boolean isPathAttributeSpecified() {
        return this.hasPathAttribute;
    }
    
    public void setDomainAttributeSpecified(final boolean value) {
        this.hasDomainAttribute = value;
    }
    
    public boolean isDomainAttributeSpecified() {
        return this.hasDomainAttribute;
    }
    
    public int hashCode() {
        return super.hashCode() ^ ((null == this.cookiePath) ? 0 : this.cookiePath.hashCode()) ^ ((null == this.cookieDomain) ? 0 : this.cookieDomain.hashCode());
    }
    
    public boolean equals(final Object obj) {
        Cookie.LOG.trace("enter Cookie.equals(Object)");
        if (obj != null && obj instanceof Cookie) {
            final Cookie that = (Cookie)obj;
            return ((null == this.getName()) ? (null == that.getName()) : this.getName().equals(that.getName())) && ((null == this.getPath()) ? (null == that.getPath()) : this.getPath().equals(that.getPath())) && ((null == this.getDomain()) ? (null == that.getDomain()) : this.getDomain().equals(that.getDomain()));
        }
        return false;
    }
    
    public String toExternalForm() {
        return CookiePolicy.getSpecByVersion(this.getVersion()).formatCookie(this);
    }
    
    public boolean matches(final String domain, final int port, final String path, final boolean secure, final Date date) {
        Cookie.LOG.trace("enter Cookie.matches(Strinng, int, String, boolean, Date");
        final CookieSpec matcher = CookiePolicy.getDefaultSpec();
        return matcher.match(domain, port, path, secure, this);
    }
    
    public boolean matches(final String domain, final int port, final String path, final boolean secure) {
        Cookie.LOG.trace("enter Cookie.matches(String, int, String, boolean");
        return this.matches(domain, port, path, secure, new Date());
    }
    
    public static Header createCookieHeader(final String domain, final String path, final Cookie[] cookies) {
        Cookie.LOG.trace("enter Cookie.createCookieHeader(String,String,Cookie[])");
        return createCookieHeader(domain, path, false, cookies);
    }
    
    public static Header createCookieHeader(final String domain, final String path, final boolean secure, final Cookie[] cookies) throws IllegalArgumentException {
        Cookie.LOG.trace("enter Cookie.createCookieHeader(String, String, boolean, Cookie[])");
        if (domain == null) {
            throw new IllegalArgumentException("null domain in createCookieHeader.");
        }
        int port = secure ? 443 : 80;
        final int ndx = domain.indexOf(":");
        if (ndx != -1) {
            try {
                port = Integer.parseInt(domain.substring(ndx + 1, domain.length()));
            }
            catch (NumberFormatException e) {
                Cookie.LOG.warn("Cookie.createCookieHeader():  Invalid port number in domain " + domain);
            }
        }
        return createCookieHeader(domain, port, path, secure, cookies);
    }
    
    public static Header createCookieHeader(final String domain, final int port, final String path, final boolean secure, final Cookie[] cookies) throws IllegalArgumentException {
        Cookie.LOG.trace("enter Cookie.createCookieHeader(String, int, String, boolean, Cookie[])");
        return createCookieHeader(domain, port, path, secure, new Date(), cookies);
    }
    
    public static Header createCookieHeader(final String domain, final int port, final String path, final boolean secure, final Date now, Cookie[] cookies) throws IllegalArgumentException {
        Cookie.LOG.trace("enter Cookie.createCookieHeader(String, int, String, boolean, Date, Cookie[])");
        final CookieSpec matcher = CookiePolicy.getDefaultSpec();
        cookies = matcher.match(domain, port, path, secure, cookies);
        if (cookies != null && cookies.length > 0) {
            return matcher.formatCookieHeader(cookies);
        }
        return null;
    }
    
    public int compare(final Object o1, final Object o2) {
        Cookie.LOG.trace("enter Cookie.compare(Object, Object)");
        if (!(o1 instanceof Cookie)) {
            throw new ClassCastException(o1.getClass().getName());
        }
        if (!(o2 instanceof Cookie)) {
            throw new ClassCastException(o2.getClass().getName());
        }
        final Cookie c1 = (Cookie)o1;
        final Cookie c2 = (Cookie)o2;
        if (c1.getPath() == null && c2.getPath() == null) {
            return 0;
        }
        if (c1.getPath() == null) {
            if (c2.getPath().equals("/")) {
                return 0;
            }
            return -1;
        }
        else {
            if (c2.getPath() != null) {
                return Cookie.STRING_COLLATOR.compare(c1.getPath(), c2.getPath());
            }
            if (c1.getPath().equals("/")) {
                return 0;
            }
            return 1;
        }
    }
    
    public String toString() {
        return this.toExternalForm();
    }
    
    public static Cookie[] parse(final String domain, final int port, final String path, final Header setCookie) throws HttpException, IllegalArgumentException {
        Cookie.LOG.trace("enter Cookie.parse(String, int, String, Header)");
        return parse(domain, port, path, false, setCookie);
    }
    
    public static Cookie[] parse(final String domain, final String path, final Header setCookie) throws HttpException, IllegalArgumentException {
        Cookie.LOG.trace("enter Cookie.parse(String, String, Header)");
        return parse(domain, 80, path, false, setCookie);
    }
    
    public static Cookie[] parse(final String domain, final String path, final boolean secure, final Header setCookie) throws HttpException, IllegalArgumentException {
        Cookie.LOG.trace("enter Cookie.parse(String, String, boolean, Header)");
        return parse(domain, secure ? 443 : 80, path, secure, setCookie);
    }
    
    public static Cookie[] parse(final String domain, final int port, final String path, final boolean secure, final Header setCookie) throws HttpException {
        Cookie.LOG.trace("enter Cookie.parse(String, int, String, boolean, Header)");
        final CookieSpec parser = CookiePolicy.getDefaultSpec();
        final Cookie[] cookies = parser.parse(domain, port, path, secure, setCookie);
        for (int i = 0; i < cookies.length; ++i) {
            final Cookie cookie = cookies[i];
            final CookieSpec validator = CookiePolicy.getSpecByVersion(cookie.getVersion());
            validator.validate(domain, port, path, secure, cookie);
        }
        return cookies;
    }
    
    static {
        STRING_COLLATOR = (RuleBasedCollator)Collator.getInstance(new Locale("en", "US", ""));
        LOG = LogFactory.getLog(Cookie.class);
    }
}
