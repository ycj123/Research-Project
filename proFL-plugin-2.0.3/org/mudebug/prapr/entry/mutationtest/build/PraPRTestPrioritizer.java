// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.mutationtest.build;

import org.mudebug.prapr.core.commons.TestCaseUtil;
import org.pitest.coverage.TestInfo;
import java.util.List;
import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.coverage.CoverageDatabase;
import java.util.Collection;
import org.pitest.mutationtest.build.DefaultTestPrioritiser;

public class PraPRTestPrioritizer extends DefaultTestPrioritiser
{
    private final Collection<String> failingTests;
    
    public PraPRTestPrioritizer(final CoverageDatabase coverage, final Collection<String> failingTests) {
        super(coverage);
        this.failingTests = failingTests;
    }
    
    @Override
    public List<TestInfo> assignTests(final MutationDetails mutation) {
        final List<TestInfo> sortedTestCases = super.assignTests(mutation);
        return TestCaseUtil.reorder(sortedTestCases, this.failingTests);
    }
}
