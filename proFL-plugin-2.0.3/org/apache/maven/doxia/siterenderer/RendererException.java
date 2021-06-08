// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.siterenderer;

public class RendererException extends Exception
{
    private static final long serialVersionUID = 3141592653589793238L;
    
    public RendererException(final String message) {
        super(message);
    }
    
    public RendererException(final String message, final Throwable t) {
        super(message, t);
    }
}
