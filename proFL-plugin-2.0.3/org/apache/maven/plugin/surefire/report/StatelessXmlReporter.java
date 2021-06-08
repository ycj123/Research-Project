// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.report;

import java.io.UnsupportedEncodingException;
import java.io.FilterOutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.io.IOException;
import org.apache.maven.surefire.report.SafeThrowable;
import java.util.StringTokenizer;
import org.apache.maven.surefire.report.ReportEntry;
import java.io.OutputStream;
import org.apache.maven.surefire.report.ReporterException;
import java.util.Iterator;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.XMLWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.IOUtil;
import java.io.Writer;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml.PrettyPrintXMLWriter;
import java.io.File;
import java.nio.charset.Charset;

public class StatelessXmlReporter
{
    private static final String ENCODING = "UTF-8";
    private static final Charset ENCODING_CS;
    private final File reportsDirectory;
    private final String reportNameSuffix;
    private final boolean trimStackTrace;
    
    public StatelessXmlReporter(final File reportsDirectory, final String reportNameSuffix, final boolean trimStackTrace) {
        this.reportsDirectory = reportsDirectory;
        this.reportNameSuffix = reportNameSuffix;
        this.trimStackTrace = trimStackTrace;
    }
    
    public void testSetCompleted(final WrappedReportEntry testSetReportEntry, final TestSetStats testSetStats) throws ReporterException {
        final FileOutputStream outputStream = this.getOutputStream(testSetReportEntry);
        final OutputStreamWriter fw = this.getWriter(outputStream);
        try {
            final XMLWriter ppw = new PrettyPrintXMLWriter(fw);
            ppw.setEncoding("UTF-8");
            createTestSuiteElement(ppw, testSetReportEntry, testSetStats, this.reportNameSuffix);
            this.showProperties(ppw);
            for (final WrappedReportEntry entry : testSetStats.getReportEntries()) {
                if (ReportEntryType.success.equals(entry.getReportEntryType())) {
                    startTestElement(ppw, entry, this.reportNameSuffix);
                    ppw.endElement();
                }
                else {
                    this.getTestProblems(fw, ppw, entry, this.trimStackTrace, this.reportNameSuffix, outputStream);
                }
            }
            ppw.endElement();
        }
        finally {
            IOUtil.close(fw);
        }
    }
    
    private OutputStreamWriter getWriter(final FileOutputStream fos) {
        return new OutputStreamWriter(fos, StatelessXmlReporter.ENCODING_CS);
    }
    
    private FileOutputStream getOutputStream(final WrappedReportEntry testSetReportEntry) {
        final File reportFile = this.getReportFile(testSetReportEntry, this.reportsDirectory, this.reportNameSuffix);
        final File reportDir = reportFile.getParentFile();
        reportDir.mkdirs();
        try {
            return new FileOutputStream(reportFile);
        }
        catch (Exception e) {
            throw new ReporterException("When writing report", e);
        }
    }
    
    private File getReportFile(final ReportEntry report, final File reportsDirectory, final String reportNameSuffix) {
        File reportFile;
        if (reportNameSuffix != null && reportNameSuffix.length() > 0) {
            reportFile = new File(reportsDirectory, FileReporterUtils.stripIllegalFilenameChars("TEST-" + report.getName() + "-" + reportNameSuffix + ".xml"));
        }
        else {
            reportFile = new File(reportsDirectory, FileReporterUtils.stripIllegalFilenameChars("TEST-" + report.getName() + ".xml"));
        }
        return reportFile;
    }
    
    private static void startTestElement(final XMLWriter ppw, final WrappedReportEntry report, final String reportNameSuffix) {
        ppw.startElement("testcase");
        ppw.addAttribute("name", report.getReportName());
        if (report.getGroup() != null) {
            ppw.addAttribute("group", report.getGroup());
        }
        if (report.getSourceName() != null) {
            if (reportNameSuffix != null && reportNameSuffix.length() > 0) {
                ppw.addAttribute("classname", report.getSourceName() + "(" + reportNameSuffix + ")");
            }
            else {
                ppw.addAttribute("classname", report.getSourceName());
            }
        }
        ppw.addAttribute("time", report.elapsedTimeAsString());
    }
    
    private static void createTestSuiteElement(final XMLWriter ppw, final WrappedReportEntry report, final TestSetStats testSetStats, final String reportNameSuffix1) {
        ppw.startElement("testsuite");
        ppw.addAttribute("name", report.getReportName(reportNameSuffix1));
        if (report.getGroup() != null) {
            ppw.addAttribute("group", report.getGroup());
        }
        ppw.addAttribute("time", testSetStats.getElapsedForTestSet());
        ppw.addAttribute("tests", String.valueOf(testSetStats.getCompletedCount()));
        ppw.addAttribute("errors", String.valueOf(testSetStats.getErrors()));
        ppw.addAttribute("skipped", String.valueOf(testSetStats.getSkipped()));
        ppw.addAttribute("failures", String.valueOf(testSetStats.getFailures()));
    }
    
    private void getTestProblems(final OutputStreamWriter outputStreamWriter, final XMLWriter ppw, final WrappedReportEntry report, final boolean trimStackTrace, final String reportNameSuffix, final FileOutputStream fw) {
        startTestElement(ppw, report, reportNameSuffix);
        ppw.startElement(report.getReportEntryType().name());
        final String stackTrace = report.getStackTrace(trimStackTrace);
        if (report.getMessage() != null && report.getMessage().length() > 0) {
            ppw.addAttribute("message", extraEscape(report.getMessage(), true));
        }
        if (report.getStackTraceWriter() != null) {
            final SafeThrowable t = report.getStackTraceWriter().getThrowable();
            if (t != null) {
                if (t.getMessage() != null) {
                    ppw.addAttribute("type", stackTrace.contains(":") ? stackTrace.substring(0, stackTrace.indexOf(":")) : stackTrace);
                }
                else {
                    ppw.addAttribute("type", new StringTokenizer(stackTrace).nextToken());
                }
            }
        }
        if (stackTrace != null) {
            ppw.writeText(extraEscape(stackTrace, false));
        }
        ppw.endElement();
        final EncodingOutputStream eos = new EncodingOutputStream(fw);
        this.addOutputStreamElement(outputStreamWriter, fw, eos, ppw, report.getStdout(), "system-out");
        this.addOutputStreamElement(outputStreamWriter, fw, eos, ppw, report.getStdErr(), "system-err");
        ppw.endElement();
    }
    
    private void addOutputStreamElement(final OutputStreamWriter outputStreamWriter, final OutputStream fw, final EncodingOutputStream eos, final XMLWriter xmlWriter, final Utf8RecodingDeferredFileOutputStream utf8RecodingDeferredFileOutputStream, final String name) {
        if (utf8RecodingDeferredFileOutputStream != null && utf8RecodingDeferredFileOutputStream.getByteCount() > 0L) {
            xmlWriter.startElement(name);
            try {
                xmlWriter.writeText("");
                outputStreamWriter.flush();
                utf8RecodingDeferredFileOutputStream.close();
                eos.getUnderlying().write(ByteConstantsHolder.CDATA_START_BYTES);
                utf8RecodingDeferredFileOutputStream.writeTo(eos);
                eos.getUnderlying().write(ByteConstantsHolder.CDATA_END_BYTES);
                eos.flush();
            }
            catch (IOException e) {
                throw new ReporterException("When writing xml report stdout/stderr", e);
            }
            xmlWriter.endElement();
        }
    }
    
    private void showProperties(final XMLWriter xmlWriter) {
        xmlWriter.startElement("properties");
        final Properties systemProperties = System.getProperties();
        if (systemProperties != null) {
            final Enumeration<?> propertyKeys = systemProperties.propertyNames();
            while (propertyKeys.hasMoreElements()) {
                final String key = (String)propertyKeys.nextElement();
                String value = systemProperties.getProperty(key);
                if (value == null) {
                    value = "null";
                }
                xmlWriter.startElement("property");
                xmlWriter.addAttribute("name", key);
                xmlWriter.addAttribute("value", extraEscape(value, true));
                xmlWriter.endElement();
            }
        }
        xmlWriter.endElement();
    }
    
    private static String extraEscape(final String message, final boolean attribute) {
        if (!containsEscapesIllegalnXml10(message)) {
            return message;
        }
        return escapeXml(message, attribute);
    }
    
    private static boolean containsEscapesIllegalnXml10(final String message) {
        for (int size = message.length(), i = 0; i < size; ++i) {
            if (isIllegalEscape(message.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isIllegalEscape(final char c) {
        return isIllegalEscape((int)c);
    }
    
    private static boolean isIllegalEscape(final int c) {
        return c >= 0 && c < 32 && c != 10 && c != 13 && c != 9;
    }
    
    private static String escapeXml(final String text, final boolean attribute) {
        final StringBuilder sb = new StringBuilder(text.length() * 2);
        for (int i = 0; i < text.length(); ++i) {
            final char c = text.charAt(i);
            if (isIllegalEscape(c)) {
                sb.append(attribute ? "&#" : "&amp#").append((int)c).append(';');
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    static {
        ENCODING_CS = Charset.forName("UTF-8");
    }
    
    private static class EncodingOutputStream extends FilterOutputStream
    {
        private int c1;
        private int c2;
        
        public EncodingOutputStream(final OutputStream out) {
            super(out);
        }
        
        public OutputStream getUnderlying() {
            return this.out;
        }
        
        private boolean isCdataEndBlock(final int c) {
            return this.c1 == 93 && this.c2 == 93 && c == 62;
        }
        
        @Override
        public void write(final int b) throws IOException {
            if (this.isCdataEndBlock(b)) {
                this.out.write(ByteConstantsHolder.CDATA_ESCAPE_STRING_BYTES);
            }
            else if (isIllegalEscape(b)) {
                this.out.write(ByteConstantsHolder.AMP_BYTES);
                this.out.write(String.valueOf(b).getBytes("UTF-8"));
                this.out.write(59);
            }
            else {
                this.out.write(b);
            }
            this.c1 = this.c2;
            this.c2 = b;
        }
    }
    
    private static class ByteConstantsHolder
    {
        private static final byte[] CDATA_START_BYTES;
        private static final byte[] CDATA_END_BYTES;
        private static final byte[] CDATA_ESCAPE_STRING_BYTES;
        private static final byte[] AMP_BYTES;
        
        static {
            try {
                CDATA_START_BYTES = "<![CDATA[".getBytes("UTF-8");
                CDATA_END_BYTES = "]]>".getBytes("UTF-8");
                CDATA_ESCAPE_STRING_BYTES = "]]><![CDATA[>".getBytes("UTF-8");
                AMP_BYTES = "&amp#".getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
