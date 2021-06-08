// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.io;

import org.apache.oro.text.PatternCacheLRU;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.PatternCache;
import org.apache.oro.text.regex.PatternMatcher;

public class Perl5FilenameFilter extends RegexFilenameFilter
{
    private static final PatternMatcher __MATCHER;
    private static final PatternCache __CACHE;
    
    public Perl5FilenameFilter(final String s, final int n) {
        super(Perl5FilenameFilter.__CACHE, Perl5FilenameFilter.__MATCHER, s, n);
    }
    
    public Perl5FilenameFilter(final String s) {
        super(Perl5FilenameFilter.__CACHE, Perl5FilenameFilter.__MATCHER, s);
    }
    
    public Perl5FilenameFilter() {
        super(Perl5FilenameFilter.__CACHE, Perl5FilenameFilter.__MATCHER);
    }
    
    static {
        __MATCHER = new Perl5Matcher();
        __CACHE = new PatternCacheLRU();
    }
}
