// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.util.Cache;
import org.apache.oro.util.CacheFIFO;
import org.apache.oro.text.regex.PatternCompiler;

public final class PatternCacheFIFO extends GenericPatternCache
{
    public PatternCacheFIFO(final int n, final PatternCompiler patternCompiler) {
        super(new CacheFIFO(n), patternCompiler);
    }
    
    public PatternCacheFIFO(final PatternCompiler patternCompiler) {
        this(20, patternCompiler);
    }
    
    public PatternCacheFIFO(final int n) {
        this(n, new Perl5Compiler());
    }
    
    public PatternCacheFIFO() {
        this(20);
    }
}
