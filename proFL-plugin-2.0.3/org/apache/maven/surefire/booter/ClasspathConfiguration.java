// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

public class ClasspathConfiguration
{
    public static final String CHILD_DELEGATION = "childDelegation";
    public static final String ENABLE_ASSERTIONS = "enableAssertions";
    public static final String CLASSPATH = "classPathUrl.";
    public static final String SUREFIRE_CLASSPATH = "surefireClassPathUrl.";
    private final Classpath classpathUrls;
    private final Classpath surefireClasspathUrls;
    private final Classpath inprocClasspath;
    private final boolean enableAssertions;
    private final boolean childDelegation;
    
    public ClasspathConfiguration(final boolean enableAssertions, final boolean childDelegation) {
        this(Classpath.emptyClasspath(), Classpath.emptyClasspath(), Classpath.emptyClasspath(), enableAssertions, childDelegation);
    }
    
    ClasspathConfiguration(final PropertiesWrapper properties) {
        this(properties.getClasspath("classPathUrl."), properties.getClasspath("surefireClassPathUrl."), Classpath.emptyClasspath(), properties.getBooleanProperty("enableAssertions"), properties.getBooleanProperty("childDelegation"));
    }
    
    public ClasspathConfiguration(final Classpath testClasspath, final Classpath surefireClassPathUrls, final Classpath inprocClasspath, final boolean enableAssertions, final boolean childDelegation) {
        this.enableAssertions = enableAssertions;
        this.childDelegation = childDelegation;
        this.inprocClasspath = inprocClasspath;
        this.classpathUrls = testClasspath;
        this.surefireClasspathUrls = surefireClassPathUrls;
    }
    
    public ClassLoader createMergedClassLoader() throws SurefireExecutionException {
        return Classpath.join(this.inprocClasspath, this.classpathUrls).createClassLoader(null, this.childDelegation, this.enableAssertions, "test");
    }
    
    public Classpath getProviderClasspath() {
        return this.surefireClasspathUrls;
    }
    
    public Classpath getTestClasspath() {
        return this.classpathUrls;
    }
    
    public void trickClassPathWhenManifestOnlyClasspath() throws SurefireExecutionException {
        System.setProperty("surefire.real.class.path", System.getProperty("java.class.path"));
        this.getTestClasspath().writeToSystemProperty("java.class.path");
    }
    
    public boolean isEnableAssertions() {
        return this.enableAssertions;
    }
    
    public boolean isChildDelegation() {
        return this.childDelegation;
    }
}
