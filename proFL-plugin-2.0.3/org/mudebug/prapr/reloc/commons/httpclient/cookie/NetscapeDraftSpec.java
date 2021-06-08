// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.cookie;

import java.util.StringTokenizer;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.mudebug.prapr.reloc.commons.httpclient.Cookie;
import org.mudebug.prapr.reloc.commons.httpclient.NameValuePair;

public class NetscapeDraftSpec extends CookieSpecBase
{
    public void parseAttribute(final NameValuePair attribute, final Cookie cookie) throws MalformedCookieException {
        if (attribute == null) {
            throw new IllegalArgumentException("Attribute may not be null.");
        }
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null.");
        }
        final String paramName = attribute.getName().toLowerCase();
        final String paramValue = attribute.getValue();
        if (paramName.equals("expires")) {
            if (paramValue == null) {
                throw new MalformedCookieException("Missing value for expires attribute");
            }
            try {
                final DateFormat expiryFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", Locale.US);
                final Date date = expiryFormat.parse(paramValue);
                cookie.setExpiryDate(date);
            }
            catch (ParseException e) {
                throw new MalformedCookieException("Invalid expires attribute: " + e.getMessage());
            }
        }
        else {
            super.parseAttribute(attribute, cookie);
        }
    }
    
    public void validate(final String host, final int port, final String path, final boolean secure, final Cookie cookie) throws MalformedCookieException {
        CookieSpecBase.LOG.trace("enterNetscapeDraftCookieProcessor RCF2109CookieProcessor.validate(Cookie)");
        super.validate(host, port, path, secure, cookie);
        if (host.indexOf(".") >= 0) {
            final int domainParts = new StringTokenizer(cookie.getDomain(), ".").countTokens();
            if (isSpecialDomain(cookie.getDomain())) {
                if (domainParts < 2) {
                    throw new MalformedCookieException("Domain attribute \"" + cookie.getDomain() + "\" violates the Netscape cookie specification for " + "special domains");
                }
            }
            else if (domainParts < 3) {
                throw new MalformedCookieException("Domain attribute \"" + cookie.getDomain() + "\" violates the Netscape cookie specification");
            }
        }
    }
    
    private static boolean isSpecialDomain(final String domain) {
        final String ucDomain = domain.toUpperCase();
        return ucDomain.endsWith(".COM") || ucDomain.endsWith(".EDU") || ucDomain.endsWith(".NET") || ucDomain.endsWith(".GOV") || ucDomain.endsWith(".MIL") || ucDomain.endsWith(".ORG") || ucDomain.endsWith(".INT");
    }
}
