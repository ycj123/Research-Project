// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.engine.gregor;

import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.mutationtest.engine.PoisonStatus;
import org.pitest.mutationtest.engine.MutationIdentifier;
import java.util.HashSet;
import java.util.Set;
import org.pitest.mutationtest.engine.Location;
import org.pitest.mutationtest.engine.gregor.analysis.InstructionCounter;

class MethodMutationContext implements MutationContext, InstructionCounter
{
    private final ClassContext classContext;
    private final Location location;
    private int instructionIndex;
    private int lastLineNumber;
    private final Set<String> mutationFindingDisabledReasons;
    
    MethodMutationContext(final ClassContext classContext, final Location location) {
        this.mutationFindingDisabledReasons = new HashSet<String>();
        this.classContext = classContext;
        this.location = location;
    }
    
    @Override
    public MutationIdentifier registerMutation(final MethodMutatorFactory factory, final String description) {
        final MutationIdentifier newId = this.getNextMutationIdentifer(factory, this.classContext.getJavaClassName());
        final MutationDetails details = new MutationDetails(newId, this.classContext.getFileName(), description, this.lastLineNumber, this.classContext.getCurrentBlock(), this.classContext.isWithinFinallyBlock(), PoisonStatus.NORMAL);
        this.registerMutation(details);
        return newId;
    }
    
    private MutationIdentifier getNextMutationIdentifer(final MethodMutatorFactory factory, final String className) {
        return new MutationIdentifier(this.location, this.instructionIndex, factory.getGloballyUniqueId());
    }
    
    private void registerMutation(final MutationDetails details) {
        if (!this.isMutationFindingDisabled()) {
            this.classContext.addMutation(details);
        }
    }
    
    private boolean isMutationFindingDisabled() {
        return !this.mutationFindingDisabledReasons.isEmpty();
    }
    
    @Override
    public void registerCurrentLine(final int line) {
        this.lastLineNumber = line;
    }
    
    @Override
    public void registerNewBlock() {
        this.classContext.registerNewBlock();
    }
    
    @Override
    public void registerFinallyBlockStart() {
        this.classContext.registerFinallyBlockStart();
    }
    
    @Override
    public void registerFinallyBlockEnd() {
        this.classContext.registerFinallyBlockEnd();
    }
    
    @Override
    public ClassInfo getClassInfo() {
        return this.classContext.getClassInfo();
    }
    
    @Override
    public boolean shouldMutate(final MutationIdentifier newId) {
        return this.classContext.shouldMutate(newId);
    }
    
    @Override
    public void disableMutations(final String reason) {
        this.mutationFindingDisabledReasons.add(reason);
    }
    
    @Override
    public void enableMutatations(final String reason) {
        this.mutationFindingDisabledReasons.remove(reason);
    }
    
    @Override
    public void increment() {
        ++this.instructionIndex;
    }
    
    @Override
    public int currentInstructionCount() {
        return this.instructionIndex;
    }
}
