// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.reloc.asm.Label;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Preference;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.FieldInfo;
import java.util.List;
import java.util.Map;
import org.pitest.reloc.asm.MethodVisitor;

class NonVoidMethCallRemovalMethodVisitor extends MethodVisitor
{
    private final Map<String, List<FieldInfo>> mutatedClassFieldsInfo;
    private final NonVoidMethodCallRemovalMutator variant;
    private final ScopeTracker scopeTracker;
    private final MutationContext context;
    private final MethodInfo mutatedMethodInfo;
    private final Preference preference;
    private final int preferenceIndex;
    
    NonVoidMethCallRemovalMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final NonVoidMethodCallRemovalMutator variant, final Preference preference, final int preferenceIndex, final CollectedClassInfo cci) {
        super(393216, methodVisitor);
        this.variant = variant;
        this.mutatedClassFieldsInfo = cci.fieldsInfo;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.context = context;
        this.mutatedMethodInfo = methodInfo;
        this.preference = preference;
        this.preferenceIndex = preferenceIndex;
    }
    
    private LocalVarInfo pickLocalVariable(final String desc, final int index) {
        return Commons.pickLocalVariable(this.scopeTracker.visibleLocals, desc, 0, index);
    }
    
    private FieldInfo pickField(final String desc, final int index) {
        if (this.mutatedMethodInfo.isConstructor()) {
            return null;
        }
        return Commons.pickField(this.mutatedClassFieldsInfo, desc, 0, index, this.mutatedMethodInfo.isStatic());
    }
    
    @Override
    public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf) {
        if (!MethodInfo.isVoid(desc)) {
            final Type returnType = Type.getReturnType(desc);
            LocalVarInfo lvi;
            FieldInfo fi;
            if (this.preference == Preference.DEFVAL) {
                lvi = null;
                fi = null;
            }
            else {
                if (this.preference == Preference.LOCAL) {
                    lvi = this.pickLocalVariable(returnType.getDescriptor(), this.preferenceIndex);
                    fi = null;
                }
                else {
                    lvi = null;
                    fi = this.pickField(returnType.getDescriptor(), this.preferenceIndex);
                }
                if (lvi == null && fi == null) {
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                    return;
                }
            }
            String description = String.format("the call to %s::%s%s is replaced with the used of ", owner.replace('/', '.'), name, desc);
            if (lvi == null && fi == null) {
                description += String.format("default value %s", Commons.defValString(returnType));
            }
            else if (fi == null) {
                description += String.format("local %s", lvi.name);
            }
            else {
                description += String.format("field %s", fi.name);
            }
            final MutationIdentifier newId = this.context.registerMutation(this.variant, description);
            if (this.context.shouldMutate(newId)) {
                this.popArguments(desc);
                if (!Commons.isStaticCall(opcode)) {
                    super.visitInsn(87);
                }
                if (lvi == null && fi == null) {
                    Commons.injectDefaultValue(this.mv, returnType);
                }
                else if (fi == null) {
                    Commons.injectLocalValue(this.mv, lvi.index, returnType);
                }
                else {
                    Commons.injectFieldValue(this.mv, 0, fi, returnType);
                }
            }
            else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
        else {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }
    
    private void popArguments(final String methodDesc) {
        final Type[] args = Type.getArgumentTypes(methodDesc);
        for (int i = args.length - 1; i >= 0; --i) {
            final Type argType = args[i];
            if (argType.getSize() == 1) {
                super.visitInsn(87);
            }
            else {
                super.visitInsn(88);
            }
        }
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.scopeTracker.transfer(label);
        super.visitLabel(label);
    }
}
