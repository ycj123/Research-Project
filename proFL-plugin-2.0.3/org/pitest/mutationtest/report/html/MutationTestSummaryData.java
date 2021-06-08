// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import java.util.Iterator;
import org.pitest.functional.F2;
import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.pitest.coverage.TestInfo;
import java.util.ArrayList;
import java.util.HashSet;
import org.pitest.classinfo.ClassInfo;
import org.pitest.mutationtest.MutationResult;
import java.util.Collection;
import java.util.Set;

public class MutationTestSummaryData
{
    private final String fileName;
    private final Set<String> mutators;
    private final Collection<MutationResult> mutations;
    private final Set<ClassInfo> classes;
    private long numberOfCoveredLines;
    
    public MutationTestSummaryData(final String fileName, final Collection<MutationResult> results, final Collection<String> mutators, final Collection<ClassInfo> classes, final long numberOfCoveredLines) {
        this.mutators = new HashSet<String>();
        this.mutations = new ArrayList<MutationResult>();
        this.classes = new HashSet<ClassInfo>();
        this.fileName = fileName;
        this.mutations.addAll(results);
        this.mutators.addAll(mutators);
        this.classes.addAll(classes);
        this.numberOfCoveredLines = numberOfCoveredLines;
    }
    
    public MutationTotals getTotals() {
        final MutationTotals mt = new MutationTotals();
        mt.addFiles(1L);
        mt.addMutations(this.getNumberOfMutations());
        mt.addMutationsDetetcted(this.getNumberOfMutationsDetected());
        mt.addLines(this.getNumberOfLines());
        mt.addLinesCovered(this.numberOfCoveredLines);
        return mt;
    }
    
    public String getPackageName() {
        final String packageName = this.getMutatedClasses().iterator().next().getName().asJavaName();
        final int lastDot = packageName.lastIndexOf(46);
        return (lastDot > 0) ? packageName.substring(0, lastDot) : "default";
    }
    
    public void add(final MutationTestSummaryData data) {
        this.mutations.addAll(data.mutations);
        this.mutators.addAll(data.getMutators());
        final int classesBefore = this.classes.size();
        this.classes.addAll(data.classes);
        if (classesBefore < this.classes.size()) {
            this.numberOfCoveredLines += data.numberOfCoveredLines;
        }
    }
    
    public Collection<TestInfo> getTests() {
        final Set<TestInfo> uniqueTests = new HashSet<TestInfo>();
        FCollection.flatMapTo((Iterable<? extends MutationResult>)this.mutations, (F<MutationResult, ? extends Iterable<Object>>)this.mutationToTargettedTests(), (Collection<? super Object>)uniqueTests);
        return uniqueTests;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public Collection<ClassInfo> getMutatedClasses() {
        return this.classes;
    }
    
    public Set<String> getMutators() {
        return this.mutators;
    }
    
    public MutationResultList getResults() {
        return new MutationResultList(this.mutations);
    }
    
    public Collection<ClassInfo> getClasses() {
        return this.classes;
    }
    
    private int getNumberOfLines() {
        return FCollection.fold(this.accumulateCodeLines(), 0, this.classes);
    }
    
    private F2<Integer, ClassInfo, Integer> accumulateCodeLines() {
        return new F2<Integer, ClassInfo, Integer>() {
            @Override
            public Integer apply(final Integer a, final ClassInfo b) {
                return a + b.getNumberOfCodeLines();
            }
        };
    }
    
    private long getNumberOfMutations() {
        return this.mutations.size();
    }
    
    private long getNumberOfMutationsDetected() {
        int count = 0;
        for (final MutationResult each : this.mutations) {
            if (each.getStatus().isDetected()) {
                ++count;
            }
        }
        return count;
    }
    
    private F<MutationResult, Iterable<TestInfo>> mutationToTargettedTests() {
        return new F<MutationResult, Iterable<TestInfo>>() {
            @Override
            public Iterable<TestInfo> apply(final MutationResult a) {
                return a.getDetails().getTestsInOrder();
            }
        };
    }
}
