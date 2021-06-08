// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.siterenderer;

import java.io.File;

class ModuleReference
{
    private final String parserId;
    private final File basedir;
    
    ModuleReference(final String parserId, final File basedir) {
        this.parserId = parserId;
        this.basedir = basedir;
    }
    
    public String getParserId() {
        return this.parserId;
    }
    
    public File getBasedir() {
        return this.basedir;
    }
}
