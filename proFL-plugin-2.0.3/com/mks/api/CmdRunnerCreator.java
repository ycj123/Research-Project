// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

import java.io.IOException;
import java.util.Iterator;
import com.mks.api.response.APIException;

public interface CmdRunnerCreator extends IntegrationVersionRequest
{
    CmdRunner createCmdRunner() throws APIException;
    
    Iterator getCmdRunners();
    
    void release() throws IOException, APIException;
    
    void release(final boolean p0) throws IOException, APIException;
    
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
}
