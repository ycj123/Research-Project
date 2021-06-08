// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugins.surefire.report;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;
import java.text.ParseException;
import org.xml.sax.Attributes;
import javax.xml.parsers.SAXParser;
import java.io.Reader;
import org.xml.sax.InputSource;
import java.util.HashMap;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.Collection;
import java.util.Locale;
import java.text.NumberFormat;
import java.util.Map;
import org.xml.sax.helpers.DefaultHandler;

public class TestSuiteXmlParser extends DefaultHandler
{
    private ReportTestSuite defaultSuite;
    private ReportTestSuite currentSuite;
    private Map<String, ReportTestSuite> classesToSuites;
    private final NumberFormat numberFormat;
    private StringBuffer currentElement;
    private ReportTestCase testCase;
    private boolean valid;
    
    public TestSuiteXmlParser() {
        this.numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
    }
    
    public Collection<ReportTestSuite> parse(final String xmlPath) throws ParserConfigurationException, SAXException, IOException {
        final File f = new File(xmlPath);
        final FileInputStream fileInputStream = new FileInputStream(f);
        final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        try {
            return this.parse(inputStreamReader);
        }
        finally {
            inputStreamReader.close();
            fileInputStream.close();
        }
    }
    
    public Collection<ReportTestSuite> parse(final InputStreamReader stream) throws ParserConfigurationException, SAXException, IOException {
        final SAXParserFactory factory = SAXParserFactory.newInstance();
        final SAXParser saxParser = factory.newSAXParser();
        this.valid = true;
        this.classesToSuites = new HashMap<String, ReportTestSuite>();
        saxParser.parse(new InputSource(stream), this);
        if (this.currentSuite != this.defaultSuite && this.defaultSuite.getNumberOfTests() == 0) {
            this.classesToSuites.remove(this.defaultSuite.getFullClassName());
        }
        return this.classesToSuites.values();
    }
    
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        if (!this.valid) {
            return;
        }
        try {
            if ("testsuite".equals(qName)) {
                final ReportTestSuite reportTestSuite = new ReportTestSuite();
                this.defaultSuite = reportTestSuite;
                this.currentSuite = reportTestSuite;
                try {
                    final Number time = this.numberFormat.parse(attributes.getValue("time"));
                    this.defaultSuite.setTimeElapsed(time.floatValue());
                }
                catch (NullPointerException npe) {
                    System.err.println("WARNING: no time attribute found on testsuite element");
                }
                if (attributes.getValue("group") != null && !"".equals(attributes.getValue("group"))) {
                    final String packageName = attributes.getValue("group");
                    final String name = attributes.getValue("name");
                    this.defaultSuite.setFullClassName(packageName + "." + name);
                }
                else {
                    final String fullClassName = attributes.getValue("name");
                    this.defaultSuite.setFullClassName(fullClassName);
                }
                this.classesToSuites.put(this.defaultSuite.getFullClassName(), this.defaultSuite);
            }
            else if ("testcase".equals(qName)) {
                this.currentElement = new StringBuffer();
                (this.testCase = new ReportTestCase()).setName(attributes.getValue("name"));
                final String fullClassName = attributes.getValue("classname");
                if (fullClassName != null) {
                    this.currentSuite = this.classesToSuites.get(fullClassName);
                    if (this.currentSuite == null) {
                        (this.currentSuite = new ReportTestSuite()).setFullClassName(fullClassName);
                        this.classesToSuites.put(fullClassName, this.currentSuite);
                    }
                }
                this.testCase.setFullClassName(this.currentSuite.getFullClassName());
                this.testCase.setClassName(this.currentSuite.getName());
                this.testCase.setFullName(this.currentSuite.getFullClassName() + "." + this.testCase.getName());
                final String timeAsString = attributes.getValue("time");
                Number time2 = 0;
                if (timeAsString != null) {
                    time2 = this.numberFormat.parse(timeAsString);
                }
                this.testCase.setTime(time2.floatValue());
                if (this.currentSuite != this.defaultSuite) {
                    this.currentSuite.setTimeElapsed(time2.floatValue() + this.currentSuite.getTimeElapsed());
                }
            }
            else if ("failure".equals(qName)) {
                this.testCase.addFailure(attributes.getValue("message"), attributes.getValue("type"));
                this.currentSuite.setNumberOfFailures(1 + this.currentSuite.getNumberOfFailures());
            }
            else if ("error".equals(qName)) {
                this.testCase.addFailure(attributes.getValue("message"), attributes.getValue("type"));
                this.currentSuite.setNumberOfErrors(1 + this.currentSuite.getNumberOfErrors());
            }
            else if ("skipped".equals(qName)) {
                final String message = attributes.getValue("message");
                this.testCase.addFailure((message != null) ? message : "skipped", "skipped");
                this.currentSuite.setNumberOfSkipped(1 + this.currentSuite.getNumberOfSkipped());
            }
            else if ("failsafe-summary".equals(qName)) {
                this.valid = false;
            }
        }
        catch (ParseException e) {
            throw new SAXException(e.getMessage(), e);
        }
    }
    
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if ("testcase".equals(qName)) {
            this.currentSuite.getTestCases().add(this.testCase);
        }
        else if ("failure".equals(qName)) {
            final Map<String, Object> failure = this.testCase.getFailure();
            failure.put("detail", this.parseCause(this.currentElement.toString()));
        }
        else if ("error".equals(qName)) {
            final Map<String, Object> error = this.testCase.getFailure();
            error.put("detail", this.parseCause(this.currentElement.toString()));
        }
        else if ("time".equals(qName)) {
            try {
                final Number time = this.numberFormat.parse(this.currentElement.toString());
                this.defaultSuite.setTimeElapsed(time.floatValue());
            }
            catch (ParseException e) {
                throw new SAXException(e.getMessage(), e);
            }
        }
    }
    
    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (!this.valid) {
            return;
        }
        final String s = new String(ch, start, length);
        if (!"".equals(s.trim())) {
            this.currentElement.append(s);
        }
    }
    
    private List<String> parseCause(final String detail) {
        final String fullName = this.testCase.getFullName();
        final String name = fullName.substring(fullName.lastIndexOf(".") + 1);
        return this.parseCause(detail, name);
    }
    
    private List<String> parseCause(final String detail, final String compareTo) {
        final StringTokenizer stringTokenizer = new StringTokenizer(detail, "\n");
        final List<String> parsedDetail = new ArrayList<String>(stringTokenizer.countTokens());
        while (stringTokenizer.hasMoreTokens()) {
            final String lineString = stringTokenizer.nextToken().trim();
            parsedDetail.add(lineString);
            if (lineString.contains(compareTo)) {
                break;
            }
        }
        return parsedDetail;
    }
    
    public boolean isValid() {
        return this.valid;
    }
}
