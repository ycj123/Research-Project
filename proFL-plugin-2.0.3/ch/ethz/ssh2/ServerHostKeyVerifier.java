// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

public interface ServerHostKeyVerifier
{
    boolean verifyServerHostKey(final String p0, final int p1, final String p2, final byte[] p3) throws Exception;
}
