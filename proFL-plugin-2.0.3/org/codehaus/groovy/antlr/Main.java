// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import groovyjarjarantlr.debug.misc.ASTFrame;
import groovyjarjarantlr.ASTFactory;
import groovyjarjarantlr.BaseAST;
import groovyjarjarantlr.collections.AST;
import groovyjarjarantlr.Token;
import groovyjarjarantlr.CharScanner;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import org.codehaus.groovy.antlr.parser.GroovyLexer;
import org.codehaus.groovy.antlr.parser.GroovyRecognizer;

class Main
{
    static boolean whitespaceIncluded;
    static boolean showTree;
    static boolean verbose;
    
    public static void main(final String[] args) {
        try {
            if (args.length > 0) {
                System.err.println("Parsing...");
                for (int i = 0; i < args.length; ++i) {
                    if (args[i].equals("-showtree")) {
                        Main.showTree = true;
                    }
                    else if (args[i].equals("-verbose")) {
                        Main.verbose = true;
                    }
                    else if (args[i].equals("-trace")) {
                        GroovyRecognizer.tracing = true;
                        GroovyLexer.tracing = true;
                    }
                    else if (args[i].equals("-traceParser")) {
                        GroovyRecognizer.tracing = true;
                    }
                    else if (args[i].equals("-traceLexer")) {
                        GroovyLexer.tracing = true;
                    }
                    else if (args[i].equals("-whitespaceIncluded")) {
                        Main.whitespaceIncluded = true;
                    }
                    else {
                        doFile(new File(args[i]));
                    }
                }
            }
            else {
                System.err.println("Usage: java -jar groovyc.jar [-showtree] [-verbose] [-trace{,Lexer,Parser}]<directory or file name>");
            }
        }
        catch (Exception e) {
            System.err.println("exception: " + e);
            e.printStackTrace(System.err);
        }
    }
    
    public static void doFile(final File f) throws Exception {
        if (f.isDirectory()) {
            final String[] files = f.list();
            for (int i = 0; i < files.length; ++i) {
                doFile(new File(f, files[i]));
            }
        }
        else if (f.getName().endsWith(".groovy")) {
            System.err.println(" --- " + f.getAbsolutePath());
            final SourceBuffer sourceBuffer = new SourceBuffer();
            final UnicodeEscapingReader unicodeReader = new UnicodeEscapingReader(new FileReader(f), sourceBuffer);
            final GroovyLexer lexer = new GroovyLexer(unicodeReader);
            unicodeReader.setLexer(lexer);
            parseFile(f.getName(), lexer, sourceBuffer);
        }
    }
    
    public static void parseFile(final String f, final GroovyLexer l, final SourceBuffer sourceBuffer) throws Exception {
        try {
            final GroovyRecognizer parser = GroovyRecognizer.make(l);
            parser.setSourceBuffer(sourceBuffer);
            parser.setFilename(f);
            if (Main.whitespaceIncluded) {
                final GroovyLexer lexer = parser.getLexer();
                lexer.setWhitespaceIncluded(true);
                Token t;
                do {
                    t = lexer.nextToken();
                    System.out.println(t);
                } while (t != null && t.getType() != 1);
                return;
            }
            parser.compilationUnit();
            System.out.println("parseFile " + f + " => " + parser.getAST());
            doTreeAction(f, parser.getAST(), parser.getTokenNames());
        }
        catch (Exception e) {
            System.err.println("parser exception: " + e);
            e.printStackTrace();
        }
    }
    
    public static void doTreeAction(final String f, final AST t, final String[] tokenNames) {
        if (t == null) {
            return;
        }
        if (Main.showTree) {
            BaseAST.setVerboseStringConversion(true, tokenNames);
            final ASTFactory factory = new ASTFactory();
            final AST r = factory.create(0, "AST ROOT");
            r.setFirstChild(t);
            final ASTFrame frame = new ASTFrame("Groovy AST", r);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(final WindowEvent e) {
                    frame.setVisible(false);
                    frame.dispose();
                    System.exit(0);
                }
            });
            if (Main.verbose) {
                System.out.println(t.toStringList());
            }
        }
    }
    
    static {
        Main.whitespaceIncluded = false;
        Main.showTree = false;
        Main.verbose = false;
    }
}
