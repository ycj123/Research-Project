// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.diff;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class PerforceDiffConsumer implements StreamConsumer
{
    private StringWriter out;
    private PrintWriter output;
    
    public PerforceDiffConsumer() {
        this.out = new StringWriter();
        this.output = new PrintWriter(this.out);
    }
    
    public void consumeLine(final String line) {
        this.output.println(line);
    }
    
    public String getOutput() {
        this.output.flush();
        this.out.flush();
        return this.out.toString();
    }
}
