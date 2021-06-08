// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.lang;

import org.mudebug.prapr.reloc.commons.lang.exception.NestableRuntimeException;

public class SerializationException extends NestableRuntimeException
{
    private static final long serialVersionUID = 4029025366392702726L;
    
    public SerializationException() {
    }
    
    public SerializationException(final String msg) {
        super(msg);
    }
    
    public SerializationException(final Throwable cause) {
        super(cause);
    }
    
    public SerializationException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
