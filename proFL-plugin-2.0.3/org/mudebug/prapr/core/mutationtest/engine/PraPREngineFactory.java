// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine;

import org.mudebug.prapr.core.mutationtest.engine.config.AugmentedMutator;
import org.pitest.util.Glob;
import org.pitest.functional.F;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.functional.predicate.Predicate;
import org.mudebug.prapr.core.mutationtest.engine.config.PraPRMutationEngineConfig;
import org.pitest.functional.prelude.Prelude;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.mudebug.prapr.core.SuspChecker;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import java.util.Collection;
import org.mudebug.prapr.core.mutationtest.AugmentedEngineArguments;
import org.pitest.mutationtest.engine.MutationEngine;
import org.pitest.mutationtest.EngineArguments;
import org.pitest.mutationtest.MutationEngineFactory;

public class PraPREngineFactory implements MutationEngineFactory
{
    @Override
    public MutationEngine createEngine(final EngineArguments arguments) {
        return this.createEngine((AugmentedEngineArguments)arguments);
    }
    
    private MutationEngine createEngine(final AugmentedEngineArguments arguments) {
        return this.createEngineWithMutators(arguments.excludedMethods(), createMutatorListFromArrayOrUseDefaults(arguments.mutators()), arguments.getSuspChecker(), arguments.getClassHierarchy());
    }
    
    private MutationEngine createEngineWithMutators(final Collection<String> excludedMethods, final Collection<? extends MethodMutatorFactory> mutators, final SuspChecker suspChecker, final GlobalInfo classHierarchy) {
        final Predicate<MethodInfo> filter = Prelude.not(stringToMethodInfoPredicate(excludedMethods));
        final PraPRMutationEngineConfig config = new PraPRMutationEngineConfig(filter, mutators, suspChecker, classHierarchy);
        return new PraPRMutationEngine(config);
    }
    
    @Override
    public String name() {
        return "prapr";
    }
    
    @Override
    public String description() {
        return "PraPR mutation engine factory";
    }
    
    private static F<MethodInfo, Boolean> stringToMethodInfoPredicate(final Collection<String> excludedMethods) {
        final Predicate<String> excluded = Prelude.or(Glob.toGlobPredicates(excludedMethods));
        return new Predicate<MethodInfo>() {
            @Override
            public Boolean apply(final MethodInfo methodInfo) {
                return (Boolean)excluded.apply(methodInfo.getName());
            }
        };
    }
    
    private static Collection<? extends MethodMutatorFactory> createMutatorListFromArrayOrUseDefaults(final Collection<String> mutators) {
        if (mutators != null && !mutators.isEmpty()) {
            return AugmentedMutator.fromStrings(mutators);
        }
        return AugmentedMutator.all();
    }
}
