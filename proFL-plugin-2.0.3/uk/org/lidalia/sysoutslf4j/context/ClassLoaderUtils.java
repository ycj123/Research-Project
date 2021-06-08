// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import uk.org.lidalia.sysoutslf4j.common.WrappedCheckedException;

final class ClassLoaderUtils
{
    static Class<?> loadClass(final ClassLoader classLoader, final Class<?> classToLoad) {
        try {
            return classLoader.loadClass(classToLoad.getName());
        }
        catch (ClassNotFoundException cne) {
            throw new WrappedCheckedException(cne);
        }
    }
    
    private ClassLoaderUtils() {
        throw new UnsupportedOperationException("Not instantiable");
    }
}
