// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

public interface GroovyClassVisitor
{
    void visitClass(final ClassNode p0);
    
    void visitConstructor(final ConstructorNode p0);
    
    void visitMethod(final MethodNode p0);
    
    void visitField(final FieldNode p0);
    
    void visitProperty(final PropertyNode p0);
}
