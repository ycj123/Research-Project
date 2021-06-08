// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro.manager;

import org.apache.maven.doxia.macro.Macro;
import java.util.Map;

public class DefaultMacroManager implements MacroManager
{
    private Map macros;
    
    public Macro getMacro(final String id) throws MacroNotFoundException {
        final Macro macro = this.macros.get(id);
        if (macro == null) {
            throw new MacroNotFoundException("Cannot find macro with id = " + id);
        }
        return macro;
    }
}
