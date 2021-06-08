// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

import java.io.File;
import javax.annotation.Nonnull;

public class MatchPatterns
{
    private final MatchPattern[] patterns;
    
    private MatchPatterns(@Nonnull final MatchPattern... patterns) {
        this.patterns = patterns;
    }
    
    public boolean matches(@Nonnull final String name, final boolean isCaseSensitive) {
        final String[] tokenized = MatchPattern.tokenizePathToString(name, File.separator);
        for (final MatchPattern pattern : this.patterns) {
            if (pattern.matchPath(name, tokenized, isCaseSensitive)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean matchesPatternStart(@Nonnull final String name, final boolean isCaseSensitive) {
        for (final MatchPattern includesPattern : this.patterns) {
            if (includesPattern.matchPatternStart(name, isCaseSensitive)) {
                return true;
            }
        }
        return false;
    }
    
    public static MatchPatterns from(@Nonnull final String... sources) {
        final int length = sources.length;
        final MatchPattern[] result = new MatchPattern[length];
        for (int i = 0; i < length; ++i) {
            result[i] = MatchPattern.fromString(sources[i]);
        }
        return new MatchPatterns(result);
    }
}
