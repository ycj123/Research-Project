// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.auth;

public class HttpAuthRealm
{
    private String realm;
    private String domain;
    
    public HttpAuthRealm(final String domain, final String realm) {
        this.realm = null;
        this.domain = null;
        this.domain = domain;
        this.realm = realm;
    }
    
    private static boolean domainAttribMatch(final String d1, final String d2) {
        return d1 == null || d2 == null || d1.equalsIgnoreCase(d2);
    }
    
    private static boolean realmAttribMatch(final String r1, final String r2) {
        return r1 == null || r2 == null || r1.equals(r2);
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof HttpAuthRealm)) {
            return super.equals(o);
        }
        final HttpAuthRealm that = (HttpAuthRealm)o;
        return domainAttribMatch(this.domain, that.domain) && realmAttribMatch(this.realm, that.realm);
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("Authentication domain: '");
        buffer.append(this.domain);
        buffer.append("', authentication realm: '");
        buffer.append(this.realm);
        buffer.append("'");
        return buffer.toString();
    }
    
    public int hashCode() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(this.domain);
        buffer.append(this.realm);
        return buffer.toString().hashCode();
    }
}
