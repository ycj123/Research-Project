// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

public class ClassLoaderConfiguration
{
    private final boolean useSystemClassLoader;
    private final boolean useManifestOnlyJar;
    
    public ClassLoaderConfiguration(final boolean useSystemClassLoader, final boolean useManifestOnlyJar) {
        this.useSystemClassLoader = useSystemClassLoader;
        this.useManifestOnlyJar = useManifestOnlyJar;
    }
    
    public boolean isUseSystemClassLoader() {
        return this.useSystemClassLoader;
    }
    
    public boolean isUseManifestOnlyJar() {
        return this.useManifestOnlyJar;
    }
    
    public boolean isManifestOnlyJarRequestedAndUsable() {
        return this.isUseSystemClassLoader() && this.useManifestOnlyJar;
    }
}
