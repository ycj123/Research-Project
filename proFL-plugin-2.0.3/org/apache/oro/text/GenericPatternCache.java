// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.util.Cache;
import org.apache.oro.text.regex.PatternCompiler;

public abstract class GenericPatternCache implements PatternCache
{
    PatternCompiler _compiler;
    Cache _cache;
    public static final int DEFAULT_CAPACITY = 20;
    
    GenericPatternCache(final Cache cache, final PatternCompiler compiler) {
        this._cache = cache;
        this._compiler = compiler;
    }
    
    public final synchronized Pattern addPattern(final String s, final int n) throws MalformedPatternException {
        final Object element = this._cache.getElement(s);
        if (element != null) {
            final Pattern pattern = (Pattern)element;
            if (pattern.getOptions() == n) {
                return pattern;
            }
        }
        final Pattern compile = this._compiler.compile(s, n);
        this._cache.addElement(s, compile);
        return compile;
    }
    
    public final synchronized Pattern addPattern(final String s) throws MalformedPatternException {
        return this.addPattern(s, 0);
    }
    
    public final synchronized Pattern getPattern(final String str, final int n) throws MalformedCachePatternException {
        Pattern addPattern;
        try {
            addPattern = this.addPattern(str, n);
        }
        catch (MalformedPatternException ex) {
            throw new MalformedCachePatternException("Invalid expression: " + str + "\n" + ex.getMessage());
        }
        return addPattern;
    }
    
    public final synchronized Pattern getPattern(final String s) throws MalformedCachePatternException {
        return this.getPattern(s, 0);
    }
    
    public final int size() {
        return this._cache.size();
    }
    
    public final int capacity() {
        return this._cache.capacity();
    }
}
