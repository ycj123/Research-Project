// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.util.BitSet;

abstract class SyntaxNode
{
    abstract boolean _nullable();
    
    abstract BitSet _firstPosition();
    
    abstract BitSet _lastPosition();
    
    abstract void _followPosition(final BitSet[] p0, final SyntaxNode[] p1);
    
    abstract SyntaxNode _clone(final int[] p0);
}
