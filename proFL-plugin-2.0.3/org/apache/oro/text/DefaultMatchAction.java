// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

final class DefaultMatchAction implements MatchAction
{
    public void processMatch(final MatchActionInfo matchActionInfo) {
        matchActionInfo.output.println(matchActionInfo.line);
    }
}
