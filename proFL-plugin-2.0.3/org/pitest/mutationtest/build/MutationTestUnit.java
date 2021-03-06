// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.build;

import org.pitest.util.Log;
import org.pitest.util.ExitCode;
import org.pitest.mutationtest.execute.MutationTestProcess;
import java.io.IOException;
import org.pitest.mutationtest.DetectionStatus;
import org.pitest.mutationtest.MutationStatusMap;
import org.pitest.mutationtest.MutationMetaData;
import org.pitest.classinfo.ClassName;
import org.pitest.mutationtest.engine.MutationDetails;
import java.util.Collection;
import java.util.logging.Logger;

public class MutationTestUnit implements MutationAnalysisUnit
{
    private static final Logger LOG;
    private final Collection<MutationDetails> availableMutations;
    private final WorkerFactory workerFactory;
    private final Collection<ClassName> testClasses;
    
    public MutationTestUnit(final Collection<MutationDetails> availableMutations, final Collection<ClassName> testClasses, final WorkerFactory workerFactor) {
        this.availableMutations = availableMutations;
        this.testClasses = testClasses;
        this.workerFactory = workerFactor;
    }
    
    @Override
    public MutationMetaData call() throws Exception {
        final MutationStatusMap mutations = new MutationStatusMap();
        mutations.setStatusForMutations(this.availableMutations, DetectionStatus.NOT_STARTED);
        mutations.markUncoveredMutations();
        this.runTestsInSeperateProcess(mutations);
        return reportResults(mutations);
    }
    
    @Override
    public int priority() {
        return this.availableMutations.size();
    }
    
    private void runTestsInSeperateProcess(final MutationStatusMap mutations) throws IOException, InterruptedException {
        while (mutations.hasUnrunMutations()) {
            this.runTestInSeperateProcessForMutationRange(mutations);
        }
    }
    
    private void runTestInSeperateProcessForMutationRange(final MutationStatusMap mutations) throws IOException, InterruptedException {
        final Collection<MutationDetails> remainingMutations = mutations.getUnrunMutations();
        final MutationTestProcess worker = this.workerFactory.createWorker(remainingMutations, this.testClasses);
        worker.start();
        setFirstMutationToStatusOfStartedInCaseMinionFailsAtBoot(mutations, remainingMutations);
        final ExitCode exitCode = waitForMinionToDie(worker);
        worker.results(mutations);
        correctResultForProcessExitCode(mutations, exitCode);
    }
    
    private static ExitCode waitForMinionToDie(final MutationTestProcess worker) {
        final ExitCode exitCode = worker.waitToDie();
        MutationTestUnit.LOG.fine("Exit code was - " + exitCode);
        return exitCode;
    }
    
    private static void setFirstMutationToStatusOfStartedInCaseMinionFailsAtBoot(final MutationStatusMap mutations, final Collection<MutationDetails> remainingMutations) {
        mutations.setStatusForMutation(remainingMutations.iterator().next(), DetectionStatus.STARTED);
    }
    
    private static void correctResultForProcessExitCode(final MutationStatusMap mutations, final ExitCode exitCode) {
        if (!exitCode.isOk()) {
            final Collection<MutationDetails> unfinishedRuns = mutations.getUnfinishedRuns();
            final DetectionStatus status = DetectionStatus.getForErrorExitCode(exitCode);
            MutationTestUnit.LOG.warning("Minion exited abnormally due to " + status);
            MutationTestUnit.LOG.fine("Setting " + unfinishedRuns.size() + " unfinished runs to " + status + " state");
            mutations.setStatusForMutations(unfinishedRuns, status);
        }
        else {
            MutationTestUnit.LOG.fine("Minion exited ok");
        }
    }
    
    private static MutationMetaData reportResults(final MutationStatusMap mutationsMap) {
        return new MutationMetaData(mutationsMap.createMutationResults());
    }
    
    static {
        LOG = Log.getLogger();
    }
}
