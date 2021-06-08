// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

public class TerminationEvent extends CVSEvent
{
    private boolean error;
    
    public TerminationEvent(final Object o, final boolean error) {
        super(o);
        this.setError(error);
    }
    
    public TerminationEvent(final Object o) {
        this(o, false);
    }
    
    public boolean isError() {
        return this.error;
    }
    
    public void setError(final boolean error) {
        this.error = error;
    }
    
    protected void fireEvent(final CVSListener cvsListener) {
        cvsListener.commandTerminated(this);
    }
}
