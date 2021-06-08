// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.report;

import java.util.Iterator;
import org.mudebug.prapr.core.commons.TestCaseUtil;
import org.pitest.coverage.TestInfo;
import java.util.HashSet;
import java.util.Collection;
import org.pitest.mutationtest.engine.MutationDetails;
import org.mudebug.prapr.core.SuspStrategy;

public final class Commons
{
    private Commons() {
    }
    
    public static String sanitizeMutatorName(String mutator) {
        mutator = mutator.substring(1 + mutator.lastIndexOf(46));
        if (Character.isDigit(mutator.charAt(mutator.length() - 1))) {
            final int lastIndex = mutator.lastIndexOf(95);
            mutator = mutator.substring(0, lastIndex);
        }
        return mutator;
    }
    
    public static double calculateSusp(final SuspStrategy suspStrategy, final MutationDetails mutationDetails, Collection<String> failingTests, final int allTestsCount) {
        final int allFailingTestsCount = failingTests.size();
        failingTests = new HashSet<String>(failingTests);
        int ef = 0;
        int ep = 0;
        for (final TestInfo ti : mutationDetails.getTestsInOrder()) {
            if (TestCaseUtil.contains(failingTests, ti)) {
                ++ef;
                TestCaseUtil.remove(failingTests, ti);
            }
            else {
                ++ep;
            }
        }
        final int nf = failingTests.size();
        final int np = allTestsCount - allFailingTestsCount - ep;
        return suspStrategy.computeSusp(ef, ep, nf, np);
    }
}
