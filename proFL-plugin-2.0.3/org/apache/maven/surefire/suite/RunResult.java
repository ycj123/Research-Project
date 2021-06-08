// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.suite;

import java.io.IOException;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.IOUtil;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.XMLWriter;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.Xpp3DomWriter;
import java.io.Writer;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.PrettyPrintXMLWriter;
import java.io.FileWriter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.Xpp3DomBuilder;
import java.io.InputStream;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.Xpp3Dom;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;

public class RunResult
{
    private final int completedCount;
    private final int errors;
    private final int failures;
    private final int skipped;
    private final String failure;
    private final boolean timeout;
    public static final int SUCCESS = 0;
    private static final int FAILURE = 255;
    private static final int NO_TESTS = 254;
    
    public static RunResult timeout(final RunResult accumulatedAtTimeout) {
        return errorCode(accumulatedAtTimeout, accumulatedAtTimeout.getFailure(), true);
    }
    
    public static RunResult failure(final RunResult accumulatedAtTimeout, final Exception cause) {
        return errorCode(accumulatedAtTimeout, getStackTrace(cause), accumulatedAtTimeout.isTimeout());
    }
    
    private static RunResult errorCode(final RunResult other, final String failure, final boolean timeout) {
        return new RunResult(other.getCompletedCount(), other.getErrors(), other.getFailures(), other.getSkipped(), failure, timeout);
    }
    
    public RunResult(final int completedCount, final int errors, final int failures, final int skipped) {
        this(completedCount, errors, failures, skipped, null, false);
    }
    
    public RunResult(final int completedCount, final int errors, final int failures, final int skipped, final String failure, final boolean timeout) {
        this.completedCount = completedCount;
        this.errors = errors;
        this.failures = failures;
        this.skipped = skipped;
        this.failure = failure;
        this.timeout = timeout;
    }
    
    private static String getStackTrace(final Exception e) {
        if (e == null) {
            return null;
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintWriter pw = new PrintWriter(out);
        e.printStackTrace(pw);
        return new String(out.toByteArray());
    }
    
    public int getCompletedCount() {
        return this.completedCount;
    }
    
    public int getErrors() {
        return this.errors;
    }
    
    public int getFailures() {
        return this.failures;
    }
    
    public int getSkipped() {
        return this.skipped;
    }
    
    public Integer getFailsafeCode() {
        if (this.completedCount == 0) {
            return 254;
        }
        if (!this.isErrorFree()) {
            return 255;
        }
        return null;
    }
    
    public boolean isErrorFree() {
        return this.getFailures() == 0 && this.getErrors() == 0;
    }
    
    public boolean isFailureOrTimeout() {
        return this.timeout || this.isFailure();
    }
    
    public boolean isFailure() {
        return this.failure != null;
    }
    
    public String getFailure() {
        return this.failure;
    }
    
    public boolean isTimeout() {
        return this.timeout;
    }
    
    public RunResult aggregate(final RunResult other) {
        final String failureMessage = (this.getFailure() != null) ? this.getFailure() : other.getFailure();
        final boolean timeout = this.isTimeout() || other.isTimeout();
        final int completed = this.getCompletedCount() + other.getCompletedCount();
        final int fail = this.getFailures() + other.getFailures();
        final int ign = this.getSkipped() + other.getSkipped();
        final int err = this.getErrors() + other.getErrors();
        return new RunResult(completed, err, fail, ign, failureMessage, timeout);
    }
    
    public static RunResult noTestsRun() {
        return new RunResult(0, 0, 0, 0);
    }
    
    private Xpp3Dom create(final String node, final String value) {
        final Xpp3Dom dom = new Xpp3Dom(node);
        dom.setValue(value);
        return dom;
    }
    
    private Xpp3Dom create(final String node, final int value) {
        return this.create(node, Integer.toString(value));
    }
    
    Xpp3Dom asXpp3Dom() {
        final Xpp3Dom dom = new Xpp3Dom("failsafe-summary");
        final Integer failsafeCode = this.getFailsafeCode();
        if (failsafeCode != null) {
            dom.setAttribute("result", Integer.toString(failsafeCode));
        }
        dom.setAttribute("timeout", Boolean.toString(this.timeout));
        dom.addChild(this.create("completed", this.completedCount));
        dom.addChild(this.create("errors", this.errors));
        dom.addChild(this.create("failures", this.failures));
        dom.addChild(this.create("skipped", this.skipped));
        dom.addChild(this.create("failureMessage", this.failure));
        return dom;
    }
    
    public static RunResult fromInputStream(final InputStream inputStream, final String encoding) throws FileNotFoundException {
        final Xpp3Dom dom = Xpp3DomBuilder.build(inputStream, encoding);
        final boolean timeout = Boolean.parseBoolean(dom.getAttribute("timeout"));
        final int completed = Integer.parseInt(dom.getChild("completed").getValue());
        final int errors = Integer.parseInt(dom.getChild("errors").getValue());
        final int failures = Integer.parseInt(dom.getChild("failures").getValue());
        final int skipped = Integer.parseInt(dom.getChild("skipped").getValue());
        final String failureMessage1 = dom.getChild("failureMessage").getValue();
        final String failureMessage2 = StringUtils.isEmpty(failureMessage1) ? null : failureMessage1;
        return new RunResult(completed, errors, failures, skipped, failureMessage2, timeout);
    }
    
    public void writeSummary(final File summaryFile, final boolean inProgress, final String encoding) throws IOException {
        if (!summaryFile.getParentFile().isDirectory()) {
            summaryFile.getParentFile().mkdirs();
        }
        FileInputStream fin = null;
        FileWriter writer = null;
        try {
            RunResult mergedSummary = this;
            if (summaryFile.exists() && inProgress) {
                fin = new FileInputStream(summaryFile);
                final RunResult runResult = fromInputStream(new BufferedInputStream(fin), encoding);
                mergedSummary = mergedSummary.aggregate(runResult);
            }
            writer = new FileWriter(summaryFile);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            final PrettyPrintXMLWriter prettyPrintXMLWriter = new PrettyPrintXMLWriter(writer);
            Xpp3DomWriter.write(prettyPrintXMLWriter, mergedSummary.asXpp3Dom());
        }
        finally {
            IOUtil.close(fin);
            IOUtil.close(writer);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final RunResult runResult = (RunResult)o;
        if (this.completedCount != runResult.completedCount) {
            return false;
        }
        if (this.errors != runResult.errors) {
            return false;
        }
        if (this.failures != runResult.failures) {
            return false;
        }
        if (this.skipped != runResult.skipped) {
            return false;
        }
        if (this.timeout != runResult.timeout) {
            return false;
        }
        if (this.failure != null) {
            if (this.failure.equals(runResult.failure)) {
                return true;
            }
        }
        else if (runResult.failure == null) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int result = this.completedCount;
        result = 31 * result + this.errors;
        result = 31 * result + this.failures;
        result = 31 * result + this.skipped;
        result = 31 * result + ((this.failure != null) ? this.failure.hashCode() : 0);
        result = 31 * result + (this.timeout ? 1 : 0);
        return result;
    }
}
