// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.messages;

import org.codehaus.groovy.control.Janitor;
import java.io.PrintWriter;
import org.codehaus.groovy.control.ProcessingUnit;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.syntax.CSTNode;

public class LocatedMessage extends SimpleMessage
{
    protected CSTNode context;
    
    public LocatedMessage(final String message, final CSTNode context, final SourceUnit source) {
        super(message, source);
        this.context = context;
    }
    
    public LocatedMessage(final String message, final Object data, final CSTNode context, final SourceUnit source) {
        super(message, data, source);
        this.context = context;
    }
    
    @Override
    public void write(final PrintWriter writer, final Janitor janitor) {
        if (this.owner instanceof SourceUnit) {
            final SourceUnit source = (SourceUnit)this.owner;
            final String name = source.getName();
            final int line = this.context.getStartLine();
            final int column = this.context.getStartColumn();
            final String sample = source.getSample(line, column, janitor);
            if (sample != null) {
                writer.println(source.getSample(line, column, janitor));
            }
            writer.println(name + ": " + line + ": " + this.message);
            writer.println("");
        }
        else {
            writer.println("<No Relevant Source>: " + this.message);
            writer.println("");
        }
    }
}
