// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro;

import java.util.Map;
import java.io.File;

public class MacroRequest
{
    private File basedir;
    private Map parameters;
    
    public MacroRequest(final Map param, final File base) {
        this.parameters = param;
        this.basedir = base;
    }
    
    public File getBasedir() {
        return this.basedir;
    }
    
    public void setBasedir(final File base) {
        this.basedir = base;
    }
    
    public Map getParameters() {
        return this.parameters;
    }
    
    public Object getParameter(final String key) {
        return this.parameters.get(key);
    }
}
