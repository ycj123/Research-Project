// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public class MultipleCompilationErrorsException extends CompilationFailedException
{
    protected ErrorCollector collector;
    
    public MultipleCompilationErrorsException(final ErrorCollector ec) {
        super(0, null);
        if (ec == null) {
            final CompilerConfiguration config = (super.getUnit() != null) ? super.getUnit().getConfiguration() : new CompilerConfiguration();
            this.collector = new ErrorCollector(config);
        }
        else {
            this.collector = ec;
        }
    }
    
    public ErrorCollector getErrorCollector() {
        return this.collector;
    }
    
    @Override
    public String getMessage() {
        final StringWriter data = new StringWriter();
        final PrintWriter writer = new PrintWriter(data);
        final Janitor janitor = new Janitor();
        writer.write(super.getMessage());
        writer.println(":");
        try {
            this.collector.write(writer, janitor);
        }
        finally {
            janitor.cleanup();
        }
        return data.toString();
    }
}
