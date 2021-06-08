// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.config;

import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import java.util.Collection;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.functional.predicate.Predicate;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.mudebug.prapr.core.SuspChecker;
import org.pitest.mutationtest.engine.gregor.config.DefaultMutationEngineConfiguration;

public class PraPRMutationEngineConfig extends DefaultMutationEngineConfiguration
{
    private final SuspChecker suspChecker;
    private final GlobalInfo classHierarchy;
    
    public PraPRMutationEngineConfig(final Predicate<MethodInfo> filter, final Collection<? extends MethodMutatorFactory> mutators, final SuspChecker suspChecker, final GlobalInfo classHierarchy) {
        super(filter, mutators);
        this.suspChecker = suspChecker;
        this.classHierarchy = classHierarchy;
    }
    
    public SuspChecker getSuspChecker() {
        return this.suspChecker;
    }
    
    public GlobalInfo getClassHierarchy() {
        return this.classHierarchy;
    }
}
