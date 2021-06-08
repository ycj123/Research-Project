// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.fml;

import org.apache.maven.doxia.module.site.AbstractSiteModule;

public class FmlSiteModule extends AbstractSiteModule
{
    public String getSourceDirectory() {
        return "fml";
    }
    
    public String getExtension() {
        return "fml";
    }
    
    public String getParserId() {
        return "fml";
    }
}
