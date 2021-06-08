// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.concurrent.ConcurrentHashMap;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.runtime.metaclass.NewInstanceMetaMethod;
import java.util.LinkedList;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.CachedMethod;
import java.util.Collections;
import org.codehaus.groovy.reflection.CachedClass;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import groovy.lang.Closure;
import java.util.concurrent.atomic.AtomicInteger;

public class GroovyCategorySupport
{
    private static AtomicInteger categoriesInUse;
    private static final MyThreadLocal THREAD_INFO;
    
    public static AtomicInteger getCategoryNameUsage(final String name) {
        return GroovyCategorySupport.THREAD_INFO.getUsage(name);
    }
    
    public static Object use(final Class categoryClass, final Closure closure) {
        return GroovyCategorySupport.THREAD_INFO.getInfo().use(categoryClass, closure);
    }
    
    public static Object use(final List<Class> categoryClasses, final Closure closure) {
        return GroovyCategorySupport.THREAD_INFO.getInfo().use(categoryClasses, closure);
    }
    
    public static boolean hasCategoryInCurrentThread() {
        if (GroovyCategorySupport.categoriesInUse.get() == 0) {
            return false;
        }
        final ThreadCategoryInfo infoNullable = GroovyCategorySupport.THREAD_INFO.getInfoNullable();
        return infoNullable != null && infoNullable.level != 0;
    }
    
    public static boolean hasCategoryInAnyThread() {
        return GroovyCategorySupport.categoriesInUse.get() != 0;
    }
    
    public static CategoryMethodList getCategoryMethods(final String name) {
        final ThreadCategoryInfo categoryInfo = GroovyCategorySupport.THREAD_INFO.getInfoNullable();
        return (categoryInfo == null) ? null : categoryInfo.getCategoryMethods(name);
    }
    
    public static String getPropertyCategoryGetterName(final String propertyName) {
        final ThreadCategoryInfo categoryInfo = GroovyCategorySupport.THREAD_INFO.getInfoNullable();
        return (categoryInfo == null) ? null : categoryInfo.getPropertyCategoryGetterName(propertyName);
    }
    
    public static String getPropertyCategorySetterName(final String propertyName) {
        final ThreadCategoryInfo categoryInfo = GroovyCategorySupport.THREAD_INFO.getInfoNullable();
        return (categoryInfo == null) ? null : categoryInfo.getPropertyCategorySetterName(propertyName);
    }
    
    static {
        GroovyCategorySupport.categoriesInUse = new AtomicInteger();
        THREAD_INFO = new MyThreadLocal();
    }
    
    public static class CategoryMethodList extends ArrayList<CategoryMethod>
    {
        public final int level;
        final CategoryMethodList previous;
        final AtomicInteger usage;
        
        public CategoryMethodList(final String name, final int level, final CategoryMethodList previous) {
            this.level = level;
            this.previous = previous;
            if (previous != null) {
                this.addAll(previous);
                this.usage = previous.usage;
            }
            else {
                this.usage = GroovyCategorySupport.getCategoryNameUsage(name);
            }
        }
        
        @Override
        public boolean add(final CategoryMethod o) {
            this.usage.incrementAndGet();
            return super.add(o);
        }
    }
    
    public static class ThreadCategoryInfo extends HashMap<String, CategoryMethodList>
    {
        int level;
        private Map<String, String> propertyGetterMap;
        private Map<String, String> propertySetterMap;
        
        private void newScope() {
            GroovyCategorySupport.categoriesInUse.incrementAndGet();
            ++this.level;
        }
        
        private void endScope() {
            final Iterator<Map.Entry<String, CategoryMethodList>> it = this.entrySet().iterator();
            while (it.hasNext()) {
                final Map.Entry<String, CategoryMethodList> e = it.next();
                final CategoryMethodList list = e.getValue();
                if (list.level == this.level) {
                    final CategoryMethodList prev = list.previous;
                    if (prev == null) {
                        it.remove();
                        list.usage.addAndGet(-list.size());
                    }
                    else {
                        e.setValue(prev);
                        list.usage.addAndGet(prev.size() - list.size());
                    }
                }
            }
            --this.level;
            GroovyCategorySupport.categoriesInUse.getAndDecrement();
            if (this.level == 0) {
                GroovyCategorySupport.THREAD_INFO.remove();
            }
        }
        
        private Object use(final Class categoryClass, final Closure closure) {
            this.newScope();
            try {
                this.use(categoryClass);
                return closure.call();
            }
            finally {
                this.endScope();
            }
        }
        
        public Object use(final List<Class> categoryClasses, final Closure closure) {
            this.newScope();
            try {
                for (final Class categoryClass : categoryClasses) {
                    this.use(categoryClass);
                }
                return closure.call();
            }
            finally {
                this.endScope();
            }
        }
        
        private void applyUse(final CachedClass cachedClass) {
            final CachedMethod[] arr$;
            final CachedMethod[] methods = arr$ = cachedClass.getMethods();
            for (final CachedMethod cachedMethod : arr$) {
                if (cachedMethod.isStatic() && cachedMethod.isPublic()) {
                    final CachedClass[] paramTypes = cachedMethod.getParameterTypes();
                    if (paramTypes.length > 0) {
                        final CachedClass metaClass = paramTypes[0];
                        final CategoryMethod mmethod = new CategoryMethod(cachedMethod, metaClass.getTheClass());
                        final String name = cachedMethod.getName();
                        CategoryMethodList list = (CategoryMethodList)this.get(name);
                        if (list == null || list.level != this.level) {
                            list = new CategoryMethodList(name, this.level, list);
                            this.put((Object)name, (Object)list);
                        }
                        list.add(mmethod);
                        Collections.sort((List<Comparable>)list);
                        this.cachePropertyAccessor(mmethod);
                    }
                }
            }
        }
        
        private void cachePropertyAccessor(final CategoryMethod method) {
            final String name = method.getName();
            final int parameterLength = method.getParameterTypes().length;
            if (name.startsWith("get") && name.length() > 3 && parameterLength == 0) {
                this.propertyGetterMap = this.putPropertyAccessor(3, name, this.propertyGetterMap);
            }
            else if (name.startsWith("set") && name.length() > 3 && parameterLength == 1) {
                this.propertySetterMap = this.putPropertyAccessor(3, name, this.propertySetterMap);
            }
        }
        
        private Map<String, String> putPropertyAccessor(final int prefixLength, final String accessorName, Map<String, String> map) {
            if (map == null) {
                map = new HashMap<String, String>();
            }
            final String property = accessorName.substring(prefixLength, prefixLength + 1).toLowerCase() + accessorName.substring(prefixLength + 1);
            map.put(property, accessorName);
            return map;
        }
        
        private void use(final Class categoryClass) {
            final CachedClass cachedClass = ReflectionCache.getCachedClass(categoryClass);
            final LinkedList<CachedClass> classStack = new LinkedList<CachedClass>();
            for (CachedClass superClass = cachedClass; superClass.getTheClass() != Object.class; superClass = superClass.getCachedSuperClass()) {
                classStack.add(superClass);
            }
            while (!classStack.isEmpty()) {
                final CachedClass klazz = classStack.removeLast();
                this.applyUse(klazz);
            }
        }
        
        public CategoryMethodList getCategoryMethods(final String name) {
            return (this.level == 0) ? null : ((CategoryMethodList)this.get(name));
        }
        
        String getPropertyCategoryGetterName(final String propertyName) {
            return (this.propertyGetterMap != null) ? this.propertyGetterMap.get(propertyName) : null;
        }
        
        String getPropertyCategorySetterName(final String propertyName) {
            return (this.propertySetterMap != null) ? this.propertySetterMap.get(propertyName) : null;
        }
    }
    
    private static class CategoryMethod extends NewInstanceMetaMethod implements Comparable
    {
        private final Class metaClass;
        
        public CategoryMethod(final CachedMethod metaMethod, final Class metaClass) {
            super(metaMethod);
            this.metaClass = metaClass;
        }
        
        @Override
        public boolean isCacheable() {
            return false;
        }
        
        public int compareTo(final Object o) {
            final CategoryMethod thatMethod = (CategoryMethod)o;
            final Class thisClass = this.metaClass;
            final Class thatClass = thatMethod.metaClass;
            if (thisClass == thatClass) {
                return 0;
            }
            if (this.isChildOfParent(thisClass, thatClass)) {
                return -1;
            }
            if (this.isChildOfParent(thatClass, thisClass)) {
                return 1;
            }
            return 0;
        }
        
        private boolean isChildOfParent(final Class candidateChild, final Class candidateParent) {
            Class loop = candidateChild;
            while (loop != null && loop != Object.class) {
                loop = loop.getSuperclass();
                if (loop == candidateParent) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private static class MyThreadLocal extends ThreadLocal<SoftReference>
    {
        ConcurrentHashMap<String, AtomicInteger> usage;
        
        private MyThreadLocal() {
            this.usage = new ConcurrentHashMap<String, AtomicInteger>();
        }
        
        public ThreadCategoryInfo getInfo() {
            final SoftReference reference = (SoftReference)this.get();
            ThreadCategoryInfo tcinfo;
            if (reference != null) {
                tcinfo = reference.get();
                if (tcinfo == null) {
                    tcinfo = new ThreadCategoryInfo();
                    this.set((Object)new SoftReference(tcinfo));
                }
            }
            else {
                tcinfo = new ThreadCategoryInfo();
                this.set((Object)new SoftReference(tcinfo));
            }
            return tcinfo;
        }
        
        public ThreadCategoryInfo getInfoNullable() {
            final SoftReference reference = (SoftReference)this.get();
            return (reference == null) ? null : reference.get();
        }
        
        public AtomicInteger getUsage(final String name) {
            final AtomicInteger u = this.usage.get(name);
            if (u != null) {
                return u;
            }
            final AtomicInteger ai = new AtomicInteger();
            final AtomicInteger prev = this.usage.putIfAbsent(name, ai);
            return (prev == null) ? ai : prev;
        }
    }
}
