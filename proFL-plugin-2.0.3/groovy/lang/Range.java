// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.List;

public interface Range<T extends Comparable> extends List<T>
{
    Comparable getFrom();
    
    Comparable getTo();
    
    boolean isReverse();
    
    boolean containsWithinBounds(final Object p0);
    
    void step(final int p0, final Closure p1);
    
    List<T> step(final int p0);
    
    String inspect();
}
