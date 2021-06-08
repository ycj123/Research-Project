// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

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

public enum ArgumentsListMutator implements PraPRMethodMutatorFactory
{
    ARGUMENT_LIST_MUTATOR_0(0, Preference.DEFVAL, -1), 
    ARGUMENT_LIST_MUTATOR_1(1, Preference.DEFVAL, -1), 
    ARGUMENT_LIST_MUTATOR_2(0, Preference.LOCAL, 0), 
    ARGUMENT_LIST_MUTATOR_3(0, Preference.LOCAL, 1), 
    ARGUMENT_LIST_MUTATOR_4(1, Preference.LOCAL, 0), 
    ARGUMENT_LIST_MUTATOR_5(1, Preference.LOCAL, 1), 
    ARGUMENT_LIST_MUTATOR_6(0, Preference.FIELD, 0), 
    ARGUMENT_LIST_MUTATOR_7(0, Preference.FIELD, 1), 
    ARGUMENT_LIST_MUTATOR_8(0, Preference.FIELD, 2), 
    ARGUMENT_LIST_MUTATOR_9(1, Preference.FIELD, 0), 
    ARGUMENT_LIST_MUTATOR_a(1, Preference.FIELD, 1), 
    ARGUMENT_LIST_MUTATOR_b(1, Preference.FIELD, 2), 
    ARGUMENT_LIST_MUTATOR_c(2, Preference.FIELD, 0), 
    ARGUMENT_LIST_MUTATOR_d(2, Preference.FIELD, 1), 
    ARGUMENT_LIST_MUTATOR_e(2, Preference.FIELD, 2), 
    ARGUMENT_LIST_MUTATOR_f(2, Preference.FIELD, 3), 
    ARGUMENT_LIST_MUTATOR_g(3, Preference.FIELD, 0), 
    ARGUMENT_LIST_MUTATOR_h(3, Preference.FIELD, 1), 
    ARGUMENT_LIST_MUTATOR_i(3, Preference.FIELD, 2), 
    ARGUMENT_LIST_MUTATOR_j(3, Preference.FIELD, 3), 
    ARGUMENT_LIST_MUTATOR_k(2, Preference.LOCAL, 0), 
    ARGUMENT_LIST_MUTATOR_l(2, Preference.LOCAL, 1), 
    ARGUMENT_LIST_MUTATOR_m(3, Preference.LOCAL, 0), 
    ARGUMENT_LIST_MUTATOR_n(3, Preference.LOCAL, 1);
    
    private final Preference preference;
    private final int overloadIndex;
    private final int preferenceIndex;
    
    private ArgumentsListMutator(final int overloadIndex, final Preference preference, final int preferenceIndex) {
        this.overloadIndex = overloadIndex;
        this.preference = preference;
        this.preferenceIndex = preferenceIndex;
    }
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        final PraPRMethodMutatorFactory variant = this;
        final ArgumentsListMutatorMethodVisitor almv = new ArgumentsListMutatorMethodVisitor(context, methodInfo, methodVisitor, cci, variant, cache, this.overloadIndex, this.preference, this.preferenceIndex);
        final int methodAccess = Commons.getMethodAccess(methodInfo);
        return almv.lvs = new LocalVariablesSorter(methodAccess, methodInfo.getMethodDescriptor(), almv);
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
}
