// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.parser;

import java.io.File;
import org.apache.maven.doxia.macro.manager.MacroNotFoundException;
import org.apache.maven.doxia.macro.MacroExecutionException;
import org.apache.maven.doxia.macro.Macro;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.macro.manager.MacroManager;

public abstract class AbstractParser implements Parser
{
    protected boolean secondParsing;
    protected MacroManager macroManager;
    
    public AbstractParser() {
        this.secondParsing = false;
    }
    
    public int getType() {
        return 0;
    }
    
    public void executeMacro(final String macroId, final MacroRequest request, final Sink sink) throws MacroExecutionException, MacroNotFoundException {
        final Macro macro = this.macroManager.getMacro(macroId);
        macro.execute(sink, request);
    }
    
    protected File getBasedir() {
        final String basedir = System.getProperty("basedir");
        if (basedir != null) {
            return new File(basedir);
        }
        return new File(new File("").getAbsolutePath());
    }
    
    public void setSecondParsing(final boolean second) {
        this.secondParsing = second;
    }
}
