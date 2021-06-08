// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import java.util.concurrent.atomic.AtomicInteger;
import org.codehaus.groovy.util.LazyReference;
import java.lang.ref.PhantomReference;
import java.util.HashMap;
import org.codehaus.groovy.util.AbstractConcurrentMap;
import org.codehaus.groovy.util.AbstractConcurrentMapBase;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import org.codehaus.groovy.reflection.stdclasses.CachedClosureClass;
import groovy.lang.Closure;
import org.codehaus.groovy.reflection.stdclasses.ArrayCachedClass;
import org.codehaus.groovy.reflection.stdclasses.ByteCachedClass;
import org.codehaus.groovy.reflection.stdclasses.BigIntegerCachedClass;
import java.math.BigInteger;
import org.codehaus.groovy.reflection.stdclasses.CharacterCachedClass;
import org.codehaus.groovy.reflection.stdclasses.BooleanCachedClass;
import org.codehaus.groovy.reflection.stdclasses.ShortCachedClass;
import org.codehaus.groovy.reflection.stdclasses.FloatCachedClass;
import org.codehaus.groovy.reflection.stdclasses.LongCachedClass;
import org.codehaus.groovy.reflection.stdclasses.BigDecimalCachedClass;
import java.math.BigDecimal;
import org.codehaus.groovy.reflection.stdclasses.DoubleCachedClass;
import org.codehaus.groovy.reflection.stdclasses.IntegerCachedClass;
import org.codehaus.groovy.reflection.stdclasses.NumberCachedClass;
import org.codehaus.groovy.reflection.stdclasses.StringCachedClass;
import org.codehaus.groovy.reflection.stdclasses.ObjectCachedClass;
import groovy.lang.MetaClassRegistry;
import groovy.lang.ExpandoMetaClassCreationHandle;
import groovy.lang.GroovySystem;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import groovy.lang.ExpandoMetaClass;
import java.lang.ref.WeakReference;
import org.codehaus.groovy.util.ReferenceBundle;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.util.ManagedReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.util.LockableObject;
import java.util.Set;
import org.codehaus.groovy.util.ManagedConcurrentMap;

public class ClassInfo extends ManagedConcurrentMap.Entry<Class, ClassInfo>
{
    private static final Set<ClassInfo> modifiedExpandos;
    private final LazyCachedClassRef cachedClassRef;
    private final LazyClassLoaderRef artifactClassLoader;
    private final LockableObject lock;
    public final int hash;
    private volatile int version;
    private MetaClass strongMetaClass;
    private ManagedReference<MetaClass> weakMetaClass;
    MetaMethod[] dgmMetaMethods;
    MetaMethod[] newMetaMethods;
    private ManagedConcurrentMap perInstanceMetaClassMap;
    private static ReferenceBundle softBundle;
    private static ReferenceBundle weakBundle;
    private static final ClassInfoSet globalClassSet;
    private static final WeakReference<ThreadLocalMapHandler> localMapRef;
    
    ClassInfo(final ManagedConcurrentMap.Segment segment, final Class klazz, final int hash) {
        super(ClassInfo.softBundle, segment, klazz, hash);
        this.lock = new LockableObject();
        this.dgmMetaMethods = CachedClass.EMPTY;
        this.newMetaMethods = CachedClass.EMPTY;
        this.hash = hash;
        this.cachedClassRef = new LazyCachedClassRef(ClassInfo.softBundle, this);
        this.artifactClassLoader = new LazyClassLoaderRef(ClassInfo.softBundle, this);
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public void incVersion() {
        ++this.version;
    }
    
    public ExpandoMetaClass getModifiedExpando() {
        return (this.strongMetaClass == null) ? null : ((this.strongMetaClass instanceof ExpandoMetaClass) ? ((ExpandoMetaClass)this.strongMetaClass) : null);
    }
    
    public static void clearModifiedExpandos() {
        final Iterator<ClassInfo> it = ClassInfo.modifiedExpandos.iterator();
        while (it.hasNext()) {
            final ClassInfo info = it.next();
            it.remove();
            info.setStrongMetaClass(null);
        }
    }
    
    public CachedClass getCachedClass() {
        return (CachedClass)this.cachedClassRef.get();
    }
    
    public ClassLoaderForClassArtifacts getArtifactClassLoader() {
        return (ClassLoaderForClassArtifacts)this.artifactClassLoader.get();
    }
    
    public static ClassInfo getClassInfo(final Class cls) {
        final ThreadLocalMapHandler handler = ClassInfo.localMapRef.get();
        SoftReference<LocalMap> ref = null;
        if (handler != null) {
            ref = handler.get();
        }
        LocalMap map = null;
        if (ref != null) {
            map = ref.get();
        }
        if (map != null) {
            return map.get(cls);
        }
        return (ClassInfo)ClassInfo.globalClassSet.getOrPut(cls, null);
    }
    
    public MetaClass getStrongMetaClass() {
        return this.strongMetaClass;
    }
    
    public void setStrongMetaClass(final MetaClass answer) {
        ++this.version;
        if (this.strongMetaClass instanceof ExpandoMetaClass) {
            ((ExpandoMetaClass)this.strongMetaClass).inRegistry = false;
            ClassInfo.modifiedExpandos.remove(this);
        }
        this.strongMetaClass = answer;
        if (this.strongMetaClass instanceof ExpandoMetaClass) {
            ((ExpandoMetaClass)this.strongMetaClass).inRegistry = true;
            ClassInfo.modifiedExpandos.add(this);
        }
        this.weakMetaClass = null;
    }
    
    public MetaClass getWeakMetaClass() {
        return (this.weakMetaClass == null) ? null : this.weakMetaClass.get();
    }
    
    public void setWeakMetaClass(final MetaClass answer) {
        ++this.version;
        this.strongMetaClass = null;
        if (answer == null) {
            this.weakMetaClass = null;
        }
        else {
            this.weakMetaClass = new ManagedReference<MetaClass>(ClassInfo.softBundle, answer);
        }
    }
    
    public MetaClass getMetaClassForClass() {
        return (this.strongMetaClass != null) ? this.strongMetaClass : ((this.weakMetaClass == null) ? null : this.weakMetaClass.get());
    }
    
    private MetaClass getMetaClassUnderLock() {
        MetaClass answer = this.getStrongMetaClass();
        if (answer != null) {
            return answer;
        }
        answer = this.getWeakMetaClass();
        final MetaClassRegistry metaClassRegistry = GroovySystem.getMetaClassRegistry();
        final MetaClassRegistry.MetaClassCreationHandle mccHandle = metaClassRegistry.getMetaClassCreationHandler();
        if (answer != null) {
            final boolean enableGloballyOn = mccHandle instanceof ExpandoMetaClassCreationHandle;
            final boolean cachedAnswerIsEMC = answer instanceof ExpandoMetaClass;
            if (!enableGloballyOn || cachedAnswerIsEMC) {
                return answer;
            }
        }
        answer = mccHandle.create(((ManagedReference<Class>)this).get(), metaClassRegistry);
        answer.initialize();
        if (GroovySystem.isKeepJavaMetaClasses()) {
            this.setStrongMetaClass(answer);
        }
        else {
            this.setWeakMetaClass(answer);
        }
        return answer;
    }
    
    public final MetaClass getMetaClass() {
        final MetaClass answer = this.getMetaClassForClass();
        if (answer != null) {
            return answer;
        }
        this.lock();
        try {
            return this.getMetaClassUnderLock();
        }
        finally {
            this.unlock();
        }
    }
    
    public MetaClass getMetaClass(final Object obj) {
        final MetaClass instanceMetaClass = this.getPerInstanceMetaClass(obj);
        if (instanceMetaClass != null) {
            return instanceMetaClass;
        }
        this.lock();
        try {
            return this.getMetaClassUnderLock();
        }
        finally {
            this.unlock();
        }
    }
    
    public static int size() {
        return ClassInfo.globalClassSet.size();
    }
    
    public static int fullSize() {
        return ClassInfo.globalClassSet.fullSize();
    }
    
    @Override
    public void finalizeRef() {
        this.setStrongMetaClass(null);
        this.cachedClassRef.clear();
        this.artifactClassLoader.clear();
        super.finalizeRef();
    }
    
    private static CachedClass createCachedClass(final Class klazz, final ClassInfo classInfo) {
        if (klazz == Object.class) {
            return new ObjectCachedClass(classInfo);
        }
        if (klazz == String.class) {
            return new StringCachedClass(classInfo);
        }
        CachedClass cachedClass;
        if (Number.class.isAssignableFrom(klazz) || klazz.isPrimitive()) {
            if (klazz == Number.class) {
                cachedClass = new NumberCachedClass(klazz, classInfo);
            }
            else if (klazz == Integer.class || klazz == Integer.TYPE) {
                cachedClass = new IntegerCachedClass(klazz, classInfo, klazz == Integer.class);
            }
            else if (klazz == Double.class || klazz == Double.TYPE) {
                cachedClass = new DoubleCachedClass(klazz, classInfo, klazz == Double.class);
            }
            else if (klazz == BigDecimal.class) {
                cachedClass = new BigDecimalCachedClass(klazz, classInfo);
            }
            else if (klazz == Long.class || klazz == Long.TYPE) {
                cachedClass = new LongCachedClass(klazz, classInfo, klazz == Long.class);
            }
            else if (klazz == Float.class || klazz == Float.TYPE) {
                cachedClass = new FloatCachedClass(klazz, classInfo, klazz == Float.class);
            }
            else if (klazz == Short.class || klazz == Short.TYPE) {
                cachedClass = new ShortCachedClass(klazz, classInfo, klazz == Short.class);
            }
            else if (klazz == Boolean.TYPE) {
                cachedClass = new BooleanCachedClass(klazz, classInfo, false);
            }
            else if (klazz == Character.TYPE) {
                cachedClass = new CharacterCachedClass(klazz, classInfo, false);
            }
            else if (klazz == BigInteger.class) {
                cachedClass = new BigIntegerCachedClass(klazz, classInfo);
            }
            else if (klazz == Byte.class || klazz == Byte.TYPE) {
                cachedClass = new ByteCachedClass(klazz, classInfo, klazz == Byte.class);
            }
            else {
                cachedClass = new CachedClass(klazz, classInfo);
            }
        }
        else if (klazz.getName().charAt(0) == '[') {
            cachedClass = new ArrayCachedClass(klazz, classInfo);
        }
        else if (klazz == Boolean.class) {
            cachedClass = new BooleanCachedClass(klazz, classInfo, true);
        }
        else if (klazz == Character.class) {
            cachedClass = new CharacterCachedClass(klazz, classInfo, true);
        }
        else if (Closure.class.isAssignableFrom(klazz)) {
            cachedClass = new CachedClosureClass(klazz, classInfo);
        }
        else {
            cachedClass = new CachedClass(klazz, classInfo);
        }
        return cachedClass;
    }
    
    public void lock() {
        this.lock.lock();
    }
    
    public void unlock() {
        this.lock.unlock();
    }
    
    public MetaClass getPerInstanceMetaClass(final Object obj) {
        if (this.perInstanceMetaClassMap == null) {
            return null;
        }
        return (MetaClass)this.perInstanceMetaClassMap.get(obj);
    }
    
    public void setPerInstanceMetaClass(final Object obj, final MetaClass metaClass) {
        ++this.version;
        if (metaClass != null) {
            if (this.perInstanceMetaClassMap == null) {
                this.perInstanceMetaClassMap = new ManagedConcurrentMap(ReferenceBundle.getWeakBundle());
            }
            this.perInstanceMetaClassMap.put(obj, metaClass);
        }
        else if (this.perInstanceMetaClassMap != null) {
            this.perInstanceMetaClassMap.remove(obj);
        }
    }
    
    public boolean hasPerInstanceMetaClasses() {
        return this.perInstanceMetaClassMap != null;
    }
    
    static {
        modifiedExpandos = new HashSet<ClassInfo>();
        ClassInfo.softBundle = ReferenceBundle.getSoftBundle();
        ClassInfo.weakBundle = ReferenceBundle.getWeakBundle();
        globalClassSet = new ClassInfoSet(ClassInfo.softBundle);
        final ThreadLocalMapHandler localMap = new ThreadLocalMapHandler();
        localMapRef = new WeakReference<ThreadLocalMapHandler>(localMap, null);
    }
    
    public static class ClassInfoSet extends ManagedConcurrentMap<Class, ClassInfo>
    {
        public ClassInfoSet(final ReferenceBundle bundle) {
            super(bundle);
        }
        
        @Override
        protected Segment createSegment(final Object segmentInfo, final int cap) {
            final ReferenceBundle bundle = (ReferenceBundle)segmentInfo;
            if (bundle == null) {
                throw new IllegalArgumentException("bundle must not be null ");
            }
            return new Segment(bundle, cap);
        }
        
        static final class Segment extends ManagedConcurrentMap.Segment<Class, ClassInfo>
        {
            Segment(final ReferenceBundle bundle, final int initialCapacity) {
                super(bundle, initialCapacity);
            }
            
            @Override
            protected ClassInfo createEntry(final Class key, final int hash, final ClassInfo unused) {
                return new ClassInfo(this, key, hash);
            }
        }
    }
    
    private static final class LocalMap extends HashMap<Class, ClassInfo>
    {
        private static final int CACHE_SIZE = 5;
        private final PhantomReference<Thread> myThread;
        private int nextCacheEntry;
        private final ClassInfo[] cache;
        private static final ClassInfo NOINFO;
        
        private LocalMap() {
            this.myThread = new PhantomReference<Thread>(Thread.currentThread(), null);
            this.cache = new ClassInfo[5];
            for (int i = 0; i < this.cache.length; ++i) {
                this.cache[i] = LocalMap.NOINFO;
            }
        }
        
        public ClassInfo get(final Class key) {
            ClassInfo info = this.getFromCache(key);
            if (info != null) {
                return info;
            }
            info = super.get(key);
            if (info != null) {
                return this.putToCache(info);
            }
            return this.putToCache((ClassInfo)ClassInfo.globalClassSet.getOrPut(key, null));
        }
        
        private ClassInfo getFromCache(final Class klazz) {
            for (int i = 0, k = this.nextCacheEntry - 1; i < this.cache.length; ++i, --k) {
                if (k < 0) {
                    k += 5;
                }
                final ClassInfo info = this.cache[k];
                if (klazz == ((ManagedReference<Class>)info).get()) {
                    this.nextCacheEntry = k + 1;
                    if (this.nextCacheEntry == 5) {
                        this.nextCacheEntry = 0;
                    }
                    return info;
                }
            }
            return null;
        }
        
        private ClassInfo putToCache(final ClassInfo classInfo) {
            this.cache[this.nextCacheEntry++] = classInfo;
            if (this.nextCacheEntry == 5) {
                this.nextCacheEntry = 0;
            }
            return classInfo;
        }
        
        static {
            NOINFO = new ClassInfo(null, null, 0);
        }
    }
    
    private static class ThreadLocalMapHandler extends ThreadLocal<SoftReference<LocalMap>>
    {
        SoftReference<LocalMap> recentThreadMapRef;
        
        @Override
        protected SoftReference<LocalMap> initialValue() {
            return new SoftReference<LocalMap>(new LocalMap(), null);
        }
        
        @Override
        public SoftReference<LocalMap> get() {
            final SoftReference<LocalMap> mapRef = this.recentThreadMapRef;
            LocalMap recent = null;
            if (mapRef != null) {
                recent = mapRef.get();
            }
            if (recent != null && recent.myThread.get() == Thread.currentThread()) {
                return mapRef;
            }
            final SoftReference<LocalMap> ref = super.get();
            return this.recentThreadMapRef = ref;
        }
    }
    
    private static class LazyCachedClassRef extends LazyReference<CachedClass>
    {
        private final ClassInfo info;
        
        LazyCachedClassRef(final ReferenceBundle bundle, final ClassInfo info) {
            super(bundle);
            this.info = info;
        }
        
        @Override
        public CachedClass initValue() {
            return createCachedClass(((ManagedReference<Class>)this.info).get(), this.info);
        }
    }
    
    private static class LazyClassLoaderRef extends LazyReference<ClassLoaderForClassArtifacts>
    {
        private final ClassInfo info;
        
        LazyClassLoaderRef(final ReferenceBundle bundle, final ClassInfo info) {
            super(bundle);
            this.info = info;
        }
        
        @Override
        public ClassLoaderForClassArtifacts initValue() {
            return new ClassLoaderForClassArtifacts(((ManagedReference<Class>)this.info).get());
        }
    }
    
    private static class DebugRef extends ManagedReference<Class>
    {
        public static final boolean debug = false;
        private static final AtomicInteger count;
        final String name;
        
        public DebugRef(final Class klazz) {
            super(ClassInfo.softBundle, klazz);
            this.name = ((klazz == null) ? "<null>" : klazz.getName());
            DebugRef.count.incrementAndGet();
        }
        
        public void finalizeRef() {
            System.out.println(this.name + " unloaded " + DebugRef.count.decrementAndGet() + " classes kept");
            super.finalizeReference();
        }
        
        static {
            count = new AtomicInteger();
        }
    }
}
