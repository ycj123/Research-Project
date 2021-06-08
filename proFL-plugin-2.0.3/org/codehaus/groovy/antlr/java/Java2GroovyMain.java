// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.java;

import org.codehaus.groovy.antlr.parser.GroovyRecognizer;
import org.codehaus.groovy.antlr.parser.GroovyLexer;
import org.codehaus.groovy.antlr.treewalker.NodePrinter;
import groovyjarjarantlr.CharScanner;
import java.io.Reader;
import org.codehaus.groovy.antlr.UnicodeEscapingReader;
import java.io.StringReader;
import org.codehaus.groovy.antlr.SourceBuffer;
import org.codehaus.groovy.antlr.AntlrASTProcessor;
import org.codehaus.groovy.antlr.treewalker.Visitor;
import groovyjarjarantlr.collections.AST;
import org.codehaus.groovy.antlr.treewalker.SourceCodeTraversal;
import org.codehaus.groovy.antlr.treewalker.SourcePrinter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import org.codehaus.groovy.antlr.treewalker.PreOrderTraversal;
import org.codehaus.groovy.antlr.treewalker.MindMapPrinter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import groovyjarjarcommonscli.CommandLine;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.File;
import java.util.Arrays;
import groovyjarjarcommonscli.PosixParser;
import groovyjarjarcommonscli.Options;

public class Java2GroovyMain
{
    public static void main(final String[] args) {
        try {
            final Options options = new Options();
            final PosixParser cliParser = new PosixParser();
            final CommandLine cli = cliParser.parse(options, args);
            final String[] filenames = cli.getArgs();
            if (filenames.length == 0) {
                System.err.println("Needs at least one filename");
            }
            final List filenameList = Arrays.asList(filenames);
            for (final String filename : filenameList) {
                final File f = new File(filename);
                final String text = DefaultGroovyMethods.getText(f);
                System.out.println(convert(filename, text, true, true));
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static String convert(final String filename, final String input) throws Exception {
        return convert(filename, input, false, false);
    }
    
    public static String convert(final String filename, final String input, final boolean withHeader, final boolean withNewLines) throws Exception {
        final JavaRecognizer parser = getJavaParser(input);
        final String[] tokenNames = parser.getTokenNames();
        parser.compilationUnit();
        final AST ast = parser.getAST();
        if ("mindmap".equals(System.getProperty("groovyjarjarantlr.ast"))) {
            try {
                final PrintStream out = new PrintStream(new FileOutputStream(filename + ".mm"));
                final Visitor visitor = new MindMapPrinter(out, tokenNames);
                final AntlrASTProcessor treewalker = new PreOrderTraversal(visitor);
                treewalker.process(ast);
            }
            catch (FileNotFoundException e) {
                System.out.println("Cannot create " + filename + ".mm");
            }
        }
        modifyJavaASTintoGroovyAST(tokenNames, ast);
        final String[] groovyTokenNames = getGroovyTokenNames(input);
        groovifyFatJavaLikeGroovyAST(ast, groovyTokenNames);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Visitor visitor2 = new SourcePrinter(new PrintStream(baos), groovyTokenNames, withNewLines);
        final AntlrASTProcessor traverser = new SourceCodeTraversal(visitor2);
        traverser.process(ast);
        String header = "";
        if (withHeader) {
            header = "/*\n  Automatically Converted from Java Source \n  \n  by java2groovy v0.0.1   Copyright Jeremy Rayner 2007\n  \n  !! NOT FIT FOR ANY PURPOSE !! \n  'java2groovy' cannot be used to convert one working program into another  */\n\n";
        }
        return header + new String(baos.toByteArray());
    }
    
    private static void groovifyFatJavaLikeGroovyAST(final AST ast, final String[] groovyTokenNames) {
        final Visitor groovifier = new Groovifier(groovyTokenNames);
        final AntlrASTProcessor groovifierTraverser = new PreOrderTraversal(groovifier);
        groovifierTraverser.process(ast);
    }
    
    private static void modifyJavaASTintoGroovyAST(final String[] tokenNames, final AST ast) {
        final Visitor preJava2groovyConverter = new PreJava2GroovyConverter(tokenNames);
        final AntlrASTProcessor preJava2groovyTraverser = new PreOrderTraversal(preJava2groovyConverter);
        preJava2groovyTraverser.process(ast);
        final Visitor java2groovyConverter = new Java2GroovyConverter(tokenNames);
        final AntlrASTProcessor java2groovyTraverser = new PreOrderTraversal(java2groovyConverter);
        java2groovyTraverser.process(ast);
    }
    
    private static JavaRecognizer getJavaParser(final String input) {
        JavaRecognizer parser = null;
        final SourceBuffer sourceBuffer = new SourceBuffer();
        final UnicodeEscapingReader unicodeReader = new UnicodeEscapingReader(new StringReader(input), sourceBuffer);
        final JavaLexer lexer = new JavaLexer(unicodeReader);
        unicodeReader.setLexer(lexer);
        parser = JavaRecognizer.make(lexer);
        parser.setSourceBuffer(sourceBuffer);
        return parser;
    }
    
    public static String mindmap(final String input) throws Exception {
        final JavaRecognizer parser = getJavaParser(input);
        final String[] tokenNames = parser.getTokenNames();
        parser.compilationUnit();
        final AST ast = parser.getAST();
        modifyJavaASTintoGroovyAST(tokenNames, ast);
        final String[] groovyTokenNames = getGroovyTokenNames(input);
        groovifyFatJavaLikeGroovyAST(ast, groovyTokenNames);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Visitor visitor = new MindMapPrinter(new PrintStream(baos), groovyTokenNames);
        final AntlrASTProcessor traverser = new SourceCodeTraversal(visitor);
        traverser.process(ast);
        return new String(baos.toByteArray());
    }
    
    public static String nodePrinter(final String input) throws Exception {
        final JavaRecognizer parser = getJavaParser(input);
        final String[] tokenNames = parser.getTokenNames();
        parser.compilationUnit();
        final AST ast = parser.getAST();
        modifyJavaASTintoGroovyAST(tokenNames, ast);
        final String[] groovyTokenNames = getGroovyTokenNames(input);
        groovifyFatJavaLikeGroovyAST(ast, groovyTokenNames);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Visitor visitor = new NodePrinter(new PrintStream(baos), groovyTokenNames);
        final AntlrASTProcessor traverser = new SourceCodeTraversal(visitor);
        traverser.process(ast);
        return new String(baos.toByteArray());
    }
    
    private static String[] getGroovyTokenNames(final String input) {
        GroovyRecognizer groovyParser = null;
        final SourceBuffer groovySourceBuffer = new SourceBuffer();
        final UnicodeEscapingReader groovyUnicodeReader = new UnicodeEscapingReader(new StringReader(input), groovySourceBuffer);
        final GroovyLexer groovyLexer = new GroovyLexer(groovyUnicodeReader);
        groovyUnicodeReader.setLexer(groovyLexer);
        groovyParser = GroovyRecognizer.make(groovyLexer);
        return groovyParser.getTokenNames();
    }
}
