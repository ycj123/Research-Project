// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.reloc.asm.Label;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.pitest.reloc.asm.Type;
import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.reloc.asm.MethodVisitor;

class VoidMethodCallGuardMutatorMethodVisitor extends MethodVisitor
{
    private final MethodMutatorFactory variant;
    private final MutationContext context;
    LocalVariablesSorter lvs;
    
    VoidMethodCallGuardMutatorMethodVisitor(final MutationContext context, final MethodVisitor methodVisitor, final MethodMutatorFactory variant) {
        super(393216, methodVisitor);
        this.context = context;
        this.variant = variant;
    }
    
    @Override
    public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf) {
        final Type calleeReturnType = Type.getReturnType(desc);
        final int calleeReturnSort = calleeReturnType.getSort();
        if (calleeReturnSort == 0 && Commons.isVirtualCall(opcode) && !name.equals("<init>")) {
            final String msg = String.format("the call to %s::%s%s is guarded", owner.replace('/', '.'), name, desc);
            final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
            if (this.context.shouldMutate(newId)) {
                final Type[] args = Type.getArgumentTypes(desc);
                final int[] tempLocals = Commons.createTempLocals(this.lvs, args);
                Commons.storeValues(this.mv, args, tempLocals);
                super.visitInsn(89);
                final Label lEscape = new Label();
                super.visitJumpInsn(199, lEscape);
                super.visitInsn(87);
                final Label lEnd = new Label();
                super.visitJumpInsn(167, lEnd);
                super.visitLabel(lEscape);
                Commons.restoreValues(this.mv, tempLocals, args);
                super.visitMethodInsn(opcode, owner, name, desc, itf);
                super.visitLabel(lEnd);
            }
            else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
        else {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }
}
