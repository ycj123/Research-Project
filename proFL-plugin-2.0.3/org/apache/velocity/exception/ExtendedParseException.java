// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.exception;

public interface ExtendedParseException
{
    String getTemplateName();
    
    int getLineNumber();
    
    int getColumnNumber();
}
