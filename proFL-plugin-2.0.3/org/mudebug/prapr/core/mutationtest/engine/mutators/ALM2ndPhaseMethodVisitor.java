// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators;

import org.pitest.reloc.asm.Label;
import java.util.Iterator;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Preference;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.LocalVarInfo;
import java.util.LinkedList;
import org.pitest.reloc.asm.Type;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.commons.LocalVariablesSorter;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ScopeTracker;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.FieldInfo;
import java.util.List;
import java.util.Map;
import org.pitest.reloc.asm.MethodVisitor;

class ALM2ndPhaseMethodVisitor extends MethodVisitor
{
    private final Map<String, List<FieldInfo>> mutatedClassFieldsInfo;
    private final ArgumentsListMutatorSecondPhase variant;
    private final MethodInfo mutatedMethodInfo;
    private final ScopeTracker scopeTracker;
    private final MutationContext context;
    LocalVariablesSorter lvs;
    
    ALM2ndPhaseMethodVisitor(final MethodVisitor methodVisitor, final MethodInfo methodInfo, final MutationContext context, final CollectedClassInfo cci, final ArgumentsListMutatorSecondPhase variant) {
        super(393216, methodVisitor);
        this.variant = variant;
        this.context = context;
        this.scopeTracker = new ScopeTracker(cci.findMethod(methodInfo.getName(), methodInfo.getMethodDescriptor()).localsInfo);
        this.mutatedClassFieldsInfo = cci.fieldsInfo;
        this.mutatedMethodInfo = methodInfo;
    }
    
    private int getArgsCount() {
        return this.variant.getPattern().length;
    }
    
    @Override
    public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf) {
        final Type[] args = Type.getArgumentTypes(desc);
        if (args.length == this.getArgsCount()) {
            final List<LocalVarInfo> lvis = new LinkedList<LocalVarInfo>();
            final List<FieldInfo> fis = new LinkedList<FieldInfo>();
            for (int i = 0; i < args.length; ++i) {
                final String paramDesc = args[i].getDescriptor();
                final Preference preference = this.variant.getPattern()[i];
                final int ordinal = this.variant.getVariantOrdinal();
                switch (preference) {
                    case LOCAL: {
                        final List<LocalVarInfo> visibleLocals = this.scopeTracker.visibleLocals;
                        final LocalVarInfo lvi = Commons.pickLocalVariable(visibleLocals, paramDesc, 0, ordinal);
                        if (lvi == null) {
                            super.visitMethodInsn(opcode, owner, name, desc, itf);
                            return;
                        }
                        lvis.add(lvi);
                        break;
                    }
                    case FIELD: {
                        final boolean isStatic = this.mutatedMethodInfo.isStatic();
                        final FieldInfo fi = Commons.pickField(this.mutatedClassFieldsInfo, paramDesc, 0, ordinal, isStatic);
                        if (fi == null) {
                            super.visitMethodInsn(opcode, owner, name, desc, itf);
                            return;
                        }
                        fis.add(fi);
                        break;
                    }
                }
            }
            final String msg = String.format("replaced call to %s%s with a call to %s%s", name, desc, name, desc);
            final MutationIdentifier id = this.context.registerMutation(this.variant, msg);
            if (this.context.shouldMutate(id)) {
                final int[] tempLocals = Commons.createTempLocals(this.lvs, args);
                Commons.storeValues(this.mv, args, tempLocals);
                final Iterator<LocalVarInfo> lvit = lvis.iterator();
                final Iterator<FieldInfo> fit = fis.iterator();
                for (int j = 0; j < args.length; ++j) {
                    final Preference preference2 = this.variant.getPattern()[j];
                    final Type type = args[j];
                    switch (preference2) {
                        case FIELD: {
                            final FieldInfo fi2 = fit.next();
                            Commons.injectFieldValue(this.mv, 0, fi2, type);
                            fit.remove();
                            break;
                        }
                        case LOCAL: {
                            final LocalVarInfo lvi2 = lvit.next();
                            Commons.injectLocalValue(this.mv, lvi2.index, type);
                            lvit.remove();
                            break;
                        }
                        case DEFVAL: {
                            Commons.injectDefaultValue(this.mv, type);
                            break;
                        }
                        case NONE: {
                            Commons.injectLocalValue(this.mv, tempLocals[j], type);
                            break;
                        }
                    }
                }
            }
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
    
    @Override
    public void visitLabel(final Label label) {
        this.scopeTracker.transfer(label);
        super.visitLabel(label);
    }
}
