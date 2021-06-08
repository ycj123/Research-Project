// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import java.io.IOException;
import org.codehaus.groovy.tools.shell.util.Preferences;
import org.fusesource.jansi.AnsiRenderWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.OutputStream;
import java.io.InputStream;

public class IO
{
    public final InputStream inputStream;
    public final OutputStream outputStream;
    public final OutputStream errorStream;
    public final Reader in;
    public final PrintWriter out;
    public final PrintWriter err;
    
    public IO(final InputStream inputStream, final OutputStream outputStream, final OutputStream errorStream) {
        assert inputStream != null;
        assert outputStream != null;
        assert errorStream != null;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.errorStream = errorStream;
        this.in = new InputStreamReader(inputStream);
        this.out = (PrintWriter)new AnsiRenderWriter(outputStream, true);
        this.err = (PrintWriter)new AnsiRenderWriter(errorStream, true);
    }
    
    public IO() {
        this(System.in, System.out, System.err);
    }
    
    public void setVerbosity(final Verbosity verbosity) {
        assert verbosity != null;
        Preferences.verbosity = verbosity;
    }
    
    public Verbosity getVerbosity() {
        return Preferences.verbosity;
    }
    
    public boolean isQuiet() {
        return this.getVerbosity() == Verbosity.QUIET;
    }
    
    public boolean isInfo() {
        return this.getVerbosity() == Verbosity.INFO;
    }
    
    public boolean isVerbose() {
        return this.getVerbosity() == Verbosity.VERBOSE;
    }
    
    public boolean isDebug() {
        return this.getVerbosity() == Verbosity.DEBUG;
    }
    
    public void flush() throws IOException {
        this.out.flush();
        this.err.flush();
    }
    
    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.err.close();
    }
    
    public static final class Verbosity
    {
        public static final Verbosity QUIET;
        public static final Verbosity INFO;
        public static final Verbosity VERBOSE;
        public static final Verbosity DEBUG;
        public final String name;
        
        private Verbosity(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
        
        public static Verbosity forName(final String name) {
            assert name != null;
            if (Verbosity.QUIET.name.equalsIgnoreCase(name)) {
                return Verbosity.QUIET;
            }
            if (Verbosity.INFO.name.equalsIgnoreCase(name)) {
                return Verbosity.INFO;
            }
            if (Verbosity.VERBOSE.name.equalsIgnoreCase(name)) {
                return Verbosity.VERBOSE;
            }
            if (Verbosity.DEBUG.name.equalsIgnoreCase(name)) {
                return Verbosity.DEBUG;
            }
            throw new IllegalArgumentException("Invalid verbosity name: " + name);
        }
        
        static {
            QUIET = new Verbosity("QUIET");
            INFO = new Verbosity("INFO");
            VERBOSE = new Verbosity("VERBOSE");
            DEBUG = new Verbosity("DEBUG");
        }
    }
}
