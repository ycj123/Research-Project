// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugins.surefire.report;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.DirectoryScanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.maven.reporting.MavenReportException;
import java.util.Locale;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.text.NumberFormat;

public class SurefireReportParser
{
    private static final String INCLUDES = "*.xml";
    private static final String EXCLUDES = "*.txt, testng-failed.xml, testng-failures.xml, testng-results.xml, failsafe-summary*.xml";
    private NumberFormat numberFormat;
    private List<File> reportsDirectories;
    private final List<ReportTestSuite> testSuites;
    private static final int PCENT = 100;
    
    public SurefireReportParser() {
        this.numberFormat = NumberFormat.getInstance();
        this.testSuites = new ArrayList<ReportTestSuite>();
    }
    
    public SurefireReportParser(final List<File> reportsDirectoriesFiles, final Locale locale) {
        this.numberFormat = NumberFormat.getInstance();
        this.testSuites = new ArrayList<ReportTestSuite>();
        this.reportsDirectories = reportsDirectoriesFiles;
        this.setLocale(locale);
    }
    
    public List<ReportTestSuite> parseXMLReportFiles() throws MavenReportException {
        final List<File> xmlReportFileList = new ArrayList<File>();
        for (final File reportsDirectory : this.reportsDirectories) {
            if (!reportsDirectory.exists()) {
                continue;
            }
            final String[] arr$;
            final String[] xmlReportFiles = arr$ = getIncludedFiles(reportsDirectory, "*.xml", "*.txt, testng-failed.xml, testng-failures.xml, testng-results.xml, failsafe-summary*.xml");
            for (final String xmlReportFile : arr$) {
                final File xmlReport = new File(reportsDirectory, xmlReportFile);
                xmlReportFileList.add(xmlReport);
            }
        }
        final TestSuiteXmlParser parser = new TestSuiteXmlParser();
        for (final File aXmlReportFileList : xmlReportFileList) {
            Collection<ReportTestSuite> suites;
            try {
                suites = parser.parse(aXmlReportFileList.getAbsolutePath());
            }
            catch (ParserConfigurationException e) {
                throw new MavenReportException("Error setting up parser for JUnit XML report", e);
            }
            catch (SAXException e2) {
                throw new MavenReportException("Error parsing JUnit XML report " + aXmlReportFileList, e2);
            }
            catch (IOException e3) {
                throw new MavenReportException("Error reading JUnit XML report " + aXmlReportFileList, e3);
            }
            this.testSuites.addAll(suites);
        }
        return this.testSuites;
    }
    
    protected String parseTestSuiteName(final String lineString) {
        return lineString.substring(lineString.lastIndexOf(".") + 1, lineString.length());
    }
    
    protected String parseTestSuitePackageName(final String lineString) {
        return lineString.substring(lineString.indexOf(":") + 2, lineString.lastIndexOf("."));
    }
    
    protected String parseTestCaseName(final String lineString) {
        return lineString.substring(0, lineString.indexOf("("));
    }
    
    public Map<String, String> getSummary(final List<ReportTestSuite> suites) {
        final Map<String, String> totalSummary = new HashMap<String, String>();
        int totalNumberOfTests = 0;
        int totalNumberOfErrors = 0;
        int totalNumberOfFailures = 0;
        int totalNumberOfSkipped = 0;
        float totalElapsedTime = 0.0f;
        for (final ReportTestSuite suite : suites) {
            totalNumberOfTests += suite.getNumberOfTests();
            totalNumberOfErrors += suite.getNumberOfErrors();
            totalNumberOfFailures += suite.getNumberOfFailures();
            totalNumberOfSkipped += suite.getNumberOfSkipped();
            totalElapsedTime += suite.getTimeElapsed();
        }
        final String totalPercentage = this.computePercentage(totalNumberOfTests, totalNumberOfErrors, totalNumberOfFailures, totalNumberOfSkipped);
        totalSummary.put("totalTests", Integer.toString(totalNumberOfTests));
        totalSummary.put("totalErrors", Integer.toString(totalNumberOfErrors));
        totalSummary.put("totalFailures", Integer.toString(totalNumberOfFailures));
        totalSummary.put("totalSkipped", Integer.toString(totalNumberOfSkipped));
        totalSummary.put("totalElapsedTime", this.numberFormat.format(totalElapsedTime));
        totalSummary.put("totalPercentage", totalPercentage);
        return totalSummary;
    }
    
    public void setReportsDirectory(final File reportsDirectory) {
        this.reportsDirectories = Collections.singletonList(reportsDirectory);
    }
    
    public final void setLocale(final Locale locale) {
        this.numberFormat = NumberFormat.getInstance(locale);
    }
    
    public NumberFormat getNumberFormat() {
        return this.numberFormat;
    }
    
    public Map<String, List<ReportTestSuite>> getSuitesGroupByPackage(final List<ReportTestSuite> testSuitesList) {
        final Map<String, List<ReportTestSuite>> suitePackage = new HashMap<String, List<ReportTestSuite>>();
        for (final ReportTestSuite suite : testSuitesList) {
            List<ReportTestSuite> suiteList = new ArrayList<ReportTestSuite>();
            if (suitePackage.get(suite.getPackageName()) != null) {
                suiteList = suitePackage.get(suite.getPackageName());
            }
            suiteList.add(suite);
            suitePackage.put(suite.getPackageName(), suiteList);
        }
        return suitePackage;
    }
    
    public String computePercentage(final int tests, final int errors, final int failures, final int skipped) {
        float percentage;
        if (tests == 0) {
            percentage = 0.0f;
        }
        else {
            percentage = (tests - errors - failures - skipped) / (float)tests * 100.0f;
        }
        return this.numberFormat.format(percentage);
    }
    
    public List<ReportTestCase> getFailureDetails(final List<ReportTestSuite> testSuitesList) {
        final List<ReportTestCase> failureDetailList = new ArrayList<ReportTestCase>();
        for (final ReportTestSuite suite : testSuitesList) {
            final List<ReportTestCase> testCaseList = suite.getTestCases();
            if (testCaseList != null) {
                for (final ReportTestCase tCase : testCaseList) {
                    if (tCase.getFailure() != null) {
                        failureDetailList.add(tCase);
                    }
                }
            }
        }
        return failureDetailList;
    }
    
    public static boolean hasReportFiles(final File directory) {
        return directory != null && directory.isDirectory() && getIncludedFiles(directory, "*.xml", "*.txt, testng-failed.xml, testng-failures.xml, testng-results.xml, failsafe-summary*.xml").length > 0;
    }
    
    private static String[] getIncludedFiles(final File directory, final String includes, final String excludes) {
        final DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(directory);
        scanner.setIncludes(StringUtils.split(includes, ","));
        scanner.setExcludes(StringUtils.split(excludes, ","));
        scanner.scan();
        return scanner.getIncludedFiles();
    }
}
