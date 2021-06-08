// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.pitest.classinfo.ClassByteArraySource;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.MethodVisitor;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.PraPRMethodMutatorFactory;

public enum PreconditionAdditionMutator implements PraPRMethodMutatorFactory
{
    PRECONDITION_ADDITION_MUTATOR;
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo collectedClassInfo, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        if (methodInfo.isConstructor() || methodInfo.isStaticInitializer()) {
            return Commons.dummyMethodVisitor(methodVisitor);
        }
        return new PreconditionAdditionMethodVisitor(methodVisitor, methodInfo, collectedClassInfo, this, context);
    }
    
    @Override
    public MethodVisitor create(final MutationContext mutationContext, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }
    
    @Override
    public String getName() {
        return this.name();
    }
}
