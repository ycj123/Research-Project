// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro.manager;

public class MacroNotFoundException extends Exception
{
    public MacroNotFoundException(final String message) {
        super(message);
    }
    
    public MacroNotFoundException(final Throwable cause) {
        super(cause);
    }
    
    public MacroNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
