// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import java.util.Iterator;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import org.pitest.reloc.asm.Label;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.reloc.asm.MethodVisitor;

class LocalNameMutatorMethodVisitor extends MethodVisitor
{
    private final MutationContext context;
    private final ScopeTracker scopeTracker;
    private final LocalNameMutator variant;
    
    LocalNameMutatorMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final LocalNameMutator variant) {
        super(393216, methodVisitor);
        this.context = context;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.variant = variant;
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.scopeTracker.transfer(label);
        super.visitLabel(label);
    }
    
    private LocalVarInfo pickLocalVariable(final String desc, final int excludedIndex) {
        int count = 0;
        for (final LocalVarInfo lvi : this.scopeTracker.visibleLocals) {
            if (lvi.index != excludedIndex && lvi.typeDescriptor.equals(desc)) {
                if (this.variant.ordinal() == count) {
                    return lvi;
                }
                ++count;
            }
        }
        return null;
    }
    
    private boolean isLoad(final int opcode) {
        switch (opcode) {
            case 21:
            case 22:
            case 23:
            case 24:
            case 25: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    @Override
    public void visitVarInsn(final int opcode, final int var) {
        final LocalVarInfo thisLocal = this.scopeTracker.find(var);
        if (thisLocal == null) {
            super.visitVarInsn(opcode, var);
            return;
        }
        final LocalVarInfo otherLocal = this.pickLocalVariable(thisLocal.typeDescriptor, var);
        if (otherLocal != null) {
            final String msg = String.format("local %s is replaced by local %s to be %s", thisLocal.name, otherLocal.name, this.isLoad(opcode) ? "used" : "defined");
            final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
            if (this.context.shouldMutate(newId)) {
                super.visitVarInsn(opcode, otherLocal.index);
            }
            else {
                super.visitVarInsn(opcode, var);
            }
        }
        else {
            super.visitVarInsn(opcode, var);
        }
    }
}
