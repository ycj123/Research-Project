// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.reloc.asm.Label;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Preference;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.FieldInfo;
import java.util.List;
import java.util.Map;
import org.pitest.reloc.asm.MethodVisitor;

final class DereferenceGuardMutatorMethodVisitor extends MethodVisitor
{
    private final Map<String, List<FieldInfo>> mutatedClassFieldsInfo;
    private final DereferenceGuardMutator variant;
    private final MutationContext context;
    private final ScopeTracker scopeTracker;
    private final MethodInfo mutatedMethodInfo;
    private final Preference preference;
    private final int preferenceIndex;
    protected LocalVariablesSorter lvs;
    
    DereferenceGuardMutatorMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final Preference preference, final int preferenceIndex, final DereferenceGuardMutator variant) {
        super(393216, methodVisitor);
        this.context = context;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.variant = variant;
        this.mutatedClassFieldsInfo = cci.fieldsInfo;
        this.mutatedMethodInfo = methodInfo;
        this.preference = preference;
        this.preferenceIndex = preferenceIndex;
    }
    
    private LocalVarInfo pickLocalVariable(final String desc) {
        return Commons.pickLocalVariable(this.scopeTracker.visibleLocals, desc, 0, this.preferenceIndex);
    }
    
    private FieldInfo pickField(final String desc) {
        if (this.mutatedMethodInfo.isConstructor()) {
            return null;
        }
        return Commons.pickField(this.mutatedClassFieldsInfo, desc, 0, this.preferenceIndex, this.mutatedMethodInfo.isStatic());
    }
    
    @Override
    public void visitFieldInsn(final int opcode, final String owner, final String name, final String desc) {
        if (opcode == 180) {
            final Type accessedFieldType = Type.getType(desc);
            final Preference pref = this.preference;
            LocalVarInfo lvi;
            FieldInfo fi;
            if (pref == Preference.DEFVAL) {
                lvi = null;
                fi = null;
            }
            else {
                if (pref == Preference.LOCAL) {
                    lvi = this.pickLocalVariable(desc);
                    fi = null;
                }
                else {
                    lvi = null;
                    fi = this.pickField(desc);
                }
                if (lvi == null && fi == null) {
                    super.visitFieldInsn(opcode, owner, name, desc);
                    return;
                }
            }
            String msg = String.format("the access to %s is guarded using ", name);
            if (lvi == null && fi == null) {
                msg += String.format("value %s", Commons.defValString(accessedFieldType));
            }
            else if (fi == null) {
                msg += String.format("local %s", lvi.name);
            }
            else {
                msg += String.format("field %s", fi.name);
            }
            final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(89);
                final Label lEscape = new Label();
                super.visitJumpInsn(199, lEscape);
                super.visitInsn(87);
                if (lvi == null && fi == null) {
                    Commons.injectDefaultValue(this.mv, accessedFieldType);
                }
                else if (fi == null) {
                    Commons.injectLocalValue(this.mv, lvi.index, accessedFieldType);
                }
                else {
                    Commons.injectFieldValue(this.mv, 0, fi, accessedFieldType);
                }
                final Label lEnd = new Label();
                super.visitJumpInsn(167, lEnd);
                super.visitLabel(lEscape);
                super.visitFieldInsn(opcode, owner, name, desc);
                super.visitLabel(lEnd);
            }
            else {
                super.visitFieldInsn(opcode, owner, name, desc);
            }
        }
        else {
            super.visitFieldInsn(opcode, owner, name, desc);
        }
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.scopeTracker.transfer(label);
        super.visitLabel(label);
    }
}
