// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.site.manager;

import org.apache.maven.doxia.module.site.SiteModule;
import java.util.Collection;
import java.util.Map;

public class DefaultSiteModuleManager implements SiteModuleManager
{
    private Map siteModules;
    
    public Collection getSiteModules() {
        return this.siteModules.values();
    }
    
    public SiteModule getSiteModule(final String id) throws SiteModuleNotFoundException {
        final SiteModule siteModule = this.siteModules.get(id);
        if (siteModule == null) {
            throw new SiteModuleNotFoundException("Cannot find site module id = " + id);
        }
        return siteModule;
    }
}
