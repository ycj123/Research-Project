// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.groovydoc;

public interface GroovyExecutableMemberDoc extends GroovyMemberDoc
{
    String flatSignature();
    
    boolean isNative();
    
    boolean isSynchronized();
    
    boolean isVarArgs();
    
    GroovyParameter[] parameters();
    
    String signature();
    
    GroovyClassDoc[] thrownExceptions();
    
    GroovyType[] thrownExceptionTypes();
}
