// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

import java.util.Iterator;
import com.mks.api.response.APIException;

public interface IntegrationPoint extends IntegrationVersionRequest
{
    String getHostname();
    
    int getPort();
    
    boolean isClientIntegrationPoint();
    
    boolean isSecure();
    
    Session createSession() throws APIException;
    
    Session createSession(final String p0, final String p1) throws APIException;
    
    Session createSession(final String p0, final String p1, final int p2, final int p3) throws APIException;
    
    Session getCommonSession() throws APIException;
    
    Session getCommonSession(final String p0, final String p1) throws APIException;
    
    Iterator getSessions();
    
    boolean getAutoStartIntegrityClient();
    
    void setAutoStartIntegrityClient(final boolean p0);
    
    void release();
}
