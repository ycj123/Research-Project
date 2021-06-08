// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.site.manager;

import org.apache.maven.doxia.module.site.SiteModule;
import java.util.Collection;

public interface SiteModuleManager
{
    public static final String ROLE = ((SiteModuleManager$1.class$org$apache$maven$doxia$module$site$manager$SiteModuleManager == null) ? (SiteModuleManager$1.class$org$apache$maven$doxia$module$site$manager$SiteModuleManager = SiteModuleManager$1.class$("org.apache.maven.doxia.module.site.manager.SiteModuleManager")) : SiteModuleManager$1.class$org$apache$maven$doxia$module$site$manager$SiteModuleManager).getName();
    
    Collection getSiteModules();
    
    SiteModule getSiteModule(final String p0) throws SiteModuleNotFoundException;
}
