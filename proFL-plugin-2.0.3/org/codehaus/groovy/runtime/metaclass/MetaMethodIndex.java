// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.GeneratedMetaMethod;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.util.FastArray;
import java.util.NoSuchElementException;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.util.SingleKeyHashMap;

public class MetaMethodIndex
{
    public SingleKeyHashMap methodHeaders;
    protected Entry[] table;
    protected static final int DEFAULT_CAPACITY = 32;
    protected static final int MINIMUM_CAPACITY = 4;
    protected static final int MAXIMUM_CAPACITY = 268435456;
    protected int size;
    protected transient int threshold;
    
    public MetaMethodIndex(final CachedClass theCachedClass) {
        this.methodHeaders = new SingleKeyHashMap();
        this.init(32);
        CachedClass last = null;
        if (!theCachedClass.isInterface()) {
            for (CachedClass c = theCachedClass; c != null; c = c.getCachedSuperClass()) {
                final SingleKeyHashMap.Entry e = this.methodHeaders.getOrPut(c.getTheClass());
                e.value = new Header(c.getTheClass(), (last == null) ? null : last.getTheClass());
                last = c;
            }
        }
        else {
            final SingleKeyHashMap.Entry e2 = this.methodHeaders.getOrPut(Object.class);
            e2.value = new Header(Object.class, theCachedClass.getTheClass());
        }
    }
    
    public static int hash(int h) {
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public void clear() {
        final Object[] tab = this.table;
        for (int i = 0; i < tab.length; ++i) {
            tab[i] = null;
        }
        this.size = 0;
    }
    
    public void init(final int initCapacity) {
        this.threshold = initCapacity * 6 / 8;
        this.table = new Entry[initCapacity];
    }
    
    public void resize(final int newLength) {
        final Entry[] oldTable = this.table;
        final int oldLength = this.table.length;
        final Entry[] newTable = new Entry[newLength];
        for (Entry e : oldTable) {
            while (e != null) {
                final Entry next = e.nextHashEntry;
                final int index = e.hash & newLength - 1;
                e.nextHashEntry = newTable[index];
                newTable[index] = e;
                e = next;
            }
        }
        this.table = newTable;
        this.threshold = 6 * newLength / 8;
    }
    
    public Entry[] getTable() {
        return this.table;
    }
    
    public EntryIterator getEntrySetIterator() {
        return new EntryIterator() {
            Entry next;
            int index;
            Entry current;
            
            {
                final Entry[] t = MetaMethodIndex.this.table;
                int i = t.length;
                Entry n = null;
                if (MetaMethodIndex.this.size != 0) {
                    while (i > 0 && (n = t[--i]) == null) {}
                }
                this.next = n;
                this.index = i;
            }
            
            public boolean hasNext() {
                return this.next != null;
            }
            
            public Entry next() {
                return this.nextEntry();
            }
            
            Entry nextEntry() {
                final Entry e = this.next;
                if (e == null) {
                    throw new NoSuchElementException();
                }
                Entry n;
                Entry[] t;
                int i;
                for (n = e.nextHashEntry, t = MetaMethodIndex.this.table, i = this.index; n == null && i > 0; n = t[--i]) {}
                this.index = i;
                this.next = n;
                return this.current = e;
            }
        };
    }
    
    public final Entry getMethods(final Class cls, final String name) {
        final int h = hash(31 * cls.hashCode() + name.hashCode());
        for (Entry e = this.table[h & this.table.length - 1]; e != null; e = e.nextHashEntry) {
            if (e.hash == h && cls == e.cls && (e.name == name || e.name.equals(name))) {
                return e;
            }
        }
        return null;
    }
    
    public Entry getOrPutMethods(final String name, final Header header) {
        final Class cls = header.cls;
        final int h = hash(header.clsHashCode31 + name.hashCode());
        final Entry[] t = this.table;
        final int index = h & t.length - 1;
        for (Entry e = t[index]; e != null; e = e.nextHashEntry) {
            if (e.hash == h && cls == e.cls && (e.name == name || e.name.equals(name))) {
                return e;
            }
        }
        final Entry entry = new Entry();
        entry.nextHashEntry = t[index];
        entry.hash = h;
        entry.name = name.intern();
        entry.cls = cls;
        t[index] = entry;
        entry.nextClassEntry = header.head;
        header.head = entry;
        if (++this.size == this.threshold) {
            this.resize(2 * t.length);
        }
        return entry;
    }
    
    public Header getHeader(final Class cls) {
        final SingleKeyHashMap.Entry head = this.methodHeaders.getOrPut(cls);
        if (head.value == null) {
            head.value = new Header(cls);
        }
        final Header header = (Header)head.value;
        return header;
    }
    
    public void copyNonPrivateMethods(final Class from, final Class to) {
        this.copyNonPrivateMethods(this.getHeader(from), this.getHeader(to));
    }
    
    public void copyNonPrivateMethods(final Header from, final Header to) {
        for (Entry e = from.head; e != null; e = e.nextClassEntry) {
            this.copyNonPrivateMethods(e, to);
        }
    }
    
    public void copyAllMethodsToSuper(final Header from, final Header to) {
        for (Entry e = from.head; e != null; e = e.nextClassEntry) {
            this.copyAllMethodsToSuper(e, to);
        }
    }
    
    public void copyNonPrivateMethodsFromSuper(final Header from) {
        for (Entry e = from.head; e != null; e = e.nextClassEntry) {
            this.copyNonPrivateMethodsFromSuper(e);
        }
    }
    
    private void copyNonPrivateMethods(final Entry from, final Header to) {
        final Object oldListOrMethod = from.methods;
        if (oldListOrMethod instanceof FastArray) {
            final FastArray oldList = (FastArray)oldListOrMethod;
            Entry e = null;
            final int len1 = oldList.size();
            final Object[] list = oldList.getArray();
            for (int j = 0; j != len1; ++j) {
                final MetaMethod method = (MetaMethod)list[j];
                if (!method.isPrivate()) {
                    if (e == null) {
                        e = this.getOrPutMethods(from.name, to);
                    }
                    e.methods = this.addMethodToList(e.methods, method);
                }
            }
        }
        else {
            final MetaMethod method2 = (MetaMethod)oldListOrMethod;
            if (!method2.isPrivate()) {
                final Entry e = this.getOrPutMethods(from.name, to);
                e.methods = this.addMethodToList(e.methods, method2);
            }
        }
    }
    
    private void copyAllMethodsToSuper(final Entry from, final Header to) {
        final Object oldListOrMethod = from.methods;
        if (oldListOrMethod instanceof FastArray) {
            final FastArray oldList = (FastArray)oldListOrMethod;
            Entry e = null;
            final int len1 = oldList.size();
            final Object[] list = oldList.getArray();
            for (int j = 0; j != len1; ++j) {
                final MetaMethod method = (MetaMethod)list[j];
                if (e == null) {
                    e = this.getOrPutMethods(from.name, to);
                }
                e.methodsForSuper = this.addMethodToList(e.methodsForSuper, method);
            }
        }
        else {
            final MetaMethod method2 = (MetaMethod)oldListOrMethod;
            final Entry e = this.getOrPutMethods(from.name, to);
            e.methodsForSuper = this.addMethodToList(e.methodsForSuper, method2);
        }
    }
    
    private void copyNonPrivateMethodsFromSuper(final Entry e) {
        final Object oldListOrMethod = e.methodsForSuper;
        if (oldListOrMethod == null) {
            return;
        }
        if (oldListOrMethod instanceof FastArray) {
            final FastArray oldList = (FastArray)oldListOrMethod;
            final int len1 = oldList.size();
            final Object[] list = oldList.getArray();
            for (int j = 0; j != len1; ++j) {
                final MetaMethod method = (MetaMethod)list[j];
                if (!method.isPrivate()) {
                    e.methods = this.addMethodToList(e.methods, method);
                }
            }
        }
        else {
            final MetaMethod method2 = (MetaMethod)oldListOrMethod;
            if (!method2.isPrivate()) {
                e.methods = this.addMethodToList(e.methods, method2);
            }
        }
    }
    
    public void copyNonPrivateMethodsDown(final Class from, final Class to) {
        this.copyNonPrivateNonNewMetaMethods(this.getHeader(from), this.getHeader(to));
    }
    
    public void copyNonPrivateNonNewMetaMethods(final Header from, final Header to) {
        for (Entry e = from.head; e != null; e = e.nextClassEntry) {
            this.copyNonPrivateNonNewMetaMethods(e, to);
        }
    }
    
    private void copyNonPrivateNonNewMetaMethods(final Entry from, final Header to) {
        final Object oldListOrMethod = from.methods;
        if (oldListOrMethod == null) {
            return;
        }
        if (oldListOrMethod instanceof FastArray) {
            final FastArray oldList = (FastArray)oldListOrMethod;
            Entry e = null;
            final int len1 = oldList.size();
            final Object[] list = oldList.getArray();
            for (int j = 0; j != len1; ++j) {
                final MetaMethod method = (MetaMethod)list[j];
                if (!(method instanceof NewMetaMethod)) {
                    if (!method.isPrivate()) {
                        if (e == null) {
                            e = this.getOrPutMethods(from.name, to);
                        }
                        e.methods = this.addMethodToList(e.methods, method);
                    }
                }
            }
        }
        else {
            final MetaMethod method2 = (MetaMethod)oldListOrMethod;
            if (method2 instanceof NewMetaMethod || method2.isPrivate()) {
                return;
            }
            final Entry e = this.getOrPutMethods(from.name, to);
            e.methods = this.addMethodToList(e.methods, method2);
        }
    }
    
    public Object addMethodToList(final Object o, final MetaMethod method) {
        if (o == null) {
            return method;
        }
        if (!(o instanceof MetaMethod)) {
            if (o instanceof FastArray) {
                final FastArray list = (FastArray)o;
                final int found = this.findMatchingMethod(list, method);
                if (found == -1) {
                    list.add(method);
                }
                else {
                    final MetaMethod match = (MetaMethod)list.get(found);
                    if (match == method) {
                        return o;
                    }
                    if (!match.isPrivate()) {
                        if (this.isNonRealMethod(match) || !match.getDeclaringClass().isInterface() || method.getDeclaringClass().isInterface()) {
                            final CachedClass methodC = method.getDeclaringClass();
                            final CachedClass matchC = match.getDeclaringClass();
                            if (methodC == matchC) {
                                if (this.isNonRealMethod(method)) {
                                    list.set(found, method);
                                }
                            }
                            else if (!methodC.isAssignableFrom(matchC.getTheClass())) {
                                list.set(found, method);
                            }
                        }
                    }
                }
            }
            return o;
        }
        final MetaMethod match2 = (MetaMethod)o;
        if (!this.isMatchingMethod(match2, method)) {
            final FastArray list2 = new FastArray(2);
            list2.add(match2);
            list2.add(method);
            return list2;
        }
        if (!match2.isPrivate()) {
            if (this.isNonRealMethod(match2) || !match2.getDeclaringClass().isInterface() || method.getDeclaringClass().isInterface()) {
                final CachedClass methodC2 = method.getDeclaringClass();
                final CachedClass matchC2 = match2.getDeclaringClass();
                if (methodC2 == matchC2) {
                    if (this.isNonRealMethod(method)) {
                        return method;
                    }
                }
                else if (!methodC2.isAssignableFrom(matchC2.getTheClass())) {
                    return method;
                }
            }
        }
        return o;
    }
    
    private boolean isNonRealMethod(final MetaMethod method) {
        return method instanceof NewInstanceMetaMethod || method instanceof NewStaticMetaMethod || method instanceof ClosureMetaMethod || method instanceof GeneratedMetaMethod || method instanceof ClosureStaticMetaMethod || method instanceof MixinInstanceMetaMethod;
    }
    
    private boolean isMatchingMethod(final MetaMethod aMethod, final MetaMethod method) {
        if (aMethod == method) {
            return true;
        }
        final CachedClass[] params1 = aMethod.getParameterTypes();
        final CachedClass[] params2 = method.getParameterTypes();
        if (params1.length != params2.length) {
            return false;
        }
        boolean matches = true;
        for (int i = 0; i < params1.length; ++i) {
            if (params1[i] != params2[i]) {
                matches = false;
                break;
            }
        }
        return matches;
    }
    
    private int findMatchingMethod(final FastArray list, final MetaMethod method) {
        final int len = list.size();
        final Object[] data = list.getArray();
        for (int j = 0; j != len; ++j) {
            final MetaMethod aMethod = (MetaMethod)data[j];
            if (this.isMatchingMethod(aMethod, method)) {
                return j;
            }
        }
        return -1;
    }
    
    public void copyMethodsToSuper() {
        for (Entry e : this.table) {
            while (e != null) {
                if (e.methods instanceof FastArray) {
                    e.methodsForSuper = ((FastArray)e.methods).copy();
                }
                else {
                    e.methodsForSuper = e.methods;
                }
                e = e.nextHashEntry;
            }
        }
    }
    
    public void copy(final Class c, final Header index) {
        this.copy(this.getHeader(c), index);
    }
    
    public void copy(final Header from, final Header to) {
        for (Entry e = from.head; e != null; e = e.nextClassEntry) {
            this.copyAllMethods(e, to);
        }
    }
    
    private void copyAllMethods(final Entry from, final Header to) {
        final Object oldListOrMethod = from.methods;
        if (oldListOrMethod instanceof FastArray) {
            final FastArray oldList = (FastArray)oldListOrMethod;
            Entry e = null;
            final int len1 = oldList.size();
            final Object[] list = oldList.getArray();
            for (int j = 0; j != len1; ++j) {
                final MetaMethod method = (MetaMethod)list[j];
                if (e == null) {
                    e = this.getOrPutMethods(from.name, to);
                }
                e.methods = this.addMethodToList(e.methods, method);
            }
        }
        else {
            final MetaMethod method2 = (MetaMethod)oldListOrMethod;
            if (!method2.isPrivate()) {
                final Entry e = this.getOrPutMethods(from.name, to);
                e.methods = this.addMethodToList(e.methods, method2);
            }
        }
    }
    
    public void clearCaches() {
        for (int i = 0; i != this.table.length; ++i) {
            for (Entry e = this.table[i]; e != null; e = e.nextHashEntry) {
                final Entry entry = e;
                final Entry entry2 = e;
                final Entry entry3 = e;
                final CacheEntry cachedMethod = null;
                entry3.cachedStaticMethod = cachedMethod;
                entry2.cachedMethodForSuper = cachedMethod;
                entry.cachedMethod = cachedMethod;
            }
        }
    }
    
    public void clearCaches(final String name) {
        for (int i = 0; i != this.table.length; ++i) {
            for (Entry e = this.table[i]; e != null; e = e.nextHashEntry) {
                if (e.name.equals(name)) {
                    final Entry entry = e;
                    final Entry entry2 = e;
                    final Entry entry3 = e;
                    final CacheEntry cachedMethod = null;
                    entry3.cachedStaticMethod = cachedMethod;
                    entry2.cachedMethodForSuper = cachedMethod;
                    entry.cachedMethod = cachedMethod;
                }
            }
        }
    }
    
    public static class Header
    {
        public Entry head;
        Class cls;
        public int clsHashCode31;
        public Class subclass;
        
        public Header(final Class cls) {
            this(cls, null);
        }
        
        public Header(final Class cls, final Class subclass) {
            this.cls = cls;
            this.subclass = subclass;
            this.clsHashCode31 = 31 * cls.hashCode();
        }
    }
    
    public static class CacheEntry
    {
        public Class[] params;
        public MetaMethod method;
    }
    
    public static class Entry
    {
        public int hash;
        public Entry nextHashEntry;
        public Entry nextClassEntry;
        public String name;
        public Class cls;
        public Object methods;
        public Object methodsForSuper;
        public Object staticMethods;
        public CacheEntry cachedMethod;
        public CacheEntry cachedMethodForSuper;
        public CacheEntry cachedStaticMethod;
        
        @Override
        public String toString() {
            return "[" + this.name + ", " + this.cls.getName() + "]";
        }
    }
    
    public interface EntryIterator
    {
        boolean hasNext();
        
        Entry next();
    }
}
