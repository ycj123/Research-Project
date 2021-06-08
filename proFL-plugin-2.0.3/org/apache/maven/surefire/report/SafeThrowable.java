// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.report;

public class SafeThrowable
{
    private final Throwable target;
    
    public SafeThrowable(final Throwable target) {
        this.target = target;
    }
    
    public String getLocalizedMessage() {
        try {
            return this.target.getLocalizedMessage();
        }
        catch (Throwable t) {
            return t.getLocalizedMessage();
        }
    }
    
    public String getMessage() {
        try {
            return this.target.getMessage();
        }
        catch (Throwable t) {
            return t.getMessage();
        }
    }
    
    public Throwable getTarget() {
        return this.target;
    }
}
