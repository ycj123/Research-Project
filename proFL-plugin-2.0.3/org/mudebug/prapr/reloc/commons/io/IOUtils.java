// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.io;

import java.io.PrintWriter;
import java.io.File;
import java.io.EOFException;
import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Collection;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.mudebug.prapr.reloc.commons.io.output.StringBuilderWriter;
import java.io.CharArrayWriter;
import org.mudebug.prapr.reloc.commons.io.output.ByteArrayOutputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.io.Closeable;
import java.io.Reader;

public class IOUtils
{
    public static final char DIR_SEPARATOR_UNIX = '/';
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    public static final char DIR_SEPARATOR;
    public static final String LINE_SEPARATOR_UNIX = "\n";
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    public static final String LINE_SEPARATOR;
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static char[] SKIP_CHAR_BUFFER;
    private static byte[] SKIP_BYTE_BUFFER;
    
    public static void closeQuietly(final Reader input) {
        closeQuietly((Closeable)input);
    }
    
    public static void closeQuietly(final Writer output) {
        closeQuietly((Closeable)output);
    }
    
    public static void closeQuietly(final InputStream input) {
        closeQuietly((Closeable)input);
    }
    
    public static void closeQuietly(final OutputStream output) {
        closeQuietly((Closeable)output);
    }
    
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        }
        catch (IOException ex) {}
    }
    
    public static void closeQuietly(final Socket sock) {
        if (sock != null) {
            try {
                sock.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public static InputStream toBufferedInputStream(final InputStream input) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(input);
    }
    
    public static byte[] toByteArray(final InputStream input) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }
    
    public static byte[] toByteArray(final Reader input) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }
    
    public static byte[] toByteArray(final Reader input, final String encoding) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, encoding);
        return output.toByteArray();
    }
    
    @Deprecated
    public static byte[] toByteArray(final String input) throws IOException {
        return input.getBytes();
    }
    
    public static char[] toCharArray(final InputStream is) throws IOException {
        final CharArrayWriter output = new CharArrayWriter();
        copy(is, output);
        return output.toCharArray();
    }
    
    public static char[] toCharArray(final InputStream is, final String encoding) throws IOException {
        final CharArrayWriter output = new CharArrayWriter();
        copy(is, output, encoding);
        return output.toCharArray();
    }
    
    public static char[] toCharArray(final Reader input) throws IOException {
        final CharArrayWriter sw = new CharArrayWriter();
        copy(input, sw);
        return sw.toCharArray();
    }
    
    public static String toString(final InputStream input) throws IOException {
        final StringBuilderWriter sw = new StringBuilderWriter();
        copy(input, sw);
        return sw.toString();
    }
    
    public static String toString(final InputStream input, final String encoding) throws IOException {
        final StringBuilderWriter sw = new StringBuilderWriter();
        copy(input, sw, encoding);
        return sw.toString();
    }
    
    public static String toString(final Reader input) throws IOException {
        final StringBuilderWriter sw = new StringBuilderWriter();
        copy(input, sw);
        return sw.toString();
    }
    
    @Deprecated
    public static String toString(final byte[] input) throws IOException {
        return new String(input);
    }
    
    @Deprecated
    public static String toString(final byte[] input, final String encoding) throws IOException {
        if (encoding == null) {
            return new String(input);
        }
        return new String(input, encoding);
    }
    
    public static List<String> readLines(final InputStream input) throws IOException {
        final InputStreamReader reader = new InputStreamReader(input);
        return readLines(reader);
    }
    
    public static List<String> readLines(final InputStream input, final String encoding) throws IOException {
        if (encoding == null) {
            return readLines(input);
        }
        final InputStreamReader reader = new InputStreamReader(input, encoding);
        return readLines(reader);
    }
    
    public static List<String> readLines(final Reader input) throws IOException {
        final BufferedReader reader = new BufferedReader(input);
        final List<String> list = new ArrayList<String>();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            list.add(line);
        }
        return list;
    }
    
    public static LineIterator lineIterator(final Reader reader) {
        return new LineIterator(reader);
    }
    
    public static LineIterator lineIterator(final InputStream input, final String encoding) throws IOException {
        Reader reader = null;
        if (encoding == null) {
            reader = new InputStreamReader(input);
        }
        else {
            reader = new InputStreamReader(input, encoding);
        }
        return new LineIterator(reader);
    }
    
    public static InputStream toInputStream(final CharSequence input) {
        return toInputStream(input.toString());
    }
    
    public static InputStream toInputStream(final CharSequence input, final String encoding) throws IOException {
        return toInputStream(input.toString(), encoding);
    }
    
    public static InputStream toInputStream(final String input) {
        final byte[] bytes = input.getBytes();
        return new ByteArrayInputStream(bytes);
    }
    
    public static InputStream toInputStream(final String input, final String encoding) throws IOException {
        final byte[] bytes = (encoding != null) ? input.getBytes(encoding) : input.getBytes();
        return new ByteArrayInputStream(bytes);
    }
    
    public static void write(final byte[] data, final OutputStream output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }
    
    public static void write(final byte[] data, final Writer output) throws IOException {
        if (data != null) {
            output.write(new String(data));
        }
    }
    
    public static void write(final byte[] data, final Writer output, final String encoding) throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            }
            else {
                output.write(new String(data, encoding));
            }
        }
    }
    
    public static void write(final char[] data, final Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }
    
    public static void write(final char[] data, final OutputStream output) throws IOException {
        if (data != null) {
            output.write(new String(data).getBytes());
        }
    }
    
    public static void write(final char[] data, final OutputStream output, final String encoding) throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            }
            else {
                output.write(new String(data).getBytes(encoding));
            }
        }
    }
    
    public static void write(final CharSequence data, final Writer output) throws IOException {
        if (data != null) {
            write(data.toString(), output);
        }
    }
    
    public static void write(final CharSequence data, final OutputStream output) throws IOException {
        if (data != null) {
            write(data.toString(), output);
        }
    }
    
    public static void write(final CharSequence data, final OutputStream output, final String encoding) throws IOException {
        if (data != null) {
            write(data.toString(), output, encoding);
        }
    }
    
    public static void write(final String data, final Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }
    
    public static void write(final String data, final OutputStream output) throws IOException {
        if (data != null) {
            output.write(data.getBytes());
        }
    }
    
    public static void write(final String data, final OutputStream output, final String encoding) throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            }
            else {
                output.write(data.getBytes(encoding));
            }
        }
    }
    
    @Deprecated
    public static void write(final StringBuffer data, final Writer output) throws IOException {
        if (data != null) {
            output.write(data.toString());
        }
    }
    
    @Deprecated
    public static void write(final StringBuffer data, final OutputStream output) throws IOException {
        if (data != null) {
            output.write(data.toString().getBytes());
        }
    }
    
    @Deprecated
    public static void write(final StringBuffer data, final OutputStream output, final String encoding) throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            }
            else {
                output.write(data.toString().getBytes(encoding));
            }
        }
    }
    
    public static void writeLines(final Collection<?> lines, String lineEnding, final OutputStream output) throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = IOUtils.LINE_SEPARATOR;
        }
        for (final Object line : lines) {
            if (line != null) {
                output.write(line.toString().getBytes());
            }
            output.write(lineEnding.getBytes());
        }
    }
    
    public static void writeLines(final Collection<?> lines, String lineEnding, final OutputStream output, final String encoding) throws IOException {
        if (encoding == null) {
            writeLines(lines, lineEnding, output);
        }
        else {
            if (lines == null) {
                return;
            }
            if (lineEnding == null) {
                lineEnding = IOUtils.LINE_SEPARATOR;
            }
            for (final Object line : lines) {
                if (line != null) {
                    output.write(line.toString().getBytes(encoding));
                }
                output.write(lineEnding.getBytes(encoding));
            }
        }
    }
    
    public static void writeLines(final Collection<?> lines, String lineEnding, final Writer writer) throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = IOUtils.LINE_SEPARATOR;
        }
        for (final Object line : lines) {
            if (line != null) {
                writer.write(line.toString());
            }
            writer.write(lineEnding);
        }
    }
    
    public static int copy(final InputStream input, final OutputStream output) throws IOException {
        final long count = copyLarge(input, output);
        if (count > 2147483647L) {
            return -1;
        }
        return (int)count;
    }
    
    public static long copyLarge(final InputStream input, final OutputStream output) throws IOException {
        final byte[] buffer = new byte[4096];
        long count = 0L;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    
    public static void copy(final InputStream input, final Writer output) throws IOException {
        final InputStreamReader in = new InputStreamReader(input);
        copy(in, output);
    }
    
    public static void copy(final InputStream input, final Writer output, final String encoding) throws IOException {
        if (encoding == null) {
            copy(input, output);
        }
        else {
            final InputStreamReader in = new InputStreamReader(input, encoding);
            copy(in, output);
        }
    }
    
    public static int copy(final Reader input, final Writer output) throws IOException {
        final long count = copyLarge(input, output);
        if (count > 2147483647L) {
            return -1;
        }
        return (int)count;
    }
    
    public static long copyLarge(final Reader input, final Writer output) throws IOException {
        final char[] buffer = new char[4096];
        long count = 0L;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    
    public static void copy(final Reader input, final OutputStream output) throws IOException {
        final OutputStreamWriter out = new OutputStreamWriter(output);
        copy(input, out);
        out.flush();
    }
    
    public static void copy(final Reader input, final OutputStream output, final String encoding) throws IOException {
        if (encoding == null) {
            copy(input, output);
        }
        else {
            final OutputStreamWriter out = new OutputStreamWriter(output, encoding);
            copy(input, out);
            out.flush();
        }
    }
    
    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        if (!(input1 instanceof BufferedInputStream)) {
            input1 = new BufferedInputStream(input1);
        }
        if (!(input2 instanceof BufferedInputStream)) {
            input2 = new BufferedInputStream(input2);
        }
        for (int ch = input1.read(); -1 != ch; ch = input1.read()) {
            final int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
        }
        final int ch2 = input2.read();
        return ch2 == -1;
    }
    
    public static boolean contentEquals(Reader input1, Reader input2) throws IOException {
        if (!(input1 instanceof BufferedReader)) {
            input1 = new BufferedReader(input1);
        }
        if (!(input2 instanceof BufferedReader)) {
            input2 = new BufferedReader(input2);
        }
        for (int ch = input1.read(); -1 != ch; ch = input1.read()) {
            final int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
        }
        final int ch2 = input2.read();
        return ch2 == -1;
    }
    
    public static long skip(final InputStream input, final long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        if (IOUtils.SKIP_BYTE_BUFFER == null) {
            IOUtils.SKIP_BYTE_BUFFER = new byte[2048];
        }
        long remain;
        long n;
        for (remain = toSkip; remain > 0L; remain -= n) {
            n = input.read(IOUtils.SKIP_BYTE_BUFFER, 0, (int)Math.min(remain, 2048L));
            if (n < 0L) {
                break;
            }
        }
        return toSkip - remain;
    }
    
    public static long skip(final Reader input, final long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        if (IOUtils.SKIP_CHAR_BUFFER == null) {
            IOUtils.SKIP_CHAR_BUFFER = new char[2048];
        }
        long remain;
        long n;
        for (remain = toSkip; remain > 0L; remain -= n) {
            n = input.read(IOUtils.SKIP_CHAR_BUFFER, 0, (int)Math.min(remain, 2048L));
            if (n < 0L) {
                break;
            }
        }
        return toSkip - remain;
    }
    
    public static void skipFully(final InputStream input, final long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        final long skipped = skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }
    
    public static void skipFully(final Reader input, final long toSkip) throws IOException {
        final long skipped = skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }
    
    static {
        DIR_SEPARATOR = File.separatorChar;
        final StringBuilderWriter buf = new StringBuilderWriter(4);
        final PrintWriter out = new PrintWriter(buf);
        out.println();
        LINE_SEPARATOR = buf.toString();
        out.close();
    }
}
