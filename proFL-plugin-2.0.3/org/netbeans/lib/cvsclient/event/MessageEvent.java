// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

public class MessageEvent extends CVSEvent
{
    private String message;
    private boolean error;
    private boolean tagged;
    private final byte[] raw;
    
    public MessageEvent(final Object o, final String message, final byte[] raw, final boolean error) {
        super(o);
        this.setMessage(message);
        this.setError(error);
        this.setTagged(false);
        this.raw = raw;
    }
    
    public MessageEvent(final Object o, final String s, final boolean b) {
        this(o, s, null, b);
    }
    
    public MessageEvent(final Object o) {
        this(o, null, false);
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public byte[] getRawData() {
        return this.raw;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public boolean isError() {
        return this.error;
    }
    
    public void setError(final boolean error) {
        this.error = error;
    }
    
    protected void fireEvent(final CVSListener cvsListener) {
        cvsListener.messageSent(this);
    }
    
    public boolean isTagged() {
        return this.tagged;
    }
    
    public void setTagged(final boolean tagged) {
        this.tagged = tagged;
    }
    
    public static String parseTaggedMessage(final StringBuffer sb, final String s) {
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            return null;
        }
        String string = null;
        if (s.equals("newline")) {
            string = sb.toString();
            sb.setLength(0);
        }
        final int index = s.indexOf(32);
        if (index > 0) {
            sb.append(s.substring(index + 1));
        }
        return string;
    }
}
