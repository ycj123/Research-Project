// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.event;

public class EnhancedMessageEvent extends MessageEvent
{
    public static final String MERGED_PATH = "Merged_Response_File_Path";
    public static final String FILE_SENDING = "File_Sent_To_Server";
    public static final String REQUESTS_SENT = "All_Requests_Were_Sent";
    public static final String REQUESTS_COUNT = "Requests_Count";
    private String key;
    private Object value;
    
    public EnhancedMessageEvent(final Object o, final String key, final Object value) {
        super(o, "", false);
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
}
