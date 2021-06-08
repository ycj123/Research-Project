// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.io;

import java.io.File;
import org.apache.oro.text.MalformedCachePatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.PatternCache;
import java.io.FileFilter;
import java.io.FilenameFilter;

public abstract class RegexFilenameFilter implements FilenameFilter, FileFilter
{
    PatternCache _cache;
    PatternMatcher _matcher;
    Pattern _pattern;
    
    RegexFilenameFilter(final PatternCache cache, final PatternMatcher matcher, final String filterExpression) {
        this._cache = cache;
        this._matcher = matcher;
        this.setFilterExpression(filterExpression);
    }
    
    RegexFilenameFilter(final PatternCache cache, final PatternMatcher matcher, final String s, final int n) {
        this._cache = cache;
        this._matcher = matcher;
        this.setFilterExpression(s, n);
    }
    
    RegexFilenameFilter(final PatternCache patternCache, final PatternMatcher patternMatcher) {
        this(patternCache, patternMatcher, "");
    }
    
    public void setFilterExpression(final String s) throws MalformedCachePatternException {
        this._pattern = this._cache.getPattern(s);
    }
    
    public void setFilterExpression(final String s, final int n) throws MalformedCachePatternException {
        this._pattern = this._cache.getPattern(s, n);
    }
    
    public boolean accept(final File file, final String s) {
        synchronized (this._matcher) {
            return this._matcher.matches(s, this._pattern);
        }
    }
    
    public boolean accept(final File file) {
        synchronized (this._matcher) {
            return this._matcher.matches(file.getName(), this._pattern);
        }
    }
}
