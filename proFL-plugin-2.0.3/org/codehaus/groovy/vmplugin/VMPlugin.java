// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin;

import org.codehaus.groovy.ast.CompileUnit;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassNode;

public interface VMPlugin
{
    void setAdditionalClassInformation(final ClassNode p0);
    
    Class[] getPluginDefaultGroovyMethods();
    
    void configureAnnotation(final AnnotationNode p0);
    
    void configureClassNode(final CompileUnit p0, final ClassNode p1);
}
