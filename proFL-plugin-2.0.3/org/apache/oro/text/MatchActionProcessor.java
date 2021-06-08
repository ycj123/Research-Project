// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import java.util.Collection;
import org.apache.oro.text.regex.Util;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Compiler;
import java.util.Vector;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Pattern;

public final class MatchActionProcessor
{
    private Pattern __fieldSeparator;
    private PatternCompiler __compiler;
    private PatternMatcher __matcher;
    private Vector __patterns;
    private Vector __actions;
    private MatchAction __defaultAction;
    
    public MatchActionProcessor(final PatternCompiler _compiler, final PatternMatcher _matcher) {
        this.__fieldSeparator = null;
        this.__patterns = new Vector();
        this.__actions = new Vector();
        this.__defaultAction = new DefaultMatchAction();
        this.__compiler = _compiler;
        this.__matcher = _matcher;
    }
    
    public MatchActionProcessor() {
        this(new Perl5Compiler(), new Perl5Matcher());
    }
    
    public void addAction(final String s, final int n, final MatchAction obj) throws MalformedPatternException {
        if (s != null) {
            this.__patterns.addElement(this.__compiler.compile(s, n));
        }
        else {
            this.__patterns.addElement(null);
        }
        this.__actions.addElement(obj);
    }
    
    public void addAction(final String s, final int n) throws MalformedPatternException {
        this.addAction(s, n, this.__defaultAction);
    }
    
    public void addAction(final String s) throws MalformedPatternException {
        this.addAction(s, 0);
    }
    
    public void addAction(final String s, final MatchAction matchAction) throws MalformedPatternException {
        this.addAction(s, 0, matchAction);
    }
    
    public void setFieldSeparator(final String s, final int n) throws MalformedPatternException {
        if (s == null) {
            this.__fieldSeparator = null;
            return;
        }
        this.__fieldSeparator = this.__compiler.compile(s, n);
    }
    
    public void setFieldSeparator(final String s) throws MalformedPatternException {
        this.setFieldSeparator(s, 0);
    }
    
    public void processMatches(final InputStream in, final OutputStream out, final String charsetName) throws IOException {
        this.processMatches(new InputStreamReader(in, charsetName), new OutputStreamWriter(out));
    }
    
    public void processMatches(final InputStream in, final OutputStream out) throws IOException {
        this.processMatches(new InputStreamReader(in), new OutputStreamWriter(out));
    }
    
    public void processMatches(final Reader in, final Writer out) throws IOException {
        final LineNumberReader input = new LineNumberReader(in);
        final PrintWriter output = new PrintWriter(out);
        final MatchActionInfo matchActionInfo = new MatchActionInfo();
        final ArrayList list = new ArrayList();
        matchActionInfo.matcher = this.__matcher;
        matchActionInfo.fieldSeparator = this.__fieldSeparator;
        matchActionInfo.input = input;
        matchActionInfo.output = output;
        matchActionInfo.fields = null;
        final int size = this.__patterns.size();
        matchActionInfo.lineNumber = 0;
        while ((matchActionInfo.line = input.readLine()) != null) {
            matchActionInfo.charLine = matchActionInfo.line.toCharArray();
            for (int i = 0; i < size; ++i) {
                if (this.__patterns.elementAt(i) != null) {
                    final Pattern pattern = this.__patterns.elementAt(i);
                    if (this.__matcher.contains(matchActionInfo.charLine, pattern)) {
                        matchActionInfo.match = this.__matcher.getMatch();
                        matchActionInfo.lineNumber = input.getLineNumber();
                        matchActionInfo.pattern = pattern;
                        if (this.__fieldSeparator != null) {
                            list.clear();
                            Util.split(list, this.__matcher, this.__fieldSeparator, matchActionInfo.line);
                            matchActionInfo.fields = list;
                        }
                        else {
                            matchActionInfo.fields = null;
                        }
                        ((MatchAction)this.__actions.elementAt(i)).processMatch(matchActionInfo);
                    }
                }
                else {
                    matchActionInfo.match = null;
                    matchActionInfo.lineNumber = input.getLineNumber();
                    if (this.__fieldSeparator != null) {
                        list.clear();
                        Util.split(list, this.__matcher, this.__fieldSeparator, matchActionInfo.line);
                        matchActionInfo.fields = list;
                    }
                    else {
                        matchActionInfo.fields = null;
                    }
                    ((MatchAction)this.__actions.elementAt(i)).processMatch(matchActionInfo);
                }
            }
        }
        output.flush();
        input.close();
    }
}
