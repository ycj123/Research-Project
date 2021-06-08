// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.metaclass.ClosureMetaClass;
import org.codehaus.groovy.runtime.GeneratedClosure;
import java.lang.reflect.Constructor;
import java.util.Iterator;

public interface MetaClassRegistry
{
    MetaClass getMetaClass(final Class p0);
    
    void setMetaClass(final Class p0, final MetaClass p1);
    
    void removeMetaClass(final Class p0);
    
    MetaClassCreationHandle getMetaClassCreationHandler();
    
    void setMetaClassCreationHandle(final MetaClassCreationHandle p0);
    
    void addMetaClassRegistryChangeEventListener(final MetaClassRegistryChangeEventListener p0);
    
    void removeMetaClassRegistryChangeEventListener(final MetaClassRegistryChangeEventListener p0);
    
    MetaClassRegistryChangeEventListener[] getMetaClassRegistryChangeEventListeners();
    
    Iterator iterator();
    
    public static class MetaClassCreationHandle
    {
        private boolean disableCustomMetaClassLookup;
        
        public final MetaClass create(final Class theClass, final MetaClassRegistry registry) {
            if (this.disableCustomMetaClassLookup) {
                return this.createNormalMetaClass(theClass, registry);
            }
            return this.createWithCustomLookup(theClass, registry);
        }
        
        private MetaClass createWithCustomLookup(final Class theClass, final MetaClassRegistry registry) {
            try {
                final Class customMetaClass = Class.forName("groovy.runtime.metaclass." + theClass.getName() + "MetaClass");
                if (DelegatingMetaClass.class.isAssignableFrom(customMetaClass)) {
                    final Constructor customMetaClassConstructor = customMetaClass.getConstructor(MetaClass.class);
                    final MetaClass normalMetaClass = this.createNormalMetaClass(theClass, registry);
                    return customMetaClassConstructor.newInstance(normalMetaClass);
                }
                final Constructor customMetaClassConstructor = customMetaClass.getConstructor(MetaClassRegistry.class, Class.class);
                return customMetaClassConstructor.newInstance(registry, theClass);
            }
            catch (ClassNotFoundException e2) {
                return this.createNormalMetaClass(theClass, registry);
            }
            catch (Exception e) {
                throw new GroovyRuntimeException("Could not instantiate custom Metaclass for class: " + theClass.getName() + ". Reason: " + e, e);
            }
        }
        
        protected MetaClass createNormalMetaClass(final Class theClass, final MetaClassRegistry registry) {
            if (GeneratedClosure.class.isAssignableFrom(theClass)) {
                return new ClosureMetaClass(registry, theClass);
            }
            return new MetaClassImpl(registry, theClass);
        }
        
        public boolean isDisableCustomMetaClassLookup() {
            return this.disableCustomMetaClassLookup;
        }
        
        public void setDisableCustomMetaClassLookup(final boolean disableCustomMetaClassLookup) {
            this.disableCustomMetaClassLookup = disableCustomMetaClassLookup;
        }
    }
}
