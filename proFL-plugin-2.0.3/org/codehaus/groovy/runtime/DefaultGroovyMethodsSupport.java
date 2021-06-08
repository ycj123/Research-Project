// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Hashtable;
import java.util.Properties;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Map;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Vector;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.io.IOException;
import java.io.Closeable;
import groovy.lang.EmptyRange;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Range;
import java.util.logging.Logger;

public class DefaultGroovyMethodsSupport
{
    private static final Logger LOG;
    
    protected static RangeInfo subListBorders(final int size, final Range range) {
        int from = normaliseIndex(DefaultTypeTransformation.intUnbox(range.getFrom()), size);
        int to = normaliseIndex(DefaultTypeTransformation.intUnbox(range.getTo()), size);
        boolean reverse = range.isReverse();
        if (from > to) {
            final int tmp = to;
            to = from;
            from = tmp;
            reverse = !reverse;
        }
        return new RangeInfo(from, to + 1, reverse);
    }
    
    protected static RangeInfo subListBorders(final int size, final EmptyRange range) {
        final int from = normaliseIndex(DefaultTypeTransformation.intUnbox(range.getFrom()), size);
        return new RangeInfo(from, from, false);
    }
    
    protected static int normaliseIndex(int i, final int size) {
        final int temp = i;
        if (i < 0) {
            i += size;
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative array index [" + temp + "] too large for array size " + size);
        }
        return i;
    }
    
    public static void closeWithWarning(final Closeable c) {
        if (c != null) {
            try {
                c.close();
            }
            catch (IOException e) {
                DefaultGroovyMethodsSupport.LOG.warning("Caught exception during close(): " + e);
            }
        }
    }
    
    public static void closeQuietly(final Closeable c) {
        if (c != null) {
            try {
                c.close();
            }
            catch (IOException ex) {}
        }
    }
    
    protected static <T> Collection<T> cloneSimilarCollection(final Collection<T> orig, final int newCapacity) {
        Collection<T> answer = (Collection<T>)cloneObject(orig);
        if (answer != null) {
            return answer;
        }
        answer = (Collection<T>)createSimilarCollection((Collection<Object>)orig, newCapacity);
        answer.addAll((Collection<? extends T>)orig);
        return answer;
    }
    
    private static Object cloneObject(final Object orig) {
        if (orig instanceof Cloneable) {
            try {
                return InvokerHelper.invokeMethod(orig, "clone", new Object[0]);
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    protected static Collection createSimilarOrDefaultCollection(final Object object) {
        if (object instanceof Collection) {
            return createSimilarCollection((Collection<Object>)object);
        }
        return new ArrayList();
    }
    
    protected static <T> Collection<T> createSimilarCollection(final Collection<T> collection) {
        return createSimilarCollection(collection, collection.size());
    }
    
    protected static <T> Collection<T> createSimilarCollection(final Collection<T> orig, final int newCapacity) {
        if (orig instanceof Set) {
            return (Collection<T>)createSimilarSet((Set<Object>)(Set)orig);
        }
        if (orig instanceof List) {
            return (Collection<T>)createSimilarList((List<Object>)(List)orig, newCapacity);
        }
        if (orig instanceof Queue) {
            return new LinkedList<T>();
        }
        return new ArrayList<T>(newCapacity);
    }
    
    protected static <T> List<T> createSimilarList(final List<T> orig, final int newCapacity) {
        if (orig instanceof LinkedList) {
            return new LinkedList<T>();
        }
        if (orig instanceof Stack) {
            return new Stack<T>();
        }
        if (orig instanceof Vector) {
            return new Vector<T>();
        }
        return new ArrayList<T>(newCapacity);
    }
    
    protected static <T> Set<T> createSimilarSet(final Set<T> orig) {
        if (orig instanceof SortedSet) {
            return new TreeSet<T>(((SortedSet)orig).comparator());
        }
        if (orig instanceof LinkedHashSet) {
            return new LinkedHashSet<T>();
        }
        return new HashSet<T>();
    }
    
    protected static <K, V> Map<K, V> createSimilarMap(final Map<K, V> orig) {
        if (orig instanceof SortedMap) {
            return new TreeMap<K, V>(((SortedMap)orig).comparator());
        }
        if (orig instanceof Properties) {
            return (Map<K, V>)new Properties();
        }
        if (orig instanceof Hashtable) {
            return new Hashtable<K, V>();
        }
        return new LinkedHashMap<K, V>();
    }
    
    protected static <K, V> Map<K, V> cloneSimilarMap(final Map<K, V> orig) {
        final Map<K, V> answer = (Map<K, V>)cloneObject(orig);
        if (answer != null) {
            return answer;
        }
        if (orig instanceof TreeMap) {
            return new TreeMap<K, V>((Map<? extends K, ? extends V>)orig);
        }
        if (orig instanceof Properties) {
            final Map<K, V> map = (Map<K, V>)new Properties();
            map.putAll((Map<? extends K, ? extends V>)orig);
            return map;
        }
        if (orig instanceof Hashtable) {
            return new Hashtable<K, V>((Map<? extends K, ? extends V>)orig);
        }
        return new LinkedHashMap<K, V>((Map<? extends K, ? extends V>)orig);
    }
    
    protected static boolean sameType(final Collection[] cols) {
        final List all = new LinkedList();
        for (final Collection col : cols) {
            all.addAll(col);
        }
        if (all.size() == 0) {
            return true;
        }
        final Object first = all.get(0);
        Class baseClass;
        if (first instanceof Number) {
            baseClass = Number.class;
        }
        else if (first == null) {
            baseClass = NullObject.class;
        }
        else {
            baseClass = first.getClass();
        }
        for (final Collection col2 : cols) {
            for (final Object o : col2) {
                if (!baseClass.isInstance(o)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    static {
        LOG = Logger.getLogger(DefaultGroovyMethodsSupport.class.getName());
    }
    
    protected static class RangeInfo
    {
        public final int from;
        public final int to;
        public final boolean reverse;
        
        public RangeInfo(final int from, final int to, final boolean reverse) {
            this.from = from;
            this.to = to;
            this.reverse = reverse;
        }
    }
}
