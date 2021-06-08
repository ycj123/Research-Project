// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import java.util.Arrays;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.pitest.classinfo.ClassByteArraySource;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.MethodVisitor;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.PraPRMethodMutatorFactory;

public enum LocalToMethodCallMutator implements PraPRMethodMutatorFactory
{
    LOCAL_TO_METHOD_MUTATOR_0;
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo collectedClassInfo, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        if (methodInfo.isConstructor()) {
            return Commons.dummyMethodVisitor(methodVisitor);
        }
        return new LocalToMethCallMethodVisitor(this, context, methodVisitor, methodInfo, collectedClassInfo, cache);
    }
    
    @Override
    public MethodVisitor create(final MutationContext mutationContext, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String getGloballyUniqueId() {
        return String.format("%s_%d", this.getClass().getName(), this.ordinal());
    }
    
    @Override
    public String getName() {
        return this.name();
    }
    
    public static Iterable<PraPRMethodMutatorFactory> getVariants() {
        return (Iterable<PraPRMethodMutatorFactory>)Arrays.asList(values());
    }
}
