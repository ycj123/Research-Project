// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

import javax.annotation.Nullable;
import java.nio.channels.Channel;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.io.Reader;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nonnull;
import java.io.InputStream;

public final class IOUtil
{
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    
    private IOUtil() {
    }
    
    public static void copy(@Nonnull final InputStream input, @Nonnull final OutputStream output) throws IOException {
        copy(input, output, 4096);
    }
    
    public static void copy(@Nonnull final InputStream input, @Nonnull final OutputStream output, final int bufferSize) throws IOException {
        final byte[] buffer = new byte[bufferSize];
        int n;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
    }
    
    public static void copy(@Nonnull final Reader input, @Nonnull final Writer output) throws IOException {
        copy(input, output, 4096);
    }
    
    public static void copy(@Nonnull final Reader input, @Nonnull final Writer output, final int bufferSize) throws IOException {
        final char[] buffer = new char[bufferSize];
        int n;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        output.flush();
    }
    
    public static void copy(@Nonnull final InputStream input, @Nonnull final Writer output) throws IOException {
        copy(input, output, 4096);
    }
    
    public static void copy(@Nonnull final InputStream input, @Nonnull final Writer output, final int bufferSize) throws IOException {
        final InputStreamReader in = new InputStreamReader(input);
        copy(in, output, bufferSize);
    }
    
    public static void copy(@Nonnull final InputStream input, @Nonnull final Writer output, @Nonnull final String encoding) throws IOException {
        final InputStreamReader in = new InputStreamReader(input, encoding);
        copy(in, output);
    }
    
    public static void copy(@Nonnull final InputStream input, @Nonnull final Writer output, @Nonnull final String encoding, final int bufferSize) throws IOException {
        final InputStreamReader in = new InputStreamReader(input, encoding);
        copy(in, output, bufferSize);
    }
    
    @Nonnull
    public static String toString(@Nonnull final InputStream input) throws IOException {
        return toString(input, 4096);
    }
    
    @Nonnull
    public static String toString(@Nonnull final InputStream input, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, bufferSize);
        return sw.toString();
    }
    
    @Nonnull
    public static String toString(@Nonnull final InputStream input, @Nonnull final String encoding) throws IOException {
        return toString(input, encoding, 4096);
    }
    
    @Nonnull
    public static String toString(@Nonnull final InputStream input, @Nonnull final String encoding, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, encoding, bufferSize);
        return sw.toString();
    }
    
    @Nonnull
    public static byte[] toByteArray(@Nonnull final InputStream input) throws IOException {
        return toByteArray(input, 4096);
    }
    
    @Nonnull
    public static byte[] toByteArray(@Nonnull final InputStream input, final int bufferSize) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, bufferSize);
        return output.toByteArray();
    }
    
    public static void copy(@Nonnull final Reader input, @Nonnull final OutputStream output) throws IOException {
        copy(input, output, 4096);
    }
    
    public static void copy(@Nonnull final Reader input, @Nonnull final OutputStream output, final int bufferSize) throws IOException {
        final OutputStreamWriter out = new OutputStreamWriter(output);
        copy(input, out, bufferSize);
        out.flush();
    }
    
    @Nonnull
    public static String toString(@Nonnull final Reader input) throws IOException {
        return toString(input, 4096);
    }
    
    @Nonnull
    public static String toString(@Nonnull final Reader input, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, bufferSize);
        return sw.toString();
    }
    
    @Nonnull
    public static byte[] toByteArray(@Nonnull final Reader input) throws IOException {
        return toByteArray(input, 4096);
    }
    
    @Nonnull
    public static byte[] toByteArray(@Nonnull final Reader input, final int bufferSize) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, bufferSize);
        return output.toByteArray();
    }
    
    public static void copy(@Nonnull final String input, @Nonnull final OutputStream output) throws IOException {
        copy(input, output, 4096);
    }
    
    public static void copy(@Nonnull final String input, @Nonnull final OutputStream output, final int bufferSize) throws IOException {
        final StringReader in = new StringReader(input);
        final OutputStreamWriter out = new OutputStreamWriter(output);
        copy(in, out, bufferSize);
        out.flush();
    }
    
    public static void copy(@Nonnull final String input, @Nonnull final Writer output) throws IOException {
        output.write(input);
    }
    
    @Nonnull
    public static byte[] toByteArray(@Nonnull final String input) throws IOException {
        return toByteArray(input, 4096);
    }
    
    @Nonnull
    public static byte[] toByteArray(@Nonnull final String input, final int bufferSize) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, bufferSize);
        return output.toByteArray();
    }
    
    public static void copy(@Nonnull final byte[] input, @Nonnull final Writer output) throws IOException {
        copy(input, output, 4096);
    }
    
    public static void copy(@Nonnull final byte[] input, @Nonnull final Writer output, final int bufferSize) throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, bufferSize);
    }
    
    public static void copy(@Nonnull final byte[] input, @Nonnull final Writer output, final String encoding) throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, encoding);
    }
    
    public static void copy(@Nonnull final byte[] input, @Nonnull final Writer output, @Nonnull final String encoding, final int bufferSize) throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, encoding, bufferSize);
    }
    
    @Nonnull
    public static String toString(@Nonnull final byte[] input) throws IOException {
        return toString(input, 4096);
    }
    
    @Nonnull
    public static String toString(@Nonnull final byte[] input, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, bufferSize);
        return sw.toString();
    }
    
    @Nonnull
    public static String toString(@Nonnull final byte[] input, @Nonnull final String encoding) throws IOException {
        return toString(input, encoding, 4096);
    }
    
    @Nonnull
    public static String toString(@Nonnull final byte[] input, @Nonnull final String encoding, final int bufferSize) throws IOException {
        final StringWriter sw = new StringWriter();
        copy(input, sw, encoding, bufferSize);
        return sw.toString();
    }
    
    public static void copy(@Nonnull final byte[] input, @Nonnull final OutputStream output) throws IOException {
        output.write(input);
    }
    
    public static boolean contentEquals(@Nonnull final InputStream input1, @Nonnull final InputStream input2) throws IOException {
        final InputStream bufferedInput1 = new BufferedInputStream(input1);
        final InputStream bufferedInput2 = new BufferedInputStream(input2);
        for (int ch = bufferedInput1.read(); -1 != ch; ch = bufferedInput1.read()) {
            final int ch2 = bufferedInput2.read();
            if (ch != ch2) {
                return false;
            }
        }
        final int ch2 = bufferedInput2.read();
        return -1 == ch2;
    }
    
    public static void close(@Nullable final Channel channel) {
        if (channel == null) {
            return;
        }
        try {
            channel.close();
        }
        catch (IOException ex) {}
    }
    
    public static void close(@Nullable final InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        }
        catch (IOException ex) {}
    }
    
    public static void close(@Nullable final OutputStream outputStream) {
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.close();
        }
        catch (IOException ex) {}
    }
    
    public static void close(@Nullable final Reader reader) {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        }
        catch (IOException ex) {}
    }
    
    public static void close(@Nullable final Writer writer) {
        if (writer == null) {
            return;
        }
        try {
            writer.close();
        }
        catch (IOException ex) {}
    }
}
