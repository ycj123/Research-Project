// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

public class ByteArray
{
    private byte[] bytesBuffer;
    private int length;
    
    public ByteArray() {
        this.bytesBuffer = new byte[50];
        this.length = 0;
    }
    
    public void add(final byte b) {
        if (this.bytesBuffer.length <= this.length) {
            final byte[] bytesBuffer = new byte[this.length + this.length / 2];
            System.arraycopy(this.bytesBuffer, 0, bytesBuffer, 0, this.bytesBuffer.length);
            this.bytesBuffer = bytesBuffer;
        }
        this.bytesBuffer[this.length++] = b;
    }
    
    public byte[] getBytes() {
        final byte[] array = new byte[this.length];
        System.arraycopy(this.bytesBuffer, 0, array, 0, this.length);
        return array;
    }
    
    public String getStringFromBytes() {
        return new String(this.bytesBuffer, 0, this.length);
    }
    
    public void reset() {
        this.length = 0;
    }
}
