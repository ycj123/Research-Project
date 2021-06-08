// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class FileInfoEvent extends CVSEvent
{
    private final FileInfoContainer infoContainer;
    
    public FileInfoEvent(final Object o, final FileInfoContainer infoContainer) {
        super(o);
        this.infoContainer = infoContainer;
    }
    
    public FileInfoContainer getInfoContainer() {
        return this.infoContainer;
    }
    
    protected void fireEvent(final CVSListener cvsListener) {
        cvsListener.fileInfoGenerated(this);
    }
}
