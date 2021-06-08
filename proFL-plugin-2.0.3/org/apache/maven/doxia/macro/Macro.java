// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro;

import org.apache.maven.doxia.sink.Sink;

public interface Macro
{
    public static final String ROLE = ((Macro$1.class$org$apache$maven$doxia$macro$Macro == null) ? (Macro$1.class$org$apache$maven$doxia$macro$Macro = Macro$1.class$("org.apache.maven.doxia.macro.Macro")) : Macro$1.class$org$apache$maven$doxia$macro$Macro).getName();
    
    void execute(final Sink p0, final MacroRequest p1) throws MacroExecutionException;
}
