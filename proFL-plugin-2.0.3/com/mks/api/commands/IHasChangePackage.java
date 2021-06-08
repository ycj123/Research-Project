// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

public interface IHasChangePackage extends ICommandBase
{
    void setCpid(final String p0);
    
    void setCloseCP(final boolean p0);
    
    void resetCloseCP();
    
    boolean isCloseCPOverridden();
}
