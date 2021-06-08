// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.util.Cache;
import org.apache.oro.util.CacheLRU;
import org.apache.oro.text.regex.PatternCompiler;

public final class PatternCacheLRU extends GenericPatternCache
{
    public PatternCacheLRU(final int n, final PatternCompiler patternCompiler) {
        super(new CacheLRU(n), patternCompiler);
    }
    
    public PatternCacheLRU(final PatternCompiler patternCompiler) {
        this(20, patternCompiler);
    }
    
    public PatternCacheLRU(final int n) {
        this(n, new Perl5Compiler());
    }
    
    public PatternCacheLRU() {
        this(20);
    }
}
