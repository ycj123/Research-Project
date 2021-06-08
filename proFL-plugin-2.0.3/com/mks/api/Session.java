// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

public interface Session extends CmdRunnerCreator
{
    IntegrationPoint getIntegrationPoint();
    
    void setAutoReconnect(final boolean p0);
    
    boolean getAutoReconnect();
    
    int getTimeout();
    
    void setTimeout(final int p0);
    
    boolean isCommon();
}
