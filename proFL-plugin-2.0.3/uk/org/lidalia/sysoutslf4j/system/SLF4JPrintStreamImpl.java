// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.system;

import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;
import java.io.IOException;
import java.util.Locale;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream;
import java.io.PrintStream;

public final class SLF4JPrintStreamImpl extends PrintStream implements SLF4JPrintStream
{
    private final PrintStream originalPrintStream;
    private final SLF4JPrintStreamDelegate delegate;
    
    SLF4JPrintStreamImpl(final PrintStream originalPrintStream, final SLF4JPrintStreamDelegate delegate) {
        super(new ByteArrayOutputStream());
        this.originalPrintStream = originalPrintStream;
        this.delegate = delegate;
    }
    
    @Override
    public synchronized void println(final String string) {
        this.delegate.delegatePrintln(string);
    }
    
    @Override
    public synchronized void println(final Object object) {
        this.delegate.delegatePrintln(String.valueOf(object));
    }
    
    @Override
    public synchronized void println() {
        this.delegate.delegatePrintln("");
    }
    
    @Override
    public synchronized void println(final boolean bool) {
        this.delegate.delegatePrintln(String.valueOf(bool));
    }
    
    @Override
    public synchronized void println(final char character) {
        this.delegate.delegatePrintln(String.valueOf(character));
    }
    
    @Override
    public synchronized void println(final char[] charArray) {
        this.delegate.delegatePrintln(String.valueOf(charArray));
    }
    
    @Override
    public synchronized void println(final double doub) {
        this.delegate.delegatePrintln(String.valueOf(doub));
    }
    
    @Override
    public synchronized void println(final float floa) {
        this.delegate.delegatePrintln(String.valueOf(floa));
    }
    
    @Override
    public synchronized void println(final int integer) {
        this.delegate.delegatePrintln(String.valueOf(integer));
    }
    
    @Override
    public synchronized void println(final long lon) {
        this.delegate.delegatePrintln(String.valueOf(lon));
    }
    
    @Override
    public synchronized PrintStream append(final char character) {
        this.delegate.delegatePrint(String.valueOf(character));
        return this;
    }
    
    @Override
    public synchronized PrintStream append(final CharSequence csq, final int start, final int end) {
        this.delegate.delegatePrint(csq.subSequence(start, end).toString());
        return this;
    }
    
    @Override
    public synchronized PrintStream append(final CharSequence csq) {
        this.delegate.delegatePrint(csq.toString());
        return this;
    }
    
    @Override
    public boolean checkError() {
        return this.originalPrintStream.checkError();
    }
    
    @Override
    protected void setError() {
        this.originalPrintStream.println("WARNING - calling setError on SLFJPrintStream does nothing");
    }
    
    @Override
    public void close() {
        this.originalPrintStream.close();
    }
    
    @Override
    public void flush() {
        this.originalPrintStream.flush();
    }
    
    @Override
    public synchronized PrintStream format(final Locale locale, final String format, final Object... args) {
        final String string = String.format(locale, format, args);
        this.delegate.delegatePrint(string);
        return this;
    }
    
    @Override
    public synchronized PrintStream format(final String format, final Object... args) {
        return this.format(Locale.getDefault(), format, args);
    }
    
    @Override
    public synchronized void print(final boolean bool) {
        this.delegate.delegatePrint(String.valueOf(bool));
    }
    
    @Override
    public synchronized void print(final char character) {
        this.delegate.delegatePrint(String.valueOf(character));
    }
    
    @Override
    public synchronized void print(final char[] charArray) {
        this.delegate.delegatePrint(String.valueOf(charArray));
    }
    
    @Override
    public synchronized void print(final double doubl) {
        this.delegate.delegatePrint(String.valueOf(doubl));
    }
    
    @Override
    public synchronized void print(final float floa) {
        this.delegate.delegatePrint(String.valueOf(floa));
    }
    
    @Override
    public synchronized void print(final int integer) {
        this.delegate.delegatePrint(String.valueOf(integer));
    }
    
    @Override
    public synchronized void print(final long lon) {
        this.delegate.delegatePrint(String.valueOf(lon));
    }
    
    @Override
    public synchronized void print(final Object object) {
        this.delegate.delegatePrint(String.valueOf(object));
    }
    
    @Override
    public synchronized void print(final String string) {
        this.delegate.delegatePrint(String.valueOf(string));
    }
    
    @Override
    public synchronized PrintStream printf(final Locale locale, final String format, final Object... args) {
        return this.format(locale, format, args);
    }
    
    @Override
    public synchronized PrintStream printf(final String format, final Object... args) {
        return this.format(format, args);
    }
    
    @Override
    public void write(final byte[] buf, final int off, final int len) {
        this.originalPrintStream.write(buf, off, len);
    }
    
    @Override
    public void write(final int integer) {
        this.originalPrintStream.write(integer);
    }
    
    @Override
    public void write(final byte[] bytes) throws IOException {
        this.originalPrintStream.write(bytes);
    }
    
    @Override
    public void registerLoggerAppender(final Object loggerAppenderObject) {
        final LoggerAppender loggerAppender = LoggerAppenderProxy.wrap(loggerAppenderObject);
        this.delegate.registerLoggerAppender(loggerAppender);
    }
    
    @Override
    public void deregisterLoggerAppender() {
        this.delegate.deregisterLoggerAppender();
    }
    
    @Override
    public PrintStream getOriginalPrintStream() {
        return this.originalPrintStream;
    }
}
