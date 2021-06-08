// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.pitest.classinfo.ClassByteArraySource;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.MethodVisitor;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.PraPRMethodMutatorFactory;

public enum VoidMethodCallGuardMutator implements PraPRMethodMutatorFactory
{
    VOID_METHOD_CALL_GUARD_MUTATOR;
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        final VoidMethodCallGuardMutatorMethodVisitor vmcgmmv = new VoidMethodCallGuardMutatorMethodVisitor(context, methodVisitor, this);
        final int methodAccess = Commons.getMethodAccess(methodInfo);
        return vmcgmmv.lvs = new LocalVariablesSorter(methodAccess, methodInfo.getMethodDescriptor(), vmcgmmv);
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
