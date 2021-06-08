// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.codehaus.plexus.util.cli.StreamConsumer;

public abstract class AbstractPerforceConsumer implements StreamConsumer
{
    private StringWriter out;
    protected PrintWriter output;
    
    public AbstractPerforceConsumer() {
        this.out = new StringWriter();
        this.output = new PrintWriter(this.out);
    }
    
    public String getOutput() {
        this.output.flush();
        this.out.flush();
        return this.out.toString();
    }
}
