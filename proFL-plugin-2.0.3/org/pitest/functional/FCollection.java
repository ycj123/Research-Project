// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;

public abstract class FCollection
{
    public static <A> void forEach(final Iterable<? extends A> as, final SideEffect1<A> e) {
        for (final A a : as) {
            e.apply(a);
        }
    }
    
    public static <A, B> void mapTo(final Iterable<? extends A> as, final F<A, B> f, final Collection<? super B> bs) {
        if (as != null) {
            for (final A a : as) {
                bs.add((Object)f.apply(a));
            }
        }
    }
    
    public static <A, B> FunctionalList<B> map(final Iterable<? extends A> as, final F<A, B> f) {
        final FunctionalList<B> bs = emptyList();
        mapTo(as, f, bs);
        return bs;
    }
    
    public static <A, B> void flatMapTo(final Iterable<? extends A> as, final F<A, ? extends Iterable<B>> f, final Collection<? super B> bs) {
        if (as != null) {
            for (final A a : as) {
                for (final B each : (Iterable)f.apply(a)) {
                    bs.add((Object)each);
                }
            }
        }
    }
    
    public static <A, B> FunctionalList<B> flatMap(final Iterable<? extends A> as, final F<A, ? extends Iterable<B>> f) {
        final FunctionalList<B> bs = emptyList();
        flatMapTo(as, (F<A, ? extends Iterable<Object>>)f, (Collection<? super Object>)bs);
        return bs;
    }
    
    private static <T> FunctionalList<T> emptyList() {
        return new MutableList<T>();
    }
    
    public static <T> FunctionalList<T> filter(final Iterable<? extends T> xs, final F<T, Boolean> predicate) {
        final FunctionalList<T> dest = emptyList();
        filter(xs, predicate, dest);
        return dest;
    }
    
    public static <T> void filter(final Iterable<? extends T> xs, final F<T, Boolean> predicate, final Collection<? super T> dest) {
        for (final T x : xs) {
            if (predicate.apply(x)) {
                dest.add((Object)x);
            }
        }
    }
    
    public static <T> Option<T> findFirst(final Iterable<? extends T> xs, final F<T, Boolean> predicate) {
        for (final T x : xs) {
            if (predicate.apply(x)) {
                return Option.some(x);
            }
        }
        return (Option<T>)Option.none();
    }
    
    public static <T> boolean contains(final Iterable<? extends T> xs, final F<T, Boolean> predicate) {
        for (final T x : xs) {
            if (predicate.apply(x)) {
                return true;
            }
        }
        return false;
    }
    
    public static <A, B> A fold(final F2<A, B, A> f, final A z, final Iterable<? extends B> xs) {
        A p = z;
        for (final B x : xs) {
            p = f.apply(p, x);
        }
        return p;
    }
    
    public static <T> FunctionalCollection<T> flatten(final Iterable<? extends Iterable<? extends T>> ts) {
        final MutableList<T> list = new MutableList<T>();
        for (final Iterable<? extends T> it : ts) {
            for (final T each : it) {
                list.add(each);
            }
        }
        return list;
    }
    
    public static <T> FunctionalList<List<T>> splitToLength(final int targetLength, final Iterable<T> ts) {
        final FunctionalList<List<T>> list = new MutableList<List<T>>();
        List<T> temp = new ArrayList<T>();
        int i = 0;
        for (final T each : ts) {
            if (i == targetLength) {
                list.add(temp);
                temp = new ArrayList<T>();
                i = 0;
            }
            temp.add(each);
            ++i;
        }
        if (!temp.isEmpty()) {
            list.add(temp);
        }
        return list;
    }
    
    public static <A, B> Map<A, Collection<B>> bucket(final Iterable<B> bs, final F<B, A> f) {
        final Map<A, Collection<B>> bucketed = new HashMap<A, Collection<B>>();
        for (final B each : bs) {
            final A key = f.apply(each);
            Collection<B> existing = bucketed.get(key);
            if (existing == null) {
                existing = new ArrayList<B>();
                bucketed.put(key, existing);
            }
            existing.add(each);
        }
        return bucketed;
    }
}
