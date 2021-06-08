// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.regex.Matcher;

public class RegexSupport
{
    private static final ThreadLocal CURRENT_MATCHER;
    
    public static Matcher getLastMatcher() {
        return RegexSupport.CURRENT_MATCHER.get();
    }
    
    public static void setLastMatcher(final Matcher matcher) {
        RegexSupport.CURRENT_MATCHER.set(matcher);
    }
    
    static {
        CURRENT_MATCHER = new ThreadLocal();
    }
}
