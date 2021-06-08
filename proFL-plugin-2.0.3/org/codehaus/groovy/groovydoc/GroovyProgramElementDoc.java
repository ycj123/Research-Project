// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.groovydoc;

public interface GroovyProgramElementDoc extends GroovyDoc
{
    GroovyAnnotationRef[] annotations();
    
    GroovyClassDoc containingClass();
    
    GroovyPackageDoc containingPackage();
    
    boolean isFinal();
    
    boolean isPackagePrivate();
    
    boolean isPrivate();
    
    boolean isProtected();
    
    boolean isPublic();
    
    boolean isStatic();
    
    String modifiers();
    
    int modifierSpecifier();
    
    String qualifiedName();
}
