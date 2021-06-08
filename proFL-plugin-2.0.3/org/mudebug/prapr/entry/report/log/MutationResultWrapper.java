// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.report.log;

import org.pitest.classinfo.ClassName;
import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.mutationtest.DetectionStatus;
import java.util.Objects;
import org.mudebug.prapr.entry.report.Commons;
import org.pitest.mutationtest.MutationResult;

public final class MutationResultWrapper
{
    private final MutationResult mutationResult;
    
    private MutationResultWrapper(final MutationResult mutationResult) {
        this.mutationResult = mutationResult;
    }
    
    public static MutationResultWrapper wrap(final MutationResult mr) {
        return new MutationResultWrapper(mr);
    }
    
    public MutationResult getMutationResult() {
        return this.mutationResult;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final MutationResultWrapper that = (MutationResultWrapper)o;
        final DetectionStatus status = this.mutationResult.getStatus();
        final MutationDetails mutationDetails = this.mutationResult.getDetails();
        final ClassName className = mutationDetails.getClassName();
        final String mutatorId = Commons.sanitizeMutatorName(mutationDetails.getMutator());
        final int lineNumber = mutationDetails.getLineNumber();
        final String desc = mutationDetails.getDescription();
        return status == that.mutationResult.getStatus() && Objects.equals(className, that.mutationResult.getDetails().getClassName()) && Objects.equals(mutatorId, Commons.sanitizeMutatorName(that.mutationResult.getDetails().getMutator())) && lineNumber == that.mutationResult.getDetails().getLineNumber() && Objects.equals(desc, that.mutationResult.getDetails().getDescription());
    }
    
    @Override
    public int hashCode() {
        final DetectionStatus status = this.mutationResult.getStatus();
        final MutationDetails mutationDetails = this.mutationResult.getDetails();
        final ClassName className = mutationDetails.getClassName();
        final String mutatorId = Commons.sanitizeMutatorName(mutationDetails.getMutator());
        final int lineNumber = mutationDetails.getLineNumber();
        final String desc = mutationDetails.getDescription();
        return Objects.hash(status, className, mutatorId, lineNumber, desc);
    }
}
