// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import org.apache.oro.text.regex.MatchResult;

final class AwkMatchResult implements MatchResult
{
    private int __matchBeginOffset;
    private int __length;
    private String __match;
    
    AwkMatchResult(final String _match, final int _matchBeginOffset) {
        this.__match = _match;
        this.__length = _match.length();
        this.__matchBeginOffset = _matchBeginOffset;
    }
    
    void _incrementMatchBeginOffset(final int n) {
        this.__matchBeginOffset += n;
    }
    
    public int length() {
        return this.__length;
    }
    
    public int groups() {
        return 1;
    }
    
    public String group(final int n) {
        return (n == 0) ? this.__match : null;
    }
    
    public int begin(final int n) {
        return (n == 0) ? 0 : -1;
    }
    
    public int end(final int n) {
        return (n == 0) ? this.__length : -1;
    }
    
    public int beginOffset(final int n) {
        return (n == 0) ? this.__matchBeginOffset : -1;
    }
    
    public int endOffset(final int n) {
        return (n == 0) ? (this.__matchBeginOffset + this.__length) : -1;
    }
    
    public String toString() {
        return this.group(0);
    }
}
