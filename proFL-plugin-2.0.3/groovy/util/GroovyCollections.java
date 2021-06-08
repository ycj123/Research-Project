// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;

public class GroovyCollections
{
    public static List combinations(final Object[] collections) {
        return combinations(Arrays.asList(collections));
    }
    
    public static <T> Set<List<T>> subsequences(final List<T> items) {
        Set<List<T>> ans = new HashSet<List<T>>();
        for (final T h : items) {
            final Set<List<T>> next = new HashSet<List<T>>();
            for (final List<T> it : ans) {
                final List<T> sublist = new ArrayList<T>((Collection<? extends T>)it);
                sublist.add(h);
                next.add(sublist);
            }
            next.addAll(ans);
            final List<T> hlist = new ArrayList<T>();
            hlist.add(h);
            next.add(hlist);
            ans = next;
        }
        return ans;
    }
    
    public static List combinations(final Collection collections) {
        List collectedCombos = new ArrayList();
        final Iterator outer = collections.iterator();
        while (outer.hasNext()) {
            final Collection items = DefaultTypeTransformation.asCollection(outer.next());
            if (collectedCombos.isEmpty()) {
                final Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    final List l = new ArrayList();
                    l.add(iterator.next());
                    collectedCombos.add(l);
                }
            }
            else {
                final List savedCombos = new ArrayList(collectedCombos);
                final List newCombos = new ArrayList();
                for (final Object value : items) {
                    final Iterator combos = savedCombos.iterator();
                    while (combos.hasNext()) {
                        final List oldlist = new ArrayList(combos.next());
                        oldlist.add(value);
                        newCombos.add(oldlist);
                    }
                }
                collectedCombos = newCombos;
            }
        }
        return collectedCombos;
    }
    
    public static List transpose(final Object[] lists) {
        return transpose(Arrays.asList(lists));
    }
    
    public static List transpose(final List lists) {
        final List result = new ArrayList();
        if (lists.isEmpty() || lists.size() == 0) {
            return result;
        }
        int minSize = Integer.MAX_VALUE;
        Iterator outer = lists.iterator();
        while (outer.hasNext()) {
            final List list = (List)DefaultTypeTransformation.castToType(outer.next(), List.class);
            if (list.size() < minSize) {
                minSize = list.size();
            }
        }
        if (minSize == 0) {
            return result;
        }
        for (int i = 0; i < minSize; ++i) {
            result.add(new ArrayList());
        }
        outer = lists.iterator();
        while (outer.hasNext()) {
            final List list = (List)DefaultTypeTransformation.castToType(outer.next(), List.class);
            for (int j = 0; j < minSize; ++j) {
                final List resultList = result.get(j);
                resultList.add(list.get(j));
            }
        }
        return result;
    }
    
    public static <T> T min(final T[] items) {
        return min(Arrays.asList(items));
    }
    
    public static <T> T min(final Collection<T> items) {
        T answer = null;
        for (final T value : items) {
            if (value != null && (answer == null || ScriptBytecodeAdapter.compareLessThan(value, answer))) {
                answer = value;
            }
        }
        return answer;
    }
    
    public static <T> T max(final T[] items) {
        return max(Arrays.asList(items));
    }
    
    public static <T> T max(final Collection<T> items) {
        T answer = null;
        for (final T value : items) {
            if (value != null && (answer == null || ScriptBytecodeAdapter.compareGreaterThan(value, answer))) {
                answer = value;
            }
        }
        return answer;
    }
    
    public static Object sum(final Object[] items) {
        return DefaultGroovyMethods.sum(Arrays.asList(items));
    }
    
    public static Object sum(final Collection items) {
        return DefaultGroovyMethods.sum(items);
    }
}
