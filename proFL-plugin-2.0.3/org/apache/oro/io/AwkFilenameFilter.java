// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.io;

import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.PatternCacheLRU;
import org.apache.oro.text.awk.AwkCompiler;
import org.apache.oro.text.awk.AwkMatcher;
import org.apache.oro.text.PatternCache;
import org.apache.oro.text.regex.PatternMatcher;

public class AwkFilenameFilter extends RegexFilenameFilter
{
    private static final PatternMatcher __MATCHER;
    private static final PatternCache __CACHE;
    
    public AwkFilenameFilter(final String s, final int n) {
        super(AwkFilenameFilter.__CACHE, AwkFilenameFilter.__MATCHER, s, n);
    }
    
    public AwkFilenameFilter(final String s) {
        super(AwkFilenameFilter.__CACHE, AwkFilenameFilter.__MATCHER, s);
    }
    
    public AwkFilenameFilter() {
        super(AwkFilenameFilter.__CACHE, AwkFilenameFilter.__MATCHER);
    }
    
    static {
        __MATCHER = new AwkMatcher();
        __CACHE = new PatternCacheLRU(new AwkCompiler());
    }
}
