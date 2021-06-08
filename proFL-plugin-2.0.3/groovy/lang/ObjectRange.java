// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.IteratorClosureAdapter;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.Iterator;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.AbstractList;

public class ObjectRange extends AbstractList implements Range
{
    private Comparable from;
    private Comparable to;
    private int size;
    private final boolean reverse;
    
    public ObjectRange(final Comparable from, final Comparable to) {
        this.size = -1;
        if (from == null) {
            throw new IllegalArgumentException("Must specify a non-null value for the 'from' index in a Range");
        }
        if (to == null) {
            throw new IllegalArgumentException("Must specify a non-null value for the 'to' index in a Range");
        }
        try {
            this.reverse = ScriptBytecodeAdapter.compareGreaterThan(from, to);
        }
        catch (ClassCastException cce) {
            throw new IllegalArgumentException("Unable to create range due to incompatible types: " + from.getClass().getSimpleName() + ".." + to.getClass().getSimpleName() + " (possible missing brackets around range?)", cce);
        }
        if (this.reverse) {
            this.constructorHelper(to, from);
        }
        else {
            this.constructorHelper(from, to);
        }
    }
    
    public ObjectRange(final Comparable from, final Comparable to, final boolean reverse) {
        this.size = -1;
        this.constructorHelper(from, to);
        this.reverse = reverse;
    }
    
    private void constructorHelper(Comparable from, Comparable to) {
        if (from instanceof Short) {
            from = from;
        }
        else if (from instanceof Float) {
            from = from;
        }
        if (to instanceof Short) {
            to = to;
        }
        else if (to instanceof Float) {
            to = to;
        }
        if (((Float)from).getClass() == ((Float)to).getClass()) {
            this.from = (Comparable)from;
            this.to = (Comparable)to;
        }
        else {
            this.from = normaliseStringType((Comparable)from);
            this.to = normaliseStringType((Comparable)to);
        }
        if (from instanceof String || to instanceof String) {
            final String start = from.toString();
            final String end = to.toString();
            if (start.length() > end.length()) {
                throw new IllegalArgumentException("Incompatible Strings for Range: starting String is longer than ending string");
            }
            int length;
            int i;
            for (length = Math.min(start.length(), end.length()), i = 0; i < length && start.charAt(i) == end.charAt(i); ++i) {}
            if (i < length - 1) {
                throw new IllegalArgumentException("Incompatible Strings for Range: String#next() will not reach the expected value");
            }
        }
    }
    
    @Override
    public boolean equals(final Object that) {
        return (that instanceof ObjectRange) ? this.equals((ObjectRange)that) : super.equals(that);
    }
    
    public boolean equals(final ObjectRange that) {
        return that != null && this.reverse == that.reverse && DefaultTypeTransformation.compareEqual(this.from, that.from) && DefaultTypeTransformation.compareEqual(this.to, that.to);
    }
    
    public Comparable getFrom() {
        return this.from;
    }
    
    public Comparable getTo() {
        return this.to;
    }
    
    public boolean isReverse() {
        return this.reverse;
    }
    
    @Override
    public Object get(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " should not be negative");
        }
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + " is too big for range: " + this);
        }
        Object value;
        if (this.reverse) {
            value = this.to;
            for (int i = 0; i < index; ++i) {
                value = this.decrement(value);
            }
        }
        else {
            value = this.from;
            for (int i = 0; i < index; ++i) {
                value = this.increment(value);
            }
        }
        return value;
    }
    
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index;
            private Object value = ObjectRange.this.reverse ? ObjectRange.this.to : ObjectRange.this.from;
            
            public boolean hasNext() {
                return this.index < ObjectRange.this.size();
            }
            
            public Object next() {
                if (this.index++ > 0) {
                    if (this.index > ObjectRange.this.size()) {
                        this.value = null;
                    }
                    else if (ObjectRange.this.reverse) {
                        this.value = ObjectRange.this.decrement(this.value);
                    }
                    else {
                        this.value = ObjectRange.this.increment(this.value);
                    }
                }
                return this.value;
            }
            
            public void remove() {
                ObjectRange.this.remove(this.index);
            }
        };
    }
    
    public boolean containsWithinBounds(final Object value) {
        if (value instanceof Comparable) {
            final int result = this.compareTo(this.from, (Comparable)value);
            return result == 0 || (result < 0 && this.compareTo(this.to, (Comparable)value) >= 0);
        }
        return this.contains(value);
    }
    
    private int compareTo(final Comparable first, final Comparable second) {
        return DefaultGroovyMethods.numberAwareCompareTo(first, second);
    }
    
    @Override
    public int size() {
        if (this.size == -1) {
            if ((this.from instanceof Integer || this.from instanceof Long) && (this.to instanceof Integer || this.to instanceof Long)) {
                final long fromNum = ((Number)this.from).longValue();
                final long toNum = ((Number)this.to).longValue();
                this.size = (int)(toNum - fromNum + 1L);
            }
            else if (this.from instanceof Character && this.to instanceof Character) {
                final char fromNum2 = (char)this.from;
                final char toNum2 = (char)this.to;
                this.size = toNum2 - fromNum2 + 1;
            }
            else if (this.from instanceof BigDecimal || this.to instanceof BigDecimal) {
                final BigDecimal fromNum3 = new BigDecimal("" + this.from);
                final BigDecimal toNum3 = new BigDecimal("" + this.to);
                final BigInteger sizeNum = toNum3.subtract(fromNum3).add(new BigDecimal(1.0)).toBigInteger();
                this.size = sizeNum.intValue();
            }
            else {
                this.size = 0;
                final Comparable first = this.from;
                Comparable value = this.from;
                while (this.compareTo(this.to, value) >= 0) {
                    value = (Comparable)this.increment(value);
                    ++this.size;
                    if (this.compareTo(first, value) >= 0) {
                        break;
                    }
                }
            }
        }
        return this.size;
    }
    
    @Override
    public List subList(final int fromIndex, int toIndex) {
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
            return new EmptyRange(this.from);
        }
        return new ObjectRange((Comparable)this.get(fromIndex), (Comparable)this.get(--toIndex), this.reverse);
    }
    
    @Override
    public String toString() {
        return this.reverse ? ("" + this.to + ".." + this.from) : ("" + this.from + ".." + this.to);
    }
    
    public String inspect() {
        final String toText = InvokerHelper.inspect(this.to);
        final String fromText = InvokerHelper.inspect(this.from);
        return this.reverse ? ("" + toText + ".." + fromText) : ("" + fromText + ".." + toText);
    }
    
    @Override
    public boolean contains(final Object value) {
        final Iterator it = this.iterator();
        if (value == null) {
            return false;
        }
        while (it.hasNext()) {
            try {
                if (DefaultTypeTransformation.compareEqual(value, it.next())) {
                    return true;
                }
                continue;
            }
            catch (ClassCastException e) {
                return false;
            }
            break;
        }
        return false;
    }
    
    public void step(int step, final Closure closure) {
        if (step != 0) {
            if (this.reverse) {
                step = -step;
            }
            if (step > 0) {
                final Comparable first = this.from;
                Comparable value = this.from;
                while (this.compareTo(value, this.to) <= 0) {
                    closure.call(value);
                    for (int i = 0; i < step; ++i) {
                        value = (Comparable)this.increment(value);
                        if (this.compareTo(value, first) <= 0) {
                            return;
                        }
                    }
                }
            }
            else {
                step = -step;
                final Comparable first = this.to;
                Comparable value = this.to;
                while (this.compareTo(value, this.from) >= 0) {
                    closure.call(value);
                    for (int i = 0; i < step; ++i) {
                        value = (Comparable)this.decrement(value);
                        if (this.compareTo(value, first) >= 0) {
                            return;
                        }
                    }
                }
            }
            return;
        }
        if (this.compareTo(this.from, this.to) != 0) {
            throw new GroovyRuntimeException("Infinite loop detected due to step size of 0");
        }
    }
    
    public List step(final int step) {
        final IteratorClosureAdapter adapter = new IteratorClosureAdapter(this);
        this.step(step, adapter);
        return adapter.asList();
    }
    
    protected Object increment(final Object value) {
        return InvokerHelper.invokeMethod(value, "next", null);
    }
    
    protected Object decrement(final Object value) {
        return InvokerHelper.invokeMethod(value, "previous", null);
    }
    
    private static Comparable normaliseStringType(final Comparable operand) {
        if (operand instanceof Character) {
            return operand;
        }
        if (!(operand instanceof String)) {
            return operand;
        }
        final String string = (String)operand;
        if (string.length() == 1) {
            return string.charAt(0);
        }
        return string;
    }
}
