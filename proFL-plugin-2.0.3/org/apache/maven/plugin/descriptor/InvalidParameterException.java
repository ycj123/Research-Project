// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.descriptor;

public class InvalidParameterException extends InvalidPluginDescriptorException
{
    public InvalidParameterException(final String element, final int i) {
        super("The " + element + " element in parameter # " + i + " is invalid. It cannot be null.");
    }
    
    public InvalidParameterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
