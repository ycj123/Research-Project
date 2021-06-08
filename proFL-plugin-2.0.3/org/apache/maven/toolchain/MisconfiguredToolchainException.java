// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain;

public class MisconfiguredToolchainException extends Exception
{
    public MisconfiguredToolchainException(final String message) {
        super(message);
    }
    
    public MisconfiguredToolchainException(final String message, final Throwable orig) {
        super(message, orig);
    }
}
