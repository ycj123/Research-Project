// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.mutationtest;

import org.pitest.mutationtest.engine.MutationEngine;
import org.pitest.mutationtest.SourceLocator;
import org.pitest.coverage.CoverageDatabase;
import org.pitest.util.ResultOutputStrategy;
import org.pitest.classinfo.ClassByteArraySource;
import org.mudebug.prapr.core.SuspStrategy;
import java.util.Collection;
import org.pitest.mutationtest.ListenerArguments;

public class AugmentedListenerArguments extends ListenerArguments
{
    private final Collection<String> failingTests;
    private final int allTestsCount;
    private final SuspStrategy suspStrategy;
    private final ClassByteArraySource cbas;
    private final boolean dumpMutations;
    
    public AugmentedListenerArguments(final ResultOutputStrategy outputStrategy, final CoverageDatabase coverage, final SourceLocator locator, final MutationEngine engine, final ClassByteArraySource cbas, final long startTime, final SuspStrategy suspStrategy, final Collection<String> failingTests, final int allTestsCount, final boolean dumpMutations) {
        super(outputStrategy, coverage, locator, engine, startTime);
        this.failingTests = failingTests;
        this.allTestsCount = allTestsCount;
        this.suspStrategy = suspStrategy;
        this.cbas = cbas;
        this.dumpMutations = dumpMutations;
    }
    
    public Collection<String> getFailingTests() {
        return this.failingTests;
    }
    
    public int getAllTestsCount() {
        return this.allTestsCount;
    }
    
    public SuspStrategy getSuspStrategy() {
        return this.suspStrategy;
    }
    
    public ClassByteArraySource getClassByteArraySource() {
        return this.cbas;
    }
    
    public boolean shouldDumpMutations() {
        return this.dumpMutations;
    }
}
