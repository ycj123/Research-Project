// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.site;

public interface SiteModule
{
    public static final String ROLE = ((SiteModule$1.class$org$apache$maven$doxia$module$site$SiteModule == null) ? (SiteModule$1.class$org$apache$maven$doxia$module$site$SiteModule = SiteModule$1.class$("org.apache.maven.doxia.module.site.SiteModule")) : SiteModule$1.class$org$apache$maven$doxia$module$site$SiteModule).getName();
    
    String getSourceDirectory();
    
    String getExtension();
    
    String getParserId();
}
