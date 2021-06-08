// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.profl.core;

import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Mutation
{
    private final String status;
    private final String sourceFileName;
    private final String mutatedClassJavaName;
    private final String mutatedMethodName;
    private final String mutatedMethodDesc;
    private final int lineNumber;
    private final String mutatorName;
    private final int index;
    private final String[] coveringTests;
    private final String[] killingTests;
    private final double suspiciousnessValue;
    private final String mutationDescriptio;
    private final List<FailureDescription> failures;
    private PatchCategory category;
    
    public Mutation(final String status, final String sourceFileName, final String mutatedClassJavaName, final String mutatedMethodName, final String mutatedMethodDesc, final int lineNumber, final String mutatorName, final int index, final String[] coveringTests, final String[] killingTests, final double suspiciousnessValue, final String mutationDescriptio) {
        this.status = status;
        this.sourceFileName = sourceFileName;
        this.mutatedClassJavaName = mutatedClassJavaName;
        this.mutatedMethodName = mutatedMethodName;
        this.mutatedMethodDesc = mutatedMethodDesc;
        this.lineNumber = lineNumber;
        this.mutatorName = mutatorName;
        this.index = index;
        this.coveringTests = coveringTests;
        this.killingTests = killingTests;
        this.suspiciousnessValue = suspiciousnessValue;
        this.mutationDescriptio = mutationDescriptio;
        this.failures = new LinkedList<FailureDescription>();
    }
    
    public void addFailureDescription(final FailureDescription failureDescription) {
        this.failures.add(failureDescription);
    }
    
    public void classify(final Collection<String> failingTests) {
        if (this.isCleanFix(failingTests, Arrays.asList(this.killingTests))) {
            this.category = PatchCategory.CLEAN_FIX;
        }
        else if (this.isNoisyFix(failingTests, Arrays.asList(this.killingTests))) {
            this.category = PatchCategory.NOISY_FIX;
        }
        else if (this.isNegFix(failingTests, Arrays.asList(this.killingTests))) {
            this.category = PatchCategory.NEG_FIX;
        }
        else {
            this.category = PatchCategory.NONE_FIX;
        }
    }
    
    private static boolean contains(final String[] arr, final String e) {
        for (final String a : arr) {
            if (a.equals(e)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isCleanFix(final Collection<String> originallyFailingTests, final Collection<String> killingTests) {
        boolean ok1 = false;
        for (final String ft : originallyFailingTests) {
            if (contains(this.coveringTests, ft) && !killingTests.contains(ft)) {
                ok1 = true;
            }
        }
        boolean ok2 = true;
        for (final String ti : this.coveringTests) {
            if (!originallyFailingTests.contains(ti) && killingTests.contains(ti)) {
                ok2 = false;
            }
        }
        return ok1 && ok2;
    }
    
    private boolean isNoisyFix(final Collection<String> originallyFailingTests, final Collection<String> killingTests) {
        boolean ok1 = false;
        for (final String ft : originallyFailingTests) {
            if (contains(this.coveringTests, ft) && !killingTests.contains(ft)) {
                ok1 = true;
            }
        }
        boolean ok2 = false;
        for (final String ti : this.coveringTests) {
            if (!originallyFailingTests.contains(ti) && killingTests.contains(ti)) {
                ok2 = true;
            }
        }
        return ok1 & ok2;
    }
    
    private boolean isNegFix(final Collection<String> originallyFailingTests, final Collection<String> killingTests) {
        boolean ok1 = true;
        for (final String ft : originallyFailingTests) {
            if (contains(this.coveringTests, ft) && !killingTests.contains(ft)) {
                ok1 = false;
            }
        }
        boolean ok2 = false;
        for (final String ti : this.coveringTests) {
            if (!originallyFailingTests.contains(ti) && killingTests.contains(ti)) {
                ok2 = true;
            }
        }
        return ok1 & ok2;
    }
    
    public String getMethodId() {
        return this.mutatedClassJavaName + "." + this.mutatedMethodName + this.mutatedMethodDesc;
    }
    
    public double getSuspiciousnessValue() {
        return this.suspiciousnessValue;
    }
    
    public PatchCategory getCategorization() {
        return this.category;
    }
    
    @Override
    public String toString() {
        return "Mutation{status='" + this.status + '\'' + ", sourceFileName='" + this.sourceFileName + '\'' + ", mutatedClassJavaName='" + this.mutatedClassJavaName + '\'' + ", mutatedMethodName='" + this.mutatedMethodName + '\'' + ", mutatedMethodDesc='" + this.mutatedMethodDesc + '\'' + ", lineNumber=" + this.lineNumber + ", mutatorName='" + this.mutatorName + '\'' + ", index=" + this.index + ", coveringTests=" + Arrays.toString(this.coveringTests) + ", killingTests=" + Arrays.toString(this.killingTests) + ", suspiciousnessValue=" + this.suspiciousnessValue + ", mutationDescription='" + this.mutationDescriptio + '\'' + ", failures=" + this.failures + ", category=" + this.category + '}';
    }
}
