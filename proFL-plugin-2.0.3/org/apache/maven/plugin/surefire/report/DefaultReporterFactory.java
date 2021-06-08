// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import org.apache.maven.surefire.report.DefaultDirectConsoleReporter;
import java.util.Iterator;
import org.apache.maven.surefire.suite.RunResult;
import org.apache.maven.surefire.report.RunListener;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.surefire.runorder.StatisticsReporter;
import org.apache.maven.plugin.surefire.StartupReportConfiguration;
import org.apache.maven.surefire.report.RunStatistics;
import org.apache.maven.surefire.report.ReporterFactory;

public class DefaultReporterFactory implements ReporterFactory
{
    private final RunStatistics globalStats;
    private final StartupReportConfiguration reportConfiguration;
    private final StatisticsReporter statisticsReporter;
    private final List<TestSetRunListener> listeners;
    
    public DefaultReporterFactory(final StartupReportConfiguration reportConfiguration) {
        this.globalStats = new RunStatistics();
        this.listeners = Collections.synchronizedList(new ArrayList<TestSetRunListener>());
        this.reportConfiguration = reportConfiguration;
        this.statisticsReporter = reportConfiguration.instantiateStatisticsReporter();
        this.runStarting();
    }
    
    public RunListener createReporter() {
        return this.createTestSetRunListener();
    }
    
    public RunListener createTestSetRunListener() {
        final TestSetRunListener testSetRunListener = new TestSetRunListener(this.reportConfiguration.instantiateConsoleReporter(), this.reportConfiguration.instantiateFileReporter(), this.reportConfiguration.instantiateStatelessXmlReporter(), this.reportConfiguration.instantiateConsoleOutputFileReporter(), this.statisticsReporter, this.globalStats, this.reportConfiguration.isTrimStackTrace(), "plain".equals(this.reportConfiguration.getReportFormat()), this.reportConfiguration.isBriefOrPlainFormat());
        this.listeners.add(testSetRunListener);
        return testSetRunListener;
    }
    
    public RunResult close() {
        this.runCompleted();
        for (final TestSetRunListener listener : this.listeners) {
            listener.close();
        }
        return this.globalStats.getRunResult();
    }
    
    private DefaultDirectConsoleReporter createConsoleLogger() {
        return new DefaultDirectConsoleReporter(this.reportConfiguration.getOriginalSystemOut());
    }
    
    public void runStarting() {
        final DefaultDirectConsoleReporter consoleReporter = this.createConsoleLogger();
        consoleReporter.info("");
        consoleReporter.info("-------------------------------------------------------");
        consoleReporter.info(" T E S T S");
        consoleReporter.info("-------------------------------------------------------");
    }
    
    private void runCompleted() {
        final DefaultDirectConsoleReporter logger = this.createConsoleLogger();
        if (this.reportConfiguration.isPrintSummary()) {
            logger.info("");
            logger.info("Results :");
            logger.info("");
        }
        if (this.globalStats.hadFailures()) {
            logger.info("Failed tests: ");
            for (final Object o : this.globalStats.getFailureSources()) {
                logger.info("  " + o);
            }
            logger.info("");
        }
        if (this.globalStats.hadErrors()) {
            logger.info("Tests in error: ");
            for (final Object o : this.globalStats.getErrorSources()) {
                logger.info("  " + o);
            }
            logger.info("");
        }
        logger.info(this.globalStats.getSummary());
        logger.info("");
    }
    
    public RunStatistics getGlobalRunStatistics() {
        return this.globalStats;
    }
    
    public static DefaultReporterFactory defaultNoXml() {
        return new DefaultReporterFactory(StartupReportConfiguration.defaultNoXml());
    }
}
