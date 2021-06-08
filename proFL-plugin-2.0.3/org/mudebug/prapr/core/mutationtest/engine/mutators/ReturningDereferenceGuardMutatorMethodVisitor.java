// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.reloc.asm.Label;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Preference;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.FieldInfo;
import java.util.List;
import java.util.Map;
import org.pitest.reloc.asm.MethodVisitor;

class ReturningDereferenceGuardMutatorMethodVisitor extends MethodVisitor
{
    private final Map<String, List<FieldInfo>> mutatedClassFieldsInfo;
    private final ReturningDereferenceGuardMutator variant;
    private final MutationContext context;
    private final MethodInfo mutatedMethodInfo;
    private final ScopeTracker scopeTracker;
    private final Preference preference;
    private final int preferenceIndex;
    private final Type mutatedMethodReturnType;
    LocalVariablesSorter lvs;
    
    ReturningDereferenceGuardMutatorMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final Preference preference, final int preferenceIndex, final ReturningDereferenceGuardMutator variant) {
        super(393216, methodVisitor);
        this.context = context;
        this.variant = variant;
        this.mutatedClassFieldsInfo = cci.fieldsInfo;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.mutatedMethodInfo = methodInfo;
        this.preference = preference;
        this.preferenceIndex = preferenceIndex;
        this.mutatedMethodReturnType = Type.getReturnType(methodInfo.getMethodDescriptor());
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
    
    private String makeDesc(final LocalVarInfo lvi, final FieldInfo fi) {
        if (this.mutatedMethodInfo.isVoid()) {
            return "enclosing method";
        }
        if (lvi == null && fi == null) {
            return String.format("default value %s", Commons.defValString(this.mutatedMethodReturnType));
        }
        if (lvi == null) {
            return String.format("field %s", fi.name);
        }
        return String.format("local %s", lvi.name);
    }
    
    @Override
    public void visitFieldInsn(final int opcode, final String owner, final String name, final String desc) {
        if (opcode == 180) {
            LocalVarInfo lvi;
            FieldInfo fi;
            if (this.preference == Preference.DEFVAL || this.mutatedMethodInfo.isVoid()) {
                lvi = null;
                fi = null;
            }
            else {
                final String mutatedMethodReturnTypeDesc = this.mutatedMethodReturnType.getDescriptor();
                if (this.preference == Preference.LOCAL) {
                    lvi = this.pickLocalVariable(mutatedMethodReturnTypeDesc);
                    fi = null;
                }
                else {
                    lvi = null;
                    fi = this.pickField(mutatedMethodReturnTypeDesc);
                }
                if (lvi == null && fi == null) {
                    super.visitFieldInsn(opcode, owner, name, desc);
                    return;
                }
            }
            final String msg = String.format("the access to %s is guarded returning %s", name, this.makeDesc(lvi, fi));
            final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(89);
                final Label lEscape = new Label();
                super.visitJumpInsn(199, lEscape);
                Commons.injectReturnStmt(this.mv, this.mutatedMethodReturnType, lvi, fi);
                super.visitLabel(lEscape);
                super.visitFieldInsn(opcode, owner, name, desc);
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
