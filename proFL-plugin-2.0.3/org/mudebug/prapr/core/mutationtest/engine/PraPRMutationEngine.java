// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine;

import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import java.util.Collection;
import org.pitest.mutationtest.engine.Mutater;
import org.pitest.classinfo.ClassByteArraySource;
import java.util.LinkedHashSet;
import org.mudebug.prapr.core.mutationtest.engine.config.PraPRMutationEngineConfig;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.mudebug.prapr.core.SuspChecker;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.functional.predicate.Predicate;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import java.util.Set;
import org.pitest.mutationtest.engine.MutationEngine;

public class PraPRMutationEngine implements MutationEngine
{
    private final Set<MethodMutatorFactory> mutators;
    private final Predicate<MethodInfo> methodFilter;
    private final SuspChecker suspChecker;
    private final GlobalInfo classHierarchy;
    
    public PraPRMutationEngine(final PraPRMutationEngineConfig config) {
        this.methodFilter = config.methodFilter();
        this.mutators = new LinkedHashSet<MethodMutatorFactory>(config.mutators());
        this.suspChecker = config.getSuspChecker();
        this.classHierarchy = config.getClassHierarchy();
    }
    
    @Override
    public Mutater createMutator(final ClassByteArraySource source) {
        return new PraPRMutater(this.methodFilter, this.mutators, source, this.suspChecker, this.classHierarchy);
    }
    
    @Override
    public Collection<String> getMutatorNames() {
        return FCollection.map(this.mutators, toName());
    }
    
    @Override
    public String getName() {
        return "prapr";
    }
    
    private static F<MethodMutatorFactory, String> toName() {
        return new F<MethodMutatorFactory, String>() {
            @Override
            public String apply(final MethodMutatorFactory a) {
                return a.getName();
            }
        };
    }
    
    @Override
    public String toString() {
        return "PraPRMutationEngine{mutationOperators=" + this.mutators + ", methodFilter=" + this.methodFilter + ", suspChecker=" + this.suspChecker + ", classHierarchy=" + this.classHierarchy + '}';
    }
}
