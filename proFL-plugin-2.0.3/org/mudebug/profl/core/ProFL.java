// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.profl.core;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Collection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProFL implements MutationVisitor
{
    private final Set<String> allFailingTests;
    private final List<Mutation> mutations;
    private Mutation lastVisitedMutation;
    
    public ProFL(final String failingTestsListFileName) throws Exception {
        this.mutations = new ArrayList<Mutation>();
        this.allFailingTests = new HashSet<String>();
        try (final Reader fr = new FileReader(new File(failingTestsListFileName));
             final BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                line = line.replace(':', '.');
                line = line.replace("..", ".");
                this.allFailingTests.add(line);
            }
        }
    }
    
    public ProFL(final Collection<String> failingTests) {
        this.mutations = new ArrayList<Mutation>();
        (this.allFailingTests = new HashSet<String>()).addAll(failingTests);
    }
    
    public static void compute(final String compressedXMLFileName, final Collection<String> failingTests, final String outputPath) {
        try {
            final ProFL visitor = new ProFL(failingTests);
            final PraPRXMLReportReader reader = new PraPRXMLReportReader(compressedXMLFileName);
            reader.start(visitor);
            reader.close();
            for (final Mutation mutation : visitor.mutations) {
                mutation.classify(visitor.allFailingTests);
            }
            final List<Tuple> proflList = AggregatorJDK7.aggregate(visitor.mutations, true);
            final List<Tuple> sbflList = AggregatorJDK7.aggregate(visitor.mutations, false);
            dump(outputPath + File.separator + "profl.log", proflList);
            dump(outputPath + File.separator + "sbfl.log", sbflList);
        }
        catch (Exception ex) {}
    }
    
    protected static void dump(final String outFileName, final List<Tuple> proflList) throws FileNotFoundException {
        final OutputStream fos = new FileOutputStream(outFileName);
        final OutputStream bos = new BufferedOutputStream(fos);
        final PrintWriter pw = new PrintWriter(bos);
        for (final Tuple meth : proflList) {
            pw.println(meth.toString());
        }
        pw.flush();
        pw.close();
    }
    
    private static String sanitizeTestName(final String testName) {
        final int indexOfOpenBracket = testName.indexOf(40);
        if (indexOfOpenBracket == -1) {
            return testName;
        }
        return testName.trim().substring(0, indexOfOpenBracket);
    }
    
    @Override
    public void visitMutation(final String status, final String sourceFileName, final String mutatedClassJavaName, final String mutatedMethodName, final String mutatedMethodDesc, final int lineNumber, final String mutatorName, final int index, final String[] coveringTests, final String[] killingTests, final double suspiciousnessValue, final String mutationDescription) {
        for (int i = 0; i < coveringTests.length; ++i) {
            coveringTests[i] = sanitizeTestName(coveringTests[i]);
        }
        for (int i = 0; i < killingTests.length; ++i) {
            killingTests[i] = sanitizeTestName(killingTests[i]);
        }
        this.lastVisitedMutation = new Mutation(status, sourceFileName, mutatedClassJavaName, mutatedMethodName, mutatedMethodDesc, lineNumber, mutatorName, index, coveringTests, killingTests, suspiciousnessValue, mutationDescription);
        this.mutations.add(this.lastVisitedMutation);
    }
    
    @Override
    public void visitFailureDescription(final String firstLine, final String stackTrace) {
        if (this.lastVisitedMutation == null) {
            throw new RuntimeException("malformed XML");
        }
        this.lastVisitedMutation.addFailureDescription(new FailureDescription(firstLine, stackTrace));
    }
}
