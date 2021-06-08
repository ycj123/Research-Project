// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import org.apache.maven.plugin.surefire.runorder.StatisticsReporter;
import org.apache.maven.plugin.surefire.report.DirectConsoleOutput;
import org.apache.maven.plugin.surefire.report.ConsoleOutputFileReporter;
import org.apache.maven.plugin.surefire.report.TestcycleConsoleOutputReceiver;
import org.apache.maven.plugin.surefire.report.ConsoleReporter;
import org.apache.maven.plugin.surefire.report.FileReporter;
import org.apache.maven.plugin.surefire.report.StatelessXmlReporter;
import javax.annotation.Nonnull;
import java.util.Properties;
import java.io.File;
import java.io.PrintStream;

public class StartupReportConfiguration
{
    private final PrintStream originalSystemOut;
    private final PrintStream originalSystemErr;
    private final boolean useFile;
    private final boolean printSummary;
    private final String reportFormat;
    private final String reportNameSuffix;
    private final String configurationHash;
    private final boolean requiresRunHistory;
    private final boolean redirectTestOutputToFile;
    private final boolean disableXmlReport;
    private final File reportsDirectory;
    private final boolean trimStackTrace;
    private final Properties testVmSystemProperties;
    public static final String BRIEF_REPORT_FORMAT = "brief";
    public static final String PLAIN_REPORT_FORMAT = "plain";
    
    public StartupReportConfiguration(final boolean useFile, final boolean printSummary, final String reportFormat, final boolean redirectTestOutputToFile, final boolean disableXmlReport, @Nonnull final File reportsDirectory, final boolean trimStackTrace, final String reportNameSuffix, final String configurationHash, final boolean requiresRunHistory) {
        this.testVmSystemProperties = new Properties();
        this.useFile = useFile;
        this.printSummary = printSummary;
        this.reportFormat = reportFormat;
        this.redirectTestOutputToFile = redirectTestOutputToFile;
        this.disableXmlReport = disableXmlReport;
        this.reportsDirectory = reportsDirectory;
        this.trimStackTrace = trimStackTrace;
        this.reportNameSuffix = reportNameSuffix;
        this.configurationHash = configurationHash;
        this.requiresRunHistory = requiresRunHistory;
        this.originalSystemOut = System.out;
        this.originalSystemErr = System.err;
    }
    
    public static StartupReportConfiguration defaultValue() {
        final File target = new File("./target");
        return new StartupReportConfiguration(true, true, "PLAIN", false, false, target, false, null, "TESTHASH", false);
    }
    
    public static StartupReportConfiguration defaultNoXml() {
        final File target = new File("./target");
        return new StartupReportConfiguration(true, true, "PLAIN", false, true, target, false, null, "TESTHASHxXML", false);
    }
    
    public boolean isUseFile() {
        return this.useFile;
    }
    
    public boolean isPrintSummary() {
        return this.printSummary;
    }
    
    public String getReportFormat() {
        return this.reportFormat;
    }
    
    public String getReportNameSuffix() {
        return this.reportNameSuffix;
    }
    
    public boolean isRedirectTestOutputToFile() {
        return this.redirectTestOutputToFile;
    }
    
    public boolean isDisableXmlReport() {
        return this.disableXmlReport;
    }
    
    public File getReportsDirectory() {
        return this.reportsDirectory;
    }
    
    public StatelessXmlReporter instantiateStatelessXmlReporter() {
        if (!this.isDisableXmlReport()) {
            return new StatelessXmlReporter(this.reportsDirectory, this.reportNameSuffix, this.trimStackTrace);
        }
        return null;
    }
    
    public FileReporter instantiateFileReporter() {
        if (this.isUseFile() && this.isBriefOrPlainFormat()) {
            return new FileReporter(this.reportsDirectory, this.getReportNameSuffix());
        }
        return null;
    }
    
    public boolean isBriefOrPlainFormat() {
        final String fmt = this.getReportFormat();
        return "brief".equals(fmt) || "plain".equals(fmt);
    }
    
    public ConsoleReporter instantiateConsoleReporter() {
        return this.shouldReportToConsole() ? new ConsoleReporter(this.originalSystemOut) : null;
    }
    
    private boolean shouldReportToConsole() {
        return this.isUseFile() ? this.isPrintSummary() : (this.isRedirectTestOutputToFile() || this.isBriefOrPlainFormat());
    }
    
    public TestcycleConsoleOutputReceiver instantiateConsoleOutputFileReporter() {
        if (this.isRedirectTestOutputToFile()) {
            return new ConsoleOutputFileReporter(this.reportsDirectory, this.getReportNameSuffix());
        }
        return new DirectConsoleOutput(this.originalSystemOut, this.originalSystemErr);
    }
    
    public StatisticsReporter instantiateStatisticsReporter() {
        if (this.requiresRunHistory) {
            final File target = this.getStatisticsFile();
            return new StatisticsReporter(target);
        }
        return null;
    }
    
    public File getStatisticsFile() {
        return new File(this.reportsDirectory.getParentFile().getParentFile(), ".surefire-" + this.configurationHash);
    }
    
    public Properties getTestVmSystemProperties() {
        return this.testVmSystemProperties;
    }
    
    public boolean isTrimStackTrace() {
        return this.trimStackTrace;
    }
    
    public String getConfigurationHash() {
        return this.configurationHash;
    }
    
    public boolean isRequiresRunHistory() {
        return this.requiresRunHistory;
    }
    
    public PrintStream getOriginalSystemOut() {
        return this.originalSystemOut;
    }
}
