// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.lifecycle;

import org.apache.maven.BuildFailureException;
import org.apache.maven.monitor.event.EventDispatcher;
import org.apache.maven.execution.ReactorManager;
import org.apache.maven.execution.MavenSession;

public interface LifecycleExecutor
{
    public static final String ROLE = ((LifecycleExecutor$1.class$org$apache$maven$lifecycle$LifecycleExecutor == null) ? (LifecycleExecutor$1.class$org$apache$maven$lifecycle$LifecycleExecutor = LifecycleExecutor$1.class$("org.apache.maven.lifecycle.LifecycleExecutor")) : LifecycleExecutor$1.class$org$apache$maven$lifecycle$LifecycleExecutor).getName();
    
    void execute(final MavenSession p0, final ReactorManager p1, final EventDispatcher p2) throws LifecycleExecutionException, BuildFailureException;
}
