// 
// Decompiled by Procyon v0.5.36
// 

package edu.emory.mathcs.backport.java.util.concurrent;

public class CancellationException extends IllegalStateException
{
    private static final long serialVersionUID = -9202173006928992231L;
    
    public CancellationException() {
    }
    
    public CancellationException(final String message) {
        super(message);
    }
}
