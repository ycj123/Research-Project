// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.report.compressedxml;

import java.io.IOException;
import org.pitest.util.Unchecked;
import org.pitest.functional.Option;
import org.pitest.coverage.TestInfo;
import java.util.List;
import org.pitest.util.StringUtil;
import org.mudebug.prapr.entry.report.Commons;
import org.pitest.mutationtest.engine.MutationDetails;
import java.util.Iterator;
import org.pitest.mutationtest.MutationResult;
import org.pitest.mutationtest.ClassMutationResults;
import java.util.Collection;
import org.mudebug.prapr.core.SuspStrategy;
import java.io.Writer;
import org.pitest.mutationtest.MutationResultListener;

public class CompressedXMLReportListener implements MutationResultListener
{
    private final Writer out;
    private final SuspStrategy suspStrategy;
    private final Collection<String> failingTests;
    private final int allTestsCount;
    
    public CompressedXMLReportListener(final CompressedDirectoryResultOutputStrategy outputStrategy, final SuspStrategy suspStrategy, final Collection<String> failingTests, final int allTestsCount) {
        this(outputStrategy.createWriterForFile("mutations.xml.gz"), suspStrategy, failingTests, allTestsCount);
    }
    
    private CompressedXMLReportListener(final Writer out, final SuspStrategy suspStrategy, final Collection<String> failingTests, final int allTestsCount) {
        this.out = out;
        this.suspStrategy = suspStrategy;
        this.failingTests = failingTests;
        this.allTestsCount = allTestsCount;
    }
    
    private void writeResult(final ClassMutationResults metaData) {
        for (final MutationResult mutation : metaData.getMutations()) {
            this.writeMutationResultXML(mutation);
        }
    }
    
    private void writeMutationResultXML(final MutationResult result) {
        this.write(this.makeNode(this.makeMutationNode(result), this.makeMutationAttributes(result), Tag.mutation) + "\n");
    }
    
    private String makeMutationAttributes(final MutationResult result) {
        return "detected='" + result.getStatus().isDetected() + "' status='" + result.getStatus() + "' numberOfTestsRun='" + result.getNumberOfTestsRun() + "'";
    }
    
    private String makeMutationNode(final MutationResult mutation) {
        final MutationDetails details = mutation.getDetails();
        return this.makeNode(this.clean(details.getFilename()), Tag.sourceFile) + this.makeNode(this.clean(details.getClassName().asJavaName()), Tag.mutatedClass) + this.makeNode(this.clean(details.getMethod().name()), Tag.mutatedMethod) + this.makeNode(this.clean(details.getId().getLocation().getMethodDesc()), Tag.methodDescription) + this.makeNode("" + details.getLineNumber(), Tag.lineNumber) + this.makeNode(this.clean(details.getMutator()), Tag.mutator) + this.makeNode("" + details.getFirstIndex(), Tag.index) + this.makeNode("" + details.getBlock(), Tag.block) + this.makeNode(this.createCoveringTestsDesc(details.getTestsInOrder()), Tag.coveringTests) + this.makeNode(this.createAllKillingTestDesc(mutation.getStatusTestPair().getKillingTest()), Tag.killingTests) + this.makeNode(Double.toString(this.getSusp(details)), Tag.suspValue) + this.makeNode(this.clean(details.getDescription()), Tag.description);
    }
    
    private double getSusp(final MutationDetails details) {
        return Commons.calculateSusp(this.suspStrategy, details, this.failingTests, this.allTestsCount);
    }
    
    private String clean(final String value) {
        return StringUtil.escapeBasicHtmlChars(value);
    }
    
    private String makeNode(final String value, final String attributes, final Tag tag) {
        if (value != null) {
            return "<" + tag + " " + attributes + ">" + value + "</" + tag + ">";
        }
        return "<" + tag + attributes + "/>";
    }
    
    private String makeNode(final String value, final Tag tag) {
        if (value != null) {
            return "<" + tag + ">" + value + "</" + tag + ">";
        }
        return "<" + tag + "/>";
    }
    
    private String createCoveringTestsDesc(final List<TestInfo> tio) {
        if (!tio.isEmpty()) {
            final String temp = tio.toString();
            return this.clean(temp.substring(1, temp.length() - 1));
        }
        return null;
    }
    
    private String createAllKillingTestDesc(final Option<String> killingTest) {
        if (killingTest.hasSome()) {
            final String s = killingTest.value();
            return this.clean(s);
        }
        return null;
    }
    
    private void write(final String value) {
        try {
            this.out.write(value);
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
    
    @Override
    public void runStart() {
        this.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        this.write("<mutations>\n");
    }
    
    @Override
    public void handleMutationResult(final ClassMutationResults metaData) {
        this.writeResult(metaData);
    }
    
    @Override
    public void runEnd() {
        try {
            this.write("</mutations>\n");
            this.out.close();
        }
        catch (IOException e) {
            throw Unchecked.translateCheckedException(e);
        }
    }
}
