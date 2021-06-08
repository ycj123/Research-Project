// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.File;
import javax.annotation.Nonnull;

public class MatchPattern
{
    private final String source;
    private final String regexPattern;
    private final String separator;
    private final String[] tokenized;
    
    private MatchPattern(@Nonnull final String source, @Nonnull final String separator) {
        this.regexPattern = (SelectorUtils.isRegexPrefixedPattern(source) ? source.substring("%regex[".length(), source.length() - "]".length()) : null);
        this.source = (SelectorUtils.isAntPrefixedPattern(source) ? source.substring("%ant[".length(), source.length() - "]".length()) : source);
        this.separator = separator;
        this.tokenized = tokenizePathToString(this.source, separator);
    }
    
    public boolean matchPath(final String str, final boolean isCaseSensitive) {
        if (this.regexPattern != null) {
            return str.matches(this.regexPattern);
        }
        return SelectorUtils.matchAntPathPattern(this, str, this.separator, isCaseSensitive);
    }
    
    boolean matchPath(final String str, final String[] strDirs, final boolean isCaseSensitive) {
        if (this.regexPattern != null) {
            return str.matches(this.regexPattern);
        }
        return SelectorUtils.matchAntPathPattern(this.getTokenizedPathString(), strDirs, isCaseSensitive);
    }
    
    public boolean matchPatternStart(@Nonnull final String str, final boolean isCaseSensitive) {
        if (this.regexPattern != null) {
            return true;
        }
        final String altStr = this.source.replace('\\', '/');
        return SelectorUtils.matchAntPathPatternStart(this, str, File.separator, isCaseSensitive) || SelectorUtils.matchAntPathPatternStart(this, altStr, "/", isCaseSensitive);
    }
    
    public String[] getTokenizedPathString() {
        return this.tokenized;
    }
    
    public boolean startsWith(final String string) {
        return this.source.startsWith(string);
    }
    
    static String[] tokenizePathToString(@Nonnull final String path, @Nonnull final String separator) {
        final List<String> ret = new ArrayList<String>();
        final StringTokenizer st = new StringTokenizer(path, separator);
        while (st.hasMoreTokens()) {
            ret.add(st.nextToken());
        }
        return ret.toArray(new String[ret.size()]);
    }
    
    public static MatchPattern fromString(final String source) {
        return new MatchPattern(source, File.separator);
    }
}
