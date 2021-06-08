// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import org.apache.maven.surefire.report.SafeThrowable;
import org.apache.maven.surefire.report.StackTraceWriter;
import org.apache.maven.surefire.util.internal.StringUtils;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.maven.surefire.report.ReportEntry;
import java.io.PrintStream;
import org.apache.maven.surefire.report.ConsoleOutputReceiver;
import org.apache.maven.surefire.report.ConsoleLogger;
import org.apache.maven.surefire.report.RunListener;

public class ForkingRunListener implements RunListener, ConsoleLogger, ConsoleOutputReceiver
{
    public static final byte BOOTERCODE_TESTSET_STARTING = 49;
    public static final byte BOOTERCODE_TESTSET_COMPLETED = 50;
    public static final byte BOOTERCODE_STDOUT = 51;
    public static final byte BOOTERCODE_STDERR = 52;
    public static final byte BOOTERCODE_TEST_STARTING = 53;
    public static final byte BOOTERCODE_TEST_SUCCEEDED = 54;
    public static final byte BOOTERCODE_TEST_ERROR = 55;
    public static final byte BOOTERCODE_TEST_FAILED = 56;
    public static final byte BOOTERCODE_TEST_SKIPPED = 57;
    public static final byte BOOTERCODE_TEST_ASSUMPTIONFAILURE = 71;
    public static final byte BOOTERCODE_CONSOLE = 72;
    public static final byte BOOTERCODE_SYSPROPS = 73;
    public static final byte BOOTERCODE_NEXT_TEST = 78;
    public static final byte BOOTERCODE_ERROR = 88;
    public static final byte BOOTERCODE_BYE = 90;
    private final PrintStream target;
    private final Integer testSetChannelId;
    private final boolean trimStackTraces;
    private final byte[] stdOutHeader;
    private final byte[] stdErrHeader;
    private static final char[] digits;
    
    public ForkingRunListener(final PrintStream target, final int testSetChannelId, final boolean trimStackTraces) {
        this.target = target;
        this.testSetChannelId = testSetChannelId;
        this.trimStackTraces = trimStackTraces;
        this.stdOutHeader = createHeader((byte)51, testSetChannelId);
        this.stdErrHeader = createHeader((byte)52, testSetChannelId);
        this.sendProps();
    }
    
    public void testSetStarting(final ReportEntry report) {
        this.target.print(this.toString((byte)49, report, this.testSetChannelId));
    }
    
    public void testSetCompleted(final ReportEntry report) {
        this.target.print(this.toString((byte)50, report, this.testSetChannelId));
    }
    
    public void testStarting(final ReportEntry report) {
        this.target.print(this.toString((byte)53, report, this.testSetChannelId));
    }
    
    public void testSucceeded(final ReportEntry report) {
        this.target.print(this.toString((byte)54, report, this.testSetChannelId));
    }
    
    public void testAssumptionFailure(final ReportEntry report) {
        this.target.print(this.toString((byte)71, report, this.testSetChannelId));
    }
    
    public void testError(final ReportEntry report) {
        this.target.print(this.toString((byte)55, report, this.testSetChannelId));
    }
    
    public void testFailed(final ReportEntry report) {
        this.target.print(this.toString((byte)56, report, this.testSetChannelId));
    }
    
    public void testSkipped(final ReportEntry report) {
        this.target.print(this.toString((byte)57, report, this.testSetChannelId));
    }
    
    void sendProps() {
        final Properties systemProperties = System.getProperties();
        if (systemProperties != null) {
            final Enumeration propertyKeys = systemProperties.propertyNames();
            while (propertyKeys.hasMoreElements()) {
                final String key = propertyKeys.nextElement();
                String value = systemProperties.getProperty(key);
                if (value == null) {
                    value = "null";
                }
                this.target.print(this.toPropertyString(key, value));
            }
        }
    }
    
    public void writeTestOutput(final byte[] buf, final int off, final int len, final boolean stdout) {
        final byte[] header = stdout ? this.stdOutHeader : this.stdErrHeader;
        final byte[] content = new byte[buf.length * 3 + 1];
        int i = StringUtils.escapeBytesToPrintable(content, 0, buf, off, len);
        content[i++] = 10;
        synchronized (this.target) {
            this.target.write(header, 0, header.length);
            this.target.write(content, 0, i);
        }
    }
    
    public static byte[] createHeader(final byte booterCode, final int testSetChannel) {
        final byte[] header = { booterCode, 44, 0, 0, 0, 0, 44 };
        int i = testSetChannel;
        int charPos = 6;
        final int radix = 16;
        final int mask = radix - 1;
        do {
            header[--charPos] = (byte)ForkingRunListener.digits[i & mask];
            i >>>= 4;
        } while (i != 0);
        while (charPos > 2) {
            header[--charPos] = 48;
        }
        return header;
    }
    
    public void info(final String message) {
        if (message == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder(7 + message.length() * 5);
        this.append(sb, (byte)72);
        comma(sb);
        this.append(sb, Integer.toHexString(this.testSetChannelId));
        comma(sb);
        StringUtils.escapeToPrintable(sb, message);
        sb.append('\n');
        this.target.print(sb.toString());
    }
    
    private String toPropertyString(final String key, final String value) {
        final StringBuilder stringBuilder = new StringBuilder();
        this.append(stringBuilder, (byte)73);
        comma(stringBuilder);
        this.append(stringBuilder, Integer.toHexString(this.testSetChannelId));
        comma(stringBuilder);
        StringUtils.escapeToPrintable(stringBuilder, key);
        comma(stringBuilder);
        StringUtils.escapeToPrintable(stringBuilder, value);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    
    private String toString(final byte operationCode, final ReportEntry reportEntry, final Integer testSetChannelId) {
        final StringBuilder stringBuilder = new StringBuilder();
        this.append(stringBuilder, operationCode);
        comma(stringBuilder);
        this.append(stringBuilder, Integer.toHexString(testSetChannelId));
        comma(stringBuilder);
        nullableEncoding(stringBuilder, reportEntry.getSourceName());
        comma(stringBuilder);
        nullableEncoding(stringBuilder, reportEntry.getName());
        comma(stringBuilder);
        nullableEncoding(stringBuilder, reportEntry.getGroup());
        comma(stringBuilder);
        nullableEncoding(stringBuilder, reportEntry.getMessage());
        comma(stringBuilder);
        this.nullableEncoding(stringBuilder, reportEntry.getElapsed());
        this.encode(stringBuilder, reportEntry.getStackTraceWriter());
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    
    private static void comma(final StringBuilder stringBuilder) {
        stringBuilder.append(",");
    }
    
    private ForkingRunListener append(final StringBuilder stringBuilder, final String message) {
        stringBuilder.append(this.encode(message));
        return this;
    }
    
    private ForkingRunListener append(final StringBuilder stringBuilder, final byte b) {
        stringBuilder.append((char)b);
        return this;
    }
    
    private void nullableEncoding(final StringBuilder stringBuilder, final Integer source) {
        if (source == null) {
            stringBuilder.append("null");
        }
        else {
            stringBuilder.append(source.toString());
        }
    }
    
    private String encode(final String source) {
        return source;
    }
    
    private static void nullableEncoding(final StringBuilder stringBuilder, final String source) {
        if (source == null || source.length() == 0) {
            stringBuilder.append("null");
        }
        else {
            StringUtils.escapeToPrintable(stringBuilder, source);
        }
    }
    
    private void encode(final StringBuilder stringBuilder, final StackTraceWriter stackTraceWriter) {
        encode(stringBuilder, stackTraceWriter, this.trimStackTraces);
    }
    
    public static void encode(final StringBuilder stringBuilder, final StackTraceWriter stackTraceWriter, final boolean trimStackTraces) {
        if (stackTraceWriter != null) {
            comma(stringBuilder);
            final SafeThrowable throwable = stackTraceWriter.getThrowable();
            if (throwable != null) {
                final String message = throwable.getLocalizedMessage();
                nullableEncoding(stringBuilder, message);
            }
            comma(stringBuilder);
            nullableEncoding(stringBuilder, stackTraceWriter.smartTrimmedStackTrace());
            comma(stringBuilder);
            nullableEncoding(stringBuilder, trimStackTraces ? stackTraceWriter.writeTrimmedTraceToString() : stackTraceWriter.writeTraceToString());
        }
    }
    
    static {
        digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    }
}
