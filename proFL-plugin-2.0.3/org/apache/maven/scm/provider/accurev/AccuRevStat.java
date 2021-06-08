// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import java.util.regex.Pattern;

public enum AccuRevStat
{
    DEFUNCT("-D", "defunct"), 
    MODIFIED("-m", "modified"), 
    KEPT("-k", "kept"), 
    MISSING("-M", "missing"), 
    EXTERNAL("-x", "external");
    
    private String statArg;
    private Pattern matchPattern;
    
    private AccuRevStat(final String statArg, final String indicator) {
        final String pattern = "\\s*(\\S+)\\s+.*\\(" + indicator + "\\).*";
        this.matchPattern = Pattern.compile(pattern);
        this.statArg = statArg;
    }
    
    public String getStatArg() {
        return this.statArg;
    }
    
    public Pattern getMatchPattern() {
        return this.matchPattern;
    }
}
