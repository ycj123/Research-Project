// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;

public interface PatternCache
{
    Pattern addPattern(final String p0) throws MalformedPatternException;
    
    Pattern addPattern(final String p0, final int p1) throws MalformedPatternException;
    
    Pattern getPattern(final String p0) throws MalformedCachePatternException;
    
    Pattern getPattern(final String p0, final int p1) throws MalformedCachePatternException;
    
    int size();
    
    int capacity();
}
