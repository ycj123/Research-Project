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

public enum DereferenceGuardMutator implements PraPRMethodMutatorFactory
{
    DEREFERENCE_GUARD_MUTATOR_0(Preference.DEFVAL, -1), 
    DEREFERENCE_GUARD_MUTATOR_1(Preference.LOCAL, 0), 
    DEREFERENCE_GUARD_MUTATOR_4(Preference.FIELD, 0);
    
    private final Preference preference;
    private final int preferenceIndex;
    
    private DereferenceGuardMutator(final Preference preference, final int preferenceIndex) {
        this.preference = preference;
        this.preferenceIndex = preferenceIndex;
    }
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        final DereferenceGuardMutatorMethodVisitor dgmmv = new DereferenceGuardMutatorMethodVisitor(context, methodInfo, methodVisitor, cci, this.preference, this.preferenceIndex, this);
        final int methodAccess = Commons.getMethodAccess(methodInfo);
        return dgmmv.lvs = new LocalVariablesSorter(methodAccess, methodInfo.getMethodDescriptor(), dgmmv);
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
