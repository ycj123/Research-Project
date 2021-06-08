// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

public class SurefireReflectionException extends RuntimeException
{
    public SurefireReflectionException(final Throwable cause) {
        super((cause == null) ? "" : cause.toString(), cause);
    }
}
