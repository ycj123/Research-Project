// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event.implement;

import org.apache.velocity.util.introspection.Info;

public class InvalidReferenceInfo extends Info
{
    private String invalidReference;
    
    public InvalidReferenceInfo(final String invalidReference, final Info info) {
        super(info.getTemplateName(), info.getLine(), info.getColumn());
        this.invalidReference = invalidReference;
    }
    
    public String getInvalidReference() {
        return this.invalidReference;
    }
    
    public String toString() {
        return this.getTemplateName() + " [line " + this.getLine() + ", column " + this.getColumn() + "]: " + this.invalidReference;
    }
}
