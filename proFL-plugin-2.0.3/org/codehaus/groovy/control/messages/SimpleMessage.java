// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.messages;

import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.Janitor;
import java.io.PrintWriter;
import org.codehaus.groovy.control.ProcessingUnit;

public class SimpleMessage extends Message
{
    protected String message;
    protected Object data;
    protected ProcessingUnit owner;
    
    public SimpleMessage(final String message, final ProcessingUnit source) {
        this(message, null, source);
    }
    
    public SimpleMessage(final String message, final Object data, final ProcessingUnit source) {
        this.message = message;
        this.data = null;
        this.owner = source;
    }
    
    @Override
    public void write(final PrintWriter writer, final Janitor janitor) {
        if (this.owner instanceof SourceUnit) {
            final String name = ((SourceUnit)this.owner).getName();
            writer.println("" + name + ": " + this.message);
        }
        else {
            writer.println(this.message);
        }
    }
    
    public String getMessage() {
        return this.message;
    }
}
