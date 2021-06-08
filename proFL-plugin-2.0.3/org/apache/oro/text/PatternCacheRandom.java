// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.util.Cache;
import org.apache.oro.util.CacheRandom;
import org.apache.oro.text.regex.PatternCompiler;

public final class PatternCacheRandom extends GenericPatternCache
{
    public PatternCacheRandom(final int n, final PatternCompiler patternCompiler) {
        super(new CacheRandom(n), patternCompiler);
    }
    
    public PatternCacheRandom(final PatternCompiler patternCompiler) {
        this(20, patternCompiler);
    }
    
    public PatternCacheRandom(final int n) {
        this(n, new Perl5Compiler());
    }
    
    public PatternCacheRandom() {
        this(20);
    }
}
