// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

public class FileAddedEvent extends CVSEvent
{
    protected String path;
    
    public FileAddedEvent(final Object o, final String path) {
        super(o);
        this.path = path;
    }
    
    public String getFilePath() {
        return this.path;
    }
    
    protected void fireEvent(final CVSListener cvsListener) {
        cvsListener.fileAdded(this);
    }
}
