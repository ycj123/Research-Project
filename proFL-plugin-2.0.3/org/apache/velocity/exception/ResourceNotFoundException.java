// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.exception;

public class ResourceNotFoundException extends VelocityException
{
    private static final long serialVersionUID = -4287732191458420347L;
    
    public ResourceNotFoundException(final String exceptionMessage) {
        super(exceptionMessage);
    }
    
    public ResourceNotFoundException(final String exceptionMessage, final Throwable t) {
        super(exceptionMessage, t);
    }
    
    public ResourceNotFoundException(final Throwable t) {
        super(t);
    }
}