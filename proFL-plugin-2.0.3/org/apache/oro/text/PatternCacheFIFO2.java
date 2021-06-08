// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.util.Cache;
import org.apache.oro.util.CacheFIFO2;
import org.apache.oro.text.regex.PatternCompiler;

public final class PatternCacheFIFO2 extends GenericPatternCache
{
    public PatternCacheFIFO2(final int n, final PatternCompiler patternCompiler) {
        super(new CacheFIFO2(n), patternCompiler);
    }
    
    public PatternCacheFIFO2(final PatternCompiler patternCompiler) {
        this(20, patternCompiler);
    }
    
    public PatternCacheFIFO2(final int n) {
        this(n, new Perl5Compiler());
    }
    
    public PatternCacheFIFO2() {
        this(20);
    }
}
