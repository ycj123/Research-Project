// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest;

import java.util.Collections;
import java.util.Collection;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.mudebug.prapr.core.SuspChecker;
import org.pitest.mutationtest.EngineArguments;

public class AugmentedEngineArguments extends EngineArguments
{
    private static final long serialVersionUID = 1L;
    private final SuspChecker suspChecker;
    private final GlobalInfo classHierarchy;
    
    public AugmentedEngineArguments(final Collection<String> mutators, final Collection<String> excludedMethods, final SuspChecker suspChecker, final GlobalInfo classHierarchy) {
        super(mutators, excludedMethods);
        this.suspChecker = suspChecker;
        this.classHierarchy = classHierarchy;
    }
    
    public static AugmentedEngineArguments arguments() {
        return new AugmentedEngineArguments((Collection<String>)Collections.emptyList(), (Collection<String>)Collections.emptyList(), null, null);
    }
    
    @Override
    public AugmentedEngineArguments withMutators(final Collection<String> mutators) {
        return new AugmentedEngineArguments(mutators, this.excludedMethods(), this.suspChecker, this.classHierarchy);
    }
    
    @Override
    public AugmentedEngineArguments withExcludedMethods(final Collection<String> excludedMethods) {
        return new AugmentedEngineArguments(this.mutators(), excludedMethods, this.suspChecker, this.classHierarchy);
    }
    
    public AugmentedEngineArguments withSuspChecker(final SuspChecker suspChecker) {
        return new AugmentedEngineArguments(this.mutators(), this.excludedMethods(), suspChecker, this.classHierarchy);
    }
    
    public AugmentedEngineArguments withClassHierarchy(final GlobalInfo classHierarchy) {
        return new AugmentedEngineArguments(this.mutators(), this.excludedMethods(), this.suspChecker, classHierarchy);
    }
    
    public SuspChecker getSuspChecker() {
        return this.suspChecker;
    }
    
    public GlobalInfo getClassHierarchy() {
        return this.classHierarchy;
    }
}
