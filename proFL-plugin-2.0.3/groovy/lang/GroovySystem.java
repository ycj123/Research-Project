// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.metaclass.MetaClassRegistryImpl;
import org.codehaus.groovy.util.ReleaseInfo;
import org.codehaus.groovy.util.ReferenceBundle;

public final class GroovySystem
{
    private static MetaClass objectMetaClass;
    private static final boolean USE_REFLECTION;
    private static final MetaClassRegistry META_CLASS_REGISTRY;
    private static boolean keepJavaMetaClasses;
    
    private GroovySystem() {
    }
    
    public static boolean isUseReflection() {
        return GroovySystem.USE_REFLECTION;
    }
    
    public static MetaClassRegistry getMetaClassRegistry() {
        return GroovySystem.META_CLASS_REGISTRY;
    }
    
    public static void setKeepJavaMetaClasses(final boolean keepJavaMetaClasses) {
        GroovySystem.keepJavaMetaClasses = keepJavaMetaClasses;
    }
    
    public static boolean isKeepJavaMetaClasses() {
        return GroovySystem.keepJavaMetaClasses;
    }
    
    public static void stopThreadedReferenceManager() {
        ReferenceBundle.getSoftBundle().getManager().stopThread();
        ReferenceBundle.getWeakBundle().getManager().stopThread();
    }
    
    public static String getVersion() {
        return ReleaseInfo.getVersion();
    }
    
    static {
        USE_REFLECTION = true;
        META_CLASS_REGISTRY = new MetaClassRegistryImpl();
        GroovySystem.keepJavaMetaClasses = false;
    }
}
