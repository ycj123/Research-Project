// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.auth;

import org.mudebug.prapr.reloc.commons.httpclient.Credentials;

public interface AuthScheme
{
    String getSchemeName();
    
    String getParameter(final String p0);
    
    String getRealm();
    
    String getID();
    
    String authenticate(final Credentials p0, final String p1, final String p2) throws AuthenticationException;
}
