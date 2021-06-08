// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.transport;

import java.io.IOException;

public interface MessageHandler
{
    void handleMessage(final byte[] p0, final int p1) throws IOException;
}
