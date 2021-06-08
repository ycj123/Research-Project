// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.profl.core;

import org.mudebug.prapr.reloc.commons.text.StringEscapeUtils;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.BufferedReader;

public class PraPRXMLReportReader
{
    private final BufferedReader br;
    
    public PraPRXMLReportReader(final String xmlFileName) throws Exception {
        this(new File(xmlFileName));
    }
    
    public PraPRXMLReportReader(final File xmlFile) throws Exception {
        final InputStream gzis = new GZIPInputStream(new FileInputStream(xmlFile));
        (this.br = new BufferedReader(new InputStreamReader(gzis))).readLine();
        this.br.readLine();
    }
    
    private String getStatus(final String headerElement) {
        final String[] attribs = headerElement.split("\\s");
        final String statusAssignment = attribs[2];
        final int len = statusAssignment.length();
        return statusAssignment.substring(8, len - 1);
    }
    
    private String extractContents(final String element, final String startTag, final String closingTag) {
        final int indexOfClosingTag = element.indexOf(closingTag);
        return element.substring(startTag.length(), indexOfClosingTag);
    }
    
    private String getSourceFileName(final String sourceFileElement) {
        return this.extractContents(sourceFileElement, "<sourceFile>", "</sourceFile>");
    }
    
    private String getMutatedClassJavaName(final String mutatedClassElement) {
        return this.extractContents(mutatedClassElement, "<mutatedClass>", "</mutatedClass>");
    }
    
    private final String sanitizeXML(final String str) {
        return StringEscapeUtils.unescapeXml(str);
    }
    
    private String getMutatedMethodName(final String mutatedMethodNameElement) {
        final String rawXML = this.extractContents(mutatedMethodNameElement, "<mutatedMethod>", "</mutatedMethod>");
        return this.sanitizeXML(rawXML);
    }
    
    private String getMutatedMethodDescription(final String mutatedMethodDescElement) {
        return this.extractContents(mutatedMethodDescElement, "<methodDescription>", "</methodDescription>");
    }
    
    private int getLineNumber(final String lineNumberElement) {
        return Integer.parseInt(this.extractContents(lineNumberElement, "<lineNumber>", "</lineNumber>"));
    }
    
    private String getMutatorName(final String mutatorNameElement) {
        return this.extractContents(mutatorNameElement, "<mutator>", "</mutator>");
    }
    
    private int getIndex(final String indexElement) {
        return Integer.parseInt(this.extractContents(indexElement, "<index>", "</index>"));
    }
    
    private String[] getCoveringTests(final String coveringTestsElement) {
        return this.extractContents(coveringTestsElement, "<coveringTests>", "</coveringTests>").split(",\\s");
    }
    
    private String[] getKillingTests(final String killingTestsElement) {
        return this.extractContents(killingTestsElement, "<killingTests>", "</killingTests>").split(",\\s");
    }
    
    private double getSuspiciousnessValue(final String suspValueElement) {
        return Double.parseDouble(this.extractContents(suspValueElement, "<suspValue>", "</suspValue>"));
    }
    
    private String getMutationDescription(final String mutationDescElement) {
        final String rawXML = this.extractContents(mutationDescElement, "<description>", "</description>");
        return this.sanitizeXML(rawXML);
    }
    
    public void start(final MutationVisitor visitor) throws Exception {
        String line;
        while ((line = this.br.readLine()) != null) {
            if (line.startsWith("</mutations>")) {
                return;
            }
            final int indexOfSourceFileTag = line.indexOf("<sourceFile>");
            final String headerElement = line.substring(0, indexOfSourceFileTag);
            final String status = this.getStatus(headerElement);
            line = line.substring(indexOfSourceFileTag);
            final int indexOfMutatedClassTag = line.indexOf("<mutatedClass>");
            final String sourceFileElement = line.substring(0, indexOfMutatedClassTag);
            final String sourceFileName = this.getSourceFileName(sourceFileElement);
            line = line.substring(indexOfMutatedClassTag);
            final int indexOfMutatedMethodTag = line.indexOf("<mutatedMethod>");
            final String mutatedClassElement = line.substring(0, indexOfMutatedMethodTag);
            final String mutatedClassJavaName = this.getMutatedClassJavaName(mutatedClassElement);
            line = line.substring(indexOfMutatedMethodTag);
            final int indexOfMethodDescTag = line.indexOf("<methodDescription>");
            final String mutatedMethodNameElement = line.substring(0, indexOfMethodDescTag);
            final String mutatedMethodName = this.getMutatedMethodName(mutatedMethodNameElement);
            line = line.substring(indexOfMethodDescTag);
            final int indexOfLineNumberTag = line.indexOf("<lineNumber>");
            final String mutatedMethodDescElement = line.substring(0, indexOfLineNumberTag);
            final String mutatedMethodDescriptor = this.getMutatedMethodDescription(mutatedMethodDescElement);
            line = line.substring(indexOfLineNumberTag);
            final int indexOfMutatorNameTag = line.indexOf("<mutator>");
            final String lineNumberElement = line.substring(0, indexOfMutatorNameTag);
            final int lineNumber = this.getLineNumber(lineNumberElement);
            line = line.substring(indexOfMutatorNameTag);
            final int indexOfIndexTag = line.indexOf("<index>");
            final String mutatorNameElement = line.substring(0, indexOfIndexTag);
            final String mutatorName = this.getMutatorName(mutatorNameElement);
            line = line.substring(indexOfIndexTag);
            final int indexOfBlockTag = line.indexOf("<block>");
            final String indexElement = line.substring(0, indexOfBlockTag);
            final int index = this.getIndex(indexElement);
            final int indexOfCoveringTestsTag = line.indexOf("<coveringTests>");
            line = line.substring(indexOfCoveringTestsTag);
            int indexOfKillingTestsTag = line.indexOf("<killingTests>");
            if (indexOfKillingTestsTag < 0) {
                indexOfKillingTestsTag = line.indexOf("<killingTests/>");
            }
            final String coveringTestsElement = line.substring(0, indexOfKillingTestsTag);
            final String[] coveringTests = this.getCoveringTests(coveringTestsElement);
            line = line.substring(indexOfKillingTestsTag);
            final int indexOfKillingTestsTag2 = line.indexOf("<killingTests>");
            String[] killingTests;
            if (indexOfKillingTestsTag2 < 0) {
                killingTests = new String[0];
                final int indexOfSuspValueTag = line.indexOf("<suspValue>");
                line = line.substring(indexOfSuspValueTag);
            }
            else {
                final int indexOfSuspValueTag = line.indexOf("<suspValue>");
                final String killingTestsElement = line.substring(0, indexOfSuspValueTag);
                killingTests = this.getKillingTests(killingTestsElement);
                line = line.substring(indexOfSuspValueTag);
            }
            final int indexOfMutationDescTag = line.indexOf("<description>");
            final String suspValueElement = line.substring(0, indexOfMutationDescTag);
            final double suspiciousnessValue = this.getSuspiciousnessValue(suspValueElement);
            line = line.substring(indexOfMutationDescTag);
            final int indexOfClosingTag = line.indexOf("</mutation>");
            final String mutationDescriptionElement = line.substring(0, indexOfClosingTag);
            final String mutationDescription = this.getMutationDescription(mutationDescriptionElement);
            if (!status.equals("SURVIVED") && !status.equals("KILLED")) {
                continue;
            }
            visitor.visitMutation(status, sourceFileName, mutatedClassJavaName, mutatedMethodName, mutatedMethodDescriptor, lineNumber, mutatorName, index, coveringTests, killingTests, suspiciousnessValue, mutationDescription);
        }
    }
    
    public void close() throws Exception {
        this.br.close();
    }
}
