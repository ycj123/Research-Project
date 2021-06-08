// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.reflection.ClassInfo;

public class ExpandoMetaClassCreationHandle extends MetaClassRegistry.MetaClassCreationHandle
{
    public static final ExpandoMetaClassCreationHandle instance;
    
    @Override
    protected MetaClass createNormalMetaClass(final Class theClass, final MetaClassRegistry registry) {
        if (theClass != ExpandoMetaClass.class) {
            return new ExpandoMetaClass(theClass, true, true);
        }
        return super.createNormalMetaClass(theClass, registry);
    }
    
    public void registerModifiedMetaClass(final ExpandoMetaClass emc) {
        final Class klazz = emc.getJavaClass();
        GroovySystem.getMetaClassRegistry().setMetaClass(klazz, emc);
    }
    
    public boolean hasModifiedMetaClass(final ExpandoMetaClass emc) {
        return emc.getClassInfo().getModifiedExpando() != null;
    }
    
    public static void enable() {
        final MetaClassRegistry metaClassRegistry = GroovySystem.getMetaClassRegistry();
        synchronized (metaClassRegistry) {
            if (metaClassRegistry.getMetaClassCreationHandler() != ExpandoMetaClassCreationHandle.instance) {
                ClassInfo.clearModifiedExpandos();
                metaClassRegistry.setMetaClassCreationHandle(ExpandoMetaClassCreationHandle.instance);
            }
        }
    }
    
    public static void disable() {
        final MetaClassRegistry metaClassRegistry = GroovySystem.getMetaClassRegistry();
        synchronized (metaClassRegistry) {
            if (metaClassRegistry.getMetaClassCreationHandler() == ExpandoMetaClassCreationHandle.instance) {
                ClassInfo.clearModifiedExpandos();
                metaClassRegistry.setMetaClassCreationHandle(new MetaClassRegistry.MetaClassCreationHandle());
            }
        }
    }
    
    static {
        instance = new ExpandoMetaClassCreationHandle();
    }
}
