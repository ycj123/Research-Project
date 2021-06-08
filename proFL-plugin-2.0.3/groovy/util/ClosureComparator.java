// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Closure;
import java.util.Comparator;

public class ClosureComparator<T> implements Comparator<T>
{
    Closure closure;
    
    public ClosureComparator(final Closure closure) {
        this.closure = closure;
    }
    
    public int compare(final T object1, final T object2) {
        final Object value = this.closure.call(new Object[] { object1, object2 });
        return DefaultTypeTransformation.intUnbox(value);
    }
}
