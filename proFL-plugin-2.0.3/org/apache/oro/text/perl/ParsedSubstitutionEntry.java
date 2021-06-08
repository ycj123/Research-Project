// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.perl;

import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Pattern;

final class ParsedSubstitutionEntry
{
    int _numSubstitutions;
    Pattern _pattern;
    Perl5Substitution _substitution;
    
    ParsedSubstitutionEntry(final Pattern pattern, final Perl5Substitution substitution, final int numSubstitutions) {
        this._numSubstitutions = numSubstitutions;
        this._substitution = substitution;
        this._pattern = pattern;
    }
}
