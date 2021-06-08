// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import java.io.BufferedReader;
import java.io.PrintWriter;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.PatternMatcher;
import java.util.List;
import org.apache.oro.text.regex.Pattern;

public final class MatchActionInfo
{
    public int lineNumber;
    public String line;
    public char[] charLine;
    public Pattern fieldSeparator;
    public List fields;
    public PatternMatcher matcher;
    public Pattern pattern;
    public MatchResult match;
    public PrintWriter output;
    public BufferedReader input;
}
