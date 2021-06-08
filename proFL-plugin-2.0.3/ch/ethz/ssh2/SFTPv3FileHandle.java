// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

public class SFTPv3FileHandle
{
    final SFTPv3Client client;
    final byte[] fileHandle;
    boolean isClosed;
    
    SFTPv3FileHandle(final SFTPv3Client client, final byte[] h) {
        this.isClosed = false;
        this.client = client;
        this.fileHandle = h;
    }
    
    public SFTPv3Client getClient() {
        return this.client;
    }
    
    public boolean isClosed() {
        return this.isClosed;
    }
}
