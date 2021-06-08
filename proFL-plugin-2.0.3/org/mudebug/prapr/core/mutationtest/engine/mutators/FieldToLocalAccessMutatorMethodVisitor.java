// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.reloc.asm.Label;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.reloc.asm.MethodVisitor;

class FieldToLocalAccessMutatorMethodVisitor extends MethodVisitor
{
    private final FieldToLocalAccessMutator variant;
    private final ClassByteArraySource cache;
    private final ScopeTracker scopeTracker;
    private final MutationContext context;
    
    FieldToLocalAccessMutatorMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final FieldToLocalAccessMutator variant, final ClassByteArraySource cache) {
        super(393216, methodVisitor);
        this.variant = variant;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.context = context;
        this.cache = cache;
    }
    
    private LocalVarInfo pickLocalVariable(final String desc) {
        int count = 0;
        for (final LocalVarInfo lvi : this.scopeTracker.visibleLocals) {
            if (lvi.typeDescriptor.equals(desc)) {
                if (this.variant.ordinal() == count) {
                    return lvi;
                }
                ++count;
            }
        }
        final Type desiredType = Type.getType(desc);
        if (desiredType.getSort() == 10) {
            count = 0;
            for (final LocalVarInfo lvi2 : this.scopeTracker.visibleLocals) {
                final Type varType = Type.getType(lvi2.typeDescriptor);
                if (varType.getSort() == 10) {
                    final String desiredTypeInternalName = desiredType.getInternalName();
                    final String varTypeInternalName = varType.getInternalName();
                    if (!this.getSuperTypes(varTypeInternalName).contains(desiredTypeInternalName)) {
                        continue;
                    }
                    if (this.variant.ordinal() == count) {
                        return lvi2;
                    }
                    ++count;
                }
            }
        }
        return null;
    }
    
    private Set<String> getSuperTypes(String typeInternalName) {
        final Set<String> supers = new HashSet<String>();
        while (typeInternalName != null) {
            typeInternalName = Commons.getSupertype(this.cache, typeInternalName);
            if (typeInternalName != null) {
                supers.add(typeInternalName);
            }
        }
        return supers;
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.scopeTracker.transfer(label);
        super.visitLabel(label);
    }
    
    private int varLoadOpcodeFor(final Type type) {
        switch (type.getSort()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                return 21;
            }
            case 6: {
                return 23;
            }
            case 7: {
                return 22;
            }
            case 8: {
                return 24;
            }
            default: {
                return 25;
            }
        }
    }
    
    private int varStoreOpcodeFor(final Type type) {
        switch (type.getSort()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                return 54;
            }
            case 6: {
                return 56;
            }
            case 7: {
                return 55;
            }
            case 8: {
                return 57;
            }
            default: {
                return 58;
            }
        }
    }
    
    @Override
    public void visitFieldInsn(final int opcode, final String owner, final String name, final String desc) {
        final LocalVarInfo localReplacement = this.pickLocalVariable(desc);
        if (localReplacement != null) {
            final String md = String.format("access to field %s is replaced by access to local %s", name, localReplacement.name);
            final MutationIdentifier newId = this.context.registerMutation(this.variant, md);
            if (this.context.shouldMutate(newId)) {
                final Type type = Type.getType(desc);
                int opcodePrime = 0;
                switch (opcode) {
                    case 180: {
                        super.visitInsn(87);
                    }
                    case 178: {
                        opcodePrime = this.varLoadOpcodeFor(type);
                        break;
                    }
                    case 181: {
                        if (type.getSize() == 1) {
                            super.visitInsn(95);
                        }
                        else {
                            if (type.getSize() != 2) {
                                throw new IllegalArgumentException();
                            }
                            super.visitInsn(93);
                            super.visitInsn(88);
                        }
                        super.visitInsn(87);
                    }
                    case 179: {
                        opcodePrime = this.varStoreOpcodeFor(type);
                        break;
                    }
                    default: {
                        throw new RuntimeException("unexpected opcode");
                    }
                }
                super.visitVarInsn(opcodePrime, localReplacement.index);
            }
            else {
                super.visitFieldInsn(opcode, owner, name, desc);
            }
        }
        else {
            super.visitFieldInsn(opcode, owner, name, desc);
        }
    }
}
