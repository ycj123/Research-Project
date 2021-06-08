// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import javax.annotation.Nonnull;
import org.apache.maven.surefire.booter.Classpath;
import java.util.concurrent.ConcurrentHashMap;

public class ClasspathCache
{
    private static final ConcurrentHashMap<String, Classpath> classpaths;
    
    public static Classpath getCachedClassPath(@Nonnull final String artifactId) {
        return ClasspathCache.classpaths.get(artifactId);
    }
    
    public static void setCachedClasspath(@Nonnull final String key, @Nonnull final Classpath classpath) {
        ClasspathCache.classpaths.put(key, classpath);
    }
    
    static {
        classpaths = new ConcurrentHashMap<String, Classpath>(4);
    }
}
