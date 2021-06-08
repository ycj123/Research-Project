// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core;

import java.util.Collection;
import org.pitest.mutationtest.engine.MutationDetails;
import java.io.Serializable;

public interface SuspChecker extends Serializable
{
    boolean isHit(final String p0);
    
    boolean isHit(final String p0, final String p1);
    
    boolean isHit(final MutationDetails p0);
    
    @Deprecated
    Collection<String> getAllFailingTests();
}
