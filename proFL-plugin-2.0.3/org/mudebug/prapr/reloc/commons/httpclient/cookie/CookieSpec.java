// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.cookie;

import org.mudebug.prapr.reloc.commons.httpclient.NameValuePair;
import org.mudebug.prapr.reloc.commons.httpclient.Header;
import org.mudebug.prapr.reloc.commons.httpclient.Cookie;

public interface CookieSpec
{
    public static final String PATH_DELIM = "/";
    public static final char PATH_DELIM_CHAR = "/".charAt(0);
    
    Cookie[] parse(final String p0, final int p1, final String p2, final boolean p3, final String p4) throws MalformedCookieException, IllegalArgumentException;
    
    Cookie[] parse(final String p0, final int p1, final String p2, final boolean p3, final Header p4) throws MalformedCookieException, IllegalArgumentException;
    
    void parseAttribute(final NameValuePair p0, final Cookie p1) throws MalformedCookieException, IllegalArgumentException;
    
    void validate(final String p0, final int p1, final String p2, final boolean p3, final Cookie p4) throws MalformedCookieException, IllegalArgumentException;
    
    boolean match(final String p0, final int p1, final String p2, final boolean p3, final Cookie p4);
    
    Cookie[] match(final String p0, final int p1, final String p2, final boolean p3, final Cookie[] p4);
    
    String formatCookie(final Cookie p0);
    
    String formatCookies(final Cookie[] p0) throws IllegalArgumentException;
    
    Header formatCookieHeader(final Cookie[] p0) throws IllegalArgumentException;
    
    Header formatCookieHeader(final Cookie p0) throws IllegalArgumentException;
}
