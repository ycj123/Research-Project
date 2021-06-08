// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.messages;

import org.codehaus.groovy.control.Janitor;
import java.io.PrintWriter;
import org.codehaus.groovy.control.ProcessingUnit;

public class ExceptionMessage extends Message
{
    protected boolean verbose;
    private Exception cause;
    ProcessingUnit owner;
    
    public ExceptionMessage(final Exception cause, final boolean v, final ProcessingUnit owner) {
        this.verbose = true;
        this.cause = null;
        this.owner = null;
        this.verbose = v;
        this.cause = cause;
        this.owner = owner;
    }
    
    public Exception getCause() {
        return this.cause;
    }
    
    @Override
    public void write(final PrintWriter output, final Janitor janitor) {
        final String description = "General error during " + this.owner.getPhaseDescription() + ": ";
        final String message = this.cause.getMessage();
        if (message != null) {
            output.println(description + message);
        }
        else {
            output.println(description + this.cause);
        }
        output.println();
        this.cause.printStackTrace(output);
    }
}
