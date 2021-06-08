// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.util.regex.Matcher;
import org.codehaus.groovy.groovydoc.GroovyRootDoc;
import java.util.regex.Pattern;
import org.codehaus.groovy.groovydoc.GroovyPackageDoc;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;
import org.codehaus.groovy.antlr.parser.GroovyLexer;
import groovyjarjarantlr.CharScanner;
import org.codehaus.groovy.antlr.java.JavaLexer;
import java.io.Reader;
import org.codehaus.groovy.antlr.UnicodeEscapingReader;
import java.io.StringReader;
import org.codehaus.groovy.antlr.parser.GroovyRecognizer;
import org.codehaus.groovy.antlr.AntlrASTProcessor;
import org.codehaus.groovy.antlr.treewalker.Visitor;
import groovyjarjarantlr.collections.AST;
import org.codehaus.groovy.antlr.java.JavaRecognizer;
import org.codehaus.groovy.antlr.treewalker.SourceCodeTraversal;
import org.codehaus.groovy.antlr.java.Groovifier;
import org.codehaus.groovy.antlr.treewalker.PreOrderTraversal;
import org.codehaus.groovy.antlr.java.Java2GroovyConverter;
import org.codehaus.groovy.antlr.SourceBuffer;
import groovyjarjarantlr.TokenStreamException;
import groovyjarjarantlr.RecognitionException;
import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import java.util.Map;
import java.util.Properties;
import java.util.List;

public class GroovyRootDocBuilder
{
    private static final char FS = '/';
    private List<LinkArgument> links;
    private final GroovyDocTool tool;
    private final String[] sourcepaths;
    private final SimpleGroovyRootDoc rootDoc;
    private final Properties properties;
    
    public GroovyRootDocBuilder(final GroovyDocTool tool, final String[] sourcepaths, final List<LinkArgument> links, final Properties properties) {
        this.tool = tool;
        this.sourcepaths = sourcepaths;
        this.links = links;
        this.rootDoc = new SimpleGroovyRootDoc("root");
        this.properties = properties;
    }
    
    public Map<String, GroovyClassDoc> getClassDocsFromSingleSource(final String packagePath, final String file, final String src) throws RecognitionException, TokenStreamException {
        if (file.indexOf(".java") > 0) {
            return this.parseJava(packagePath, file, src);
        }
        if (file.indexOf(".sourcefile") > 0) {
            return this.parseJava(packagePath, file, src);
        }
        return this.parseGroovy(packagePath, file, src);
    }
    
    private Map<String, GroovyClassDoc> parseJava(final String packagePath, final String file, final String src) throws RecognitionException, TokenStreamException {
        final SourceBuffer sourceBuffer = new SourceBuffer();
        final JavaRecognizer parser = this.getJavaParser(src, sourceBuffer);
        final String[] tokenNames = parser.getTokenNames();
        try {
            parser.compilationUnit();
        }
        catch (OutOfMemoryError e) {
            System.out.println("Out of memory while processing: " + packagePath + "/" + file);
            throw e;
        }
        final AST ast = parser.getAST();
        final Visitor java2groovyConverter = new Java2GroovyConverter(tokenNames);
        final AntlrASTProcessor java2groovyTraverser = new PreOrderTraversal(java2groovyConverter);
        java2groovyTraverser.process(ast);
        final Visitor groovifier = new Groovifier(tokenNames, false);
        final AntlrASTProcessor groovifierTraverser = new PreOrderTraversal(groovifier);
        groovifierTraverser.process(ast);
        final Visitor visitor = new SimpleGroovyClassDocAssembler(packagePath, file, sourceBuffer, this.links, this.properties, false);
        final AntlrASTProcessor traverser = new SourceCodeTraversal(visitor);
        traverser.process(ast);
        return ((SimpleGroovyClassDocAssembler)visitor).getGroovyClassDocs();
    }
    
    private Map<String, GroovyClassDoc> parseGroovy(final String packagePath, final String file, final String src) throws RecognitionException, TokenStreamException {
        final SourceBuffer sourceBuffer = new SourceBuffer();
        final GroovyRecognizer parser = this.getGroovyParser(src, sourceBuffer);
        try {
            parser.compilationUnit();
        }
        catch (OutOfMemoryError e) {
            System.out.println("Out of memory while processing: " + packagePath + "/" + file);
            throw e;
        }
        final AST ast = parser.getAST();
        final Visitor visitor = new SimpleGroovyClassDocAssembler(packagePath, file, sourceBuffer, this.links, this.properties, true);
        final AntlrASTProcessor traverser = new SourceCodeTraversal(visitor);
        traverser.process(ast);
        return ((SimpleGroovyClassDocAssembler)visitor).getGroovyClassDocs();
    }
    
    private JavaRecognizer getJavaParser(final String input, final SourceBuffer sourceBuffer) {
        final UnicodeEscapingReader unicodeReader = new UnicodeEscapingReader(new StringReader(input), sourceBuffer);
        final JavaLexer lexer = new JavaLexer(unicodeReader);
        unicodeReader.setLexer(lexer);
        final JavaRecognizer parser = JavaRecognizer.make(lexer);
        parser.setSourceBuffer(sourceBuffer);
        return parser;
    }
    
    private GroovyRecognizer getGroovyParser(final String input, final SourceBuffer sourceBuffer) {
        final UnicodeEscapingReader unicodeReader = new UnicodeEscapingReader(new StringReader(input), sourceBuffer);
        final GroovyLexer lexer = new GroovyLexer(unicodeReader);
        unicodeReader.setLexer(lexer);
        final GroovyRecognizer parser = GroovyRecognizer.make(lexer);
        parser.setSourceBuffer(sourceBuffer);
        return parser;
    }
    
    public void buildTree(final List<String> filenames) throws IOException, RecognitionException, TokenStreamException {
        this.setOverview();
        final List<File> sourcepathFiles = new ArrayList<File>();
        if (this.sourcepaths != null) {
            for (final String sourcepath : this.sourcepaths) {
                sourcepathFiles.add(new File(sourcepath).getAbsoluteFile());
            }
        }
        for (final String filename : filenames) {
            File srcFile = new File(filename);
            if (srcFile.exists()) {
                this.processFile(filename, srcFile, true);
            }
            else {
                for (final File spath : sourcepathFiles) {
                    srcFile = new File(spath, filename);
                    if (srcFile.exists()) {
                        this.processFile(filename, srcFile, false);
                        break;
                    }
                }
            }
        }
    }
    
    private void setOverview() {
        final String path = this.properties.getProperty("overviewFile");
        if (path != null && path.length() > 0) {
            try {
                final String content = DefaultGroovyMethods.getText(new File(path));
                this.calcThenSetOverviewDescription(content);
            }
            catch (IOException e) {
                System.err.println("Unable to load overview file: " + e.getMessage());
            }
        }
    }
    
    private void processFile(final String filename, final File srcFile, final boolean isAbsolute) throws IOException {
        final String src = DefaultGroovyMethods.getText(srcFile);
        String packagePath = isAbsolute ? "DefaultPackage" : this.tool.getPath(filename).replace('\\', '/');
        final String file = this.tool.getFile(filename);
        SimpleGroovyPackageDoc packageDoc = null;
        if (!isAbsolute) {
            packageDoc = (SimpleGroovyPackageDoc)this.rootDoc.packageNamed(packagePath);
        }
        if (filename.endsWith("package.html") || filename.endsWith("package-info.java") || filename.endsWith("package-info.groovy")) {
            if (packageDoc == null) {
                packageDoc = new SimpleGroovyPackageDoc(packagePath);
            }
            this.processPackageInfo(src, filename, packageDoc);
            this.rootDoc.put(packagePath, packageDoc);
            return;
        }
        try {
            final Map<String, GroovyClassDoc> classDocs = this.getClassDocsFromSingleSource(packagePath, file, src);
            this.rootDoc.putAllClasses(classDocs);
            if (isAbsolute) {
                final Iterator<Map.Entry<String, GroovyClassDoc>> iterator = classDocs.entrySet().iterator();
                if (iterator.hasNext()) {
                    final Map.Entry<String, GroovyClassDoc> docEntry = iterator.next();
                    final String fullPath = docEntry.getValue().getFullPathName();
                    final int slash = fullPath.lastIndexOf(47);
                    if (slash > 0) {
                        packagePath = fullPath.substring(0, slash);
                    }
                    packageDoc = (SimpleGroovyPackageDoc)this.rootDoc.packageNamed(packagePath);
                }
            }
            if (packageDoc == null) {
                packageDoc = new SimpleGroovyPackageDoc(packagePath);
            }
            packageDoc.putAll(classDocs);
            this.rootDoc.put(packagePath, packageDoc);
        }
        catch (RecognitionException e) {
            System.err.println("ignored due to RecognitionException: " + filename + " [" + e.getMessage() + "]");
        }
        catch (TokenStreamException e2) {
            System.err.println("ignored due to TokenStreamException: " + filename + " [" + e2.getMessage() + "]");
        }
    }
    
    void processPackageInfo(final String src, final String filename, final SimpleGroovyPackageDoc packageDoc) {
        final String description = this.calcThenSetPackageDescription(src, filename, packageDoc);
        this.calcThenSetSummary(description, packageDoc);
    }
    
    private String calcThenSetPackageDescription(final String src, final String filename, final SimpleGroovyPackageDoc packageDoc) {
        String description;
        if (filename.endsWith(".html")) {
            description = this.scrubOffExcessiveTags(src);
            description = this.pruneTagFromFront(description, "p");
            description = this.pruneTagFromEnd(description, "/p");
        }
        else {
            description = this.trimPackageAndComments(src);
        }
        description = this.replaceTags(description, packageDoc);
        packageDoc.setDescription(description);
        return description;
    }
    
    private String replaceTags(final String orig, final SimpleGroovyPackageDoc packageDoc) {
        String result = orig.replaceAll("(?m)^\\s*\\*", "");
        result = this.replaceAllTags(result, "", "", SimpleGroovyClassDoc.LINK_REGEX, packageDoc);
        result = this.replaceAllTags(result, "<TT>", "</TT>", SimpleGroovyClassDoc.CODE_REGEX, packageDoc);
        result = this.replaceAllTags(result + "@endMarker", "<DL><DT><B>$1:</B></DT><DD>", "</DD></DL>", SimpleGroovyClassDoc.TAG_REGEX, packageDoc);
        result = result.substring(0, result.length() - 10);
        return SimpleGroovyClassDoc.decodeSpecialSymbols(result);
    }
    
    private String replaceAllTags(final String self, final String s1, final String s2, final Pattern regex, final SimpleGroovyPackageDoc packageDoc) {
        final Matcher matcher = regex.matcher(self);
        if (matcher.find()) {
            matcher.reset();
            final StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                final String tagname = matcher.group(1);
                if (tagname.equals("see") || tagname.equals("link")) {
                    matcher.appendReplacement(sb, s1 + SimpleGroovyClassDoc.getDocUrl(SimpleGroovyClassDoc.encodeSpecialSymbols(matcher.group(2)), false, this.links, packageDoc.getRelativeRootPath(), this.rootDoc, null) + s2);
                }
                else {
                    if (tagname.equals("interface")) {
                        continue;
                    }
                    matcher.appendReplacement(sb, s1 + SimpleGroovyClassDoc.encodeSpecialSymbols(matcher.group(2)) + s2);
                }
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        return self;
    }
    
    private void calcThenSetSummary(final String src, final SimpleGroovyPackageDoc packageDoc) {
        packageDoc.setSummary(SimpleGroovyDoc.calculateFirstSentence(src));
    }
    
    private void calcThenSetOverviewDescription(final String src) {
        final String description = this.scrubOffExcessiveTags(src);
        this.rootDoc.setDescription(description);
    }
    
    private String trimPackageAndComments(final String src) {
        return src.replaceFirst("(?sm)^package.*", "").replaceFirst("(?sm)/.*\\*\\*(.*)\\*/", "$1").replaceAll("(?m)^\\s*\\*", "");
    }
    
    private String scrubOffExcessiveTags(final String src) {
        String description = this.pruneTagFromFront(src, "html");
        description = this.pruneTagFromFront(description, "/head");
        description = this.pruneTagFromFront(description, "body");
        description = this.pruneTagFromEnd(description, "/html");
        return this.pruneTagFromEnd(description, "/body");
    }
    
    private String pruneTagFromFront(final String description, final String tag) {
        final int index = Math.max(this.indexOfTag(description, tag.toLowerCase()), this.indexOfTag(description, tag.toUpperCase()));
        if (index < 0) {
            return description;
        }
        return description.substring(index);
    }
    
    private String pruneTagFromEnd(final String description, final String tag) {
        final int index = Math.max(description.lastIndexOf("<" + tag.toLowerCase() + ">"), description.lastIndexOf("<" + tag.toUpperCase() + ">"));
        if (index < 0) {
            return description;
        }
        return description.substring(0, index);
    }
    
    private int indexOfTag(final String text, final String tag) {
        int pos = text.indexOf("<" + tag + ">");
        if (pos > 0) {
            pos += tag.length() + 2;
        }
        return pos;
    }
    
    public GroovyRootDoc getRootDoc() {
        this.rootDoc.resolve();
        return this.rootDoc;
    }
}
