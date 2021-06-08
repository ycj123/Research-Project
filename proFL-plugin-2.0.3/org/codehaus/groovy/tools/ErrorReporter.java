// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.GroovyExceptionInterface;
import org.codehaus.groovy.control.CompilationFailedException;
import java.io.PrintWriter;
import java.io.PrintStream;

public class ErrorReporter
{
    private Throwable base;
    private boolean debug;
    private Object output;
    
    public ErrorReporter(final Throwable e) {
        this.base = null;
        this.debug = false;
        this.output = null;
        this.base = e;
    }
    
    public ErrorReporter(final Throwable e, final boolean debug) {
        this.base = null;
        this.debug = false;
        this.output = null;
        this.base = e;
        this.debug = debug;
    }
    
    public void write(final PrintStream stream) {
        this.output = stream;
        this.dispatch(this.base, false);
        stream.flush();
    }
    
    public void write(final PrintWriter writer) {
        this.output = writer;
        this.dispatch(this.base, false);
        writer.flush();
    }
    
    protected void dispatch(final Throwable object, final boolean child) {
        if (object instanceof CompilationFailedException) {
            this.report((CompilationFailedException)object, child);
        }
        else if (object instanceof GroovyExceptionInterface) {
            this.report((GroovyExceptionInterface)object, child);
        }
        else if (object instanceof GroovyRuntimeException) {
            this.report((Exception)object, child);
        }
        else if (object instanceof Exception) {
            this.report((Exception)object, child);
        }
        else {
            this.report(object, child);
        }
    }
    
    protected void report(final CompilationFailedException e, final boolean child) {
        this.println(e.toString());
        this.stacktrace(e, false);
    }
    
    protected void report(final GroovyExceptionInterface e, final boolean child) {
        this.println(((Exception)e).getMessage());
        this.stacktrace((Throwable)e, false);
    }
    
    protected void report(final Exception e, final boolean child) {
        this.println(e.getMessage());
        this.stacktrace(e, false);
    }
    
    protected void report(final Throwable e, final boolean child) {
        this.println(">>> a serious error occurred: " + e.getMessage());
        this.stacktrace(e, true);
    }
    
    protected void println(final String line) {
        if (this.output instanceof PrintStream) {
            ((PrintStream)this.output).println(line);
        }
        else {
            ((PrintWriter)this.output).println(line);
        }
    }
    
    protected void println(final StringBuffer line) {
        if (this.output instanceof PrintStream) {
            ((PrintStream)this.output).println(line);
        }
        else {
            ((PrintWriter)this.output).println(line);
        }
    }
    
    protected void stacktrace(final Throwable e, final boolean always) {
        if (this.debug || always) {
            this.println(">>> stacktrace:");
            if (this.output instanceof PrintStream) {
                e.printStackTrace((PrintStream)this.output);
            }
            else {
                e.printStackTrace((PrintWriter)this.output);
            }
        }
    }
}
