// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.groovydoc;

public interface GroovyParameter
{
    GroovyAnnotationRef[] annotations();
    
    String name();
    
    GroovyType type();
    
    String typeName();
    
    String defaultValue();
}
