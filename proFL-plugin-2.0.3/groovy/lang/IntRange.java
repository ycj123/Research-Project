// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.IteratorClosureAdapter;
import java.util.Collection;
import java.math.BigInteger;
import java.util.List;
import java.util.Iterator;
import java.util.AbstractList;

public class IntRange extends AbstractList<Integer> implements Range<Integer>
{
    private int from;
    private int to;
    private boolean reverse;
    
    public IntRange(final int from, final int to) {
        if (from > to) {
            this.from = to;
            this.to = from;
            this.reverse = true;
        }
        else {
            this.from = from;
            this.to = to;
        }
        if (this.to - this.from >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("range must have no more than 2147483647 elements");
        }
    }
    
    protected IntRange(final int from, final int to, final boolean reverse) {
        if (from > to) {
            throw new IllegalArgumentException("'from' must be less than or equal to 'to'");
        }
        this.from = from;
        this.to = to;
        this.reverse = reverse;
    }
    
    @Override
    public boolean equals(final Object that) {
        return (that instanceof IntRange) ? this.equals((IntRange)that) : super.equals(that);
    }
    
    public boolean equals(final IntRange that) {
        return that != null && this.reverse == that.reverse && this.from == that.from && this.to == that.to;
    }
    
    public Comparable getFrom() {
        return this.from;
    }
    
    public Comparable getTo() {
        return this.to;
    }
    
    public int getFromInt() {
        return this.from;
    }
    
    public int getToInt() {
        return this.to;
    }
    
    public boolean isReverse() {
        return this.reverse;
    }
    
    public boolean containsWithinBounds(final Object o) {
        return this.contains(o);
    }
    
    @Override
    public Integer get(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " should not be negative");
        }
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + " too big for range: " + this);
        }
        final int value = this.reverse ? (this.to - index) : (index + this.from);
        return value;
    }
    
    @Override
    public int size() {
        return this.to - this.from + 1;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new IntRangeIterator();
    }
    
    @Override
    public List<Integer> subList(final int fromIndex, final int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > this.size()) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex == toIndex) {
            return (List<Integer>)new EmptyRange(this.from);
        }
        return new IntRange(fromIndex + this.from, toIndex + this.from - 1, this.reverse);
    }
    
    @Override
    public String toString() {
        return this.reverse ? ("" + this.to + ".." + this.from) : ("" + this.from + ".." + this.to);
    }
    
    public String inspect() {
        return this.toString();
    }
    
    @Override
    public boolean contains(final Object value) {
        if (value instanceof Integer) {
            final Integer integer = (Integer)value;
            final int i = integer;
            return i >= this.from && i <= this.to;
        }
        if (value instanceof BigInteger) {
            final BigInteger bigint = (BigInteger)value;
            return bigint.compareTo(BigInteger.valueOf(this.from)) >= 0 && bigint.compareTo(BigInteger.valueOf(this.to)) <= 0;
        }
        return false;
    }
    
    @Override
    public boolean containsAll(final Collection other) {
        if (other instanceof IntRange) {
            final IntRange range = (IntRange)other;
            return this.from <= range.from && range.to <= this.to;
        }
        return super.containsAll(other);
    }
    
    public void step(int step, final Closure closure) {
        if (step != 0) {
            if (this.reverse) {
                step = -step;
            }
            if (step > 0) {
                for (int value = this.from; value <= this.to; value += step) {
                    closure.call(value);
                }
            }
            else {
                for (int value = this.to; value >= this.from; value += step) {
                    closure.call(value);
                }
            }
            return;
        }
        if (this.from != this.to) {
            throw new GroovyRuntimeException("Infinite loop detected due to step size of 0");
        }
    }
    
    public List<Integer> step(final int step) {
        final IteratorClosureAdapter<Integer> adapter = new IteratorClosureAdapter<Integer>(this);
        this.step(step, adapter);
        return adapter.asList();
    }
    
    private class IntRangeIterator implements Iterator<Integer>
    {
        private int index;
        private int size;
        private int value;
        
        private IntRangeIterator() {
            this.size = IntRange.this.size();
            this.value = (IntRange.this.reverse ? IntRange.this.to : IntRange.this.from);
        }
        
        public boolean hasNext() {
            return this.index < this.size;
        }
        
        public Integer next() {
            if (this.index++ > 0) {
                if (this.index > this.size) {
                    return null;
                }
                if (IntRange.this.reverse) {
                    --this.value;
                }
                else {
                    ++this.value;
                }
            }
            return this.value;
        }
        
        public void remove() {
            IntRange.this.remove(this.index);
        }
    }
}
