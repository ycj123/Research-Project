// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro.manager;

import org.apache.maven.doxia.macro.Macro;

public interface MacroManager
{
    public static final String ROLE = ((MacroManager$1.class$org$apache$maven$doxia$macro$manager$MacroManager == null) ? (MacroManager$1.class$org$apache$maven$doxia$macro$manager$MacroManager = MacroManager$1.class$("org.apache.maven.doxia.macro.manager.MacroManager")) : MacroManager$1.class$org$apache$maven$doxia$macro$manager$MacroManager).getName();
    
    Macro getMacro(final String p0) throws MacroNotFoundException;
}
