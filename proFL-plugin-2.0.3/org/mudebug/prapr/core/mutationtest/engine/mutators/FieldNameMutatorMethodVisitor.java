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
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.FieldInfo;
import java.util.List;
import java.util.Map;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.reloc.asm.MethodVisitor;

final class FieldNameMutatorMethodVisitor extends MethodVisitor
{
    private final MutationContext context;
    private final FieldNameMutator variant;
    private final Map<String, List<FieldInfo>> mutatedClassFieldsInfo;
    private final ClassName mutatedClassName;
    private final ClassByteArraySource cache;
    
    FieldNameMutatorMethodVisitor(final MutationContext context, final MethodVisitor methodVisitor, final MethodInfo methodInfo, final CollectedClassInfo cci, final FieldNameMutator variant, final ClassByteArraySource cache) {
        super(393216, methodVisitor);
        this.context = context;
        this.variant = variant;
        this.mutatedClassFieldsInfo = cci.fieldsInfo;
        this.mutatedClassName = Commons.getOwningClassName(methodInfo);
        this.cache = cache;
    }
    
    private String pickFieldName(final int opcode, final String excludedName, final String desc, final Map<String, List<FieldInfo>> fieldsInfo) {
        final List<FieldInfo> fil = fieldsInfo.get(desc);
        if (fil != null) {
            int counter = 0;
            for (final FieldInfo fi : fil) {
                if (!fi.name.equals(excludedName) && (this.mutatedClassName.equals(fi.owningClassName) || fi.isPublic) && this.isStaticAccess(opcode) == fi.isStatic && (!this.isStore(opcode) || !fi.isFinal)) {
                    if (counter == this.variant.ordinal()) {
                        return fi.name;
                    }
                    ++counter;
                }
            }
        }
        return null;
    }
    
    private boolean isStaticAccess(final int opcode) {
        return opcode == 178 || opcode == 179;
    }
    
    private boolean isStore(final int opcode) {
        return opcode == 181 || opcode == 179;
    }
    
    @Override
    public void visitFieldInsn(final int opcode, final String ownerInternalName, final String name, final String desc) {
        String namePrime;
        if (ownerInternalName.equals(this.mutatedClassName.asInternalName())) {
            namePrime = this.pickFieldName(opcode, name, desc, this.mutatedClassFieldsInfo);
        }
        else {
            final CollectedClassInfo cci = ClassInfoCollector.collect(this.cache, ownerInternalName);
            namePrime = this.pickFieldName(opcode, name, desc, cci.fieldsInfo);
        }
        if (namePrime != null) {
            final String msg = String.format("replaced access to %s with an access to %s", name, namePrime);
            final MutationIdentifier newId = this.context.registerMutation(this.variant, msg);
            if (this.context.shouldMutate(newId)) {
                super.visitFieldInsn(opcode, ownerInternalName, namePrime, desc);
            }
            else {
                super.visitFieldInsn(opcode, ownerInternalName, name, desc);
            }
        }
        else {
            super.visitFieldInsn(opcode, ownerInternalName, name, desc);
        }
    }
}
