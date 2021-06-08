// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import org.pitest.reloc.asm.Type;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.reloc.asm.Label;
import java.util.Iterator;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.FieldInfo;
import java.util.List;
import java.util.Map;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.reloc.asm.MethodVisitor;

class LocalToFieldAccessMutatorMethodVisitor extends MethodVisitor
{
    private final MutationContext context;
    private final LocalToFieldAccessMutator variant;
    private final ScopeTracker scopeTracker;
    private final MethodInfo mutatedMethodInfo;
    private final Map<String, List<FieldInfo>> mutatedClassFieldsInfo;
    
    LocalToFieldAccessMutatorMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final LocalToFieldAccessMutator variant) {
        super(393216, methodVisitor);
        this.context = context;
        this.variant = variant;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.mutatedMethodInfo = methodInfo;
        this.mutatedClassFieldsInfo = cci.fieldsInfo;
    }
    
    private FieldInfo pickField(final String desc, final boolean toBeWrittenOn) {
        final List<FieldInfo> fil = this.mutatedClassFieldsInfo.get(desc);
        if (fil != null) {
            int counter = 0;
            for (final FieldInfo fi : fil) {
                if ((!this.mutatedMethodInfo.isStatic() || fi.isStatic) && (!toBeWrittenOn || !fi.isFinal)) {
                    if (counter == this.variant.ordinal()) {
                        return fi;
                    }
                    ++counter;
                }
            }
        }
        return null;
    }
    
    private boolean isStore(final int opcode) {
        switch (opcode) {
            case 54:
            case 55:
            case 56:
            case 57:
            case 58: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private void loadThis() {
        super.visitVarInsn(25, 0);
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.scopeTracker.transfer(label);
        super.visitLabel(label);
    }
    
    @Override
    public void visitVarInsn(final int opcode, final int var) {
        if (opcode == 169) {
            super.visitVarInsn(opcode, var);
            return;
        }
        final LocalVarInfo thisLocal = this.scopeTracker.find(var);
        if (thisLocal != null) {
            final String desc = thisLocal.typeDescriptor;
            final FieldInfo fieldReplacement = this.pickField(desc, this.isStore(opcode));
            if (fieldReplacement != null) {
                final String md = String.format("access to local %s is replaced by access to field %s", thisLocal.name, fieldReplacement.name);
                final MutationIdentifier newId = this.context.registerMutation(this.variant, md);
                if (this.context.shouldMutate(newId)) {
                    final String fieldOwnerInternalName = fieldReplacement.owningClassName.asInternalName();
                    final String fieldName = fieldReplacement.name;
                    int fieldAccessOpcode;
                    if (this.isStore(opcode)) {
                        if (fieldReplacement.isStatic) {
                            fieldAccessOpcode = 179;
                        }
                        else {
                            this.loadThis();
                            switch (Type.getType(desc).getSize()) {
                                case 1: {
                                    super.visitInsn(95);
                                    break;
                                }
                                case 2: {
                                    super.visitInsn(91);
                                    super.visitInsn(87);
                                    break;
                                }
                                default: {
                                    throw new RuntimeException();
                                }
                            }
                            fieldAccessOpcode = 181;
                        }
                    }
                    else if (fieldReplacement.isStatic) {
                        fieldAccessOpcode = 178;
                    }
                    else {
                        this.loadThis();
                        fieldAccessOpcode = 180;
                    }
                    super.visitFieldInsn(fieldAccessOpcode, fieldOwnerInternalName, fieldName, desc);
                }
                else {
                    super.visitVarInsn(opcode, var);
                }
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
