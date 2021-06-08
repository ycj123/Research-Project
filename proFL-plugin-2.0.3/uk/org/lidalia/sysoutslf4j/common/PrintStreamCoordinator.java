// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.common;

public interface PrintStreamCoordinator
{
    void replaceSystemOutputsWithSLF4JPrintStreams();
    
    void restoreOriginalSystemOutputs();
}
