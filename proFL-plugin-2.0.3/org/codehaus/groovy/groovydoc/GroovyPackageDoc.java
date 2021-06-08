// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.groovydoc;

public interface GroovyPackageDoc extends GroovyDoc
{
    GroovyClassDoc[] allClasses();
    
    GroovyClassDoc[] allClasses(final boolean p0);
    
    GroovyClassDoc[] enums();
    
    GroovyClassDoc[] errors();
    
    GroovyClassDoc[] exceptions();
    
    GroovyClassDoc findClass(final String p0);
    
    GroovyClassDoc[] interfaces();
    
    GroovyClassDoc[] ordinaryClasses();
    
    String summary();
    
    String description();
    
    String nameWithDots();
    
    String getRelativeRootPath();
}
