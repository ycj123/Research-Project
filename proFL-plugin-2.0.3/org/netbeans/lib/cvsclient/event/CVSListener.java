// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

public interface CVSListener
{
    void messageSent(final MessageEvent p0);
    
    void messageSent(final BinaryMessageEvent p0);
    
    void fileAdded(final FileAddedEvent p0);
    
    void fileToRemove(final FileToRemoveEvent p0);
    
    void fileRemoved(final FileRemovedEvent p0);
    
    void fileUpdated(final FileUpdatedEvent p0);
    
    void fileInfoGenerated(final FileInfoEvent p0);
    
    void commandTerminated(final TerminationEvent p0);
    
    void moduleExpanded(final ModuleExpansionEvent p0);
}
