// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.lang;

import org.mudebug.prapr.reloc.commons.lang.exception.NestableRuntimeException;

public class UnhandledException extends NestableRuntimeException
{
    private static final long serialVersionUID = 1832101364842773720L;
    
    public UnhandledException(final Throwable cause) {
        super(cause);
    }
    
    public UnhandledException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
