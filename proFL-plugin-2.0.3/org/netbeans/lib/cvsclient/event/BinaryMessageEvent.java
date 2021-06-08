// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

public class BinaryMessageEvent extends CVSEvent
{
    private byte[] message;
    private int len;
    
    public BinaryMessageEvent(final Object o, final byte[] message, final int len) {
        super(o);
        this.message = message;
        this.len = len;
    }
    
    public byte[] getMessage() {
        return this.message;
    }
    
    public int getMessageLength() {
        return this.len;
    }
    
    protected void fireEvent(final CVSListener cvsListener) {
        cvsListener.messageSent(this);
    }
}
