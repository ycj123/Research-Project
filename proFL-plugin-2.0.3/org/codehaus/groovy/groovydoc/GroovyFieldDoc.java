// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.groovydoc;

public interface GroovyFieldDoc extends GroovyMemberDoc
{
    Object constantValue();
    
    String constantValueExpression();
    
    boolean isTransient();
    
    boolean isVolatile();
    
    GroovyType type();
}
