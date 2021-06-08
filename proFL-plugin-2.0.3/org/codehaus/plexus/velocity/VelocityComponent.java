// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.velocity;

import org.apache.velocity.app.VelocityEngine;

public interface VelocityComponent
{
    public static final String ROLE = ((VelocityComponent$1.class$org$codehaus$plexus$velocity$VelocityComponent == null) ? (VelocityComponent$1.class$org$codehaus$plexus$velocity$VelocityComponent = VelocityComponent$1.class$("org.codehaus.plexus.velocity.VelocityComponent")) : VelocityComponent$1.class$org$codehaus$plexus$velocity$VelocityComponent).getName();
    
    VelocityEngine getEngine();
}
