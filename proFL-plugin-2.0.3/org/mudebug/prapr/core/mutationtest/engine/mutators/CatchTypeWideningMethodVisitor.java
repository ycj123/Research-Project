// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import java.util.Objects;
import org.pitest.reloc.asm.Label;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.reloc.asm.MethodVisitor;

class CatchTypeWideningMethodVisitor extends MethodVisitor
{
    private final CatchTypeWideningMutator variant;
    private final ClassByteArraySource cache;
    private final MutationContext context;
    private int count;
    private Label handler;
    private String desc;
    
    public CatchTypeWideningMethodVisitor(final MethodVisitor methodVisitor, final CatchTypeWideningMutator variant, final MutationContext context, final ClassByteArraySource cache) {
        super(393216, methodVisitor);
        this.variant = variant;
        this.context = context;
        this.count = 0;
        this.cache = cache;
    }
    
    @Override
    public void visitLabel(final Label label) {
        if (Objects.equals(label, this.handler)) {
            this.context.registerMutation(this.variant, this.desc);
        }
        super.visitLabel(label);
    }
    
    @Override
    public void visitTryCatchBlock(final Label start, final Label end, final Label handler, String type) {
        if (type == null) {
            super.visitTryCatchBlock(start, end, handler, null);
            return;
        }
        if (this.count == this.variant.ordinal()) {
            final String replacedType = Commons.getSupertype(this.cache, type);
            if (replacedType == null) {
                super.visitTryCatchBlock(start, end, handler, type);
                return;
            }
            this.desc = String.format("catch type %s is replaced with %s", type, replacedType);
            type = replacedType;
            this.handler = handler;
        }
        ++this.count;
        super.visitTryCatchBlock(start, end, handler, type);
    }
}
