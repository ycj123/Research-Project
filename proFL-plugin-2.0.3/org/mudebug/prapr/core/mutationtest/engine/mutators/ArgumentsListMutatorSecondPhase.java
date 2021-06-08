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

public enum ArgumentsListMutatorSecondPhase implements PraPRMethodMutatorFactory
{
    VARIANT_0(0, new Preference[] { Preference.NONE, Preference.NONE, Preference.LOCAL, Preference.LOCAL, Preference.NONE }), 
    VARIANT_1(1, new Preference[] { Preference.NONE, Preference.NONE, Preference.LOCAL, Preference.LOCAL, Preference.NONE });
    
    private final Preference[] pattern;
    private final int variantOrdinal;
    
    private ArgumentsListMutatorSecondPhase(final int variantOrdinal, final Preference[] pattern) {
        this.variantOrdinal = variantOrdinal;
        this.pattern = pattern;
    }
    
    public Preference[] getPattern() {
        return this.pattern;
    }
    
    public int getVariantOrdinal() {
        return this.variantOrdinal;
    }
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo collectedClassInfo, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        final ALM2ndPhaseMethodVisitor almv = new ALM2ndPhaseMethodVisitor(methodVisitor, methodInfo, context, collectedClassInfo, this);
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
