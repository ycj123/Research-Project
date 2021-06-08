// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.tools.Utilities;
import java.io.Writer;
import java.io.FileWriter;
import com.thoughtworks.xstream.XStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.codehaus.groovy.syntax.SyntaxException;
import java.io.Reader;
import java.io.IOException;
import org.codehaus.groovy.control.messages.SimpleMessage;
import org.codehaus.groovy.GroovyBugError;
import groovyjarjarantlr.Token;
import org.codehaus.groovy.control.messages.Message;
import groovyjarjarantlr.MismatchedTokenException;
import groovyjarjarantlr.MismatchedCharException;
import groovyjarjarantlr.NoViableAltForCharException;
import groovyjarjarantlr.NoViableAltException;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.control.io.StringReaderSource;
import org.codehaus.groovy.control.io.URLReaderSource;
import java.net.URL;
import org.codehaus.groovy.control.io.FileReaderSource;
import java.io.File;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.syntax.Reduction;
import org.codehaus.groovy.control.io.ReaderSource;

public class SourceUnit extends ProcessingUnit
{
    private ParserPlugin parserPlugin;
    protected ReaderSource source;
    protected String name;
    protected Reduction cst;
    protected ModuleNode ast;
    
    public SourceUnit(final String name, final ReaderSource source, final CompilerConfiguration flags, final GroovyClassLoader loader, final ErrorCollector er) {
        super(flags, loader, er);
        this.name = name;
        this.source = source;
    }
    
    public SourceUnit(final File source, final CompilerConfiguration configuration, final GroovyClassLoader loader, final ErrorCollector er) {
        this(source.getPath(), new FileReaderSource(source, configuration), configuration, loader, er);
    }
    
    public SourceUnit(final URL source, final CompilerConfiguration configuration, final GroovyClassLoader loader, final ErrorCollector er) {
        this(source.getPath(), new URLReaderSource(source, configuration), configuration, loader, er);
    }
    
    public SourceUnit(final String name, final String source, final CompilerConfiguration configuration, final GroovyClassLoader loader, final ErrorCollector er) {
        this(name, new StringReaderSource(source, configuration), configuration, loader, er);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Reduction getCST() {
        return this.cst;
    }
    
    public ModuleNode getAST() {
        return this.ast;
    }
    
    public boolean failedWithUnexpectedEOF() {
        if (this.getErrorCollector().hasErrors()) {
            final Message last = this.getErrorCollector().getLastError();
            Throwable cause = null;
            if (last instanceof SyntaxErrorMessage) {
                cause = ((SyntaxErrorMessage)last).getCause().getCause();
            }
            if (cause != null) {
                if (cause instanceof NoViableAltException) {
                    return this.isEofToken(((NoViableAltException)cause).token);
                }
                if (cause instanceof NoViableAltForCharException) {
                    final char badChar = ((NoViableAltForCharException)cause).foundChar;
                    return badChar == '\uffff';
                }
                if (cause instanceof MismatchedCharException) {
                    final char badChar = (char)((MismatchedCharException)cause).foundChar;
                    return badChar == '\uffff';
                }
                if (cause instanceof MismatchedTokenException) {
                    return this.isEofToken(((MismatchedTokenException)cause).token);
                }
            }
        }
        return false;
    }
    
    protected boolean isEofToken(final Token token) {
        return token.getType() == 1;
    }
    
    public static SourceUnit create(final String name, final String source) {
        final CompilerConfiguration configuration = new CompilerConfiguration();
        configuration.setTolerance(1);
        return new SourceUnit(name, source, configuration, null, new ErrorCollector(configuration));
    }
    
    public static SourceUnit create(final String name, final String source, final int tolerance) {
        final CompilerConfiguration configuration = new CompilerConfiguration();
        configuration.setTolerance(tolerance);
        return new SourceUnit(name, source, configuration, null, new ErrorCollector(configuration));
    }
    
    public void parse() throws CompilationFailedException {
        if (this.phase > 2) {
            throw new GroovyBugError("parsing is already complete");
        }
        if (this.phase == 1) {
            this.nextPhase();
        }
        Reader reader = null;
        try {
            reader = this.source.getReader();
            this.parserPlugin = this.getConfiguration().getPluginFactory().createParserPlugin();
            this.cst = this.parserPlugin.parseCST(this, reader);
            reader.close();
        }
        catch (IOException e) {
            this.getErrorCollector().addFatalError(new SimpleMessage(e.getMessage(), this));
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException ex) {}
            }
        }
    }
    
    public void convert() throws CompilationFailedException {
        if (this.phase == 2 && this.phaseComplete) {
            this.gotoPhase(3);
        }
        if (this.phase != 3) {
            throw new GroovyBugError("SourceUnit not ready for convert()");
        }
        try {
            (this.ast = this.parserPlugin.buildAST(this, this.classLoader, this.cst)).setDescription(this.name);
        }
        catch (SyntaxException e) {
            this.getErrorCollector().addError(new SyntaxErrorMessage(e, this));
        }
        final String property = AccessController.doPrivileged((PrivilegedAction<String>)new PrivilegedAction() {
            public Object run() {
                return System.getProperty("groovy.ast");
            }
        });
        if ("xml".equals(property)) {
            this.saveAsXML(this.name, this.ast);
        }
    }
    
    private void saveAsXML(final String name, final ModuleNode ast) {
        final XStream xstream = new XStream();
        try {
            xstream.toXML((Object)ast, (Writer)new FileWriter(name + ".xml"));
            System.out.println("Written AST to " + name + ".xml");
        }
        catch (Exception e) {
            System.out.println("Couldn't write to " + name + ".xml");
            e.printStackTrace();
        }
    }
    
    public String getSample(final int line, final int column, final Janitor janitor) {
        String sample = null;
        final String text = this.source.getLine(line, janitor);
        if (text != null) {
            if (column > 0) {
                final String marker = Utilities.repeatString(" ", column - 1) + "^";
                if (column > 40) {
                    final int start = column - 30 - 1;
                    final int end = (column + 10 > text.length()) ? text.length() : (column + 10 - 1);
                    sample = "   " + text.substring(start, end) + Utilities.eol() + "   " + marker.substring(start, marker.length());
                }
                else {
                    sample = "   " + text + Utilities.eol() + "   " + marker;
                }
            }
            else {
                sample = text;
            }
        }
        return sample;
    }
    
    public void addException(final Exception e) throws CompilationFailedException {
        this.getErrorCollector().addException(e, this);
    }
    
    public void addError(final SyntaxException se) throws CompilationFailedException {
        this.getErrorCollector().addError(se, this);
    }
}
