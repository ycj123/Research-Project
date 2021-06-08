// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.groovydoc;

public interface GroovyMethodDoc extends GroovyExecutableMemberDoc
{
    boolean isAbstract();
    
    GroovyClassDoc overriddenClass();
    
    GroovyMethodDoc overriddenMethod();
    
    GroovyType overriddenType();
    
    boolean overrides(final GroovyMethodDoc p0);
    
    GroovyType returnType();
    
    void setReturnType(final GroovyType p0);
}
