// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

public interface ChannelCondition
{
    public static final int TIMEOUT = 1;
    public static final int CLOSED = 2;
    public static final int STDOUT_DATA = 4;
    public static final int STDERR_DATA = 8;
    public static final int EOF = 16;
    public static final int EXIT_STATUS = 32;
    public static final int EXIT_SIGNAL = 64;
}
