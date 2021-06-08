// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.io;

import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.PatternCacheLRU;
import org.apache.oro.text.GlobCompiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.PatternCache;
import org.apache.oro.text.regex.PatternMatcher;

public class GlobFilenameFilter extends RegexFilenameFilter
{
    private static final PatternMatcher __MATCHER;
    private static final PatternCache __CACHE;
    
    public GlobFilenameFilter(final String s, final int n) {
        super(GlobFilenameFilter.__CACHE, GlobFilenameFilter.__MATCHER, s, n);
    }
    
    public GlobFilenameFilter(final String s) {
        super(GlobFilenameFilter.__CACHE, GlobFilenameFilter.__MATCHER, s);
    }
    
    public GlobFilenameFilter() {
        super(GlobFilenameFilter.__CACHE, GlobFilenameFilter.__MATCHER);
    }
    
    static {
        __MATCHER = new Perl5Matcher();
        __CACHE = new PatternCacheLRU(new GlobCompiler());
    }
}
