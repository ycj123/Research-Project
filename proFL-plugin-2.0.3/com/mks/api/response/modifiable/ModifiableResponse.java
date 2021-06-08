// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response.modifiable;

import com.mks.api.response.Response;

public interface ModifiableResponse extends Response, ModifiableResultContainer, ModifiableSubRoutineContainer, ModifiableWorkItemContainer, ModifiableAPIExceptionContainer
{
    void setConnectionHostname(final String p0);
    
    void setConnectionPort(final int p0);
    
    void setConnectionUsername(final String p0);
    
    void setExitCode(final int p0);
    
    void setApplicationName(final String p0);
    
    void setCacheContents(final boolean p0);
    
    void setUseInterim(final boolean p0);
}
