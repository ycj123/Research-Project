// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.lifecycle.mapping;

import java.util.Map;
import java.util.List;

public interface LifecycleMapping
{
    public static final String ROLE = ((LifecycleMapping$1.class$org$apache$maven$lifecycle$mapping$LifecycleMapping == null) ? (LifecycleMapping$1.class$org$apache$maven$lifecycle$mapping$LifecycleMapping = LifecycleMapping$1.class$("org.apache.maven.lifecycle.mapping.LifecycleMapping")) : LifecycleMapping$1.class$org$apache$maven$lifecycle$mapping$LifecycleMapping).getName();
    
    List getOptionalMojos(final String p0);
    
    Map getPhases(final String p0);
}
