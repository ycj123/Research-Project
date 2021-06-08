// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ClassInfoCollector;
import java.util.Iterator;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.classinfo.ClassName;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.PraPRMethodInfo;
import java.util.List;
import java.util.Map;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.reloc.asm.MethodVisitor;

class MethodNameMutatorMethodVisitor extends MethodVisitor
{
    private final MutationContext context;
    private final MethodNameMutator variant;
    private final Map<String, List<PraPRMethodInfo>> mutatedClassMethodsInfo;
    private final ClassName mutatedClassName;
    private final ClassByteArraySource cache;
    
    MethodNameMutatorMethodVisitor(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor, final CollectedClassInfo cci, final ClassByteArraySource cache, final MethodNameMutator variant) {
        super(393216, methodVisitor);
        this.context = context;
        this.variant = variant;
        this.mutatedClassMethodsInfo = cci.methodsInfo;
        this.mutatedClassName = Commons.getOwningClassName(methodInfo);
        this.cache = cache;
    }
    
    private String pickMethodName(final int opcode, final String excludedName, final String desc, final Map<String, List<PraPRMethodInfo>> methodsInfo) {
        final List<PraPRMethodInfo> smil = methodsInfo.get(desc);
        if (smil != null) {
            int counter = 0;
            for (final PraPRMethodInfo smi : smil) {
                final String name = smi.name;
                if (!name.equals(excludedName) && !this.isInitializer(name) && (this.mutatedClassName.equals(smi.owningClassName) || smi.isPublic) && opcode == 184 == smi.isStatic) {
                    if (counter == this.variant.ordinal()) {
                        return name;
                    }
                    ++counter;
                }
            }
        }
        return null;
    }
    
    private boolean isInitializer(final String methodName) {
        return methodName.matches("<init>|<clinit>");
    }
    
    @Override
    public void visitMethodInsn(final int opcode, final String ownerInternalName, final String name, final String desc, final boolean itf) {
        if (MethodInfo.isConstructor(name)) {
            super.visitMethodInsn(opcode, ownerInternalName, name, desc, itf);
        }
        else {
            String namePrime;
            if (ownerInternalName.equals(this.mutatedClassName.asInternalName())) {
                namePrime = this.pickMethodName(opcode, name, desc, this.mutatedClassMethodsInfo);
            }
            else {
                final CollectedClassInfo cci = ClassInfoCollector.collect(this.cache, ownerInternalName);
                namePrime = this.pickMethodName(opcode, name, desc, cci.methodsInfo);
            }
            if (namePrime != null) {
                final String msg = String.format("replaced call to %s with a call to %s", name, namePrime);
                final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
                if (this.context.shouldMutate(newId)) {
                    super.visitMethodInsn(opcode, ownerInternalName, namePrime, desc, itf);
                }
                else {
                    super.visitMethodInsn(opcode, ownerInternalName, name, desc, itf);
                }
            }
            else {
                super.visitMethodInsn(opcode, ownerInternalName, name, desc, itf);
            }
        }
    }
}
