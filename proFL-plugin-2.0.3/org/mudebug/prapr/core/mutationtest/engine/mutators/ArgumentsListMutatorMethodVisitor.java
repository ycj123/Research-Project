// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.reloc.asm.Label;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ClassInfoCollector;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import java.util.Iterator;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.classinfo.ClassName;
import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.FieldInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.PraPRMethodInfo;
import java.util.List;
import java.util.Map;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Preference;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.PraPRMethodMutatorFactory;
import org.pitest.reloc.asm.MethodVisitor;

final class ArgumentsListMutatorMethodVisitor extends MethodVisitor
{
    private final PraPRMethodMutatorFactory variant;
    private final MutationContext context;
    private final Preference preference;
    private final int overloadIndex;
    private final int preferenceIndex;
    private final Map<String, List<PraPRMethodInfo>> mutatedClassMethodsInfo;
    private final Map<String, List<FieldInfo>> mutatedClassFieldsInfo;
    private final MethodInfo mutatedMethodInfo;
    private final ClassByteArraySource cache;
    private final ScopeTracker scopeTracker;
    protected LocalVariablesSorter lvs;
    private final ClassName owningClassName;
    
    ArgumentsListMutatorMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final PraPRMethodMutatorFactory variant, final ClassByteArraySource cache, final int overloadIndex, final Preference preference, final int preferenceIndex) {
        super(393216, methodVisitor);
        this.context = context;
        this.mutatedClassMethodsInfo = cci.methodsInfo;
        this.mutatedClassFieldsInfo = cci.fieldsInfo;
        this.mutatedMethodInfo = methodInfo;
        this.cache = cache;
        this.variant = variant;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.preference = preference;
        this.overloadIndex = overloadIndex;
        this.preferenceIndex = preferenceIndex;
        this.owningClassName = Commons.getOwningClassName(methodInfo);
    }
    
    private String pickOverload(final int opcode, final String name, final String excludedDesc, final Map<String, List<PraPRMethodInfo>> methodsInfo) {
        int count = 0;
        for (final Map.Entry<String, List<PraPRMethodInfo>> ent : methodsInfo.entrySet()) {
            final String desc = ent.getKey();
            if (!desc.equals(excludedDesc) && Type.getReturnType(desc).equals(Type.getReturnType(excludedDesc))) {
                final List<PraPRMethodInfo> smil = ent.getValue();
                for (final PraPRMethodInfo smi : smil) {
                    if (smi.name.equals(name)) {
                        final ClassName mutatedClassName = this.owningClassName;
                        if ((!mutatedClassName.equals(smi.owningClassName) && !smi.isPublic) || opcode == 184 != smi.isStatic) {
                            continue;
                        }
                        if (count == this.overloadIndex) {
                            return desc;
                        }
                        ++count;
                    }
                }
            }
        }
        return null;
    }
    
    private int pickLocalVariable(final String desc, final int index) {
        final LocalVarInfo lvi = Commons.pickLocalVariable(this.scopeTracker.visibleLocals, desc, 0, index);
        return (lvi == null) ? -1 : lvi.index;
    }
    
    private int firstMatch(final Type[] asis, final boolean[] used, final Type cat) {
        for (int i = 0; i < asis.length; ++i) {
            if (asis[i].equals(cat) && !used[i]) {
                return i;
            }
        }
        return -1;
    }
    
    private FieldInfo pickField(final String desc, final int index) {
        if (this.mutatedMethodInfo.isConstructor()) {
            return null;
        }
        return Commons.pickField(this.mutatedClassFieldsInfo, desc, 0, index, this.mutatedMethodInfo.isStatic());
    }
    
    private void prepareStack(final Type[] asis, final int[] tempLocals, final Type[] tobe) {
        final boolean[] used = new boolean[asis.length];
        for (final Type cat : tobe) {
            final int argIndex = this.firstMatch(asis, used, cat);
            int localIndex;
            FieldInfo fieldInfo;
            if (argIndex >= 0) {
                used[argIndex] = true;
                localIndex = tempLocals[argIndex];
                fieldInfo = null;
            }
            else {
                final String catDesc = cat.getDescriptor();
                if (this.preference == Preference.FIELD) {
                    fieldInfo = this.pickField(catDesc, this.preferenceIndex);
                    if (fieldInfo == null) {
                        localIndex = this.pickLocalVariable(catDesc, 0);
                    }
                    else {
                        localIndex = -1;
                    }
                }
                else if (this.preference == Preference.LOCAL) {
                    localIndex = this.pickLocalVariable(catDesc, this.preferenceIndex);
                    if (localIndex < 0) {
                        fieldInfo = this.pickField(catDesc, 0);
                    }
                    else {
                        fieldInfo = null;
                    }
                }
                else {
                    localIndex = -1;
                    fieldInfo = null;
                }
            }
            if (fieldInfo != null) {
                Commons.injectFieldValue(this.mv, 0, fieldInfo, cat);
            }
            else if (localIndex < 0) {
                Commons.injectDefaultValue(this.mv, cat);
            }
            else {
                Commons.injectLocalValue(this.mv, localIndex, cat);
            }
        }
    }
    
    @Override
    public void visitMethodInsn(final int opcode, final String ownerInternalName, final String name, final String desc, final boolean itf) {
        String descPrime;
        if (ownerInternalName.equals(this.owningClassName.asInternalName())) {
            descPrime = this.pickOverload(opcode, name, desc, this.mutatedClassMethodsInfo);
        }
        else {
            final CollectedClassInfo cci = ClassInfoCollector.collect(this.cache, ownerInternalName);
            descPrime = this.pickOverload(opcode, name, desc, cci.methodsInfo);
        }
        if (descPrime != null) {
            final String msg = String.format("replaced call to %s%s with a call to %s%s", name, desc, name, descPrime);
            final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
            if (this.context.shouldMutate(newId)) {
                final Type[] asis = Type.getArgumentTypes(desc);
                final int[] tempLocals = Commons.createTempLocals(this.lvs, asis);
                Commons.storeValues(this.mv, asis, tempLocals);
                final Type[] tobe = Type.getArgumentTypes(descPrime);
                this.prepareStack(asis, tempLocals, tobe);
                super.visitMethodInsn(opcode, ownerInternalName, name, descPrime, itf);
            }
            else {
                super.visitMethodInsn(opcode, ownerInternalName, name, desc, itf);
            }
        }
        else {
            super.visitMethodInsn(opcode, ownerInternalName, name, desc, itf);
        }
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.scopeTracker.transfer(label);
        super.visitLabel(label);
    }
}
