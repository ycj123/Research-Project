// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient.output;

import org.apache.maven.surefire.report.ConsoleLogger;
import org.apache.maven.surefire.report.ConsoleOutputReceiver;
import org.apache.maven.surefire.report.CategorizedReportEntry;
import org.apache.maven.surefire.report.ReportEntry;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import org.apache.maven.surefire.report.ReporterException;
import java.util.StringTokenizer;
import org.apache.maven.surefire.util.internal.StringUtils;
import java.util.Collections;
import java.util.HashMap;
import org.apache.maven.surefire.report.StackTraceWriter;
import java.util.Properties;
import org.apache.maven.surefire.report.RunListener;
import java.util.Map;
import org.apache.maven.plugin.surefire.booterclient.lazytestprovider.TestProvidingInputStream;
import org.apache.maven.plugin.surefire.report.DefaultReporterFactory;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.cli.StreamConsumer;

public class ForkClient implements StreamConsumer
{
    private final DefaultReporterFactory defaultReporterFactory;
    private final TestProvidingInputStream testProvidingInputStream;
    private final Map<Integer, RunListener> testSetReporters;
    private final Properties testVmSystemProperties;
    private volatile boolean saidGoodBye;
    private volatile StackTraceWriter errorInFork;
    
    public ForkClient(final DefaultReporterFactory defaultReporterFactory, final Properties testVmSystemProperties) {
        this(defaultReporterFactory, testVmSystemProperties, null);
    }
    
    public ForkClient(final DefaultReporterFactory defaultReporterFactory, final Properties testVmSystemProperties, final TestProvidingInputStream testProvidingInputStream) {
        this.testSetReporters = Collections.synchronizedMap(new HashMap<Integer, RunListener>());
        this.saidGoodBye = false;
        this.errorInFork = null;
        this.defaultReporterFactory = defaultReporterFactory;
        this.testVmSystemProperties = testVmSystemProperties;
        this.testProvidingInputStream = testProvidingInputStream;
    }
    
    public void consumeLine(final String s) {
        try {
            if (s.length() == 0) {
                return;
            }
            final byte operationId = (byte)s.charAt(0);
            final int commma = s.indexOf(",", 3);
            if (commma < 0) {
                System.out.println(s);
                return;
            }
            final Integer channelNumber = Integer.parseInt(s.substring(2, commma), 16);
            final int rest = s.indexOf(",", commma);
            final String remaining = s.substring(rest + 1);
            switch (operationId) {
                case 49: {
                    this.getOrCreateReporter(channelNumber).testSetStarting(this.createReportEntry(remaining));
                    break;
                }
                case 50: {
                    this.getOrCreateReporter(channelNumber).testSetCompleted(this.createReportEntry(remaining));
                    break;
                }
                case 53: {
                    this.getOrCreateReporter(channelNumber).testStarting(this.createReportEntry(remaining));
                    break;
                }
                case 54: {
                    this.getOrCreateReporter(channelNumber).testSucceeded(this.createReportEntry(remaining));
                    break;
                }
                case 56: {
                    this.getOrCreateReporter(channelNumber).testFailed(this.createReportEntry(remaining));
                    break;
                }
                case 57: {
                    this.getOrCreateReporter(channelNumber).testSkipped(this.createReportEntry(remaining));
                    break;
                }
                case 55: {
                    this.getOrCreateReporter(channelNumber).testError(this.createReportEntry(remaining));
                    break;
                }
                case 71: {
                    this.getOrCreateReporter(channelNumber).testAssumptionFailure(this.createReportEntry(remaining));
                    break;
                }
                case 73: {
                    final int keyEnd = remaining.indexOf(",");
                    final StringBuilder key = new StringBuilder();
                    final StringBuilder value = new StringBuilder();
                    StringUtils.unescapeString(key, remaining.substring(0, keyEnd));
                    StringUtils.unescapeString(value, remaining.substring(keyEnd + 1));
                    synchronized (this.testVmSystemProperties) {
                        this.testVmSystemProperties.put(key.toString(), value.toString());
                    }
                    break;
                }
                case 51: {
                    final byte[] bytes = new byte[remaining.length()];
                    final int len = StringUtils.unescapeBytes(bytes, remaining);
                    this.getOrCreateConsoleOutputReceiver(channelNumber).writeTestOutput(bytes, 0, len, true);
                    break;
                }
                case 52: {
                    final byte[] bytes = new byte[remaining.length()];
                    final int len = StringUtils.unescapeBytes(bytes, remaining);
                    this.getOrCreateConsoleOutputReceiver(channelNumber).writeTestOutput(bytes, 0, len, false);
                    break;
                }
                case 72: {
                    this.getOrCreateConsoleLogger(channelNumber).info(this.createConsoleMessage(remaining));
                    break;
                }
                case 78: {
                    if (null != this.testProvidingInputStream) {
                        this.testProvidingInputStream.provideNewTest();
                        break;
                    }
                    break;
                }
                case 88: {
                    this.errorInFork = this.deserializeStackStraceWriter(new StringTokenizer(remaining, ","));
                    break;
                }
                case 90: {
                    this.saidGoodBye = true;
                    break;
                }
                default: {
                    System.out.println(s);
                    break;
                }
            }
        }
        catch (NumberFormatException e2) {
            System.out.println(s);
        }
        catch (ReporterException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void consumeMultiLineContent(final String s) throws IOException {
        final BufferedReader stringReader = new BufferedReader(new StringReader(s));
        String s2;
        while ((s2 = stringReader.readLine()) != null) {
            this.consumeLine(s2);
        }
    }
    
    private String createConsoleMessage(final String remaining) {
        return this.unescape(remaining);
    }
    
    private ReportEntry createReportEntry(final String untokenized) {
        final StringTokenizer tokens = new StringTokenizer(untokenized, ",");
        try {
            final String source = this.nullableCsv(tokens.nextToken());
            final String name = this.nullableCsv(tokens.nextToken());
            final String group = this.nullableCsv(tokens.nextToken());
            final String message = this.nullableCsv(tokens.nextToken());
            final String elapsedStr = tokens.nextToken();
            final Integer elapsed = "null".equals(elapsedStr) ? null : Integer.decode(elapsedStr);
            final StackTraceWriter stackTraceWriter = tokens.hasMoreTokens() ? this.deserializeStackStraceWriter(tokens) : null;
            return CategorizedReportEntry.reportEntry(source, name, group, stackTraceWriter, elapsed, message);
        }
        catch (RuntimeException e) {
            throw new RuntimeException(untokenized, e);
        }
    }
    
    private StackTraceWriter deserializeStackStraceWriter(final StringTokenizer tokens) {
        final String stackTraceMessage = this.nullableCsv(tokens.nextToken());
        final String smartStackTrace = this.nullableCsv(tokens.nextToken());
        final String stackTrace = tokens.hasMoreTokens() ? this.nullableCsv(tokens.nextToken()) : null;
        final StackTraceWriter stackTraceWriter = (stackTrace != null) ? new DeserializedStacktraceWriter(stackTraceMessage, smartStackTrace, stackTrace) : null;
        return stackTraceWriter;
    }
    
    private String nullableCsv(final String source) {
        if ("null".equals(source)) {
            return null;
        }
        return this.unescape(source);
    }
    
    private String unescape(final String source) {
        final StringBuilder stringBuffer = new StringBuilder(source.length());
        StringUtils.unescapeString(stringBuffer, source);
        return stringBuffer.toString();
    }
    
    public RunListener getReporter(final Integer channelNumber) {
        return this.testSetReporters.get(channelNumber);
    }
    
    private RunListener getOrCreateReporter(final Integer channelNumber) {
        RunListener reporter = this.testSetReporters.get(channelNumber);
        if (reporter == null) {
            reporter = this.defaultReporterFactory.createReporter();
            this.testSetReporters.put(channelNumber, reporter);
        }
        return reporter;
    }
    
    private ConsoleOutputReceiver getOrCreateConsoleOutputReceiver(final Integer channelNumber) {
        return (ConsoleOutputReceiver)this.getOrCreateReporter(channelNumber);
    }
    
    private ConsoleLogger getOrCreateConsoleLogger(final Integer channelNumber) {
        return (ConsoleLogger)this.getOrCreateReporter(channelNumber);
    }
    
    public void close(final boolean hadTimeout) {
    }
    
    public boolean isSaidGoodBye() {
        return this.saidGoodBye;
    }
    
    public StackTraceWriter getErrorInFork() {
        return this.errorInFork;
    }
    
    public boolean isErrorInFork() {
        return this.errorInFork != null;
    }
}
