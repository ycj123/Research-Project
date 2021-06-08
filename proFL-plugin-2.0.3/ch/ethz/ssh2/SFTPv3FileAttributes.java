// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

public class SFTPv3FileAttributes
{
    public Long size;
    public Integer uid;
    public Integer gid;
    public Integer permissions;
    public Integer atime;
    public Integer mtime;
    
    public SFTPv3FileAttributes() {
        this.size = null;
        this.uid = null;
        this.gid = null;
        this.permissions = null;
        this.atime = null;
        this.mtime = null;
    }
    
    public boolean isDirectory() {
        return this.permissions != null && (this.permissions & 0x4000) != 0x0;
    }
    
    public boolean isRegularFile() {
        return this.permissions != null && (this.permissions & 0x8000) != 0x0;
    }
    
    public boolean isSymlink() {
        return this.permissions != null && (this.permissions & 0xA000) != 0x0;
    }
    
    public String getOctalPermissions() {
        if (this.permissions == null) {
            return null;
        }
        final String res = Integer.toString(this.permissions & 0xFFFF, 8);
        final StringBuffer sb = new StringBuffer();
        for (int leadingZeros = 7 - res.length(); leadingZeros > 0; --leadingZeros) {
            sb.append('0');
        }
        sb.append(res);
        return sb.toString();
    }
}
