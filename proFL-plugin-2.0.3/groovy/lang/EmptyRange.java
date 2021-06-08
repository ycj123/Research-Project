// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.AbstractList;

public class EmptyRange extends AbstractList implements Range
{
    protected Comparable at;
    
    public EmptyRange(final Comparable at) {
        this.at = at;
    }
    
    public Comparable getFrom() {
        return this.at;
    }
    
    public Comparable getTo() {
        return this.at;
    }
    
    public boolean isReverse() {
        return false;
    }
    
    public boolean containsWithinBounds(final Object o) {
        return false;
    }
    
    public String inspect() {
        return InvokerHelper.inspect(this.at) + "..<" + InvokerHelper.inspect(this.at);
    }
    
    @Override
    public String toString() {
        return (null == this.at) ? "null..<null" : (this.at.toString() + "..<" + this.at.toString());
    }
    
    @Override
    public int size() {
        return 0;
    }
    
    @Override
    public Object get(final int index) {
        throw new IndexOutOfBoundsException("can't get values from Empty Ranges");
    }
    
    @Override
    public boolean add(final Object o) {
        throw new UnsupportedOperationException("cannot add to Empty Ranges");
    }
    
    @Override
    public boolean addAll(final int index, final Collection c) {
        throw new UnsupportedOperationException("cannot add to Empty Ranges");
    }
    
    @Override
    public boolean addAll(final Collection c) {
        throw new UnsupportedOperationException("cannot add to Empty Ranges");
    }
    
    @Override
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException("cannot remove from Empty Ranges");
    }
    
    @Override
    public Object remove(final int index) {
        throw new UnsupportedOperationException("cannot remove from Empty Ranges");
    }
    
    @Override
    public boolean removeAll(final Collection c) {
        throw new UnsupportedOperationException("cannot remove from Empty Ranges");
    }
    
    @Override
    public boolean retainAll(final Collection c) {
        throw new UnsupportedOperationException("cannot retainAll in Empty Ranges");
    }
    
    @Override
    public Object set(final int index, final Object element) {
        throw new UnsupportedOperationException("cannot set in Empty Ranges");
    }
    
    public void step(final int step, final Closure closure) {
    }
    
    public List step(final int step) {
        return new ArrayList();
    }
}
