// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Comparator;

public class NumberAwareComparator<T> implements Comparator<T>
{
    public int compare(final T o1, final T o2) {
        try {
            return DefaultTypeTransformation.compareTo(o1, o2);
        }
        catch (ClassCastException cce) {}
        catch (GroovyRuntimeException ex) {}
        final int x1 = o1.hashCode();
        final int x2 = o2.hashCode();
        if (x1 == x2) {
            return 0;
        }
        if (x1 < x2) {
            return -1;
        }
        return 1;
    }
}
