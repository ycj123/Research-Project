// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

public interface InteractiveCallback
{
    String[] replyToChallenge(final String p0, final String p1, final int p2, final String[] p3, final boolean[] p4) throws Exception;
}
