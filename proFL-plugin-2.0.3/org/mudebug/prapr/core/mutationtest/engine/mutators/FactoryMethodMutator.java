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
import org.mudebug.prapr.core.mutationtest.engine.PraPRMethodMutatorFactory;

public enum FactoryMethodMutator implements PraPRMethodMutatorFactory
{
    FACTORY_METHOD_MUTATOR_0(Preference.PREF_0), 
    FACTORY_METHOD_MUTATOR_1(Preference.PREF_0), 
    FACTORY_METHOD_MUTATOR_2(Preference.PREF_0), 
    FACTORY_METHOD_MUTATOR_3(Preference.PREF_0), 
    FACTORY_METHOD_MUTATOR_4(Preference.PREF_1), 
    FACTORY_METHOD_MUTATOR_5(Preference.PREF_1), 
    FACTORY_METHOD_MUTATOR_6(Preference.PREF_1), 
    FACTORY_METHOD_MUTATOR_7(Preference.PREF_1);
    
    private final Preference preference;
    
    private FactoryMethodMutator(final Preference preference) {
        this.preference = preference;
    }
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo collectedClassInfo, final ClassByteArraySource cache, final GlobalInfo classHierarchy) {
        if (methodInfo.isConstructor()) {
            return new MethodVisitor(393216, methodVisitor) {};
        }
        final FactoryMethodMutatorMethodVisitor fmmmv = new FactoryMethodMutatorMethodVisitor(context, methodVisitor, methodInfo, collectedClassInfo, cache, this, classHierarchy);
        final int methodAccess = Commons.getMethodAccess(methodInfo);
        return fmmmv.lvs = new LocalVariablesSorter(methodAccess, methodInfo.getMethodDescriptor(), fmmmv);
    }
    
    @Override
    public MethodVisitor create(final MutationContext mutationContext, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String getGloballyUniqueId() {
        return String.format("MethodNameMutator_%d", this.ordinal());
    }
    
    @Override
    public String getName() {
        return this.name();
    }
    
    public static Iterable<PraPRMethodMutatorFactory> getVariants() {
        return (Iterable<PraPRMethodMutatorFactory>)Arrays.asList(values());
    }
    
    protected int getOrdinal() {
        for (final FactoryMethodMutator fmm : values()) {
            if (fmm.preference == this.preference) {
                final int base = fmm.ordinal();
                return this.ordinal() - base;
            }
        }
        return 0;
    }
    
    protected int getPreferenceOrdinal() {
        return this.preference.ordinal();
    }
    
    private enum Preference
    {
        PREF_0, 
        PREF_1;
    }
}
