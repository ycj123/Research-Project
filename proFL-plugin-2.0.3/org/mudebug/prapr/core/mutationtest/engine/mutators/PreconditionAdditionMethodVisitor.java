// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.mudebug.prapr.core.mutationtest.engine.mutators.util.FieldInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.pitest.reloc.asm.Label;
import java.util.Iterator;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.reloc.asm.Type;
import java.util.List;
import org.pitest.reloc.asm.MethodVisitor;

class PreconditionAdditionMethodVisitor extends MethodVisitor
{
    private final List<Integer> nullableParamIndices;
    private final Type mutatedMethodReturnType;
    private final String mutatedMethodName;
    private final MethodMutatorFactory variant;
    private final MutationContext context;
    
    public PreconditionAdditionMethodVisitor(final MethodVisitor methodVisitor, final MethodInfo methodInfo, final CollectedClassInfo cci, final MethodMutatorFactory variant, final MutationContext context) {
        super(393216, methodVisitor);
        this.mutatedMethodReturnType = Type.getReturnType(methodInfo.getMethodDescriptor());
        this.mutatedMethodName = methodInfo.getName();
        this.nullableParamIndices = cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).nullableParamIndices;
        this.context = context;
        this.variant = variant;
    }
    
    @Override
    public void visitCode() {
        super.visitCode();
        final int sz = this.nullableParamIndices.size();
        if (sz > 0) {
            final String msg = String.format("nullity checks are added for %d nullable parameters of the method %s", sz, this.mutatedMethodName);
            final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
            if (this.context.shouldMutate(newId)) {
                for (final int paramIndex : this.nullableParamIndices) {
                    this.injectNullityCheck(paramIndex);
                }
            }
        }
    }
    
    private void injectNullityCheck(final int paramIndex) {
        super.visitVarInsn(25, paramIndex);
        final Label lEscape = new Label();
        super.visitJumpInsn(199, lEscape);
        Commons.injectReturnStmt(this.mv, this.mutatedMethodReturnType, null, null);
        super.visitLabel(lEscape);
    }
}
