// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

import com.mks.api.response.Response;
import com.mks.api.response.APIException;

public interface CmdRunner
{
    Session getSession();
    
    void interrupt() throws APIException;
    
    void setDefaultImpersonationUser(final String p0);
    
    String getDefaultImpersonationUser();
    
    String getDefaultHostname();
    
    int getDefaultPort();
    
    void setDefaultHostname(final String p0);
    
    void setDefaultPort(final int p0);
    
    String getDefaultUsername();
    
    String getDefaultPassword();
    
    void setDefaultUsername(final String p0);
    
    void setDefaultPassword(final String p0);
    
    Response execute(final String[] p0) throws APIException;
    
    Response execute(final Command p0) throws APIException;
    
    Response executeWithInterim(final String[] p0, final boolean p1) throws APIException;
    
    Response executeWithInterim(final Command p0, final boolean p1) throws APIException;
    
    void release() throws APIException;
    
    boolean isFinished();
}
