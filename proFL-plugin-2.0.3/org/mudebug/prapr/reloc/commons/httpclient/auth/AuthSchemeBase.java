// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.auth;

import org.mudebug.prapr.reloc.commons.httpclient.Credentials;

public abstract class AuthSchemeBase implements AuthScheme
{
    private String challenge;
    
    public AuthSchemeBase(final String challenge) throws MalformedChallengeException {
        this.challenge = null;
        if (challenge == null) {
            throw new IllegalArgumentException("Challenge may not be null");
        }
        this.challenge = challenge;
    }
    
    public boolean equals(final Object obj) {
        if (obj instanceof AuthSchemeBase) {
            return this.challenge.equals(((AuthSchemeBase)obj).challenge);
        }
        return super.equals(obj);
    }
    
    public int hashCode() {
        return this.challenge.hashCode();
    }
    
    public String toString() {
        return this.challenge;
    }
    
    public abstract String authenticate(final Credentials p0, final String p1, final String p2) throws AuthenticationException;
    
    public abstract String getID();
    
    public abstract String getRealm();
    
    public abstract String getParameter(final String p0);
    
    public abstract String getSchemeName();
}
