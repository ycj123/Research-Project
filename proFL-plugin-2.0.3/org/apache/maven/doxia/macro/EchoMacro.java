// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro;

import java.util.Iterator;
import org.apache.maven.doxia.sink.Sink;

public class EchoMacro extends AbstractMacro
{
    private static final String EOL;
    
    public void execute(final Sink sink, final MacroRequest request) {
        sink.verbatim(true);
        sink.text("echo" + EchoMacro.EOL);
        for (final String key : request.getParameters().keySet()) {
            sink.text(key + " ---> " + request.getParameter(key) + EchoMacro.EOL);
        }
        sink.verbatim_();
    }
    
    static {
        EOL = System.getProperty("line.separator");
    }
}
