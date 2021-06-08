// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Iterator;
import java.util.ArrayList;
import org.codehaus.groovy.runtime.NumberAwareComparator;
import groovy.lang.Closure;
import java.util.List;
import java.util.Comparator;

public class OrderBy<T> implements Comparator<T>
{
    private final List<Closure> closures;
    private final NumberAwareComparator<Object> numberAwareComparator;
    
    public OrderBy() {
        this.numberAwareComparator = new NumberAwareComparator<Object>();
        this.closures = new ArrayList<Closure>();
    }
    
    public OrderBy(final Closure closure) {
        this();
        this.closures.add(closure);
    }
    
    public OrderBy(final List<Closure> closures) {
        this.numberAwareComparator = new NumberAwareComparator<Object>();
        this.closures = closures;
    }
    
    public void add(final Closure closure) {
        this.closures.add(closure);
    }
    
    public int compare(final T object1, final T object2) {
        for (final Closure closure : this.closures) {
            final Object value1 = closure.call(object1);
            final Object value2 = closure.call(object2);
            final int result = this.numberAwareComparator.compare(value1, value2);
            if (result == 0) {
                continue;
            }
            return result;
        }
        return 0;
    }
}
