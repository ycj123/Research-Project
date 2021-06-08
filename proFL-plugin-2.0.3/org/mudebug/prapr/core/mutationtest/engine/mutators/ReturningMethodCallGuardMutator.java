// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import java.util.Arrays;
import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.pitest.classinfo.ClassByteArraySource;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.MethodVisitor;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Preference;
import org.mudebug.prapr.core.mutationtest.engine.PraPRMethodMutatorFactory;

public enum ReturningMethodCallGuardMutator implements PraPRMethodMutatorFactory
{
    RET_METHOD_CALL_GUARD_MUTATOR_0(Preference.DEFVAL, -1), 
    RET_METHOD_CALL_GUARD_MUTATOR_1(Preference.LOCAL, 0), 
    RET_METHOD_CALL_GUARD_MUTATOR_2(Preference.LOCAL, 1), 
    RET_METHOD_CALL_GUARD_MUTATOR_3(Preference.FIELD, 0), 
    RET_METHOD_CALL_GUARD_MUTATOR_4(Preference.FIELD, 1);
    
    private final Preference preference;
    private final int preferenceIndex;
    
    private ReturningMethodCallGuardMutator(final Preference preference, final int preferenceIndex) {
        this.preference = preference;
        this.preferenceIndex = preferenceIndex;
    }
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        if (methodInfo.isStaticInitializer()) {
            return Commons.dummyMethodVisitor(methodVisitor);
        }
        final ReturningMethodCallGuardMethodVisitor rmcgmmv = new ReturningMethodCallGuardMethodVisitor(context, methodInfo, methodVisitor, cci, this.preference, this.preferenceIndex, this);
        final int methodAccess = Commons.getMethodAccess(methodInfo);
        return rmcgmmv.lvs = new LocalVariablesSorter(methodAccess, methodInfo.getMethodDescriptor(), rmcgmmv);
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
