// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration.inheritance;

import org.apache.maven.doxia.site.decoration.DecorationModel;

public interface DecorationModelInheritanceAssembler
{
    public static final String ROLE = ((DecorationModelInheritanceAssembler$1.class$org$apache$maven$doxia$site$decoration$inheritance$DecorationModelInheritanceAssembler == null) ? (DecorationModelInheritanceAssembler$1.class$org$apache$maven$doxia$site$decoration$inheritance$DecorationModelInheritanceAssembler = DecorationModelInheritanceAssembler$1.class$("org.apache.maven.doxia.site.decoration.inheritance.DecorationModelInheritanceAssembler")) : DecorationModelInheritanceAssembler$1.class$org$apache$maven$doxia$site$decoration$inheritance$DecorationModelInheritanceAssembler).getName();
    
    void assembleModelInheritance(final String p0, final DecorationModel p1, final DecorationModel p2, final String p3, final String p4);
    
    void resolvePaths(final DecorationModel p0, final String p1);
}
