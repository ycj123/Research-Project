// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.messages;

import org.codehaus.groovy.control.Janitor;
import java.io.PrintWriter;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.syntax.SyntaxException;

public class SyntaxErrorMessage extends Message
{
    protected SyntaxException cause;
    protected SourceUnit source;
    
    public SyntaxErrorMessage(final SyntaxException cause, final SourceUnit source) {
        this.cause = cause;
        this.source = source;
        cause.setSourceLocator(source.getName());
    }
    
    public SyntaxException getCause() {
        return this.cause;
    }
    
    @Override
    public void write(final PrintWriter output, final Janitor janitor) {
        final String name = this.source.getName();
        final int line = this.getCause().getStartLine();
        final int column = this.getCause().getStartColumn();
        final String sample = this.source.getSample(line, column, janitor);
        output.print(name + ": " + line + ": " + this.getCause().getMessage());
        if (sample != null) {
            output.println();
            output.print(sample);
            output.println();
        }
    }
}
